/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoPistas.java,v 1.3 2007/04/20 12:38:40 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Muñoz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.interpol.interfaz;

import java.util.List;

import javax.swing.JDialog;

/**
 * Dialogo que contiene las pistas.
 */
public class DialogoPistas extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel donde se muestran las pistas.
     */
    private PanelPistas panelPistas;

    /**
     * Ventana principal de la aplicación
     */
    private InterfazInterpol principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros.
     * @param interpol Interfaz principal de la aplicación. Diferente de null.
     * @param lugares Lista de los lugares donde hay una pista
     */
    public DialogoPistas( InterfazInterpol interpol, List lugares )
    {
        principal = interpol;

        setTitle( "Lugares" );
        panelPistas = new PanelPistas( this, lugares );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        add( panelPistas );
        pack( );
        setLocationRelativeTo( this );
        setVisible( true );
    }

    /**
     * Llama a la ventana principal para que muestre una pista de un lugar.
     * @param nombreLugar Nombre del lugar donde se quiere mostrar la pista. Diferente de null.
     */
    public void mostrarPista( String nombreLugar )
    {
        principal.mostrarPista( nombreLugar );
    }

}
