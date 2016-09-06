/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelMascota.java,v 1.3 2007/06/25 18:47:35 camil-ji Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_tiendaMascotas
 * Autor: Manuel Muñoz - 15-Sep-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.tiendaMascotas.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.tiendaMascotas.mundo.Mascota;

/**
 * Panel para mostrar la información de una mascota
 */
public class PanelMascota extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el botón botonVender
     */
    private static final String VENDER = "VENDER";

    /**
     * Comando para el botón botonComprar
     */
    private static final String COMPRAR = "COMPRAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Identificador de la mascota
     */
    private String nombreMascota;

    /**
     * Ventana principal de la aplicación
     */
    private InterfazTiendaMascotas principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta con el nombre de la mascota
     */
    private JLabel lblNombre;

    /**
     * Etiqueta con la imagen de la mascota
     */
    private JLabel lblImagen;

    /**
     * Etiqueta de la edad
     */
    private JLabel lblEdad;

    /**
     * Etiqueta del precio de la mascota
     */
    private JLabel lblPrecio;

    /**
     * Etiqueta de la cantidad disponible de mascotas
     */
    private JLabel lblCantidad;

    /**
     * Campo de texto para mostrar la raza de la mascota
     */
    private JTextField txtRaza;

    /**
     * Campo de texto con la edad
     */
    private JTextField txtEdad;

    /**
     * Campo de texto con el precio de la mascota
     */
    private JTextField txtPrecio;

    /**
     * Campo de texto con la cantidad de mascotas
     */
    private JTextField txtCantidad;

    /**
     * Botón hacer comprar
     */
    private JButton btnComprar;

    /**
     * Botón hacer vender
     */
    private JButton btnVender;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param nPrincipal Ventana principal de la aplicación. laPrincipal != null.
     * @param nNumeroMascota El número de la mascota
     */
    public PanelMascota( InterfazTiendaMascotas nPrincipal, String nNombreMascota )
    {
        nombreMascota = nNombreMascota;
        principal = nPrincipal;
        setBorder( new TitledBorder( "Mascota " + nombreMascota ) );
        setLayout( new GridBagLayout( ) );

        // Etiqueta para la imagen
        ImageIcon icono = new ImageIcon( "data/" + nombreMascota + ".jpg" );
        lblImagen = new JLabel( "" );
        lblImagen.setIcon( icono );
        lblImagen.setBorder( new TitledBorder( "" ) );

        // Etiqueta para el nombre
        lblNombre = new JLabel( "Raza:" );

        // Etiqueta cantidad
        lblEdad = new JLabel( "Edad:" );

        // Etiqueta precio
        lblPrecio = new JLabel( "Precio:" );

        // Etiqueta cantidad
        lblCantidad = new JLabel( "Cantidad:" );

        // Campo para el nombre
        txtRaza = new JTextField( );
        txtRaza.setEditable( false );

        // Campo para la cantidad
        txtEdad = new JTextField( );
        txtEdad.setEditable( false );

        // Campo para precio
        txtPrecio = new JTextField( );
        txtPrecio.setEditable( false );

        // Campo para cantidad
        txtCantidad = new JTextField( );
        txtCantidad.setEditable( false );

        // Botón hacer pedido
        btnComprar = new JButton( "Comprar" );
        btnComprar.setPreferredSize( new Dimension( 150, 25 ) );
        btnComprar.setActionCommand( COMPRAR );
        btnComprar.addActionListener( this );
        btnComprar.setEnabled( true );
        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( ) );
        panelBotones.add( btnComprar );

        btnVender = new JButton( "Vender" );
        btnVender.setPreferredSize( new Dimension( 150, 25 ) );
        btnVender.setActionCommand( VENDER );
        btnVender.addActionListener( this );
        btnVender.setEnabled( true );
        panelBotones.add( btnVender );

        // Organización
        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 6, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( lblImagen, gbc );

        gbc = new GridBagConstraints( 1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( lblNombre, gbc );
        gbc.gridx = 2;
        add( txtRaza, gbc );

        gbc = new GridBagConstraints( 1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( lblEdad, gbc );
        gbc.gridx = 2;
        add( txtEdad, gbc );

        gbc = new GridBagConstraints( 1, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( lblPrecio, gbc );
        gbc.gridx = 2;
        add( txtPrecio, gbc );

        gbc = new GridBagConstraints( 1, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( lblCantidad, gbc );
        gbc.gridx = 2;
        add( txtCantidad, gbc );

        gbc = new GridBagConstraints( 1, 5, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( panelBotones, gbc );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que se ejecuta cuando se hace un click en el mouse
     */
    public void actionPerformed( ActionEvent e )
    {
        String accion = e.getActionCommand( );
        if( accion.equals( VENDER ) )
        {
            String strCantidad = JOptionPane.showInputDialog( this, "Ingrese la cantidad que desea vender.", "Venta de mascotas", JOptionPane.INFORMATION_MESSAGE );
            try
            {
                int cantidad = Integer.parseInt( strCantidad );
                if( cantidad < 0 )
                {
                    JOptionPane.showMessageDialog( this, "La cantidad que desea vender es inválida", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    principal.venderMascota( nombreMascota, cantidad );
                }
            }
            catch( NumberFormatException e1 )
            {
                JOptionPane.showMessageDialog( this, "Datos inválidos", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( accion.equals( COMPRAR ) )
        {
            String strCantidad = JOptionPane.showInputDialog( this, "Ingrese la cantidad que desea comprar.", "Compra de mascotas", JOptionPane.INFORMATION_MESSAGE );
            try
            {
                int cantidad = Integer.parseInt( strCantidad );
                if( cantidad < 0 )
                {
                    JOptionPane.showMessageDialog( this, "La cantidad que desea comprar es inválida", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    principal.comprarMascota( nombreMascota, cantidad );
                }
            }
            catch( NumberFormatException e1 )
            {
                JOptionPane.showMessageDialog( this, "Datos inválidos", "Error", JOptionPane.ERROR_MESSAGE );
            }

        }
    }

    /**
     * Actualiza la información que se muestra en el panel para las mascotas
     * @param mascota La mascota que contiene la información que se quiere mostrar en la interfaz
     */
    public void actualizar( Mascota mascota )
    {
        if( mascota != null )
        {
            txtEdad.setText( "" + mascota.obtenerEdad( ) );
            txtPrecio.setText( "" + formatearValorEntero( mascota.obtenerPrecio( ) ) );
            txtRaza.setText( mascota.obtenerNombre( ) );
            txtCantidad.setText( "" + mascota.obtenerCantidadActual( ) );
            lblImagen.setEnabled( true );
            btnVender.setEnabled( true );
        }
        else
        {
            txtEdad.setText( "N/A" );
            txtPrecio.setText( "N/A" );
            txtRaza.setText( "N/A" );
            txtCantidad.setText( "N/A" );
            lblImagen.setEnabled( false );
            btnVender.setEnabled( false );
        }

    }

    /**
     * Formatea un valor numérico entero para presentar en la interfaz <br>
     * @param valor Valor numérico a ser formateado
     * @return Retorna una cadena con el valor formateado con puntos y signos.
     */
    private String formatearValorEntero( int valor )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( " ###,###" );
        df.setMinimumFractionDigits( 0 );
        return df.format( valor );
    }

}