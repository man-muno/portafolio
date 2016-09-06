/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: DialogoAgregarCiudad.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupi2CruiseLines
 * Autor: Manuel Muñoz - 13-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupi2CruiseLines.interfaz;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

/**
 * Es el diálogo usado para agregar una ciudad
 */
public class DialogoAgregarCiudad extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal de la interfaz
     */
    private InterfazCupi2CruiseLines ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se indican los datos de la ciudad
     */
    private PanelDatosAgregarCiudad panelDatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diálogo e inicializa sus componentes
     * @param ia Es una referencia a la ventana principal de la interfaz - ia!=null
     */
    public DialogoAgregarCiudad( InterfazCupi2CruiseLines ia )
    {
        super( ia, true );
        ventanaPrincipal = ia;

        panelDatos = new PanelDatosAgregarCiudad( this );
        getContentPane( ).add( panelDatos );
        setResizable( false );
        setTitle( "Agregar Ciudad" );
        pack( );
        centrar( );
        setVisible( true );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega una ciudad a un crucero
     * @param nombre El nombre de la ciudad. Diferente de null
     * @param pais País donde se encuentra la ciudad. Diferente de null
     * @param coordX La coordenada x de la ciudad - 0<coordX<1
     * @param coordY La coordenada y de la ciudad - 0<coordY<1
     */
    public void agregarCiudad( String nombre, String pais, double coordX, double coordY )
    {
        ventanaPrincipal.agregarCiudad( nombre, pais, coordX, coordY );
    }

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
}
