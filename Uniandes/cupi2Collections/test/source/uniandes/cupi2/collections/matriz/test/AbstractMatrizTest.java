/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AbstractMatrizTest.java,v 1.1 2008/04/07 01:37:22 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Abr 1, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.matriz.test;

import uniandes.cupi2.collections.matriz.CoordenadaFueraDeRangoException;
import uniandes.cupi2.collections.matriz.IMatriz;
import junit.framework.TestCase;

/**
 * Pruebas abstractas para una matriz.
 * @see IMatriz
 */
public abstract class AbstractMatrizTest extends TestCase
{

    /**
     * Matriz sobre la que se van a hacer las pruebas.
     */
    protected IMatriz<String> matriz;

    
    /**
     * Método abstracto que instancia una matriz de tamaño 2000*2000 sin ningún elemento.
     * @return Una instancia de matriz.
     */
    public abstract IMatriz<String> instanciarMatriz( );

    /**
     * Crea una matriz de tamaño 2000*2000 sin ningún elemento
     */
    public void setUpEscenario1( )
    {
        matriz = instanciarMatriz( );
    }

    /**
     * Pruebas para el método <code>cambiarElemento</code>.
     * @see IMatriz#cambiarElemento(int, int, Object)
     */
    public void testCambiar1( )
    {
        setUpEscenario1( );

        matriz.cambiarElemento( 0, 0, "Elemento 0 0" );
        matriz.cambiarElemento( 10, 20, "Elemento 10 20" );
        matriz.cambiarElemento( 0, 21, "Elemento 0 21" );
        matriz.cambiarElemento( 11, 0, "Elemento 11 0" );
        matriz.cambiarElemento( 5, 5, "Elemento 5 5" );

        assertEquals( "No se encuentran los elementos insertados", "Elemento 0 0", matriz.darElemento( 0, 0 ) );
        assertEquals( "No se encuentran los elementos insertados", "Elemento 0 21", matriz.darElemento( 0, 21 ) );
        assertEquals( "No se encuentran los elementos insertados", "Elemento 11 0", matriz.darElemento( 11, 0 ) );
        assertEquals( "No se encuentran los elementos insertados", "Elemento 5 5", matriz.darElemento( 5, 5 ) );

        assertNull( "No debe encontrar elemento no insertados", matriz.darElemento( 0, 1 ) );
        assertNull( "No debe encontrar elemento no insertados", matriz.darElemento( 0, 22 ) );
        assertNull( "No debe encontrar elemento no insertados", matriz.darElemento( 11, 1 ) );
        assertNull( "No debe encontrar elemento no insertados", matriz.darElemento( 5, 6 ) );

        try
        {
            matriz.darElemento( -1, 0 );
            fail( "La matriz no debe recibir coordenadas negativas" );
        }
        catch( CoordenadaFueraDeRangoException e )
        {
            // Comportamiento esperado
        }
        try
        {
            matriz.darElemento( 0, -1 );
            fail( "La matriz no debe recibir coordenadas negativas" );
        }
        catch( CoordenadaFueraDeRangoException e )
        {
            // Comportamiento esperado
        }
        try
        {
            matriz.darElemento( 50000, 0 );
            fail( "La matriz no debe recibir coordenadas superiores a sus dimensiones" );
        }
        catch( CoordenadaFueraDeRangoException e )
        {
            // Comportamiento esperado
        }
        try
        {
            matriz.darElemento( 0, 50000 );
            fail( "La matriz no debe recibir coordenadas superiores a sus dimensiones" );
        }
        catch( CoordenadaFueraDeRangoException e )
        {
            // Comportamiento esperado
        }
    }

    /**
     * Pruebas para el método <code>cambiarElemento</code>.
     * @see IMatriz#cambiarElemento(int, int, Object)
     */
    public void testCambiar2( )
    {
        setUpEscenario1( );

        for( int f = 0; f < matriz.darNFilas( ); f += 7 )
        {
            for( int c = 0; c < matriz.darNColumnas( ); c += 3 )
            {
                matriz.cambiarElemento( f, c, "Elemento " + f + " " + c );
                assertEquals( "No se encuentran los elementos insertados", "Elemento " + f + " " + c, matriz.darElemento( f, c ) );
            }
        }
        
        for( int f = 0; f < matriz.darNFilas( ); f += 7 )
        {
            for( int c = 0; c < matriz.darNColumnas( ); c += 3 )
            {
                matriz.cambiarElemento( f, c, null );
                assertNull( "Los elementos no se están eliminando de forma correcta", matriz.darElemento( f, c ) );
            }
        }
    }

}
