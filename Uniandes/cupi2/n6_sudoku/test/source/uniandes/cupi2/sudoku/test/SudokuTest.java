/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: SudokuTest.java,v 1.8 2006/11/19 21:50:00 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Manuel Muñoz - 07-Sep-2006
 * Autor: Daniel Romero - 17-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sudoku.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;
import uniandes.cupi2.sudoku.mundo.Casilla;
import uniandes.cupi2.sudoku.mundo.Sudoku;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Sudoku estén correctamente implementados
 */
public class SudokuTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Sudoku sudoku;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nuevo juego vacío
     */
    private void setupEscenario1( )
    {
        sudoku = new Sudoku( );
    }

    /**
     * Construye una nueva clase Sudoku vacía y con un tablero válido
     */
    private void setupEscenario2( )
    {
        sudoku = new Sudoku( );
        try
        {
            Properties propiedades = new Properties( );
            propiedades.load( new FileInputStream( new File( "./test/data/sudoku.properties" ) ) );
            sudoku.cargarTablero( propiedades );
        }
        catch( Exception e )
        {
            fail( "No se debería arrojar esta excepción" );
        }
    }

    /**
     * Prueba de cargarTablero
     */
    public void testCargarTablero( )
    {
        setupEscenario1( );
        Properties propiedades = new Properties( );
        try
        {
            propiedades.load( new FileInputStream( new File( "./test/data/sudoku.properties" ) ) );
            sudoku.cargarTablero( propiedades );

            // Se verifica que en cada fila esten los nueve número
            boolean[] numeros = new boolean[Sudoku.NUMERO_FILAS];

            Casilla[][] tablero = sudoku.darTablero( );

            for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
            {
                for( int k = 0; k < Sudoku.NUMERO_FILAS; k++ )
                {
                    numeros[ k ] = false;
                }

                for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
                {
                    numeros[ tablero[ i ][ j ].darValorReal( ) - 1 ] = true;

                    // Se verifica que el valor ingresado sea cero
                    assertEquals( "El valor ingresado debería ser cero", 0, tablero[ i ][ j ].darValorIngresado( ) );
                }

                for( int k = 0; k < Sudoku.NUMERO_FILAS; k++ )
                {
                    assertTrue( "El número debió encontrarse", numeros[ k ] );
                }
            }
        }
        catch( Exception e )
        {
            fail( "No se debió arrojar excepción" );
        }
    }

    /**
     * Prueba el método iniciarJuego,terminarJuego y juegoActivo de la clase Sudoku
     */
    public void testIniciarJuego( ) throws FileNotFoundException, IOException
    {
        setupEscenario2( );

        // Verifica que se hayan marcado las casillas del juego correctamente y que su estado sea activo
        sudoku.iniciarJuego( );
        assertTrue( "El juego debió comenzar", sudoku.juegoActivo( ) );
        Casilla[][] tablero = sudoku.darTablero( );

        int marcadas = 0;

        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                if( tablero[ i ][ j ].esInicial( ) )
                {
                    marcadas++;
                }
            }
        }
        assertEquals( "El juego no se inició correctamente", Sudoku.CANTIDAD_CASILLAS_INICIALES * Sudoku.CANTIDAD_ZONAS, marcadas );

        // Verifica que cuando el juego termine, su estado se haya cambiado
        sudoku.terminarJuego( );
        assertFalse( "El juego debería estar terminado", sudoku.juegoActivo( ) );
    }

    /**
     * Prueba el método validarTablero
     */
    public void testValidarTablero( )
    {
        setupEscenario2( );
        Casilla[][] casillas = sudoku.darTablero( );
        int[][] valores = new int[Sudoku.NUMERO_FILAS][Sudoku.NUMERO_COLUMNAS];
        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                valores[ i ][ j ] = casillas[ i ][ j ].darValorReal( );
            }
        }
        assertTrue( "El juego debería ser válido", sudoku.validarTablero( valores ) );

        valores[ Sudoku.NUMERO_FILAS - 1 ][ Sudoku.NUMERO_COLUMNAS - 1 ] = 7;

        assertFalse( "El juego no debería ser válido", sudoku.validarTablero( valores ) );

        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                valores[ i ][ j ] = 3;
            }
        }

        assertFalse( "El juego no debería ser válido", sudoku.validarTablero( valores ) );
    }

    /**
     * Prueba del método desmarcarCasillas
     */
    public void testDesmarcarCasillas( )
    {
        setupEscenario2( );
        sudoku.iniciarJuego( );

        int[][] valores = new int[Sudoku.NUMERO_FILAS][Sudoku.NUMERO_COLUMNAS];
        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                valores[ i ][ j ] = 1;
            }
        }

        assertFalse( "El juego no debería ser válido", sudoku.validarTablero( valores ) );
        sudoku.desmarcarCasillas( );
        Casilla[][] casillas = sudoku.darTablero( );

        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                assertFalse( "La casilla no debería estar marcada", casillas[ i ][ j ].estaMarcada( ) );
            }
        }
    }

    /**
     * Prueba del método limpiar
     */
    public void testLimpiar( ) throws FileNotFoundException, IOException
    {
        setupEscenario2( );
        sudoku.limpiar( );

        Casilla[][] casillas = sudoku.darTablero( );

        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                assertNull( "La debiera ser nula", casillas[ i ][ j ] );
            }
        }
    }
}