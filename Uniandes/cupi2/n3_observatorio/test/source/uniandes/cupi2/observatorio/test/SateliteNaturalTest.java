/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: SateliteNaturalTest.java,v 1.5 2007/06/28 22:59:38 camil-ji Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Muñoz - 13-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.test;

import junit.framework.TestCase;
import uniandes.cupi2.observatorio.mundo.SateliteNatural;

/**
 * Esta es la clase usada para verificar que los métodos de la clase SateliteNatural estén correctamente implementados
 */
public class SateliteNaturalTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private SateliteNatural satelite;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo SateliteNatural vacío
     * 
     */
    private void setupEscenario1( )
    {
        satelite = new SateliteNatural( "nombre", 0.1, 0.2 );
    }

    /**
     * Este método se encarga de verificar el método editar y la inicialización de los atributos en el método constructor<br>
     * <b> Métodos a probar: </b> <br>
     * editar<br>
     * <b> Objetivo: </b> Probar la modificación de un satélite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creación de un nuevo satélite natural con el nombre nombre con excentricidad 0.1 e inclinación 0.2<br>
     * 2. Modificar los datos del satélite natural con el nombre nombre con excentricidad 0.3 e inclinación 0.4<br>
     * 3. Verificar que los nuevos datos de el satélite natural sean correctos.
     */
    public void testEditar( )
    {
        setupEscenario1( );
        assertEquals( "La excentricidad es incorrecta", 0.1, satelite.obtenerExcentricidad( ) );
        assertEquals( "La inclinación es incorrecta", 0.2, satelite.obtenerInclinacion( ) );
        satelite.cambiarExcentricidad( 0.3 );
        satelite.cambiarInclinacionOrbital( 0.4 );
        assertEquals( "La excentricidad es incorrecta", 0.3, satelite.obtenerExcentricidad( ) );
        assertEquals( "La inclinación es incorrecta", 0.4, satelite.obtenerInclinacion( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerExcentricidad<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerExcentricidad<br>
     * <b> Objetivo: </b> Probar que se creo correctamente el satélite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el valor correcto de la excentricidad.
     */
    public void testObtenerExcentricidad( )
    {
        setupEscenario1( );
        assertEquals( "La excentricidad es incorrecta", 0.1, satelite.obtenerExcentricidad( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerInclinacion<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerInclinacion<br>
     * <b> Objetivo: </b> Probar que se creo correctamente el satélite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el valor correcto de la inclinación.
     */
    public void testObtenerInclinacion( )
    {
        setupEscenario1( );
        assertEquals( "La inclinación es incorrecta", 0.2, satelite.obtenerInclinacion( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerNombre<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerNombre<br>
     * <b> Objetivo: </b> Probar que se creo correctamente el planeta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el valor correcto del nombre del planeta.
     */
    public void testObtenerNombre( )
    {
        setupEscenario1( );
        assertEquals( "El nombre es incorrecto", "nombre", satelite.obtenerNombre( ) );
    }

}