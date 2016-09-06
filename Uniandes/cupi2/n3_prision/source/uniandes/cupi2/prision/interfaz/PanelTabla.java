/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelTabla.java,v 1.4 2006/11/26 22:05:00 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Muñoz - Sep 15, 2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Panel de visualización de prisioneros de un sector
 */
public class PanelTabla extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String CERRAR = "Cerrar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Panel en el que se encuentra el dialogo
     */
    private DialogoPrisionerosSector dialogo;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * JScrollPane de la tabla
     */
    private JScrollPane barraDesplazamiento;

    /**
     * Tabla para mostrar los prisioneros
     */
    private JTable tblPrisioneros;

    /**
     * Botón para cerrar el diálogo
     */
    private JButton btnCerrar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param dialogoP El dialogo en el que se encuentra el panel. dialogoP!=null
     */
    public PanelTabla( DialogoPrisionerosSector dialogoP )
    {
        dialogo = dialogoP;
        setLayout( new BorderLayout( ) );
        setPreferredSize( new Dimension(592, 250) );

        tblPrisioneros = new JTable( );

        barraDesplazamiento = new JScrollPane( );
        barraDesplazamiento.setViewportView( tblPrisioneros );

        btnCerrar = new JButton( "Cerrar" );
        btnCerrar.addActionListener( this );
        btnCerrar.setActionCommand( CERRAR );
        JPanel panelBotnes = new JPanel( );
        panelBotnes.add( btnCerrar );

        add( barraDesplazamiento, BorderLayout.CENTER );

        add( panelBotnes, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Establece los prisioneros a visualizar
     * @param prisioneros Los prisioneros a mostrar
     */
    public void establecerPrisioneros( ArrayList prisioneros )
    {
        tblPrisioneros.setModel( new TableModelPrisioneros( prisioneros ) );
    }

    /**
     * Responde a los eventos de los botones del panel
     * @param evento Evento generado por un botón. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( comando.equals( CERRAR ) )
        {
            dialogo.cerrar( );
        }
    }

}
