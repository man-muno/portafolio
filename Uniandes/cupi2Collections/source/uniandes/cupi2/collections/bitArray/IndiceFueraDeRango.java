/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IndiceFueraDeRango.java,v 1.1 2006/09/25 04:49:19 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 13, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.bitArray;

/**
 * Excepción que indica que el índice pasado como parámetro está fuera de rango
 */
@SuppressWarnings("serial")
public class IndiceFueraDeRango extends RuntimeException
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepción
     * @param valor Valor fuera de rango
     */
    public IndiceFueraDeRango( int valor )
    {
        super( "Índice: " + valor );
    }
}
