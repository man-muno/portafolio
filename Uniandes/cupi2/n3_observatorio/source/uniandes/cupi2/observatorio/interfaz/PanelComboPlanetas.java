package uniandes.cupi2.observatorio.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * Panel donde se muestra un combo con los planetas.
 */
public class PanelComboPlanetas extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que identifica que el usuario quiere aceptar
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Constante que identifica que el usuario quiere cancelar
     */
    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Combo que contiene a los planetas
     */
    private JComboBox comboPlanetas;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Dialogo que contiene este panel
     */
    private DialogoComboPlanetas dialogo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros del panel.
     * @param nDialogo Instancia del dialogo que contiene este panel
     * @param nombresPlanetas Arreglo que contiene los nombres de los planetas.
     */
    public PanelComboPlanetas( DialogoComboPlanetas nDialogo, String[] nombresPlanetas )
    {
        dialogo = nDialogo;
        comboPlanetas = new JComboBox( nombresPlanetas );
        setLayout( new GridLayout( 2, 1 ) );
        JButton btnAceptar = new JButton( "Aceptar" );
        btnAceptar.addActionListener( this );
        btnAceptar.setActionCommand( ACEPTAR );
        JButton btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );

        add( comboPlanetas );

        JPanel botones = new JPanel( );
        botones.add( btnAceptar );
        botones.add( btnCancelar );
        add( botones );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( ACEPTAR ) )
        {
            String planeta = ( String )comboPlanetas.getSelectedItem( );
            dialogo.consultarPlanetasPorInclinacion( planeta );
            dialogo.dispose( );
        }
        else if( comando.equals( CANCELAR ) )
        {
            dialogo.dispose( );
        }
    }

}
