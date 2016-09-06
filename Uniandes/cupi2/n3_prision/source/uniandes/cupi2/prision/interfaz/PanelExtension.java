/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.3 2006/11/26 22:13:00 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Muñoz - 04-Sep-2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Opción 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opción 2
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando ingresar
     */
    private static final String INGRESAR = "INGRESAR";

    /**
     * Comando salida
     */
    private static final String SALIDA = "SALIDA";

    /**
     * Comando buscar
     */
    private static final String BUSCAR = "BUSCAR";

    /**
     * Comando buscar
     */
    private static final String REUBICAR = "REUBICAR";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazPrision principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Botón Opción 1
     */
    private JButton btnOpcion1;

    /**
     * Botón Opción 2
     */
    private JButton btnOpcion2;

    /**
     * Botón ingresar prisionero
     */
    private JButton btnIngresar;

    /**
     * Botón dar salida prisionero
     */
    private JButton btnSalida;

    /**
     * Botón buscar prisionero
     */
    private JButton btnBuscar;

    /**
     * Botón reubicar prisionero
     */
    private JButton btnReubicar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelExtension( InterfazPrision ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 2, 5, 0 ) );

        // Botón ingresar prisionero
        btnIngresar = new JButton( "Ingresar" );
        btnIngresar.setActionCommand( INGRESAR );
        btnIngresar.addActionListener( this );
        add( btnIngresar );

        // Botón dar salida prisionero
        btnSalida = new JButton( "Dar Salida" );
        btnSalida.setActionCommand( SALIDA );
        btnSalida.addActionListener( this );
        add( btnSalida );

        // Botón buscar prisionero
        btnBuscar = new JButton( "Buscar" );
        btnBuscar.setActionCommand( BUSCAR );
        btnBuscar.addActionListener( this );
        add( btnBuscar );

        // Botón reubicar prisionero
        btnReubicar = new JButton( "Reubicar" );
        btnReubicar.setActionCommand( REUBICAR );
        btnReubicar.addActionListener( this );
        add( btnReubicar );

        // Botón opción 1
        btnOpcion1 = new JButton( "Opción 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Botón opción 2
        btnOpcion2 = new JButton( "Opción 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param evento Acción que generó el evento. evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        if( OPCION_1.equals( evento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( evento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( INGRESAR.equals( evento.getActionCommand( ) ) )
        {
            principal.mostrarDialogoIngresarPrisionero( );

        }
        else if( SALIDA.equals( evento.getActionCommand( ) ) )
        {
            principal.darSalida( );

        }
        else if( BUSCAR.equals( evento.getActionCommand( ) ) )
        {
            principal.buscar( );

        }
        else if( REUBICAR.equals( evento.getActionCommand( ) ) )
        {
            principal.mostrarDialogoReubicarPrisionero( );
        }
    }

}
