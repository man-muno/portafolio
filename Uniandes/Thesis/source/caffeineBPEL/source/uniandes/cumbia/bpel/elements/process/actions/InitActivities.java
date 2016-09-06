/*******************************************************************************
 * $Id: InitActivities.java,v 1.5 2009/02/10 01:45:04 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
 * 
 * Grupo de Investigaci�n en Construcci�n de Software
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Universidad de los Andes
 * Bogot� - Colombia
 * 
 * Copyright (c) 2008
 * Todos los derechos reservados. 
 * 
 *******************************************************************************/
package uniandes.cumbia.bpel.elements.process.actions;

import java.io.Serializable;
import java.util.List;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.process.Process;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingPoint;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.Transition;

/**
 * Action for the Process State Machine
 */
public class InitActivities implements IAction, Serializable
{

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    /**
     * Stops and resets all the activities and subprocesses
     * 
     * @param event 
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute( EventNotification event, Transition transition, IOpenObject element )
    {
        Process process = ( Process )element;
        //System.out.println( "InitActivities" );
        
        List<IStartingPoint> startingPoints = process.getStartingPoints( );
        for( IStartingPoint startingPoint : startingPoints)
        {
            startingPoint.initialize( );
            //System.out.println("Initializing startingpoint " + startingPoint.getName( ));
        }
        
        List<IActivity> elements = process.getProcessElements( );
        for( int i = 0; i < elements.size( ); i++ )
        {
            IBasicElement basicElement = elements.get( i );
            basicElement.initialize( );
            //System.out.println("Initializing " + basicElement.getName( ));
        }

    }
}
