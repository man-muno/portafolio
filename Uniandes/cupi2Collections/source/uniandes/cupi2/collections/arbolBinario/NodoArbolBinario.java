/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoArbolBinario.java,v 1.16 2006/10/22 17:55:41 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 3, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbolBinario;

import uniandes.cupi2.collections.colaEncadenada.*;
import uniandes.cupi2.collections.iterador.*;
import uniandes.cupi2.collections.pilaEncadenada.*;

/**
 * Nodo del árbol binario
 * 
 * @param <T> Tipo de datos que contiene cada nodo del árbol
 */
public class NodoArbolBinario<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Elemento del nodo
     */
    private T elem;

    /**
     * Subárbol izquierdo
     */
    private NodoArbolBinario<T> izqNodo;

    /**
     * Subárbol derecho
     */
    private NodoArbolBinario<T> derNodo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nodo con el elemento especificado. <br>
     * <b>post: </b> Se creó un nodo con elem= pElem.
     * @param pElem Elemento del nodo
     */
    public NodoArbolBinario( T pElem )
    {
        elem = pElem;
        izqNodo = null;
        derNodo = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el elemento del nodo. <br>
     * <b>post: </b> Se retornó el elemento del árbol.
     * @return El elemento que contiene el nodo
     */
    public T darElemento( )
    {
        return elem;
    }

    /**
     * Retorna el hijo izquierdo del nodo. <br>
     * <b>post: </b> Se retornó el hijo izquierdo del nodo.
     * @return Hijo izquierdo del nodo
     */
    public NodoArbolBinario<T> darIzquierdo( )
    {
        return izqNodo;
    }

    /**
     * Retorna el hijo derecho del nodo. <br>
     * <b>post: </b> Se retornó el hijo derecho del nodo.
     * @return Hijo derecho del nodo
     */
    public NodoArbolBinario<T> darDerecho( )
    {
        return derNodo;
    }

    /**
     * Encadena el nodo especificado como hijo izquierdo del nodo actual. <br>
     * <b>post: </b> izqNodo= nodo.
     * @param nodo Hijo izquierdo del nodo actual
     */
    public void encadenarIzquierdo( NodoArbolBinario<T> nodo )
    {
        izqNodo = nodo;
    }

    /**
     * Encadena el nodo especificado como hijo derecho del nodo actual. <br>
     * <b>post: </b> derNodo= nodo.
     * @param nodo Hijo derecho del nodo actual
     */
    public void encadenarDerecho( NodoArbolBinario<T> nodo )
    {
        derNodo = nodo;
    }

    /**
     * Cambia el elemento del nodo. <br>
     * <b>post: </b> elem= pElem.
     * @param pElem Nuevo elemento del nodo
     */
    public void asignarElemento( T pElem )
    {
        elem = pElem;
    }

    /**
     * Indica si el nodo es una hoja. <br>
     * <b>post: </b> True si el nodo es una hoja o false de lo contrario.
     * @return True si el elemento es hoja o false de contrario
     */
    public boolean esHoja( )
    {
        return izqNodo == null && derNodo == null;
    }

    /**
     * Devuelve la altura del árbol cuya raíz es el nodo actual. <br>
     * <b>post: </b> Se retornó la altura del árbol.
     * @return Altura del árbol cuya raíz es el nodo actual
     */
    public int darAltura( )
    {
        int a1 = ( izqNodo == null ) ? 0 : izqNodo.darAltura( );
        int a2 = ( derNodo == null ) ? 0 : derNodo.darAltura( );
        return ( a1 >= a2 ) ? a1 + 1 : a2 + 1;
    }

    /**
     * Devuelve el peso del árbol cuya raíz es el nodo actual. <br>
     * <b>post: </b> Se retornó el peso del árbol.
     * @return Peso del árbol cuya raíz es el nodo actual
     */
    public int darPeso( )
    {
        int p1 = ( izqNodo == null ) ? 0 : izqNodo.darPeso( );
        int p2 = ( derNodo == null ) ? 0 : derNodo.darPeso( );
        return p1 + p2 + 1;
    }

    /**
     * Devuelve el numero de hojas del árbol cuya raíz es el nodo actual. <br>
     * <b>post: </b> Se retornó el número de hojas del árbol.
     * @return Numero de hojas del árbol cuya raíz es el nodo actual
     */
    public int contarHojas( )
    {
        if( esHoja( ) )
            return 1;
        else
        {
            int h1 = ( izqNodo == null ) ? 0 : izqNodo.contarHojas( );
            int h2 = ( derNodo == null ) ? 0 : derNodo.contarHojas( );
            return h1 + h2;
        }
    }

    /**
     * Busca el elemento del árbol que corresponda al modelo especificado. <br>
     * <b>pre: </b> modelo!=null. <br>
     * <b>post: </b> Se retornó el elemento correspondiente al modelo o false de lo contrario.
     * @param modelo Descripción del elemento que se va a buscar en el árbol. Debe contener por lo menos la información mínima necesaria para que el método de comparación del
     *        nodo pueda establecer una relación de orden
     * @return El elemento que corresponde al modelo. si ningún elemento corresponde al modelo se retorna null
     */
    public T buscar( T modelo )
    {
        if( modelo.equals( elem ) )
            return elem;
        else
        {
            T temp = ( izqNodo == null ) ? null : izqNodo.buscar( modelo );
            if( temp != null )
                return temp;
            else
                return ( derNodo == null ) ? null : derNodo.buscar( modelo );
        }
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en inorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del árbol en inorden.
     * @param resultado Resultado del recorrido
     */
    public void inordenIterativo( IteradorSimple<T> resultado )
    {
        PilaEncadenada<NodoArbolBinario<T>> pila = new PilaEncadenada<NodoArbolBinario<T>>( );
        NodoArbolBinario<T> nodo = this;
        while( nodo != null || pila.darLongitud( ) != 0 )
        {
            if( nodo == null )
            {
                try
                {
                    // Toma el primer árbol de la pila
                    nodo = pila.tomarElemento( );
                }
                catch( PilaVaciaException e )
                {
                    // Nunca debería aparecer esta excepción
                }
                // Recorre el nodo
                try
                {
                    resultado.agregar( nodo.elem );
                }
                catch( IteradorException e )
                {
                    // Nunca debería aparecer esta excepción
                }
                nodo = nodo.derNodo;
            }
            else if( nodo.izqNodo == null )
            {
                // Recorre el nodo
                try
                {
                    resultado.agregar( nodo.elem );
                }
                catch( IteradorException e )
                {
                    // Nunca debería aparecer esta excepción
                }
                nodo = nodo.derNodo;
            }
            else
            {
                pila.insertar( nodo );
                nodo = nodo.izqNodo;
            }
        }
    }
    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en inorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del árbol en inorden.
     * @param resultado Resultado del recorrido
     */
    public void inorden( IteradorSimple<T> resultado )
    {
        // Agrega los elementos del subárbol izquierdo
        if( izqNodo != null )
        {
            izqNodo.inorden( resultado );
        }

        try
        {
            // Agrega el elemento que se encuentra en el nodo
            resultado.agregar( elem );
        }
        catch( IteradorException e )
        {
            // Nunca debería aparecer esta excepción
        }

        // Agrega los elementos del subárbol derecho
        if( derNodo != null )
        {
            derNodo.inorden( resultado );
        }
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en preorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del árbol en preorden.
     * @param resultado Resultado del recorrido
     */
    public void preorden( IteradorSimple<T> resultado )
    {
        try
        {
            // Agrega el elemento que se encuentra en el nodo
            resultado.agregar( elem );
        }
        catch( IteradorException e )
        {
            // Nunca debería aparecer esta excepción
        }

        // Agrega los elementos del subárbol izquierdo
        if( izqNodo != null )
        {
            izqNodo.preorden( resultado );
        }
        // Agrega los elementos del subárbol derecho
        if( derNodo != null )
        {
            derNodo.preorden( resultado );
        }
    }

    /**
     * Agrega los elementos al iterador que llega como parámetro, utilizando para esto un recorrido en postorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del árbol en postorden.
     * @param resultado Resultado del recorrido
     */
    public void postorden( IteradorSimple<T> resultado )
    {
        // Agrega los elementos del subárbol izquierdo
        if( izqNodo != null )
        {
            izqNodo.postorden( resultado );
        }
        // Agrega los elementos del subárbol derecho
        if( derNodo != null )
        {
            derNodo.postorden( resultado );
        }

        try
        {
            // Agrega el elemento que se encuentra en el nodo
            resultado.agregar( elem );
        }
        catch( IteradorException e )
        {
            // Nunca debería aparecer esta excepción
        }
    }

    /**
     * Agrega los elementos al arreglo utilizando un recorrido por niveles, partiendo del nodo actual. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del árbol por niveles.
     * @param resultado Resultado del recorrido
     */
    public void darRecorridoNiveles( IteradorSimple<T> resultado )
    {
        ColaEncadenada<NodoArbolBinario<T>> cola = new ColaEncadenada<NodoArbolBinario<T>>( );
        cola.insertar( this );
        while( cola.darLongitud( ) != 0 )
        {
            NodoArbolBinario<T> nodo = null;
            try
            {
                // Toma el primer árbol de la cola
                nodo = cola.tomarElemento( );
            }
            catch( ColaVaciaException e )
            {
                // Nunca debería aparecer esta excepción
            }
            try
            {
                resultado.agregar( nodo.elem );
            }
            catch( IteradorException e )
            {
                // Nunca debería aparecer esta excepción
            }

            // Agrega los dos subárboles (si no son vacíos) a la cola
            if( nodo.izqNodo != null )
            {
                cola.insertar( nodo.izqNodo );
            }
            if( nodo.derNodo != null )
            {
                cola.insertar( nodo.derNodo );
            }
        }
    }

    /**
     * Retorna el tamaño del nivel especificado. <br>
     * <b>post: </b> Se retorno el tamaño del nivel especificado.
     * @param nivel Nivel del que se desea consultar el tamaño
     * @return El tamaño del nivel especificado
     */
    public int darTamanoNivel( int nivel )
    {
        if( nivel == 0 )
            return 1;
        else
        {
            int acum = 0;
            if( izqNodo != null )
                acum += izqNodo.darTamanoNivel( nivel - 1 );
            if( derNodo != null )
                acum += derNodo.darTamanoNivel( nivel - 1 );
            return acum;
        }
    }

    /**
     * Retorna los elementos del nivel especificado en un iterador. <br>
     * <b>post: </b> Se retornó los elementos del nivel especificado en un iterador.
     * @param nivel Nivel del que se desean los elementos
     * @param resultado Iterador para colocar los elementos del nivel
     * @return El iterador con los elementos del nivel especificado dentro de resultado
     */
    public void darNivel( int nivel, IteradorSimple<T> resultado )
    {
        if( nivel == 0 )
        {
            try
            {
                resultado.agregar( elem );
            }
            catch( IteradorException e )
            {
                // Nunca debería aparecer esta excepción
            }
        }
        else
        {
            if( izqNodo != null )
                izqNodo.darNivel( nivel - 1, resultado );
            if( derNodo != null )
                derNodo.darNivel( nivel - 1, resultado );
        }
    }

    /**
     * Indica si el árbol está balanceado por altura. <br>
     * <b>post: </b> Se retorno true si el árbol está balanceado por altura o false de lo contrario.
     * @return True si el árbol está balanceado por altura o false de lo contrario
     */
    public boolean estaBalanceadoPorAltura( )
    {
        if( esHoja( ) )
            return true;
        else if( izqNodo == null )
            return derNodo.esHoja( );
        else if( derNodo == null )
            return izqNodo.esHoja( );
        else
            return Math.abs( izqNodo.darAltura( ) - derNodo.darAltura( ) ) <= 1 && izqNodo.estaBalanceadoPorAltura( ) && derNodo.estaBalanceadoPorAltura( );
    }

    /**
     * Indica si el árbol está balanceado por peso. <br>
     * <b>post: </b> Se retorno true si el árbol está balanceado por peso o false de lo contrario.
     * @return True si el árbol está balanceado por peso o false de lo contrario
     */
    public boolean estaBalanceadoPorPeso( )
    {
        if( esHoja( ) )
            return true;
        else if( izqNodo == null )
            return derNodo.esHoja( );
        else if( derNodo == null )
            return izqNodo.esHoja( );
        else
            return Math.abs( izqNodo.darPeso( ) - derNodo.darPeso( ) ) <= 1 && izqNodo.estaBalanceadoPorPeso( ) && derNodo.estaBalanceadoPorPeso( );
    }

    /**
     * Indica si el árbol es completo. <br>
     * <b>post: </b> Se retorno true si el árbol está completo o false de lo contrario.
     * @return True si el árbol es completo o false de lo contrario
     */
    public boolean esCompleto( )
    {
        if( esHoja( ) )
            return true;
        else if( izqNodo != null && derNodo != null )
            return izqNodo.esCompleto( ) && derNodo.esCompleto( );
        else
            return false;
    }

    /**
     * Devuelve un iterador con los elementos que constituyen el camino al elemento especificado. <br>
     * <b>pre: </b> pElem!=null, resultado!=null. <br>
     * <b>post: </b> Se retornó el iterador con los elementos del camino al elemento especificado.
     * @param pElem Elemento del que se desea el camino
     * @param resultado Iterador para colocar los elementos del camino
     * @return Iterador con los elementos del camino
     */
    public void darCamino( T pElem, IteradorSimple<T> resultado ) throws NoExisteException
    {
        PilaEncadenada<T> pila = new PilaEncadenada<T>( );
        if( !darCaminoAux( pElem, pila ) )
        {
            throw new NoExisteException( "Elemento no encontrado" );
        }
        else
        {
            while( !pila.estaVacia( ) )
            {
                try
                {
                    resultado.agregar( pila.tomarElemento( ) );
                }
                catch( IteradorException e )
                {
                    // Esta excepción nunca debería ocurrir
                    e.printStackTrace( );
                }
                catch( PilaVaciaException e )
                {
                    // Esta excepción nunca debería ocurrir
                    e.printStackTrace( );
                }
            }
        }

    }
    /**
     * Método auxiliar. Retorna el camino el camino al elemento especificado usando la pila dada. <br>
     * <b>pre: </b> pElem!=null, pila!=null. <br>
     * <b>post: </b> Se retornó true si se encontró el camino al elemento especificado o false de lo contrario.
     * @param pElem Elemento al que se desea el camino
     * @param pila Pila en la que se va a retornar el camino
     * @return True si se encontró el camino o false de lo contrario
     */
    private boolean darCaminoAux( T pElem, PilaEncadenada<T> pila )
    {
        if( pElem.equals( elem ) )
        {
            pila.insertar( elem );
            return true;
        }
        else
        {
            if( izqNodo != null && izqNodo.darCaminoAux( pElem, pila ) )
            {
                pila.insertar( elem );
                return true;
            }
            if( derNodo != null && derNodo.darCaminoAux( pElem, pila ) )
            {
                pila.insertar( elem );
                return true;
            }
            return false;
        }
    }

    /**
     * Retorna el elemento que le sigue en el recorrido por inorden al elemento dado. <br>
     * <b>pre: </b> pElem!=null. <b>post: </b> Se retornó el elemento siguiente en el recorrido por inorden al elemento dado.<br>
     * @param pElem El elemento del que se desea el siguiente elemento
     * @return El siguiente elemento en el recorrido por inorden al elemento dado
     * @throws NoExisteException Si el elemento espeficado no existe
     */
    public T darSiguienteInorden( T pElem ) throws NoExisteException
    {
        Retorno ret = new Retorno( null, false );
        darSiguienteInordenAux( pElem, ret );
        if( ret.respuesta != null )
            return ret.respuesta;
        else
            throw new NoExisteException( "Elemento no encontrado" );
    }

    /**
     * Método auxliar. Retorna el elemento que le sigue en el recorrido por inorden. <br>
     * al elemento dado en el objeto Retorno especificado. <br>
     * <b>pre: </b> pElem!=null, rest!=null. <b>post: </b> Se retornó el elemento siguiente en el recorrido por inorden al elemento dado en ret o null dentro del atributo
     * respuesta de ret si éste no existe.
     * @param pElem El elemento del que se desea el siguiente elemento
     * @param ret Objeto en el que se retorna la respuesta
     */
    private void darSiguienteInordenAux( T pElem, Retorno ret )
    {
        if( izqNodo != null )
        {
            izqNodo.darSiguienteInordenAux( pElem, ret );
            if( ret.respuesta != null )
                return;
        }
        if( ret.encontrado )
        {
            ret.respuesta = elem;
            return;
        }
        else if( pElem.equals( elem ) )
        {
            ret.encontrado = true;
        }
        if( derNodo != null )
        {
            derNodo.darSiguienteInordenAux( pElem, ret );
        }
    }

    /**
     * Indica si el árbol que tiene como raíz el nodo actual se encuentra lleno. <br>
     * <b>pre: </b> altura!=null, altura>0. <br>
     * <b>post: </b> Se retornó true si el árbol está lleno o false de lo contrario.
     * @param altura La altura para verificar si el árbol está lleno
     * @return True si el árbol está lleno o false de lo contrario
     */
    public boolean estaLleno( int altura )
    {
        if( esHoja( ) )
            return altura == 1;
        else if( izqNodo == null || derNodo == null )
            return false;
        else
            return izqNodo.estaLleno( altura - 1 ) && derNodo.estaLleno( altura - 1 );
    }

    /**
     * Indica si el árbol que tiene como raíz el nodo actual se encuentra casi lleno. <br>
     * <b>pre: </b> altura!=null, altura>0. <br>
     * <b>post: </b> Se retornó true si el árbol está casi lleno o false de lo contrario.
     * @param altura La altura para verificar si el árbol está casi lleno
     * @return True si el árbol está casi lleno o false de lo contrario
     */
    public boolean estaCasiLleno( int altura )
    {
        if( esHoja( ) )
            return altura == 1;
        else if( izqNodo == null )
            return false;
        else if( derNodo == null )
            return altura == 2 && izqNodo.esHoja( );
        else
        {
            boolean llenoIzq = izqNodo.estaLleno( altura - 1 );
            if( llenoIzq && derNodo.estaLleno( altura - 1 ) )
                return true;
            else if( izqNodo.estaCasiLleno( altura - 1 ) && derNodo.estaLleno( altura - 2 ) )
                return true;
            else
                return llenoIzq && derNodo.estaCasiLleno( altura - 1 );
        }
    }

    /**
     * Calcula la rama más larga del árbol y la retorna en el iterador que se recibe como parámetro
     * @param altura La altura del árbol
     * @param itera El iterador en el que se está almacenando la rama más larga
     * @return true si ya se encontró la rama más larga o false en caso contrario
     */
    public boolean darRamaMasLarga( int altura, IteradorSimple<T> itera )
    {
        try
        {
            if( esHoja( ) && altura == 1 )
            {
                itera.agregar( elem );
                return true;
            }
            else if( esHoja( ) )
            {
                // No ha llegado al nivel esperado y es una hoja
                return false;
            }
            else if( izqNodo != null && izqNodo.darRamaMasLarga( altura - 1, itera ) )
            {
                // Intenta buscar la rama más larga por el subárbol izquierdo
                itera.insertar( elem );
                return true;
            }
            else if( derNodo != null && derNodo.darRamaMasLarga( altura - 1, itera ) )
            {
                // Intenta buscar la rama más larga por el subárbol derecho
                itera.insertar( elem );
                return true;
            }
            else
                return false;
        }
        catch( IteradorException e )
        {
            // Nunca debería aparecer esta excepción
            return false;
        }
    }
    
    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    /**
     * Clase auxiliar para los recorridos realizados sobre el árbol cuya raíz es el nodo actual
     */
    private class Retorno
    {
        // -----------------------------------------------------------------
        // Atributos
        // -----------------------------------------------------------------

        /**
         * Elemento correspondiente a la respuesta de la operación que se está realizando
         */
        private T respuesta;

        /**
         * Indica si se encontró el elemento buscado de acuerdo a la operación que se este efectuando
         */
        private boolean encontrado;

        // -----------------------------------------------------------------
        // Constructores
        // -----------------------------------------------------------------

        /**
         * Construye un objeto retorno con la respuesta y el valor de encontrado especificados <br>
         * <b>post: </b> Se construyó un objeto de la clase con los valores especificados<br>
         * @param pRespuesta Elemento que corresponde a la respuesta
         * @param pEncontrado Booleano con el valor de encontrado
         */
        private Retorno( T pRespuesta, boolean pEncontrado )
        {
            respuesta = pRespuesta;
            encontrado = pEncontrado;
        }
    }
}
