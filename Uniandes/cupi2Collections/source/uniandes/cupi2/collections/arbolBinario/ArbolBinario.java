/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolBinario.java,v 1.16 2006/10/22 17:55:41 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbolBinario;

import uniandes.cupi2.collections.iterador.*;

/**
 * Implementaci�n de un �rbol binario
 * 
 * @param <T> Tipo de datos que contiene cada nodo del �rbol
 */
public class ArbolBinario<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Ra�z del �rbol
     */
    private NodoArbolBinario<T> raiz;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un �rbol vac�o. <br>
     * <b>post: </b> Se construy� un �rbol binario vac�o.
     */
    public ArbolBinario( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ra�z del �rbol para navegarlo. <br>
     * <b>post: </b> Se retorn� la ra�z del �rbol.
     * @return Ra�z del �rbol para navegarlo
     */
    public NodoArbolBinario<T> darRaiz( )
    {
        return raiz;
    }

    /**
     * Cambia la ra�z del �rbol por el nodo especificado. <br>
     * <b>post: </b> raiz= nodo. <br>
     * @param nodo Nueva ra�z del �rbol
     */
    public void definirRaiz( NodoArbolBinario<T> nodo )
    {
        raiz = nodo;
    }

    /**
     * Retorna la altura del �rbol. <br>
     * <b>post: </b> Se retorn� la altura del �rbol.
     * @return La altura del �rbol
     */
    public int darAltura( )
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }

    /**
     * Retorna el peso del �rbol (n�mero de elementos que contiene). <br>
     * <b>post: </b> Se retorn� el peso del �rbol.
     * @return El peso del �rbol
     */
    public int darPeso( )
    {
        return ( raiz != null ) ? raiz.darPeso( ) : 0;
    }

    /**
     * Retorna el n�mero de hojas que hay en el �rbol. <br>
     * <b>post: </b> Se retorn� el n�mero de hojas que hay en el �rbol.
     * @return El n�mero de hojas que hay en el �rbol
     */
    public int contarHojas( )
    {
        return ( raiz != null ) ? raiz.contarHojas( ) : 0;
    }

    /**
     * Retorna el tama�o del nivel especificado. <br>
     * <b>post: </b> Se retorn� tama�o del nivel especificado.
     * @param nivel Nivel del que se va a consultar el tama�o
     * @return El tama�o del nivel especificado
     */
    public int darTamanoNivel( int nivel )
    {
        return ( raiz != null ) ? raiz.darTamanoNivel( nivel ) : 0;
    }

    /**
     * Indica si el �rbol est� lleno. <br>
     * <b>post: </b> Se retorn� true si el �rbol se encuentra lleno o false en caso contrario.
     * @return True si el �rbol est� lleno o false de lo contrario
     */
    public boolean estaLleno( )
    {
        return ( raiz != null ) ? raiz.estaLleno( raiz.darAltura( ) ) : true;
    }

    /**
     * Indica si el �rbol est� casi lleno. <br>
     * <b>post: </b> Se retorn� true si el �rbol se encuentra casi lleno o false en caso contrario.
     * @return True si el �rbol est� casi lleno o false de lo contrario
     */
    public boolean estaCasiLleno( )
    {
        return ( raiz != null ) ? raiz.estaCasiLleno( raiz.darAltura( ) ) : true;
    }

    /**
     * Indica si el �rbol est� balanceado por altura. <br>
     * <b>post: </b> Se retorn� true si el �rbol est� baleanceado por altura o false en caso contrario.
     * @return True si el �rbol est� balanceado por altura o false de lo contrario
     */
    public boolean estaBalanceadoPorAltura( )
    {
        return ( raiz != null ) ? raiz.estaBalanceadoPorAltura( ) : false;
    }

    /**
     * Indica si el �rbol est� balanceado por peso. <br>
     * <b>post: </b> Se retorn� true si el �rbol est� baleanceado por peso o false en caso contrario.
     * @return True si el �rbol est� balanceado por peso o false de lo contrario
     */
    public boolean estaBalanceadoPorPeso( )
    {
        return ( raiz != null ) ? raiz.estaBalanceadoPorPeso( ) : false;
    }

    /**
     * Indica si el �rbol es completo. <br>
     * <b>post: </b> Se retorn� true si el �rbol es completo o false en caso contrario.
     * @return True si el �rbol es completo o false de lo contrario
     */
    public boolean esCompleto( )
    {
        return ( raiz != null ) ? raiz.esCompleto( ) : false;
    }

    /**
     * Busca el elemento del �rbol que corresponda al modelo especificado. <br>
     * <b>pre: </b> modelo!=null. <br>
     * <b>post: </b> Se retorn� el elemento correspondiente al modelo o false de lo contrario.
     * @param modelo Descripci�n del elemento que se va a buscar en el �rbol. Debe contener por lo menos la informaci�n m�nima necesaria para que el m�todo de comparaci�n del
     *        nodo pueda establecer una relaci�n de orden
     * @return El elemento que corresponde al modelo. si ning�n elemento corresponde al modelo se retorna null
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * Devuelve los elementos del �rbol en inorden. <br>
     * <b>post: </b> Se retorn� el iterador para recorrer los elementos del �rbol en inorden.
     * @return Iterador para recorrer los elementos del �rbol en inorden
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
     * Devuelve los elementos del �rbol en inorden. <br>
     * <b>post: </b> Se retorn� el iterador para recorrer los elementos del �rbol en inorden.
     * @return Iterador para recorrer los elementos del �rbol en inorden
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
     * Devuelve los elementos del �rbol en preorden. <br>
     * <b>post: </b> Se retorn� el iterador para recorrer los elementos del �rbol en preorden.
     * @return Iterador para recorrer los elementos del �rbol en preorden
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
     * Devuelve los elementos del �rbol en postorden. <br>
     * <b>post: </b> Se retorn� el iterador para recorrer los elementos del �rbol en postorden.
     * @return Iterador para recorrer los elementos del �rbol en postorden
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

    /**
     * Devuelve un iterador con los elementos que constituyen el camino al elemento especificado. <br>
     * <b>pre: </b> elem!=null. <br>
     * <b>post: </b> Se retorn� el iterador con los elementos del camino al elemento especificado.
     * @param elem Elemento del que se desea el camino
     * @return Iterador con los elementos del camino
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
     * Devuelve un iterador con los elementos del nivel especificado. <br>
     * <b>post: </b> Se retorn� el iterador con los elementos del nivel especificado.
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
     * Devuelve un iterador con los elementos del nivel especificado. <br>
     * <b>pre: </b> elem!=null. <br>
     * <b>post: </b> Se retorn� el siguiente elemento en inorden del elemento especificado.
     * @param elem Elemento del que se desea el siguiente elemento en el recorrido por inorden
     * @return Siguiente elemento del elemento especificado
     * @throws NoExisteException Si el elemento especificado no existe
     */
    public T darSiguienteInorden( T elem ) throws NoExisteException
    {
        if( raiz != null )
        {
            return raiz.darSiguienteInorden( elem );
        }
        throw new NoExisteException( "Elemento no encontrado" );
    }
    
	/**
	 * Devuelve la rama m�s larga del �rbol 
	 * @return La rama m�s larga. Si hay varias ramas cuya longitud sea igual <br> 
	 * a la de la m�s larga se retorna la que est� m�s a la izquierda en el �rbol
	 */
    public Iterador<T> darRamaMasLarga( )
    {
        int altura = darAltura();
        IteradorSimple<T> resultado = new IteradorSimple<T>( altura );
        if( raiz != null )
        {
            raiz.darRamaMasLarga( altura, resultado );
        }
        return resultado;
    }
}
