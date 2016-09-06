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

import uniandes.cupi2.collections.colaEncadenada.*;
import uniandes.cupi2.collections.grafo.dijkstra.*;
import uniandes.cupi2.collections.iterador.*;

/**
 * Representa un grafo dirigido
 * @param <K> Tipo del identificador de un v�rtice
 * @param <V> Tipo de datos del elemento del v�rtice
 * @param <A> Tipo de datos del elemento del arco
 */
public class Grafo<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tabla de hashing con los v�rtices
     */
    private HashMap<K, Vertice<K, V, A>> vertices;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo grafo vac�o
     */
    public Grafo( )
    {
        vertices = new HashMap<K, Vertice<K, V, A>>( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve el v�rtice identificado con el identificador especificado
     * @param idVertice Identificador del v�rtice
     * @return V�rtice buscado
     * @throws VerticeNoExisteException Excepci�n generada cuando el v�rtice
     *             buscado no existe en el grafo
     */
    public Vertice<K, V, A> darVertice( K idVertice ) throws VerticeNoExisteException
    {
        Vertice<K, V, A> vertice = vertices.get( idVertice );
        if( vertice == null )
        {
            throw new VerticeNoExisteException( "El v�rtice buscado no existe en el grafo", idVertice );
        }
        return vertice;
    }

    /**
     * Indica si el v�rtice con el identificador dado existe en el grafo
     * @param idVertice Identificador del v�rtice
     * @return true si el v�rtice con el identificador dado existe o false en
     *         caso contrario
     */
    public boolean existeVertice( K idVertice )
    {
        return vertices.get( idVertice ) != null;
    }

    /**
     * Devuelve todos los v�rtices del grafo
     * @return V�rtices del grafo
     */
    public Collection<Vertice<K, V, A>> darVertices( )
    {
        return vertices.values( );
    }

    /**
     * Devuelve el arco definido entre los dos v�rtices especificados. Devuelve
     * null si no existe
     * @param idVerticeOrigen V�rtice desde donde sale el arco
     * @param idVerticeDestino V�rtice hacia donde llega el arco
     * @return Arco que conecta los v�rtices. Null si no existe
     * @throws VerticeNoExisteException Excepci�n generada cuando alguno de los
     *             v�rtices no existe en el grafo
     */
    public Arco<K, V, A> darArco( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException
    {
        // Busca el primer v�rtice y luego busca el arco
        Vertice<K, V, A> vertice = darVertice( idVerticeOrigen );
        if( existeVertice( idVerticeDestino ) )
            return vertice.darArco( idVerticeDestino );
        else
            throw new VerticeNoExisteException( "V�rtice destino no existe", idVerticeDestino );
    }

    /**
     * Devuelve todos los arcos del grafo
     * @return Arcos del grafo
     */
    public ArrayList<Arco<K, V, A>> darArcos( )
    {
        ArrayList<Arco<K, V, A>> arcos = new ArrayList<Arco<K, V, A>>( );

        // Recorre todos los v�rtices buscando los arcos
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            arcos.addAll( vertice.darSucesores( ) );
        }
        return arcos;
    }

    /**
     * Devuelve los arcos que salen del v�rtice especificado
     * @param idVertice Identificador del v�rtice
     * @return Arcos que salen del v�rtice especificado
     * @throws VerticeNoExisteException Excepci�n generada cuando el v�rtice
     *             especificado no existe
     */
    public ArrayList<Arco<K, V, A>> darSucesores( K idVertice ) throws VerticeNoExisteException
    {
        return darVertice( idVertice ).darSucesores( );
    }

    /**
     * Devuelve los arcos que llegan al v�rtice especificado
     * @param idVertice Identificador del v�rtice
     * @return Arcos que llegan al v�rtice especificado
     * @throws VerticeNoExisteException Excepci�n generada cuando el v�rtice
     *             especificado no existe
     */
    public ArrayList<Arco<K, V, A>> darPredecesores( K idVertice ) throws VerticeNoExisteException
    {
        return darVertice( idVertice ).darPredecesores( );
    }

    /**
     * Crea un nuevo v�rtice en el grafo
     * @param elemento Elemento del v�rtice
     * @return V�rtice creado
     * @throws VerticeYaExisteException Si el v�rtice que se trata de agregar ya
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
     * Elimina el v�rtice identificado con el Identificador especificado
     * @param idVertice Identificador del v�rtice
     * @throws VerticeNoExisteException Excepci�n generada cuando el v�rtice
     *             especificado no existe
     */
    public void eliminarVertice( K idVertice ) throws VerticeNoExisteException
    {
        // Localiza el v�rtice en el grafo
        Vertice<K, V, A> vertice = darVertice( idVertice );
        // Elimina todos los arcos que salen del v�rtice
        vertice.eliminarArcos( );
        // Localiza en el grafo todos los arcos que llegan a este v�rtice y los
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
        // Elimina el v�rtice
        vertices.remove( vertice.darId( ) );
    }

    /**
     * Agrega un nuevo arco al grafo
     * @param idVerticeOrigen Identificador del v�rtice desde donde sale el arco
     * @param idVerticeDestino Identificador del v�rtice hasta donde llega el
     *            arco
     * @param infoArco Elemento del arco
     * @return Arco Creado
     * @throws VerticeNoExisteException Excepci�n generada si alguno de los
     *             v�rtices especificados no existe
     * @throws ArcoYaExisteException
     */
    public Arco<K, V, A> agregarArco( K idVerticeOrigen, K idVerticeDestino, A infoArco ) throws VerticeNoExisteException, ArcoYaExisteException
    {
        // Obtiene los v�rtices
        Vertice<K, V, A> verticeOrigen = darVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darVertice( idVerticeDestino );
        // Crea el arco y lo agrega
        Arco<K, V, A> arco = new Arco<K, V, A>( verticeOrigen, verticeDestino, infoArco );
        verticeOrigen.agregarArco( arco );
        return arco;
    }

    /**
     * Elimina el arco que existe entre dos v�rtices
     * @param idVerticeOrigen Identificador del v�rtice desde donde sale el arco
     * @param idVerticeDestino Identificador del v�rtice hasta donde llega el
     *            arco
     * @throws VerticeNoExisteException Excepci�n generada cuando el v�rtice de
     *             salida no existe
     * @throws ArcoNoExisteException Excepci�n generada cuando el arco no existe
     */
    public void eliminarArco( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException, ArcoNoExisteException
    {
        // Obtiene el v�rtice y elimina el arco
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
     * Borra las marcas de todos los v�rtices del grafo
     */
    public void reiniciarMarcas( )
    {
        // Elimina todas las marcas presentes en los v�rtices del grafo
        for( Vertice<K, V, A> vertice : vertices.values( ) )
        {
            vertice.desmarcar( );
        }
    }

    /**
     * Verifica si existe un camino entre los dos v�rtices especificados
     * @param idVerticeOrigen V�rtice de origen
     * @param idVerticeDestino V�rtice de destino
     * @return true si hay camino entre los dos v�rtices especificados o false
     *         de lo contrario
     * @throws VerticeNoExisteException Si no existe alguno de los dos v�rtices
     *             dados
     */
    public boolean hayCamino( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene los v�rtices
        Vertice<K, V, A> verticeOrigen = darVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darVertice( idVerticeDestino );
        return verticeOrigen.hayCamino( verticeDestino );
    }

    /**
     * Retorna el camino m�s corto (de menor longitud) entre el par de v�rtices
     * especificados
     * @param idVerticeOrigen V�rtice en el que inicia el camino
     * @param idVerticeDestino V�rtice en el que termina el camino
     * @return El camino m�s corto entre el par de v�rtices especificados
     * @throws VerticeNoExisteException Si alguno de los dos v�rtices no existe
     */
    public Camino darCaminoMasCorto( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene los v�rtices
        Vertice<K, V, A> verticeOrigen = darVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darVertice( idVerticeDestino );
        // Le pide al v�rtice de origen que localice el camino
        return verticeOrigen.darCaminoMasCorto( verticeDestino );
    }

    /**
     * Retorna el camino m�s barato (de menor costo) entre el par de v�rtices
     * especificados
     * @param idVerticeOrigen V�rtice en el que inicia el camino
     * @param idVerticeDestino V�rtice en el que termina el camino
     * @return El camino m�s barato entre el par de v�rtices especificados
     * @throws VerticeNoExisteException Si alguno de los dos v�rtices no existe
     */
    public Camino darCaminoMasBarato( K idVerticeOrigen, K idVerticeDestino ) throws VerticeNoExisteException
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene los v�rtices
        Vertice<K, V, A> verticeOrigen = darVertice( idVerticeOrigen );
        Vertice<K, V, A> verticeDestino = darVertice( idVerticeDestino );
        // Le pide al v�rtice de origen que localice el camino
        return verticeOrigen.darCaminoMasBarato( verticeDestino );
    }

    /**
     * Indica si hay un ciclo en el grafo que parte en el v�rtice especificado
     * @param idVertice El identificador del v�rtice
     * @return true si hay camino o false en caso contrario
     * @throws VerticeNoExisteException Si el v�rtice especificado no existe
     */
    public boolean hayCiclo( K idVertice ) throws VerticeNoExisteException
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene el v�rtice
        Vertice<K, V, A> vertice = darVertice( idVertice );
        // Le pregunta al v�rtice de origen si a partir de �l hay un ciclo
        return vertice.hayCiclo( );
    }

    /**
     * Indica si en el grafo hay camino hamiltoniano
     * @return true si hay camino hamiltoniano o false en caso contrario
     */
    public boolean hayCaminoHamilton( )
    {
        // Recorre todos los v�rtices del grafo buscando un camino de Hamilton
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
        // Recorre todos los v�rtices del grafo buscando un camino de Hamilton
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
     * Calcula todos los caminos m�nimos desde el v�rtice dado hacia los dem�s
     * v�rtices del grafo
     * @param idVertice El identificador del v�rtice
     * @return Los caminos m�nimos desde el v�rtice especificado hac�a los dem�s
     *         nodos
     * @throws VerticeNoExisteException Si el v�rtice especificado no existe
     */
    public CaminosMinimos dijkstra( K idVertice ) throws VerticeNoExisteException
    {
        // Borra todas las marcas presentes en el grafo
        reiniciarMarcas( );
        // Obtiene el v�rtice
        Vertice<K, V, A> vertice = darVertice( idVertice );
        // Inicializa la estructura que va a permitir representar los caminos
        // m�nimos que van
        // desde v�rtice dado a todos los dem�s v�rtices del grafo
        CaminosMinimos<K, V, A> minimos = new CaminosMinimos<K, V, A>( vertice, darVertices( ) );
        return vertice.dijkstra( minimos );
    }

    /**
     * Indica si el grafo es conexo
     * @return true si el �rbol es conexo o false en caso contrario
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
     * @return true si el �rbol es fuertemente conexo o false en caso contrario
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
     * @return El camino de euler o null si �ste no existe
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
     * @return El ciclo de euler o null si �ste no existe
     */
    public Camino darCicloEuler( )
    {
        // TODO Por implementar
        return null;
    }

    /**
     * Indica si el grafo es completo
     * @return true si el �rbol es completo o false en caso contrario
     */
    public boolean esCompleto( )
    {
        // TODO Por implementar
        return false;
    }

    /**
     * Retorna el recorrido plano sobre el �rbol
     * @return El recorrido plano sobre el �rbol
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
                // Nunca debe ocurrir esta excepci�n
            }
        }
        return itera;
    }

    /**
     * Retorna el recorrido por profundidad sobre el �rbol
     * @return El recorrido por profundidad sobre el �rbol
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
                        // Esta excepci�n nunca deber�a ocurrir
                    }
                    catch( IteradorException e )
                    {
                        // Esta excepci�n nunca deber�a ocurrir
                    }
                }
            }
        }
        return itera;
    }

    /**
     * Retorna el grafo central del �rbol
     * @return El grafo central del �rbol
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
     * Retorna el �rbol parcial de recubrimiento del grafo que parte del v�rtice
     * dado
     * @param idVertice El identificador del v�rtice
     * @return El �rbol de recubrimiento parcial del grafo que parte del v�rtice
     *         dado
     */
    public Grafo<K, V, A> darArbolParcialRecubrimiento( K idVertice ) throws VerticeNoExisteException
    {
        // TODO Por implementar
        return null;
    }

    /**
     * Retorna la clausura transitiva del �rbol
     * @return La clausura transitiva del �rbol
     */
    public Grafo<K, V, A> darClausuraTransitiva( )
    {
        // TODO Por implementar
        return null;
    }

    /**
     * Cuenta el n�mero de subgrafos que son conexos
     * @return El n�mero de grafos que son conexos
     */
    public int contarComponentesConexos( )
    {
        // TODO Por implementar
        return 0;
    }
}
