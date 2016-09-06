/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - 4/02/2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbolRojoNegro.test;



import uniandes.cupi2.collections.arbol.arbolRojoNegro.ArbolRojoNegro;
import uniandes.cupi2.collections.arbol.ElementoExisteException;
import uniandes.cupi2.collections.arbol.ElementoNoExisteException;
import junit.framework.TestCase;

public class ArbolRojoNegroTest extends TestCase
{

    /**
     * Arbol sobre le que se van a hacer las pruebas.
     */
    private ArbolRojoNegro<Integer> arbol = new ArbolRojoNegro<Integer>( );

    /**
     * Objeto encargado de verificar las propiedades del árbol.
     */
    private VerificadorEstructura<Integer> ver;

    /**
     * Crea un árbol vacio.
     */
    private void setupEscenario1( )
    {
        arbol = new ArbolRojoNegro<Integer>( );
        ver = new VerificadorEstructura<Integer>( arbol );
    }

    /**
     * Crea un árbol vacio e ingresa los números 10, 85, 15, 70, 20, 60, 30, 50, 65, 80, 90, 40, 5, 55, 45.
     */
    private void setupEscenario2( )
    {
        arbol = new ArbolRojoNegro<Integer>( );
        ver = new VerificadorEstructura<Integer>( arbol );
        try
        {
            arbol.insertar( 10 );
            arbol.insertar( 85 );
            arbol.insertar( 15 );
            arbol.insertar( 70 );
            arbol.insertar( 20 );
            arbol.insertar( 60 );
            arbol.insertar( 30 );
            arbol.insertar( 50 );
            arbol.insertar( 65 );
            arbol.insertar( 80 );
            arbol.insertar( 90 );
            arbol.insertar( 40 );
            arbol.insertar( 5 );
            arbol.insertar( 55 );
            arbol.insertar( 45 );
        }
        catch( ElementoExisteException e1 )
        {
            // Esto no debería suceder
            fail( );
        }
    }

    /**
     * Crea un árbol rojo-negro con datos desde 0 hasta 15000 ingresados aleatoreamente
     */
    private void setupEscenario3( )
    {
        // Agregar números aleatorios y verificar la estructura en cada paso
        setupEscenario1( );
        try
        {
            for( int i = 0; i < 15000; i++ )
            {
                Integer elem;
                do
                {
                    elem = ( int ) ( Math.random( ) * 15000 );
                } while( arbol.existe( elem ) );
                arbol.insertar( elem );
            }
            ver.verificarEstructura( );
        }
        catch( ElementoExisteException e )
        {
            // Esto no debería suceder
            fail( );
        }
        catch( EstructuraException e )
        {
            e.printStackTrace( );
            fail( e.getMessage( ) );
        }
    }

    /**
     * Pruebas para el método de agregar.
     */
    public void testAgregar( )
    {
        setupEscenario1( );
        try
        {
            arbol.insertar( 10 );
            arbol.insertar( 85 );
            arbol.insertar( 15 );
            arbol.insertar( 70 );
            arbol.insertar( 20 );
            arbol.insertar( 60 );
            arbol.insertar( 30 );
            arbol.insertar( 50 );
            arbol.insertar( 65 );
            arbol.insertar( 80 );
            arbol.insertar( 90 );
            arbol.insertar( 40 );
            arbol.insertar( 5 );
            arbol.insertar( 55 );
            arbol.insertar( 45 );
        }
        catch( ElementoExisteException e1 )
        {
            // Esto no debería suceder
            fail( );
        }

        // Agregar números aleatorios y verificar la estructura en cada paso
        setupEscenario1( );
        try
        {
            for( int i = 0; i < 15000; i++ )
            {
                Integer elem;
                do
                {
                    elem = ( int ) ( Math.random( ) * 15000 );
                } while( arbol.existe( elem ) );
                arbol.insertar( elem );
                assertTrue( "El elemento " + elem + " no fue agregado", arbol.existe( elem ) );
                assertEquals( "El nodo no fue agregado", i + 1, arbol.darPeso( ) );
                ver.verificarEstructura( );
            }
        }
        catch( ElementoExisteException e )
        {
            // Esto no debería suceder
            fail( );
        }
        catch( EstructuraException e )
        {
            e.printStackTrace( );
            fail( e.getMessage( ) );
        }
    }

    /**
     * Pruebas para el método de eliminar.
     */
    public void testEliminar( )
    {
        setupEscenario2( );

        // Eliminar un dato no registrado en el árbol
        try
        {
            arbol.eliminar( 34 );
            fail( "El elemento no estaba presente en el arbol" );
        }
        catch( ElementoNoExisteException e1 )
        {
            // Este es el comportamiento esperado
        }

        try
        {
            // Eliminar la raiz del árbol
            int peso = arbol.darPeso( );
            arbol.eliminar( 30 );
            ver.verificarEstructura( );
            assertFalse( "El elemento no fue eliminado", arbol.existe( 30 ) );
            assertEquals( "El arbol perdió su forma", peso - 1, arbol.darPeso( ) );

            // Hacer una eliminación que implique cambio de raiz
            peso = arbol.darPeso( );
            arbol.eliminar( 5 );
            ;
            ver.verificarEstructura( );
            assertFalse( "El elemento no fue eliminado", arbol.existe( 5 ) );
            assertEquals( "El arbol perdió su forma", peso - 1, arbol.darPeso( ) );
            peso = arbol.darPeso( );
            arbol.eliminar( 10 );
            ver.verificarEstructura( );
            assertFalse( "El elemento no fue eliminado", arbol.existe( 10 ) );
            assertEquals( "El arbol perdió su forma", peso - 1, arbol.darPeso( ) );
        }
        catch( ElementoNoExisteException e )
        {
            // Esto no debería suceder
            fail( );
        }
        catch( EstructuraException e )
        {
            e.printStackTrace( );
            fail( e.getMessage( ) );
        }

        // Eliminar datos aleatorios de un �rbol
        setupEscenario3( );
        try
        {
            for( int i = 0; i < 15000; i++ )
            {
                Integer elem;
                do
                {
                    elem = ( int ) ( Math.random( ) * 15000 );
                } while( !arbol.existe( elem ) );

                arbol.eliminar( elem );

                assertTrue( "El elemento " + elem + " no fue eliminado", !arbol.existe( elem ) );
                assertEquals( "El nodo no fue agregado", 15000 - ( i + 1 ), arbol.darPeso( ) );
                ver.verificarEstructura( );
            }
        }
        catch( ElementoNoExisteException e )
        {
            // Esto no debería suceder
            fail( );
        }
        catch( EstructuraException e )
        {
            e.printStackTrace( );
            fail( e.getMessage( ) );
        }
    }
 
}