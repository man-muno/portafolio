/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Casilla.java,v 1.6 2006/11/19 21:21:28 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Manuel Muñoz - Aug 7, 2006
 * Autor: Daniel Romero - 17-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sudoku.mundo;

/**
 * Clase que representa una casilla del tablero de Sudoku
 */
public class Casilla
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Representa el valor real de la casilla en el juego
     */
    private int valorReal;

    /**
     * Representa el valor ingresado por el usuario sobre la casilla
     */
    private int valorIngresado;

    /**
     * Indica si la casilla se muestra en el tablero al iniciar
     */
    private boolean inicial;

    /**
     * Indica si la casilla debe mostrarse en rojo
     */
    private boolean marcada;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una casilla con el valor dado como parámetro. <br>
     * <b> post: </b> Se creó la casilla con el valor especificado. <br>
     * @param valor El número que corresponde a la casilla. 1<=valor<=9.
     */
    public Casilla( int valor )
    {
        valorReal = valor;
        valorIngresado = 0;
        inicial = false;
        marcada = false;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Se indica si la casilla fue escogida como una casilla que se muestra al comienzo del juego
     * @return Se retornó true si la casilla es de las casillas iniciales, false de lo contrario.
     */
    public boolean esInicial( )
    {
        return inicial;
    }

    /**
     * Retorna el valor que realmente debe tener la casilla
     * @return Se retornó el valor real de la casilla
     */
    public int darValorReal( )
    {
        return valorReal;
    }

    /**
     * Indica si la casilla esta marcada para ser mostrada en rojo en el tablero
     * @return Se retornó true si la casilla debe verse en rojo, false de lo contrario.
     */
    public boolean estaMarcada( )
    {
        return marcada;
    }

    /**
     * Retorna el valor ingresado por el usuario
     * @return Se retornó el valor ingresado por el usuario
     */
    public int darValorIngresado( )
    {
        return valorIngresado;
    }

    /**
     * Cambia el estado de la casilla para que sea mostrada al comienzo del juego. <br>
     * <b> post: </b> inicial= true <br>
     */
    public void volverIncial( )
    {
        inicial = true;
    }

    /**
     * Marca una casilla para que sea mostrada en rojo. <br>
     * <b> post: </b> marcada= true <br>
     */
    public void marcar( )
    {
        marcada = true;
    }

    /**
     * Cambia el valor ingresado por el usuario. <br>
     * <b> post: </b> valorIngresado= nValorIngresado <br>
     * @param nValorIngresado Un entero entre 1 y 9
     */
    public void cambiarValorIngresado( int nValorIngresado )
    {
        valorIngresado = nValorIngresado;
    }

    /**
     * Le quita la marca a la casilla para que ya no sea mostrada en rojo <b> post: </b> marcada= false <br>
     */
    public void desmarcar( )
    {
        marcada = false;
    }
}
