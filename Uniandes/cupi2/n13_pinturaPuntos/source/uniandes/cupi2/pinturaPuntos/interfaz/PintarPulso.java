/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PintarPulso.java,v 1.1 2007/03/05 02:09:02 man-muno Exp $
 * Universidad de los Andes (Bogotá· - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_pinturaPuntos
 * Autor: Pablo Andrés Márquez - 03-sep-2006
 * Autor: Manuel Muñoz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.pinturaPuntos.interfaz;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Controller para poder pintar a pulso.
 */
public class PintarPulso implements MouseMotionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Interfaz principal
     */
    private InterfazPinturaPuntos principal;

    /**
     * Altura de la imagen
     */
    private int x;

    /**
     * Ancho de la imagen
     */
    private int y;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Construye un Listener que está escuchando al mouse.
     * 
     * @param principal Interfaz principal de la aplicación Pintura
     * @param x Altura de la imagen
     * @param y Ancho de la imagen
     */
    public PintarPulso( InterfazPinturaPuntos principal, int x, int y )
    {
        this.principal = principal;
        this.x = x;
        this.y = y;
    }
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Acción
     */
    public void mouseDragged( MouseEvent e )
    {

        int x1 = e.getX( ) - ( principal.getAncho( ) - x ) / 2;
        int y1 = e.getY( ) - ( principal.getAlto( ) - y ) / 2;
        if( ( x1 < x && x1 >= 0 ) && ( y1 < y && y1 >= 0 ) )
        {
            principal.agregarNuevoPunto( x1, y1 );
        }

    }

    public void mouseMoved( MouseEvent e )
    {

    }

    /**
     * Asignar altura de la imagen
     * 
     * @param x Altura
     */
    public void setX( int x )
    {
        this.x = x;
    }

    /**
     * Asignar ancho de la imagen
     * 
     * @param x Altura
     */
    public void setY( int y )
    {
        this.y = y;
    }

}
