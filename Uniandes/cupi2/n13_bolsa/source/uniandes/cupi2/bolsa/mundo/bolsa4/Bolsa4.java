/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Jorge Villalobos - 23-ago-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.mundo.bolsa4;

import java.util.ArrayList;
import java.util.Collections;

import uniandes.cupi2.bolsa.mundo.FueraLimiteException;
import uniandes.cupi2.bolsa.mundo.IBolsa;
import uniandes.cupi2.bolsa.mundo.IIteradorBolsa;
import uniandes.cupi2.bolsa.mundo.IteradorSimple;
import uniandes.cupi2.bolsa.mundo.NoExisteException;

/**
 * Implementación de una bolsa de valores numéricos
 */
public class Bolsa4 implements IBolsa
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * ArrayList que contiene los elementos de la bolsa
     */
    ArrayList elementos;

    /**
     * Límite inferior de la bolsa
     */
    private int inferior;

    /**
     * Límite superior de la bolsa
     */
    private int superior;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros
     */
    public Bolsa4(int inf, int sup)
    {
        elementos = new ArrayList( );
        inferior = inf;
        superior = sup;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo elemento a la bolsa
     * @param elem Elemento que quiere ser agregado a la bolsa
     */
    public void agregar( int elem ) throws FueraLimiteException
    {
        if( elem > inferior && elem < superior )
        {
            elementos.add( new Integer( elem ) );
            Collections.sort( elementos );
        }
        else
        {
            throw new FueraLimiteException( "Elemento fuera de rango" );
        }
    }

    /**
     * Elimina el primer elemento de la bolsa
     * @param elem Elemento que quiere ser eliminado
     * @throws Se lanza la excepción si no se encuentra el elemento
     */
    public void eliminar( int elem ) throws NoExisteException
    {
        if( ! ( elementos.remove( new Integer( elem ) ) ) )
        {
            throw new NoExisteException( "El elemento que se quiere eliminar no existe" );
        }
        Collections.sort( elementos );
    }

    /**
     * Retorna el elemento que se encuentra en la posición pos
     * @param pos Posición que se quiere buscar el elemento.
     * @return La posición del elemento
     * @throws Se lanza la excepción si no se encuentra la posición del elemento deseado
     */
    public boolean buscar( int elem )
    {
        return elementos.contains( new Integer( elem ) );
    }

    /**
     * Retorna el elemento que se encuentra en la posición pos
     * @param pos Posición que se quiere buscar el elemento.
     * @return La posición del elemento
     * @throws Se lanza la excepción si no se encuentra la posicion del elemento deseado
     */
    public int retornar( int pos ) throws NoExisteException
    {
        Integer elemento = null;
        try
        {
            elemento = ( Integer )elementos.get( new Integer( Math.abs( pos - 1 ) ) );
        }
        catch( IndexOutOfBoundsException e )
        {
            throw new NoExisteException( "No existe la posición que se quiere retornar" );
        }
        return elemento;
    }

    /**
     * Retorna el número de elementos de la bolsa
     * @return Longitud de la bolsa
     */
    public int darLongitud( )
    {
        return elementos.size( );
    }

    /**
     * Retorna el límite superior de la bolsa
     * @return Límite de la bolsa
     */
    public int darInferior( )
    {
        return inferior;
    }

    /**
     * Retorna el límite inferior de la bolsa
     * @return Límite inferior de la bolsa
     */
    public int darSuperior( )
    {
        return superior;
    }

    /**
     * Retorna el iterador de la bolsa
     */
    public IIteradorBolsa darIterador( )
    {
        IteradorSimple iterador = new IteradorSimple( elementos.size( ) );
        for( int i = 0; i < elementos.size( ); i++ )
        {
            iterador.agregar( ( Integer )elementos.get( i ) );
        }
        return iterador;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}