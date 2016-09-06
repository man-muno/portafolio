/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VerticeYaExisteException.java,v 1.1 2006/04/14 23:30:12 cupi2ejemplos Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - Mar 28, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.grafo;

/**
 * Excepci�n utilizada para informar que el v�rtice especificado ya existe en el grafo
 */
@SuppressWarnings("serial")
public class VerticeYaExisteException extends Exception
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Identificador del v�rtice que ya existe en el grafo
     */
    private Object idVertice;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepci�n
     * @param mensaje Mensaje de error
     * @param id Identificador del v�rtice que ya existe
     */
    public VerticeYaExisteException( String mensaje, Object id )
    {
        super( mensaje );
        idVertice = id;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve el identificador del v�rtice que ya existe
     * @return identificador del v�rtice que ya existe
     */
    public Object darIdentificador( )
    {
        return idVertice;
    }
}
