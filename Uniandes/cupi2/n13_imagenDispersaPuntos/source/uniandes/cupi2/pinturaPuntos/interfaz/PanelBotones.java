/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBotones.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_imagenDispersaPuntos
 * Autor: Pablo Andr�s M�rquez - 03-sep-2006
 * Autor: Manuel Mu�oz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.pinturaPuntos.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel con los botones.
 */
public class PanelBotones extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que indica la acci�n de crear una nueva imagen
     */
    public static final String NUEVA_IMAGEN = "NUEVA_IMAGEN";

    /**
     * Constante que indica la acci�n de agregar un nuevo punto
     */
    public static final String NUEVO_PUNTO = "NUEVO_PUNTO";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Interfaz principal de la aplicaci�n Pintura.
     */
    private InterfazImagenDispersaPuntos principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n Crear nueva imagen
     */
    private JButton btnCrearNuevaImagen = null;

    /**
     * Bot�n agregar nuevo punto
     */
    private JButton btnAgregarPunto = null;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * 
     * @param principal Ventana principal de la aplicaci�n
     */
    public PanelBotones( InterfazImagenDispersaPuntos principal )
    {
        this.principal = principal;
        initialize( );
    }

    // -----------------------------------------------------------------
    // M�todos de Interfaz
    // -----------------------------------------------------------------

    /**
     * M�todo de inicializaci�n del panel
     */
    private void initialize( )
    {
        GridLayout gridLayout = new GridLayout( );
        gridLayout.setColumns( 1 );
        gridLayout.setHgap( 4 );
        gridLayout.setVgap( 4 );
        gridLayout.setRows( 5 );
        this.setPreferredSize( new Dimension( 90, 500 ) );
        this.setBorder( javax.swing.BorderFactory.createTitledBorder( null, "Pintura", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null ) );
        this.setLayout( gridLayout );
        this.add( getBtnNuevaImagen( ), null );
        this.add( getBtnAgregarPunto( ), null );
    }

    /**
     * Inicializaci�n del bot�n nueva imagen
     * 
     * @return Bot�n nueva imagen.
     */
    private JButton getBtnNuevaImagen( )
    {
        if( btnCrearNuevaImagen == null )
        {
            btnCrearNuevaImagen = new JButton( );
            btnCrearNuevaImagen.setIcon( new ImageIcon( "data/nuevo.png" ) );
            btnCrearNuevaImagen.setToolTipText( "Nueva Imagen" );
            btnCrearNuevaImagen.addActionListener( this );
            btnCrearNuevaImagen.setActionCommand( NUEVA_IMAGEN );
        }
        return btnCrearNuevaImagen;
    }

    /**
     * Inicializaci�n del bot�n agregar punto
     * 
     * @return Bot�n agregar punto.
     */
    private JButton getBtnAgregarPunto( )
    {
        if( btnAgregarPunto == null )
        {
            btnAgregarPunto = new JButton( );
            btnAgregarPunto.setIcon( new ImageIcon( "data/punto.png" ) );
            btnAgregarPunto.setToolTipText( "Nuevo Punto" );
            btnAgregarPunto.addActionListener( this );
            btnAgregarPunto.setActionCommand( NUEVO_PUNTO );

        }
        return btnAgregarPunto;
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( NUEVA_IMAGEN ) )
        {
            nuevaImagen( );
        }
        else if( comando.equals( NUEVO_PUNTO ) )
        {
            agregarPunto( );
        }
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Crear nueva imagen.
     */
    private void nuevaImagen( )
    {
        principal.mostrarDialogoNuevaImagen( );
    }

    /**
     * Agregar un nuevo punto a la imagen.
     */
    private void agregarPunto( )
    {
        principal.mostrarDialogoNuevoPunto( );
    }

}
