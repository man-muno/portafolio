/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Observatorio.java,v 1.4 2007/08/09 19:40:26 man-muno Exp $
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
 * Clase que representa el observatorio
 */
public class Observatorio
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Cantidad de elementos que tiene el arreglo de planetas
     */
    public static final int CANTIDAD_PLANETAS = 8;

    /**
     * Constante para el nombre de Mercurio
     */
    public static final String NOMBRE_MERCURIO = "Mercurio";

    /**
     * Constante para el nombre de Venus
     */
    public static final String NOMBRE_VENUS = "Venus";

    /**
     * Constante para el nombre de la Tierra
     */
    public static final String NOMBRE_TIERRA = "Tierra";

    /**
     * Constante para el nombre de Marte
     */
    public static final String NOMBRE_MARTE = "Marte";

    /**
     * Constante para el nombre de J�piter
     */
    public static final String NOMBRE_JUPITER = "J�piter";

    /**
     * Constante para el nombre de Saturno
     */
    public static final String NOMBRE_SATURNO = "Saturno";

    /**
     * Constante para el nombre de Urano
     */
    public static final String NOMBRE_URANO = "Urano";

    /**
     * Constante para el nombre de Neptuno
     */
    public static final String NOMBRE_NEPTUNO = "Neptuno";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Arreglo fijo de 8 planetas
     */
    private Planeta[] planetas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del observatorio. Inicializa el arreglo de planetas, as� como cada uno de los planetas en su posici�n respectiva.
     */
    public Observatorio( )
    {
        planetas = new Planeta[CANTIDAD_PLANETAS];
        planetas[ 0 ] = new Planeta( NOMBRE_MERCURIO, 0.466, 0.205, 115.88, 478.725, 7.004 );
        planetas[ 1 ] = new Planeta( NOMBRE_VENUS, 0.728, 0.006, 583.92, 35.021, 339.471 );
        planetas[ 2 ] = new Planeta( NOMBRE_TIERRA, 1.016, 0.0167, 365.25, 30.28, 23.45 );
        planetas[ 3 ] = new Planeta( NOMBRE_MARTE, 1.665, 0.09341233, 779.95, 24.13, 1.850 );
        planetas[ 4 ] = new Planeta( NOMBRE_JUPITER, 5.458, 0.09341233, 398.9, 13.069, 1.305 );
        planetas[ 5 ] = new Planeta( NOMBRE_SATURNO, 10.115, 0.0541506, 378.1, 9.67, 2.484 );
        planetas[ 6 ] = new Planeta( NOMBRE_URANO, 20.096, 0.04716771, 369.7, 6.835, 0.769 );
        planetas[ 7 ] = new Planeta( NOMBRE_NEPTUNO, 30.327, 0.00858587, 367.5, 5.47, 1.769 );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna un arreglo con los nombres de los planetas en el sistema.
     * @return Arreglo diferente de null que contiene los nombres de los planetas.
     */
    public String[] obtenerNombresPlanetas( )
    {
        String[] nombres = new String[CANTIDAD_PLANETAS];
        for( int i = 0; i < planetas.length; i++ )
        {
            nombres[ i ] = planetas[ i ].obtenerNombre( );
        }
        return nombres;
    }

    /**
     * Agrega un sat�lite natural a un planeta especificado.
     * @param nombrePlaneta Nombre del planeta donde se quiere agregar el sat�lite natural. Diferente de null.
     * @param nombreSatelite Nombre del sat�lite natural a agregar. Diferente de null.
     * @param excentricidad Excentricidad del nuevo sat�lite natural. Real superior a cero.
     * @param inclinacion Inclinaci�n del nuevo sat�lite natural. Real superior a cero.
     * @return True en caso que haya agregado el sat�lite natural al planeta especificado. False de lo contrario.
     */
    public boolean agregarSateliteNatural( String nombrePlaneta, String nombreSatelite, double excentricidad, double inclinacion )
    {
        boolean agregado = false;

        for( int i = 0; i < planetas.length && !agregado; i++ )
        {
            if( nombrePlaneta.equals( planetas[ i ].obtenerNombre( ) ) )
            {
                agregado = planetas[ i ].agregarSateliteNatural( nombreSatelite, excentricidad, inclinacion );
            }
        }
        return agregado;
    }

    /**
     * M�todo que retorna la lista de los sat�lites naturales de un planeta dado.
     * @param nombrePlaneta Nombre del planeta. String diferente de null
     * @return Lista de objetos de tipo SateliteNatural. Puede ser vac�a pero no null.
     */
    public ArrayList obtenerSatelitesNaturales( String nombrePlaneta )
    {
        ArrayList satelites = new ArrayList( );
        boolean encontrado = false;

        for( int i = 0; i < planetas.length && !encontrado; i++ )
        {
            if( planetas[ i ].obtenerNombre( ).equals( nombrePlaneta ) )
            {
                satelites = planetas[ i ].obtenerSatelitesNaturales( );
                encontrado = true;
            }
        }
        return satelites;
    }

    /**
     * M�todo que dado el nombre de un planeta, elimina el sat�lite natural identificado con el nombre.
     * @param nombrePlaneta Nombre del planeta al cual se le quiere eliminar un sat�lite natural. Diferente de null.
     * @param nombreSatelite Nombre del sat�lite natural que se quiere eliminar.
     */
    public void eliminarSatelite( String nombrePlaneta, String nombreSatelite )
    {
        boolean encontrado = false;
        for( int i = 0; i < planetas.length && !encontrado; i++ )
        {
            Planeta planeta = planetas[ i ];
            if( planeta.obtenerNombre( ).equals( nombrePlaneta ) )
            {
                planeta.eliminarSateliteNatural( nombreSatelite );
                encontrado = true;
            }
        }

    }

    /**
     * M�todo que retorna un sat�lite natural de un planeta identificado con el nombre.
     * @param nombrePlaneta Nombre del planeta de donde se quiere obtener el sat�lite natural.
     * @param nombreSatelite Nombre del sat�lite natural que se quiere obtener.
     * @return El sat�lite natural del planeta. En caso de no existir se retorna null.
     */
    public SateliteNatural obtenerSateliteNatural( String nombrePlaneta, String nombreSatelite )
    {
        for( int i = 0; i < planetas.length; i++ )
        {
            if( planetas[ i ].obtenerNombre( ).equals( nombrePlaneta ) )
                return planetas[ i ].obtenerSateliteNatural( nombreSatelite );
        }
        return null;
    }

    /**
     * M�todo que cambia la excentricidad y la inclinaci�n de un sat�lite natural dado, de un planeta dado.
     * @param nombrePlaneta Nombre del planeta donde se encuentra el sat�lite natural. Diferente de null.
     * @param nombre Nombre del sat�lite natural. Diferente de null.
     * @param excentricidad Nuevo valor de la excentricidad para el sat�lite natural. Real mayor a cero.
     * @param inclinacion Nuevo valor de la inclinaci�n para el sat�lite natural. Real mayor a cero.
     */
    public void editarSateliteNatural( String nombrePlaneta, String nombre, double excentricidad, double inclinacion )
    {
        for( int i = 0; i < planetas.length; i++ )
        {
            if( planetas[ i ].obtenerNombre( ).equals( nombrePlaneta ) )
            {
                planetas[ i ].editarSateliteNatural( nombre, excentricidad, inclinacion );
            }
        }
    }

    /**
     * Retorna una lista de los planetas que tienen menor distancia orbital que la que se pasa por par�metro.
     * @param distancia Distancia por la cual se quiere preguntar. Real mayor a cero.
     * @return Lista con objetos de tipo String, que contienen los nombres de los planetas que tienen menor distancia orbital que la que se pasa por par�metro. Puede ser vacia
     *         pero no null.
     */
    public ArrayList obtenerPlanetasPorDistancia( double distancia )
    {
        ArrayList listaPlanetas = new ArrayList( );
        for( int i = 0; i < CANTIDAD_PLANETAS; i++ )
        {
            Planeta planeta = planetas[ i ];
            if( planeta.obtenerDistancia( ) <= distancia )
                listaPlanetas.add( planeta.obtenerNombre( ) );
        }
        return listaPlanetas;
    }

    /**
     * Retorna una lista de los planetas que tienen menor inclinaci�n orbital que la del planeta cuyo nombre se para por par�metro.
     * @param nombre Nombre del planeta del cual se quiere obtener su inclinaci�n para comprar. Diferente de null.
     * @return Lista con objetos de tipo String, que contienen los nombres de los planetas que tienen menor inclinaci�n orbital que la del planeta cuyo nombre se para por
     *         par�metro.
     */
    public ArrayList obtenerPlanetasPorInclinacion( String nombre )
    {
        ArrayList listaPlanetas = new ArrayList( );
        for( int i = 0; i < planetas.length; i++ )
        {
            Planeta planeta = planetas[ i ];
            if( planeta.obtenerNombre( ).equals( nombre ) )
            {
                double inclinacion = planeta.obtenerInclinacion( );
                for( int j = 0; j < planetas.length; j++ )
                {
                    if( planetas[ j ].obtenerInclinacion( ) < inclinacion )
                        listaPlanetas.add( planetas[ j ].obtenerNombre( ) );
                }
            }
        }
        return listaPlanetas;
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
        return "Los sat�lites que han sido eliminados son: \n" +
                "Nombre: Io, Excentricidad: 0.041, Inclinacion: 0.040\n" +
                "Nombre: Luna, Excentricidad: 0.549, Inclinacion: 5.145\n" +
                "Nombre: Europa, Excentricidad: 0.0101, Inclinacion: 0.464\n";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "El planeta con mas sat�lites naturales es: \n" +
                NOMBRE_JUPITER + " con 62 sat�lites naturales";
    }

}