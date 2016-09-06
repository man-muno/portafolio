/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ListaEncadenadaOrdenada.java,v 1.16 2006/07/03 23:31:22 cupi2ejemplos Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 3, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.listaEncadenadaOrdenada;

/**
 * Lista doblemente encadenada con cabeza y cola, en la que sus elementos se encuentran ordenados. Los objetos de tipo T debe implementar la interface <b>Comparable</b>
 * @param <T> Tipo de datos a almacenar en la lista
 */
public class ListaEncadenadaOrdenada<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Cabeza de la lista encadenada
     */
    private NodoLista<T> primero;

    /**
     * Número de elementos de la lista
     */
    private int numElems;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la lista vacía. <br>
     * <b>post: </b> Se construyó una lista vacía.
     */
    public ListaEncadenadaOrdenada( )
    {
        primero = null;
        numElems = 0;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Busca un elemento en la lista encadenada ordenada. <br>
     * <b>post: </b> Se retornó el elemento o null si no existe. <br>
     * @param modelo Modelo del elemento a buscar
     * @return Elemento en la lista, null si no existe
     */
    public T buscar( T modelo )
    {
        for( NodoLista<T> p = primero; p != null; p = p.darSiguiente( ) )
        {
            if( p.darElemento( ).compareTo( modelo ) == 0 )
                return p.darElemento( );
        }
        return null;
    }

    /**
     * Retorna la longitud (cantidad de elementos) de la lista encadenada ordenada. <br>
     * <b>post: </b> Se retornó la longitud de la lista.
     * @return Longitud de la lista
     */
    public int darLongitud( )
    {
        return numElems;
    }

    /**
     * Retorna el primer nodo de la lista. <br>
     * <b>post: </b> Se retornó el primer nodo de la lista.
     * @return Primer nodo de la lista
     */
    public NodoLista<T> darPrimero( )
    {
        return primero;
    }

    /**
     * Inserta el elemento en la lista en la posición que le corresponde. <br>
     * <b>post: </b> Se insertó el elemento en la posición que le corresponde dentro de la lista de acuerdo a la relación de orden existente entre los elementos de tipo T.
     * @param elemento Elemento a insertar
     */
    public void insertar( T elemento )
    {
        NodoLista<T> nodo = new NodoLista<T>( elemento );
        if( primero == null )
        {
            primero = nodo;
        }
        else if( elemento.compareTo( primero.darElemento( ) ) < 0 )
        {
            // Debe quedar como primer elemento de la lista
            primero.insertarAntes( nodo );
            primero = nodo;
        }
        else
        {
            NodoLista<T> p = primero;
            for( ; p.darSiguiente( ) != null && p.darSiguiente( ).darElemento( ).compareTo( elemento ) < 0; p = p.darSiguiente( ) )
                ;
            p.insertarDespues( nodo );
        }
        numElems++;
    }

    /**
     * Elimina el elemento especificado de la lista encadenada ordenada. <br>
     * <b>post: </b> Se eliminó el elemento especificado de la lista.
     * @param elemento Elemento a eliminar
     * @throws NoExisteException Si el elemento especificado no existe
     */
    public void eliminar( T elemento ) throws NoExisteException
    {
        if( primero == null )
        {
            throw new NoExisteException( "Elemento no existe" );
        }
        else if( elemento.compareTo( primero.darElemento( ) ) == 0 )
        {
            // Se debe eliminar el primer elemento de la lista
            primero = primero.desconectarPrimero( );
            numElems--;
        }
        else
        {
            for( NodoLista<T> p = primero.darSiguiente( ); p != null; p = p.darSiguiente( ) )
            {
                if( p.darElemento( ).compareTo( elemento ) == 0 )
                {
                    p.desconectarNodo( );
                    numElems--;
                    return;
                }
            }
            throw new NoExisteException( "Elemento no existe" );
        }
    }

    /**
     * Convierte la lista a un String. <br>
     * <b>post: </b> Se retornó la representación en String de la lista. El String tiene el formato "[numeroElementos]: e1<->e2<->e3..<->en", donde e1, e2, ..., en son los
     * elementos que contiene la lista y numeroElementos su longitud. <br>
     * @return La representación en String de la lista
     */
    @Override
    public String toString( )
    {
        String resp = "[" + numElems + "]:";
        for( NodoLista<T> p = primero; p != null; p = p.darSiguiente( ) )
        {
            resp += p.darElemento( ).toString( ) + "<->";
        }
        return resp;
    }
}
