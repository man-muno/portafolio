/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelJuego.java,v 1.3 2007/04/20 12:38:40 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Muñoz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.interpol.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Panel donde se colocan los elementos principales del juego
 */
public class PanelJuego extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que identiica que el usuario quiere ver las pistas de la ciudad
     */
    private static final String PISTAS = "PISTAS";

    /**
     * Constante que identifica que el usuario desea viajar
     */
    private static final String VIAJAR = "VIAJAR";

    /**
     * Constante que identifica que el usuario quiere ver el caso
     */
    private static final String VER_CASO = "VER_CASO";

    /**
     * Acción asociada al botón recorrido inorden
     */
    private static final String INORDEN = "INORDEN";

    /**
     * Acción asociada al botón recorrido preorden
     */
    private static final String PREORDEN = "PREORDEN";

    /**
     * Acción asociada al botón recorrido postorden
     */
    private static final String POSTORDEN = "POSTORDEN";

    /**
     * Acción asociada al botón de obtener altura
     */
    private static final String ALTURA = "ALTURA";

    /**
     * Acción asociada al botón de obtener peso
     */
    private static final String PESO = "PESO";

    /**
     * Acción asociada al botón para expedir una orden de captura
     */
    private static final String EXPEDIR_ORDEN_CAPTURA = "EXPEDIR_ORDEN_CAPTURA";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    private JLabel lblImagen;
    private JButton btnVerCaso;
    private JButton btnViajar;
    private JButton btnPistas;
    private JButton btnInorden;
    private JButton btnPreorden;
    private JButton btnPostorden;
    private JButton btnAltura;
    private JButton btnPeso;
    private JButton btnOrdenCaptura;
    private JTextArea txtDescripcion;
    private JScrollPane scroll;
    private JLabel lblCiudadActual;
    private JTextField txtCiudadActual;
    private JLabel lblDiasFaltantes;
    private JTextField txtDiasFaltantes;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazInterpol principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros. Inicializa y coloca los componentes gráficos.
     * @param interpol Interfaz principal de la aplicación. Diferente de null.
     */
    public PanelJuego( InterfazInterpol interpol )
    {
        principal = interpol;
        setLayout( new GridBagLayout( ) );

        lblImagen = new JLabel( );
        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;

        add( lblImagen, constraint );

        JPanel panel = new JPanel( );
        panel.setLayout( new GridBagLayout( ) );

        lblCiudadActual = new JLabel( "Ciudad Actual: " );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        panel.add( lblCiudadActual, constraint );

        txtCiudadActual = new JTextField( );
        txtCiudadActual.setEditable( false );
        txtCiudadActual.setPreferredSize( new Dimension( 100, 25 ) );
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        panel.add( txtCiudadActual, constraint );

        lblDiasFaltantes = new JLabel( "Días Faltantes: " );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        panel.add( lblDiasFaltantes, constraint );

        txtDiasFaltantes = new JTextField( );
        txtDiasFaltantes.setEditable( false );
        txtDiasFaltantes.setPreferredSize( new Dimension( 100, 25 ) );
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        panel.add( txtDiasFaltantes, constraint );

        txtDescripcion = new JTextArea( );
        scroll = new JScrollPane( txtDescripcion );
        scroll.setPreferredSize( new Dimension( 100, 100 ) );
        txtDescripcion.setEditable( false );
        txtDescripcion.setLineWrap( true );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.gridwidth = 2;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        panel.add( scroll, constraint );

        btnVerCaso = new JButton( "Ver Caso" );
        btnVerCaso.setActionCommand( VER_CASO );
        btnVerCaso.addActionListener( this );
        btnVerCaso.setPreferredSize( new Dimension( 100, 25 ) );
        btnVerCaso.setEnabled( false );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.gridwidth = 2;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        panel.add( btnVerCaso, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 4;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        btnPistas = new JButton( "Pistas" );
        btnPistas.setActionCommand( PISTAS );
        btnPistas.addActionListener( this );
        btnPistas.setPreferredSize( new Dimension( 100, 25 ) );
        btnPistas.setEnabled( false );
        panel.add( btnPistas, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 5;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        btnViajar = new JButton( "Viajar" );
        btnViajar.setActionCommand( VIAJAR );
        btnViajar.addActionListener( this );
        btnViajar.setPreferredSize( new Dimension( 100, 25 ) );
        btnViajar.setEnabled( false );
        panel.add( btnViajar, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 6;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        btnOrdenCaptura = new JButton( "Expedir Orden de Captura" );
        btnOrdenCaptura.setActionCommand( EXPEDIR_ORDEN_CAPTURA );
        btnOrdenCaptura.addActionListener( this );
        btnOrdenCaptura.setPreferredSize( new Dimension( 100, 25 ) );
        btnOrdenCaptura.setEnabled( false );
        panel.add( btnOrdenCaptura, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 7;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        btnInorden = new JButton( "Recorrido Inorden" );
        btnInorden.setActionCommand( INORDEN );
        btnInorden.addActionListener( this );
        btnInorden.setPreferredSize( new Dimension( 100, 25 ) );
        btnInorden.setEnabled( false );
        panel.add( btnInorden, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 8;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        btnPreorden = new JButton( "Recorrido Preorden" );
        btnPreorden.setActionCommand( PREORDEN );
        btnPreorden.addActionListener( this );
        btnPreorden.setPreferredSize( new Dimension( 100, 25 ) );
        btnPreorden.setEnabled( false );
        panel.add( btnPreorden, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 9;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        btnPostorden = new JButton( "Recorrido Postorden" );
        btnPostorden.setActionCommand( POSTORDEN );
        btnPostorden.addActionListener( this );
        btnPostorden.setPreferredSize( new Dimension( 100, 25 ) );
        btnPostorden.setEnabled( false );
        panel.add( btnPostorden, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 10;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        btnAltura = new JButton( "Altura" );
        btnAltura.setActionCommand( ALTURA );
        btnAltura.addActionListener( this );
        btnAltura.setPreferredSize( new Dimension( 100, 25 ) );
        btnAltura.setEnabled( false );
        panel.add( btnAltura, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 11;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        btnPeso = new JButton( "Peso" );
        btnPeso.setActionCommand( PESO );
        btnPeso.addActionListener( this );
        btnPeso.setPreferredSize( new Dimension( 100, 25 ) );
        btnPeso.setEnabled( false );
        panel.add( btnPeso, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets( 5, 10, 5, 10 );
        constraint.fill = GridBagConstraints.BOTH;
        add( panel, constraint );

        setVisible( false );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia los componentes gráficos de acuerdo al estado del mundo.
     */
    public void actualizar( )
    {
        setVisible( true );
        Random rand = new Random( );
        int numero = rand.nextInt( 10 );
        lblImagen.setIcon( new ImageIcon( "./data/imagenes/" + principal.darCiudadActual( ).darNombreCiudad( ) + ".jpg" ) );
        btnVerCaso.setEnabled( true );
        btnPistas.setEnabled( true );
        btnViajar.setEnabled( true );
        btnAltura.setEnabled( true );
        btnInorden.setEnabled( true );
        btnPeso.setEnabled( true );
        btnPostorden.setEnabled( true );
        btnPreorden.setEnabled( true );
        btnOrdenCaptura.setEnabled( true );
        txtDescripcion.setText( principal.darCiudadActual( ).darDescripcion( ) );
        txtCiudadActual.setText( principal.darCiudadActual( ).darNombreCiudad( ) );
        txtDiasFaltantes.setText( "" + principal.darDiasFaltantes( ) );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( VER_CASO ) )
        {
            principal.mostrarCaso( );
        }
        else if( comando.equals( PISTAS ) )
        {
            principal.mostrarPistas( );
        }
        else if( comando.equals( VIAJAR ) )
        {
            principal.mostrarViajes( );
        }
        else if( comando.equals( EXPEDIR_ORDEN_CAPTURA ) )
        {
            principal.mostrarDialogoExpedirOrdenCaptura( );
        }
        else if( comando.equals( INORDEN ) )
        {
            principal.mostrarRecorridoInorden( );
        }
        else if( comando.equals( PREORDEN ) )
        {
            principal.mostrarRecorridoPreorden( );
        }
        else if( comando.equals( POSTORDEN ) )
        {
            principal.mostrarRecorridoPostorden( );
        }
        else if( comando.equals( ALTURA ) )
        {
            principal.mostrarAltura( );
        }
        else if( comando.equals( PESO ) )
        {
            principal.mostrarPeso( );
        }
    }

    /**
     * Cambia los días faltantes en la interfaz.
     */
    public void actualizarDiasFaltantes( )
    {
        txtDiasFaltantes.setText( "" + principal.darDiasFaltantes( ) );
    }

    /**
     * Oculta los botones de acuerdo al estado del mundo.
     */
    public void ocultarBotones( )
    {
        btnVerCaso.setEnabled( false );
        btnPistas.setEnabled( false );
        btnViajar.setEnabled( false );
        btnAltura.setEnabled( false );
        btnInorden.setEnabled( false );
        btnPeso.setEnabled( false );
        btnPostorden.setEnabled( false );
        btnPreorden.setEnabled( false );
        btnOrdenCaptura.setEnabled( false );
    }
}
