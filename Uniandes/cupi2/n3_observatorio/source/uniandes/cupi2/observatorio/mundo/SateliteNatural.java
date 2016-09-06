/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: SateliteNatural.java,v 1.4 2007/06/28 22:59:38 camil-ji Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Mu�oz - 13-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.mundo;

/**
 * Clase que representa un sat�lite natural de un planeta.
 */
public class SateliteNatural
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Nombre del sat�lite natural
     */
    private String nombre;

    /**
     * La excentricidad del sat�lite natural
     */
    private double excentricidad;

    /**
     * La inclinaci�n del sat�lite natural
     */
    private double inclinacionOrbital;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros. Inicializa los atributos con los par�metros.
     * @param nombreSatelite Nombre del sat�lite natural. Diferente de null.
     * @param nExcentricidad Excentricidad del sat�lite natural. Real mayor a cero.
     * @param nInclinacion Inclinaci�n del sat�lite natural. Real mayor a cero.
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
     * Retorna el nombre del sat�lite natural.
     * @return Nombre del sat�lite natural. diferente de null.
     */
    public String obtenerNombre( )
    {
        return nombre;
    }

    /**
     * 
     * Retorna la excentricidad del sat�lite natural
     * @return Real mayor que cero.
     */
    public double obtenerExcentricidad( )
    {
        return excentricidad;
    }

    /**
     * Retorna la inclinaci�n del sat�lite natural
     * @return Real mayor que cero.
     */
    public double obtenerInclinacion( )
    {
        return inclinacionOrbital;
    }

    /**
     * Cambia la excentricidad del sat�lite natural
     * @param nExcentricidad Nueva excentricidad del sat�lite. Real mayor que cero
     */
    public void cambiarExcentricidad( double nExcentricidad )
    {
        excentricidad = nExcentricidad;
    }

    /**
     * Cambia la Inclinaci�n Orbital del sat�lite natural
     * @param nInclinacionOrbital Nueva Inclinaci�n Orbital del sat�lite. Real mayor que cero
     */
    public void cambiarInclinacionOrbital( double nInclinacionOrbital )
    {
        inclinacionOrbital = nInclinacionOrbital;
    }
}
