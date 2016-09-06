/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Muñoz - Oct 19, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa una sucursal del hipermercado. Las sucursales se encuentran organizadas en una lista sencillamente encadenadas Las líneas de producto se encuentran en
 * una lista doblemente encadenada con referencia a la cabeza. <b>inv: </b> <br>
 * nombre != null direccion != null<br>
 * primeraLineaProducto.darAnterior() == null <br>
 * para toda línea de producto, si lineaProducto.darSiguiente() != null entonces lineaProducto.darSiguiente().darAnterior() == lineaProducto<br>
 * para toda línea de producto, si lineaProducto.darAnterior() != null entonces lineaProducto.darAnterior().darSiguiente() == lineaProducto<br>
 */
public class Sucursal implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versión para la serialización
     */
    private static final long serialVersionUID = 1884900645933675837L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre de la sucursal
     */
    private String nombre;

    /**
     * Dirección de la sucursal
     */
    private String direccion;

    /**
     * Referencia a la siguiente sucursal
     */
    private Sucursal siguiente;

    /**
     * Primera línea de producto de la sucursal
     */
    private LineaProducto primeraLineaProducto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por párametros de la clase. Se inicializan los atributos con los parámetros. La primeraLineaProducto se inicia en null.
     * @param nombreSucursal Nombre para la sucursal. Diferente de null
     * @param direccionSucursal Dirección que tiene la sucursal. Diferente de null
     */
    public Sucursal( String nombreSucursal, String direccionSucursal )
    {
        nombre = nombreSucursal;
        direccion = direccionSucursal;
        siguiente = null;
        primeraLineaProducto = null;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre que tiene la sucursal
     * @return El nombre de la sucursal. Diferente de null
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la referencia que se tiene a la siguiente sucursal
     * @return Sucursal que se encuentra siguiente en la lista. Puede ser null
     */
    public Sucursal darSiguiente( )
    {
        return siguiente;
    }

    /**
     * Método que agrega una nueva línea de producto en la sucursal. <b>post:</b> Se agrega una nueva línea de producto a la sucursal. En caso que ya exista una línea de
     * producto con el mismo código se lanza una excepción<br>
     * @param nombreProducto Nombre que se da al producto. Diferente de null
     * @param codigoProducto Código que se le asignara al producto. Diferente de null
     * @param descripcion Descripción que se hace de la línea de producto. Diferente de null
     * @param precio Precio que tendrán los productos de esa línea. Mayor que cero
     * @param fecha Fecha cuando fue lanzada la línea del producto. Diferente de null
     * @throws LineaProductoExisteException Se lanza cuando ya se encuentra un producto identificado con el mismo código
     */
    public void lanzarLineaProducto( String nombreProducto, String codigoProducto, String descripcion, int precio, Fecha fecha ) throws LineaProductoExisteException
    {
        if( primeraLineaProducto == null )
        {
            primeraLineaProducto = new LineaProducto( nombreProducto, codigoProducto, descripcion, precio, fecha );
        }
        else
        {
            LineaProducto iterador = primeraLineaProducto;
            boolean termino = false;
            while( iterador != null && !termino )
            {
                if( iterador.darCodigo( ).equalsIgnoreCase( codigoProducto ) )
                {
                    throw new LineaProductoExisteException( "Ya se encuentra en la sucursal una línea de producto con ese misma dirección" );
                }
                else
                {
                    if( iterador.darSiguiente( ) == null )
                    {
                        LineaProducto producto = new LineaProducto( nombreProducto, codigoProducto, descripcion, precio, fecha );
                        iterador.cambiarSiguiente( producto );
                        producto.cambiarAnterior( iterador );
                        termino = true;
                    }
                }
                iterador = iterador.darSiguiente( );
            }
        }
        verificarInvariante( );
    }

    /**
     * Método que retorna la línea de producto mas antigua de esta sucursal
     * @return Línea de producto más antigua. Null en caso que no haya líneas de producto
     */
    public LineaProducto darLineaProductoMasAntiguo( )
    {
        LineaProducto producto = null;
        LineaProducto iterador = primeraLineaProducto;
        producto = iterador;
        while( iterador != null )
        {
            Fecha fecha = producto.darFecha( );
            if( ! ( fecha.esAntes( iterador.darFecha( ) ) ) )
                producto = iterador;
            iterador = iterador.darSiguiente( );
        }
        return producto;
    }

    /**
     * Método que retorna una lista con los nombres de las líneas de productos de esta sucursal
     * @return Lista que contiene objetos de tipo String. Esta lista es diferente de null, pero puede ser vacía
     */
    public ArrayList darCodigosLineasProductos( )
    {
        ArrayList nombres = new ArrayList( );
        LineaProducto iterador = primeraLineaProducto;
        while( iterador != null )
        {
            nombres.add( iterador.darCodigo( ) );
            iterador = iterador.darSiguiente( );
        }
        return nombres;
    }

    /**
     * Método que retorna la dirección seguida de el nombre de la sucursal
     * @return Cadena de caracteres que contiene la dirección de la sucursal seguida de " - " y luego el nombre
     */
    public String toString( )
    {
        return direccion + " - " + nombre;
    }

    /**
     * Método que retira una línea de producto dado su nombre
     * @param codigoProducto Código del producto a ser descontinuado. Diferente de null
     * @throws LineaProductoNoExisteException Excepción que ocurre cuando no se encuentra el producto especificado
     */
    public void descontinuarLineaProducto( String codigoProducto ) throws LineaProductoNoExisteException
    {
        if( primeraLineaProducto == null )
            throw new LineaProductoNoExisteException( "No existe ninguna línea de producto" );
        LineaProducto iterador = primeraLineaProducto;
        LineaProducto anterior = null;
        boolean encontrado = false;
        while( iterador != null )
        {
            if( iterador.darCodigo( ).equals( codigoProducto ) )
            {
                encontrado = true;
                if( anterior != null )
                {
                    anterior.cambiarSiguiente( iterador.darSiguiente( ) );
                }
                else
                {
                    primeraLineaProducto = iterador.darSiguiente( );
                }
                if( iterador.darSiguiente( ) != null )
                {
                    iterador.darSiguiente( ).cambiarAnterior( anterior );
                }
            }
            anterior = iterador;
            iterador = iterador.darSiguiente( );
        }
        if( !encontrado )
            throw new LineaProductoNoExisteException( "No existe la línea de producto" );
        verificarInvariante( );
    }

    /**
     * Método que elimina las líneas de producto que tengan un valor inferior o igual al rango dado <b>post: </b>Si existe una línea de producto identificada con el código, se
     * elimina. Se retorna la cantidad de líneas eliminadas.<br>
     * @param inferior Limite inferior del rango. Entero mayor a cero
     * @param superior Limite superior del rango. Entero mayor a cero
     * @return Cantidad de productos eliminados. Entero mayor o igual a cero
     */
    public int eliminarLineasProductosDadoRango( int inferior, int superior )
    {
        int contador = 0;
        if( primeraLineaProducto != null )
        {
            LineaProducto iterador = primeraLineaProducto;
            while( iterador != null )
            {
                if( iterador.darPrecioUnidad( ) >= inferior && iterador.darPrecioUnidad( ) <= superior )
                {
                    LineaProducto anterior = iterador.darAnterior( );
                    LineaProducto siguiente = iterador.darSiguiente( );

                    if( anterior == null )
                    {
                        primeraLineaProducto = siguiente;
                    }
                    else
                    {
                        anterior.cambiarSiguiente( siguiente );
                    }
                    if( siguiente != null )
                        siguiente.cambiarAnterior( anterior );
                    contador++;
                }
                iterador = iterador.darSiguiente( );
            }
        }
        verificarInvariante( );
        return contador;
    }

    /**
     * Retorna una lista con todos las líneas de productos que tiene la sucursal
     * @return Lista con objetos de tipo LineaProducto. La lista es diferente de null pero puede ser vacia
     */
    public ArrayList darLineasProducto( )
    {
        ArrayList productos = new ArrayList( );
        LineaProducto iterador = primeraLineaProducto;
        while( iterador != null )
        {
            productos.add( iterador );
            iterador = iterador.darSiguiente( );
        }
        return productos;
    }

    /**
     * Retorna la dirección de la sucursal
     * @return Cadena de caracteres que contiene la dirección de la sucursal, esta cadena es diferente de null
     */
    public String darDireccion( )
    {
        return direccion;
    }

    /**
     * Método que cambia la referencia del la siguiente sucursal en la lista sencillamente encadenada <b>post:</b> El siguiente elemento ahora es que se pasó por parámetro<br>
     * @param sucursal Siguiente sucursal, puede ser null
     */
    public void cambiarSiguiente( Sucursal sucursal )
    {
        siguiente = sucursal;
    }

    /**
     * Verifica la invariante de la clase<br>
     * <b>inv: </b> nombre != null direccion != null<br>
     * primeraLineaProducto.darAnterior() == null <br>
     * para toda línea de producto, si lineaProducto.darSiguiente() != null entonces lineaProducto.darSiguiente().darAnterior() == lineaProducto<br>
     * para toda línea de producto, si lineaProducto.darAnterior() != null entonces lineaProducto.darAnterior().darSiguiente() == lineaProducto<br>
     */
    private void verificarInvariante( )
    {
        assert nombre != null : "El nombre no puede ser null";
        assert direccion != null : "La dirección no puede ser null";

        if( primeraLineaProducto != null )
            assert primeraLineaProducto.darAnterior( ) == null : "La primera línea no puede tener un anterior";

        // Verificar que la lista esté bien construida
        LineaProducto temp = primeraLineaProducto;
        while( temp != null )
        {
            if( temp.darSiguiente( ) != null )
            {
                assert ( temp.darSiguiente( ).darAnterior( ) == temp ) : "La lista no está bien construida";
            }

            if( temp.darAnterior( ) != null )
            {
                assert ( temp.darAnterior( ).darSiguiente( ) == temp ) : "La lista no está bien construida";
            }

            temp = temp.darSiguiente( );
        }
    }
}