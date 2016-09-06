/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Mu�oz - Oct 24, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.interfaz;

import javax.swing.JDialog;

import uniandes.cupi2.cupEMart.mundo.LineaProducto;

/**
 * Dialogo que aparece cuando se quiere mostrar la informaci�n de una l�nea de producto
 */
public class DialogoInfoLineaProducto extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel donde se mostrara la informaci�n de la l�nea de producto
     */
    private PanelInfoLineaProducto panelInfo;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * L�nea de producto que quiere ser mostrada
     */
    private LineaProducto lineaProducto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros
     * @param nProducto L�nea de producto que quiere ser mostrada. Diferente de null
     */
    public DialogoInfoLineaProducto( LineaProducto nProducto )
    {
        lineaProducto = nProducto;
        setTitle( "L�nea Producto" );
        setDefaultCloseOperation( JDialog.EXIT_ON_CLOSE );
        panelInfo = new PanelInfoLineaProducto( lineaProducto, this );
        add( panelInfo );

        pack( );
    }
}