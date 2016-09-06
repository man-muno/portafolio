/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: Arc.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $ 
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

/**
 * 
 */
public class Arc
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The origin vertex of the arc
     */
    private Vertex from;

    /**
     * The destination vertex of the arc
     */
    private Vertex to;

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Constructs a non-empty vertex
     */
    public Arc( Vertex from, Vertex to )
    {
        this.from = from;
        this.to = to;
    }

    /**
     * Return the destination vertex
     * @return The destination vertex
     */
    public Vertex getToVertex( )
    {
        return to;
    }

}
