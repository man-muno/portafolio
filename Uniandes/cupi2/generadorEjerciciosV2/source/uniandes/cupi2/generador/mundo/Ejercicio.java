/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 
 * Ejercicio: generadorEjerciciosV2
 * Autor: Pablo Barvo - Sep 2, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.generador.mundo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa un ejercicio
 * @author Pablo Barvo
 */
public class Ejercicio
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Inicio de los paquetes
     */
    private static final String INICIO_PAQUETES = "uniandes.cupi2.";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Nombre del ejercicio.
     */
    private String nombre;

    /**
     * Nombre del autor
     */
    private String autor;

    /**
     * Nivel del ejercicio
     */
    private int nivel;

    /**
     * Fecha de creación
     */
    private Date fecha;

    /**
     * Nombre de la clase principal del mundo
     */
    private String clasePrincipal;

    /**
     * Ruta completa donde se va a generar el ejercicio
     */
    private String ubicacion;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor sin parametros
     */
    public Ejercicio( )
    {
        super( );
    }

    /**
     * Constructor con toda la información de un ejercicio
     * @param nombre Nombre del ejercicio
     * @param autor Autor
     * @param nivel Nivel
     * @param fecha Fecha de creación
     * @param clasePrincipal Clase Principal
     */
    public Ejercicio( String nombre, String autor, int nivel, Date fecha, String clasePrincipal )
    {
        super( );
        this.nombre = nombre;
        this.autor = autor;
        this.nivel = nivel;
        this.fecha = fecha;
        this.clasePrincipal = clasePrincipal;
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Devuelve el nombre del autor del ejercicio
     * @return Devuelve el nombre del autor del ejercicio
     */
    public String darAutor( )
    {
        return autor;
    }

    /**
     * Establece el nombre del autor del ejercicio
     * @param autor Nombre del autor del ejercicio
     */
    public void cambiarAutor( String autor )
    {
        this.autor = autor;
    }

    /**
     * Devuelve la fecha de creación del ejercicio
     * @return Devuelve la fecha de creación del ejercicio
     */
    public Date darFecha( )
    {
        return fecha;
    }

    /**
     * Establece la fecha del ejercicio
     * @param fecha Fecha del ejercicio
     */
    public void cambiarFecha( Date fecha )
    {
        this.fecha = fecha;
    }

    /**
     * Devuelve el nivel del ejercicio
     * @return Devuelve el nivel del ejercicio
     */
    public int darNivel( )
    {
        return nivel;
    }

    /**
     * Establece el nivel del ejercicio
     * @param nivel Nivel del ejercicio
     */
    public void cambiarNivel( int nivel )
    {
        this.nivel = nivel;
    }

    /**
     * Devuelve el nombre del ejercicio
     * @return Devuelve el nombre del ejercicio
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Establece el nombre del ejercicio
     * @param nombre Nombre del ejercicio
     */
    public void cambiarNombre( String nombre )
    {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre de la clase principal del ejercicio
     * @return Devuelve el nombre de la clase principal del ejercicio
     */
    public String darClasePrincipal( )
    {
        return clasePrincipal;
    }

    /**
     * Establece el nombre de la clase principal del mundo en del ejercicio
     * @param nombreClasePrincipal Nombre de la clase principal del mundo en del ejercicio
     */
    public void cambiarClasePrincipal( String nombreClasePrincipal )
    {
        this.clasePrincipal = nombreClasePrincipal;
    }

    /**
     * Devuelve la ruta completa de ubicación del ejercicio. Esta ruta incluye el folder del ejercicio con base a su nombre y nivel.
     * @return Returns the ubicacion.
     */
    public String darUbicacion( )
    {
        return new File( ubicacion + "/" + this.darNombreCompleto( ) ).getAbsolutePath( );
    }

    /**
     * Establece la ruta donde se ubica el ejercicio. No debe incluir el folder del ejercicio.
     * @param ubicacion Ubicación del ejercicio
     */
    public void cambiarUbicacion( String ubicacion )
    {
        this.ubicacion = ubicacion;
    }

    /**
     * Devuelve el nombre completo del ejercicio incluyendo el nivel. Se sigue la convensión de n[NIVEL]_[NOMBRE]. Ejemplo: n1_alcancia
     * @return Nombre completo del ejercicio incluyendo el nivel
     */
    public String darNombreCompleto( )
    {
        return "n" + nivel + "_" + nombre;
    }

    /**
     * Devuelve el nombre de la clase principal de la interfaz
     * @return Nombre de la clase principal de la interfaz
     */
    public String darClaseInterfaz( )
    {
        ConvertidorCamelPascal convertidor = new ConvertidorCamelPascal( );
        return "Interfaz" + convertidor.convertirPascal( nombre );
    }

    /**
     * Devuelve el nombre de la clase principal de las pruebas
     * @return Nombre de la clase principal de las pruebas
     */
    public String darClasePruebas( )
    {
        ConvertidorCamelPascal convertidor = new ConvertidorCamelPascal( );
        return convertidor.convertirPascal( clasePrincipal ) + "Test";
    }

    /**
     * Devuelve el nombre completo del paquete del ejercicio (raíz)
     * @return Nombre completo del paquete del ejercicio (raíz)
     */
    public String darPaquete( )
    {
        return Ejercicio.INICIO_PAQUETES + darNombre( );
    }

    /**
     * Devuelve la ruta del paquete
     * @return Ruta del paquete
     */
    public String darRutaPaquete( )
    {
        return darPaquete( ).replaceAll( "\\.", "/" );
    }

    /**
     * Devuelve la fecha del ejercicio formateada para ser usada
     * @return Fecha formateada
     */
    public String darFechaFormateada( )
    {
        SimpleDateFormat formateador = new SimpleDateFormat( "dd-MMM-yyyy" );
        return formateador.format( fecha );
    }
}
