/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Crucero.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupi2CruiseLines
 * Autor: Manuel Muñoz - 13-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupi2CruiseLines.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa un crucero.
 */
public class Crucero implements Serializable
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Versión de la clase
     */
    private static final long serialVersionUID = 3684874442567821506L;

    /**
     * Nombre del barco con el que se realizara el crucero
     */
    private String nombreBarco;

    /**
     * Fecha de salida del crucero
     */
    private String fechaSalida;

    /**
     * Duración del crucero en días.
     */
    private int duracion;

    /**
     * Costo en dólares del crucero.
     */
    private int precio;

    /**
     * Numero de ciudades visitadas por el crucero
     */
    private int numCiudades;

    /**
     * Ciudad de donde comienza el crucero
     */
    private Ciudad puertoSalida;

    /**
     * Ciudad donde termina el crucero
     */
    private Ciudad puertoLlegada;

    /**
     * Siguiente crucero en la lista
     */
    private Crucero siguiente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros. Inicializa el nombre, la duración, el precio y la fecha con los parámetros dados. El número de ciudades es inicializado en 0 y los
     * puertos en null
     * @param nNombre Nombre del crucero. Diferente de null.
     * @param nNumDias Número de días que dura el crucero. Entero mayor a cero.
     * @param nPrecio Precio del crucero. Entero mayor a cero.
     * @param strFecha Fecha de inicio del crucero. Diferente de null.
     */
    public Crucero( String nNombre, int nNumDias, int nPrecio, String strFecha )
    {
        nombreBarco = nNombre;
        fechaSalida = strFecha;
        duracion = nNumDias;
        precio = nPrecio;
        numCiudades = 0;
        puertoSalida = null;
        puertoLlegada = null;
        verificarInvariante( );
    }

    /**
     * Retorna el nombre del crucero.
     * @return Nombre del crucero. Diferente de null
     */
    public String darNombre( )
    {
        return nombreBarco;
    }

    /**
     * Siguiente elemento en la lista de cruceros.
     * @return Siguiente elemento en la lista de crueros. Puede ser null.
     */
    public Crucero darSiguiente( )
    {
        return siguiente;
    }

    /**
     * Modifica el siguiente elemento al actual.
     * @param crucero El elemento que va a ser el siguiente. Puede ser null.
     */
    public void cambiarSiguiente( Crucero crucero )
    {
        siguiente = crucero;
    }

    /**
     * Retorna la duración del crucero.
     * @return Entero mayor a cero.
     */
    public int darDuracion( )
    {
        return duracion;
    }

    /**
     * Retorna el precio del crucero
     * @return Entero mayor a cero.
     */
    public int darPrecio( )
    {
        return precio;
    }

    /**
     * Retorna la fecha de salida del crucero.
     * @return Diferente de null.
     */
    public String darFechaSalida( )
    {
        return fechaSalida;
    }

    /**
     * Agrega una nueva ciudad al crucero.
     * @param nombreCiudad Nombre de la ciudad a agregar. Diferente de null.
     * @param pais Nombre del país donde pertenece la ciudad. Diferente e null.
     * @param coordX Coordenada X de la ciudad en el mapa. Entero mayor a cero.
     * @param coordY Coordenada Y de la ciudad en el mapa. Entero mayor a cero.
     */
    public void agregarCiudad( String nombreCiudad, String pais, double coordX, double coordY )
    {
        if( puertoSalida == null )
        {
            puertoSalida = new Ciudad( nombreCiudad, pais, coordX, coordY );
        }
        else if( puertoLlegada == null )
        {
            puertoLlegada = new Ciudad( nombreCiudad, pais, coordX, coordY );
            puertoSalida.cambiarSiguiente( puertoLlegada );
            puertoLlegada.cambiarAnterior( puertoSalida );
        }
        else
        {
            Ciudad ciudad = new Ciudad( nombreCiudad, pais, coordX, coordY );
            Ciudad anterior = puertoLlegada;
            puertoLlegada.cambiarSiguiente( ciudad );
            ciudad.cambiarAnterior( anterior );
            puertoLlegada = ciudad;
        }
        numCiudades++;
        verificarInvariante( );
    }

    /**
     * Busca una ciudad dado su nombre y el país al que pertenece.
     * @param nombre Nombre de la ciudad a buscar. Diferente de null.
     * @param pais Nombre del país donde pertenece la ciudad. Diferente de null.
     * @return Retorna la ciudad identificada con el nombre y el país. Puede ser null.
     * @throws CiudadNoExisteException Excepción lanzada cuando no se encuentra la ciudad buscada.
     */
    public Ciudad buscarCiudad( String nombre, String pais ) throws CiudadNoExisteException
    {
        if( puertoSalida == null )
        {
            throw new CiudadNoExisteException( "No hay ciudades en el crucero" );
        }
        else
        {
            Ciudad iterador = puertoSalida;
            while( iterador != null )
            {
                if( iterador.darNombre( ).equalsIgnoreCase( nombre ) && iterador.darPais( ).equalsIgnoreCase( pais ) )
                {
                    return iterador;
                }
                iterador = iterador.darSiguiente( );
            }
        }
        throw new CiudadNoExisteException( "La ciudad a buscar no existe" );
    }

    /**
     * Retorna la ciudad que es el puerto de salida del crucero.
     * @return Ciudad que es el puerto de salida. Puede ser null.
     */
    public Ciudad darPuertoSalida( )
    {
        return puertoSalida;
    }

    /**
     * Retorna la ciudad que es el puerto de llegada del crucero.
     * @return Ciudad que es el puerto de llegada del crucero. Puede ser null.
     */
    public Ciudad darPuertoLlegada( )
    {
        return puertoLlegada;
    }

    /**
     * Retorna el número de ciudades por las que pasa el crucero.
     * @return Entero mayor o igual a cero.
     */
    public int darNumeroCiudades( )
    {
        return numCiudades;
    }

    /**
     * Retorna una lista con todas la ciudades por donde pasa el crucero.
     * @return Lista de objeto Ciudad. Diferente de null, pero puede ser vacía.
     */
    public ArrayList darCiudades( )
    {
        ArrayList ciudades = new ArrayList( );
        Ciudad iterador = puertoSalida;
        while( iterador != null )
        {
            ciudades.add( iterador );
            iterador = iterador.darSiguiente( );
        }
        return ciudades;
    }

    /**
     * Retorna ciudad siguiente a la identificada con los parámetros.
     * @param nombre Nombre de la ciudad a la cual se le quiere obtener la siguiente. Diferente de null.
     * @param pais Nombre del país donde pertenece la ciudad a la cual se le quiere obtener la siguiente. Diferente de null.
     * @return La siguiente ciudad a a la identificada por los parámetros.
     * @throws CiudadNoExisteException Excepción lanzada cuando no se encuentra la ciudad a la cual se le quiere obtener la siguiente
     */
    public Ciudad darSiguienteCiudad( String nombre, String pais ) throws CiudadNoExisteException
    {
        return buscarCiudad( nombre, pais ).darSiguiente( );
    }

    /**
     * Retorna ciudad anterior a la identificada con los parámetros.
     * @param nombre Nombre de la ciudad a la cual se le quiere obtener la anterior. Diferente de null.
     * @param pais Nombre del país donde pertenece la ciudad a la cual se le quiere obtener la anterior. Diferente de null.
     * @return La anterior ciudad a a la identificada por los parámetros.
     * @throws CiudadNoExisteException Excepción lanzada cuando no se encuentra la ciudad a la cual se le quiere obtener la anterior.
     */
    public Ciudad darAnteriorCiudad( String nombre, String pais ) throws CiudadNoExisteException
    {
        return buscarCiudad( nombre, pais ).darAnterior( );
    }

    /**
     * Elimina una ciudad dada del crucero.
     * @param nombreCiudad Nombre de la ciudad a ser eliminada. Diferente de null.
     * @param pais País al cual pertenece la ciudad a ser eliminada. Diferente de null.
     * @throws CiudadNoExisteException Excepción que es lanzada cuando no existe la ciudad a ser eliminada.
     */
    public void eliminarCiudad( String nombreCiudad, String pais ) throws CiudadNoExisteException
    {
        if( puertoSalida == null )
            throw new CiudadNoExisteException( "No existe ninguna ciudad" );
        Ciudad iterador = puertoSalida;
        Ciudad anterior = null;
        boolean encontrado = false;
        while( iterador != null )
        {
            if( iterador.darNombre( ).equalsIgnoreCase( nombreCiudad ) && iterador.darPais( ).equalsIgnoreCase( pais ) )
            {
                encontrado = true;
                if( anterior != null )
                {
                    anterior.cambiarSiguiente( iterador.darSiguiente( ) );
                }
                else
                {
                    puertoSalida = iterador.darSiguiente( );
                }
                if( iterador.darSiguiente( ) != null )
                {
                    iterador.darSiguiente( ).cambiarAnterior( anterior );
                }
            }
            anterior = iterador;
            iterador = iterador.darSiguiente( );
        }
        if( !encontrado )
            throw new CiudadNoExisteException( "No existe la ciudad" );
        verificarInvariante( );
    }

    /**
     * Verifica la invariante de la clase<br>
     * <b>inv: </b> nombre != null fechaSalida != null duracion > 0 precio > 0<br>
     * puertoSalida.darAnterior() == null puertoSalida.darSiguiente() == null<br>
     * para toda ciudad, si ciudad.darSiguiente() != null entonces ciudad.darSiguiente().darAnterior() == ciudad<br>
     * para toda ciudad, si ciudad.darAnterior() != null entonces ciudad.darAnterior().darSiguiente() == ciudad<br>
     */
    private void verificarInvariante( )
    {
        assert nombreBarco != null : "El nombre no puede ser null";
        assert fechaSalida != null : "La fecha de salida no puede ser null";
        assert duracion >= 0 : "La duración del crucero debe ser mayor a cero";
        assert precio >= 0 : "El precio del crucero debe ser mayor a cero";

        if( puertoSalida != null )
            assert puertoSalida.darAnterior( ) == null : "La primera ciudad no puede tener anterior";

        if( puertoLlegada != null )
            assert puertoLlegada.darSiguiente( ) == null : "La ultima ciudad no puede tener siguiente";

        // Verificar que la lista esté bien construida
        Ciudad temp = puertoSalida;
        while( temp != null )
        {
            if( temp.darSiguiente( ) != null )
            {
                assert ( temp.darSiguiente( ).darAnterior( ) == temp ) : "La lista no está bien construida";
            }

            if( temp.darAnterior( ) != null )
            {
                assert ( temp.darAnterior( ).darSiguiente( ) == temp ) : "La lista no está bien construida";
            }

            temp = temp.darSiguiente( );
        }
    }

}
