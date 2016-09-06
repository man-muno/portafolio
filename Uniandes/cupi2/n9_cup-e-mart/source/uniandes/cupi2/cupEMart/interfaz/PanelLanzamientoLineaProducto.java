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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.cupEMart.mundo.Fecha;
import uniandes.cupi2.cupEMart.mundo.LineaProductoExisteException;
import uniandes.cupi2.cupEMart.mundo.SucursalNoExisteException;

import com.toedter.calendar.JCalendar;

/**
 * Es el panel donde se ingresa la información para lanzar una línea de producto
 */
public class PanelLanzamientoLineaProducto extends JPanel implements ActionListener
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
     * Etiqueta Nombre;
     */
    private JLabel lblNombre;

    /**
     * Etiqueta Código;
     */
    private JLabel lblCodigo;

    /**
     * Etiqueta Descripción;
     */
    private JLabel lblDescripcion;

    /**
     * Etiqueta Precio;
     */
    private JLabel lblPrecio;

    /**
     * Etiqueta Fecha:
     */
    private JLabel lblFecha;

    /**
     * Campo de texto donde se muestra el nombre de la línea de producto
     */
    private JTextField txtNombre;

    /**
     * Campo de texto donde se ingresa el código de la línea de producto
     */
    private JTextField txtCodigo;

    /**
     * Campo de texto donde se muestra el precio de la línea de producto
     */
    private JTextField txtPrecio;

    /**
     * Campo de texto donde se muestra la descripción de la línea de producto
     */
    private JTextField txtDescripcion;

    /**
     * Calendario para seleccionar de lanzamiento de la línea de producto
     */
    private JCalendar calendarioFecha;

    /**
     * Botón usado para lanzar la línea de producto
     */
    private JButton botonAgregar;

    /**
     * Botón para cancelar el lanzamiento
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Dialogo que contiene este panel
     */
    private DialogoLanzamientoLineaProducto padre;

    /**
     * Dirección de la sucursal donde quiere ser lanzado la línea de producto
     */
    private String sucursal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param nSucursal Dirección de la sucursal donde quiere ser lanzado la línea de producto
     * @param producto Dialogo que contiene este panel
     */
    public PanelLanzamientoLineaProducto( String nSucursal, DialogoLanzamientoLineaProducto producto )
    {
        sucursal = nSucursal;
        padre = producto;
        setLayout( new GridBagLayout( ) );
        setPreferredSize( new Dimension( 477, 360 ) );

        // Construir e inicializar las etiquetas
        inicializarEtiquetas( );

        // Construir e inicializar los campos
        inicializarCampos( );

        // Construir e inicializar los botones
        inicializarBotones( );
    }
    // -----------------------------------------------------------------
    // Métodoss
    // -----------------------------------------------------------------
    /**
     * Inicializa las etiquetas del panel
     */
    private void inicializarEtiquetas( )
    {
        GridBagConstraints gbcE = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        lblNombre = new JLabel( "Nombre:" );
        add( lblNombre, gbcE );

        gbcE.gridy = 1;
        lblCodigo = new JLabel( "Código:" );
        add( lblCodigo, gbcE );

        gbcE.gridy = 2;
        lblDescripcion = new JLabel( "Descripción:" );
        add( lblDescripcion, gbcE );

        gbcE.gridy = 3;
        lblPrecio = new JLabel( "Precio por Unidad:" );
        add( lblPrecio, gbcE );

        gbcE.gridy = 4;
        lblFecha = new JLabel( "Fecha:" );
        add( lblFecha, gbcE );

    }

    /**
     * Inicializa los campos del panel
     */
    private void inicializarCampos( )
    {
        GridBagConstraints gbcC = new GridBagConstraints( 1, 0, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        txtNombre = new JTextField( );
        add( txtNombre, gbcC );

        gbcC.gridy = 1;
        txtCodigo = new JTextField( );
        add( txtCodigo, gbcC );

        gbcC.gridy = 2;
        txtDescripcion = new JTextField( );
        add( txtDescripcion, gbcC );

        gbcC.gridy = 3;
        txtPrecio = new JTextField( );
        add( txtPrecio, gbcC );

        gbcC.gridy = 4;
        calendarioFecha = new JCalendar( );
        add( calendarioFecha, gbcC );

    }

    /**
     * Inicializa los botones del panel
     */
    private void inicializarBotones( )
    {
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
            String codigo = txtCodigo.getText( );
            String descripcion = txtDescripcion.getText( );
            String strPrecio = txtPrecio.getText( );

            if( nombre == null )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un nombre válido", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if( codigo == null )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un código válido", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if( strPrecio == null )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un precio válido", "Error", JOptionPane.ERROR_MESSAGE );
            }

            try
            {
                int precio = Integer.valueOf( strPrecio );

                if( precio <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "Debe ingresar un número mayor a cero", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    Date fechaIngresada = calendarioFecha.getDate( );
                    Calendar calendario = Calendar.getInstance( );
                    calendario.setTime( fechaIngresada );
                    Fecha fecha = new Fecha( calendario.get( Calendar.DAY_OF_MONTH ), calendario.get( Calendar.MONTH ) + 1, calendario.get( Calendar.YEAR ) );
                    padre.lanzarLineaProducto( sucursal, nombre, codigo, descripcion, precio, fecha );
                    padre.dispose( );
                }
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un precio válido", "Error", JOptionPane.ERROR_MESSAGE );
            }
            catch( SucursalNoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
            catch( LineaProductoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( CANCELAR.equals( comando ) )
        {
            padre.dispose( );
        }

    }
}