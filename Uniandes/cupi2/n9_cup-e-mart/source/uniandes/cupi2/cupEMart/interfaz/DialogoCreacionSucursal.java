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

package uniandes.cupi2.cupEMart.interfaz;

import java.awt.Dimension;

import javax.swing.JDialog;

/**
 * Clase que representa el dialogo que se muestra cuando se desea crear una nueva sucursal
 */
public class DialogoCreacionSucursal extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que abrió el dialogo
     */
    private InterfazCupEMart padre;

    /**
     * Panel que contiene los elementos gráficos para la creación de una nueva sucursal
     */
    private PanelCreacionSucursal panelCreacion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros
     * @param mart Panel que abre el dialogo
     */
    public DialogoCreacionSucursal( InterfazCupEMart interfazCupiEMart )
    {
        super( );
        padre = interfazCupiEMart;
        initialize( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que inicializa los atributos gráficos de la clase
     */
    private void initialize( )
    {
        setTitle( "Información de la Sucursal" );
        setResizable( false );
        setSize( new Dimension( 200, 150 ) );

        panelCreacion = new PanelCreacionSucursal( this );

        setContentPane( panelCreacion );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
    }

    /**
     * Método que es llamado por el panel cuando se quiere crear una sucursal
     * @param nombre Nombre que se le dará a la nueva sucursal. Diferente de null
     * @param direccion Dirección que se le dará a la nueva sucursal. Diferente de null
     */
    public void agregarSucursal( String nombre, String direccion )
    {
        padre.agregarSucursal( nombre, direccion );
    }
}