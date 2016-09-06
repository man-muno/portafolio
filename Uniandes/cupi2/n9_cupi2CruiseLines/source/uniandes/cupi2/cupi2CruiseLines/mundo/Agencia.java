/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Agencia.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupi2CruiseLines
 * Autor: Manuel Mu�oz - 13-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupi2CruiseLines.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa una agencia de viajes en cruceros. <b>inv:</b></br> el primerCrucero est� bien construido: no existe ninguna crucero tal que
 * crucero.obtenerSiguiente() == puertoSalida</br> El crucero est� bien construido: no existen crucero1 y crucero2 tal que crucero1.obtenerSiguiente() ==
 * crucero2.obtenerSiguiente() && crucero1 != crucero2</br> no hay dos cruceros con el mismo nombre
 */
public class Agencia implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serializaci�n de la clase
     */
    private static final long serialVersionUID = -6317124615040187608L;

    /**
     * Ruta donde se guarda el archivo con la informacion del mundo
     */
    public static final String RUTA_ARCHIVO_PERSISTENCIA = "./data/cupi2-cruiseLines.data";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Primer crucero de la agencia
     */
    private Crucero primerCrucero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor de la clase Agencia. Llama al m�todo del mundo que carga el archivo el estado del mundo
     * @param nombreArchivo Nombre del archivo de persistencia. Diferente de null.
     */
    public Agencia( String nombreArchivo ) throws PersistenciaException
    {
        cargarCruiseLine( nombreArchivo );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que agrega un nuevo crucero. <br>
     * <b>post:</b>Existe un nuevo crucero en caso que ya no exista uno con el mismo nombre <br>
     * @param nombre Nombre del nuevo crucero. Diferente de null.
     * @param numDias Cantidad de d�as que dura el crucero. Entero mayor a cero.
     * @param precio Precio total del crucero. Entero mayor a cero.
     * @param fecha Fecha de salida del crucero. Diferente de null.
     * @throws CruceroExisteException Error generado cuando ya existe un crucero con el nombre
     */
    public void agregarCrucero( String nombre, int numDias, int precio, Date fecha ) throws CruceroExisteException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        String strFecha = sdf.format( fecha );
        Crucero crucero = new Crucero( nombre, numDias, precio, strFecha );
        if( primerCrucero == null )
        {
            primerCrucero = crucero;
        }
        else
        {
            Crucero ultimo = null;
            // Variable que tendr� un crucero
            Crucero iterador = primerCrucero;
            while( iterador != null )
            {
                // Si el nombre es igual no se agrega
                if( iterador.darNombre( ).equalsIgnoreCase( nombre ) )
                {
                    throw new CruceroExisteException( "El crucero que se desea agregar ya existe" );
                }
                // Se busca cual es el ultimo crucero
                if( iterador.darSiguiente( ) != null )
                    ultimo = iterador.darSiguiente( );
                else
                    ultimo = iterador;
                iterador = iterador.darSiguiente( );
            }
            // Se agrega el crucero de ultimo
            ultimo.cambiarSiguiente( crucero );
        }
        verificarInvariante( );
    }

    /**
     * Retorna el crucero que existe con el nombre dado. <br>
     * <b>post:</b>Retorna un crucero con el nombre dado, en caso de no existir lanza una excepci�n <br>
     * @param nombreCrucero Nombre del crucero que se quiere buscar. Diferente de null.
     * @return Retorna el crucero identificado con ese nombre.
     * @throws CruceroNoExisteException Excepci�n lanzada cuando no se encontr� un crucero con el nombre dado.
     */
    public Crucero darCrucero( String nombreCrucero ) throws CruceroNoExisteException
    {
        if( primerCrucero == null )
        {
            throw new CruceroNoExisteException( "No se encontr� ning�n crucero" );
        }
        Crucero iterador = primerCrucero;
        while( iterador != null )
        {
            if( iterador.darNombre( ).equalsIgnoreCase( nombreCrucero ) )
            {
                return iterador;
            }
            iterador = iterador.darSiguiente( );
        }
        if( iterador == null )
        {
            throw new CruceroNoExisteException( "No se encontr� el crucero buscado" );
        }
        return null;
    }

    /**
     * Retorna una lista con los nombres de los cruceros. <br>
     * <b>post:</b> Retorna una lista con los nombres de los cruceros. Esa lista es diferente de null<br>
     * @return Lista con objetos de tipo String que son los nombres de los cruceros. Puede ser vacia pero no null.
     */
    public ArrayList darCruceros( )
    {
        ArrayList cruceros = new ArrayList( );
        Crucero iterador = primerCrucero;
        while( iterador != null )
        {
            cruceros.add( iterador.darNombre( ) );
            iterador = iterador.darSiguiente( );
        }
        return cruceros;
    }

    /**
     * Agrega una nueva ciudad a un crucero dado. <br>
     * <b>post:</b> Agrega una nueva ciudad a un crucero dado. En caso de no existir el crucero o que ya exista la ciudad para ese crucero se lanza una excepci�n<br>
     * @param nombreCrucero Nombre del crucero por donde se quiere que pase el crucero. Diferente de null.
     * @param nombreCiudad Nombre de la nueva ciudad. Diferente de null.
     * @param pais Pa�s donde pertenece la nueva ciudad. Diferente de null.
     * @param coordX Coordenada X donde se encuentra la ciudad en el mapa. Entero mayor a cero.
     * @param coordY Coordenada Y donde se encuentra la ciudad en el mapa. Entero mayor a cero.
     * @throws CruceroNoExisteException Excepci�n lanzada cuando el crucero donde se quiere agregar la ciudad no existe.
     * @throws CiudadExisteException Excepci�n lanzada cuando ya existe la ciudad que se quiere agregar.
     */
    public void agregarCiudad( String nombreCrucero, String nombreCiudad, String pais, double coordX, double coordY ) throws CruceroNoExisteException, CiudadExisteException
    {
        Crucero crucero = darCrucero( nombreCrucero );
        try
        {
            crucero.buscarCiudad( nombreCiudad, pais );
            throw new CiudadExisteException( "La ciudad que se quiere agregar ya existe" );
        }
        catch( CiudadNoExisteException e )
        {
            crucero.agregarCiudad( nombreCiudad, pais, coordX, coordY );
            verificarInvariante( );
        }
    }

    /**
     * Retorna una ciudad dado en nombre de un crucero. <br>
     * <b>post:</b> Retorna una ciudad dado su nombre, su pa�s y el nombre del crucero al que pertenece. En caso de no existir el crucero o la ciudad se lanza una excepci�n<br>
     * @param nombreCrucero Nombre del crucero donde se quiere buscar la ciudad. Diferente de null.
     * @param nombre Nombre de la ciudad que se quiere buscar. Diferente de null.
     * @param pais Nombre del pa�s donde se quiere buscar la ciudad.
     * @return Retorna la ciudad en el crucero identificada con el nombre y el pa�s donde pertenece.
     * @throws CruceroNoExisteException Excepci�n lanzada cuando el crucero donde se quiere obtener la ciudad no existe.
     * @throws CiudadNoExisteException Excepci�n lanzada cuando la ciudad que se quiere buscar no existe en el crucero.
     */
    public Ciudad darCiudad( String nombreCrucero, String nombre, String pais ) throws CruceroNoExisteException, CiudadNoExisteException
    {
        Crucero crucero = darCrucero( nombreCrucero );
        return crucero.buscarCiudad( nombre, pais );
    }

    /**
     * Retorna el puerto de salida de un crucero dado. <br>
     * <b>post:</b> Retorna el puerto de salida de un crucero dado, puede ser null. En caso de no existir el crucero se lanza una excepci�n<br>
     * @param nombreCrucero Nombre del crucero del cual se quiere obtener el puerto de salida. Diferente de null.
     * @return Puerto de salida del crucero. Puede ser null.
     * @throws CruceroNoExisteException Excepci�n lanzada cuando no existe el crucero.
     */
    public Ciudad darPuertoSalida( String nombreCrucero ) throws CruceroNoExisteException
    {
        return darCrucero( nombreCrucero ).darPuertoSalida( );
    }

    /**
     * Retorna el puerto de llegada de un crucero dado. <br>
     * <b>post:</b> Retorna el puerto de llegada de un crucero dado, puede ser null. En caso de no existir el crucero se lanza una excepci�n<br>
     * @param nombreCrucero Nombre del crucero del cual se quiere obtener el puerto de llegada. Diferente de null.
     * @return Puerto de llegada del crucero. Puede ser null.
     * @throws CruceroNoExisteException Excepci�n lanzada cuando no existe el crucero.
     */
    public Ciudad darPuertoLlegada( String nombreCrucero ) throws CruceroNoExisteException
    {
        return darCrucero( nombreCrucero ).darPuertoLlegada( );
    }

    /**
     * Retorna una lista con las ciudades por las que pasa el crucero. <br>
     * <b>post:</b>Se retorna una lista con objetos de tipo Ciudad, puede ser vac�a pero no null. En caso de no existir el crucero se lanza una excepci�n <br>
     * @param nombreCrucero Nombre del crucero donde se quieren obtener las ciudades. Diferente de null.
     * @return Lista con objetos de tipo Ciudad. Puede ser vac�a, pero no null.
     * @throws CruceroNoExisteException Excepci�n lanzada cuando no se encuentra el crucero.
     */
    public ArrayList darCiudades( String nombreCrucero ) throws CruceroNoExisteException
    {
        return darCrucero( nombreCrucero ).darCiudades( );
    }

    /**
     * Retorna la ciudad siguiente a la que cuyos datos son pasados por par�metros. <br>
     * <b>post:</b> Retorna la ciudad siguiente a la cual se pasan los par�metros, puede ser null, en caso de no existir el crucero o la ciudad se lanza una excepci�n<br>
     * @param nombreCrucero Nombre del crucero donde se quiere obtener la siguiente ciudad. Diferente de null.
     * @param nombre Nombre de la ciudad de la cual se quiere obtener la siguiente ciudad. Diferente de null.
     * @param pais Pa�s de la ciudad de la cual se quiere obtener la siguiente ciudad. Diferente de null.
     * @return La ciudad siguiente cuyos datos son pasados por par�metros. Puede ser null.
     * @throws CruceroNoExisteException Excepci�n lanzada cuando no se encuentra el crucero.
     * @throws CiudadNoExisteException Excepci�n lanzada cuando no se encuentra la ciudad a la cual se quiere obtener la siguiente.
     */
    public Ciudad darSiguienteCiudad( String nombreCrucero, String nombre, String pais ) throws CruceroNoExisteException, CiudadNoExisteException
    {
        return darCrucero( nombreCrucero ).darSiguienteCiudad( nombre, pais );
    }

    /**
     * Retorna la ciudad anterior a la que cuyos datos son pasados por par�metros. <br>
     * <b>post:</b> Retorna la ciudad anterior a la cual se pasan los par�metros, puede ser null. En caso de no existir el crucero o la ciudad se lanza una excepci�n<br>
     * @param nombreCrucero Nombre del crucero donde se quiere obtener la anterior ciudad. Diferente de null.
     * @param nombre Nombre de la ciudad de la cual se quiere obtener la anterior ciudad. Diferente de null.
     * @param pais Pa�s de la ciudad de la cual se quiere obtener la anterior ciudad. Diferente de null.
     * @return La ciudad anterior cuyos datos son pasados por par�metros. Puede ser null.
     * @throws CruceroNoExisteException Excepci�n lanzada cuando no se encuentra el crucero.
     * @throws CiudadNoExisteException Excepci�n lanzada cuando no se encuentra la ciudad a la cual se quiere obtener la anterior.
     */
    public Ciudad darAnteriorCiudad( String nombreCrucero, String nombre, String pais ) throws CruceroNoExisteException, CiudadNoExisteException
    {
        return darCrucero( nombreCrucero ).darAnteriorCiudad( nombre, pais );
    }

    /**
     * Elimina una ciudad dada de un crucero dado. <br>
     * <b>post:</b> Se elimina la ciudad identificada con el nombre y el pa�s. En caso que la ciudad no exista o el crucero del cual se quiere eliminar la ciudad no exista,
     * se lanza una excepci�n<br>
     * @param nombreCrucero Nombre del crucero donde se quiere eliminar la ciudad. Diferente de null.
     * @param nombreCiudad Nombre de la ciudad que se quiere eliminar. Diferente de null.
     * @param pais Nombre de pa�s donde se quiere eliminar la ciudad. Diferente de null.
     * @throws CiudadNoExisteException Excepci�n lanzada cuando no existe la ciudad que se quiere eliminar.
     * @throws CruceroNoExisteException Excepci�n lanzada cuando no existe el crucero de donde se quiere eliminar la ciudad.
     */
    public void eliminarCiudad( String nombreCrucero, String nombreCiudad, String pais ) throws CiudadNoExisteException, CruceroNoExisteException
    {
        darCrucero( nombreCrucero ).eliminarCiudad( nombreCiudad, pais );
        verificarInvariante( );
    }

    /**
     * Es el m�todo que va escribir el archivo con el reporte que se necesita.</br> <b>post:</b> Se crea un archivo cuyo nombre y ubicaci�n es definida por el usuario. Este
     * archivo contiene la informaci�n en texto del mundo </br>
     * @param rutaArchivo El ruta del archivo en el que se va escribir. Diferente de null.
     * @param nombreArchivo El nombre del archivo que va generar. Diferente de null.
     * @throws FileNotFoundException En caso que no se pueda escribir el archivo.
     * @throws CruceroNoExisteException Excepci�n que se lanza en caso que se quiera buscar un crucero que no exista.
     */
    public void generarItinerario( String rutaNombre, String nombreArchivo ) throws FileNotFoundException, CruceroNoExisteException
    {
        File directorioReporte = new File( rutaNombre );
        if( !directorioReporte.exists( ) )
        {
            directorioReporte.mkdirs( );
        }
        File archivoReporte = new File( directorioReporte, nombreArchivo );
        PrintWriter out = new PrintWriter( archivoReporte );
        out.println( "Reporte Cupi2 CriseLines" );
        out.println( );

        ArrayList cruceros = darCruceros( );

        if( cruceros.isEmpty( ) )
        {
            out.println( "No hay cruceros en el sistema" );
        }

        for( int i = 0; i < cruceros.size( ); i++ )
        {
            String nombre = ( String )cruceros.get( i );
            out.println( "Crucero: " );
            out.println( nombre );
            Crucero crucero = darCrucero( nombre );
            out.println( "Fecha Salida: " );
            out.println( crucero.darFechaSalida( ) );
            out.println( "Duraci�n (d�as): " + crucero.darDuracion( ) );
            out.println( "Ciudades Visitadas: " + crucero.darNumeroCiudades( ) );

            ArrayList ciudades = darCiudades( nombre );
            int numeroDiasViaje = crucero.darDuracion( ) - ciudades.size( );
            for( int j = 0; j < ciudades.size( ); j++ )
            {
                if( ( j + 1 ) == ciudades.size( ) )
                {
                    for( int k = 0; k < numeroDiasViaje; k++ )
                    {
                        out.println( "\t- D�a de Viaje" );
                    }
                }
                Ciudad ciudad = ( Ciudad )ciudades.get( j );
                out.println( "\t- Nombre: " + ciudad.darNombre( ) + " - Pa�s: " + ciudad.darPais( ) );
            }
            out.println( );
        }
        out.close( );
    }

    /**
     * Guarda en un archivo identificado con el nombre, el estado del mundo. </br> <b>post:</b> El estado del mundo quedar� guardado en un archivo que estar� identificado con
     * rutaNombre</br>
     * @param rutaNombre ruta y nombre del archivo. Diferente de null.
     * @throws PersistenciaException Lanzada cuando no se encuentra el archivo o no se puede guardar en �l.
     */
    public void guardarCruiseLine( String rutaNombre ) throws PersistenciaException
    {
        try
        {
            FileOutputStream archivo = new FileOutputStream( rutaNombre );
            ObjectOutputStream objetoSaliente = new ObjectOutputStream( archivo );
            objetoSaliente.writeObject( primerCrucero );
            objetoSaliente.close( );
            archivo.close( );
        }
        catch( IOException e )
        {
            throw new PersistenciaException( "Error al salvar: " + e.getMessage( ) );
        }

    }

    /**
     * Cargar de un archivo identificado con el nombre el estado del mundo que se encuentra guardado.</br> <b>post:</b> El archivo que est� identificado con rutaNombre queda
     * cargado en el sistema.</br>
     * @param rutaNombre ruta y nombre del archivo. Diferente de null
     * @throws PersistenciaException Lanzada cuando ocurre alg�n problema con el archivo o con su formato
     * 
     */
    public void cargarCruiseLine( String rutaNombre ) throws PersistenciaException
    {
        try
        {
            File archivo = new File( rutaNombre );
            if( archivo.exists( ) )
            {
                ObjectInputStream ois = new ObjectInputStream( new FileInputStream( archivo ) );
                primerCrucero = ( Crucero )ois.readObject( );
                ois.close( );
            }
            else
            {
                primerCrucero = null;
            }
        }
        catch( FileNotFoundException e )
        {
            throw new PersistenciaException( "No se encontr� archivo de persistencia. \nAl cerrar la aplicaci�n se generar� por primera vez el archivo de persistencia " );
        }
        catch( IOException e )
        {
            throw new PersistenciaException( "No fue posible leer el archivo de persistencia" + e.getMessage( ) );
        }
        catch( ClassNotFoundException e )
        {
            throw new PersistenciaException( "Problemas al cargar el archivo de persistencia" + e.getMessage( ) );
        }
    }

    /**
     * Verifica la invariante de la clase</br> <b>inv:</b></br> el primerCrucero est� bien construido: no existe ninguna crucero tal que crucero.obtenerSiguiente() ==
     * puertoSalida</br> El crucero est� bien construido: no existen crucero1 y crucero2 tal que crucero1.obtenerSiguiente() == crucero2.obtenerSiguiente() && crucero1 !=
     * crucero2</br> no hay dos cruceros con el mismo nombre
     */
    private void verificarInvariante( )
    {
        // La lista no tiene ciclos con el primer elemento
        if( primerCrucero != null )
        {
            Crucero temp1 = primerCrucero;
            while( temp1 != null )
            {
                assert ( temp1.darSiguiente( ) != primerCrucero ) : "La lista tiene un ciclo";
                temp1 = temp1.darSiguiente( );
            }
        }

        // No hay ciclos ni direcciones repetidas
        Crucero crucero1 = primerCrucero;
        while( crucero1 != null )
        {
            Crucero crucero2 = crucero1.darSiguiente( );
            while( crucero2 != null )
            {
                assert ( crucero2.darSiguiente( ) != crucero1 ) : "La lista tiene un ciclo";
                assert ( !crucero2.darNombre( ).equals( crucero1.darNombre( ) ) ) : "La lista tiene sucursales con direcciones repetidas";
                crucero2 = crucero2.darSiguiente( );
            }
            crucero1 = crucero1.darSiguiente( );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}