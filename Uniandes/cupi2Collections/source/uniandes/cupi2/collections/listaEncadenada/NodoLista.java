/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 3, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.listaEncadenada;

/**
 * Nodo de la lista doblemente encadenada
 * @param <T> Tipo de datos almacenados
 */
public class NodoLista<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista a la cual pertenece el nodo
     */
    private ListaEncadenada<T> lista;

    /**
     * Elemento del nodo
     */
    private T elemento;

    /**
     * Siguiente elemento encadenado
     */
    private NodoLista<T> sigNodo;

    /**
     * Anterior elemento encadenado
     */
    private NodoLista<T> antNodo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo con el elemento especificado. <br>
     * <b>post: </b> Se construyó el nodo con el elemento especificado, sigNodo= null, antNodo= null, elemento= pElemento y lista= pLista.
     * @param pElemento Elemento del nodo
     * @param pLista Lista Encadenada a la cual pertenece el nodo
     */
    public NodoLista( T pElemento, ListaEncadenada<T> pLista )
    {
        elemento = pElemento;
        sigNodo = null;
        antNodo = null;
        lista = pLista;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la lista encadenada a la cual pertenece este nodo. <br>
     * <b>post: </b> Se retornó la lista encadenada a la cual pertenece el nodo.
     * @return Lista encadenada dueña del nodo
     */
    public ListaEncadenada darLista( )
    {
        return lista;
    }

    /**
     * Retorna el elemento que se encuentra en el nodo. <br>
     * <b>post: </b> Se retornó el elemento del nodo.
     * @return Elemento del nodo
     */
    public T darElemento( )
    {
        return elemento;
    }

    /**
     * Retorna el siguiente nodo en la lista. <br>
     * <b>post: </b> Se retornó el siguiente nodo en la lista.
     * @return Siguiente nodo en la lista
     */
    public NodoLista<T> darSiguiente( )
    {
        return sigNodo;
    }

    /**
     * Retorna el nodo anterior en la lista. <br>
     * <b>post: </b>Se retornó el nodo anterior en la lista.
     * @return Nodo anterior en la lista
     */
    public NodoLista<T> darAnterior( )
    {
        return antNodo;
    }

    /**
     * Inserta el nodo antes del actual. <br>
     * <b>pre: </b> nodo!=null. <br>
     * <b>post: </b> Se insertó el nodo especificado antes del actual.
     * @param nodo Nodo a insertar
     */
    public void insertarAntes( NodoLista<T> nodo )
    {
        nodo.sigNodo = this;
        nodo.antNodo = antNodo;
        if( antNodo != null )
            antNodo.sigNodo = nodo;
        antNodo = nodo;
    }

    /**
     * Inserta el nodo después del actual. <br>
     * <b>pre: </b> nodo!=null. <br>
     * <b>post: </b> Se insertó el nodo especificado después del nodo actual.
     * @param nodo Nodo a insertar
     */
    public void insertarDespues( NodoLista<T> nodo )
    {
        nodo.sigNodo = sigNodo;
        nodo.antNodo = this;
        if( sigNodo != null )
            sigNodo.antNodo = nodo;
        sigNodo = nodo;
    }

    /**
     * Desconecta el nodo de la lista suponiendo que es el primero. <br>
     * <b>pre: </b> El nodo es el primero de la lista. <br>
     * <b>post: </b> Se desconectó el nodo de la lista, sigNodo= null y antNodo= null.
     * @return Nodo con el cual comienza la lista ahora
     */
    public NodoLista<T> desconectarPrimero( )
    {
        NodoLista<T> p = sigNodo;
        sigNodo = null;
        if( p != null )
        {
            p.antNodo = null;
        }
        return p;
    }

    /**
     * Desconecta el nodo de la lista suponiendo que no es el primero. <br>
     * <b>pre: </b> El nodo no es el primero de la lista. <br>
     * <b>post: </b> El nodo fue desconectado de la lista.
     */
    public void desconectarNodo( )
    {
        NodoLista<T> ant = antNodo;
        NodoLista<T> sig = sigNodo;
        antNodo = null;
        sigNodo = null;
        ant.sigNodo = sig;
        if( sig != null )
        {
            sig.antNodo = ant;
        }
    }

    /**
     * Elimina el nodo de la lista. <br>
     * <b>post: </b>Se eliminó el elemento de la lista.
     */
    public void eliminar( )
    {
        try
        {
            lista.eliminarNodo( this );
        }
        catch( NoExisteException e )
        {
            //
            // Esto nunca debe suceder
        }
    }

    /**
     * Convierte el nodo a un String. <br>
     * <b>post: </b> Se retornó la representación en String del nodo. Dicha representación corresponde a la representación en String del elemento que contiene. <br>
     * @return La representación en String del nodo
     */
    @Override
    public String toString( )
    {
        return elemento.toString( );
    }
}
