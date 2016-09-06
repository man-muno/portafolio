/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PersistenciaException.java,v 1.2 2007/02/27 21:04:15 f-vela Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Milena Vela - 25-ago-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.mundo;

/**
 * Esta excepci�n se lanza cuando se presenta un error al leer o escribir el archivo con la informaci�n del estado del modelo del mundo. <br>
 * El mensaje asociado con la excepci�n debe describir el problema que se present�.
 */
public class PersistenciaException extends Exception
{
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye la excepci�n con el mensaje que se pasa como par�metro y que describe la causa del problema
     * 
     * @param causa el mensaje que describe el problema
     */
    public PersistenciaException( String causa )
    {
        super( causa );
    }

}
