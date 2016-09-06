/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoColorXY.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_imagenDispersaPuntos
 * Autor: Pablo Andr�s M�rquez - 03-sep-2006
 * Autor: Manuel Mu�oz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.pinturaPuntos.interfaz;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Di�logo que se utiliza para hallar el color de un punto.
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
     * Bot�n Aceptar
     */
    private JButton btnAceptar;

    /**
     * Bot�n Cancelar
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
     * Construye el di�logo a partir de la ventana principal de Pintura
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
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Arma el bot�n aceptar
     * 
     * @return Devuelve el bot�n aceptar.
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
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Arma el bot�n cancelar
     * 
     * @return Devuelve el bot�n cancelar.
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
     * Muestra en una ventana el color del p�xel anteriormente seleccionado
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
            JOptionPane.showMessageDialog( this, color, "Informaci�n", JOptionPane.INFORMATION_MESSAGE );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Valores fuera del rango", "Informaci�n", JOptionPane.INFORMATION_MESSAGE );
        }

    }

    /**
     * Cierra el di�logo
     */
    private void cancelar( )
    {
        dispose( );
    }
}
