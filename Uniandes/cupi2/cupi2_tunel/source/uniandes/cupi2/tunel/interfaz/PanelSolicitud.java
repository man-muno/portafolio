/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupi2_tunel
 * Autor: Manuel Muñoz - Nov 20, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.tunel.interfaz;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * Panel donde se muestra la información que envía el browser
 */
public class PanelSolicitud extends JPanel
{
    private JTextArea areaSolicitud;
    
    private JScrollPane scroll;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Constructor por defecto del panel
     */
    public PanelSolicitud()
    {
        Border borde = BorderFactory.createTitledBorder( "Solicitud Enviada" );
        setLayout( new BorderLayout());
        areaSolicitud = new JTextArea();
        areaSolicitud.setEditable(false);
        scroll = new JScrollPane(areaSolicitud);
        scroll.setBorder(borde);
        add(scroll, BorderLayout.CENTER);
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Método que actualiza la información mostrada en el area de texto
     */
    public void actualizarInfo( String string )
    {
        areaSolicitud.setText( string );
    }
}

