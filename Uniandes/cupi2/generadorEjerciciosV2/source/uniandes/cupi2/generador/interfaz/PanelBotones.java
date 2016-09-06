/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 
 * Ejercicio: generadorEjerciciosV2
 * Autor: Pablo Barvo - Sep 2, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.generador.interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel donde se encuentra el bot�n de generar
 */
public class PanelBotones extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando generar ejercicio
     */
    private static final String GENERAR = "GENERAR";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal
     */
    private InterfazGenerador padre;

    //-----------------------------------------------------------------
    // Atributos de Interfaz
    //-----------------------------------------------------------------

    /**
     * Bot�n Generar
     */
    private JButton btnGenerar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor que recibe la ventana principal
     * @param elPadre Interfaz principal
     */
    public PanelBotones( InterfazGenerador elPadre )
    {
        super( );
        padre = elPadre;
        FlowLayout layout = new FlowLayout( FlowLayout.RIGHT );
        layout.setHgap( 20 );
        layout.setVgap( 20 );
        setLayout( layout );

        //Boton de generar
        btnGenerar = new JButton( "Generar" );
        btnGenerar.setActionCommand( PanelBotones.GENERAR );
        btnGenerar.setPreferredSize( new Dimension( 150, 30 ) );
        btnGenerar.addActionListener( this );

        add( btnGenerar );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Maneja los eventos de los botones
     * @param e Evento que gener� la acci�n
     */
    public void actionPerformed( ActionEvent e )
    {
        if( PanelBotones.GENERAR.equals( e.getActionCommand( ) ) )
        {
            padre.generarEjercicio( );
        }
    }
}
