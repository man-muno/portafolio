/*******************************************************************************
 * $Id: AspectOrganizer.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
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
package uniandes.cumbia.aspects.instantiation.elements;

import java.util.List;

import org.w3c.dom.Node;

import uniandes.cumbia.aspects.elements.IBasicElement;
import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class used to load a process
 */
public class AspectOrganizer extends BaseElementOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ELEMENT_TRANSITION_POINT ="transitionPoint"; 

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Creates a loader for a process
     * 
     * @param node Node for loading the process
     * @param modelInstance The instance where the elements are being organized
     */
    public AspectOrganizer( Node node, ModelInstance modelInstance, IAspect aspect)
    {
        super( node, modelInstance, aspect );
        String name = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(name);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the element with its internal structure organized
     * 
     * @return element
     */
    public IBasicElement getElement( )
    {
        return element;
    }

    /**
     * Organizes the internal structure of the element
     * @throws OrganizerException
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        IAspect aspect = null;
        try
        {
            aspect = ( IAspect )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IAspect.", e );
        }
        
        List<Node> transitionPoints = getChilds( ELEMENT_TRANSITION_POINT);
        
        for(Node transitionPointNode : transitionPoints)
        {
            TransitionPointOrganizer tpOrganizer = new TransitionPointOrganizer(transitionPointNode,modelInstance,aspect);
            tpOrganizer.organizeInternalStructure( );
            aspect.addTransitionPoint(tpOrganizer.getTransitionPoint());
        }
        
    }
}
