/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoIngresarPrisionero.java,v 1.3 2006/11/26 22:05:00 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Mu�oz - Sep 13, 2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.prision.interfaz;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import uniandes.cupi2.prision.mundo.Sector;

/**
 * Dialogo para agregar un prisionero
 */
public class DialogoIngresarPrisionero extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazPrision principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con los componentes para ingresar los datos.
     */
    private PanelIngresarPrisionero panelIngresarPrisionero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del di�logo
     * @param ventana Ventana principal de la aplicaci�n. ventana != null.
     */
    public DialogoIngresarPrisionero( InterfazPrision ventana )
    {
        super( ventana, true );
        principal = ventana;
        setResizable( false );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        setTitle( "Ingresar Prisionero" );        

        panelIngresarPrisionero = new PanelIngresarPrisionero( this );

        add( panelIngresarPrisionero );

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
     * Retorna los nombres de los sectores de la prisi�n
     * @return Los nombres de los sectores de la prisi�n
     */
    public Sector[] obtenerSectores( )
    {
        return principal.obtenerSectores( );
    }

    /**
     * Ingresa el prisionero con la informaci�n especificada por el usuario
     * 
     */
    public void ingresarPrisionero( )
    {
        String nombre = panelIngresarPrisionero.darNombre( );
        String apellido = panelIngresarPrisionero.darApellido( );
        String crimen = panelIngresarPrisionero.darCrimen( );
        String grupo = panelIngresarPrisionero.darGrupoCriminal( );
        String sentencia = panelIngresarPrisionero.darTiempoSentencia( );
        String numero = panelIngresarPrisionero.darNumero( );
        String sector = panelIngresarPrisionero.darSector( );
        // Verificar datos y que no exista el prisionero
        if( nombre == null || nombre.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "El nombre no es v�lido.", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else if( apellido == null || apellido.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "El apellido no es v�lido.", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else if( numero == null || numero.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "El n�mero no es v�lido.", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else if( crimen == null || crimen.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "El crimen no es v�lido.", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else if( sentencia == null || sentencia.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "El tiempo sentencia no es v�lido.", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            // Verificar la sentencia
            boolean todoOk = true;
            int tSentencia = 0;
            try
            {
                tSentencia = Integer.parseInt( sentencia );

                // Verificar el n�mero del prisionero
                int numeroInt = 0;
                try
                {
                    numeroInt = Integer.parseInt( numero );
                    if( principal.existePrisionero( numeroInt ) )
                    {
                        todoOk = false;
                        JOptionPane.showMessageDialog( this, "Ya existe un prisionero con ese numero.", "Error", JOptionPane.ERROR_MESSAGE );
                    }

                    // Agregar el prisionero
                    if( todoOk )
                    {
                        principal.ingresarPrisionero( nombre, apellido, numeroInt, crimen, tSentencia, grupo, sector );
                        dispose( );
                    }
                }
                catch( NumberFormatException e )
                {
                    todoOk = false;
                    JOptionPane.showMessageDialog( this, "El n�mero del prisionero es inv�lido.", "Error", JOptionPane.ERROR_MESSAGE );
                }

            }
            catch( NumberFormatException e )
            {
                todoOk = false;
                JOptionPane.showMessageDialog( this, "La sentencia es inv�lida.", "Error", JOptionPane.ERROR_MESSAGE );
            }

        }
    }

    /**
     * Cierra el dialogo
     */
    public void cancelar( )
    {
        dispose( );
    }

}
