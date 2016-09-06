/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelReubicarPrisionero.java,v 1.2 2006/11/17 15:38:20 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.prision.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.prision.mundo.Sector;

/**
 * Panel en el que se pide la informaci�n para reubicar un prisionero
 */
public class PanelReubicarPrisionero extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando que se ejecuta al hacer click en el bot�n de aceptar
     */
    public static final String ACEPTAR_REUBICAR = "ACEPTAR_REUBICAR";

    /**
     * Comando que se ejecuta al hacer click en el bot�n de cancelar
     */
    public static final String CANCELAR_REUBICAR = "CANCELAR_REUBICAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Dialogo en el que se encuentra el panel
     */
    private DialogoReubicarPrisionero dialogo;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta que muestra el mensaje "Numero Prisionero"
     */
    private JLabel etiquetaNumeroPrisionero;

    /**
     * Etiqueta que muestra el mensaje "Nuevo Sector"
     */

    private JLabel etiquetaSector;

    /**
     * Texto para ingresar el n�mero de prisionero
     */
    private JTextField txtNumeroPrisionero;
    /**
     * Combo donde se muestran los sectores
     */
    private JComboBox comboSectores;

    /**
     * Bot�n que acepta el cambio de sector
     */
    private JButton btnAceptar;

    /**
     * Bot�n de cancelar
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros
     * @param sectores Los sectores a mostrar
     * @param dialogoP El dialogo en el que se encuentra el panel
     */
    public PanelReubicarPrisionero( Sector[] sectores, DialogoReubicarPrisionero dialogoP )
    {
        dialogo = dialogoP;
        setLayout( new GridBagLayout( ) );

        etiquetaSector = new JLabel( "Nuevo Sector" );
        etiquetaNumeroPrisionero = new JLabel( "Numero Prisionero" );
        txtNumeroPrisionero = new JTextField( );
        comboSectores = new JComboBox( );
        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.setActionCommand( ACEPTAR_REUBICAR );
        btnAceptar.addActionListener( this );
        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR_REUBICAR );
        btnCancelar.addActionListener( this );
        JPanel panelBotones = new JPanel( );
        for( int i = 0; i < sectores.length; i++ )
        {
            comboSectores.addItem( sectores[ i ].darNombre( ) );
        }

        panelBotones.add( btnAceptar );
        panelBotones.add( btnCancelar );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 5, 5, 5, 5 );
        add( etiquetaSector, gbc );
        gbc.gridx = 1;
        add( comboSectores, gbc );
        gbc.gridx = 0;
        gbc.gridy = 1;
        add( etiquetaNumeroPrisionero, gbc );
        gbc.gridx = 1;
        add( txtNumeroPrisionero, gbc );
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add( panelBotones, gbc );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que se ejecuta cuando se hace click en un bot�n del panel
     * @param evento Evento generado. evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( comando.equals( ACEPTAR_REUBICAR ) )
        {
            dialogo.reubicar( );
        }
        else if( comando.equals( CANCELAR_REUBICAR ) )
        {
            dialogo.cancelar( );
        }
    }

    /**
     * Retorna el nuevo sector al que va a ser transferido el prisionero
     * @return Se retorn� el sector en el que se va a realizar la reubicaci�n
     */
    public String darSectorSeleccionado( )
    {
        return ( String )comboSectores.getSelectedItem( );
    }

    /**
     * Retorna el n�mero de prisionero ingresado por el usuario
     * @return Se retorn� el n�mero de prisionero ingresado por el usuario
     */
    public String darNumeroPrisionero( )
    {
        return txtNumeroPrisionero.getText( );
    }

}
