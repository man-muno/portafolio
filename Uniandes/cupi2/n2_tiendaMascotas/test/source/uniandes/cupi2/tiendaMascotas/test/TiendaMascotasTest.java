/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: TiendaMascotasTest.java,v 1.5 2007/06/25 19:02:31 camil-ji Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_tiendaMascotas
 * Autor: Manuel Mu�oz - 08-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.tiendaMascotas.test;

import junit.framework.TestCase;
import uniandes.cupi2.tiendaMascotas.mundo.Mascota;
import uniandes.cupi2.tiendaMascotas.mundo.TiendaMascotas;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase TiendaMascotas est�n correctamente implementados
 */
public class TiendaMascotasTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private TiendaMascotas tiendaMascotas;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva TiendaMascotas vac�a
     */
    private void setupEscenario1( )
    {
        tiendaMascotas = new TiendaMascotas( );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo constructor<br>
     * <b> M�todos a probar: </b> <br>
     * TiendaMascotas<br>
     * obtenerMascota<br>
     * <b> Objetivo: </b> Probar construcci�n correcta del objeto TiendaMascotas<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creaci�n de una instancia valida de TiendaMascotas, que tiene 4 mascotas validas<br>
     */
    public void testTiendaMascotas( )
    {
        setupEscenario1( );
        assertNotNull( "Al crear la mascota 1, esta deber�a ser v�lida", tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_1 ) );
        assertNotNull( "Al crear la mascota 2, esta deber�a ser v�lida", tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_2 ) );
        assertNotNull( "Al crear la mascota 3, esta deber�a ser v�lida", tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_3 ) );
        assertNotNull( "Al crear la mascota 4, esta deber�a ser v�lida", tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_4 ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo comprarMascota<br>
     * <b> M�todos a probar: </b> <br>
     * comprarMascota<br>
     * S obtenerCantidadActual<br>
     * <b> Objetivo: </b> Probar que se lleva bien la cuenta de la cantidad de mascotas actuales<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La mascota tiene la cantidad correcta antes de comprar<br>
     * 2. La mascota tiene la cantidad correcta despu�s de comprar<br>
     */
    public void testComprarMascota( )
    {
        setupEscenario1( );
        assertEquals( "La cantidad actual de la mascota deber�a ser 20", 20, tiendaMascotas.obtenerCantidadActual( TiendaMascotas.NOMBRE_MASCOTA_1 ) );
        tiendaMascotas.comprarMascota( TiendaMascotas.NOMBRE_MASCOTA_1, 20 );
        assertEquals( "La cantidad actual de la mascota deber�a ser 40", 40, tiendaMascotas.obtenerCantidadActual( TiendaMascotas.NOMBRE_MASCOTA_1 ) );
        tiendaMascotas.venderMascota( TiendaMascotas.NOMBRE_MASCOTA_1, 40 );
        tiendaMascotas.comprarMascota( TiendaMascotas.NOMBRE_MASCOTA_1, 20 );
        assertEquals( "La cantidad actual de la mascota deber�a ser 20", 20, tiendaMascotas.obtenerCantidadActual( TiendaMascotas.NOMBRE_MASCOTA_1 ) );

    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerCantidadActual<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerCantidadActual<br>
     * <b> Objetivo: </b> Probar que se llevan bien la cuenta de la cantidad de mascotas actuales<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La mascota tiene la cantidad correcta al crear<br>
     */
    public void testObtenerCantidadActual( )
    {
        setupEscenario1( );
        assertEquals( "La cantidad actual de la mascota deber�a ser 20", 20, tiendaMascotas.obtenerCantidadActual( TiendaMascotas.NOMBRE_MASCOTA_1 ) );
        assertEquals( "La cantidad actual de la mascota deber�a ser 54", 54, tiendaMascotas.obtenerCantidadActual( TiendaMascotas.NOMBRE_MASCOTA_2 ) );
        assertEquals( "La cantidad actual de la mascota deber�a ser 23", 23, tiendaMascotas.obtenerCantidadActual( TiendaMascotas.NOMBRE_MASCOTA_3 ) );
        assertEquals( "La cantidad actual de la mascota deber�a ser 34", 34, tiendaMascotas.obtenerCantidadActual( TiendaMascotas.NOMBRE_MASCOTA_4 ) );

    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerMascota<br>
     * <b> M�todos a probar: </b> <br>
     * <b> Objetivo: </b> Probar que se obtienen correctamente las mascotas de acuerdo a su nombre<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtienen las mascotas por su nombre<br>
     * 2. Retorna null cuando no encuentra una mascota<br>
     */
    public void testObtenerMascota( )
    {
        setupEscenario1( );
        assertNotNull( "Al crear la mascota 1, esta deber�a ser v�lida", tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_1 ) );
        assertNotNull( "Al crear la mascota 2, esta deber�a ser v�lida", tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_2 ) );
        assertNotNull( "Al crear la mascota 3, esta deber�a ser v�lida", tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_3 ) );
        assertNotNull( "Al crear la mascota 4, esta deber�a ser v�lida", tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_4 ) );

        assertEquals( "Al crear la mascota 1, esta deber�a ser valida", TiendaMascotas.NOMBRE_MASCOTA_1, tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_1 ).obtenerNombre( ) );
        assertEquals( "Al crear la mascota 2, esta deber�a ser valida", TiendaMascotas.NOMBRE_MASCOTA_2, tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_2 ).obtenerNombre( ) );
        assertEquals( "Al crear la mascota 3, esta deber�a ser valida", TiendaMascotas.NOMBRE_MASCOTA_3, tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_3 ).obtenerNombre( ) );
        assertEquals( "Al crear la mascota 4, esta deber�a ser valida", TiendaMascotas.NOMBRE_MASCOTA_4, tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_4 ).obtenerNombre( ) );

        assertNull( "No deber�a encontrar una mascota con un nombre inv�lido", tiendaMascotas.obtenerMascota( "adfasfa" ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerMascota<br>
     * <b> M�todos a probar: </b> <br>
     * <b> Objetivo: </b> Probar que se obtienen correctamente las mascotas de acuerdo a su nombre<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtienen las mascotas por su nombre<br>
     * 2. Retorna null cuando no encuentra una mascota<br>
     */
    public void testObtenerMascotaMasVendida( )
    {
        setupEscenario1( );
        assertEquals( "La mascota m�s vendida deber�a ser ninguna", TiendaMascotas.NINGUNA, tiendaMascotas.obtenerMascotaMasVendida( ) );
        tiendaMascotas.venderMascota( TiendaMascotas.NOMBRE_MASCOTA_1, 20 );
        assertEquals( "La mascota m�s vendida deber�a ser la primera", TiendaMascotas.NOMBRE_MASCOTA_1, tiendaMascotas.obtenerMascotaMasVendida( ) );
        tiendaMascotas.venderMascota( TiendaMascotas.NOMBRE_MASCOTA_2, 20 );
        assertEquals( "La mascota m�s vendida deber�a ser ninguna", TiendaMascotas.NINGUNA, tiendaMascotas.obtenerMascotaMasVendida( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo seleccionarMascota<br>
     * <b> M�todos a probar: </b> <br>
     * seleccionarMascota<br>
     * <b> Objetivo: </b> Probar que se seleccionan correctamente las mascotas de acuerdo los diferentes criterios<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtienen las mascotas de acuerdo a un criterio<br>
     */
    public void testSeleccionarMascota( )
    {
        setupEscenario1( );

        // Escenario cuando ninguna mascota cumple los criterios
        assertEquals( "No deber�a existir ninguna mascota de acuerdo a esos criterios", "Ninguna de las mascotas cumple los criterios", tiendaMascotas.seleccionarMascota( Mascota.SEXO_HEMBRA, 0, 0 ) );

        // Escenario cuando la boa cumple los requisitos
        assertTrue( "Deber�an existir mascotas de acuerdo a esos criterios", tiendaMascotas.seleccionarMascota( Mascota.SEXO_MACHO, 2, 1500000 ).contains( TiendaMascotas.NOMBRE_MASCOTA_1 ) );
        assertTrue( "Deber�an existir mascotas de acuerdo a esos criterios", tiendaMascotas.seleccionarMascota( Mascota.SEXO_HEMBRA, 1, 2000000 ).contains( TiendaMascotas.NOMBRE_MASCOTA_2 ) );
        assertTrue( "Deber�an existir mascotas de acuerdo a esos criterios", tiendaMascotas.seleccionarMascota( Mascota.SEXO_MACHO, 5, 5000000 ).contains( TiendaMascotas.NOMBRE_MASCOTA_3 ) );
        assertTrue( "Deber�an existir mascotas de acuerdo a esos criterios", tiendaMascotas.seleccionarMascota( Mascota.SEXO_HEMBRA, 3, 15000000 ).contains( TiendaMascotas.NOMBRE_MASCOTA_4 ) );

    }

    /**
     * Este m�todo se encarga de verificar el m�todo venderMascota<br>
     * <b> M�todos a probar: </b> <br>
     * venderMascota<br>
     * obtenerMascota<br>
     * <b> Objetivo: </b> Probar que se vende correctamente una mascota<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se obtiene la mascota sin ser vendida<br>
     * 2. Se obtiene la mascota despu�s de vender ciertas unidades<br>
     * 3. Se obtiene null despu�s de vender todas las unidades<br>
     */
    public void testVenderMascota( )
    {
        setupEscenario1( );
        assertNotNull( "Hay existencia de la mascota. No deber�a ser null", tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_1 ) );
        tiendaMascotas.venderMascota( TiendaMascotas.NOMBRE_MASCOTA_1, 10 );
        assertNotNull( "Hay existencia de la mascota. No deber�a ser null", tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_1 ) );
        tiendaMascotas.venderMascota( TiendaMascotas.NOMBRE_MASCOTA_1, 10 );
        assertNull( "No hay existencia de la mascota. Deber�a ser null", tiendaMascotas.obtenerMascota( TiendaMascotas.NOMBRE_MASCOTA_1 ) );
    }
}