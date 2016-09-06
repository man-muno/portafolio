package uniandes.cumbia.aspects.containers;

import org.w3c.dom.Node;

import uniandes.cumbia.openobjects.containers.model.ModelStructureInformation;

public class CumbiaAspectStructure implements ModelStructureInformation
{
	// -----------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------

	private Node root;

	// -----------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------

	public CumbiaAspectStructure(Node root)
	{
		this.root = root;
	}

	// -----------------------------------------------------------------
	// Method
	// -----------------------------------------------------------------

	/**
	 * Returns the root of the model
	 */
	public Node getRoot()
	{
		return root;
	}

}
