/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelEliminarCiudad.java,v 1.2 2007/04/07 23:39:17 man-muno Exp $
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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import uniandes.cupi2.cupi2CruiseLines.mundo.Ciudad;

/**
 * Panel donde se piden los datos para eliminar una ciudad
 */
public class PanelEliminarCiudad extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Acción asociada al botón aceptar
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Acción asociada al botón cancelar
     */

    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Botón de aceptar
     */
    private JButton btnAceptar;

    /**
     * Botón de cancelar
     */
    private JButton btnCancelar;

    /**
     * Grupo para colocar todos los radio buttons y que solo se pueda escoger uno
     */
    private ButtonGroup grupo;

    /**
     * Instancia del dialogo padre.
     */
    private DialogoEliminarCiudad padre;

    /**
     * Panel donde se colocaran los JRadioButton seguido del nombre de la línea de producto
     */
    private JPanel panel;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros del panel.
     * @param dialogo Dialogo que contiene el panel. Diferente de null.
     * @param ciudades Lista que contiene las ciudades a ser eliminadas. Diferente de null pero puede ser vacía.
     */
    public PanelEliminarCiudad( DialogoEliminarCiudad dialogo, ArrayList ciudades )
    {
        padre = dialogo;

        setLayout( new GridBagLayout( ) );
        setPreferredSize( new Dimension( 190, 197 ) );
        GridBagConstraints constraint = new GridBagConstraints( );

        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;

        panel = new JPanel( );
        panel.setLayout( new GridBagLayout( ) );
        JScrollPane scroll = new JScrollPane( panel );
        scroll.setPreferredSize( new Dimension( 100, 150 ) );
        grupo = new ButtonGroup( );
        for( int i = 0; i < ciudades.size( ); i++ )
        {
            Ciudad ciudad = ( Ciudad )ciudades.get( i );
            JRadioButton radio = new JRadioButton( ciudad.darNombre( ) + " - " + ciudad.darPais( ) );
            grupo.add( radio );
            constraint = new GridBagConstraints( );
            constraint.gridx = 0;
            constraint.gridy = i;
            constraint.anchor = GridBagConstraints.WEST;
            constraint.insets = new Insets( 5, 0, 0, 0 );
            panel.add( radio, constraint );
        }

        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;

        add( scroll, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;

        JPanel panelBotones = new JPanel( );
        constraint = new GridBagConstraints( );
        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.addActionListener( this );
        btnAceptar.setActionCommand( ACEPTAR );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.weightx = 1;
        panelBotones.add( btnAceptar, constraint );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.weightx = 1;
        constraint.fill = GridBagConstraints.BOTH;
        panelBotones.add( btnCancelar, constraint );

        // Coloca el panel con los botones
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;

        add( panelBotones, constraint );

    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( ACEPTAR ) )
        {
            aceptar( );
            padre.dispose( );
        }
        else if( comando.equals( CANCELAR ) )
        {
            padre.dispose( );
        }
    }

    /**
     * Método llamado cuando el usuario decide eliminar una ciudad.
     */
    private void aceptar( )
    {
        String[] ciudad = obtenerCiudadSeleccionada( );
        if( ciudad == null )
            JOptionPane.showMessageDialog( this, "Debe seleccionar una ciudad", "Error", JOptionPane.ERROR_MESSAGE );
        else
            padre.eliminarCiudad( ciudad[ 0 ], ciudad[ 1 ] );
    }

    /**
     * Retorna el nombre y el país de la ciudad seleccionada
     * @return En la primera posición se encuentra el nombre de la ciudad y en la segunda el país.
     */
    public String[] obtenerCiudadSeleccionada( )
    {
        Object[] botones = panel.getComponents( );
        String[] ciudad = null;
        for( int i = 0; i < botones.length; i++ )
        {
            JRadioButton boton = ( JRadioButton )botones[ i ];
            String strCiudad = null;
            if( boton.isSelected( ) )
            {
                strCiudad = boton.getText( );
                ciudad = strCiudad.split( " - " );
            }
        }
        return ciudad;
    }
}
