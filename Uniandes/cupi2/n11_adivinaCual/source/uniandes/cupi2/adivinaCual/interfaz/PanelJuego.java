/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Muñoz - Nov 8, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import uniandes.cupi2.adivinaCual.mundo.Pregunta;

/**
 * Panel que contiene el panel de las preguntas y el panel de las respuestas (SI/NO)
 */
public class PanelJuego extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Acción que se asocia al botón SI
     */
    private static final String OPCION_SI = "OPCION_SI";

    /**
     * Acción que se asocia al botón SI
     */
    private static final String OPCION_NO = "OPCION_NO";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    private JLabel lblImagen;

    private JLabel lblPregunta;

    private JButton btnSi;

    private JButton btnNo;

    private JTextArea txtHistoria;

    private JScrollPane scroll;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazAdivinaCual principal;

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros de la clase.
     * @param cual Instancia de la ventana principal de la aplicación. Diferente de null
     */
    public PanelJuego( InterfazAdivinaCual cual )
    {
        principal = cual;
        setLayout( new GridBagLayout( ) );

        GridBagConstraints constraint = new GridBagConstraints( );

        txtHistoria = new JTextArea( );
        txtHistoria.setEditable( false );
        scroll = new JScrollPane( txtHistoria );
        scroll.setPreferredSize( new Dimension( 680, 40 ) );
        scroll.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );

        Pregunta actual = principal.obtenerPreguntaActual( );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 2;
        add( scroll, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;

        JPanel panelPreguntas = new JPanel( );
        panelPreguntas.setLayout( new FlowLayout( FlowLayout.LEADING, 0, 0 ) );
        Border borde = BorderFactory.createLineBorder( Color.BLACK );
        panelPreguntas.setBorder( borde );
        lblImagen = new JLabel( new ImageIcon( "data/imagenes/merlina.jpg" ) );
        lblPregunta = new JLabel( new ImageIcon( "data/imagenes/perro3.jpg" ) );
        lblPregunta.setHorizontalTextPosition( SwingConstants.CENTER );

        panelPreguntas.add( lblImagen );
        panelPreguntas.add( lblPregunta );
        add( panelPreguntas, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;

        JPanel panelRespuesta = new JPanel( );
        panelRespuesta.setLayout( new FlowLayout( FlowLayout.LEADING, 20, 10 ) );
        btnSi = new JButton( "Si" );
        btnSi.setPreferredSize( new Dimension( 100, 25 ) );
        btnSi.setActionCommand( OPCION_SI );
        btnSi.addActionListener( this );
        btnNo = new JButton( "No" );
        btnNo.setPreferredSize( new Dimension( 100, 25 ) );
        btnNo.setActionCommand( OPCION_NO );
        btnNo.addActionListener( this );
        panelRespuesta.add( btnSi );
        panelRespuesta.add( btnNo );

        add( panelRespuesta, constraint );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String accion = e.getActionCommand( );
        if( accion.equals( OPCION_SI ) )
        {
            txtHistoria.setText( txtHistoria.getText( ) + ":Si>" );
            principal.seleccionarRespuestaPositiva( );
        }
        else if( accion.equals( OPCION_NO ) )
        {
            txtHistoria.setText( txtHistoria.getText( ) + ":No>" );
            principal.seleccionarRespuestaNegativa( );
        }

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Indica a los paneles que contiene que se actualicen
     */
    public void actualizar( )
    {
        Pregunta actual = principal.obtenerPreguntaActual( );
        if( actual.darDescripcion( ) != null )
        {
            txtHistoria.setText( txtHistoria.getText( ) + actual.darDescripcion( ) );
            lblPregunta.setText( "El animal: " + actual.darDescripcion( ) );
        }
        else
        {
            txtHistoria.setText( txtHistoria.getText( ) + actual.darNombreAnimal( ) );
            lblPregunta.setText( "El animal pensado fue: " + actual.darNombreAnimal( ) + "?" );
        }
    }

    /**
     * Reinicia el campo de texto
     */
    public void reiniciar( )
    {
        txtHistoria.setText( "" );
        actualizar( );
    }

    public void desactivarBotones( )
    {
        btnNo.setEnabled( false );
        btnSi.setEnabled( false );
    }

    public void activarBotones( )
    {
        btnNo.setEnabled( true );
        btnSi.setEnabled( true );
    }

}
