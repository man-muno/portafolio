/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PintarPulso.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
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
package uniandes.cupi2.pinturaPuntos.interfaz;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Controlador para poder pintar a pulso.
 */
public class PintarPulso implements MouseMotionListener
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Interfaz principal
     */
    private InterfazImagenDispersaPuntos principal;

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
     * Construye un Listener que est� escuchando al mouse.
     * 
     * @param principal Interfaz principal de la aplicaci�n Pintura
     * @param x Altura de la imagen
     * @param y Ancho de la imagen
     */
    public PintarPulso( InterfazImagenDispersaPuntos principal, int x, int y )
    {
        this.principal = principal;
        this.x = x;
        this.y = y;
    }
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Acci�n cuando se arrastra el mouse
     * @param Evento del mouse
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

    /**
     * Accion cuando se mueve el mouse
     * @param Evento del mouse
     */
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
