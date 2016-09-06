/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: cupi2_tunel
 * Autor: Manuel Muñoz - 20-Nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.tunel.interfaz;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.tunel.mundo.ArchivoNoLeidoException;
import uniandes.cupi2.tunel.mundo.Tunel;


/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazTunel extends JFrame implements Observer
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Tunel tunel;
    

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    
    private PanelSolicitud panelSolicitud;
    
    private PanelRespuesta panelRespuesta;
    
    private PanelImagen panelImagen;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor de la ventana principal <br>
     */
    public InterfazTunel()
    {

        // Construye la forma
        getContentPane().setLayout( new BorderLayout() );
        setSize( 620, 490 );
        setLocationRelativeTo(null);
        setTitle("Tunel Http");
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        //Creacion de los paneles aquí
        JPanel panel = new JPanel();
        panel.setLayout( new BorderLayout() );
        panelSolicitud = new PanelSolicitud();
        panel.add(panelSolicitud,BorderLayout.NORTH);
        panelRespuesta = new PanelRespuesta();
        panel.add(panelRespuesta,BorderLayout.CENTER);
        panelImagen = new PanelImagen();
        add(panelImagen,BorderLayout.NORTH);
        add(panel,BorderLayout.CENTER);
        setVisible( true );
        construirTunel();
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    /**
     * Actualización de la interfaz gráfica
     */
    public void update( Observable o, Object arg )
    {
        actulizarInterfaz((String[])arg);
    }

    /**
     * Método que actualiza los paneles con la información recibida
     * @param strings Arreglo de dos posiciones donde se encuentra la información a mostrar
     */
    private void actulizarInterfaz( String[] strings )
    {   
        panelSolicitud.actualizarInfo(strings[0]);
        panelRespuesta.actualizarInfo(strings[1]);
    }

    /**
     * Construye la clase tunel y crea un nuevo thread para que maneje las solicitudes
     */
    private void construirTunel( )
    {
        while(true)
        {
            try
            {
                tunel = new Tunel();
                tunel.addObserver( this );
                tunel.inicializarServidorSolicitud();
                Thread thread = new Thread(tunel);
                thread.start( );
            }
            catch( ArchivoNoLeidoException e )
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
            catch( IOException e )
            {
                continue;
            }
            
        }
    }


    //-----------------------------------------------------------------
    // Main
    //-----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {
        InterfazTunel interfaz = new InterfazTunel();
    }



}