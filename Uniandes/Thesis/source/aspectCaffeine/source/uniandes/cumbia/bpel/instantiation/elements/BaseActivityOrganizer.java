/*******************************************************************************
 * $Id: BaseActivityOrganizer.java,v 1.1 2009/01/29 21:38:42 man-muno Exp $
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
package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.AbstractLoader;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Base class to load any element.
 */
public abstract class BaseActivityOrganizer extends AbstractLoader
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public static final String ATTRIBUTE_NAME = "name";

    public static final String ATTRIBUTE_SUPPRESED_JOIN_FAILURE = "suppresJoinFailure";
    
	// -----------------------------------------------------------------
	// Atributes
	// -----------------------------------------------------------------

	/**
	 * Element being loaded
	 */
	protected IBasicElement element;

	/**
	 * The instance where the elements are being organized
	 */
	protected ModelInstance modelInstance;
	
	/**
	 * The process owner of the element. Null if the element is the process 
	 */
	protected IProcess parentProcess;

	// -----------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------

	/**
	 * Constructor of the loader
	 * 
	 * @param node The node of the element
	 * @param modelInstance The instance where the elements are being organized
	 */
	public BaseActivityOrganizer(Node node, ModelInstance modelInstance, IProcess parentProcess)
	{
		super(node);
		this.modelInstance = modelInstance;
		this.parentProcess = parentProcess;
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Returns the element with its internal structure organized
	 * 
	 * @return element
	 */
	public abstract IActivity getActivity();

	/**
	 * Organizes the internal structure of the element
	 * @throws LoaderException 
	 */
	public abstract void organizeInternalStructure() throws LoaderException;

}
