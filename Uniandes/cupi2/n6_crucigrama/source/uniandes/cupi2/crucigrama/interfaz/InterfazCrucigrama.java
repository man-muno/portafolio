/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazCrucigrama.java,v 1.3 2007/04/03 07:40:44 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_crucigrama
 * Autor: Manuel Muñoz - 05-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.crucigrama.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.crucigrama.mundo.Crucigrama;


/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazCrucigrama extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Crucigrama crucigrama;

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;
    
    /**
     * Panel con la imagen del titulo
     */
    private PanelImagen panelImagen;
    
    /**
     * Panel donde se muestran los campos para llenar el crucigrama
     */
    private PanelTablero panelCrucigrama;
    
    /**
     * Panel donde se muestran las preguntas;
     */
    private PanelPreguntas panelPreguntas;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Descripción <br>
     * <b>post: </b> Descripción
     */
    public InterfazCrucigrama()
    {
        // Crea la clase principal
        crucigrama = new Crucigrama();
        setTitle( "Crucigrama" );
        
        // Construye la forma
        getContentPane().setLayout( new BorderLayout( ) );
        
        setSize(  815,541 );
        //setResizable( false );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        centrar();
        //Creación de los paneles aquí
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );
        panelImagen = new PanelImagen();
        add(panelImagen, BorderLayout.NORTH);

        panelCrucigrama = new PanelTablero(this);
        panelPreguntas = new PanelPreguntas(this);
        
        JPanel panel = new JPanel();
        add(panelCrucigrama,BorderLayout.CENTER);
        add(panelPreguntas,BorderLayout.EAST);
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Centra el diálogo en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }
    
    /**
     * Se carga el estado del crucigrama a partir de un archivo dado por el usuario
     */
    public void cargarJuego( )
    {
        // Crear el jfileChooser
        JFileChooser chooser = new JFileChooser( );
        chooser.setCurrentDirectory( new java.io.File( "./data" ) );
        chooser.setDialogTitle( "Seleccionar Archivo" );
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
                cargarTablero(propiedades);
            }
            catch( Exception e )
            {                
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        
    }
    
    /**
     * Carga el tablero de juego dada un archivo de propiedades
     * @param propiedades Archivo que contiene el juego. Diferente de null.
     */
    private void cargarTablero( Properties propiedades )
    {
        try
        {
            crucigrama.cargarTablero( propiedades );
            actualizar();
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Modifica los paneles para tener en cuenta la nueva informacion del crucigrama
     */
    private void actualizar( )
    {
        panelCrucigrama.actualizar(crucigrama.obtenerCasillasNegras());
        panelPreguntas.actualizar( crucigrama.obtenerListaPreguntasVerticales(), crucigrama.obtenerListaPreguntasHorizontales() );
        validate();
    }

    /**
     * Pide las letras ingresadas por el usuario y las envia al mundo para validarlas
     */
    public void validarJuego( )
    {
        if(crucigrama.validarJuego(panelCrucigrama.obtenerJuegoUsuario()))
            JOptionPane.showMessageDialog( this, "Crucigrama armado correctamente", "Correcto", JOptionPane.INFORMATION_MESSAGE );
        else
            JOptionPane.showMessageDialog( this, "Error en el crucigrama", "Incorrecto", JOptionPane.ERROR_MESSAGE);
    }
    
    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = crucigrama.metodo1();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = crucigrama.metodo2();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }


    //-----------------------------------------------------------------
    // Main
    //-----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {
        InterfazCrucigrama interfaz = new InterfazCrucigrama();
        interfaz.setVisible( true );
    }


}