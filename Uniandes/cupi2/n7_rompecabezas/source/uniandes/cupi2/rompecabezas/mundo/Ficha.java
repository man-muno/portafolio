/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas
 * Autor: Manuel Mu�oz - Oct 2, 2006
 * Autor: Milena Vela - 01-Dic-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.rompecabezas.mundo;

/**
 * Clase que representa una ficha de la figura <br>
 * <b>inv: </b> La posici�n de la ficha relativa a la figura tiene que ser mayor a cero. <br>
 * La fila a la que pertenece la ficha en la figura tiene que ser mayor o igual a cero. <br>
 * La columna a la que pertenece la ficha en la figura tiene que ser mayor o igual a cero. <br>
 * La ruta de la imagen debe ser diferente de null. <br>
 * La regi�n de la ficha solo puede ser ESQUINA, BORDE o INTERNA. <br>
 */
public class Ficha
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para indicar la regi�n esquina.
     */
    public static final int ESQUINA = 1;

    /**
     * Constante para indicar la regi�n borde.
     */
    public static final int BORDE = 2;

    /**
     * Constante para indicar la regi�n interna
     */
    public static final int INTERNA = 3;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Indica la regi�n donde se encuentra la ficha en la figura.
     */
    private int region;

    /**
     * Ruta relativa donde se encuentra la imagen de la ficha
     */
    private String rutaImagen;

    /**
     * Columna a la que pertenece la ficha en la figura de acuerdo al est�ndar de nombrado de matrices
     */
    private int numColumna;

    /**
     * Fila a la que pertenece la ficha en la figura de acuerdo al est�ndar de nombrado de matrices
     */
    private int numFila;

    /**
     * Posici�n de la ficha relativa a la figura. Las fichas se empiezan a enumerar dede la n�mero uno de izquierda a derecha y de arriba hacia abajo.
     */
    private int posicion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una ficha inicializando sus atributos con los valores que vienen por par�metro
     * @param ruta Ruta de la imagen de la ficha
     * @param nPosicion Posici�n de la ficha relativa a la figura
     * @param fila posici�n en x de la ficha, de acuerdo a como se nombran las posiciones en las matrices.
     * @param columna posici�n en y de la ficha, de acuerdo a como se nombran las posiciones en las matrices.
     * @param nRegion Entero que representa la regi�n donde se encuentra la ficha en la figura.
     */
    public Ficha( String ruta, int nPosicion, int fila, int columna, int nRegion )
    {
        rutaImagen = ruta;
        posicion = nPosicion;
        numFila = fila;
        numColumna = columna;
        region = nRegion;

        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Da la columna de la ficha en la figura
     * @return n�mero mayor o igual a cero
     */
    public int obtenerColumna( )
    {
        return numColumna;
    }

    /**
     * Da la fila de la ficha en la figura
     * @return n�mero mayor o igual a cero
     */
    public int obtenerFila( )
    {
        return numFila;
    }

    /**
     * Retorna la ruta de la imagen de la ficha
     * @return String diferente de null
     */
    public String obtenerRuta( )
    {
        return rutaImagen;
    }

    /**
     * Retorna la posici�n de la ficha con respecto a la figura
     * @return Entero mayor a 0
     */
    public int obtenerPosicion( )
    {
        return posicion;
    }

    /**
     * Retorna la regi�n donde se encuentra la ficha
     * @return Entero que puede tomar los valores ESQUINA, BORDE, INTERNA
     */
    public int obtenerRegion( )
    {
        return region;
    }

    /**
     * Representaci�n en String de la ficha
     */
    public String toString( )
    {
        String nombre = "Ficha " + posicion + " - ";
        if( region == ESQUINA )
            nombre += "Est� en la esquina";
        if( region == BORDE )
            nombre += "Est� en el borde";
        if( region == INTERNA )
            nombre += "Es interna";

        return nombre;
    }

    /**
     * Compara dos fichas por n�mero de columna
     * @param f La ficha con la que se est� comparando - f != null
     * @return Retorna 0 si las fichas pertenecen a la misma columna. <br>
     *         Retorna -1 si la columna de la ficha f es mayor . <br>
     *         Retorna 1 si la columna de la ficha f es menor. <br>
     */
    public int compararPorColumna( Ficha f )
    {
        if( numColumna == f.obtenerColumna( ) )
            return 0;
        else if( numColumna > f.obtenerColumna( ) )
            return 1;
        else
            return -1;

    }

    /**
     * Compara dos fichas por n�mero de fila
     * @param f La ficha con la que se est� comparando - f != null
     * @return Retorna 0 si las fichas pertenecen a la misma fila. <br>
     *         Retorna -1 si la fila de la ficha f es mayor . <br>
     *         Retorna 1 si la fila de la ficha f es menor. <br>
     */
    public int compararPorFila( Ficha f )
    {
        if( numFila == f.obtenerFila( ) )
            return 0;
        else if( numFila > f.obtenerFila( ) )
            return 1;
        else
            return -1;

    }

    /**
     * Compara dos fichas por regi�n. La regi�n menor es ESQUINA, seguida de BORDE y la regi�n mayor es INTERNA.
     * @param f La ficha con la que se est� comparando - f != null
     * @return Retorna 0 si las fichas pertenecen a la misma regi�n. <br>
     *         Retorna -1 si la regi�n de la ficha f es mayor . <br>
     *         Retorna 1 si la regi�n de la ficha f es menor. <br>
     */
    public int compararPorRegion( Ficha f )
    {
        if( region == f.obtenerRegion( ) )
            return 0;
        else if( region > f.obtenerRegion( ) )
            return 1;
        else
            return -1;

    }

    /**
     * Compara dos fichas por posici�n relativa a la figura.
     * @param f La ficha con la que se est� comparando - f != null
     * @return Retorna 0 si las fichas tienen la misma posici�n. <br>
     *         Retorna -1 si la posici�n de la ficha f es mayor . <br>
     *         Retorna 1 si la posici�n de la ficha f es menor. <br>
     */
    public int compararPorPosicion( Ficha f )
    {
        if( posicion == f.obtenerPosicion( ) )
            return 0;
        else if( posicion > f.obtenerPosicion( ) )
            return 1;
        else
            return -1;

    }

    /**
     * M�todo verifica el invariante de la clase. <br>
     * <b>inv: </b> La posici�n de la ficha relativa a la figura tiene que ser mayor a cero. <br>
     * La fila a la que pertenece la ficha en la figura tiene que ser mayor o igual a cero. <br>
     * La columna a la que pertenece la ficha en la figura tiene que ser mayor o igual a cero. <br>
     * La ruta de la imagen debe ser diferente de null. <br>
     * La regi�n de la ficha solo puede ser ESQUINA, BORDE o INTERNA. <br>
     */
    private void verificarInvariante( )
    {
        assert posicion > 0 : "La posici�n de la ficha es inv�lida";
        assert numFila >= 0 : "La fila de la ficha tiene que ser mayor o igual a cero";
        assert numColumna >= 0 : "Tiene que ser mayor o igual a cero";
        assert rutaImagen != null : "La ruta de la imagen tiene que ser diferente de null";
        assert ( region == ESQUINA || region == BORDE || region == INTERNA ) : "La regi�n de la ficha es incorrecta";
    }
}