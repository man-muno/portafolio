/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ImagenPunto.java,v 1.2 2007/03/05 19:18:48 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_pinturaPuntos
 * Autor: Pablo Andr�s M�rquez - 03-sep-2006
 * Autor: Manuel Mu�oz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.pinturaPuntos.mundo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa una Imagen dispersa tipo punto.
 */
public class ImagenPunto implements Serializable, IImagen
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Numero de p�xeles negros que tiene la imagen.
     */
    protected int numNegros;

    /**
     * N�mero de filas que tiene la imagen.
     */
    protected int numFilas;

    /**
     * N�mero de columnas que tiene la imagen.
     */
    protected int numColumnas;

    /**
     * La lista de filas de la imagen.
     */
    protected ArrayList filas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una imagen dispersa por puntos a partir del n�mero de filas y columnas que va tener.
     * @param nNumFilas N�mero de filas que tiene la imagen. Entero mayor o igual a cero.
     * @param nNumColumnas N�mero de columnas que tiene la imagen. Entero mayor o igual a cero.
     */
    public ImagenPunto( int nNumFilas, int nNumColumnas )
    {
        numFilas = nNumFilas;
        numColumnas = nNumColumnas;
        filas = new ArrayList( );
        for( int i = 0; i < numFilas; i++ )
        {
            filas.add( new Fila( numColumnas ) );
        }
        numNegros = 0;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega un punto a la imagen dadas las coordenadas.
     * @param fila Fila en la que se quiere colocar el punto.
     * @param columna Columna que se quiere poner el punto.
     */
    public void agregarPunto( int fila, int columna )
    {
        Fila myFila = ( Fila )filas.get( fila );
        if( !myFila.esPuntoNegro( columna ) )
        {
            myFila.agregarPunto( columna );
            numNegros++;
        }
    }

    /**
     * Retorna un iterador sobre los elementos de la imagen
     * @param fila Entero que representa la fila donde se quiere obtener el iterador
     * @return Un iterador sobre los elementos de la imagen
     */
    public IIteradorPintura darIterador( int fila )
    {
        IteradorSimple iterador = new IteradorSimple( fila, this );
        return iterador;
    }

    /**
     * Devuelve el n�mero de columnas que tiene la imagen.
     * @return N�mero de columnas de la imagen. Entero mayor o igual a cero
     */
    public int darColumnas( )
    {
        return numColumnas;
    }

    /**
     * Devuelve el n�mero de filas de la imagen.
     * @return N�mero de filas de la imagen. Entero mayor o igual a cero
     */
    public int darFilas( )
    {
        return numFilas;
    }

    /**
     * Devuelve si el p�xel que se encuentra en las coordenada dada es negro.
     * @param fila La fila del p�xel que se quiere saber si es negro. Entero mayor o igual a cero.
     * @param columna La columna del p�xel que se quiere saber si es negro. Entero mayor o igual a cero.
     * @return Booleano que representa: <br>
     *         <li>true, en caso que sea negro. <br>
     *         <li>false, en caso que sea blanco. <br>
     */
    public boolean esNegro( int fila, int columna )
    {
        Fila myFila = ( Fila )filas.get( fila );
        return myFila.esPuntoNegro( columna );
    }

    /**
     * Devuelve el numero de p�xeles negros dada una fila.
     * @param fila Fila que se desea averiguar cuantos p�xeles son negros. Entero mayor o igual a cero.
     * @return N�mero de p�xeles negros en una imagen. Entero mayor a cero o cero.
     */
    public int darNumeroPixelesNegrosFila( int fila )
    {
        Fila myFila = ( Fila )filas.get( fila );
        return myFila.darNumeroPixelesNegros( );
    }

    /**
     * Devuelve el n�mero de p�xeles negros que tiene toda la imagen.
     * @return P�xeles negros que tiene la imagen. Entero mayor a cero o cero.
     */
    public int darNumeroNegros( )
    {
        return numNegros;

    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * 
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * 
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}
