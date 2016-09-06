/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - Mar 28, 2006
 * Autor: J. Villalobos - Abr 14, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.grafo;

import java.util.*;

import uniandes.cupi2.collections.grafo.dijkstra.*;
import uniandes.cupi2.collections.iterador.*;

/**
 * Representa un v�rtice del grafo
 * @param <K> Tipo del identificador de un v�rtice
 * @param <V> Tipo de datos del elemento del v�rtice
 * @param <A> Tipo de datos del elemento del arco
 */
public class Vertice<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Elemento contenido en el v�rtice
     */
    private V infoVertice;

    /**
     * Lista de arcos hacia los sucesores de �ste v�rtice
     */
    private ArrayList<Arco<K, V, A>> predecesores;

    /**
     * Lista de arcos hacia los sucesores de �ste v�rtice
     */
    private ArrayList<Arco<K, V, A>> sucesores;

    /**
     * Marca del nodo
     */
    private boolean marcado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del v�rtice
     * @param pInfoVertice Elemento contenido en el v�rtice
     */
    public Vertice(V pInfoVertice)
    {
        infoVertice = pInfoVertice;
        sucesores = new ArrayList<Arco<K, V, A>>( );
        predecesores = new ArrayList<Arco<K, V, A>>( );
        marcado = false;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve el Id del v�rtice
     * @return Identificador del v�rtice
     */
    public K darId( )
    {
        return infoVertice.darId( );
    }

    /**
     * Devuelve la informaci�n del v�rtice
     * @return Informaci�n del v�rtice
     */
    public V darInfoVertice( )
    {
        return infoVertice;
    }

    /**
     * Devuelve los arcos hacia los sucesores del v�rtice
     * @return Arcos hacia los sucesores del v�rtice
     */
    public ArrayList<Arco<K, V, A>> darSucesores( )
    {
        return sucesores;
    }

    /**
     * Devuelve los arcos hacia los predecesores del v�rtice
     * @return Arcos hacia los predecesores del v�rtice
     */
    public ArrayList<Arco<K, V, A>> darPredecesores( )
    {
        return predecesores;
    }

    /**
     * Devuelve el arco (si existe) hacia el v�rtice especificado. Devuelve null
     * si no existe.
     * @param idDestino Identificador del v�rtice destino
     * @return Arco hacia el v�rtice especificado, null si no existe
     */
    public Arco<K, V, A> darArco( K idDestino )
    {
        // Busca secuencialmente el arco
        for( int i = 0; i < sucesores.size( ); i++ )
        {
            Arco<K, V, A> arco = sucesores.get( i );
            if( idDestino.equals( arco.darVerticeDestino( ).darId( ) ) )
            {
                return arco;
            }
        }
        return null;
    }

    /**
     * Devuelve la marca del v�rtice
     * @return Indica si el v�rtice se encuentra marcado
     */
    public boolean marcado( )
    {
        return marcado;
    }

    /**
     * Marca el v�rtice
     */
    public void marcar( )
    {
        marcado = true;
    }

    /**
     * Elimina la marca del v�rtice
     */
    public void desmarcar( )
    {
        marcado = false;
    }

    /**
     * Elimina un arco del v�rtice
     * @param idDestino Identificador del v�rtice destino del arco que se quiere
     *            eliminar
     * @throws ArcoNoExisteException Excepci�n generada cuando el arco no existe
     */
    public void eliminarArco( K idDestino ) throws ArcoNoExisteException
    {
        Arco<K, V, A> arco = darArco( idDestino );
        if( arco == null )
        {
            throw new ArcoNoExisteException( "El arco no existe", darId( ), idDestino );
        }
        sucesores.remove( arco );
        arco.darVerticeDestino( ).eliminarArcoPredecesor( arco );
    }

    /**
     * Elimina un arco de los predecesores del v�rtice
     * @param arco Arco a eliminar
     */
    private void eliminarArcoPredecesor( Arco<K, V, A> arco )
    {
        predecesores.remove( arco );
    }

    /**
     * Agrega un arco al v�rtice
     * @param arco Arco a agregar al v�rtice
     * @throws ArcoYaExisteException Excepci�n generada cuando ya hay un arco
     *             hacia el mismo v�rtice
     */
    public void agregarArco( Arco<K, V, A> arco ) throws ArcoYaExisteException
    {
        K idDestino = arco.darVerticeDestino( ).darId( );
        if( esSucesor( idDestino ) )
        {
            throw new ArcoYaExisteException( "El arco ya existe", darId( ), idDestino );
        }
        sucesores.add( arco );
        arco.darVerticeDestino( ).agregarArcoPredecesor( arco );
    }

    /**
     * Agrega un arco al v�rtice
     * @param arco Arco a agregar al v�rtice
     * @throws ArcoYaExisteException Excepci�n generada cuando ya hay un arco
     *             hacia el mismo v�rtice
     */
    private void agregarArcoPredecesor( Arco<K, V, A> arco ) throws ArcoYaExisteException
    {
        predecesores.add( arco );
    }

    /**
     * Elimina todos los arcos del v�rtice
     */
    public void eliminarArcos( )
    {
        sucesores.clear( );
    }

    /**
     * Verifica si el arco especificado es sucesor de �ste
     * @param idDestino Identificador del v�rtice destino
     * @return True si es sucesor, False si no
     */
    public boolean esSucesor( K idDestino )
    {
        return darArco( idDestino ) != null;
    }

    /**
     * Devuelve el n�mero de sucesores del v�rtice
     * @return N�mero de sucesores del v�rtice
     */
    public int darNumeroSucesores( )
    {
        return sucesores.size( );
    }

    /**
     * Devuelve el n�mero de predecesores del v�rtice
     * @return N�mero de predecesores del v�rtice
     */
    public int darNumeroPredecesores( )
    {
        return predecesores.size( );
    }

    /**
     * Indica si hay un camino simple del v�rtice actual al v�rtice que se
     * recibe como par�metro
     * @param destino V�rtice destino de la b�squeda
     * @return True si existe, False si no
     */
    public boolean hayCamino( Vertice<K, V, A> destino )
    {
        if( infoVertice.darId( ).equals( destino.darId( ) ) )
            return true;
        else
        {
            marcar( );
            for( Arco<K, V, A> arco : darSucesores( ) )
            {
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) && vert.hayCamino( destino ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Devuelve el camino mas corto al v�rtice especificado
     * @param destino V�rtice destino
     * @return Camino mas corto hacia el v�rtice especificado
     */
    public Camino<K, V, A> darCaminoMasCorto( Vertice<K, V, A> destino )
    {
        if( infoVertice.darId( ).equals( destino.darId( ) ) )
            return new Camino<K, V, A>( this );
        else
        {
            marcar( );
            ArrayList<Arco<K, V, A>> sucesores = darSucesores( );
            Camino<K, V, A> camino = null;
            Arco<K, V, A> arcoEnCamino = null;
            for( int i = 0; i < sucesores.size( ); i++ )
            {
                Arco<K, V, A> arco = sucesores.get( i );
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) )
                {
                    Camino<K, V, A> cam = vert.darCaminoMasCorto( destino );
                    if( cam != null )
                    {
                        if( camino == null || cam.darLongitud( ) < camino.darLongitud( ) )
                        {
                            camino = cam;
                            arcoEnCamino = arco;
                        }
                    }
                }
            }
            desmarcar( );
            if( camino == null )
                return null;
            else
            {
                camino.agregarArcoComienzo( arcoEnCamino );
                return camino;
            }
        }
    }

    /**
     * Devuelve el camino mas barato hacia el v�rtice especificado
     * @param destino V�rtice destino
     * @return Camino mas barato al v�rtice especificado
     */
    public Camino<K, V, A> darCaminoMasBarato( Vertice<K, V, A> destino )
    {
        if( infoVertice.darId( ).equals( destino.darId( ) ) )
            return new Camino<K, V, A>( this );
        else
        {
            marcar( );
            ArrayList<Arco<K, V, A>> sucesores = darSucesores( );
            Camino<K, V, A> camino = null;
            Arco<K, V, A> arcoEnCamino = null;
            for( int i = 0; i < sucesores.size( ); i++ )
            {
                Arco<K, V, A> arco = sucesores.get( i );
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) )
                {
                    Camino<K, V, A> cam = vert.darCaminoMasBarato( destino );
                    if( cam != null )
                    {
                        if( camino == null || cam.darCosto( ) + arco.darPeso( ) < camino.darCosto( ) + arcoEnCamino.darPeso( ) )
                        {
                            camino = cam;
                            arcoEnCamino = arco;
                        }
                    }
                }
            }
            desmarcar( );
            if( camino == null )
                return null;
            else
            {
                camino.agregarArcoComienzo( arcoEnCamino );
                return camino;
            }
        }
    }

    /**
     * Indica si hay un ciclo que parte del v�rtice actual
     * @return True si existe un ciclo, False si no
     */
    public boolean hayCiclo( )
    {
        ArrayList<Arco<K, V, A>> sucesores = darSucesores( );
        for( Arco<K, V, A> arco : sucesores )
        {
            Vertice<K, V, A> vert = arco.darVerticeDestino( );
            if( vert.hayCamino( this ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Indica si hay un camino de Hamilton que pasa por el v�rtice actual,
     * teniendo en cuenta que en dicho camino ya se ha pasado por un cierto
     * n�mero de v�rtice (longActual) y que debe pasar por todos lo v�rtices del
     * grafo (ordenGrafo)
     * @param longActual Longitud actual del camino
     * @param ordenGrafo Orden del grafo
     * @return True si existe, False si no
     */
    public boolean hayCaminoHamilton( int longActual, int ordenGrafo )
    {
        longActual++;
        if( longActual == ordenGrafo )
            return true;
        else
        {
            marcar( );
            for( Arco<K, V, A> arco : darSucesores( ) )
            {
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) && vert.hayCaminoHamilton( longActual, ordenGrafo ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Devuelve el camino de Hamilton
     * @param hamilton Camino de Hamilton
     * @param ordenGrafo Orden del grafo
     * @return True si existe, False si no
     */
    public boolean darCaminoHamilton( Camino<K, V, A> hamilton, int ordenGrafo )
    {
        if( hamilton.darLongitud( ) + 1 == ordenGrafo )
        {
            return true;
        }
        else
        {
            marcar( );
            for( Arco<K, V, A> arco : darSucesores( ) )
            {
                Vertice<K, V, A> vert = arco.darVerticeDestino( );
                if( !vert.marcado( ) )
                {
                    hamilton.agregarArcoFinal( arco );
                    if( vert.darCaminoHamilton( hamilton, ordenGrafo ) )
                        return true;
                    hamilton.eliminarUltimoArco( );
                }
            }
            desmarcar( );
        }
        return false;
    }

    /**
     * Incluye en el c�lculo de caminos m�nimos el v�rtice actual
     * @param minimos El objeto sobre el que se est� haciendo el calculo
     * @return El objeto en el que se est�n calculando los caminos m�nimos
     */
    public CaminosMinimos dijkstra( CaminosMinimos<K, V, A> minimos )
    {
        Vertice<K, V, A> vert = minimos.darSiguienteVertice( );
        while( vert != null )
        {
            minimos.recalcularCaminosEspeciales( vert );
            vert = minimos.darSiguienteVertice( );
        }
        return minimos;
    }

    /**
     * Indica si el grafo partiendo de �ste v�rtice es conexo
     * @return true si el grafo partiendo del v�rtice actual es conexo o false
     *         en caso contrario
     */
    public boolean esConexo( )
    {
        // TODO Por implementar
        return false;
    }

    /**
     * Indica si el grafo partiendo de �ste v�rtice es fuertemente conexo
     * @return true si el grafo partiendo del v�rtice actual es fuertemente
     *         conexo o false en caso contrario
     */
    public boolean esFuertementeConexo( )
    {
        // TODO Por implementar
        return false;
    }

    /**
     * 
     */
    public void darRecorridoProfundidad( IteradorSimple<Vertice<K, V, A>> itera )
    {
        try
        {
            itera.agregar( this );
        }
        catch( IteradorException e )
        {
            // Nunca deber�a ocurrir esta excepci�n
        }
        marcar( );
        for( Arco<K, V, A> arco : darSucesores( ) )
        {
            Vertice<K, V, A> vert = arco.darVerticeDestino( );
            if( !vert.marcado( ) )
            {
                vert.darRecorridoProfundidad( itera );
            }
        }
    }
}
