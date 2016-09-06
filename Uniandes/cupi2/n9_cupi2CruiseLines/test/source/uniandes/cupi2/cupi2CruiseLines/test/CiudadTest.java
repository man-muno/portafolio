/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CiudadTest.java,v 1.1 2007/04/07 23:39:18 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupi2CruiseLines
 * Autor: Manuel Mu�oz - 13-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupi2CruiseLines.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupi2CruiseLines.mundo.Ciudad;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Ciudad est�n correctamente implementados
 */
public class CiudadTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Ciudad ciudad;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Ciudad vac�a
     */
    private void setupEscenario1( )
    {
        ciudad = new Ciudad( "New York", "USA", 0.1, 0.1 );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo cambiarAnterior<br>
     * <b> M�todos a probar: </b> <br>
     * cambiarAnterior<br>
     * darAnterior<br>
     * <b> Objetivo: </b> Probar que se cambie correctamente la ciudad anterior<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se cambia la anterior ciudad a la actual<br>
     */
    public void testCambiarAnterior( )
    {
        setupEscenario1( );
        Ciudad ciudadAnterior1 = ciudad.darAnterior( );
        assertNull( "La ciudad anterior deber�a ser null", ciudadAnterior1 );
        Ciudad ciudadAnterior2 = new Ciudad( "Londres", "Inglaterra", 0.2, 0.2 );
        ciudad.cambiarAnterior( ciudadAnterior2 );
        assertEquals( "No se cambio correctamente la ciudad anterior", ciudadAnterior2, ciudad.darAnterior( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo cambiarSiguiente<br>
     * <b> M�todos a probar: </b> <br>
     * cambiarSiguiente<br>
     * darSiguiente<br>
     * <b> Objetivo: </b> Probar que se cambie correctamente la ciudad siguiente<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se cambia la siguiente ciudad a la actual<br>
     */
    public void testCambiarSiguiente( )
    {
        setupEscenario1( );
        Ciudad ciudadSiguiente1 = ciudad.darSiguiente( );
        assertNull( "La ciudad siguiente deber�a ser null", ciudadSiguiente1 );
        Ciudad ciudadSiguiente2 = new Ciudad( "Londres", "Inglaterra", 0.2, 0.2 );
        ciudad.cambiarSiguiente( ciudadSiguiente2 );
        assertEquals( "No se cambio correctamente la ciudad siguiente", ciudadSiguiente2, ciudad.darSiguiente( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darAnterior<br>
     * <b> M�todos a probar: </b> <br>
     * darSiguiente<br>
     * cambiarAnterior<br>
     * <b> Objetivo: </b> Probar que se retorna correctamente la ciudad anterior a la actual<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene la ciudad anterior al actual<br>
     */
    public void testDarAnterior( )
    {
        setupEscenario1( );
        Ciudad ciudadAnterior1 = ciudad.darAnterior( );
        assertNull( "La ciudad anterior deber�a ser null", ciudadAnterior1 );
        Ciudad ciudadAnterior2 = new Ciudad( "Londres", "Inglaterra", 0.2, 0.2 );
        ciudad.cambiarAnterior( ciudadAnterior2 );
        assertEquals( "No se cambio correctamente la ciudad anterior", ciudadAnterior2, ciudad.darAnterior( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darCoordenadaX<br>
     * <b> M�todos a probar: </b> <br>
     * darCoordenadaX<br>
     * <b> Objetivo: </b> Probar que se retorna correctamente la coordenada X de la ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene la coordenada X de la ciudad<br>
     */
    public void testDarCoordenadaX( )
    {
        setupEscenario1( );
        assertTrue( "La coordenada X fue mal inicializada", 0.1 == ciudad.darCoordenadaX( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darCoordenadaY<br>
     * <b> M�todos a probar: </b> <br>
     * darCoordenadaY<br>
     * <b> Objetivo: </b> Probar que se retorna correctamente la coordenada Y de la ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene la coordenada Y de la ciudad<br>
     */
    public void testDarCoordenadaY( )
    {
        setupEscenario1( );
        assertTrue( "La coordenada Y fue mal inicializada", 0.1 == ciudad.darCoordenadaY( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darNombre<br>
     * <b> M�todos a probar: </b> <br>
     * darNombre<br>
     * <b> Objetivo: </b> Probar que se retorna correctamente el nombre de la ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene el nombre de la ciudad<br>
     */
    public void testDarNombre( )
    {
        setupEscenario1( );
        assertEquals( "El nombre fue mal inicializado", "New York", ciudad.darNombre( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darPais<br>
     * <b> M�todos a probar: </b> <br>
     * darPais<br>
     * <b> Objetivo: </b> Probar que se retorna correctamente el nombre del pa�s al que pertenece la ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene el nombre del pa�s al que pertenece la ciudad<br>
     */
    public void testDarPais( )
    {
        setupEscenario1( );
        assertEquals( "El pa�s fue mal inicializado", "USA", ciudad.darPais( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darSiguiente<br>
     * <b> M�todos a probar: </b> <br>
     * darSiguiente<br>
     * cambiarSiguiente<br>
     * <b> Objetivo: </b> Probar que se retorna correctamente la ciudad siguiente a la actual<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene la ciudad siguiente al actual
     */
    public void testDarSiguiente( )
    {
        setupEscenario1( );
        Ciudad ciudadSiguiente1 = ciudad.darSiguiente( );
        assertNull( "La ciudad siguiente deber�a ser null", ciudadSiguiente1 );
        Ciudad ciudadSiguiente2 = new Ciudad( "Londres", "Inglaterra", 0.2, 0.2 );
        ciudad.cambiarSiguiente( ciudadSiguiente2 );
        assertEquals( "No se cambio correctamente la ciudad siguiente", ciudadSiguiente2, ciudad.darSiguiente( ) );
    }

}