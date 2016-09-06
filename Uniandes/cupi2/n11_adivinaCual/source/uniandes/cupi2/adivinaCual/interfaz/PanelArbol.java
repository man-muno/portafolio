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

package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import uniandes.cupi2.adivinaCual.mundo.AdivinaCual;
import uniandes.cupi2.adivinaCual.mundo.Nodo;

/**
 * Es el panel donde se muestra gráficamente el árbol
 */
public class PanelArbol extends JPanel
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * El ancho en pixeles de la representación de un nodo
     */
    private static final int ANCHO = 100;

    /**
     * El alto en pixeles de la representación de un nodo
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
    private AdivinaCual adivinaCual;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus atributos
     * @param inter Es una referencia al mundo. Diferente de null.
     */
    public PanelArbol( AdivinaCual nAdivina )
    {
        setDoubleBuffered( true );
        adivinaCual = nAdivina;
        imagenArbol = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_RGB );
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
        if( adivinaCual != null )
        {
            Nodo primeraPregunta = adivinaCual.darPrimeraPregunta( );
            if( primeraPregunta != null )
            {
                imagenArbol = dibujarNodoConNodosHijos( primeraPregunta );
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
     * Este método se encarga de dibujar un nodo y a todos los que están debajo de el.
     * @param elem El nodo que debe ser dibujado
     * @return Retorna la imagen del nodo.
     */
    private BufferedImage dibujarNodoConNodosHijos( Nodo elem )
    {
        if( elem.esHoja( ) )
        {
            return dibujarNodo( elem );
        }
        else
        {
            ArrayList resultado = ( ArrayList )elem.darNodosHijos( );
            ArrayList imagenes = new ArrayList( );

            int ancho = 0;
            int alto = 0;

            for( int i = 0; i < resultado.size( ); i++ )
            {
                Nodo hijo = ( Nodo )resultado.get( i );
                BufferedImage imagenHijo = dibujarNodoConNodosHijos( hijo );
                ancho += imagenHijo.getWidth( );
                alto = Math.max( alto, imagenHijo.getHeight( ) );
                imagenes.add( imagenHijo );
            }

            alto += ALTO + BORDE_Y * 2;
            BufferedImage imagenElemento = dibujarNodo( elem );

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
     * Este método se encarga de dibujar un nodo (sin dibujar sus hijos si los tiene)
     * @param nodo El nodo a ser dibujado
     * @return Retorna una imagen donde está dibujado el nodo
     */
    private BufferedImage dibujarNodo( Nodo nodo )
    {
        // Crear la superficie para dibujar el elemento
        BufferedImage imagen = new BufferedImage( ANCHO + BORDE_X * 2, ALTO + BORDE_Y * 2, BufferedImage.TYPE_INT_RGB );
        Graphics2D g = imagen.createGraphics( );
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, ANCHO + BORDE_X * 2, ALTO + BORDE_Y * 2 );

        // Dibujar el rectángulo
        Rectangle2D rec = new Rectangle2D.Double( BORDE_X, BORDE_Y, ANCHO, ALTO );
        g.setPaint( COLOR_FONDO );
        g.fill( rec );
        g.setPaint( COLOR_BORDE );
        g.draw( rec );

        // Dibujar el texto
        String texto = "";
        if( nodo.esHoja( ) )
        {
            texto = nodo.darNombreAnimal( ).substring( 0, Math.min( 18, nodo.darNombreAnimal( ).length( ) ) );
        }
        else
        {
            texto = nodo.darDescripcion( ).substring( 0, Math.min( 18, nodo.darDescripcion( ).length( ) ) );
        }
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
