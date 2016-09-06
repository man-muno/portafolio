/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: SospechosoTest.java,v 1.1 2007/04/20 12:38:41 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Mu�oz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.interpol.test;

import junit.framework.TestCase;
import uniandes.cupi2.interpol.mundo.Sospechoso;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Sospechoso est�n correctamente implementados
 */
public class SospechosoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas. Sospechoso ladr�n
     */
    private Sospechoso sospechoso1;

    /**
     * Es la clase donde se har�n las pruebas. Sospechoso que no es ladr�n
     */
    private Sospechoso sospechoso2;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Lugar vac�a
     * 
     */
    private void setupEscenario1( )
    {
        sospechoso1 = new Sospechoso( "Sospechoso1", false );
        sospechoso2 = new Sospechoso( "Sospechoso2", true );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo constructor y los analizadores<br>
     * <b> M�todos a probar: </b> <br>
     * Sospechoso<br>
     * darRutaImagen<br>
     * esLadron<br>
     * <b> Objetivo: </b> Probar que se construye correctamente el objeto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye un sospechoso con la ruta imagen "Sospechoso1" y no es ladr�n<br>
     * 2. Se obtienen los datos del sospechoso creado y concuerdan con los ingresados<br>
     * 3. Se construye un sospechoso con la ruta imagen "Sospechoso2" y no es ladr�n<br>
     * 4. Se obtienen los datos del sospechoso creado y concuerdan con los ingresados<br>
     */
    public void testSospechoso( )
    {
        setupEscenario1( );
        assertNotNull( "La ruta de la imagen del sospechoso no puede ser null", sospechoso1.darRutaImagen( ) );
        assertEquals( "La ruta de la imagen del sospechoso 1 es incorrecta", "Sospechoso1", sospechoso1.darRutaImagen( ) );
        assertFalse( "El sospechoso 1 no es el ladr�n ", sospechoso1.esLadron( ) );

        assertNotNull( "La ruta de la imagen del sospechoso no puede ser null", sospechoso2.darRutaImagen( ) );
        assertEquals( "La ruta de la imagen del sospechoso 2 es incorrecta", "Sospechoso2", sospechoso2.darRutaImagen( ) );
        assertTrue( "El sospechoso 1 no es el ladr�n ", sospechoso2.esLadron( ) );
    }
}
