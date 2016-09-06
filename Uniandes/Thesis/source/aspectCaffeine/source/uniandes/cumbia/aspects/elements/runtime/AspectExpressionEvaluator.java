package uniandes.cumbia.aspects.elements.runtime;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.aspects.elements.IBasicElement;
import uniandes.cumbia.aspects.elements.advice.IAdvice;
import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.elements.instruction.IInstruction;
import uniandes.cumbia.aspects.elements.runtime.AspectRuntime.AspectElementType;
import uniandes.cumbia.aspects.elements.transitionPoint.ITransitionPoint;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.runtime.IExpressionEvaluator;

public class AspectExpressionEvaluator implements IExpressionEvaluator
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The model instance of the process
     */
    private ModelInstance instanceSpace;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the expression evaluator
     */
    public AspectExpressionEvaluator( ModelInstance instanceSpace )
    {
        super( );
        this.instanceSpace = instanceSpace;
    }

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Returns the elements of the instance that satisfy the path or expression provided
     * 
     * @param expression The path or expression to evaluate
     * @return The list of elements that satisfy the expression or path
     */
    public List getElement( String expression )
    {
        String[] s = expression.split( "\\|" );
        String[] targetPath = s[ 1 ].split( ":" );
        AspectElementType type = parseType( s[ 0 ] );
        ////System.out.println("Searching expression: " + expression);
        ////System.out.println( "Searching type: " + type );
        ArrayList<IBasicElement> target = new ArrayList<IBasicElement>( );
        int posInTargetPath = 0;
        if( type.equals( AspectElementType.Aspect) )
        {
            target.add( ( IAspect )instanceSpace.getRoot( ) );
            // ////System.out.println("Aspect added");
        }
        else if( type != null && ( ( IAspect )instanceSpace.getRoot( ) ).getName( ).equals( targetPath[ posInTargetPath ] ) )
        {
            getElements( ( IAspect )instanceSpace.getRoot( ), targetPath, target, posInTargetPath, type );
        }
        return target;
    }

    /**
     * Searches for the elements inside the process
     * @param expression
     * @param aspect
     * @param resp
     * @param posInTargetPath
     * @param type
     */
    private void getElements( IAspect aspect, String[] targetPath, ArrayList<IBasicElement> resp, int posInTargetPath, AspectElementType type )
    {
        
        for( int i = 0; i < aspect.getTransitionPoints( ).size( ); i++ )
        {
            searchElements( aspect.getTransitionPoints( ).get( i ), targetPath, posInTargetPath + 1, resp );
        }
    }


    /**
     * 
     * @param expression
     * @param element
     * @param resp
     */
    private void searchElements( IBasicElement element, String[] targetPath, int posInTargetPath, ArrayList resp )
    {
        // ////System.out.println( "Searching for element " + targetPath[posInTargetPath] + " in element " + element.getName( ) );
        if( element instanceof ITransitionPoint && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            ITransitionPoint transitionPoint = ( ITransitionPoint)element;
            for( int i = 0; i < transitionPoint.getAdvices( ).size( ); i++ )
            {
                searchElements( transitionPoint.getAdvices( ).get( i ), targetPath, posInTargetPath + 1, resp );
            }
        }
        else if( element instanceof IAdvice && targetPath[ posInTargetPath ].equals( element.getName( ) ) && posInTargetPath != targetPath.length - 1 )
        {
            IAdvice advice = ( IAdvice )element;
            for( int i = 0; i < advice.getInstructions( ).size( ); i++ )
            {
                searchElements( advice.getInstructions( ).get( i ), targetPath, posInTargetPath + 1, resp );
            }
        }
        else
        {
            if( targetPath[ posInTargetPath ].equals( element.getName( ) ) )
                resp.add( element );
        }
    }

    /**
     * Parse the type specified
     * 
     * @param type The type to parsing
     * @return The type parsing
     */
    private AspectElementType parseType( String type )
    {
        AspectElementType res = null;
        if( type.equalsIgnoreCase( IAspect.TYPE_ASPECT) )
        {
            res = AspectElementType.Aspect;
        }
        else if( type.equalsIgnoreCase( IAdvice.TYPE_ADVICE) )
        {
            res = AspectElementType.Advice;
        }
        else if( type.equalsIgnoreCase( ITransitionPoint.TYPE_GRAPH_TRANSITION_POINT) )
        {
            res = AspectElementType.TransitionPoint;
        }
        else if( type.equalsIgnoreCase( IInstruction.TYPE_INSTRUCTION) )
        {
            res = AspectElementType.Instruction;
        }
        return res;
    }
}
