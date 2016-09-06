/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: MascotaTest.java,v 1.3 2007/06/25 19:02:31 camil-ji Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_tiendaMascotas
 * Autor: Manuel Muñoz - 08-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.tiendaMascotas.test;

import junit.framework.TestCase;
import uniandes.cupi2.tiendaMascotas.mundo.Mascota;
import uniandes.cupi2.tiendaMascotas.mundo.TiendaMascotas;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Mascota estén correctamente implementados
 */
public class MascotaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Mascota mascota;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Mascota con nombre Cacatúa, edad 2, precio 1500000, cantidad 20, sexo macho
     */
    private void setupEscenario1( )
    {
        mascota = new Mascota( TiendaMascotas.NOMBRE_MASCOTA_1, 2, 1500000, 20, Mascota.SEXO_MACHO );
    }

    /**
     * Este método se encarga de verificar el método constructor<br>
     * <b> Métodos a probar: </b> <br>
     * Mascota<br>
     * obtenerNombre<br>
     * obtenerSexo<br>
     * obtenerCantidadActual<br>
     * obtenerEdad<br>
     * obtenerPrecio<br>
     * <b> Objetivo: </b> Probar construcción correcta del objeto Mascota<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creación de una instancia valida de Mascota<br>
     */
    public void testMascota( )
    {
        setupEscenario1( );
        assertEquals( "Nombre no iniciado correctamente", TiendaMascotas.NOMBRE_MASCOTA_1, mascota.obtenerNombre( ) );
        assertEquals( "Sexo no iniciado correctamente", Mascota.SEXO_MACHO, mascota.obtenerSexo( ) );
        assertEquals( "Cantidad actual no iniciado correctamente", 20, mascota.obtenerCantidadActual( ) );
        assertEquals( "Edad no iniciada correctamente", 2, mascota.obtenerEdad( ) );
        assertEquals( "Precio no iniciado correctamente", 1500000, mascota.obtenerPrecio( ) );
    }

    /**
     * Este método se encarga de verificar el método comprarMascota<br>
     * <b> Métodos a probar: </b> <br>
     * comprarMascota<br>
     * obtenerCantidadActual<br>
     * <b> Objetivo: </b> Probar que lleva correctamente las cuentas de cuantas mascotas se han comprado<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se modifica correctamente la cantidad actual de la mascota<br>
     */
    public void testComprarMascota( )
    {
        setupEscenario1( );
        mascota.comprarMascota( 50 );
        assertEquals( "La cantidad de la mascota no coincide", 70, mascota.obtenerCantidadActual( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerCantidadActual<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerCantidadActual<br>
     * <b> Objetivo: </b> Probar obtención correcta de la cantidad actual de la mascota<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtención correcta de la cantidad actual de la mascota<br>
     */
    public void testObtenerCantidadActual( )
    {
        setupEscenario1( );
        assertEquals( "Cantidad actual no iniciado correctamente", 20, mascota.obtenerCantidadActual( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerEdad<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerEdad<br>
     * <b> Objetivo: </b> Probar obtención correcta de la edad de la mascota<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtención correcta de la edad de la mascota<br>
     */
    public void testObtenerEdad( )
    {
        setupEscenario1( );
        assertEquals( "Edad no iniciada correctamente", 2, mascota.obtenerEdad( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerNombre<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerNombre<br>
     * <b> Objetivo: </b> Probar obtención correcta del nombre de la mascota<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtención correcta del nombre de la mascota<br>
     */
    public void testObtenerNombre( )
    {
        setupEscenario1( );
        assertEquals( "Nombre no iniciado correctamente", TiendaMascotas.NOMBRE_MASCOTA_1, mascota.obtenerNombre( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerPrecio<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerPrecio<br>
     * <b> Objetivo: </b> Probar obtención correcta del precio de la mascota<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtención correcta del precio de la mascota<br>
     */
    public void testObtenerPrecio( )
    {
        setupEscenario1( );
        assertEquals( "Precio no iniciado correctamente", 1500000, mascota.obtenerPrecio( ) );
    }

    /**
     * Este método se encarga de verificar el método obtenerSexo<br>
     * <b> Métodos a probar: </b> <br>
     * obtenerSexo<br>
     * <b> Objetivo: </b> Probar obtención correcta del sexo de la mascota<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtención correcta del sexo de la mascota<br>
     */
    public void testObtenerSexo( )
    {
        setupEscenario1( );
        assertEquals( "Sexo no iniciado correctamente", Mascota.SEXO_MACHO, mascota.obtenerSexo( ) );
    }

    /**
     * Este método se encarga de verificar el método venderMascota<br>
     * <b> Métodos a probar: </b> <br>
     * venderMascota<br>
     * obtenerCantidadActual<br>
     * <b> Objetivo: </b> Probar que lleva correctamente las cuentas de cuantas mascotas se han vendido<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se modifica correctamente la cantidad actual de la mascota<br>
     */
    public void testVenderMascota( )
    {
        setupEscenario1( );
        assertEquals( "La cantidad de mascotas restantes no coincide", 20, mascota.obtenerCantidadActual( ) );
        mascota.venderMascota( 10 );
        assertEquals( "La cantidad de mascotas restantes no coincide", 10, mascota.obtenerCantidadActual( ) );
        mascota.venderMascota( 10 );
        assertEquals( "La cantidad de mascotas restantes no coincide", 0, mascota.obtenerCantidadActual( ) );
    }

}