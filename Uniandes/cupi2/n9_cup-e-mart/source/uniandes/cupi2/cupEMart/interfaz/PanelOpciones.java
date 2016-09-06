/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel donde se muestran los botones de las opciones sobre las sucursales y sobre las l�neas de producto
 */
public class PanelOpciones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Acci�n para asociar al bot�n de agregar una nueva sucursal
     */
    public static final String AGREGAR_SUCURSAL = "AGREGAR_SUCURSAL";

    /**
     * Acci�n para asociar al bot�n de eliminar una nueva sucursal
     */
    public static final String ELIMINAR_SUCURSAL = "ELIMINAR_SUCURSAL";

    /**
     * Acci�n para asociar al bot�n de descontinuar una l�nea de producto
     */
    public static final String DESCONTINUAR_PRODUCTO = "DESCONTINUAR_PRODUCTO";

    /**
     * Acci�n para asociar al bot�n de lanzar una l�nea de producto
     */
    public static final String LANZAR_PRODUCTO = "LANZAR_PRODUCTO";

    /**
     * Acci�n para asociar al bot�n de mostrar la l�nea de producto m�s antigua
     */
    public static final String MOSTRAR_MAS_ANTIGUO = "MOSTRAR_MAS_ANTIGUO";

    /**
     * Acci�n para asociar al bot�n de eliminar unas l�nea de producto de acuerdo al rango
     */
    public static final String ELIMINAR_RANGO = "ELIMINAR_RANGO";

    /**
     * Accion para generar un reporte de las sucursales y l�neas de producto de la aplicaci�n
     */
    public static final String GENERAR_REPORTE = "GENERAR_REPORTE";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n para agregar una sucursal
     */
    private JButton btnAgregarSucursal;

    /**
     * Bot�n para eliminar una sucursal
     */
    private JButton btnEliminarSucursal;

    /**
     * Bot�n para descontinuar una l�nea de producto
     */
    private JButton btndescontinuarProducto;

    /**
     * Bot�n para lanzar una l�nea de producto
     */
    private JButton btnLanzarProducto;

    /**
     * Bot�n para mostrar la l�nea de producto m�s antigua
     */
    private JButton btnProductoMasAntiguo;

    /**
     * Bot�n para eliminar unas l�neas de producto de acuerdo a un rango
     */
    private JButton btnEliminarRango;

    /**
     * Bot�n para generar el reporte de la aplicaci�n
     */
    private JButton btnGenerarReporte;

    /**
     * Clase principal de la interfaz
     */
    private InterfazCupEMart tienda;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros
     * @param nTienda Clase principal de la interfaz. Diferente de null
     * 
     */
    public PanelOpciones( InterfazCupEMart nTienda )
    {
        tienda = nTienda;
        setLayout( new GridBagLayout( ) );
        GridBagConstraints constraint = null;

        btnAgregarSucursal = new JButton( "Agregar Sucursal" );
        btnAgregarSucursal.setActionCommand( AGREGAR_SUCURSAL );
        btnAgregarSucursal.addActionListener( this );
        btnAgregarSucursal.setPreferredSize( new Dimension( 230, 25 ) );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( btnAgregarSucursal, constraint );

        btnEliminarSucursal = new JButton( "Eliminar Sucursal" );
        btnEliminarSucursal.setActionCommand( ELIMINAR_SUCURSAL );
        btnEliminarSucursal.addActionListener( this );
        btnEliminarSucursal.setPreferredSize( new Dimension( 170, 25 ) );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( btnEliminarSucursal, constraint );

        btnLanzarProducto = new JButton( "Lanzar L�nea de Producto" );
        btnLanzarProducto.setActionCommand( LANZAR_PRODUCTO );
        btnLanzarProducto.setPreferredSize( new Dimension( 170, 25 ) );
        btnLanzarProducto.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( btnLanzarProducto, constraint );

        btndescontinuarProducto = new JButton( "Descontinuar L�nea de Producto" );
        btndescontinuarProducto.setActionCommand( DESCONTINUAR_PRODUCTO );
        btndescontinuarProducto.addActionListener( this );
        btndescontinuarProducto.setPreferredSize( new Dimension( 170, 25 ) );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( btndescontinuarProducto, constraint );

        btnProductoMasAntiguo = new JButton( "L�nea de Producto m�s Antigua" );
        btnProductoMasAntiguo.setActionCommand( MOSTRAR_MAS_ANTIGUO );
        btnProductoMasAntiguo.setPreferredSize( new Dimension( 170, 25 ) );
        btnProductoMasAntiguo.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 4;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( btnProductoMasAntiguo, constraint );

        btnEliminarRango = new JButton( "Eliminar dado un Rango" );
        btnEliminarRango.setActionCommand( ELIMINAR_RANGO );
        btnEliminarRango.setPreferredSize( new Dimension( 170, 25 ) );
        btnEliminarRango.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 5;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( btnEliminarRango, constraint );

        btnGenerarReporte = new JButton( "Generar Reporte" );
        btnGenerarReporte.setActionCommand( GENERAR_REPORTE );
        btnGenerarReporte.setPreferredSize( new Dimension( 170, 25 ) );
        btnGenerarReporte.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 6;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 10, 0, 0, 0 );
        add( btnGenerarReporte, constraint );

    }

    // -----------------------------------------------------------------
    // M�todos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Se ejecuta una acci�n cuando se hace click en uno de los botones
     * @param e El evento del click en un bot�n- evento != null
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( AGREGAR_SUCURSAL ) )
        {
            tienda.mostrarVentanaInfoSucursal( );
        }
        else if( e.getActionCommand( ).equals( ELIMINAR_SUCURSAL ) )
        {
            tienda.eliminarSucursal( );
        }
        else if( e.getActionCommand( ).equals( DESCONTINUAR_PRODUCTO ) )
        {
            tienda.mostrarDialogoDescontinuarLineaProducto( );
        }
        else if( e.getActionCommand( ).equals( LANZAR_PRODUCTO ) )
        {
            tienda.mostrarDialogoLanzamientoProducto( );
        }
        else if( e.getActionCommand( ).equals( MOSTRAR_MAS_ANTIGUO ) )
        {
            tienda.mostrarMasAntiguo( );
        }
        else if( e.getActionCommand( ).equals( ELIMINAR_RANGO ) )
        {
            tienda.eliminarDadoRango( );
        }
        else if( e.getActionCommand( ).equals( GENERAR_REPORTE ) )
        {
            tienda.generarReporte( );
        }
    }
}