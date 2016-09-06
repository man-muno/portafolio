/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoDestinos.java,v 1.3 2007/04/20 12:38:40 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Mu�oz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.interpol.interfaz;

import java.util.List;

import javax.swing.JDialog;

/**
 * Contiene el panel donde se muestran los posibles destinos de viaje.
 */
public class DialogoDestinos extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    private PanelDestinos panelDestinos;
    private InterfazInterpol principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros. Inicializa y coloca los componentes gr�ficos.
     * @param interpol Ventana principal de la aplicaci�n. Diferente de null.
     * @param destinos Lista de los posibles destinos para avanzar. Diferente de null
     */
    public DialogoDestinos( InterfazInterpol interpol, List destinos )
    {
        principal = interpol;

        setTitle( "Posibles Destinos" );
        panelDestinos = new PanelDestinos( this, destinos );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        add( panelDestinos );
        pack( );
        setLocationRelativeTo( null );
        setVisible( true );
    }

    /**
     * Llama al mundo para cambiar la ciudad seleccionada.
     * @param nombreCiudad Nombre de la ciudad. Diferente de null.
     */
    public void cambiarCiudad( String nombreCiudad )
    {
        principal.viajar( nombreCiudad );
    }
}
