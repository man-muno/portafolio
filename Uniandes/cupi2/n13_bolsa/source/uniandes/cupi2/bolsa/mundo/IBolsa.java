/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IBolsa.java,v 1.9 2007/02/01 15:18:01 jvillalo2 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Jorge Villalobos - 23-ago-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.mundo;

/**
 * Interfaz que define las operaciones de una bolsa de valores numéricos, que se encuentran en un rango
 */
public interface IBolsa
{
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un elemento a la bolsa
     * @param elem Elemento que se va a agregar a la bolsa
     */
    public void agregar( int elem ) throws FueraLimiteException;

    /**
     * Elimina un elemento de la bolsa
     * @param elem Elemento que va a ser eliminado
     * @throws Se lanza la excepción si no se encuentra el elemento
     */
    public void eliminar( int elem ) throws NoExisteException;

    /**
     * Informa si existe un elemento dado dentro de la bolsa
     * @param elem Elemento que va a ser buscado
     * @return TRUE si encontró el elemento, FALSE de lo contrario
     */
    public boolean buscar( int elem );

    /**
     * Retorna el elemento que se encuentra en la posición pos de la bolsa, suponiendo que los elementos están situados ordenados de manera ascendentemente
     * @param pos Posición del elemento que se quiere buscar
     * @return Elemento que se encuentra en la posición pedida
     * @throws Se lanza la excepción si no se encuentra la posición pedida
     */
    public int retornar( int pos ) throws NoExisteException;

    /**
     * Retorna el número de elementos de la bolsa
     * @return Longitud de la bolsa
     */
    public int darLongitud( );

    /**
     * Retorna el límite inferior de la bolsa
     * @return Límite inferior de la bolsa
     */
    public int darInferior( );

    /**
     * Retorna el límite superior de la bolsa
     * @return Límite superior de la bolsa
     */
    public int darSuperior( );

    /**
     * Retorna un iterador sobre los elementos de la bolsa
     * @return Un iterador sobre los elementos de la bolsa
     */
    public IIteradorBolsa darIterador( );
}
