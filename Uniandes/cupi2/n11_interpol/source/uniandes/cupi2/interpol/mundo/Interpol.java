/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Interpol.java,v 1.3 2007/04/20 12:38:40 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Muñoz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.interpol.mundo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Representa la clase que tendrá el estado del juego.
 */
public class Interpol
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Propiedad que indica el caso a resolver
     */
    public static final String PROPIEDADES_CASO = "juego.caso";

    /**
     * Propiedad donde esta la cantidad de ciudades en el archivo
     */
    public static final String PROPIEDADES_CANTIDAD_CIUDADES = "ciudades.cantidad";

    /**
     * Propiedad donde se guarda el tiempo de juego
     */
    public static final String PROPIEDADES_TIEMPO_JUEGO = "ciudades.tiempo.juego";

    /**
     * Propiedad donde se guarda ciudad
     */
    public static final String PROPIEDADES_CIUDAD = "ciudad";

    /**
     * Propiedad donde esta el nombre de la ciudad
     */
    public static final String PROPIEDADES_NOMBRE_CIUDAD = ".nombre";

    /**
     * Propiedad con el tiempo del viaje
     */
    public static final String PROPIEDADES_TIEMPO_VIAJE = ".tiempoViaje";

    /**
     * Propiedad del lugar de una pista en la ciudad
     */
    public static final String PROPIEDADES_LUGAR = ".lugar";

    /**
     * Propiedad del nombre del lugar de una pista en la ciudad
     */
    public static final String PROPIEDADES_NOMBRE_LUGAR = ".nombre";

    /**
     * Propiedad de la pista en la ciudad
     */
    public static final String PROPIEDADES_PISTA_LUGAR = ".pista";

    /**
     * Propiedad del tiempo que se demora buscar en el lugar
     */
    public static final String PROPIEDADES_TIEMPO_LUGAR = ".tiempo";

    /**
     * Propiedad del nombre de la ciudad padre
     */
    public static final String PROPIEDADES_PADRE = ".padre";

    /**
     * Propiedad que indica si el sospechoso se encuentra en esa ciudad
     */
    public static final String PROPIEDADES_ESTA_SOSPECHOSO = ".esta.sospechoso";

    /**
     * Propiedad que indica la descripcion de la la ciudad
     */
    public static final String PROPIEDADES_DESCRIPCION_CIUDAD = ".descripcion";

    /**
     * Propiedad que indica el número de sospechosos
     */
    public static final String PROPIEDADES_NUMERO_SOSPECHOSO = "sospechosos.cantidad";

    /**
     * Propiedad que indica el prefijo sospechoso
     */
    public static final String PROPIEDADES_SOSPECHOSO = "sospechoso";

    /**
     * Propiedad que indica la imagen del sospechoso
     */
    public static final String PROPIEDADES_SOSPECHOSO_IMAGEN = ".imagen";

    /**
     * Propiedad que indica que el sospechoso es el ladron o no.
     */
    public static final String PROPIEDADES_SOPECHOSO_ES_LADRON = ".esLadron";

    /**
     * Propiedad que indica la cantidad de lugares que tiene cada ciudad
     */
    public static final String PROPIEDADES_CANTIDAD_LUGARES = ".cantidad";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tiempo de juego
     */
    private int tiempoJuego;

    /**
     * Primera ciudad del juego
     */
    private Ciudad ciudadRaiz;

    /**
     * Ciudad donde esta el jugador
     */
    private Ciudad ciudadActual;

    /**
     * Texto que contiene el caso a resolver
     */
    private String caso;

    /**
     * Lista de los posibles sospechosos
     */
    private List sospechosos;

    /**
     * Indica si se generó o no una orden de captura del ladrón.
     */
    private boolean generadaOrdenCaptura;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por defecto. Inicializa los atributos. <br>
     * <b>post:</b> tiempoJuego = 0, ciudadRaiz = null, ciudadActual = null, caso = "", sospechosos = new ArrayList, generadaOrdenCaptura = false<br>
     */
    public Interpol( )
    {
        tiempoJuego = 0;
        ciudadRaiz = null;
        ciudadActual = null;
        caso = "";
        sospechosos = new ArrayList( );
        generadaOrdenCaptura = false;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea el mundo de acuerdo a lo que se encuentre en el archivo de propiedades.<br>
     * <b>post:</b> Se construye el árbol de las ciudades. La ciudad actual y la primera ciudad son la misma<br>
     * @param propiedades Lugar donde se encuentra el estado del mundo. Diferente de null
     * @throws InterpolException Lanzada cuando se quiere agregar una ciudad que ya existe
     * @throws CiudadNoAgredagaException Excepción lanzada cuando la ciudad donde se quiere agregar la nueva ciudad ya tiene a sus hijos llenos
     */
    public void cargarJuego( Properties propiedades ) throws InterpolException, CiudadNoAgredagaException
    {
        ciudadRaiz = null;
        ciudadActual = null;
        sospechosos = new ArrayList( );
        tiempoJuego = Integer.parseInt( propiedades.getProperty( PROPIEDADES_TIEMPO_JUEGO ) );
        caso = propiedades.getProperty( PROPIEDADES_CASO );

        cargarCiudades( propiedades );
        cargarSospechosos( propiedades );

        verificarInvariante( );
    }

    /**
     * Carga los sospechosos del archivo de propiedades<br>
     * 
     * <b>pre:</b> Lista de sospechosos inicializada.<br>
     * 
     * <b>post:</b> Se agregan a la lista de sospechosos todos los que se encuentren en el archivo de propiedades.<br>
     * @param propiedades Diferente de null.
     */
    private void cargarSospechosos( Properties propiedades ) throws InterpolException
    {
        int cantidadCiudades = Integer.parseInt( propiedades.getProperty( PROPIEDADES_NUMERO_SOSPECHOSO ) );
        for( int i = 0; i < cantidadCiudades; i++ )
        {
            String rutaImagen = propiedades.getProperty( PROPIEDADES_SOSPECHOSO + ( i + 1 ) + PROPIEDADES_SOSPECHOSO_IMAGEN );
            boolean esLadron = Boolean.parseBoolean( propiedades.getProperty( PROPIEDADES_SOSPECHOSO + ( i + 1 ) + PROPIEDADES_SOPECHOSO_ES_LADRON ) );
            sospechosos.add( new Sospechoso( rutaImagen, esLadron ) );
        }
    }

    /**
     * Lee las ciudades y sus lugares del archivo de propiedades.<br>
     * <b>post:</b> Se creó el árbol de ciudades, cada una de ellas con tres lugares para buscar pistas.<br>
     * @param propiedades Diferente de null.
     * @throws InterpolException Lanzada cuando se intenta agregar una ciudad que ya existe.
     * @throws CiudadNoAgredagaException Excepción lanzada cuando la ciudad donde se quiere agregar la nueva ciudad ya tiene a sus hijos llenos
     */
    private void cargarCiudades( Properties propiedades ) throws InterpolException, CiudadNoAgredagaException
    {
        int cantidadSospechosos = Integer.parseInt( propiedades.getProperty( PROPIEDADES_CANTIDAD_CIUDADES ) );
        for( int i = 0; i < cantidadSospechosos; i++ )
        {
            String nombreCiudad = propiedades.getProperty( PROPIEDADES_CIUDAD + ( i + 1 ) + PROPIEDADES_NOMBRE_CIUDAD );
            String descripcion = propiedades.getProperty( PROPIEDADES_CIUDAD + ( i + 1 ) + PROPIEDADES_DESCRIPCION_CIUDAD );
            if( nombreCiudad == null || descripcion == null )
            {
                throw new InterpolException( "La información del archivo no está bien definida" );
            }
            if( darCiudad( nombreCiudad ) == null )
            {
                int tiempoViaje = Integer.parseInt( propiedades.getProperty( PROPIEDADES_CIUDAD + ( i + 1 ) + PROPIEDADES_TIEMPO_VIAJE ) );
                int cantLugares = Integer.parseInt( propiedades.getProperty( PROPIEDADES_CIUDAD + ( i + 1 ) + PROPIEDADES_LUGAR + PROPIEDADES_CANTIDAD_LUGARES ) );
                boolean estaSospechoso = Boolean.parseBoolean( propiedades.getProperty( PROPIEDADES_CIUDAD + ( i + 1 ) + PROPIEDADES_ESTA_SOSPECHOSO ) );
                Ciudad ciudad = new Ciudad( nombreCiudad, descripcion, tiempoViaje, estaSospechoso );
                for( int j = 0; j < cantLugares; j++ )
                {
                    Lugar lugar = new Lugar( propiedades.getProperty( PROPIEDADES_CIUDAD + ( i + 1 ) + PROPIEDADES_LUGAR + ( j + 1 ) + PROPIEDADES_NOMBRE_LUGAR ), propiedades.getProperty( PROPIEDADES_CIUDAD + ( i + 1 ) + PROPIEDADES_LUGAR + ( j + 1 )
                            + PROPIEDADES_PISTA_LUGAR ), Integer.parseInt( propiedades.getProperty( PROPIEDADES_CIUDAD + ( i + 1 ) + PROPIEDADES_LUGAR + ( j + 1 ) + PROPIEDADES_TIEMPO_LUGAR ) ) );
                    ciudad.agregarLugar( lugar );
                }
                agregarCiudad( ciudad, propiedades.getProperty( PROPIEDADES_CIUDAD + ( i + 1 ) + PROPIEDADES_PADRE ) );
            }
            else
            {
                throw new InterpolException( "Una de las ciudades que desea agregar ya existe" );
            }
        }
    }

    /**
     * Agrega una nueva ciudad al mundo. <br>
     * <b>pre:</b> La ciudad a agregar no existe<br>
     * <b>post:</b> Se agrega una nueva hoja al árbol, debajo de la que se indica en el nombrePadre<br>
     * @param ciudad Ciudad que se quiere agregar. Diferente de null.
     * @param nombrePadre Nombre de la ciudad padre a la cual se le quiere agregar la ciudad. Diferente de null.
     * @throws CiudadNoAgredagaException Excepción lanzada cuando la ciudad donde se quiere agregar la nueva ciudad ya tiene a sus hijos llenos
     */
    private void agregarCiudad( Ciudad ciudad, String nombrePadre ) throws CiudadNoAgredagaException
    {
        if( ciudadRaiz == null )
        {
            ciudadRaiz = ciudad;
            ciudadActual = ciudadRaiz;
        }
        else
        {
            if( !ciudadRaiz.existeCiudad( nombrePadre ) )
                throw new CiudadNoAgredagaException( "No se encontró la " + nombrePadre + " donde se quiere agregar la nueva ciudad" );
            ciudadRaiz.agregarCiudad( ciudad, nombrePadre );
        }
    }

    /**
     * Retorna una ciudad dado su nombre.<br>
     * @param nombreCiudad Nombre de la ciudad a buscar. Diferente de null.
     * @return Retorna la ciudad cuyo nombre es el que se pasa por parámetro, en caso que no exista se retorna null.
     */
    private Ciudad darCiudad( String nombreCiudad )
    {
        return ciudadRaiz != null ? ciudadRaiz.darCiudad( nombreCiudad ) : null;
    }

    /**
     * Retorna el caso que se esta jugando actualmente<br>
     * @return Cadena de caracteres con el caso. Diferente de null.
     */
    public String darCaso( )
    {
        return caso;
    }

    /**
     * Retorna la ciudad seleccionada actualmente.<br>
     * @return Ciudad actual.
     */
    public Ciudad darCiudadActual( )
    {
        return ciudadActual;
    }

    /**
     * Retorna la cantidad de días faltantes en el juego.<br>
     * @return Entero mayor o igual a cero.
     */
    public int darDiasFaltantes( )
    {
        return tiempoJuego;
    }

    /**
     * Retorna una lista con los nombres de los lugares que tiene la ciudad actual.<br>
     * @return Lista de Strings con los nombres de los lugares. Ninguno es null.
     */
    public List darNombresLugaresCiudadActual( )
    {
        return ciudadActual != null ? ciudadActual.darNombresLugares( ) : new ArrayList( );
    }

    /**
     * Retorna la pista de un lugar de la ciudad actual, dado el nombre del lugar.<br>
     * @param nombreLugar Nombre del lugar al cual se le quiere obtener la pista.
     * @return Cadena de caracteres con la pista. Diferente de null.
     */
    public String darPistaLugar( String nombreLugar )
    {
        if( ciudadActual != null )
        {
            Lugar lugar = ciudadActual.darLugar( nombreLugar );
            tiempoJuego -= lugar.darTiempoGastado( );
            return lugar.darPista( );
        }
        else
            return "";

    }

    /**
     * Retorna una lista de los nombres de los hijos de la ciudad actual.<br>
     * @return Lista con Strings. Diferente de null pero puede ser vacía.
     */
    public List darNombresPosiblesDestinos( )
    {
        return ciudadActual != null ? ciudadActual.darNombresPosiblesDestinos( ) : new ArrayList( );
    }

    /**
     * Cambia la ciudad actual por la ciudad cuyo nombre se pasa por parámetro. <br>
     * <b>post:</b>La ciudad actual cambia. El tiempo de juego se reduce en la cantidad de días de viaje que tiene la nueva ciudad<br>
     * @param nombreCiudad Nombre de la nueva ciudad actual. Diferente de null.
     */
    public void viajar( String nombreCiudad )
    {
        if( ciudadActual != null )
        {
            ciudadActual = ciudadActual.viajar( nombreCiudad );
            tiempoJuego -= ciudadActual.darTiempoViaje( );
        }
    }

    /**
     * Retorna si el sospechoso fue encontrado en la ciudad actual.<br>
     * @return Retorna true en caso que el sospechoso se encuentre en la ciudad actual, false de lo contrario.
     */
    public boolean sospechosoEncontrado( )
    {
        return ciudadActual != null ? ciudadActual.estaSospechoso( ) : false;
    }

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>post: </b> Se retornó la lista con las preguntas ordenadas en inorden<br>
     * @return Lista con las preguntas en inorden. Diferente de null.
     */
    public List darInorden( )
    {
        List resultado = new ArrayList( );
        if( ciudadRaiz != null )
        {
            ciudadRaiz.darInorden( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol en preorden.<br>
     * <b>post: </b> Se retornó la lista con las preguntas ordenadas en preorden<br>
     * @return Lista con las preguntas en preorden. Diferente de null.
     */
    public List darPreorden( )
    {
        List resultado = new ArrayList( );
        if( ciudadRaiz != null )
        {
            ciudadRaiz.darPreorden( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol en postorden. <br>
     * <b>post: </b> Se retornó la lista con las preguntas ordenadas en postorden<br>
     * @return Lista con las preguntas en postorden. Diferente de null
     */
    public List darPostorden( )
    {
        List resultado = new ArrayList( );
        if( ciudadRaiz != null )
        {
            ciudadRaiz.darPostorden( resultado );
        }
        return resultado;
    }

    /**
     * Retorna la altura del árbol. <br>
     * <b>post: </b> Se retornó la altura del árbol<br>
     * @return La altura del árbol. Entero mayor o igual a cero.
     */
    public int darAltura( )
    {
        return ciudadRaiz != null ? ciudadRaiz.darAltura( ) : 0;
    }

    /**
     * Retorna el peso del árbol (número de elementos que contiene). <br>
     * <b>post: </b> Se retornó la altura del árbol<br>
     * @return El peso del árbol. Entero mayor o igual a cero.
     */
    public int darPeso( )
    {
        return ciudadRaiz != null ? ciudadRaiz.darPeso( ) : 0;
    }

    /**
     * Retorna la primera ciudad del árbol<br>
     * @return Puede ser null.
     */
    public Ciudad darPrimeraCiudad( )
    {
        return ciudadRaiz;
    }

    /**
     * Retorna el camino de la raíz (primera ciudad) a la ciudad que tiene el sospechoso.<br>
     * @return Lista con los nombres de las ciudades que se encuentran entre la raíz y la ciudad que tiene el sospechoso. Diferente de null.
     */
    public List darCaminoRespuesta( )
    {
        return ciudadRaiz != null ? ciudadRaiz.darCaminoRespuesta( ) : new ArrayList( );
    }

    /**
     * Evalúa si la ciudad actual es una hoja.<br>
     * @return Retorna true en caso que la ciudad actual es una hoja, false de lo contrario.
     */
    public boolean ciudadActualEsHoja( )
    {
        return ciudadActual != null ? ciudadActual.esHoja( ) : false;
    }

    /**
     * Verifica que en el árbol no haya dos ciudades con el mismo nombre<br>
     * @return True en caso que haya ciudades repetidas en el árbol, false de lo contrario.
     */
    private boolean sinRepetidos( )
    {
        if( ciudadRaiz == null )
            return true;
        else
        {
            ArrayList nombres = ( ArrayList )darInorden( );
            for( int i = 0; i < nombres.size( ); i++ )
            {
                String nombre = ( String )nombres.get( i );
                if( contarOcurrencias( nombre ) != 1 )
                    return false;
            }
            return true;
        }
    }

    /**
     * Cuenta el número de ciudades en el árbol que tienen el nombre dado como parámetro <br>
     * <b>pre: </b> El árbol de ciudades no es vacío
     * @param nombre nombre de la ciudad de la cual se está contando el número de ocurrencias
     */
    private int contarOcurrencias( String nombre )
    {
        return ciudadRaiz != null ? ciudadRaiz.contarOcurrencias( nombre ) : 0;
    }

    /**
     * Retorna la lista de sospechosos
     * @return Diferente de null.
     */
    public List darSospechosos( )
    {
        return sospechosos;
    }

    /**
     * Retorna si la orden de captura fue generada o no.
     * @return True en caso que la orden de captura fuese generada, false de lo contrario.
     */
    public boolean ordenCapturaGenerada( )
    {
        return generadaOrdenCaptura;
    }

    /**
     * Retorna si el sospechoso en la posición dada es el ladrón o no, en caso que lo sea genera la orden de captura <br>
     * <b>pre:</b> Lista de sospechosos inicializada <br>
     * <b>pos:</b> En caso que el sospechoso dado sea el ladrón, generarOrdenCaptura = true <br>
     * @param pos Posición en la cual se quiere buscar el ladrón. Pos es mayor igual a cero y menor que el tamaño de la lista de sospechosos.
     * @return True en caso que el sospechoso de la posición en la lista de sospechosos sea el ladrón, false de lo contrario.
     */
    public boolean generarOrdenCaptura( int pos )
    {
        return ( ( Sospechoso )sospechosos.get( pos ) ).esLadron( ) ? generadaOrdenCaptura = true : false;
    }

    /**
     * Verifica la invariante de la clase.
     * 
     */
    private void verificarInvariante( )
    {
        assert tiempoJuego > 0 : "El tiempo de juego debería ser entero mayor o igual a cero";
        assert caso != null : "El caso no debería ser null";
        assert sospechosos != null : "La lista de sospechosos no puede ser null";
        assert sinRepetidos( ) : "El árbol contiene elementos repetidos";
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}