/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DimensionesInvalidasException.java,v 1.1 2008/04/07 01:37:46 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Abr 1, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.matriz;

/**
 * Excepción que indica que las dimensiones de una matriz sin menores o iguales a 0.
 */
@SuppressWarnings("serial")
public class DimensionesInvalidasException extends Exception
{

    /**
     * Constructor de la clase.
     * @param filas Número de filas de la matriz.
     * @param columnas Número de columnas de la matriz.
     */
    public DimensionesInvalidasException( int filas, int columnas )
    {
        super( "La matriz no puede tener dimensiones [" + filas + "," + columnas + "]" );
    }
}
