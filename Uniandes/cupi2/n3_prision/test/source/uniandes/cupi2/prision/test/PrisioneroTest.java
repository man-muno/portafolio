/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PrisioneroTest.java,v 1.4 2007/01/22 07:08:48 f-vela Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Mu�oz - Aug 21, 2006
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
     * Objeto donde se har�n las pruebas
     */
    private Prisionero prisionero;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un prisionero
     */
    public void setupEscenario1( )
    {
        prisionero = new Prisionero( "Charles", "Manson", 33920, "Homicidio", 1220, Prisionero.NINGUNO );
    }

    /**
     * Prueba el m�todo darApellido
     */
    public void testDarApellido( )
    {
        setupEscenario1( );
        assertEquals( "El apellido no coincide", "Manson", prisionero.darApellido( ) );
    }

    /**
     * Prueba el m�todo darCrimenCometido
     */
    public void testDarCrimenCometido( )
    {
        setupEscenario1( );
        assertEquals( "El crimen no coincide", "Homicidio", prisionero.darCrimenCometido( ) );
    }

    /**
     * Prueba el m�todo darGrupoCriminal
     */
    public void testDarGrupoCriminal( )
    {
        setupEscenario1( );
        assertEquals( "El grupo criminal no coincide", Prisionero.NINGUNO, prisionero.darGrupoCriminal( ) );
    }

    /**
     * Prueba el m�todo darTiempoSentencia
     */
    public void testDarTiempoSentencia( )
    {
        setupEscenario1( );
        assertEquals( "La sentencia no coincide", 1220, prisionero.darTiempoSentencia( ) );
    }

    /**
     * Prueba el m�todo darNombre
     */
    public void testDarNombrePrisionero( )
    {
        setupEscenario1( );
        assertEquals( "El nombre no coincide", "Charles", prisionero.darNombre( ) );
    }

    /**
     * Prueba el m�todo darNumero
     */
    public void testDarNumeroPrisionero( )
    {
        setupEscenario1( );
        assertEquals( "El n�mero del prisionero no coincide", 33920, prisionero.darNumero( ) );
    }
}
