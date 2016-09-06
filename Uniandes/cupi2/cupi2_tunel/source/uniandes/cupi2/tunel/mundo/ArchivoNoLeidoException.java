/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupi2_tunel
 * Autor: Manuel Mu�oz - Nov 22, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.tunel.mundo;

/**
 * Excepci�n lanzada cuando hay problemas de lectura del archivo de propiedades
 */
public class ArchivoNoLeidoException extends Exception
{
    /**
     * Constructor de la clase
     * @param string Mensaje que quiere ser mostrado
     */
    public ArchivoNoLeidoException( String string )
    {
        super(string);
    }

}
