/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelSatelite.java,v 1.3 2007/06/28 22:46:45 camil-ji Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Mu�oz - 13-Feb-2007
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
 * Panel donde se puede crear un nuevo sat�lite natural o editar uno existente
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
     * Constante cuando el usuario quiere eliminar el sat�lite natural
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
     * Etiqueta donde se muestra inclinaci�n orbital
     */
    private JLabel lblInclinacion;

    /**
     * Campo de texto donde se muestra la inclinaci�n orbital
     */
    private JTextField txtInclinacion;

    /**
     * Bot�n para aceptar
     */
    private JButton btnAceptar;

    /**
     * Bot�n para cancelar
     */
    private JButton btnCancelar;

    /**
     * Bot�n para eliminar el sat�lite natural
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
     * Atributo que indica si se quiere usar el dialogo para creaci�n o edici�n. <br>
     * Si es null, se desea utilizar el dialogo para creaci�n. De lo contrario, se quiere utilizar el dialogo para edici�n 
     */
    private SateliteNatural satelite;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros. Inicia el atributo del cuerpo celeste con el par�metro.
     * @param nDialogo Dialogo que contiene a este panel
     * @param natural Null para cuando se quiere mostrar el dialogo para creaci�n. Diferente de null para edici�n.
     */
    public PanelSatelite( DialogoInformacionSatelite nDialogo, SateliteNatural natural )
    {
        dialogo = nDialogo;
        satelite = natural;

        //
        // Inicializaci�n de los atributos de interfaz
        lblNombre = new JLabel( "Nombre:" );
        txtNombre = new JTextField( );
        lblExcentricidad = new JLabel( "Excentricidad:" );
        txtExcentricidad = new JTextField( );
        lblInclinacion = new JLabel( "Inclinaci�n Orbital    " );
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
     * Tiene en cuenta si existe un cuerpo celeste v�lido, y en tal caso coloca los campos que pueden ser editados del campo
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
     * @param e Acci�n que gener� el evento.
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
                JOptionPane.showMessageDialog( this, "Debe ingresar una inclinaci�n valida", "Error", JOptionPane.ERROR_MESSAGE );
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
                // Los datos est�n correctos y dependiendo del uso del panel, se ejecuta la acci�n
                if( satelite == null )
                    dialogo.agregarSateliteNatural( nombre, excentricidad, inclinacion );
                else
                    dialogo.editarSateliteNatural( nombre, excentricidad, inclinacion );
                dialogo.dispose( );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar datos v�lidos", "Error", JOptionPane.ERROR_MESSAGE );
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
