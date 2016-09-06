package uniandes.cumbia.bpel.test.instantiation;

import java.io.File;
import java.io.IOException;

import uniandes.cumbia.bpel.engine.ProcessEngine;
import uniandes.cumbia.bpel.instance.InstanceSpace;
import uniandes.cumbia.bpel.process.ProcessSpace;
import uniandes.cumbia.bpel.test.instantiation.utils.EngineHolder;
import uniandes.cumbia.bpel.test.instantiation.utils.MessageCenter;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.instantiation.exceptions.ResourcesException;
import uniandes.cumbia.openobjects.loaders.LoaderException;
import uniandes.cumbia.testFW.interfaces.IInstanciator;

public class Instantiator implements IInstanciator
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private ProcessEngine engine;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    public Instantiator()
    {
        File kernelPropertiesBPEL = new File("./data/kernelBPEL.properties");
        
        try
        {
            engine = new ProcessEngine(kernelPropertiesBPEL,MessageCenter.getInstance());
        }
        catch( LoaderException e )
        {
            e.printStackTrace();
        }
        catch( ResourcesException e )
        {
            e.printStackTrace();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }


        EngineHolder.getInstance( ).setEngine( engine );
        
    }
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    

    public Object createEngine( )
    {
        //System.out.println("Create engine: " + engine);
        MessageCenter.getInstance().setBpelEngine( engine );
        return engine;
    }

    public Object createModelInstance( String modelInstancePath )
    {
        InstanceSpace instanceSpace = null;
        ProcessSpace processSpace =  null;
        try
        {
            processSpace = engine.createProcess( new File(modelInstancePath), ClassLoader.getSystemClassLoader( ) );
            instanceSpace = processSpace.createInstance( );
        }
        catch( LoaderException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch( CumbiaException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("createModelInstance: "+ processSpace.getProcessName( ) + " " + processSpace.getId( ) + " " + instanceSpace.getId( ).getInstanceId( ) + " " + instanceSpace.getInstance( ).getRuntime( ));
        return instanceSpace.getInstance( ).getRuntime( );
    }
}
