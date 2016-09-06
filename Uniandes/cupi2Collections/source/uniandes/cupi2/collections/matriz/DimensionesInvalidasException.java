/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DimensionesInvalidasException.java,v 1.1 2008/04/07 01:37:46 jua-gome Exp $
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
 * Excepci�n que indica que las dimensiones de una matriz sin menores o iguales a 0.
 */
@SuppressWarnings("serial")
public class DimensionesInvalidasException extends Exception
{

    /**
     * Constructor de la clase.
     * @param filas N�mero de filas de la matriz.
     * @param columnas N�mero de columnas de la matriz.
     */
    public DimensionesInvalidasException( int filas, int columnas )
    {
        super( "La matriz no puede tener dimensiones [" + filas + "," + columnas + "]" );
    }
}
