/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CiudadTest.java,v 1.2 2007/04/20 12:38:41 man-muno Exp $
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

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.interpol.mundo.Ciudad;
import uniandes.cupi2.interpol.mundo.CiudadNoAgredagaException;
import uniandes.cupi2.interpol.mundo.Lugar;

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
     * Construye una nueva ciudad vac�a, llamada Bogot�, con descripcion La capital de Colombia y con el sospechoso
     */
    private void setupEscenario1( )
    {
        ciudad = new Ciudad( "Bogot�", "La capital de Colombia", 3, true );
    }

    /**
     * Se construye una ciudad Bogot�, con dos hijos Cartagena y Barranquilla. Barranquilla tiene la ciudad San Andr�s como hija, y all� es donde est� el sospechoso
     */
    private void setupEscenario2( )
    {
        ciudad = new Ciudad( "Bogot�", "La capital de Colombia", 3, false );
        Ciudad ciudad1 = new Ciudad( "Cartagena", "Descripci�n cartagena", 3, false );
        Ciudad ciudad2 = new Ciudad( "Barranquilla", "Descripci�n Barranquilla", 4, false );
        Ciudad ciudad3 = new Ciudad( "San Andr�s", "Descripci�n San Andr�s", 5, true );
        try
        {
            ciudad.agregarCiudad( ciudad1, "Bogot�" );
            ciudad.agregarCiudad( ciudad2, "Bogot�" );
            ciudad.agregarCiudad( ciudad3, "Barranquilla" );
        }
        catch( CiudadNoAgredagaException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Construye una nueva ciudad vac�a, llamada Bogot�, con descripcion La capital de Colombia y con el sospechoso. Adem�s tiene tres lugares.
     */
    private void setupEscenario3( )
    {
        ciudad = new Ciudad( "Bogot�", "La capital de Colombia", 3, true );
        Lugar lugar1 = new Lugar( "Lugar 1", "Descripcion del Lugar 1", 3 );
        ciudad.agregarLugar( lugar1 );
        Lugar lugar2 = new Lugar( "Lugar 2", "Descripcion del Lugar 2", 6 );
        ciudad.agregarLugar( lugar2 );
        Lugar lugar3 = new Lugar( "Lugar 3", "Descripcion del Lugar 3", 23 );
        ciudad.agregarLugar( lugar3 );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo constructor y los analizadores<br>
     * <b> M�todos a probar: </b> <br>
     * Ciudad<br>
     * darAltura<br>
     * darCaminoRespuesta<br>
     * darCentral<br>
     * darCiudadesHijas<br>
     * darDerecho<br>
     * darDescripcion<br>
     * darInorden<br>
     * darIzquierdo<br>
     * darNombreCiudad<br>
     * darNombresLugares<br>
     * darNombresPosiblesDestinos<br>
     * darPeso<br>
     * darPostorden<br>
     * darPreorden<br>
     * darTiempoViaje<br>
     * estaSospechoso<br>
     * esHoja<br>
     * <b> Objetivo: </b> Probar que se construye correctamente el objeto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye una Ciudad con el nombre "Bogot�", la descripci�n "La capital de Colombia" y con tiempo de 3 y donde se encuentra el sospechoso<br>
     * 2. Se obtienen los datos de la ciudad creada y concuerdan con los ingresados<br>
     */
    public void testCiudadHoja( )
    {
        setupEscenario1( );
        assertEquals( "El peso de un �rbol de una hoja es de 1", 1, ciudad.darAltura( ) );
        assertNotNull( "El camino a la respuesta del �rbol de una hoja deber�a ser v�lido", ciudad.darCaminoRespuesta( ) );
        assertEquals( "El camino a la respuesta del �rbol de una hoja deber�a tener un elemento", 1, ciudad.darCaminoRespuesta( ).size( ) );
        assertNull( "El camino central de una hoja deber�a ser null", ciudad.darCentral( ) );
        assertNotNull( "A pesar que no tenga ciudades hijas, la lista no deber�a ser null", ciudad.darCiudadesHijas( ) );
        assertEquals( "La lista no deber�a tener elementos", 0, ciudad.darCiudadesHijas( ).size( ) );
        assertNull( "El hijo derecho deber�a ser null", ciudad.darDerecho( ) );
        assertNotNull( "La descripcion de la ciudad no deber�a ser null", ciudad.darDescripcion( ) );
        assertEquals( "La descripcion de la ciudad es incorrecta", "La capital de Colombia", ciudad.darDescripcion( ) );
        ArrayList lista = new ArrayList( );
        ciudad.darInorden( lista );
        assertNotNull( "La lista del recorrido inorden no deber�a ser null", lista );
        assertEquals( "El tama�o de la lista del recorrido deber�a ser 1", 1, lista.size( ) );
        assertNull( "El hijo izquierdo deber�a ser null", ciudad.darIzquierdo( ) );
        assertNotNull( "El nombre de la ciudad no deber�a ser null", ciudad.darNombreCiudad( ) );
        assertEquals( "El nombre de la ciudad es inv�lido", "Bogot�", ciudad.darNombreCiudad( ) );
        assertNotNull( "El arreglo de nombres de ciudades no deber�a ser null", ciudad.darNombresLugares( ) );
        assertEquals( "El tama�o del arreglo de nombres deber�a ser 0", 0, ciudad.darNombresLugares( ).size( ) );
        assertNotNull( "El vector de nombres de los posibles destinos no deber�a ser null", ciudad.darNombresPosiblesDestinos( ) );
        assertEquals( "El tama�o de la lista de los nombres de los posibles destinos deber�a ser 0", 0, ciudad.darNombresPosiblesDestinos( ).size( ) );
        assertEquals( "El peso de un �rbol de una hoja es 1", 1, ciudad.darPeso( ) );
        lista = new ArrayList( );
        ciudad.darPostorden( lista );
        assertNotNull( "La lista del recorrido postorden no deber�a ser null", lista );
        assertEquals( "El tama�o de la lista del recorrido deber�a ser 1", 1, lista.size( ) );
        lista = new ArrayList( );
        ciudad.darPreorden( lista );
        assertNotNull( "La lista del recorrido preorden no deber�a ser null", lista );
        assertEquals( "El tama�o de la lista del recorrido deber�a ser 1", 1, lista.size( ) );
        assertEquals( "El tiempo de viaje deber�a ser 3", 3, ciudad.darTiempoViaje( ) );
        assertTrue( "El sospechoso se tiene que encontrar en la ciudad ya que es una hoja", ciudad.estaSospechoso( ) );
        assertTrue( "La ciudad deber�a ser una hoja", ciudad.esHoja( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo agregarCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * agregarCiudad<br>
     * darCiudadesHijas<br>
     * darAltura<br>
     * esHoja<br>
     * <b> Objetivo: </b> Probar que se agrega correctamente tres ciudades a una hoja<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se agregan tres ciudades a la ciudad actual<br>
     * 2. La ciudad deja de ser hoja<br>
     * 3. La cantidad de ciudades aumenta<br>
     * 4. La altura del �rbol pasa a ser de 1 a 2<br>
     * 5. El peso del �rbol aumenta de acuerdo a la cantidad de ciudades agregadas<br>
     * 6. Al tratar de agregar una ciudad cuando la actual ya tiene sus hijos se lanza una excepci�n<br>
     */
    public void testAgregarCiudadHoja( )
    {
        setupEscenario1( );
        if( ciudad.esHoja( ) )
        {
            assertEquals( "La altura del �rbol deber�a ser 1", 1, ciudad.darAltura( ) );
            assertEquals( "El peso del �rbol deber�a ser 1", 1, ciudad.darPeso( ) );
            Ciudad ciudad1 = new Ciudad( "Cartagena", "Descripci�n cartagena", 3, false );
            try
            {
                ciudad.agregarCiudad( ciudad1, ciudad.darNombreCiudad( ) );
                assertFalse( "La ciudad no deber�a ser una hoja", ciudad.esHoja( ) );
                assertEquals( "El peso del �rbol deber�a ser 2", 2, ciudad.darPeso( ) );
            }
            catch( CiudadNoAgredagaException e )
            {
                fail( e.getMessage( ) );
            }
            if( ciudad.esHoja( ) )
            {
                fail( "La ciudad no puede ser una hoja" );
            }
            assertEquals( "La cantidad de ciudades deber�a ser 1", 1, ciudad.darCiudadesHijas( ).size( ) );
            assertEquals( "La altura del �rbol deber�a ser 2", 2, ciudad.darAltura( ) );
            Ciudad ciudad2 = new Ciudad( "Barranquilla", "Descripci�n Barranquilla", 4, false );
            try
            {
                ciudad.agregarCiudad( ciudad2, ciudad.darNombreCiudad( ) );
                assertFalse( "La ciudad no deber�a ser una hoja", ciudad.esHoja( ) );
                assertEquals( "El peso del �rbol deber�a ser 3", 3, ciudad.darPeso( ) );
            }
            catch( CiudadNoAgredagaException e )
            {
                fail( e.getMessage( ) );
            }
            if( ciudad.esHoja( ) )
            {
                fail( "La ciudad no puede ser una hoja" );
            }
            assertEquals( "La cantidad de ciudades deber�a ser 2", 2, ciudad.darCiudadesHijas( ).size( ) );
            assertEquals( "La altura del �rbol deber�a ser 2", 2, ciudad.darAltura( ) );
            Ciudad ciudad3 = new Ciudad( "San Andr�s", "Descripci�n San Andr�s", 5, true );
            try
            {
                ciudad.agregarCiudad( ciudad3, ciudad.darNombreCiudad( ) );
                assertFalse( "La ciudad no deber�a ser una hoja", ciudad.esHoja( ) );
                assertEquals( "El peso del �rbol deber�a ser 4", 4, ciudad.darPeso( ) );
            }
            catch( CiudadNoAgredagaException e )
            {
                fail( e.getMessage( ) );
            }
            assertEquals( "La altura del �rbol deber�a ser 2", 2, ciudad.darAltura( ) );
            if( ciudad.esHoja( ) )
            {
                fail( "La ciudad no puede ser una hoja" );
            }
            assertEquals( "La cantidad de ciudades deber�a ser 3", 3, ciudad.darCiudadesHijas( ).size( ) );
            try
            {
                ciudad.agregarCiudad( new Ciudad( "ad", "ADfs", 3, false ), "Bogot�" );
                fail( "No deber�a agregar una ciudad a un padre lleno" );
            }
            catch( CiudadNoAgredagaException e )
            {
                // Prueba correcta
            }
        }
        else
            fail( "Al iniciar la ciudad no debe tener hijos" );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo cambiarCentral<br>
     * <b> M�todos a probar: </b> <br>
     * cambiarCentral<br>
     * darCentral<br>
     * <b> Objetivo: </b> Probar que se cambia correctamente el hijo central de la hoja actual<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se cambia el hijo central de la hoja actual<br>
     */
    public void testCambiarCentral( )
    {
        setupEscenario1( );
        assertNull( "La ciudad central deber�a ser null", ciudad.darCentral( ) );
        ciudad.cambiarCentral( new Ciudad( "Ciudad Inexistente", "Descripci�n Ciudad Inexistente", 4, false ) );
        assertNotNull( "La ciudad central no deber�a ser null", ciudad.darCentral( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo cambiarDerecho<br>
     * <b> M�todos a probar: </b> <br>
     * cambiarDerecho<br>
     * darDerecho<br>
     * <b> Objetivo: </b> Probar que se cambia correctamente el hijo derecho de la hoja actual<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se cambia el hijo derecho de la hoja actual<br>
     */
    public void testCambiarDerecho( )
    {
        setupEscenario1( );
        assertNull( "La ciudad derecha deber�a ser null", ciudad.darDerecho( ) );
        ciudad.cambiarDerecho( new Ciudad( "Ciudad Inexistente", "Descripci�n Ciudad Inexistente", 4, false ) );
        assertNotNull( "La ciudad derecha no deber�a ser null", ciudad.darDerecho( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo cambiarIzquierdo<br>
     * <b> M�todos a probar: </b> <br>
     * cambiarIzquierdo<br>
     * darIzquierdo<br>
     * <b> Objetivo: </b> Probar que se cambia correctamente el hijo izquierdo de la hoja actual<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se cambia el hijo derecho de la hoja actual<br>
     */
    public void testCambiarIzquierdo( )
    {
        setupEscenario1( );
        assertNull( "La ciudad izquierda deber�a ser null", ciudad.darIzquierdo( ) );
        ciudad.cambiarIzquierdo( new Ciudad( "Ciudad Inexistente", "Descripci�n Ciudad Inexistente", 4, false ) );
        assertNotNull( "La ciudad central no deber�a ser null", ciudad.darIzquierdo( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo contarOcurrencias<br>
     * <b> M�todos a probar: </b> <br>
     * contarOcurrencias<br>
     * <b> Objetivo: </b> Probar que se cuenta correctamente la cantidad de ocurrencias en una hoja<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se cuentan correctamente las ocurrencias de una ciudad en una hoja<br>
     */
    public void testContarOcurrenciasHoja( )
    {
        setupEscenario1( );
        assertEquals( "Las ocurrencias de la ciudad deber�a ser 1", 1, ciudad.contarOcurrencias( "Bogot�" ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo constructor y los analizadores<br>
     * <b> M�todos a probar: </b> <br>
     * Ciudad<br>
     * darAltura<br>
     * darCaminoRespuesta<br>
     * darCentral<br>
     * darCiudadesHijas<br>
     * darDerecho<br>
     * darDescripcion<br>
     * darIzquierdo<br>
     * darNombreCiudad<br>
     * darNombresLugares<br>
     * darNombresPosiblesDestinos<br>
     * darPeso<br>
     * darTiempoViaje<br>
     * estaSospechoso<br>
     * esHoja<br>
     * <b> Objetivo: </b> Probar que se construye correctamente el objeto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye una Ciudad con el nombre "Bogot�", la descripci�n "La capital de Colombia" y con tiempo de 3 y donde se encuentra el sospechoso<br>
     * 2. Se obtienen los datos del sospechoso creado y concuerdan con los ingresados<br>
     */
    public void testCiudad( )
    {
        setupEscenario2( );
        assertEquals( "El peso de un �rbol es de 3", 3, ciudad.darAltura( ) );
        assertNotNull( "El camino a la respuesta del �rbol deber�a ser valido", ciudad.darCaminoRespuesta( ) );
        assertEquals( "El camino a la respuesta del �rbol deber�a tener 3 elementos", 3, ciudad.darCaminoRespuesta( ).size( ) );
        assertNotNull( "El camino central deber�a ser diferente de null", ciudad.darCentral( ) );
        assertNotNull( "La lista de ciudades hijas no deber�a ser null", ciudad.darCiudadesHijas( ) );
        assertEquals( "La lista deber�a tener elementos", 2, ciudad.darCiudadesHijas( ).size( ) );
        assertNotNull( "El hijo derecho no deber�a ser null", ciudad.darDerecho( ) );
        assertNotNull( "La descripcion de la ciudad no deber�a ser null", ciudad.darDescripcion( ) );
        assertEquals( "La descripcion de la ciudad es incorrecta", "La capital de Colombia", ciudad.darDescripcion( ) );
        assertNull( "El hijo izquierdo deber�a ser null", ciudad.darIzquierdo( ) );
        assertNotNull( "El nombre de la ciudad no deber�a ser null", ciudad.darNombreCiudad( ) );
        assertEquals( "El nombre de la ciudad es inv�lido", "Bogot�", ciudad.darNombreCiudad( ) );
        assertNotNull( "El arreglo de nombres de ciudades no deber�a ser null", ciudad.darNombresLugares( ) );
        assertEquals( "El tama�o del arreglo de nombres deber�a ser 0", 0, ciudad.darNombresLugares( ).size( ) );
        assertNotNull( "El vector de nombres de los posibles destinos no deber�a ser null", ciudad.darNombresPosiblesDestinos( ) );
        assertEquals( "El tama�o de la lista de los nombres de los posibles destinos deber�a ser 2", 2, ciudad.darNombresPosiblesDestinos( ).size( ) );
        assertEquals( "El peso de un �rbol de una hoja es 4", 4, ciudad.darPeso( ) );
        assertEquals( "El tiempo de viaje deber�a ser 3", 3, ciudad.darTiempoViaje( ) );
        assertFalse( "El sospechoso no se tiene que encontrar en la ciudad ya que es una hoja", ciudad.estaSospechoso( ) );
        assertFalse( "La ciudad no deber�a ser una hoja", ciudad.esHoja( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo agregarCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * agregarCiudad<br>
     * darCiudadesHijas<br>
     * darAltura<br>
     * esHoja<br>
     * <b> Objetivo: </b> Probar que se agrega correctamente tres ciudades al �rbol<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se agregan tres ciudades a la ciudad actual.<br>
     * 3. La cantidad de ciudades aumenta<br>
     * 4. La altura del �rbol pasa a ser de 2 a 4<br>
     * 5. El peso del �rbol aumenta de acuerdo a la cantidad de ciudades agregadas<br>
     * 6. Al tratar de agregar una ciudad cuando la actual ya tiene sus hijos se lanza una excepci�n<br>
     */
    public void testAgregarCiudad( )
    {
        setupEscenario2( );
        assertEquals( "La altura del �rbol deber�a ser 3", 3, ciudad.darAltura( ) );
        assertEquals( "El peso del �rbol deber�a ser 4", 4, ciudad.darPeso( ) );
        Ciudad ciudad1 = new Ciudad( "Medell�n", "Descripci�n Medell�n", 3, false );
        try
        {
            ciudad.agregarCiudad( ciudad1, ciudad.darNombreCiudad( ) );
            assertFalse( "La ciudad no deber�a ser una hoja", ciudad.esHoja( ) );
            assertEquals( "El peso del �rbol deber�a ser 5", 5, ciudad.darPeso( ) );
        }
        catch( CiudadNoAgredagaException e )
        {
            fail( e.getMessage( ) );
        }
        if( ciudad.esHoja( ) )
        {
            fail( "La ciudad no puede ser una hoja" );
        }
        assertEquals( "La cantidad de ciudades deber�a ser 3", 3, ciudad.darCiudadesHijas( ).size( ) );
        assertEquals( "La altura del �rbol deber�a ser 3", 3, ciudad.darAltura( ) );
        Ciudad ciudad2 = new Ciudad( "Pasto", "Descripci�n Pasto", 4, false );
        try
        {
            ciudad.agregarCiudad( ciudad2, "Cartagena" );
            assertFalse( "La ciudad no deber�a ser una hoja", ciudad.esHoja( ) );
            assertEquals( "El peso del �rbol deber�a ser 6", 6, ciudad.darPeso( ) );
        }
        catch( CiudadNoAgredagaException e )
        {
            fail( e.getMessage( ) );
        }
        if( ciudad.esHoja( ) )
        {
            fail( "La ciudad no puede ser una hoja" );
        }
        assertEquals( "La cantidad de ciudades deber�a ser 3", 3, ciudad.darCiudadesHijas( ).size( ) );
        assertEquals( "La altura del �rbol deber�a ser 3", 3, ciudad.darAltura( ) );
        Ciudad ciudad3 = new Ciudad( "Leticia", "Descripci�n Leticia", 5, true );
        try
        {
            ciudad.agregarCiudad( ciudad3, "Pasto" );
            assertFalse( "La ciudad no deber�a ser una hoja", ciudad.esHoja( ) );
            assertEquals( "El peso del �rbol deber�a ser 7", 7, ciudad.darPeso( ) );
        }
        catch( CiudadNoAgredagaException e )
        {
            fail( e.getMessage( ) );
        }
        assertEquals( "La altura del �rbol deber�a ser 4", 4, ciudad.darAltura( ) );
        if( ciudad.esHoja( ) )
        {
            fail( "La ciudad no puede ser una hoja" );
        }
        assertEquals( "La cantidad de ciudades deber�a ser 3", 3, ciudad.darCiudadesHijas( ).size( ) );
        try
        {
            ciudad.agregarCiudad( new Ciudad( "ad", "ADfs", 3, false ), "Bogot�" );
            fail( "No deber�a agregar una ciudad a un padre lleno" );
        }
        catch( CiudadNoAgredagaException e )
        {
            // Prueba correcta
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo agregarLugar<br>
     * <b> M�todos a probar: </b> <br>
     * agregarLugar<br>
     * darNombresLugares<br>
     * <b> Objetivo: </b> Probar que se agrega correctamente tres lugares al �rbol<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se agregan tres lugares a la ciudad actual<br>
     * 2. Al tratar de agregar un lugar cuando la ciudad actual ya tiene sus lugares completos se lanza una excepci�n<br>
     */
    public void testAgregarLugar( )
    {
        setupEscenario2( );
        Lugar lugar1 = new Lugar( "Lugar 1", "Descripcion del Lugar 1", 3 );
        ciudad.agregarLugar( lugar1 );
        assertEquals( "La cantidad de lugares que tiene la ciudad es incorrecta", 1, ciudad.darNombresLugares( ).size( ) );
        Lugar lugar2 = new Lugar( "Lugar 2", "Descripcion del Lugar 2", 6 );
        ciudad.agregarLugar( lugar2 );
        assertEquals( "La cantidad de lugares que tiene la ciudad es incorrecta", 2, ciudad.darNombresLugares( ).size( ) );
        Lugar lugar3 = new Lugar( "Lugar 3", "Descripcion del Lugar 3", 23 );
        ciudad.agregarLugar( lugar3 );
        assertEquals( "La cantidad de lugares que tiene la ciudad es incorrecta", 3, ciudad.darNombresLugares( ).size( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo contarOcurrencias<br>
     * <b> M�todos a probar: </b> <br>
     * contarOcurrencias<br>
     * <b> Objetivo: </b> Probar que se cuenta correctamente la cantidad de ocurrencias en una hoja<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se cuentan correctamente las ocurrencias de una ciudad en una hoja<br>
     */
    public void testContarOcurrencias( )
    {
        setupEscenario2( );
        assertEquals( "La cantidad de ocurrencias de Cartagena es 1", 1, ciudad.contarOcurrencias( "Cartagena" ) );
        try
        {
            ciudad.agregarCiudad( new Ciudad( "Cartagena", "Ciudad de prueba", 3, false ), "Barranquilla" );
            assertEquals( "La cantidad de ocurrencias de Cartagena es 2", 2, ciudad.contarOcurrencias( "Cartagena" ) );
        }
        catch( CiudadNoAgredagaException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darAltura<br>
     * <b> M�todos a probar: </b> <br>
     * darAltura<br>
     * agregarCiudad<br>
     * <b> Objetivo: </b> Probar que se calcula correctamente la altura al ir agrando correctamente tres ciudades al �rbol<br>
     * <b> Resultados esperados: </b> <br>
     * 1. De acuerdo al orden en que son agregadas las ciudades se va calcula correctamente el peso del �rbol<br>
     */
    public void testDarAltura( )
    {
        setupEscenario2( );
        assertEquals( "La altura del �rbol deber�a ser 3", 3, ciudad.darAltura( ) );
        assertEquals( "El peso del �rbol deber�a ser 4", 4, ciudad.darPeso( ) );
        Ciudad ciudad1 = new Ciudad( "Medell�n", "Descripci�n Medell�n", 3, false );
        try
        {
            ciudad.agregarCiudad( ciudad1, ciudad.darNombreCiudad( ) );
            assertEquals( "El peso del �rbol deber�a ser 5", 5, ciudad.darPeso( ) );
            assertEquals( "La altura del �rbol deber�a ser 3", 3, ciudad.darAltura( ) );
            Ciudad ciudad2 = new Ciudad( "Pasto", "Descripci�n Pasto", 4, false );
            ciudad.agregarCiudad( ciudad2, "Cartagena" );
            assertEquals( "El peso del �rbol deber�a ser 6", 6, ciudad.darPeso( ) );
            assertEquals( "La altura del �rbol deber�a ser 3", 3, ciudad.darAltura( ) );
            Ciudad ciudad3 = new Ciudad( "Leticia", "Descripci�n Leticia", 5, true );
            ciudad.agregarCiudad( ciudad3, "Pasto" );
            assertEquals( "El peso del �rbol deber�a ser 7", 7, ciudad.darPeso( ) );
            assertEquals( "La altura del �rbol deber�a ser 4", 4, ciudad.darAltura( ) );
        }
        catch( CiudadNoAgredagaException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darCaminoRespuesta<br>
     * <b> M�todos a probar: </b> <br>
     * darCaminoRespuesta<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente el camino hacia la hoja con el sospechoso<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se agregan tres ciudades a la ciudad actual.<br>
     * 3. La cantidad de ciudades aumenta<br>
     * 4. La altura del �rbol pasa a ser de 2 a 4<br>
     * 5. El peso del �rbol aumenta de acuerdo a la cantidad de ciudades agregadas<br>
     * 6. Al tratar de agregar una ciudad cuando la actual ya tiene sus hijos se lanza una excepci�n<br>
     */
    public void testDarCaminoRespuesta( )
    {
        setupEscenario2( );
        ArrayList respuesta = ciudad.darCaminoRespuesta( );
        assertNotNull( "La lista del camino de respuesta no puede ser null", respuesta );
        assertEquals( "La lista del camino de respuesta debe tener 3 elementos", 3, respuesta.size( ) );
        for( int i = 0; i < respuesta.size( ); i++ )
        {
            switch( i )
            {
                case 0:
                    assertEquals( "El camino hacia la hoja con el sospechoso es incorrecto", "Bogot�", ( String )respuesta.get( 0 ) );
                    break;
                case 1:
                    assertEquals( "El camino hacia la hoja con el sospechoso es incorrecto", "Barranquilla", ( String )respuesta.get( 1 ) );
                    break;
                case 2:
                    assertEquals( "El camino hacia la hoja con el sospechoso es incorrecto", "San Andr�s", ( String )respuesta.get( 2 ) );
                    break;
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darCentral<br>
     * <b> M�todos a probar: </b> <br>
     * darCentral<br>
     * cambiarCentral<br>
     * darNombreCiudad<br>
     * darDescripcion<br>
     * darTiempoViaje<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente el hijo central<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se pregunta cual es el hijo central de una hoja.<br>
     * 3. Se cambia el hijo central por una ciudad valida<br>
     * 4. Se verifica que el hijo central sea diferente de null<br>
     * 5. Se verifican que los atributos de la ciudad agregada sean correctos<br>
     */
    public void testDarCentral( )
    {
        setupEscenario1( );
        assertNull( "La ciudad central deber�a ser null", ciudad.darCentral( ) );
        ciudad.cambiarCentral( new Ciudad( "Nombre", "Descripcion", 4, false ) );
        assertNotNull( "La ciudad central no deber�a ser null", ciudad.darCentral( ) );
        assertEquals( "El nombre de la ciudad es incorrecto", "Nombre", ciudad.darCentral( ).darNombreCiudad( ) );
        assertEquals( "La descripcion de la ciudad es incorrecta", "Descripcion", ciudad.darCentral( ).darDescripcion( ) );
        assertEquals( "La duraci�n del viaje a la ciudad es incorrecto", 4, ciudad.darCentral( ).darTiempoViaje( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * darCiudad<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente una ciudad que esta en la rama de la ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se encuentra una ciudad que esta en la rama<br>
     * 2. No se encuentra una ciudad que no esta en la rama<br>
     */
    public void testDarCiudad( )
    {
        setupEscenario2( );
        assertNotNull( "Deber�a obtener una ciudad que existe", ciudad.darCiudad( "Cartagena" ) );
        assertNull( "No deber�a poder obtener una ciudad que no existe", ciudad.darCiudad( "Medell�n" ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darCiudadesHijas<br>
     * <b> M�todos a probar: </b> <br>
     * darCiudadesHijas<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente la cantidad de ciudades hijas<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene correctamente la lista de ciudades hijas<br>
     */
    public void testDarCiudadesHijas( )
    {
        setupEscenario2( );
        ArrayList respuesta = ( ArrayList )ciudad.darCiudadesHijas( );
        assertNotNull( "La lista de ciudades hijas no puede ser null", respuesta );
        assertEquals( "La lista de ciudades hijas deber�a tener dos elementos", 2, respuesta.size( ) );
        for( int i = 0; i < respuesta.size( ); i++ )
        {
            Ciudad ciudad = ( Ciudad )respuesta.get( i );
            if( ! ( ciudad.darNombreCiudad( ).equals( "Barranquilla" ) || ciudad.darNombreCiudad( ).equals( "Cartagena" ) ) )
            {
                fail( "Las ciudades hijas est�n mal construidas" );
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darDerecho<br>
     * <b> M�todos a probar: </b> <br>
     * darDerecho<br>
     * cambiarDerecho<br>
     * darNombreCiudad<br>
     * darDescripcion<br>
     * darTiempoViaje<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente el hijo derecho<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se pregunta cual es el hijo derecho de una hoja.<br>
     * 3. Se cambia el hijo derecho por una ciudad valida<br>
     * 4. Se verifica que el hijo derecho sea diferente de null<br>
     * 5. Se verifican que los atributos de la ciudad agregada sean correctos<br>
     */
    public void testDarDerecho( )
    {
        setupEscenario1( );
        assertNull( "La ciudad derecha deber�a ser null", ciudad.darDerecho( ) );
        ciudad.cambiarDerecho( new Ciudad( "Nombre", "Descripcion", 4, false ) );
        assertNotNull( "La ciudad derecha no deber�a ser null", ciudad.darDerecho( ) );
        assertEquals( "El nombre de la ciudad es incorrecto", "Nombre", ciudad.darDerecho( ).darNombreCiudad( ) );
        assertEquals( "La descripcion de la ciudad es incorrecta", "Descripcion", ciudad.darDerecho( ).darDescripcion( ) );
        assertEquals( "La duraci�n del viaje a la ciudad es incorrecto", 4, ciudad.darDerecho( ).darTiempoViaje( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darInorden<br>
     * <b> M�todos a probar: </b> <br>
     * darInorden<br>
     * <b> Objetivo: </b> Probar que se obtenga el recorrido en inorden de un �rbol.<br>
     * <b> Resultados esperados: </b> <br>
     * Se crea una lista para guardar los resultados, y despu�s de llamar el m�todo esta lista tiene los elementos organizados en inorden<br>
     */
    public void testDarInorden( )
    {
        setupEscenario2( );
        ArrayList respuesta = new ArrayList( );
        ciudad.darInorden( respuesta );
        assertNotNull( "La lista del recorrido no deber�a ser null", respuesta );
        for( int i = 0; i < respuesta.size( ); i++ )
        {
            switch( i )
            {
                case 0:
                    String ciudad = ( String )respuesta.get( 0 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "Bogot�", ciudad );
                    break;
                case 1:
                    ciudad = ( String )respuesta.get( 1 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "Barranquilla", ciudad );
                    break;
                case 2:
                    ciudad = ( String )respuesta.get( 2 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "San Andr�s", ciudad );
                    break;
                case 3:
                    ciudad = ( String )respuesta.get( 3 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "Cartagena", ciudad );
                    break;
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darIzquierdo<br>
     * <b> M�todos a probar: </b> <br>
     * darIzquierdo<br>
     * cambiarIzquierdo<br>
     * darNombreCiudad<br>
     * darDescripcion<br>
     * darTiempoViaje<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente el hijo izquierdo<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se pregunta cual es el hijo izquierdo de una hoja.<br>
     * 3. Se cambia el hijo izquierdo por una ciudad valida<br>
     * 4. Se verifica que el hijo izquierdo sea diferente de null<br>
     * 5. Se verifican que los atributos de la ciudad agregada sean correctos<br>
     */
    public void testDarIzquierdo( )
    {
        setupEscenario1( );
        assertNull( "La ciudad izquierda deber�a ser null", ciudad.darIzquierdo( ) );
        ciudad.cambiarIzquierdo( new Ciudad( "Nombre", "Descripcion", 4, false ) );
        assertNotNull( "La ciudad izquierda no deber�a ser null", ciudad.darIzquierdo( ) );
        assertEquals( "El nombre de la ciudad es incorrecto", "Nombre", ciudad.darIzquierdo( ).darNombreCiudad( ) );
        assertEquals( "La descripcion de la ciudad es incorrecta", "Descripcion", ciudad.darIzquierdo( ).darDescripcion( ) );
        assertEquals( "La duraci�n del viaje a la ciudad es incorrecto", 4, ciudad.darIzquierdo( ).darTiempoViaje( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darLugar<br>
     * <b> M�todos a probar: </b> <br>
     * darLugar<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente un lugar<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene un lugar<br>
     * 3. Se verifican los datos del lugar obtenido<br>
     * 4. No se obtiene un lugar inv�lido<br>
     */
    public void testDarLugar( )
    {
        setupEscenario3( );
        assertNotNull( "El lugar Lugar1 deber�a ser valido", ciudad.darLugar( "Lugar 1" ) );
        assertEquals( "La descripcion del lugar es incorrecta", "Descripcion del Lugar 1", ciudad.darLugar( "Lugar 1" ).darPista( ) );
        assertEquals( "El tiempo gastado es incorrecto", 3, ciudad.darLugar( "Lugar 1" ).darTiempoGastado( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darNombreCiudad<br>
     * <b> M�todos a probar: </b> <br>
     * darNombreCiudad<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente el nombre de la ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene un nombre de la ciudad valido<br>
     * 3. Se obtiene el nombre de la ciudad correcto<br>
     */
    public void testDarNombreCiudad( )
    {
        setupEscenario1( );
        assertNotNull( "El nombre de la ciudad es inv�lido", ciudad.darNombreCiudad( ) );
        assertEquals( "El nombre de la ciudad es incorrecto", "Bogot�", ciudad.darNombreCiudad( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darNombresLugares<br>
     * <b> M�todos a probar: </b> <br>
     * darNombresLugares<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente los nombres de los lugares de la ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene una lista valida de los lugares validos<br>
     * 3. Se obtiene una lista valida de los lugares correctos<br>
     */
    public void testDarNombresLugares( )
    {
        setupEscenario3( );
        ArrayList nombres = ( ArrayList )ciudad.darNombresLugares( );
        assertNotNull( "El nombre del lugar es inv�lido", ( String )nombres.get( 0 ) );
        assertEquals( "El nombre del lugar 1 es incorrecto", "Lugar 1", ( String )nombres.get( 0 ) );

        assertNotNull( "El nombre del lugar es inv�lido", ( String )nombres.get( 1 ) );
        assertEquals( "El nombre del lugar 1 es incorrecto", "Lugar 2", ( String )nombres.get( 1 ) );

        assertNotNull( "El nombre del lugar es inv�lido", ( String )nombres.get( 2 ) );
        assertEquals( "El nombre del lugar 1 es incorrecto", "Lugar 3", ( String )nombres.get( 2 ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darNombresLugares<br>
     * <b> M�todos a probar: </b> <br>
     * darNombresLugares<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente los nombres de los posibles destinos de la ciudad (los hijos)<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene una lista valida de los posibles destinos validos<br>
     * 3. Se obtiene una lista valida de los posibles destinos correctos<br>
     */
    public void testDarNombresPosiblesDestinos( )
    {
        setupEscenario2( );
        ArrayList nombres = ( ArrayList )ciudad.darNombresPosiblesDestinos( );
        assertNotNull( "La lista de los posibles destinos es invalida", nombres );
        assertEquals( "El tama�o de la lista de los posibles destinos es incorrecta", 2, nombres.size( ) );
        assertTrue( "La lista deber�a contener a Cartagena", nombres.contains( "Cartagena" ) );
        assertTrue( "La lista deber�a contener a Barranquilla", nombres.contains( "Barranquilla" ) );
        assertFalse( "La lista no deber�a contener a San Andr�s", nombres.contains( "San Andr�s" ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darPeso<br>
     * <b> M�todos a probar: </b> <br>
     * darPeso<br>
     * agregarCiudad<br>
     * <b> Objetivo: </b> Probar que se calcula correctamente el peso al ir agregando correctamente tres ciudades al �rbol<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Cada vez que se agrega una nueva ciudad el peso se re-calcula<br>
     */
    public void testDarPeso( )
    {
        setupEscenario2( );
        assertEquals( "El peso del �rbol deber�a ser 4", 4, ciudad.darPeso( ) );
        Ciudad ciudad1 = new Ciudad( "Medell�n", "Descripci�n Medell�n", 3, false );
        try
        {
            ciudad.agregarCiudad( ciudad1, ciudad.darNombreCiudad( ) );
            assertEquals( "El peso del �rbol deber�a ser 5", 5, ciudad.darPeso( ) );
            Ciudad ciudad2 = new Ciudad( "Pasto", "Descripci�n Pasto", 4, false );
            ciudad.agregarCiudad( ciudad2, "Cartagena" );
            assertEquals( "El peso del �rbol deber�a ser 6", 6, ciudad.darPeso( ) );
            Ciudad ciudad3 = new Ciudad( "Leticia", "Descripci�n Leticia", 5, true );
            ciudad.agregarCiudad( ciudad3, "Pasto" );
            assertEquals( "El peso del �rbol deber�a ser 7", 7, ciudad.darPeso( ) );
        }
        catch( CiudadNoAgredagaException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darPostorden<br>
     * <b> M�todos a probar: </b> <br>
     * darPostorden<br>
     * <b> Objetivo: </b> Probar que se obtenga el recorrido en postorden de un �rbol.<br>
     * <b> Resultados esperados: </b> <br>
     * Se crea una lista para guardar los resultados, y despu�s de llamar el m�todo esta lista tiene los elementos organizados en postorden<br>
     */
    public void testDarPostorden( )
    {
        setupEscenario2( );
        ArrayList respuesta = new ArrayList( );
        ciudad.darPostorden( respuesta );
        assertNotNull( "La lista del recorrido no deber�a ser null", respuesta );
        for( int i = 0; i < respuesta.size( ); i++ )
        {
            switch( i )
            {
                case 0:
                    String ciudad = ( String )respuesta.get( 0 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "San Andr�s", ciudad );
                    break;
                case 1:
                    ciudad = ( String )respuesta.get( 1 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "Barranquilla", ciudad );
                    break;
                case 2:
                    ciudad = ( String )respuesta.get( 2 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "Cartagena", ciudad );
                    break;
                case 3:
                    ciudad = ( String )respuesta.get( 3 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "Bogot�", ciudad );
                    break;
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darPreorden<br>
     * <b> M�todos a probar: </b> <br>
     * darPreorden<br>
     * <b> Objetivo: </b> Probar que se obtenga el recorrido en preorden de un �rbol.<br>
     * <b> Resultados esperados: </b> <br>
     * Se crea una lista para guardar los resultados, y despu�s de llamar el m�todo esta lista tiene los elementos organizados en preorden<br>
     */
    public void testDarPreorden( )
    {
        setupEscenario2( );
        ArrayList respuesta = new ArrayList( );
        ciudad.darPreorden( respuesta );
        assertNotNull( "La lista del recorrido no deber�a ser null", respuesta );
        for( int i = 0; i < respuesta.size( ); i++ )
        {
            switch( i )
            {
                case 0:
                    String ciudad = ( String )respuesta.get( 0 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "Bogot�", ciudad );
                    break;
                case 1:
                    ciudad = ( String )respuesta.get( 1 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "Cartagena", ciudad );
                    break;
                case 2:
                    ciudad = ( String )respuesta.get( 2 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "Barranquilla", ciudad );
                    break;
                case 3:
                    ciudad = ( String )respuesta.get( 3 );
                    assertEquals( "El nombre del primer elemento es inv�lido", "San Andr�s", ciudad );
                    break;
            }
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darTiempoViaje<br>
     * <b> M�todos a probar: </b> <br>
     * darTiempoViaje<br>
     * <b> Objetivo: </b> Probar que se obtenga correctamente el tiempo de viaje a la ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene correctamente el tiempo de viaje a la ciudad<br>
     */
    public void testDarTiempoViaje( )
    {
        setupEscenario2( );
        assertEquals( "El tiempo de viaje deber�a ser 3", 3, ciudad.darTiempoViaje( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo esHoja<br>
     * <b> M�todos a probar: </b> <br>
     * esHoja<br>
     * <b> Objetivo: </b> Probar que calcule correctamente que la ciudad sea una hoja<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Dado que la ciudad no es una hoja, deber�a informarlo<br>
     */
    public void testEsHoja( )
    {
        setupEscenario1( );
        assertTrue( "La ciudad deber�a ser una hoja", ciudad.esHoja( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo esHoja<br>
     * <b> M�todos a probar: </b> <br>
     * esHoja<br>
     * <b> Objetivo: </b> Probar que calcule correctamente que la ciudad sea una hoja<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Dado que la ciudad es una hoja, deber�a informarlo<br>
     */
    public void testEsHojaArbol( )
    {
        setupEscenario2( );
        assertFalse( "La ciudad no deber�a ser una hoja", ciudad.esHoja( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo estaSospechoso<br>
     * <b> M�todos a probar: </b> <br>
     * estaSospechoso<br>
     * <b> Objetivo: </b> Probar que calcule correctamente que el sospechoso se encuentra en la ciudad<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Informa correctamente que una ciudad que contenga al sospechoso<br>
     * 2. Informa correctamente que una ciudad que no contenga al sospechoso<br>
     */
    public void testEstaSospechoso( )
    {
        setupEscenario1( );
        assertTrue( "En la ciudad deber�a encontrarse el sospechoso", ciudad.estaSospechoso( ) );
        setupEscenario2( );
        assertFalse( "En la ciudad no deber�a encontrarse el sospechoso", ciudad.estaSospechoso( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo viajar<br>
     * <b> M�todos a probar: </b> <br>
     * viajar<br>
     * <b> Objetivo: </b> Probar que viaje correctamente a la ciudad dada<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Retorna correctamente la ciudad a donde se quiere viajar<br>
     * 2. Retorna null cuando se quiere viajar a una ciudad inexistente o que no es hija<br>
     */
    public void testViajar( )
    {
        setupEscenario2( );
        Ciudad ciudad1 = ciudad.viajar( ( String )ciudad.darNombresPosiblesDestinos( ).get( 0 ) );
        assertNotNull( "La ciudad donde se viajo no deber�a ser null", ciudad1 );
        assertEquals( "La ciudad obtenida es incorrecta", "Cartagena", ciudad1.darNombreCiudad( ) );
        Ciudad ciudad2 = ciudad.viajar( "Ciudad Inexistente" );
        assertNull( "La ciudad a viajar no deber�a ser valida", ciudad2 );
    }

}