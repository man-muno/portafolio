package uniandes.cumbia.bpel.test.instantiation.utils;

import uniandes.cumbia.bpel.messagecenter.IWsdlService;

public class CounterService implements IWsdlService
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private String processName;
    private int id;
    private int counter;
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    public CounterService( String processName )
    {
        this.processName = processName;
        counter = 0;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    
    @Override
    public String getProcessName( )
    {
        return processName;
    }

    @Override
    public void invokeAsynchronusService( Object message, String uri, String operation, int instanceId, int processId )
    {
        System.out.println("WSDLService invokeAsynchronusService");
    }

    @Override
    public Object invokeSynchronusService( Object message, String uri, String operation, int instanceId, int processId )
    {
        int toReturn = counter;
        counter++;
        System.out.println("WSDLService invokeSynchronusService " + (toReturn));
        return toReturn;
    }

    @Override
    public void setId( int id )
    {
        this.id = id;
    }

}
