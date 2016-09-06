/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelDetallesCiudad.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupi2CruiseLines
 * Autor: Manuel Muñoz - 13-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupi2CruiseLines.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import uniandes.cupi2.cupi2CruiseLines.mundo.Ciudad;

/**
 * Panel donde se muestran los detalles de una ciudad
 */
public class PanelDetallesCiudad extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que indica que el usuario quiere cerrar la ventana
     */
    private static final String CERRAR = "CERRAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta que muestra nombre
     */
    private JLabel lblNombre;

    /**
     * Cuadro de texto con el nombre de la ciudad
     */
    private JTextField txtNombre;

    /**
     * Etiqueta que muestra país
     */
    private JLabel lblPais;

    /**
     * Cuadro de texto que muestra el país de la ciudad
     */
    private JTextField txtPais;

    /**
     * Botón para cerrar el dialogo
     */
    private JButton btnCerrar;

    /**
     * Dialogo que contiene este panel
     */
    private DialogoDetallesCiudad padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros.
     * @param dialogo Dialogo que contiene al padre
     * @param ciudad Ciudad que se quiere mostrar
     */
    public PanelDetallesCiudad( DialogoDetallesCiudad dialogo, Ciudad ciudad )
    {
        padre = dialogo;
        setLayout( new GridBagLayout( ) );

        Border borde = BorderFactory.createTitledBorder( "Ciudad" );
        setBorder( borde );

        lblNombre = new JLabel( "Nombre: " );
        GridBagConstraints constraints = new GridBagConstraints( );
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        add( lblNombre, constraints );

        txtNombre = new JTextField( ciudad.darNombre( ) );
        txtNombre.setEditable( false );
        constraints = new GridBagConstraints( );
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        add( txtNombre, constraints );

        lblPais = new JLabel( "País: " );
        constraints = new GridBagConstraints( );
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        add( lblPais, constraints );

        txtPais = new JTextField( ciudad.darPais( ) );
        txtPais.setEditable( false );
        constraints = new GridBagConstraints( );
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        add( txtPais, constraints );

        btnCerrar = new JButton( "Cerrar" );
        btnCerrar.addActionListener( this );
        btnCerrar.setActionCommand( CERRAR );
        constraints = new GridBagConstraints( );
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        add( btnCerrar, constraints );

    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( CERRAR ) )
        {
            padre.dispose( );
        }
    }
}
