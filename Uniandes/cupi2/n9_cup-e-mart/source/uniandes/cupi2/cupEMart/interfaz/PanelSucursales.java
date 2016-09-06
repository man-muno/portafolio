/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.cupEMart.mundo.LineaProducto;

/**
 * Panel donde se muestra la lista de sucursales del hipermercado
 */
public class PanelSucursales extends JPanel implements ActionListener, ListSelectionListener
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    private JComboBox comboSucursales;

    /**
     * Lista donde se colocaran los productos de las sucursales
     */
    private JList listaProductos;

    /**
     * JScrollPane de la tabla
     */
    private JScrollPane scroll;

    /**
     * Ventana principal de la aplicación.
     */
    InterfazCupEMart principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por defecto. Inicializa y coloca los componentes gráficos en el panel
     * @param nPrincipal Instancia de la interfaz principal de la aplicación
     */
    public PanelSucursales( InterfazCupEMart nPrincipal )
    {
        principal = nPrincipal;
        TitledBorder borde = ( TitledBorder )BorderFactory.createTitledBorder( "Sucursales" );
        borde.setTitleColor( Color.BLACK );
        setBorder( borde );

        comboSucursales = new JComboBox( );
        comboSucursales.setPreferredSize( new Dimension( 250, 20 ) );
        comboSucursales.addActionListener( this );

        listaProductos = new JList( );
        listaProductos.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        listaProductos.addListSelectionListener( this );
        setLayout( new GridBagLayout( ) );
        scroll = new JScrollPane( listaProductos );

        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 5, 20, 5, 20 );
        add( comboSucursales, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets( 5, 20, 5, 20 );
        constraint.fill = GridBagConstraints.BOTH;
        add( scroll, constraint );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que quita las sucursales del panel y coloca las que se encuentran en el modelo
     * @param sucursales Lista de objeto de String con la información de las sucursales
     */
    public void actualizarSucursales( ArrayList sucursales )
    {
        comboSucursales.removeAllItems( );
        for( int i = 0; i < sucursales.size( ); i++ )
        {
            comboSucursales.addItem( ( ( String )sucursales.get( i ) ) );
        }
        comboSucursales.setSelectedIndex( 0 );
    }

    /**
     * Método que retorna el nombre del sucursal que se selecciono en la lista
     * @return String con el nombre de la sucursal. Puede ser null en caso que no se haya seleccionado nada
     */
    public String obtenerSucursalSeleccionada( )
    {
        String nombre = ( String )comboSucursales.getSelectedItem( );
        if( nombre != null )
            nombre = nombre.substring( 0, nombre.indexOf( " - " ) );
        return nombre;
    }

    /**
     * Manejo de los eventos del combo
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String direccion = obtenerSucursalSeleccionada( );
        principal.actualizarListaLineaProductos( direccion );
    }

    /**
     * Actualiza los valores que van en la lista.
     * @param lineaProductos. Lista de elementos de tipo String. No es nula pero puede ser vacía.
     */
    public void actualizarLineaProductos( ArrayList lineaProductos )
    {
        listaProductos.setListData( lineaProductos.toArray( ) );
        comboSucursales.setSelectedIndex( 0 );
    }

    /**
     * Manejo de los eventos de la lista
     */
    public void valueChanged( ListSelectionEvent e )
    {
        LineaProducto productoSeleccionado = ( LineaProducto )listaProductos.getSelectedValue( );
        if( productoSeleccionado != null && !e.getValueIsAdjusting( ) )
        {
            principal.mostrarLineaProducto( productoSeleccionado );
        }
    }
}