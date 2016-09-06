/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.colaPrioridad;

import java.io.Serializable;

/**
 * Nodo de la cola encadenada
 * @param <T> Tipo de elemento con el cual se le dar� la prioridad al nodo de la cola
 * @param <E> Tipo de elemento que va a contener cada nodo de la cola
 */
public class NodoColaPrioridad<T extends Comparable<? super T>, E> implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Elemento del nodo
     */
    private E elemento;

    /**
     * Elemento que le dara la prioridad al nodo de la cola
     */
    private T prioridad;

    /**
     * Siguiente elemento encadenado
     */
    private NodoColaPrioridad<T, E> sigNodo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros del nodo. <br>
     * <b>post: </b> Se construy� el nodo con el elemento especificado, sigNodo=null y elemento = pElemento.<br>
     * @param pPrioridad Elemento que le da la prioridad al nodo de la cola. Diferente de null.<br>
     * @param pElemento Elemento que va a ser almacenado en el nodo. Diferente de null<br>
     */
    public NodoColaPrioridad( T pPrioridad, E pElemento )
    {
        elemento = pElemento;
        prioridad = pPrioridad;
        sigNodo = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el elemento del nodo. <br>
     * <b>post: </b> Se retorn� el elemento contenido en el nodo.<br>
     * @return El elemento contenido en el nodo. Diferente de null<br>
     */
    public E darElemento( )
    {
        return elemento;
    }

    /**
     * Retorna el elemento que le da la prioridad al nodo. <br>
     * <b>post: </b> Se retorn� el elemento que le da la prioridad al nodo.<br>
     * @return El elemento que le da la prioridad al nodo. Diferente de null<br>
     */
    public T darPrioridad( )
    {
        return prioridad;
    }

    /**
     * Desconecta el nodo de la cola suponiendo que es el primero. <br>
     * <b>pre: </b> El nodo actual es el primer nodo de la cola. <br>
     * <b>post: </b> Se retorn� el nodo con el cual comienza la cola ahora, sigNodo=null.<br>
     * @return Nodo con el cual comienza la cola ahora.<br>
     */
    public NodoColaPrioridad<T, E> desconectarPrimero( )
    {
        NodoColaPrioridad<T, E> p = sigNodo;
        sigNodo = null;
        return p;
    }

    /**
     * Inserta el nodo especificado despu�s del nodo actual. <br>
     * <b>post: </b> Se insert� el nodo despu�s del nodo actual lo que implica que sigNodo=nodo.<br>
     * @param nodo El nodo a ser insertado<br>
     * @return Nodo que se insert� despu�s del nodo actual<br>
     */
    public NodoColaPrioridad<T, E> insertarDespues( NodoColaPrioridad<T, E> nodo )
    {
        sigNodo = nodo;
        return nodo;
    }

    /**
     * Retorna el siguiente nodo. <br>
     * <b>post: </b> Se retorn� el siguiente nodo.<br>
     * @return El nodo siguiente<br>
     */
    public NodoColaPrioridad<T, E> darSiguiente( )
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
        return "(" + prioridad.toString( ) + "," + elemento.toString( ) + ")";
    }
}
