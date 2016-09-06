/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolBinarioOrdenado.java,v 1.1 2008/03/30 01:53:03 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 13, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbol.arbolBinarioOrdenado;

import java.io.Serializable;

import uniandes.cupi2.collections.arbol.ElementoExisteException;
import uniandes.cupi2.collections.arbol.ElementoNoExisteException;
import uniandes.cupi2.collections.arbol.IArbolOrdenado;
import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.iterador.IteradorSimple;

/**
 * Implementación de un árbol binario ordenado
 * @param <T> Tipo de datos que contiene cada nodo del árbol. Debe implementar la interface Comparable
 */
public class ArbolBinarioOrdenado<T extends Comparable<? super T>> implements Serializable, IArbolOrdenado<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Raíz del árbol binario ordenado
     */
    private NodoArbolBinarioOrdenado<T> raiz;

    /**
     * Peso del árbol binario ordenado
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del árbol binario ordenado vacío. <br>
     * <b>post: </b> Se construyó un árbol binario ordenado vacío.
     */
    public ArbolBinarioOrdenado( )
    {
        raiz = null;
        peso = 0;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz del árbol para navegarlo. <br>
     * <b>post: </b> Se retornó la raíz del árbol para navegarlo.
     * @return Raíz del árbol para navegarlo
     */
    public NodoArbolBinarioOrdenado<T> darRaiz( )
    {
        return raiz;
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbolOrdenado#insertar(java.lang.Comparable)
     */
    public void insertar( T elemento ) throws ElementoExisteException
    {
        if( raiz == null )
        {
            // Caso 1: el árbol es vacío
            raiz = new NodoArbolBinarioOrdenado<T>( elemento );
        }
        else
        {
            // Caso 2: el árbol no es vacío
            raiz.insertar( elemento );
        }
        peso++;
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbolOrdenado#eliminar(java.lang.Comparable)
     */
    public void eliminar( T elemento ) throws ElementoNoExisteException
    {
        if( raiz != null )
        {
            // Caso 1: el árbol no es vacío
            raiz = raiz.eliminar( elemento );
            peso--;
        }
        else
        {
            // Caso 2: el árbol es vacío
            throw new ElementoNoExisteException( "El elemento especificado no existe en el árbol" );
        }
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#buscar(java.lang.Object)
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>post:</b> Se retornó el iterador con los elementos del árbol en inorden.
     * @return Iterador con los elementos del árbol en inorden
     */
    public Iterador<T> inorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( peso );
        if( raiz != null )
        {
            raiz.inorden( resultado );
        }
        return resultado;
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
        return peso;
    }

    /**
     * Devuelve el elemento mayor del árbol. <br>
     * <b>post:</b> Se retornó el elemento mayor del árbol o null si el árbol está vacío.
     * @return El elemento mayor del árbol o null si el árbol está vacío
     */
    public T darMayor( )
    {
        return ( raiz != null ) ? raiz.darMayor( ) : null;
    }

    /**
     * Devuelve el elemento menor del árbol. <br>
     * <b>post:</b> Se retornó el elemento menor del árbol o null si el árbol está vacío.
     * @return El elemento menor del árbol o null si el árbol está vacío
     */
    public T darMenor( )
    {
        return ( raiz != null ) ? raiz.darMenor( ) : null;
    }
}