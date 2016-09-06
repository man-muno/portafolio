/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
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
 * @param <T> Tipo de elemento con el cual se le dará la prioridad al nodo de la cola
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
     * Constructor por parámetros del nodo. <br>
     * <b>post: </b> Se construyó el nodo con el elemento especificado, sigNodo=null y elemento = pElemento.<br>
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
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el elemento del nodo. <br>
     * <b>post: </b> Se retornó el elemento contenido en el nodo.<br>
     * @return El elemento contenido en el nodo. Diferente de null<br>
     */
    public E darElemento( )
    {
        return elemento;
    }

    /**
     * Retorna el elemento que le da la prioridad al nodo. <br>
     * <b>post: </b> Se retornó el elemento que le da la prioridad al nodo.<br>
     * @return El elemento que le da la prioridad al nodo. Diferente de null<br>
     */
    public T darPrioridad( )
    {
        return prioridad;
    }

    /**
     * Desconecta el nodo de la cola suponiendo que es el primero. <br>
     * <b>pre: </b> El nodo actual es el primer nodo de la cola. <br>
     * <b>post: </b> Se retornó el nodo con el cual comienza la cola ahora, sigNodo=null.<br>
     * @return Nodo con el cual comienza la cola ahora.<br>
     */
    public NodoColaPrioridad<T, E> desconectarPrimero( )
    {
        NodoColaPrioridad<T, E> p = sigNodo;
        sigNodo = null;
        return p;
    }

    /**
     * Inserta el nodo especificado después del nodo actual. <br>
     * <b>post: </b> Se insertó el nodo después del nodo actual lo que implica que sigNodo=nodo.<br>
     * @param nodo El nodo a ser insertado<br>
     * @return Nodo que se insertó después del nodo actual<br>
     */
    public NodoColaPrioridad<T, E> insertarDespues( NodoColaPrioridad<T, E> nodo )
    {
        sigNodo = nodo;
        return nodo;
    }

    /**
     * Retorna el siguiente nodo. <br>
     * <b>post: </b> Se retornó el siguiente nodo.<br>
     * @return El nodo siguiente<br>
     */
    public NodoColaPrioridad<T, E> darSiguiente( )
    {
        return sigNodo;
    }

    /**
     * Convierte el nodo a un String. <br>
     * <b>post: </b> Se retornó la representación en String del nodo. Dicha representación corresponde a la representación en String del elemento que contiene.
     * @return La representación en String del nodo
     */
    @Override
    public String toString( )
    {
        return "(" + prioridad.toString( ) + "," + elemento.toString( ) + ")";
    }
}
