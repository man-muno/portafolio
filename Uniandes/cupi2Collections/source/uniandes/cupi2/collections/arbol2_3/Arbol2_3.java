/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Arbol2_3.java,v 1.17 2006/06/05 16:42:28 da-romer Exp $
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
package uniandes.cupi2.collections.arbol2_3;

import uniandes.cupi2.collections.iterador.*;

/**
 * Implementaci�n de un �rbol 2-3
 * 
 * @param <T> Tipo de datos que contiene cada nodo del �rbol
 */
public class Arbol2_3<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Ra�z del �rbol 2-3
     */
    private Nodo2_3<T> raiz;

    /**
     * Peso del �rbol 2-3
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo �rbol vac�o. <br>
     * <b>post: </b> Se construy� un �rbol vac�o.
     */
    public Arbol2_3( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ra�z del �rbol para ser navegada. <br>
     * <b>post: </b> Se retorn� la ra�z del �rbol.
     * @return Ra�z del �rbol
     */
    public Nodo2_3<T> darRaiz( )
    {
        return raiz;
    }

    /**
     * Busca un elemento en el �rbol dado su modelo. <br>
     * <b>pre: </b> modelo!=null <br>
     * <b>post: </b> Se retorn� el elemento del �rbol que corresponde al modelo. Si ning�n elemento corresponde al modelo se retorna null.
     * 
     * @param modelo Descripci�n del elemento que se va a buscar en el �rbol. Debe contener por lo menos la informaci�n m�nima necesaria para que el m�todo de comparaci�n del
     *        nodo pueda establecer una relaci�n de orden.
     * @return T elemento del �rbol que corresponde al modelo o null si no lo encuentra.
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * Calcula la altura del �rbol. <br>
     * <b>post: </b> Se retorn� la ultura del �rbol.
     * @return Altura del �rbol
     */
    public int darAltura( )
    {
        return raiz == null ? 0 : raiz.darAltura( );
    }

    /**
     * Retorna el peso total del �rbol. <br>
     * <b>post: </b> Se retorn� el peso total del �rbol.
     * @return Peso total del �rbol
     */
    public int darPeso( )
    {
        return peso;
    }

    /**
     * Inserta el elemento al �rbol. <br>
     * <b>pre:</b> elemento!=null. <br>
     * <b>post: </b> Se insert� un elemento en el �rbol si este no exist�a previamente en la estructura. <br>
     * 
     * @param elemento Elemento a insertar
     * @throws ExisteException Excepci�n generada si el elemento insertado ya existe en el �rbol
     */
    public void insertar( T elemento ) throws ExisteException
    {
        if( raiz == null )
        {
            // Caso 1: el �rbol es vac�o
            raiz = new Nodo2_3<T>( elemento );
        }
        else
        {
            // Caso 2: el �rbol no es vac�o
            raiz = raiz.insertar( elemento );
        }
        peso++;
    }

    /**
     * Elimina el elemento especificado del �rbol. <br>
     * <b>pre: </b> elemento!=null. <br>
     * <b>post: </b> Se elimin� el elemento del �rbol si este exist�a en la estructura. <br>
     * @param elemento Elemento a eliminar
     * @throws NoExisteException Excepci�n lanzada si el elemento especificado no existe en el �rbol
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
     * Devuelve los elementos del �rbol en inorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del �rbol en inorden.
     * @return Iterador para recorrer los elementos del �rbol en inorden
     */
    public Iterador<T> inorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( peso );
        if( raiz != null )
        {
            try
            {
                raiz.inorden( resultado );
            }
            catch( IteradorException e )
            {
                // Nunca deber�a lanzar esta excepci�n porque el tama�o del
                // iterador es el peso del �rbol
            }
        }
        return resultado;
    }
}
