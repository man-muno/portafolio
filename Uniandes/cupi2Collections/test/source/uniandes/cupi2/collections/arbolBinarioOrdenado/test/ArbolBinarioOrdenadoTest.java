package uniandes.cupi2.collections.arbolBinarioOrdenado.test;

import uniandes.cupi2.collections.arbolBinarioOrdenado.ArbolBinarioOrdenado;
import uniandes.cupi2.collections.arbolBinarioOrdenado.ExisteException;
import uniandes.cupi2.collections.arbolBinarioOrdenado.NoExisteException;
import uniandes.cupi2.collections.iterador.Iterador;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase ArbolBinarioOrdenado
 */
public class ArbolBinarioOrdenadoTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private ArbolBinarioOrdenado arbol;

    /**
     * El número de elementos a manejar en cada escenario
     */
    private int numeroElementos;

    /**
     * Objeto para verificar el invariante de la estructura
     */
    private VerificadorEstructura verificador;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un árbol con 8 nodos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
        try
        {
            arbol = new ArbolBinarioOrdenado<Long>( );
            arbol.insertar( new Long( 10 ) );
            arbol.insertar( new Long( 9 ) );
            arbol.insertar( new Long( 12 ) );
            arbol.insertar( new Long( 8 ) );
            arbol.insertar( new Long( 11 ) );
            arbol.insertar( new Long( 15 ) );
            arbol.insertar( new Long( 7 ) );
            arbol.insertar( new Long( 6 ) );

            numeroElementos = 8;
            verificador = new VerificadorEstructura<Long>( );

        }
        catch( ExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Construye un árbol vacio de enteros
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        arbol = new ArbolBinarioOrdenado<Long>( );
        numeroElementos = 0;
        verificador = new VerificadorEstructura<Long>( );
    }

    /**
     * Construye un árbol de enteros con tan solo la raíz
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        try
        {
            arbol = new ArbolBinarioOrdenado<Integer>( );
            arbol.insertar( new Integer( 10 ) );
            numeroElementos = 1;
            verificador = new VerificadorEstructura<Integer>( );
        }
        catch( ExisteException e )
        {
            e.printStackTrace( );
        }

    }

    /**
     * Construye un árbol con 8 nodos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario4( )
    {
        try
        {
            arbol = new ArbolBinarioOrdenado<Long>( );
            arbol.insertar( new Long( -2 ) );
            arbol.insertar( new Long( -6 ) );
            arbol.insertar( new Long( -1 ) );
            arbol.insertar( new Long( -7 ) );
            arbol.insertar( new Long( -3 ) );
            arbol.insertar( new Long( 0 ) );

            numeroElementos = 6;
            verificador = new VerificadorEstructura<Long>( );

        }
        catch( ExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Verifica que la estructura y el orden se mantengan en el árbol ordenado
     * 
     */
    @SuppressWarnings("unchecked")
    private void verificarInvariante( )
    {
        boolean estructuraBien = verificador.verificarArbol( arbol );

        assertTrue( "La estructura y/o el orden en el árbol no es correcto", estructuraBien );
    }

    /**
     * Verifica que las inserciones en el árbol se realicen correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar( )
    {
        setupEscenario2( );

        numeroElementos = 11;
        try
        {

            for( int cont = 0; cont < numeroElementos; cont++ )
            {

                arbol.insertar( new Long( cont ) );
                verificarInvariante( );
            }

            // Verificar que la inserción se haya realizado en el orden correcto

            Iterador iterador = arbol.inorden( );
            int cont = 0;
            while( iterador.haySiguiente( ) )
            {
                Long elemento = ( Long )iterador.darSiguiente( );

                assertEquals( "Uno de los elementos retornados no es correcto", new Long( cont ), elemento );
                cont++;
            }

            // Verificar que se hayan recorrido todos los elementos del arbol
            assertEquals( "No se recuperaron todos los elementos al recorrer el árbol por inorden", numeroElementos, cont );
        }
        catch( ExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Verifica que los elementos se estén insertando en el orden correcto
     * 
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar2( )
    {
        // Insertarle más elementos al árbol y verificar que se esté creando correctamente
        setupEscenario3( );
        Integer elemento7 = new Integer( 7 );
        Integer elemento8 = new Integer( 8 );
        Integer elemento9 = new Integer( 9 );
        Integer elemento13 = new Integer( 13 );
        Integer elemento11 = new Integer( 11 );
        Integer elemento12 = new Integer( 12 );
        Integer elemento14 = new Integer( 14 );

        try
        {
            arbol.insertar( elemento9 );
            verificarInvariante( );
            arbol.insertar( elemento12 );
            verificarInvariante( );
            arbol.insertar( elemento8 );
            verificarInvariante( );
            arbol.insertar( elemento13 );
            verificarInvariante( );
            arbol.insertar( elemento11 );
            verificarInvariante( );
            arbol.insertar( elemento14 );
            verificarInvariante( );
            arbol.insertar( elemento7 );

            // Verificar que los elementos se hayan insertado en el orden correcto
            Iterador iterador = arbol.inorden( );

            Integer elemento;

            int cont = 7;

            while( iterador.haySiguiente( ) )
            {

                elemento = ( Integer )iterador.darSiguiente( );

                if( elemento.intValue( ) != 10 )
                    assertEquals( "Uno de los elementos retornados no es correcto", new Integer( cont ), elemento );

                cont++;

            }

            // Tratar de ingresar un elemento repetido
            try
            {
                arbol.insertar( elemento7 );
                assertTrue( "No se se debió insertar el elemento", false );
                verificarInvariante( );
            }
            catch( ExisteException e )
            {
                assertTrue( "No se se debió insertar el elemento", true );
                verificarInvariante( );
            }

        }
        catch( ExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Verifica que la búsqueda de un elemento se realice correctametne
     * 
     */
    @SuppressWarnings("unchecked")
    public void testBuscar( )
    {
        setupEscenario1( );

        Long elemento = new Long( 7 );
        Long busqueda = ( Long )arbol.buscar( elemento );

        assertEquals( "El resultado de la búsqueda no es correcto", elemento, busqueda );

        elemento = new Long( 15 );

        busqueda = ( Long )arbol.buscar( elemento );

        assertEquals( "El resultado de la búsqueda no es correcto", elemento, busqueda );

        // buscar un elemento que no exista
        elemento = new Long( 1000 );

        busqueda = ( Long )arbol.buscar( elemento );

        assertNull( "El resultado de la búsqueda no es correcto", busqueda );
    }

    /**
     * Verifica que la altura del árbol se genere correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarAltura( )
    {
        setupEscenario1( );

        int altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 5, altura );

        setupEscenario2( );

        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 0, altura );

        setupEscenario3( );

        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 1, altura );

        setupEscenario4( );

        altura = arbol.darAltura( );

        assertEquals( "La altura retornada no es correcta", 3, altura );
    }

    /**
     * Verifica que se retorne el elemento mayor correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarMayor( )
    {
        setupEscenario1( );

        Long mayor = ( Long )arbol.darMayor( );

        assertEquals( "El elemento mayor no es el correcto", new Long( 15 ), mayor );

        // Verificar que se retorne null cuando el árbol no tiene elementos
        setupEscenario2( );
        mayor = ( Long )arbol.darMayor( );

        assertNull( "El elemento mayor no es el correcto", mayor );

        // Verificar el método mayor con un solo elemento en el árbol
        setupEscenario3( );

        Integer mayorI = ( Integer )arbol.darMayor( );

        assertEquals( "El elemento mayor no es el correcto", new Integer( 10 ), mayorI );

        setupEscenario4( );

        mayor = ( Long )arbol.darMayor( );

        assertEquals( "El elemento mayor no es el correcto", new Long( 0 ), mayor );
    }

    /**
     * Verifica que se retorne el elemento menor correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarMenor( )
    {
        setupEscenario1( );

        Long menor = ( Long )arbol.darMenor( );

        assertEquals( "El elemento menor no es el correcto", new Long( 6 ), menor );

        // Verificar que se retorne null cuando el árbol no tiene elementos
        setupEscenario2( );
        menor = ( Long )arbol.darMenor( );

        assertNull( "El elemento menor no es el correcto", menor );

        // Verificar el método menor con un solo elemento en el árbol
        setupEscenario3( );

        Integer menorI = ( Integer )arbol.darMenor( );

        assertEquals( "El elemento menor no es el correcto", new Integer( 10 ), menorI );

        setupEscenario4( );

        menor = ( Long )arbol.darMenor( );

        assertEquals( "El elemento mayor no es el correcto", new Long( -7 ), menor );
    }

    /**
     * Verifica que el peso se retorne correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarPeso( )
    {
        setupEscenario1( );

        int peso = arbol.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );

        setupEscenario2( );
        peso = arbol.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );

        setupEscenario3( );

        peso = arbol.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );

        setupEscenario4( );

        peso = arbol.darPeso( );

        assertEquals( "El peso no es correcto", numeroElementos, peso );
    }

    /**
     * Verifica que se recorra el árbol en inorden de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInorden( )
    {
        setupEscenario1( );

        Iterador iterador = arbol.inorden( );

        // Verificar que el recorrido en inorden sea correcto
        int cont = 6;
        while( iterador.haySiguiente( ) )
        {

            Long elemento = ( Long )iterador.darSiguiente( );

            if( cont == 13 )
            {
                cont = cont + 2;
            }

            assertEquals( "El elemento retornado no es el correcto", new Long( cont ), elemento );
            cont++;
        }

        // Verificar que se hayan recorrido todos los elementos
        assertEquals( "No se recorrieron todos los elementos en el inorden", numeroElementos, cont - 8 );

        setupEscenario2( );
        iterador = arbol.inorden( );
        assertFalse( "No se debió haber recorrido elemento alguno", iterador.haySiguiente( ) ); // Verificar que no se haya recorrido nada

        // Verificar que se retorne el único elemento del árbol
        setupEscenario3( );
        iterador = arbol.inorden( );
        assertEquals( "El peso no es correcto", new Integer( 10 ), iterador.darSiguiente( ) );
    }

    /**
     * Verifica que todos los elementos del árbol sean eliminados de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar1( )
    {
        setupEscenario1( );

        try
        {
            for( int cont = 6; cont < numeroElementos + 8; cont++ )
            {

                if( cont != 13 && cont != 14 )
                {
                    arbol.eliminar( new Long( cont ) );
                    verificarInvariante( );
                }

            }

            // Verificar que se hayan eliminado todos los elementos
            for( int cont = 6; cont < numeroElementos + 8; cont++ )
            {

                if( cont != 13 && cont != 14 )
                {
                    Long elemento = ( Long )arbol.buscar( new Long( cont ) );
                    assertNull( "No se debió haber retornado el elemento", elemento );
                }
            }

            // Verificar que se hayan eliminado todos los elementos
            assertEquals( "El peso no es correcto", 0, arbol.darPeso( ) );

            setupEscenario3( );
            arbol.eliminar( new Integer( 10 ) );

            // Verificar que se hayan eliminado todos los elementos
            assertEquals( "El peso no es correcto", 0, arbol.darPeso( ) );
            verificarInvariante( );

            setupEscenario4( );
            arbol.eliminar( new Long( -1 ) );

            // Verificar que se hayan eliminado todos los elementos
            assertEquals( "El peso no es correcto", numeroElementos - 1, arbol.darPeso( ) );
            verificarInvariante( );

            // buscar el elemento que se acabo de eliminar
            Long elemento = ( Long )arbol.buscar( new Long( -1 ) );

            assertNull( "No se debió haber retornado el elemento", elemento );
            verificarInvariante( );

            // Eliminar la raiz y verificar que el árbol haya quedado bien construido
            setupEscenario1( );
            arbol.eliminar( new Long( 10 ) );
            verificarInvariante( );
            Iterador iterador = arbol.inorden( );

            int cont = 6;
            while( iterador.haySiguiente( ) )
            {
                elemento = ( Long )iterador.darSiguiente( );

                if( cont == 10 )
                {
                    cont++;
                }
                else if( cont == 13 )
                {
                    cont = cont + 2;
                }

                assertEquals( "Los elementos no se eliminaron de forma correcta", new Long( cont ), elemento );
                cont++;
            }

            // Eliminar otro elemento que no sea la raíz y verificar que el árbol quede bien construido
            setupEscenario4( );
            iterador = arbol.inorden( );
            elemento = ( Long )iterador.darSiguiente( );
            assertEquals( "Los elementos no se eliminaron de forma correcta", new Long( -7 ), elemento );
            verificarInvariante( );
            elemento = ( Long )iterador.darSiguiente( );

            cont = -3;
            while( iterador.haySiguiente( ) )
            {
                elemento = ( Long )iterador.darSiguiente( );

                assertEquals( "Los elementos no se eliminaron de forma correcta", new Long( cont ), elemento );
                cont++;
            }
        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Verifica que se arroje excepción al tratar de eliminar un elemento que no existe
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar2( )
    {
        setupEscenario2( );

        try
        {
            arbol.eliminar( new Long( 10 ) );
            assertTrue( "No se debió eliminar el elemento", false );

        }
        catch( NoExisteException e )
        {

            // Verificar que se hayan eliminado todos los elementos
            assertTrue( "No se debió eliminar el elemento", true );
            verificarInvariante( );
            setupEscenario4( );
            try
            {
                arbol.eliminar( new Long( 15 ) );
                assertTrue( "No se debió eliminar el elemento", false );
            }
            catch( NoExisteException e1 )
            {
                assertTrue( "No se debió eliminar el elemento", true );
                verificarInvariante( );
            }

        }
    }
}
