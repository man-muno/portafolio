/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoComboPlanetas.java,v 1.3 2007/06/28 22:46:45 camil-ji Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Mu�oz - 13-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.interfaz;

import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Dialogo que muestra un combo con los planetas
 */
public class DialogoComboPlanetas extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia de la ventana principal de la aplicaci�n.
     */
    private InterfazObservatorio principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    private JPanel panel;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros del dialogo.
     */
    public DialogoComboPlanetas( InterfazObservatorio observatorio, String[] nombresPlanetas )
    {

        principal = observatorio;

        setTitle( "Consultar Planetas" );
        setLocationRelativeTo( null );
        panel = new PanelComboPlanetas( this, nombresPlanetas );
        add( panel );
        pack( );
        setVisible( true );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Llama a la ventana principal de la aplicaci�n para que consulte los planetas por inclinaci�n.
     */
    public void consultarPlanetasPorInclinacion( String planeta )
    {
        principal.consultarPlanetasPorInclinacion( planeta );
    }

}
