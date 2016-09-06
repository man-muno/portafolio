/*******************************************************************************
 * $Id: InstructionOrganizer.java,v 1.2 2009/02/10 00:55:04 man-muno Exp $
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

import java.io.File;

import org.w3c.dom.Node;

import uniandes.cumbia.aspects.elements.IBasicElement;
import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.elements.instruction.CreateBPELElements;
import uniandes.cumbia.aspects.elements.instruction.IInstruction;
import uniandes.cumbia.aspects.elements.instruction.InstallAction;
import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class used to load a process
 */
public class InstructionOrganizer extends BaseElementOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ELEMENT_TYPE = "type";
    private static final String INSTALL_ACTION = "installAction";
    private static final String CREATE_BPEL_ELEMENTS = "createBpelElements";
    private static final String ACTION_NAME = "actionName";
    private static final String ACTION_CLASS = "actionClass";
    private static final String ADVICE_FILE = "file";
    
    
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
    public InstructionOrganizer( Node node, ModelInstance modelInstance, IAspect aspect)
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
        IInstruction instruction = null;
        try
        {
            instruction = ( IInstruction )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement InstallAction.", e );
        }
        
        String type = getAttribute( ELEMENT_TYPE );
        
        
        if(type.equals( INSTALL_ACTION ))
        {
            
            String actionName = getAttribute( ACTION_NAME );
            String actionClass = getAttribute( ACTION_CLASS );
            ((InstallAction)instruction).setActionName(actionName);
            ((InstallAction)instruction).setActionClass(actionClass);
        }
        else if(type.equals( CREATE_BPEL_ELEMENTS ))
        {
            String file = getAttribute( ADVICE_FILE );
            ((CreateBPELElements)instruction).setAdviceFile(new File(file));
            ((CreateBPELElements)instruction).setParentAspect( parentAspect );
        }
        
        
    }

    public IInstruction getInstruction( )
    {
        return (IInstruction) element;
    }
}
