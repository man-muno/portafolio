package uniandes.cupi2.tiendaMascotas.interfaz;

import javax.swing.JDialog;

/**
 * Dialogo donde se muestran las opciones para seleccionar una mascota
 */
public class DialogoSeleccionarMascota extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel donde se seleccionan las mascotas de acuerdo a varios criterios
     */
    private PanelSeleccionarMascota panelSeleccionarMascota;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia de la ventana principal del programa
     */
    private InterfazTiendaMascotas principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Instancia y coloca el panel donde se seleccionan las mascotas
     * @param mascotas Instancia de la ventana principal de la aplicaci�n. Diferente de null
     */
    public DialogoSeleccionarMascota( InterfazTiendaMascotas mascotas )
    {
        principal = mascotas;
        panelSeleccionarMascota = new PanelSeleccionarMascota( this );
        setTitle( "Seleccionar Mascota" );
        setLocationRelativeTo( null );
        add( panelSeleccionarMascota );
        setResizable( false );
        pack( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que se llama cuando el usuario hace click en el bot�n cancelar
     */
    public void cancelar( )
    {
        dispose( );
    }

    /**
     * M�todo que se llama cuando el usuario hace click en el bot�n aceptar
     */
    public void aceptar( String sexo, int edad, int precio )
    {
        principal.seleccionarMascota( sexo, edad, precio );
        dispose( );
    }

}
