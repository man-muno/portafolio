/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelArbol.java,v 1.2 2007/04/20 12:38:40 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Muñoz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.interpol.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import uniandes.cupi2.interpol.mundo.Ciudad;
import uniandes.cupi2.interpol.mundo.Interpol;

/**
 * Es el panel donde se muestra gráficamente el árbol de las ciudades
 */
public class PanelArbol extends JPanel
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * El ancho en pixeles de la representación de una ciudad
     */
    private static final int ANCHO = 100;

    /**
     * El alto en pixeles de la representación de una ciudad
     */
    private static final int ALTO = 50;

    /**
     * El tamaño de los bordes izquierdo y derecho
     */
    private static final int BORDE_X = 5;

    /**
     * El tamaño de los bordes superior e inferior
     */
    private static final int BORDE_Y = 20;

    /**
     * El color del borde de las cajas
     */
    private static final Color COLOR_BORDE = Color.BLACK;

    /**
     * El color de las cajas no seleccionadas
     */
    private static final Color COLOR_FONDO = new Color( 255, 254, 170 );

    /**
     * El color de la caja seleccionada
     */
    private static final Color COLOR_FONDO_SELECCIONADO = new Color( 139, 219, 245 );

    /**
     * El color de las líneas que unen a los empleados
     */
    private static final Color COLOR_LINEAS = new Color( 89, 89, 89 );

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La imagen generada con el organigrama
     */
    private BufferedImage imagenArbol;

    /**
     * Es la referencia al mundo
     */
    private Interpol interpol;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus atributos
     * @param inter Es una referencia al mundo. Diferente de null.
     */
    public PanelArbol( Interpol inter )
    {
        setDoubleBuffered( true );
        interpol = inter;
        try
        {
            imagenArbol = ImageIO.read( new File( "./data/imagenes/ladron.png" ) );
        }
        catch( IOException e )
        {
            imagenArbol = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_RGB );
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este método se encarga de repintar el panel copiando la imagen generada
     * @param g La superficie del panel donde debe pintarse la imagen
     */
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        dibujarArbol( g );
    }

    /**
     * Dibuja la imagen generada sobre la superficie
     * @param g La superficie del panel donde debe pintarse la imagen
     */
    private void dibujarArbol( Graphics g )
    {
        setBackground( Color.WHITE );
        g.clearRect( 0, 0, getWidth( ), getHeight( ) );

        if( imagenArbol == null )
            actualizarImagen( );

        // Copiar la imagen generada
        if( imagenArbol.getWidth( ) > getWidth( ) )
            g.drawImage( imagenArbol, 0, 0, null );
        else
        {
            g.drawImage( imagenArbol, ( getWidth( ) - imagenArbol.getWidth( ) ) / 2, 0, null );
        }
    }

    /**
     * Actualiza la imagen generada con la información actual del mundo
     */
    public void actualizarImagen( )
    {
        if( interpol != null )
        {
            Ciudad primeraCiudad = interpol.darPrimeraCiudad( );
            if( primeraCiudad != null )
            {
                imagenArbol = dibujarCiudadConCiudadesHijas( primeraCiudad );
                setPreferredSize( new Dimension( imagenArbol.getWidth( ), imagenArbol.getHeight( ) ) );
                setSize( new Dimension( imagenArbol.getWidth( ), imagenArbol.getHeight( ) ) );
            }
            else
            {
                imagenArbol = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_RGB );
                setSize( new Dimension( getWidth( ) - 1, getHeight( ) - 1 ) );

            }
        }
    }

    /**
     * Este método se encarga de dibujar una ciudad y a todas las que están debajo de ella.
     * @param elem La ciudad que debe ser dibujada
     * @return Retorna la imagen de la ciudad.
     */
    private BufferedImage dibujarCiudadConCiudadesHijas( Ciudad elem )
    {
        if( elem.esHoja( ) )
        {
            return dibujarCiudad( elem );
        }
        else
        {
            ArrayList hijos = ( ArrayList )elem.darCiudadesHijas( );
            ArrayList imagenes = new ArrayList( );

            int ancho = 0;
            int alto = 0;

            for( int i = 0; i < hijos.size( ); i++ )
            {
                Ciudad hijo = ( Ciudad )hijos.get( i );
                BufferedImage imagenHijo = dibujarCiudadConCiudadesHijas( hijo );
                ancho += imagenHijo.getWidth( );
                alto = Math.max( alto, imagenHijo.getHeight( ) );
                imagenes.add( imagenHijo );
            }

            alto += ALTO + BORDE_Y * 2;
            BufferedImage imagenElemento = dibujarCiudad( elem );

            // Crear la imagen que va a contener al elemento y a sus hijos
            BufferedImage imagenCompleta = new BufferedImage( ancho, alto, BufferedImage.TYPE_INT_RGB );
            Graphics2D superficie = imagenCompleta.createGraphics( );
            superficie.setColor( Color.WHITE );
            superficie.fillRect( 0, 0, ancho, alto );

            // Dibujar el elemento
            int posElemento = ( ancho / 2 ) - ( ANCHO / 2 ) - BORDE_X;
            superficie.drawImage( imagenElemento, posElemento, 0, null );

            // Agregar a la imagen completa las imágenes de cada uno de los hijos
            int posXActual = 0;
            int posYActual = ALTO + BORDE_Y * 2;

            for( int i = 0; i < imagenes.size( ); i++ )
            {
                BufferedImage imagenHijo = ( BufferedImage )imagenes.get( i );
                superficie.drawImage( imagenHijo, posXActual, posYActual, null );
                int anchoHijo = imagenHijo.getWidth( );

                posXActual += anchoHijo;
            }

            posXActual = 0;
            // Dibujar las líneas
            for( int i = 0; i < imagenes.size( ); i++ )
            {
                BufferedImage imagenHijo = ( BufferedImage )imagenes.get( i );
                int anchoHijo = imagenHijo.getWidth( );
                superficie.setPaint( COLOR_LINEAS );
                superficie.drawLine( ancho / 2, ALTO + BORDE_Y, posXActual + anchoHijo / 2, posYActual + BORDE_Y );
                posXActual += anchoHijo;
            }

            return imagenCompleta;
        }

    }

    /**
     * Este método se encarga de dibujar una empleado (sin dibujar subalternos si los tiene)
     * @param ciudad La ciudad a ser dibujada
     * @return Retorna una imagen donde está dibujado la ciudad
     */
    private BufferedImage dibujarCiudad( Ciudad ciudad )
    {
        // Crear la superficie para dibujar el elemento
        BufferedImage imagen = new BufferedImage( ANCHO + BORDE_X * 2, ALTO + BORDE_Y * 2, BufferedImage.TYPE_INT_RGB );
        Graphics2D g = imagen.createGraphics( );
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, ANCHO + BORDE_X * 2, ALTO + BORDE_Y * 2 );

        // Dibujar el rectángulo
        Rectangle2D rec = new Rectangle2D.Double( BORDE_X, BORDE_Y, ANCHO, ALTO );
        if( interpol.darCaminoRespuesta( ).contains( ciudad.darNombreCiudad( ) ) )
        {
            g.setPaint( COLOR_FONDO_SELECCIONADO );
        }
        else
        {
            g.setPaint( COLOR_FONDO );
        }
        g.fill( rec );
        g.setPaint( COLOR_BORDE );
        g.draw( rec );

        // Dibujar el icono si el ladrón está en esa ciudad
        if( ciudad.estaSospechoso( ) )
            g.drawImage( imagenArbol, 65, 50, null );

        // Dibujar el texto
        String texto = ciudad.darNombreCiudad( ).substring( 0, Math.min( 18, ciudad.darNombreCiudad( ).length( ) ) );
        int centroX = Math.abs( ANCHO + BORDE_X * 2 ) / 2;
        int centroY = Math.abs( ALTO + BORDE_Y * 2 ) / 2;

        Font fuente = new Font( "Arial", Font.PLAIN, 11 );
        g.setFont( fuente );
        g.setColor( Color.BLACK );

        FontMetrics metrics = g.getFontMetrics( );
        int ancho = metrics.stringWidth( texto );
        g.drawString( texto, centroX - ancho / 2, centroY + 5 );

        return imagen;
    }

}
