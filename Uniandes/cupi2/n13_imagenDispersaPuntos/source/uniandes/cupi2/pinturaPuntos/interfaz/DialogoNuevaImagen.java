/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoNuevaImagen.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
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
 * Diálogo que se utiliza para crear una nueva imagen.
 */
public class DialogoNuevaImagen extends JDialog
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

    public DialogoNuevaImagen( InterfazImagenDispersaPuntos nPrincipal, int nAlto, int nAncho )
    {
        principal = nPrincipal;
        PanelNuevaImagen panelNuevaImagen = new PanelNuevaImagen( this, nAlto, nAncho );
        setSize( 310, 260 );
        setLocationRelativeTo( null );
        setResizable( false );
        setTitle( "Pintura - Nueva Imagen" );
        add( panelNuevaImagen );
        setVisible( true );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cierra el diálogo
     */
    public void cancelar( )
    {
        dispose( );
    }

    /**
     * Llama a la interfaz principal para que cree una nueva imagen
     * @param x Cantidad de filas. Entero mayor a cero
     * @param y Cantidad de columnas. Entero mayor a cero.
     */
    public void crearImagen( int x, int y )
    {
        principal.crearImagen( x, y );
    }

}
