/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: LineaProductoExisteException.java,v 1.1 2007/02/27 15:57:18 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Muñoz - Oct 24, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.mundo;

/**
 * Excepción que es lanzada cuando se quiere agregar un producto con el mismo código de uno que ya existe
 */
public class LineaProductoExisteException extends Exception
{

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Método constructor por parámetros
     * @param mensaje Mensaje que quiere mostrarse al usuario. Diferente de null
     */
    public LineaProductoExisteException( String mensaje )
    {
        super( mensaje );
    }
}