/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Muñoz - Oct 23, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.interfaz;

import javax.swing.JDialog;

import uniandes.cupi2.cupEMart.mundo.Fecha;
import uniandes.cupi2.cupEMart.mundo.LineaProductoExisteException;
import uniandes.cupi2.cupEMart.mundo.SucursalNoExisteException;

/**
 * Dialogo que es mostrado cuando se quieren ingresar datos para lanzar una nueva línea de producto
 */
public class DialogoLanzamientoLineaProducto extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con los elementos gráficos para el lanzamiento de una línea de producto
     */
    private PanelLanzamientoLineaProducto panelLanzamiento;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase quien llamó el dialogo
     */
    private InterfazCupEMart padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros
     * @param sucursal Dirección de la sucursal donde se quiere lanzar la nueva línea de producto. Diferente de null
     * @param mart Clase quien llamó al dialogo. Diferente de null
     */
    public DialogoLanzamientoLineaProducto( String sucursal, InterfazCupEMart mart )
    {
        padre = mart;
        setTitle( "Lanzamiento de Línea de Producto - " + sucursal );
        panelLanzamiento = new PanelLanzamientoLineaProducto( sucursal, this );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        add( panelLanzamiento );
        pack( );
        setLocationRelativeTo( this );
        setVisible( true );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que es llamado por el panel para que pueda invocar la clase principal a través de InterfazCupEMart
     * @param sucursal Nombre de la sucursal donde se quiere lanzar la línea de producto. Diferente de null
     * @param nombre Dirección del producto que se quiere lanzar. Diferente de null
     * @param codigo Código del producto que se quiere lanzar. Diferente de null
     * @param descripcion Descripción del producto que se quiere lanzar. Diferente de null
     * @param precio Precio del producto que se quiere lanzar. Entero mayor a cero
     * @param fecha Fecha de lanzamiento del producto que se quiere lanzar. Diferente de null
     * @throws SucursalNoExisteException Excepción lanzada cuando no se encuentra una sucursal con el nombre dado.
     * @throws LineaProductoExisteException Excepción que es lanzada cuando se trata de lanzar un producto con el mismo código de un producto que ya existe.
     */
    public void lanzarLineaProducto( String sucursal, String nombre, String codigo, String descripcion, int precio, Fecha fecha ) throws SucursalNoExisteException, LineaProductoExisteException
    {
        padre.lanzarProducto( sucursal, nombre, codigo, descripcion, precio, fecha );
    }
}