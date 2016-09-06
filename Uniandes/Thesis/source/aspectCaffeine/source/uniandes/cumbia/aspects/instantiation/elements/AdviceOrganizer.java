/*******************************************************************************
 * $Id: AdviceOrganizer.java,v 1.2 2009/02/10 00:55:04 man-muno Exp $
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
import uniandes.cumbia.aspects.elements.advice.IAdvice;
import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class used to load a process
 */
public class AdviceOrganizer extends BaseElementOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ELEMENT_INSTRUCTION = "instruction";
    
    private static final String ATTRIBUTE_TYPE = "type";

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
    public AdviceOrganizer( Node node, ModelInstance modelInstance, IAspect aspect)
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
        IAdvice advice = null;
        try
        {
            advice = ( IAdvice )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IAdvice.", e );
        }
        
        String type = getAttribute( ATTRIBUTE_TYPE );
        advice.setType(type);
        
        List<Node> instructionNodes = getChilds( ELEMENT_INSTRUCTION);
        
        for(Node instructionNode : instructionNodes)
        {
            InstructionOrganizer tpOrganizer = new InstructionOrganizer(instructionNode,modelInstance,parentAspect);
            tpOrganizer.organizeInternalStructure( );
            advice.addInstruction(tpOrganizer.getInstruction());
        }
    }

    public IAdvice getAdvice( )
    {
        return (IAdvice) element;
    }
}
