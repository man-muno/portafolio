/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Pixel.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
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

/**
 * Clase que representa un p�xel.
 */
public class Pixel implements Serializable
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Color de p�xel.
     * <li>true: Negro
     * <li>false: Blanco
     */
    private boolean color;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por defecto. El color por defecto es blanco.
     */
    public Pixel( )
    {
        color = false;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Verificaci�n si el p�xel es blanco o negro.
     * @return Los posibles valores que se toman son:<br>
     *         <li>true: Negro
     *         <li>false: Blanco
     */
    public boolean esNegro( )
    {
        return color;
    }

    /**
     * Asigna el nuevo color al p�xel.
     * @param nColor Color que se le va asignar al p�xel.
     *        <li>true: Negro
     *        <li>false: Blanco
     */
    public void cambiarColor( boolean nColor )
    {
        color = nColor;
    }
}
