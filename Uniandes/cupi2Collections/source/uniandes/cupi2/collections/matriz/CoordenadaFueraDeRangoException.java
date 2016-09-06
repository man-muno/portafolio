/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CoordenadaFueraDeRangoException.java,v 1.1 2008/04/07 01:37:46 jua-gome Exp $
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
 * Excepción que indica que la coordenada pasada como parámetro está fuera de rango.
 */
@SuppressWarnings("serial")
public class CoordenadaFueraDeRangoException extends RuntimeException
{
    /**
     * Constructor de la clase.
     * @param fila Fila de la coordenada invalida.
     * @param columna Columna de la coordenada inválida.
     */
    public CoordenadaFueraDeRangoException( int fila, int columna )
    {
        super( "Coordenada: [" + fila + "," + columna + "]" );
    }
}
