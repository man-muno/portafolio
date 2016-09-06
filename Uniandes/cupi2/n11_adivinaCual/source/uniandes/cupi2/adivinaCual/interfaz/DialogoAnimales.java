/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Muñoz - Nov 7, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JDialog;

/**
 * Diálogo que muestra los animales que tiene la aplicación
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
     * Panel que contiene el botón y el comentario
     */
    private PanelBoton panelBoton;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazAdivinaCual principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros de la clase
     * @param padre Instancia de la ventana principal de la aplicación. Diferente de null
     * @param animales Lista de los animales que tiene la aplicación. Contiene objetos de tipo Pregunta. Diferente de null
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
     * Método que cierra el dialogo y muestra la ventana principal de la aplicación
     */
    public void mostrarInterfazPrincipal( )
    {
        principal.iniciarInterfazPrincipal( );
        dispose( );
    }
}
