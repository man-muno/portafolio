/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoReubicarPrisionero.java,v 1.4 2006/11/26 22:13:00 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Daniel Romero - 10-Nov-2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.prision.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Dialogo para pedir los datos de reubicaci�n de un prisionero
 */
public class DialogoReubicarPrisionero extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------
    /**
     * Panel para pedir la informaci�n para la reubicaci�n
     */
    private PanelReubicarPrisionero panelReubicarPrisionero;

    /**
     * Interfaz principal de la aplicaci�n
     */
    private InterfazPrision principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del dialogo
     * @param ventanaPrincipal Ventana principal de la aplicaci�n
     */
    public DialogoReubicarPrisionero( InterfazPrision ventanaPrincipal )
    {
        super( ventanaPrincipal, true );
        principal = ventanaPrincipal;
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setSize( 220, 90 );
        setTitle( "Reubicaci�n de Prisionero" );        
        setResizable( false );
        setLayout( new BorderLayout( ) );

        panelReubicarPrisionero = new PanelReubicarPrisionero( principal.obtenerSectores( ), this );
        add( panelReubicarPrisionero, BorderLayout.CENTER );
        pack( );
        centrar();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    
    /**
     * Centra el di�logo en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

    /**
     * Reubica el prisionero con el n�mero dado por el usuario en el sector especificado
     */
    public void reubicar( )
    {
        String numero = panelReubicarPrisionero.darNumeroPrisionero( );
        String sector = panelReubicarPrisionero.darSectorSeleccionado( );

        if( numero == null || numero.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "El n�mero del prisionero es inv�lido.", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try
            {
                int numeroInt = Integer.parseInt( numero );
                principal.reubicar( numeroInt, sector );
                dispose( );
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "El n�mero del prisionero es inv�lido.", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    /**
     * Cierra el di�logo
     */
    public void cancelar( )
    {
        dispose( );
    }

}
