package uniandes.cumbia.aspects.instantiation;

import org.w3c.dom.Node;

import uniandes.cumbia.aspects.containers.CumbiaAspectStructure;
import uniandes.cumbia.aspects.instantiation.elements.AspectOrganizer;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.containers.model.ModelInformation;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.instantiation.ModelStructureInstantiator;

public class CumbiaAspectStructureInstantiator extends ModelStructureInstantiator
{

	// -----------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------

	/**
	 * This creates a new instance using the model structure information, and a reference to the element that holds the instance information
	 * 
	 * @param modelInstance Instance that is being created
	 * @param modelInformation The information available on the node
	 */
	public CumbiaAspectStructureInstantiator(ModelInstance modelInstance, ModelInformation modelInformation)
	{
		super(modelInstance, modelInformation);
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * This method creates the model structure and stores the root in the modelInstance field.<br>
	 * The modelInstance already has instances ov every element defined in the model.
	 * @throws CumbiaException 
	 */
	public void createStructure() throws CumbiaException
	{
		CumbiaAspectStructure structure = (CumbiaAspectStructure) modelInformation.getModelStructureInformation();

		Node processNode = structure.getRoot();
		//////System.out.println("Before organizing process root: " + processNode);
		AspectOrganizer loader = new AspectOrganizer(processNode, modelInstance, null);
		loader.organizeInternalStructure();

		modelInstance.setRoot(loader.getElement( ));
	}

}
