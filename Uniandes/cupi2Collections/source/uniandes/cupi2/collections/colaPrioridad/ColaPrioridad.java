/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ColaPrioridad.java,v 1.12 2006/06/07 17:15:24 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 11, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.colaPrioridad;

import uniandes.cupi2.collections.colaEncadenada.*;

/**
 * Implementación de una cola de prioridad
 * @param <T> Tipo de datos que contiene cada nodo de la cola. Los objetos de tipo T deben implentar la interface <b>Comparable</b> para poder realizar la inserción en la cola
 *        de forma correcta
 */
public class ColaPrioridad<T extends Comparable<? super T>> extends ColaEncadenada<T>
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inserta un elemento en la cola de acuerdo a su prioridad. <br>
     * <b>pre: </b> elem!=null. <br>
     * <b>post: </b> Se insertó el elemento en la cola de acuerdo a su prioridad.
     * @param elem El elemento a ser insertado
     */
    public void insertar( T elem )
    {
        NodoCola<T> nodo = new NodoCola<T>( elem );
        if( primero == null )
        {
            primero = nodo;
            ultimo = nodo;
        }
        // Verifica si tiene mayor prioridad que el primer elemento de la cola
        else if( primero.darElemento( ).compareTo( elem ) < 0 )
        {
            nodo.insertarDespues( primero );
            primero = nodo;
        }
        else
        {
            // Recorre la cola hasta encontrar un nodo de menor prioridad
            boolean inserto = false;
            for( NodoCola<T> p = primero; !inserto && p.darSiguiente( ) != null; p = p.darSiguiente( ) )
            {
                if( p.darSiguiente( ).darElemento( ).compareTo( elem ) < 0 )
                {
                    nodo.insertarDespues( p.darSiguiente( ) );
                    p.insertarDespues( nodo );
                    inserto = true;
                }
            }
            if( !inserto )
            {
                // No lo ha insertado porque tiene la menor prioridad de toda la cola
                ultimo = ultimo.insertarDespues( nodo );
            }
        }
        numElems++;
    }
}
