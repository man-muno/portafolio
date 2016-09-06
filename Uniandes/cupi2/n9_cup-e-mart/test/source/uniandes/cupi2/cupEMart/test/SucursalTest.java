/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Mu�oz - 19-Oct-2006
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
 * Esta es la clase usada para verificar que los m�todos de la clase Sucursal est�n correctamente implementados
 */
public class SucursalTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Sucursal sucursal;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Sucursal con nombre Sucursal 1 y direcci�n Calle 69 # 72-18
     */
    private void setupEscenario1( )
    {
        sucursal = new Sucursal( "Sucursal 1", "Calle 69 # 72-18" );
    }

    /**
     * Construye una nueva Sucursal con nombre Sucursal 1 y direcci�n Calle 69 # 72-18 y 9 l�neas de producto con diferentes precios y fechas de lanzamiento
     */
    private void setupEscenario2( )
    {
        sucursal = new Sucursal( "Sucursal 1", "Calle 69 # 72-18" );
        try
        {
            sucursal.lanzarLineaProducto( "Producto 1", "asdf1", "L�nea de producto de prueba", 150, new Fecha( 22, 5, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 2", "asdf2", "L�nea de producto de prueba", 150, new Fecha( 22, 5, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 3", "asdf3", "L�nea de producto de prueba", 150, new Fecha( 19, 9, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 4", "asdf4", "L�nea de producto de prueba", 150, new Fecha( 25, 5, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 5", "asdf5", "L�nea de producto de prueba", 15000, new Fecha( 1, 7, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 6", "asdf6", "L�nea de producto de prueba", 1500, new Fecha( 13, 12, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 7", "asdf7", "L�nea de producto de prueba", 15000, new Fecha( 15, 4, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 8", "asdf8", "L�nea de producto de prueba", 15000, new Fecha( 18, 9, 2006 ) );
            sucursal.lanzarLineaProducto( "Producto 9", "asdf9", "L�nea de producto de prueba", 20, new Fecha( 30, 7, 2005 ) );
        }
        catch( LineaProductoExisteException e )
        {
            fail( "No deber�a fallar en el lanzamiento de las l�neas de producto" );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo constructor<br>
     * <b> M�todos a probar: </b> <br>
     * Sucursal<br>
     * darNombre<br>
     * darDireccion<br>
     * darSiguiente<br>
     * darProductos<br>
     * <b> Objetivo: </b> Probar la inicializaci�n correcta de una sucursal<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creaci�n de una nueva sucursal con el nombre Sucursal 1 y la direcci�n Calle 69 # 72-18 <br>
     * 2. La inicializaci�n de los atributos de la sucursal sea correcta.<br>
     */
    public void testSucursal( )
    {
        setupEscenario1( );
        assertNotNull( "La sucursal deber�a tener un nombre v�lido", sucursal.darNombre( ) );
        assertEquals( "El nombre de la sucursal deber�a ser Sucursal 1", "Sucursal 1", sucursal.darNombre( ) );
        assertNotNull( "La sucursal deber�a tener una direcci�n v�lida", sucursal.darDireccion( ) );
        assertEquals( "La direcci�n de la sucursal deber�a ser Calle 69 # 72-18", "Calle 69 # 72-18", sucursal.darDireccion( ) );
        assertNull( "No deber�a tener siguiente elemento", sucursal.darSiguiente( ) );
        assertNotNull( "La lista de productos deber�a ser vac�a, pero no nulla", sucursal.darLineasProducto( ) );
        assertEquals( "La lista de productos deber�a ser vac�a", 0, sucursal.darLineasProducto( ).size( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo lanzarLineaProducto<br>
     * <b> M�todos a probar: </b> <br>
     * lanzarLineaProducto<br>
     * darLineasProducto<br>
     * <b> Objetivo: </b> Probar el lanzamiento de una nueva l�nea de producto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se agrega una l�nea de producto a la sucursal. La l�nea de producto tendr� los valores: Nombre: Producto 1 C�digo: asdf Descripci�n: L�nea de producto de prueba
     * Precio: 1500 Fecha: 22,5,2006<br>
     * 2. Al tratar de lanzar una l�nea de producto que existe, que genere la excepci�n.<br>
     */
    public void testLanzarLineaProducto( )
    {
        setupEscenario1( );
        try
        {
            sucursal.lanzarLineaProducto( "Producto 1", "asdf", "L�nea de producto de prueba", 1500, new Fecha( 22, 5, 2006 ) );
        }
        catch( LineaProductoExisteException e )
        {
            fail( "No deber�a lanzar excepci�n debido a que no existen l�neas de producto en la sucursal" );
        }

        assertNotNull( "La lista de l�neas de producto no deber�a ser null", sucursal.darLineasProducto( ) );
        assertEquals( "La lista de l�neas de producto deber�a tener un elemento", 1, sucursal.darLineasProducto( ).size( ) );

        try
        {
            sucursal.lanzarLineaProducto( "Producto 1", "asdf", "L�nea de producto de prueba", 1500, new Fecha( 22, 5, 2006 ) );
            fail( "No deber�a lanzar la l�nea de producto ya que exist�a en la sucursal" );
        }
        catch( LineaProductoExisteException e )
        {
            // Deber�a lanzar la excepci�n
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darLineaProductoMasAntigua<br>
     * <b> M�todos a probar: </b> <br>
     * darLineaProductoMasAntigua<br>
     * <b> Objetivo: </b> Probar dar la l�nea de producto m�s antigua<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se encuentre una l�nea de producto como la m�s antigua
     */
    public void testDarLineaProductoMasAntiguo( )
    {
        setupEscenario1( );
        assertNull( "Deber�a retornar null ya que no hay l�neas de productos", sucursal.darLineaProductoMasAntiguo( ) );
        setupEscenario2( );
        assertNotNull( "Deber�a tener una l�nea de producto m�s antigua", sucursal.darLineaProductoMasAntiguo( ) );
        assertEquals( "El nombre de la l�nea de producto m�s antigua deber�a ser Producto 9", "Producto 9", sucursal.darLineaProductoMasAntiguo( ).darNombre( ) );
        try
        {
            sucursal.lanzarLineaProducto( "Producto 10", "asdf10", "L�nea de producto de prueba", 20, new Fecha( 30, 7, 1999 ) );
        }
        catch( LineaProductoExisteException e )
        {
            fail( "Deber�a lanzar la l�nea de producto" );
        }
        assertNotNull( "Deber�a tener una l�nea de producto m�s antigua", sucursal.darLineaProductoMasAntiguo( ) );
        assertEquals( "El nombre de la l�nea de producto m�s antigua deber�a ser asdf10", "asdf10", sucursal.darLineaProductoMasAntiguo( ).darCodigo( ) );
        try
        {
            sucursal.lanzarLineaProducto( "Producto 10", "asdf10", "L�nea de producto de prueba", 20, new Fecha( 30, 7, 1999 ) );
            fail( "No deber�a lanzar la l�nea de producto, ya que esta existe" );
        }
        catch( LineaProductoExisteException e )
        {
            // Deberia entrar a este catch
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darLineaProductoMasAntigua<br>
     * <b> M�todos a probar: </b> <br>
     * darLineaProductoMasAntigua<br>
     * <b> Objetivo: </b> Probar dar la l�nea de producto m�s antigua<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se encuentre una l�nea de producto como la m�s antigua
     */
    public void testDarCodigosLineasProductos( )
    {
        setupEscenario2( );
        ArrayList codigos = sucursal.darCodigosLineasProductos( );
        assertNotNull( "No deber�a retornar null", codigos );
        assertEquals( "La lista de c�digos deber�a tener 9 elementos", 9, codigos.size( ) );

    }

    /**
     * Este m�todo se encarga de verificar el m�todo descontinuarLineaProducto<br>
     * <b> M�todos a probar: </b> <br>
     * descontinuarLineaProducto<br>
     * darLineasProducto<br>
     * <b> Objetivo: </b> Probar descontinuar una l�nea de producto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se descontinua la l�nea de producto Producto 7
     */
    public void testDescontinuarLineaProducto( )
    {
        setupEscenario1( );
        try
        {
            sucursal.descontinuarLineaProducto( "asdf7" );
            fail( "No deber�a descontinuar la l�nea de producto ya que la sucursal no ten�a ninguna" );
        }
        catch( LineaProductoNoExisteException e1 )
        {
            // Deber�a entrar a este catch
        }
        setupEscenario2( );
        try
        {
            sucursal.descontinuarLineaProducto( "asdf7" );
            assertEquals( "La lista de l�neas de producto deber�a ser 8", 8, sucursal.darLineasProducto( ).size( ) );
        }
        catch( LineaProductoNoExisteException e )
        {
            fail( "La l�nea de producto deber�a existir en la sucursal" );
        }
        try
        {
            sucursal.descontinuarLineaProducto( "asdf7" );
            fail( "No deber�a poder descontinuar una l�nea de producto inexistente" );
        }
        catch( LineaProductoNoExisteException e )
        {
            // Deber�a entrar a este catch
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo eliminarLineasProductosDadoRango<br>
     * <b> M�todos a probar: </b> <br>
     * descontinuarLineaProducto<br>
     * listarLineasProductoSucursal<br>
     * <b> Objetivo: </b> Probar la eliminaci�n de las l�neas de producto que tienen un precio menor o igual a un rango<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se eliminen las l�neas de producto con su precio menor o igual a 500.
     */
    public void testEliminarLineasProductosDadoRango( )
    {
        setupEscenario1( );
        assertEquals( "No deber�a poder eliminar por un rango ya que no hay l�neas de producto registradas", 0, sucursal.eliminarLineasProductosDadoRango( 50, 500 ) );
        setupEscenario2( );
        assertEquals( "La sucursal deber�a haber eliminado 4 l�neas de producto", 4, sucursal.eliminarLineasProductosDadoRango( 50, 500 ) );
        assertEquals( "La sucursal deber�a tener 5 l�neas de producto", 5, sucursal.darLineasProducto( ).size( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo establecerSiguiente<br>
     * <b> M�todos a probar: </b> <br>
     * establecerSiguiente<br>
     * darSiguiente<br>
     * <b> Objetivo: </b> Probar que se coloque correctamente el siguiente elemento de la lista<br>
     * <b> Resultados esperados: </b> <br>
     * 1. La sucursal tendr� un elemento siguiente.
     */
    public void testEstablecerSiguiente( )
    {
        setupEscenario1( );
        assertNull( "La sucursal siguiente deber�a ser null", sucursal.darSiguiente( ) );
        Sucursal temp = new Sucursal( "Sucursal 2", "Direcci�n 2" );

        sucursal.cambiarSiguiente( temp );
        assertNotNull( "La sucursal siguiente no deber�a ser null", sucursal.darSiguiente( ) );
        assertEquals( "Las sucursales no coinciden", temp, sucursal.darSiguiente( ) );
    }
}
