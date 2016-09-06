/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AgenciaTest.java,v 1.3 2007/04/07 23:39:18 man-muno Exp $
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

import java.io.File;
import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.cupi2CruiseLines.mundo.Agencia;
import uniandes.cupi2.cupi2CruiseLines.mundo.CiudadNoExisteException;
import uniandes.cupi2.cupi2CruiseLines.mundo.CruceroExisteException;
import uniandes.cupi2.cupi2CruiseLines.mundo.CruceroNoExisteException;
import uniandes.cupi2.cupi2CruiseLines.mundo.PersistenciaException;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Agencia est�n correctamente implementados
 */
public class AgenciaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Ruta donde se guarda el archivo con la informacion del mundo
     */
    public static final String RUTA_ARCHIVO_PERSISTENCIA = "./test/data/cupi2-cruiseLines.data";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Agencia agencia;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Agencia vac�a
     */
    private void setupEscenario1( )
    {
        try
        {
            agencia = new Agencia( RUTA_ARCHIVO_PERSISTENCIA );
        }
        catch( PersistenciaException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Se crea una nueva agencia con un crucero
     */
    private void setupEscenario2( )
    {
        try
        {
            agencia = new Agencia( RUTA_ARCHIVO_PERSISTENCIA );
            agencia.agregarCrucero( "Titanic", 13, 1500, new Date( ) );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Se crea una nueva agencia con un crucero y dos ciudades
     */
    private void setupEscenario3( )
    {
        try
        {
            agencia = new Agencia( RUTA_ARCHIVO_PERSISTENCIA );
            agencia.agregarCrucero( "Titanic", 13, 1500, new Date( ) );
            agencia.agregarCiudad( "Titanic", "Londres", "Inglaterra", 0.1, 0.1 );
            agencia.agregarCiudad( "Titanic", "New York", "USA", 0.2, 0.2 );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo constructor<br>
     * <b> M�todos a probar: </b> <br>
     * constructor<br>
     * darCrucero<br>
     * darCruceros<br>
     * <b> Objetivo: </b> Probar que se crea correctamente la agencia vac�a<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se crea la agencia vac�a<br>
     */
    public void testAgencia( )
    {
        setupEscenario1( );
        assertNotNull( "La lista cruceros inicial no debe ser null", agencia.darCruceros( ) );
        assertEquals( "La cantidad cruceros inicial debe ser cero", 0, agencia.darCruceros( ).size( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo agregarCrucero<br>
     * <b> M�todos a probar: </b> <br>
     * agregarCrucero<br>
     * darCrucero<br>
     * darCruceros<br>
     * <b> Objetivo: </b> Probar que se crea correctamente el crucero especificado<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se crea el crucero Titanic<br>
     * 2. Se trata de crear el crucero Titanic pero debe salir un error porque ya existe<br>
     */
    public void testAgregarCrucero( )
    {
        setupEscenario1( );
        try
        {
            agencia.agregarCrucero( "Titanic", 13, 1500, new Date( ) );
            assertNotNull( "El crucero reci�n agregado no puede ser null", agencia.darCrucero( "Titanic" ) );
            assertNotNull( "La lista de cruceros no puede ser null", agencia.darCruceros( ) );
            assertEquals( "La lista de cruceros debe tener 1 elemento", 1, agencia.darCruceros( ).size( ) );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            agencia.agregarCrucero( "Titanic", 13, 1500, new Date( ) );
            fail( "No deber�a poder agregar un crucero existente" );
        }
        catch( CruceroExisteException e )
        {
            // Prueba correcta
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo agregarCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * agregarCiudad<br>
     * darCiudad<br>
     * darCiudades<br>
     * <b> Objetivo: </b> Probar que se crea correctamente la ciudad especificada<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se crea la ciudad de Londres, Inglaterra en el crucero Titanic<br>
     * 2. Al tratar de crear la ciudad de Londres, Inglaterra en el crucero Titanic se genera un error<br>
     */
    public void testAgregarCiudad( )
    {
        setupEscenario2( );
        try
        {
            agencia.agregarCiudad( "Titanic", "Londres", "Inglaterra", 0.1, 0.1 );
            assertNotNull( "La ciudad agregada no puede ser null", agencia.darCiudad( "Titanic", "Londres", "Inglaterra" ) );
            assertNotNull( "La lista de ciudades no puede ser null", agencia.darCiudades( "Titanic" ) );
            assertEquals( "La lista de ciudades no puede estar vac�a", 1, agencia.darCiudades( "Titanic" ).size( ) );
            assertNotNull( "La ciudad no puede ser null", agencia.darCiudad( "Titanic", "Londres", "Inglaterra" ) );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            agencia.agregarCiudad( "Titanic", "Londres", "Inglaterra", 0.1, 0.1 );
            fail( "No deber�a agregar una ciudad que ya existe" );
        }
        catch( Exception e )
        {
            // Prueba correcta
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darAnteriorCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * darAnteriorCiudad<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente la anterior ciudad de la especificada<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene la anterior ciudad de Londres, Inglaterra en el crucero Titanic<br>
     * 2. Al tratar de obtener la anterior ciudad de la ultima ciudad se obtiene null<br>
     * 3. Al tratar de obtener la anterior ciudad de una ciudad que no existe se informa del error<br>
     */
    public void testDarAnteriorCiudad( )
    {
        setupEscenario3( );
        try
        {
            assertNotNull( "La ciudad anterior no puede ser null", agencia.darAnteriorCiudad( "Titanic", "New York", "USA" ) );
            assertNull( "La ciudad anterior tiene que ser null", agencia.darAnteriorCiudad( "Titanic", "Londres", "Inglaterra" ) );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            agencia.darAnteriorCiudad( "Titanic", "rqwerwe", "afdaf" );
            fail( "No deber�a encontrar una ciudad no existente" );
        }
        catch( Exception e )
        {
            // Prueba correcta
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * darCiudad<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente una ciudad de crucero<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene la ciudad Londres en Inglaterra del crucero llamado Titanic<br>
     * 2. Se lanza un error cuando se quiere obtener una ciudad que no existe<br>
     */
    public void testDarCiudad( )
    {
        setupEscenario3( );
        try
        {
            assertNotNull( "La ciudad no puede ser null", agencia.darCiudad( "Titanic", "Londres", "Inglaterra" ) );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            agencia.darCiudad( "Titanic", "adfasfd", "asfdafd" );
            fail( "No deber�a encontrar una ciudad que no encuentra" );
        }
        catch( Exception e )
        {
            // Prueba correcta
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darCrucero<br>
     * <b> M�todos a probar: </b> <br>
     * darCrucero<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente un crucero<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene el crucero llamado Titanic<br>
     * 2. Se lanza un error cuando se quiere obtener un crucero que no existe<br>
     */
    public void testDarCiudades( )
    {
        setupEscenario3( );
        try
        {
            assertNotNull( "La lista de las ciudades no deber�a ser null", agencia.darCiudades( "Titanic" ) );
            assertEquals( "La cantidad de ciudades en la lista deber�a ser 2", 2, agencia.darCiudades( "Titanic" ).size( ) );
        }
        catch( CruceroNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darCrucero<br>
     * <b> M�todos a probar: </b> <br>
     * darCrucero<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente un crucero<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene el crucero llamado Titanic<br>
     * 2. Se lanza un error cuando se quiere obtener un crucero que no existe<br>
     */
    public void testDarCrucero( )
    {
        setupEscenario2( );
        try
        {
            assertNotNull( "El crucero no puede ser null", agencia.darCrucero( "Titanic" ) );
        }
        catch( CruceroNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            agencia.darCrucero( "asfdasfd" );
            fail( "No deber�a encontrar un crucero inexistente" );
        }
        catch( CruceroNoExisteException e )
        {
            // Prueba Correcta
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darCruceros<br>
     * <b> M�todos a probar: </b> <br>
     * darCruceros<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente la lista de los cruceros<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene la lista de cruceros de la agencia<br>
     */
    public void testDarCruceros( )
    {
        setupEscenario2( );
        assertNotNull( "La lista de cruceros no puede ser null", agencia.darCruceros( ) );
        assertEquals( "La lista de cruceros deber�a tener 1 elemento", 1, agencia.darCruceros( ).size( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darPuertoLlegada<br>
     * <b> M�todos a probar: </b> <br>
     * darPuertoLlegada<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente el puerto de llegada de un crucero<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene el puerto de llegada del crucero Titanic<br>
     * 2. No se puede obtener el puerto de llegada de un crucero inexistente<br>
     */
    public void testDarPuertoLlegada( )
    {
        setupEscenario3( );
        try
        {
            assertNotNull( "El puerto de llegada no puede ser null", agencia.darPuertoLlegada( "Titanic" ) );
        }
        catch( CruceroNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            agencia.darPuertoLlegada( "afdafdafd" );
            fail( "No deber�a encontrar el puerto de llegada de un crucero inexistente" );
        }
        catch( CruceroNoExisteException e )
        {
            // Prueba correcta
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darPuertoSalida<br>
     * <b> M�todos a probar: </b> <br>
     * darPuertoSalida<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente el puerto de salida de un crucero<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene el puerto de salida del crucero Titanic<br>
     * 2. No se puede obtener el puerto de salida de un crucero inexistente<br>
     */
    public void testDarPuertoSalida( )
    {
        setupEscenario3( );
        try
        {
            assertNotNull( "El puerto de llegada no puede ser null", agencia.darPuertoSalida( "Titanic" ) );
        }
        catch( CruceroNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            agencia.darPuertoSalida( "afdafdafd" );
            fail( "No deber�a encontrar el puerto de salida de un crucero inexistente" );
        }
        catch( CruceroNoExisteException e )
        {
            // Prueba correcta
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darSiguienteCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * darSiguienteCiudad<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente la siguiente ciudad de la especificada<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene la siguiente ciudad de Londres, Inglaterra en el crucero Titanic<br>
     * 2. Al tratar de obtener la siguiente ciudad de la ultima ciudad se obtiene null<br>
     * 3. Al tratar de obtener la siguiente ciudad de una ciudad que no existe se informa del error<br>
     */
    public void testDarSiguienteCiudad( )
    {
        setupEscenario3( );
        try
        {
            assertNotNull( "La ciudad siguiente no puede ser null", agencia.darSiguienteCiudad( "Titanic", "Londres", "Inglaterra" ) );
            assertNull( "La ciudad siguiente tiene que ser null", agencia.darSiguienteCiudad( "Titanic", "New York", "USA" ) );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            agencia.darSiguienteCiudad( "Titanic", "rqwerwe", "afdaf" );
            fail( "No deber�a encontrar una ciudad no existente" );
        }
        catch( Exception e )
        {
            // Prueba correcta
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo eliminarCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * eliminarCiudad<br>
     * darCiudad<br>
     * <b> Objetivo: </b> Probar la eliminaci�n de una ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Eliminaci�n de una ciudad New York del pa�s USA del crucero Titanic<br>
     * 2. Al tratar de eliminar una sucursal que no existe, que genere la excepci�n.<br>
     */
    public void testEliminarCiudad( )
    {
        setupEscenario3( );
        try
        {
            agencia.eliminarCiudad( "Titanic", "New York", "USA" );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            agencia.eliminarCiudad( "Titanic", "New York", "USA" );
            fail( "No se puede eliminar una ciudad que no existe" );
        }
        catch( Exception e )
        {
            // Prueba correcta
        }
        try
        {
            agencia.darCiudad( "Titanic", "New York", "USA" );
            fail( "No se puede buscar una ciudad eliminada" );
        }
        catch( CruceroNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
        catch( CiudadNoExisteException e1 )
        {
            // Prueba Correcta
        }

        try
        {
            agencia.eliminarCiudad( "Titanic", "New York", "USA" );
            fail( "No deber�a eliminar una ciudad que no existe" );
        }
        catch( Exception e )
        {
            // Prueba Correcta
        }
    }

    /**
     * Se prueba que se guarda y carga correctamente los archivos serializados. <b> M�todos a probar: </b> <br>
     * guardarCruiseLine<br>
     * cargarCruiseLine<br>
     * <b> Objetivo: </b> Verificaci�n que se actualizan correctamente los datos de la agencia.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Guardar un archivo con el estado del mundo<br>
     * 2. Leer del archivo con el estado del mundo<br>
     * 3. Cargar la cantidad correcta de cruceros<br>
     * 4. Cargar la cantidad correcta de ciudades para el crucero<br>
     */
    public void testGuardarCargarCruiseLine( )
    {
        setupEscenario3( );
        try
        {
            agencia.guardarCruiseLine( RUTA_ARCHIVO_PERSISTENCIA );
            agencia.cargarCruiseLine( RUTA_ARCHIVO_PERSISTENCIA );
            assertEquals( "La lista deber�a tener 1 elemento", 1, agencia.darCruceros( ).size( ) );
            assertNotNull( "El crucero no deber�a ser null", agencia.darCrucero( "Titanic" ) );
            assertNotNull( "La ciudad deber�a existir en la agencia", agencia.darCiudad( "Titanic", "New York", "USA" ) );
            assertNotNull( "La ciudad deber�a existir en la agencia", agencia.darCiudad( "Titanic", "Londres", "Inglaterra" ) );
            assertNotNull( "La ciudad deber�a tener anterior", agencia.darAnteriorCiudad( "Titanic", "New York", "USA" ) );
            assertNotNull( "La ciudad deber�a tener siguiente", agencia.darSiguienteCiudad( "Titanic", "Londres", "Inglaterra" ) );
            boolean borrado = new File( RUTA_ARCHIVO_PERSISTENCIA ).delete( );
            if( !borrado )
                fail( "No se pudo borrar el archivo de persistencia. Eliminelo manualmente" );
        }
        catch( PersistenciaException e )
        {
            fail( e.getMessage( ) );
        }
        catch( CruceroNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
        catch( CiudadNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
    }

}