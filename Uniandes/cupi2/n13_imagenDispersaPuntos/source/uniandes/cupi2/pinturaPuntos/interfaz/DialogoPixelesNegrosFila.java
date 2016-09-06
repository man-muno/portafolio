/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoPixelesNegrosFila.java,v 1.2 2007/04/24 00:42:49 man-muno Exp $
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
 * Diálogo para hallar el número de píxeles negros en una fila.
 */
public class DialogoPixelesNegrosFila extends JDialog
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
     * Cuadro de texto Alto
     */
    private JTextField txtAlto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Construye el diálogo a partir de la ventana principal de Pintura
     * 
     * @param principal Interfaz principal Pintura
     */
    public DialogoPixelesNegrosFila( InterfazImagenDispersaPuntos principal )
    {
        super( principal );
        this.principal = principal;
        lblAlto = new JLabel( );

        txtAlto = new JTextField( );

        getContentPane( ).setLayout( null );

        getContentPane( ).add( getBtnAceptar( ) );

        getContentPane( ).add( getBtnCancelar( ) );
        setTitle( "Cantidad de pixeles negros por fila" );

        lblAlto.setText( "Fila:" );
        getContentPane( ).add( lblAlto );
        lblAlto.setBounds( 50, 40, 40, 20 );

        getContentPane( ).add( txtAlto );
        txtAlto.setBounds( 110, 40, 140, 22 );

        setSize( 310, 160 );
        setLocationRelativeTo( null );
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
            btnAceptar.setBounds( 40, 80, 120, 29 );
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
            btnCancelar.setBounds( 170, 80, 120, 29 );
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
     * Muestra en un diálogo el número de píxeles negros de una fila.
     * 
     */
    private void mostrarColor( )
    {

        int y = ( new Integer( txtAlto.getText( ) ) ).intValue( );
        if( y < principal.getFilas( ) && y >= 0 )
        {
            String mensaje = "Número de píxeles negros en la fila " + y + " es: " + principal.darNumeroPixelesNegrosFila( y );
            JOptionPane.showMessageDialog( this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE );

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
