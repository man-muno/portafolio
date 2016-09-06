/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Sudoku.java,v 1.12 2007/01/22 21:07:48 f-vela Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Manuel Muñoz - 07-Sep-2006
 * Autor: Daniel Romero - 17-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sudoku.mundo;

import java.util.Properties;

/**
 * Clase que representa el juego Sudoku
 */
public class Sudoku
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Modela como se llamará la llave del archivo de propiedades
     */
    private static final String LLAVE_PROPIEDADES = "sudoku.fila";

    /**
     * Representa el número de filas del tablero
     */
    public static final int NUMERO_FILAS = 9;

    /**
     * Representa el número de columnas del tablero
     */
    public static final int NUMERO_COLUMNAS = 9;

    /**
     * Representa la cantidad de zonas que tiene el tablero
     */
    public static final int CANTIDAD_ZONAS = 9;

    /**
     * Representa la cantidad de casillas que se van a mostrar al comenzar el juego. <br>
     * Su valor máximo debería ser 9 y el mínimo debería ser 1
     */
    public static final int CANTIDAD_CASILLAS_INICIALES = 3;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Indica si se está jugando actualmente
     */
    private boolean jugando;

    /**
     * La matriz de casillas del tablero
     */
    private Casilla[][] tablero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor del juego. <br>
     * <b> post: </b> tablero!=null.<br>
     */
    public Sudoku( )
    {
        tablero = new Casilla[NUMERO_FILAS][NUMERO_COLUMNAS];
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa el tablero utilizando los valores que vienen en el archivo de propiedades. <br>
     * <b> post: </b> tablero!=null.<br>
     * @param propiedades Objeto Properties con la solución del sudoku. propiedades!=null
     * @throws Exception Se lanza esta excepción si hay problemas leyendo el archivo.
     */
    public void cargarTablero( Properties propiedades ) throws Exception
    {
        for( int i = 0; i < NUMERO_COLUMNAS; i++ )
        {
            String fila = ( String )propiedades.get( LLAVE_PROPIEDADES + ( i + 1 ) );
            if( fila != null && fila.length( ) == NUMERO_FILAS )
                cargarFila( fila, i );
            else
                throw new Exception( "El archivo no tiene el formato esperado" );
        }
    }

    /**
     * Comienza el juego, marcando las casillas que se mostrarán <br>
     * <b> post: </b> jugando = true. <br>
     */
    public void iniciarJuego( )
    {
        jugando = true;
        marcarIniciales( );

    }

    /**
     * Valida que se cumplan las reglas del juego. <br>
     * <b> pre: </b> tablero!=null y jugando= true <br>
     * @param elTablero Tablero con los valores que ingresa el usuario
     * @return Se retornó true si el juego es correcto, false de lo contrario
     */
    public boolean validarTablero( int[][] elTablero )
    {
        ingresarValoresUsuario( elTablero );
        boolean zonas = validarZonas( );
        boolean filas = validarFilas( );
        boolean columnas = validarColumnas( );
        boolean ceros = validarLlenado( );
        return zonas && filas && columnas && ceros;

    }

    /**
     * Llena el tablero con los valores que ingresó usuario. <br>
     * <b> pre: </b> tablero!=null y jugando= true. <br>
     * @param elTablero Tablero con los valores ingresados por el usuario. elTablero!=null
     */
    private void ingresarValoresUsuario( int[][] elTablero )
    {
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < NUMERO_COLUMNAS; j++ )
            {
                tablero[ i ][ j ].cambiarValorIngresado( elTablero[ i ][ j ] );
            }
        }
    }

    /**
     * Limpia el tablero, creándolo de nuevo. <br>
     * <b> post: </b> tablero!=null y el tablero se encuentra vacío<br>
     */
    public void limpiar( )
    {
        tablero = new Casilla[NUMERO_FILAS][NUMERO_COLUMNAS];
    }

    /**
     * Se utiliza para que la aplicación sepa que termino el juego <br>
     * <b> post: </b> jugando= false <br>
     */
    public void terminarJuego( )
    {
        jugando = false;
    }

    /**
     * Indica si el juego esta activo o no (si el juego no ha terminado)
     * @return Se retornó true si el juego no ha terminado, false de lo contrario
     */
    public boolean juegoActivo( )
    {
        return jugando;
    }

    /**
     * Crea una fila de casillas a partir de un String leído de un archivo de propiedades.<br>
     * <b> pre: </b> casillas!=null. <br>
     * @param fila El String leído del archivo de propiedades. fila!=null y fila debe tener el mismo largo NUMERO_FILAS de la matriz
     * @param numFila La fila en la matriz donde se colocara la línea. 0<=numFila<= NUMERO_FILAS
     * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo.
     */
    private void cargarFila( String fila, int numFila ) throws Exception
    {

        for( int i = 0; i < fila.length( ); i++ )
        {
            int valor;
            try
            {
                valor = Integer.parseInt( fila.substring( i, i + 1 ) );
            }
            catch( NumberFormatException e )
            {
                throw new Exception( "El archivo de propiedades no tiene el formato esperado" );
            }
            Casilla casilla = new Casilla( valor );
            tablero[ numFila ][ i ] = casilla;
        }
    }

    /**
     * Escoge y marca cuales van a ser las casillas que se mostrarán cuando se carga un juego. <br>
     * <b> pre: </b> tablero!=null. <br>
     */
    private void marcarIniciales( )
    {
        for( int i = 0; i < CANTIDAD_ZONAS; i++ )
        {
            Casilla[] lasCasillas = darCasillasZona( i + 1 );
            int contador = 0;
            while( contador < CANTIDAD_CASILLAS_INICIALES )
            {
                int valor = generarNumeroAleatorioEnRango( lasCasillas.length );
                Casilla casilla = lasCasillas[ valor ];
                if( !casilla.esInicial( ) )
                {
                    contador++;
                    casilla.volverIncial( );
                }
            }
        }
    }

    /**
     * Genera un número entero aleatorio entre 0 y tamRango - 1
     * @param tamRango Tamaño del rango
     * @return Número entero entre 0 y tamRango - 1
     */
    private int generarNumeroAleatorioEnRango( int tamRango )
    {
        return ( int ) ( Math.random( ) * tamRango );
    }

    /**
     * Retorna un arreglo con las casillas que se encuentran en una zona dada. <br>
     * <b> pre: </b> tablero!=null. <br>
     * @param zona La zona de la que se desean las casillas. 1<=zona<=9
     * @return Se retornó el arreglo con las casillas de la zona especificada
     */
    public Casilla[] darCasillasZona( int zona )
    {
        Casilla[] lasCasillas = new Casilla[NUMERO_FILAS];

        int inicioFilas = ( ( zona - 1 ) / 3 ) * 3;
        int inicioColumnas = ( ( zona - 1 ) % 3 ) * 3;
        int cont = 0;

        for( int i = inicioFilas; i < inicioFilas + 3; i++ )
        {
            for( int j = inicioColumnas; j < inicioColumnas + 3; j++ )
            {
                lasCasillas[ cont ] = tablero[ i ][ j ];
                cont++;
            }
        }

        return lasCasillas;
    }

    /**
     * Válida que se cumplan la regla de no repetidos en las filas. <br>
     * <b> pre: </b> tablero!=null y cada una de las posiciones de tablero se encuentran inicializadas. <br>
     * @return Se retornó true si se cumple la regla, false de lo contrario
     */
    private boolean validarFilas( )
    {
        boolean respuesta = true;
        for( int i = 0; i < NUMERO_COLUMNAS; i++ )
        {
            Casilla[] lasCasillas = darFila( i );
            if( existenRepedidos( lasCasillas ) )
            {
                marcar( lasCasillas );
                respuesta = false;
            }

        }
        return respuesta;
    }

    /**
     * Válida que se cumplan la regla de no repetidos en las columnas. <br>
     * <b> pre: </b> tablero!=null y cada una de las posiciones de tablero se encuentran inicializadas. <br>
     * @return Se retornó true si se cumple la regla, false de lo contrario
     */
    private boolean validarColumnas( )
    {
        boolean respuesta = true;
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            Casilla[] lasCasillas = tablero[ i ];
            if( existenRepedidos( lasCasillas ) )
            {
                marcar( lasCasillas );
                respuesta = false;
            }
        }
        return respuesta;
    }

    /**
     * Válida que se cumpla la regla de no repetidos en las zonas. <br>
     * <b> pre: </b> tablero!=null y cada una de las posiciones de tablero se encuentran inicializadas. <br>
     * @return Se retornó true si se cumple la regla, false de lo contrario
     */
    private boolean validarZonas( )
    {
        boolean respuesta = true;
        for( int i = 0; i < CANTIDAD_ZONAS; i++ )
        {
            Casilla[] lasCasillas = darCasillasZona( i + 1 );
            if( existenRepedidos( lasCasillas ) )
            {
                marcar( lasCasillas );
                respuesta = false;
            }
        }
        return respuesta;
    }

    /**
     * Marca las casillas para que sean mostradas como erradas.<br>
     * <b>post: </b> Se marcaron las casillas del arreglo dado. <br>
     * @param lasCasillas Arreglo de casillas para marcar. lasCasillas!=null
     */
    private void marcar( Casilla[] lasCasillas )
    {
        for( int i = 0; i < lasCasillas.length; i++ )
        {
            lasCasillas[ i ].marcar( );
        }
    }

    /**
     * Retorna las casillas de una fila. <br>
     * <b> pre: </b> casillas!=null. <br>
     * @param numFila El número de la fila que se quiere obtener. 0<=numFila<NUMERO_FILAS
     * @return Se retornó el arreglo de casillas de la fila especificada
     */
    private Casilla[] darFila( int numFila )
    {
        Casilla[] lasCasillas = new Casilla[NUMERO_COLUMNAS];
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            lasCasillas[ i ] = tablero[ i ][ numFila ];
        }
        return lasCasillas;
    }

    /**
     * Método que evalúa si existen repetidos en un arreglo de casillas. <br>
     * @param lasCasillas Casillas a evaluar. lasCasillas!=null y cada una de las posiciones de lasCasillas se encuentran <br>
     *        inicializados.
     * @return Se retornó true si existen repetidos en el arreglo, false de lo contrario
     */
    private boolean existenRepedidos( Casilla[] lasCasillas )
    {
        for( int i = 0; i < lasCasillas.length; i++ )
        {
            Casilla casilla = lasCasillas[ i ];
            for( int j = 0; casilla != null && j < lasCasillas.length; j++ )
            {
                Casilla casilla2 = lasCasillas[ j ];
                if( casilla.darValorIngresado( ) == casilla2.darValorIngresado( ) && i != j && casilla.darValorIngresado( ) != 0 )
                    return true;

            }
        }
        return false;
    }

    /**
     * Quita las marcas de error de las casillas. <br>
     * <b> pre: </b> tablero!=null y cada una de las posiciones de tablero se encuentran inicializadas. <br>
     * <b> post: </b> Todas las casillas de la matriz se encuentran desmarcadas
     * 
     */
    public void desmarcarCasillas( )
    {
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < NUMERO_COLUMNAS; j++ )
            {
                tablero[ i ][ j ].desmarcar( );
            }
        }
    }

    /**
     * Valida que no existan espacios vacíos en el tablero. <br>
     * <b> pre: </b> casillas!=null y cada una de las posiciones de la casilla se encuentran inicializadas. <br>
     * @return Se retornó true si existe algún espacio vacío, false de lo contrario
     */
    private boolean validarLlenado( )
    {
        for( int i = 0; i < NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < NUMERO_COLUMNAS; j++ )
            {
                Casilla casilla = tablero[ i ][ j ];
                if( casilla == null || casilla.darValorIngresado( ) == 0 )
                    return false;
            }
        }
        return true;
    }

    /**
     * Método que retorna el tablero del juego
     * @return Se retornaron las casillas del tablero
     */
    public Casilla[][] darTablero( )
    {
        return tablero;
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