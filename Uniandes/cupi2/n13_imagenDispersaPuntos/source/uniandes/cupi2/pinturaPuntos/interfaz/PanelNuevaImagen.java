/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelNuevaImagen.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
 * Universidad de los Andes (Bogotá· - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_imagenDispersaPuntos
 * Autor: Pablo Andrés Márquez - 03-sep-2006
 * Autor: Manuel Muñoz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.pinturaPuntos.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelNuevaImagen extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Constante que identifica la acción de aceptar
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Constante que identifica la acción de cancelar
     */
    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Interfaz principal de Pintura
     */
    private DialogoNuevaImagen dialogo;

    /**
     * Alto que tiene la superficie de la imagen
     */
    private int alto;

    /**
     * Ancho que tiene la superficie de la imagen
     */
    private int ancho;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------
    /**
     * Botón Aceptar
     */
    private JButton btnAceptar;

    /**
     * Botón Cancelar
     */
    private JButton btnCancelar;

    /**
     * Etiqueta Alto
     */
    private JLabel lblAlto;

    /**
     * Etiqueta Ancho
     */
    private JLabel lblAncho;

    /**
     * Etiqueta Máxima altura
     */
    private JLabel lblMaxAlto;

    /**
     * Etiqueta Máxima Altura
     */
    private JLabel lblMaxAncho;

    /**
     * Cuadro de texto Alto
     */
    private JTextField txtAlto;

    /**
     * Cuadro de texto Ancho
     */
    private JTextField txtAncho;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Construye el diálogo a partir de la ventana principal de Pintura
     * 
     * @param nDialogo Dialogo que contiene este panel
     * @param nAlto Entero mayor a cero.
     * @param nAncho Entero mayor a cero.
     */
    public PanelNuevaImagen( DialogoNuevaImagen nDialogo, int nAlto, int nAncho )
    {
        dialogo = nDialogo;
        alto = nAlto;
        ancho = nAncho;
        lblAlto = new JLabel( );
        lblAncho = new JLabel( );
        lblMaxAlto = new JLabel( );
        lblMaxAncho = new JLabel( );
        txtAlto = new JTextField( );
        txtAncho = new JTextField( );

        setLayout( null );
        add( getBtnAceptar( ) );
        add( getBtnCancelar( ) );

        lblAlto.setText( "Alto:" );
        add( lblAlto );
        lblAlto.setBounds( 50, 40, 44, 20 );
        lblMaxAlto.setText( "Max: " + ( alto - 20 ) );
        add( lblMaxAlto );
        lblMaxAlto.setBounds( 210, 40, 210, 20 );

        lblAncho.setText( "Ancho:" );
        add( lblAncho );
        lblAncho.setBounds( 50, 80, 44, 16 );
        lblMaxAncho.setText( "Max: " + ( ancho - 20 ) );
        add( lblMaxAncho );
        lblMaxAncho.setBounds( 210, 80, 210, 20 );

        add( txtAlto );
        txtAlto.setBounds( 110, 40, 90, 22 );

        add( txtAncho );
        txtAncho.setBounds( 110, 80, 90, 22 );

        // setSize( 310, 260 );
        setVisible( true );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Arma el botón aceptar
     * 
     * @return Devuelve el botón aceptar.
     */
    private JButton getBtnAceptar( )
    {
        if( btnAceptar == null )
        {

            btnAceptar = new JButton( );
            btnAceptar.setText( "Aceptar" );
            btnAceptar.setBounds( 40, 160, 115, 29 );
            btnAceptar.addActionListener( this );
            btnAceptar.setActionCommand( ACEPTAR );
        }
        return btnAceptar;
    }

    /**
     * Arma el botón cancelar
     * 
     * @return Devuelve el botón cancelar.
     */
    private JButton getBtnCancelar( )
    {
        if( btnCancelar == null )
        {
            btnCancelar = new JButton( );
            btnCancelar.setText( "Cancelar" );
            btnCancelar.setBounds( 165, 160, 115, 29 );
            btnCancelar.addActionListener( this );
            btnCancelar.setActionCommand( CANCELAR );
        }
        return btnCancelar;
    }

    /**
     * Toma los datos de la interfaz para crear una nueva imagen.
     */
    private void crearNuevaImagen( )
    {
        int x = -1;
        int y = -1;
        try
        {
            x = ( new Integer( txtAncho.getText( ) ) ).intValue( );
            y = ( new Integer( txtAlto.getText( ) ) ).intValue( );
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "Valores invalídos", "Error", JOptionPane.INFORMATION_MESSAGE );
        }

        if( x < ancho - 20 && y < alto - 20 && x > 0 && y > 0 )
        {
            dialogo.crearImagen( x, y );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Valores fuera del rango", "Información", JOptionPane.INFORMATION_MESSAGE );
        }

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
            crearNuevaImagen( );
            cancelar( );
        }
        else if( comando.equals( CANCELAR ) )
        {
            cancelar( );
        }
    }

    /**
     * Cierra el dialogo actual
     */
    private void cancelar( )
    {
        dialogo.cancelar( );
    }
}
