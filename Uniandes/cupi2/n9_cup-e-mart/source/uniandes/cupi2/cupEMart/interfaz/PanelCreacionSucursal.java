/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Muñoz - Oct 25, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel donde están los elementos gráficos para la creación de una nueva sucursal
 */
public class PanelCreacionSucursal extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando que se le asigna al botón aceptar
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Comando que se le asigna al botón cancelar
     */
    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Etiqueta para indicar el espacio del nombre
     */
    private JLabel lblNombreSucursal;

    /**
     * Campo de texto para que se ingrese el nombre
     */
    private JTextField txtNombreSucursal;

    /**
     * Etiqueta para indicar el espacio para la dirección de la sucursal
     */
    private JLabel lblDireccionSucursal;

    /**
     * Cuadro de texto para que se ingrese la dirección de la sucursal
     */
    private JTextField txtDireccionSucursal;

    /**
     * Botón de aceptar
     */
    private JButton btnAceptar;

    /**
     * Botón de cancelar
     */
    private JButton btnCancelar;

    /**
     * Diálogo que contiene este panel
     */
    private DialogoCreacionSucursal padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros
     * @param sucursal Dialogo que contiene este panel
     */
    public PanelCreacionSucursal( DialogoCreacionSucursal sucursal )
    {
        padre = sucursal;
        initialize( );
    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Método que inicializa y coloca los elementos gráficos en este panel
     */
    private void initialize( )
    {
        setLayout( new GridBagLayout( ) );
        GridBagConstraints constraint = null;

        // Inicialización y ubicación de la etiqueta nombre
        lblNombreSucursal = new JLabel( "Nombre: " );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( lblNombreSucursal, constraint );

        // Inicialización y ubicación del cuadro de texto para el nombre
        txtNombreSucursal = new JTextField( );
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( txtNombreSucursal, constraint );

        // Inicialización y ubicación de la etiqueta dirección
        lblDireccionSucursal = new JLabel( "Dirección: " );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( lblDireccionSucursal, constraint );

        // Inicialización y ubicación del cuadro de texto para la dirección
        txtDireccionSucursal = new JTextField( );
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( txtDireccionSucursal, constraint );

        // Inicialización y ubicación del botón aceptar
        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.setActionCommand( ACEPTAR );
        btnAceptar.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( btnAceptar, constraint );

        // Inicialización y ubicación del botón aceptar
        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.insets = new Insets( 10, 10, 0, 0 );
        add( btnCancelar, constraint );

    }

    /**
     * Se ejecuta una acción cuando se hace click en uno de los botones
     * @param e El evento del click en un botón- evento != null
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( ACEPTAR ) )
        {
            agregarSucursal( );
        }
        else if( e.getActionCommand( ).equals( CANCELAR ) )
        {
            padre.dispose( );
        }
    }

    /**
     * Obtiene los datos de la interfaz gráfica, valida su forma y llama al padre para que los utilice
     */
    private void agregarSucursal( )
    {
        String nombre = txtNombreSucursal.getText( );
        String direccion = txtDireccionSucursal.getText( );
        if( nombre == null || nombre.length( ) == 0 )
        {
            JOptionPane.showMessageDialog( this, "Debe colocar un nombre a la sucursal", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else if( direccion == null || direccion.length( ) == 0 )
        {
            JOptionPane.showMessageDialog( this, "Debe colocar una dirección para la sucursal", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            padre.agregarSucursal( nombre, direccion );
            padre.dispose( );
        }
    }
}