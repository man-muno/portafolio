/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoEliminarCiudad.java,v 1.2 2007/04/07 23:39:17 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupi2CruiseLines
 * Autor: Manuel Muñoz - 13-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupi2CruiseLines.interfaz;

import java.util.ArrayList;

import javax.swing.JDialog;

/**
 * Diálogo donde se muestran las ciudades para ser eliminadas
 */
public class DialogoEliminarCiudad extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel donde se muestran las ciudades a eliminar
     */
    private PanelEliminarCiudad panelEliminarCiudad;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazCupi2CruiseLines principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros.
     * @param lines Interfaz principal de la aplicación. Diferente de null.
     * @param ciudades Lista de las ciudades del crucero. Diferente null pero puede ser vacía.
     */
    public DialogoEliminarCiudad( InterfazCupi2CruiseLines lines, ArrayList ciudades )
    {
        principal = lines;
        setTitle( "Eliminar una Ciudad" );
        panelEliminarCiudad = new PanelEliminarCiudad( this, ciudades );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        add( panelEliminarCiudad );
        pack( );
        setLocationRelativeTo( this );
        setVisible( true );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Llama el método de la interfaz para eliminar la ciudad.
     * @param nombreCiudad Nombre de la ciudad a eliminar. Diferente de null.
     * @param pais Nombre del país donde se encuentra la ciudad. Diferente de null.
     */
    public void eliminarCiudad( String nombreCiudad, String pais )
    {
        principal.eliminarCiudad( nombreCiudad, pais );
    }
}
