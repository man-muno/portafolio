/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: ConflictingInstructionsLoader.java,v 1.2 2009/02/11 18:11:58 man-muno Exp $ 
 * Universidad de los Andes (Bogota - Colombia) 
 * Departamento de Ingenieria de Sistemas y Computacion 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto CUMBIA
 * Aplicacion: JCumbia
 * Autor: Manuel - Apr 20, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cumbia.aspects.instantiation;

import java.util.List;

import org.w3c.dom.Node;

import uniandes.cumbia.aspects.elements.transitionPoint.graphTransitionPoint.graph.ConflictChecker;
import uniandes.cumbia.openobjects.loaders.AbstractLoader;

public class ConflictingInstructionsLoader extends AbstractLoader
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ELEMENT_CONFLICTING_INSTRUCTIONS = "conflicts";

    private static final String ELEMENT_ADVICE = "advice";
    
    private static final String ELEMENT_INSTRUCTION = "instruction";

    private static final String ELEMENT_CONFIICTS_WITH = "canNotFollow";

    private static final String ATTRIBUTE_NAME = "name";
    

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
    
    
    public ConflictingInstructionsLoader(Node node)
    {
        super(node);
    }
    

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Loads the elements in the node
     */
    public void load( )
    {
        
        //Loads the conflicts between instructions
        Node rootNode = getChild( node, ELEMENT_CONFLICTING_INSTRUCTIONS );
        List<Node> instructions = getChilds( rootNode, ELEMENT_INSTRUCTION );
        for( int i = 0; i < instructions.size( ); i++ )
        {
            Node instruction = instructions.get( i );
            String instructionName = getAttribute( instruction, ATTRIBUTE_NAME );
            List<Node> conflicts = getChilds( instruction, ELEMENT_CONFIICTS_WITH );
            for( int j = 0; j < conflicts.size( ); j++ )
            {
                Node conflict = conflicts.get( j );
                String conflictName = getAttribute( conflict, ATTRIBUTE_NAME );
                ConflictChecker.getInstance( ).addConflictingInstruction( instructionName, conflictName );
            }
        }
        
        //Loads the conflicts between advices
        List<Node> advices = getChilds( rootNode, ELEMENT_ADVICE );
        for( int i = 0; i < advices.size( ); i++ )
        {
            Node instruction = advices.get( i );
            String instructionName = getAttribute( instruction, ATTRIBUTE_NAME );
            List<Node> conflicts = getChilds( instruction, ELEMENT_CONFIICTS_WITH );
            for( int j = 0; j < conflicts.size( ); j++ )
            {
                Node conflict = conflicts.get( j );
                String conflictName = getAttribute( conflict, ATTRIBUTE_NAME );
                ConflictChecker.getInstance( ).addConflictingAdvice( instructionName, conflictName );
            }
        }
    }

}
