/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelCanvas.java,v 1.2 2007/03/05 19:18:48 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_pinturaPuntos
 * Autor: Pablo Andr�s M�rquez - 03-sep-2006
 * Autor: Manuel Mu�oz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.pinturaPuntos.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel donde est� el canvas.
 */
public class PanelCanvas extends JPanel
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Interfaz principal aplicaci�n Pintura
     */
    private InterfazPinturaPuntos principal;
    /**
     * Ancho de la imagen que se est� editando
     */
    private int ancho;

    /**
     * Alto de la imagen que se est� editando
     */
    private int alto;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------
    /**
     * Panel donde est� la imagen que se edita
     */
    private JPanel imagen;

    /**
     * Controlador para poder pintar a pulso.
     */
    private PintarPulso pintar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Construye el di�logo a partir de la ventana principal de Pintura
     * 
     * @param principal Interfaz principal Pintura
     */
    public PanelCanvas( InterfazPinturaPuntos principal )
    {
        this.principal = principal;

        setLayout( new BorderLayout( ) );
        ancho = 0;
        alto = 0;
        setBorder( new TitledBorder( "Imagen" ) );
        setPreferredSize( new Dimension( 600, 500 ) );

        imagen = new JPanel( );
        add( imagen, BorderLayout.CENTER );

        pintar = new PintarPulso( principal, 0, 0 );
        imagen.addMouseMotionListener( pintar );

    }
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Dibujar una nueva imagen en el panel de dimensiones x y y.
     * 
     * @param x Ancho de la imagen
     * @param y Alto de la imagen
     */
    public void nueva( int x, int y )
    {
        ancho = x;
        alto = y;
        Graphics g = imagen.getGraphics( );
        g.clearRect( 0, 0, ( int )imagen.getSize( ).getWidth( ), ( int )imagen.getSize( ).getHeight( ) );
        g.setColor( Color.WHITE );
        g.fillRect( ( int ) ( imagen.getSize( ).getWidth( ) - x ) / 2, ( int ) ( imagen.getSize( ).getHeight( ) - y ) / 2, x, y );
        g.setColor( Color.BLACK );
        pintar.setX( x );
        pintar.setY( y );
        // principal.setResizable(false);
    }
    /**
     * Dibujar una nueva imagen en el panel de dimensiones x y y sobre el gr�fico.
     * 
     * @param x Ancho de la imagen
     * @param y Alto de la imagen
     * @param g Gr�fica que se quiere pintar
     */
    public void nueva( int x, int y, Graphics g )
    {
        ancho = x;
        alto = y;

        g.setColor( Color.WHITE );
        g.fillRect( ( int ) ( imagen.getSize( ).getWidth( ) - x ) / 2, ( int ) ( imagen.getSize( ).getHeight( ) - y ) / 2, x, y );
        g.setColor( Color.BLACK );
        pintar.setX( x );
        pintar.setY( y );
    }

    /**
     * Crear un nuevo punto en la imagen.
     * 
     * @param x Posici�n x donde estar� el punto.
     * @param y Posici�n y donde estar� el punto.
     * @param g Gr�fica que se quiere pintar
     */

    public void nuevoPunto( int x, int y, Graphics g )
    {
        g.fillRect( ( int ) ( imagen.getSize( ).getWidth( ) - ancho ) / 2 + x, ( int ) ( imagen.getSize( ).getHeight( ) - alto ) / 2 + y, 1, 1 );
    }
    /**
     * Crear un nuevo punto en la imagen.
     * 
     * @param x Posici�n x donde estar� el punto.
     * @param y Posici�n y donde estar� el punto.
     */
    public void nuevoPunto( int x, int y )
    {
        Graphics g = imagen.getGraphics( );
        g.fillRect( ( int ) ( imagen.getSize( ).getWidth( ) - ancho ) / 2 + x, ( int ) ( imagen.getSize( ).getHeight( ) - alto ) / 2 + y, 1, 1 );
    }

    /**
     * Devuelve el ancho de la imagen que se est� editando.
     * 
     * @return Ancho de la imagen.
     */

    public int getAncho( )
    {
        return imagen.getWidth( );
    }

    /**
     * Devuelve el alto de la imagen que se est� editando.
     * 
     * @return Alto de la imagen.
     */
    public int getAlto( )
    {
        return imagen.getHeight( );

    }

    /**
     * Volver a pintar la imagen que se est� editando.
     */

    public void paint( Graphics g )
    {
        if( imagen != null )
        {
            nueva( principal.getColumnas( ), principal.getFilas( ), g );
            for( int i = 0; i < principal.getColumnas( ); i++ )
            {
                for( int j = 0; j < principal.getFilas( ); j++ )
                {
                    if( principal.isColor( j, i ) )
                    {
                        nuevoPunto( i, j, g );
                    }
                }
            }
        }
    }

}
