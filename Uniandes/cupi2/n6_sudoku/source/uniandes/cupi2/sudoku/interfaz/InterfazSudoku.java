/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazSudoku.java,v 1.12 2007/01/24 22:36:43 f-vela Exp $
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.sudoku.mundo.Casilla;
import uniandes.cupi2.sudoku.mundo.Sudoku;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazSudoku extends JFrame
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Sudoku sudoku;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel donde se muestra el tablero de Sudoku
     */
    private PanelTablero panelTablero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la interfaz
     */
    public InterfazSudoku( )
    {
        // Crea la clase principal
        sudoku = new Sudoku( );

        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 500, 500 );
        setResizable( false );
        setTitle( "Sudoku" );
        getContentPane( ).setBackground( Color.BLACK );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

        // Creación de los paneles aquí
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        panelTablero = new PanelTablero( this );
        add( panelTablero, BorderLayout.CENTER );

        // Paneles auxiliares
        JPanel panelAuxiliar1 = new JPanel( );
        panelAuxiliar1.setPreferredSize( new Dimension( 110, 0 ) );
        panelAuxiliar1.setBackground( Color.BLACK );
        add( panelAuxiliar1, BorderLayout.WEST );
        JPanel panelAuxiliar2 = new JPanel( );
        panelAuxiliar2.setPreferredSize( new Dimension( 110, 0 ) );
        panelAuxiliar2.setBackground( Color.BLACK );
        add( panelAuxiliar2, BorderLayout.EAST );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Se carga el estado del Sudoku a partir de un archivo dado por el usuario
     */
    public void cargarJuego( )
    {
        // Crear el jfileChooser
        JFileChooser chooser = new JFileChooser( );
        chooser.setCurrentDirectory( new java.io.File( "./data" ) );
        chooser.setDialogTitle( "Seleccionar archivo" );
        chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
        chooser.setVisible( true );

        if( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
        {
            try
            {
                // Crear las propiedades
                Properties propiedades = new Properties( );
                propiedades.load( new FileInputStream( chooser.getSelectedFile( ) ) );
                // Pasar los valores de las propiedades al mundo
                sudoku.cargarTablero( propiedades );

                // Iniciar el juego
                sudoku.iniciarJuego( );
                // Actualizar el tablero
                actualizar( );
            }
            catch( Exception e )
            {                
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Actualiza el tablero gráfico de acuerdo a la información que se encuentra en el mundo
     */
    private void actualizar( )
    {
        panelTablero.actualizarTablero( );
        panelExtension.actualizarBotones( );
    }

    /**
     * Se encarga de validar la información ingresada por el usuario sobre el tablero
     */
    public void validarJuego( )
    {
        int[][] elTablero;
        try
        {
            elTablero = panelTablero.darMatriz( );
            sudoku.desmarcarCasillas( );
            if( sudoku.validarTablero( elTablero ) )
            {
                sudoku.terminarJuego( );
                pintarTableroTerminado( );
                JOptionPane.showMessageDialog( this, "Juego terminado con éxito", "Juego terminado", JOptionPane.INFORMATION_MESSAGE );
            }
            else
            {
                actualizar( );
            }
        }
        catch( Exception e )
        {            
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        
    }

    /**
     * Se cambia el color del tablero indicando que se termino el juego
     */
    private void pintarTableroTerminado( )
    {
        panelTablero.pintarTableroTerminado( );
        panelExtension.actualizarBotones( );
    }

    /**
     * Termina el juego y muestra la solución del Sudoku
     */
    public void mostrarSolucion( )
    {
        sudoku.terminarJuego( );
        panelTablero.mostrarSolucion( );
        panelExtension.actualizarBotones( );
        sudoku.limpiar( );
    }

    /**
     * Retorna si el juego esta activo o no
     * @return Se retornó true si el juego esta activo, false de lo contrario
     */
    public boolean juegoActivo( )
    {
        return sudoku.juegoActivo( );
    }

    /**
     * Retorna las casillas del tablero de juego
     * @return Se retornó las casillas del tablero de juego
     */
    public Casilla[][] darCasillasTablero( )
    {
        return sudoku.darTablero( );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = sudoku.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = sudoku.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Argumentos para la ejecución de la aplicación. No son necesarios en este caso
     */
    public static void main( String[] args )
    {

        InterfazSudoku interfaz = new InterfazSudoku( );
        interfaz.setVisible( true );
    }
}