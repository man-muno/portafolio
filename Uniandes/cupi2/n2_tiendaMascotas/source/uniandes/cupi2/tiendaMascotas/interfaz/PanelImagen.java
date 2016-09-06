/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelImagen.java,v 1.3 2007/06/25 18:47:35 camil-ji Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_tiendaMascotas
 * Autor: Manuel Muñoz - 15-Sep-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.tiendaMascotas.interfaz;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel para mostrar una imagen de decoración
 */
public class PanelImagen extends JPanel

{
    // ------------------------------------------------------------
    // Constantes
    // ------------------------------------------------------------

    /**
     * Ruta a la imagen de la tienda de mascotas
     */
    private static final String IMAGEN = "data/titulo.png";

    // ------------------------------------------------------------
    // Atributos de interfaz
    // ------------------------------------------------------------

    /**
     * Etiqueta con la imagen de la tienda de mascotas
     */
    private JLabel etiquetaImagen;

    // ------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------

    /**
     * Constructor del panel de la imagen de la tienda de mascotas
     */
    public PanelImagen( )
    {

        setLayout( new BorderLayout( ) );

        // Etiqueta de Imagen
        ImageIcon icono = new ImageIcon( IMAGEN );
        etiquetaImagen = new JLabel( "" );

        etiquetaImagen.setIcon( icono );
        etiquetaImagen.setBorder( new TitledBorder( "" ) );
        add( etiquetaImagen, BorderLayout.CENTER );

    }
}
