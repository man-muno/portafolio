/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Mu�oz - 19-Oct-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.cupEMart.mundo.CupEMart;
import uniandes.cupi2.cupEMart.mundo.Fecha;
import uniandes.cupi2.cupEMart.mundo.LineaProducto;
import uniandes.cupi2.cupEMart.mundo.LineaProductoExisteException;
import uniandes.cupi2.cupEMart.mundo.LineaProductoNoExisteException;
import uniandes.cupi2.cupEMart.mundo.PersistenciaException;
import uniandes.cupi2.cupEMart.mundo.Sucursal;
import uniandes.cupi2.cupEMart.mundo.SucursalExisteException;
import uniandes.cupi2.cupEMart.mundo.SucursalNoExisteException;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazCupEMart extends JFrame
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private CupEMart cupEMart;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel con las opciones sobre las sucursales y las l�neas de producto
     */
    private PanelOpciones panelOpciones;

    /**
     * Panel donde se muestra la lista de las sucursales
     */
    private PanelSucursales panelSucursales;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor de la ventana principal
     */
    public InterfazCupEMart( )
    {
        // Crea la clase principal
        try
        {
            cupEMart = new CupEMart( CupEMart.RUTA_ARCHIVO_PERSISTENCIA );

        }
        catch( PersistenciaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }

        // Construye la forma
        getContentPane( ).setLayout( new GridBagLayout( ) );
        setSize( 571, 499 );
        setTitle( "Cup-E-Mart" );
        setResizable( false );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        // Creaci�n de los paneles aqu�
        panelImagen = new PanelImagen( );
        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridwidth = 2;
        add( panelImagen, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 5, 5, 20, 5 );
        panelSucursales = new PanelSucursales( this );
        add( panelSucursales, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets( 5, 5, 20, 5 );
        constraint.fill = GridBagConstraints.BOTH;
        panelOpciones = new PanelOpciones( this );
        add( panelOpciones, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.gridwidth = 2;
        constraint.fill = GridBagConstraints.BOTH;
        panelExtension = new PanelExtension( this );
        add( panelExtension, constraint );

        actualizarInformacionPaneles( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que se llama cuando se quiere actualizar la lista de las sucursales del panel de sucursales
     */
    private void actualizarSucursales( )
    {
        panelSucursales.actualizarSucursales( cupEMart.darSucursales( ) );
    }

    /**
     * Actualiza la informaci�n de los paneles cuando se cargan los datos
     */
    private void actualizarInformacionPaneles( )
    {
        actualizarSucursales( );
        ArrayList nombres = cupEMart.darSucursales( );
        for( int i = 0; i < nombres.size( ); i++ )
        {
            String nombre = ( String )nombres.get( i );
            nombre = nombre.substring( 0, nombre.indexOf( " -" ) );
            actualizarListaLineaProductos( nombre );
        }
    }

    /**
     * M�todo que llama el modelo del mundo para agregar una sucursal
     * @param nombre Nombre de la nueva sucursal. Diferente de null
     * @param direccion Direcci�n de la nueva sucursal. Diferente de null
     */
    public void agregarSucursal( String nombre, String direccion )
    {
        try
        {
            cupEMart.agregarSucursal( nombre, direccion );
            actualizarSucursales( );
        }
        catch( SucursalExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * M�todo que llama el panel que contiene las sucursales, para que se muestren sus l�neas de producto
     * @param direccion Direcci�n de la sucursal. Diferente de null
     */
    public void actualizarListaLineaProductos( String direccion )
    {
        panelSucursales.actualizarLineaProductos( cupEMart.darLineasProductoSucursal( direccion ) );
    }

    /**
     * M�todo que llama al modelo del mundo para eliminar una sucursal que este seleccionada del panel de sucursales
     */
    public void eliminarSucursal( )
    {
        String direccionSucursal = panelSucursales.obtenerSucursalSeleccionada( );
        if( direccionSucursal != null )
        {
            try
            {
                cupEMart.eliminarSucursal( direccionSucursal );
                actualizarSucursales( );
            }
            catch( SucursalNoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar una sucursal para se eliminada", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * M�todo que crea y muestra el dialogo con las l�neas de productos de una sucursal seleccionada en el panel de sucursales
     */
    public void mostrarDialogoDescontinuarLineaProducto( )
    {
        String direccionSucursal = panelSucursales.obtenerSucursalSeleccionada( );
        if( direccionSucursal != null )
        {
            try
            {
                Sucursal sucursal = cupEMart.darSucursal( direccionSucursal );
                DialogoDescontinuarLineaProducto ventana = new DialogoDescontinuarLineaProducto( sucursal.darDireccion( ), sucursal.darLineasProducto( ), this );

            }
            catch( SucursalNoExisteException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar una sucursal para se eliminada", "Error", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * M�todo que llama al negocio para que discontin�e una l�nea de producto
     * @param direccionSucursal Direcc�n de la sucursal de la cual se quiere eliminar una l�nea de producto. Diferente de null
     * @param codigoProducto C�digo de la l�nea de producto que se quiere descontinuar
     */
    public void descontinuarProducto( String direccionSucursal, String codigoProducto )
    {
        try
        {
            cupEMart.descontinuarLineaProducto( direccionSucursal, codigoProducto );
            actualizarListaLineaProductos( direccionSucursal );
        }
        catch( LineaProductoNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( SucursalNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * M�todo que muestra el dialogo para el lanzamiento de una nueva l�nea de producto
     */
    public void mostrarDialogoLanzamientoProducto( )
    {
        String sucursal = panelSucursales.obtenerSucursalSeleccionada( );
        if( sucursal == null )
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar una sucursal", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            DialogoLanzamientoLineaProducto dialogoLanzamientoProducto = new DialogoLanzamientoLineaProducto( sucursal, this );
        }
    }

    /**
     * M�todo que se encarga de llamar al modelo del mundo para lanzar una l�nea de producto
     * @param sucursal Direcci�n de la sucursal de la cual se quiere lanzar la l�nea de producto. Diferente de null
     * @param nombre Nombre de la l�nea de producto que se quiere lanzar. Diferente de null
     * @param codigo C�digo de la l�nea de producto que se quiere lanzar. Diferente de null
     * @param descripcion Descripci�n de la l�nea de producto que se quiere lanzar. Diferente de null
     * @param precio Precio de la l�nea de producto que se quiere lanzar. Mayor que 0
     * @param fecha Fecha de la l�nea de producto que se quiere lanzar. Diferente de null
     * @throws SucursalNoExisteException Excepci�n lanzada cuando no se encuentra una sucursal con el nombre dado.
     * @throws LineaProductoExisteException Excepci�n que es lanzada cuando se trata de lanzar un producto con el mismo c�digo de un producto que ya existe.
     */
    public void lanzarProducto( String sucursal, String nombre, String codigo, String descripcion, int precio, Fecha fecha ) throws SucursalNoExisteException, LineaProductoExisteException
    {
        cupEMart.lanzarLineaProductoSucursal( sucursal, nombre, codigo, descripcion, precio, fecha );
        actualizarListaLineaProductos( sucursal );
    }

    /**
     * Este m�todo se encarga de salvar la informaci�n del hipermercado, justo antes de cerrar la aplicaci�n
     */
    public void dispose( )
    {
        try
        {
            cupEMart.guardarCupEMart( CupEMart.RUTA_ARCHIVO_PERSISTENCIA );
            super.dispose( );
        }
        catch( Exception e )
        {
            setVisible( true );
            int respuesta = JOptionPane.showConfirmDialog( this, "Problemas guardando la informaci�n de Cup-E-Mart:\n" + e.getMessage( ) + "\n�Quiere cerrar el programa sin salvar?", "Error", JOptionPane.YES_NO_OPTION );
            if( respuesta == JOptionPane.YES_OPTION )
            {
                super.dispose( );
            }
        }
    }

    /**
     * M�todo que se encarga de llamar al modelo del mundo y preguntar por la l�nea de producto m�s antigua y muestra un dialogo con esa informaci�n
     */
    public void mostrarMasAntiguo( )
    {
        LineaProducto producto;
        try
        {
            producto = cupEMart.darLineaProductoMasAntigua( );
            DialogoInfoLineaProducto infoProducto = new DialogoInfoLineaProducto( producto );
            infoProducto.setLocationRelativeTo( null );
            infoProducto.setVisible( true );
        }
        catch( LineaProductoNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * M�todo que se encarga de pedir el rango y llamar al modelo del mundo para eliminar las l�neas de productos que tienen un precio menor o igual al rango dado
     */
    public void eliminarDadoRango( )
    {
        String strInferior = JOptionPane.showInputDialog( this, "Ingrese el precio rango inferior: ", "Rango para eliminar Productos", JOptionPane.QUESTION_MESSAGE );
        String strSuperior = JOptionPane.showInputDialog( this, "Ingrese el precio rango superior: ", "Rango para eliminar Productos", JOptionPane.QUESTION_MESSAGE );
        if( strSuperior == null || strSuperior.trim( ).length( ) == 0 )
        {
            JOptionPane.showMessageDialog( this, "Debe ingresar alg�n valor", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try
            {
                int superior = Integer.parseInt( strSuperior );
                int inferior = Integer.parseInt( strInferior );
                if( superior <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "Debe ingresar un valor positivo para el limite superior", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else if( inferior <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "Debe ingresar un valor positivo para el limite inferior", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    int numProductosEliminados = cupEMart.eliminarLineasProductosDadoRango( inferior, superior );
                    if( numProductosEliminados == 1 )
                    {
                        JOptionPane.showMessageDialog( this, "Se elimin� " + numProductosEliminados + " producto", "Productos eliminados", JOptionPane.INFORMATION_MESSAGE );
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, "Se eliminaron " + numProductosEliminados + " productos", "Productos eliminados", JOptionPane.INFORMATION_MESSAGE );
                    }

                }
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un n�mero v�lido", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    /**
     * Abre el dialogo de creaci�n de sucursal
     */
    public void mostrarVentanaInfoSucursal( )
    {
        DialogoCreacionSucursal ventana = new DialogoCreacionSucursal( this );
        ventana.setLocationRelativeTo( this );
        ventana.setVisible( true );
    }

    /**
     * Muestra el dialogo con la informaci�n de una l�nea de producto
     * @param producto Producto que se quiere mostrar en el dialogo. Diferente de null
     */
    public void mostrarLineaProducto( LineaProducto producto )
    {
        DialogoInfoLineaProducto infoProducto = new DialogoInfoLineaProducto( producto );
        infoProducto.setLocationRelativeTo( null );
        infoProducto.setVisible( true );
    }

    /**
     * M�todo que pide al usuario el directorio y el nombre del archivo del reporte.
     */
    public void generarReporte( )
    {
        JFileChooser chooser = new JFileChooser( "./data" );
        int returnVal = chooser.showSaveDialog( this );
        if( returnVal == JFileChooser.APPROVE_OPTION )
        {
            String pathArchivo = chooser.getSelectedFile( ).getParent( );
            String nombreArchivo = chooser.getSelectedFile( ).getName( );
            if( !nombreArchivo.endsWith( ".txt" ) )
            {
                nombreArchivo = nombreArchivo + ".txt";
            }
            try
            {
                cupEMart.generarReporte( pathArchivo, nombreArchivo );
            }
            catch( FileNotFoundException e )
            {
                JOptionPane.showMessageDialog( this, "Problemas al crear el archivo del reporte", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = cupEMart.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = cupEMart.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazCupEMart interfaz = new InterfazCupEMart( );
        interfaz.setVisible( true );
    }
}