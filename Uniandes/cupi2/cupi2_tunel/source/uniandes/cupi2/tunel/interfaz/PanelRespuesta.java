/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupi2_tunel
 * Autor: Manuel Mu�oz - Nov 20, 2006
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
 * Panel donde se muestra la informaci�n que env�a el servidor HTTP
 */
public class PanelRespuesta extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
    
    private JTextArea areaRespuesta;
    
    private JScrollPane scroll;
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Constructor por defecto del panel
     */
    public PanelRespuesta()
    {
        Border borde = BorderFactory.createTitledBorder( "Respuesta Recibida" );
        
        setLayout( new BorderLayout() );
        areaRespuesta = new JTextArea();
        areaRespuesta.setEditable(false);
        scroll = new JScrollPane(areaRespuesta);
        scroll.setBorder(borde);
        add(scroll, BorderLayout.CENTER);
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    public void actualizarInfo( String string )
    {
        areaRespuesta.setText( string );
    }
}
