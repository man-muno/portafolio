/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Mu�oz - Nov 7, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * Panel donde se muestran las opciones que hay sobre el �rbol
 */
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Acci�n asociada al bot�n recorrido inorden
     */
    private static final String INORDEN = "INORDEN";

    /**
     * Acci�n asociada al bot�n recorrido preorden
     */
    private static final String PREORDEN = "PREORDEN";

    /**
     * Acci�n asociada al bot�n recorrido postorden
     */
    private static final String POSTORDEN = "POSTORDEN";

    /**
     * Acci�n asociada al bot�n de obtener altura
     */
    private static final String ALTURA = "ALTURA";

    /**
     * Acci�n asociada al bot�n de obtener peso
     */
    private static final String PESO = "PESO";

    /**
     * Acci�n asociada al bot�n de reiniciar el juego
     */
    private static final String REINICIAR = "REINICIAR";

    /**
     * Comando Opci�n 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opci�n 2
     */
    private static final String OPCION_2 = "OPCION_2";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    private JButton btnInorden;
    private JButton btnPreorden;
    private JButton btnPostorden;
    private JButton btnAltura;
    private JButton btnPeso;
    private JButton btnReiniciar;
    private JButton btnOpcion1;
    private JButton btnOpcion2;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia de la ventana principal de la aplicaci�n
     */
    private InterfazAdivinaCual principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros de la clase
     * @param nPrincipal Instancia de la clase principal de la aplicaci�n. Diferente de null
     */
    public PanelOpciones( InterfazAdivinaCual nPrincipal )
    {
        principal = nPrincipal;

        Border titulo = BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder( EtchedBorder.LOWERED ), "Opciones" );
        setBorder( titulo );
        setLayout( new GridBagLayout( ) );
        GridBagConstraints constraint = null;

        btnReiniciar = new JButton( "Reiniciar" );
        btnReiniciar.setPreferredSize( new Dimension( 170, 25 ) );
        btnReiniciar.setActionCommand( REINICIAR );
        btnReiniciar.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.weightx = 1;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        add( btnReiniciar, constraint );

        btnInorden = new JButton( "Recorrido Inorden" );
        btnInorden.setPreferredSize( new Dimension( 170, 25 ) );
        btnInorden.setActionCommand( INORDEN );
        btnInorden.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.weightx = 1;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        add( btnInorden, constraint );

        btnPreorden = new JButton( "Recorrido Preorden" );
        btnPreorden.setPreferredSize( new Dimension( 170, 25 ) );
        btnPreorden.setActionCommand( PREORDEN );
        btnPreorden.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.weightx = 1;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        add( btnPreorden, constraint );

        btnPostorden = new JButton( "Recorrido Postorden" );
        btnPostorden.setPreferredSize( new Dimension( 170, 25 ) );
        btnPostorden.setActionCommand( POSTORDEN );
        btnPostorden.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.weightx = 1;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        add( btnPostorden, constraint );

        btnAltura = new JButton( "Altura" );
        btnAltura.setPreferredSize( new Dimension( 170, 25 ) );
        btnAltura.setActionCommand( ALTURA );
        btnAltura.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 4;
        constraint.weightx = 1;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        add( btnAltura, constraint );

        btnPeso = new JButton( "Peso" );
        btnPeso.setPreferredSize( new Dimension( 170, 25 ) );
        btnPeso.setActionCommand( PESO );
        btnPeso.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 5;
        constraint.weightx = 1;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        add( btnPeso, constraint );

        btnOpcion1 = new JButton( "Opci�n 1" );
        btnOpcion1.setPreferredSize( new Dimension( 170, 25 ) );
        btnOpcion1.setActionCommand( PESO );
        btnOpcion1.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 6;
        constraint.weightx = 1;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        add( btnOpcion1, constraint );

        btnOpcion2 = new JButton( "Opci�n 2" );
        btnOpcion2.setPreferredSize( new Dimension( 170, 25 ) );
        btnOpcion2.setActionCommand( PESO );
        btnOpcion2.addActionListener( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 7;
        constraint.weightx = 1;
        constraint.insets = new Insets( 5, 0, 5, 0 );
        add( btnOpcion2, constraint );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String accion = e.getActionCommand( );

        if( accion.equals( INORDEN ) )
        {
            principal.mostrarRecorridoInorden( );
        }
        else if( accion.equals( PREORDEN ) )
        {
            principal.mostrarRecorridoPreorden( );
        }
        else if( accion.equals( POSTORDEN ) )
        {
            principal.mostrarRecorridoPostorden( );
        }
        else if( accion.equals( ALTURA ) )
        {
            principal.mostrarAltura( );
        }
        else if( accion.equals( PESO ) )
        {
            principal.mostrarPeso( );
        }
        else if( accion.equals( REINICIAR ) )
        {
            principal.reiniciarInterfazPrincipal( );
        }
    }

}
