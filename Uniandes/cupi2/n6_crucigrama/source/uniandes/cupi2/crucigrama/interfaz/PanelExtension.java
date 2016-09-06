/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelExtension.java,v 1.1 2007/03/22 14:05:58 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_crucigrama
 * Autor: Manuel Mu�oz - 05-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.crucigrama.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando Opci�n 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opci�n 2
     */
    private static final String OPCION_2 = "OPCION_2";
    
    /**
     * Opci�n que indica que el usuario quiere cargar un nuevo crucigrama
     */
    private static final String CARGAR = "CARGAR";
    
    /**
     * Opci�n que indica que el usuario quiere validar el crucigrama
     */
    private static final String VALIDAR = "VALIDAR";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCrucigrama principal;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /**
     * Bot�n Opci�n 1
     */
    private JButton btnOpcion1;

    /**
     * Bot�n Opci�n 2
     */
    private JButton btnOpcion2;
    
    /**
     * Bot�n de cargar un crucigrama
     */
    private JButton btnCargar;
    
    /**
     * Bot�n de validar un crucigrama
     */
    private JButton btnValidar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
    public PanelExtension( InterfazCrucigrama ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 4 ) );

        btnCargar = new JButton("Cargar");
        btnCargar.setActionCommand( CARGAR);
        btnCargar.addActionListener( this );
        add(btnCargar);
        
        btnValidar = new JButton("Validar");
        btnValidar.setActionCommand( VALIDAR);
        btnValidar.addActionListener( this );
        add(btnValidar);
        
        //Bot�n opci�n 1
        btnOpcion1 = new JButton("Opci�n 1");
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add(btnOpcion1);
        
        //Bot�n opci�n 2
        btnOpcion2 = new JButton("Opci�n 2");
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add(btnOpcion2);
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if(OPCION_1.equals( e.getActionCommand() ))
        {
            principal.reqFuncOpcion1();
        }
        else if(OPCION_2.equals( e.getActionCommand() ))
        {
            principal.reqFuncOpcion2();
        }
        else if(CARGAR.equals( e.getActionCommand() ))
        {
            principal.cargarJuego( );
        }
        else if(VALIDAR.equals( e.getActionCommand() ))
        {
            principal.validarJuego();
        }
    }

}
