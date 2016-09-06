package uniandes.cupi2.collections.listaEncadenada.test;

import uniandes.cupi2.collections.listaEncadenada.IndiceFueraDeRango;
import uniandes.cupi2.collections.listaEncadenada.ListaEncadenada;
import uniandes.cupi2.collections.listaEncadenada.NoExisteException;
import uniandes.cupi2.collections.listaEncadenada.NodoLista;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase ListaEncadenada funcionen correctamente
 */
public class ListaEncadenadaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private ListaEncadenada lista;

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
     * Construye una nueva lista vacia de Integers
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
        lista = new ListaEncadenada<Integer>( );
        numeroElementos = 100;
        verificador = new VerificadorEstructura<Integer>( );
    }

    /**
     * Construye una nueva lista con 500 elementos inicialados con el valor de la posicion por 2
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        numeroElementos = 500;
        lista = new ListaEncadenada<Integer>( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            lista.insertarCola( new Integer( cont * 2 ) );
        }
        verificador = new VerificadorEstructura<Integer>( );
    }

    /**
     * Construye una nueva lista con 100 elementos inicialados con el valor de la posicion por -1
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        numeroElementos = 100;
        lista = new ListaEncadenada<Long>( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            lista.insertarCola( new Long( cont * ( -1 ) ) );
        }
        verificador = new VerificadorEstructura<Long>( );
    }

    /**
     * Verifica que la estructura en la cola sea correcta
     * 
     */
    @SuppressWarnings("unchecked")
    private void verificarInvariante( )
    {
        boolean estructuraBien = ( verificador.verificarLista( lista ) );

        assertTrue( "La estructura de la lista no es correcta", estructuraBien );
    }

    /**
     * Prueba que los elementos se estén ingresando correctamente a partir de la cabeza
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertarCabeza( )
    {
        setupEscenario1( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            lista.insertarCola( new Integer( 5 * cont ) );
            verificarInvariante( );
        }

        // Verificar que la lista tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", numeroElementos, lista.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto

        for( int cont = lista.darLongitud( ) - 1; cont >= 0; cont-- )
        {
            Integer elemento = ( Integer )lista.dar( cont );

            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", 5 * cont, elemento.intValue( ) );
        }

    }

    /**
     * Prueba que los elementos se estén ingresando correctamente a partir de la cola
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertarCola( )
    {
        setupEscenario1( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            lista.insertarCola( new Integer( 5 * cont ) );
            verificarInvariante( );
        }

        // Verificar que la lista tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", numeroElementos, lista.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        for( int cont = 0; cont < lista.darLongitud( ); cont++ )
        {
            Integer elemento = ( Integer )lista.dar( cont );

            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", 5 * cont, elemento.intValue( ) );
        }

    }

    /**
     * Prueba que los elementos se estén ingresando correctamente al final de la lista usando el método de inserción por posición
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar1( )
    {
        setupEscenario1( );

        lista.insertarCabeza( new Integer( numeroElementos ) );
        lista.insertarCola( new Integer( 1 ) );

        for( int cont = 1; cont < numeroElementos - 1; cont++ )
        {
            lista.insertar( new Integer( numeroElementos - cont ), cont );
            verificarInvariante( );
        }

        // Verificar que la lista tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", numeroElementos, lista.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        for( int cont = 0; cont < lista.darLongitud( ); cont++ )
        {
            Integer elemento = ( Integer )lista.dar( cont );

            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", numeroElementos - cont, elemento.intValue( ) );
        }

    }

    /**
     * Prueba que los elementos se estén ingresando correctamente en las posiciones intermedias de la lista usando el método de inserción por posición
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar2( )
    {
        setupEscenario2( );

        // Prueba la inserción de un elemento en una posición intermedia
        for( int cont = 0; cont < 100; cont++ )
        {
            lista.insertar( new Integer( numeroElementos - cont ), cont + 200 );
            verificarInvariante( );
        }

        // Verificar que la lista tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", numeroElementos + 100, lista.darLongitud( ) );

        // Verificar que las inserciones se hayan realizado en la posición correcta
        for( int cont = 0; cont < 100; cont++ )
        {
            Integer elemento = ( Integer )lista.dar( cont + 200 );
            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", numeroElementos - cont, elemento.intValue( ) );
        }

        // Verificar que los demás elementos de la lista se mantengan en el orden correcto
        int pos = 0;
        for( int cont = 0; cont < numeroElementos + 100; cont++ )
        {
            if( cont == 200 )
            {
                cont += 100;
            }
            Integer elemento = ( Integer )lista.dar( cont );
            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", pos * 2, elemento.intValue( ) );
            pos++;
        }

    }

    /**
     * Prueba que no se permita la inserción de elementos en posiciones que no existan
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar3( )
    {
        setupEscenario3( );

        try
        {
            lista.insertar( new Long( 1000 ), numeroElementos );
            assertTrue( "No se debio insertar el elemento", false );

        }
        catch( IndiceFueraDeRango e )
        {
            assertTrue( "No se debio insertar el elemento", true );
            verificarInvariante( );
            setupEscenario2( );

            try
            {
                lista.insertar( new Long( 1000 ), -100 );
                assertTrue( "No se debio insertar el elemento", false );
            }
            catch( IndiceFueraDeRango e2 )
            {
                assertTrue( "No se debio insertar el elemento", true );
                verificarInvariante( );
            }
        }

    }

    /**
     * Prueba que todos los elementos se estén eliminando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar1( )
    {
        setupEscenario2( );

        try
        {
            for( int cont = 0; cont < numeroElementos; cont++ )
            {
                Integer elemento = ( Integer )lista.eliminar( new Integer( cont * 2 ) );
                // Verificar que el elemento que se elimino sea el correcto
                assertEquals( "No eliminaron todos los elementos", cont * 2, elemento.intValue( ) );
                verificarInvariante( );
            }

            // Verificar que la lista tenga la longitud correcta
            assertEquals( "No eliminaron todos los elementos", 0, lista.darLongitud( ) );

        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Prueba que algunos elementos se estén eliminando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar2( )
    {
        setupEscenario2( );

        try
        {
            for( int cont = 0; cont < 10; cont++ )
            {

                lista.eliminar( new Integer( cont * 4 ) );
                verificarInvariante( );

            }

        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }

        // Verificar que la lista tenga la longitud correcta
        assertEquals( "No eliminaron todos los elementos", numeroElementos - 10, lista.darLongitud( ) );

        // Verificar que se hayan eliminado los elementos correctos

        for( int cont = 0; cont < 10; cont++ )
        {

            Integer elemento = ( Integer )lista.buscar( new Integer( cont * 4 ) );

            assertNull( "No se elimino el elemento correcto", elemento );

        }

    }

    /**
     * Prueba que se arroje la excepción cuando se trata de eliminar un elemento que no existe
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar3( )
    {
        setupEscenario2( );

        try
        {

            lista.eliminar( new Integer( -1000 ) );

            assertEquals( "No se debería haber eliminado ningún elemento", numeroElementos, lista.darLongitud( ) );

        }
        catch( NoExisteException e )
        {

            assertEquals( "No se debería haber eliminado ningún elemento", numeroElementos, lista.darLongitud( ) );
            verificarInvariante( );
        }

    }

    /**
     * Prueba que todos los elementos se estén eliminando correctamente por posición
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar4( )
    {
        setupEscenario2( );

        try
        {
            for( int cont = 0; cont < numeroElementos; cont++ )
            {
                Integer elemento = ( Integer )lista.eliminar( 0 );
                // Verificar que el elemento que se elimino sea el correcto
                assertEquals( "No eliminaron todos los elementos", cont * 2, elemento.intValue( ) );
                verificarInvariante( );
            }

            // Verificar que la lista tenga la longitud correcta
            assertEquals( "No eliminaron todos los elementos", 0, lista.darLongitud( ) );

        }
        catch( IndiceFueraDeRango e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Prueba que algunos elementos se estén eliminando correctamente por posición
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar5( )
    {
        setupEscenario2( );

        for( int cont = 0; cont < 10; cont++ )
        {
            Integer elemento = ( Integer )lista.eliminar( cont );
            assertEquals( "No se elimino el elemento correcto", cont * 4, elemento.intValue( ) );
            verificarInvariante( );
        }

        // Verificar que la lista tenga la longitud correcta
        assertEquals( "No eliminaron todos los elementos", numeroElementos - 10, lista.darLongitud( ) );

        // Verificar que se hayan eliminado los elementos correctos

        for( int cont = 0; cont < 10; cont++ )
        {

            Integer elemento = ( Integer )lista.buscar( new Integer( cont * 4 ) );

            assertNull( "No se elimino el elemento correcto", elemento );

        }

    }

    /**
     * Prueba que se arroje la excepción cuando se trata de eliminar un elemento en una posición que no existe
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar6( )
    {
        setupEscenario2( );

        try
        {

            Integer elemento = ( Integer )lista.eliminar( numeroElementos );

            assertNull( "No se debería haber eliminado ningún elemento", elemento );

        }
        catch( IndiceFueraDeRango e )
        {

            assertEquals( "No se debería haber eliminado ningún elemento", numeroElementos, lista.darLongitud( ) );
            verificarInvariante( );

            try
            {
                Integer elemento = ( Integer )lista.eliminar( -numeroElementos );
                assertNull( "No se debería haber eliminado ningún elemento", elemento );
                verificarInvariante( );
            }
            catch( IndiceFueraDeRango e2 )
            {
                assertEquals( "No se debería haber eliminado ningún elemento", numeroElementos, lista.darLongitud( ) );
            }
        }
    }

    /**
     * Prueba la búsqueda de los elementos se realice correctamente en la lista ordenada
     * 
     */
    @SuppressWarnings("unchecked")
    public void testBuscar1( )
    {
        setupEscenario3( );

        for( int cont = 0; cont < 10; cont++ )
        {

            Long elemento = ( Long )lista.buscar( new Long( cont * ( -1 ) ) );

            // Verificar que se haya encontrado el elemento correcto
            assertEquals( "No se busco el elemento correctamente", new Long( cont * ( -1 ) ), elemento );
        }

        assertEquals( "La búsqueda no debería haber eliminado ningún elemento", numeroElementos, lista.darLongitud( ) );
    }

    /**
     * Prueba que se retorne null cuando se buscan elementos que no existen
     * 
     */
    @SuppressWarnings("unchecked")
    public void testBuscar2( )
    {
        setupEscenario3( );

        Long elemento = ( Long )lista.buscar( new Long( 100 ) );

        // Verificar que se haya encontrado el elemento correcto
        assertNull( "No se busco el elemento correctamente", elemento );

        elemento = ( Long )lista.buscar( new Long( -10000 ) );

        // Verificar que se haya encontrado el elemento correcto
        assertNull( "No se busco el elemento correctamente", elemento );
    }

    /**
     * Prueba que se retorne la longitud de la lista correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarLongitud( )
    {
        setupEscenario2( );

        // Verificar que la longitud de la lista sea correcta
        assertEquals( "La longitud no se retorno correctamente", numeroElementos, lista.darLongitud( ) );

        try
        {
            lista.eliminar( new Integer( 2 ) );
            lista.eliminar( new Integer( 4 ) );

            assertEquals( "La longitud no se retorno correctamente", numeroElementos - 2, lista.darLongitud( ) );

            lista.eliminar( new Integer( 6 ) );

            assertEquals( "La longitud no se retorno correctamente", numeroElementos - 3, lista.darLongitud( ) );
        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Prueba que se retorne correctamente el primer elemento de la lista
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarPrimero( )
    {
        setupEscenario2( );

        NodoLista nodo = lista.darPrimero( );

        Integer elemento = ( Integer )nodo.darElemento( );

        // Verificar que el primero elemento sea el correcto
        assertEquals( "El primer elemento no es correcto", 0, elemento.intValue( ) );

        // Verificar que el anterior del primero sea null
        assertNull( "El anterior elemento debería ser null", nodo.darAnterior( ) );

        // Verificar que el encadenamiento entre los nodos sea correcto
        NodoLista aux = nodo;

        nodo = nodo.darSiguiente( );

        int cont = 1;
        while( nodo != null )
        {
            NodoLista anterior = nodo.darAnterior( );

            assertEquals( "El elemento anterior del nodo no es correcto", aux, anterior );

            aux = nodo;
            nodo = nodo.darSiguiente( );
            cont++;
        }

        // Verificar que el nodo siguiente al ultimo sea null
        assertNull( "El siguiente elemento debería ser null", aux.darSiguiente( ) );

        // Verificar que el número de nodos sea igual al número de elementos
        assertEquals( "El número de nodos no correcponde al número de elementos", numeroElementos, cont );
    }

    /**
     * Prueba que el método toString de la lista funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testToString( )
    {
        // Verificar que la lista sea vaciada
        setupEscenario3( );

        String listaS = lista.toString( );

        // Construir el string espererado para la prueba
        String resp = "ida: [" + numeroElementos + "]:";
        for( int i = 0; i < numeroElementos; i++ )
        {
            resp += ( new Long( i * ( -1 ) ) ).toString( ) + "<->";
        }

        resp += "\r\nvuelta:[" + numeroElementos + "]:";

        for( int i = numeroElementos - 1; i >= 0; i-- )
        {
            resp += ( new Long( i * ( -1 ) ) ).toString( ) + "<->";
        }
        assertEquals( "El método toString no funciona correctamente", resp, listaS );
    }
}
