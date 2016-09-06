/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoNuevaImagen.java,v 1.1 2007/03/05 02:09:02 man-muno Exp $
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

import javax.swing.JDialog;

/**
 * Di�logo que se utiliza para crear una nueva imagen.
 */
public class DialogoNuevaImagen extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazPinturaPuntos principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    public DialogoNuevaImagen( InterfazPinturaPuntos nPrincipal, int nAlto, int nAncho )
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Cierra el di�logo
     */
    public void cancelar( )
    {
        dispose( );
    }

    public void crearImagen( int x, int y )
    {
        principal.crearImagen( x, y );
    }

}
