/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CruceroTest.java,v 1.1 2007/04/07 23:39:18 man-muno Exp $
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
import uniandes.cupi2.cupi2CruiseLines.mundo.CiudadNoExisteException;
import uniandes.cupi2.cupi2CruiseLines.mundo.Crucero;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Crucero est�n correctamente implementados
 */
public class CruceroTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Crucero crucero;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Crucero vac�o
     */
    private void setupEscenario1( )
    {
        crucero = new Crucero( "Titanic", 13, 1500, "la fecha" );
    }

    /**
     * Construye un crucero con una ciudad
     */
    private void setupEscenario2( )
    {
        crucero = new Crucero( "Titanic", 13, 1500, "la fecha" );
        crucero.agregarCiudad( "Londres", "Inglaterra", 0.1, 0.1 );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo constructor<br>
     * <b> M�todos a probar: </b> <br>
     * constructor<br>
     * darCiudades<br>
     * darDuracion<br>
     * darFechaSalida<br>
     * darNombre<br>
     * darNumeroCiudades<br>
     * darPrecio<br>
     * darPuertoLlegada<br>
     * darPuertoSalida<br>
     * <b> Objetivo: </b> Probar que se crea correctamente el crucero vac�o<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se crea la agencia vac�a<br>
     * 2. Se obtiene la informacion del crucero
     */
    public void testCrucero( )
    {
        setupEscenario1( );
        assertNotNull( "La lista de ciudades no deber�a ser null", crucero.darCiudades( ) );
        assertEquals( "La lista de las ciudades no deber�a tener elementos", 0, crucero.darCiudades( ).size( ) );
        assertEquals( "La duraci�n del crucero deber�a ser 13", 13, crucero.darDuracion( ) );
        assertNotNull( "La fecha de salida no puede ser null", crucero.darFechaSalida( ) );
        assertEquals( "La fecha de salida no es valida", "la fecha", crucero.darFechaSalida( ) );
        assertNotNull( "El nombre de la ", crucero.darNombre( ) );
        assertEquals( "El nombre del crucero no corresponde", "Titanic", crucero.darNombre( ) );
        assertEquals( "La cantidad de ciudades iniciales no es correcta", 0, crucero.darNumeroCiudades( ) );
        assertEquals( "El precio del viaje inicial es incorrecto", 1500, crucero.darPrecio( ) );
        assertNull( "No se inicializ� correctamente el puerto de llegada", crucero.darPuertoLlegada( ) );
        assertNull( "No se inicializ� correctamente el puerto de salida", crucero.darPuertoSalida( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo agregarCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * agregarCiudad<br>
     * darNumeroCiudades<br>
     * darCiudades<br>
     * darPuertoSalida<br>
     * darPuertoLlegada<br>
     * <b> Objetivo: </b> Probar que se agrega correctamente la ciudad especificada<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se crea la ciudad de Londres, Inglaterra en el crucero<br>
     */
    public void testAgregarCiudad( )
    {
        setupEscenario1( );
        assertEquals( "El tama�o inicial es incorrecto", 0, crucero.darCiudades( ).size( ) );
        assertEquals( "La cantidad de ciudades es incorrecta", 0, crucero.darNumeroCiudades( ) );
        assertNull( "El crucero no fue inicializado correctamente", crucero.darPuertoSalida( ) );
        assertNull( "El crucero no fue inicializado correctamente", crucero.darPuertoLlegada( ) );
        crucero.agregarCiudad( "Londres", "Inglaterra", 0.1, 0.1 );
        assertEquals( "No se agrego a la lista de ciudades la nueva ciudad", 1, crucero.darCiudades( ).size( ) );
        assertEquals( "La cantidad de ciudades es incorrecta", 1, crucero.darNumeroCiudades( ) );
        assertNotNull( "No se creo el puerto de llegada correctamente", crucero.darPuertoSalida( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo buscarCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * buscarCiudad<br>
     * <b> Objetivo: </b> Probar que se busca correctamente la ciudad especificada<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se busca la ciudad de Londres, Inglaterra en el crucero<br>
     * 2. Se busca una ciudad inexistente en el crucero<br>
     */
    public void testBuscarCiudad( )
    {
        setupEscenario2( );
        try
        {
            crucero.buscarCiudad( "Londres", "Inglaterra" );
        }
        catch( CiudadNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            crucero.buscarCiudad( "afdas", "asfdasf" );
            fail( "No deber�a encontrar una ciudad inexistente" );
        }
        catch( CiudadNoExisteException e )
        {
            // Prueba correcta
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo cambiarSiguiente<br>
     * <b> M�todos a probar: </b> <br>
     * cambiarSiguiente<br>
     * darSiguiente<br>
     * <b> Objetivo: </b> Probar que se cambie correctamente el crucero siguiente<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se cambia el siguiente crucero al actualS<br>
     */
    public void testCambiarSiguiente( )
    {
        setupEscenario2( );
        assertNull( "El siguiente crucero deber�a ser null", crucero.darSiguiente( ) );
        crucero.cambiarSiguiente( new Crucero( "Freedom of the Seas", 15, 2000, "otra Fecha" ) );
        assertNotNull( "El siguiente crucero deber�a ser diferente de null", crucero.darSiguiente( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darAnteriorCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * darAnteriorCiudad<br>
     * agregarCiudad<br>
     * <b> Objetivo: </b> Probar que se retorna correctamente la ciudad anterior a la especificada<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se busca la ciudad anterior a Londres, Inglaterra en el crucero<br>
     */
    public void testDarAnteriorCiudad( )
    {
        setupEscenario2( );
        try
        {
            assertNull( "La ciudad anterior deber�a ser null", crucero.darAnteriorCiudad( "Londres", "Inglaterra" ) );
        }
        catch( CiudadNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
        crucero.agregarCiudad( "New York", "USA", 0.2, 0.2 );
        try
        {
            assertNotNull( "La ciudad anterior no deberla ser null", crucero.darAnteriorCiudad( "New York", "USA" ) );
        }
        catch( CiudadNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darSiguiente<br>
     * <b> M�todos a probar: </b> <br>
     * darSiguiente<br>
     * cambiarSiguiente<br>
     * <b> Objetivo: </b> Probar que se retorna correctamente el crucero siguiente al actual<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene el crucero siguiente al actual
     */
    public void testDarSiguiente( )
    {
        setupEscenario2( );
        assertNull( "El siguiente crucero deber�a ser null", crucero.darSiguiente( ) );
        crucero.cambiarSiguiente( new Crucero( "Freedom of the Seas", 15, 2000, "otra Fecha" ) );
        assertNotNull( "El siguiente crucero deber�a ser diferente de null", crucero.darSiguiente( ) );

    }

    /**
     * Este m�todo se encarga de verificar el m�todo darSiguienteCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * darSiguienteCiudad<br>
     * agregarCiudad<br>
     * <b> Objetivo: </b> Probar que se retorna correctamente la ciudad siguiente a la especificada<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se busca la ciudad siguiente a Londres, Inglaterra en el crucero<br>
     */
    public void testDarSiguienteCiudad( )
    {
        setupEscenario2( );
        crucero.agregarCiudad( "New York", "USA", 0.2, 0.2 );
        try
        {
            assertNull( "La ciudad anterior deber�a ser null", crucero.darSiguienteCiudad( "New York", "USA" ) );
        }
        catch( CiudadNoExisteException e )
        {
            fail( e.getMessage( ) );
        }

        try
        {
            assertNotNull( "La ciudad anterior no deberla ser null", crucero.darSiguienteCiudad( "Londres", "Inglaterra" ) );
        }
        catch( CiudadNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo eliminarCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * eliminarCiudad<br>
     * buscarCiudad<br>
     * <b> Objetivo: </b> Probar que se elimina correctamente el crucero<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se elimina correctamente el crucero<br>
     * 2. No se encuentra el crucero que no exista<br>
     */
    public void testEliminarCiudad( )
    {
        setupEscenario2( );
        try
        {
            assertNotNull( "Deber�a encontrar la ciudad", crucero.buscarCiudad( "Londres", "Inglaterra" ) );
            crucero.eliminarCiudad( "Londres", "Inglaterra" );
        }
        catch( CiudadNoExisteException e )
        {
            fail( e.getMessage( ) );
        }
        try
        {
            assertNull( "Deber�a encontrar la ciudad", crucero.buscarCiudad( "Londres", "Inglaterra" ) );
            fail( "No deber�a encontrar una ciudad que ya fue eliminada" );
        }
        catch( CiudadNoExisteException e )
        {
            // Prueba correcta
        }
        try
        {
            crucero.eliminarCiudad( "Londres", "Inglaterra" );
            fail( "No eliminar una ciudad que ya fue eliminada" );
        }
        catch( CiudadNoExisteException e )
        {
            // Prueba correcta
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darPuertoSalida<br>
     * <b> M�todos a probar: </b> <br>
     * darPuertoSalida<br>
     * agregarCiudad<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente el puerto de salida del crucero<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene correctamente el puerto de salida<br>
     */
    public void testDarPuertoSalida( )
    {
        setupEscenario1( );
        assertNull( "El puerto de salida deber�a ser null", crucero.darPuertoSalida( ) );
        crucero.agregarCiudad( "Londres", "Inglaterra", 0.1, 0.1 );
        Ciudad puertoSalida = crucero.darPuertoSalida( );
        assertNotNull( "El puerto de salida no deber�a ser null", puertoSalida );
        crucero.agregarCiudad( "New York", "USA", 0.2, 0.2 );
        assertNotNull( "El puerto de salid no deber�a ser null", crucero.darPuertoSalida( ) );
        assertEquals( "El puerto de salida no deber�a cambiar", puertoSalida, crucero.darPuertoSalida( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darPuertoLlegada<br>
     * <b> M�todos a probar: </b> <br>
     * darPuertoLlegada<br>
     * agregarCiudad<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente el puerto de llegada del crucero<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Cuando no existe ninguna ciudad el puerto de llegada es null<br>
     * 2. Al agregar la primera ciudad el puerto de llegada es null<br>
     * 3. Al agregar otra ciudad esta se vuelve el puerto de llegada<br>
     */
    public void testDarPuertoLlegada( )
    {
        setupEscenario1( );
        assertNull( "El puerto de llegada deber�a ser null", crucero.darPuertoLlegada( ) );
        crucero.agregarCiudad( "Londres", "Inglaterra", 0.1, 0.1 );
        assertNull( "El puerto de llegada no deber�a ser null", crucero.darPuertoLlegada( ) );
        crucero.agregarCiudad( "New York", "USA", 0.2, 0.2 );
        Ciudad puertoLlegada = crucero.darPuertoLlegada( );
        assertNotNull( "El puerto de llegada no deber�a ser null", puertoLlegada );
        crucero.agregarCiudad( "Miami", "USA", 0.3, 0.3 );
        assertTrue( "El puerto de llegada deber�a cambiar", puertoLlegada != crucero.darPuertoLlegada( ) );
    }

}