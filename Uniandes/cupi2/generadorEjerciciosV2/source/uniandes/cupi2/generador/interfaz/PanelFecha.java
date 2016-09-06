/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 
 * Ejercicio: generadorEjerciciosV2
 * Autor: Pablo Barvo - Sep 2, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.generador.interfaz;

import java.util.Date;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;

/**
 * Panel donde se puede seleccionar la fecha de generación del ejercicio.
 */
public class PanelFecha extends JPanel
{

    //-----------------------------------------------------------------
    // Atributos de Interfaz
    //-----------------------------------------------------------------

    /**
     * Selector de fechas
     */
    private JCalendar calendario;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor sin parametros
     */
    public PanelFecha( )
    {
        setBorder( new TitledBorder( "Fecha de creación" ) );
        calendario = new JCalendar( );
        add( calendario );
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Devuelve la fecha seleccionada
     * @return Fecha selecionada
     */
    public Date darFercha( )
    {
        return calendario.getDate( );
    }

}
