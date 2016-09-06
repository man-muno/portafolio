/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoNuevoPunto.java,v 1.1 2007/03/05 02:09:02 man-muno Exp $
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
 * Di�logo que se utiliza para pintar un nuevo punto.
 */
public class DialogoNuevoPunto extends JDialog
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

    public DialogoNuevoPunto( InterfazPinturaPuntos nPrincipal, int alto, int ancho )
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

    public void agregarNuevoPunto( int x, int y )
    {
        principal.agregarNuevoPunto( x, y );
    }
}
