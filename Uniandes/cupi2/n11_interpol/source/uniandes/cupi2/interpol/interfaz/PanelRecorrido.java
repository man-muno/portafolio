/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelRecorrido.java,v 1.1 2007/04/09 11:20:06 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Mu�oz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.interpol.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Panel donde se muestran los diferentes recorridos sobre el �rbol
 */
public class PanelRecorrido extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Acci�n asociada al bot�n cerrar
     */
    private static final String CERRAR = "CERRAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    private JButton btnAceptar;

    private JTextArea txtaRecorrido;

    private JScrollPane scroll;

    private JDialog padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros.
     * @param recorrido Lista que contiene la informaci�n a mostrar. Contiene objetos de tipo String y no es null
     * @param papa Dialogo que contiene el panel
     */
    public PanelRecorrido( List recorrido, JDialog papa )
    {
        padre = papa;
        setLayout( new BorderLayout( ) );
        btnAceptar = new JButton( "Cerrar" );
        btnAceptar.setActionCommand( CERRAR );
        btnAceptar.addActionListener( this );
        setPreferredSize( new Dimension( 300, 300 ) );
        txtaRecorrido = new JTextArea( );
        txtaRecorrido.setEditable( false );
        for( int i = 0; i < recorrido.size( ); i++ )
        {
            txtaRecorrido.setText( txtaRecorrido.getText( ) + recorrido.get( i ) + "\n" );
        }
        scroll = new JScrollPane( txtaRecorrido );
        scroll.setPreferredSize( new Dimension( 300, 300 ) );
        add( scroll, BorderLayout.CENTER );
        add( btnAceptar, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // M�todos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String accion = e.getActionCommand( );
        if( accion.equals( CERRAR ) )
        {
            padre.dispose( );
        }
    }

}
