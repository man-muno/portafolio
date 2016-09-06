/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CasillaTest.java,v 1.4 2006/11/19 21:21:13 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Manuel Muñoz - Sep 28, 2006
 * Autor: Daniel Romero - 17-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sudoku.test;

import uniandes.cupi2.sudoku.mundo.Casilla;
import junit.framework.TestCase;

/**
 * Pruebas unitarias para la clase Casilla
 */
public class CasillaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Clase a la que se le harán las pruebas
     */
    private Casilla casilla;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva casilla con el valor 8
     */
    private void setupTest1( )
    {
        casilla = new Casilla( 8 );
    }

    /**
     * Prueba el método esInicial y volverInicial de la clase Casilla
     */
    public void testEsInicial( )
    {
        setupTest1( );
        assertFalse( "La casilla no es inicial", casilla.esInicial( ) );
        casilla.volverIncial( );
        assertTrue( "La casilla es inicial", casilla.esInicial( ) );
    }

    /**
     * Prueba el método darValorReal
     */
    public void testDarValorReal( )
    {
        setupTest1( );
        assertEquals( "El valor real de la casilla es 8", 8, casilla.darValorReal( ) );
    }

    /**
     * Prueba los métodos estaMarcada, marcar y desmarcar de la clase Casilla
     */
    public void testEstaMarcada( )
    {
        setupTest1( );
        assertFalse( "La casilla no esta marcada", casilla.estaMarcada( ) );
        casilla.marcar( );
        assertTrue( "La casilla esta marcada", casilla.estaMarcada( ) );
        casilla.desmarcar( );
        assertFalse( "La casilla no esta marcada", casilla.estaMarcada( ) );
    }

    /**
     * Prueba el método darValorIngresado y cambiarValorIngresado de la clase Casilla
     */
    public void testCambiarValorIngresado( )
    {
        setupTest1( );
        assertEquals( "El valor real inicial es 0", 0, casilla.darValorIngresado( ) );
        casilla.cambiarValorIngresado( 2 );
        assertEquals( "El valor real es 2", 2, casilla.darValorIngresado( ) );
    }
}
