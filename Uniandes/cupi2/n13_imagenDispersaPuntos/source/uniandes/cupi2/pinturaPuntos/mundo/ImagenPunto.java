/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ImagenPunto.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
 * Universidad de los Andes (Bogotá· - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_imagenDispersaPuntos
 * Autor: Pablo Andrés Márquez - 03-sep-2006
 * Autor: Manuel Muñoz - 24 - feb - 2007
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
     * Numero de píxeles negros que tiene la imagen.
     */
    protected int numNegros;
    
    /**
     * Numero de píxeles blancos que tiene la imagen.
     */
    protected int numBlancos;

    /**
     * Número de filas que tiene la imagen.
     */
    protected int numFilas;

    /**
     * Número de columnas que tiene la imagen.
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
     * Crea una imagen dispersa por puntos a partir del número de filas y columnas que va tener.
     * @param nNumFilas Número de filas que tiene la imagen. Entero mayor o igual a cero.
     * @param nNumColumnas Número de columnas que tiene la imagen. Entero mayor o igual a cero.
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
        numBlancos = numFilas * numColumnas;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega un punto a la imagen dadas las coordenadas.
     * @param fila Fila en la que se quiere colocar el punto. Entero mayor a cero y menor que la cantidad de filas.
     * @param columna Columna que se quiere poner el punto. Entero mayor a cero y menor que la cantidad de columnas.
     */
    public void agregarPunto( int fila, int columna )
    {
        Fila myFila = ( Fila )filas.get( fila );
        if( !myFila.esPuntoNegro( columna ) )
        {
            myFila.agregarPunto( columna );
            numNegros++;
            numBlancos--;
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
     * Devuelve el número de columnas que tiene la imagen.
     * @return Número de columnas de la imagen. Entero mayor a cero y menor que la cantidad de columnas.
     */
    public int darColumnas( )
    {
        return numColumnas;
    }

    /**
     * Devuelve el número de filas de la imagen.
     * @return Número de filas de la imagen. Entero mayor a cero y menor que la cantidad de filas.
     */
    public int darFilas( )
    {
        return numFilas;
    }

    /**
     * Devuelve si el píxel que se encuentra en las coordenada dada es negro.
     * @param fila La fila del píxel que se quiere saber si es negro. Entero mayor a cero y menor que la cantidad de filas.
     * @param columna La columna del píxel que se quiere saber si es negro. Entero mayor a cero y menor que la cantidad de columnas.
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
     * Devuelve el numero de píxeles negros dada una fila.
     * @param fila Fila que se desea averiguar cuantos píxeles son negros. Entero mayor a cero y menor que la cantidad de filas.
     * @return Número de píxeles negros en una imagen. Entero mayor a cero o cero.
     */
    public int darNumeroPixelesNegrosFila( int fila )
    {
        Fila myFila = ( Fila )filas.get( fila );
        return myFila.darNumeroPixelesNegros( );
    }
    
    /**
     * Devuelve el numero de píxeles negros dada una columna.
     * @param columna Columna que se desea averiguar cuantos píxeles son negros. Entero mayor a cero y menor que la cantidad de filas.
     * @return Número de píxeles negros en una imagen. Entero mayor a cero o cero.
     */
    public int darNumeroPixelesNegrosColumna( int columna )
    {
        int contador = 0;
        for(int i = 0;i<filas.size( );i++)
        {
            if(((Fila)filas.get( i )).esPuntoNegro( columna ))
            {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve el número de píxeles negros que tiene toda la imagen.
     * @return Píxeles negros que tiene la imagen. Entero mayor a cero o cero.
     */
    public int darNumeroNegros( )
    {
        return numNegros;

    }
    
    /**
     * Devuelve el número de píxeles blancos que tiene toda la imagen.
     * @return Píxeles blancos que tiene la imagen. Entero mayor a cero o cero.
     */
    public int darNumeroBlancos( )
    {
        return numBlancos;

    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * 
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * 
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}
