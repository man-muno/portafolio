/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoArbolBinarioOrdenado.java,v 1.9 2006/06/05 16:42:28 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - 25/02/2006
 * Autor: Pablo Barvo - 25/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbolBinarioOrdenado;

import uniandes.cupi2.collections.iterador.*;

/**
 * Nodo de un árbol binario ordenado
 * @param <T> Tipo de elemento que va a contener cada nodo del árbol
 */
public class NodoArbolBinarioOrdenado<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Elemento almacenado en el nodo
     */
    private T elem;

    /**
     * Nodo a la derecha
     */
    private NodoArbolBinarioOrdenado<T> derNodo;

    /**
     * Nodo a la izquierda
     */
    private NodoArbolBinarioOrdenado<T> izqNodo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo. <br>
     * <b>post:</b> Se creó un nodo con el elemento específicado y con derNodo=null y izqNodo= null.
     * @param pElemento Elemento que va a ser almacenado en el nodo
     */
    public NodoArbolBinarioOrdenado( T pElemento )
    {
        elem = pElemento;
        derNodo = null;
        izqNodo = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz del árbol (elemento del nodo). <br>
     * <b>post:</b> Se retornó la raíz del árbol (elemento del nodo).
     * @return Raíz del árbol (elemento del nodo)
     */
    public T darRaiz( )
    {
        return elem;
    }

    /**
     * Devuelve el hijo derecho del nodo. <br>
     * <b>post:</b> Se retornó el hijo derecho del nodo.
     * @return Hijo derecho del nodo
     */
    public NodoArbolBinarioOrdenado<T> darHijoDerecho( )
    {
        return derNodo;
    }

    /**
     * Devuelve el hijo izquierdo del nodo. <br>
     * <b>post:</b> Se retornó el hijo izquierdo del nodo.
     * @return Hijo izquierdo del nodo
     */
    public NodoArbolBinarioOrdenado<T> darHijoIzquierdo( )
    {
        return izqNodo;
    }

    /**
     * Agrega un nuevo elemento en el árbol cuya raíz es el nodo actual. <br>
     * <b>pre:</b> pElemento!=null. <br>
     * <b>post:</b> Se insertó el elemento especificado en el árbol.
     * @param pElemento elemento que se va a agregar
     * @throws ExisteException El elemento ya existe en el árbol
     */
    public void insertar( T pElemento ) throws ExisteException
    {
        // Compara el valor con el valor del nodo
        int resultado = elem.compareTo( pElemento );
        if( resultado == 0 )
        {
            // Caso 1: El elemento está en el nodo raíz
            throw new ExisteException( "Elemento presente en el árbol" );
        }
        else if( resultado > 0 )
        {
            // Caso 2: El elemento se debe insertar en el subárbol izquierdo
            if( izqNodo == null )
                izqNodo = new NodoArbolBinarioOrdenado<T>( pElemento );
            else
                izqNodo.insertar( pElemento );
        }
        else
        {
            // Caso 3: El elemento se debe insertar en el subárbol derecho
            if( derNodo == null )
                derNodo = new NodoArbolBinarioOrdenado<T>( pElemento );
            else
                derNodo.insertar( pElemento );
        }
    }

    /**
     * Elimina el elemento dado como parámetro, del árbol cuya raíz es el nodo actual. <br>
     * <b>post:</b> Se eliminó el elemento si existía en árbol cuya raíz es el nodo actual.
     * @param pElemento Elemento que se va a eliminar
     * @return Raíz del árbol producto de eliminar del árbol que comienza en el nodo actual el elemento que llega como parámetro
     * @throws NoExisteException El elemento no se encontró en el árbol
     */
    public NodoArbolBinarioOrdenado<T> eliminar( T pElemento ) throws NoExisteException
    {
        // Compara el valor con el valor del nodo
        int resultado = elem.compareTo( pElemento );
        if( resultado == 0 )
        {
            // Caso 1: El elemento está en el nodo raíz
            if( izqNodo == null )
                return derNodo;
            else if( derNodo == null )
                return izqNodo;
            else
            {
                elem = izqNodo.darMayor( );
                izqNodo = izqNodo.eliminar( elem );
                return this;
            }
        }
        else if( resultado > 0 )
        {
            // Caso 2: El elemento debe estar en el subárbol izquierdo
            if( izqNodo == null )
            {
                throw new NoExisteException( "El elemento no encontrado" );
            }
            else
            {
                izqNodo = izqNodo.eliminar( pElemento );
                return this;
            }
        }
        else
        {
            // Caso 3: El elemento debe estar en el subárbol derecho
            if( derNodo == null )
            {
                throw new NoExisteException( "El elemento no encontrado" );
            }
            else
            {
                derNodo = derNodo.eliminar( pElemento );
                return this;
            }
        }
    }

    /**
     * Busca el elemento cuyo modelo viene dado como parámetro, en el árbol cuya raíz es el nodo actual. <br>
     * <b>pre:</b> modelo!=null. <br>
     * <b>post:</b> Se retornó el elemento que cumple con el modelo o null si no encuentra ninguno.
     * @param modelo Modelo del elemento que se va a buscar
     * @return Elemento que cumple con el modelo o null si no encuentra ninguno
     */
    public T buscar( T modelo )
    {
        // Compara el valor con el valor del nodo
        int resultado = elem.compareTo( modelo );
        if( resultado == 0 )
        {
            // Caso 1: El elemento está en el nodo raíz
            return elem;
        }
        else if( resultado > 0 )
        {
            // Caso 2: El elemento debería estar en el subárbol izquierdo
            return ( izqNodo != null ) ? izqNodo.buscar( modelo ) : null;
        }
        else
        {
            // Caso 3: El elemento debería estar en el subárbol derecho
            return ( derNodo != null ) ? derNodo.buscar( modelo ) : null;
        }
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en inorden. <br>
     * <b>pre:</b> resultado!=null. <br>
     * <b>post:</b> Se retornó el resultado del recorrido.
     * @param resultado Resultado del recorrido
     */
    public void inorden( IteradorSimple<T> resultado )
    {
        // Agrega los elementos del subárbol izquierdo
        if( izqNodo != null )
        {
            izqNodo.inorden( resultado );
        }

        try
        {
            // Agrega el elemento que se encuentra en el nodo
            resultado.agregar( elem );
        }
        catch( IteradorException e )
        {
            // Nunca debería aparecer esta excepción
        }

        // Agrega los elementos del subárbol derecho
        if( derNodo != null )
        {
            derNodo.inorden( resultado );
        }
    }

    /**
     * Devuelve la altura del árbol cuya raíz es el nodo actual. <br>
     * <b>post:</b> Se retornó la altura del árbol cuya raíz es el nodo actual.
     * @return Altura del árbol cuya raíz es el nodo actual
     */
    public int darAltura( )
    {
        int a1 = ( izqNodo == null ) ? 0 : izqNodo.darAltura( );
        int a2 = ( derNodo == null ) ? 0 : derNodo.darAltura( );
        return ( a1 >= a2 ) ? a1 + 1 : a2 + 1;
    }

    /**
     * Retorna el elemento mayor del árbol cuya raíz es el nodo actual. <br>
     * <b>post:</b> Se retornó el elemento mayor del árbol cuya raíz es el nodo actual o null si el árbol es vacio.
     * @return Elemento mayor del árbol cuya raíz es el nodo actual o null si el árbol es vacio
     */
    public T darMayor( )
    {
        NodoArbolBinarioOrdenado<T> nodo = mayorElemento( );
        return ( nodo == null ) ? null : nodo.darRaiz( );
    }

    /**
     * Retorna el elemento menor del árbol cuya raíz es el nodo actual. <br>
     * <b>post:</b> Se retornó el elemento menor del árbol cuya raíz es el nodo actual o null si el árbol es vacio.
     * @return Elemento menor del árbol cuya raíz es el nodo actual o null si el árbol es vacio
     */
    public T darMenor( )
    {
        NodoArbolBinarioOrdenado<T> nodo = menorElemento( );
        return ( nodo == null ) ? null : nodo.darRaiz( );
    }

    // -----------------------------------------------------------------
    // Operaciones Auxiliares
    // -----------------------------------------------------------------

    /**
     * Retorna el nodo con el mayor elemento del árbol. <br>
     * <b>post:</b> Se retornó el nodo con el mayor elemento del árbol.
     * @return Nodo Nodo con el mayor elemento del árbol
     */
    private NodoArbolBinarioOrdenado<T> mayorElemento( )
    {
        return ( derNodo == null ) ? this : derNodo.mayorElemento( );
    }

    /**
     * Retorna el nodo menor elemento del árbol. <br>
     * <b>post:</b> Se retornó el nodo con el menor elemento del árbol.
     * @return Nodo El nodo con el menor elemento del árbol
     */
    private NodoArbolBinarioOrdenado<T> menorElemento( )
    {
        return ( izqNodo == null ) ? this : izqNodo.menorElemento( );
    }
}
