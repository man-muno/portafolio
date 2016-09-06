/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 
 * Ejercicio: generadorEjerciciosV2
 * Autor: Pablo Barvo - Sep 2, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.generador.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Panel donde se ingresan los datos del nuevo ejercicio
 */
public class PanelDatos extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando seleccionar ubicaci�n
     */
    private static final String SELECCIONAR_UBICACION = "SELECCIONAR_UBICACION";
    
    /**
     * Comando agregar archivos adicionales.
     */
    private static final String SELECCIONAR_ARCHIVOS_ADICIONALES = "SELECCIONAR_ARCHIVOS_ADICIONALES";

    //-----------------------------------------------------------------
    // Atributos de Interfaz
    //-----------------------------------------------------------------

    /**
     * Etiqueta Nombre
     */
    private JLabel etiquetaNombre;

    /**
     * Campo Nombre
     */
    private JTextField txtNombre;

    /**
     * Etiqueta Autor
     */
    private JLabel etiquetaAutor;

    /**
     * Campo Autor
     */
    private JTextField txtAutor;

    /**
     * Etiqueta Nivel
     */
    private JLabel etiquetaNivel;

    /**
     * Combo de selecci�n de Nivel
     */
    private JComboBox cboNivel;

    /**
     * Etiqueta Clase Mundo
     */
    private JLabel etiquetaClaseMundo;

    /**
     * Campo Clase Mundo
     */
    private JTextField txtClaseMundo;

    /**
     * Etiqueta Ubicaci�n
     */
    private JLabel etiquetaUbicacion;

    /**
     * Campo Ubicaci�n
     */
    private JTextField txtUbicacion;

    /**
     * Bot�n seleccionar ubicaci�n
     */
    private JButton btnUbicacion;
    
    /**
     * Bot�n de agregar archivos
     */
    private JButton btnAgregarArchivosAdicionales;
    
    /**
     * Instancia de la ventana principal.
     */
    private InterfazGenerador principal;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel datos
     * @param generador Interfaz principal de la aplicaci�n.
     */
    public PanelDatos(InterfazGenerador generador )
    {
        principal = generador;
        setLayout( new GridLayout( 7, 2 ) );
        setBorder( new TitledBorder( "Datos del Ejercicio" ) );

        //Nombre
        etiquetaNombre = new JLabel( "Nombre: " );
        etiquetaNombre.setToolTipText( "Ejemplo: brazoMecanico" );
        add( etiquetaNombre );
        txtNombre = new JTextField( );
        txtNombre.setToolTipText( "Ejemplo: brazoMecanico" );
        add( txtNombre );

        //Autor
        etiquetaAutor = new JLabel( "Autor: " );
        etiquetaAutor.setToolTipText( "Ejemplo: Pedro P�rez" );
        add( etiquetaAutor );
        txtAutor = new JTextField( );
        txtAutor.setToolTipText( "Ejemplo: Pedro P�rez" );
        add( txtAutor );

        //Nivel
        etiquetaNivel = new JLabel( "Nivel: " );
        add( etiquetaNivel );
        cboNivel = new JComboBox( );
        for( int i = 1; i < 19; i++ )
        {
            cboNivel.addItem( new Integer( i ) );
        }
        add( cboNivel );

        //Clase Mundo
        etiquetaClaseMundo = new JLabel( "Clase Mundo: " );
        etiquetaClaseMundo.setToolTipText( "Ejemplo: BrazoMecanico" );
        add( etiquetaClaseMundo );
        txtClaseMundo = new JTextField( );
        txtClaseMundo.setToolTipText( "Ejemplo: BrazoMecanico" );
        add( txtClaseMundo );

        //Ubicaci�n
        etiquetaUbicacion = new JLabel( "Ubicaci�n: " );
        etiquetaUbicacion.setToolTipText( "Ejemplo: C:\\ejemplo1" );
        add( etiquetaUbicacion );
        txtUbicacion = new JTextField( );
        txtUbicacion.setToolTipText( "Ejemplo: C:\\ejemplo1" );
        add( txtUbicacion );

        //
        //Bot�n Ubicaci�n
        btnUbicacion = new JButton( "Seleccionar Ubicaci�n..." );
        btnUbicacion.setActionCommand( PanelDatos.SELECCIONAR_UBICACION );
        btnUbicacion.addActionListener( this );
        add( new JLabel( "" ) );
        add( btnUbicacion );
        
        //
        //Bot�n Agregar Archivos
        btnAgregarArchivosAdicionales = new JButton( "Agregar Archivos Adicionales..." );
        btnAgregarArchivosAdicionales.setActionCommand( PanelDatos.SELECCIONAR_ARCHIVOS_ADICIONALES );
        btnAgregarArchivosAdicionales.addActionListener( this );
        add( new JLabel( "" ) );
        add( btnAgregarArchivosAdicionales );

    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Devuelve el nombre del ejercicio
     * @return Nombre del ejercicio
     */
    public String darNombre( )
    {
        return txtNombre.getText( );
    }

    /**
     * Devuelve el Autor
     * @return Autor
     */
    public String darAutor( )
    {
        return txtAutor.getText( );
    }

    /**
     * Devuelve el nivel del ejercicio
     * @return Nivel del ejercicio
     */
    public int darNivel( )
    {
        Integer nivel = ( Integer )cboNivel.getSelectedItem( );
        return nivel.intValue( );
    }

    /**
     * Devuelve el nombre de la clase principal del mundo
     * @return Nombre de la clase principal del mundo
     */
    public String darClaseMundo( )
    {
        return txtClaseMundo.getText( );
    }

    /**
     * Devuelve la ruta de ubicaci�n de los archivos generados
     * @return Nombre de la clase principal del mundo
     */
    public String darUbicacion( )
    {
        return txtUbicacion.getText( );
    }

    //-----------------------------------------------------------------
    // Manejo de eventos
    //-----------------------------------------------------------------

    /**
     * Manejo de eventos del bot�n.
     * @param e Evento que gener� la acci�n.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( PanelDatos.SELECCIONAR_UBICACION.equals( e.getActionCommand( ) ) )
        {
            JFileChooser chooserDirectorio = new JFileChooser( );
            chooserDirectorio.setCurrentDirectory( new java.io.File( "." ) );
            chooserDirectorio.setDialogTitle( "Seleccionar Directorio" );
            chooserDirectorio.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
            chooserDirectorio.setVisible( true );
            if( chooserDirectorio.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
            {
                txtUbicacion.setText( chooserDirectorio.getSelectedFile( ).toString( ) );
            }
        }
        else if(PanelDatos.SELECCIONAR_ARCHIVOS_ADICIONALES.equals( e.getActionCommand( ) ))
        {
            principal.mostrarDialogoArchivosAdicionales();
        }
    }

}
