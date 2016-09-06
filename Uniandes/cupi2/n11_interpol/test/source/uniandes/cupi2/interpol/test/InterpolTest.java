/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterpolTest.java,v 1.2 2007/04/20 12:38:41 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Muñoz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.interpol.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import junit.framework.TestCase;
import uniandes.cupi2.interpol.mundo.Interpol;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Interpol estén correctamente implementados
 */
public class InterpolTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Interpol interpol;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Interpol vacía
     */
    private void setupEscenario1( )
    {
        interpol = new Interpol( );
    }

    /**
     * Construye una nueva Interpol con una sola hoja
     */
    private void setupEscenario2( )
    {
        try
        {
            interpol = new Interpol( );
            Properties propiedades = new Properties( );
            propiedades.load( new FileInputStream( new File( "./test/data/hoja.properties" ) ) );
            interpol.cargarJuego( propiedades );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Construye una nueva Interpol con un árbol de juego valido
     */
    private void setupEscenario3( )
    {
        try
        {
            interpol = new Interpol( );
            Properties propiedades = new Properties( );
            propiedades.load( new FileInputStream( new File( "./test/data/facil.properties" ) ) );
            interpol.cargarJuego( propiedades );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Este método se encarga de verificar el método constructor y los analizadores<br>
     * <b> Métodos a probar: </b> <br>
     * Interpol<br>
     * ciudadActualEsHoja<br>
     * darCaminoRespuesta<br>
     * darCaso<br>
     * darCiudadActual<br>
     * darNombresLugaresCiudadActual<br>
     * darNombresPosiblesDestinos<br>
     * darInorden<br>
     * darPeso<br>
     * darPostorden<br>
     * darPreorden<br>
     * darPrimeraCiudad<br>
     * darSospechosos<br>
     * ordenCapturaGenerada<br>
     * <b> Objetivo: </b> Probar que se construye correctamente el objeto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol vacía<br>
     * 2. Se obtienen los datos de Interpol creado y concuerdan con los ingresados<br>
     */
    public void testInterpol( )
    {
        setupEscenario1( );
        assertFalse( "La ciudad actual no es es hoja", interpol.ciudadActualEsHoja( ) );
        assertEquals( "La altura de un árbol vacío es cero", 0, interpol.darAltura( ) );
        assertNotNull( "La lista del camino a la respuesta no debería ser null", interpol.darCaminoRespuesta( ) );
        assertEquals( "La lista del camino a la respuesta debería ser vacía", 0, interpol.darCaminoRespuesta( ).size( ) );
        assertNotNull( "El caso sobre el que se esta trabajando no puede ser null", interpol.darCaso( ) );
        assertEquals( "El caso debería ser vacío", 0, interpol.darCaso( ).length( ) );
        assertNull( "La ciudad actual debería ser null", interpol.darCiudadActual( ) );
        assertEquals( "Los días faltantes deberían ser 0", 0, interpol.darDiasFaltantes( ) );
        assertNotNull( "La lista del recorrido por inorden no debería ser null", interpol.darInorden( ) );
        assertEquals( "El tamaño de la lista del recorrido por inordenn debería ser 0", 0, interpol.darInorden( ).size( ) );
        assertNotNull( "La lista de los nombres de los lugares de la ciudad actual no debería ser null", interpol.darNombresLugaresCiudadActual( ) );
        assertEquals( "El tamaño de la lista de los nombres de los lugares de la ciudad actual debería ser 0", 0, interpol.darNombresLugaresCiudadActual( ).size( ) );
        assertNotNull( "La lista de los nombres de los posibles destinos de la ciudad actual no debería ser null", interpol.darNombresPosiblesDestinos( ) );
        assertEquals( "El tamaño de la lista de los posibles destinos de la ciudad actual debería ser 0", 0, interpol.darNombresPosiblesDestinos( ).size( ) );
        assertEquals( "El peso de un árbol vacío debería ser 0", 0, interpol.darPeso( ) );
        assertNotNull( "La lista del recorrido por inorden no debería ser null", interpol.darPostorden( ) );
        assertEquals( "El tamaño de la lista del recorrido por inordenn debería ser 0", 0, interpol.darPreorden( ).size( ) );
        assertNotNull( "La lista del recorrido por inorden no debería ser null", interpol.darPostorden( ) );
        assertEquals( "El tamaño de la lista del recorrido por inordenn debería ser 0", 0, interpol.darPreorden( ).size( ) );
        assertNull( "La primera ciudad debería ser null", interpol.darPrimeraCiudad( ) );
        assertNotNull( "La lista de sospechosos no debería ser null", interpol.darSospechosos( ) );
        assertEquals( "La lista de sospechosos debería ser vacía", 0, interpol.darSospechosos( ).size( ) );
        assertFalse( "No puede existir orden de captura para el árbol vacío", interpol.ordenCapturaGenerada( ) );
    }

    /**
     * Este método se encarga de verificar el método cargarJuego<br>
     * <b> Métodos a probar: </b> <br>
     * cargarJuego<br>
     * ciudadActualEsHoja<br>
     * darCaminoRespuesta<br>
     * darCaso<br>
     * darCiudadActual<br>
     * darNombresLugaresCiudadActual<br>
     * darNombresPosiblesDestinos<br>
     * darInorden<br>
     * darPeso<br>
     * darPostorden<br>
     * darPreorden<br>
     * darPrimeraCiudad<br>
     * darSospechosos<br>
     * ordenCapturaGenerada<br>
     * <b> Objetivo: </b> Probar que se construye correctamente el objeto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol vacía<br>
     * 2. Se carga la informacion del archivo de propiedades 3. Se obtienen los datos de Interpol creado y concuerdan con los ingresados<br>
     */
    public void testCargarJuego( )
    {
        setupEscenario1( );
        Properties propiedades = new Properties( );
        try
        {
            propiedades.load( new FileInputStream( new File( "./test/data/facil.properties" ) ) );
            interpol.cargarJuego( propiedades );
            assertFalse( "La ciudad actual no es es hoja", interpol.ciudadActualEsHoja( ) );
            assertEquals( "La altura de un árbol vacío es 3", 3, interpol.darAltura( ) );
            assertNotNull( "La lista del camino a la respuesta no debería ser null", interpol.darCaminoRespuesta( ) );
            assertEquals( "La lista del camino a la respuesta debería tener 3 elementos", 3, interpol.darCaminoRespuesta( ).size( ) );
            assertNotNull( "El caso sobre el que se esta trabajando no puede ser null", interpol.darCaso( ) );
            assertTrue( "El caso no debería ser vacío", interpol.darCaso( ).length( ) != 0 );
            assertNotNull( "La ciudad actual no debería ser null", interpol.darCiudadActual( ) );
            assertEquals( "Los días faltantes deberían ser 8", 8, interpol.darDiasFaltantes( ) );
            assertNotNull( "La lista del recorrido por inorden no debería ser null", interpol.darInorden( ) );
            assertEquals( "El tamaño de la lista del recorrido por inordenn debería ser 7", 7, interpol.darInorden( ).size( ) );
            assertNotNull( "La lista de los nombres de los lugares de la ciudad actual no debería ser null", interpol.darNombresLugaresCiudadActual( ) );
            assertEquals( "El tamaño de la lista de los nombres de los lugares de la ciudad actual debería ser 3", 3, interpol.darNombresLugaresCiudadActual( ).size( ) );
            assertNotNull( "La lista de los nombres de los posibles destinos de la ciudad actual no debería ser null", interpol.darNombresPosiblesDestinos( ) );
            assertEquals( "El tamaño de la lista de los posibles destinos de la ciudad actual debería ser 3", 3, interpol.darNombresPosiblesDestinos( ).size( ) );
            assertEquals( "El peso de un árbol vacío debería ser 7", 7, interpol.darPeso( ) );
            assertNotNull( "La lista del recorrido por inorden no debería ser null", interpol.darPostorden( ) );
            assertEquals( "El tamaño de la lista del recorrido por inordenn debería ser 7", 7, interpol.darPreorden( ).size( ) );
            assertNotNull( "La lista del recorrido por inorden no debería ser null", interpol.darPostorden( ) );
            assertEquals( "El tamaño de la lista del recorrido por inordenn debería ser 7", 7, interpol.darPreorden( ).size( ) );
            assertNotNull( "La primera ciudad no debería ser null", interpol.darPrimeraCiudad( ) );
            assertNotNull( "La lista de sospechosos no debería ser null", interpol.darSospechosos( ) );
            assertEquals( "La lista de sospechosos no debería ser vacía", 4, interpol.darSospechosos( ).size( ) );
            assertFalse( "No puede existir orden de captura para el árbol vacío", interpol.ordenCapturaGenerada( ) );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Este método se encarga de verificar el método ciudadActualEsHoja<br>
     * <b> Métodos a probar: </b> <br>
     * ciudadActualEsHoja<br>
     * <b> Objetivo: </b> Probar que se evalúa correctamente a la ciudad actual cuando es un árbol que es una hoja<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol cuyo árbol solo es una hoja<br>
     * 2. Se evalúa que en un árbol vacío la ciudad actual es hoja<br>
     */
    public void testCiudadActualEsHojaHoja( )
    {
        setupEscenario2( );
        assertTrue( "Un árbol de una sola hoja debería ser hoja", interpol.ciudadActualEsHoja( ) );
    }

    /**
     * Este método se encarga de verificar el método ciudadActualEsHoja<br>
     * <b> Métodos a probar: </b> <br>
     * ciudadActualEsHoja<br>
     * <b> Objetivo: </b> Probar que se evalúa correctamente a la ciudad actual cuando es un árbol que es una hoja<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol cuyo árbol solo es una hoja<br>
     * 2. Se evalúa que en un árbol vacío la ciudad actual es hoja<br>
     */
    public void testCiudadActualEsHoja( )
    {
        setupEscenario3( );
        interpol.viajar( "Cartagena" );
        interpol.viajar( "Leticia" );
        assertTrue( "La ciudad actual debería ser hoja", interpol.ciudadActualEsHoja( ) );

    }

    /**
     * Este método se encarga de verificar el método darCaminoRespuesta<br>
     * <b> Métodos a probar: </b> <br>
     * darCaminoRespuesta<br>
     * <b> Objetivo: </b> Probar que se obtiene el camino a la respuesta cuando es un árbol que es una hoja<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol cuyo árbol solo es una hoja<br>
     * 2. Se obtiene la lista del camino a la respuesta.<br>
     * 3. La lista debe tener un solo elemento "Bogotá"<br>
     */
    public void testDarCaminoRespuestaHoja( )
    {
        setupEscenario2( );
        assertNotNull( "La lista del camino respuesta no debería ser null", interpol.darCaminoRespuesta( ) );
        assertEquals( "El tamaño de la lista del camino a la respuesta debería tener un elemento", 1, interpol.darCaminoRespuesta( ).size( ) );
        assertEquals( "El camino a la ciudad a la respuesta es incorrecto", "Bogotá", interpol.darCaminoRespuesta( ).get( 0 ) );
    }

    /**
     * Este método se encarga de verificar el método darCaminoRespuesta<br>
     * <b> Métodos a probar: </b> <br>
     * darCaminoRespuesta<br>
     * <b> Objetivo: </b> Probar que se obtiene el camino a la respuesta cuando es un árbol<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol cuyo árbol<br>
     * 2. Se obtiene la lista del camino a la respuesta.<br>
     * 3. La lista debe tener el primer elemento "Bogotá", luego "Cartagena", y luego "Leticia"<br>
     */
    public void testDarCaminoRespuesta( )
    {
        setupEscenario3( );
        assertNotNull( "La lista del camino respuesta no debería ser null", interpol.darCaminoRespuesta( ) );
        assertEquals( "El tamaño de la lista del camino a la respuesta debería tener 3 elementos", 3, interpol.darCaminoRespuesta( ).size( ) );
        assertEquals( "El camino a la ciudad a la respuesta es incorrecto", "Bogotá", interpol.darCaminoRespuesta( ).get( 0 ) );
        assertEquals( "El camino a la ciudad a la respuesta es incorrecto", "Cartagena", interpol.darCaminoRespuesta( ).get( 1 ) );
        assertEquals( "El camino a la ciudad a la respuesta es incorrecto", "Leticia", interpol.darCaminoRespuesta( ).get( 2 ) );
    }

    /**
     * Este método se encarga de verificar el método darCaminoRespuesta<br>
     * <b> Métodos a probar: </b> <br>
     * darCaminoRespuesta<br>
     * <b> Objetivo: </b> Probar que se obtiene el camino a la respuesta cuando es un árbol<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol cuyo árbol<br>
     * 2. Se obtiene la lista del camino a la respuesta.<br>
     * 3. La lista debe tener el primer elemento "Bogotá", luego "Cartagena", y luego "Leticia"<br>
     */
    public void testDarCaso( )
    {
        setupEscenario3( );
        assertNotNull( "El caso no puede ser null", interpol.darCaso( ) );
        assertEquals( "El caso no debería ser vacío", 57, interpol.darCaso( ).length( ) );
        assertEquals( "El caso no es correcto", "Alguien se ha robado el Poporo Quimbaya del Museo del Oro", interpol.darCaso( ) );
    }

    /**
     * Este método se encarga de verificar el método darCiudadActual<br>
     * <b> Métodos a probar: </b> <br>
     * darCiudadActual<br>
     * viajar<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente la ciudad actual<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol con un árbol valido<br>
     * 2. Se obtiene la ciudad actual que es la raiz.<br>
     * 3. Se viaja a otra ciudad<br>
     * 3. Se obtiene la ciudad actual y debe ser "San Andrés"<br>
     */
    public void testDarCiudadActual( )
    {
        setupEscenario3( );
        assertNotNull( "La ciudad actual no puede ser null", interpol.darCiudadActual( ) );
        assertEquals( "La ciudad actual es incorrecta", "Bogotá", interpol.darCiudadActual( ).darNombreCiudad( ) );
        interpol.viajar( "San Andrés" );
        assertEquals( "La ciudad actual es incorrecta", "San Andrés", interpol.darCiudadActual( ).darNombreCiudad( ) );
    }

    /**
     * Este método se encarga de verificar el método darDiasFaltantes<br>
     * <b> Métodos a probar: </b> <br>
     * darDiasFaltantes<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente los días faltantes<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol con un árbol<br>
     * 2. Se obtienen los días faltantes.<br>
     */
    public void testDarDiasFaltantes( )
    {
        setupEscenario3( );
        assertEquals( "La cantidad de días faltantes es incorrecta", 8, interpol.darDiasFaltantes( ) );
    }

    /**
     * Este método se encarga de verificar el método darNombresLugaresCiudadActual<br>
     * <b> Métodos a probar: </b> <br>
     * darNombresLugaresCiudadActual<br>
     * viajar<br>
     * <b> Objetivo: </b> Probar que se obtienen correctamente los lugares para buscar pistas<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol con un árbol<br>
     * 2. Se obtienen los 3 lugares para buscar pistas de la ciudad actual, que es la raíz<br>
     * 3. Se viaja a una de las ciudades hijas de la raíz. A Cartagena<br>
     * 4. Se obtienen los 2 lugares para buscar pistas de la ciudad actual, que es Cartagena<br>
     */
    public void testDarNombresLugaresCiudadActual( )
    {
        setupEscenario3( );
        ArrayList respuesta = ( ArrayList )interpol.darNombresLugaresCiudadActual( );
        assertNotNull( "La lista de los lugares no puede ser null", respuesta );
        assertEquals( "La cantidad de lugares de la ciudad actual es incorrecta", 3, respuesta.size( ) );
        assertTrue( "Debería contener al Museo como lugar para buscar pistas", respuesta.contains( "Museo" ) );
        assertTrue( "Debería contener a la Agencia de Viajes como lugar para buscar pistas", respuesta.contains( "Agencia de Viajes" ) );
        assertTrue( "Debería contener a la Biblioteca como lugar para buscar pistas", respuesta.contains( "Biblioteca" ) );

        interpol.viajar( "Cartagena" );
        respuesta = ( ArrayList )interpol.darNombresLugaresCiudadActual( );
        assertNotNull( "La lista de los lugares no puede ser null", respuesta );
        assertEquals( "La cantidad de lugares de la ciudad actual es incorrecta", 2, respuesta.size( ) );
        assertTrue( "Debería contener al Museo como lugar para buscar pistas", respuesta.contains( "Museo" ) );
        assertTrue( "Debería contener a la Agencia de Viajes como lugar para buscar pistas", respuesta.contains( "Agencia de Viajes" ) );
    }

    /**
     * Este método se encarga de verificar el método darNombresPosiblesDestinos<br>
     * <b> Métodos a probar: </b> <br>
     * darNombresPosiblesDestinos<br>
     * viajar<br>
     * <b> Objetivo: </b> Probar que se obtienen correctamente los nombres de los posibles destinos de la ciudad actual<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol con un árbol<br>
     * 2. Se obtienen los 3 nombres de los posibles destinos para viajar de la ciudad actual, que es la raíz<br>
     * 3. Los nombres que debe contener la lista son Cartagena, Barranquilla, San Andrés. 3. Se viaja a una de las ciudades hijas de la raíz. A Barranquilla<br>
     * 4. No se obtienen nombres de los posibles destinos a viajar<br>
     */
    public void testDarNombresPosiblesDestinos( )
    {
        setupEscenario3( );
        ArrayList respuesta = ( ArrayList )interpol.darNombresPosiblesDestinos( );
        assertNotNull( "La lista de los posibles destinos no puede ser null", respuesta );
        assertEquals( "La lista de los posibles destinos debería tener 3 elementos", 3, respuesta.size( ) );
        assertTrue( "La lista de posibles destinos debería contener a Cartagena", respuesta.contains( "Cartagena" ) );
        assertTrue( "La lista de posibles destinos debería contener a Barranquilla", respuesta.contains( "Barranquilla" ) );
        assertTrue( "La lista de posibles destinos debería contener a San Andrés", respuesta.contains( "San Andrés" ) );

        interpol.viajar( "Barranquilla" );
        respuesta = ( ArrayList )interpol.darNombresPosiblesDestinos( );
        assertNotNull( "La lista de los posibles destinos no puede ser null", respuesta );
        assertEquals( "La lista de los posibles destinos debería tener 0 elementos", 0, respuesta.size( ) );

    }

    /**
     * Este método se encarga de verificar el método darNombresLugaresCiudadActual<br>
     * <b> Métodos a probar: </b> <br>
     * darNombresLugaresCiudadActual<br>
     * <b> Objetivo: </b> Probar que se obtienen correctamente las pistas dado un lugar de la ciudad actual<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol con un árbol<br>
     * 2. Se obtienen los 3 lugares para buscar pistas de la ciudad actual, que es la raíz<br>
     * 3. Se obtienen las pistas de esos lugares y se comparan para ver si están correctas<br>
     */
    public void testDarPistaLugar( )
    {
        setupEscenario3( );
        String pista = interpol.darPistaLugar( ( String )interpol.darNombresLugaresCiudadActual( ).get( 0 ) );
        assertNotNull( "La pista del lugar no puede ser null", pista );
        assertEquals( "La pista es incorrecta", "Mucha gente visitó el museo, pero había una persona particularmente interesada en las medidas de seguridad, pero nadie recuerda como es.", pista );
        pista = interpol.darPistaLugar( ( String )interpol.darNombresLugaresCiudadActual( ).get( 1 ) );
        assertNotNull( "La pista del lugar no puede ser null", pista );
        assertEquals( "La pista es incorrecta", "Una persona estaba interesada por conocer los viajes mas inmediatos hacia la ciudad amurallada", pista );
        pista = interpol.darPistaLugar( ( String )interpol.darNombresLugaresCiudadActual( ).get( 2 ) );
        assertNotNull( "La pista del lugar no puede ser null", pista );
        assertEquals( "Una persona estaba muy interesada en saber cuales eran las ciudades puerto de Colombia por las cuales salía el trafico de obras culturales del Colombia.", pista );
    }

    /**
     * Este método se encarga de verificar el método darPrimeraCiudad<br>
     * <b> Métodos a probar: </b> <br>
     * darPrimeraCiudad<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente el nombre de la primera ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol con un árbol<br>
     * 2. Se obtienen la raíz y se pregunta si el nombre es correcto<br>
     */
    public void testDarPrimeraCiudad( )
    {
        setupEscenario3( );
        assertNotNull( "La ciudad raíz no puede ser invalida", interpol.darPrimeraCiudad( ) );
        assertEquals( "El nombre de la primera ciudad es incorrecto", "Bogotá", interpol.darPrimeraCiudad( ).darNombreCiudad( ) );
    }

    /**
     * Este método se encarga de verificar el método darSospechosos<br>
     * <b> Métodos a probar: </b> <br>
     * darSospechosos<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente la lista de sospechosos<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol con un árbol<br>
     * 2. Se obtiene la lista con el tamaño correcto<br>
     */
    public void testDarSospechosos( )
    {
        setupEscenario3( );
        ArrayList respuesta = ( ArrayList )interpol.darSospechosos( );
        assertNotNull( "La lista de sospechosos no puede ser null", respuesta );
        assertEquals( "La cantidad de los sospechosos es invalida", 4, respuesta.size( ) );
    }

    /**
     * Este método se encarga de verificar el método generarOrdenCaptura<br>
     * <b> Métodos a probar: </b> <br>
     * generarOrdenCaptura<br>
     * <b> Objetivo: </b> Probar que se genera correctamente la orden <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol con un árbol<br>
     * 2. Se obtienen el primer sospechoso y no se genera la orden de captura<br>
     * 3. Se obtienen el segundo sospechoso y se genera la orden de captura<br>
     * 4. Se obtienen el tercer sospechoso y no se genera la orden de captura<br>
     * 5. Se obtienen el cuarto sospechoso y no se genera la orden de captura<br>
     */
    public void testGenerarOrdenCaptura( )
    {
        setupEscenario3( );
        assertFalse( "El primer sospechoso no es el ladrón", interpol.generarOrdenCaptura( 0 ) );
        assertTrue( "El segundo sospechoso no es el ladrón", interpol.generarOrdenCaptura( 1 ) );
        assertFalse( "El tercer sospechoso no es el ladrón", interpol.generarOrdenCaptura( 2 ) );
        assertFalse( "El cuarto sospechoso no es el ladrón", interpol.generarOrdenCaptura( 3 ) );
    }

    /**
     * Este método se encarga de verificar el método ordenCapturaGenerada<br>
     * <b> Métodos a probar: </b> <br>
     * ordenCapturaGenerada<br>
     * generarOrdenCaptura<br>
     * <b> Objetivo: </b> Probar que se genera informa si se genero correctamente la orden de captura<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol con un árbol<br>
     * 2. Se obtienen el primer sospechoso y no se genera la orden de captura<br>
     * 3. Se pregunta si se genero la orden de captura<br>
     * 4. Se obtienen el tercer sospechoso y no se genera la orden de captura<br>
     * 5. Se pregunta si se genero la orden de captura<br>
     * 6. Se obtienen el cuarto sospechoso y no se genera la orden de captura<br>
     * 7. Se pregunta si se genero la orden de captura<br>
     * 8. Se obtienen el segundo sospechoso y no se genera la orden de captura<br>
     * 9. Se pregunta si se genero la orden de captura<br>
     */
    public void testOrdenCapturaGenerada( )
    {
        setupEscenario3( );
        assertFalse( "La orden de captura no debió ser generada", interpol.ordenCapturaGenerada( ) );
        assertFalse( "El primer sospechoso no es el ladrón", interpol.generarOrdenCaptura( 0 ) );
        assertFalse( "La orden de captura no debió ser generada", interpol.ordenCapturaGenerada( ) );
        assertFalse( "El tercer sospechoso no es el ladrón", interpol.generarOrdenCaptura( 2 ) );
        assertFalse( "La orden de captura no debió ser generada", interpol.ordenCapturaGenerada( ) );
        assertFalse( "El cuarto sospechoso no es el ladrón", interpol.generarOrdenCaptura( 3 ) );
        assertFalse( "La orden de captura no debió ser generada", interpol.ordenCapturaGenerada( ) );
        assertTrue( "El segundo sospechoso no es el ladrón", interpol.generarOrdenCaptura( 1 ) );
        assertTrue( "La orden de captura debió ser generada", interpol.ordenCapturaGenerada( ) );
    }

    /**
     * Este método se encarga de verificar el método sospechosoEncontrado<br>
     * <b> Métodos a probar: </b> <br>
     * sospechosoEncontrado<br>
     * viajar<br>
     * <b> Objetivo: </b> Probar que se informa si esta en la ciudad con el sospechoso<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol con un árbol<br>
     * 2. Se pregunta en la primera ciudad si esta el sospechoso, no se encuentra<br>
     * 3. Se viaja a otra ciudad<br>
     * 4. Se pregunta a la siguiente ciudad si esta el sospechoso, no se encuentra<br>
     * 5. Se viaja a otra ciudad<br>
     * 6. Se pregunta a la siguiente ciudad si esta el sospechoso, si se encuentra<br>
     */
    public void testSospechosoEncontrado( )
    {
        setupEscenario3( );
        assertFalse( "Al comenzar no se debió haber encontrado el sospechoso", interpol.sospechosoEncontrado( ) );
        interpol.viajar( "Cartagena" );
        assertFalse( "Al viajar no se debió haber encontrado el sospechoso", interpol.sospechosoEncontrado( ) );
        interpol.viajar( "Leticia" );
        assertTrue( "Al viajar se debió haber encontrado el sospechoso", interpol.sospechosoEncontrado( ) );
    }

    /**
     * Este método se encarga de verificar el método viajar<br>
     * <b> Métodos a probar: </b> <br>
     * viajar<br>
     * <b> Objetivo: </b> Probar que se viaja correctamente a la siguiente ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye Interpol con un árbol<br>
     * 2. Se obtienen el primer sospechoso y no se genera la orden de captura<br>
     * 3. Se pregunta si se genero la orden de captura<br>
     * 4. Se obtienen el tercer sospechoso y no se genera la orden de captura<br>
     * 5. Se pregunta si se genero la orden de captura<br>
     * 6. Se obtienen el cuarto sospechoso y no se genera la orden de captura<br>
     * 7. Se pregunta si se genero la orden de captura<br>
     * 8. Se obtienen el segundo sospechoso y no se genera la orden de captura<br>
     * 9. Se pregunta si se genero la orden de captura<br>
     */
    public void testViajar( )
    {
        setupEscenario3( );
        assertEquals( "El nombre de la primera ciudad es inválido", "Bogotá", interpol.darCiudadActual( ).darNombreCiudad( ) );
        interpol.viajar( "Cartagena" );
        assertEquals( "El nombre de la segunda ciudad es inválido", "Cartagena", interpol.darCiudadActual( ).darNombreCiudad( ) );
        interpol.viajar( "Leticia" );
        assertEquals( "El nombre de la tercera ciudad es inválido", "Leticia", interpol.darCiudadActual( ).darNombreCiudad( ) );
    }

}