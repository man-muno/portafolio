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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Maneja operaciones generales de archivos
 * @author Pablo Barvo
 */
public class ManejadorArchivos
{

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    private ManejadorArchivos( )
    {
    }

    //-----------------------------------------------------------------
    // Métodos estaticos
    //-----------------------------------------------------------------

    /**
     * Copia un archivo desde un lugar a otro.
     * @param desde Ruta del archivo original
     * @param hasta lugar donde se va a copiar el archivo
     * @throws IOException Error al copiar el archivo
     */
    public static void copiarArchivo( String desde, String hasta ) throws IOException
    {
        InputStream in = new FileInputStream( desde );
        OutputStream out = new FileOutputStream( hasta );

        // lee y escribe
        byte[] buf = new byte[1024];
        int len;
        while( ( len = in.read( buf ) ) > 0 )
        {
            out.write( buf, 0, len );
        }
        in.close( );
        out.close( );

    }

    /**
     * Escribe un archivo de un arreglo de bytes
     * @param strFile Ruta del archivo a escribir
     * @param pData Información a escribir
     * @throws IOException Error al escribir el archivo
     */
    public static final void escribirArchivo( String strFile, byte[] pData ) throws IOException
    {
        BufferedOutputStream outStream = new BufferedOutputStream( new FileOutputStream( strFile ), 32768 );
        if( pData.length > 0 )
        {
            outStream.write( pData, 0, pData.length );
        }
        outStream.close( );
    }

    /**
     * Lee un archivo a un arreglo de bytes
     * @param strFile Ruta del archivo a leer
     * @return Arreglo de bytes del archivo
     * @throws IOException Error al leer el archivo
     */
    static public final byte[] leerArchivo( String strFile ) throws IOException
    {
        File archivo = new File( strFile );
        InputStream is = new FileInputStream( archivo );

        // Tamaño del archivo
        long length = archivo.length( );

        // No se puede crear un arreglo con un long.
        // Se verifica que no se mas grande que un int
        if( length > Integer.MAX_VALUE )
        {
            throw new IOException( "Archivo muy grande" );
        }

        // Crea el arreglo y lee los datos
        byte[] bytes = new byte[( int )length];

        int offset = 0;
        int numRead = 0;
        while( offset < bytes.length && ( numRead = is.read( bytes, offset, bytes.length - offset ) ) >= 0 )
        {
            offset += numRead;
        }

        // Se asegura que lo leyo todo
        if( offset < bytes.length )
        {
            throw new IOException( "No se pudo leer completo el archivo " + archivo.getName( ) );
        }

        // Cierra y devuelve los bytes
        is.close( );
        return bytes;

    }

    /**
     * Crea un directorio en la ruta especificada
     * @param directorio Ruta para crear el directorio
     * @throws IOException Excepción lanzada cuando hay un error al crear el directorio
     */
    public static void crearDirectorio( String directorio ) throws IOException
    {
        File dir = new File( directorio );
        if( !dir.mkdirs( ) )
        {
            throw new IOException( "Error al crear el directorio '" + directorio + "'" );
        }
    }

}
