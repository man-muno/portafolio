/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolBinarioOrdenado.java,v 1.8 2006/06/05 16:43:45 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 13, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbolBinarioOrdenado;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.iterador.IteradorSimple;

/**
 * Implementación de un árbol binario ordenado
 * @param <T> Tipo de datos que contiene cada nodo del árbol
 */
public class ArbolBinarioOrdenado<T extends Comparable<? super T>>
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

    /**
     * Agrega un nuevo elemento al árbol binario ordenado. <br>
     * <b>pre:</b> elemento!=null. <br>
     * <b>post:</b> Se agregó el elemento al árbol de manera ordenada.
     * @param elemento Elemento que se va a agregar
     * @throws ExisteException El elemento especificado ya existe en el árbol
     */
    public void insertar( T elemento ) throws ExisteException
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

    /**
     * Elimina del árbol el elemento que llega como parámetro. <br>
     * <b>pre:</b> elemento!=null. <br>
     * <b>post:</b> Se eliminó el elemento si existía en la estructura.
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
     * Localiza y retorna un elemento en el árbol, recibiendo un modelo del mismo como parámetro. <br>
     * <b>pre:</b> modelo!=null. <br>
     * <b>post:</b> Se retornó elemento del árbol que corresponde al modelo o null si no encuentra el elemento.
     * @param modelo Descripción del elemento que se va a buscar en el árbol. Debe contener por lo menos la información mínima necesaria para que el método de comparación del
     *        nodo pueda establecer una relación de orden
     * @return E elemento del árbol que corresponde al modelo o null si no encuentra el elemento
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

    /**
     * Devuelve la altura del árbol. <br>
     * <b>post:</b> Se retornó la altura del árbol.
     * @return Altura del árbol
     */
    public int darAltura( )
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }

    /**
     * Devuelve el peso del árbol. <br>
     * <b>post:</b> Se retornó el peso del árbol.
     * @return Peso del árbol
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