/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Muñoz - 19-Oct-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.cupEMart.mundo.CupEMart;
import uniandes.cupi2.cupEMart.mundo.Fecha;
import uniandes.cupi2.cupEMart.mundo.LineaProducto;
import uniandes.cupi2.cupEMart.mundo.LineaProductoExisteException;
import uniandes.cupi2.cupEMart.mundo.LineaProductoNoExisteException;
import uniandes.cupi2.cupEMart.mundo.PersistenciaException;
import uniandes.cupi2.cupEMart.mundo.Sucursal;
import uniandes.cupi2.cupEMart.mundo.SucursalExisteException;
import uniandes.cupi2.cupEMart.mundo.SucursalNoExisteException;

/**
 * Esta es la clase usada para verificar que los métodos de la clase CupiMart estén correctamente implementados
 */
public class CupEMartTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private CupEMart cupEMart;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva CupiMart vacía
     */
    private void setupEscenario1( )
    {
        try
        {
            cupEMart = new CupEMart( "./test/data/cupEMart.data" );
        }
        catch( PersistenciaException e )
        {
            fail();
        }
    }
    /**
     * Construye una nueva CupiMart con 7 sucursales sin productos
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );
        try
        {
            cupEMart.agregarSucursal( "Sucursal 1", "Dirección 1" );
            cupEMart.agregarSucursal( "Sucursal 2", "Dirección 2" );
            cupEMart.agregarSucursal( "Sucursal 3", "Dirección 3" );
            cupEMart.agregarSucursal( "Sucursal 4", "Dirección 4" );
            cupEMart.agregarSucursal( "Sucursal 5", "Dirección 5" );
            cupEMart.agregarSucursal( "Sucursal 6", "Dirección 6" );
            cupEMart.agregarSucursal( "Sucursal 7", "Dirección 7" );
        }
        catch( SucursalExisteException e )
        {
            fail( );
        }
    }

    /**
     * Construye una nueva CupiMart con 6 sucursales y la distribución de líneas de productos de la siguiente manera y cada una con al menos una línea de producto
     */
    private void setupEscenario3( )
    {
        setupEscenario2( );
        try
        {
            cupEMart.lanzarLineaProductoSucursal( "Dirección 1", "Producto 1", "asdf1", "Línea de producto de prueba", 150, new Fecha( 22, 5, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Dirección 1", "Producto 2", "asdf2", "Línea de producto de prueba", 150, new Fecha( 22, 5, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Dirección 2", "Producto 3", "asdf3", "Línea de producto de prueba", 150, new Fecha( 19, 9, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Dirección 3", "Producto 4", "asdf4", "Línea de producto de prueba", 150, new Fecha( 25, 5, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Dirección 4", "Producto 5", "asdf5", "Línea de producto de prueba", 15000, new Fecha( 1, 7, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Dirección 4", "Producto 6", "asdf6", "Línea de producto de prueba", 1500, new Fecha( 13, 12, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Dirección 5", "Producto 7", "asdf7", "Línea de producto de prueba", 15000, new Fecha( 15, 4, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Dirección 6", "Producto 8", "asdf8", "Línea de producto de prueba", 15000, new Fecha( 18, 9, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Dirección 6", "Producto 9", "asdf9", "Línea de producto de prueba", 20, new Fecha( 30, 7, 2005 ) );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "No debería fallar encontrando la sucursal" );
        }
        catch( LineaProductoExisteException e )
        {
            fail( "No debería fallar porque el producto no ha sido creado" );
        }
    }

    /**
     * Este método se encarga de verificar el método AgregarSucursal<br>
     * <b> Métodos a probar: </b> <br>
     * agregarSucursal<br>
     * darSucursales<br>
     * darSucursal<br>
     * <b> Objetivo: </b> Probar la inserción de una nueva sucursal<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creación de una nueva sucursal con el nombre Sucursal 1 y la dirección Calle 69 # 72-18 <br>
     * 2. Al tratar de agregar una sucursal que ya existe, que genere la excepción.<br>
     */
    public void testAgregarSucursal( )
    {
        setupEscenario1( );
        assertEquals( "No deben existir sucursales", 0, cupEMart.darSucursales( ).size( ) );
        assertEquals( "La lista de sucursales debería ser vacía", 0, cupEMart.darSucursales( ).size( ) );
        try
        {
            cupEMart.agregarSucursal( "Sucursal 1", "Calle 69 # 72-18" );
            assertEquals( "Debería existir 1 sucursal", 1, cupEMart.darSucursales( ).size( ) );
            assertNotNull( "Debería encontrar la sucursal con nombre Sucursal 1", cupEMart.darSucursal( "Calle 69 # 72-18" ) );
            assertNotNull( "La lista de sucursales no debería ser null", cupEMart.darSucursales( ) );
            assertEquals( "La lista debería tener un elemento", 1, cupEMart.darSucursales( ).size( ) );
        }
        catch( SucursalExisteException e )
        {
            fail( "La sucursal no debería existir" );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "La sucursal debería existir" );
        }
        try
        {
            cupEMart.agregarSucursal( "Sucursal 1", "Calle 69 # 72-18" );
            fail( "No debería poder agregar la sucursal, ya que esta existe" );
        }
        catch( SucursalExisteException e )
        {
            // Debería ocurrir
        }

    }

    /**
     * Este método se encarga de verificar el método eliminarSucursal<br>
     * <b> Métodos a probar: </b> <br>
     * eliminarSucursal<br>
     * darSucursales<br>
     * darSucursal<br>
     * <b> Objetivo: </b> Probar la eliminación de una nueva sucursal<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Eliminación de una sucursal con nombre Sucursal 5<br>
     * 2. Al tratar de eliminar una sucursal que no existe, que genere la excepción.<br>
     */
    public void testEliminarSucursal( )
    {
        setupEscenario2( );
        assertNotNull( "La lista de sucursales no debería se null", cupEMart.darSucursales( ) );
        assertEquals( "La lista de sucursales debería tener 7 elementos", 7, cupEMart.darSucursales( ).size( ) );
        try
        {
            cupEMart.eliminarSucursal( "Dirección 5" );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "La sucursal debería existir" );
        }

        assertNotNull( "La lista de sucursales no debería se null", cupEMart.darSucursales( ) );
        assertEquals( "La lista de sucursales debería tener 6 elementos", 6, cupEMart.darSucursales( ).size( ) );

        try
        {
            cupEMart.darSucursal( "Dirección 5" );
            fail( "No debió encontrar la sucursal" );

        }
        catch( SucursalNoExisteException e )
        {
            // Debería entrar acá
        }
    }

    /**
     * Este método se encarga de verificar el método lanzarLineaProductoSucursal<br>
     * <b> Métodos a probar: </b> <br>
     * lanzarLineaProductoSucursal<br>
     * listarLineasProductosSucursal<br>
     * <b> Objetivo: </b> Probar el lanzamiento de una nueva línea de producto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se agrega una línea de producto a la sucursal con nombre Sucursal 5. La línea de producto tendrá los valores: Nombre: Producto 1 Código: asdf Descripción: Línea de
     * producto de prueba Precio: 1500 Fecha: 22,5,2006<br>
     * 2. Al tratar de lanzar una línea de producto que existe, que genere la excepción.<br>
     */
    public void testLanzarLineaProductoSucursal( )
    {
        setupEscenario2( );

        try
        {
            cupEMart.lanzarLineaProductoSucursal( "Dirección 5", "Producto 1", "asdf", "Línea de producto de prueba", 1500, new Fecha( 22, 5, 2006 ) );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "La sucursal: Sucursal 5 debería existir" );
        }
        catch( LineaProductoExisteException e )
        {
            fail( "la línea de producto con nombre Producto 5 no debería existir" );
        }
        try
        {
            cupEMart.lanzarLineaProductoSucursal( "Dirección 5", "Producto 1", "asdf", "Línea de producto de prueba", 1500, new Fecha( 22, 5, 2006 ) );
            fail( "No debería poder lanzar la línea de producto porque ya existe" );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "La sucursal: Sucursal 5 debería existir" );
        }
        catch( LineaProductoExisteException e )
        {
            // Debería atrapar esta excepción
        }

        assertNotNull( "La lista de líneas de producto de la sucursal 5 no debería ser null.", cupEMart.darLineasProductoSucursal( "Dirección 5" ) );
        assertEquals( "La lista de líneas de producto de la sucursal 5 debería tener 1 elemento", 1, cupEMart.darLineasProductoSucursal( "Dirección 5" ).size( ) );
    }

    /**
     * Este método se encarga de verificar el método darLineaProductoMasAntigua<br>
     * <b> Métodos a probar: </b> <br>
     * darLineaProductoMasAntigua<br>
     * <b> Objetivo: </b> Probar dar la línea de producto más antigua<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se encuentre una línea de producto como la más antigua
     */
    public void testDarLineaProductoMasAntigua( )
    {
        setupEscenario1( );
        try
        {
            cupEMart.darLineaProductoMasAntigua( );
            fail( "No debería encontrar una línea de producto" );
        }
        catch( LineaProductoNoExisteException e1 )
        {
            // Debería entrar al catch
        }

        setupEscenario3( );
        try
        {
            assertNotNull( "No debería retornar una línea de producto null", cupEMart.darLineaProductoMasAntigua( ) );
            assertEquals( "La línea de producto más antigua debería tener nombre Producto 9", "Producto 9", cupEMart.darLineaProductoMasAntigua( ).darNombre( ) );
        }
        catch( LineaProductoNoExisteException e )
        {
            fail( "Si existen líneas de productos en las sucursales" );
        }
    }

    /**
     * Este método se encarga de verificar el método descontinuarLineaProducto<br>
     * <b> Métodos a probar: </b> <br>
     * descontinuarLineaProducto<br>
     * listarLineasProductoSucursal<br>
     * <b> Objetivo: </b> Probar descontinuar una línea de producto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se descontinua la línea de producto Producto 7 de la Sucursal 5
     */
    public void testDescontinuarLineaProducto( )
    {
        setupEscenario1( );
        try
        {
            cupEMart.descontinuarLineaProducto( "Sucursal 5", "Producto 7" );
            fail( "No debería encontrar una sucursal" );
        }
        catch( LineaProductoNoExisteException e1 )
        {
            fail( "Debería validar primero cual la sucursal" );
        }
        catch( SucursalNoExisteException e1 )
        {
            // Debería entrar a este catch
        }
        setupEscenario2( );
        try
        {
            cupEMart.descontinuarLineaProducto( "Sucursal 5", "Producto 7" );
        }
        catch( LineaProductoNoExisteException e1 )
        {
            // Debaria entrar a este catch
        }
        catch( SucursalNoExisteException e1 )
        {
            fail( "La sucursal debería estar" );
        }

        setupEscenario3( );
        try
        {
            cupEMart.descontinuarLineaProducto( "Sucursal 5", "Producto 7" );
            assertEquals( "La lista de líneas de producto debería ser vacía", 0, cupEMart.darLineasProductoSucursal( "Sucursal 5" ).size( ) );
        }
        catch( LineaProductoNoExisteException e )
        {
            fail( "La línea de producto debería existir en la sucursal" );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "La sucursal debería existir" );
        }
    }

    /**
     * Este método se encarga de verificar el método eliminarLineasProductosDadoRango<br>
     * <b> Métodos a probar: </b> <br>
     * descontinuarLineaProducto<br>
     * listarLineasProductoSucursal<br>
     * <b> Objetivo: </b> Probar la eliminación de las líneas de producto que tienen un precio menor o igual a un rango<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se eliminen las líneas de producto con su precio menor o igual a 500.
     */
    public void testEliminarLineasProductosDadoRango( )
    {
        setupEscenario3( );
        assertEquals( "Debería haber eliminado 4 líneas de producto", 4, cupEMart.eliminarLineasProductosDadoRango( 100, 500 ) );

        assertEquals( "La sucursal 1 debería tener 0 líneas de producto", 0, cupEMart.darLineasProductoSucursal( "Dirección 1" ).size( ) );
        assertEquals( "La sucursal 2 debería tener 0 líneas de producto", 0, cupEMart.darLineasProductoSucursal( "Dirección 2" ).size( ) );
        assertEquals( "La sucursal 3 debería tener 0 líneas de producto", 0, cupEMart.darLineasProductoSucursal( "Dirección 3" ).size( ) );
        assertEquals( "La sucursal 4 debería tener 2 líneas de producto", 2, cupEMart.darLineasProductoSucursal( "Dirección 4" ).size( ) );
        assertEquals( "La sucursal 5 debería tener 1 líneas de producto", 1, cupEMart.darLineasProductoSucursal( "Dirección 5" ).size( ) );
        assertEquals( "La sucursal 6 debería tener 2 líneas de producto", 2, cupEMart.darLineasProductoSucursal( "Dirección 6" ).size( ) );
    }

    /**
     * Se prueba que se guarda y carga correctamente los archivos serializados. <b> Métodos a probar: </b> <br>
     * guardarCupEMart<br>
     * cargarCupEMart<br>
     * <b> Objetivo: </b> Verificación que se actualizan correctamente los datos del hipermercado.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Guardar un archivo con el estado del mundo<br>
     * 2. Leer del archivo con el estado del mundo<br>
     * 3. Cargar la cantidad correcta de sucursales<br>
     * 4. Cargar la cantidad correcta de líneas de producto para la sucursal<br>
     */
    public void testGuardarCargarSistema( )
    {
        try
        {
            setupEscenario3( );
            cupEMart.guardarCupEMart( "./test/data/archivoPrueba.data" );
            cupEMart.cargarCupEMart( "./test/data/archivoPrueba.data" );
            Sucursal sucursal = cupEMart.darSucursal( "Dirección 1" );
            assertTrue( "Error en el nombre de la sucursal", sucursal.darNombre( ).equals( "Sucursal 1" ) );
            assertTrue( "Error en el género de la sucursal", sucursal.darDireccion( ).equals( "Dirección 1" ) );

            ArrayList lineas = cupEMart.darLineasProductoSucursal( sucursal.darDireccion( ) );
            assertEquals( "Las líneas de producto deberían ser 2", 2, lineas.size( ) );
            assertEquals( "Las líneas de producto deberían ser 1", 1, cupEMart.darLineasProductoSucursal( "Dirección 2" ).size( ) );
            assertEquals( "Las líneas de producto deberían ser 1", 1, cupEMart.darLineasProductoSucursal( "Dirección 3" ).size( ) );
            assertEquals( "Las líneas de producto deberían ser 2", 2, cupEMart.darLineasProductoSucursal( "Dirección 4" ).size( ) );
            assertEquals( "Las líneas de producto deberían ser 1", 1, cupEMart.darLineasProductoSucursal( "Dirección 5" ).size( ) );
            assertEquals( "Las líneas de producto deberían ser 2", 2, cupEMart.darLineasProductoSucursal( "Dirección 6" ).size( ) );

            for( int i = 0; i < lineas.size( ); i++ )
            {
                LineaProducto producto = ( LineaProducto )lineas.get( i );
                assertEquals( "El código del producto debería ser asdf" + ( i + 1 ), "asdf" + ( i + 1 ), producto.darCodigo( ) );
            }
        }

        catch( PersistenciaException e )
        {
            fail( e.getMessage( ) );
        }
        catch( SucursalNoExisteException e )
        {
            fail( e.getMessage( ) );
        }

    }

}