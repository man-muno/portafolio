/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Mu�oz - Oct 19, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.mundo;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Clase que representa la fecha de lanzamiento de una l�nea de producto
 */
public class Fecha implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versi�n para la serializaci�n
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
     * Constructor por par�metros. Inicializa los atributos con los par�metros
     * @param dia D�a que fue lanzado la l�nea de producto. Entero entre 1 y 31 (dependiendo del mes)
     * @param mes Mes de lanzamiento de una l�nea de producto. Entero entre 1 y 12
     * @param anio A�o de lanzamiento de la l�nea de producto. Entero mayor que cero
     */
    public Fecha( int dia, int mes, int anio )
    {
        fecha = Calendar.getInstance( );
        fecha.set( Calendar.DAY_OF_MONTH, dia );
        fecha.set( Calendar.MONTH, mes );
        fecha.set( Calendar.YEAR, anio );
    }
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que compara dos fechas para saber si es antes de la que se pasa por par�metro.<br>
     * Si la fecha es antes de la que pasa por par�metro el m�todo retorna true.
     * @param nFecha Fecha a comparar diferente de null
     * @return True si la fecha ocurri� antes de la que se pasa por par�metro
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
     * M�todo que retorna el d�a
     * @return Entero entre 1 y 31 (dependiendo del mes)
     */
    private int darDia( )
    {
        return fecha.get( Calendar.DAY_OF_MONTH );
    }

    /**
     * M�todo que retorna el mes
     * @return Entero entre 1 y 12
     */
    private int darMes( )
    {
        return fecha.get( Calendar.MONTH );
    }

    /**
     * M�todo que retorna el a�o
     * @return Entero mayor que cero
     */
    private int darAnio( )
    {
        return fecha.get( Calendar.YEAR );
    }

    /**
     * Retorna la representaci�n en String de la fecha
     * @param String que primero muestra el d�a, luego el mes y luego el a�o separados por guiones.
     */
    public String toString( )
    {
        return fecha.get( Calendar.DAY_OF_MONTH ) + " - " + fecha.get( Calendar.MONTH ) + " - " + fecha.get( Calendar.YEAR );
    }
}