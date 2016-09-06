/*******************************************************************************
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
 * 
 * Grupo de Investigación en Construcción de Software
 * Departamento de Ingeniería de Sistemas y Computación
 * Universidad de los Andes
 * Bogotá - Colombia
 * 
 * Copyright (c) 2008
 * Todos los derechos reservados. 
 * 
 *******************************************************************************/
package uniandes.cumbia.bpel.jboss;

import java.io.File;
import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.system.ServiceMBeanSupport;
import org.jboss.system.server.ServerConfig;
import org.jboss.system.server.ServerConfigLocator;

import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.bpel.engine.ProcessEngine;
import uniandes.cumbia.bpel.engine.persistance.IPersistenceManager;
import uniandes.cumbia.bpel.engine.persistance.PersistenceManager;
import uniandes.cumbia.bpel.messagecenter.IMessageCenter;
import uniandes.cumbia.bpel.messagecenter.MessageCenter;
import uniandes.cumbia.deposit.CumbiaDeposit;
import uniandes.cumbia.deposit.interfaces.IDeposit;
import uniandes.cumbia.openobjects.instantiation.exceptions.ResourcesException;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * The service that wraps around a JCumbia bpel Engine and can be used to publish the engine inside a JBoss System
 */
public class BpelService extends ServiceMBeanSupport implements BpelServiceMBean
{
    private static final String BPEL_DIR_PROPERTY = "cumbia.bpel.path";

    private static final String ENGINE_BIND_NAME = "caffeineEngine";

    private static final String MESSAGE_CENTER_BIND_NAME = "caffeineEngineMessageCenter";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    /**
     * The ModelEngine that executes the service logic
     */
    private static IProcessEngine engine;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Builds the engine and starts the service.
     */
    public BpelService( )
    {
        try
        {
            start( );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Inits the service building the IModelEngine object and registering it in JNDI.
     */
    private void initEngine( )
    {
        Context iniCtx = null;
        Context compEnv = null;
        CumbiaDeposit deposit = null;

        try
        {
            iniCtx = new InitialContext( );
            compEnv = ( Context )iniCtx.lookup( "java:" );
            compEnv.unbind( ENGINE_BIND_NAME );
            compEnv.unbind( MESSAGE_CENTER_BIND_NAME );

        }
        catch( NamingException e1 )
        {
        }

        try
        {
            // Locate the CUMBIA_HOME and the folders where state machines should be stored
            String cumbiaHome = System.getenv( "CUMBIA_HOME" );
            if( cumbiaHome == null )
            {
                ServerConfig config = ServerConfigLocator.locate( );
                File dataDir = config.getServerDataDir( );
                File cumbiaDir = new File( dataDir, "cumbia" );
                if( !cumbiaDir.exists( ) )
                    cumbiaDir.mkdir( );
                cumbiaHome = cumbiaDir.getAbsolutePath( );
            }

            File cumbiaBpelHome = new File( cumbiaHome, ENGINE_BIND_NAME );
            if( !cumbiaBpelHome.exists( ) )
                cumbiaBpelHome.mkdirs( );

            System.setProperty( BPEL_DIR_PROPERTY, cumbiaBpelHome.getAbsolutePath( ) );

            log.info( "Loading files from " + cumbiaBpelHome.getAbsolutePath( ) );

            // Loading the deposit
            deposit = ( CumbiaDeposit )getCumbiaDeposit( );

            // Message Center
            // Creates the message center
            ServerConfig config = ServerConfigLocator.locate( );
            File deployDirectory = config.getServerTempDeployDir( );

            IPersistenceManager manager = new PersistenceManager( deposit);

            IMessageCenter messageCenter = new MessageCenter( deployDirectory, manager );
            messageCenter.setBpelEngine( engine );
            compEnv.bind( MESSAGE_CENTER_BIND_NAME, messageCenter );
            
            File configurationFile = new File( cumbiaHome, "kernel.properties" );
            engine = new ProcessEngine( configurationFile, messageCenter );
            compEnv.bind( ENGINE_BIND_NAME, engine );

            registryConsole( );

        }
        catch( NamingException e )
        {
            log.error( "Error initializing the engine: " + getClass( ).getName( ) + " " + e.getMessage( ) );
            e.printStackTrace( );
        }
        catch( LoaderException e )
        {
            log.error( "Error initializing the engine: " + getClass( ).getName( ) + " " + e.getMessage( ) );
            e.printStackTrace( );
        }
        catch( ResourcesException e )
        {
            log.error( "Error initializing the engine: " + getClass( ).getName( ) + " " + e.getMessage( ) );
            e.printStackTrace( );
        }
        catch( IOException e )
        {
            log.error( "Error initializing the engine: " + getClass( ).getName( ) + " " + e.getMessage( ) );
            e.printStackTrace( );
        }
        catch( Exception e )
        {
            log.error( "Error initializing the engine: " + getClass( ).getName( ) + " " + e.getMessage( ) );
            e.printStackTrace( );
        }

    }
    
    /**
     * Returns the cumbia deposit
     * @return The cumbia deposit or null if there is any error
     * @throws Exception If there is any problem searching the cumbia deposit
     */
    public static IDeposit getCumbiaDeposit( ) throws Exception
    {
        try
        {
            Context iniCtx = new InitialContext( );
            Object obDeposit = iniCtx.lookup( "java:cumbiaDeposit" );
            IDeposit deposit = null;

            if( obDeposit != null )
            {
                deposit = ( IDeposit )obDeposit;
            }

            return deposit;
        }
        catch( Exception ex )
        {
            //System.out.println( "Problems look up the cumbiaDeposit: " + ex.getLocalizedMessage( ) );
            ex.printStackTrace( );
            throw ex;
        }
    }
    
    /**
     * Creates the JCumbia bpel Engine Service. <br>
     * After creation the service is still unusable.
     */
    public void create( )
    {
        unregistryConsole( );
        engine = null;
        log.info( "Creating the CumbiaBPEL Engine Service" );
    }

    /**
     * Starts the JCumbia bpel Engine. <br>
     * After been started, the service is usable.
     */
    public void start( )
    {
        initEngine( );
        log.info( "Starting the CumbiaBPEL Engine Service." );
    }

    /**
     * Stops the service.
     */
    public void stop( )
    {
        unregistryConsole( );
        engine = null;
        log.info( "Stopping the CumbiaBPEL Engine Service." );
    }

    /**
     * Destroys the service.
     */
    public void destroy( )
    {
        log.info( "Destroying the CumbiaBPEL Engine Service." );
    }

    /**
     * Registry the engine in the cumbia console
     */
    private void registryConsole( )
    {
    }

    /**
     * Unregistry the engine in the cumbia console.
     */
    private void unregistryConsole( )
    {
    }
}