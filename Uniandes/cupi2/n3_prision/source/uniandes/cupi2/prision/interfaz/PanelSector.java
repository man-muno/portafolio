/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelSector.java,v 1.3 2006/11/26 22:13:00 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Muñoz - Sep 13, 2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.prision.mundo.Sector;

/**
 * Panel donde se muestra la información de los sectores
 */
public class PanelSector extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando de mostrar sector en el panel de sectores
     */
    private static final String MOSTRAR_SECTOR = "MOSTRAR_SECTOR";

    /**
     * Comando de mostrar prisioneros de un sector
     */
    private static final String MOSTRAR_PRISIONEROS = "MOSTRAR_PRISIONEROS";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Interfaz principal de la aplicación
     */
    private InterfazPrision principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta donde se guarda la imagen del sector
     */
    private JLabel etiquetaImagen;

    /**
     * Etiqueta que muestra la cantidad de prisioneros del sector
     */
    private JLabel etiquetaCantidad;

    /**
     * Lista donde se muestran los sectores
     */
    private JComboBox comboSectores;

    /**
     * Botón para ver los prisioneros del sector
     */
    private JButton btnVerPrisioneros;

    /**
     * Botón para ver la información del sector
     */
    private JButton btnMostrarSector;

    /**
     * Texto para mostrar la cantidad de prisioneros en el sector
     */
    private JTextField txtCantidad;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Método constructor por parámetros
     * @param ventanaPrincipal Referencia a la ventana principal. ventanaPrincipal!=null
     */
    public PanelSector( InterfazPrision ventanaPrincipal )
    {
        super( );
        principal = ventanaPrincipal;
        TitledBorder titulo;
        titulo = BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ), "Sectores" );
        titulo.setTitleJustification( TitledBorder.LEFT );
        setBorder( titulo );

        setLayout( new GridBagLayout( ) );

        ImageIcon icono = new ImageIcon( "data/prison.jpg" );

        // La agrega a la etiqueta
        etiquetaImagen = new JLabel( "" );
        etiquetaImagen.setIcon( icono );

        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets( 0, 0, 20, 0 );
        constraint.fill = GridBagConstraints.BOTH;

        add( etiquetaImagen, constraint );

        JPanel panelInformacion = new JPanel( );
        panelInformacion.setLayout( new GridBagLayout( ) );

        comboSectores = new JComboBox( );
        etiquetaCantidad = new JLabel( );
        btnVerPrisioneros = new JButton( "Ver Prisioneros" );
        btnVerPrisioneros.setActionCommand( MOSTRAR_PRISIONEROS );
        btnVerPrisioneros.addActionListener( this );
        btnMostrarSector = new JButton( "Mostrar" );
        btnMostrarSector.addActionListener( this );
        btnMostrarSector.setActionCommand( MOSTRAR_SECTOR );
        txtCantidad = new JTextField( );
        txtCantidad.setEditable( false );
        txtCantidad.setPreferredSize( new Dimension( 100, 20 ) );

        Sector[] sect = principal.obtenerSectores( );

        for( int i = 0; i < sect.length; i++ )
        {
            comboSectores.addItem( sect[ i ].darNombre( ) + " - " + sect[ i ].darTipo( ) );
        }

        etiquetaCantidad.setText( "Cantidad de prisioneros:" );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        constraint.fill = GridBagConstraints.BOTH;
        panelInformacion.add( comboSectores, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets( 10, 10, 0, 0 );
        constraint.fill = GridBagConstraints.BOTH;
        panelInformacion.add( btnMostrarSector, constraint );

        JPanel panelCantidad = new JPanel( );
        panelCantidad.setLayout( new GridBagLayout( ) );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        constraint.fill = GridBagConstraints.BOTH;

        panelCantidad.add( etiquetaCantidad, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.gridwidth = 1;
        constraint.insets = new Insets( 10, 10, 0, 0 );
        constraint.fill = GridBagConstraints.BOTH;
        panelCantidad.add( txtCantidad, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.gridwidth = 2;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        constraint.fill = GridBagConstraints.BOTH;
        panelInformacion.add( panelCantidad, constraint );

        JPanel panelPrisioneros = new JPanel( );
        panelPrisioneros.setLayout( new GridBagLayout( ) );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        constraint.fill = GridBagConstraints.BOTH;
        panelPrisioneros.add( btnVerPrisioneros, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.gridwidth = 2;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        constraint.fill = GridBagConstraints.BOTH;
        panelInformacion.add( panelPrisioneros, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets( 0, 20, 0, 0 );
        add( panelInformacion, constraint );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Método que actualiza la información del panel del sector
     */
    public void mostrarInfoSector( )
    {
        String nomSector = obtenerSectorSeleccionado( );

        Sector sector = principal.buscarSector( nomSector );

        txtCantidad.setText( "" + sector.darNumeroPrisioneros( ) );

        String url = "data/" + sector.darNombre( ) + ".jpg";
        ImageIcon icono = new ImageIcon( url );

        // La agrega a la etiqueta
        etiquetaImagen.setIcon( icono );
    }

    /**
     * Retorna el sector seleccionado por el usuario
     * @return El sector seleccionado por el usuario
     */
    public String obtenerSectorSeleccionado( )
    {
        String sector = ( String )comboSectores.getSelectedItem( );
        return sector.substring( 0, sector.indexOf( " - " ) );
    }

    /**
     * Manejo de los eventos de los botones
     * @param evento Acción que generó el evento. evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( MOSTRAR_PRISIONEROS.equals( comando ) )
        {
            principal.mostrarDialogoPrisionerosSector( );
        }
        else if( MOSTRAR_SECTOR.equals( comando ) )
        {
            principal.mostrarSector( );
        }
    }
}
