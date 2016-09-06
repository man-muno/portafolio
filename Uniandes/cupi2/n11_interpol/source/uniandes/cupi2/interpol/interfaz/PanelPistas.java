/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelPistas.java,v 1.3 2007/04/20 12:38:40 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Muñoz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.interpol.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Panel donde se muestran las pistas.
 */
public class PanelPistas extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Constante que identifica que el usuario acepto y quiere ver la pista
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Constante que identifica que el usuario cancelo
     */
    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    private JComboBox combo;

    private JButton btnAceptar;

    private JButton btnCancelar;

    private DialogoPistas padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel. Inicializa y coloca los componentes gráficos.
     * @param pistas Dialogo que contiene este panel. Diferente de null.
     * @param lugares Lugares donde se pueden ver las pistas. Diferente de null.
     */
    public PanelPistas( DialogoPistas pistas, List lugares )
    {
        padre = pistas;
        setLayout( new GridBagLayout( ) );

        combo = new JComboBox( lugares.toArray( ) );

        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridwidth = 2;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        add( combo, constraint );

        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.setActionCommand( ACEPTAR );
        btnAceptar.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        add( btnAceptar, constraint );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        add( btnCancelar, constraint );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( ACEPTAR ) )
        {
            padre.mostrarPista( ( String )combo.getSelectedItem( ) );
            padre.dispose( );
        }
        else if( comando.equals( CANCELAR ) )
        {
            padre.dispose( );
        }
    }
}
