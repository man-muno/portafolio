package uniandes.cupi2.cupi2CruiseLines.interfaz;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

/**
 * Dialogo que se muestra cuando se quiere buscar una ciudad especifica
 */
public class DialogoBuscarCiudad extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se indican los datos de la ciudad a buscar
     */
    private PanelBuscarCiudad panelDatos;

    /**
     * Es la ventana principal de la aplicación
     */
    private InterfazCupi2CruiseLines principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del dialogo. Inicia y coloca el panel
     * @param lines Ventana principal de la aplicación.
     * @param ciudad Ciudad a mostrar. Diferente de null.
     */
    public DialogoBuscarCiudad( InterfazCupi2CruiseLines lines )
    {
        principal = lines;
        panelDatos = new PanelBuscarCiudad( this );
        getContentPane( ).add( panelDatos );
        // setResizable( false );
        setTitle( "Buscar Ciudad" );
        pack( );
        centrar( );
        setVisible( true );
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
     * Llama a la interfaz principal para buscar la ciudad
     * @param nombre Nombre de la ciudad a buscar. Diferente de null.
     * @param pais Nombre del país donde pertenece la ciudad. Diferente de null.
     */
    public void buscarCiudad( String nombre, String pais )
    {
        principal.buscarCiudad( nombre, pais );
    }
}
