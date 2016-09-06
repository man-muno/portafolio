/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Mu�oz - Oct 23, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.interfaz;

import javax.swing.JDialog;

import uniandes.cupi2.cupEMart.mundo.Fecha;
import uniandes.cupi2.cupEMart.mundo.LineaProductoExisteException;
import uniandes.cupi2.cupEMart.mundo.SucursalNoExisteException;

/**
 * Dialogo que es mostrado cuando se quieren ingresar datos para lanzar una nueva l�nea de producto
 */
public class DialogoLanzamientoLineaProducto extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con los elementos gr�ficos para el lanzamiento de una l�nea de producto
     */
    private PanelLanzamientoLineaProducto panelLanzamiento;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase quien llam� el dialogo
     */
    private InterfazCupEMart padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros
     * @param sucursal Direcci�n de la sucursal donde se quiere lanzar la nueva l�nea de producto. Diferente de null
     * @param mart Clase quien llam� al dialogo. Diferente de null
     */
    public DialogoLanzamientoLineaProducto( String sucursal, InterfazCupEMart mart )
    {
        padre = mart;
        setTitle( "Lanzamiento de L�nea de Producto - " + sucursal );
        panelLanzamiento = new PanelLanzamientoLineaProducto( sucursal, this );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        add( panelLanzamiento );
        pack( );
        setLocationRelativeTo( this );
        setVisible( true );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que es llamado por el panel para que pueda invocar la clase principal a trav�s de InterfazCupEMart
     * @param sucursal Nombre de la sucursal donde se quiere lanzar la l�nea de producto. Diferente de null
     * @param nombre Direcci�n del producto que se quiere lanzar. Diferente de null
     * @param codigo C�digo del producto que se quiere lanzar. Diferente de null
     * @param descripcion Descripci�n del producto que se quiere lanzar. Diferente de null
     * @param precio Precio del producto que se quiere lanzar. Entero mayor a cero
     * @param fecha Fecha de lanzamiento del producto que se quiere lanzar. Diferente de null
     * @throws SucursalNoExisteException Excepci�n lanzada cuando no se encuentra una sucursal con el nombre dado.
     * @throws LineaProductoExisteException Excepci�n que es lanzada cuando se trata de lanzar un producto con el mismo c�digo de un producto que ya existe.
     */
    public void lanzarLineaProducto( String sucursal, String nombre, String codigo, String descripcion, int precio, Fecha fecha ) throws SucursalNoExisteException, LineaProductoExisteException
    {
        padre.lanzarProducto( sucursal, nombre, codigo, descripcion, precio, fecha );
    }
}