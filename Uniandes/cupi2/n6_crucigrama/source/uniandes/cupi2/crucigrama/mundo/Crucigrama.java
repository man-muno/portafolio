/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Crucigrama.java,v 1.3 2007/04/03 07:40:44 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_crucigrama
 * Autor: Manuel Muñoz - 05-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.crucigrama.mundo;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Clase que representa el crucigrama.
 */
public class Crucigrama
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que identifica la propiedad de la cantidad de columnas
     */
    private static final String PROPIEDAD_NUM_COLUMNAS = "crucigrama.columnas";

    /**
     * Constante que identifica la propiedad de la cantidad de filas
     */
    private static final String PROPIEDAD_NUM_FILAS = "crucigrama.filas";

    /**
     * Constante que identifica la propiedad de una fila de letras
     */
    private static final String PROPIEDAD_FILA = "crucigrama.fila";

    /**
     * Constante que identifica la propiedad de la coordenada de una palabra vertical
     */
    private static final String PROPIEDAD_COORD_VERTICAL = "crucigrama.Vpalabra";

    /**
     * Constante que identifica la propiedad de la coordenada de una palabra horizontal
     */
    private static final String PROPIEDAD_COORD_HORIZONTAL = "crucigrama.Hpalabra";

    /**
     * Constante que identifica la propiedad de la descripción de la palabra horizontal
     */
    private static final String PROPIEDAD_DESCRIPCION_HORIZONTAL = "crucigrama.Hdescripcion";

    /**
     * Constante que identifica la propiedad de la descripción de la palabra vertical
     */
    private static final String pROPIEDAD_DESCRIPCION_VERTICAL = "crucigrama.Vdescripcion";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Matriz donde se guardan todas las letras del crucigrama
     */
    private Letra[][] letras;

    /**
     * Lista donde se guardan todas las preguntas verticales
     */
    private ArrayList verticales;

    /**
     * Lista donde se guardan todas las preguntas horizontales
     */
    private ArrayList horizontales;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por defecto. Inicia las listas pero no la matriz.
     */
    public Crucigrama( )
    {
        horizontales = new ArrayList( );
        verticales = new ArrayList( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa el tablero utilizando los valores que vienen en el archivo de propiedades. <br>
     * <b> post: </b> tablero!=null.<br>
     * @param propiedades Objeto Properties con la solución del crucigrama. propiedades!=null
     * @throws Exception Se lanza esta excepción si hay problemas leyendo el archivo.
     */
    public void cargarTablero( Properties propiedades ) throws Exception
    {
        String strFilas = propiedades.getProperty( PROPIEDAD_NUM_FILAS);
        String strColumnas = propiedades.getProperty( PROPIEDAD_NUM_COLUMNAS );

        int filas = 0;
        int columnas = 0;

        try
        {
            filas = Integer.parseInt( strFilas );
            columnas = Integer.parseInt( strColumnas );
        }
        catch( Exception e )
        {
            throw new Exception( "Error de lectura del archivo: Los datos del tamaño del tablero son inválidos" );
        }
        letras = new Letra[filas][columnas];

        // Carga de las letras
        for( int i = 0; i < filas; i++ )
        {
            String strLetras = propiedades.getProperty( PROPIEDAD_FILA + ( i + 1 ) );
            for( int j = 0; j < columnas; j++ )
            {
                char c = strLetras.charAt( j );
                letras[ i ][ j ] = new Letra(""+c);
            }
        }

        // Carga de las coordenadas de las palabras

        boolean termino = false;
        for( int i = 0; !termino; i++ )
        {
            String strDescripcion = propiedades.getProperty( PROPIEDAD_COORD_HORIZONTAL + (i+1) );
            if( strDescripcion != null )
            {
                int fila = 0;
                int columna = 0;
                // Al hacer split del String, en la primera posición queda la fila y en la segunda la columna
                String[] arrCoordenada = new String[2];
                arrCoordenada = strDescripcion.split( ";" );
                try
                {
                    fila = Integer.parseInt( arrCoordenada[ 0 ].trim( ) );
                    columna = Integer.parseInt( arrCoordenada[ 1 ].trim( ) );
                }
                catch( RuntimeException e )
                {
                    throw new Exception( "Error de lectura del archivo: Las coordenadas son inválidas" );
                }
                Pregunta pregunta = new Pregunta( fila, columna, propiedades.getProperty( PROPIEDAD_DESCRIPCION_HORIZONTAL + (i+1) ) );
                horizontales.add( pregunta );
            }
            else
            {
                termino = true;
            }
        }

        termino = false;
        for( int i = 0; !termino; i++ )
        {
            String strDescripcion = propiedades.getProperty( PROPIEDAD_COORD_VERTICAL + (i+1) );
            if( strDescripcion != null )
            {
                int fila = 0;
                int columna = 0;
                // Al hacer split del String, en la primera posición queda la fila y en la segunda la columna
                String[] arrCoordenada = new String[2];
                arrCoordenada = strDescripcion.split( ";" );
                try
                {
                    fila = Integer.parseInt( arrCoordenada[ 0 ].trim( ) );
                    columna = Integer.parseInt( arrCoordenada[ 1 ].trim( ) );
                }
                catch( RuntimeException e )
                {
                    throw new Exception( "Error de lectura del archivo: Las coordenadas son inválidas" );
                }
                Pregunta pregunta = new Pregunta( fila, columna, propiedades.getProperty( pROPIEDAD_DESCRIPCION_VERTICAL + (i+1) ) );
                verticales.add( pregunta );
            }
            else
            {
                termino = true;
            }
        }
    }
    
    /**
     * Retorna una matriz donde solo se encuentran los campos que deben ser casillas negras.
     * @return Matriz de caracteres. Las casillas negras están representadas por el carácter $.
     */
    public char[][] obtenerCasillasNegras( )
    {
        char[][] casillasNegras = new char[letras.length][letras[0].length];
        for(int i=0;i<letras.length;i++)
        {
            Letra[] columna = letras[i];
            for(int j=0;j<columna.length;j++)
            {
                Letra letra = columna[j];
                if(letra.esNegra())
                {
                    casillasNegras[i][j] = '$';
                }
            }
        }
        return casillasNegras;
    }
    
    /**
     * Retorna la lista de las preguntas horizontales.
     * @return Lista de objetos de tipo pregunta. Puede ser vacía pero no null.
     */
    public ArrayList obtenerListaPreguntasHorizontales( )
    {
        return horizontales;
    }
    
    /**
     * Retorna la lista de las preguntas verticales.
     * @return Lista de objetos de tipo String. Puede ser vacía pero no null.
     */
    public ArrayList obtenerListaPreguntasVerticales( )
    {
        return verticales;
    }

    
    /**
     * Valida que las palabras ingresadas por el usuario sean validas.
     * @param letrasUsuario La matriz de caracteres que el usuario ingreso en la interfaz.
     * @return true en caso que el crucigrama este correcto, false de lo contrario
     */
    public boolean validarJuego( String[][] letrasUsuario )
    {
        boolean correcto = true;
        for( int i = 0; i < letras.length && correcto; i++ )
        {
            for( int j = 0; j < letras[i].length && correcto; j++ )
            {
                letras[ i ][ j ].ingresarLetraJuego( letrasUsuario[ i ][ j ]);
                if(!letras[ i ][ j ].esLetraCorrecta( ))
                    correcto = false;
            }
        }
        return correcto;
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