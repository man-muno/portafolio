/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolNArio.java,v 1.4 2006/05/24 23:37:06 cupi2ejemplos Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 15, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbolNArio;

import uniandes.cupi2.collections.iterador.*;

public class ArbolNArio<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Raíz del árbol
     */
    private NodoArbolNario<T> raiz;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * 
     */
    public ArbolNArio( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz del árbol para navegarlo
     * @return Raíz del árbol para navegarlo
     */
    public NodoArbolNario<T> darRaiz( )
    {
        return raiz;
    }

    /**
     * 
     */
    public void definirRaiz( NodoArbolNario<T> nodo )
    {
        raiz = nodo;
    }

    /**
     * 
     */
    public int darAltura( )
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }

    /**
     * 
     */
    public int darPeso( )
    {
        return ( raiz != null ) ? raiz.darPeso( ) : 0;
    }

    /**
     * 
     */
    public int contarHojas( )
    {
        return ( raiz != null ) ? raiz.contarHojas( ) : 0;
    }

    /**
     * 
     */
    public int darTamanoNivel( int nivel )
    {
        return ( raiz != null ) ? raiz.darTamanoNivel( nivel ) : 0;
    }

    /**
     * 
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * 
     */
    public Iterador<T> darInordenIterativo( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.inordenIterativo( resultado );
        }
        return resultado;
    }

    /**
     * 
     */
    public Iterador<T> darInorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.inorden( resultado );
        }
        return resultado;
    }

    /**
     * 
     */
    public Iterador<T> darPreorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.preorden( resultado );
        }
        return resultado;
    }

    /**
     * 
     */
    public Iterador<T> darPostorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.postorden( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol utilizando un recorrido por niveles<br>
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

    /**
     * 
     */
    public Iterador<T> darCamino( T elem ) throws NoExisteException
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darAltura( ) );
        if( raiz != null )
        {
            raiz.darCamino( elem, resultado );
        }
        return resultado;
    }

    /**
     * 
     */
    public Iterador<T> darNivel( int nivel )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.darNivel( nivel, resultado );
        }
        return resultado;
    }

    /**
     * 
     */
    public static void main( String[] args ) throws Exception
    {
        ArbolNArio<Integer> arbol = new ArbolNArio<Integer>( );
        NodoArbolNario<Integer> nodo1 = new NodoArbolNario<Integer>( new Integer( 1 ) );
        NodoArbolNario<Integer> nodo2 = new NodoArbolNario<Integer>( new Integer( 2 ) );
        NodoArbolNario<Integer> nodo3 = new NodoArbolNario<Integer>( new Integer( 3 ) );
        NodoArbolNario<Integer> nodo4 = new NodoArbolNario<Integer>( new Integer( 4 ) );
        NodoArbolNario<Integer> nodo5 = new NodoArbolNario<Integer>( new Integer( 5 ) );
        NodoArbolNario<Integer> nodo6 = new NodoArbolNario<Integer>( new Integer( 6 ) );
        NodoArbolNario<Integer> nodo7 = new NodoArbolNario<Integer>( new Integer( 7 ) );
        NodoArbolNario<Integer> nodo8 = new NodoArbolNario<Integer>( new Integer( 8 ) );
        NodoArbolNario<Integer> nodo9 = new NodoArbolNario<Integer>( new Integer( 9 ) );
        arbol.definirRaiz( nodo1 );
        nodo1.agregarHijo( nodo2 );
        nodo1.agregarHijo( nodo3 );
        nodo1.agregarHijo( nodo4 );
        nodo4.agregarHijo( nodo5 );
        nodo4.agregarHijo( nodo6 );
        nodo6.agregarHijo( nodo7 );
        nodo6.agregarHijo( nodo8 );
        nodo6.agregarHijo( nodo9 );

        // Prueba1: altura, peso y número de hojas
        System.out.println( arbol.darAltura( ) );
        System.out.println( arbol.darPeso( ) );
        System.out.println( arbol.contarHojas( ) );

        // Prueba2: buscar
        for( int i = 1; i <= 10; i++ )
        {
            System.out.println( i + ":" + ( arbol.buscar( new Integer( i ) ) != null ? "OK" : "ERROR" ) );
        }
    }
}
