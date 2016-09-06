/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelAgregarPregunta.java,v 1.5 2007/04/13 12:36:33 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Muñoz - Nov 9, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel para agregar una nueva pregunta a la aplicación
 */
public class PanelAgregarPregunta extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que identifica que el usuario desea agregar una nueva pregunta
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Constante que identifica que el usuario desea cancelar
     */
    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------
    private JLabel lblPregunta;

    private JTextField txtPregunta;

    private JLabel lblAnimal;

    private JTextField txtAnimal;

    private JButton btnAceptar;

    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia del dialogo que contiene este panel
     */
    private DialogoAgregarPregunta padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros. Inicializa los componentes gráficos y los coloca.
     * @param cual Instancia del dialogo que contiene este panel.
     * @param nombreAnimal Nombre del animal que el programa adivinó. Diferente de null
     */
    public PanelAgregarPregunta( DialogoAgregarPregunta cual, String nombreAnimal )
    {
        padre = cual;
        setLayout( new GridBagLayout( ) );
        setPreferredSize( new Dimension( 477, 130 ) );

        // Construir e inicializar las etiquetas
        GridBagConstraints gbcE = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        lblAnimal = new JLabel( "En qué Animal pensó Ud?" );
        lblAnimal.setHorizontalAlignment( JLabel.RIGHT );
        add( lblAnimal, gbcE );

        gbcE.gridy = 1;
        lblPregunta = new JLabel( "Qué tiene el animal: " + nombreAnimal + ", que no tiene este?" );
        add( lblPregunta, gbcE );

        // Construir e inicializar los campos
        GridBagConstraints gbcC = new GridBagConstraints( 1, 0, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        txtAnimal = new JTextField( );
        add( txtAnimal, gbcC );

        gbcC.gridy = 1;
        txtPregunta = new JTextField( );
        add( txtPregunta, gbcC );

        // Construir e inicializar los botones
        JPanel panelBotones = new JPanel( );

        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.setActionCommand( ACEPTAR );
        btnAceptar.addActionListener( this );
        panelBotones.add( btnAceptar );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        panelBotones.add( btnCancelar );

        GridBagConstraints gbcB = new GridBagConstraints( 0, 5, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        add( panelBotones, gbcB );
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
        if( comando.equals( ACEPTAR ) )
        {
            String pregunta = txtPregunta.getText( );
            String animal = txtAnimal.getText( );
            boolean todoOk = true;
            if( pregunta.trim( ).length( ) == 0 )
            {
                todoOk = false;
                JOptionPane.showMessageDialog( this, "Debe ingresar una nueva pregunta.", "Error", JOptionPane.ERROR_MESSAGE );
            }
            if( animal.trim( ).length( ) == 0 )
            {
                todoOk = false;
                JOptionPane.showMessageDialog( this, "Debe ingresar un animal.", "Error", JOptionPane.ERROR_MESSAGE );
            }
            if( todoOk )
            {
                padre.agregarPregunta( pregunta + "?", animal );
                padre.dispose( );
            }
        }
        else if( comando.equals( CANCELAR ) )
        {
            padre.dispose( );
        }

    }
}
