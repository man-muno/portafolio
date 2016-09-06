/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Fila.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_imagenDispersaPuntos
 * Autor: Pablo Andr�s M�rquez - 03-sep-2006
 * Autor: Manuel Mu�oz - 24 - feb - 2007
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
     * Representa la lista de p�xeles de la fila.
     */
    private ArrayList columnaPixeles;

    /**
     * Representa el n�mero de p�xeles de la fila.
     */
    private int numeroPixeles;

    /**
     * Representa el n�mero de p�xeles negros de la fila.
     */
    private int numeroPixelesNegros;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una fila dado el n�mero de p�xeles que va tener.
     * @param numeroPixeles N�mero de p�xeles va tener la nueva fila. Entero mayor a cero.
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Revisa si el p�xel de la columna es negro o es blanco dada su posici�n.
     * @param columna Posici�n sobre la lista de p�xeles donde se quiere averiguar el color. Entero mayor a cero y menor que la cantidad de columnas.
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
     * @param columna La columna del p�xel que se quiere saber si es negro. Entero mayor a cero y menor que la cantidad de columnas.
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
     * Devuelve los p�xeles que tiene la fila.
     * @return Lista con objetos de tipo Pixel. No es nula, pero puede estar vac�a.
     */
    public ArrayList darPixeles( )
    {
        return columnaPixeles;
    }

    /**
     * Devuelve el n�mero de p�xeles negros que tiene la fila.
     * @return N�mero de p�xeles negros que tiene la fila. Entero mayor o igual a cero.
     */
    public int darNumeroPixelesNegros( )
    {
        return numeroPixelesNegros;
    }

}
