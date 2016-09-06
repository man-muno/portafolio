/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: generadorEjerciciosV2
 * Autor: Manuel Muñoz - May 4, 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.generador.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.generador.mundo.Archivo;

public class PanelAgregarArchivosExtra extends JPanel implements ActionListener, ListSelectionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que indica que el usuario quiere seleccionar un archivo
     */
    private static final String SELECCIONAR = "SELECCIONAR";

    /**
     * Constante que indica que el usuario quiere deseleccionar un archivo
     */
    private static final String DESELECCIONAR = "DESELECCIONAR";

    /**
     * Constante que indica que el usuario quiere aceptar la selección de archivos
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Constante que indica que el usuario quiere cancelar la selección de archivos
     */
    private static final String CANCELAR = "CANCELAR";

    /**
     * Constante que indica que el usuario quiere ver la ayuda
     */
    private static final String AYUDA = "AYUDA";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Dialogo padre que contiene este panel
     */
    private DialogoAgregarArchivosExtra dialogo;

    /**
     * Lista de los archivos para mostrar
     */
    private List archivosMostrar;

    /**
     * Lista de los archivos seleccionados
     */
    private List archivosSeleccionados;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    private JList lstDisponibles;
    private JScrollPane scrollDisponibles;
    private JList lstSeleccionados;
    private JScrollPane scrollSeleccionados;
    private JButton btnSeleccionar;
    private JButton btnDeseleccionar;
    private JCheckBox chkEditable;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JButton btnAyuda;

    /**
     * Construye el panel
     * @param extra Dialogo padre que contiene este panel
     * @param archivos Lista de archivos para mostrar *
     * @param archivosSeleccionados Lista de archivos que el usuario ha seleccionado anteriormente
     */
    public PanelAgregarArchivosExtra( DialogoAgregarArchivosExtra extra, List archivos, List archivosSeleccionados )
    {
        dialogo = extra;
        archivosMostrar = archivos;
        this.archivosSeleccionados = archivosSeleccionados;
        setLayout( new BorderLayout( ) );
        add( darPanelListas( ), BorderLayout.CENTER );
        add( darPanelBotones( ), BorderLayout.SOUTH );
        actualizarListas( );
    }

    /**
     * Retorna un panel con los botones de aceptar y cancelar, además tiene la opción de informar si un archivo se debe comportar como plantilla
     * @return El panel inferior
     */
    private JPanel darPanelBotones( )
    {
        JPanel panel = new JPanel( );
        panel.setLayout( new GridBagLayout( ) );

        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.setActionCommand( ACEPTAR );
        btnAceptar.addActionListener( this );
        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        chkEditable = new JCheckBox( "Plantilla?" );
        btnAyuda = new JButton( "?" );
        btnAyuda.setActionCommand( AYUDA );
        btnAyuda.addActionListener( this );

        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets( 5, 5, 10, 5 );

        panel.add( chkEditable, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets( 5, 5, 10, 5 );

        panel.add( btnAyuda, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets( 5, 5, 10, 5 );

        panel.add( btnAceptar, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets( 5, 5, 10, 5 );

        panel.add( btnCancelar, constraint );

        return panel;
    }

    /**
     * Panel que contiene las listas de los archivos y los botones para modificarlas
     * @return Panel con las listas de los archivos.
     */
    private JPanel darPanelListas( )
    {
        JPanel panel = new JPanel( );
        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridBagLayout( ) );
        panel.setLayout( new GridBagLayout( ) );

        lstDisponibles = new JList( archivosMostrar.toArray( ) );
        lstDisponibles.addListSelectionListener( this );
        scrollDisponibles = new JScrollPane( lstDisponibles );
        scrollDisponibles.setBorder( new TitledBorder( "Archivos Disponibles" ) );
        scrollDisponibles.setPreferredSize( new Dimension( 200, 150 ) );

        lstSeleccionados = new JList( );
        lstSeleccionados.addListSelectionListener( this );
        scrollSeleccionados = new JScrollPane( lstSeleccionados );
        scrollSeleccionados.setBorder( new TitledBorder( "Archivos Seleccionados" ) );
        scrollSeleccionados.setPreferredSize( new Dimension( 200, 150 ) );

        btnSeleccionar = new JButton( ">" );
        btnSeleccionar.addActionListener( this );
        btnSeleccionar.setActionCommand( SELECCIONAR );

        btnDeseleccionar = new JButton( "<" );
        btnDeseleccionar.addActionListener( this );
        btnDeseleccionar.setActionCommand( DESELECCIONAR );

        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.gridheight = 5;
        panel.add( scrollDisponibles, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets( 5, 5, 5, 5 );

        panelBotones.add( btnSeleccionar, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets( 5, 5, 5, 5 );

        panelBotones.add( btnDeseleccionar, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 4;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        panel.add( panelBotones, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 2;
        constraint.gridy = 0;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.gridheight = 5;

        panel.add( scrollSeleccionados, constraint );

        return panel;
    }

    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( comando.equals( CANCELAR ) )
        {
            dialogo.dispose( );
        }
        else if( comando.equals( AYUDA ) )
        {
            JOptionPane.showMessageDialog( dialogo, "Debe seleccionar el cuadro si el documento tiene algún encabezado que desee reemplazar con la informacion del ejercicio que está generando", "Informacion", JOptionPane.INFORMATION_MESSAGE );
        }
        else if( comando.equals( ACEPTAR ) )
        {
            dialogo.cambiarListaArchivosSeleccionados( archivosSeleccionados );
            dialogo.dispose( );
        }
        else if( comando.equals( SELECCIONAR ) )
        {
            seleccionarArchivo( );
        }
        else if( comando.equals( DESELECCIONAR ) )
        {
            deseleccionarArchivo( );
        }

    }

    /**
     * Evento que se llama cuando se selecciona algo sobre alguna de las dos listas
     */
    public void valueChanged( ListSelectionEvent e )
    {
        if( e.getValueIsAdjusting( ) )
        {
            if( e.getSource( ).equals( lstDisponibles ) )
            {
                chkEditable.setEnabled( true );
                chkEditable.setSelected( false );
                lstSeleccionados.clearSelection( );
            }
            else if( e.getSource( ).equals( lstSeleccionados ) )
            {
                chkEditable.setEnabled( false );
                chkEditable.setSelected( ( ( Archivo )lstSeleccionados.getSelectedValue( ) ).esEncabezadoModificable( ) );
                lstDisponibles.clearSelection( );
            }
        }
    }

    /**
     * Quita de la lista de archivos seleccionados el archivo que esta seleccionado y lo agrega a la lista de archivos para seleccionar
     */
    private void deseleccionarArchivo( )
    {
        Archivo archivo = ( Archivo )lstSeleccionados.getSelectedValue( );
        if( archivo != null )
        {
            archivosMostrar.add( archivo );
            archivosSeleccionados.remove( archivo );
            actualizarListas( );
        }
    }

    /**
     * Quita de la lista de archivos para seleccionar el archivo que se encuentra seleccionado actualmente y la coloca en la lista de los archivos seleccionados y tiene en
     * cuenta el checkBox de si es plantilla o no 
     */
    private void seleccionarArchivo( )
    {
        Archivo archivo = ( Archivo )lstDisponibles.getSelectedValue( );
        if( archivo != null )
        {
            archivo.cambiarEsModificable( chkEditable.isSelected( ) );
            archivosSeleccionados.add( archivo );
            archivosMostrar.remove( archivo );
            actualizarListas( );
        }
    }

    /**
     * Actualiza las JList de acuerdo a la informacion de las listas de los archivos
     */
    private void actualizarListas( )
    {
        for( int i = 0; i < archivosSeleccionados.size( ); i++ )
        {
            archivosMostrar.remove( archivosSeleccionados.get( i ) );
        }
        lstSeleccionados.removeAll( );
        lstDisponibles.removeAll( );
        lstSeleccionados.setListData( archivosSeleccionados.toArray( ) );
        lstDisponibles.setListData( archivosMostrar.toArray( ) );
    }

}
