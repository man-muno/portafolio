/*******************************************************************************
 * $Id: HierarchicalMemory.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
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
package uniandes.cumbia.aspects.containers;

import java.io.Serializable;
import java.util.HashMap;

import uniandes.cumbia.openobjects.data.Data;

/**
 * This class contains the memory of the engine
 */
public class HierarchicalMemory extends HashMap<String, Data> implements Serializable
{
	// -----------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------

	/**
	 * Reference to the parent memory
	 */
	private HierarchicalMemory parentMemory;

	// -----------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------

	/**
	 * Constructor of the memory
	 * 
	 * @param parentMemory Parent memory
	 */
	public HierarchicalMemory(HierarchicalMemory parentMemory)
	{
		super();
		this.parentMemory = parentMemory;
	}

	/**
	 * Constructor of the memory
	 */
	public HierarchicalMemory()
	{
		super();
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Returns the parent memory
	 * 
	 * @return Parent memory
	 */
	public HierarchicalMemory getParentMemory()
	{
		return parentMemory;
	}

	/**
	 * Sets the parent memory
	 * 
	 * @param parentMemory Parent memory
	 */
	public void setAspectMemory(HierarchicalMemory parentMemory)
	{
		this.parentMemory = parentMemory;
	}
}
