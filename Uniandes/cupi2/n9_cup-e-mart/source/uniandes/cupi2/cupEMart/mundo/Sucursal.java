/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Mu�oz - Oct 19, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEMart.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa una sucursal del hipermercado. Las sucursales se encuentran organizadas en una lista sencillamente encadenadas Las l�neas de producto se encuentran en
 * una lista doblemente encadenada con referencia a la cabeza. <b>inv: </b> <br>
 * nombre != null direccion != null<br>
 * primeraLineaProducto.darAnterior() == null <br>
 * para toda l�nea de producto, si lineaProducto.darSiguiente() != null entonces lineaProducto.darSiguiente().darAnterior() == lineaProducto<br>
 * para toda l�nea de producto, si lineaProducto.darAnterior() != null entonces lineaProducto.darAnterior().darSiguiente() == lineaProducto<br>
 */
public class Sucursal implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indicador de versi�n para la serializaci�n
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
     * Direcci�n de la sucursal
     */
    private String direccion;

    /**
     * Referencia a la siguiente sucursal
     */
    private Sucursal siguiente;

    /**
     * Primera l�nea de producto de la sucursal
     */
    private LineaProducto primeraLineaProducto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por p�rametros de la clase. Se inicializan los atributos con los par�metros. La primeraLineaProducto se inicia en null.
     * @param nombreSucursal Nombre para la sucursal. Diferente de null
     * @param direccionSucursal Direcci�n que tiene la sucursal. Diferente de null
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
    // M�todos
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
     * M�todo que agrega una nueva l�nea de producto en la sucursal. <b>post:</b> Se agrega una nueva l�nea de producto a la sucursal. En caso que ya exista una l�nea de
     * producto con el mismo c�digo se lanza una excepci�n<br>
     * @param nombreProducto Nombre que se da al producto. Diferente de null
     * @param codigoProducto C�digo que se le asignara al producto. Diferente de null
     * @param descripcion Descripci�n que se hace de la l�nea de producto. Diferente de null
     * @param precio Precio que tendr�n los productos de esa l�nea. Mayor que cero
     * @param fecha Fecha cuando fue lanzada la l�nea del producto. Diferente de null
     * @throws LineaProductoExisteException Se lanza cuando ya se encuentra un producto identificado con el mismo c�digo
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
                    throw new LineaProductoExisteException( "Ya se encuentra en la sucursal una l�nea de producto con ese misma direcci�n" );
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
     * M�todo que retorna la l�nea de producto mas antigua de esta sucursal
     * @return L�nea de producto m�s antigua. Null en caso que no haya l�neas de producto
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
     * M�todo que retorna una lista con los nombres de las l�neas de productos de esta sucursal
     * @return Lista que contiene objetos de tipo String. Esta lista es diferente de null, pero puede ser vac�a
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
     * M�todo que retorna la direcci�n seguida de el nombre de la sucursal
     * @return Cadena de caracteres que contiene la direcci�n de la sucursal seguida de " - " y luego el nombre
     */
    public String toString( )
    {
        return direccion + " - " + nombre;
    }

    /**
     * M�todo que retira una l�nea de producto dado su nombre
     * @param codigoProducto C�digo del producto a ser descontinuado. Diferente de null
     * @throws LineaProductoNoExisteException Excepci�n que ocurre cuando no se encuentra el producto especificado
     */
    public void descontinuarLineaProducto( String codigoProducto ) throws LineaProductoNoExisteException
    {
        if( primeraLineaProducto == null )
            throw new LineaProductoNoExisteException( "No existe ninguna l�nea de producto" );
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
            throw new LineaProductoNoExisteException( "No existe la l�nea de producto" );
        verificarInvariante( );
    }

    /**
     * M�todo que elimina las l�neas de producto que tengan un valor inferior o igual al rango dado <b>post: </b>Si existe una l�nea de producto identificada con el c�digo, se
     * elimina. Se retorna la cantidad de l�neas eliminadas.<br>
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
     * Retorna una lista con todos las l�neas de productos que tiene la sucursal
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
     * Retorna la direcci�n de la sucursal
     * @return Cadena de caracteres que contiene la direcci�n de la sucursal, esta cadena es diferente de null
     */
    public String darDireccion( )
    {
        return direccion;
    }

    /**
     * M�todo que cambia la referencia del la siguiente sucursal en la lista sencillamente encadenada <b>post:</b> El siguiente elemento ahora es que se pas� por par�metro<br>
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
     * para toda l�nea de producto, si lineaProducto.darSiguiente() != null entonces lineaProducto.darSiguiente().darAnterior() == lineaProducto<br>
     * para toda l�nea de producto, si lineaProducto.darAnterior() != null entonces lineaProducto.darAnterior().darSiguiente() == lineaProducto<br>
     */
    private void verificarInvariante( )
    {
        assert nombre != null : "El nombre no puede ser null";
        assert direccion != null : "La direcci�n no puede ser null";

        if( primeraLineaProducto != null )
            assert primeraLineaProducto.darAnterior( ) == null : "La primera l�nea no puede tener un anterior";

        // Verificar que la lista est� bien construida
        LineaProducto temp = primeraLineaProducto;
        while( temp != null )
        {
            if( temp.darSiguiente( ) != null )
            {
                assert ( temp.darSiguiente( ).darAnterior( ) == temp ) : "La lista no est� bien construida";
            }

            if( temp.darAnterior( ) != null )
            {
                assert ( temp.darAnterior( ).darSiguiente( ) == temp ) : "La lista no est� bien construida";
            }

            temp = temp.darSiguiente( );
        }
    }
}