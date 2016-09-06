/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolAVL.java,v 1.21 2006/08/08 14:12:34 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Implementaci�n de un �rbol binario ordenado balanceado por altura: �rbol AVL
 * @param <T> Tipo de datos que contiene cada nodo del �rbol
 */
public class ArbolAVL<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ra�z del �rbol AVL
     */
    private NodoAVL<T> raiz;

    /**
     * Peso del �rbol AVL
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del �rbol AVL vac�o. <br>
     * <b>post: </b> Se construy� un �rbol AVL vac�o.
     */
    public ArbolAVL( )
    {
        raiz = null;
        peso = 0;
    }
    
    /**
     * Constructor del �rbol AVL con ra�z. <br>
     * <b>pre: </b> r!=null y p>=0. 
     * <b>post: </b> Se construy� un �rbol AVL vac�o.
     * @param r Raiz del �rbol
     * @param p Peso del �rbol
     */
    public ArbolAVL( NodoAVL<T> r, int p )
    {
        raiz = null;
        peso = p;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ra�z del �rbol para navegarlo. <br>
     * <b>post: </b> Se retorn� la ra�z del �rbol para navegarlo.
     * @return Ra�z del �rbol para navegarlo
     */
    public NodoAVL<T> darRaiz( )
    {
        return raiz;
    }

    /**
     * Agrega un nuevo elemento al �rbol AVL. <br>
     * <b>post:</b> se agreg� el elemento al �rbol de manera ordenada.
     * @param elemento Elemento que se va a agregar
     * @throws ExisteException El elemento especificado ya existe en el �rbol
     */
    public void insertar( T elemento ) throws ExisteException
    {
        if( raiz == null )
        {
            // Caso 1: el �rbol es vac�o
            raiz = new NodoAVL<T>( elemento );
        }
        else
        {
            // Caso 2: el �rbol no es vac�o
            raiz = raiz.insertar( elemento );
        }
        peso++;
    }

    /**
     * Elimina del �rbol el elemento que llega como par�metro. <br>
     * <b>post: </b> Se elimin� el elemento especificado si exist�a en la estructura.
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
     * Localiza y retorna un elemento en el �rbol AVL, recibiendo un modelo del mismo como par�metro. <br>
     * <b>post: </b> Se retorn� el elemento del �rbol que corresponde al modelo o null si no existe ninguno.
     * @param modelo Descripci�n del elemento que se va a buscar en el �rbol. Debe contener por lo menos la informaci�n m�nima necesaria para que el m�todo de comparaci�n del
     *        nodo pueda establecer una relaci�n de orden
     * @return E elemento del �rbol que corresponde al modelo o null si no existe ninguno
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * Devuelve los elementos del �rbol en inorden. <br>
     * <b>post: </b> Se retorn� el iterador con los elementos del �rbol en inorden.
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
     * <b>post: </b> Se retorn� la altura del �rbol.
     * @return Altura del �rbol
     */
    public int darAltura( )
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }

    /**
     * Devuelve el peso del �rbol AVL. <br>
     * <b>post: </b> Se retorn� el peso del �rbol.
     * @return Peso del �rbol AVL
     */
    public int darPeso( )
    {
        return peso;
    }

    /**
     * Devuelve el elemento mayor del �rbol AVL. <br>
     * <b>post: </b> Se retorn� el elemento mayor del �rbol AVL o null si el �rbol est� vacio.
     * @return Elemento mayor del �rbol AVL o null si el �rbol est� vacio
     */
    public T darMayor( )
    {
        return ( raiz != null ) ? raiz.darMayor( ) : null;
    }

    /**
     * Devuelve el elemento menor del �rbol AVL. <br>
     * <b>post: </b> Se retorn� el elemento menor del �rbol AVL o null si el �rbol est� vacio.
     * @return Elemento menor del �rbol AVL o null si el �rbol est� vacio
     */
    public T darMenor( )
    {
        return ( raiz != null ) ? raiz.darMenor( ) : null;
    }
    
    /**
     * Devuelve los elementos del �rbol utilizando un recorrido por niveles. <br>
     * <b>post: </b> Se retorn� el iterador para recorrer los elementos del �rbol por niveles.
     * @return Iterador con los elementos del �rbol en un recorrido por niveles
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
