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
 * Esta es la clase usada para verificar que los m�todos de la clase CupiMart est�n correctamente implementados
 */
public class CupEMartTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private CupEMart cupEMart;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva CupiMart vac�a
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
            cupEMart.agregarSucursal( "Sucursal 1", "Direcci�n 1" );
            cupEMart.agregarSucursal( "Sucursal 2", "Direcci�n 2" );
            cupEMart.agregarSucursal( "Sucursal 3", "Direcci�n 3" );
            cupEMart.agregarSucursal( "Sucursal 4", "Direcci�n 4" );
            cupEMart.agregarSucursal( "Sucursal 5", "Direcci�n 5" );
            cupEMart.agregarSucursal( "Sucursal 6", "Direcci�n 6" );
            cupEMart.agregarSucursal( "Sucursal 7", "Direcci�n 7" );
        }
        catch( SucursalExisteException e )
        {
            fail( );
        }
    }

    /**
     * Construye una nueva CupiMart con 6 sucursales y la distribuci�n de l�neas de productos de la siguiente manera y cada una con al menos una l�nea de producto
     */
    private void setupEscenario3( )
    {
        setupEscenario2( );
        try
        {
            cupEMart.lanzarLineaProductoSucursal( "Direcci�n 1", "Producto 1", "asdf1", "L�nea de producto de prueba", 150, new Fecha( 22, 5, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Direcci�n 1", "Producto 2", "asdf2", "L�nea de producto de prueba", 150, new Fecha( 22, 5, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Direcci�n 2", "Producto 3", "asdf3", "L�nea de producto de prueba", 150, new Fecha( 19, 9, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Direcci�n 3", "Producto 4", "asdf4", "L�nea de producto de prueba", 150, new Fecha( 25, 5, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Direcci�n 4", "Producto 5", "asdf5", "L�nea de producto de prueba", 15000, new Fecha( 1, 7, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Direcci�n 4", "Producto 6", "asdf6", "L�nea de producto de prueba", 1500, new Fecha( 13, 12, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Direcci�n 5", "Producto 7", "asdf7", "L�nea de producto de prueba", 15000, new Fecha( 15, 4, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Direcci�n 6", "Producto 8", "asdf8", "L�nea de producto de prueba", 15000, new Fecha( 18, 9, 2006 ) );
            cupEMart.lanzarLineaProductoSucursal( "Direcci�n 6", "Producto 9", "asdf9", "L�nea de producto de prueba", 20, new Fecha( 30, 7, 2005 ) );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "No deber�a fallar encontrando la sucursal" );
        }
        catch( LineaProductoExisteException e )
        {
            fail( "No deber�a fallar porque el producto no ha sido creado" );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo AgregarSucursal<br>
     * <b> M�todos a probar: </b> <br>
     * agregarSucursal<br>
     * darSucursales<br>
     * darSucursal<br>
     * <b> Objetivo: </b> Probar la inserci�n de una nueva sucursal<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creaci�n de una nueva sucursal con el nombre Sucursal 1 y la direcci�n Calle 69 # 72-18 <br>
     * 2. Al tratar de agregar una sucursal que ya existe, que genere la excepci�n.<br>
     */
    public void testAgregarSucursal( )
    {
        setupEscenario1( );
        assertEquals( "No deben existir sucursales", 0, cupEMart.darSucursales( ).size( ) );
        assertEquals( "La lista de sucursales deber�a ser vac�a", 0, cupEMart.darSucursales( ).size( ) );
        try
        {
            cupEMart.agregarSucursal( "Sucursal 1", "Calle 69 # 72-18" );
            assertEquals( "Deber�a existir 1 sucursal", 1, cupEMart.darSucursales( ).size( ) );
            assertNotNull( "Deber�a encontrar la sucursal con nombre Sucursal 1", cupEMart.darSucursal( "Calle 69 # 72-18" ) );
            assertNotNull( "La lista de sucursales no deber�a ser null", cupEMart.darSucursales( ) );
            assertEquals( "La lista deber�a tener un elemento", 1, cupEMart.darSucursales( ).size( ) );
        }
        catch( SucursalExisteException e )
        {
            fail( "La sucursal no deber�a existir" );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "La sucursal deber�a existir" );
        }
        try
        {
            cupEMart.agregarSucursal( "Sucursal 1", "Calle 69 # 72-18" );
            fail( "No deber�a poder agregar la sucursal, ya que esta existe" );
        }
        catch( SucursalExisteException e )
        {
            // Deber�a ocurrir
        }

    }

    /**
     * Este m�todo se encarga de verificar el m�todo eliminarSucursal<br>
     * <b> M�todos a probar: </b> <br>
     * eliminarSucursal<br>
     * darSucursales<br>
     * darSucursal<br>
     * <b> Objetivo: </b> Probar la eliminaci�n de una nueva sucursal<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Eliminaci�n de una sucursal con nombre Sucursal 5<br>
     * 2. Al tratar de eliminar una sucursal que no existe, que genere la excepci�n.<br>
     */
    public void testEliminarSucursal( )
    {
        setupEscenario2( );
        assertNotNull( "La lista de sucursales no deber�a se null", cupEMart.darSucursales( ) );
        assertEquals( "La lista de sucursales deber�a tener 7 elementos", 7, cupEMart.darSucursales( ).size( ) );
        try
        {
            cupEMart.eliminarSucursal( "Direcci�n 5" );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "La sucursal deber�a existir" );
        }

        assertNotNull( "La lista de sucursales no deber�a se null", cupEMart.darSucursales( ) );
        assertEquals( "La lista de sucursales deber�a tener 6 elementos", 6, cupEMart.darSucursales( ).size( ) );

        try
        {
            cupEMart.darSucursal( "Direcci�n 5" );
            fail( "No debi� encontrar la sucursal" );

        }
        catch( SucursalNoExisteException e )
        {
            // Deber�a entrar ac�
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo lanzarLineaProductoSucursal<br>
     * <b> M�todos a probar: </b> <br>
     * lanzarLineaProductoSucursal<br>
     * listarLineasProductosSucursal<br>
     * <b> Objetivo: </b> Probar el lanzamiento de una nueva l�nea de producto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se agrega una l�nea de producto a la sucursal con nombre Sucursal 5. La l�nea de producto tendr� los valores: Nombre: Producto 1 C�digo: asdf Descripci�n: L�nea de
     * producto de prueba Precio: 1500 Fecha: 22,5,2006<br>
     * 2. Al tratar de lanzar una l�nea de producto que existe, que genere la excepci�n.<br>
     */
    public void testLanzarLineaProductoSucursal( )
    {
        setupEscenario2( );

        try
        {
            cupEMart.lanzarLineaProductoSucursal( "Direcci�n 5", "Producto 1", "asdf", "L�nea de producto de prueba", 1500, new Fecha( 22, 5, 2006 ) );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "La sucursal: Sucursal 5 deber�a existir" );
        }
        catch( LineaProductoExisteException e )
        {
            fail( "la l�nea de producto con nombre Producto 5 no deber�a existir" );
        }
        try
        {
            cupEMart.lanzarLineaProductoSucursal( "Direcci�n 5", "Producto 1", "asdf", "L�nea de producto de prueba", 1500, new Fecha( 22, 5, 2006 ) );
            fail( "No deber�a poder lanzar la l�nea de producto porque ya existe" );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "La sucursal: Sucursal 5 deber�a existir" );
        }
        catch( LineaProductoExisteException e )
        {
            // Deber�a atrapar esta excepci�n
        }

        assertNotNull( "La lista de l�neas de producto de la sucursal 5 no deber�a ser null.", cupEMart.darLineasProductoSucursal( "Direcci�n 5" ) );
        assertEquals( "La lista de l�neas de producto de la sucursal 5 deber�a tener 1 elemento", 1, cupEMart.darLineasProductoSucursal( "Direcci�n 5" ).size( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo darLineaProductoMasAntigua<br>
     * <b> M�todos a probar: </b> <br>
     * darLineaProductoMasAntigua<br>
     * <b> Objetivo: </b> Probar dar la l�nea de producto m�s antigua<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se encuentre una l�nea de producto como la m�s antigua
     */
    public void testDarLineaProductoMasAntigua( )
    {
        setupEscenario1( );
        try
        {
            cupEMart.darLineaProductoMasAntigua( );
            fail( "No deber�a encontrar una l�nea de producto" );
        }
        catch( LineaProductoNoExisteException e1 )
        {
            // Deber�a entrar al catch
        }

        setupEscenario3( );
        try
        {
            assertNotNull( "No deber�a retornar una l�nea de producto null", cupEMart.darLineaProductoMasAntigua( ) );
            assertEquals( "La l�nea de producto m�s antigua deber�a tener nombre Producto 9", "Producto 9", cupEMart.darLineaProductoMasAntigua( ).darNombre( ) );
        }
        catch( LineaProductoNoExisteException e )
        {
            fail( "Si existen l�neas de productos en las sucursales" );
        }
    }

    /**
     * Este m�todo se encarga de verificar el m�todo descontinuarLineaProducto<br>
     * <b> M�todos a probar: </b> <br>
     * descontinuarLineaProducto<br>
     * listarLineasProductoSucursal<br>
     * <b> Objetivo: </b> Probar descontinuar una l�nea de producto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se descontinua la l�nea de producto Producto 7 de la Sucursal 5
     */
    public void testDescontinuarLineaProducto( )
    {
        setupEscenario1( );
        try
        {
            cupEMart.descontinuarLineaProducto( "Sucursal 5", "Producto 7" );
            fail( "No deber�a encontrar una sucursal" );
        }
        catch( LineaProductoNoExisteException e1 )
        {
            fail( "Deber�a validar primero cual la sucursal" );
        }
        catch( SucursalNoExisteException e1 )
        {
            // Deber�a entrar a este catch
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
            fail( "La sucursal deber�a estar" );
        }

        setupEscenario3( );
        try
        {
            cupEMart.descontinuarLineaProducto( "Sucursal 5", "Producto 7" );
            assertEquals( "La lista de l�neas de producto deber�a ser vac�a", 0, cupEMart.darLineasProductoSucursal( "Sucursal 5" ).size( ) );
        }
        catch( LineaProductoNoExisteException e )
        {
            fail( "La l�nea de producto deber�a existir en la sucursal" );
        }
        catch( SucursalNoExisteException e )
        {
            fail( "La sucursal deber�a existir" );
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
        setupEscenario3( );
        assertEquals( "Deber�a haber eliminado 4 l�neas de producto", 4, cupEMart.eliminarLineasProductosDadoRango( 100, 500 ) );

        assertEquals( "La sucursal 1 deber�a tener 0 l�neas de producto", 0, cupEMart.darLineasProductoSucursal( "Direcci�n 1" ).size( ) );
        assertEquals( "La sucursal 2 deber�a tener 0 l�neas de producto", 0, cupEMart.darLineasProductoSucursal( "Direcci�n 2" ).size( ) );
        assertEquals( "La sucursal 3 deber�a tener 0 l�neas de producto", 0, cupEMart.darLineasProductoSucursal( "Direcci�n 3" ).size( ) );
        assertEquals( "La sucursal 4 deber�a tener 2 l�neas de producto", 2, cupEMart.darLineasProductoSucursal( "Direcci�n 4" ).size( ) );
        assertEquals( "La sucursal 5 deber�a tener 1 l�neas de producto", 1, cupEMart.darLineasProductoSucursal( "Direcci�n 5" ).size( ) );
        assertEquals( "La sucursal 6 deber�a tener 2 l�neas de producto", 2, cupEMart.darLineasProductoSucursal( "Direcci�n 6" ).size( ) );
    }

    /**
     * Se prueba que se guarda y carga correctamente los archivos serializados. <b> M�todos a probar: </b> <br>
     * guardarCupEMart<br>
     * cargarCupEMart<br>
     * <b> Objetivo: </b> Verificaci�n que se actualizan correctamente los datos del hipermercado.<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Guardar un archivo con el estado del mundo<br>
     * 2. Leer del archivo con el estado del mundo<br>
     * 3. Cargar la cantidad correcta de sucursales<br>
     * 4. Cargar la cantidad correcta de l�neas de producto para la sucursal<br>
     */
    public void testGuardarCargarSistema( )
    {
        try
        {
            setupEscenario3( );
            cupEMart.guardarCupEMart( "./test/data/archivoPrueba.data" );
            cupEMart.cargarCupEMart( "./test/data/archivoPrueba.data" );
            Sucursal sucursal = cupEMart.darSucursal( "Direcci�n 1" );
            assertTrue( "Error en el nombre de la sucursal", sucursal.darNombre( ).equals( "Sucursal 1" ) );
            assertTrue( "Error en el g�nero de la sucursal", sucursal.darDireccion( ).equals( "Direcci�n 1" ) );

            ArrayList lineas = cupEMart.darLineasProductoSucursal( sucursal.darDireccion( ) );
            assertEquals( "Las l�neas de producto deber�an ser 2", 2, lineas.size( ) );
            assertEquals( "Las l�neas de producto deber�an ser 1", 1, cupEMart.darLineasProductoSucursal( "Direcci�n 2" ).size( ) );
            assertEquals( "Las l�neas de producto deber�an ser 1", 1, cupEMart.darLineasProductoSucursal( "Direcci�n 3" ).size( ) );
            assertEquals( "Las l�neas de producto deber�an ser 2", 2, cupEMart.darLineasProductoSucursal( "Direcci�n 4" ).size( ) );
            assertEquals( "Las l�neas de producto deber�an ser 1", 1, cupEMart.darLineasProductoSucursal( "Direcci�n 5" ).size( ) );
            assertEquals( "Las l�neas de producto deber�an ser 2", 2, cupEMart.darLineasProductoSucursal( "Direcci�n 6" ).size( ) );

            for( int i = 0; i < lineas.size( ); i++ )
            {
                LineaProducto producto = ( LineaProducto )lineas.get( i );
                assertEquals( "El c�digo del producto deber�a ser asdf" + ( i + 1 ), "asdf" + ( i + 1 ), producto.darCodigo( ) );
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