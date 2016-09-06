/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazTiendaMascotas.java,v 1.5 2007/06/25 19:02:31 camil-ji Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_tiendaMascotas
 * Autor: Manuel Muñoz - 08-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.tiendaMascotas.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.tiendaMascotas.mundo.TiendaMascotas;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazTiendaMascotas extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private TiendaMascotas tiendaMascotas;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con una imagen de decoración
     */
    private PanelImagen panelImagen;

    /**
     * Panel de la cacatúa
     */
    private PanelMascota panelCacatua;

    /**
     * Panel de la boa
     */
    private PanelMascota panelBoa;

    /**
     * Panel del tití
     */
    private PanelMascota panelTiti;

    /**
     * Panel de la guacamaya
     */
    private PanelMascota panelGuacamaya;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Descripción <br>
     * <b>post: </b> Descripción
     */
    public InterfazTiendaMascotas( )
    {
        // Crea la clase principal
        tiendaMascotas = new TiendaMascotas( );
        // organizar el panel principal
        setLayout( new BorderLayout( ) );
        setSize( 736, 600 );
        setTitle( "Tienda de Mascotas" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        setLocationRelativeTo( null );
        setResizable( false );

        // Paneles Centrales
        JPanel panelCentral = new JPanel( );
        panelCentral.setLayout( new GridLayout( 2, 2 ) );
        add( panelCentral, BorderLayout.CENTER );

        // Panel de la imagen
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        // Panel mascota 1
        panelCacatua = new PanelMascota( this, TiendaMascotas.NOMBRE_MASCOTA_1 );
        panelCentral.add( panelCacatua );

        // Panel mascota 2
        panelBoa = new PanelMascota( this, TiendaMascotas.NOMBRE_MASCOTA_2 );
        panelCentral.add( panelBoa );

        // Panel mascota 3
        panelTiti = new PanelMascota( this, TiendaMascotas.NOMBRE_MASCOTA_3 );
        panelCentral.add( panelTiti );

        // Panel mascota 4
        panelGuacamaya = new PanelMascota( this, TiendaMascotas.NOMBRE_MASCOTA_4 );
        panelCentral.add( panelGuacamaya );

        // Panel Extensiones
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        actualizarPanelesMascotas( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza la información que se encuentra en los paneles de las mascotas
     */
    private void actualizarPanelesMascotas( )
    {
        panelBoa.actualizar( tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_2 ) );
        panelCacatua.actualizar( tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_1 ) );
        panelGuacamaya.actualizar( tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_4 ) );
        panelTiti.actualizar( tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_3 ) );
    }

    /**
     * Método que se encarga de obtener la información para vender una mascota
     */
    public void venderMascota( String nombreMascota, int cantidad )
    {
        if( tiendaMascotas.obtenerCantidadActual( nombreMascota ) >= cantidad )
        {
            tiendaMascotas.venderMascota( nombreMascota, cantidad );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No se puede vender la mascota ya que no hay existencias", "Error", JOptionPane.ERROR_MESSAGE );
        }
        actualizarPanelesMascotas( );
    }

    /**
     * Método que se encarga de obtener la información para comprar una mascota
     */
    public void comprarMascota( String nombreMascota, int cantidad )
    {
        tiendaMascotas.comprarMascota( nombreMascota, cantidad );
        actualizarPanelesMascotas( );
    }

    /**
     * Método que muestra en la interfaz la mascota más vendida
     */
    public void mostrarMasVendida( )
    {
        JOptionPane.showMessageDialog( this, "La mascota más vendida es: " + tiendaMascotas.obtenerMascotaMasVendida( ), "Mascota mas vendida", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Este método se encarga de mostrar el dialogo que recoge la información para que el usuario escoja una mascota
     */
    public void mostrarDialogoSeleccionarMascota( )
    {
        DialogoSeleccionarMascota dialogo = new DialogoSeleccionarMascota( this );
        dialogo.setVisible( true );
    }

    /**
     * Se encarga de llamar al mundo para saber cuáles de las mascotas cumplen los criterios de búsqueda
     * @param sexo Sexo que el usuario quiere que tenga la mascota. Diferente de null
     * @param edad Edad máxima que el usuario quiere que tenga la mascota. Entero mayor o igual a cero
     * @param precio Precio máximo que el usuario quiere que tenga la mascota. Entero mayor a cero
     */
    public void seleccionarMascota( String sexo, int edad, int precio )
    {
        JOptionPane.showMessageDialog( this, tiendaMascotas.seleccionarMascota( sexo, edad, precio ), "Resultados", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = tiendaMascotas.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = tiendaMascotas.metodo2( );
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
        InterfazTiendaMascotas interfaz = new InterfazTiendaMascotas( );
        interfaz.setVisible( true );
    }
}