/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Mu�oz - Oct 24, 2006
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.cupEMart.mundo.LineaProducto;

/**
 * Panel que contiene los elementos gr�ficos para mostrar la informaci�n de la l�nea de producto
 */
public class PanelInfoLineaProducto extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Acci�n para el bot�n de cerrar
     */
    private static final String CERRAR = "CERRAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta que indica que la informaci�n que se mostrar� es el nombre
     */
    private JLabel lblNombre;

    /**
     * Etiqueta que indica que la informaci�n que se mostrara es el c�digo
     */
    private JLabel lblCodigo;

    /**
     * Etiqueta que indica que la informaci�n que se mostrara es la descripci�n
     */
    private JLabel lblDescripcion;

    /**
     * Etiqueta que indica que la informaci�n que se mostrara es el precio
     */
    private JLabel lblPrecio;

    /**
     * Etiqueta que indica que la informaci�n que se mostrara es la fecha
     */
    private JLabel lblFecha;

    /**
     * Cuadro de texto que contiene el nombre de la l�nea de producto
     */
    private JTextField txtNombre;

    /**
     * Cuadro de texto que contiene el c�digo de la l�nea de producto
     */
    private JTextField txtCodigo;

    /**
     * Cuadro de texto que contiene la descripci�n de la l�nea de producto
     */
    private JTextField txtDescripcion;

    /**
     * Cuadro de texto que contiene el precio de la l�nea de producto
     */
    private JTextField txtPrecio;

    /**
     * Cuadro de texto que contiene la fecha de la l�nea de producto
     */
    private JTextField txtFecha;

    /**
     * Bot�n de cerrar
     */
    private JButton btnCerrar;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * L�nea de producto que se quiere mostrar
     */
    private LineaProducto producto;

    /**
     * Dialogo que contiene este panel
     */
    private DialogoInfoLineaProducto padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros
     * @param nProducto L�nea de producto que quiere ser mostrada. Diferente de null
     * @param dialogo Dialogo que contiene este panel. Diferente de null
     */
    public PanelInfoLineaProducto( LineaProducto nProducto, DialogoInfoLineaProducto dialogo )
    {
        producto = nProducto;
        padre = dialogo;
        setLayout( new GridBagLayout( ) );

        // Construir e inicializar las etiquetas
        inicializarEtiquetas( );

        // Construir e inicializar los campos
        inicializarCampos( );

        // Construir e inicializar los botones
        inicializarBotones( );

    }
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * M�todo que inicializa y ubica todas las etiquetas
     */
    private void inicializarEtiquetas( )
    {
        GridBagConstraints gbcE = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        lblNombre = new JLabel( "Nombre:" );
        add( lblNombre, gbcE );

        gbcE.gridy = 1;
        lblCodigo = new JLabel( "C�digo:" );
        add( lblCodigo, gbcE );

        gbcE.gridy = 2;
        lblDescripcion = new JLabel( "Descripci�n:" );
        add( lblDescripcion, gbcE );

        gbcE.gridy = 3;
        lblPrecio = new JLabel( "Precio:" );
        add( lblPrecio, gbcE );

        gbcE.gridy = 4;
        lblFecha = new JLabel( "Fecha:" );
        add( lblFecha, gbcE );
    }

    /**
     * M�todo que inicializa y ubica todas los cuadros de texto
     */
    private void inicializarCampos( )
    {
        GridBagConstraints gbcC = new GridBagConstraints( 1, 0, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        txtNombre = new JTextField( );
        txtNombre.setEditable( false );
        txtNombre.setText( producto.darNombre( ) );
        add( txtNombre, gbcC );

        gbcC.gridy = 1;
        txtCodigo = new JTextField( );
        txtCodigo.setEditable( false );
        txtCodigo.setText( producto.darCodigo( ) );
        add( txtCodigo, gbcC );

        gbcC.gridy = 2;
        txtDescripcion = new JTextField( );
        txtDescripcion.setEditable( false );
        txtDescripcion.setText( producto.darDescripcion( ) );
        add( txtDescripcion, gbcC );

        gbcC.gridy = 3;
        txtPrecio = new JTextField( );
        txtPrecio.setEditable( false );
        txtPrecio.setText( "" + producto.darPrecioUnidad( ) );
        add( txtPrecio, gbcC );

        gbcC.gridy = 4;
        txtFecha = new JTextField( );
        txtFecha.setEditable( false );
        txtFecha.setText( producto.darFecha( ).toString( ) );
        add( txtFecha, gbcC );

    }

    /**
     * M�todo que inicializa y ubica todas los botones
     */
    private void inicializarBotones( )
    {
        JPanel panelBotones = new JPanel( );

        btnCerrar = new JButton( "Cerrar" );
        btnCerrar.setActionCommand( CERRAR );
        btnCerrar.addActionListener( this );
        panelBotones.add( btnCerrar );

        GridBagConstraints gbcB = new GridBagConstraints( 0, 5, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( panelBotones, gbcB );
    }

    /**
     * Se ejecuta una acci�n cuando se hace click en uno de los botones
     * @param evento El evento del click en un boton- evento != null
     */
    public void actionPerformed( ActionEvent evento )
    {
        if( evento.getActionCommand( ).equals( CERRAR ) )
            padre.dispose( );
    }
}