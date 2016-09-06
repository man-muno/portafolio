/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PrisioneroTest.java,v 1.4 2007/01/22 07:08:48 f-vela Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Muñoz - Aug 21, 2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.test;

import junit.framework.TestCase;
import uniandes.cupi2.prision.mundo.Prisionero;

/**
 * Prueba de la clase Prisionero
 */
public class PrisioneroTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Objeto donde se harán las pruebas
     */
    private Prisionero prisionero;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un prisionero
     */
    public void setupEscenario1( )
    {
        prisionero = new Prisionero( "Charles", "Manson", 33920, "Homicidio", 1220, Prisionero.NINGUNO );
    }

    /**
     * Prueba el método darApellido
     */
    public void testDarApellido( )
    {
        setupEscenario1( );
        assertEquals( "El apellido no coincide", "Manson", prisionero.darApellido( ) );
    }

    /**
     * Prueba el método darCrimenCometido
     */
    public void testDarCrimenCometido( )
    {
        setupEscenario1( );
        assertEquals( "El crimen no coincide", "Homicidio", prisionero.darCrimenCometido( ) );
    }

    /**
     * Prueba el método darGrupoCriminal
     */
    public void testDarGrupoCriminal( )
    {
        setupEscenario1( );
        assertEquals( "El grupo criminal no coincide", Prisionero.NINGUNO, prisionero.darGrupoCriminal( ) );
    }

    /**
     * Prueba el método darTiempoSentencia
     */
    public void testDarTiempoSentencia( )
    {
        setupEscenario1( );
        assertEquals( "La sentencia no coincide", 1220, prisionero.darTiempoSentencia( ) );
    }

    /**
     * Prueba el método darNombre
     */
    public void testDarNombrePrisionero( )
    {
        setupEscenario1( );
        assertEquals( "El nombre no coincide", "Charles", prisionero.darNombre( ) );
    }

    /**
     * Prueba el método darNumero
     */
    public void testDarNumeroPrisionero( )
    {
        setupEscenario1( );
        assertEquals( "El número del prisionero no coincide", 33920, prisionero.darNumero( ) );
    }
}
