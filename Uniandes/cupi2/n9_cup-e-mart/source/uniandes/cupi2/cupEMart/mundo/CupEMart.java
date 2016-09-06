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

package uniandes.cupi2.cupEMart.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * Clase que representa el hipermercado <br>
 * <b>inv:</b></br> la sucursal est� bien construida: no existe ninguna sucursal tal que sucursal.obtenerSiguiente() ==
 * primerasucursal</br> la sucursal est� bien construida: no existen sucursal1 y sucursal2 tal que sucursal1.obtenerSiguiente() == sucursal2.obtenerSiguiente() &&
 * sucursal1 != sucursal2</br> no hay dos sucursales con la misma direcci�n
 */
public class CupEMart
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que mantiene el nombre del archivo donde se guarda el estado del mundo
     */
    public static final String RUTA_ARCHIVO_PERSISTENCIA = "data/cup-e-mart.data";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia al primer elemento de la lista de sucursales
     */
    private Sucursal primeraSucursal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor un hipermercado con la informaci�n del archivo binario. <br>
     * Se deben considerar dos casos: <br>
     * si el archivo no existe se inicia la primera sucursal en null de lo contrario se inicializa con los datos del archivo.
     * @param nombreArchivo El nombre completo del archivo binario - nombreArchivo != null
     * @throws PersistenciaException Si hay problemas leyendo el archivo
     */
    public CupEMart( String nombreArchivo ) throws PersistenciaException
    {
        cargarCupEMart( nombreArchivo );
    }
    
    

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que agrega una nueva sucursal al final de la lista encadenada de sucursales. <br>
     * Si la primera sucursal es nula, la nueva sucursal ser� la primera.<br>
     * <b>post:</b> La lista de las sucursales tiene una nueva sucursal si solo si ya no existe una<br>
     * sucursal con esa direcci�n<br>
     * @param nombreSucursal Nombre que tendr� la nueva sucursal. Diferente de null.
     * @param direccionSucursal Direcci�n que tendr� la nueva sucursal. Diferente de null.
     * @throws SucursalExisteException Es lanzada cuando ya existe una sucursal con ese nombre.
     */
    public void agregarSucursal( String nombreSucursal, String direccionSucursal ) throws SucursalExisteException
    {
        Sucursal nSucursal = new Sucursal( nombreSucursal, direccionSucursal );

        // Si no tiene primera sucursal entonces la nueva sera la primera
        if( primeraSucursal == null )
        {
            primeraSucursal = nSucursal;
        }
        else
        {
            Sucursal ultima = null;
            // Variable que tendra una sucursal
            Sucursal iterador = primeraSucursal;
            while( iterador != null )
            {
                // Si la direccion es igual no se agrega
                if( iterador.darDireccion( ).equals( direccionSucursal ) )
                {
                    throw new SucursalExisteException( "La sucursal que se desea agregar ya existe" );
                }
                // Se busca cual es la ultima sucursal
                if( iterador.darSiguiente( ) != null )
                    ultima = iterador.darSiguiente( );
                else
                    ultima = iterador;
                iterador = iterador.darSiguiente( );
            }
            // Se agrega la sucursal a la ultima
            ultima.cambiarSiguiente( nSucursal );
        }
        verificarInvariante( );
    }

    /**
     * M�todo que se encarga de eliminar una sucursal dado su direcci�n.<br>
     * <b>post:</b>Si la sucursal existe ser� eliminada junto con sus l�neas de producto, de lo contrario se lanzar� una excepci�n <br>
     * @param direccionSucursal Direcci�n de la sucursal a ser eliminada. Diferente de null
     * @throws SucursalNoExisteException Excepci�n que es lanzada cuando no se encuentra la sucursal que se quer�a eliminada
     */
    public void eliminarSucursal( String direccionSucursal ) throws SucursalNoExisteException
    {
        // Si no tiene primera sucursal entonces no tiene nada que eliminar
        if( primeraSucursal == null )
        {
            throw new SucursalNoExisteException( "No se encontr� ninguna sucursal" );
        }

        // Variable que tendr� una sucursal
        Sucursal iterador = primeraSucursal;
        Sucursal anterior = null;
        boolean termino = false;
        while( iterador != null && !termino )
        {
            if( iterador.darDireccion( ).equals( direccionSucursal ) )
            {
                Sucursal siguiente = iterador.darSiguiente( );
                if( anterior != null )
                {
                    anterior.cambiarSiguiente( siguiente );
                }
                else
                {
                    primeraSucursal = primeraSucursal.darSiguiente( );
                }
                termino = true;
            }
            anterior = iterador;
            iterador = iterador.darSiguiente( );
        }
        verificarInvariante( );
    }

    /**
     * M�todo que se encarga de agregar una l�nea de producto dada la direcci�n de la sucursal<br>
     * <b>post:</b> Si la sucursal existe y no existe un producto identificado con ese c�digo, se agrega. <br>
     * Si la sucursal no existe o si existe un producto con el mismo c�digo se lanza una excepci�n <br>
     * @param direccionSucursal Direcci�n de la sucursal donde se quiere lanzar la l�nea de producto. Diferente de null.
     * @param nombreProducto Nombre de la nueva l�nea de producto que quiere ser lanzada. Diferente de null.
     * @param codigoProducto C�digo de la nueva l�nea de producto que quiere ser lanzada. Diferente de null.
     * @param descripcion Descripci�n de la nueva l�nea de producto que quiere ser lanzada. Diferente de null.
     * @param precio Precio de la nueva l�nea de producto que quiere ser lanzada. Mayor que cero.
     * @param fecha Fecha de lanzamiento de la nueva l�nea de producto que quiere ser lanzada. Diferente de null.
     * @throws SucursalNoExisteException Excepci�n lanzada cuando no se encuentra una sucursal con el nombre dado.
     * @throws LineaProductoExisteException Excepci�n que es lanzada cuando se trata de lanzar un producto con el mismo c�digo de un producto que ya existe.
     */
    public void lanzarLineaProductoSucursal( String direccionSucursal, String nombreProducto, String codigoProducto, String descripcion, int precio, Fecha fecha ) throws SucursalNoExisteException, LineaProductoExisteException
    {
        if( primeraSucursal == null )
        {
            throw new SucursalNoExisteException( "No se encontr� ninguna sucursal" );
        }

        Sucursal iterador = primeraSucursal;
        boolean termino = false;
        while( iterador != null && !termino )
        {
            if( iterador.darDireccion( ).equals( direccionSucursal ) )
            {
                iterador.lanzarLineaProducto( nombreProducto, codigoProducto, descripcion, precio, fecha );
                termino = true;
            }
            iterador = iterador.darSiguiente( );
        }
    }

    /**
     * M�todo que se encarga de retornar la lista de l�neas de productos que tiene una sucursal dada. </br> <b>post:</b> Se retorna la lista de las l�neas de producto dada la
     * direcci�n de la sucursal. Si no se encuentra la sucursal se retorna una lista vac�a </br>
     * @param direccionSucursal Direcci�n de la sucursal de la cual se quieren obtener las l�neas de producto
     * @return Lista con objetos de tipo LineaProducto. Diferente de null, pero puede ser vac�a.
     */
    public ArrayList darLineasProductoSucursal( String direccionSucursal )
    {
        ArrayList nombres = new ArrayList( );
        Sucursal iterador = primeraSucursal;
        while( iterador != null )
        {
            if( iterador.darDireccion( ).equals( direccionSucursal ) )
                nombres = iterador.darLineasProducto( );
            iterador = iterador.darSiguiente( );
        }
        return nombres;
    }

    /**
     * M�todo que se encarga de obtener la l�nea de producto m�s antigua entre todas las sucursales. </br> <b>post:</b> Se retorna la l�nea de producto m�s antigua entre las
     * sucursales. De no haber sucursales o productos lanza una excepci�n</br>
     * @return Objeto de tipo LineaProducto que representa l�nea m�s antigua.
     * @throws LineaProductoNoExisteException Se lanza esta excepci�n cuando no se encuentra el producto.
     */
    public LineaProducto darLineaProductoMasAntigua( ) throws LineaProductoNoExisteException
    {
        LineaProducto producto = null;
        Sucursal iterador = primeraSucursal;
        while( iterador != null )
        {
            if( producto != null )
            {
                if( iterador.darLineaProductoMasAntiguo( ) != null && ! ( producto.darFecha( ).esAntes( iterador.darLineaProductoMasAntiguo( ).darFecha( ) ) ) )
                    producto = iterador.darLineaProductoMasAntiguo( );
            }
            else
            {
                producto = iterador.darLineaProductoMasAntiguo( );
            }
            iterador = iterador.darSiguiente( );
        }
        if( producto == null )
        {
            throw new LineaProductoNoExisteException( "No se encontr� ning�n producto" );
        }
        return producto;
    }

    /**
     * M�todo que retorna una lista de todas las sucursales que tiene el hipermercado. </br> <b>post:</b>Se retorna una lista con objetos de tipo String. De no haber
     * sucursales retorna una lista vac�a</br>
     * @return Lista con objetos de tipo String. Diferente de null, pero puede ser vac�a
     */
    public ArrayList darSucursales( )
    {
        ArrayList sucursales = new ArrayList( );
        Sucursal iterador = primeraSucursal;
        while( iterador != null )
        {
            sucursales.add( iterador.toString( ) );
            iterador = iterador.darSiguiente( );
        }
        return sucursales;
    }

    /**
     * M�todo que retorna una sucursal dada su direcci�n <b>post:</b>Se retorna la sucursal que tiene la direcci�n. </br> De no existir se lanza una excepci�n</br>
     * @param direccionSucursal Nombre de la sucursal que se quiere buscar. Diferente de null
     * @return Sucursal que tiene el nombre si la encuentra.
     * @throws SucursalNoExisteException Excepci�n que es lanzada cuando no se encuentra la sucursal o no hay sucursales
     */
    public Sucursal darSucursal( String direccionSucursal ) throws SucursalNoExisteException
    {
        if( primeraSucursal == null )
        {
            throw new SucursalNoExisteException( "No se encontr� ninguna sucursal" );
        }
        Sucursal iterador = primeraSucursal;
        while( iterador != null )
        {
            if( iterador.darDireccion( ).equals( direccionSucursal ) )
            {
                return iterador;
            }
            iterador = iterador.darSiguiente( );
        }
        if( iterador == null )
        {
            throw new SucursalNoExisteException( "No se encontr� la sucursal buscada" );
        }
        return null;
    }

    /**
     * M�todo que descontinua una l�nea de producto de una sucursal dada </br> <b>post: </b>Si existe una sucursal identificada con el nombre y una l�nea de producto
     * identificada con el c�digo, se elimina de la sucursal. De no existir la sucursal o no existir el producto, se lanza una excepci�n </br>
     * @param direccionSucursal Direcci�n de la sucursal donde se quiere descontinuar la l�nea de producto. Diferente de null
     * @param codigoProducto Nombre del producto que quiere ser descontinuado. Diferente de null
     * @throws LineaProductoNoExisteException Excepci�n que es lanzada cuando no se encuentra el producto que se quiere descontinuar.
     * @throws SucursalNoExisteException Excepci�n que es lanzada cuando no se encuentra la sucursal de donde se quiere descontinuar el producto
     */
    public void descontinuarLineaProducto( String direccionSucursal, String codigoProducto ) throws LineaProductoNoExisteException, SucursalNoExisteException
    {
        if( primeraSucursal == null )
        {
            throw new SucursalNoExisteException( "No se encontr� ninguna sucursal" );
        }

        Sucursal iterador = primeraSucursal;
        while( iterador != null )
        {
            if( iterador.darDireccion( ).equals( direccionSucursal ) )
            {
                iterador.descontinuarLineaProducto( codigoProducto );
            }
            iterador = iterador.darSiguiente( );
        }
    }

    /**
     * M�todo que elimina de todas las sucursales las l�neas de productos que tienen un precio dentro del rango dado. </br> <b>post:</b> Para todos las l�neas de producto
     * cuyo precio este en el rango son eliminadas, sin importar su sucursal</br>
     * @param inferior Limite inferior del rango. Entero mayor a cero.
     * @param superior Limite superior del rango. Entero mayor a cero.
     * @return Cantidad total de productos eliminados. Entero mayor o igual a cero.
     */
    public int eliminarLineasProductosDadoRango( int inferior, int superior )
    {
        Sucursal iterador = primeraSucursal;
        int contador = 0;
        while( iterador != null )
        {
            contador += iterador.eliminarLineasProductosDadoRango( inferior, superior );
            iterador = iterador.darSiguiente( );
        }
        return contador;
    }

    /**
     * Guarda en un archivo identificado con el nombre, el estado del mundo. </br> <b>post:</b> El estado del mundo quedar� guardado en un archivo que estar� identificado con
     * rutaNombre</br>
     * @param rutaNombre ruta y nombre del archivo. Diferente de null.
     * @throws PersistenciaException Lanzada cuando no se encuentra el archivo o no se puede guardar en �l.
     */
    public void guardarCupEMart( String rutaNombre ) throws PersistenciaException
    {
        try
        {
            FileOutputStream archivo = new FileOutputStream( rutaNombre );
            ObjectOutputStream objetoSaliente = new ObjectOutputStream( archivo );
            objetoSaliente.writeObject( primeraSucursal );
            objetoSaliente.close( );
            archivo.close( );
        }
        catch( IOException e )
        {
            throw new PersistenciaException( "Error al salvar: " + e.getMessage( ) );
        }

    }

    /**
     * Cargar de un archivo identificado con el nombre el estado del mundo que se encuentra guardado.</br> <b>post:</b> El archivo que est� identificado con rutaNombre queda
     * cargado en el sistema.</br>
     * @param rutaNombre ruta y nombre del archivo. Diferente de null
     * @throws PersistenciaException Lanzada cuando ocurre algun problema con el archivo o con su formato
     * 
     */
    public void cargarCupEMart( String rutaNombre ) throws PersistenciaException
    {
        
        try
        {
            File archivo = new File( rutaNombre );
            if( archivo.exists() )
            {    
                ObjectInputStream ois = new ObjectInputStream(  new FileInputStream( archivo ) );
                primeraSucursal = ( Sucursal )ois.readObject( );
                ois.close( );
             }
            else
            {
                primeraSucursal = null;
            }
                
        }
        catch( FileNotFoundException e )
        {
            throw new PersistenciaException( "No se encontr� archivo de persistencia. \nAl cerrar la aplicaci�n se generar� por primera vez el archivo de persistencia " );
        }
        catch( IOException e )
        {
            throw new PersistenciaException( "No fue posible leer el archivo de persistencia" + e.getMessage( ) );
        }
        catch( ClassNotFoundException e )
        {
            throw new PersistenciaException( "Problemas al cargar el archivo de persistencia" + e.getMessage( ) );
        }
        verificarInvariante( );
    }

    /**
     * Es el m�todo que va escribir el archivo con el reporte que se necesita.</br> <b>post:</b> Se crea un archivo cuyo nombre y ubicaci�n es definida por el usuario. Este
     * archivo contiene la informaci�n en texto del mundo </br>
     * @param rutaArchivo El ruta del archivo en el que se va escribir. Diferente de null.
     * @param nombreArchivo El nombre del archivo que va generar. Diferente de null.
     * @throws FileNotFoundException En caso que no se pueda escribir el archivo.
     */
    public void generarReporte( String rutaNombre, String nombreArchivo ) throws FileNotFoundException
    {
        File directorioReporte = new File( rutaNombre );
        if( !directorioReporte.exists( ) )
        {
            directorioReporte.mkdirs( );
        }
        File archivoReporte = new File( directorioReporte, nombreArchivo );
        PrintWriter out = new PrintWriter( archivoReporte );
        out.println( "Reporte Cup-E-Mart" );
        out.println( );

        ArrayList sucursales = darSucursales( );

        if( sucursales.isEmpty( ) )
        {
            out.println( "No hay sucursales en el sistema" );
        }

        for( int i = 0; i < sucursales.size( ); i++ )
        {
            String nombre = ( String )sucursales.get( i );
            out.println( "Sucursal: " );
            out.println( nombre );
            nombre = nombre.substring( 0, nombre.indexOf( " -" ) );
            ArrayList productos = darLineasProductoSucursal( nombre );
            for( int j = 0; j < productos.size( ); j++ )
            {
                LineaProducto producto = ( LineaProducto )productos.get( j );
                out.println( "\t- L�nea de Producto:" );
                out.println( "\t\t- Nombre: " + producto.darNombre( ) );
                out.println( "\t\t- C�digo: " + producto.darCodigo( ) );
                out.println( "\t\t- Descripci�n: " + producto.darDescripcion( ) );
                out.println( "\t\t- Precio por Unidad: " + producto.darPrecioUnidad( ) );
                out.println( "\t\t- Fecha: " + producto.darFecha( ) );
            }
            out.println( );
        }
        out.close( );
    }

    /**
     * Verifica la invariante de la clase</br> <b>inv:</b></br> la sucursal est� bien construida: no existe ninguna sucursal tal que sucursal.obtenerSiguiente() ==
     * primerasucursal</br> la sucursal est� bien construida: no existen sucursal1 y sucursal2 tal que sucursal1.obtenerSiguiente() == sucursal2.obtenerSiguiente() &&
     * sucursal1 != sucursal2</br> no hay dos sucursales con la misma direcci�n
     */
    private void verificarInvariante( )
    {
        // La lista no tiene ciclos con el primer elemento
        if( primeraSucursal != null )
        {
            Sucursal temp1 = primeraSucursal;
            while( temp1 != null )
            {
                assert ( temp1.darSiguiente( ) != primeraSucursal ) : "La lista tiene un ciclo";
                temp1 = temp1.darSiguiente( );
            }
        }

        // No hay ciclos ni direcciones repetidas
        Sucursal sucursal1 = primeraSucursal;
        while( sucursal1 != null )
        {
            Sucursal sucursal2 = sucursal1.darSiguiente( );
            while( sucursal2 != null )
            {
                assert ( sucursal2.darSiguiente( ) != sucursal1 ) : "La lista tiene un ciclo";
                assert ( sucursal2.darDireccion( ) != sucursal1.darDireccion( ) ) : "La lista tiene sucursales con direcciones repetidas";
                sucursal2 = sucursal2.darSiguiente( );
            }

            sucursal1 = sucursal1.darSiguiente( );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}