/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Muñoz - Nov 7, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import uniandes.cupi2.adivinaCual.mundo.Pregunta;

/**
 * Panel donde se muestran las opciones de los animales del programa
 */
public class PanelAnimales extends JPanel
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que indica la cantidad de columnas que se va a mostrar
     */
    private static final int NUM_COLS = 5;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros de la clase.
     * @param animales Lista de los animales de la aplicación. Contiene objetos de tipo Pregunta. Diferente de null
     */
    public PanelAnimales( List animales )
    {

        setPreferredSize( ( new Dimension( 252, 800 ) ) );
        if( animales.size( ) % NUM_COLS == 0 )
        {
            setLayout( new GridLayout( animales.size( ) / 5, NUM_COLS ) );
        }
        else
        {
            setLayout( new GridLayout( animales.size( ) / 5 + 1, NUM_COLS ) );
        }
        for( int i = 0; i < animales.size( ); i++ )
        {
            String ruta = ( ( Pregunta )animales.get( i ) ).darRuta( );
            String nombre = ( ( Pregunta )animales.get( i ) ).darNombreAnimal( );
            JLabel etiqueta = new JLabel( );
            Border borde = BorderFactory.createLineBorder( Color.BLACK );
            etiqueta.setBorder( borde );
            etiqueta.setToolTipText( nombre );
            etiqueta.setVerticalAlignment( JLabel.CENTER );
            etiqueta.setHorizontalAlignment( JLabel.CENTER );
            if( !ruta.equals( "" ) )
            {
                etiqueta.setIcon( new ImageIcon( ruta ) );
            }
            else
            {
                etiqueta.setText( nombre );
            }
            add( etiqueta );
        }
    }

}
