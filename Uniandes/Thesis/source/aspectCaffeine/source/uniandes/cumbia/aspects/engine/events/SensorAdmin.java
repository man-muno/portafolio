/*******************************************************************************
 * $Id: SensorAdmin.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
 * 
 * Grupo de Investigacion en Construccion de Software
 * Departamento de Ingenieria de Sistemas y Computacion
 * Universidad de los Andes
 * Bogota - Colombia
 * 
 * Copyright (c) 2008
 * Todos los derechos reservados. 
 * 
 *******************************************************************************/
package uniandes.cumbia.aspects.engine.events;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class defines the behaviour of the event managers for the processSpace, instanceSpace and processEngine.
 */
public abstract class SensorAdmin implements Serializable
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The listeners currently registered to receive notifications about the high level events generated.
     */
    private List<IEngineEventListener> listeners;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Builds the admin and initializes the list of listeners
     */
    public SensorAdmin( )
    {
        listeners = new ArrayList<IEngineEventListener>( );
    }

    /**
     * Registers a new listener that will receive notifications from the Engine
     * @param listener The new listener
     */
    public void registerListener( IEngineEventListener listener )
    {
        listeners.add( listener );
    }

    /**
     * De-registers a listener
     * @param listener The listener that will be de-registered
     */
    public void removeListener( IEngineEventListener listener )
    {
        listeners.remove( listener );
    }

    /**
     * Informs if some listeners have been installed on the sensor manager
     * @return True if there are listeners registered
     */
    public boolean hasListeners( )
    {
        return listeners.size( ) != 0;
    }

    /**
     * This method publishes the event to all the registered listeners
     * @param event The event that will be published
     */
    public void publishEvent( IEvent event )
    {
        int num = listeners.size( );
        for( int i = 0; i < num; i++ )
        {
            IEngineEventListener l = ( IEngineEventListener )listeners.get( i );
            l.receiveEvent( event );
        }
    }

    /**
     * Informs if the listener is associated with sensor
     * @param listener The listener
     * @return true if the listener is associated with the sensor or false on the contrary
     */
    public boolean isListener( IEngineEventListener listener )
    {
        return listeners.contains( listener );
    }

}
