/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: SectorTest.java,v 1.4 2007/01/22 07:08:48 f-vela Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Mu�oz - Sep 19, 2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.test;

import java.math.BigInteger;

import junit.framework.TestCase;
import uniandes.cupi2.prision.mundo.Prisionero;
import uniandes.cupi2.prision.mundo.Sector;

/**
 * Clase usada para probar el Sector
 */
public class SectorTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase donde se har�n las pruebas
     */
    private Sector sector;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un sector
     */
    public void setupEscenario1( )
    {
        sector = new Sector( "Sector de Prueba", "Maxima Seguridad" );
        sector.agregarPrisionero( new Prisionero( "Pedro", "Navajas", 6969, "Homicidio", 150, Prisionero.INDEPENDIENTE ) );
    }

    /**
     * Prueba de agregar un prisionero
     */
    public void testAgregarPrisionero( )
    {
        setupEscenario1( );
        assertTrue( "El prisionero deber�a existir en el sector", sector.existePrisionero( 6969 ) );
    }

    /**
     * Prueba la salida de un prisionero
     */
    public void testDarSalida( )
    {
        setupEscenario1( );
        sector.darSalidaPrisionero( 6969 );
        assertFalse( "El prisionero no deber�a existir", sector.existePrisionero( 6969 ) );
    }

    /**
     * Prueba el m�todo que busca un prisionero
     */
    public void testBuscarPrisionero( )
    {
        setupEscenario1( );

        Prisionero prisionero = sector.buscarPrisionero( 6969 );

        assertNotNull( "El prisionero debi� encontrarse", prisionero );
        assertEquals( "El prisionero encontrado no es correcto", 6969, prisionero.darNumero( ) );

        prisionero = sector.buscarPrisionero( 7070 );
        assertNull( "El prisionero no deber�a existir", prisionero );
    }
}
