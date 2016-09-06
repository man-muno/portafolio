package uniandes.cupi2.tiendaMascotas.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.tiendaMascotas.mundo.Mascota;

/**
 * Panel del dialogo donde se seleccionan las mascotas
 */
public class PanelSeleccionarMascota extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Acción de aceptar
     */
    public static final String ACEPTAR = "ACEPTAR";

    /**
     * Acción de cancelar
     */
    public static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta donde se muestra "Sexo"
     */
    private JLabel lblSexo;

    /**
     * Etiqueta donde se muestra "Edad"
     */
    private JLabel lblEdad;

    /**
     * Etiqueta donde se muestra "Precio"
     */
    private JLabel lblPrecio;

    /**
     * Combo donde se puede seleccionar el sexo
     */
    private JComboBox comboSexo;

    /**
     * Cuadro de texto donde se coloca el precio máximo por el cual se quiere buscar
     */
    private JTextField txtPrecio;

    /**
     * Cuadro de texto donde se coloca la edad máxima de la mascota que se quiere buscar
     */
    private JTextField txtEdad;

    /**
     * Botón para aceptar
     */
    private JButton btnAceptar;

    /**
     * Botón para cancelar
     */
    private JButton btnCancelar;

    /**
     * Ventana principal de la aplicación
     */
    private DialogoSeleccionarMascota dialogo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye y coloca los elementos gráficos en el panel
     * @param nDialogo Dialogo que contiene este panel. Diferente de null
     */
    public PanelSeleccionarMascota( DialogoSeleccionarMascota nDialogo )
    {
        dialogo = nDialogo;
        lblSexo = new JLabel( "Sexo: " );
        lblEdad = new JLabel( "Edad (max): " );
        lblPrecio = new JLabel( "Precio (max): " );
        comboSexo = new JComboBox( );
        txtPrecio = new JTextField( );
        txtEdad = new JTextField( );
        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.addActionListener( this );
        btnAceptar.setActionCommand( ACEPTAR );
        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );

        comboSexo.addItem( Mascota.SEXO_HEMBRA );
        comboSexo.addItem( Mascota.SEXO_MACHO );
        setLayout( new GridLayout( 4, 2 ) );
        add( lblSexo );
        add( comboSexo );
        add( lblEdad );
        add( txtEdad );
        add( lblPrecio );
        add( txtPrecio );
        add( btnAceptar );
        add( btnCancelar );

    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String accion = e.getActionCommand( );
        if( accion.equals( ACEPTAR ) )
        {
            try
            {
                String strEdad = txtEdad.getText( );
                int edad = Integer.parseInt( strEdad );
                String sexo = ( String )comboSexo.getSelectedItem( );
                if( edad < 0 )
                {
                    JOptionPane.showMessageDialog( this, "Debe ingresar una edad válida", "Error", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    String strPrecio = txtPrecio.getText( );
                    int precio = Integer.parseInt( strPrecio );
                    if( precio <= 0 )
                    {
                        JOptionPane.showMessageDialog( this, "Debe ingresar un precio válido", "Error", JOptionPane.ERROR_MESSAGE );
                    }
                    else
                    {
                        dialogo.aceptar( sexo, edad, precio );
                    }
                }
            }
            catch( NumberFormatException e1 )
            {
                JOptionPane.showMessageDialog( this, "Ha ocurrido un error con los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( accion.equals( CANCELAR ) )
        {
            dialogo.cancelar( );
        }
    }

}
