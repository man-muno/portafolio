/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoMostrarArbol.java,v 1.3 2007/05/04 22:46:47 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Mu�oz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JScrollPane;

import uniandes.cupi2.adivinaCual.mundo.AdivinaCual;

/**
 * Dialogo donde se muestra el �rbol
 */
public class DialogoMostrarArbol extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------
    private PanelArbol panelArbol;
    private PanelBotones panelBoton;
    private InterfazAdivinaCual principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros. Inicializa y coloca los componentes gr�ficos.
     * @param interfaz Ventana principal de la aplicaci�n. Diferente de null.
     * @param nInterpol Instancia del mundo. Diferente de null
     */
    public DialogoMostrarArbol( InterfazAdivinaCual interfaz, AdivinaCual adivina )
    {
        principal = interfaz;
        setLayout( new BorderLayout( ) );
        setTitle( "�rbol Respuesta" );
        panelArbol = new PanelArbol( adivina );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        add( new JScrollPane( panelArbol ), BorderLayout.CENTER );
        add( new PanelBotones( this, interfaz ), BorderLayout.SOUTH );
        setPreferredSize( new Dimension( 685, 400 ) );
        pack( );
        centrar( );
        panelArbol.actualizarImagen( );
        setVisible( true );
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
