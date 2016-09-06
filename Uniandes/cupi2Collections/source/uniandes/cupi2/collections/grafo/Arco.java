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
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.grafo;

/**
 * Representa un arco del grafo
 * @param <K> Tipo del identificador de un v�rtice
 * @param <V> Tipo de datos del elemento del v�rtice
 * @param <A> Tipo de datos del elemento del arco
 */
public class Arco<K, V extends IVertice<K>, A extends IArco>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * V�rtice desde el cual sale el arco
     */
    private Vertice<K, V, A> origen;

    /**
     * V�rtice hacia el cual va el arco
     */
    private Vertice<K, V, A> destino;

    /**
     * Elemento en el arco
     */
    private A infoArco;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del arco
     * @param pOrigen V�rtice desde el cual sale el arco
     * @param pDestino V�rtice hacia donde se dirige el arco
     * @param pInfoArco Elemento en el arco
     */
    public Arco( Vertice<K, V, A> pOrigen, Vertice<K, V, A> pDestino, A pInfoArco )
    {
        origen = pOrigen;
        destino = pDestino;
        infoArco = pInfoArco;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve el elemento del arco
     * @return Elemento en el arco
     */
    public A darInfoArco( )
    {
        return infoArco;
    }

    /**
     * Devuelve el v�rtice de destino del arco
     * @return v�rtice de destino del arco
     */
    public Vertice<K, V, A> darVerticeDestino( )
    {
        return destino;
    }

    /**
     * Devuelve el v�rtice de origen del arco
     * @return v�rtice de origen del arco
     */
    public Vertice<K, V, A> darVerticeOrigen( )
    {
        return origen;
    }

    /**
     * Devuelve el peso del arco
     * @return Peso del arco
     */
    public int darPeso( )
    {
        return infoArco.darPeso( );
    }
}
