/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Planeta.java,v 1.6 2007/07/03 14:37:44 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Mu�oz - 13-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.mundo;

import java.util.ArrayList;

/**
 * Clase que representa un planeta del sistema solar.
 */
public class Planeta
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del planeta
     */
    private String nombre;

    /**
     * Distancia media al sol. Dada en UA
     */
    private double distanciaMediaSol;

    /**
     * Excentricidad del planeta
     */
    private double excentricidad;

    /**
     * EL periodo orbital del planeta
     */
    private double periodoOrbitalSinodico;

    /**
     * Velocidad media. Dada en km/s
     */
    private double velocidadOrbitalMedia;

    /**
     * La inclinaci�n del planeta. Dada en grados
     */
    private double inclinacion;

    /**
     * Lista de los sat�lites del planeta
     */
    private ArrayList satelites;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros. Inicializa los par�metros con los atributos. Inicializa la lista de sat�lites naturales vac�a.
     * @param nNombre Nombre del planeta. String diferente de null.
     * @param nDistanciaMediaSol La distancia media al sol. Real mayor a cero.
     * @param nExcentricidad La excentricidad de la orbita. Real mayor a cero.
     * @param nPeriodoOrbitalSinodico Periodo orbital sinodico. Real mayor a cero.
     * @param nVelocidadOrbitalMedia Velocidad media del planeta. Real mayor a cero.
     * @param nInclinacion Inclinaci�n del planeta con respecto a su eje. Real mayor a cero.
     */
    public Planeta( String nNombre, double nDistanciaMediaSol, double nExcentricidad, double nPeriodoOrbitalSinodico, double nVelocidadOrbitalMedia, double nInclinacion )
    {
        nombre = nNombre;
        distanciaMediaSol = nDistanciaMediaSol;
        excentricidad = nExcentricidad;
        periodoOrbitalSinodico = nPeriodoOrbitalSinodico;
        velocidadOrbitalMedia = nVelocidadOrbitalMedia;
        inclinacion = nInclinacion;
        satelites = new ArrayList( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del planeta
     * @return String diferente de null
     */
    public String obtenerNombre( )
    {
        return nombre;
    }

    /**
     * Agrega un nuevo sat�lite natural al planeta y devuelve true o false dependiendo si pudo agregar el sat�lite o no. <br>
     * Para agregar un sat�lite natural, se debe verificar que no exista un sat�lite natural previamente con el mismo nombre
     * @param nombreSatelite Nombre del nuevo sat�lite natural. Diferente de null.
     * @param nEexcentricidad Excentricidad del nuevo sat�lite natural. Real mayor a cero.
     * @param nInclinacion Inclinaci�n del nuevo sat�lite natural. Real mayor a cero.
     * @return true si logr� agregar un sat�lite natural al planeta. False de lo contrario
     */
    public boolean agregarSateliteNatural( String nombreSatelite, double nEexcentricidad, double nInclinacion )
    {
        boolean encontro = false;
        for( int i = 0; i < satelites.size( ) && !encontro; i++ )
        {
            SateliteNatural satelite = ( SateliteNatural )satelites.get( i );
            if( satelite.obtenerNombre( ).equals( nombreSatelite ) )
            {
                encontro = true;
            }
        }
        if( !encontro )
        {
            SateliteNatural satelite = new SateliteNatural( nombreSatelite, nEexcentricidad, nInclinacion );
            satelites.add( satelite );
        }
        return !encontro;
    }

    /**
     * Retorna la lista de los sat�lites naturales del planeta
     * @return Lista con objetos de tipo SateliteNatural. La lista puede ser vac�a, pero no null.
     */
    public ArrayList obtenerSatelitesNaturales( )
    {
        return satelites;
    }

    /**
     * Elimina un sat�lite natural dado su nombre.
     * @param nombreSatelite Nombre del sat�lite natural que se quiere eliminar. Diferente de null.
     */
    public void eliminarSateliteNatural( String nombreSatelite )
    {
        for( int i = 0; i < satelites.size( ); i++ )
        {
            SateliteNatural satelite = ( SateliteNatural )satelites.get( i );
            if( satelite.obtenerNombre( ).equals( nombreSatelite ) )
                satelites.remove( i );
        }
    }

    /**
     * Retorna el sat�lite natural identificado con el nombre. si no lo encuentra devuelve null
     * @param nombreSatelite Nombre del sat�lite natural que se quiere buscar. Diferente de null
     * @return Retorna el sat�lite natural. En caso de no encontrarlo retorna null.
     */
    public SateliteNatural obtenerSateliteNatural( String nombreSatelite )
    {
        for( int i = 0; i < satelites.size( ); i++ )
        {
            SateliteNatural satelite = ( SateliteNatural )satelites.get( i );
            if( satelite.obtenerNombre( ).equals( nombreSatelite ) )
                return satelite;
        }
        return null;
    }

    /**
     * Cambia la excentricidad y la inclinaci�n de un sat�lite natural dado su nombre.
     * @param nombreSatelite Nombre del sat�lite que se quiere modificar. Diferente de null.
     * @param nEexcentricidad Nueva excentricidad del sat�lite natural. Real mayor a cero.
     * @param nInclinacion Nueva inclinaci�n del sat�lite natural. Real mayor a cero.
     */
    public void editarSateliteNatural( String nombreSatelite, double nEexcentricidad, double nInclinacion )
    {
        for( int i = 0; i < satelites.size( ); i++ )
        {
            SateliteNatural satelite = ( SateliteNatural )satelites.get( i );
            if( satelite.obtenerNombre( ).equals( nombreSatelite ) )
            {
                satelite.cambiarExcentricidad( nEexcentricidad );
                satelite.cambiarInclinacionOrbital( nInclinacion );
            }
        }
    }

    /**
     * Retorna la distancia media al sol del planeta.
     * @return Real mayor a cero.
     */
    public double obtenerDistancia( )
    {
        return distanciaMediaSol;
    }

    /**
     * Retorna la inclinaci�n del planeta.
     * @return Real mayor a cero.
     */
    public double obtenerInclinacion( )
    {
        return inclinacion;
    }

}
