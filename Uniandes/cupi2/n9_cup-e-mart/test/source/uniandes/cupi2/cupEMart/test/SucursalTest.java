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
import uniandes.cupi2.cupEMart.mundo.Fecha;
import uniandes.cupi2.cupEMart.mundo.LineaProductoExisteException;
import uniandes.cupi2.cupEMart.mundo.LineaProductoNoExisteException;
import uniandes.cupi2.cupEMart.mundo.Sucursal;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Sucursal estén correctamente implementados
 */
public class SucursalTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Sucursal sucursal;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Sucursal con nombre Sucursal 1 y dirección Calle 69 # 72-18
     */
    private void setupEscenario1( )
    {
        sucursal = new Sucursal( "Sucursal 1", "Calle 69 # 72-18" );
    }

    /**
     * Construye una nueva Sucursal con nombre Sucursal 1 y dirección Calle 69 # 72-18 y 9 líneas de producto con diferentes precios y fechas de lanzamiento
     */
    private void setupEscenario2( )
    {
        sucursal = new Sucursal( "Sucursal 1", "Calle 69 # 72-18" );
        try
        {
            sucursal.lanzarLineaProducto( "Producto 1", "asdf1", "Línea de producto de prueba", 150, new Fecha( 22, 5, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 2", "asdf2", "Línea de producto de prueba", 150, new Fecha( 22, 5, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 3", "asdf3", "Línea de producto de prueba", 150, new Fecha( 19, 9, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 4", "asdf4", "Línea de producto de prueba", 150, new Fecha( 25, 5, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 5", "asdf5", "Línea de producto de prueba", 15000, new Fecha( 1, 7, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 6", "asdf6", "Línea de producto de prueba", 1500, new Fecha( 13, 12, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 7", "asdf7", "Línea de producto de prueba", 15000, new Fecha( 15, 4, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 8", "asdf8", "Línea de producto de prueba", 15000, new Fecha( 18, 9, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 9", "asdf9", "Línea de producto de prueba", 20, new Fecha( 30, 7, 2005 ) );
        }
        catch( LineaProductoExisteException e )
        {
            fail( "No debería fallar en el lanzamiento de las líneas de producto" );
        }
    }

    /**
     * Este método se encarga de verificar el método constructor<br>
     * <b> Métodos a probar: </b> <br>
     * Sucursal<br>
     * darNombre<br>
     * darDireccion<br>
     * darSiguiente<br>
     * darProductos<br>
     * <b> Objetivo: </b> Probar la inicialización correcta de una sucursal<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creación de una nueva sucursal con el nombre Sucursal 1 y la dirección Calle 69 # 72-18 <br>
     * 2. La inicialización de los atributos de la sucursal sea correcta.<br>
     */
    public void testSucursal( )
    {
        setupEscenario1( );
        assertNotNull( "La sucursal debería tener un nombre válido", sucursal.darNombre( ) );
        assertEquals( "El nombre de la sucursal debería ser Sucursal 1", "Sucursal 1", sucursal.darNombre( ) );
        assertNotNull( "La sucursal debería tener una dirección válida", sucursal.darDireccion( ) );
        assertEquals( "La dirección de la sucursal debería ser Calle 69 # 72-18", "Calle 69 # 72-18", sucursal.darDireccion( ) );
        assertNull( "No debería tener siguiente elemento", sucursal.darSiguiente( ) );
        assertNotNull( "La lista de productos debería ser vacía, pero no nulla", sucursal.darLineasProducto( ) );
        assertEquals( "La lista de productos debería ser vacía", 0, sucursal.darLineasProducto( ).size( ) );
    }

    /**
     * Este método se encarga de verificar el método lanzarLineaProducto<br>
     * <b> Métodos a probar: </b> <br>
     * lanzarLineaProducto<br>
     * darLineasProducto<br>
     * <b> Objetivo: </b> Probar el lanzamiento de una nueva línea de producto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se agrega una línea de producto a la sucursal. La línea de producto tendrá los valores: Nombre: Producto 1 Código: asdf Descripción: Línea de producto de prueba
     * Precio: 1500 Fecha: 22,5,2006<br>
     * 2. Al tratar de lanzar una línea de producto que existe, que genere la excepción.<br>
     */
    public void testLanzarLineaProducto( )
    {
        setupEscenario1( );
        try
        {
            sucursal.lanzarLineaProducto( "Producto 1", "asdf", "Línea de producto de prueba", 1500, new Fecha( 22, 5, 2006 ) );
        }
        catch( LineaProductoExisteException e )
        {
            fail( "No debería lanzar excepción debido a que no existen líneas de producto en la sucursal" );
        }

        assertNotNull( "La lista de líneas de producto no debería ser null", sucursal.darLineasProducto( ) );
        assertEquals( "La lista de líneas de producto debería tener un elemento", 1, sucursal.darLineasProducto( ).size( ) );

        try
        {
            sucursal.lanzarLineaProducto( "Producto 1", "asdf", "Línea de producto de prueba", 1500, new Fecha( 22, 5, 2006 ) );
            fail( "No debería lanzar la línea de producto ya que existía en la sucursal" );
        }
        catch( LineaProductoExisteException e )
        {
            // Debería lanzar la excepción
        }
    }

    /**
     * Este método se encarga de verificar el método darLineaProductoMasAntigua<br>
     * <b> Métodos a probar: </b> <br>
     * darLineaProductoMasAntigua<br>
     * <b> Objetivo: </b> Probar dar la línea de producto más antigua<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se encuentre una línea de producto como la más antigua
     */
    public void testDarLineaProductoMasAntiguo( )
    {
        setupEscenario1( );
        assertNull( "Debería retornar null ya que no hay líneas de productos", sucursal.darLineaProductoMasAntiguo( ) );
        setupEscenario2( );
        assertNotNull( "Debería tener una línea de producto más antigua", sucursal.darLineaProductoMasAntiguo( ) );
        assertEquals( "El nombre de la línea de producto más antigua debería ser Producto 9", "Producto 9", sucursal.darLineaProductoMasAntiguo( ).darNombre( ) );
        try
        {
            sucursal.lanzarLineaProducto( "Producto 10", "asdf10", "Línea de producto de prueba", 20, new Fecha( 30, 7, 1999 ) );
        }
        catch( LineaProductoExisteException e )
        {
            fail( "Debería lanzar la línea de producto" );
        }
        assertNotNull( "Debería tener una línea de producto más antigua", sucursal.darLineaProductoMasAntiguo( ) );
        assertEquals( "El nombre de la línea de producto más antigua debería ser asdf10", "asdf10", sucursal.darLineaProductoMasAntiguo( ).darCodigo( ) );
        try
        {
            sucursal.lanzarLineaProducto( "Producto 10", "asdf10", "Línea de producto de prueba", 20, new Fecha( 30, 7, 1999 ) );
            fail( "No debería lanzar la línea de producto, ya que esta existe" );
        }
        catch( LineaProductoExisteException e )
        {
            // Deberia entrar a este catch
        }
    }

    /**
     * Este método se encarga de verificar el método darLineaProductoMasAntigua<br>
     * <b> Métodos a probar: </b> <br>
     * darLineaProductoMasAntigua<br>
     * <b> Objetivo: </b> Probar dar la línea de producto más antigua<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se encuentre una línea de producto como la más antigua
     */
    public void testDarCodigosLineasProductos( )
    {
        setupEscenario2( );
        ArrayList codigos = sucursal.darCodigosLineasProductos( );
        assertNotNull( "No debería retornar null", codigos );
        assertEquals( "La lista de códigos debería tener 9 elementos", 9, codigos.size( ) );

    }

    /**
     * Este método se encarga de verificar el método descontinuarLineaProducto<br>
     * <b> Métodos a probar: </b> <br>
     * descontinuarLineaProducto<br>
     * darLineasProducto<br>
     * <b> Objetivo: </b> Probar descontinuar una línea de producto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se descontinua la línea de producto Producto 7
     */
    public void testDescontinuarLineaProducto( )
    {
        setupEscenario1( );
        try
        {
            sucursal.descontinuarLineaProducto( "asdf7" );
            fail( "No debería descontinuar la línea de producto ya que la sucursal no tenía ninguna" );
        }
        catch( LineaProductoNoExisteException e1 )
        {
            // Debería entrar a este catch
        }
        setupEscenario2( );
        try
        {
            sucursal.descontinuarLineaProducto( "asdf7" );
            assertEquals( "La lista de líneas de producto debería ser 8", 8, sucursal.darLineasProducto( ).size( ) );
        }
        catch( LineaProductoNoExisteException e )
        {
            fail( "La línea de producto debería existir en la sucursal" );
        }
        try
        {
            sucursal.descontinuarLineaProducto( "asdf7" );
            fail( "No debería poder descontinuar una línea de producto inexistente" );
        }
        catch( LineaProductoNoExisteException e )
        {
            // Debería entrar a este catch
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
        setupEscenario1( );
        assertEquals( "No debería poder eliminar por un rango ya que no hay líneas de producto registradas", 0, sucursal.eliminarLineasProductosDadoRango( 50, 500 ) );
        setupEscenario2( );
        assertEquals( "La sucursal debería haber eliminado 4 líneas de producto", 4, sucursal.eliminarLineasProductosDadoRango( 50, 500 ) );
        assertEquals( "La sucursal debería tener 5 líneas de producto", 5, sucursal.darLineasProducto( ).size( ) );
    }

    /**
     * Este método se encarga de verificar el método establecerSiguiente<br>
     * <b> Métodos a probar: </b> <br>
     * establecerSiguiente<br>
     * darSiguiente<br>
     * <b> Objetivo: </b> Probar que se coloque correctamente el siguiente elemento de la lista<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La sucursal tendrá un elemento siguiente.
     */
    public void testEstablecerSiguiente( )
    {
        setupEscenario1( );
        assertNull( "La sucursal siguiente debería ser null", sucursal.darSiguiente( ) );
        Sucursal temp = new Sucursal( "Sucursal 2", "Dirección 2" );

        sucursal.cambiarSiguiente( temp );
        assertNotNull( "La sucursal siguiente no debería ser null", sucursal.darSiguiente( ) );
        assertEquals( "Las sucursales no coinciden", temp, sucursal.darSiguiente( ) );
    }
}
