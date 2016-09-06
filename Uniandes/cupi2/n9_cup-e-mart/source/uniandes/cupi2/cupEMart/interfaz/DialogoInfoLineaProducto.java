/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Muñoz - Oct 24, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.interfaz;

import javax.swing.JDialog;

import uniandes.cupi2.cupEMart.mundo.LineaProducto;

/**
 * Dialogo que aparece cuando se quiere mostrar la información de una línea de producto
 */
public class DialogoInfoLineaProducto extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel donde se mostrara la información de la línea de producto
     */
    private PanelInfoLineaProducto panelInfo;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Línea de producto que quiere ser mostrada
     */
    private LineaProducto lineaProducto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros
     * @param nProducto Línea de producto que quiere ser mostrada. Diferente de null
     */
    public DialogoInfoLineaProducto( LineaProducto nProducto )
    {
        lineaProducto = nProducto;
        setTitle( "Línea Producto" );
        setDefaultCloseOperation( JDialog.EXIT_ON_CLOSE );
        panelInfo = new PanelInfoLineaProducto( lineaProducto, this );
        add( panelInfo );

        pack( );
    }
}