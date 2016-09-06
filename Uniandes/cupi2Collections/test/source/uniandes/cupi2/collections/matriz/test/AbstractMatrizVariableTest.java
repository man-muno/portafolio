/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AbstractMatrizVariableTest.java,v 1.1 2008/04/07 01:37:22 jua-gome Exp $
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
import uniandes.cupi2.collections.matriz.DimensionesInvalidasException;
import uniandes.cupi2.collections.matriz.IMatrizVariable;

/**
 * Pruebas abstractas para los métodos de cualquier matriz variable.
 * @see IMatrizVariable
 */
public abstract class AbstractMatrizVariableTest extends AbstractMatrizTest
{

    /**
     * Pruebas para los metodos aumentarFilas y aumentarColumnas.
     * @see IMatrizVariable#aumentarFilas(int)
     * @see IMatrizVariable#aumentarColumnas(int)
     */
    public void testAumentarDimensiones( )
    {
        setUpEscenario1( );

        IMatrizVariable<String> matrizV = ( IMatrizVariable<String> )matriz;

        assertEquals( "El tamaño de la matriz no es correcto", 2000, matrizV.darNColumnas( ) );
        assertEquals( "El tamaño de la matriz no es correcto", 2000, matrizV.darNFilas( ) );

        try
        {
            matrizV.aumentarColumnas( -1000 );
            matrizV.aumentarFilas( -1000 );
            assertEquals( "El tamaño de la matriz no es correcto", 1000, matrizV.darNColumnas( ) );
            matrizV.cambiarElemento( 900, 900, "Elemento 900 900" );
            matrizV.cambiarElemento( 800, 900, "Elemento 800 900" );
            matrizV.cambiarElemento( 900, 800, "Elemento 900 800" );
            matrizV.cambiarElemento( 800, 800, "Elemento 800 800" );
            
            assertEquals( "No se encuentran los elementos insertados", "Elemento 900 900", matrizV.darElemento( 900, 900 ) );
            assertEquals( "No se encuentran los elementos insertados", "Elemento 800 900", matrizV.darElemento( 800, 900 ) );
            assertEquals( "No se encuentran los elementos insertados", "Elemento 900 800", matrizV.darElemento( 900, 800 ) );
            assertEquals( "No se encuentran los elementos insertados", "Elemento 800 800", matrizV.darElemento( 800, 800 ) );
            
            matrizV.aumentarColumnas( -500 );
            assertEquals( "El tamaño de la matriz no es correcto", 500, matrizV.darNColumnas( ) );
            matrizV.aumentarColumnas( 500 );            
            assertEquals( "El tamaño de la matriz no es correcto", 1000, matrizV.darNColumnas( ) );
            assertNull("Los elemento de columnas eliminadas deben ser eliminados", matrizV.darElemento( 900, 900 ));
            assertNull("Los elemento de columnas eliminadas deben ser eliminados", matrizV.darElemento( 800, 900 ));
            assertNull("Los elemento de columnas eliminadas deben ser eliminados", matrizV.darElemento( 900, 800 ));
            assertNull("Los elemento de columnas eliminadas deben ser eliminados", matrizV.darElemento( 800, 800 ));
            
            matrizV.cambiarElemento( 900, 900, "Elemento 900 900" );
            matrizV.cambiarElemento( 800, 900, "Elemento 800 900" );
            matrizV.cambiarElemento( 900, 800, "Elemento 900 800" );
            matrizV.cambiarElemento( 800, 800, "Elemento 800 800" );
            
            matrizV.aumentarFilas( -500 );
            assertEquals( "El tamaño de la matriz no es correcto", 500, matrizV.darNFilas( ) );
            matrizV.aumentarFilas( 500 );            
            assertEquals( "El tamaño de la matriz no es correcto", 1000, matrizV.darNFilas( ) );
            assertNull("Los elemento de columnas eliminadas deben ser eliminados", matrizV.darElemento( 900, 900 ));
            assertNull("Los elemento de columnas eliminadas deben ser eliminados", matrizV.darElemento( 800, 900 ));
            assertNull("Los elemento de columnas eliminadas deben ser eliminados", matrizV.darElemento( 900, 800 ));
            assertNull("Los elemento de columnas eliminadas deben ser eliminados", matrizV.darElemento( 800, 800 ));
        }
        catch( DimensionesInvalidasException e )
        {
            fail(e.getMessage( ));
        }
        
        try
        {
            matrizV.aumentarColumnas( -1000 );
            fail("La matriz no puede ser de dimensión 0");
        }
        catch( DimensionesInvalidasException e )
        {
            // Comportamiento esperado
        }
        
        try
        {
            matrizV.aumentarFilas( -1000 );
            fail("La matriz no puede ser de dimensión 0");
        }
        catch( DimensionesInvalidasException e )
        {
            // Comportamiento esperado
        }

        try {
            matrizV.cambiarElemento( 1000, 1000, "Elemento 1000 1000" );
            fail("La posición 1000 1000 no existe");
        } catch (CoordenadaFueraDeRangoException e) {
            // Comportamiento esperado
        }
    }
}
