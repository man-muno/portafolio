/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelIngresarPrisionero.java,v 1.4 2007/01/22 07:08:48 f-vela Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Muñoz - Sep 13, 2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.prision.mundo.Prisionero;
import uniandes.cupi2.prision.mundo.Sector;

/**
 * Panel con los componentes para ingresar los datos del prisionero
 */
public class PanelIngresarPrisionero extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Ingresar
     */
    private static final String INGRESAR = "INGRESAR";
    /**
     * Comando Cancelar
     */
    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Diálogo en el que se encuentra el panel
     */
    private DialogoIngresarPrisionero dialogo;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------
    /**
     * Botón para el Ingreso
     */
    private JButton botonIngresar;

    /**
     * Botón de cancelar
     */
    private JButton botonCancelar;

    /**
     * Etiqueta del nombre del prisionero
     */
    private JLabel etiquetaNombre;

    /**
     * Etiqueta del apellido del prisionero
     */
    private JLabel etiquetaApellido;

    /**
     * Etiqueta del número único del prisionero
     */
    private JLabel etiquetaNumero;

    /**
     * Etiqueta del precio base de la acción
     */
    private JLabel etiquetaCrimen;

    /**
     * Etiqueta del tiempo de la sentencia
     */
    private JLabel etiquetaTiempoSentencia;

    /**
     * Etiqueta del grupo criminal del prisionero
     */
    private JLabel etiquetaGrupoCriminal;

    /**
     * Etiqueta del sector donde se va a colocar el prisionero
     */
    private JLabel etiquetaSector;

    /**
     * Campo de texto del nombre del prisionero
     */
    private JTextField txtNombre;

    /**
     * Campo de texto de la sigla de la acción
     */
    private JTextField txtApellido;

    /**
     * Campo de texto del número del prisionero
     */
    private JTextField txtNumero;

    /**
     * Campo de texto del crimen
     */
    private JTextField txtCrimen;

    /**
     * Campo de texto del precio base de la acción
     */
    private JTextField txtTiempoSentencia;

    /**
     * Combo donde aparecen los grupos criminales
     */
    private JComboBox comboGrupoCriminal;

    /**
     * Combo donde aparecen los sectores de la prisión
     */
    private JComboBox comboSectores;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del diálogo
     * @param dialogoP Dialogo para ingresar prisionero  dialogoP != null.
     */

    public PanelIngresarPrisionero( DialogoIngresarPrisionero dialogoP )
    {
        dialogo = dialogoP;

        setLayout( new GridLayout( 8, 2 ) );
        setBorder( BorderFactory.createTitledBorder( "Datos del prisionero" ) );

        // Crea las etiquetas y los campos de texto y los agrega al panel
        etiquetaApellido = new JLabel( "Apellido:" );
        etiquetaNombre = new JLabel( "Nombre:" );
        etiquetaTiempoSentencia = new JLabel( "Tiempo Sentencia:" );
        etiquetaNumero = new JLabel( "Número:" );
        etiquetaGrupoCriminal = new JLabel( "Grupo Criminal" );
        etiquetaSector = new JLabel( "Sector:" );
        etiquetaCrimen = new JLabel( "Crimen:" );

        txtApellido = new JTextField( "" );
        txtNombre = new JTextField( "" );
        txtTiempoSentencia = new JTextField( "" );
        txtNumero = new JTextField( "" );
        txtCrimen = new JTextField( "" );

        add( etiquetaNombre );
        add( txtNombre );

        add( etiquetaApellido );
        add( txtApellido );

        add( etiquetaNumero );
        add( txtNumero );

        add( etiquetaCrimen );
        add( txtCrimen );

        add( etiquetaTiempoSentencia );
        add( txtTiempoSentencia );

        comboGrupoCriminal = new JComboBox( );
        String[] grupos = { Prisionero.FARC, Prisionero.PARAMILITARES, Prisionero.CARTEL_MEDELLIN, Prisionero.INDEPENDIENTE, Prisionero.NINGUNO };
        for( int i = 0; i < grupos.length; i++ )
        {
            comboGrupoCriminal.addItem( grupos[ i ] );
        }

        add( etiquetaGrupoCriminal );
        add( comboGrupoCriminal );

        comboSectores = new JComboBox( );
        Sector[] sectores = dialogo.obtenerSectores( );
        for( int i = 0; i < sectores.length; i++ )
        {
            comboSectores.addItem( sectores[ i ].darNombre( ) );
        }
        add( etiquetaSector );
        add( comboSectores );

        //
        // Crea los botones y los agrega al panel
        botonIngresar = new JButton( "Agregar" );
        botonIngresar.setActionCommand( INGRESAR );
        botonIngresar.addActionListener( this );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );

        add( botonIngresar );
        add( botonCancelar );
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

        if( comando.equals( INGRESAR ) )
        {
            dialogo.ingresarPrisionero( );
        }
        else if( comando.equals( CANCELAR ) )
        {
            dialogo.cancelar( );
        }
    }

    /**
     * Retorna el nombre del prisionero ingresado por el usuario
     * @return Se retornó el nombre del prisionero
     */
    public String darNombre( )
    {
        return txtNombre.getText( );
    }

    /**
     * Retorna el apellido del prisionero ingresado por el usuario
     * @return Se retornó el apellido del prisionero
     */
    public String darApellido( )
    {
        return txtApellido.getText( );
    }

    /**
     * Retorna el crimen cometido del prisionero ingresado por el usuario
     * @return Se retornó el crimen del prisionero
     */
    public String darCrimen( )
    {
        return txtCrimen.getText( );
    }

    /**
     * Retorna el número del prisionero ingresado por el usuario
     * @return Se retornó el numero del prisionero
     */
    public String darNumero( )
    {
        return txtNumero.getText( );
    }

    /**
     * Retorna el tiempo de sentencia del prisionero ingresado por el usuario
     * @return Se retornó el tiempo de sentencia del prisionero
     */
    public String darTiempoSentencia( )
    {
        return txtTiempoSentencia.getText( );
    }

    /**
     * Retorna el sector del prisionero ingresado por el usuario
     * @return Se retornó el sector del prisionero
     */
    public String darSector( )
    {
        return ( String )comboSectores.getSelectedItem( );
    }

    /**
     * Retorna el grupo criminal del prisionero ingresado por el usuario
     * @return Se retornó el grupo criminal del prisionero
     */
    public String darGrupoCriminal( )
    {
        return ( String )comboGrupoCriminal.getSelectedItem( );
    }

}
