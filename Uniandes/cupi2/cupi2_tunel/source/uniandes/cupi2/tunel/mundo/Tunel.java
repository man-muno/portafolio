/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupi2_tunel
 * Autor: Manuel Muñoz - 20-Nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.tunel.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Observable;
import java.util.Properties;

/**
 * Clase que representa el túnel
 */
public class Tunel extends Observable implements Runnable
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
    
    /**
     * Nombre del archivo de propiedades
     */
    public static final String ARCHIVO = "./data/tunel.properties";
    
    /**
     * Nombre de la propiedad donde se establece el puerto
     */
    public static final String PROPIEDADES_PUERTO = "tunel.puerto";
    
    /**
     * Protocolo de comunicación
     */
    public static final String PROTOCOLO = "http://";
    

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Cadena de caracteres donde se guardara la solicitud del usuario
     */
    private String solicitud;
    
    /**
     * Cadena de caracteres donde se guardara la respuesta del servidor
     */
    private String respuesta;
    
    /**
     * Puerto establecido en el properties
     */
    private int puerto;
    
    /**
     * Socket para enviar la información de respuesta del servidor al usuario
     */
    private Socket socketSolicitud;

    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor de la clase Tunel. Lee el archivo de propiedades
     * @throws ArchivoNoLeidoException Es generada cuando no se encuentra el archivo de propiedades
     */
    public Tunel( ) throws ArchivoNoLeidoException
    {
        solicitud = new String();
        respuesta = new String();
        Properties propiedades = new Properties();
        try
        {
            FileInputStream in = new FileInputStream(new File(ARCHIVO));
            propiedades.load( in );
            String strPuerto = propiedades.getProperty( PROPIEDADES_PUERTO );
            puerto = Integer.parseInt( strPuerto );
        }
        catch( FileNotFoundException e )
        {
            throw new ArchivoNoLeidoException("No se encuentra el archivo de propiedades");
        }
        catch( IOException e )
        {
            throw new ArchivoNoLeidoException("Problemas al cargar el archivo");
        }
    }



    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Crea la conexión con el browser
     */
    public void inicializarServidorSolicitud( ) throws IOException
    {
        ServerSocket socketServidor = null;
        socketServidor = new ServerSocket( puerto );
        socketSolicitud = socketServidor.accept( );
    }
    
    /**
     * Obtiene la solicitud del browser
     */
    private void manejarSolicitud( )
    {
        try
        {
            BufferedReader inSolicitud = new BufferedReader( new InputStreamReader( socketSolicitud.getInputStream( ) ) );
            solicitud = inSolicitud.readLine( );
            enviarSolicitud();
            String[] temp = new String[2];
            temp[0] = solicitud;
            temp[1] = respuesta;
            setChanged( );
            notifyObservers(temp);
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Envía la solicitud del cliente al servidor 
     */
    private void enviarSolicitud()
    {
        String solicitudaEnviar = solicitud.substring( "GET /".length( ),solicitud.indexOf( " HTTP" ) );
        try
        {
            URL url = new URL(PROTOCOLO+solicitudaEnviar);
            URLConnection conexionURL = url.openConnection( );
            InputStream in = conexionURL.getInputStream( );
            byte[] bytes = new byte[1024];
            int b = in.read(bytes);
            while (b != -1) 
            {
                respuesta += new String(bytes,0,b);
                socketSolicitud.getOutputStream( ).write( bytes,0,b);
                b = in.read(bytes);
            }            
            in.close( );
            socketSolicitud.getOutputStream( ).flush( );
        }
        catch( MalformedURLException e )
        {
            respuesta = "URL mal formado";
        }
        catch( IOException e )
        {
            respuesta = "Problemas de I/O: " + e.getMessage( );
        }
        finally 
        {
            try
            {
                socketSolicitud.getOutputStream( ).close( );
                socketSolicitud.close( );
            }
            catch( IOException e )
            {
            }
        }
    }

    /**
     * Método que se ejecuta cuando se inicia el thread. Se encarga de manejar la solicitud del cliente.
     */
    public void run( )
    {
        manejarSolicitud( );
    }
}