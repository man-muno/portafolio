/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoArbolNario.java,v 1.4 2006/06/01 17:01:42 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 21, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbolNArio;

import uniandes.cupi2.collections.iterador.*;
import uniandes.cupi2.collections.lista.*;

public class NodoArbolNario<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Elemento del nodo
     */
    private T elem;

    /**
     * Subárboles
     */
    private Lista<NodoArbolNario<T>> hijos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * 
     */
    public NodoArbolNario( )
    {
        elem = null;
        hijos = null;
    }

    /**
     * 
     */
    public NodoArbolNario( T pElem )
    {
        elem = pElem;
        hijos = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * 
     */
    public T darElemento( )
    {
        return elem;
    }

    /**
     * 
     */
    public Lista<NodoArbolNario<T>> darHijos( )
    {
        return hijos;
    }

    /**
     * 
     */
    public void agregarHijo( NodoArbolNario<T> nodo )
    {
        if( hijos == null )
            hijos = new Lista<NodoArbolNario<T>>( );
        hijos.agregar( nodo );
    }

    /**
     * 
     */
    public void asignarElemento( T pElem )
    {
        elem = pElem;
    }

    /**
     * 
     */
    public boolean esHoja( )
    {
        return hijos == null;
    }

    /**
     * Devuelve la altura del árbol cuya raíz es el nodo actual
     * @return Altura del árbol cuya raíz es el nodo actual
     */
    public int darAltura( )
    {
        if( esHoja( ) )
            return 1;
        else
        {
            int maxAltura = 0;
            for( int i = 0; i < hijos.darLongitud( ); i++ )
            {
                NodoArbolNario<T> hijo = hijos.darElemento( i );
                int auxAltura = hijo.darAltura( );
                if( auxAltura > maxAltura )
                    maxAltura = auxAltura;
            }
            return maxAltura + 1;
        }
    }

    /**
     * Devuelve el peso del árbol cuya raíz es el nodo actual
     * @return Peso del árbol cuya raíz es el nodo actual
     */
    public int darPeso( )
    {
        if( esHoja( ) )
            return 1;
        else
        {
            int peso = 0;
            for( int i = 0; i < hijos.darLongitud( ); i++ )
            {
                peso += hijos.darElemento( i ).darPeso( );
            }
            return peso + 1;
        }
    }

    /**
     * Devuelve el numero de hojas del árbol cuya raíz es el nodo actual
     * @return Numero de hojas del árbol cuya raíz es el nodo actual
     */
    public int contarHojas( )
    {
        if( esHoja( ) )
            return 1;
        else
        {
            int numHojas = 0;
            for( int i = 0; i < hijos.darLongitud( ); i++ )
            {
                numHojas += hijos.darElemento( i ).contarHojas( );
            }
            return numHojas;
        }
    }

    /**
     * 
     */
    public T buscar( T modelo )
    {
        if( modelo.equals( elem ) )
            return elem;
        else if( esHoja( ) )
            return null;
        else
        {
            for( int i = 0; i < hijos.darLongitud( ); i++ )
            {
                T aux = hijos.darElemento( i ).buscar( modelo );
                if( aux != null )
                    return aux;
            }
            return null;
        }
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en inorden
     * @param resultado Resultado del recorrido
     */
    public void inordenIterativo( IteradorSimple<T> resultado )
    {
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en inorden
     * @param resultado Resultado del recorrido
     */
    public void inorden( IteradorSimple<T> resultado )
    {
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en preorden
     * @param resultado Resultado del recorrido
     */
    public void preorden( IteradorSimple<T> resultado )
    {
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en postorden
     * @param resultado Resultado del recorrido
     */
    public void postorden( IteradorSimple<T> resultado )
    {
    }

    /**
     * Agrega los elementos al arreglo utilizando un recorrido por niveles, partiendo del nodo actual
     * @param resultado Resultado del recorrido
     */
    public void darRecorridoNiveles( IteradorSimple<T> resultado )
    {
    }

    /**
     * 
     * @return
     */
    public int darTamanoNivel( int nivel )
    {
        return 0;
    }

    /**
     * 
     */
    public void darNivel( int nivel, IteradorSimple<T> resultado )
    {
    }

    /**
     * 
     */
    public void darCamino( T pElem, IteradorSimple<T> resultado ) throws NoExisteException
    {
    }
}
