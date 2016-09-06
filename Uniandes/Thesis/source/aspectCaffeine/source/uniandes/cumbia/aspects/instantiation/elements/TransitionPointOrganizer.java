/*******************************************************************************
 * $Id: TransitionPointOrganizer.java,v 1.2 2009/02/10 00:55:04 man-muno Exp $
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
import uniandes.cumbia.aspects.elements.transitionPoint.ITransitionPoint;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class used to load a process
 */
public class TransitionPointOrganizer extends BaseElementOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ELEMENT_ADVICE = "advice";
    
    private static final String ATTRIBUTE_POINT_CUT = "pointcut";
    
    private static final String ATTRIBUTE_INSTANCE = "instance";

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
    public TransitionPointOrganizer( Node node, ModelInstance modelInstance, IAspect aspect)
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
        ITransitionPoint transitionPoint = null;
        try
        {
            transitionPoint = ( ITransitionPoint )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement ITransitionPoint.", e );
        }

        
        String pointCut = getAttribute( ATTRIBUTE_POINT_CUT );
       
        
        int instance = Integer.parseInt(  getAttribute( ATTRIBUTE_INSTANCE ) != null ? getAttribute( ATTRIBUTE_INSTANCE ) : "-1");
        

        transitionPoint.setPointCut(pointCut);
        transitionPoint.setProcessInstance(instance);
        
        
        List<Node> advices = getChilds( ELEMENT_ADVICE);
        
        for(Node adviceNode : advices)
        {
            AdviceOrganizer tpOrganizer = new AdviceOrganizer(adviceNode,modelInstance,parentAspect);
            tpOrganizer.organizeInternalStructure( );
            transitionPoint.addAdvice(tpOrganizer.getAdvice());
        }
        
        
    }

    public ITransitionPoint getTransitionPoint( )
    {
        return (ITransitionPoint)element;
    }
}
