/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NoExisteException.java,v 1.2 2006/04/13 14:36:27 cupi2ejemplos Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 3, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.listaEncadenadaOrdenada;

/**
 * Excepci�n generada cuando un elemento de la lista no es encontrado
 */
@SuppressWarnings("serial")
public class NoExisteException extends Exception
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Constructor con mensaje
     * @param mensaje Mensaje de error
     */
    public NoExisteException( String mensaje )
    {
        super( mensaje );
    }
}