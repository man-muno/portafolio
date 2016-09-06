/*******************************************************************************
 * $Id: ActiveElementListener.java,v 1.2 2008/11/12 19:42:11 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://cumbia.uniandes.edu.co/)
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
package uniandes.cumbia.bpel.elements.runtime.listeners;

import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.runtime.BpelRuntime;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.execution.events.IEventListener;

public class ActiveElementListener implements IEventListener 
{
	// -----------------------------------------------
    // Attributes
    // -----------------------------------------------

    /**
     * Reference to the runtime
     */
    private BpelRuntime runtime;

    // -----------------------------------------------
    // Methods
    // -----------------------------------------------

    /**
     * Constructor of the listener
     * @param responsible The responsible to set.
     */
    public ActiveElementListener( BpelRuntime rt )
    {
        runtime = rt;
    }
    

    /**
     * Notfies the event
     */
    public void notifyEvent( EventNotification event )
    {
        // Element that generated the event
        IBasicElement element = (IBasicElement) event.getSourceElement( );
        //
        //Adds the work space active
        runtime.addElementActive(element); 
    }
}
