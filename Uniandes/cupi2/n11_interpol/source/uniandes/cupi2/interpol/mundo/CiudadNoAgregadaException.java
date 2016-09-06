/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CiudadNoAgregadaException.java,v 1.2 2007/07/06 01:44:42 camil-ji Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Mu�oz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.interpol.mundo;

/**
 * Clase que representa una excepci�n lanzada cuando ocurre un error al agregar una ciudad.
 */
public class CiudadNoAgregadaException extends Exception
{
    /**
     * Constructor por par�metros.
     * @param mensaje Mensaje a mostrar
     */
    public CiudadNoAgregadaException( String mensaje )
    {
        super( mensaje );
    }
}
