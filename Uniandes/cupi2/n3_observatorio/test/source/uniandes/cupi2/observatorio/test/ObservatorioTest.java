/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ObservatorioTest.java,v 1.4 2007/06/28 22:46:45 camil-ji Exp $
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
import uniandes.cupi2.observatorio.mundo.SateliteNatural;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Observatorio est�n correctamente implementados
 */
public class ObservatorioTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Observatorio observatorio;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Observatorio vac�a
     * 
     */
    private void setupEscenario1( )
    {
        observatorio = new Observatorio( );
    }

    /**
     * Construye una nueva Observatorio con una luna en j�piter
     * 
     */
    private void setupEscenario2( )
    {
        observatorio = new Observatorio( );
        observatorio.agregarSateliteNatural( Observatorio.NOMBRE_JUPITER, "Io", 0.041, 0.040 );
    }

    /**
     * Prueba 1
     */
    public void testObservatorio( )
    {
        setupEscenario1( );

    }

    /**
     * Este m�todo se encarga de verificar el m�todo agregarSateliteNatural<br>
     * <b> M�todos a probar: </b> <br>
     * agregarSateliteNatural<br>
     * obtenerSateliteNatural<br>
     * <b> Objetivo: </b> Probar la inserci�n de un nuevo sat�lite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un sat�lite natural que no existe, retorne null. 2. Creaci�n de un nuevo sat�lite natural con el nombre Io con excentricidad 0.041 e inclinaci�n 0.040 y
     * retorne true<br>
     */
    public void testAgregarSateliteNatural( )
    {
        setupEscenario1( );
        assertNull( "El sat�lite natural Io no deber�a existir para J�piter.", observatorio.obtenerSateliteNatural( Observatorio.NOMBRE_JUPITER, "Io" ) );
        assertTrue( "El sat�lite natural debi� haberse agregado", observatorio.agregarSateliteNatural( Observatorio.NOMBRE_JUPITER, "Io", 0.041, 0.040 ) );
        assertFalse( "El sat�lite natural no debi� haberse agregado", observatorio.agregarSateliteNatural( Observatorio.NOMBRE_JUPITER, "Io", 0.041, 0.040 ) );
        SateliteNatural io = observatorio.obtenerSateliteNatural( Observatorio.NOMBRE_JUPITER, "Io" );
        assertNotNull( "El sat�lite natural Io deber�a existir para J�piter.", io );
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
        SateliteNatural io = observatorio.obtenerSateliteNatural( Observatorio.NOMBRE_JUPITER, "Io" );
        assertNotNull( "El sat�lite natural Io deber�a existir para J�piter.", io );
        assertEquals( "No creo el sat�lite natural con la excentricidad correcta", 0.041, io.obtenerExcentricidad( ) );
        assertEquals( "No creo el sat�lite natural con la inclinaci�n correcta", 0.040, io.obtenerInclinacion( ) );
        observatorio.editarSateliteNatural( Observatorio.NOMBRE_JUPITER, "Io", 0.5, 0.05 );
        io = observatorio.obtenerSateliteNatural( Observatorio.NOMBRE_JUPITER, "Io" );
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
        SateliteNatural io = observatorio.obtenerSateliteNatural( Observatorio.NOMBRE_JUPITER, "Io" );
        assertNotNull( "El sat�lite natural Io deber�a existir para J�piter.", io );
        observatorio.eliminarSatelite( Observatorio.NOMBRE_JUPITER, "Io" );
        io = observatorio.obtenerSateliteNatural( Observatorio.NOMBRE_JUPITER, "Io" );
        assertNull( "El sat�lite natural Io no deber�a existir para J�piter.", io );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerNombresPlanetas<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerNombresPlanetas<br>
     * <b> Objetivo: </b> Probar la eliminaci�n de un sat�lite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener los nombres de todos los planetas 2. Verificar que los nombres son los mismos de las constantes. 3. Verificar que la cantidad de los nombres sea la misma de
     * los plantas.
     */
    public void testObtenerNombresPlanetas( )
    {
        setupEscenario1( );
        String[] nombres = observatorio.obtenerNombresPlanetas( );
        int i = 0;
        for( ; i < nombres.length; i++ )
        {
            if( nombres[ i ] != Observatorio.NOMBRE_JUPITER && nombres[ i ] != Observatorio.NOMBRE_MARTE && nombres[ i ] != Observatorio.NOMBRE_MERCURIO && nombres[ i ] != Observatorio.NOMBRE_NEPTUNO && nombres[ i ] != Observatorio.NOMBRE_SATURNO
                    && nombres[ i ] != Observatorio.NOMBRE_TIERRA && nombres[ i ] != Observatorio.NOMBRE_URANO && nombres[ i ] != Observatorio.NOMBRE_VENUS )
                fail( "Uno de los nombres retornados no pertenece a los definidos en las constantes" );
        }
        if( i != Observatorio.CANTIDAD_PLANETAS )
            fail( "La cantidad de planetas retornados no corresponde al tama�o del arreglo de planetas" );

    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerPlanetasPorDistancia<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerPlanetasPorDistancia<br>
     * <b> Objetivo: </b> Probar que se obtienen correctamente la cantidad de planetas que cumplen el criterio de distancia <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener ning�n planeta cuando se quiere buscar por distancia 0. 2. Obtener 6 planetas cuando se quiere buscar por distancia 5. 3. Obtener 8 planetas cuando se quiere
     * buscar por distancia 20.
     */
    public void testObtenerPlanetasPorDistancia( )
    {
        setupEscenario1( );
        ArrayList planetas = observatorio.obtenerPlanetasPorDistancia( 0 );
        assertNotNull( "La lista de planetas no puede ser null", planetas );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 0", 0, planetas.size( ) );
        planetas = observatorio.obtenerPlanetasPorDistancia( 5 );
        assertNotNull( "La lista de planetas no puede ser null", planetas );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 6", 6, planetas.size( ) );
        planetas = observatorio.obtenerPlanetasPorDistancia( 50 );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 8", 8, planetas.size( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerPlanetasPorInclinacion<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerPlanetasPorDistancia<br>
     * <b> Objetivo: </b> Probar que se obtienen correctamente la cantidad de planetas que cumplen el criterio de inclinaci�n <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener 1 planetas cuando se quiere buscar por inclinaci�n de j�piter. 2. Obtener 3 planetas cuando se quiere buscar por inclinaci�n de marte. 3. Obtener 5 planetas
     * cuando se quiere buscar por inclinaci�n de mercurio. 4. Obtener 2 planetas cuando se quiere buscar por inclinaci�n de neptuno. 5. Obtener 4 planetas cuando se quiere
     * buscar por inclinaci�n de saturno. 6. Obtener 6 planetas cuando se quiere buscar por inclinaci�n de tierra. 7. Obtener 0 planetas cuando se quiere buscar por
     * inclinaci�n de urano. 8. Obtener 7 planetas cuando se quiere buscar por inclinaci�n de venus.
     */
    public void testObtenerPlanetasPorInclinacion( )
    {
        setupEscenario1( );
        ArrayList planetas = observatorio.obtenerPlanetasPorInclinacion( "" );
        assertNotNull( "La lista de planetas no puede ser null", planetas );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 0", 0, planetas.size( ) );
        planetas = observatorio.obtenerPlanetasPorInclinacion( Observatorio.NOMBRE_JUPITER );
        assertNotNull( "La lista de planetas no puede ser null", planetas );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 1", 1, planetas.size( ) );
        planetas = observatorio.obtenerPlanetasPorInclinacion( Observatorio.NOMBRE_MARTE );
        assertNotNull( "La lista de planetas no puede ser null", planetas );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 3", 3, planetas.size( ) );
        planetas = observatorio.obtenerPlanetasPorInclinacion( Observatorio.NOMBRE_MERCURIO );
        assertNotNull( "La lista de planetas no puede ser null", planetas );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 5", 5, planetas.size( ) );
        planetas = observatorio.obtenerPlanetasPorInclinacion( Observatorio.NOMBRE_NEPTUNO );
        assertNotNull( "La lista de planetas no puede ser null", planetas );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 2", 2, planetas.size( ) );
        planetas = observatorio.obtenerPlanetasPorInclinacion( Observatorio.NOMBRE_SATURNO );
        assertNotNull( "La lista de planetas no puede ser null", planetas );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 4", 4, planetas.size( ) );
        planetas = observatorio.obtenerPlanetasPorInclinacion( Observatorio.NOMBRE_TIERRA );
        assertNotNull( "La lista de planetas no puede ser null", planetas );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 6", 6, planetas.size( ) );
        planetas = observatorio.obtenerPlanetasPorInclinacion( Observatorio.NOMBRE_URANO );
        assertNotNull( "La lista de planetas no puede ser null", planetas );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 0", 0, planetas.size( ) );
        planetas = observatorio.obtenerPlanetasPorInclinacion( Observatorio.NOMBRE_VENUS );
        assertNotNull( "La lista de planetas no puede ser null", planetas );
        assertEquals( "La cantidad de planetas que cumplen el criterio deber�an ser 7", 7, planetas.size( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerSateliteNatural<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerSateliteNatural<br>
     * <b> Objetivo: </b> Probar que se obtiene correctamente un sat�lite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un sat�lite natural que no existe, retorne null. 2. Al buscar un sat�lite natural que existe, retorne uno con el nombre Io con excentricidad 0.041 e
     * inclinaci�n 0.040 y retorne true<br>
     */
    public void testObtenerSateliteNatural( )
    {
        setupEscenario2( );
        SateliteNatural io = observatorio.obtenerSateliteNatural( Observatorio.NOMBRE_JUPITER, "" );
        assertNull( "Al no existir el sat�lite natural, deber�a retornar null", io );
        io = observatorio.obtenerSateliteNatural( Observatorio.NOMBRE_JUPITER, "Io" );
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
        setupEscenario2( );
        ArrayList satelites = observatorio.obtenerSatelitesNaturales( Observatorio.NOMBRE_MARTE );
        assertNotNull( "La lista de sat�lites naturales no deber�a ser null", satelites );
        assertEquals( "La lista de sat�lites naturales deber�a ser vac�a", 0, satelites.size( ) );
        satelites = observatorio.obtenerSatelitesNaturales( Observatorio.NOMBRE_JUPITER );
        assertNotNull( "La lista de sat�lites naturales no deber�a ser null", satelites );
        assertEquals( "La lista de sat�lites naturales no deber�a ser vac�a", 1, satelites.size( ) );

    }

}