/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 
 * Ejercicio: generadorEjerciciosV2
 * Autor: Pablo Barvo - Sep 2, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.generador.interfaz;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.generador.mundo.Archivo;
import uniandes.cupi2.generador.mundo.DatoInvalidoException;
import uniandes.cupi2.generador.mundo.Ejercicio;
import uniandes.cupi2.generador.mundo.Generador;
import uniandes.cupi2.generador.mundo.GeneradorException;
import uniandes.cupi2.generador.mundo.Validador;

/**
 * Ventana Principal del generador
 */
public class InterfazGenerador extends JFrame
{

    //-----------------------------------------------------------------
    // Atributos de Interfaz
    //-----------------------------------------------------------------

    /**
     * Panel de la imágen superior
     */
    private PanelImagen panelImagen;

    /**
     * Panel de los datos
     */
    private PanelDatos panelDatos;

    /**
     * Panel de selección de fecha
     */
    private PanelFecha panelFecha;

    /**
     * Panel de botones
     */
    private PanelBotones panelBotones;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor sin parametros
     */
    public InterfazGenerador( )
    {
        construirForma( );
    }

    //-----------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------

    /**
     *  
     */
    public void generarEjercicio( )
    {
        //
        //Crea un ejercicio
        Ejercicio ejercicio = new Ejercicio( );
        ejercicio.cambiarNombre( panelDatos.darNombre( ) );
        ejercicio.cambiarAutor( panelDatos.darAutor( ) );
        ejercicio.cambiarNivel( panelDatos.darNivel( ) );
        ejercicio.cambiarClasePrincipal( panelDatos.darClaseMundo( ) );
        ejercicio.cambiarUbicacion( panelDatos.darUbicacion( ) );
        ejercicio.cambiarFecha( panelFecha.darFercha( ) );
        try
        {
            //
            //Valida el ejercicio
            Validador validador = new Validador( );
            validador.validar( ejercicio );
            //
            //Ahora genera el ejercicio
            Generador generador = new Generador( darRutaPlantilla( ),darArchivosDefecto( ) );
            generador.generarEjercicio( ejercicio );
            JOptionPane.showMessageDialog( this, "Ejercicio generado correctamente!", "OK", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( GeneradorException e )
        {
            JOptionPane.showMessageDialog( this, "Error al generar el ejercicio:\r\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( DatoInvalidoException e )
        {
            JOptionPane.showMessageDialog( this, "Los datos son inválidos:\r\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( FileNotFoundException e )
        {
            JOptionPane.showMessageDialog( this, "No se encuentra el archivo de configuración 'generador.properties'.", "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, "Error al cargar 'generador.properties': " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "Error al cargar una propiedad: " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    private String darRutaPlantilla( ) throws FileNotFoundException, IOException
    {
        //
        //Carga el nombre de la plantilla
        Properties properties = new Properties( );
        properties.load( new FileInputStream( "data/generador.properties" ) );
        return "data/plantillas/" + properties.getProperty( "generador.plantilla" );
    }
    
    private ArrayList darArchivosDefecto() throws FileNotFoundException, IOException, NumberFormatException
    {
        //
        //Carga el nombre de los archivos por defecto
        Properties properties = new Properties( );
        properties.load( new FileInputStream( "data/generador.properties" ) );
        int tam = Integer.parseInt( properties.getProperty( "generador.cantidad.documentos" ));
        ArrayList archivos = new ArrayList();
        for( int i = 0; i < tam; i++ )
        {
            archivos.add( new Archivo(properties.getProperty( "generador.documento" + (i+1) + ".nombre"),Boolean.parseBoolean( properties.getProperty( "generador.documento" + (i+1) + ".reemplazable"))));
        }
        return archivos;
    }


    public void mostrarDialogoArchivosAdicionales( )
    {
        
    }

    
    /**
     * Este método sirve para construir la forma inicializando cada uno de los componentes. <br>
     * <b>pre: </b> La ventana está vacía <br>
     * <b>post: </b> Se inicializaron los componentes gráficos de la aplicación
     */
    private void construirForma( )
    {

        // organizar el panel principal
        getContentPane( ).setLayout( new BorderLayout( ) );
        setSize( 700, 400 );
        setTitle( "Generador de Ejercicios - Proyecto CUPI2" );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // construir los paneles
        panelImagen = new PanelImagen( );
        panelDatos = new PanelDatos(this );
        panelFecha = new PanelFecha( );
        panelBotones = new PanelBotones( this );

        getContentPane( ).add( panelImagen, BorderLayout.NORTH );
        getContentPane( ).add( panelDatos, BorderLayout.CENTER );
        getContentPane( ).add( panelFecha, BorderLayout.EAST );
        getContentPane( ).add( panelBotones, BorderLayout.SOUTH );

        pack( );
    }

    //-----------------------------------------------------------------
    // Main
    //-----------------------------------------------------------------

    /**
     * Método main
     * @param args Argumentos de entrada.
     */
    public static void main( String[] args )
    {
        InterfazGenerador interfaz = new InterfazGenerador( );
        interfaz.setVisible( true );
    }
}
