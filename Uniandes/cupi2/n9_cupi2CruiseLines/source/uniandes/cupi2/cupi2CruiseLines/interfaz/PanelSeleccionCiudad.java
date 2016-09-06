/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelSeleccionCiudad.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupi2CruiseLines
 * Autor: Manuel Muñoz - 13-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupi2CruiseLines.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import uniandes.cupi2.cupi2CruiseLines.mundo.Ciudad;

/**
 * Es el panel usado para seleccionar una ciudad destino en la ventana principal
 */
public class PanelSeleccionCiudad extends JPanel implements MouseListener
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ciudad a ser pintada
     */
    private Ciudad ciudadActual;

    /**
     * Es la imagen del mundo que se muestra
     */
    private BufferedImage imagenMundo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param principal Es una referencia a la ventana principal - principal
     */
    public PanelSeleccionCiudad( )
    {
        setMinimumSize( new Dimension( 354, 210 ) );
        setPreferredSize( new Dimension( 354, 210 ) );

        try
        {
            imagenMundo = ImageIO.read( new File( "./data/imagenes/mapaPeque.jpg" ) );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }

        addMouseListener( this );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este método se encarga de actualizar el dibujo del mundo
     * @param g Es la superficie del panel sobre la que se debe dibujar
     */
    protected void paintComponent( Graphics g )
    {
        super.paintComponent( g );

        if( imagenMundo != null )
        {
            // Calcular la posición de la imagen
            int posX = ( getWidth( ) - imagenMundo.getWidth( ) ) / 2;

            // Copiar la imagen al panel
            Graphics2D g2d = ( Graphics2D )g;
            g2d.drawImage( imagenMundo, posX, 5, Color.BLACK, null );
        }
    }

    /**
     * Actualiza la imagen del mundo con una de las ciudades por las que pasa el crucero
     * @param ciudadSeleccionada Ciudad que se quiere mostrar en el mapa. Diferente de null
     */
    public void actualizarImagen( Ciudad ciudadSeleccionada )
    {
        ciudadActual = ciudadSeleccionada;
        try
        {
            // Cargar la imagen del mundo
            imagenMundo = ImageIO.read( new File( "./data/imagenes/mapaPeque.jpg" ) );

            Graphics2D g = imagenMundo.createGraphics( );
            g.setColor( Color.CYAN );

            // Dibujar la ciudad base
            if( ciudadActual != null )
            {
                int baseX = ( int ) ( ciudadActual.darCoordenadaX( ) * imagenMundo.getWidth( ) );
                int baseY = ( int ) ( ciudadActual.darCoordenadaY( ) * imagenMundo.getHeight( ) );

                g.setColor( Color.WHITE );
                g.fillOval( baseX - 2, baseY - 2, 5, 5 );
                g.setColor( Color.YELLOW );
            }
            else
            {

            }
            repaint( );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Retorna la ciudad que se esta mostrando.
     * @return Diferente de null.
     */
    public Ciudad obtenerCiudad( )
    {
        return ciudadActual;
    }

    /**
     * Este es el método que se llama cuando se hace click sobre el panel
     * @param evento Es el evento del click sobre el panel
     */
    public void mouseClicked( MouseEvent evento )
    {
    }

    /**
     * Este método no se implementará
     * @param e evento
     */
    public void mousePressed( MouseEvent e )
    {
    }

    /**
     * Este método no se implementará
     * @param e evento
     */
    public void mouseReleased( MouseEvent e )
    {
    }

    /**
     * Este método no se implementará
     * @param e evento
     */
    public void mouseEntered( MouseEvent e )
    {
    }

    /**
     * Este método no se implementará
     * @param e evento
     */
    public void mouseExited( MouseEvent e )
    {
    }
}