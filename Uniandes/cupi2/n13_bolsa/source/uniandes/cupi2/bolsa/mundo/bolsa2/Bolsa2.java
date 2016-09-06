/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Jorge Villalobos - 23-ago-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.mundo.bolsa2;

import uniandes.cupi2.bolsa.mundo.FueraLimiteException;
import uniandes.cupi2.bolsa.mundo.IBolsa;
import uniandes.cupi2.bolsa.mundo.IIteradorBolsa;
import uniandes.cupi2.bolsa.mundo.IteradorSimple;
import uniandes.cupi2.bolsa.mundo.NoExisteException;

/**
 * Implementaci�n de una bolsa de valores num�ricos
 */
public class Bolsa2 implements IBolsa
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista encadenada que contiene los elementos de la bolsa
     */
    private Grupo primerElemento;

    /**
     * L�mite inferior de la bolsa
     */
    private int inferior;

    /**
     * L�mite superior de la bolsa
     */
    private int superior;

    /**
     * Cantidad de elementos que tiene la bolsa
     */
    private int cantidadElementos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros
     */
    public Bolsa2(int inf, int sup)
    {
        primerElemento = null;
        cantidadElementos = 0;
        inferior = inf;
        superior = sup;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo elemento a la bolsa
     * @param elem Elemento que quiere ser agregado a la bolsa
     */
    public void agregar( int elem ) throws FueraLimiteException
    {
        if( elem > inferior && elem < superior )
        {
            Grupo nodo = new Grupo( elem );
            if( primerElemento == null )
            {
                primerElemento = nodo;
            }
            else if( nodo.obtenerValor( ) < primerElemento.obtenerValor( ) )
            {
                // Debe quedar como primer elemento de la lista
                primerElemento.insertarAntes( nodo );
                primerElemento = nodo;
            }
            else
            {
                Grupo temp = primerElemento;
                boolean insertado = false;
                while( !insertado )
                {
                    if( temp.obtenerValor( ) == nodo.obtenerValor( ) )
                    {
                        temp.aumentarVeces( );
                        insertado = true;
                    }
                    else if( temp.obtenerSiguiente( ) == null )
                    {
                        temp.insertarDespues( nodo );
                        insertado = true;
                    }
                    else if( temp.obtenerSiguiente( ).obtenerValor( ) > nodo.obtenerValor( ) )
                    {
                        temp.insertarDespues( nodo );
                        insertado = true;
                    }
                    else
                    {
                        temp = ( Grupo )temp.obtenerSiguiente( );
                    }
                }
            }
            cantidadElementos++;
        }
        else
        {
            throw new FueraLimiteException( "Elemento fuera de rango" );
        }
    }

    /**
     * Elimina el primer elemento de la bolsa
     * @param elem Elemento que quiere ser eliminado
     * @throws Se lanza la excepci�n si no se encuentra el elemento
     */
    public void eliminar( int elem ) throws NoExisteException
    {
        Grupo elemento = new Grupo( elem );
        if( primerElemento == null )
        {
            throw new NoExisteException( "Elemento no existe" );
        }
        else if( elemento.obtenerValor( ) == primerElemento.obtenerValor( ) )
        {
            // Se debe eliminar el primer elemento de la lista
            primerElemento.disminuirVeces( );
            if( primerElemento.obtenerVeces( ) == 0 )
            {
                Grupo temp = ( Grupo )primerElemento.obtenerSiguiente( );
                primerElemento.desconectarPrimero( );
                primerElemento = temp;
            }
            cantidadElementos--;
            return;
        }
        else
        {
            for( Grupo p = ( Grupo )primerElemento.obtenerSiguiente( ); p != null; p = ( Grupo )p.obtenerSiguiente( ) )
            {
                if( p.obtenerValor( ) == elemento.obtenerValor( ) )
                {
                    p.disminuirVeces( );
                    cantidadElementos--;
                    if( p.obtenerVeces( ) == 0 )
                    {
                        p.desconectarNodo( );
                    }
                    return;
                }
            }
        }
        throw new NoExisteException( "Elemento no existe" );
    }

    /**
     * Busca si existe el elemento dentro de la bolsa
     * @param elem Elemento que quiere ser buscado
     * @return TRUE si encontr� el elemento, FALSE de lo contrario
     */
    public boolean buscar( int elem )
    {
        Grupo elemento = new Grupo( elem );
        for( Grupo p = primerElemento; p != null; p = ( Grupo )p.obtenerSiguiente( ) )
        {
            if( p.obtenerValor( ) == elemento.obtenerValor( ) )
                return true;
        }
        return false;
    }

    /**
     * Retorna el elemento que se encuentra en la posici�n pos
     * @param pos Posici�n que se quiere buscar el elemento.
     * @return La posici�n del elemento
     * @throws Se lanza la excepci�n si no se encuentra la posici�n del elemento deseado
     */
    public int retornar( int pos ) throws NoExisteException
    {
        int valor = 0;
        if( pos > cantidadElementos )
        {
            throw new NoExisteException( "La posici�n que desea se encuentra fuera del rango" );
        }
        else
        {
            int contador = 0;
            Grupo temp = primerElemento;
            boolean terminado = false;
            while( temp != null && !terminado )
            {
                contador += temp.obtenerVeces( );
                if( contador >= pos )
                {
                    valor = temp.obtenerValor( );
                    terminado = true;
                }
                else
                {
                    temp = ( Grupo )temp.obtenerSiguiente( );
                }
            }
        }
        return valor;
    }

    /**
     * Retorna el n�mero de elementos de la bolsa
     * @return Longitud de la bolsa
     */
    public int darLongitud( )
    {
        return cantidadElementos;
    }

    /**
     * Retorna el l�mite superior de la bolsa
     * @return L�mite de la bolsa
     */
    public int darInferior( )
    {
        return inferior;
    }

    /**
     * Retorna el l�mite inferior de la bolsa
     * @return L�mite inferior de la bolsa
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
        IteradorSimple iterador = new IteradorSimple( darLongitud( ) );
        Grupo temp = primerElemento;
        while( temp != null )
        {
            for( int i = 0; i < temp.obtenerVeces( ); i++ )
            {
                iterador.agregar( temp.obtenerValor( ) );
            }
            temp = ( Grupo )temp.obtenerSiguiente( );
        }
        return iterador;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}