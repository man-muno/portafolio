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

package uniandes.cupi2.bolsa.mundo.bolsa3;

import uniandes.cupi2.bolsa.mundo.FueraLimiteException;
import uniandes.cupi2.bolsa.mundo.IBolsa;
import uniandes.cupi2.bolsa.mundo.IIteradorBolsa;
import uniandes.cupi2.bolsa.mundo.IteradorSimple;
import uniandes.cupi2.bolsa.mundo.NoExisteException;

/**
 * Implementaci�n de una bolsa de valores num�ricos
 */
public class Bolsa3 implements IBolsa
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Arreglo que contiene los elementos de la bolsa
     */
    private int[] elementos;

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
    public Bolsa3(int inf, int sup)
    {
        inferior = inf;
        superior = sup;
        elementos = new int[sup - inf + 1];
        for( int i = 0; i < elementos.length; i++ )
        {
            elementos[i] = 0;
        }
        cantidadElementos = 0;
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
            for( int i = 0; i < superior - inferior + 1; i++ )
            {
                if( inferior + i + 1 == elem )
                    elementos[i] = elementos[i] + 1;
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
        boolean eliminado = false;
        for( int i = 0; i < superior - inferior + 1; i++ )
        {
            if( inferior + i + 1 == elem )
            {
                elementos[i] = elementos[i] - 1;
                eliminado = true;
                cantidadElementos--;
            }
        }
        if( !eliminado )
        {
            throw new NoExisteException( "El elemento a eliminar no existe" );
        }
    }

    /**
     * Busca si existe el elemento dentro de la bolsa
     * @param elem Elemento que quiere ser buscado
     * @return TRUE si encontr� el elemento, FALSE de lo contrario
     */
    public boolean buscar( int elem )
    {
        boolean encontrado = false;
        for( int i = 0; i < superior - inferior + 1; i++ )
        {
            if( inferior + i + 1 == elem )
            {
                if( elementos[i] != 0 )
                    encontrado = true;
            }
        }
        return encontrado;
    }

    /**
     * Retorna el elemento que se encuentra en la posici�n pos
     * @param pos Posici�n que se quiere buscar el elemento.
     * @return La posici�n del elemento
     * @throws Se lanza la excepci�n si no se encuentra la posici�n del elemento deseado
     */
    public int retornar( int pos ) throws NoExisteException
    {
        if( pos < inferior || pos > superior )
        {
            throw new NoExisteException( "No existe el elemento" );
        }
        return elementos[pos - 1];
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
        for( int i = 0; i < elementos.length; i++ )
        {
            for( int j = 0; j < elementos[i]; j++ )
            {
                iterador.agregar( i + inferior + 1 );
            }
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