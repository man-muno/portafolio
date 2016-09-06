package uniandes.cumbia.bpel.messagecenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.bpel.engine.client.BPELClient;
import uniandes.cumbia.bpel.engine.persistance.IPersistenceManager;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.instance.InstanceSpace;
import uniandes.cumbia.bpel.process.ProcessSpace;
import uniandes.cumbia.caffeine.deployer.deposit.BPELProcessInfo;
import uniandes.cumbia.caffeine.deployer.utils.ConsecutiveIdGenerator;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.utils.FileSystemUtils;

public class MessageCenter implements IMessageCenter
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String AXIS_2 = "axis2";

    private static final String WEB_INFO_DIRECTORY = "WEB-INF";

    private static final String SERVICES_DIRECTORY = "services";

    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * The bpel engine
     */
    private IProcessEngine bpelEngine;

    /**
     * The directory of work for deploying the ws
     */
    private transient File workDirectory;

    /**
     * The list of process published
     */
    private List<String> processesPublished;

    /**
     * The services installed
     */
    private Map<Integer, IWsdlService> services;

    /**
     * The id generator for the web services
     */
    private ConsecutiveIdGenerator webServiceIdGenerator;

    /**
     * Indicates if the processes alredy was deployed in axis
     */
    private boolean processesPublishedInAxis;

    /**
     * The deposit where the process specifications are located
     */
    private IPersistenceManager persistenceManager;

    /**
     * The synchronous process running
     */
    private transient List<ExecuteSynchronousServices> threadsSynchronousServices;

    /**
     * The information of the receive realizate
     */
    private List<InvocationInformation> receivesInformation;

    /**
     * The information of the invokes realizate
     */
    private List<InvocationInformation> invokesInformation;

    /**
     * List of the messages not expected by the message center
     */
    private List<Object> messagesNotExpected;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Constructor of the central
     * @param be The bpel engine
     * @param wDir The directoy for deploying the web services
     */
    public MessageCenter( File wDir, IPersistenceManager manager )
    {
        workDirectory = wDir;
        processesPublished = new Vector<String>( );
        services = new Hashtable<Integer, IWsdlService>( );
        webServiceIdGenerator = new ConsecutiveIdGenerator( );
        processesPublishedInAxis = false;
        persistenceManager = manager;
        threadsSynchronousServices = new Vector<ExecuteSynchronousServices>( );
        receivesInformation = new Vector<InvocationInformation>( );
        invokesInformation = new Vector<InvocationInformation>( );
        messagesNotExpected = new Vector<Object>( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Creates a new web services in the message center
     * @return The id of the web service create
     */
    public int createNewService( IWsdlService service )
    {
        int id = webServiceIdGenerator.nextId( );
        service.setId( id );
        services.put( id, service );
        return id;
    }

    /**
     * Execute an asynchronous call
     * @param serviceId The id of the services associated with the call
     * @param message The message to be processed
     * @param loader The classloader for process the call
     * @param instanceId The instance id
     * @param processId The process id
     * @param clientUri Uri of the client
     * @return The response
     */
    public void executeAsynchronousCall( int serviceId, Object message, ClassLoader loader, int instanceId, int processId, String clientUri )
    {
        IWsdlService service = services.get( serviceId );
        String processName = service.getProcessName( );
        executeAsynchronousCall( processName, message, loader, serviceId, new InstanceId( processId, instanceId ), clientUri );
    }

    /**
     * Executes an asynchronous call
     * @param processName The process name
     * @param message The message
     * @param loader The classloader for process the call
     * @param serviceId the id of the service
     * @param instanceId The instance id
     * @param clientUri Uri of the client
     * @return The response The response
     * @throws InstantiationException
     */
    public void executeAsynchronousCall( String processName, Object message, ClassLoader loader, int serviceId, InstanceId instanceId, String clientUri )
    {
        // ////System.out.println( "PASOOOOOOOOOOOOOOO1" );
        ProcessSpace ps = getProcessSpace( processName );
        // ////System.out.println( "PASOOOOOOOOOOOOOOO1" + ps );
        IProcess process = null;
        InstanceSpace pi = null;
        if( ps != null && instanceId != null )
        {
            if( ps.getInstanceRegistry( ).getAll( ).size( ) > 0 )
            {
                /*InstanceSpace pir = ( InstanceSpace )pi;
                process = ( IProcess )pir.getInstance( ).getRoot( );*/
                pi = ps.getInstance( instanceId.getInstanceId( ) );
                process = ( IProcess )pi.getInstance( ).getRoot( );
                pi.setServiceId( serviceId );
                pi.setClientURI( clientUri );
            }
            else
            {
                try
                {
                    pi = ps.createInstance( );
                    pi.setServiceId( serviceId );
                    pi.setClientURI( clientUri );
                    process = ( IProcess )pi.getInstance( ).getRoot( );
                }
                catch( CumbiaException e )
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace( );
                }
                // ////System.out.println( "PASOOOOOOOOOOOOOOO3 pi==null" );
            }
        }
        else
        {
            // process= bpelEngine.createProcess(processName, loader, serviceId, clientUri);
            // ////System.out.println( "PASOOOOOOOOOOOOOOO3 ps==null && instanceId==null" );
        }

        process.getModelInstance( ).getRuntime( ).start( );
        try
        {
            if( !pi.isDataInitialized( ) )
                pi.initializeData( loader );
        }
        catch( CumbiaException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace( );
        }

        /* process.activateProcess( ); */

        boolean register = false;
        while( !register )
            register = registerMessageReceivedProcess( processName, message, process.getInstanceSpaceId( ) );
    }

    /**
     * Execute an synchronous call
     * @param id The id of the services associated with the call
     * @param message The message to be processed
     * @param loader The classloader for process the call
     * @param instanceId The instance id
     * @param processId The process id
     * @return The response
     */
    public Object executeSynchronousCall( int id, Object message, ClassLoader loader, int instanceId, int processId )
    {
        // ////System.out.println( "LLAMADA SINCRONICA executeSynchronousCall " + instanceId + " " + processId );
        IWsdlService service = services.get( id );
        // ////System.out.println( "LLAMADA SINCRONICA2 executeSynchronousCall " + service );
        String processName = service.getProcessName( );
        // ////System.out.println( "LLAMADA SINCRONICA3 executeSynchronousCall " + processName + " " + message );
        return executeSynchronousCall( processName, message, loader, id, new InstanceId( processId, instanceId ) );
    }

    /**
     * Executes an synchronous call
     * @param processName The process name
     * @param message The message
     * @param loader The classloader for process the call
     * @param serviceId the id of the service
     * @param instanceId The instance id
     * @return The response
     * @throws InstantiationException
     */
    public Object executeSynchronousCall( String processName, Object message, ClassLoader loader, int serviceId, InstanceId instanceId )
    {
        // ////System.out.println( "PASOOOOOOOOOOOOOOO1 " + processName );
        ProcessSpace ps = getProcessSpace( processName );
        // ////System.out.println( "PASOOOOOOOOOOOOOOO1" + ps + " instanceid " + instanceId.getInstanceId( ) );
        IProcess process = null;
        InstanceSpace pi = null;
        if( ps != null && instanceId != null )
        {
            if( instanceId.getInstanceId( ) != -1 )
            {
                // ////System.out.println( "Before getting instance space" );
                pi = ps.getInstance( instanceId.getInstanceId( ) );
                // ////System.out.println( "Instance Created " + pi );
                process = ( IProcess )pi.getInstance( ).getRoot( );
                // ////System.out.println( "PASOOOOOOOOOOOOOOO pi!=null" );
                pi.setServiceId( serviceId );
            }
            else
            {
                try
                {
                    pi = ps.createInstance( );
                    pi.setServiceId( serviceId );
                    process = ( IProcess )pi.getInstance( ).getRoot( );
                }
                catch( CumbiaException e )
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace( );
                }
                // ////System.out.println( "PASOOOOOOOOOOOOOOO3 pi==null" );
            }
        }
        else
        {
            // process= createProcess(processName, loader, serviceId, "");
            // ////System.out.println( "PASOOOOOOOOOOOOOOO3 ps==null && instanceId==null" );
        }

        try
        {
            if( !pi.isDataInitialized( ) )
                pi.initializeData( loader );
        }
        catch( CumbiaException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace( );
        }

        if( !process.getModelInstance( ).getRuntime( ).isInitialized( ) )
            process.getModelInstance( ).getRuntime( ).start( );
        // ////System.out.println( "PASOOOOOOOOOOOOOOO2 after start" );
        try
        {
            Thread.sleep( 1000 );
        }
        catch( InterruptedException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace( );
        }

        // Waits for the response
        ExecuteSynchronousServices thread = new ExecuteSynchronousServices( pi.getId( ) );
        ////System.out.println( "PASOOOOOOOOOOOOOOO2" );
        threadsSynchronousServices.add( thread );
        ////System.out.println( "PASOOOOOOOOOOOOOOO4" );
        int trys = 0;
        boolean register = false;
        while( !register && trys<11)
        {
            register = registerMessageReceivedProcess( processName, message, pi.getId( ) );
            trys++;
        }
            
        if( register )
        {
            ////System.out.println( "PASOOOOOOOOOOOOOOO51" );
            thread.run( );
            ////System.out.println( "PASOOOOOOOOOOOOOOO6" );
            ////System.out.println( "---------------LA RESPUESTA ES " + thread.getResponse( ) );
            return thread.getResponse( );
        }
        else
            return null;
    }

    /**
     * Processes asynchronous response
     * @param id The id of the services associated with the response
     * @param response The response
     * @param instanceId The instance id
     * @param processId The process id
     */
    public void processAsynchronousResponse( int id, Object response, int instanceId, int processId )
    {
        IWsdlService service = services.get( id );
        String processName = service.getProcessName( );
        registerMessageReceivedProcess( processName, response, new InstanceId( processId, instanceId ) );
    }

    /**
     * Register a message receive for a synchronous process
     * @param processName The process name
     * @param message The message
     * @param instanceId The instance id
     */
    public boolean registerMessageReceivedProcess( String processName, Object message, InstanceId instanceId )
    {
        ProcessSpace processSpace = ( ProcessSpace )getProcessSpace( processName );
        boolean finish = false;
        synchronized( receivesInformation )
        {
            for( int i = 0; i < receivesInformation.size( ) && !finish; i++ )
            {
                InvocationInformation receiveInfo = receivesInformation.get( i );
                InstanceId id = receiveInfo.getInstanceId( );

                if( id.getInstanceId( ) == instanceId.getInstanceId( ) && id.getProcessId( ) == instanceId.getProcessId( ) )
                {
                    InstanceSpace is = processSpace.getInstance( instanceId.getInstanceId( ) );
                    // ////System.out.println( "ReceiveInfo : " + receiveInfo.getElement( ) );
                    IInteraction receive = receiveInfo.getElement( );
                    // ////System.out.println( "ReceiveInfo : " + receive );
                    receive.notifyMessageReceived( message );
                    receivesInformation.remove( i );
                    finish = true;
                    messagesNotExpected.remove( message );
                    // ////System.out.println( "Message Received" );
                }
            }

            if( !finish && !messagesNotExpected.contains( message ) )
            {
                messagesNotExpected.add( message );
            }
        }

        return finish;
    }

    /**
     * Invokes an asynchronous call
     * @param serviceId The service id
     * @param partnerLinkUri The uri of the partner link
     * @param message The message to be sent
     * @param portTypeName The port for making the invocation
     * @param operationName The operation name to be invoked
     * @param id The id of the process instance
     */
    public void invokeAsynchronousCall( int serviceId, String partnerLinkUri, Object message, String portTypeName, String operationName, InstanceId id )
    {
        IWsdlService service = services.get( serviceId );
        service.invokeAsynchronusService( message, partnerLinkUri, operationName, id.getInstanceId( ), id.getProcessId( ) );
    }

    /**
     * Execute a synchronous call
     * @param serviceId The service id
     * @param partnerLinkUri The uri of the partner link
     * @param message The message to be sent
     * @param portTypeName The port for making the invocation
     * @param operationName The operation name to be invoked
     * @param id The id of the process instance
     * @return The response of the invocation
     */
    public Object invokeSynchronousCall( int serviceId, String partnerLinkUri, Object message, String portTypeName, String operationName, InstanceId id )
    {
        IWsdlService service = services.get( serviceId );
        Object resp = service.invokeSynchronusService( message, partnerLinkUri, operationName, id.getInstanceId( ), id.getProcessId( ) );
        return resp;
    }

    /**
     * Public the processes as web services
     * @throws CumbiaException
     * @throws MessageCenterException If there is any problem publishing the processes as ws
     */
    protected void publishProcessesAsWebServices( ) throws CumbiaException
    {
        List processes = persistenceManager.getStoredProcesses( );
        List<File> axisDirectories = getAxisDirectories( );

        for( int i = 0; i < processes.size( ); i++ )
        {
            BPELProcessInfo pInfo = ( BPELProcessInfo )processes.get( i );
            // ////System.out.println( "LLEGO A BUSCAR EL ARCHIVO AXIS" );
            File axisFile = pInfo.getAxisFile( );
            // ////System.out.println( "CONSIGUIO EL ARCHIVO AXIS" );
            // Install the directory in all the folders that contains the string "axis2"
            int timesCopies = 0;
            for( int j = 0; j < axisDirectories.size( ); j++ )
            {
                File actual = axisDirectories.get( j );
                File servicesDirectory = new File( actual, WEB_INFO_DIRECTORY + File.separator + SERVICES_DIRECTORY );

                if( servicesDirectory.exists( ) )
                {
                    try
                    {
                        FileSystemUtils.copyFile( axisFile, servicesDirectory, "" );

                    }
                    catch( IOException e )
                    {
                        throw new CumbiaException( "Problems publishing the processes as web services" );
                    }

                    if( timesCopies == 0 )
                    {
                        processesPublished.add( pInfo.getProcessName( ) );
                    }
                    timesCopies++;
                }
            }
        }
        // ////System.out.println( "SALIO DE PUBLICAR LOS WS" );
    }

    /**
     * Returns the list of directories associated with axis
     * @return The list of files associated with axis
     */
    protected List<File> getAxisDirectories( )
    {
        File[] files = workDirectory.listFiles( );
        List<File> axisDirectories = new ArrayList<File>( );

        for( int i = 0; i < files.length; i++ )
        {
            if( files[ i ].isDirectory( ) && files[ i ].getName( ).contains( AXIS_2 ) )
            {
                axisDirectories.add( files[ i ] );
            }
        }

        return axisDirectories;
    }

    /**
     * Returns the bpel engine
     * @return The bpel engine
     */
    public IProcessEngine getBpelEngine( )
    {
        return bpelEngine;
    }

    /**
     * Changes the bpel engine
     * @param bEngine The new bpel engine
     */
    public void setBpelEngine( IProcessEngine bEngine )
    {
        bpelEngine = bEngine;
    }

    /**
     * Returns the process name associated with the bpel client
     * @param processName The process name
     * @return The bpel client
     */
    public BPELClient getBpelClient( String processName, String url )
    {
        return new BPELClient( processName, url );
    }

    /**
     * Returns the list of process instance
     * @param processName The name of the process
     * @return The list of process instances
     */
    public List<InstanceSpace> getProcessInstances( String processName )
    {
        return null;// bpelEngine.getProcessInstances(processName);
    }

    /**
     * Returns the process space with the specified
     * @param processName The process name
     * @return The process with the name specified
     */
    public ProcessSpace getProcessSpace( String processName )
    {
        return bpelEngine.getProcessSpaceByName( processName );
    }

    /**
     * Creates the process space of the specified process
     * @param processName The name of the process
     * @return The process space created
     */
    public ProcessSpace createProcessSpace( String processName, ClassLoader loader )
    {
        return null;// bpelEngine.createProcessSpace(processName, loader);
    }

    /**
     * Returns the names of bpel process published
     * @return The names of the bpel process
     * @throws CumbiaException
     */
    public List<String> getBPELProcessesNamesPublished( ) throws CumbiaException
    {
        if( !processesPublishedInAxis )
        {
            publishProcessesAsWebServices( );
            processesPublishedInAxis = true;
        }

        List processes = persistenceManager.getStoredProcesses( );
        List<String> processNames = new ArrayList<String>( );

        for( int i = 0; i < processes.size( ); i++ )
        {
            BPELProcessInfo pInfo = ( BPELProcessInfo )processes.get( i );
            String processName = pInfo.getProcessName( );
            processNames.add( processName );
        }
        return processNames;
    }

    /**
     * Registry the activity specified like waiting for a message
     * @param partnerLinkName The name of the partner link to send the message
     * @param partnerLinkUri The uri of the partner link
     * @param portTypeName The name of the port type name
     * @param operationName The name of the operation invoke by the invoke activity associated
     * @param typeMessageExpected The type of message expected
     * @param elementName The name of the element
     * @param instanceID The id of the instance
     * @param beginTime The time when the waiting begins
     */
    public void registerElementExpectingMessage( String partnerLinkName, String partnerLinkUri, String portTypeName, String operationName, String typeMessageExpected, IInteraction element, InstanceId instanceID, long beginTime )
    {
        InvocationInformation receiveInformation = new InvocationInformation( );
        receiveInformation.setPartnerLinkName( partnerLinkName );
        receiveInformation.setPartnerLinkUri( partnerLinkUri );
        receiveInformation.setPortTypeName( portTypeName );
        receiveInformation.setOperationName( operationName );
        receiveInformation.setMessageExpectedType( typeMessageExpected );
        receiveInformation.setElement( element );
        receiveInformation.setBeginTimeWaiting( beginTime );
        receiveInformation.setInstanceId( instanceID );
        receivesInformation.add( receiveInformation );
    }

    /**
     * Sends the response to the partner link provided
     * @param partnerLinkName The name of the partner link
     * @param portTypeName The name of the port type
     * @param operationName The name of the operation to be invoked for the answer
     * @param response The response
     * @param name name of the element
     * @param instanceID The id of the instance
     */
    public void sendReplyResponse( String partnerLinkName, String portTypeName, String operationName, Object response, String name, InstanceId instanceID )
    {
        boolean assigned = false;
        for( int i = 0; i < threadsSynchronousServices.size( ) && !assigned; i++ )
        {
            ExecuteSynchronousServices actual = threadsSynchronousServices.get( i );
            InstanceId id = actual.getInstanceId( );
            // ////System.out.println( "entro a mirar que si es el thread correcto " + id.getInstanceId( ) + " " + id.getProcessId( ) );
            if( id.getInstanceId( ) == instanceID.getInstanceId( ) && id.getProcessId( ) == instanceID.getProcessId( ) )
            {
                // ////System.out.println( "Response: " + response );
                actual.setResponse( response );
                assigned = true;
                // ////System.out.println( "LO ASIGNO" );
            }
        }
    }

    /**
     * Invokes the asynchronuos partner link specified
     * @param partnerLinkName The partner link name
     * @param partnerLinkUri The uri of the partner link
     * @param portTypeName The port for making the invocation
     * @param message The message to be sent
     */
    public void invokeAsynchronusPartnerLink( String partnerLinkName, String partnerLinkUri, String portTypeName, String operationName, Object message, IInteraction element, InstanceId instanceID, int serviceId )
    {
        InvocationInformation invokeInformation = new InvocationInformation( );
        invokeInformation.setBeginTimeWaiting( System.currentTimeMillis( ) );
        invokeInformation.setInstanceId( instanceID );
        invokeInformation.setElement( element );
        invokeInformation.setPartnerLinkName( partnerLinkName );
        invokeInformation.setPartnerLinkUri( partnerLinkUri );
        invokeInformation.setOperationName( operationName );
        invokesInformation.add( invokeInformation );
        // ////System.out.println("LA URI DEL SERVICIO QUE ESTA INVOCANDO ES :):):):):))))))))))))))))))))))))))))))"+partnerLinkUri);
        invokeAsynchronousCall( serviceId, partnerLinkUri, message, portTypeName, operationName, instanceID );
    }
}
