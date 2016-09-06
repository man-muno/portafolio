/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
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

import uniandes.cupi2.collections.colaEncadenada.*;
import uniandes.cupi2.collections.grafo.dijkstra.*;
import uniandes.cupi2.collections.iterador.*;

/**
 * Representa un grafo dirigido
 * @param <K> Tipo del identificador de un vértice
 * @param <V> Tipo de datos del elemento del vértice
 * @param <A> Tipo de datos del elemento del arco
 */
public class Grafo<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tabla de hashing con los vértices
     */
    private HashMap<K, Vertice<K, V, A>> vertices;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo grafo vacío
     */
    public Grafo( )
    {
        vertices = new HashMap<K, Vertice<K, V, A>>( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el vértice identificado con el identificador especificado
     * @param idVertice Identificador del vértice
     * @return Vértice buscado
     * @throws VerticeNoExisteException Excepción generada cuando el vértice
     *             buscado no existe en el grafo
     */
    public Vertice<K, V, A> darVertice( K idVertice ) throws VerticeNoExisteException
    {
        Vertice<K, V, A> vertice = vertices.get( idVertice );
        if( vertice == null )
        {
            throw new VerticeNoExisteException( "El vértice buscado no existe en el grafo", idVertice );
        }
        return vertice;
    }

    /**
     * Indica si el vértice con el identificador dado existe en el grafo
     * @param idVertice Identificador del vértice
     * @return true si el vértice con el identificador dado existe o false en
     *         caso contrario
     */
    public boolean existeVertice( K idVertice )
    {
        return vertices.get( idVertice ) != null;
    }

    /**
     * Devuelve todos los vértices del grafo
     * @return Vértices del grafo
     */
    public Collection<Vertice<K, V, A>> darVertices( )
    {
        return vertices.values( );
    }

    /**
     * Devuelve el arco definido entre los dos vértices especificados. Devuelve
     * null si no existe
     * @param idVerticeOrigen Vértice desde donde sale el arco
     * @param idVerticeDestino Vértice hacia donde llega el arco
     * @return Arco que conecta los vértices. Null si no existe
     * @throws VerticeNoExisteException Excepción generada cuando alguno de los
     *             vértices no existe en el grafo
     */
    public Arco<K, V, A> darArco( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException
    {
        // Busca el primer vértice y luego busca el arco
        Vertice<K, V, A> vertice = darVertice( idVerticeOrigen );
        if( existeVertice( idVerticeDestino ) )
            return vertice.darArco( idVerticeDestino );
        else
            throw new VerticeNoExisteException( "Vértice destino no existe", idVerticeDestino );
    }

    /**
     * Devuelve todos los arcos del grafo
     * @return Arcos del grafo
     */
    public ArrayList<Arco<K, V, A>> darArcos( )
    {
        ArrayList<Arco<K, V, A>> arcos = new ArrayList<Arco<K, V, A>>( );

        // Recorre todos los vértices buscando los arcos
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            arcos.addAll( vertice.darSucesores( ) );
        }
        return arcos;
    }

    /**
     * Devuelve los arcos que salen del vértice especificado
     * @param idVertice Identificador del vértice
     * @return Arcos que salen del vértice especificado
     * @throws VerticeNoExisteException Excepción generada cuando el vértice
     *             especificado no existe
     */
    public ArrayList<Arco<K, V, A>> darSucesores( K idVertice ) throws VerticeNoExisteException
    {
        return darVertice( idVertice ).darSucesores( );
    }

    /**
     * Devuelve los arcos que llegan al vértice especificado
     * @param idVertice Identificador del vértice
     * @return Arcos que llegan al vértice especificado
     * @throws VerticeNoExisteException Excepción generada cuando el vértice
     *             especificado no existe
     */
    public ArrayList<Arco<K, V, A>> darPredecesores( K idVertice ) throws VerticeNoExisteException
    {
        return darVertice( idVertice ).darPredecesores( );
    }

    /**
     * Crea un nuevo vértice en el grafo
     * @param elemento Elemento del vértice
     * @return Vértice creado
     * @throws VerticeYaExisteException Si el vértice que se trata de agregar ya
     *             existe
     */
    public Vertice<K, V, A> agregarVertice( V elemento ) throws VerticeYaExisteException
    {
        if( existeVertice( elemento.darId( ) ) )
            throw new VerticeYaExisteException( "Elemento ya existe", elemento.darId( ) );
        else
        {
            Vertice<K, V, A> vertice = new Vertice<K, V, A>( elemento );
            vertices.put( elemento.darId( ), vertice );
            return vertice;
        }
    }

    /**
     * Elimina el vértice identificado con el Identificador especificado
     * @param idVertice Identificador del vértice
     * @throws VerticeNoExisteException Excepción generada cuando el vértice
     *             especificado no existe
     */
    public void eliminarVertice( K idVertice ) throws VerticeNoExisteException
    {
        // Localiza el vértice en el grafo
        Vertice<K, V, A> vertice = darVertice( idVertice );
        // Elimina todos los arcos que salen del vértice
        vertice.eliminarArcos( );
        // Localiza en el grafo todos los arcos que llegan a este vértice y los
        // elimina
        for( Vertice<K, V, A> vert : vertices.values( ) )
        {
            try
            {
                vert.eliminarArco( vertice.darId( ) );
            }
            catch( ArcoNoExisteException e )
            {
                // En caso de no existir no hace nada
            }
        }
        // Elimina el vértice
        vertices.remove( vertice.darId( ) );
    }

    /**
     * Agrega un nuevo arco al grafo
     * @param idVerticeOrigen Identificador del vértice desde donde sale el arco
     * @param idVerticeDestino Identificador del vértice hasta donde llega el
     *            arco
     * @param infoArco Elemento del arco
     * @return Arco Creado
     * @throws VerticeNoExisteException Excepción generada si alguno de los
     *             vértices especificados no existe
     * @throws ArcoYaExisteException
     */
    public Arco<K, V, A> agregarArco( K idVerticeOrigen, K idVerticeDestino, A infoArco ) throws VerticeNoExisteException, ArcoYaExisteException
    {
        // Obtiene los vértices
        Vertice<K, V, A> verticeOrigen = darVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darVertice( idVerticeDestino );
        // Crea el arco y lo agrega
        Arco<K, V, A> arco = new Arco<K, V, A>( verticeOrigen, verticeDestino, infoArco );
        verticeOrigen.agregarArco( arco );
        return arco;
    }

    /**
     * Elimina el arco que existe entre dos vértices
     * @param idVerticeOrigen Identificador del vértice desde donde sale el arco
     * @param idVerticeDestino Identificador del vértice hasta donde llega el
     *            arco
     * @throws VerticeNoExisteException Excepción generada cuando el vértice de
     *             salida no existe
     * @throws ArcoNoExisteException Excepción generada cuando el arco no existe
     */
    public void eliminarArco( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException, ArcoNoExisteException
    {
        // Obtiene el vértice y elimina el arco
        Vertice<K, V, A> verticeOrigen = darVertice( idVerticeOrigen );
        verticeOrigen.eliminarArco( idVerticeDestino );
    }

    /**
     * Devuelve el orden del grafo
     * @return Orden del grafo
     */
    public int darOrden( )
    {
        return vertices.size( );
    }

    /**
     * Borra las marcas de todos los vértices del grafo
     */
    public void reiniciarMarcas( )
    {
        // Elimina todas las marcas presentes en los vértices del grafo
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            vertice.desmarcar( );
        }
    }

    /**
     * Verifica si existe un camino entre los dos vértices especificados
     * @param idVerticeOrigen Vértice de origen
     * @param idVerticeDestino Vértice de destino
     * @return true si hay camino entre los dos vértices especificados o false
     *         de lo contrario
     * @throws VerticeNoExisteException Si no existe alguno de los dos vértices
     *             dados
     */
    public boolean hayCamino( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene los vértices
        Vertice<K, V, A> verticeOrigen = darVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darVertice( idVerticeDestino );
        return verticeOrigen.hayCamino( verticeDestino );
    }

    /**
     * Retorna el camino más corto (de menor longitud) entre el par de vértices
     * especificados
     * @param idVerticeOrigen Vértice en el que inicia el camino
     * @param idVerticeDestino Vértice en el que termina el camino
     * @return El camino más corto entre el par de vértices especificados
     * @throws VerticeNoExisteException Si alguno de los dos vértices no existe
     */
    public Camino darCaminoMasCorto( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene los vértices
        Vertice<K, V, A> verticeOrigen = darVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darVertice( idVerticeDestino );
        // Le pide al vértice de origen que localice el camino
        return verticeOrigen.darCaminoMasCorto( verticeDestino );
    }

    /**
     * Retorna el camino más barato (de menor costo) entre el par de vértices
     * especificados
     * @param idVerticeOrigen Vértice en el que inicia el camino
     * @param idVerticeDestino Vértice en el que termina el camino
     * @return El camino más barato entre el par de vértices especificados
     * @throws VerticeNoExisteException Si alguno de los dos vértices no existe
     */
    public Camino darCaminoMasBarato( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene los vértices
        Vertice<K, V, A> verticeOrigen = darVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darVertice( idVerticeDestino );
        // Le pide al vértice de origen que localice el camino
        return verticeOrigen.darCaminoMasBarato( verticeDestino );
    }

    /**
     * Indica si hay un ciclo en el grafo que parte en el vértice especificado
     * @param idVertice El identificador del vértice
     * @return true si hay camino o false en caso contrario
     * @throws VerticeNoExisteException Si el vértice especificado no existe
     */
    public boolean hayCiclo( K idVertice ) throws VerticeNoExisteException
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene el vértice
        Vertice<K, V, A> vertice = darVertice( idVertice );
        // Le pregunta al vértice de origen si a partir de él hay un ciclo
        return vertice.hayCiclo( );
    }

    /**
     * Indica si en el grafo hay camino hamiltoniano
     * @return true si hay camino hamiltoniano o false en caso contrario
     */
    public boolean hayCaminoHamilton( )
    {
        // Recorre todos los vértices del grafo buscando un camino de Hamilton
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            // Borra todas las marcas presentes en el grafo
            reiniciarMarcas( );
            if( vertice.hayCaminoHamilton( 0, darOrden( ) ) )
                return true;
        }
        return false;
    }

    /**
     * Indica si en el grafo hay ciclo hamiltoniano
     * @return true si hay ciclo hamiltoniano o false en caso contrario
     */
    public boolean hayCicloHamilton( )
    {
        // TODO Por implementar
        return false;
    }

    /**
     * Retorna el camino hamiltoniano que hay en el grafo
     * @param El camino hamiltoniano que hay en el grafo. En caso de que no haya
     *            se retorna null
     */
    public Camino darCaminoHamilton( )
    {
        // Recorre todos los vértices del grafo buscando un camino de Hamilton
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            // Borra todas las marcas presentes en el grafo
            reiniciarMarcas( );
            Camino<K, V, A> hamilton = new Camino<K, V, A>( vertice );
            if( vertice.darCaminoHamilton( hamilton, darOrden( ) ) )
                return hamilton;
        }
        return null;
    }

    /**
     * Retorna el ciclo hamiltoniano que hay en el grafo
     * @return El ciclo de Hamilton que hay en el grafo. En caso de que no haya
     *         se retorna null
     */
    public Camino darCicloHamilton( )
    {
        return null;
    }

    /**
     * Calcula todos los caminos mínimos desde el vértice dado hacia los demás
     * vértices del grafo
     * @param idVertice El identificador del vértice
     * @return Los caminos mínimos desde el vértice especificado hacía los demás
     *         nodos
     * @throws VerticeNoExisteException Si el vértice especificado no existe
     */
    public CaminosMinimos dijkstra( K idVertice ) throws VerticeNoExisteException
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene el vértice
        Vertice<K, V, A> vertice = darVertice( idVertice );
        // Inicializa la estructura que va a permitir representar los caminos
        // mínimos que van
        // desde vértice dado a todos los demás vértices del grafo
        CaminosMinimos<K, V, A> minimos = new CaminosMinimos<K, V, A>( vertice, darVertices( ) );
        return vertice.dijkstra( minimos );
    }

    /**
     * Indica si el grafo es conexo
     * @return true si el árbol es conexo o false en caso contrario
     */
    public boolean esConexo( )
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        Collection<Vertice<K, V, A>> verts = vertices.values( );
        if( verts.size( ) == 0 )
            return true;
        else
            return verts.iterator( ).next( ).esConexo( );
    }

    /**
     * Indica si el grafo es fuertemente conexo
     * @return true si el árbol es fuertemente conexo o false en caso contrario
     */
    public boolean esFuertementeConexo( )
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        Collection<Vertice<K, V, A>> verts = vertices.values( );
        if( verts.size( ) == 0 )
            return true;
        else
            return verts.iterator( ).next( ).esFuertementeConexo( );
    }

    /**
     * Indica si en el grafo no hay ciclos
     * @return true si en el grafo no hay ciclos o false en caso contrario
     */
    public boolean esAciclico( )
    {
        // TODO Por implementar
        return false;
    }

    /**
     * Indica si en el grafo hay camino de Euler o false en caso contrario
     * @return true si hay camino de euler o false en caso contrario
     */
    public boolean hayCaminoEuler( )
    {
        return false;
    }

    /**
     * Retorna el camino de euler
     * @return El camino de euler o null si éste no existe
     */
    public Camino darCaminoEuler( )
    {
        // TODO Por implementar
        return null;
    }

    /**
     * Indica si en el grafo hay ciclo de Euler o false en caso contrario
     * @return true si hay ciclo de euler o false en caso contrario
     */
    public boolean hayCicloEuler( )
    {
        // TODO Por implementar
        return false;
    }

    /**
     * Retorna el ciclo de euler
     * @return El ciclo de euler o null si éste no existe
     */
    public Camino darCicloEuler( )
    {
        // TODO Por implementar
        return null;
    }

    /**
     * Indica si el grafo es completo
     * @return true si el árbol es completo o false en caso contrario
     */
    public boolean esCompleto( )
    {
        // TODO Por implementar
        return false;
    }

    /**
     * Retorna el recorrido plano sobre el árbol
     * @return El recorrido plano sobre el árbol
     */
    public Iterador<Vertice<K, V, A>> darRecorridoPlano( )
    {
        IteradorSimple<Vertice<K, V, A>> itera = new IteradorSimple<Vertice<K, V, A>>( vertices.size( ) );
        for( Vertice<K, V, A> v : vertices.values( ) )
        {
            try
            {
                itera.agregar( v );
            }
            catch( IteradorException e )
            {
                // Nunca debe ocurrir esta excepción
            }
        }
        return itera;
    }

    /**
     * Retorna el recorrido por profundidad sobre el árbol
     * @return El recorrido por profundidad sobre el árbol
     */
    public Iterador<Vertice<K, V, A>> darRecorridoProfundidad( )
    {
        IteradorSimple<Vertice<K, V, A>> itera = new IteradorSimple<Vertice<K, V, A>>( vertices.size( ) );
        for( Vertice<K, V, A> v : vertices.values( ) )
        {
            if( !v.marcado( ) )
                v.darRecorridoProfundidad( itera );
        }
        return itera;
    }

    /**
     * Retorna el recorrido por niveles del grafo
     * @return El recorrido por niveles
     */
    public Iterador<Vertice<K, V, A>> darRecorridoNiveles( )
    {
        IteradorSimple<Vertice<K, V, A>> itera = new IteradorSimple<Vertice<K, V, A>>( vertices.size( ) );
        ColaEncadenada<Vertice<K, V, A>> frenteExploracion = new ColaEncadenada<Vertice<K, V, A>>( );
        for( Vertice<K, V, A> v : vertices.values( ) )
        {
            if( !v.marcado( ) )
            {
                frenteExploracion.insertar( v );
                while( frenteExploracion.darLongitud( ) != 0 )
                {
                    try
                    {
                        Vertice<K, V, A> actual = frenteExploracion.tomarElemento( );
                        if( !actual.marcado( ) )
                        {
                            actual.marcar( );
                            itera.agregar( actual );
                            for( Arco<K, V, A> a : actual.darSucesores( ) )
                            {
                                Vertice<K, V, A> sucesor = a.darVerticeDestino( );
                                if( !sucesor.marcado( ) )
                                    frenteExploracion.insertar( sucesor );
                            }
                        }
                    }
                    catch( ColaVaciaException e )
                    {
                        // Esta excepción nunca debería ocurrir
                    }
                    catch( IteradorException e )
                    {
                        // Esta excepción nunca debería ocurrir
                    }
                }
            }
        }
        return itera;
    }

    /**
     * Retorna el grafo central del árbol
     * @return El grafo central del árbol
     */
    public Vertice<K, V, A> darCentro( )
    {
        // TODO Por implementar
        return null;
    }

    /**
     * El peso de un grafo es la suma de los pesos de todos sus arcos
     */
    public int darPeso( )
    {
        // TODO Por implementar
        return 0;
    }

    /**
     * Retorna el árbol parcial de recubrimiento del grafo que parte del vértice
     * dado
     * @param idVertice El identificador del vértice
     * @return El árbol de recubrimiento parcial del grafo que parte del vértice
     *         dado
     */
    public Grafo<K, V, A> darArbolParcialRecubrimiento( K idVertice ) throws VerticeNoExisteException
    {
        // TODO Por implementar
        return null;
    }

    /**
     * Retorna la clausura transitiva del árbol
     * @return La clausura transitiva del árbol
     */
    public Grafo<K, V, A> darClausuraTransitiva( )
    {
        // TODO Por implementar
        return null;
    }

    /**
     * Cuenta el número de subgrafos que son conexos
     * @return El número de grafos que son conexos
     */
    public int contarComponentesConexos( )
    {
        // TODO Por implementar
        return 0;
    }
}
