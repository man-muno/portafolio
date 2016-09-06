package uniandes.cupi2.collections.listaEncadenadaOrdenada.test;

import uniandes.cupi2.collections.listaEncadenadaOrdenada.ListaEncadenadaOrdenada;
import uniandes.cupi2.collections.listaEncadenadaOrdenada.NoExisteException;
import uniandes.cupi2.collections.listaEncadenadaOrdenada.NodoLista;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase ListaEncadenadaOrdenada
 */
public class ListaEncadenadaOrdenadaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private ListaEncadenadaOrdenada lista;

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
        lista = new ListaEncadenadaOrdenada<Integer>( );
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
        lista = new ListaEncadenadaOrdenada<Integer>( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            lista.insertar( new Integer( cont * 2 ) );
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
        lista = new ListaEncadenadaOrdenada<Long>( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            lista.insertar( new Long( cont * ( -1 ) ) );
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
     * Prueba que los elementos se estén ingresando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar1( )
    {
        setupEscenario1( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            lista.insertar( new Integer( 5 * cont ) );
            verificarInvariante( );
        }

        // Verificar que la lista tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", numeroElementos, lista.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        NodoLista nodo = ( NodoLista )lista.darPrimero( );
        int cont = 0;

        while( nodo != null )
        {
            Integer elemento = ( Integer )nodo.darElemento( );

            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", 5 * cont, elemento.intValue( ) );

            nodo = nodo.darSiguiente( );
            cont++;
        }
    }

    /**
     * Prueba que los elementos se estén ingresando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar2( )
    {
        setupEscenario1( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            lista.insertar( new Integer( numeroElementos - cont ) );
            verificarInvariante( );
        }

        // Verificar que la lista tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", numeroElementos, lista.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        NodoLista nodo = ( NodoLista )lista.darPrimero( );
        int cont = 1;

        while( nodo != null )
        {
            Integer elemento = ( Integer )nodo.darElemento( );

            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", cont, elemento.intValue( ) );

            nodo = nodo.darSiguiente( );
            cont++;
        }
    }

    /**
     * Prueba que los elementos se estén ingresando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar3( )
    {
        setupEscenario1( );

        // Insertar 5 elementos en ordenes distintos
        lista.insertar( new Integer( 5 ) );
        verificarInvariante( );

        lista.insertar( new Integer( 1 ) );
        verificarInvariante( );

        lista.insertar( new Integer( 1000 ) );
        verificarInvariante( );

        lista.insertar( new Integer( 500 ) );
        verificarInvariante( );

        lista.insertar( new Integer( -1 ) );
        verificarInvariante( );

        // Verificar que la lista tenga la longitud correcta
        assertEquals( "No se adicionaron todos los elementos", 5, lista.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        NodoLista nodo = ( NodoLista )lista.darPrimero( );

        Integer elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor en la posición -1 no se agrego de forma correcta", -1, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor en la posición 1 no se agrego de forma correcta", 1, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor en la posición 5 no se agrego de forma correcta", 5, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor en la posición 500 no se agrego de forma correcta", 500, elemento.intValue( ) );

        nodo = nodo.darSiguiente( );

        elemento = ( Integer )nodo.darElemento( );

        assertEquals( "El valor en la posición 1000 no se agrego de forma correcta", 1000, elemento.intValue( ) );

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

                lista.eliminar( new Integer( cont * 2 ) );
                verificarInvariante( );

            }

        }
        catch( NoExisteException e )
        {

            e.printStackTrace( );
        }
        // Verificar que la lista tenga la longitud correcta
        assertEquals( "No eliminaron todos los elementos", 0, lista.darLongitud( ) );

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
        String resp = "[" + numeroElementos + "]:";
        for( int i = numeroElementos - 1; i >= 0; i-- )
        {
            resp += ( new Long( i * ( -1 ) ) ).toString( ) + "<->";
        }

        assertEquals( "El método toString no funciona correctamente", resp, listaS );
    }
}
