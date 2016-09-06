/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Elemento.java,v 1.1 2007/02/01 15:18:00 jvillalo2 Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Manuel Mu�oz - Jan 29, 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.mundo;

public class Elemento
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El elemento que se tiene despu�s del actual
     */
    private Elemento siguiente;

    /**
     * El elemento que se tiene antes del actual
     */
    private Elemento anterior;

    /**
     * Valor del elemento
     */
    private int valor;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros
     */
    public Elemento( int pValor )
    {
        valor = pValor;
        siguiente = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor que tiene el elemento
     */
    public int obtenerValor( )
    {
        return valor;
    }

    /**
     * Inserta el nodo antes del actual. <br>
     * <b>pre: </b> nodo!=null. <br>
     * <b>post: </b> Se insert� el nodo especificado antes del actual.
     * @param nodo Nodo a insertar
     */
    public void insertarAntes( Elemento nodo )
    {
        nodo.siguiente = this;
        nodo.anterior = anterior;
        if( anterior != null )
            anterior.siguiente = nodo;
        anterior = nodo;
    }

    /**
     * Inserta el nodo despu�s del actual. <br>
     * <b>pre: </b> nodo!=null. <br>
     * <b>post: </b> Se insert� el nodo especificado despu�s del nodo actual.
     * @param nodo Nodo a insertar
     */
    public void insertarDespues( Elemento nodo )
    {
        nodo.siguiente = siguiente;
        nodo.anterior = this;
        if( siguiente != null )
            siguiente.anterior = nodo;
        siguiente = nodo;
    }

    /**
     * Desconecta el nodo de la lista suponiendo que es el primero. <br>
     * <b>pre: </b> El nodo es el primero de la lista. <br>
     * <b>post: </b> Se desconect� el nodo de la lista, sigNodo= null y antNodo= null.
     * @return Nodo con el cual comienza la lista ahora
     */
    public Elemento desconectarPrimero( )
    {
        Elemento p = siguiente;
        siguiente = null;
        if( p != null )
        {
            p.anterior = null;
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
        Elemento ant = anterior;
        Elemento sig = siguiente;
        anterior = null;
        siguiente = null;
        ant.siguiente = sig;
        if( sig != null )
        {
            sig.anterior = ant;
        }
    }

    /**
     * Retorna el siguiente elemento al actual
     * @return Elemento, pude ser null
     */
    public Elemento obtenerSiguiente( )
    {
        return siguiente;
    }

    /**
     * Representaci�n en String del elemento
     */
    public String toString( )
    {
        return "" + valor;
    }
}
