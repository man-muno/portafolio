/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: [[NOMBRE_COMPLETO]]
 * Autor: [[AUTOR]] - [[FECHA]]
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package [[PAQUETE]].[[DIRECTORIO_INTERFAZ]];

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
        //Crea un icono con la imagen
        ImageIcon icono = new ImageIcon( "data/imagenes/titulo.png" );
        //Establece el tama�o del panel de acuerdo al tama�o de la imagen
        setSize( icono.getIconHeight( ), icono.getIconWidth( ) );
        // La agrega a la etiqueta
        imagen = new JLabel( icono );
        //Agrega la etiqueta al panel
        add( imagen );

        setBackground( Color.WHITE );
        setBorder( new LineBorder( Color.GRAY ) );
    }
}