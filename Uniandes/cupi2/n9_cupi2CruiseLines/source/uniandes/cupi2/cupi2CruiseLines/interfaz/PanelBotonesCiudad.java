/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBotonesCiudad.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
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
import javax.swing.JPanel;

import uniandes.cupi2.cupi2CruiseLines.mundo.Ciudad;

/**
 * Panel donde se colocan los botones de acciones sobre la ciudad
 */
public class PanelBotonesCiudad extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que indica que el usuario desea agregar una nueva ciudad
     */
    private static final String AGREGAR_CIUDAD = "AGREGAR_CIUDAD";

    /**
     * Constante que indica que el usuario desea seleccionar la ciudad inicial
     */
    private static final String CIUDAD_INICIAL = "CIUDAD_INICIAL";

    /**
     * Constante que indica que el usuario desea seleccionar la ciudad final
     */
    private static final String CIUDAD_FINAL = "CIUDAD_FINAL";

    /**
     * Constante que indica que el usuario desea seleccionar la ciudad siguiente
     */
    private static final String CIUDAD_SIGUIENTE = "CIUDAD_SIGUIENTE";

    /**
     * Constante que indica que el usuario desea seleccionar la ciudad siguiente
     */
    private static final String CIUDAD_ANTERIOR = "CIUDAD_ANTERIOR";

    /**
     * Constante que indica que el usuario desea ver la información de la ciudad seleccionada
     */
    private static final String VER_DETALLES = "VER_DETALLES";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Botón de agregar ciudad
     */
    private JButton btnAgregarCiudad;

    /**
     * Botón de ir a ciudad inicial
     */
    private JButton btnInicio;

    /**
     * Botón de ir a la ciudad final
     */
    private JButton btnFinal;

    /**
     * Botón de ir a la ciudad siguiente
     */
    private JButton btnSiguiente;

    /**
     * Botón de ir a la ciudad anterior
     */
    private JButton btnAnterior;

    /**
     * Botón de ver los detalles de la ciudad
     */
    private JButton btnVerDetalles;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia de la ventana principal de la aplicación
     */
    private InterfazCupi2CruiseLines principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros del panel. Inicializa la instancia de la ventana principal. Crea y coloca los componentes gráficos
     * @param ventana Instancia de la ventana principal. Diferente de null
     */
    public PanelBotonesCiudad( InterfazCupi2CruiseLines ventana )
    {
        principal = ventana;

        setLayout( new GridBagLayout( ) );

        btnAgregarCiudad = new JButton( "Agregar Ciudad" );
        btnAgregarCiudad.addActionListener( this );
        btnAgregarCiudad.setActionCommand( AGREGAR_CIUDAD );
        btnAgregarCiudad.setEnabled( false );

        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        Insets insets = new Insets( 5, 5, 5, 5 );
        constraint.insets = insets;
        add( btnAgregarCiudad, constraint );

        btnInicio = new JButton( "<<|" );
        btnInicio.addActionListener( this );
        btnInicio.setActionCommand( CIUDAD_INICIAL );
        btnInicio.setEnabled( false );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 5, 5, 5 );
        constraint.insets = insets;
        add( btnInicio, constraint );

        btnAnterior = new JButton( "<<" );
        btnAnterior.addActionListener( this );
        btnAnterior.setActionCommand( CIUDAD_ANTERIOR );
        btnAnterior.setEnabled( false );

        constraint = new GridBagConstraints( );
        constraint.gridx = 2;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 5, 5, 5 );
        constraint.insets = insets;
        add( btnAnterior, constraint );

        btnVerDetalles = new JButton( "Ver Detalles" );
        btnVerDetalles.addActionListener( this );
        btnVerDetalles.setActionCommand( VER_DETALLES );
        btnVerDetalles.setEnabled( false );

        constraint = new GridBagConstraints( );
        constraint.gridx = 3;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 5, 5, 5 );
        constraint.insets = insets;
        add( btnVerDetalles, constraint );

        btnSiguiente = new JButton( ">>" );
        btnSiguiente.addActionListener( this );
        btnSiguiente.setActionCommand( CIUDAD_SIGUIENTE );
        btnSiguiente.setEnabled( false );

        constraint = new GridBagConstraints( );
        constraint.gridx = 4;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 5, 5, 5 );
        constraint.insets = insets;
        add( btnSiguiente, constraint );

        btnFinal = new JButton( "|>>" );
        btnFinal.addActionListener( this );
        btnFinal.setActionCommand( CIUDAD_FINAL );
        btnFinal.setEnabled( false );

        constraint = new GridBagConstraints( );
        constraint.gridx = 5;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        insets = new Insets( 5, 5, 5, 5 );
        constraint.insets = insets;
        add( btnFinal, constraint );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String accion = e.getActionCommand( );
        if( accion.equals( AGREGAR_CIUDAD ) )
        {
            principal.mostrarDialogoAgregarCiudad( );
        }
        else if( accion.equals( VER_DETALLES ) )
        {
            principal.mostrarDialogoDetallesCiudad( );
        }
        else if( accion.equals( CIUDAD_INICIAL ) )
        {
            principal.mostrarCiudadInicial( );
        }
        else if( accion.equals( CIUDAD_FINAL ) )
        {
            principal.mostrarCiudadFinal( );
        }
        else if( accion.equals( CIUDAD_SIGUIENTE ) )
        {
            principal.mostrarSiguienteCiudad( );
        }
        else if( accion.equals( CIUDAD_ANTERIOR ) )
        {
            principal.mostrarAnteriorCiudad( );
        }
    }

    /**
     * Hace visibles los botones necesarios cuando se crea un nuevo crucero
     */
    public void mostrarBotonesCreacionCrucero( )
    {
        btnAgregarCiudad.setEnabled( true );
    }

    /**
     * Muestra los botones de acuerdo a la información que esta en la ciudad.
     * @param ciudad Ciudad a mostrar en el panel. Diferente de null
     */
    public void mostrarBotonesCreacionCiudad( Ciudad ciudad )
    {
        btnAgregarCiudad.setEnabled( true );
        if( ciudad == null )
        {
            btnInicio.setEnabled( false );
            btnAnterior.setEnabled( false );
            btnVerDetalles.setEnabled( false );
        }
        else
        {
            if( ciudad.darSiguiente( ) != null )
            {
                btnFinal.setEnabled( true );
                btnSiguiente.setEnabled( true );
            }
            else
            {
                btnFinal.setEnabled( false );
                btnSiguiente.setEnabled( false );
            }
            if( ciudad.darAnterior( ) != null )
            {
                btnInicio.setEnabled( true );
                btnAnterior.setEnabled( true );
            }
            else
            {
                btnInicio.setEnabled( false );
                btnAnterior.setEnabled( false );
            }
            btnVerDetalles.setEnabled( true );
        }
    }
}
