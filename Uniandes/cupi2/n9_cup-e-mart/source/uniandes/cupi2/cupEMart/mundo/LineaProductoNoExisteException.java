/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: LineaProductoNoExisteException.java,v 1.1 2007/02/27 15:57:18 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Mu�oz - Oct 23, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.mundo;

/**
 * Excepci�n que es lanzada cuando no se encuentra un producto
 */
public class LineaProductoNoExisteException extends Exception
{

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros de la clase
     * @param mensaje mensaje que quiere ser mostrado al usuario, diferente de null
     */
    public LineaProductoNoExisteException( String mensaje )
    {
        super( mensaje );
    }

}