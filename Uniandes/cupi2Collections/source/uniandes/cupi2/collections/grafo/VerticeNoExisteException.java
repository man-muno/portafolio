/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - Mar 28, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.grafo;

/**
 * Excepción utilizada para informar que el vértice especificado no existe en el grafo
 */
@SuppressWarnings("serial")
public class VerticeNoExisteException extends Exception
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Identificador del vértice buscado
     */
    private Object idVertice;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepción
     * @param mensaje Mensaje de error
     * @param id Identificador del vértice no existente
     */
    public VerticeNoExisteException( String mensaje, Object id )
    {
        super( mensaje );
        idVertice = id;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el identificador del vértice no existente
     * @return identificador del vértice no existente
     */
    public Object darIdentificador( )
    {
        return idVertice;
    }
}
