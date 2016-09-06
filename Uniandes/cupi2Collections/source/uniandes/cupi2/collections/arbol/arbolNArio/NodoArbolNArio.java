/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoArbolNArio.java,v 1.1 2008/03/30 01:53:03 jua-gome Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Manuel Mu�oz - May 16, 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbol.arbolNArio;

import java.io.Serializable;

import uniandes.cupi2.collections.colaEncadenada.ColaEncadenada;
import uniandes.cupi2.collections.colaEncadenada.ColaVaciaException;
import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;
import uniandes.cupi2.collections.lista.Lista;

/**
 * Implementaci�n de un nodo de un �rbol n-ario
 * @param <T> Tipo de datos que contiene el nodo
 */
public class NodoArbolNArio<T> implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Elemento del nodo
     */
    private T elem;

    /**
     * Sub�rboles hijos del nodo actual
     */
    private Lista<NodoArbolNArio<T>> hijos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nodo para un arbol n-ario. <b>post: </b> elem=pElem; hijos=null
     * @param Elemento que tendr� el nuevo nodo.
     */
    public NodoArbolNArio( T pElem )
    {
        elem = pElem;
        hijos = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo hijo al nodo actual. <b>post: </b> Si la lista esta no inicializada la crea y agrega un nuevo nodo al final. nodo!=null
     * @param Nuevo nodo a ser agregado.
     */
    public void agregarHijo( NodoArbolNArio<T> nodo )
    {
        if( hijos == null )
            hijos = new Lista<NodoArbolNArio<T>>( );
        hijos.agregar( nodo );
    }

    /**
     * Retorna el elemento del nodo. <br>
     * <b>post: </b> Se retorn� el elemento del �rbol.
     * @return El elemento que contiene el nodo
     */
    public T darElemento( )
    {
        return elem;
    }

    /**
     * 
     */
    public Lista<NodoArbolNArio<T>> darHijos( )
    {
        return hijos;
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
        return hijos == null;
    }

    /**
     * Devuelve la altura del �rbol cuya ra�z es el nodo actual. <b>post: </b> Se retorno la altura del �rbol actual.
     * @return Altura del �rbol cuya ra�z es el nodo actual. Entero mayor o igual a 1.
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
                NodoArbolNArio<T> hijo = hijos.darElemento( i );
                int auxAltura = hijo.darAltura( );
                if( auxAltura > maxAltura )
                    maxAltura = auxAltura;
            }
            return maxAltura + 1;
        }
    }

    /**
     * Devuelve el peso del �rbol cuya ra�z es el nodo actual. <b>post: </b> Se retorna el peso del �rbol actual.
     * @return Peso del �rbol cuya ra�z es el nodo actual. Entero mayor o igual a cero.
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
     * Devuelve el numero de hojas del �rbol cuya ra�z es el nodo actual <b>post: </b> Retorna la cantidad de hojas que tiene el �rbol actual. Entero mayor o igual a 1.
     * @return Numero de hojas del �rbol cuya ra�z es el nodo actual
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
     * Busca el elemento del �rbol que corresponda al modelo especificado. <br>
     * <b>pre: </b> modelo!=null. <br>
     * <b>post: </b> Se retorn� el elemento correspondiente al modelo o null
     * @param modelo Descripci�n del elemento que se va a buscar en el �rbol.
     * @return El elemento que corresponde al modelo. si ning�n elemento corresponde al modelo se retorna null
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
     * Agrega los elementos al iterador que llega como par�metro, utilizando para esto un recorrido en inorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del �rbol en inorden.
     * @param resultado Resultado del recorrido
     */
    public void inorden( IteradorSimple<T> resultado )
    {
        if( hijos == null )
        {
            try
            {
                resultado.agregar( elem );
            }
            catch( IteradorException e )
            {
                // No deber�a ocurrir esta excepci�n
            }
        }
        else
        {
            hijos.darElemento( 0 ).inorden( resultado );
            try
            {
                resultado.agregar( elem );
            }
            catch( IteradorException e )
            {
                // No deber�a ocurrir esta excepci�n
            }
            for( int i = 1; i < hijos.darLongitud( ); i++ )
            {
                hijos.darElemento( i ).inorden( resultado );
            }
        }
    }

    /**
     * Agrega los elementos al iterador que llega como par�metro, utilizando para esto un recorrido en preorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del �rbol en preorden.
     * @param resultado Resultado del recorrido
     */
    public void preorden( IteradorSimple<T> resultado )
    {
        try
        {
            resultado.agregar( elem );
        }
        catch( IteradorException e )
        {
            // No deber�a ocurrir esta excepci�n
        }
        for( int i = 0; hijos != null && i < hijos.darLongitud( ); i++ )
        {
            hijos.darElemento( i ).preorden( resultado );
        }
    }

    /**
     * Agrega los elementos al iterador que llega como par�metro, utilizando para esto un recorrido en postorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del �rbol en postorden.
     * @param resultado Resultado del recorrido
     */
    public void postorden( IteradorSimple<T> resultado )
    {
        for( int i = 0; hijos != null && i < hijos.darLongitud( ); i++ )
        {
            hijos.darElemento( i ).postorden( resultado );
        }
        try
        {
            resultado.agregar( elem );
        }
        catch( IteradorException e )
        {
            // No deber�a ocurrir esta excepci�n
        }
    }

    /**
     * Agrega los elementos al iterador que llega como par�metro, utilizando para esto un recorrido por niveles. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del �rbol por niveles.
     * @param resultado Resultado del recorrido
     */
    public void darRecorridoNiveles( IteradorSimple<T> resultado )
    {
        ColaEncadenada<NodoArbolNArio<T>> cola = new ColaEncadenada<NodoArbolNArio<T>>( );
        cola.insertar( this );
        while( cola.darLongitud( ) != 0 )
        {
            NodoArbolNArio<T> nodo = null;
            try
            {
                // Toma el primer �rbol de la cola
                nodo = cola.tomarElemento( );
            }
            catch( ColaVaciaException e )
            {
                // Nunca deber�a aparecer esta excepci�n
            }
            try
            {
                resultado.agregar( nodo.elem );
            }
            catch( IteradorException e )
            {
                // Nunca deber�a aparecer esta excepci�n
            }

            // Agrega los dos sub�rboles (si no son vac�os) a la cola
            for( int i = 0; nodo.hijos != null && i < nodo.hijos.darLongitud( ); i++ )
            {
                cola.insertar( nodo.hijos.darElemento( i ) );
            }
        }

    }

    /**
     * Retorna la cantidad de elementos que se encuentran en el nivel especificado. <b>post: </b> Entero mayor o igual a 1.
     * @param Entero mayor o igual a 0.
     * @return Entero con la cantidad de nodos que se encuentran en el nivel dado.
     */
    public int darTamanoNivel( int nivel )
    {
        if( nivel == 0 )
        {
            return 1;
        }

        int cantidadElementos = 0;
        for( int i = 0; hijos != null && i < hijos.darLongitud( ); i++ )
        {
            cantidadElementos += hijos.darElemento( i ).darTamanoNivel( nivel - 1 );
        }

        return cantidadElementos;
    }

    /**
     * Retorna los elementos del nivel especificado en un iterador. <br>
     * <b>post: </b> Se retorn� los elementos del nivel especificado en un iterador.
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
                // Nunca deber�a aparecer esta excepci�n
            }
        }
        else
        {
            for( int i = 0; hijos != null && i < hijos.darLongitud( ); i++ )
            {
                hijos.darElemento( i ).darNivel( nivel - 1, resultado );
            }
        }
    }
}
