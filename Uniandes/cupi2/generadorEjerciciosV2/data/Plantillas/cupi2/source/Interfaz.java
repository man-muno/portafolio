/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: [[NOMBRE_COMPLETO]]
 * Autor: [[AUTOR]] - [[FECHA]]
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package [[PAQUETE]].[[DIRECTORIO_INTERFAZ]];

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import [[PAQUETE]].[[DIRECTORIO_MUNDO]].[[CLASE_MUNDO]];


/**
 * Esta es la ventana principal de la aplicación.
 */
public class [[CLASE_INTERFAZ]] extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private [[CLASE_MUNDO]] [[NOMBRE_ATRIBUTO_MUNDO]];

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;
    
    /**
     * Panel donde se muestra la imagen encabezado de la aplicación
     */
    private PanelImagen panelImagen;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Descripción <br>
     * <b>post: </b> Descripción
     */
    public [[CLASE_INTERFAZ]]()
    {
        // Crea la clase principal
        [[NOMBRE_ATRIBUTO_MUNDO]] = new [[CLASE_MUNDO]]();
        
        // Construye la forma
        getContentPane().setLayout( new BorderLayout( ) );
        setSize( 530, 530 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        //Creación de los paneles aquí
        panelImagen = new PanelImagen();
        add( panelImagen, BorderLayout.NORTH );
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );
        centrar();
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
    
    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = [[NOMBRE_ATRIBUTO_MUNDO]].metodo1();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = [[NOMBRE_ATRIBUTO_MUNDO]].metodo2();
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

        [[CLASE_INTERFAZ]] interfaz = new [[CLASE_INTERFAZ]]();
        interfaz.setVisible( true );
    }

}