/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoInformacionSatelite.java,v 1.3 2007/06/28 22:46:45 camil-ji Exp $
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

import uniandes.cupi2.observatorio.mundo.SateliteNatural;

/**
 * Dialogo donde se muestran los detalles de un sat�lite natural
 */
public class DialogoInformacionSatelite extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Instancia principal de la aplicaci�n
     */
    private InterfazObservatorio principal;

    /**
     * Panel del dialogo
     */
    private PanelSatelite panelCuerpoCeleste;

    /**
     * Atributo que indica si se quiere usar el dialogo para creaci�n o edici�n.
     */
    private SateliteNatural satelite;

    /**
     * Nombre del planeta al que pertenece el sat�lite natural
     */
    private String nombrePlaneta;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor del dialogo
     * @param interfaz Interfaz principal de la aplicaci�n
     * @param natural Null para cuando se quiere mostrar el dialogo para creaci�n. Diferente de null para edici�n.
     * @param nombrePlaneta Nombre del planeta al que pertenece el sat�lite natural
     */
    public DialogoInformacionSatelite( InterfazObservatorio interfaz, SateliteNatural natural, String nNombrePlaneta )
    {
        principal = interfaz;
        satelite = natural;
        nombrePlaneta = nNombrePlaneta;
        panelCuerpoCeleste = new PanelSatelite( this, satelite );
        add( panelCuerpoCeleste );
        pack( );
        setLocationRelativeTo( interfaz );
        setTitle( "Sat�lite Natural" );
        setVisible( true );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que agrega un sat�lite natural al planeta
     * @param nombre Nombre del sat�lite natural. Diferente de null
     * @param excentricidad Excentricidad del sat�lite natural. Real mayor a cero.
     * @param inclinacion Inclinaci�n del sat�lite natural. Real mayor a cero.
     */
    public void agregarSateliteNatural( String nombre, double excentricidad, double inclinacion )
    {
        principal.agregarSateliteNatural( nombre, excentricidad, inclinacion );
    }

    /**
     * Llama el m�todo de editar un sat�lite natural.
     * @param nombre Nombre del sat�lite natural a editar. Diferente de null.
     * @param excentricidad Nuevo valor de la excentricidad del sat�lite natural. Real mayor a cero.
     * @param inclinacion Nuevo valor de la inclinaci�n del sat�lite natural. Real mayor a cero.
     */
    public void editarSateliteNatural( String nombre, double excentricidad, double inclinacion )
    {
        principal.editarSateliteNatural( nombre, excentricidad, inclinacion );
    }

    /**
     * Llama el m�todo de eliminar un sat�lite natural.
     * @param nombre Nombre del sat�lite natural a eliminar. Diferente de null.
     */
    public void eliminarSateliteNatural( String nombreSatelite )
    {
        principal.eliminarSateliteNatural( nombrePlaneta, nombreSatelite );
    }
}
