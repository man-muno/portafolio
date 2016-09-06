/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * $Id: AbstractTablaHashingTest.java,v 1.1 2008/03/28 03:38:05 jua-gome Exp $
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Daniel Romero - Mayo 21, 2006
 * Autor: Juan Erasmo Gómez - Mayo 15, 2008 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.tablaHashing.test;

import uniandes.cupi2.collections.tablaHashing.ITablaHashing;
import uniandes.cupi2.collections.tablaHashing.LlaveInvalidaException;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase TablaHashing
 */
public abstract class AbstractTablaHashingTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private ITablaHashing<String, Integer> tabla;

    /**
     * El número de elementos a manejar en cada escenario
     */
    private int numeroElementos;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea una instancia concreta de la tabla que se quiera probar.
     */
    public abstract ITablaHashing<String, Integer> crearTabla( );

    /**
     * Construye una tabla vacia de con elementos de tipo Integer y llaves de tipo String
     */
    public void setupEscenario1( )
    {
        tabla = crearTabla( );
    }

    /**
     * Construye una tabla con 10000 elementos de tipo Integer y llaves de tipo String
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        tabla = crearTabla( );

        numeroElementos = 10000;
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.agregar( Integer.toString( cont ), new Integer( cont ) );

            assertNull( "la llave no debería tener elemento alguno asociado", elemento );
        }

    }

    /**
     * Prueba que el método agregar funcione correctamente
     */
    @SuppressWarnings("unchecked")
    public void testAgregar1( )
    {
        setupEscenario1( );
        numeroElementos = 100;
        for( int cont = 0; cont <= numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.agregar( Integer.toString( cont ), new Integer( cont ) );
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
            // Comportamiento esperado
        }
    }

    /**
     * Prueba que las búsquedas de los elementos por llave se estén realizando correctamente
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
            // Comportamiento esperado
        }
    }

}
