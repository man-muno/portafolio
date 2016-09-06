package uniandes.cupi2.cupi2CruiseLines.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Panel que contiene los componentes gráficos para hacer las búsqueda de una ciudad
 */
public class PanelBuscarCiudad extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que indica que el usuario quiere aceptar la búsqueda
     */
    private static final String ACEPTAR = "ACEPTAR";

    /**
     * Constante que indica que el usuario quiere cancelar la búsqueda
     */
    private static final String CANCELAR = "CANCELAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta que muestra nombre
     */
    private JLabel lblNombre;

    /**
     * Cuadro de texto con el nombre de la ciudad
     */
    private JTextField txtNombre;

    /**
     * Etiqueta que muestra país
     */
    private JLabel lblPais;

    /**
     * Cuadro de texto que muestra el país de la ciudad
     */
    private JTextField txtPais;

    /**
     * Botón que indica que el usuario quiere buscar la ciudad
     */
    private JButton btnAceptar;

    /**
     * Botón para cancelar el dialogo
     */
    private JButton btnCancelar;

    /**
     * Dialogo que contiene este panel
     */
    private DialogoBuscarCiudad padre;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros.
     * @param dialogo Dialogo que contiene al padre
     */
    public PanelBuscarCiudad( DialogoBuscarCiudad dialogo )
    {
        padre = dialogo;
        setLayout( new GridBagLayout( ) );

        Border borde = BorderFactory.createTitledBorder( "Ciudad" );
        setBorder( borde );

        lblNombre = new JLabel( "Nombre: " );
        GridBagConstraints constraints = new GridBagConstraints( );
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        add( lblNombre, constraints );

        txtNombre = new JTextField( );
        constraints = new GridBagConstraints( );
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        add( txtNombre, constraints );

        lblPais = new JLabel( "País: " );
        constraints = new GridBagConstraints( );
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        add( lblPais, constraints );

        txtPais = new JTextField( );
        constraints = new GridBagConstraints( );
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        add( txtPais, constraints );

        btnAceptar = new JButton( "Aceptar" );
        btnAceptar.addActionListener( this );
        btnAceptar.setActionCommand( ACEPTAR );
        constraints = new GridBagConstraints( );
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        add( btnAceptar, constraints );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );
        constraints = new GridBagConstraints( );
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets( 5, 5, 5, 5 );
        constraints.fill = GridBagConstraints.BOTH;
        add( btnCancelar, constraints );

    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( CANCELAR ) )
        {
            padre.dispose( );
        }
        else if( comando.equals( ACEPTAR ) )
        {
            String nombre = txtNombre.getText( );
            String pais = txtPais.getText( );
            boolean todoOk = true;
            if( nombre == null || nombre.trim( ).length( ) == 0 )
            {
                todoOk = false;
                JOptionPane.showMessageDialog( this, "Debe ingresar un nombre valido", "Error", JOptionPane.ERROR_MESSAGE );
            }
            if( pais == null || pais.trim( ).length( ) == 0 )
            {
                todoOk = false;
                JOptionPane.showMessageDialog( this, "Debe ingresar un pais valido", "Error", JOptionPane.ERROR_MESSAGE );
            }
            if( todoOk )
            {
                padre.buscarCiudad( nombre, pais );
                padre.dispose( );
            }
        }
    }
}
