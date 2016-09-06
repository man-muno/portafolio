/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Manuel Mu�oz - Aug 16, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.mundo.bolsa5;

import uniandes.cupi2.bolsa.mundo.FueraLimiteException;
import uniandes.cupi2.bolsa.mundo.IBolsa;
import uniandes.cupi2.bolsa.mundo.IIteradorBolsa;
import uniandes.cupi2.bolsa.mundo.NoExisteException;

/**
 * Implementaci�n de una bolsa de valores num�ricos
 */
public class Bolsa5 implements IBolsa
{

    /**
     * L�mite inferior de la bolsa
     */
    private int inferior;

    /**
     * L�mite superior de la bolsa
     */
    private int superior;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros
     */
    public Bolsa5( int inf, int sup )
    {

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

    }

    /**
     * Elimina el primer elemento de la bolsa
     * @param elem Elemento que quiere ser eliminado
     * @throws Se lanza la excepci�n si no se encuentra el elemento
     */
    public void eliminar( int elem ) throws NoExisteException
    {

    }

    /**
     * Busca si existe el elemento dentro de la bolsa
     * @param elem Elemento que quiere ser buscado
     * @return TRUE si encontr� el elemento, FALSE de lo contrario
     */
    public boolean buscar( int elem )
    {
        return false;
    }

    /**
     * Retorna el elemento que se encuentra en la posici�n pos
     * @param pos Posici�n que se quiere buscar el elemento.
     * @return La posici�n del elemento
     * @throws Se lanza la excepci�n si no se encuentra la posicion del elemento deseado
     */
    public int retornar( int pos ) throws NoExisteException
    {
        return -1;
    }

    /**
     * Retorna el n�mero de elementos de la bolsa
     * @return Longitud de la bolsa
     */
    public int darLongitud( )
    {
        return -1;
    }

    /**
     * Retorna el l�mite superior de la bolsa
     * @return L�mite de la bolsa
     */
    public int darInferior( )
    {
        return -1;
    }

    /**
     * Retorna el l�mite inferior de la bolsa
     * @return L�mite inferior de la bolsa
     */
    public int darSuperior( )
    {
        return -1;
    }

    /**
     * Retorna un iterador sobre la bolsa
     */
    public IIteradorBolsa darIterador( )
    {
        return null;
    }
}
