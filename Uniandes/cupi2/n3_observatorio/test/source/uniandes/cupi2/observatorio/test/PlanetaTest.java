/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PlanetaTest.java,v 1.2 2007/06/28 22:46:45 camil-ji Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Mu�oz - 13-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.observatorio.mundo.Observatorio;
import uniandes.cupi2.observatorio.mundo.Planeta;
import uniandes.cupi2.observatorio.mundo.SateliteNatural;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Planeta est�n correctamente implementados
 */
public class PlanetaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Planeta planeta;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Observatorio vac�a
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
     * Este m�todo se encarga de verificar el m�todo agregarSateliteNatural<br>
     * <b> M�todos a probar: </b> <br>
     * agregarSateliteNatural<br>
     * obtenerSateliteNatural<br>
     * <b> Objetivo: </b> Probar la inserci�n de un nuevo sat�lite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un sat�lite natural que no existe, retorne null. <br>
     * 2. Creaci�n de un nuevo sat�lite natural con el nombre Io con excentricidad 0.041 e inclinaci�n 0.040 y retorne true<br>
     */
    public void testAgregarSateliteNatural( )
    {
        setupEscenario1( );
        assertNull( "No deber�a existir el sat�lite natural", planeta.obtenerSateliteNatural( "Io" ) );
        planeta.agregarSateliteNatural( "Io", 0.041, 0.040 );
        assertNotNull( "No deber�a existir el sat�lite natural", planeta.obtenerSateliteNatural( "Io" ) );
        SateliteNatural io = planeta.obtenerSateliteNatural( "Io" );
        assertEquals( "No creo el sat�lite natural con la excentricidad correcta", 0.041, io.obtenerExcentricidad( ) );
        assertEquals( "No creo el sat�lite natural con la inclinaci�n correcta", 0.040, io.obtenerInclinacion( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo EditarSateliteNatural<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerSateliteNatural<br>
     * editarSateliteNatural<br>
     * <b> Objetivo: </b> Probar la modificaci�n de un sat�lite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creaci�n de un nuevo sat�lite natural con el nombre Io con excentricidad 0.041 e inclinaci�n 0.040<br>
     * 2. Modificar los datos del sat�lite natural con el nombre Io con excentricidad 0.5 e inclinaci�n 0.05<br>
     * 3. Verificar que los nuevos datos de el sat�lite natural sean correctos.
     */
    public void testEditarSateliteNatural( )
    {
        setupEscenario2( );
        SateliteNatural io = planeta.obtenerSateliteNatural( "Io" );
        assertNotNull( "El sat�lite natural Io deber�a existir para J�piter.", io );
        assertEquals( "No creo el sat�lite natural con la excentricidad correcta", 0.041, io.obtenerExcentricidad( ) );
        assertEquals( "No creo el sat�lite natural con la inclinaci�n correcta", 0.040, io.obtenerInclinacion( ) );
        planeta.editarSateliteNatural( "Io", 0.5, 0.05 );
        io = planeta.obtenerSateliteNatural( "Io" );
        assertNotNull( "El sat�lite natural Io deber�a existir para J�piter.", io );
        assertEquals( "No creo el sat�lite natural con la excentricidad correcta", 0.5, io.obtenerExcentricidad( ) );
        assertEquals( "No creo el sat�lite natural con la inclinaci�n correcta", 0.05, io.obtenerInclinacion( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo EliminarSatelite<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerSateliteNatural<br>
     * eliminarSatelite<br>
     * <b> Objetivo: </b> Probar la eliminaci�n de un sat�lite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el sat�lite natural creado. 2. Eliminar el sat�lite natural. 3. Obtener el sat�lite natural y verificar que no existe.
     */
    public void testEliminarSatelite( )
    {
        setupEscenario2( );
        SateliteNatural io = planeta.obtenerSateliteNatural( "Io" );
        assertNotNull( "El sat�lite natural Io deber�a existir para J�piter.", io );
        planeta.eliminarSateliteNatural( "Io" );
        io = planeta.obtenerSateliteNatural( "Io" );
        assertNull( "El sat�lite natural Io no deber�a existir para J�piter.", io );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerDistancia<br>
     * <b> M�todos a probar: </b> <br>
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
     * Este m�todo se encarga de verificar el m�todo obtenerInclinacion<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerInclinacion<br>
     * <b> Objetivo: </b> Probar que se creo correctamente el planeta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el valor correcto de la inclinaci�n orbital.
     */
    public void testObtenerInclinacion( )
    {
        setupEscenario2( );
        assertEquals( "La distancia es incorrecta", 7.004, planeta.obtenerInclinacion( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerNombre<br>
     * <b> M�todos a probar: </b> <br>
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
     * Este m�todo se encarga de verificar el m�todo obtenerSateliteNatural<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerSateliteNatural<br>
     * <b> Objetivo: </b> Probar que se agrego correctamente el sat�lite natural al planeta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el valor correcto del sat�lite natural.
     */
    public void testObtenerSateliteNatural( )
    {
        setupEscenario2( );
        SateliteNatural io = planeta.obtenerSateliteNatural( "Io" );
        assertNotNull( "El sat�lite natural Io deber�a existir para J�piter.", io );
        assertEquals( "No creo el sat�lite natural con la excentricidad correcta", 0.041, io.obtenerExcentricidad( ) );
        assertEquals( "No creo el sat�lite natural con la inclinaci�n correcta", 0.040, io.obtenerInclinacion( ) );

    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerSatelitesNaturales<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerSateliteNatural<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente un sat�lite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un sat�lite natural que no existe, retorne null. 2. Al buscar un sat�lite natural que existe, retorne uno con el nombre Io con excentricidad 0.041 e
     * inclinaci�n 0.040 y retorne true<br>
     */
    public void testObtenerSatelitesNaturales( )
    {
        setupEscenario1( );
        ArrayList satelites = planeta.obtenerSatelitesNaturales( );
        assertNotNull( "La lista de sat�lites naturales no deber�a ser null", satelites );
        assertEquals( "La lista de sat�lites naturales deber�a ser vac�a", 0, satelites.size( ) );
        setupEscenario2( );
        satelites = planeta.obtenerSatelitesNaturales( );
        assertNotNull( "La lista de sat�lites naturales no deber�a ser null", satelites );
        assertEquals( "La lista de sat�lites naturales no deber�a ser vac�a", 1, satelites.size( ) );
    }

}