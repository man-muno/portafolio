/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Mu�oz - Nov 7, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JDialog;

/**
 * Di�logo que muestra los animales que tiene la aplicaci�n
 */
public class DialogoAnimales extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------
    /**
     * Panel donde se muestran los animales
     */
    private PanelAnimales panelAnimales;

    /**
     * Panel que contiene el bot�n y el comentario
     */
    private PanelBoton panelBoton;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazAdivinaCual principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros de la clase
     * @param padre Instancia de la ventana principal de la aplicaci�n. Diferente de null
     * @param animales Lista de los animales que tiene la aplicaci�n. Contiene objetos de tipo Pregunta. Diferente de null
     */
    public DialogoAnimales( InterfazAdivinaCual padre, List animales )
    {
        principal = padre;
        setLayout( new BorderLayout( ) );
        setSize( new Dimension( 519, 530 ) );
        setLocationRelativeTo( null );
        setTitle( "Lista de animales" );
        setDefaultCloseOperation( JDialog.DO_NOTHING_ON_CLOSE );
        panelAnimales = new PanelAnimales( animales );
        panelBoton = new PanelBoton( this );
        add( panelAnimales, BorderLayout.CENTER );
        add( panelBoton, BorderLayout.SOUTH );
    }

    /**
     * M�todo que cierra el dialogo y muestra la ventana principal de la aplicaci�n
     */
    public void mostrarInterfazPrincipal( )
    {
        principal.iniciarInterfazPrincipal( );
        dispose( );
    }
}
