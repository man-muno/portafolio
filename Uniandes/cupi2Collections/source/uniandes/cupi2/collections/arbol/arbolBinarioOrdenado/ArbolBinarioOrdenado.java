/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolBinarioOrdenado.java,v 1.1 2008/03/30 01:53:03 jua-gome Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Implementaci�n de un �rbol binario ordenado
 * @param <T> Tipo de datos que contiene cada nodo del �rbol. Debe implementar la interface Comparable
 */
public class ArbolBinarioOrdenado<T extends Comparable<? super T>> implements Serializable, IArbolOrdenado<T>
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

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbolOrdenado#insertar(java.lang.Comparable)
     */
    public void insertar( T elemento ) throws ElementoExisteException
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

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbolOrdenado#eliminar(java.lang.Comparable)
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

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#buscar(java.lang.Object)
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