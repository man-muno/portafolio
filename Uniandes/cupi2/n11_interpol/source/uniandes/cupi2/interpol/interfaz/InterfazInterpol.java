/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazInterpol.java,v 1.3 2007/04/20 12:38:40 man-muno Exp $
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

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.interpol.mundo.Ciudad;
import uniandes.cupi2.interpol.mundo.Interpol;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazInterpol extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Interpol interpol;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen
     */
    private PanelImagen panelImagen;

    /**
     * Panel con el juego
     */
    private PanelJuego panelJuego;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación. Construye los componentes gráficos y los coloca<br>
     */
    public InterfazInterpol( )
    {
        // Crea la clase principal
        interpol = new Interpol( );
        setTitle( "Interpol" );

        // Construye la forma
        getContentPane( ).setLayout( new BorderLayout( ) );
        setSize( 759, 697 );
        setResizable( false );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

        // Creación de los paneles aquí
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelJuego = new PanelJuego( this );
        add( panelJuego, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Muestra el dialogo para seleccionar un archivo del juego.
     */
    public void iniciarJuego( )
    {
        try
        {
            // Seleccionar el archivo
            JFileChooser fc = new JFileChooser( "./data/" );
            fc.setDialogTitle( "Seleccionar" );
            int resultado = fc.showOpenDialog( this );
            if( resultado == JFileChooser.APPROVE_OPTION )
            {
                File seleccionado = fc.getSelectedFile( );
                Properties propiedades = new Properties( );
                propiedades.load( new FileInputStream( seleccionado ) );
                // Se crea el modelo
                interpol.cargarJuego( propiedades );
                mostrarCaso( );
                panelJuego.actualizar( );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Error al cargar el archivo " + e.getLocalizedMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra el dialogo con las pistas.
     */
    public void mostrarPistas( )
    {
        DialogoPistas dialogo = new DialogoPistas( this, interpol.darNombresLugaresCiudadActual( ) );
    }

    /**
     * Muestra el dialogo de las posibles ciudades a las cuales se puede viajar.
     */
    public void mostrarViajes( )
    {
        DialogoDestinos dialogo = new DialogoDestinos( this, interpol.darNombresPosiblesDestinos( ) );
    }

    /**
     * Muestra el caso en el que se esta trabajando actualmente
     */
    public void mostrarCaso( )
    {
        JOptionPane.showMessageDialog( this, interpol.darCaso( ), "Caso a Resolver", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Retorna la ciudad actual del mundo.
     * @return Diferente de null.
     */
    public Ciudad darCiudadActual( )
    {
        return interpol.darCiudadActual( );
    }

    /**
     * Retorna los días faltantes en el juego.
     * @return Entero mayor o igual a cero.
     */
    public int darDiasFaltantes( )
    {
        return interpol.darDiasFaltantes( );
    }

    /**
     * Muestra la pista de un lugar dado.
     * @param nombreLugar Nombre del lugar donde se quiere mostrar la pista.
     */
    public void mostrarPista( String nombreLugar )
    {
        JOptionPane.showMessageDialog( this, interpol.darPistaLugar( nombreLugar ), "Pista de " + nombreLugar, JOptionPane.INFORMATION_MESSAGE );
        panelJuego.actualizarDiasFaltantes( );
        verificarTerminacionJuego( );
    }

    /**
     * Cambia la ciudad que se quiere mostrar.
     * @param nombreCiudad Diferente de null.
     */
    public void viajar( String nombreCiudad )
    {
        interpol.viajar( nombreCiudad );
        panelJuego.actualizar( );
        verificarTerminacionJuego( );
    }

    /**
     * Muestra la interfaz de acuerdo al estado del mundo
     */
    private void verificarTerminacionJuego( )
    {
        if( !interpol.ordenCapturaGenerada( ) && interpol.sospechosoEncontrado( ) )
        {
            JOptionPane.showMessageDialog( this, "Encontró al sospechoso. \nPero no puede ser arrestado por la falta de la orden de captura.", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE );
            panelJuego.ocultarBotones( );
            mostrarDialogoArbol( );
        }
        else if( interpol.ordenCapturaGenerada( ) && interpol.sospechosoEncontrado( ) )
        {
            JOptionPane.showMessageDialog( this, "Sospechoso atrapado", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE );
            panelJuego.ocultarBotones( );
            mostrarDialogoArbol( );
        }
        else if( interpol.darDiasFaltantes( ) <= 0 || interpol.ciudadActualEsHoja( ) )
        {
            JOptionPane.showMessageDialog( this, "El sospechoso se escapó", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE );
            panelJuego.ocultarBotones( );
            mostrarDialogoArbol( );
        }
    }

    /**
     * Muestra el diálogo del árbol final
     */
    private void mostrarDialogoArbol( )
    {
        DialogoMostrarArbol dialogo = new DialogoMostrarArbol( this, interpol );
    }

    /**
     * Muestra el diálogo del recorrido en inorden
     */
    public void mostrarRecorridoInorden( )
    {
        DialogoRecorrido dialogo = new DialogoRecorrido( interpol.darInorden( ), "Recorrido Inorden" );
        dialogo.setVisible( true );
    }

    /**
     * Muestra el diálogo del recorrido en preorden
     */
    public void mostrarRecorridoPreorden( )
    {
        DialogoRecorrido dialogo = new DialogoRecorrido( interpol.darPreorden( ), "Recorrido Preorden" );
        dialogo.setVisible( true );
    }

    /**
     * Muestra el diálogo del recorrido en postorden
     */
    public void mostrarRecorridoPostorden( )
    {
        DialogoRecorrido dialogo = new DialogoRecorrido( interpol.darPostorden( ), "Recorrido Postorden" );
        dialogo.setVisible( true );
    }

    /**
     * Muestra el diálogo de la altura del árbol
     */
    public void mostrarAltura( )
    {
        int altura = interpol.darAltura( );
        JOptionPane.showMessageDialog( this, "La altura del árbol es: " + altura, "Altura del Árbol", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra el diálogo del peso del árbol
     */
    public void mostrarPeso( )
    {
        int peso = interpol.darPeso( );
        JOptionPane.showMessageDialog( this, "El peso del árbol es: " + peso, "Peso del Árbol", JOptionPane.INFORMATION_MESSAGE );
    }

    public void mostrarDialogoExpedirOrdenCaptura( )
    {
        DialogoSospechoso dialogo = new DialogoSospechoso( this, interpol.darSospechosos( ) );
    }

    public void generarOrdenCaptura( int i )
    {
        if( interpol.generarOrdenCaptura( i ) )
        {
            JOptionPane.showMessageDialog( this, "Orden de captura generada. Ahora tiene que atrapar al sospechoso", "Orden de Captura", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            panelJuego.ocultarBotones( );
            mostrarDialogoArbol( );
            JOptionPane.showMessageDialog( this, "El sospechoso seleccionado no es correcto", "Orden de Captura", JOptionPane.INFORMATION_MESSAGE );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = interpol.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = interpol.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {
        InterfazInterpol interfaz = new InterfazInterpol( );
        interfaz.setVisible( true );
    }
}