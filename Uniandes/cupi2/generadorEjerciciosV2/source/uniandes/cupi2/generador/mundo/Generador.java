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

package uniandes.cupi2.generador.mundo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Generador de ejercicios
 * @author Pablo Barvo
 */
public class Generador
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    //
    //De ubicación general
    private static final String RUTA_BIN = "bin";
    private static final String RUTA_DOCS = "docs";
    private static final String RUTA_API = "api";
    private static final String RUTA_SPECS = "specs";
    private static final String RUTA_DATA = "data";
    private static final String RUTA_IMAGENES = "imagenes";
    private static final String RUTA_CLASSES = "classes";
    private static final String RUTA_SOURCE = "source";
    private static final String RUTA_LIB = "lib";
    private static final String RUTA_TEST = "test";

    //
    //De ubicación interna
    private static final String DIRECTORIO_INTERFAZ = "interfaz";
    private static final String DIRECTORIO_MUNDO = "mundo";
    private static final String DIRECTORIO_PRUEBAS = "test";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ruta completa a los archivos de la plantilla
     */
    private String rutaPlantilla;

    /**
     * Reemplazador de propiedades
     */
    private ReemplazadorPropiedades reemplazador;

    /**
     * Propiedades para el reemplazador
     */
    private Properties propiedades;
    
    private ArrayList archivos;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor sin parámetros
     * @param rutaPlantilla Ruta de los archivos de la plantilla
     * @param archivos Archivos que se deben generar por defecto
     */
    public Generador( String rutaPlantilla, ArrayList archivos )
    {
        this.rutaPlantilla = rutaPlantilla;
        reemplazador = new ReemplazadorPropiedades( );
        this.archivos = archivos;
    }

    //-----------------------------------------------------------------
    // Métodos públicos
    //-----------------------------------------------------------------

    /**
     * Genera el ejercicio especificado
     * @param ejercicio Ejercicio a generar
     * @throws GeneradorException Error al generar el ejercicio
     */
    public void generarEjercicio( Ejercicio ejercicio ) throws GeneradorException
    {
        //
        //Crea la lista de propiedades para reemplazar
        crearListaPropiedades( ejercicio );
        //
        //Genera la estructura de directorios
        try
        {
            generarDirectorios( ejercicio );
        }
        catch( Exception e )
        {
            throw new GeneradorException( "Error al crear los directorios.", e );
        }
        //
        //Genera los archivos del proyecto de eclipse
        try
        {
            generarProyectoEclipse( ejercicio );
        }
        catch( Exception e )
        {
            throw new GeneradorException( "Error al generar los archivos del proyecto eclipse.", e );
        }
        //
        //Genera los archivos BAT
        try
        {
            generarBats( ejercicio );
        }
        catch( Exception e )
        {
            throw new GeneradorException( "Error al generar los archivos BAT.", e );
        }
        //
        //Genera la documentación
        try
        {
            generarDocumentacion( ejercicio );
        }
        catch( Exception e )
        {
            throw new GeneradorException( "Error al generar la documentación del ejercicio: " + e.getMessage( ), e );
        }
        //
        //Genera las clases principales
        try
        {
            generarClases( ejercicio );
        }
        catch( Exception e )
        {
            throw new GeneradorException( "Error al generar las clases principales del ejercicio.", e );
        }
        //
        //Genera los archivos adicionales
        try
        {
            generarAdicionales( ejercicio );
        }
        catch( Exception e )
        {
            throw new GeneradorException( "Error al generar los archivos adicionales del ejercicio.", e );
        }
    }

    //-----------------------------------------------------------------
    // Métodos privados
    //-----------------------------------------------------------------

    /**
     * Genera los directorios del proyecto
     * @param ejercicio Ejercicio a generar
     * @throws IOException Error al crear el directorio
     */
    private void generarDirectorios( Ejercicio ejercicio ) throws IOException
    {
        //-----------------------------
        // Directorios Principales
        //-----------------------------
        //
        //Directorio 'bin'
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_BIN );
        //
        //Directorio 'classes'
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_CLASSES );
        //
        //Directorio 'docs' y 'docs/api'
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_DOCS + "/" + Generador.RUTA_API );
        //
        //Directorio 'docs/specs'
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_DOCS + "/" + Generador.RUTA_SPECS );
        //
        //Directorio 'data'
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_DATA );
        //
        //Directorio 'data/imagenes'
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_DATA + "/" + Generador.RUTA_IMAGENES );        
        //
        //Directorio 'lib'
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_LIB );
        //
        //Directorio 'source' de la interfaz
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_SOURCE + "/" + ejercicio.darRutaPaquete( ) + "/" + Generador.DIRECTORIO_INTERFAZ );
        //
        //Directorio 'source' del mundo
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_SOURCE + "/" + ejercicio.darRutaPaquete( ) + "/" + Generador.DIRECTORIO_MUNDO );

        //-----------------------------
        // Directorios de las Pruebas
        //-----------------------------
        //
        //Directorio 'classes'
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_TEST + "/" + Generador.RUTA_CLASSES );
        //
        //Directorio 'data'
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_TEST + "/" + Generador.RUTA_DATA );
        //
        //Directorio 'lib'
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_TEST + "/" + Generador.RUTA_LIB );
        //
        //Directorio 'source' de la interfaz
        ManejadorArchivos.crearDirectorio( ejercicio.darUbicacion( ) + "/" + Generador.RUTA_TEST + "/" + Generador.RUTA_SOURCE + "/" + ejercicio.darRutaPaquete( ) + "/" + Generador.DIRECTORIO_PRUEBAS );
    }

    /**
     * Genera los archivos BAT del proyecto
     * @param ejercicio Ejercicio a generar
     * @throws IOException Error al copiar o reemplazar los archivos
     */
    private void generarBats( Ejercicio ejercicio ) throws IOException
    {
        //
        //Archivo build.bat
        generarArchivo( ejercicio, "/" + Generador.RUTA_BIN + "/build.bat", true );
        //
        //Archivo buildTest.bat
        generarArchivo( ejercicio, "/" + Generador.RUTA_BIN + "/buildTest.bat", true );
        //
        //Archivo clean.bat
        generarArchivo( ejercicio, "/" + Generador.RUTA_BIN + "/clean.bat", true );
        //
        //Archivo cleanTest.bat
        generarArchivo( ejercicio, "/" + Generador.RUTA_BIN + "/cleanTest.bat", true );
        //
        //Archivo doc.bat
        generarArchivo( ejercicio, "/" + Generador.RUTA_BIN + "/doc.bat", true );
        //
        //Archivo run.bat
        generarArchivo( ejercicio, "/" + Generador.RUTA_BIN + "/run.bat", true );
        //
        //Archivo runTest.bat
        generarArchivo( ejercicio, "/" + Generador.RUTA_BIN + "/runTest.bat", true );
    }

    /**
     * Genera la documentación del proyecto
     * @param ejercicio Ejercicio a generar
     * @throws IOException
     */
    private void generarDocumentacion( Ejercicio ejercicio ) throws IOException
    {
/*        //
        //Archivo Descripcion.doc
        generarArchivo( ejercicio, "/" + Generador.RUTA_DOCS + "/" + Generador.RUTA_SPECS + "/Descripcion.doc", false );
        //
        //Archivo RequerimientosFuncionales.doc
        generarArchivo( ejercicio, "/" + Generador.RUTA_DOCS + "/" + Generador.RUTA_SPECS + "/RequerimientosFuncionales.doc", false );
        //
        //Archivo Modelo.mdl
        generarArchivo( ejercicio, "/" + Generador.RUTA_DOCS + "/" + Generador.RUTA_SPECS + "/Modelo.mdl", true );*/
        
        for( int i = 0; i < archivos.size( ); i++ )
        {
            Archivo archivo = (Archivo)archivos.get( i );
            generarArchivo( ejercicio, "/" + Generador.RUTA_DOCS + "/" + Generador.RUTA_SPECS + "/" +archivo.darNombreArchivo( ), archivo.esReemplazable( ));
        }
    }

    /**
     * Genera las clases Principales del proyecto
     * @param ejercicio Ejercicio a generar
     * @throws IOException
     */
    private void generarClases( Ejercicio ejercicio ) throws IOException
    {
        //
        //Archivo Interfaz.java
        generarArchivo( ejercicio, "/" + Generador.RUTA_SOURCE + "/Interfaz.java", "/" + Generador.RUTA_SOURCE + "/" + ejercicio.darRutaPaquete( ) + "/" + Generador.DIRECTORIO_INTERFAZ + "/" + ejercicio.darClaseInterfaz( ) + ".java", true );
        //
        //Archivo Mundo.java
        generarArchivo( ejercicio, "/" + Generador.RUTA_SOURCE + "/Mundo.java", "/" + Generador.RUTA_SOURCE + "/" + ejercicio.darRutaPaquete( ) + "/" + Generador.DIRECTORIO_MUNDO + "/" + ejercicio.darClasePrincipal( ) + ".java", true );
        //
        //Archivo Test.java
        generarArchivo( ejercicio, "/" + Generador.RUTA_SOURCE + "/Test.java", "/" + Generador.RUTA_TEST + "/" + Generador.RUTA_SOURCE + "/" + ejercicio.darRutaPaquete( ) + "/" + Generador.DIRECTORIO_PRUEBAS + "/" + ejercicio.darClasePruebas( )
                + ".java", true );

        //
        //Archivo PanelOpciones.java
        generarArchivo( ejercicio, "/" + Generador.RUTA_SOURCE + "/PanelExtension.java", "/" + Generador.RUTA_SOURCE + "/" + ejercicio.darRutaPaquete( ) + "/" + Generador.DIRECTORIO_INTERFAZ + "/PanelExtension.java", true );
        
        //
        //Archivo PanelImagen.java
        generarArchivo( ejercicio, "/" + Generador.RUTA_SOURCE + "/PanelImagen.java", "/" + Generador.RUTA_SOURCE + "/" + ejercicio.darRutaPaquete( ) + "/" + Generador.DIRECTORIO_INTERFAZ + "/PanelImagen.java", true );
    }

    /**
     * Genera los archivos necesarios para el proyecto de eclipse
     * @param ejercicio Ejercicio a generar
     * @throws IOException Error al generar los archivos de eclipse
     */
    private void generarProyectoEclipse( Ejercicio ejercicio ) throws IOException
    {
        //
        //Archivo .classpath
        generarArchivo( ejercicio, "/.classpath", true );
        //
        //Archivo .project
        generarArchivo( ejercicio, "/.project", true );
    }

    /**
     * Genera los archvios adicionales del proyecto
     * @param ejercicio Ejercicio a generar
     * @throws IOException Error al generar los archivos adicionales
     */
    private void generarAdicionales( Ejercicio ejercicio ) throws IOException
    {
        //
        //Archivo junit.jar
        generarArchivo( ejercicio, "/" + Generador.RUTA_TEST + "/" + Generador.RUTA_LIB + "/junit.jar", false );
        
        //
        //Archivo de la imagen del encabezado
        generarArchivo( ejercicio, "/" + Generador.RUTA_DATA+ "/" + Generador.RUTA_IMAGENES + "/titulo.png", false );
        
        //
        //Archivo de la imagen del encabezado
        generarArchivo( ejercicio, "/" + Generador.RUTA_DATA+ "/" + Generador.RUTA_IMAGENES  + "/readme.txt", true );
    }

    /**
     * Crea la lista de propiedades para reemplazar
     * @param ejercicio Ejercicio a generar
     */
    private void crearListaPropiedades( Ejercicio ejercicio )
    {
        ConvertidorCamelPascal convertidor = new ConvertidorCamelPascal( );
        propiedades = new Properties( );
        propiedades.setProperty( "NOMBRE_COMPLETO", ejercicio.darNombreCompleto( ) );
        propiedades.setProperty( "NOMBRE", ejercicio.darNombre( ) );
        propiedades.setProperty( "AUTOR", ejercicio.darAutor( ) );
        propiedades.setProperty( "FECHA", ejercicio.darFechaFormateada( ) );
        propiedades.setProperty( "CLASE_INTERFAZ", ejercicio.darClaseInterfaz( ) );
        propiedades.setProperty( "CLASE_MUNDO", ejercicio.darClasePrincipal( ) );
        propiedades.setProperty( "CLASE_PRUEBAS", ejercicio.darClasePruebas( ) );
        propiedades.setProperty( "NOMBRE_ATRIBUTO_MUNDO", convertidor.convertirCamel( ejercicio.darClasePrincipal( ) ) );
        propiedades.setProperty( "PAQUETE", ejercicio.darPaquete( ) );
        propiedades.setProperty( "RUTA_PAQUETE", ejercicio.darRutaPaquete( ) );
        propiedades.setProperty( "DIRECTORIO_INTERFAZ", Generador.DIRECTORIO_INTERFAZ );
        propiedades.setProperty( "DIRECTORIO_MUNDO", Generador.DIRECTORIO_MUNDO );
        propiedades.setProperty( "DIRECTORIO_PRUEBAS", Generador.DIRECTORIO_PRUEBAS );
        propiedades.setProperty( "RUTA_BIN", Generador.RUTA_BIN );
        propiedades.setProperty( "RUTA_DOCS", Generador.RUTA_DOCS );
        propiedades.setProperty( "RUTA_API", Generador.RUTA_API );
        propiedades.setProperty( "RUTA_SPECS", Generador.RUTA_SPECS );
        propiedades.setProperty( "RUTA_DATA", Generador.RUTA_DATA );
        propiedades.setProperty( "RUTA_CLASSES", Generador.RUTA_CLASSES );
        propiedades.setProperty( "RUTA_SOURCE", Generador.RUTA_SOURCE );
        propiedades.setProperty( "RUTA_LIB", Generador.RUTA_LIB );
        propiedades.setProperty( "RUTA_TEST", Generador.RUTA_TEST );
    }

    /**
     * Genera el archivo especificado
     * @param ejercicio Ejercicio a generar
     * @param pathArchivo Archivo a generar
     * @param reemplazar Si reemplaza las propiedades o no
     * @throws IOException Error al generar el archivo
     */
    private void generarArchivo( Ejercicio ejercicio, String pathArchivo, boolean reemplazar ) throws IOException
    {
        //
        //Genera el archivo
        generarArchivo( ejercicio, pathArchivo, pathArchivo, reemplazar );
    }

    /**
     * Genera el archivo especificado
     * @param ejercicio Ejercicio a generar
     * @param pathArchivoInicial Archivo inical
     * @param pathArchivoFinal Archivo a generar
     * @param reemplazar Si reemplaza las propiedades o no
     * @throws IOException Error al generar el archivo
     */
    private void generarArchivo( Ejercicio ejercicio, String pathArchivoInicial, String pathArchivoFinal, boolean reemplazar ) throws IOException
    {
        //
        //Copia el archivo
        ManejadorArchivos.copiarArchivo( rutaPlantilla + pathArchivoInicial, ejercicio.darUbicacion( ) + pathArchivoFinal );
        //
        //Reemplaza las propiedades si es necesario
        if( reemplazar )
        {
            reemplazador.reemplazarArchivo( ejercicio.darUbicacion( ) + pathArchivoFinal, propiedades );
        }
    }

}
