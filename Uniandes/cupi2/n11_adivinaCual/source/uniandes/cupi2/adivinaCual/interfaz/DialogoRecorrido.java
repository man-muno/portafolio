/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Mu�oz - Nov 9, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;

/**
 * Di�logo donde se muestran los recorridos sobre el �rbol
 */
public class DialogoRecorrido extends JDialog
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros de la clase.
     * @param recorrido Lista que contiene los elementos organizados de acuerdo al recorrido solicitado. Diferente de null
     * @param titulo Titulo que tendr� la ventana. Diferente de null
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
     * Centra el di�logo en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

}
