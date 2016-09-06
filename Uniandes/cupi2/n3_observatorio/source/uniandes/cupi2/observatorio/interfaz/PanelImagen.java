/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelImagen.java,v 1.1 2007/02/27 16:47:44 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cup-e-mart
 * Autor: Manuel Mu�oz - Oct 19, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.interfaz;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Clase donde se coloca la imagen encabezado
 */
public class PanelImagen extends JPanel
{

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * M�todo constructor por defecto. Coloca la imagen del encabezado de la aplicaci�n.
     */
    public PanelImagen( )
    {
        JLabel imagen = new JLabel( );
        ImageIcon icono = new ImageIcon( "data/titulo.png" );
        setSize( icono.getIconHeight( ), icono.getIconWidth( ) );
        // La agrega a la etiqueta
        imagen = new JLabel( "" );
        imagen.setIcon( icono );
        add( imagen );

        setBackground( Color.WHITE );
        imagen.setBorder( new LineBorder( Color.GRAY ) );
    }
}