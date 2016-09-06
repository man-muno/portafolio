/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan E. Gomez - Ene 28, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.grafo.matrizAdyacencia;

import uniandes.cupi2.collections.grafo.Arco;
import uniandes.cupi2.collections.grafo.ArcoNoExisteException;
import uniandes.cupi2.collections.grafo.Grafo;
import uniandes.cupi2.collections.grafo.IArco;
import uniandes.cupi2.collections.grafo.IVertice;
import uniandes.cupi2.collections.grafo.Vertice;
import uniandes.cupi2.collections.grafo.VerticeNoExisteException;
import uniandes.cupi2.collections.iterador.Iterador;

public class MatrizAdyacencia<K, V extends IVertice<K>, A extends IArco>
{

    /**
     * Matriz que va a representar las adyacencias
     */
    private ArcoMatriz[][] matriz;

    /**
     * Iterador de vertices que servirá de referencia para los valores de las filas y columnas de la matriz
     */
    private Iterador<Vertice<K, V, A>> vertices;

    /**
     * Grafo a partir del cual se calcula la matriz de adyacencia
     */
    private Grafo<K, V, A> grafo;

    /**
     * Constructor a partir de un grafo
     * 
     * @param grafo Grafo a partir del cual se va calcular la matriz de adyacencia
     */
    public MatrizAdyacencia( Grafo<K, V, A> grafo )
    {
        this.grafo = grafo;

        // Inicializar el arreglo con los vertices del grafo que sirva como
        // referencia para las columnas y filas de la matriz
        vertices = grafo.darRecorridoPlano( );

        // Crear la matriz de adyacencia vacia
        matriz = new ArcoMatriz[grafo.darOrden( )][grafo.darOrden( )];

        // Inicializar los valores de la matriz
        inicializarMatriz( );
    }

    /**
     * Marca un arco de la matriz
     * 
     * @param idOrigen id del vertice origen del arco
     * @param idDestino id del vertice destino del arco
     * @throws ArcoNoExisteException Si no existe un arco entre los vertices buscados
     * @throws VerticeNoExisteException Si alguno de los vértices buscados no existe
     */
    public void marcarArco( K idOrigen, K idDestino ) throws ArcoNoExisteException, VerticeNoExisteException
    {
        int posOrigen = darPosVertice( idOrigen );
        int posDestino = darPosVertice( idDestino );
        if( matriz[ posOrigen ][ posDestino ] == null )
            throw new ArcoNoExisteException( "El arco buscado no existe", idOrigen, idDestino );
        matriz[ posOrigen ][ posDestino ].marcar( );
    }

    /**
     * Desmarca un arco de la matriz
     * 
     * @param idOrigen id del vertice origen del arco
     * @param idDestino id del vertice destino del arco
     * @throws ArcoNoExisteException Si no existe un arco entre los vertices buscados
     * @throws VerticeNoExisteException Si no existe alguno de los vértices buscados
     */
    public void desmarcarArco( K idOrigen, K idDestino ) throws ArcoNoExisteException, VerticeNoExisteException
    {
        int posOrigen = darPosVertice( idOrigen );
        int posDestino = darPosVertice( idDestino );
        if( matriz[ posOrigen ][ posDestino ] == null )
            throw new ArcoNoExisteException( "El arco buscado no existe", idOrigen, idDestino );
        matriz[ posOrigen ][ posDestino ].desmarcar( );
    }

    /**
     * Retorna la marca de un arco
     * 
     * @param idOrigen id del vertice origen del arco
     * @param idDestino id del vertice destino del arco
     * @throws ArcoNoExisteException Si no existe un arco entre los vertices buscados
     * @return <code>true</code> si el arco está marcado o <code>false</code> en caso contrario
     * @throws VerticeNoExisteException Si alguno de los vértices buscados no existe
     */
    public boolean marcado( K idOrigen, K idDestino ) throws ArcoNoExisteException, VerticeNoExisteException
    {
        int posOrigen = darPosVertice( idOrigen );
        int posDestino = darPosVertice( idDestino );
        if( matriz[ posOrigen ][ posDestino ] == null )
            throw new ArcoNoExisteException( "El arco buscado no existe", idOrigen, idDestino );
        return matriz[ posOrigen ][ posDestino ].marcado( );
    }

    /**
     * Desmarca todos los arcos de la matriz
     */
    public void reiniciarMarcas( )
    {
        for( Arco<K, V, A> arco : grafo.darArcos( ) )
        {
            try
            {
                int posOrigen;
                posOrigen = darPosVertice( arco.darVerticeOrigen( ).darId( ) );
                int posDestino = darPosVertice( arco.darVerticeDestino( ).darId( ) );
                matriz[ posOrigen ][ posDestino ].desmarcar( );
            }
            catch( VerticeNoExisteException e )
            {
                // Esto no debería suceder
            }
        }
    }

    /**
     * Inicializa los valores de la matriz de adyacancia a partir de un grafo
     */
    private void inicializarMatriz( )
    {
        for( Arco<K, V, A> arco : grafo.darArcos( ) )
        {
            try
            {
                int posOrigen;
                posOrigen = darPosVertice( arco.darVerticeOrigen( ).darId( ) );
                int posDestino = darPosVertice( arco.darVerticeDestino( ).darId( ) );
                matriz[ posOrigen ][ posDestino ] = new ArcoMatriz( arco );
            }
            catch( VerticeNoExisteException e )
            {
                // Esto no debería suceder
            }
        }
    }

    /**
     * Retorna la posición de un vertice en el iterador de referencia
     * 
     * @param id Id del vértice buscado
     * @return La posicion del vértice buscado en el iterador o -1 si no lo encuentra
     * @throws VerticeNoExisteException Si el vértice buscado no existe
     */
    private int darPosVertice( K id ) throws VerticeNoExisteException
    {
        vertices.reiniciar( );
        for( int i = 0; vertices.haySiguiente( ); i++ )
        {
            Vertice<K, V, A> vert = vertices.darSiguiente( );
            if( vert.darId( ).equals( id ) )
                return i;
        }
        throw new VerticeNoExisteException( "El vertice buscado no existe", id );
    }

}
