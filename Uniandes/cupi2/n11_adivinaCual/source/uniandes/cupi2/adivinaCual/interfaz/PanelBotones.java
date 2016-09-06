/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Mu�oz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel donde se muestra el bot�n para cerrar el dialogo
 */
public class PanelBotones extends JPanel implements ActionListener
{

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
     * Acci�n asociada al bot�n cerrar
     */
    private static final String CERRAR = "CERRAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------
    private JButton btnCerrar;
    private JButton btnInorden;
    private JButton btnPreorden;
    private JButton btnPostorden;
    private JButton btnAltura;
    private JButton btnPeso;
    private DialogoMostrarArbol dialogo;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Instancia de la interfaz principal
     */
    private InterfazAdivinaCual principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros de la clase.
     * @param arbol Dialogo que contiene el panel
     * @param padre Instancia de la interfaz principal
     */
    public PanelBotones( DialogoMostrarArbol arbol, InterfazAdivinaCual padre )
    {
        dialogo = arbol;
        principal = padre;
        btnInorden = new JButton( "Inorden" );
        btnInorden.setActionCommand( INORDEN );
        btnInorden.addActionListener( this );
        btnInorden.setPreferredSize( new Dimension( 100, 25 ) );
        add( btnInorden );

        btnPreorden = new JButton( "Preorden" );
        btnPreorden.setActionCommand( PREORDEN );
        btnPreorden.addActionListener( this );
        btnPreorden.setPreferredSize( new Dimension( 100, 25 ) );
        add( btnPreorden );

        btnPostorden = new JButton( "Postorden" );
        btnPostorden.setActionCommand( POSTORDEN );
        btnPostorden.addActionListener( this );
        btnPostorden.setPreferredSize( new Dimension( 100, 25 ) );
        add( btnPostorden );

        btnAltura = new JButton( "Altura" );
        btnAltura.setActionCommand( ALTURA );
        btnAltura.addActionListener( this );
        btnAltura.setPreferredSize( new Dimension( 100, 25 ) );
        add( btnAltura );

        btnPeso = new JButton( "Peso" );
        btnPeso.setActionCommand( PESO );
        btnPeso.addActionListener( this );
        btnPeso.setPreferredSize( new Dimension( 100, 25 ) );
        add( btnPeso );

        btnCerrar = new JButton( "Cerrar" );
        btnCerrar.setActionCommand( CERRAR );
        btnCerrar.addActionListener( this );
        btnCerrar.setPreferredSize( new Dimension( 100, 25 ) );
        add( btnCerrar );
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
        String comando = e.getActionCommand( );
        if( comando.equals( CERRAR ) )
        {
            principal.reiniciarInterfazPrincipal( );
            dialogo.dispose( );
        }
        else if( comando.equals( INORDEN ) )
        {
            principal.mostrarRecorridoInorden( );
        }
        else if( comando.equals( PREORDEN ) )
        {
            principal.mostrarRecorridoPreorden( );
        }
        else if( comando.equals( POSTORDEN ) )
        {
            principal.mostrarRecorridoPostorden( );
        }
        else if( comando.equals( ALTURA ) )
        {
            principal.mostrarAltura( dialogo );
        }
        else if( comando.equals( PESO ) )
        {
            principal.mostrarPeso( dialogo );
        }
    }
}
