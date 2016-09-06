/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoPila.java,v 1.6 2006/06/02 19:37:16 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.pilaEncadenada;

/**
 * Nodo de la pila encadenada encadenada
 * @param <T> Tipo de datos almacenados
 */
public class NodoPila<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Elemento del nodo
     */
    private T elemento;

    /**
     * Siguiente nodo encadenado
     */
    private NodoPila<T> sigNodo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo con el elemento especificado. <br>
     * <b>post: </b> Se construy� el nodo con el elemento especificado, sigNodo= null, y elemento= pElemento. <br>
     * @param pElemento Elemento del nodo
     */
    public NodoPila( T pInfo )
    {
        elemento = pInfo;
        sigNodo = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el elemento que se encuentra en el nodo. <br>
     * <b>post: </b> Se retorn� el elemento del nodo.
     * @return Elemento del nodo
     */
    public T darElemento( )
    {
        return elemento;
    }

    /**
     * Desconecta el nodo de la pila suponiendo que es el primero. <br>
     * <b>pre: </b> El nodo es el primero de la pila. <br>
     * <b>post: </b> Se desconect� el nodo de la pila y sigNodo= null.
     * @return Nodo con el cual comienza la pila ahora
     */
    public NodoPila<T> desconectarPrimero( )
    {
        NodoPila<T> p = sigNodo;
        sigNodo = null;
        return p;
    }

    /**
     * Inserta el nodo antes del actual. <br>
     * <b>pre: </b> nodo!=null. <br>
     * <b>post: </b> Se insert� el nodo especificado antes del actual.
     * @param nodo Nodo a insertar
     */
    public NodoPila<T> insertarAntes( NodoPila<T> nodo )
    {
        nodo.sigNodo = this;
        return nodo;
    }

    /**
     * Retorna el siguiente nodo en la pila. <br>
     * <b>post: </b> Se retorn� el siguiente nodo en la pila.
     * @return Siguiente nodo en la pila
     */
    public NodoPila<T> darSiguiente( )
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
