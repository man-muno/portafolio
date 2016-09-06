/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Lista.java,v 1.14 2006/06/07 17:15:24 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 13, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.lista;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;

/**
 * Representa una lista modelada con un arreglo dinamico
 * @param <T> Tipo de datos a almacenar en la lista. Los objetos de tipo T deben tener bien definido el m�todo <b>equals</b>
 */
public class Lista<T>
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Tama�o inicial del arreglo
     */
    private final static int INIT = 20;

    /**
     * N�mero de posiciones a agregar al crecer el arreglo
     */
    private final static int DELTA = 20;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Elementos de la lista
     */
    protected T[] elems;

    /**
     * N�mero de elementos actualmente en la lista
     */
    protected int numElems;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la lista. <br>
     * <b>post: </b> Se construy� una lista vac�a.
     */
    public Lista( )
    {
        this( INIT );
    }

    /**
     * Constructor de la lista con la capacidad inicial. <br>
     * <b>pre: </b> capacidad!=null, capacidad>0. <br>
     * <b>post: </b> Se construy� la lista con la capacidad inicial especificada.
     * @param capacidad Capacidad inicial para la lista
     */
    @SuppressWarnings("unchecked")
    public Lista( int capacidad )
    {
        elems = ( T[] )new Object[capacidad];
        numElems = 0;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve el elemento en la posici�n especificada. <br>
     * <b>post</b>: Se retorn� el elemento en la posici�n especificada.
     * @param pos Posici�n del elemento
     * @return Elemento en la posici�n especificada
     */
    public T darElemento( int pos )
    {
        if( pos < 0 || pos > numElems )
            throw new IndiceFueraDeRango( pos );
        return elems[ pos ];
    }

    /**
     * Agrega el elemento al final lista. <br>
     * <b>pre: </b> elem!= null. <br>
     * <b>post: </b> Se agreg� el elemento especificado al final de la lista. Si con la adici�n se excede la capacidad de la lista, la nueva capacidad de la lista es
     * elem.length+DELTA. <br>
     * @param elem Elemento a agregar
     */
    @SuppressWarnings("unchecked")
    public void agregar( T elem )
    {
        // Verifica si hay que aumentar el tama�o de la representaci�n
        if( numElems == elems.length )
        {
            T viejo[] = elems;
            elems = ( T[] )new Object[elems.length + DELTA];
            System.arraycopy( viejo, 0, elems, 0, viejo.length );
        }
        elems[ numElems++ ] = elem;
    }

    /**
     * Inserta el elemento en la posici�n especificada. <br>
     * <b>post: </b> Se insert� el elemento en la posici�n especificada desplaz�ndose los elementos que se encuentran desde pos una posici�n adelante. Si con la adici�n se
     * excede la capacidad de la lista, la nueva capacidad de la lista es elem.length+DELTA. <br>
     * @param elem Elemento a insertar
     * @param pos Posici�n en la que se va a insertar el elemento
     */
    @SuppressWarnings("unchecked")
    public void insertar( T elem, int pos )
    {
        if( pos < 0 || pos > numElems )
        {
            throw new IndiceFueraDeRango( pos );
        }
        // Verifica si hay que aumentar el tama�o de la representaci�n
        if( numElems == elems.length )
        {
            T viejo[] = elems;
            elems = ( T[] )new Object[elems.length + DELTA];
            System.arraycopy( viejo, 0, elems, 0, viejo.length );
        }
        // Abre espacio para el nuevo elemento
        for( int i = numElems - 1; i >= pos; i-- )
        {
            elems[ i + 1 ] = elems[ i ];
        }
        // Incrementa el n�mero de elementos
        numElems++;
        // Inserta el nuevo elemento
        elems[ pos ] = elem;
    }

    /**
     * Elimina el elemento en la posici�n especificada. <br>
     * <b>post: </b> Se elimin� el elemento en la posici�n especificada desplaz�ndose todos los elementos que se encuentran desde pos+1 una posici�n hacia atr�s. <br>
     * @param pos Posici�n del elemento a eliminar
     * @return Elemento eliminado. En el caso de que no se elimine ning�n elemento se retorna null
     */
    public T eliminar( int pos )
    {
        if( pos < 0 || pos >= numElems )
        {
            throw new IndiceFueraDeRango( pos );
        }

        T resp = elems[ pos ];
        for( int i = pos; i < numElems - 1; i++ )
        {
            elems[ i ] = elems[ i + 1 ];
        }
        elems[ numElems - 1 ] = null;
        numElems--;
        return resp;
    }

    /**
     * Elimina el elemento especificado de la lista. <br>
     * <b>post: </b> Se elimin� el elemento especificado desplaz�ndose todos los elementos que se encuentran adelante de este elemento una posici�n hacia atr�s. Si el
     * elemento no existe se retorna null. 
     * @param elem Elemento a eliminar
     * @return Elemento eliminado o null si el elemento no existe.
     */
    public T eliminar( T elem )
    {
        int pos = 0;
        for( ; pos < numElems && !elem.equals( elems[ pos ] ); pos++ )
            ;
        
        T eliminado= null; 
        if(pos<numElems)
            eliminado= eliminar( pos );
        
        return eliminado;
    }

    /**
     * Busca el elemento especificado. <br>
     * <b>post:</b> Se retorn� la posici�n del elemento buscado dentro de la lista o -1 si no existe. <br>
     * @param elem Elemento a buscar
     * @return Posici�n del elemento buscado dentro de la lista o -1 si no existe
     */
    public int buscar( T elem )
    {
        int pos = 0;
        for( ; pos < numElems && !elem.equals( elems[ pos ] ); pos++ )
            ;
        return pos == numElems ? -1 : pos;
    }

    /**
     * Devuelve la longitud de la lista. <br>
     * <b>post:</b> Se retorn� la longitud de la lista (n�mero de elementos).
     * @return Longitud de la lista
     */
    public int darLongitud( )
    {
        return numElems;
    }

    /**
     * Asigna a la posici�n especificada al elemento dado. <br>
     * <b>post:</b> El nuevo elemento en la posici�n pos es elem.
     * @param elem Elemento a asignar
     * @param pos Posici�n en la que se va a relizar la asignaci�n
     */
    public void asignar( T elem, int pos )
    {
        if( pos < 0 || pos > numElems || numElems == 0 )
            throw new IndiceFueraDeRango( pos );
        elems[ pos ] = elem;
    }

    /**
     * Devuelve un iterador con los elementos de la lista. <br>
     * <b>post:</b> Se retorn� iterador con los elementos de la lista.
     * @return Iterador con los elementos de la lista
     */
    public Iterador<T> darIterador( )
    {
        IteradorSimple<T> respuesta = new IteradorSimple<T>( numElems );
        for( int i = 0; i < numElems; i++ )
        {
            try
            {
                respuesta.agregar( elems[ i ] );
            }
            catch( IteradorException e )
            {
                // Nunca deber�a ocurrir esta excepci�n
                e.printStackTrace( );
            }
        }
        return respuesta;
    }

    /**
     * Vac�a la lista.<br>
     * <b>post:</b> La lista se encuentra vacia.
     */
    public void vaciar( )
    {
        // Se borran las referencias a los objetos presentes en la lista, para permitir el adecuado
        // trabajo del recolector de basura
        for( int i = 0; i < numElems; i++ )
            elems[ i ] = null;
        numElems = 0;
    }

    /**
     * Convierte la lista a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n en String de la lista. El String tiene el formato "[numeroElementos]: e1-e2-e3..-en", donde e1, e2, ..., en son los elementos
     * que contiene la lista y numeroElementos su longitud.
     * @return La representaci�n en String de la lista
     */
    @Override
    public String toString( )
    {
        String resp = "[" + numElems + "]:";
        for( int i = 0; i < numElems; i++ )
        {
            resp += elems[ i ] + "-";
        }
        return resp;
    }
}