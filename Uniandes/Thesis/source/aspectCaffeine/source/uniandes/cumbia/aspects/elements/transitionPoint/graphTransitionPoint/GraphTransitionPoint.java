package uniandes.cumbia.aspects.elements.transitionPoint.graphTransitionPoint;

import java.util.List;

import uniandes.cumbia.aspects.elements.advice.IAdvice;
import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.elements.aspectedElement.IAspectedElement;
import uniandes.cumbia.aspects.elements.transitionPoint.AbstractTransitionPoint;
import uniandes.cumbia.aspects.elements.transitionPoint.graphTransitionPoint.graph.ConflictChecker;
import uniandes.cumbia.aspects.elements.transitionPoint.graphTransitionPoint.graph.Graph;
import uniandes.cumbia.aspects.elements.transitionPoint.graphTransitionPoint.graph.Vertex;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cupi2.collections.arbol.arbolNArio.ArbolNArio;

public class GraphTransitionPoint extends AbstractTransitionPoint
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The parent aspect
     */
    private IAspect parentAspect;

    /**
     * Position of the current advice being evaluated
     */
    private int currentAdviceIndex;

    /**
     * List the aspected element in the transition point
     */
    private IAspectedElement aspectedElement;

    /**
     * The graph containing the advices
     */
    protected Graph graph;
    
    /**
     * The point cut expression
     */
    private String pointCut;

    /**
     * The id of the process instace where to locate the aspect
     */
    private int processInstance;

    private IAspectEngine aspectEngine;

    private List<IAdvice> advices;


    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates the new conditional
     * 
     * @param modelInstance This is the model instance where the element exists
     * @param elementName Name that distinguishes the element in the model
     * @param typeName Name of the type
     */
    public GraphTransitionPoint( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
        currentAdviceIndex = 0;
        graph = new Graph( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Stops the element
     */
    public void stop( )
    {

    }

    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 0;
    }

    /**
     * Returns the parent aspect of this element.
     * @return Parent aspect of this element
     */
    public IAspect getParentAspect( )
    {
        return parentAspect;
    }

    /**
     * Sets the parent process for this element
     * @param parentProcess Parent process
     */
    public void setParentAspect( IAspect parentAspect )
    {
        this.parentAspect = parentAspect;
    }

    /**
     * @return the name of the element
     */
    public String getName( )
    {
        return elementName;
    }

    /**
     * Changes the name of the element
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.elementName = name;
    }
    
    /**
     * Terminates execution of this element
     */
    public void finalizeElement( )
    {
        super.finalizing( );
    }

    /**
     * Initializes the transition point
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {

    }

    /**
     * Method that begins execution of the activity
     */
    public void execute( )
    {
        super.sendStartEvent( );
    }

    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return TYPE_GRAPH_TRANSITION_POINT;
    }
    
    /**
     * Calculates the next advice to be executed
     */
    public void calculateNextAdvice( )
    {
        
        if(currentAdviceIndex==0)
        {
            //Si currentAdviceIndex es 0, es porque no ha ejecutado nada
            //Calcula el orden de los advices
            ArbolNArio<IAdvice> arbol = graph.getSpanningTree( );
            advices = arbol.buscarRamaTodosAdvices(graph.getAllAdvices( ).size()).size( ) > 0 ? arbol.buscarRamaTodosAdvices(graph.getAllAdvices( ).size( )) : null;
            super.sendExecuteAdviceEvent( );
        }
        else if(currentAdviceIndex<advices.size( ))
            super.sendExecuteAdviceEvent( );
        else
            super.sendFinalizedEvent( );
    
    }
    
    /**
     * Executes the advice selected for execution
     */
    public void executeAdvice( )
    {
        advices.get( currentAdviceIndex ).execute();
        currentAdviceIndex++;
    }
    
    /**
     * Returns a list of all the advices
     * @return
     */
    public List<IAdvice> getAdvices( )
    {
        return graph.getAllAdvices( );
    }
    
    /**
     * Adds a new advice
     * @param advice
     */
    public void addAdvice( IAdvice advice )
    {
        if( !graph.existsAdvice( advice.getName( ) ) )
        {
            Vertex vertex = new Vertex(advice);
            graph.addVertex( vertex );
            createArcs();
        }
    }

    private void createArcs( )
    {
        List<IAdvice> advicesInGraph = graph.getAllAdvices( );
        for(int i=0;i<advicesInGraph.size( );i++)
        {
            IAdvice advice = advicesInGraph.get( i );
            List<IAdvice> nonConflictingAdvices = ConflictChecker.getInstance( ).getNonConflictingAdvices( advice, graph.getAllAdvices( ) );
            for(int j=0;j<nonConflictingAdvices.size( );j++)
            {
                IAdvice nonConflictingAdvice = nonConflictingAdvices.get( j );
                if(!graph.existsArc( advice.getName( ), nonConflictingAdvice.getName( ) ))
                    graph.addArc( graph.getVertex( advice.getName( ) ), graph.getVertex( nonConflictingAdvice.getName( ) ) );
            }
        }
    }

    /**
     * 
     * @param pointCut
     */
    public void setPointCut( String pointCut )
    {
        this.pointCut = pointCut;
    }

    /**
     * Sets the process instance where the aspect should be executed
     * @param instance
     */
    public void setProcessInstance( int instance )
    {
        this.processInstance = instance;
    }
    
    /**
     * Returns the point cut expression
     * @return
     */
    public String getPointCut()
    {
        return pointCut;
    }
    
    /**
     * Returns the process instance
     * @return
     */
    public int getProcessInstance()
    {
        return processInstance;
    }
    
    public void setAspectEngine( IAspectEngine aspectEngine )
    {
        this.aspectEngine = aspectEngine;
        for(IAdvice advice : getAdvices( ))
            advice.setAspectEngine(aspectEngine);
    }
}
