/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.7 2007/01/24 22:36:42 f-vela Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Manuel Muñoz - 07-Sep-2006
 * Autor: Daniel Romero - 17-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sudoku.interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando para abrir un nuevo sudoku
     */
    private static final String ABRIR_ARCHIVO = "ABRIR_ARCHIVO";

    /**
     * Comando para validar el sudoku
     */
    private static final String VALIDAR = "VALIDAR";

    /**
     * Comando que muestra la solución del sukoku
     */
    private static final String CARGAR_JUEGO = "CARGAR_JUEGO";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazSudoku principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Botón Opción 1
     */
    private JButton btnOpcion1;

    /**
     * Botón Opción 2
     */
    private JButton btnOpcion2;

    /**
     * Botón de abrir un nuevo archivo
     */
    private JButton btnArchivo;

    /**
     * Botón para validar un tablero
     */
    private JButton btnValidar;

    /**
     * Botón para mostrar la solución del sudoku
     */
    private JButton btnMostrarSolucion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal. ventana!=null
     */
    public PanelExtension( InterfazSudoku ventana )
    {
        principal = ventana;

        TitledBorder borde = new TitledBorder( "Opciones" );
        borde.setTitleColor( Color.WHITE );
        setBorder( borde );
        setLayout( new GridLayout( 1, 2 ) );
        setBackground( Color.BLACK );

        // Botón abrir archivo
        btnArchivo = new JButton( "Cargar" );
        btnArchivo.setActionCommand( ABRIR_ARCHIVO );
        btnArchivo.addActionListener( this );
        add( btnArchivo );

        // Botón validar
        btnValidar = new JButton( "Validar" );
        btnValidar.setActionCommand( VALIDAR );
        btnValidar.addActionListener( this );
        btnValidar.setEnabled( false );
        add( btnValidar );

        // Botón mostrar solución
        btnMostrarSolucion = new JButton( "Solución" );
        btnMostrarSolucion.setActionCommand( CARGAR_JUEGO );
        btnMostrarSolucion.addActionListener( this );
        btnMostrarSolucion.setEnabled( false );
        add( btnMostrarSolucion );

        // Botón opción 1
        btnOpcion1 = new JButton( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Botón opción 2
        btnOpcion2 = new JButton( "Opción 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param evento Acción que generó el evento. evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        if( OPCION_1.equals( evento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( evento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( ABRIR_ARCHIVO.equals( evento.getActionCommand( ) ) )
        {
            principal.cargarJuego( );
        }
        else if( VALIDAR.equals( evento.getActionCommand( ) ) )
        {
            principal.validarJuego( );
        }
        else if( CARGAR_JUEGO.equals( evento.getActionCommand( ) ) )
        {
            principal.mostrarSolucion( );
        }
    }

    /**
     * Habilita o deshabilita el los botones de validación y de mostrar solución <br>
     * dependiendo del estado del juego
     */
    public void actualizarBotones( )
    {
        if( principal.juegoActivo( ) )
        {
            btnValidar.setEnabled( true );
            btnMostrarSolucion.setEnabled( true );
        }
        else
        {
            btnValidar.setEnabled( false );
            btnMostrarSolucion.setEnabled( false );
        }
    }

}
