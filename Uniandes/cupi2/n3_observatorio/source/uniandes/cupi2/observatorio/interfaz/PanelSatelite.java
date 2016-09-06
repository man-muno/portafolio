/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelSatelite.java,v 1.3 2007/06/28 22:46:45 camil-ji Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Muñoz - 13-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.observatorio.mundo.SateliteNatural;

/**
 * Panel donde se puede crear un nuevo satélite natural o editar uno existente
 */
public class PanelSatelite extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante cuando el usuario acepta el dialogo
     */
    public static final String ACEPTAR = "ACEPTAR";

    /**
     * Constante cuando el usuario cancela el dialogo
     */
    public static final String CANCELAR = "CANCELAR";

    /**
     * Constante cuando el usuario quiere eliminar el satélite natural
     */
    public static final String ELIMINAR = "ELIMINAR";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta donde se muestra nombre
     */
    private JLabel lblNombre;

    /**
     * Campo de texto para el nombre
     */
    private JTextField txtNombre;

    /**
     * Etiqueta donde se muestra la excentricidad
     */
    private JLabel lblExcentricidad;

    /**
     * Campo de texto donde se coloca la excentricidad
     */
    private JTextField txtExcentricidad;

    /**
     * Etiqueta donde se muestra inclinación orbital
     */
    private JLabel lblInclinacion;

    /**
     * Campo de texto donde se muestra la inclinación orbital
     */
    private JTextField txtInclinacion;

    /**
     * Botón para aceptar
     */
    private JButton btnAceptar;

    /**
     * Botón para cancelar
     */
    private JButton btnCancelar;

    /**
     * Botón para eliminar el satélite natural
     */
    private JButton btnEliminar;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Dialogo que contiene al panel
     */
    private DialogoInformacionSatelite dialogo;

    /**
     * Atributo que indica si se quiere usar el dialogo para creación o edición. <br>
     * Si es null, se desea utilizar el dialogo para creación. De lo contrario, se quiere utilizar el dialogo para edición 
     */
    private SateliteNatural satelite;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros. Inicia el atributo del cuerpo celeste con el parámetro.
     * @param nDialogo Dialogo que contiene a este panel
     * @param natural Null para cuando se quiere mostrar el dialogo para creación. Diferente de null para edición.
     */
    public PanelSatelite( DialogoInformacionSatelite nDialogo, SateliteNatural natural )
    {
        dialogo = nDialogo;
        satelite = natural;

        //
        // Inicialización de los atributos de interfaz
        lblNombre = new JLabel( "Nombre:" );
        txtNombre = new JTextField( );
        lblExcentricidad = new JLabel( "Excentricidad:" );
        txtExcentricidad = new JTextField( );
        lblInclinacion = new JLabel( "Inclinación Orbital    " );
        txtInclinacion = new JTextField( );

        if( satelite == null )
            btnAceptar = new JButton( "Aceptar" );
        else
            btnAceptar = new JButton( "Editar" );
        btnAceptar.addActionListener( this );
        btnAceptar.setActionCommand( ACEPTAR );
        if( satelite != null )
        {
            btnEliminar = new JButton( "Eliminar" );
            btnEliminar.addActionListener( this );
            btnEliminar.setActionCommand( ELIMINAR );
        }
        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );

        //
        // Se modifican las opciones dependiendo del uso que se le quiere dar al panel
        if( satelite != null )
        {
            setLayout( new GridLayout( 4, 3 ) );
            add( lblNombre );
            add( new JLabel( ) );
            add( txtNombre );
            add( lblExcentricidad );
            add( new JLabel( ) );
            add( txtExcentricidad );
            add( lblInclinacion );
            add( new JLabel( ) );
            add( txtInclinacion );
            add( btnAceptar );
            add( btnEliminar );
            add( btnCancelar );
        }
        else
        {
            setLayout( new GridLayout( 4, 2 ) );
            add( lblNombre );
            add( txtNombre );
            add( lblExcentricidad );
            add( txtExcentricidad );
            add( lblInclinacion );
            add( txtInclinacion );
            add( btnAceptar );
            add( btnCancelar );
        }
        
        establecerCamposEditables( );
    }

    /**
     * Tiene en cuenta si existe un cuerpo celeste válido, y en tal caso coloca los campos que pueden ser editados del campo
     */
    private void establecerCamposEditables( )
    {
        if( satelite != null )
        {
            txtNombre.setEditable( false );
            txtNombre.setText( satelite.obtenerNombre( ) );
            txtExcentricidad.setText( "" + satelite.obtenerExcentricidad( ) );
            txtInclinacion.setText( "" + satelite.obtenerInclinacion( ) );
        }
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
            String nombre = txtNombre.getText( );
            String strExcentricidad = txtExcentricidad.getText( );
            String strInclinacion = txtInclinacion.getText( );

            boolean todoOk = true;

            if( nombre == null || nombre.length( ) == 0 )
            {
                todoOk = false;
                JOptionPane.showMessageDialog( this, "Debe ingresar un nombre valido", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if( strExcentricidad == null || strExcentricidad.length( ) == 0 )
            {
                todoOk = false;
                JOptionPane.showMessageDialog( this, "Debe ingresar una excentricidad valida", "Error", JOptionPane.ERROR_MESSAGE );
            }
            else if( strInclinacion == null || strInclinacion.length( ) == 0 )
            {
                todoOk = false;
                JOptionPane.showMessageDialog( this, "Debe ingresar una inclinación valida", "Error", JOptionPane.ERROR_MESSAGE );
            }

            double excentricidad = 0;
            double inclinacion = 0;
            try
            {
                excentricidad = Double.parseDouble( strExcentricidad );
                inclinacion = Double.parseDouble( strInclinacion );
            }
            catch( NumberFormatException e1 )
            {
                todoOk = false;
            }

            if( todoOk )
            {
                //
                // Los datos están correctos y dependiendo del uso del panel, se ejecuta la acción
                if( satelite == null )
                    dialogo.agregarSateliteNatural( nombre, excentricidad, inclinacion );
                else
                    dialogo.editarSateliteNatural( nombre, excentricidad, inclinacion );
                dialogo.dispose( );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar datos válidos", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( comando.equals( CANCELAR ) )
        {
            dialogo.dispose( );
        }
        else if( comando.equals( ELIMINAR ) )
        {
            dialogo.eliminarSateliteNatural( satelite.obtenerNombre( ) );
            dialogo.dispose( );
        }
    }
}
