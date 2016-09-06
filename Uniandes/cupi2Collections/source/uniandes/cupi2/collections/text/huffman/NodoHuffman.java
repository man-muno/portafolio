/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: J. Villalobos - 07-mar-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.text.huffman;

/**
 * Representa un nodo en el �rbol de huffman
 */
public class NodoHuffman
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Informaci�n asociada con el nodo
     */
    private InfoCaracter info;

    /**
     * Frecuencia del nodo
     */
    private int frecuencia;

    /**
     * Siguiente nodo en la lista encadenada
     */
    private NodoHuffman sigNodo;

    /**
     * Anterior nodo en la lista encadenada
     */
    private NodoHuffman antNodo;

    /**
     * Hijo izquierdo en el �rbol huffman
     */
    private NodoHuffman izqNodo;

    /**
     * Hijo derecho en el �rbol huffman
     */
    private NodoHuffman derNodo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo con un caracter (hoja). <br>
     * <b>post: </b> Se construy� un nodo con el caracter especificado.
     * @param c Caracter ubicado en este nodo
     */
    public NodoHuffman( char c )
    {
        info = new InfoCaracter( c );
        frecuencia = 0;
        sigNodo = null;
        antNodo = null;
        izqNodo = null;
        derNodo = null;
    }

    /**
     * Constructor del nodo intermedio (padre con 2 hijos). <br>
     * <b>post: </b> Se construy� en nodo con los hijos especificados.
     * @param nodoIzq Hijo izquierdo
     * @param nodoDer Hijo derecho
     */
    public NodoHuffman( NodoHuffman nodoIzq, NodoHuffman nodoDer )
    {
        info = new InfoCaracter( '*' );
        frecuencia = nodoIzq.frecuencia + nodoDer.frecuencia;
        sigNodo = null;
        antNodo = null;
        izqNodo = nodoIzq;
        derNodo = nodoDer;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Registra un nuevo uso del caracter. Aumenta su frecuencia en uno .<br>
     * <b>post:</b> Se regirtr� un nuevo uso del car�cter, frecuencia= frecuencia+1.
     */
    public void registrarNuevo( )
    {
        frecuencia++;
    }

    /**
     * Devuelve la frecuencia de uso <b>post: Se retorn� la frecuencia de uso</b>
     * @return Frecuencia de uso
     */
    public int darFrecuencia( )
    {
        return frecuencia;
    }

    /**
     * Devuelve el caracter en el nodo. '*' si no es hoja. <br>
     * <b>post: </b> Se retorn� el caracter en el nodo.
     * @return Caracter en el nodo
     */
    public InfoCaracter darInfoCaracter( )
    {
        return info;
    }

    /**
     * Devuelve el siguiente nodo de la lista encadenada. <br>
     * <b>post: </b> Se retorn� el siguiente nodo en la lista encadenada.
     * @return Siguiente nodo
     */
    public NodoHuffman darSiguiente( )
    {
        return sigNodo;
    }

    /**
     * Devuelve el anterior nodo de la lista encadenada. <br>
     * <b>post: </b> Se retorn� el anterior nodo en la lista encadenada.
     * @return Anterior nodo
     */
    public NodoHuffman darAnterior( )
    {
        return antNodo;
    }

    /**
     * Devuelve el hijo izquierdo del �rbol Huffman. <br>
     * <b>post: </b> Se retorn� el nodo izquierdo del �rbol de Huffman.
     * @return Hijo Izquierdo
     */
    public NodoHuffman darIzquierdo( )
    {
        return izqNodo;
    }

    /**
     * Devuelve el hijo derecho del �rbol Huffman. <br>
     * <b>post: </b> Se retorn� el nodo derecho del �rbol de Huffman.
     * @return Hijo Derecho
     */
    public NodoHuffman darDerecho( )
    {
        return derNodo;
    }

    /**
     * Inserta un nodo antes en la lista encadenada. <br>
     * <b>post: </b> Se insert� en nodo antes del nodo actual, antNodo= nodo.
     * @param nodo Nodo a insertar
     */
    public void insertarAntes( NodoHuffman nodo )
    {
        nodo.sigNodo = this;
        nodo.antNodo = antNodo;
        if( antNodo != null )
        {
            antNodo.sigNodo = nodo;
        }
        antNodo = nodo;
    }

    /**
     * Inserta un nodo despu�s en la lista encadenada. <br>
     * <b>post: </b> Se insert� en nodo despu�s del nodo actual, sigNodo= nodo.
     * @param nodo Nodo a insertar
     */
    public void insertarDespues( NodoHuffman nodo )
    {
        nodo.sigNodo = sigNodo;
        nodo.antNodo = this;
        if( sigNodo != null )
        {
            sigNodo.antNodo = nodo;
        }
        sigNodo = nodo;
    }

    /**
     * Desconecta este nodo de la lista suponiendo que es el primero. <br>
     * <b>pre: </b> El nodo es el primero de la lista. <br>
     * <b>post: </b> Se desconecto el nodo de la lista.
     * @return Nodo con el cual comienza la lista ahora
     */
    public NodoHuffman desconectarPrimero( )
    {
        NodoHuffman p = sigNodo;
        sigNodo = null;
        if( p != null )
        {
            p.antNodo = null;
        }
        return p;
    }

    /**
     * Genera los c�digos para los nodos en el �rbol de Huffman. <br>
     * <b>post: </b> Se generar�n los c�digos para los nodos del �rbol.
     */
    public void generarCodigos( )
    {
        if( izqNodo != null )
        {
            izqNodo.info.asignarCodigo( info, false );
            izqNodo.generarCodigos( );
        }
        if( derNodo != null )
        {
            derNodo.info.asignarCodigo( info, true );
            derNodo.generarCodigos( );
        }
    }

    /**
     * Convierte el nodo a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n del nodo en String: la representaci�n tiene el formato "caracter+(frecuencia)".
     * @return La representaci�n en String del nodo
     */
    @Override
    public String toString( )
    {
        return info + " (" + frecuencia + ")";
    }
}
