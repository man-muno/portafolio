/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolAVL.java,v 1.1 2008/03/30 01:53:03 jua-gome Exp $
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

package uniandes.cupi2.collections.arbol.avl;

import java.io.Serializable;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.iterador.IteradorSimple;
import uniandes.cupi2.collections.arbol.ElementoExisteException;
import uniandes.cupi2.collections.arbol.ElementoNoExisteException;
import uniandes.cupi2.collections.arbol.IArbolOrdenado;

/**
 * Implementación de un árbol binario ordenado balanceado por altura: árbol AVL
 * @param <T> Tipo de datos que contiene cada nodo del árbol
 */
public class ArbolAVL<T extends Comparable<? super T>> implements Serializable, IArbolOrdenado<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Raíz del árbol AVL
     */
    private NodoAVL<T> raiz;

    /**
     * Peso del árbol AVL
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del árbol AVL vacío. <br>
     * <b>post: </b> Se construyó un árbol AVL vacío.
     */
    public ArbolAVL( )
    {
        raiz = null;
        peso = 0;
    }

    /**
     * Constructor del árbol AVL con raíz. <br>
     * <b>pre: </b> r!=null y p>=0. <b>post: </b> Se construyó un árbol AVL con la raiz r.
     * @param r Raiz del árbol
     * @param p Peso del árbol
     */
    public ArbolAVL( NodoAVL<T> r, int p )
    {
        raiz = r;
        peso = p;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz del árbol para navegarlo. <br>
     * <b>post: </b> Se retornó la raíz del árbol para navegarlo.
     * @return Raíz del árbol para navegarlo
     */
    public NodoAVL<T> darRaiz( )
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
            raiz = new NodoAVL<T>( elemento );
        }
        else
        {
            // Caso 2: el árbol no es vacío
            raiz = raiz.insertar( elemento );
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
     * <b>post: </b> Se retornó el iterador con los elementos del árbol en inorden.
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
     * Devuelve el elemento mayor del árbol AVL. <br>
     * <b>post: </b> Se retornó el elemento mayor del árbol AVL o null si el árbol está vacio.
     * @return Elemento mayor del árbol AVL o null si el árbol está vacio
     */
    public T darMayor( )
    {
        return ( raiz != null ) ? raiz.darMayor( ) : null;
    }

    /**
     * Devuelve el elemento menor del árbol AVL. <br>
     * <b>post: </b> Se retornó el elemento menor del árbol AVL o null si el árbol está vacio.
     * @return Elemento menor del árbol AVL o null si el árbol está vacio
     */
    public T darMenor( )
    {
        return ( raiz != null ) ? raiz.darMenor( ) : null;
    }

    /**
     * Devuelve los elementos del árbol utilizando un recorrido por niveles. <br>
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
}
