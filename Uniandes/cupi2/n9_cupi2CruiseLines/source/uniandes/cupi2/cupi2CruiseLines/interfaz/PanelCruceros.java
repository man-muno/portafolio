/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelCruceros.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Panel donde se encuentra la información y las operaciones de los cruceros
 */
public class PanelCruceros extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que indica que el usuario quiere ver los detalles de un crucero
     */
    public static final String MOSTRAR_DETALLES = "MOSTRAR_DETALLES";

    /**
     * Constante que identifica que el usuario quiere agregar un nuevo crucero
     */
    public static final String AGREGAR_CRUCERO = "AGREGAR_CRUCERO";

    /**
     * Constante que identifica que el usuario quiere generar el itinerario del crucero
     */
    public static final String GENERAR_ITINERARIO = "GENERAR_ITINERARIO";

    /**
     * Constante que identifica que el usuario quiere eliminar una ciudad del crucero
     */
    public static final String ELIMINAR_CIUDADES = "ELIMINAR_CIUDADES";

    /**
     * Constante que identifica que el usuario cambio el crucero seleccionado
     */
    public static final String CAMBIO_CRUCERO = "CAMBIO_CRUCERO";

    /**
     * Constante que indica que el usuario desea buscar un ciudad en el crucero
     */
    private static final String BUSCAR_CIUDAD = "BUSCAR_CIUDAD";

    /**
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Combo con los cruceros
     */
    private JComboBox comboCruceros;

    /**
     * Botón para mostrar los detalles del crucero
     */
    private JButton btnMostraDetalles;

    /**
     * Botón para agregar un nuevo crucero
     */
    private JButton btnAgregarCrucero;

    /**
     * Botón para eliminar una ciudad por la que pasa un crucero
     */
    private JButton btnEliminarCiudad;

    /**
     * Botón para buscar una ciudad por la que pasa un crucero
     */
    private JButton btnBuscarCiudad;

    /**
     * Botón para generar el itinerario del crucero
     */
    private JButton btnGenerarItinerario;

    /**
     * Botón Opción 1
     */
    private JButton btnOpcion1;

    /**
     * Botón Opción 2
     */
    private JButton btnOpcion2;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal
     */
    private InterfazCupi2CruiseLines principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros. Inicializa y coloca los componentes gráficos
     * @param ventana Instancia de la ventana principal de la aplicación. Diferente de null
     */
    public PanelCruceros( InterfazCupi2CruiseLines ventana )
    {
        principal = ventana;
        setLayout( new GridBagLayout( ) );
        Border borde = BorderFactory.createTitledBorder( "Cruceros" );
        setBorder( borde );

        comboCruceros = new JComboBox( );
        comboCruceros.addActionListener( this );
        comboCruceros.setActionCommand( CAMBIO_CRUCERO );
        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        Insets insets = new Insets( 5, 10, 5, 10 );
        constraint.insets = insets;
        add( comboCruceros, constraint );

        btnMostraDetalles = new JButton( "Mostrar Detalles" );
        btnMostraDetalles.addActionListener( this );
        btnMostraDetalles.setActionCommand( MOSTRAR_DETALLES );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 10, 5, 10 );
        constraint.insets = insets;
        add( btnMostraDetalles, constraint );

        btnAgregarCrucero = new JButton( "Agregar Crucero" );
        btnAgregarCrucero.addActionListener( this );
        btnAgregarCrucero.setActionCommand( AGREGAR_CRUCERO );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 10, 5, 10 );
        constraint.insets = insets;
        add( btnAgregarCrucero, constraint );

        btnEliminarCiudad = new JButton( "Eliminar Ciudad" );
        btnEliminarCiudad.addActionListener( this );
        btnEliminarCiudad.setActionCommand( ELIMINAR_CIUDADES );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 10, 5, 10 );
        constraint.insets = insets;
        add( btnEliminarCiudad, constraint );

        btnBuscarCiudad = new JButton( "Buscar Ciudad" );
        btnBuscarCiudad.addActionListener( this );
        btnBuscarCiudad.setActionCommand( BUSCAR_CIUDAD );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 4;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 10, 5, 10 );
        constraint.insets = insets;
        add( btnBuscarCiudad, constraint );

        btnGenerarItinerario = new JButton( "Generar Itinerario Crucero" );
        btnGenerarItinerario.addActionListener( this );
        btnGenerarItinerario.setActionCommand( GENERAR_ITINERARIO );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 5;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 10, 5, 10 );
        constraint.insets = insets;
        add( btnGenerarItinerario, constraint );

        btnOpcion1 = new JButton( "Opción 1" );
        btnOpcion1.addActionListener( this );
        btnOpcion1.setActionCommand( OPCION_1 );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 6;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 10, 5, 10 );
        constraint.insets = insets;
        add( btnOpcion1, constraint );

        btnOpcion2 = new JButton( "Opción 2" );
        btnOpcion2.addActionListener( this );
        btnOpcion2.setActionCommand( OPCION_2 );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 7;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 10, 5, 10 );
        constraint.insets = insets;
        add( btnOpcion2, constraint );

    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String accion = e.getActionCommand( );
        if( accion.equals( MOSTRAR_DETALLES ) )
        {
            principal.mostrarDialogoDetallesCrucero( );
        }
        else if( accion.equals( AGREGAR_CRUCERO ) )
        {
            principal.mostraDialogoAgregarCrucero( );
        }
        else if( accion.equals( GENERAR_ITINERARIO ) )
        {
            principal.generarReporte( );
        }
        else if( accion.equals( ELIMINAR_CIUDADES ) )
        {
            principal.mostrarDialogoEliminarCiudad( );
        }
        else if( accion.equals( BUSCAR_CIUDAD ) )
        {
            principal.mostrarDialogoBuscarCiudad( );
        }
        else if( accion.equals( CAMBIO_CRUCERO ) )
        {
            principal.cambioCrucero( );
        }
        else if( accion.equals( OPCION_1 ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( accion.equals( OPCION_2 ) )
        {
            principal.reqFuncOpcion2( );
        }

    }

    /**
     * Actualiza el combo con los cruceros del mundo.
     * @param nombresCruceros Lista de los nombres de los cruceros. La lista puede ser vacía pero no null.
     */
    public void actualizarCruceros( ArrayList nombresCruceros )
    {
        comboCruceros.removeAllItems( );
        for( int i = 0; i < nombresCruceros.size( ); i++ )
        {
            comboCruceros.addItem( nombresCruceros.get( i ) );
        }
        comboCruceros.setSelectedIndex( comboCruceros.getItemCount( ) - 1 );
    }

    /**
     * Retorna el nombre del crucero que esta seleccionado.
     * @return Nombre del crucero seleccionado
     */
    public String obtenerSeleccionado( )
    {
        return ( String )comboCruceros.getSelectedItem( );
    }

}
