/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Arbol1_2_3.java,v 1.3 2007/10/16 15:41:45 p-marque Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Manuel Mu�oz - May 16, 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.arbol1_2_3;

import java.io.Serializable;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;

/**
 * Implementaci�n de un �rbol 1-2-3
 * 
 * @param <T> Tipo de datos que contiene cada nodo del �rbol. Los nodos deben implementar la interface comparable
 */
public class Arbol1_2_3<T extends Comparable> implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Ra�z del �rbol 1-2-3
     */
    private Nodo1_2_3<T> raiz;

    /**
     * Peso del �rbol 1-2-3
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo �rbol vac�o. <br>
     * <b>post: </b> Se construy� un �rbol vac�o, con ra�z null.
     */
    public Arbol1_2_3( )
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
    public Nodo1_2_3<T> darRaiz( )
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
     * <b>post: </b> Se retorn� la altura del �rbol. Entero mayor o igual a cero.
     * @return Altura del �rbol. Entero mayor o igual a cero.
     */
    public int darAltura( )
    {
        return raiz == null ? 0 : raiz.darAltura( );
    }

    /**
     * Retorna el peso total del �rbol. <br>
     * <b>post: </b> Se retorn� el peso total del �rbol. Entero mayor o igual a cero.
     * @return Peso total del �rbol. Entero mayor o igual a cero.
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
     * @throws ElementoExisteException Excepci�n lanzada si el elemento insertado ya existe en el �rbol
     */
    public void insertar( T elemento ) throws ElementoExisteException
    {
        if( raiz == null )
        {
            // Caso 1: el �rbol es vac�o
            raiz = new Nodo1_2_3<T>( elemento );
        }
        else
        {
            // Caso 2: el �rbol no es vac�o
            raiz.insertar( elemento );
        }
        peso++;
    }

    /**
     * Elimina el elemento especificado del �rbol. <br>
     * <b>pre: </b> elemento!=null. <br>
     * <b>post: </b> Se elimin� el elemento del �rbol si este exist�a en la estructura. <br>
     * @param elemento Elemento a eliminar
     * @throws ElementoNoExisteException Excepci�n lanzada si el elemento especificado no existe en el �rbol
     */
    public void eliminar( T elemento ) throws ElementoNoExisteException
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
            throw new ElementoNoExisteException( "El elemento especificado no existe en el �rbol" );
        }
    }

    /**
     * Devuelve los elementos del �rbol en inorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del �rbol en inorden.
     * @return Iterador para recorrer los elementos del �rbol en inorden. Diferente de null.
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
