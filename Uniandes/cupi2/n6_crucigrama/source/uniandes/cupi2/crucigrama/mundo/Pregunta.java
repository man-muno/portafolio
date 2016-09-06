/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Pregunta.java,v 1.1 2007/03/22 14:05:58 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_crucigrama
 * Autor: Manuel Muñoz - 05-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.crucigrama.mundo;

public class Pregunta
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * Fila a la que corresponde la respuesta a la pregunta 
     */
    private int fila;
    
    /**
     * Fila a la que corresponde la respuesta a la pregunta
     */
    private int columna;
    
    /**
     * Descripción de la pregunta
     */
    private String descripcion;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor por parámetros.
     * @param fila2 fila donde pertenece la descripción de la pregunta
     * @param columna2  columna donde pertenece la descripción de la pregunta
     * @param descripcion2 Descripción de la pregunta
     */
    public Pregunta( int fila2, int columna2, String descripcion2 )
    {
        fila = fila2;
        columna = columna2;
        descripcion = descripcion2;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna un String compuesto por la fila y la columna a la cual pertenece la pregunta
     * @return Las coordenadas de la pregunta en un String. Diferente de null
     */
    public String obtenerCoordenada( )
    {
        return fila + ":" +columna;
    }


    /**
     * Retorna la descripción de la pregunta
     * @return La descripción de la pregunta. Diferente de null.
     */
    public String obtenerDescripcion( )
    {
        return descripcion;
    }
}
