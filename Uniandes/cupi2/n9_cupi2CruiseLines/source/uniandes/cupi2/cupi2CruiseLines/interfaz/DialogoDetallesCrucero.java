/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoDetallesCrucero.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupi2CruiseLines
 * Autor: Manuel Muñoz - 13-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupi2CruiseLines.interfaz;

import javax.swing.JDialog;

import uniandes.cupi2.cupi2CruiseLines.mundo.Crucero;

/**
 * Dialogo que es mostrado cuando se quieren mostrar los detalles de un crucero
 */
public class DialogoDetallesCrucero extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con los elementos gráficos para ver los detalles del crucero
     */
    private PanelDetallesCrucero panelDetallesCrucero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros
     * @param crucero Crucero al cual se le quieren mostrar los detalles. Diferente de null.
     */
    public DialogoDetallesCrucero( Crucero crucero )
    {
        setTitle( "Detalles del Crucero" );
        panelDetallesCrucero = new PanelDetallesCrucero( this, crucero );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        add( panelDetallesCrucero );
        pack( );
        setLocationRelativeTo( this );
        setVisible( true );
    }

}