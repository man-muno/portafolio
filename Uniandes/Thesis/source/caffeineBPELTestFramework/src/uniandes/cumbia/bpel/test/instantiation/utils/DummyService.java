package uniandes.cumbia.bpel.test.instantiation.utils;

import uniandes.cumbia.bpel.messagecenter.IWsdlService;

public class DummyService implements IWsdlService
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private String processName;
    private int id;
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    public DummyService( String processName )
    {
        this.processName = processName;
    }

    // -----------------------------------------------------------------
    // Constructors
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
        System.out.println("WSDLService invokeSynchronusService");
        return "Service Response";
    }

    @Override
    public void setId( int id )
    {
        this.id = id;
    }
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
}
