/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolBinario.java,v 1.1 2008/03/30 01:53:04 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbol.arbolBinario;

import java.io.Serializable;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.iterador.IteradorSimple;
import uniandes.cupi2.collections.arbol.ElementoNoExisteException;
import uniandes.cupi2.collections.arbol.IArbol;

/**
 * Implementación de un árbol binario
 * 
 * @param <T> Tipo de datos que contiene cada nodo del árbol
 */
public class ArbolBinario<T> implements Serializable, IArbol<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Raíz del árbol
     */
    private NodoArbolBinario<T> raiz;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un árbol vacío. <br>
     * <b>post: </b> Se construyó un árbol binario vacío.
     */
    public ArbolBinario( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz del árbol para navegarlo. <br>
     * <b>post: </b> Se retornó la raíz del árbol.
     * @return Raíz del árbol para navegarlo
     */
    public NodoArbolBinario<T> darRaiz( )
    {
        return raiz;
    }

    /**
     * Cambia la raíz del árbol por el nodo especificado. <br>
     * <b>post: </b> raiz= nodo. <br>
     * @param nodo Nueva raíz del árbol
     */
    public void definirRaiz( NodoArbolBinario<T> nodo )
    {
        raiz = nodo;
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
        return ( raiz != null ) ? raiz.darPeso( ) : 0;
    }

    /**
     * Retorna el número de hojas que hay en el árbol. <br>
     * <b>post: </b> Se retornó el número de hojas que hay en el árbol.
     * @return El número de hojas que hay en el árbol. Entero mayor o igual a 0.
     */
    public int contarHojas( )
    {
        return ( raiz != null ) ? raiz.contarHojas( ) : 0;
    }

    /**
     * Retorna la cantidad de elementos que se encuentran en el nivel especificado.<br>
     * <b>post: </b> Se retornó tamaño del nivel especificado.
     * @param nivel Nivel del que se va a consultar el tamaño. Entero mayor o igual a cero.
     * @return El tamaño del nivel especificado. Entero mayor o igual a cero.
     */
    public int darTamanoNivel( int nivel )
    {
        return ( raiz != null ) ? raiz.darTamanoNivel( nivel ) : 0;
    }

    /**
     * Indica si el árbol está lleno. <br>
     * <b>post: </b> Se retornó true si el árbol se encuentra lleno o false en caso contrario.
     * @return True si el árbol está lleno o false de lo contrario
     */
    public boolean estaLleno( )
    {
        return ( raiz != null ) ? raiz.estaLleno( raiz.darAltura( ) ) : true;
    }

    /**
     * Indica si el árbol está casi lleno. <br>
     * <b>post: </b> Se retornó true si el árbol se encuentra casi lleno o false en caso contrario.
     * @return True si el árbol está casi lleno o false de lo contrario
     */
    public boolean estaCasiLleno( )
    {
        return ( raiz != null ) ? raiz.estaCasiLleno( raiz.darAltura( ) ) : true;
    }

    /**
     * Indica si el árbol está balanceado por altura. <br>
     * <b>post: </b> Se retornó true si el árbol está baleanceado por altura o false en caso contrario.
     * @return True si el árbol está balanceado por altura o false de lo contrario
     */
    public boolean estaBalanceadoPorAltura( )
    {
        return ( raiz != null ) ? raiz.estaBalanceadoPorAltura( ) : false;
    }

    /**
     * Indica si el árbol está balanceado por peso. <br>
     * <b>post: </b> Se retornó true si el árbol está baleanceado por peso o false en caso contrario.
     * @return True si el árbol está balanceado por peso o false de lo contrario
     */
    public boolean estaBalanceadoPorPeso( )
    {
        return ( raiz != null ) ? raiz.estaBalanceadoPorPeso( ) : false;
    }

    /**
     * Indica si el árbol es completo. <br>
     * <b>post: </b> Se retornó true si el árbol es completo o false en caso contrario.
     * @return True si el árbol es completo o false de lo contrario
     */
    public boolean esCompleto( )
    {
        return ( raiz != null ) ? raiz.esCompleto( ) : false;
    }

    /* (non-Javadoc)
     * @see uniandes.cupi2.collections.arbol.IArbol#buscar(java.lang.Object)
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * Devuelve los elementos del árbol en inorden utilizando instrucciones iterativas. <br>
     * <b>post: </b> Se retornó el iterador para recorrer los elementos del árbol en inorden.
     * @return Iterador para recorrer los elementos del árbol en inorden
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
     * Devuelve los elementos del árbol en preorden utilizando instrucciones iterativas. <br>
     * <b>post: </b> Se retornó el iterador para recorrer los elementos del árbol en preorden.
     * @return Iterador para recorrer los elementos del árbol en preorden
     */
    public Iterador<T> darPreordenIterativo( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.preordenIterativo( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol en postorden utilizando instrucciones iterativas. <br>
     * <b>post: </b> Se retornó el iterador para recorrer los elementos del árbol en postorden.
     * @return Iterador para recorrer los elementos del árbol en postorden
     */
    public Iterador<T> darPostordenIterativo( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darPeso( ) );
        if( raiz != null )
        {
            raiz.postordenIterativo( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol en inorden utilizando instrucciones recursivas. <br>
     * <b>post: </b> Se retornó el iterador para recorrer los elementos del árbol en inorden.
     * @return Iterador para recorrer los elementos del árbol en inorden
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
     * Devuelve los elementos del árbol en preorden utilizando instrucciones recursivas. <br>
     * <b>post: </b> Se retornó el iterador para recorrer los elementos del árbol en preorden.
     * @return Iterador para recorrer los elementos del árbol en preorden
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
     * Devuelve los elementos del árbol en postorden utilizando instrucciones recursivas. <br>
     * <b>post: </b> Se retornó el iterador para recorrer los elementos del árbol en postorden.
     * @return Iterador para recorrer los elementos del árbol en postorden
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
     * Devuelve los elementos del árbol utilizando un recorrido por niveles utilizando instrucciones recursivas. <br>
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

    /**
     * Devuelve un iterador con los elementos que constituyen el camino al elemento especificado. <br>
     * <b>pre: </b> elem!=null. <br>
     * <b>post: </b> Se retornó el iterador con los elementos del camino al elemento especificado.
     * @param elem Elemento del que se desea el camino
     * @return Iterador con los elementos del camino
     */
    public Iterador<T> darCamino( T elem ) throws ElementoNoExisteException
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( darAltura( ) );
        if( raiz != null )
        {
            raiz.darCamino( elem, resultado );
        }
        return resultado;
    }

    /**
     * Devuelve un iterador con los elementos del nivel especificado. <br>
     * <b>post: </b> Se retornó el iterador con los elementos del nivel especificado.
     * @param nivel Nivel del que se desean los elementos
     * @return Iterador con los elementos del nivel
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
     * Retorna el siguiente elemento en inorden al elemento especificado.<br>
     * <b>pre: </b> elem!=null. <br>
     * <b>post: </b> Se retornó el siguiente elemento en inorden al elemento especificado.
     * @param elem Elemento del que se desea el siguiente elemento en el recorrido por inorden
     * @return Siguiente elemento del elemento especificado
     * @throws ElementoNoExisteException Si el elemento especificado no existe
     */
    public T darSiguienteInorden( T elem ) throws ElementoNoExisteException
    {
        if( raiz != null )
        {
            return raiz.darSiguienteInorden( elem );
        }
        throw new ElementoNoExisteException( "Elemento no encontrado" );
    }

    /**
     * Devuelve la rama más larga del árbol
     * @return La rama más larga. Si hay varias ramas cuya longitud sea igual <br>
     *         a la de la más larga se retorna la que está más a la izquierda en el árbol
     */
    public Iterador<T> darRamaMasLarga( )
    {
        int altura = darAltura( );
        IteradorSimple<T> resultado = new IteradorSimple<T>( altura );
        if( raiz != null )
        {
            raiz.darRamaMasLarga( altura, resultado );
        }
        return resultado;
    }
}
