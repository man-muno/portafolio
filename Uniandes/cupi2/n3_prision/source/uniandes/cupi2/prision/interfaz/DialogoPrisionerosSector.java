/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoPrisionerosSector.java,v 1.4 2006/11/26 22:13:00 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Mu�oz - Sep 15, 2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JDialog;

/**
 * Dialogo de visualizaci�n de prisioneros de un sector
 */
public class DialogoPrisionerosSector extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la tabla
     */
    private PanelTabla panelTabla;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del dialogo
     * @param ventanaPrincipal Ventana principal de la aplicaci�n
     * @param prisioneros La lista de prisioneros a mostrar
     */
    public DialogoPrisionerosSector( InterfazPrision ventanaPrincipal, ArrayList prisioneros )
    {
        super( ventanaPrincipal, true );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );        
        setResizable( false );
        setTitle( "Prisioneros" );

        // Establece los prisioneros en la tabla
        panelTabla = new PanelTabla( this );
        panelTabla.establecerPrisioneros( prisioneros );
        add( panelTabla, BorderLayout.CENTER );
        pack( );
        centrar();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Centra el di�logo en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }
    
    /**
     * Cierra el di�logo
     */
    public void cerrar( )
    {
        dispose( );
    }
}
