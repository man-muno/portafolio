/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoAgregarCrucero.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupi2CruiseLines
 * Autor: Manuel Mu�oz - 13-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupi2CruiseLines.interfaz;

import java.util.Date;

import javax.swing.JDialog;

/**
 * Dialogo que es mostrado cuando se quieren ingresar datos para lanzar una nueva l�nea de producto
 */
public class DialogoAgregarCrucero extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con los elementos gr�ficos para el lanzamiento de una l�nea de producto
     */
    private PanelAgregarCrucero panelLanzamiento;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase quien llam� el dialogo
     */
    private InterfazCupi2CruiseLines padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros
     * @param sucursal Direcci�n de la sucursal donde se quiere lanzar la nueva l�nea de producto. Diferente de null
     * @param cruise Clase quien llam� al dialogo. Diferente de null
     */
    public DialogoAgregarCrucero( InterfazCupi2CruiseLines cruise )
    {
        padre = cruise;
        setTitle( "Agregar Nuevo Crucero" );
        panelLanzamiento = new PanelAgregarCrucero( this );
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
     * Llama a la interfaz principal para agregar un crucero
     * @param nombre Nombre del crucero a agregar. Diferente de null.
     * @param numDias Numero de dios que dura el crucero. Entero mayor a cero.
     * @param precio Precio en d�lares por persona. Entero mayor a cero.
     * @param fecha Fecha de zarpado del crucero. Diferente de null.
     */
    public void agregarCrucero( String nombre, int numDias, int precio, Date fecha )
    {
        padre.agregarCrucero( nombre, numDias, precio, fecha );
    }
}