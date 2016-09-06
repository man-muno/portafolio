package uniandes.cupi2.collections.tablaHashing.test;

import uniandes.cupi2.collections.tablaHashing.LlaveInvalidaException;
import uniandes.cupi2.collections.tablaHashing.TablaHashing;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase TablaHashing
 */
public class TablaHashingTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private TablaHashing tabla;

    /**
     * El número de elementos a manejar en cada escenario
     */
    private int numeroElementos;

    /**
     * Objeto para verificar el invariante de la estructura
     */
    private VerificardorEstructura verificador;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una tabla vacia de con elementos de tipo Integer y llaves de tipo String
     */
    public void setupEscenario1( )
    {
        tabla = new TablaHashing<String, Integer>( );
        verificador = new VerificardorEstructura<String, Integer>( );

    }

    /**
     * Construye una tabla con 1000 elementos de tipo Integer y llaves de tipo String
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        tabla = new TablaHashing<String, Integer>( );
        verificador = new VerificardorEstructura<String, Integer>( );

        numeroElementos = 1000;
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.agregar( Integer.toString( cont ), new Integer( cont ) );

            assertNull( "la llave no debería tener elemento alguno asociado", elemento );
        }

    }

    /**
     * Verifica que la estructura en la tabla sea correcta
     * 
     */
    @SuppressWarnings("unchecked")
    private void verificarInvariante( )
    {
        boolean estructuraBien = ( verificador.verificarTablaHashing( tabla ) );

        assertTrue( "La estructura de la tabla no es correcta", estructuraBien );
    }

    /**
     * Prueba que el método agregar funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAgregar1( )
    {
        setupEscenario1( );
        numeroElementos = 100;
        for( int cont = 0; cont <= numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.agregar( Integer.toString( cont ), new Integer( cont ) );
            verificarInvariante( );

            assertNull( "la llave no debería tener elemento alguno asociado", elemento );
        }

        // Verificar que el número de elementos sea correcto
        assertEquals( "El número de elementos no es el correcto", numeroElementos + 1, tabla.darNumeroElementos( ) );

        // Verificar que todos los elementos se hayan adicionado de forma correcta
        for( int cont = 0; cont <= numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.dar( Integer.toString( cont ) );

            assertEquals( "La llave no debería tener elemento alguno asociado", new Integer( cont ), elemento );
        }
    }

    /**
     * Prueba que el método agregar funcione correctamente cuando se asocia otro elemento a una llave que ya se encuentra asociada
     */
    @SuppressWarnings("unchecked")
    public void testAgregar2( )
    {
        setupEscenario2( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.agregar( Integer.toString( cont ), new Integer( cont + numeroElementos ) );
            verificarInvariante( );

            assertEquals( "La inserción no se realizó correctamente", new Integer( cont ), elemento );
        }

        // Verificar que el número de elementos sea correcto
        assertEquals( "La inserción no se realizó correctamente", numeroElementos, tabla.darNumeroElementos( ) );

        // Verificar que la inserción se haya realizado de forma correcta
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.dar( Integer.toString( cont ) );

            assertEquals( "La inserción no se realizó correctamente", new Integer( cont + numeroElementos ), elemento );
        }
    }

    /**
     * Prueba que el método agregar arroje excepción cuando se intente asociar un elemento con una llave null
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAgregar3( )
    {
        setupEscenario2( );

        try
        {
            tabla.agregar( null, new Integer( numeroElementos ) );
            assertTrue( "No se debío adicionar ningún elemento", false );
        }
        catch( LlaveInvalidaException e )
        {
            verificarInvariante( );
        }
    }

    /**
     * Prueba que las búsquedas de los elementos por llave se estén realizando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDar( )
    {
        setupEscenario2( );

        // Verificar que los elementos se consulten correctamente
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.dar( Integer.toString( cont ) );

            assertEquals( "La consulta no se realizó correctamente", new Integer( cont ), elemento );
        }

        // Verificar que cuando se busque un elemento con llave inexistente se retorne null
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.dar( Integer.toString( cont + numeroElementos ) );

            assertNull( "La consulta no se realizó correctamente", elemento );
        }
    }

    /**
     * Prueba que la eliminación se este realizando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar1( )
    {
        setupEscenario2( );

        // Elimina todos los elementos de la tabla
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.eliminar( Integer.toString( cont ) );

            verificarInvariante( );

            assertEquals( "La eliminación no se realizó correctamente", new Integer( cont ), elemento );
        }

        // Verificar que el tamaño de la tabla sea correcta
        assertEquals( "El tamaño de la tabla no es correcto", 0, tabla.darNumeroElementos( ) );
    }

    /**
     * Prueba que se retorne null cuando se trata de eliminar un elemento que no existe
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar2( )
    {
        setupEscenario1( );

        // Elimina todos los elementos de la tabla
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.eliminar( Integer.toString( cont ) );

            verificarInvariante( );

            assertNull( "La eliminación no se realizó correctamente", elemento );
        }

        // Verificar que el tamaño de la tabla sea correcta
        assertEquals( "El tamaño de la tabla no es correcto", 0, tabla.darNumeroElementos( ) );
    }

    /**
     * Prueba que se arroje excepción cuando se pasa una llave null para eliminar un elemento
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEliminar3( )
    {
        setupEscenario1( );

        try
        {
            tabla.eliminar( null );
            assertTrue( "No se debio eliminar nada", false );
        }
        catch( LlaveInvalidaException e )
        {
            verificarInvariante( );
        }
    }

}
