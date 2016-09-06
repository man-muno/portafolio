/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Conjunto.java,v 1.8 2006/06/05 16:43:45 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 11, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.conjunto;

import uniandes.cupi2.collections.iterador.*;
import uniandes.cupi2.collections.lista.*;

/**
 * Implementaci�n de un conjunto
 * @param <T> Tipo de datos que contiene el conjunto. Los objetos de tipo T deben tener bien definido el m�todo <b>equals</b>
 */
public class Conjunto<T>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Elementos del conjunto
     */
    private Lista<T> elems;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del conjunto vac�o. <br>
     * <b>post: </b> Se construy� un conjunto vac�o.
     */
    public Conjunto( )
    {
        elems = new Lista<T>( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inserta el elemento especificado en el conjunto. <br>
     * <b>pre: </b> elem!=null. <br>
     * <b>post: </b> Se retorn� true si el elemento fue insertado o false en caso contrario. Un elemento no es adicionado si �ste ya existe en el conjunto.
     * @return True si el elemento fue insertado o false en caso contrario
     */
    public boolean insertar( T elem )
    {
        if( elems.buscar( elem ) != -1 )
            return false;
        else
        {
            elems.agregar( elem );
            return true;
        }
    }

    /**
     * Elimina el elemento especificado del conjunto. <br>
     * <b>pre: </b> elem!=null. <br>
     * <b>post: </b> Se retorn� true si el elemento fue eliminad� del conjunto o o false en caso contrario. Un elemento no es eliminado si �ste no existe en el conjunto. <br>
     * @return True si el elemento fue eliminado o false en caso contrario
     */
    public boolean eliminar( T elem )
    {
        try
        {
            elems.eliminar( elem );
            return true;
        }
        catch( IndiceFueraDeRango e )
        {
            return false;
        }
    }

    /**
     * Busca el elemento especificado en el conjunto. <br>
     * <b>pre: </b> elem!=null. <br>
     * <b>post: </b> Se retorn� true si el elemento existe en el conjunto o false en caso contrario.
     * @return True si el elemento se encontr� en el conjunto o false en caso contrario
     */
    public boolean buscar( T elem )
    {
        return elems.buscar( elem ) != -1;
    }

    /**
     * Retorna la cardinalidad del conjunto (el n�mero de elementos). <br>
     * <b>post: </b> Se retorn� la cardinalidad del conjunto.
     * @return La cardinalidad del conjunto
     */
    public int cardinalidad( )
    {
        return elems.darLongitud( );
    }

    /**
     * Indica si el conjunto es vacio (el n�mero de elementos es cero). <br>
     * <b>post: </b> Se retorn� true si el conjunto es vac�o o false en caso contrario.
     * @return True si el conjunto es vac�o o false en caso contrario<br>
     */
    public boolean vacio( )
    {
        return elems.darLongitud( ) == 0;
    }

    /**
     * Retorna un iterador para recorrer los elementos del conjunto. <br>
     * <b>post: </b> Se retorn� el iterador para recorrer los elementos del conjunto.
     * @return El iterador para recorrer los elementos del conjunto
     */
    public Iterador<T> darIterador( )
    {
        return elems.darIterador( );
    }

    /**
     * Convierte el conjunto a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n en String del conjunto. El String tiene el formato "[numeroElementos]: e1-e2-e3..-en", donde e1, e2, ..., en son los
     * elementos que contiene el conjunto y numeroElementos su cardinalidad.
     * @return La representaci�n en String del conjunto
     */
    @Override
    public String toString( )
    {
        return elems.toString( );
    }
}
