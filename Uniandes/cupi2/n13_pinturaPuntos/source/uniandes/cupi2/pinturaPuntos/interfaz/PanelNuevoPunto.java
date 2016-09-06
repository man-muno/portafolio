/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelNuevoPunto.java,v 1.1 2007/03/05 02:09:02 man-muno Exp $
 * Universidad de los Andes (Bogotá· - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_pinturaPuntos
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

public class PanelNuevoPunto extends JPanel implements ActionListener
{

    public static final String ACEPTAR = "ACEPTAR";

    public static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Dialogo que contiene este panel
     */
    private DialogoNuevoPunto dialogo;

    private int alto;

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

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo a partir de la ventana principal de Pintura
     * 
     * @param principal Interfaz principal Pintura
     */
    public PanelNuevoPunto( DialogoNuevoPunto nDialogo, int nAlto, int nAncho )
    {
        alto = nAlto;
        ancho = nAncho;
        dialogo = nDialogo;
        lblAlto = new JLabel( );
        lblAncho = new JLabel( );
        txtAlto = new JTextField( );
        txtAncho = new JTextField( );

        setLayout( null );

        add( getBtnAceptar( ) );

        add( getBtnCancelar( ) );

        lblAlto.setText( "Coordenada Y:" );
        add( lblAlto );
        lblAlto.setBounds( 50, 40, 100, 20 );

        lblAncho.setText( "Coordenada X:" );
        add( lblAncho );
        lblAncho.setBounds( 50, 80, 100, 16 );

        add( txtAlto );
        txtAlto.setBounds( 200, 40, 110, 22 );

        add( txtAncho );
        txtAncho.setBounds( 200, 80, 110, 22 );
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
            btnAceptar.setBounds( 40, 120, 120, 29 );
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
            btnCancelar.setBounds( 170, 120, 120, 29 );
            btnCancelar.addActionListener( this );
            btnCancelar.setActionCommand( CANCELAR );
        }
        return btnCancelar;
    }

    /**
     * Toma los datos de la interfaz para crear una nueva imagen.
     * 
     */
    private void agregarNuevoPunto( )
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
        if( ( x < ancho && x >= 0 ) && ( y < alto && y >= 0 ) )
        {
            dialogo.agregarNuevoPunto( x, y );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Valores fuera del rango", "Información", JOptionPane.INFORMATION_MESSAGE );
        }

    }

    /**
     * Cierra el diÃ¡logo
     */
    private void cancelar( )
    {
        dialogo.dispose( );
    }

    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( ACEPTAR ) )
        {
            agregarNuevoPunto( );
            cancelar( );
        }
        else if( comando.equals( CANCELAR ) )
        {
            cancelar( );
        }
    }
}
