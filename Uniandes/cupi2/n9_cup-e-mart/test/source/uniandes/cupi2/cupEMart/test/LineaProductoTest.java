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

import junit.framework.TestCase;
import uniandes.cupi2.cupEMart.mundo.Fecha;
import uniandes.cupi2.cupEMart.mundo.LineaProducto;

/**
 * Esta es la clase usada para verificar que los métodos de la clase LineaProducto estén correctamente implementados
 */
public class LineaProductoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private LineaProducto lineaProducto;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva línea de producto con los valores La línea de producto tendrá los valores: Nombre: Producto 1 Código: asdf Descripción: Línea de producto de prueba
     * Precio: 150 Fecha: 22,5,2006<br>
     */
    private void setupEscenario1( )
    {
        lineaProducto = new LineaProducto( "Producto 1", "asdf1", "Línea de producto de prueba", 150, new Fecha( 22, 5, 2006 ) );
    }

    /**
     * Este método se encarga de verificar el método constructor<br>
     * <b> Métodos a probar: </b> <br>
     * LineaProducto<br>
     * darCodigo<br>
     * darDescripcion<br>
     * darPrecio<br>
     * darSiguiente<br>
     * darAnterior<br>
     * <b> Objetivo: </b> Probar la inicialización correcta de una línea de producto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creación de una nueva línea de producto con los valores: Nombre: Producto 1 Código: asdf Descripción: Línea de producto de prueba Precio: 150 Fecha: 22,5,2006<br>
     * 2. La inicialización de los atributos de la línea de producto sea correcta.<br>
     */
    public void testLineaProducto( )
    {
        setupEscenario1( );

        assertNotNull( "El nombre de la línea de producto no debería ser null", lineaProducto.darNombre( ) );
        assertNotNull( "El código de la línea de producto no debería ser null", lineaProducto.darCodigo( ) );
        assertNotNull( "La descripción de la línea de producto no debería ser null", lineaProducto.darDescripcion( ) );
        assertTrue( "El precio debe ser mayor a cero", lineaProducto.darPrecioUnidad( ) > 0 );
        assertNull( "El elemento siguiente de la lista debería ser null", lineaProducto.darSiguiente( ) );
        assertNull( "El elemento anterior de la lista debería ser null", lineaProducto.darAnterior( ) );

        assertEquals( "El nombre de la línea de producto debería ser Producto 1", "Producto 1", lineaProducto.darNombre( ) );
        assertEquals( "El código de la línea de producto debería ser asdf1", "asdf1", lineaProducto.darCodigo( ) );
        assertEquals( "La descripción de la línea de producto debería ser Línea de producto de prueba", "Línea de producto de prueba", lineaProducto.darDescripcion( ) );
        assertEquals( "El precio de la línea de producto debería ser 150", 150, lineaProducto.darPrecioUnidad( ) );
    }

    /**
     * Este método se encarga de verificar el método cambiarAnterior<br>
     * <b> Métodos a probar: </b> <br>
     * cambiarAnterior<br>
     * darSiguiente<br>
     * darAnterior<br>
     * <b> Objetivo: </b> Probar la asignación correcta del elemento anterior de la lista<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creación de una nueva línea de producto con los valores: Nombre: Producto 2 Código: qwer Descripción: Línea de producto de prueba Precio: 1500 Fecha: 21,12,1999<br>
     * 2. El elemento anterior sea colocado correctamente<br>
     */
    public void testCambiarAnterior( )
    {
        setupEscenario1( );

        assertNull( "El elemento siguiente de la lista debería ser null", lineaProducto.darSiguiente( ) );
        assertNull( "El elemento anterior de la lista debería ser null", lineaProducto.darAnterior( ) );

        LineaProducto temp = new LineaProducto( "Producto 2", "qwer", "Línea de producto de prueba", 1500, new Fecha( 21, 12, 1999 ) );
        lineaProducto.cambiarAnterior( temp );

        assertNull( "El elemento siguiente de la lista debería ser null", lineaProducto.darSiguiente( ) );
        assertNotNull( "El elemento anterior de la lista no debería ser null", lineaProducto.darAnterior( ) );

    }

    /**
     * Este método se encarga de verificar el método cambiarSiguiente<br>
     * <b> Métodos a probar: </b> <br>
     * cambiarSiguiente<br>
     * darSiguiente<br>
     * darAnterior<br>
     * <b> Objetivo: </b> Probar la asignación correcta del elemento siguiente de la lista<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creación de una nueva línea de producto con los valores: Nombre: Producto 2 Código: qwer Descripción: Línea de producto de prueba Precio: 1500 Fecha: 21,12,1999<br>
     * 2. El elemento siguiente sea colocado correctamente<br>
     */
    public void testCambiarSiguiente( )
    {
        setupEscenario1( );

        assertNull( "El elemento siguiente de la lista debería ser null", lineaProducto.darSiguiente( ) );
        assertNull( "El elemento anterior de la lista debería ser null", lineaProducto.darAnterior( ) );

        LineaProducto temp = new LineaProducto( "Producto 2", "qwer", "Línea de producto de prueba", 1500, new Fecha( 21, 12, 1999 ) );
        lineaProducto.cambiarSiguiente( temp );

        assertNotNull( "El elemento siguiente de la no lista debería ser null", lineaProducto.darSiguiente( ) );
        assertNull( "El elemento anterior de la lista debería ser null", lineaProducto.darAnterior( ) );
    }

}