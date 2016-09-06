/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazBolsa.java,v 1.7 2007/01/31 07:15:37 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Jorge Villalobos - 23-ago-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.interfaz;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import uniandes.cupi2.bolsa.interfaz.PanelesBolsa.PanelBolsa1;
import uniandes.cupi2.bolsa.interfaz.PanelesBolsa.PanelBolsa2;
import uniandes.cupi2.bolsa.interfaz.PanelesBolsa.PanelBolsa3;
import uniandes.cupi2.bolsa.interfaz.PanelesBolsa.PanelBolsa4;
import uniandes.cupi2.bolsa.interfaz.PanelesBolsa.PanelBolsa5;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazBolsa extends JFrame
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String NOMBRE_ARCHIVO = "data/fabricas.properties";

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel principal de la interfaz.
     */
    private JPanel panelTabs = null;

    /**
     * Panel para el tipo de bolsa 1
     */
    private PanelBolsa1 panelBolsa1 = null;

    /**
     * Panel para el tipo de bolsa 2
     */
    private PanelBolsa2 panelBolsa2 = null;

    /**
     * Panel para el tipo de bolsa 3
     */
    private PanelBolsa3 panelBolsa3 = null;

    /**
     * Panel para el tipo de bolsa 4
     */
    private PanelBolsa4 panelBolsa4 = null;

    /**
     * Panel para el tipo de bolsa 5
     */
    private PanelBolsa5 panelBolsa5 = null;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Descripción <br>
     * <b>post: </b> Descripción
     */
    public InterfazBolsa( )
    {
        initialize( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa la interfaz. Este método es llamado por el constructor.
     */
    private void initialize( )
    {

        Properties propiedades = new Properties( );
        try
        {
            propiedades.load( new FileInputStream( new File( NOMBRE_ARCHIVO ) ) );
        }
        catch( FileNotFoundException e )
        {
            JOptionPane.showMessageDialog( this, "Error al cargar el archivo de propiedades", "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, "Error al cargar el archivo de propiedades", "Error", JOptionPane.ERROR_MESSAGE );
        }

        setContentPane( getPanelTabs( propiedades ) );

        setSize( 625, 250 );
        setResizable( false );
        setTitle( "Probador para Bolsas" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocationRelativeTo( null );

    }

    /**
     * Retorna el panel que contiene el TabbedPane.
     * @param propiedades Objeto donde se encuentran los nombres de las fábricas
     * @return Panel principal de la interfaz.
     */
    private JPanel getPanelTabs( Properties propiedades )
    {
        if( panelTabs == null )
        {
            panelTabs = new JPanel( );
            panelTabs.setLayout( new BorderLayout( ) );
            JTabbedPane tabs = new JTabbedPane( );
            // Agregar los tabs al panel
            panelBolsa1 = new PanelBolsa1( propiedades );
            panelBolsa2 = new PanelBolsa2( propiedades );
            panelBolsa3 = new PanelBolsa3( propiedades );
            panelBolsa4 = new PanelBolsa4( propiedades );
            panelBolsa5 = new PanelBolsa5( propiedades );
            tabs.addTab( "Implementación 1", panelBolsa1 );
            tabs.addTab( "Implementación 2", panelBolsa2 );
            tabs.addTab( "Implementación 3", panelBolsa3 );
            tabs.addTab( "Implementación 4", panelBolsa4 );
            tabs.addTab( "Implementación 5", panelBolsa5 );
            panelTabs.add( tabs, BorderLayout.CENTER );
        }
        return panelTabs;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {
        InterfazBolsa interfaz = new InterfazBolsa( );
        interfaz.setVisible( true );
    }
}