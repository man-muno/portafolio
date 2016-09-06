/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CoordenadaFueraDeRangoException.java,v 1.1 2008/04/07 01:37:46 jua-gome Exp $
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
 * Excepci�n que indica que la coordenada pasada como par�metro est� fuera de rango.
 */
@SuppressWarnings("serial")
public class CoordenadaFueraDeRangoException extends RuntimeException
{
    /**
     * Constructor de la clase.
     * @param fila Fila de la coordenada invalida.
     * @param columna Columna de la coordenada inv�lida.
     */
    public CoordenadaFueraDeRangoException( int fila, int columna )
    {
        super( "Coordenada: [" + fila + "," + columna + "]" );
    }
}
