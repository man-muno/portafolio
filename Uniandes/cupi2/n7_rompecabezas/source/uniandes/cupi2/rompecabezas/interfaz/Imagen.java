/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas2
 * Autor: Manuel Mu�oz - Oct 11, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.rompecabezas.interfaz;

import java.awt.Image;

/**
 * Clase que representa una imagen de la figura
 */
public class Imagen
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Imagen que se quiere mostrar en este componente
     */
    private Image imagen;

    /**
     * Posici�n que tiene esta imagen dentro del rompecabezas
     */
    private int pos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros
     * @param image Imagen que quiere que se muestre al usuario. Puede ser null
     * @param nPos Posici�n que representa la imagen en el rompecabezas
     */
    public Imagen( Image image, int nPos )
    {
        imagen = image;
        pos = nPos;
    }

    /**
     * Retorna la imagen que contiene este objeto
     * @return La imagen que se quiere mostrar. Puede ser null
     */
    public Image obtenerImagen( )
    {
        return imagen;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la posici�n de la ficha en el rompecabezas
     * @return Entero mayor o igual a cero
     */
    public int obtenerPosicion( )
    {
        return pos;
    }
}