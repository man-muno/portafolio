/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolAVL.java,v 1.21 2006/08/08 14:12:34 da-romer Exp $
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

package uniandes.cupi2.collections.avl;

import uniandes.cupi2.collections.iterador.*;

/**
 * Implementación de un árbol binario ordenado balanceado por altura: árbol AVL
 * @param <T> Tipo de datos que contiene cada nodo del árbol
 */
public class ArbolAVL<T extends Comparable<? super T>>
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
     * <b>pre: </b> r!=null y p>=0. 
     * <b>post: </b> Se construyó un árbol AVL vacío.
     * @param r Raiz del árbol
     * @param p Peso del árbol
     */
    public ArbolAVL( NodoAVL<T> r, int p )
    {
        raiz = null;
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

    /**
     * Agrega un nuevo elemento al árbol AVL. <br>
     * <b>post:</b> se agregó el elemento al árbol de manera ordenada.
     * @param elemento Elemento que se va a agregar
     * @throws ExisteException El elemento especificado ya existe en el árbol
     */
    public void insertar( T elemento ) throws ExisteException
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

    /**
     * Elimina del árbol el elemento que llega como parámetro. <br>
     * <b>post: </b> Se eliminó el elemento especificado si existía en la estructura.
     * @param elemento Elemento que se va a eliminar
     * @throws NoExisteException El elemento especificado no existe en el árbol
     */
    public void eliminar( T elemento ) throws NoExisteException
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
            throw new NoExisteException( "El elemento especificado no existe en el árbol" );
        }
    }

    /**
     * Localiza y retorna un elemento en el árbol AVL, recibiendo un modelo del mismo como parámetro. <br>
     * <b>post: </b> Se retornó el elemento del árbol que corresponde al modelo o null si no existe ninguno.
     * @param modelo Descripción del elemento que se va a buscar en el árbol. Debe contener por lo menos la información mínima necesaria para que el método de comparación del
     *        nodo pueda establecer una relación de orden
     * @return E elemento del árbol que corresponde al modelo o null si no existe ninguno
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

    /**
     * Devuelve la altura del árbol. <br>
     * <b>post: </b> Se retornó la altura del árbol.
     * @return Altura del árbol
     */
    public int darAltura( )
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }

    /**
     * Devuelve el peso del árbol AVL. <br>
     * <b>post: </b> Se retornó el peso del árbol.
     * @return Peso del árbol AVL
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
