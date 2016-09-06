/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: SateliteNatural.java,v 1.4 2007/06/28 22:59:38 camil-ji Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Muñoz - 13-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.mundo;

/**
 * Clase que representa un satélite natural de un planeta.
 */
public class SateliteNatural
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Nombre del satélite natural
     */
    private String nombre;

    /**
     * La excentricidad del satélite natural
     */
    private double excentricidad;

    /**
     * La inclinación del satélite natural
     */
    private double inclinacionOrbital;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros. Inicializa los atributos con los parámetros.
     * @param nombreSatelite Nombre del satélite natural. Diferente de null.
     * @param nExcentricidad Excentricidad del satélite natural. Real mayor a cero.
     * @param nInclinacion Inclinación del satélite natural. Real mayor a cero.
     */
    public SateliteNatural( String nombreSatelite, double nExcentricidad, double nInclinacion )
    {
        nombre = nombreSatelite;
        excentricidad = nExcentricidad;
        inclinacionOrbital = nInclinacion;
    }

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del satélite natural.
     * @return Nombre del satélite natural. diferente de null.
     */
    public String obtenerNombre( )
    {
        return nombre;
    }

    /**
     * 
     * Retorna la excentricidad del satélite natural
     * @return Real mayor que cero.
     */
    public double obtenerExcentricidad( )
    {
        return excentricidad;
    }

    /**
     * Retorna la inclinación del satélite natural
     * @return Real mayor que cero.
     */
    public double obtenerInclinacion( )
    {
        return inclinacionOrbital;
    }

    /**
     * Cambia la excentricidad del satélite natural
     * @param nExcentricidad Nueva excentricidad del satélite. Real mayor que cero
     */
    public void cambiarExcentricidad( double nExcentricidad )
    {
        excentricidad = nExcentricidad;
    }

    /**
     * Cambia la Inclinación Orbital del satélite natural
     * @param nInclinacionOrbital Nueva Inclinación Orbital del satélite. Real mayor que cero
     */
    public void cambiarInclinacionOrbital( double nInclinacionOrbital )
    {
        inclinacionOrbital = nInclinacionOrbital;
    }
}
