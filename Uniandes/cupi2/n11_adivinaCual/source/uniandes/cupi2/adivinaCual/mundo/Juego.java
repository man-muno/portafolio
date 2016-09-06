/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Muñoz - 27-Oct-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Representa el juego como tal <b>inv: </b> <br>
 * No debería existir animales repetidos<br>
 * 
 */
public class Juego
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa el animal perro
     */
    public static final String PERRO = "Perro";

    /**
     * Representa el animal Gato
     */
    public static final String GATO = "Gato";

    /**
     * Representa el animal Ratón
     */
    public static final String RATON = "Ratón";

    /**
     * Representa el animal Caballo
     */
    public static final String CABALLO = "Caballo";

    /**
     * Representa el animal Burro
     */
    public static final String BURRO = "Burro";

    /**
     * Representa el animal Cóndor
     */
    public static final String CONDOR = "Cóndor";

    /**
     * Representa el animal Gaviota
     */
    public static final String GAVIOTA = "Gaviota";

    /**
     * Representa el animal Camaleón
     */
    public static final String CAMALEON = "Camaleón";

    /**
     * Representa el animal Delfín
     */
    public static final String DELFIN = "Delfín";

    /**
     * Representa el animal Tiburón
     */
    public static final String TIBURON = "Tiburón";

    /**
     * Representa el animal Pez Payaso
     */
    public static final String PEZ_PAYASO = "Pez Payaso";

    /**
     * Representa el animal Ballena
     */
    public static final String BALLENA = "Ballena";

    /**
     * Representa el animal Lombriz
     */
    public static final String LOMBRIZ = "Lombriz";

    /**
     * Representa el animal Langosta
     */
    public static final String LANGOSTA = "Langosta";

    /**
     * Representa el animal Escorpión
     */
    public static final String ESCORPION = "Escorpión";

    /**
     * Representa el animal Araña
     */
    public static final String ARANIA = "Araña";

    /**
     * Representa el animal Paloma
     */
    public static final String PALOMA = "Paloma";

    /**
     * Representa el animal Caracol
     */
    public static final String CARACOL = "Caracol";

    /**
     * Representa el animal Escarabajo
     */
    public static final String ESCARABAJO = "Escarabajo";

    /**
     * Representa el animal Mico
     */
    public static final String MICO = "Mico";

    /**
     * Representa el animal León
     */
    public static final String LEON = "León";

    /**
     * Representa el animal Hiena
     */
    public static final String HIENA = "Hiena";

    /**
     * Representa el animal Lagartija
     */
    public static final String LAGARTIJA = "Lagartija";

    /**
     * Representa el animal Oveja
     */
    public static final String OVEJA = "Oveja";

    /**
     * Representa el animal Abeja
     */
    public static final String ABEJA = "Abeja";

    /**
     * Representa el animal Avispa
     */
    public static final String AVISPA = "Avispa";

    /**
     * Representa el animal Oso
     */
    public static final String OSO = "Oso";

    /**
     * Representa el animal Elefante
     */
    public static final String ELEFANTE = "Elefante";

    /**
     * Representa el animal Toro
     */
    public static final String TORO = "Toro";

    /**
     * Representa el animal Libélula
     */
    public static final String LIBELULA = "Libélula";

    /**
     * Nombre del archivo de los animales
     */
    public static final String NOMBRE_ARCHIVO = "./data/animales.properties";

    /**
     * Representa una propiedad del archivo de propiedades
     */
    public static final String PROPIEDAD_ANIMAL = "animal";

    /**
     * Representa una propiedad del archivo de propiedades
     */
    public static final String PROPIEDAD_NOMBRE = ".nombre";

    /**
     * Representa una propiedad del archivo de propiedades
     */
    public static final String PROPIEDAD_ARCHIVO = ".imagen";

    /**
     * Representa la cantidad de animales en el archivo de propiedades
     */
    public static final String PROPIEDAD_CANTIDAD_ANIMALES = "animales.cantidad";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Raíz del árbol. Es la primera pregunta.
     */
    private Pregunta raiz;

    /**
     * Pregunta que esta en juego actualmente
     */
    private Pregunta preguntaActual;

    /**
     * Lista donde se tendrán los animales de la aplicación
     */
    private List animales;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por defecto de la clase.
     * @param nombreArchivo Nombre del archivo de propiedades. Diferente de null.
     * @param inicioLleno Indica si se empieza con un árbol lleno
     * @throws ArchivoNoCargadoException Se lanza cuando no se puede leer correctamente el archivo de propiedades
     */
    public Juego( String nombreArchivo, boolean inicioLleno ) throws ArchivoNoCargadoException
    {
        animales = new ArrayList( );
        if( inicioLleno )
        {
            cargarArchivo( nombreArchivo );
        }
        if( animales.size( ) != 0 )
        {
            raiz = armarArbol( );
        }
        preguntaActual = raiz;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Carga el archivo de propiedades. Coloca todos los animales en la lista de animales, como objetos de tipo Pregunta <b>post: </b> Se cargo el archivo de propiedades<br>
     * @param Nombre del archivo de propiedades. Diferente de null.
     */
    private void cargarArchivo( String nombreArchivo ) throws ArchivoNoCargadoException
    {
        try
        {
            FileInputStream in = new FileInputStream( new File( nombreArchivo ) );
            Properties propiedades = new Properties( );
            propiedades.load( in );
            String strCantidad = propiedades.getProperty( PROPIEDAD_CANTIDAD_ANIMALES );
            int cantidad = Integer.parseInt( strCantidad );
            for( int i = 0; i < cantidad; i++ )
            {
                String nombre = propiedades.getProperty( PROPIEDAD_ANIMAL + ( i + 1 ) + PROPIEDAD_NOMBRE );
                String archivo = propiedades.getProperty( PROPIEDAD_ANIMAL + ( i + 1 ) + PROPIEDAD_ARCHIVO );
                Pregunta pregunta = new Pregunta( nombre, archivo );
                animales.add( pregunta );
            }
        }
        catch( Exception e )
        {
            throw new ArchivoNoCargadoException( "Problemas de lectura del archivo: " + e.getMessage( ) );
        }
    }

    /**
     * Método que arma el árbol de preguntas <b>post: </b> Se arma y retorna el árbol de las preguntas. <br>
     * @return La pregunta raíz. Diferente de null
     */
    public Pregunta armarArbol( )
    {

        Pregunta pregunta29 = new Pregunta( "¿Le encanta comer pescado?", darAnimal( OSO ), darAnimal( LEON ) );

        Pregunta pregunta24 = new Pregunta( "¿Es carroñero?", darAnimal( HIENA ), pregunta29 );
        Pregunta pregunta25 = new Pregunta( "¿Vive en los árboles?", darAnimal( MICO ), darAnimal( ELEFANTE ) );

        Pregunta pregunta27 = new Pregunta( "¿Es considerado un animal terco?", darAnimal( BURRO ), darAnimal( CABALLO ) );
        Pregunta pregunta28 = new Pregunta( "¿Produce lana?", darAnimal( OVEJA ), darAnimal( TORO ) );

        Pregunta pregunta26 = new Pregunta( "¿Le encanta la leche?", darAnimal( GATO ), darAnimal( RATON ) );

        Pregunta pregunta22 = new Pregunta( "¿Ladra?", darAnimal( PERRO ), pregunta26 );
        Pregunta pregunta23 = new Pregunta( "¿Sirve para llevar carga?", pregunta27, pregunta28 );

        Pregunta pregunta20 = new Pregunta( "¿Se considera una mascota para la ciudad?", pregunta22, pregunta23 );
        Pregunta pregunta21 = new Pregunta( "¿Come carne?", pregunta24, pregunta25 );

        Pregunta pregunta19 = new Pregunta( "¿Se mueve arrastrándose?", darAnimal( CARACOL ), darAnimal( ESCARABAJO ) );

        Pregunta pregunta17 = new Pregunta( "¿Brilla bajo la luz ultravioleta?", darAnimal( ESCORPION ), darAnimal( ARANIA ) );
        Pregunta pregunta18 = new Pregunta( "¿Tiene coraza?", pregunta19, darAnimal( LOMBRIZ ) );

        Pregunta pregunta15 = new Pregunta( "¿Cambia su color?", darAnimal( CAMALEON ), darAnimal( LAGARTIJA ) );
        Pregunta pregunta16 = new Pregunta( "¿Es un arácnido?", pregunta17, pregunta18 );

        Pregunta pregunta14 = new Pregunta( "¿Es un reptil?", pregunta15, pregunta16 );

        Pregunta pregunta13 = new Pregunta( "¿Se considera animal doméstico?", pregunta20, pregunta21 );

        Pregunta pregunta12 = new Pregunta( "¿Es un gran depredador?", darAnimal( TIBURON ), darAnimal( PEZ_PAYASO ) );

        Pregunta pregunta11 = new Pregunta( "¿Es un pez?", pregunta12, darAnimal( LANGOSTA ) );

        Pregunta pregunta10 = new Pregunta( "¿Tiene nariz con forma de botella?", darAnimal( DELFIN ), darAnimal( BALLENA ) );

        Pregunta pregunta8 = new Pregunta( "¿Es mamífero?", pregunta10, pregunta11 );
        Pregunta pregunta9 = new Pregunta( "¿Es mamífero?", pregunta13, pregunta14 );

        Pregunta pregunta7 = new Pregunta( "¿Salen a mar abierto para morir?", darAnimal( "Gaviota" ), darAnimal( "Paloma" ) );
        Pregunta pregunta6 = new Pregunta( "¿Puede picar más de una vez?", darAnimal( AVISPA ), darAnimal( ABEJA ) );

        Pregunta pregunta4 = new Pregunta( "¿Pica?", pregunta6, darAnimal( LIBELULA ) );
        Pregunta pregunta5 = new Pregunta( "¿Está en vía de extinción?", darAnimal( CONDOR ), pregunta7 );

        Pregunta pregunta2 = new Pregunta( "¿Es un insecto?", pregunta4, pregunta5 );
        Pregunta pregunta3 = new Pregunta( "¿Vive en el mar?", pregunta8, pregunta9 );
        Pregunta pregunta1 = new Pregunta( "¿Vuela?", pregunta2, pregunta3 );

        return pregunta1;
    }

    /**
     * Retorna una lista con los animales <br>
     * <b>post: </b> Se retornó la lista de los animales<br>
     * @return Diferente de null
     */
    public List darAnimales( )
    {
        List hojas = new ArrayList( );
        if( raiz != null )
        {
            raiz.darHojas( hojas );
        }
        return hojas;
    }

    /**
     * Retorna una pregunta con la información de un animal de acuerdo a su nombre <b>post: </b> Se retornó un animal de acuerdo a su nombre<br>
     * @param nombre Nombre del animal que se quiere buscar. Diferente de null
     * @return Pregunta con la información de un animal. Puede ser null si no se encuentra el animal
     */
    private Pregunta darAnimal( String nombre )
    {
        for( int i = 0; i < animales.size( ); i++ )
        {
            Pregunta temp = ( Pregunta )animales.get( i );
            if( nombre.equals( temp.darNombreAnimal( ) ) )
            {
                return temp;
            }
        }
        return null;
    }

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>post: </b> Se retornó la lista con las preguntas ordenadas en inorden<br>
     * @return Lista de Strings con las preguntas en inorden. Diferente de null.
     */
    public List darInorden( )
    {
        List resultado = new ArrayList( );
        if( raiz != null )
        {
            raiz.darInorden( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol en preorden. <b>post: </b> Se retornó la lista con las preguntas ordenadas en preorden<br>
     * @return Lista de Strings con las preguntas en preorden. Diferente de null.
     */
    public List darPreorden( )
    {
        List resultado = new ArrayList( );
        if( raiz != null )
        {
            raiz.darPreorden( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve los elementos del árbol en postorden. <b>post: </b> Se retornó la lista con las preguntas ordenadas en postorden<br>
     * @return Lista de Strings con las preguntas en postorden. Diferente de null
     */
    public List darPostorden( )
    {
        List resultado = new ArrayList( );
        if( raiz != null )
        {
            raiz.darPostorden( resultado );
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
        return raiz == null ? 0 : raiz.darAltura( );
    }

    /**
     * Retorna el peso del árbol (número de elementos que contiene). <b>post: </b> Se retornó la altura del árbol<br>
     * @return El peso del árbol. Entero mayor o igual a cero.
     */
    public int darPeso( )
    {
        return raiz == null ? 0 : raiz.darPeso( );
    }

    /**
     * Reinicia el juego <b>post: </b> El valor de la pregunta actual ahora es la raiz.<br>
     */
    public void reiniciar( )
    {
        preguntaActual = raiz;
        verificarInvariante( );
    }

    /**
     * Retorna la pregunta que esta actualmente en juego <b>post: </b> Se retornó la pregunta actual<br>
     * @return Diferente de null
     */
    public Pregunta darPreguntaActual( )
    {
        return preguntaActual;
    }

    /**
     * Si la respuesta a la pregunta actual fue positiva, obtiene la siguiente pregunta por la rama positiva.En caso que la la siguiente pregunta por la rama positiva sea null
     * no se cambia <br>
     * post:</br> El valor de preguntaActual cambia por la siguiente pregunta por la rama positiva
     */
    public void seleccionarRespuestaPositiva( )
    {
        if( preguntaActual != null && preguntaActual.darRespuestaPositiva( ) != null )
            preguntaActual = preguntaActual.darRespuestaPositiva( );
        verificarInvariante( );
    }

    /**
     * Si la respuesta a la pregunta actual fue positiva, obtiene la siguiente pregunta por la rama negativa. En caso que la la siguiente pregunta por la rama negativa sea
     * null no se cambia<br>
     * post:</br> El valor de preguntaActual cambia por la siguiente pregunta por la rama negativa
     */
    public void seleccionarRespuestaNegativa( )
    {
        if( preguntaActual != null && preguntaActual.darRespuestaNegativa( ) != null )
            preguntaActual = preguntaActual.darRespuestaNegativa( );
        verificarInvariante( );
    }

    /**
     * Agrega una nueva pregunta y un nuevo animal al árbol, también actualiza la lista de animales.
     * @param descripcion Descripcion de la nueva pregunta. Diferente de null.
     * @param animal Nombre del nuevo animal. Diferente de null
     * @throws AnimalExisteException Excepción que se lanza cuando se desea agregar un animal que ya existe
     * @throws PreguntaNoAgregadaException Excepción que se lanza cuando se desea agregar una pregunta a un árbol vacío.
     */
    public void agregarPregunta( String descripcion, String animal ) throws AnimalExisteException, PreguntaNoAgregadaException
    {
        if( buscarAnimal( animal ) )
        {
            throw new AnimalExisteException( "El animal que quiere agregar ya se encuentra en el sistema" );
        }
        if( preguntaActual != null )
            preguntaActual.agregarPregunta( descripcion, animal );
        else
            throw new PreguntaNoAgregadaException( "No se puede agregar una pregunta a un árbol vacío" );
    }

    /**
     * Busca si existe un animal especifico en el mundo<br>
     * @param animal Nombre del animal que se quiere buscar. Diferente de null.
     * @return True en caso que el animal exista, false de lo contrario.
     */
    private boolean buscarAnimal( String animal )
    {
        return raiz == null ? false : raiz.buscarAnimal( animal );
    }

    /**
     * Retorna la raíz del árbol<br>
     * @return la raíz del árbol
     */
    public Pregunta darRaiz( )
    {
        return raiz;
    }

    /**
     * Crea un árbol con un solo animal
     * @param primerAnimal el nombre del primer animal
     */
    public void agregarPrimerAnimal( String primerAnimal )
    {
        raiz = new Pregunta( primerAnimal, "" );
        preguntaActual = raiz;
    }

    /**
     * Método que verifica la invariante<br>
     * <b>inv: </b> <br>
     * No debería existir animales repetidos<br>
     */
    private void verificarInvariante( )
    {
        List animales = darAnimales( );
        boolean encontrado = false;
        for( int i = 0; i < animales.size( ) && !encontrado; i++ )
        {
            Pregunta animal = ( Pregunta )animales.get( i );
            for( int j = 0; j < animales.size( ) && !encontrado; j++ )
            {
                Pregunta animal2 = ( Pregunta )animales.get( j );
                if( animal.darNombreAnimal( ).equalsIgnoreCase( animal2.darNombreAnimal( ) ) && i != j )
                {
                    encontrado = true;
                }
            }
        }
        assert !encontrado : "No debería haber animales repetidos";
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