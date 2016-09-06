package uniandes.cumbia.aspects.loaders;

import org.w3c.dom.Node;

import uniandes.cumbia.aspects.containers.CumbiaAspectStructure;
import uniandes.cumbia.openobjects.containers.model.ModelInformation;
import uniandes.cumbia.openobjects.containers.model.ModelStructureInformation;
import uniandes.cumbia.openobjects.loaders.LoaderException;
import uniandes.cumbia.openobjects.loaders.ModelStructureLoader;

public class CumbiaAspectStructureLoader extends ModelStructureLoader
{
	// -----------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------

	/**
	 * This creates a new loader using the node that contains the model structure information, and a reference to the element that holds all
	 * the information about the model.
	 * 
	 * @param modelNode The model that contains the structure definition
	 * @param modelInformation The information available on the node
	 */
	public CumbiaAspectStructureLoader(Node modelNode, ModelInformation modelInformation)
	{
		super(modelNode, modelInformation);
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * This method loads the model structure and stores it in the modelInformation field
	 * 
	 * @throws LoaderException
	 */
	public void loadModelStructure() throws LoaderException
	{
		Node processNode = getChild("aspect");

		verifyStructure(processNode);

		ModelStructureInformation modelStructureInformation = new CumbiaAspectStructure(processNode);
		modelInformation.storeModelStructureInformation(modelStructureInformation);
	}

	/**
	 * Verifies that the structure of the process is correctly defined and is consistent with the rest of the model definition.
	 * 
	 * @param rootNode The node that contains the structure
	 * @throws LoaderException
	 */
	protected void verifyStructure(Node rootNode) throws LoaderException
	{
		// TODO verify the structure
	}
}
