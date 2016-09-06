/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IMatrizVariable.java,v 1.1 2008/04/07 01:37:46 jua-gome Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo G�mez - Abr 1, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.matriz;

/**
 * Representa una matriz cuyas dimensiones son variables.
 * @param <T> Tipo de datos a guardar en la matriz.
 * @see IMatriz
 */
public interface IMatrizVariable<T> extends IMatriz<T>
{

    /**
     * Aumenta el n�mero de filas de la matriz.
     * </p>
     * Este aumento puede ser negativo. En ese caso se perderan todos los datos que se salgan del nuevo rango de la matriz.
     * @param aumento N�mero de filas a agregar/disminuir en la matriz.
     * @throws DimensionesInvalidasException si la matriz quedase con dimensiones invalidas (<=0) en caso de efectuar el aumento.
     */
    public void aumentarFilas( int aumento ) throws DimensionesInvalidasException;

    /**
     * Aumenta el n�mero de columnas de la matriz.
     * </p>
     * Este aumento puede ser negativo. En ese caso se perderan todos los datos que se salgan del nuevo rango de la matriz.
     * @param aumento N�mero de columnas a agregar/disminuir en la matriz.
     * @throws DimensionesInvalidasException si la matriz quedase con dimensiones invalidas (<=0) en caso de efectuar el aumento.
     */
    public void aumentarColumnas( int aumento ) throws DimensionesInvalidasException;

}
