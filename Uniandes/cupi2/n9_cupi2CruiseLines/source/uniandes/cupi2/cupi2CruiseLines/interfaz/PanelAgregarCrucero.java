/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelAgregarCrucero.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
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
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

/**
 * Es el panel donde se ingresa la información para crear un nuevo crucero
 */
public class PanelAgregarCrucero extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el botón Agregar
     */
    private static final String AGREGAR = "Agregar";

    /**
     * Comando para el botón Cancelar
     */
    private static final String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta Nombre
     */
    private JLabel lblNombre;

    /**
     * Etiqueta numero de días
     */
    private JLabel lblNumDias;

    /**
     * Etiqueta Precio;
     */
    private JLabel lblPrecio;

    /**
     * Etiqueta Fecha:
     */
    private JLabel lblFecha;

    /**
     * Campo de texto donde se muestra el nombre del crucero
     */
    private JTextField txtNombre;

    /**
     * Campo de texto donde se ingresa el código del crucero
     */
    private JTextField txtNumDias;

    /**
     * Campo de texto donde se muestra el precio del crucero
     */
    private JTextField txtPrecio;

    /**
     * Calendario para seleccionar fecha de salida del crucero
     */
    private JCalendar calendarioFecha;

    /**
     * Botón usado para crear un crucero
     */
    private JButton botonAgregar;

    /**
     * Botón para cancelar la creación del crucero
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Dialogo que contiene este panel
     */
    private DialogoAgregarCrucero padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param dialogo Dialogo que contiene este panel
     */
    public PanelAgregarCrucero( DialogoAgregarCrucero dialogo )
    {
        padre = dialogo;
        setLayout( new GridBagLayout( ) );
        setPreferredSize( new Dimension( 477, 360 ) );

        // Construir e inicializar las etiquetas
        GridBagConstraints gbcE = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        lblNombre = new JLabel( "Nombre:" );
        add( lblNombre, gbcE );

        gbcE.gridy = 1;
        lblNumDias = new JLabel( "Duración (días):" );
        add( lblNumDias, gbcE );

        gbcE.gridy = 2;
        lblPrecio = new JLabel( "Precio por Persona (US$):" );
        add( lblPrecio, gbcE );

        gbcE.gridy = 3;
        lblFecha = new JLabel( "Fecha de Salida:" );
        add( lblFecha, gbcE );

        // Construir e inicializar los campos
        GridBagConstraints gbcC = new GridBagConstraints( 1, 0, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        txtNombre = new JTextField( );
        add( txtNombre, gbcC );

        gbcC.gridy = 1;
        txtNumDias = new JTextField( );
        add( txtNumDias, gbcC );

        gbcC.gridy = 2;
        txtPrecio = new JTextField( );
        add( txtPrecio, gbcC );

        gbcC.gridy = 3;
        calendarioFecha = new JCalendar( );
        add( calendarioFecha, gbcC );

        // Construir e inicializar los botones
        JPanel panelBotones = new JPanel( );

        botonAgregar = new JButton( "Aceptar" );
        botonAgregar.setActionCommand( AGREGAR );
        botonAgregar.addActionListener( this );
        panelBotones.add( botonAgregar );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );
        panelBotones.add( botonCancelar );

        GridBagConstraints gbcB = new GridBagConstraints( 0, 5, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( panelBotones, gbcB );
    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Es el método que se llama cuando de hace click sobre un botón
     * @param evento Es el evento de click sobre un botón
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( AGREGAR.equals( comando ) )
        {
            String nombre = txtNombre.getText( );
            String numDias = txtNumDias.getText( );
            String strPrecio = txtPrecio.getText( );

            if( nombre == null )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un nombre válido", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if( numDias == null )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar una duración válida", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if( strPrecio == null )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un precio válido", "Error", JOptionPane.ERROR_MESSAGE );
            }

            try
            {
                int precio = Integer.parseInt( strPrecio );
                int duracion = Integer.parseInt( numDias );
                if( precio <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "Debe ingresar un número mayor a cero para el precio", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else if( duracion <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "Debe ingresar un número mayor a cero para la duración", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    Date fecha = calendarioFecha.getDate( );
                    padre.agregarCrucero( nombre, duracion, precio, fecha );
                    padre.dispose( );
                }
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un precio válido", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( CANCELAR.equals( comando ) )
        {
            padre.dispose( );
        }
    }
}