/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDescontinuarLineasProducto.java,v 1.1 2007/02/27 15:57:18 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Mu�oz - Oct 23, 2006
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
 * Panel donde se muestran las l�neas de producto de una sucursal usando JRadioButton para descontinuar una de ellas
 */
public class PanelDescontinuarLineasProducto extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Acci�n asociada al bot�n aceptar
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Acci�n asociada al bot�n cancelar
     */

    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n de aceptar
     */
    private JButton btnAceptar;

    /**
     * Bot�n de cancelar
     */
    private JButton btnCancelar;

    /**
     * Grupo para colocar todos los radio buttons y que solo se pueda escoger uno
     */
    private ButtonGroup grupo;

    /**
     * Panel donde se colocaran los JRadioButton seguido del nombre de la l�nea de producto
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
     * M�todo constructor por par�metros
     * @param lineas L�neas de producto que se quieren mostrar. Diferente de null pero puede ser vacia
     * @param productos Dialogo que contiene este panel
     */
    public PanelDescontinuarLineasProducto( ArrayList lineas, DialogoDescontinuarLineaProducto productos )
    {
        ventana = productos;

        setLayout( new GridBagLayout( ) );
        GridBagConstraints constraint = new GridBagConstraints( );

        // Coloca el scroll con las l�neas de productos
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
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Inicializa el JScrollPane y coloca la informaci�n de las l�neas de productos
     * @param lineas Lista con las l�neas que quieren ser mostradas
     * @return Scroll que contiene un panel que contiene las l�neas que quieren ser mostradas. Diferente de null
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
     * @param e Acci�n que gener� el evento.
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
     * M�todo que se ejecuta cuando se presiona el bot�n de aceptar
     */
    private void aceptar( )
    {
        String producto = obtenerCodigoLineaProductoSeleccionada( );
        if( producto == null )
            JOptionPane.showMessageDialog( this, "Debe seleccionar un producto", "Error", JOptionPane.ERROR_MESSAGE );
        ventana.descontinuarProducto( producto );
    }

    /**
     * Este m�todo retorna el c�digo de la l�nea de producto que fue seleccionada
     * @return Cadena de caracteres con el nombre de la l�nea de producto. Puede ser null en caso que no se haya seleccionado ninguna l�nea de producto
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