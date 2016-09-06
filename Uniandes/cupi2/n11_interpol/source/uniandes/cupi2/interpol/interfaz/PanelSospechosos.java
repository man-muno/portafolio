package uniandes.cupi2.interpol.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import uniandes.cupi2.interpol.mundo.Sospechoso;

public class PanelSospechosos extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Acción asociada al botón aceptar
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Acción asociada al botón cancelar
     */

    private static final String CANCELAR = "CANCELAR";

    /**
     * Acción asociada al momento de seleccionar un sospechoso
     */
    private static final String SOSPECHOSO_CAMBIADO = "SOSPECHOSO_CAMBIADO";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Botón de aceptar
     */
    private JButton btnAceptar;

    /**
     * Botón de cancelar
     */
    private JButton btnCancelar;

    /**
     * Grupo para colocar todos los radio buttons y que solo se pueda escoger uno
     */
    private ButtonGroup grupo;

    /**
     * Instancia del dialogo padre.
     */
    private DialogoSospechoso padre;

    /**
     * Panel donde se colocaran los JRadioButton seguido del nombre de la línea de producto
     */
    private JPanel panel;

    /**
     * Etiqueta donde se coloca la imagen del sospechoso
     */
    private JLabel lblImagen;

    /**
     * Lista de los sospechosos a mostrar
     */
    private List sospechosos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros del panel.
     * @param dialogo Dialogo que contiene el panel. Diferente de null.
     * @param nSospechosos Lista que contiene las ciudades a ser eliminadas. Diferente de null pero puede ser vacía.
     */
    public PanelSospechosos( DialogoSospechoso dialogo, List nSospechosos )
    {
        padre = dialogo;
        sospechosos = nSospechosos;

        setLayout( new GridBagLayout( ) );
        setPreferredSize( new Dimension( 375, 303 ) );
        GridBagConstraints constraint = new GridBagConstraints( );

        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;

        panel = new JPanel( );
        panel.setLayout( new GridBagLayout( ) );
        JScrollPane scroll = new JScrollPane( panel );
        scroll.setPreferredSize( new Dimension( 100, 150 ) );
        grupo = new ButtonGroup( );
        String rutaInicial = "";
        for( int i = 0; i < nSospechosos.size( ); i++ )
        {
            JRadioButton radio = new JRadioButton( "Sospechoso " + ( i + 1 ) );
            radio.addActionListener( this );
            radio.setActionCommand( SOSPECHOSO_CAMBIADO + i );
            if( i == 0 )
            {
                Sospechoso sospechoso = ( Sospechoso )nSospechosos.get( i );
                radio.setSelected( true );
                rutaInicial = sospechoso.darRutaImagen( );
            }
            grupo.add( radio );
            constraint = new GridBagConstraints( );
            constraint.gridx = 0;
            constraint.gridy = i;
            constraint.anchor = GridBagConstraints.WEST;
            constraint.insets = new Insets( 5, 0, 0, 0 );
            panel.add( radio, constraint );
        }

        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        add( scroll, constraint );

        lblImagen = new JLabel( new ImageIcon( rutaInicial ) );
        constraint = new GridBagConstraints( );
        constraint.gridx = 2;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        add( lblImagen, constraint );

        JPanel panelBotones = new JPanel( );
        constraint = new GridBagConstraints( );
        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.addActionListener( this );
        btnAceptar.setActionCommand( ACEPTAR );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.weightx = 1;
        panelBotones.add( btnAceptar, constraint );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.weightx = 1;
        constraint.fill = GridBagConstraints.BOTH;
        panelBotones.add( btnCancelar, constraint );

        // Coloca el panel con los botones
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        constraint.insets = new Insets( 5, 5, 5, 5 );

        add( panelBotones, constraint );

    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( ACEPTAR ) )
        {
            padre.generarOrdenCaptura( obtenerSospechosoSeleccionado( ) );
            padre.dispose( );
        }
        else if( comando.equals( CANCELAR ) )
        {
            padre.dispose( );
        }
        else if( comando.startsWith( SOSPECHOSO_CAMBIADO ) )
        {
            int pos = Integer.parseInt( comando.substring( comando.length( ) - 1, comando.length( ) ) );
            cambiarSospechoso( pos );
        }
    }

    /**
     * Cambia la imagen del sospechoso seleccionado
     * @param pos Posición del sospechoso seleccionado
     */
    private void cambiarSospechoso( int pos )
    {
        Sospechoso sospechoso = ( Sospechoso )sospechosos.get( pos );
        lblImagen.setIcon( new ImageIcon( sospechoso.darRutaImagen( ) ) );
    }

    /**
     * Retorna la posición del sospechoso seleccionado
     * @return Posición en el arreglo se sospechosos. Entero mayor o igual a cero.
     */
    public int obtenerSospechosoSeleccionado( )
    {
        Object[] botones = panel.getComponents( );
        String sospechoso = null;
        for( int i = 0; i < botones.length; i++ )
        {
            JRadioButton boton = ( JRadioButton )botones[ i ];
            if( boton.isSelected( ) )
            {
                sospechoso = boton.getText( );
            }
        }
        return Integer.parseInt( sospechoso.substring( sospechoso.length( ) - 1, sospechoso.length( ) ) ) - 1;
    }
}
