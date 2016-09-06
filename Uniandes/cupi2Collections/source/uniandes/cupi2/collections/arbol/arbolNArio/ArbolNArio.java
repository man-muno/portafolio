/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolNArio.java,v 1.1 2008/03/30 01:53:03 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Manuel Muñoz - May 16, 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbol.arbolNArio;

import java.io.Serializable;

import uniandes.cupi2.collections.arbol.IArbol;
import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.iterador.IteradorSimple;

/**
 * Implementación del un árbol n-ario
 * @param <T> Tipo de datos que contiene cada nodo del árbol
 */
public class ArbolNArio<T> implements Serializable, IArbol<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Raíz del árbol
     */
    private NodoArbolNArio<T> raiz;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de un árbol vacío.
     */
    public ArbolNArio( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz del árbol para navegarlo <b>post: </b> Se retornó la raíz del árbol para navegarlo.
     * @return Raíz del árbol para navegarlo
     */
    public NodoArbolNArio<T> darRaiz( )
    {
        return raiz;
    }

    /**
     * Cambia la raíz del árbol. <b>post: </b> raiz = nodo.
     * @param Nodo que va a ser la nueva raíz del árbol.
     */
    public void definirRaiz( NodoArbolNArio<T> nodo )
    {
        raiz = nodo;
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#darAltura()
     */
    public int darAltura( )
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#darPeso()
     */
    public int darPeso( )
    {
        return ( raiz != null ) ? raiz.darPeso( ) : 0;
    }

    /**
     * Retorna el número de hojas que tiene el árbol <b>post: </b> Se retornó la cantidad de hojas que contiene el árbol
     * @return Cantidad de hojas del árbol. Entero mayor o igual a cero.
     */
    public int contarHojas( )
    {
        return ( raiz != null ) ? raiz.contarHojas( ) : 0;
    }

    /**
     * Retorna la cantidad de nodos que se encuentran en el nivel especificado. <b>post: </b> Se retornó la cantidad de nodos que contiene el nivel dado.
     * @param Número del nivel que se quiere averiguar. Entero mayor o igual a cero.
     * @return Número de nodos del nivel. Entero mayor o igual a cero.
     */
    public int darTamanoNivel( int nivel )
    {
        return ( raiz != null ) ? raiz.darTamanoNivel( nivel ) : 0;
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#buscar(java.lang.Object)
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * Devuelve los elementos del árbol en inorden utilizando instrucciones recursivas. <br>
     * <b>post: </b> Se retornó el iterador para recorrer los elementos del árbol en inorden.
     * @return Iterador para recorrer los elementos del árbol en inorden
     */
    public Iterador<T> darInorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.inorden( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol en preorden utilizando instrucciones recursivas. <br>
     * <b>post: </b> Se retornó el iterador para recorrer los elementos del árbol en preorden.
     * @return Iterador para recorrer los elementos del árbol en preorden
     */
    public Iterador<T> darPreorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.preorden( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol en postorden utilizando instrucciones recursivas. <br>
     * <b>post: </b> Se retornó el iterador para recorrer los elementos del árbol en postorden.
     * @return Iterador para recorrer los elementos del árbol en postorden
     */
    public Iterador<T> darPostorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.postorden( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol utilizando un recorrido por niveles<br>
     * <b>post: </b> Se retornó el iterador para recorrer los elementos del árbol por niveles.
     * @return Iterador con los elementos del árbol en un recorrido por niveles
     */
    public Iterador<T> darRecorridoNiveles( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.darRecorridoNiveles( resultado );
        }
        return resultado;
    }

    /**
     * Retorna los nodos que se encuentran en un nivel especificado. <b>pre: </b> Número del nivel entero mayor o igual a cero. <b>post: </b> Se retornó el iterador para
     * recorrer los elementos del árbol en un nivel dado.
     * @param Entero mayor o igual a cero.
     * @return Iterador con los elementos de un nivel dado. Vacío si no se encuentra el nivel.
     */
    public Iterador<T> darNivel( int nivel )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.darNivel( nivel, resultado );
        }
        return resultado;
    }
}
