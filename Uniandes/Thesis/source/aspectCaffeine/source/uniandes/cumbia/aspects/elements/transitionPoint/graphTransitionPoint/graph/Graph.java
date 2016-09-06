/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: Graph.java,v 1.3 2009/02/11 18:11:58 man-muno Exp $ 
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
import uniandes.cupi2.collections.arbol.arbolNArio.ArbolNArio;
import uniandes.cupi2.collections.arbol.arbolNArio.NodoArbolNArio;

/**
 * Class to hold all the advices specifying non-conflicts with arcs
 */
public class Graph
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * List of the advices contained in the graph
     */
    private List<IAdvice> advices;

    /**
     * List of vertexes of the graph
     */
    private List<Vertex> vertexes;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Creates an empty grph
     */
    public Graph( )
    {
        advices = new ArrayList<IAdvice>( );
        vertexes = new ArrayList<Vertex>( );
    }

    /**
     * Returns the list of all the advices in the graph
     * @return List of the advices in the graph. Could be an empty list.
     */
    public List<IAdvice> getAllAdvices( )
    {
        return advices;
    }

    /**
     * Creates and returns the spanning tree of the graph. This tree uses an uniform distribution
     * @return The spanning tree. Could be an empty tree.
     */
    public ArbolNArio<IAdvice> getSpanningTree( )
    {
        ArbolNArio<IAdvice> spanningTree = new ArbolNArio<IAdvice>( );
        boolean[] alreadyChecked = new boolean[vertexes.size( )];

        int randVertexPos = -1;
        boolean selected = false;
        while( !selected )
        {
            int pos = ( int ) ( Math.random( ) * ( vertexes.size( ) ) );
            if( !alreadyChecked[ pos ] )
            {
                selected = true;
                randVertexPos = pos;
                alreadyChecked[ pos ] = true;
            }
        }

        Vertex vertex = vertexes.get( randVertexPos );

        if( spanningTree.darRaiz( ) == null )
        {
            spanningTree.definirRaiz( new NodoArbolNArio<IAdvice>( vertex.getAdvice( ) ) );
            ArrayList<IAdvice> existentes = new ArrayList<IAdvice>();
            existentes.add(spanningTree.darRaiz( ).darElemento( ));
            vertex.addSuccesorsSpanningTree(spanningTree.darRaiz( ), existentes );
        }

        return spanningTree;
    }

    /**
     * Removes the "in the spanning tree" mark
     */
    private void unmarkAll( )
    {
        for( int i = 0; i < vertexes.size( ); i++ )
        {
            vertexes.get( i ).unmark( );
        }
    }

    /**
     * Searches for an advice given the advice name
     * @param adviceName The name of the advice
     * @return true if the advice is anywhere in the graph, false otherwise
     */
    public boolean existsAdvice( String adviceName )
    {
        boolean found = false;
        for( int i = 0; i < advices.size( ) && !found; i++ )
        {
            found = advices.get( i ).getName( ).equals( adviceName );
        }
        return found;
    }

    /**
     * Adds a new vertex in the graph. Also adds all the advices in the new vertex. Does not check if an advices exists already
     * @param vertex The vertex to be added
     */
    public void addVertex( Vertex vertex )
    {
        advices.add( vertex.getAdvice( ) );
        vertexes.add( vertex );
    }

    /**
     * Adds a new arc to the graph. Does not check if the arc already exists
     * @param from The origin vertex
     * @param to The destination vertex
     */
    public void addArc( Vertex from, Vertex to )
    {
        Arc arc = new Arc( from, to );
        from.addArc( arc );
    }

    /**
     * Returns true if an arc exist between to vertex containing the ids of the advices given
     * @param adviceNameFrom The name of the advice in the origin vertex
     * @param adviceNameTo The name of the advice in the destination vertex
     * @return True if an arc exists between the vertex that contain the giving advices, false otherwise
     */
    public boolean existsArc( String adviceNameFrom, String adviceNameTo )
    {
        Vertex from = getVertex( adviceNameFrom );
        Vertex to = getVertex( adviceNameTo );
        return from.containsArcTo( to );
    }

    /**
     * Returns a vertex that in its id contains the specified id
     * @param id Part or the completed id of the searched vertex
     * @return The vertex or null if no vertex is found
     */
    public Vertex getVertex( String id )
    {
        Vertex vertex = null;
        for( int i = 0; i < vertexes.size( ) && vertex == null; i++ )
            vertex = vertexes.get( i ).containsAdvice( id ) ? vertexes.get( i ) : null;
        return vertex;
    }

}
