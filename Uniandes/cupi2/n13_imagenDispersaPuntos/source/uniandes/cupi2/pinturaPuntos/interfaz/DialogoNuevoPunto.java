/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoNuevoPunto.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
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
package uniandes.cupi2.pinturaPuntos.interfaz;

import javax.swing.JDialog;

/**
 * Diálogo que se utiliza para pintar un nuevo punto.
 */
public class DialogoNuevoPunto extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazImagenDispersaPuntos principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    public DialogoNuevoPunto( InterfazImagenDispersaPuntos nPrincipal, int alto, int ancho )
    {
        principal = nPrincipal;
        PanelNuevoPunto panel = new PanelNuevoPunto( this, alto, ancho );
        principal = nPrincipal;
        setSize( 350, 200 );
        setLocationRelativeTo( null );
        add( panel );

        setTitle( "Pintura - Nuevo Punto" );
        setVisible( true );
    }

    /**
     * Llama a la interfaz para que agregue un nuevo punto el las coordenadas dadas.
     * @param x Fila donde se quiere agregar el nuevo punto. Entero mayor a cero.
     * @param y Columna donde se quiere agregar el nuevo punto. Entero mayor a cero.
     */
    public void agregarNuevoPunto( int x, int y )
    {
        principal.agregarNuevoPunto( x, y );
    }
}
