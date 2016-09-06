/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CaminosMinimos.java,v 1.6 2006/11/02 13:59:21 jvillalo2 Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: J. Villalobos - Abr 21, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.grafo.dijkstra;

import java.util.*;

import uniandes.cupi2.collections.grafo.*;
import uniandes.cupi2.collections.iterador.*;

/**
 * Clase utilizada para el c�lculo de caminos m�nimos desde un v�rtice origen
 * usando el algoritmo de Dijkstra 
 */
public class CaminosMinimos<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * V�rtice origen del camino
     */
    private Vertice<K, V, A> origen;

    /**
     * Conjunto de v�rtices que representan el grafo al que pertenece el v�rtice origen
     */
    private HashMap<K, NodoDijkstra<K, V, A>> nodos;

    /**
     * Lista de v�rtices que a�n no han sido marcados (no se han incluido en el c�lculo del camino m�nimo)
     */
    private ArrayList<Vertice<K, V, A>> sinMarcar;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la clase
     * @param pOrigen V�rtice a partir del cual se van a calcular los caminos m�nimos
     * @param pVertices V�rtices que hacen parte del grafo al que pertenece el v�rtice origen
     */
    public CaminosMinimos( Vertice<K, V, A> pOrigen, Collection<Vertice<K, V, A>> pVertices )
    {
        // Guarda el origen de todos los caminos m�nimos
        origen = pOrigen;
        // Crea la estructura que representa la informaci�n del camino m�nimo asociada con cada v�rtice
        nodos = new HashMap<K, NodoDijkstra<K, V, A>>( );
        // Crea la estructura que representa el conjunto de v�rtices sin marcar
        sinMarcar = new ArrayList<Vertice<K, V, A>>( );
        NodoDijkstra<K, V, A> nodoInicial = null;
        // Recorre los v�rtices del grafo inicializando las estructuras
        for( Vertice<K, V, A> vert : pVertices )
        {
            NodoDijkstra<K, V, A> nodo = new NodoDijkstra<K, V, A>( vert );
            nodos.put( vert.darId( ), nodo );
            if( !vert.darId( ).equals( origen.darId( ) ) )
                sinMarcar.add( vert );
            else
                nodoInicial = nodo;
        }
        // Inicializa la estructura de caminos m�nimos, definiendo el costo del camino especial hacia todos los sucesores del v�rtice de partida
        for( Arco<K, V, A> arco : origen.darSucesores( ) )
        {
            asignarCosto( arco.darVerticeDestino( ).darId( ), arco.darPeso( ), nodoInicial );
        }
        // Inicializa el costo del camino especial al v�rtice de origen como de costo 0
        asignarCosto( origen.darId( ), 0, null );
        origen.marcar( );
    }
    
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Asigna el costo m�nimo entre los dos v�rtices especificados  
     * @param idVertice El identificador del v�rtice destino
     * @param costo El costo entre los dos v�rtices
     * @param anterior El v�rtices origen
     */    
    public void asignarCosto( K idVertice, int costo, NodoDijkstra<K, V, A> anterior )
    {
        NodoDijkstra<K, V, A> nodo = nodos.get( idVertice );
        nodo.asignarCostoMinimo( costo, anterior );
    }

    /**
     * Localiza el v�rtice no marcado con costo de camino especial m�nimo, 
     * cuyo costo sea distinto de NodoDijkstra.INDEFINIDO, y lo marca. Si no lo encuentra retorna null
     * @return El siguiente v�rtice no marcado. Si no se encuentra se retorn� null  
     */
    public Vertice<K, V, A> darSiguienteVertice( )
    {
        Vertice<K, V, A> menorV = null;
        NodoDijkstra<K, V, A> menorN = null;
        for( Vertice<K, V, A> vert : sinMarcar )
        {
            NodoDijkstra<K, V, A> nodo = nodos.get( vert.darId( ) );
            if( menorV == null && nodo.darCostoMinimo( ) != NodoDijkstra.INDEFINIDO )
            {
                menorV = vert;
                menorN = nodo;
            }
            else if( nodo.darCostoMinimo( ) < menorN.darCostoMinimo( ) )
            {
                menorV = vert;
                menorN = nodo;
            }
        }
        sinMarcar.remove( menorV );
        menorV.marcar( );
        return menorV;
    }

    /**
     * Recalcula todos los caminos especiales de la siguiente manera: para cada sucesor no marcado se debe decidir si resulta mejor el camino antes calculado o si es
     * preferible utilizar el camino especial hasta el v�rtice que se acaba de incluir (y que llega como par�metro) y luego utilizar el arco que los une.
     * @param nuevoVert Nuevo v�rtice que se acaba de incluir en el camino
     */
    public void recalcularCaminosEspeciales( Vertice<K, V, A> nuevoVert )
    {
        NodoDijkstra<K, V, A> nuevoNodo = nodos.get( nuevoVert.darId( ) );
        int costoANuevoVert = nuevoNodo.darCostoMinimo( );
        for( Arco<K, V, A> arco : nuevoVert.darSucesores( ) )
        {
            Vertice<K, V, A> vert = arco.darVerticeDestino( );
            if( !vert.marcado( ) )
            {
                NodoDijkstra<K, V, A> nodo = nodos.get( vert.darId( ) );
                if( nodo.darCostoMinimo( ) > costoANuevoVert + arco.darPeso( ) )
                    nodo.asignarCostoMinimo( costoANuevoVert + arco.darPeso( ), nuevoNodo );
            }
        }
    }

    /**
     * Retorna el costo del camino m�nimo que parte del origen y llega al 
     * v�rtice que se recibe como par�metro. Si no existe dicho camino, retorna -1.
     * @param vertice V�rtice destino
     * @return Costo del camino m�nimo desde el nodo origen al nodo destino. Si no existe el camino se retorna -1  
     */
    public int darCostoCamino( Vertice<K, V, A> vertice )
    {
        NodoDijkstra<K, V, A> nodo = nodos.get( vertice.darId( ) );
        int costo = nodo.darCostoMinimo( );
        return costo == NodoDijkstra.INDEFINIDO ? -1 : costo;
    }

    /**
     * Devuelve los v�rtices por los cuales pasa el camino m�nimo que lleva del origen al 
     * v�rtice que se recibe como par�metro.
     * @param vertice El v�rtice destino
     * @return Iterador sobre los v�rtices
     */
    public Iterador<Vertice<K, V, A>> darCaminoMinimo( Vertice<K, V, A> vertice )
    {
        NodoDijkstra<K, V, A> nodo = nodos.get( vertice.darId( ) );
        IteradorSimple<Vertice<K, V, A>> itera = new IteradorSimple<Vertice<K, V, A>>( nodos.size( ) );
        try
        {
            while( nodo != null )
            {
                itera.insertar( nodo.darVertice( ) );
                nodo = nodo.darPredecesor( );
            }
        }
        catch( IteradorException e )
        {
            // Nunca deber�a aparecer esta excepci�n
            e.printStackTrace( );
        }
        return itera;
    }
}
