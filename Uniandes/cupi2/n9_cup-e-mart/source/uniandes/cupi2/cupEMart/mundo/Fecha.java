/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Muñoz - Oct 19, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.mundo;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Clase que representa la fecha de lanzamiento de una línea de producto
 */
public class Fecha implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versión para la serialización
     */
    private static final long serialVersionUID = -5583333349215053421L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Objeto de tipo calendar que maneja la fecha
     */
    private Calendar fecha;
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros. Inicializa los atributos con los parámetros
     * @param dia Día que fue lanzado la línea de producto. Entero entre 1 y 31 (dependiendo del mes)
     * @param mes Mes de lanzamiento de una línea de producto. Entero entre 1 y 12
     * @param anio Año de lanzamiento de la línea de producto. Entero mayor que cero
     */
    public Fecha( int dia, int mes, int anio )
    {
        fecha = Calendar.getInstance( );
        fecha.set( Calendar.DAY_OF_MONTH, dia );
        fecha.set( Calendar.MONTH, mes );
        fecha.set( Calendar.YEAR, anio );
    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que compara dos fechas para saber si es antes de la que se pasa por parámetro.<br>
     * Si la fecha es antes de la que pasa por parámetro el método retorna true.
     * @param nFecha Fecha a comparar diferente de null
     * @return True si la fecha ocurrió antes de la que se pasa por parámetro
     */
    public boolean esAntes( Fecha nFecha )
    {
        Calendar temp = Calendar.getInstance( );
        temp.set( Calendar.DAY_OF_MONTH, nFecha.darDia( ) );
        temp.set( Calendar.MONTH, nFecha.darMes( ) );
        temp.set( Calendar.YEAR, nFecha.darAnio( ) );
        return fecha.before( temp );
    }

    /**
     * Método que retorna el día
     * @return Entero entre 1 y 31 (dependiendo del mes)
     */
    private int darDia( )
    {
        return fecha.get( Calendar.DAY_OF_MONTH );
    }

    /**
     * Método que retorna el mes
     * @return Entero entre 1 y 12
     */
    private int darMes( )
    {
        return fecha.get( Calendar.MONTH );
    }

    /**
     * Método que retorna el año
     * @return Entero mayor que cero
     */
    private int darAnio( )
    {
        return fecha.get( Calendar.YEAR );
    }

    /**
     * Retorna la representación en String de la fecha
     * @param String que primero muestra el día, luego el mes y luego el año separados por guiones.
     */
    public String toString( )
    {
        return fecha.get( Calendar.DAY_OF_MONTH ) + " - " + fecha.get( Calendar.MONTH ) + " - " + fecha.get( Calendar.YEAR );
    }
}