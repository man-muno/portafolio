/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - 9-May-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.listaEncadenada;

/**
 * Lista doblemente encadenada con cabeza y cola
 * @param <T> Tipo de datos a almacenar en la lista
 */
public class ListaEncadenada<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Cabeza de la lista encadenada
     */
    private NodoLista<T> primero;

    /**
     * �ltimo elemento de la lista encadenada
     */
    private NodoLista<T> ultimo;

    /**
     * N�mero de elementos de la lista
     */
    private int numElems;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la lista vac�a. <br>
     * <b>post: </b> Se construy� una lista vac�a.
     */
    public ListaEncadenada( )
    {
        primero = null;
        ultimo = null;
        numElems = 0;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Busca un elemento en la lista encadenada. <br>
     * <b>post: </b> Se retorn� el elemento o null si no existe.
     * @param modelo Modelo del elemento a buscar
     * @return Elemento en la lista, null si no existe
     */
    public T buscar( T modelo )
    {
        for( NodoLista<T> p = primero; p != null; p = p.darSiguiente( ) )
        {
            if( p.darElemento( ).equals( modelo ) )
            {
                return p.darElemento( );
            }
        }
        return null;
    }

    /**
     * Retorna la longitud (cantidad de elementos) de la lista encadenada. <br>
     * <b>post: </b> Se retorn� la longitud de la lista.
     * @return Longitud de la lista
     */
    public int darLongitud( )
    {
        return numElems;
    }

    /**
     * Retorna el primer nodo de la lista. <br>
     * <b>post: </b> Se retorn� el primer nodo de la lista.
     * @return Primer nodo de la lista
     */
    public NodoLista<T> darPrimero( )
    {
        return primero;
    }

    /**
     * Retorna el �ltimo nodo de la lista. <br>
     * <b>post: </b> Se retorn� el �ltmo nodo de la lista.
     * @return �ltimo nodo de la lista
     */
    public NodoLista<T> darUltimo( )
    {
        return ultimo;
    }

    /**
     * Inserta el elemento en la cabeza (inicio) de la lista. <br>
     * <b>post: </b> Se insert� el elemento en la cabeza de la lista.
     * @param elemento Elemento a insertar
     */
    public void insertarCabeza( T elemento )
    {
        NodoLista<T> nodo = new NodoLista<T>( elemento, this );
        if( primero == null )
        {
            primero = nodo;
            ultimo = nodo;
        }
        else
        {
            primero.insertarAntes( nodo );
            primero = nodo;
        }
        numElems++;
    }

    /**
     * Inserta el elemento en la cola (final) de la lista. <br>
     * <b>post: </b> Se insert� el elemento en la cola de la lista.
     * @param elemento Elemento a insertar
     */
    public void insertarCola( T elemento )
    {
        NodoLista<T> nodo = new NodoLista<T>( elemento, this );
        if( primero == null )
        {
            primero = nodo;
            ultimo = nodo;
        }
        else
        {
            ultimo.insertarDespues( nodo );
            ultimo = nodo;
        }
        numElems++;
    }

    /**
     * Elimina el nodo de la lista encadenada. <br>
     * <b>post: </b> Se elimin� el nodo especificado de la lista.
     * @param nodo Nodo a ser eliminado de la lista
     * @throws NoExisteException El nodo especificado no pertenece a la lista
     */
    public void eliminarNodo( NodoLista<T> nodo ) throws NoExisteException
    {
        if( !nodo.darLista( ).equals( this ) )
        {
            throw new NoExisteException( "El nodo especificado no pertenece a la lista" );
        }
        if( primero == nodo )
        {
            primero = nodo.desconectarPrimero( );
            if( ultimo == nodo )
            {
                ultimo = null;
            }
        }
        else
        {
            if( ultimo == nodo )
            {
                ultimo = nodo.darAnterior( );
            }
            nodo.desconectarNodo( );
        }
    }

    /**
     * Elimina el primer nodo (cabeza) de la lista y devuelve el elemento que conten�a. <br>
     * <b>post: </b> Se elimin� el primer nodo de la lista. Se retorn� el elemento contenido en el nodo eliminado. Si la lista no tiene nodos se retorna null.
     * @return Elemento del nodo eliminado
     */
    public T eliminarPrimero( )
    {
        //
        // Si no tiene
        if( primero == null )
        {
            return null;
        }
        else
        {
            //
            // Elimina el primer elemento
            try
            {
                return eliminar( primero.darElemento( ) );
            }
            catch( NoExisteException e )
            {
                //
                // Esto nunca deber�a pasar
                return null;
            }
        }
    }

    /**
     * Elimina el �ltimo nodo y devuelve el elemento que conten�a. <br>
     * <b>post: </b> Se elimin� el �ltimo nodo de la lista. Se retorn� el elemento contenido en el nodo eliminado. Si la lista no tiene nodos se retorna null.
     * @return Elemento del nodo eliminado
     */
    public T eliminarUltimo( )
    {
        //
        // Si no tiene
        if( ultimo == null )
        {
            return null;
        }
        else
        {
            //
            // Elimina el �ltimo elemento
            try
            {
                return eliminar( ultimo.darElemento( ) );
            }
            catch( NoExisteException e )
            {
                //
                // Esto nunca deber�a pasar
                return null;
            }
        }
    }

    /**
     * Elimina el elemento especificado de la lista encadenada. <br>
     * <b>post: </b> Se elimin� el elemento especificado de la lista.
     * @param elemento Elemento a eliminar
     * @return Elemento eliminado
     * @throws NoExisteException Si el elemento especificado no existe
     */
    public T eliminar( T elemento ) throws NoExisteException
    {
        T valor = null;
        if( primero == null )
        {
            throw new NoExisteException( "Elemento no existe" );
        }
        else if( primero.darElemento( ).equals( elemento ) )
        {
            if( primero.equals( ultimo ) )
            {
                ultimo = null;
            }
            valor = primero.darElemento( );
            primero = primero.desconectarPrimero( );
            numElems--;
            return valor;
        }
        else
        {
            for( NodoLista<T> p = primero.darSiguiente( ); p != null; p = p.darSiguiente( ) )
            {
                if( p.darElemento( ).equals( elemento ) )
                {
                    if( p.equals( ultimo ) )
                    {
                        ultimo = p.darAnterior( );
                    }
                    valor = p.darElemento( );
                    p.desconectarNodo( );
                    numElems--;
                    return valor;
                }
            }
            throw new NoExisteException( "Elemento no existe" );
        }
    }

    /**
     * Inserta el elemento en la posici�n especificada. <br>
     * <b>post: </b> Se insert� el elemento en la posici�n especificada.
     * @param elemento Elemento a insertar
     * @param pos La posici�n en la que se va a insertar el elemento
     */
    public void insertar( T elemento, int pos )
    {
        NodoLista<T> nodo = new NodoLista<T>( elemento, this );
        if( ( pos >= numElems ) || pos < 0 )
        {
            throw new IndiceFueraDeRango( pos );
        }
        else
        {
            NodoLista<T> aux = primero;

            for( int cont = 0; cont < pos - 1; cont++ )
            {
                aux = aux.darSiguiente( );
            }
            aux.insertarDespues( nodo );
            numElems++;
        }
    }

    /**
     * Retorna el elemento en la posici�n especificada. <br>
     * <b>post: </b>Se retorn� el elemento que se encuentra en la posici�n especificada.
     * @param pos La posici�n del elemento a retornar
     * @return Elemento que se encuentra en la posici�n especificada
     */
    public T dar( int pos )
    {
        if( pos >= numElems || pos < 0 )
        {
            throw new IndiceFueraDeRango( pos );
        }
        else
        {
            NodoLista<T> aux = primero;

            for( int cont = 0; cont < pos; cont++ )
            {
                aux = aux.darSiguiente( );
            }

            return aux.darElemento( );
        }
    }

    /**
     * Elimina el elemento en la posici�n especificada. <br>
     * <b>post: </b> Se elimin� el elemento en la posici�n especificada.
     * @param pos Posici�n del elemento a eliminar
     * @return Elemento eliminado
     */
    public T eliminar( int pos )
    {
        T valor = null;

        if( ( pos >= numElems ) || pos < 0 )
        {
            throw new IndiceFueraDeRango( pos );
        }
        else if( pos == 0 )
        {
            if( primero.equals( ultimo ) )
            {
                ultimo = null;
            }
            valor = primero.darElemento( );
            primero = primero.desconectarPrimero( );
            numElems--;
            return valor;
        }
        else
        {

            NodoLista<T> p = primero.darSiguiente( );
            for( int cont = 1; cont < pos; cont++ )
            {
                p = p.darSiguiente( );
            }

            if( p.equals( ultimo ) )
            {
                ultimo = p.darAnterior( );
            }
            valor = p.darElemento( );
            p.desconectarNodo( );
            numElems--;
            return valor;
        }
    }

    /**
     * Convierte la lista a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n en String de la lista. El String tiene el formato "[numeroElementos]: e1<->e2<->e3..<->en", donde e1, e2, ..., en son los
     * elementos que contiene la lista y numeroElementos su longitud.
     * @return La representaci�n en String de la lista
     */
    @Override
    public String toString( )
    {
        String resp = "ida: [" + numElems + "]:";
        for( NodoLista<T> p = primero; p != null; p = p.darSiguiente( ) )
        {
            resp += p.darElemento( ).toString( ) + "<->";
        }
        resp += "\r\nvuelta:[" + numElems + "]:";
        for( NodoLista<T> p = ultimo; p != null; p = p.darAnterior( ) )
        {
            resp += p.darElemento( ).toString( ) + "<->";
        }
        return resp;
    }
}
