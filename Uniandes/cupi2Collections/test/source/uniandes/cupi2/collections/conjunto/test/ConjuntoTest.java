package uniandes.cupi2.collections.conjunto.test;

import uniandes.cupi2.collections.conjunto.Conjunto;
import uniandes.cupi2.collections.iterador.Iterador;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase Conjunto
 */
public class ConjuntoTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private Conjunto conjunto;

    /**
     * El número de elementos a manejar en cada escenario
     */
    private int numeroElementos;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un conjunto vacío
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
        conjunto = new Conjunto<Integer>( );
        numeroElementos = 0;
    }

    /**
     * Construye un conjunto con 30 elementos negativos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        conjunto = new Conjunto<Integer>( );
        numeroElementos = 30;

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            conjunto.insertar( cont * ( -2 ) );
        }
    }

    /**
     * Construye un conjunto con un elemento negativo
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        conjunto = new Conjunto<Integer>( );
        numeroElementos = 1;
        conjunto.insertar( new Integer( 300 ) );
    }

    /**
     * Verifica que los elementos en el conjunto sean adicionados
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar1( )
    {
        setupEscenario1( );

        Integer[] elementos = new Integer[20];

        for( int cont = 0; cont < 20; cont++ )
        {
            elementos[ cont ] = new Integer( cont );
        }

        // Insertar 20 elementos en el conjunto
        for( int cont = 0; cont < 20; cont++ )
        {
            boolean insertado = conjunto.insertar( elementos[ cont ] );
            assertTrue( "El elemento debió ser adicionado", insertado );
        }

        // Verificar que los elementos hayan sido insertados
        for( int cont = 19; cont >= 0; cont-- )
        {
            boolean existe = conjunto.buscar( elementos[ cont ] );
            assertTrue( "El elemento debería existir", existe );
        }
    }

    /**
     * Verifica no se inserten elementos repetidos
     */
    @SuppressWarnings("unchecked")
    public void testInsertar2( )
    {
        setupEscenario2( );

        // Insertar 20 elementos en el conjunto
        for( int cont = 0; cont < 20; cont++ )
        {
            boolean insertado = conjunto.insertar( new Integer( cont * ( -2 ) ) );
            assertFalse( "El elemento no debió ser adicionado", insertado );
        }
    }

    /**
     * Verifica que los elementos en el conjunto se busquen de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testBuscar( )
    {
        setupEscenario2( );

        // Verificar que los elementos se busquen correctamente
        for( int cont = 19; cont >= 0; cont-- )
        {
            boolean existe = conjunto.buscar( new Integer( cont * ( -2 ) ) );
            assertTrue( "El elemento debería existir", existe );
        }

        // Realizar búsqueda de elementos que no existan
        for( int cont = 1; cont <= numeroElementos; cont++ )
        {
            boolean existe = conjunto.buscar( new Integer( cont ) );
            assertFalse( "El elemento debería existir", existe );
        }
    }

    /**
     * Verifica que la cardinalidad se maneje correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarCardinalidad( )
    {
        // Verificar que la cardinalidad sea correcta
        setupEscenario2( );
        int cardinalidad = conjunto.cardinalidad( );
        assertEquals( "La cardinalidad no es correcta", numeroElementos, cardinalidad );

        setupEscenario1( );
        cardinalidad = conjunto.cardinalidad( );
        assertEquals( "La cardinalidad no es correcta", numeroElementos, cardinalidad );

        setupEscenario3( );
        cardinalidad = conjunto.cardinalidad( );
        assertEquals( "La cardinalidad no es correcta", numeroElementos, cardinalidad );

    }

    /**
     * Verifica que los elementos se eliminen de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar( )
    {
        // Verificar que la cardinalidad sea correcta
        setupEscenario2( );

        boolean elimino;
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            elimino = conjunto.eliminar( cont * ( -2 ) );

            assertTrue( "El elemento debió ser eliminado", elimino );
        }

        assertEquals( "La cardinalidad no es correcta", 0, conjunto.cardinalidad( ) );

        setupEscenario3( );
        elimino = conjunto.eliminar( 1000 );

        assertFalse( "El elemento no debió ser eliminado", elimino );
        assertEquals( "La cardinalidad no es correcta", numeroElementos, conjunto.cardinalidad( ) );

        setupEscenario1( );
        elimino = conjunto.eliminar( 1000 );

        assertFalse( "El elemento no debió ser eliminado", elimino );
        assertEquals( "La cardinalidad no es correcta", numeroElementos, conjunto.cardinalidad( ) );
    }

    /**
     * Verifica se retorne un iterador correcto sobre los elementos del conjunto
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarIterador( )
    {
        // Verificar que la cardinalidad sea correcta
        setupEscenario2( );

        Iterador it = conjunto.darIterador( );

        // Recorrer cada elemento de la lista usando el iterador
        int cont = 0;
        while( it.haySiguiente( ) )
        {
            Integer elemento = ( Integer )it.darSiguiente( );
            Integer aux = new Integer( cont * ( -2 ) );
            assertEquals( "El elemento no es correcto", aux, elemento );
            cont++;
        }

        assertEquals( "No se recorrieron todos los elementos del conjunto", numeroElementos, cont );

        setupEscenario3( );

        it = conjunto.darIterador( );

        // Recorrer cada elemento de la lista usando el iterador
        cont = 0;
        while( it.haySiguiente( ) )
        {
            Integer elemento = ( Integer )it.darSiguiente( );

            assertEquals( "El elemento no es correcto", new Integer( 300 ), elemento );
            cont++;
        }

        assertEquals( "No se recorrieron todos los elementos del conjunto", numeroElementos, cont );

        setupEscenario1( );
        it = conjunto.darIterador( );
        assertFalse( "El iterador debería ser vacio", it.haySiguiente( ) );
    }

}
