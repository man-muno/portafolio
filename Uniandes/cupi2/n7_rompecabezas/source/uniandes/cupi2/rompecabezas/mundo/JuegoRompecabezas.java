/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: JuegoRompecabezas.java,v 1.14 2007/01/16 16:00:29 f-vela Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas
 * Autor: Manuel Mu�oz - 02-Oct-2006
 * Autor: Milena Vela - 01-Dic-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.rompecabezas.mundo;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa el Juego de Rompecabezas. <br>
 * <b>inv: </b> El vector de figuras ha sido inicializado. figuras != null <br>
 * No hay dos figuras con el mismo nombre en el juego <br>
 * El n�mero intentos no puede se menor a cero <br>
 * El n�mero de veces que se ha jugado no puede ser menor a cero <br>
 * El tiempo de juego no puede ser negativo<br>
 */

public class JuegoRompecabezas
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * N�mero de rompecabezas que han sido tratados de armar.
     */
    private int intentos;

    /**
     * N�mero de figuras que han sido armadas
     */
    private int armadas;

    /**
     * Tiempo(milisegundos) en el que empez� el juego.
     */
    private long tiempoInicial;

    /**
     * Tiempo(milisegundos) en el que termin� el juego.
     */
    private long tiempoFinal;

    /**
     * Figuras que existen para armar
     */
    private ArrayList figuras;

    /**
     * Figura actual que est� siendo armada
     */
    private Figura figuraActual;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa el juego de rompecabezas. <br>
     * El tiempo se inicializa en cero. <br>
     * El n�mero de intentos se inicializa en cero. <br>
     * El n�mero de figuras armadas se inicializa en cero. <br>
     * Se inicializa el vector de figuras vac�o. <br>
     */
    public JuegoRompecabezas( )
    {
        intentos = 0;
        armadas = 0;
        tiempoInicial = 0;
        tiempoFinal = 0;
        figuras = new ArrayList( );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega la figura al vector de figuras <br>
     * <b>post: </b> Se agreg� la figura al vector de figuras si no existe otra figura con el mismo nombre.
     * @param f Figura que se va a adicionar al vector. f != null
     * @return true si la figura se puedo agregar, false en caso contrario.
     */
    public boolean agregarFigura( Figura f )
    {
        int figuraBuscada = buscarFigura( f.obtenerNombre( ) );
        boolean agregado = false;

        if( figuraBuscada == -1 )
        {
            figuras.add( f );
            agregado = true;
        }
        verificarInvariante( );

        return agregado;
    }

    /**
     * M�todo que es llamado cuando se quiere iniciar un juego <br>
     * <b>post: </b> Se inicializa el tiempo y se establece la figura actual
     * @param figura Figura que se va a armar. figura != null
     */
    public void iniciarJuego( Figura figura )
    {
        tiempoInicial = darTiempoActual( );
        tiempoFinal = darTiempoActual( );
        figuraActual = figura;
        verificarInvariante( );
    }

    /**
     * Indica si la figura se ha armado correctamente o no y actualiza los datos del juego. <br>
     * <b>post: </b> Si la figura esta armada se incrementa el n�mero de figuras armadas del juego, de lo contrario se incrementa el n�mero de intentos.
     * @param fichasJugadas Arreglo de enteros con los identificadores de las fichas que est�n colocadas en la figura de juego. Diferente de null
     */
    public boolean terminarJuego( int[] fichasJugadas )
    {
        boolean armado = figuraActual.estaArmada( fichasJugadas );
        if( armado )
        {
            armadas++;
        }
        else
        {
            intentos++;
        }
        verificarInvariante( );
        return armado;
    }

    /**
     * M�todo que ordena las figuras del rompecabezas por categor�a (lexicogr�ficamente) de manera ascendente. <br>
     * El m�todo de ordenamiento utilizado es selecci�n. <br>
     * <b>post: </b>El vector de figuras est� ordenado por categor�a de manera ascendente.
     */
    public void ordenarFigurasPorCategoria( )
    {
        for( int i = 0; i < figuras.size( ) - 1; i++ )
        {
            Figura menor = ( Figura )figuras.get( i );
            int cual = i;
            for( int j = i + 1; j < figuras.size( ); j++ )
            {
                Figura posibleMenor = ( Figura )figuras.get( j );
                if( posibleMenor.compararPorCategoria( menor ) < 0 )
                {
                    menor = posibleMenor;
                    cual = j;
                }
            }
            Figura temp = ( Figura )figuras.get( i );
            figuras.set( i, menor );
            figuras.set( cual, temp );
        }
        verificarInvariante( );
    }

    /**
     * M�todo que ordena las figuras del rompecabezas por dificultad (lexicogr�ficamente) de manera ascendente. <br>
     * El m�todo de ordenamiento utilizado es burbuja. <br>
     * <b>post: </b>El vector de figuras est� ordenado por dificultad de manera ascendente.
     */
    public void ordenarFigurasPorDificultad( )
    {
        for( int i = figuras.size( ); i > 0; i-- )
        {
            for( int j = 0; j < i - 1; j++ )
            {
                Figura figura1 = ( Figura )figuras.get( j );
                Figura figura2 = ( Figura )figuras.get( j + 1 );
                if( figura1.compararPorDificultad( figura2 ) > 0 )
                {
                    figuras.set( j, figura2 );
                    figuras.set( j + 1, figura1 );
                }
            }
        }
        verificarInvariante( );
    }

    /**
     * M�todo que ordena las figuras del rompecabezas por nombre (lexicogr�ficamente) de manera ascendente. <br>
     * El m�todo de ordenamiento utilizado es inserci�n. <br>
     * <b>post: </b>El vector de figuras est� ordenado por nombre de manera ascendente.
     */
    public void ordenarFigurasPorNombre( )
    {
        for( int i = 1; i < figuras.size( ); i++ )
        {
            Figura porInsertar = ( Figura )figuras.get( i );
            boolean termino = false;

            for( int j = i; j > 0 && !termino; j-- )
            {
                Figura actual = ( Figura )figuras.get( j - 1 );

                if( actual.compararPorNombre( porInsertar ) > 0 )
                {
                    figuras.set( j, actual );
                    figuras.set( j - 1, porInsertar );
                }
                else
                    termino = true;
            }
        }
        verificarInvariante( );
    }

    /**
     * Busca una figura por su nombre y retorna la posici�n en la que se encuentra en el vector de figuras del juego. <br>
     * @param nombre El nombre de la figura buscada - nombre!=null
     * @return Se retorn� la posici�n donde se encuentra una figura con el nombre dado. Si no se encuentra ninguna figura con ese nombre se retorn� -1.
     */
    public int buscarFigura( String nombre )
    {
        int posicion = -1;
        boolean termine = false;

        for( int i = 0; i < figuras.size( ) && !termine; i++ )
        {
            Figura figuraPosicion = ( Figura )figuras.get( i );
            String nombreFigura = figuraPosicion.obtenerNombre( );

            // Los nombres son iguales
            if( nombreFigura.equalsIgnoreCase( nombre ) )
            {
                posicion = i;
                termine = true;
            }
        }

        return posicion;
    }

    /**
     * Busca una figura utilizando una b�squeda binaria. <br>
     * <b>pre: </b> La lista de figuras se encuentra ordenada por nombre. <br>
     * @param nombre es el nombre de la figura que se va a buscar - nombre!=null
     * @return Se retorn� la posici�n de la figura con el nombre dado. Si la figura no existe se retorn� -1.
     */
    public int buscarBinarioPorNombre( String nombre )
    {
        int posicion = -1;
        int inicio = 0;
        int fin = figuras.size( ) - 1;

        // Figura a buscar
        Ficha fichas[][] = new Ficha[1][1];
        fichas[ 0 ][ 0 ] = new Ficha( "f1imagen1", 1, 0, 0, Ficha.ESQUINA );
        Figura aBuscar = new Figura( nombre, "", 1, 1, "", "", fichas );

        while( inicio <= fin && posicion == -1 )
        {
            int medio = ( inicio + fin ) / 2;
            Figura mitad = ( Figura )figuras.get( medio );
            if( mitad.compararPorNombre( aBuscar ) == 0 )
            {
                posicion = medio;
            }
            else if( mitad.compararPorNombre( aBuscar ) > 0 )
            {
                fin = medio - 1;
            }
            else
            {
                inicio = medio + 1;
            }
        }
        return posicion;
    }

    /**
     * Retorna una posici�n aleatoriamente en el vector de figuras
     * @return pos Posici�n en el vector de figuras. pos < figuras.size()
     */
    public int seleccionarAleatorio( )
    {
        int pos = ( int ) ( ( Math.random( ) + .1 ) * figuras.size( ) );
        return pos;
    }

    /**
     * Retorna la cantidad de rompecabezas que fueron armados
     * @return N�mero de rompecabezas armados
     */
    public int obtenerArmados( )
    {
        return armadas;
    }

    /**
     * Retorna la cantidad de rompecabezas que han sido tratados de armar
     * @return N�mero de intentos
     */
    public int obtenerIntentos( )
    {
        return intentos;
    }

    /**
     * Incrementa en uno la cantidad de rompecabezas que han sido tratados de armar
     */
    public void incrementarIntentos( )
    {
        intentos++;
    }

    /**
     * Retorna un vector con las figuras del rompecabezas
     * @return Vector con las figuras.
     */
    public ArrayList obtenerFiguras( )
    {
        return figuras;
    }

    /**
     * Retorna la figura que se est� armando
     * @return Figura actual
     */
    public Figura obtenerFiguraActual( )
    {
        return figuraActual;
    }

    /**
     * Establece la figura actual con la que viene por par�metro
     * @param figura diferente de null
     */
    public void establecerFiguraActual( Figura figura )
    {
        figuraActual = figura;
    }

    /**
     * M�todo que indica a la figura actual que ordene sus fichas por columnas <br>
     * <b>post: </b> Las fichas de la figura est�n ordenadas por columna.
     */
    public void ordenarFichasPorColumnas( )
    {
        figuraActual.ordenarPorColumnas( );
    }

    /**
     * M�todo que indica a la figura actual que ordene sus fichas por filas <br>
     * <b>post: </b> Las fichas de la figura est�n ordenadas por fila.
     */
    public void ordenarFichasPorFilas( )
    {
        figuraActual.ordenarPorFilas( );
    }

    /**
     * M�todo que indica a la figura actual que ordene sus fichas por regiones <br>
     * <b>post: </b> Las fichas de la figura est�n ordenadas por regiones.
     */
    public void ordenarFichasPorRegiones( )
    {
        figuraActual.ordenarPorRegion( );
    }

    /**
     * Devuelve un n�mero que representa los milisegundos desde el 1 de enero de 1970 hasta el tiempo actual.
     * @return milisegundos desde el 1 de enero de 1970 hasta el tiempo actual.
     */
    private long darTiempoActual( )
    {
        return new Date( ).getTime( );
    }

    /**
     * Retorna el tiempo total en segundos que fue necesario para resolver el juego
     * @return tiempo
     */
    public int darTiempoTotal( )
    {
        if( tiempoInicial == 0 )
            return 0;
        else
            tiempoFinal = darTiempoActual( );
        return ( int ) ( ( tiempoFinal - tiempoInicial ) / 1000 );
    }

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv: </b> El vector de figuras ha sido inicializado. figuras != null <br>
     * No hay dos figuras con el mismo nombre en el juego <br>
     * El n�mero intentos no puede se menor a cero <br>
     * El n�mero de veces que se ha jugado no puede ser menor a cero <br>
     * El tiempo de juego no puede ser negativo<br>
     */
    private void verificarInvariante( )
    {
        assert figuras != null : "La lista de figuras no puede ser null";
        assert ( !buscarFigurasConNombresRepetidos( ) ) : "Hay dos figuras con el mismo nombre";
        assert intentos >= 0 : "La cantidad de intentos de juego del usuario no puede ser negativa";
        assert armadas >= 0 : "La cantidad de figuras armados del usuario no puede ser negativa";
        assert tiempoInicial <= tiempoFinal : "El tiempo de juego no puede ser negativo";
    }

    /**
     * Verifica si hay dos figuras con el mismo nombre.
     * @return Retorna true si hay dos figuras con el mismo nombre, retorna false en caso contrario
     */
    private boolean buscarFigurasConNombresRepetidos( )
    {
        for( int i = 0; i < figuras.size( ); i++ )
        {
            Figura figuraI = ( Figura )figuras.get( i );
            for( int j = 0; j < figuras.size( ); j++ )
            {
                if( i != j )
                {
                    Figura figuraJ = ( Figura )figuras.get( j );
                    if( figuraJ.compararPorNombre( figuraI ) == 0 )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
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