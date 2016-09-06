/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ColaEncadenada.java,v 1.14 2006/06/05 16:42:28 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.colaEncadenada;

/**
 * Implementaci�n de una cola encadenada
 * @param <T> Tipo de datos que contiene cada nodo de la cola
 */
public class ColaEncadenada<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Primer elemento de la cola encadenada
     */
    protected NodoCola<T> primero;

    /**
     * Ultimo elemento de la cola encadenada
     */
    protected NodoCola<T> ultimo;

    /**
     * N�mero de elementos de la cola
     */
    protected int numElems;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la cola encadenada vac�a. <br>
     * <b>post: </b> Se construy� una cola vac�a.
     */
    public ColaEncadenada( )
    {
        primero = null;
        ultimo = null;
        numElems = 0;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Retorna la longitud de la cola (n�mero de elementos). <br>
     * <b>post: </b> Se retorn� la longitud de la cola<br>.
     * @return El n�mero de elementos de la cola
     */
    public int darLongitud( )
    {
        return numElems;
    }

    /**
     * Retorna el primer elemento y lo elimina de la cola. <br>
     * <b>post: </b> Se retorn� y elimin� el primer elemento de la cola.
     * @return El primer elemento de la cola
     * @throws ColaVaciaException Si la cola no tiene elementos
     */
    public T tomarElemento( ) throws ColaVaciaException
    {
        if( primero == null )
            throw new ColaVaciaException( "No hay elementos en la cola" );
        else
        {
            NodoCola<T> p = primero;
            primero = primero.desconectarPrimero( );
            if( primero == null )
                ultimo = null;
            numElems--;
            return p.darElemento( );
        }
    }

    /**
     * Inserta un elemento al final de la cola. <br>
     * <b>post: </b> Se agreg� el elemento especificado al final de la cola.
     * @param elemento El elemento a ser insertado
     */
    public void insertar( T elemento )
    {
        NodoCola<T> nodo = new NodoCola<T>( elemento );
        if( primero == null )
        {
            primero = nodo;
            ultimo = nodo;
        }
        else
        {
            ultimo = ultimo.insertarDespues( nodo );
        }
        numElems++;
    }

    /**
     * Retorna el primer nodo de la cola. <br>
     * <b>post: </b> Se retorn� el primer nodo de la cola.
     * @return El primer nodo de la cola
     */
    public NodoCola darPrimero( )
    {
        return primero;
    }

    /**
     * Retorna el �ltimo nodo de la cola. <br>
     * <b>post: </b> Se retorn� el �ltimo nodo de la cola.
     * @return El �ltimo nodo de la cola
     */
    public NodoCola darUltimo( )
    {
        return ultimo;
    }

    /**
     * Indica si la cola se encuentra vac�a (no tiene elementos). <br>
     * <b>post: </b> Se retorn� true si primero==null o false en caso contrario.
     * @return True si primero==null o false en caso contrario
     */
    public boolean estaVacia( )
    {
        return primero == null;
    }

    /**
     * Convierte la cola a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n en String de la cola. El String tiene el formato "[numeroElementos]: e1->e2->e3..->en", donde e1, e2, ..., en son los los
     * elementos de la cola y numeroElementos su tama�o. <br>
     * @return La representaci�n en String de la cola
     */
    @Override
    public String toString( )
    {
        String resp = "[" + numElems + "]:";
        for( NodoCola<T> p = primero; p != null; p = p.darSiguiente( ) )
        {
            resp += p.darElemento( ).toString( ) + "->";
        }
        return resp;
    }

}
