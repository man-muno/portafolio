package uniandes.cupi2.collections.lista.test;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.lista.IndiceFueraDeRango;
import uniandes.cupi2.collections.lista.Lista;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase Lista funcionen correctamente
 */
public class ListaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Lista lista;

    /**
     * El número de elementos a manejar en cada escenario
     */
    private int numeroElementos;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva lista vacia de objetos de tipo Long. El numero de elementos a insertar en este escenario es 100.
     */
    public void setupEscenario1( )
    {
        lista = new Lista<Long>( );
        numeroElementos = 100;
    }

    /**
     * Construye una nueva lista con una capacidad inicial de 100 elementos
     * 
     */
    public void setupEscenario2( )
    {
        lista = new Lista<Long>( 100 );
        numeroElementos = 100;
    }

    /**
     * Construye una nueva lista con 300 elementos inicialados con el valor de la posición
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario3( )
    {
        numeroElementos = 300;
        lista = new Lista<Integer>( numeroElementos );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            lista.agregar( new Integer( cont ) );
        }

    }

    /**
     * Prueba que los elementos se estén ingresando en la posición especificada correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAgregar( )
    {
        setupEscenario1( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            lista.agregar( new Long( 10 * cont ) );

        }

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Long elemento = ( Long )lista.darElemento( cont );

            assertEquals( "El valor en la posición " + cont + " no se agrego de forma correcta", 10 * cont, elemento.longValue( ) );

        }
    }

    /**
     * Prueba que los elementos se estén insertando el posición especificada
     * 
     */
    @SuppressWarnings("unchecked")
    public void testInsertar( )
    {
        setupEscenario3( );

        int tam = lista.darLongitud( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            lista.insertar( new Integer( cont ), 2 * cont + 1 );
        }

        // Verificar que la lista tenga el tamaño tam+numeroElementosInsertar

        assertEquals( "El tamaño de la lista no es correcto", tam + numeroElementos, lista.darLongitud( ) );

        // Verificar que todos los elementos hayan sido adicionados y en el orden correcto
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )lista.darElemento( 2 * cont + 1 );

            assertEquals( "El valor en la posición " + 2 * cont + 1 + " no se inserto de forma correcta", cont, elemento.longValue( ) );

        }

        // Insertar un elemento
    }

    /**
     * Prueba que todos los elementos se estén eliminando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar1( )
    {
        setupEscenario3( );

        // Eliminar todos los elementos de la lista

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )lista.eliminar( 0 );

            assertEquals( "No se elimino el elemento correcto", cont, elemento.intValue( ) );
        }

        // Verificar que el tamaño de la lista sea cero

        assertEquals( "El tamaño de la lista no es correcto", 0, lista.darLongitud( ) );
    }

    /**
     * Prueba que se arroje la excepción cuando se trate de eliminar un elemento inexistente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar2( )
    {
        setupEscenario1( );

        try
        {
            Integer elemento = ( Integer )lista.eliminar( 10 );
            assertNull( "No deberia permitir la eliminación", elemento );
        }
        catch( IndiceFueraDeRango e )
        {
        }
    }

    /**
     * Prueba que se eliminen 10 elementos correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar3( )
    {
        setupEscenario3( );
        int tam = lista.darLongitud( );

        // Eliminar 10 Elementos de la lista

        for( int cont = 0; cont < 10; cont++ )
        {
            Integer elemento = ( Integer )lista.eliminar( cont * 10 );

            assertEquals( "No se elimino el elemento correcto", cont * 11, elemento.intValue( ) );
        }

        // Verificar que el tamaño de la lista sea correcto

        assertEquals( "El tamaño de la lista no es correcto", tam - 10, lista.darLongitud( ) );
    }

    /**
     * Prueba que se eliminen 20 elementos de la lista por valor
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar4( )
    {
        setupEscenario3( );
        int tam = lista.darLongitud( );

        // Eliminar 20 Elementos de la lista por su valor

        for( int cont = 0; cont < 20; cont++ )
        {
            Integer elemento = ( Integer )lista.eliminar( new Integer( cont * 10 ) );

            assertEquals( "No se elimino el elemento correcto", cont * 10, elemento.intValue( ) );
        }

        // Verificar que el tamaño de la lista sea correcto

        assertEquals( "El tamaño de la lista no es correcto", tam - 20, lista.darLongitud( ) );
    }

    /**
     * Prueba que no se elimine un elemento por valor inexistene
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar5( )
    {
        setupEscenario3( );

        try
        {
            Integer elemento = ( Integer )lista.eliminar( new Integer( -1 ) );
            assertNull( "No deberia permitir la eliminación", elemento );
        }
        catch( IndiceFueraDeRango e )
        {

        }
    }

    /**
     * Prueba que que las asignaciones sobre todos los elementos de una lista se realicen correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAsignar1( )
    {
        setupEscenario3( );
        Integer elementoAdicionar;

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            elementoAdicionar = new Integer( cont );
            lista.asignar( elementoAdicionar, cont );
            Integer elemento = ( Integer )lista.darElemento( cont );

            assertEquals( "No se realizo la asignación correctamente", elementoAdicionar.intValue( ), elemento.intValue( ) );

        }

    }

    /**
     * Prueba que no se pueda asignar un valor a una posición inválida
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAsignar2( )
    {
        setupEscenario1( );
        Integer elementoAdicionar;

        try
        {

            elementoAdicionar = new Integer( 99 );
            lista.asignar( elementoAdicionar, -1 );
            assertEquals( "No se debería permitir la realización de la asignación", 0, -1 );
        }
        catch( IndiceFueraDeRango e )
        {
        }
    }

    /**
     * Prueba que se encontren los objetos que existan en la lista
     * 
     */
    @SuppressWarnings("unchecked")
    public void testBuscar1( )
    {
        setupEscenario3( );

        for( int cont = numeroElementos - 1; cont >= 0; cont-- )
        {

            int pos = lista.buscar( new Integer( cont ) );
            boolean incorrecto = pos == -1;

            assertFalse( "la búsqueda no se realizaco correctamente", incorrecto );
        }
    }

    /**
     * Prueba que no se encontren que no existan en la lista
     * 
     */
    @SuppressWarnings("unchecked")
    public void testBuscar2( )
    {
        setupEscenario1( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {

            int pos = lista.buscar( new Integer( numeroElementos + cont ) );

            assertEquals( "la búsqueda no se realizaco correctamente", -1, pos );
        }
    }

    /**
     * Prueba que los elementos se obtengan correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarElemento1( )
    {
        setupEscenario3( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {

            int pos = lista.buscar( new Integer( cont ) );

            Integer elemento = ( Integer )lista.darElemento( pos );
            assertEquals( "la búsqueda no se realizó correctamente", cont, elemento.intValue( ) );
        }
    }

    /**
     * Prueba que se arroje excepción cuando se use un índice inválido
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarElemento2( )
    {
        setupEscenario1( );

        try
        {
            for( int cont = 0; cont < numeroElementos; cont++ )
            {

                int pos = lista.buscar( new Integer( cont ) );

                Long elemento = ( Long )lista.darElemento( pos );
                assertNull( "La búsqueda no se realizó correctamente", elemento );
            }
        }
        catch( IndiceFueraDeRango e )
        {
            try
            {
                Long elemento = ( Long )lista.darElemento( -1000 );
                assertNull( "La búsqueda no se realizó correctamente", elemento );
            }
            catch( IndiceFueraDeRango e2 )
            {

            }
        }
    }

    /**
     * Prueba que el iterator funcione correctamente para el recorrido de los elementos de la lista
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarIterador1( )
    {
        setupEscenario3( );

        Iterador it = lista.darIterador( );
        int cont = 0;
        while( it.haySiguiente( ) )
        {
            Integer elemento = ( Integer )it.darSiguiente( );

            assertEquals( "El iterador no retorna el elemento correcto", cont, elemento.intValue( ) );

            cont++;

        }

        // Verificar que el número de elementos recorridos con el iterador sea el correcto
        assertEquals( "No se recorrieron todos los elementos con el iterador", numeroElementos, cont );

        // Verificar que el iterador se reinicia correctamente
        it.reiniciar( );

        cont = 0;
        while( cont < 10 )
        {
            Integer elemento = ( Integer )it.darSiguiente( );

            assertEquals( "El iterador no retorna el elemento correcto", cont, elemento.intValue( ) );

            cont++;

        }

        // Verificar que swe hayan recorrido 10 elementos
        assertEquals( "No se recorrieron todos los elementos con el iterador", 10, cont );

        // Verificar que el iterador se reinicia correctamente y recorrer todo la lista de nuevo
        it.reiniciar( );

        cont = 0;
        while( it.haySiguiente( ) )
        {
            Integer elemento = ( Integer )it.darSiguiente( );

            assertEquals( "El iterador no retorna el elemento correcto", cont, elemento.intValue( ) );

            cont++;

        }

        // Verificar que el número de elementos recorridos con el iterador sea el correcto
        assertEquals( "No se recorrieron todos los elementos con el iterador", numeroElementos, cont );

    }

    /**
     * Prueba que el iterator sobre una lista vacia elementos de la lista
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarIterador2( )
    {
        setupEscenario2( );

        Iterador it = lista.darIterador( );

        Long elemento = ( Long )it.darSiguiente( );

        assertNull( "El iterador no debería retornar elemento", elemento );

        elemento = ( Long )it.darSiguiente( );

        assertNull( "El iterador no debería retornar elemento", elemento );
    }

    /**
     * Prueba que se retorne correctamente la longitud de la lista
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarLongitud( )
    {
        // Verificar que la longitud de la lista sea correcta
        setupEscenario2( );

        int longitud = lista.darLongitud( );

        assertEquals( "La longitud de la lista no es correcta", 0, longitud );

        // Verificar que la longitud de la lista sea correcta
        setupEscenario3( );

        longitud = lista.darLongitud( );

        assertEquals( "La longitud de la lista no es correcta", numeroElementos, longitud );

        // Verificar que la longitud de la lista sea correcta eliminando elementos
        int cont = 0;
        Iterador it = lista.darIterador( );

        while( cont < 20 )
        {
            Integer elemento = ( Integer )it.darSiguiente( );
            lista.eliminar( elemento );

            cont++;
        }

        longitud = lista.darLongitud( );

        assertEquals( "La longitud de la lista no es correcta", numeroElementos - cont, longitud );

    }

    /**
     * Prueba que una lista se vacie efectivamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testVaciar( )
    {
        // Verificar que la lista sea vaciada
        setupEscenario3( );

        lista.vaciar( );

        int longitud = lista.darLongitud( );

        assertEquals( "La longitud de la lista no es correcta", 0, longitud );

        lista.vaciar( );

        assertEquals( "La longitud de la lista no es correcta", 0, longitud );

        lista.agregar( new Integer( 100 ) );

        lista.vaciar( );

        assertEquals( "La longitud de la lista no es correcta", 0, longitud );

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
        for( int i = 0; i < numeroElementos; i++ )
        {
            resp += i + "-";
        }

        assertEquals( "El método toString no funciona correctamente", resp, listaS );
    }
}
