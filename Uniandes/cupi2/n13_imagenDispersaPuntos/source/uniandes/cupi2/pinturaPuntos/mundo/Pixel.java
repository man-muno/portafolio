/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Pixel.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
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

/**
 * Clase que representa un píxel.
 */
public class Pixel implements Serializable
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Color de píxel.
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
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Verificación si el píxel es blanco o negro.
     * @return Los posibles valores que se toman son:<br>
     *         <li>true: Negro
     *         <li>false: Blanco
     */
    public boolean esNegro( )
    {
        return color;
    }

    /**
     * Asigna el nuevo color al píxel.
     * @param nColor Color que se le va asignar al píxel.
     *        <li>true: Negro
     *        <li>false: Blanco
     */
    public void cambiarColor( boolean nColor )
    {
        color = nColor;
    }
}
