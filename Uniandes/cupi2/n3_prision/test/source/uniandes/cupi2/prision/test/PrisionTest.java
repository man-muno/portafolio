/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PrisionTest.java,v 1.4 2007/01/22 07:08:48 f-vela Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Mu�oz - 04-Sep-2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.prision.mundo.Prision;
import uniandes.cupi2.prision.mundo.Prisionero;
import uniandes.cupi2.prision.mundo.Sector;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Prision est�n correctamente implementados
 */
public class PrisionTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Prision prision;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva prisi�n vac�a
     * 
     */
    private void setupEscenario1( )
    {
        prision = new Prision( );

    }

    /**
     * Construye una prisi�n y le adiciona un prisionero
     * 
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );
        prision.ingresarNuevoPrisionero( "Pedro", "Navajas", 6969, "Homicidio", 150, Prisionero.INDEPENDIENTE, "Sector A" );
    }

    /**
     * Prueba el constructor de prisi�n
     */
    public void testPrision( )
    {
        setupEscenario1( );
        // C�digo de la prueba
        Sector[] sectores = prision.darSectores( );
        assertNotNull( "El arreglo de sectores no deber�a ser nulo", sectores );
        assertNotNull( "El primer sector no deber�a ser nulo", sectores[ 0 ] );
        assertNotNull( "El segundo sector no deber�a ser nulo", sectores[ 1 ] );
        assertNotNull( "El tercer sector no deber�a ser nulo", sectores[ 2 ] );
        assertNotNull( "El cuarto sector no deber�a ser nulo", sectores[ 3 ] );
        assertNotNull( "El quinto sector no deber�a ser nulo", sectores[ 4 ] );
    }

    /**
     * Prueba el m�todo que se encarga de ingresar un prisionero
     */
    public void testIngresar( )
    {
        setupEscenario1( );
        prision.ingresarNuevoPrisionero( "Pedro", "Navajas", 6969, "Homicidio", 150, Prisionero.INDEPENDIENTE, "Sector A" );
        assertNotNull( "Debe existir un prisionero identificado con el n�mero 6969", prision.buscarPrisionero( 6969 ) );
    }

    /**
     * Prueba el m�todo que le da la salida a un prisionero
     */
    public void testDarSalida( )
    {
        setupEscenario2( );
        assertTrue( "El prisionero debi� haber salido sin problema", prision.darSalidaPrisionero( 6969 ) );

        assertFalse( "No se debi� dar salida al prisionero", prision.darSalidaPrisionero( 6969 ) );
    }

    /**
     * Prueba del m�todo de buscar prisionero
     */
    public void testExistePrisionero( )
    {
        setupEscenario2( );
        assertNotNull( "El prisionero identificado con el 6969 existe", prision.buscarPrisionero( 6969 ) );
        assertNull( "El prisionero identificado con el 7070 no existe", prision.buscarPrisionero( 7070 ) );
    }

    /**
     * Prueba el m�todo que se encarga de reubicar prisionero
     */
    public void testReubicarPrisionero( )
    {
        setupEscenario2( );
        assertTrue( "El prisionero debi� ser reubicado", prision.reubicarPrisionero( 6969, "Sector B" ) );
        Sector sector = prision.buscarSectorPrisionero( 6969 );
        assertEquals( "No se encontr� el prisionero en el sector esperado", "Sector B", sector.darNombre( ) );

        assertFalse( "El prisionero no debi� ser reubicado", prision.reubicarPrisionero( 6969, "Sector B" ) );
    }

    /**
     * Prueba el m�todo que se encarga de buscar un prisionero
     */
    public void testBuscarPrisionero( )
    {
        setupEscenario2( );

        assertNotNull( "El prisionero debi� encontrarse", prision.buscarPrisionero( 6969 ) );

        assertNull( "El prisionero no debi� encontrarse", prision.buscarPrisionero( 7070 ) );
    }

    /**
     * Prueba el m�todo que se encarga de buscar un sector
     */
    public void testBuscarSector( )
    {
        setupEscenario2( );

        Sector sector = prision.buscarSector( "Sector D" );
        assertNotNull( "El sector debi� encontrarse", sector );
        assertEquals( "El sector encontrado no es el correcto", "Sector D", sector.darNombre( ) );

        sector = prision.buscarSector( "Alg�n sector" );
        assertNull( "El sector no debi� encontrarse", prision.buscarSector( "Alg�n sector" ) );
    }

    /**
     * Prueba el m�todo que se encarga de buscar el sector de un prisionero
     */
    public void testBuscarSectorPrisionero( )
    {
        setupEscenario2( );

        Sector sector = prision.buscarSectorPrisionero( 6969 );
        assertNotNull( "El sector debi� encontrarse", sector );
        assertEquals( "El sector encontrado no es el correcto", "Sector A", sector.darNombre( ) );

        sector = prision.buscarSectorPrisionero( 7070 );
        assertNull( "El sector no debi� encontrarse", sector );
    }

    /**
     * Prueba el m�todo que retorna los prisioneros que hay en un sector
     */
    public void testDarPrisionerosSector( )
    {
        setupEscenario2( );

        ArrayList prisioneros = prision.darPrisionerosSector( "Sector A" );

        assertNotNull( "El sector si existe", prisioneros );
        assertEquals( "El de prisioneros no es el correcto", 1, prisioneros.size( ) );

        prisioneros = prision.darPrisionerosSector( "Sector B" );

        assertNotNull( "El sector si existe", prisioneros );
        assertEquals( "El de prisioneros no es el correcto", 0, prisioneros.size( ) );

        prisioneros = prision.darPrisionerosSector( "Sector C" );

        assertNotNull( "El sector si existe", prisioneros );
        assertEquals( "El de prisioneros no es el correcto", 0, prisioneros.size( ) );

        prisioneros = prision.darPrisionerosSector( "Sector D" );

        assertNotNull( "El sector si existe", prisioneros );
        assertEquals( "El de prisioneros no es el correcto", 0, prisioneros.size( ) );

        prisioneros = prision.darPrisionerosSector( "Sector E" );

        assertNotNull( "El sector si existe", prisioneros );
        assertEquals( "El de prisioneros no es el correcto", 0, prisioneros.size( ) );

        prisioneros = prision.darPrisionerosSector( "Sector F" );

        assertNull( "El sector no existe", prisioneros );
    }

    /**
     * Prueba el m�todo que se encarga de verificar si existe un sector
     */
    public void testExisteSector( )
    {
        setupEscenario2( );

        assertTrue( "El sector si existe", prision.existeSector( "Sector A" ) );

        assertTrue( "El sector si existe", prision.existeSector( "Sector B" ) );

        assertTrue( "El sector si existe", prision.existeSector( "Sector C" ) );

        assertTrue( "El sector si existe", prision.existeSector( "Sector D" ) );

        assertTrue( "El sector si existe", prision.existeSector( "Sector E" ) );

        assertFalse( "El sector no existe", prision.existeSector( "Sector F" ) );

    }

}