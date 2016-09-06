/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PlanetaTest.java,v 1.2 2007/06/28 22:46:45 camil-ji Exp $
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

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.observatorio.mundo.Observatorio;
import uniandes.cupi2.observatorio.mundo.Planeta;
import uniandes.cupi2.observatorio.mundo.SateliteNatural;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Planeta estén correctamente implementados
 */
public class PlanetaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Planeta planeta;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Observatorio vacía
     * 
     */
    private void setupEscenario1( )
    {
        planeta = new Planeta( Observatorio.NOMBRE_MERCURIO, 0.382, 0.205, 115.88, 478.725, 7.004 );
    }

    /**
     * Construye un nuevo planeta con una luna
     */
    private void setupEscenario2( )
    {
        planeta = new Planeta( Observatorio.NOMBRE_MERCURIO, 0.382, 0.205, 115.88, 478.725, 7.004 );
        planeta.agregarSateliteNatural( "Io", 0.041, 0.040 );
    }

    /**
     * Este método se encarga de verificar el método agregarSateliteNatural<br>
     * <b> Métodos a probar: </b> <br>
     * agregarSateliteNatural<br>
     * obtenerSateliteNatural<br>
     * <b> Objetivo: </b> Probar la inserción de un nuevo satélite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un satélite natural que no existe, retorne null. <br>
     * 2. Creación de un nuevo satélite natural con el nombre Io con excentricidad 0.041 e inclinación 0.040 y retorne true<br>
     */
    public void testAgregarSateliteNatural( )
    {
        setupEscenario1( );
        assertNull( "No debería existir el satélite natural", planeta.obtenerSateliteNatural( "Io" ) );
        planeta.agregarSateliteNatural( "Io", 0.041, 0.040 );
        assertNotNull( "No debería existir el satélite natural", planeta.obtenerSateliteNatural( "Io" ) );
        SateliteNatural io = planeta.obtenerSateliteNatural( "Io" );
        assertEquals( "No creo el satélite natural con la excentricidad correcta", 0.041, io.obtenerExcentricidad( ) );
        assertEquals( "No creo el satélite natural con la inclinación correcta", 0.040, io.obtenerInclinacion( ) );
    }

    /**
     * Este método se encarga de verificar el método EditarSateliteNatural<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerSateliteNatural<br>
     * editarSateliteNatural<br>
     * <b> Objetivo: </b> Probar la modificación de un satélite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creación de un nuevo satélite natural con el nombre Io con excentricidad 0.041 e inclinación 0.040<br>
     * 2. Modificar los datos del satélite natural con el nombre Io con excentricidad 0.5 e inclinación 0.05<br>
     * 3. Verificar que los nuevos datos de el satélite natural sean correctos.
     */
    public void testEditarSateliteNatural( )
    {
        setupEscenario2( );
        SateliteNatural io = planeta.obtenerSateliteNatural( "Io" );
        assertNotNull( "El satélite natural Io debería existir para Júpiter.", io );
        assertEquals( "No creo el satélite natural con la excentricidad correcta", 0.041, io.obtenerExcentricidad( ) );
        assertEquals( "No creo el satélite natural con la inclinación correcta", 0.040, io.obtenerInclinacion( ) );
        planeta.editarSateliteNatural( "Io", 0.5, 0.05 );
        io = planeta.obtenerSateliteNatural( "Io" );
        assertNotNull( "El satélite natural Io debería existir para Júpiter.", io );
        assertEquals( "No creo el satélite natural con la excentricidad correcta", 0.5, io.obtenerExcentricidad( ) );
        assertEquals( "No creo el satélite natural con la inclinación correcta", 0.05, io.obtenerInclinacion( ) );
    }

    /**
     * Este método se encarga de verificar el método EliminarSatelite<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerSateliteNatural<br>
     * eliminarSatelite<br>
     * <b> Objetivo: </b> Probar la eliminación de un satélite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el satélite natural creado. 2. Eliminar el satélite natural. 3. Obtener el satélite natural y verificar que no existe.
     */
    public void testEliminarSatelite( )
    {
        setupEscenario2( );
        SateliteNatural io = planeta.obtenerSateliteNatural( "Io" );
        assertNotNull( "El satélite natural Io debería existir para Júpiter.", io );
        planeta.eliminarSateliteNatural( "Io" );
        io = planeta.obtenerSateliteNatural( "Io" );
        assertNull( "El satélite natural Io no debería existir para Júpiter.", io );
    }

    /**
     * Este método se encarga de verificar el método obtenerDistancia<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerDistancia<br>
     * <b> Objetivo: </b> Probar que se creo correctamente el planeta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el valor correcto de la distancia media al sol.
     */
    public void testObtenerDistancia( )
    {
        setupEscenario2( );
        assertEquals( "La distancia es incorrecta", 0.382, planeta.obtenerDistancia( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerInclinacion<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerInclinacion<br>
     * <b> Objetivo: </b> Probar que se creo correctamente el planeta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el valor correcto de la inclinación orbital.
     */
    public void testObtenerInclinacion( )
    {
        setupEscenario2( );
        assertEquals( "La distancia es incorrecta", 7.004, planeta.obtenerInclinacion( ) );
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
        assertEquals( "El nombre es incorrecto", Observatorio.NOMBRE_MERCURIO, planeta.obtenerNombre( ) );

    }

    /**
     * Este método se encarga de verificar el método obtenerSateliteNatural<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerSateliteNatural<br>
     * <b> Objetivo: </b> Probar que se agrego correctamente el satélite natural al planeta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el valor correcto del satélite natural.
     */
    public void testObtenerSateliteNatural( )
    {
        setupEscenario2( );
        SateliteNatural io = planeta.obtenerSateliteNatural( "Io" );
        assertNotNull( "El satélite natural Io debería existir para Júpiter.", io );
        assertEquals( "No creo el satélite natural con la excentricidad correcta", 0.041, io.obtenerExcentricidad( ) );
        assertEquals( "No creo el satélite natural con la inclinación correcta", 0.040, io.obtenerInclinacion( ) );

    }

    /**
     * Este método se encarga de verificar el método obtenerSatelitesNaturales<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerSateliteNatural<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente un satélite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un satélite natural que no existe, retorne null. 2. Al buscar un satélite natural que existe, retorne uno con el nombre Io con excentricidad 0.041 e
     * inclinación 0.040 y retorne true<br>
     */
    public void testObtenerSatelitesNaturales( )
    {
        setupEscenario1( );
        ArrayList satelites = planeta.obtenerSatelitesNaturales( );
        assertNotNull( "La lista de satélites naturales no debería ser null", satelites );
        assertEquals( "La lista de satélites naturales debería ser vacía", 0, satelites.size( ) );
        setupEscenario2( );
        satelites = planeta.obtenerSatelitesNaturales( );
        assertNotNull( "La lista de satélites naturales no debería ser null", satelites );
        assertEquals( "La lista de satélites naturales no debería ser vacía", 1, satelites.size( ) );
    }

}