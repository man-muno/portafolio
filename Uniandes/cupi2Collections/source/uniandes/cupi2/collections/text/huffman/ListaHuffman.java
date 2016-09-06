/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: J. Villalobos - 07-mar-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.text.huffman;

/**
 * Lista encadenada ordenada ascendentemente utilizada para la construcción del árbol Huffman
 */
public class ListaHuffman
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Cabeza de la lista
     */
    private NodoHuffman cab;

    /**
     * Numero de elementos en la lista
     */
    private int numElems;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de una lista vacía. <br>
     * <b>post: </b> Se construyó una lista vacía.
     */
    public ListaHuffman( )
    {
        cab = null;
        numElems = 0;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inserta de manera ordenada un nuevo nodo a la lista. <br>
     * <b>post: </b> Se agregó de manera ordenada el nuevo nodo a la lista.
     * @param nodo Nodo que se va a agregar
     */
    public void insertarOrdenado( NodoHuffman nodo )
    {
        if( cab == null )
        {
            // Es el primer elemento de la lista
            cab = nodo;
        }
        else if( nodo.darFrecuencia( ) < cab.darFrecuencia( ) )
        {
            // Debe quedar como primer elemento de la lista
            cab.insertarAntes( nodo );
            cab = nodo;
        }
        else
        {
            NodoHuffman p = cab;
            while( p.darSiguiente( ) != null && p.darSiguiente( ).darFrecuencia( ) < nodo.darFrecuencia( ) )
            {
                p = p.darSiguiente( );
            }
            p.insertarDespues( nodo );
        }
        numElems++;
    }

    /**
     * Toma el primer elemento de la lista y lo saca. <br>
     * <b>pre:</b> la lista tiene por lo menos un nodo. <br>
     * <b>post: </b> Se tómo el primer elemento de la lista y fue eliminado de la misma.
     * @return Primer nodo de la lista
     */
    public NodoHuffman tomarPrimero( )
    {
        NodoHuffman p = cab;
        cab = cab.desconectarPrimero( );
        numElems--;
        return p;
    }

    /**
     * Genera el árbol de huffman con todos los nodos de la lista. <br>
     * <b>post: </b> Se generó el árbol de Huffman con todos los nodos de la lista.
     */
    public void generarArbolHuffman( )
    {
        while( numElems > 1 )
        {
            // Hay por lo menos dos elementos en la lista de nodos
            NodoHuffman a1 = tomarPrimero( );
            NodoHuffman a2 = tomarPrimero( );
            // Crea la mezcla de los dos nodos
            NodoHuffman nuevoNodo = new NodoHuffman( a1, a2 );
            // Agrega el nodo a la lista
            insertarOrdenado( nuevoNodo );
        }
        // Genera los códigos para todos los nodos del árbol
        if( cab != null )
            cab.generarCodigos( );
    }
}
