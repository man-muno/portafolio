/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDescontinuarLineasProducto.java,v 1.1 2007/02/27 15:57:18 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Muñoz - Oct 23, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.interfaz;

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

import uniandes.cupi2.cupEMart.mundo.LineaProducto;

/**
 * Panel donde se muestran las líneas de producto de una sucursal usando JRadioButton para descontinuar una de ellas
 */
public class PanelDescontinuarLineasProducto extends JPanel implements ActionListener
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
     * Panel donde se colocaran los JRadioButton seguido del nombre de la línea de producto
     */
    private JPanel panel;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Dialogo que contiene este panel
     */
    private DialogoDescontinuarLineaProducto ventana;

    /**
     * Método constructor por parámetros
     * @param lineas Líneas de producto que se quieren mostrar. Diferente de null pero puede ser vacia
     * @param productos Dialogo que contiene este panel
     */
    public PanelDescontinuarLineasProducto( ArrayList lineas, DialogoDescontinuarLineaProducto productos )
    {
        ventana = productos;

        setLayout( new GridBagLayout( ) );
        GridBagConstraints constraint = new GridBagConstraints( );

        // Coloca el scroll con las líneas de productos
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        add( obtenerScrollLineas( lineas ), constraint );

        // Coloca el panel con los botones
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        add( obtenerPanelBotones( ), constraint );

    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Inicializa el JScrollPane y coloca la información de las líneas de productos
     * @param lineas Lista con las líneas que quieren ser mostradas
     * @return Scroll que contiene un panel que contiene las líneas que quieren ser mostradas. Diferente de null
     */
    private JScrollPane obtenerScrollLineas( ArrayList lineas )
    {
        panel = new JPanel( );
        panel.setLayout( new GridBagLayout( ) );
        JScrollPane scroll = new JScrollPane( panel );
        setPreferredSize( new Dimension( 100, 150 ) );
        scroll.setPreferredSize( new Dimension( 100, 150 ) );
        setLayout( new GridBagLayout( ) );
        grupo = new ButtonGroup( );
        for( int i = 0; i < lineas.size( ); i++ )
        {
            JRadioButton radio = new JRadioButton( ( ( LineaProducto )lineas.get( i ) ).toString( ) );
            grupo.add( radio );
            GridBagConstraints constraint = new GridBagConstraints( );
            constraint.gridx = 0;
            constraint.gridy = i;
            constraint.anchor = GridBagConstraints.WEST;
            constraint.insets = new Insets( 5, 0, 0, 0 );
            panel.add( radio, constraint );
        }
        return scroll;
    }

    /**
     * Crea e instancia un panel donde se colocan los botones
     * @return Panel con los botones
     */
    private JPanel obtenerPanelBotones( )
    {
        JPanel panel = new JPanel( );
        GridBagConstraints constraint = new GridBagConstraints( );
        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.addActionListener( this );
        btnAceptar.setActionCommand( ACEPTAR );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.weightx = 1;
        panel.add( btnAceptar, constraint );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.weightx = 1;
        constraint.fill = GridBagConstraints.BOTH;
        panel.add( btnCancelar, constraint );

        return panel;
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( ACEPTAR ) )
        {
            aceptar( );
            ventana.dispose( );
        }
        else
        {
            ventana.dispose( );
        }

    }

    /**
     * Método que se ejecuta cuando se presiona el botón de aceptar
     */
    private void aceptar( )
    {
        String producto = obtenerCodigoLineaProductoSeleccionada( );
        if( producto == null )
            JOptionPane.showMessageDialog( this, "Debe seleccionar un producto", "Error", JOptionPane.ERROR_MESSAGE );
        ventana.descontinuarProducto( producto );
    }

    /**
     * Este método retorna el código de la línea de producto que fue seleccionada
     * @return Cadena de caracteres con el nombre de la línea de producto. Puede ser null en caso que no se haya seleccionado ninguna línea de producto
     */
    public String obtenerCodigoLineaProductoSeleccionada( )
    {
        Object[] botones = panel.getComponents( );
        String codigoProducto = null;
        for( int i = 0; i < botones.length; i++ )
        {
            JRadioButton boton = ( JRadioButton )botones[ i ];
            if( boton.isSelected( ) )
                codigoProducto = boton.getText( );
            codigoProducto = codigoProducto.substring( 0, codigoProducto.indexOf( " - " ) );
        }
        return codigoProducto;
    }
}