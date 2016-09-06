/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
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

/**
 * Clase que representa una línea de producto que maneja una sucursal. Las líneas de producto estan en una lista doblemente encadenada <br>
 * <b>inv: </b> <br>
 * nombre != null codigo != null descripcion != null precio > 0 fecha != null
 */
public class LineaProducto implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versión para la serialización
     */
    private static final long serialVersionUID = -9169368522749731384L;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del producto
     */
    private String nombre;

    /**
     * Código del producto
     */
    private String codigo;

    /**
     * Descripción del producto
     */
    private String descripcion;

    /**
     * Precio por unidad del producto
     */
    private int precioUnidad;

    /**
     * Fecha de lanzamiento del producto
     */
    private Fecha fecha;

    /**
     * Referencia al siguiente elemento de la lista encadenada
     */
    private LineaProducto siguiente;

    /**
     * Referencia al anterior elemento de la lista encadenada
     */
    private LineaProducto anterior;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros. Se inicializan los atributos con los parámetros.<br>
     * siguiente y anterior se inicializan con null.
     * @param nombre2 Nombre que se le asignara a la línea de producto. Diferente de null
     * @param codigo2 Código que se le asignara a la línea de producto. Diferente de null
     * @param descripcion2 Descripción de la línea de producto. Diferente de null
     * @param precio2 Precio por unidad de esta línea de producto. Entero mayor a cero
     * @param fecha2 fecha de lanzamiento de la línea de producto. Diferente de null
     */
    public LineaProducto( String nombre2, String codigo2, String descripcion2, int precioUnidad2, Fecha fecha2 )
    {
        nombre = nombre2;
        codigo = codigo2;
        descripcion = descripcion2;
        precioUnidad = precioUnidad2;
        fecha = fecha2;
        siguiente = null;
        anterior = null;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método que retorna el código de la línea de producto
     * @return Cadena de caracteres que contiene el código de la línea de producto. Diferente de null
     */
    public String darCodigo( )
    {
        return codigo;
    }

    /**
     * Método que retorna la referencia a la siguiente posición de la lista encadenada
     * @return Objeto de tipo LineaProducto. Puede ser null
     */
    public LineaProducto darSiguiente( )
    {
        return siguiente;
    }

    /**
     * Método que retorna la fecha de lanzamiento de la línea de producto
     * @return Objeto de tipo fecha, diferente de null
     */
    public Fecha darFecha( )
    {
        return fecha;
    }

    /**
     * Método que cambia el anterior elemento de la lista encadenada de línea de producto<br>
     *  <b>post:</b> El elemento anterior ahora es el que se pasó por parámetro<br>
     * @param producto Nuevo elemento anterior. Puede ser null
     */
    public void cambiarAnterior( LineaProducto producto )
    {
        anterior = producto;
    }

    /**
     * Método que cambia el siguiente elemento de la lista encadenada de línea de producto<br>
     *  <b>post:</b> El elemento siguiente ahora es el que se pasó por parámetro<br>
     * @param siguiente2 Nuevo siguiente elemento. Puede ser null
     */
    public void cambiarSiguiente( LineaProducto siguiente2 )
    {
        siguiente = siguiente2;
    }

    /**
     * Método que retorna el nombre de la línea de producto
     * @return Cadena de caracteres que contiene el nombre de la línea de producto. Diferente de null
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Método que retorna la descripción de la línea de producto.
     * @return Cadena de caracteres que contiene la línea de producto. Diferente de null
     */
    public String darDescripcion( )
    {
        return descripcion;
    }

    /**
     * Método que retorna el precio por unidad de los productos de esta línea de producto
     * @return Real que contiene el precio de los productos de esta línea. Mayor que cero
     */
    public int darPrecioUnidad( )
    {
        return precioUnidad;
    }

    /**
     * Método que retorna el anterior elemento de la lista doblemente encadenada
     * @return Objeto de tipo LineaProducto que representa el elemento anterior de la lista. Puede ser null
     */
    public LineaProducto darAnterior( )
    {
        return anterior;
    }

    /**
     * Retorna una representación en String de la línea de producto.
     * @param String que contiene el código y luego el nombre.
     */
    public String toString( )
    {
        return codigo + " - " + nombre;
    }

    /**
     * Método que verifica la invariante de la clase <b>inv: </b> <br>
     * nombre != null codigo != null descripcion != null precio > 0 fecha != null
     */
    private void verificarInvariante( )
    {
        assert nombre != null : "El nombre no puede ser null";
        assert codigo != null : "El código no puede ser null";
        assert descripcion != null : "La descripción no puede ser null";
        assert fecha != null : "La fecha no puede ser null";
        assert precioUnidad > 0 : "Precio por unidad no puede ser menor o igual a cero";
    }
}