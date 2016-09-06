/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IndiceFueraDeRango.java,v 1.1 2006/09/25 04:49:19 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 13, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.bitArray;

/**
 * Excepci�n que indica que el �ndice pasado como par�metro est� fuera de rango
 */
@SuppressWarnings("serial")
public class IndiceFueraDeRango extends RuntimeException
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepci�n
     * @param valor Valor fuera de rango
     */
    public IndiceFueraDeRango( int valor )
    {
        super( "�ndice: " + valor );
    }
}
