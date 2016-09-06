/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelInformacionPrisionero.java,v 1.3 2006/11/17 15:38:20 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Daniel Romero - 10-Nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.prision.interfaz;

import java.awt.Dimension;
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

import uniandes.cupi2.prision.mundo.Prisionero;

/**
 * Panel para mostrar la información de un prisionero
 */
public class PanelInformacionPrisionero extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando aceptar
     */
    private static final String CERRAR = "Cerrar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Diálogo en el que se encuentra el panel
     */
    private DialogoInformacionPrisionero dialogo;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Botón de aceptar
     */
    private JButton btnCerrar;

    /**
     * Etiqueta del nombre del prisionero
     */
    private JLabel lblNombre;

    /**
     * Etiqueta del apellido del prisionero
     */
    private JLabel lblApellido;

    /**
     * Etiqueta del número del prisionero
     */
    private JLabel lblNumero;

    /**
     * Etiqueta de la sentencia del prisionero
     */
    private JLabel lblSentencia;

    /**
     * Etiqueta del crimen del prisionero
     */
    private JLabel lblCrimen;

    /**
     * Etiqueta del grupo criminal
     */
    private JLabel lblGrupo;

    /**
     * Etiqueta del sector donde se encuentra
     */
    private JLabel lblSector;

    /**
     * Campo de texto del nombre del prisionero
     */
    private JTextField txtNombre;

    /**
     * Campo de texto del apellido del prisionero
     */
    private JTextField txtApellido;

    /**
     * Campo de texto del numero del prisionero
     */
    private JTextField txtNumero;

    /**
     * Campo de texto de la sentencia del prisionero
     */
    private JTextField txtSentencia;

    /**
     * Campo de texto del crimen del prisionero
     */
    private JTextField txtCrimen;

    /**
     * Campo de texto del grupo criminal
     */
    private JTextField txtGrupo;

    /**
     * Campo de texto del sector
     */
    private JTextField txtSector;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo
     * @param dialogoP Diálogo en el que se encuentra el panel. dialogoP!=null
     * @param prisionero Prisionero del que se va a mostrar la información. prisionero!=null
     * @param sector Nombre del sector en el que se encuentra el prisionero. sector!=null
     */
    public PanelInformacionPrisionero( DialogoInformacionPrisionero dialogoP, Prisionero prisionero, String sector )
    {
        dialogo = dialogoP;
        setBorder( BorderFactory.createTitledBorder( "Datos del prisionero" ) );
        setLayout( new GridBagLayout( ) );

        // Crea las etiquetas y los campos de texto y los agrega al panel
        lblApellido = new JLabel( "Apellido:" );
        lblNombre = new JLabel( "Nombre:" );
        lblSentencia = new JLabel( "Sentencia:" );
        lblNumero = new JLabel( "Número:" );
        lblCrimen = new JLabel( "Crimen:" );
        lblGrupo = new JLabel( "Grupo Criminal:" );
        lblSector = new JLabel( "Sector:" );

        txtApellido = new JTextField( prisionero.darApellido( ) );
        txtApellido.setEditable( false );
        txtNombre = new JTextField( prisionero.darNombre( ) );
        txtNombre.setEditable( false );
        txtSentencia = new JTextField( prisionero.darTiempoSentencia( ) + " meses" );
        txtSentencia.setEditable( false );
        txtNumero = new JTextField( "" + prisionero.darNumero( ) );
        txtNumero.setEditable( false );
        txtCrimen = new JTextField( prisionero.darCrimenCometido( ) );
        txtCrimen.setEditable( false );
        txtGrupo = new JTextField( prisionero.darGrupoCriminal( ) );
        txtGrupo.setEditable( false );
        txtSector = new JTextField( sector );
        txtSector.setEditable( false );

        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 3, 3 );
        lblNumero.setPreferredSize( new Dimension( 70, 20 ) );
        add( lblNumero, gbc );
        gbc.gridx = 1;
        txtNumero.setPreferredSize( new Dimension( 70, 20 ) );
        add( txtNumero, gbc );

        gbc.gridx = 0;
        gbc.gridy = 1;
        add( lblNombre, gbc );
        gbc.gridx = 1;
        add( txtNombre, gbc );

        gbc.gridx = 0;
        gbc.gridy = 2;
        add( lblApellido, gbc );
        gbc.gridx = 1;
        add( txtApellido, gbc );

        gbc.gridx = 0;
        gbc.gridy = 3;
        add( lblSentencia, gbc );
        gbc.gridx = 1;
        add( txtSentencia, gbc );

        gbc.gridx = 0;
        gbc.gridy = 4;
        add( lblCrimen, gbc );
        gbc.gridx = 1;
        add( txtCrimen, gbc );

        gbc.gridx = 0;
        gbc.gridy = 5;
        add( lblGrupo, gbc );
        gbc.gridx = 1;
        add( txtGrupo, gbc );

        gbc.gridx = 0;
        gbc.gridy = 6;
        add( lblSector, gbc );
        gbc.gridx = 1;
        add( txtSector, gbc );

        // Crea los botones y los agrega al panel
        JPanel panelBotones = new JPanel( );
        btnCerrar = new JButton( CERRAR );
        btnCerrar.setActionCommand( CERRAR );
        btnCerrar.addActionListener( this );
        panelBotones.add( btnCerrar );

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add( panelBotones, gbc );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Responde a los eventos de los botones del panel
     * @param evento Evento generado por un botón. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( comando.equals( CERRAR ) )
        {
            dialogo.cerrar( );
        }
    }
}
