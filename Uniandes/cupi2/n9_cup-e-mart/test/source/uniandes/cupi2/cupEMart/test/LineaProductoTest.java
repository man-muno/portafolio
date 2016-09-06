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

import junit.framework.TestCase;
import uniandes.cupi2.cupEMart.mundo.Fecha;
import uniandes.cupi2.cupEMart.mundo.LineaProducto;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase LineaProducto est�n correctamente implementados
 */
public class LineaProductoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private LineaProducto lineaProducto;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva l�nea de producto con los valores La l�nea de producto tendr� los valores: Nombre: Producto 1 C�digo: asdf Descripci�n: L�nea de producto de prueba
     * Precio: 150 Fecha: 22,5,2006<br>
     */
    private void setupEscenario1( )
    {
        lineaProducto = new LineaProducto( "Producto 1", "asdf1", "L�nea de producto de prueba", 150, new Fecha( 22, 5, 2006 ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo constructor<br>
     * <b> M�todos a probar: </b> <br>
     * LineaProducto<br>
     * darCodigo<br>
     * darDescripcion<br>
     * darPrecio<br>
     * darSiguiente<br>
     * darAnterior<br>
     * <b> Objetivo: </b> Probar la inicializaci�n correcta de una l�nea de producto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creaci�n de una nueva l�nea de producto con los valores: Nombre: Producto 1 C�digo: asdf Descripci�n: L�nea de producto de prueba Precio: 150 Fecha: 22,5,2006<br>
     * 2. La inicializaci�n de los atributos de la l�nea de producto sea correcta.<br>
     */
    public void testLineaProducto( )
    {
        setupEscenario1( );

        assertNotNull( "El nombre de la l�nea de producto no deber�a ser null", lineaProducto.darNombre( ) );
        assertNotNull( "El c�digo de la l�nea de producto no deber�a ser null", lineaProducto.darCodigo( ) );
        assertNotNull( "La descripci�n de la l�nea de producto no deber�a ser null", lineaProducto.darDescripcion( ) );
        assertTrue( "El precio debe ser mayor a cero", lineaProducto.darPrecioUnidad( ) > 0 );
        assertNull( "El elemento siguiente de la lista deber�a ser null", lineaProducto.darSiguiente( ) );
        assertNull( "El elemento anterior de la lista deber�a ser null", lineaProducto.darAnterior( ) );

        assertEquals( "El nombre de la l�nea de producto deber�a ser Producto 1", "Producto 1", lineaProducto.darNombre( ) );
        assertEquals( "El c�digo de la l�nea de producto deber�a ser asdf1", "asdf1", lineaProducto.darCodigo( ) );
        assertEquals( "La descripci�n de la l�nea de producto deber�a ser L�nea de producto de prueba", "L�nea de producto de prueba", lineaProducto.darDescripcion( ) );
        assertEquals( "El precio de la l�nea de producto deber�a ser 150", 150, lineaProducto.darPrecioUnidad( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo cambiarAnterior<br>
     * <b> M�todos a probar: </b> <br>
     * cambiarAnterior<br>
     * darSiguiente<br>
     * darAnterior<br>
     * <b> Objetivo: </b> Probar la asignaci�n correcta del elemento anterior de la lista<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creaci�n de una nueva l�nea de producto con los valores: Nombre: Producto 2 C�digo: qwer Descripci�n: L�nea de producto de prueba Precio: 1500 Fecha: 21,12,1999<br>
     * 2. El elemento anterior sea colocado correctamente<br>
     */
    public void testCambiarAnterior( )
    {
        setupEscenario1( );

        assertNull( "El elemento siguiente de la lista deber�a ser null", lineaProducto.darSiguiente( ) );
        assertNull( "El elemento anterior de la lista deber�a ser null", lineaProducto.darAnterior( ) );

        LineaProducto temp = new LineaProducto( "Producto 2", "qwer", "L�nea de producto de prueba", 1500, new Fecha( 21, 12, 1999 ) );
        lineaProducto.cambiarAnterior( temp );

        assertNull( "El elemento siguiente de la lista deber�a ser null", lineaProducto.darSiguiente( ) );
        assertNotNull( "El elemento anterior de la lista no deber�a ser null", lineaProducto.darAnterior( ) );

    }

    /**
     * Este m�todo se encarga de verificar el m�todo cambiarSiguiente<br>
     * <b> M�todos a probar: </b> <br>
     * cambiarSiguiente<br>
     * darSiguiente<br>
     * darAnterior<br>
     * <b> Objetivo: </b> Probar la asignaci�n correcta del elemento siguiente de la lista<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creaci�n de una nueva l�nea de producto con los valores: Nombre: Producto 2 C�digo: qwer Descripci�n: L�nea de producto de prueba Precio: 1500 Fecha: 21,12,1999<br>
     * 2. El elemento siguiente sea colocado correctamente<br>
     */
    public void testCambiarSiguiente( )
    {
        setupEscenario1( );

        assertNull( "El elemento siguiente de la lista deber�a ser null", lineaProducto.darSiguiente( ) );
        assertNull( "El elemento anterior de la lista deber�a ser null", lineaProducto.darAnterior( ) );

        LineaProducto temp = new LineaProducto( "Producto 2", "qwer", "L�nea de producto de prueba", 1500, new Fecha( 21, 12, 1999 ) );
        lineaProducto.cambiarSiguiente( temp );

        assertNotNull( "El elemento siguiente de la no lista deber�a ser null", lineaProducto.darSiguiente( ) );
        assertNull( "El elemento anterior de la lista deber�a ser null", lineaProducto.darAnterior( ) );
    }

}