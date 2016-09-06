/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterpolException.java,v 1.2 2007/04/20 12:38:41 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Muñoz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.interpol.mundo;

/**
 * Clase que representa una excepción lanzada cuando ocurre un error al cargar el mundo.
 */
public class InterpolException extends Exception
{
    /**
     * Constructor por parámetros.
     * @param mensaje Mensaje a mostrar
     */
    public InterpolException( String mensaje )
    {
        super( mensaje );
    }

}
