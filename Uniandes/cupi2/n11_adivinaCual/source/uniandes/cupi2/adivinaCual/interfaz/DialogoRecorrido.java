/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Muñoz - Nov 9, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;

/**
 * Diálogo donde se muestran los recorridos sobre el árbol
 */
public class DialogoRecorrido extends JDialog
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros de la clase.
     * @param recorrido Lista que contiene los elementos organizados de acuerdo al recorrido solicitado. Diferente de null
     * @param titulo Titulo que tendrá la ventana. Diferente de null
     */
    public DialogoRecorrido( List recorrido, String titulo )
    {
        setLayout( new BorderLayout( ) );
        setTitle( titulo );
        setLocationRelativeTo( null );
        setSize( new Dimension( 300, 350 ) );
        setModal( true );
        PanelRecorrido panelRecorrido = new PanelRecorrido( recorrido, this );
        centrar( );
        add( panelRecorrido, BorderLayout.CENTER );
    }

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

}
