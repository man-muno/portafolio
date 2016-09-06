/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Fila.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
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
 * Clase que representa una fila de la imagen.
 */
public class Fila implements Serializable
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Representa la lista de píxeles de la fila.
     */
    private ArrayList columnaPixeles;

    /**
     * Representa el número de píxeles de la fila.
     */
    private int numeroPixeles;

    /**
     * Representa el número de píxeles negros de la fila.
     */
    private int numeroPixelesNegros;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una fila dado el número de píxeles que va tener.
     * @param numeroPixeles Número de píxeles va tener la nueva fila. Entero mayor a cero.
     */
    public Fila( int numeroPixeles )
    {
        columnaPixeles = new ArrayList( );
        numeroPixelesNegros = 0;
        this.numeroPixeles = numeroPixeles;
        for( int i = 0; i < this.numeroPixeles; i++ )
        {
            columnaPixeles.add( new Pixel( ) );
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Revisa si el píxel de la columna es negro o es blanco dada su posición.
     * @param columna Posición sobre la lista de píxeles donde se quiere averiguar el color. Entero mayor a cero y menor que la cantidad de columnas.
     * @return Booleano que representa: <br>
     *         <li>true, en caso que sea negro. <br>
     *         <li>false, en caso que sea blanco. <br>
     */
    public boolean esPuntoNegro( int columna )
    {
        return ( ( Pixel )columnaPixeles.get( columna ) ).esNegro( );
    }

    /**
     * Agrega un punto en la fila, en la columna dada
     * @param columna La columna del píxel que se quiere saber si es negro. Entero mayor a cero y menor que la cantidad de columnas.
     */
    public void agregarPunto( int columna )
    {
        if( !esPuntoNegro( columna ) )
        {
            numeroPixelesNegros++;
            Pixel pixel = ( Pixel )columnaPixeles.get( columna );
            pixel.cambiarColor( true );
        }
    }

    /**
     * Devuelve los píxeles que tiene la fila.
     * @return Lista con objetos de tipo Pixel. No es nula, pero puede estar vacía.
     */
    public ArrayList darPixeles( )
    {
        return columnaPixeles;
    }

    /**
     * Devuelve el número de píxeles negros que tiene la fila.
     * @return Número de píxeles negros que tiene la fila. Entero mayor o igual a cero.
     */
    public int darNumeroPixelesNegros( )
    {
        return numeroPixelesNegros;
    }

}
