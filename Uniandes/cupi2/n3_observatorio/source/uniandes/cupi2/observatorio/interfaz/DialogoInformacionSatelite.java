/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoInformacionSatelite.java,v 1.3 2007/06/28 22:46:45 camil-ji Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Muñoz - 13-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.interfaz;

import javax.swing.JDialog;

import uniandes.cupi2.observatorio.mundo.SateliteNatural;

/**
 * Dialogo donde se muestran los detalles de un satélite natural
 */
public class DialogoInformacionSatelite extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Instancia principal de la aplicación
     */
    private InterfazObservatorio principal;

    /**
     * Panel del dialogo
     */
    private PanelSatelite panelCuerpoCeleste;

    /**
     * Atributo que indica si se quiere usar el dialogo para creación o edición.
     */
    private SateliteNatural satelite;

    /**
     * Nombre del planeta al que pertenece el satélite natural
     */
    private String nombrePlaneta;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor del dialogo
     * @param interfaz Interfaz principal de la aplicación
     * @param natural Null para cuando se quiere mostrar el dialogo para creación. Diferente de null para edición.
     * @param nombrePlaneta Nombre del planeta al que pertenece el satélite natural
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
        setTitle( "Satélite Natural" );
        setVisible( true );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que agrega un satélite natural al planeta
     * @param nombre Nombre del satélite natural. Diferente de null
     * @param excentricidad Excentricidad del satélite natural. Real mayor a cero.
     * @param inclinacion Inclinación del satélite natural. Real mayor a cero.
     */
    public void agregarSateliteNatural( String nombre, double excentricidad, double inclinacion )
    {
        principal.agregarSateliteNatural( nombre, excentricidad, inclinacion );
    }

    /**
     * Llama el método de editar un satélite natural.
     * @param nombre Nombre del satélite natural a editar. Diferente de null.
     * @param excentricidad Nuevo valor de la excentricidad del satélite natural. Real mayor a cero.
     * @param inclinacion Nuevo valor de la inclinación del satélite natural. Real mayor a cero.
     */
    public void editarSateliteNatural( String nombre, double excentricidad, double inclinacion )
    {
        principal.editarSateliteNatural( nombre, excentricidad, inclinacion );
    }

    /**
     * Llama el método de eliminar un satélite natural.
     * @param nombre Nombre del satélite natural a eliminar. Diferente de null.
     */
    public void eliminarSateliteNatural( String nombreSatelite )
    {
        principal.eliminarSateliteNatural( nombrePlaneta, nombreSatelite );
    }
}
