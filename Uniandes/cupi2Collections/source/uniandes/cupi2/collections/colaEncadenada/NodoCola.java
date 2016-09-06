/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoCola.java,v 1.9 2006/06/02 19:37:16 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.colaEncadenada;

/**
 * Nodo cola
 * @param <T> Tipo de elemento que va a contener cada nodo del �rbol
 */
public class NodoCola<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Elemento del nodo
     */
    private T elemento;

    /**
     * Siguiente elemento encadenado
     */
    private NodoCola<T> sigNodo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo. <br>
     * <b>post: </b> Se construy� el nodo con el elemento especificado, sigNodo=null y info= pElemento.
     * @param pElemento Elemento que va a ser almacenado en el nodo
     */
    public NodoCola( T pElemento )
    {
        elemento = pElemento;
        sigNodo = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el elemento del nodo. <br>
     * <b>post: </b> Se retorn� el elemento contenido en el nodo.
     * @return El elemento contenido en el nodo
     */
    public T darElemento( )
    {
        return elemento;
    }

    /**
     * Desconecta el nodo de la cola suponiendo que es el primero. <br>
     * <b>pre: </b> El nodo actual es el primer nodo de la cola. <br>
     * <b>post: </b> Se retorn� el nodo con el cual comienza la cola ahora, sigNodo=null.
     * @return Nodo con el cual comienza la cola ahora
     */
    public NodoCola<T> desconectarPrimero( )
    {
        NodoCola<T> p = sigNodo;
        sigNodo = null;
        return p;
    }

    /**
     * Inserta el nodo especificado despu�s del nodo actual. <br>
     * <b>post: </b> Se insert� el nodo despu�s del nodo actual lo que implica que sigNodo=nodo.
     * @param nodo El nodo a ser insertado
     * @return Nodo que se insert� despuesto del nodo actual
     */
    public NodoCola<T> insertarDespues( NodoCola<T> nodo )
    {
        sigNodo = nodo;
        return nodo;
    }

    /**
     * Retorna el siguiente nodo. <br>
     * <b>post: </b> Se retorn� el siguiente nodo.
     * @return El nodo siguiente
     */
    public NodoCola<T> darSiguiente( )
    {
        return sigNodo;
    }

    /**
     * Convierte el nodo a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n en String del nodo. Dicha representaci�n corresponde a la representaci�n en String del elemento que contiene.
     * @return La representaci�n en String del nodo
     */
    @Override
    public String toString( )
    {
        return elemento.toString( );
    }
}
