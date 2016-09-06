/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Camino.java,v 1.5 2006/10/26 20:23:27 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: J. Villalobos - Abril 14, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.grafo;

import java.util.*;

import uniandes.cupi2.collections.iterador.*;

/**
 * Representa un camino en un grafo
 * @param <K> Tipo del identificador de un vértice
 * @param <V> Tipo de datos del elemento del vértice
 * @param <A> Tipo de datos del elemento del arco
 */
public class Camino<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Vértice origen del camino
     */
    private Vertice<K, V, A> origen;

    /**
     * Vector con los arcos del camino
     */
    private ArrayList<Arco<K, V, A>> arcos;

    /**
     * Costo total del camino
     */
    private int costo;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del camino
     * @param pOrigen Vértice de origen del camino
     */
    public Camino( Vertice<K, V, A> pOrigen )
    {
        origen = pOrigen;
        arcos = new ArrayList<Arco<K, V, A>>( );
        costo = 0;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un arco al final del camino
     * @param arco Arco a agregar
     */
    public void agregarArcoFinal( Arco<K, V, A> arco )
    {
        arcos.add( arco );
        costo += arco.darPeso( );
    }

    /**
     * Agrega un arco al comienzo del camino
     * @param nuevoArco Arco a agregar
     */
    public void agregarArcoComienzo( Arco<K, V, A> nuevoArco )
    {
        origen = nuevoArco.darVerticeOrigen( );
        arcos.add( 0, nuevoArco );
        costo += nuevoArco.darPeso( );
    }

    /**
     * Concatena todos los arcos del camino especificado al final del camino
     * @param camino Camino a concatenar
     */
    public void concatenar( Camino<K, V, A> camino )
    {
        for( int i = 0; i < camino.arcos.size( ); i++ )
            agregarArcoFinal( camino.arcos.get( i ) );
    }

    /**
     * Elimina el último arco
     */
    public void eliminarUltimoArco( )
    {
        if( arcos.size( ) >= 1 )
        {
            Arco<K, V, A> arco = arcos.get( arcos.size( ) - 1 );
            arcos.remove( arcos.size( ) - 1 );
            costo -= arco.darPeso( );
        }
    }

    /**
     * Reinicia el camino
     */
    public void reiniciar( )
    {
        arcos.clear( );
        costo = 0;
    }

    /**
     * Devuelve la longitud del camino
     * @return Longitud del camino
     */
    public int darLongitud( )
    {
        return arcos.size( );
    }

    /**
     * Devuelve el costo del camino
     * @return Costo del camino
     */
    public int darCosto( )
    {
        return costo;
    }

    /**
     * Devuelve los vértices por los cuales pasa el camino
     * @return Iterador sobre los vértices
     */
    public Iterador<Vertice<K, V, A>> darSecuenciaVertices( )
    {
        IteradorSimple<Vertice<K, V, A>> itera = new IteradorSimple<Vertice<K, V, A>>( arcos.size( ) + 1 );
        try
        {
            itera.agregar( origen );
            for( Arco<K, V, A> arco : arcos )
            {
                itera.agregar( arco.darVerticeDestino( ) );
            }
        }
        catch( IteradorException e )
        {
            // Nunca debería generarse esta excepción
            e.printStackTrace( );
        }
        return itera;
    }
}
