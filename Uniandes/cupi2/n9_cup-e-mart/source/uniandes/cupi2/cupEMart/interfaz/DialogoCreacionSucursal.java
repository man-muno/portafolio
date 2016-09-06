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
     * Panel que abri� el dialogo
     */
    private InterfazCupEMart padre;

    /**
     * Panel que contiene los elementos gr�ficos para la creaci�n de una nueva sucursal
     */
    private PanelCreacionSucursal panelCreacion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros
     * @param mart Panel que abre el dialogo
     */
    public DialogoCreacionSucursal( InterfazCupEMart interfazCupiEMart )
    {
        super( );
        padre = interfazCupiEMart;
        initialize( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que inicializa los atributos gr�ficos de la clase
     */
    private void initialize( )
    {
        setTitle( "Informaci�n de la Sucursal" );
        setResizable( false );
        setSize( new Dimension( 200, 150 ) );

        panelCreacion = new PanelCreacionSucursal( this );

        setContentPane( panelCreacion );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
    }

    /**
     * M�todo que es llamado por el panel cuando se quiere crear una sucursal
     * @param nombre Nombre que se le dar� a la nueva sucursal. Diferente de null
     * @param direccion Direcci�n que se le dar� a la nueva sucursal. Diferente de null
     */
    public void agregarSucursal( String nombre, String direccion )
    {
        padre.agregarSucursal( nombre, direccion );
    }
}