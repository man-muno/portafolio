/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoColorXY.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Diálogo que se utiliza para hallar el color de un punto.
 */
public class DialogoColorXY extends JDialog
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Interfaz principal de Pintura
     */
    private InterfazImagenDispersaPuntos principal;

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
     * Etiqueta Alto
     */
    private JTextField txtAlto;

    /**
     * Etiqueta Ancho
     */
    private JTextField txtAncho;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Construye el diálogo a partir de la ventana principal de Pintura
     * 
     * @param principal
     */
    public DialogoColorXY( InterfazImagenDispersaPuntos principal )
    {
        super( principal );
        this.principal = principal;
        lblAlto = new JLabel( );
        lblAncho = new JLabel( );
        txtAlto = new JTextField( );
        txtAncho = new JTextField( );

        getContentPane( ).setLayout( null );

        getContentPane( ).add( getBtnAceptar( ) );

        getContentPane( ).add( getBtnCancelar( ) );

        lblAlto.setText( "Y:" );
        getContentPane( ).add( lblAlto );
        lblAlto.setBounds( 50, 40, 40, 20 );

        lblAncho.setText( "X:" );
        getContentPane( ).add( lblAncho );
        lblAncho.setBounds( 50, 80, 44, 16 );

        getContentPane( ).add( txtAlto );
        txtAlto.setBounds( 110, 40, 140, 22 );

        getContentPane( ).add( txtAncho );
        txtAncho.setBounds( 110, 80, 140, 22 );

        setSize( 300, 200 );
        setLocationRelativeTo( null );

        setTitle( "Pintura - Color" );
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
            btnAceptar.addActionListener( new java.awt.event.ActionListener( )
            {
                public void actionPerformed( java.awt.event.ActionEvent e )
                {
                    mostrarColor( );
                }

            } );
        }
        return btnAceptar;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
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
            btnCancelar.setText( "Cerrar" );
            btnCancelar.setBounds( 170, 120, 120, 29 );
            btnCancelar.addActionListener( new java.awt.event.ActionListener( )
            {
                public void actionPerformed( java.awt.event.ActionEvent e )
                {
                    cancelar( );
                }

            } );
        }
        return btnCancelar;
    }

    /**
     * Muestra en una ventana el color del píxel anteriormente seleccionado
     * 
     */
    private void mostrarColor( )
    {
        String color = "";
        int x = ( new Integer( txtAncho.getText( ) ) ).intValue( );
        int y = ( new Integer( txtAlto.getText( ) ) ).intValue( );
        if( ( y < principal.getFilas( ) && y >= 0 ) && ( x < principal.getColumnas( ) && x >= 0 ) )
        {
            if( principal.isColor( y, x ) )
            {
                color = "El color es : Negro";
            }
            else
            {
                color = "El color es: Blanco";
            }
            JOptionPane.showMessageDialog( this, color, "Información", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Valores fuera del rango", "Información", JOptionPane.INFORMATION_MESSAGE );
        }

    }

    /**
     * Cierra el diálogo
     */
    private void cancelar( )
    {
        dispose( );
    }
}
