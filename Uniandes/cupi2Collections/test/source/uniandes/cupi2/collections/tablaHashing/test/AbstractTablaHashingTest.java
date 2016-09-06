/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * $Id: AbstractTablaHashingTest.java,v 1.1 2008/03/28 03:38:05 jua-gome Exp $
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Daniel Romero - Mayo 21, 2006
 * Autor: Juan Erasmo G�mez - Mayo 15, 2008 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.tablaHashing.test;

import uniandes.cupi2.collections.tablaHashing.ITablaHashing;
import uniandes.cupi2.collections.tablaHashing.LlaveInvalidaException;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los m�todos de la clase TablaHashing
 */
public abstract class AbstractTablaHashingTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private ITablaHashing<String, Integer> tabla;

    /**
     * El n�mero de elementos a manejar en cada escenario
     */
    private int numeroElementos;

    // -----------------------------------------------------------------
    // M�todos
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

            assertNull( "la llave no deber�a tener elemento alguno asociado", elemento );
        }

    }

    /**
     * Prueba que el m�todo agregar funcione correctamente
     */
    @SuppressWarnings("unchecked")
    public void testAgregar1( )
    {
        setupEscenario1( );
        numeroElementos = 100;
        for( int cont = 0; cont <= numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.agregar( Integer.toString( cont ), new Integer( cont ) );
            assertNull( "la llave no deber�a tener elemento alguno asociado", elemento );
        }

        // Verificar que el n�mero de elementos sea correcto
        assertEquals( "El n�mero de elementos no es el correcto", numeroElementos + 1, tabla.darNumeroElementos( ) );

        // Verificar que todos los elementos se hayan adicionado de forma correcta
        for( int cont = 0; cont <= numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.dar( Integer.toString( cont ) );
            assertEquals( "La llave no deber�a tener elemento alguno asociado", new Integer( cont ), elemento );
        }
    }

    /**
     * Prueba que el m�todo agregar funcione correctamente cuando se asocia otro elemento a una llave que ya se encuentra asociada
     */
    @SuppressWarnings("unchecked")
    public void testAgregar2( )
    {
        setupEscenario2( );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.agregar( Integer.toString( cont ), new Integer( cont + numeroElementos ) );
            assertEquals( "La inserci�n no se realiz� correctamente", new Integer( cont ), elemento );
        }

        // Verificar que el n�mero de elementos sea correcto
        assertEquals( "La inserci�n no se realiz� correctamente", numeroElementos, tabla.darNumeroElementos( ) );

        // Verificar que la inserci�n se haya realizado de forma correcta
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.dar( Integer.toString( cont ) );
            assertEquals( "La inserci�n no se realiz� correctamente", new Integer( cont + numeroElementos ), elemento );
        }
    }

    /**
     * Prueba que el m�todo agregar arroje excepci�n cuando se intente asociar un elemento con una llave null
     */
    @SuppressWarnings("unchecked")
    public void testAgregar3( )
    {
        setupEscenario2( );

        try
        {
            tabla.agregar( null, new Integer( numeroElementos ) );
            assertTrue( "No se deb�o adicionar ning�n elemento", false );
        }
        catch( LlaveInvalidaException e )
        {
            // Comportamiento esperado
        }
    }

    /**
     * Prueba que las b�squedas de los elementos por llave se est�n realizando correctamente
     */
    @SuppressWarnings("unchecked")
    public void testDar( )
    {
        setupEscenario2( );

        // Verificar que los elementos se consulten correctamente
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.dar( Integer.toString( cont ) );
            assertEquals( "La consulta no se realiz� correctamente", new Integer( cont ), elemento );
        }

        // Verificar que cuando se busque un elemento con llave inexistente se retorne null
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            Integer elemento = ( Integer )tabla.dar( Integer.toString( cont + numeroElementos ) );
            assertNull( "La consulta no se realiz� correctamente", elemento );
        }
    }

    /**
     * Prueba que la eliminaci�n se este realizando correctamente
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
            assertEquals( "La eliminaci�n no se realiz� correctamente", new Integer( cont ), elemento );
        }

        // Verificar que el tama�o de la tabla sea correcta
        assertEquals( "El tama�o de la tabla no es correcto", 0, tabla.darNumeroElementos( ) );
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
            assertNull( "La eliminaci�n no se realiz� correctamente", elemento );
        }

        // Verificar que el tama�o de la tabla sea correcta
        assertEquals( "El tama�o de la tabla no es correcto", 0, tabla.darNumeroElementos( ) );
    }

    /**
     * Prueba que se arroje excepci�n cuando se pasa una llave null para eliminar un elemento
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
