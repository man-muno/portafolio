/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoPixelesNegrosColumna.java,v 1.1 2007/03/05 02:09:02 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_pinturaPuntos
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
 * Di�logo para hallar el n�mero de p�xeles en una columna.
 */
public class DialogoPixelesNegrosColumna extends JDialog
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
    private InterfazPinturaPuntos principal;

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
     * Cuadro de texto Alto
     */
    private JTextField txtAlto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el di�logo a partir de la ventana principal de Pintura y el nombre de la funci�n que va desempe�ar
     * 
     * @param principal Interfaz principal Pintura.
     * @param texto Nombre de la funci�n que va desempe�ar.
     */
    public DialogoPixelesNegrosColumna( InterfazPinturaPuntos principal )
    {
        super( principal );
        this.principal = principal;
        lblAlto = new JLabel( );

        txtAlto = new JTextField( );

        getContentPane( ).setLayout( null );

        getContentPane( ).add( getBtnCancelar( ) );

        lblAlto.setText( "Columna:" );
        getContentPane( ).add( lblAlto );
        lblAlto.setBounds( 50, 40, 40, 20 );

        getContentPane( ).add( txtAlto );
        txtAlto.setBounds( 110, 40, 140, 22 );
        setTitle( "Cantidad de pixeles negros por columna" );
        setSize( 310, 160 );
        setLocationRelativeTo( null );
        getContentPane( ).add( getBtnAceptar( ) );
        getContentPane( ).add( lblAlto );
        lblAlto.setBounds( 50, 40, 70, 20 );

        getContentPane( ).add( txtAlto );
        txtAlto.setBounds( 120, 40, 140, 22 );
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
     * Muestra en un di�logo el n�mero de p�xeles negros de una fila.
     * 
     */
    private void mostrarColor( )
    {

        int y = ( new Integer( txtAlto.getText( ) ) ).intValue( );
        if( y < principal.getFilas( ) && y > 0 )
        {
            String mensaje = "N�mero de p�xeles negros en la fila " + y + " es: " + principal.darNumeroPixelesNegrosFila( y );
            JOptionPane.showMessageDialog( this, mensaje, "Informaci�n", JOptionPane.INFORMATION_MESSAGE );

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
