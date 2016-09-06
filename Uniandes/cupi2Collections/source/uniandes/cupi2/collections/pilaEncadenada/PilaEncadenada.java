/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PilaEncadenada.java,v 1.12 2006/06/05 16:43:45 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.pilaEncadenada;

/**
 * Implementación de una pila encadenada
 * @param <T> Tipo de datos a almacenar en la pila
 */
public class PilaEncadenada<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Primer elemento de la pila encadenada
     */
    private NodoPila<T> primero;

    /**
     * Número de elementos de la pila
     */
    private int numElems;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la cola encadenada vacía<br>
     * <b>post: </b> Se construyó una cola vacía
     */
    public PilaEncadenada( )
    {
        primero = null;
        numElems = 0;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Se retornó la longitud de la pila (número de elementos). <br>
     * <b>post: </b> Se retornó la longitud de la pila.
     * @return Retorna el número de elementos de la pila
     */
    public int darLongitud( )
    {
        return numElems;
    }

    /**
     * Retorna el primer elemento y lo elimina de la pila. <br>
     * <b>post: </b> Se retornó y eliminó el primer elemento de la pila.
     * @return El primer elemento de la pila
     * @throws PilaVaciaException Si la pila no tiene elementos
     */
    public T tomarElemento( ) throws PilaVaciaException
    {
        if( primero == null )
            throw new PilaVaciaException( "No hay elementos en la pila" );
        else
        {
            NodoPila<T> p = primero;
            primero = primero.desconectarPrimero( );
            numElems--;
            return p.darElemento( );
        }
    }

    /**
     * Inserta un elemento en el tope de la pila. <br>
     * <b>post: </b> Se agregó el elemento especificado en el tope de la pila.
     * @param elemento El elemento a ser insertado
     */
    public void insertar( T elemento )
    {
        NodoPila<T> nodo = new NodoPila<T>( elemento );
        if( primero == null )
        {
            primero = nodo;
        }
        else
        {
            primero = primero.insertarAntes( nodo );
        }
        numElems++;
    }

    /**
     * Retorna el primer nodo de la pila. <br>
     * <b>post: </b> Se retornó el primer nodo de la pila.
     * @return El primer nodo de la pila
     */
    public NodoPila<T> darPrimero( )
    {
        return primero;
    }

    /**
     * Indica si la pila se encuentra vacía (no tiene elementos). <br>
     * <b>post: </b> Se retornó true si primero==null o false en caso contrario.
     * @return True si primero==null o false en caso contrario
     */
    public boolean estaVacia( )
    {
        return primero == null;
    }

    /**
     * Convierte la pila a un String. <br>
     * <b>post: </b> Se retornó la representación en String de la pila. El String tiene el formato "[numeroElementos]: e1->e2->e3..->en", donde e1, e2, ..., en son los los
     * elementos de la pila y numeroElementos su tamaño.
     * @return La representación en String de la cola
     */
    @Override
    public String toString( )
    {
        String resp = "[" + numElems + "]:";
        for( NodoPila<T> p = primero; p != null; p = p.darSiguiente( ) )
        {
            resp += p.darElemento( ).toString( ) + "->";
        }
        return resp;
    }
}
