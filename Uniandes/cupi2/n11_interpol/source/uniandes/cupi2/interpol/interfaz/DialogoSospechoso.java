package uniandes.cupi2.interpol.interfaz;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;

public class DialogoSospechoso extends JDialog
{
    private PanelSospechosos panelSospechoso;

    private InterfazInterpol principal;

    public DialogoSospechoso( InterfazInterpol interpol, List sospechosos )
    {
        principal = interpol;

        setTitle( "Expedir Orden de Captura" );
        setResizable( false );
        panelSospechoso = new PanelSospechosos( this, sospechosos );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        add( panelSospechoso );
        pack( );
        centrar( );
        setVisible( true );
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

    public void generarOrdenCaptura( int i )
    {
        principal.generarOrdenCaptura( i );
    }

}
