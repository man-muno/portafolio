/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDetallesCrucero.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.cupi2CruiseLines.mundo.Crucero;

/**
 * Es el panel donde se ingresa la información para agregar un nuevo crucero
 */
public class PanelDetallesCrucero extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para el botón Cerrar
     */
    private static final String CERRAR = "CERRAR";

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
     * Etiqueta numero de ciudades
     */
    private JLabel lblNumCiudades;

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
    private JTextField txtNumDias;

    /**
     * Campo de texto donde se ingresa el código de la línea de producto
     */
    private JTextField txtNumCiudades;

    /**
     * Campo de texto donde se muestra el precio de la línea de producto
     */
    private JTextField txtPrecio;

    /**
     * Calendario para seleccionar de lanzamiento de la línea de producto
     */
    private JTextField txtFecha;

    /**
     * Botón para cancelar el lanzamiento
     */
    private JButton botonCerrar;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Dialogo que contiene este panel
     */
    private DialogoDetallesCrucero padre;

    /**
     * Crucero al cual se le quieren mostrar los detalles
     */
    private Crucero crucero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param producto Dialogo que contiene este panel
     */
    public PanelDetallesCrucero( DialogoDetallesCrucero producto, Crucero nCrucero )
    {
        padre = producto;
        crucero = nCrucero;
        setLayout( new GridBagLayout( ) );

        // Construir e inicializar las etiquetas
        GridBagConstraints gbcE = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        lblNombre = new JLabel( "Nombre:" );
        add( lblNombre, gbcE );

        gbcE.gridy = 1;
        lblNumDias = new JLabel( "Duración (días):" );
        add( lblNumDias, gbcE );

        gbcE.gridy = 2;
        lblNumCiudades = new JLabel( "Número de Ciudades: " );
        add( lblNumCiudades, gbcE );

        gbcE.gridy = 3;
        lblPrecio = new JLabel( "Precio por Persona (US$):" );
        add( lblPrecio, gbcE );

        gbcE.gridy = 4;
        lblFecha = new JLabel( "Fecha de Zarpado:" );
        add( lblFecha, gbcE );

        // Construir e inicializar los campos
        GridBagConstraints gbcC = new GridBagConstraints( 1, 0, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        txtNombre = new JTextField( crucero.darNombre( ) );
        txtNombre.setEditable( false );
        add( txtNombre, gbcC );

        gbcC.gridy = 1;
        txtNumDias = new JTextField( "" + crucero.darDuracion( ) );
        txtNumDias.setEditable( false );
        add( txtNumDias, gbcC );

        gbcC.gridy = 2;
        txtNumCiudades = new JTextField( "" + crucero.darNumeroCiudades( ) );
        txtNumCiudades.setEditable( false );
        add( txtNumCiudades, gbcC );

        gbcC.gridy = 3;
        txtPrecio = new JTextField( "" + crucero.darPrecio( ) );
        txtPrecio.setEditable( false );
        add( txtPrecio, gbcC );

        gbcC.gridy = 4;
        txtFecha = new JTextField( crucero.darFechaSalida( ) );
        txtFecha.setEditable( false );
        add( txtFecha, gbcC );

        // Construir e inicializar los botones
        JPanel panelBotones = new JPanel( );

        botonCerrar = new JButton( "Cerrar" );
        botonCerrar.setActionCommand( CERRAR );
        botonCerrar.addActionListener( this );
        panelBotones.add( botonCerrar );

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
        if( CERRAR.equals( comando ) )
        {
            padre.dispose( );
        }

    }
}