/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelListaPlanetas.java,v 1.3 2007/06/28 22:46:45 camil-ji Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Muñoz - 13-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Panel donde se listan los planetas que cumplen con las consultas de los requerimientos R4 y R5
 */
public class PanelListaPlanetas extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para identificar cuando se quiere cerrar el dialogo que contiene al panel
     */
    private static final String CERRAR = "CERRAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Dialogo al que pertenece el panel
     */
    private DialogoListaPlanetas dialogo;

    /**
     * Lista de planetas a desplegar en el panel
     */
    private ArrayList planetas;

    /**
     * Scroll para la lista de planetas
     */
    private JScrollPane scroll;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor por parámetros.
     * @param nDialogo Dialogo que contiene este panel
     * @param nPlanetas Lista de planetas para mostrar en el panel.
     */
    public PanelListaPlanetas( DialogoListaPlanetas nDialogo, ArrayList nPlanetas )
    {
        planetas = nPlanetas;
        dialogo = nDialogo;

        setLayout( new BorderLayout( ) );

        JList listaPlanetas = new JList( planetas.toArray( ) );
        scroll = new JScrollPane( listaPlanetas );

        JButton btnCerrar = new JButton( "Cerrar" );
        btnCerrar.addActionListener( this );
        btnCerrar.setActionCommand( CERRAR );

        add( scroll, BorderLayout.CENTER );
        add( btnCerrar, BorderLayout.SOUTH );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( comando.equals( CERRAR ) )
        {
            dialogo.dispose( );
        }
    }

}
