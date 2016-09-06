/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoDescontinuarLineaProducto.java,v 1.2 2007/02/28 03:29:37 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Muñoz - Oct 19, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.interfaz;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JDialog;

/**
 * Dialogo que muestra las líneas de productos de una sucursal para descontinuar una de ellas.
 */
public class DialogoDescontinuarLineaProducto extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que contiene los elementos gráficos para mostrar las líneas de productos
     */
    private PanelDescontinuarLineasProducto panelDescontinuar;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase que llamó el dialogo
     */
    private InterfazCupEMart padre;

    /**
     * Dirección de la sucursal a la cual se le quiere descontinuar un producto
     */
    private String direccionSucursal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros
     * @param nDireccion Dirección de la sucursal
     * @param lineas Líneas de producto de la sucursal
     * @param mart Clase que llamó el dialogo
     */
    public DialogoDescontinuarLineaProducto( String nDireccion, ArrayList lineas, InterfazCupEMart mart )
    {
        super( );
        padre = mart;
        direccionSucursal = nDireccion;

        setTitle( "Información de las Líneas de Productos" );
        setSize( new Dimension( 200, 244 ) );
        panelDescontinuar = new PanelDescontinuarLineasProducto( lineas, this );
        setContentPane( panelDescontinuar );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setVisible( true );
        setLocationRelativeTo( this );
    }

    /**
     * Método que es llamado por el panel para que se llame a la clase principal de la interfaz
     * @param codigoProducto Código del producto que se quiere descontinuar. Diferente de null
     */
    public void descontinuarProducto( String codigoProducto )
    {
        padre.descontinuarProducto( direccionSucursal, codigoProducto );
    }
}