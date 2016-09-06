/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolBinarioOrdenado.java,v 1.8 2006/06/05 16:43:45 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Implementaci�n de un �rbol binario ordenado
 * @param <T> Tipo de datos que contiene cada nodo del �rbol
 */
public class ArbolBinarioOrdenado<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ra�z del �rbol binario ordenado
     */
    private NodoArbolBinarioOrdenado<T> raiz;

    /**
     * Peso del �rbol binario ordenado
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del �rbol binario ordenado vac�o. <br>
     * <b>post: </b> Se construy� un �rbol binario ordenado vac�o.
     */
    public ArbolBinarioOrdenado( )
    {
        raiz = null;
        peso = 0;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ra�z del �rbol para navegarlo. <br>
     * <b>post: </b> Se retorn� la ra�z del �rbol para navegarlo.
     * @return Ra�z del �rbol para navegarlo
     */
    public NodoArbolBinarioOrdenado<T> darRaiz( )
    {
        return raiz;
    }

    /**
     * Agrega un nuevo elemento al �rbol binario ordenado. <br>
     * <b>pre:</b> elemento!=null. <br>
     * <b>post:</b> Se agreg� el elemento al �rbol de manera ordenada.
     * @param elemento Elemento que se va a agregar
     * @throws ExisteException El elemento especificado ya existe en el �rbol
     */
    public void insertar( T elemento ) throws ExisteException
    {
        if( raiz == null )
        {
            // Caso 1: el �rbol es vac�o
            raiz = new NodoArbolBinarioOrdenado<T>( elemento );
        }
        else
        {
            // Caso 2: el �rbol no es vac�o
            raiz.insertar( elemento );
        }
        peso++;
    }

    /**
     * Elimina del �rbol el elemento que llega como par�metro. <br>
     * <b>pre:</b> elemento!=null. <br>
     * <b>post:</b> Se elimin� el elemento si exist�a en la estructura.
     * @param elemento Elemento que se va a eliminar
     * @throws NoExisteException El elemento especificado no existe en el �rbol
     */
    public void eliminar( T elemento ) throws NoExisteException
    {
        if( raiz != null )
        {
            // Caso 1: el �rbol no es vac�o
            raiz = raiz.eliminar( elemento );
            peso--;
        }
        else
        {
            // Caso 2: el �rbol es vac�o
            throw new NoExisteException( "El elemento especificado no existe en el �rbol" );
        }
    }

    /**
     * Localiza y retorna un elemento en el �rbol, recibiendo un modelo del mismo como par�metro. <br>
     * <b>pre:</b> modelo!=null. <br>
     * <b>post:</b> Se retorn� elemento del �rbol que corresponde al modelo o null si no encuentra el elemento.
     * @param modelo Descripci�n del elemento que se va a buscar en el �rbol. Debe contener por lo menos la informaci�n m�nima necesaria para que el m�todo de comparaci�n del
     *        nodo pueda establecer una relaci�n de orden
     * @return E elemento del �rbol que corresponde al modelo o null si no encuentra el elemento
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * Devuelve los elementos del �rbol en inorden. <br>
     * <b>post:</b> Se retorn� el iterador con los elementos del �rbol en inorden.
     * @return Iterador con los elementos del �rbol en inorden
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
     * Devuelve la altura del �rbol. <br>
     * <b>post:</b> Se retorn� la altura del �rbol.
     * @return Altura del �rbol
     */
    public int darAltura( )
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }

    /**
     * Devuelve el peso del �rbol. <br>
     * <b>post:</b> Se retorn� el peso del �rbol.
     * @return Peso del �rbol
     */
    public int darPeso( )
    {
        return peso;
    }

    /**
     * Devuelve el elemento mayor del �rbol. <br>
     * <b>post:</b> Se retorn� el elemento mayor del �rbol o null si el �rbol est� vac�o.
     * @return El elemento mayor del �rbol o null si el �rbol est� vac�o
     */
    public T darMayor( )
    {
        return ( raiz != null ) ? raiz.darMayor( ) : null;
    }

    /**
     * Devuelve el elemento menor del �rbol. <br>
     * <b>post:</b> Se retorn� el elemento menor del �rbol o null si el �rbol est� vac�o.
     * @return El elemento menor del �rbol o null si el �rbol est� vac�o
     */
    public T darMenor( )
    {
        return ( raiz != null ) ? raiz.darMenor( ) : null;
    }
}