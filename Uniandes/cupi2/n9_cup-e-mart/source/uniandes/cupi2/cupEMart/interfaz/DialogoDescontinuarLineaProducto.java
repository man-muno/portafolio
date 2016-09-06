/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoDescontinuarLineaProducto.java,v 1.2 2007/02/28 03:29:37 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Mu�oz - Oct 19, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.interfaz;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;

/**
 * Dialogo que muestra las l�neas de productos de una sucursal para descontinuar una de ellas.
 */
public class DialogoDescontinuarLineaProducto extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que contiene los elementos gr�ficos para mostrar las l�neas de productos
     */
    private PanelDescontinuarLineasProducto panelDescontinuar;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase que llam� el dialogo
     */
    private InterfazCupEMart padre;

    /**
     * Direcci�n de la sucursal a la cual se le quiere descontinuar un producto
     */
    private String direccionSucursal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros
     * @param nDireccion Direcci�n de la sucursal
     * @param lineas L�neas de producto de la sucursal
     * @param mart Clase que llam� el dialogo
     */
    public DialogoDescontinuarLineaProducto( String nDireccion, ArrayList lineas, InterfazCupEMart mart )
    {
        super( );
        padre = mart;
        direccionSucursal = nDireccion;

        setTitle( "Informaci�n de las L�neas de Productos" );
        setSize( new Dimension( 200, 244 ) );
        panelDescontinuar = new PanelDescontinuarLineasProducto( lineas, this );
        setContentPane( panelDescontinuar );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setVisible( true );
        setLocationRelativeTo( this );
    }

    /**
     * M�todo que es llamado por el panel para que se llame a la clase principal de la interfaz
     * @param codigoProducto C�digo del producto que se quiere descontinuar. Diferente de null
     */
    public void descontinuarProducto( String codigoProducto )
    {
        padre.descontinuarProducto( direccionSucursal, codigoProducto );
    }
}