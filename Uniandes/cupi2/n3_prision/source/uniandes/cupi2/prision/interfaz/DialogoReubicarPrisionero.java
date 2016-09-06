/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoReubicarPrisionero.java,v 1.4 2006/11/26 22:13:00 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
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
 * Dialogo para pedir los datos de reubicación de un prisionero
 */
public class DialogoReubicarPrisionero extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------
    /**
     * Panel para pedir la información para la reubicación
     */
    private PanelReubicarPrisionero panelReubicarPrisionero;

    /**
     * Interfaz principal de la aplicación
     */
    private InterfazPrision principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del dialogo
     * @param ventanaPrincipal Ventana principal de la aplicación
     */
    public DialogoReubicarPrisionero( InterfazPrision ventanaPrincipal )
    {
        super( ventanaPrincipal, true );
        principal = ventanaPrincipal;
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setSize( 220, 90 );
        setTitle( "Reubicación de Prisionero" );        
        setResizable( false );
        setLayout( new BorderLayout( ) );

        panelReubicarPrisionero = new PanelReubicarPrisionero( principal.obtenerSectores( ), this );
        add( panelReubicarPrisionero, BorderLayout.CENTER );
        pack( );
        centrar();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Centra el diálogo en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

    /**
     * Reubica el prisionero con el número dado por el usuario en el sector especificado
     */
    public void reubicar( )
    {
        String numero = panelReubicarPrisionero.darNumeroPrisionero( );
        String sector = panelReubicarPrisionero.darSectorSeleccionado( );

        if( numero == null || numero.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "El número del prisionero es inválido.", "Error", JOptionPane.ERROR_MESSAGE );
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
                JOptionPane.showMessageDialog( this, "El número del prisionero es inválido.", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    /**
     * Cierra el diálogo
     */
    public void cancelar( )
    {
        dispose( );
    }

}
