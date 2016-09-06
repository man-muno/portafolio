/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoInformacionPrisionero.java,v 1.7 2007/01/22 07:08:48 f-vela Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Muñoz - Sep 15, 2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.prision.interfaz;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

import uniandes.cupi2.prision.mundo.Prisionero;

/**
 * Diálogo para mostrar la información de un prisionero
 */
public class DialogoInformacionPrisionero extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazPrision principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la información del prisionero
     */
    private PanelInformacionPrisionero panelInformacionPrisionero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo
     * @param ventana Ventana principal de la aplicación. ventana != null.
     * @param prisionero prisionero a mostrar
     * @param sector El sector al que pertenece el prisionero
     */
    public DialogoInformacionPrisionero( InterfazPrision ventana, Prisionero prisionero, String sector )
    {
        super( ventana, true );
        principal = ventana;
        setResizable( false );
        setSize( new Dimension( 230, 260 ) );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setTitle( "Información del Prisionero" );
        setLocationRelativeTo( principal );

        panelInformacionPrisionero = new PanelInformacionPrisionero( this, prisionero, sector );
        add( panelInformacionPrisionero );

        centrar();

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
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
     * Cierra el diálogo
     */
    public void cerrar( )
    {
        dispose( );
    }
}
