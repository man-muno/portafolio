/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: Vertex.java,v 1.3 2009/02/11 18:11:57 man-muno Exp $ 
 * Universidad de los Andes (Bogota - Colombia) 
 * Departamento de Ingenieria de Sistemas y Computacion 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto CUMBIA
 * Aplicacion: AspectCaffeine
 * Autor: Manuel - Apr 15, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cumbia.aspects.elements.transitionPoint.graphTransitionPoint.graph;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.aspects.elements.advice.IAdvice;
import uniandes.cupi2.collections.arbol.arbolNArio.NodoArbolNArio;

/**
 * Element of the Graph
 */
public class Vertex
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * the advice contained in the vertex
     */
    private IAdvice advice;

    /**
     * List of succesors arcs
     */
    private List<Arc> succesors;

    /**
     * id of the element
     */
    private String id;

    /**
     * Mark that represents if the vertex is in the spanning tree
     */
    private boolean inSpannigTree;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Creates a new vertex with one advice
     */
    public Vertex( IAdvice advice )
    {
        this.advice = advice ;
        succesors = new ArrayList<Arc>( );
    }

    /**
     * Checks if an advices exists in the advice list
     * @param advice The advice to be looked for
     * @return True if the advices is in the advices list
     */
    public boolean hasAdvice( IAdvice advice )
    {
        return advice.getName( ).equals( advice.getName( ));
    }

    /**
     * Returns the id of the vertex. The id is composed by the names of the advices separated by ":"
     * @return The id of the vertex.
     */
    public String getId( )
    {
        return advice.getName( );
    }

    /**
     * Returns the list of the advices.
     * @return Must contain at least one advice
     */
    public IAdvice getAdvice( )
    {
        return advice;
    }

    /**
     * Adds a new arc to the vertex
     * @param to The arc to be added.
     */
    public void addArc( Arc to )
    {
        String idTo = to.getToVertex( ).getId( );
        if( !isSucesor( idTo ) )
            succesors.add( to );
    }

    /**
     * Checks if the vertex identified by the id is a successor of this vertex
     * @param idTo Id of the desired vertex
     * @return true if the vertex identified by the id is an immediate successor of this vertex
     */
    private boolean isSucesor( String idTo )
    {
        return getArc( idTo ) != null;
    }

    /**
     * Returns an arc form this vertex to the vertex identified by the id
     * @param idTo Id of the destination vertex
     * @return An arc if it exists, null otherwise
     */
    private Arc getArc( String idTo )
    {
        for( int i = 0; i < succesors.size( ); i++ )
        {
            Arc arc = succesors.get( i );
            if( idTo.equals( arc.getToVertex( ).getId( ) ) )
                return arc;
        }
        return null;
    }

    /**
     * Returns a list of all the arcs successor to this vertex
     * @return The list of successors arcs. May be empty
     */
    public List<Arc> getSuccesors( )
    {
        return succesors;
    }

    /**
     * Marks the vertex as a vertex in the spanning tree
     */
    public void mark( )
    {
        inSpannigTree = true;
    }

    /**
     * Adds all the successors vertexes as children in the spanning tree
     * @param nodeTree 
     * @param existentes The node to add the children to
     */
    public void addSuccesorsSpanningTree( NodoArbolNArio<IAdvice> nodeTree, ArrayList<IAdvice> exist )
    {
        ArrayList<IAdvice> existentes = new ArrayList<IAdvice>();
        existentes.addAll( exist );
        boolean[] alreadyChecked = new boolean[succesors.size( )];
        for( int i = 0; i < succesors.size( ); i++ )
        {
            int randVertexPos = -1;
            boolean selected = false;
            while( !selected )
            {
                int pos = ( int ) ( Math.random( ) * (succesors.size( )) );
                if( !alreadyChecked[ pos ] )
                {
                    selected = true;
                    randVertexPos = pos;
                    alreadyChecked[ pos ] = true;
                }
            }
            
            Vertex to = succesors.get( randVertexPos ).getToVertex( );
            if( !existentes.contains( to.getAdvice( ) ) )
            {
                NodoArbolNArio<IAdvice> nodoHijo = new NodoArbolNArio<IAdvice>( to.getAdvice( ) ); 
                nodeTree.agregarHijo( nodoHijo );
                existentes.add( nodoHijo.darElemento( ) );
                to.addSuccesorsSpanningTree( nodoHijo, existentes );
                existentes.clear( );
                existentes.addAll( exist );
            }
        }
    }

    /**
     * Checks if the vertex is marked as a vertex in the spanning tree
     * @return true if the vertex is in the spanning tree
     */
    private boolean isMarked( )
    {
        return inSpannigTree;
    }

    /**
     * Unmarks the vertex as a vertex in the spanning tree
     */
    public void unmark( )
    {
        inSpannigTree = false;
    }

    /**
     * Checks for an advice given its name in the vertex
     * @param adviceName The advice name to be searched for
     * @return true if the vertex contains the advice
     */
    public boolean containsAdvice( String adviceName )
    {
        return advice.getName( ).equals( adviceName );
    }

    /**
     * Checks for an arc from this vertex to the one given by parameter
     * @param to The destination vertex
     * @return tr true if an arc between the to vertex exist
     */
    public boolean containsArcTo( Vertex to )
    {
        boolean found = false;
        for( int i = 0; i < succesors.size( ) && !found; i++ )
            found = succesors.get( i ).getToVertex( ).getId( ).equals( to.getId( ) );
        return found;
    }

}
