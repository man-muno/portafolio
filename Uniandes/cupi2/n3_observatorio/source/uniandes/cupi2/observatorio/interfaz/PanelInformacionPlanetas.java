/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelInformacionPlanetas.java,v 1.3 2007/06/28 22:46:45 camil-ji Exp $
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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Clase donde se definen los componentes gráficos para el panel de las operaciones sobre los planetas
 */
public class PanelInformacionPlanetas extends JPanel implements ActionListener, ListSelectionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa la instrucción que se envía de la interfaz cuando el usuario selecciona agregar un satelite
     */
    public static final String AGREGAR_SATELITE = "AGREGAR_SATELITE";

    /**
     * Constante que identifica cuando se selecciona un planeta diferente en el combo de planetas
     */
    public static final String CAMBIAR_PLANETA = "CAMBIAR_PLANETA";

    /**
     * Constante que representa la instrucción que se envía de la interfaz cuando el usuario selecciona consultar planetas por distancia
     */
    public static final String CONSULTAR_PLANETAS_POR_DISTANCIA = "CONSULTAR_PLANETAS_POR_DISTANCIA";

    /**
     * Constante que representa la instrucción que se envía de la interfaz cuando el usuario selecciona consultar planetas por inclinación
     */
    public static final String CONSULTAR_PLANETAS_POR_INCLINACION = "CONSULTAR_PLANETAS_POR_INCLINACION";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazObservatorio principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta donde se coloca la imagen de los planetas
     */
    private JLabel lblImagen;

    /**
     * Combo donde se colocan los nombres de los planetas
     */
    private JComboBox comboPlanetas;

    /**
     * Botón para agregar un nuevo satélite al planeta seleccionado
     */
    private JButton btnAgregarSatelite;

    /**
     * Botón para consultar los planetas por distancia al sol
     */
    private JButton btnConsultarPorDistancia;

    /**
     * Botón para consultar los planetas por inclinación
     */
    private JButton btnConsultarPorInclinacion;

    /**
     * Panel que contiene los botones
     */
    private JPanel panelBotones;

    /**
     * Panel donde esta el combo de planetas y la lista de los satélites
     */
    private JPanel panelCombo;

    /**
     * Lugar donde se colocara la lista de satélites naturales
     */
    private JScrollPane scrollSatelites;

    /**
     * Lista donde se muestran los satélites naturales
     */
    private JList listaSatelites;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Método constructor de la clase. Instancia y coloca los componentes gráficos del panel.
     * @param observatorio Ventana principal de la aplicación
     */
    public PanelInformacionPlanetas( InterfazObservatorio observatorio )
    {
        panelBotones = new JPanel( );
        panelCombo = new JPanel( );
        listaSatelites = new JList( );
        listaSatelites.addListSelectionListener( this );
        panelCombo.setLayout( new GridBagLayout( ) );
        panelBotones.setLayout( new GridBagLayout( ) );
        principal = observatorio;
        setLayout( new GridBagLayout( ) );
        scrollSatelites = new JScrollPane( listaSatelites );
        lblImagen = new JLabel( );
        Icon icono = new ImageIcon( );
        lblImagen.setIcon( icono );

        comboPlanetas = new JComboBox( principal.obtenerNombresPlanetas( ) );
        comboPlanetas.addActionListener( this );
        comboPlanetas.setActionCommand( CAMBIAR_PLANETA );
        comboPlanetas.setSelectedIndex( 0 );

        btnAgregarSatelite = new JButton( "Agregar Satélite Natural" );
        btnAgregarSatelite.addActionListener( this );
        btnAgregarSatelite.setActionCommand( AGREGAR_SATELITE );

        btnConsultarPorDistancia = new JButton( "Consultar Planetas Por Distancia" );
        btnConsultarPorDistancia.addActionListener( this );
        btnConsultarPorDistancia.setActionCommand( CONSULTAR_PLANETAS_POR_DISTANCIA );
        btnConsultarPorInclinacion = new JButton( "Consultar Planetas Por Inclinación" );
        btnConsultarPorInclinacion.addActionListener( this );
        btnConsultarPorInclinacion.setActionCommand( CONSULTAR_PLANETAS_POR_INCLINACION );

        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridheight = 2;
        panelCombo.add( lblImagen, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        Insets insets = new Insets( 5, 20, 5, 10 );
        constraint.insets = insets;
        panelCombo.add( comboPlanetas, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 1;
        insets = new Insets( 5, 20, 5, 10 );
        constraint.insets = insets;
        constraint.fill = GridBagConstraints.BOTH;
        panelCombo.add( scrollSatelites, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        insets = new Insets( 5, 10, 5, 10 );
        constraint.insets = insets;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.weighty = 1;
        add( panelCombo, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        insets = new Insets( 5, 2, 5, 2 );
        constraint.insets = insets;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weighty = 1;
        panelBotones.add( btnAgregarSatelite, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 0;
        insets = new Insets( 5, 2, 5, 2 );
        constraint.insets = insets;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weighty = 1;
        panelBotones.add( btnConsultarPorInclinacion, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 2;
        constraint.gridy = 0;
        insets = new Insets( 5, 2, 5, 2 );
        constraint.insets = insets;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weighty = 1;
        panelBotones.add( btnConsultarPorDistancia, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 3;
        constraint.gridy = 0;
        insets = new Insets( 5, 2, 5, 2 );
        constraint.insets = insets;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weighty = 1;
        panelBotones.add( btnConsultarPorInclinacion, constraint );

        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 1;
        add( panelBotones, constraint );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( CAMBIAR_PLANETA ) )
        {
            cambiarImagen( );
            llenarListaSatelitesNaturales( principal.obtenerNombresSatelitesNaturales( obtenerPlanetaSeleccionado( ) ) );
        }
        else if( comando.equals( AGREGAR_SATELITE ) )
        {
            principal.mostrarDialogoSateliteCreacion( );
        }
        else if( comando.equals( CONSULTAR_PLANETAS_POR_DISTANCIA ) )
        {
            principal.mostrarDialogoConsultarPorDistancia( );
        }
        else if( comando.equals( CONSULTAR_PLANETAS_POR_INCLINACION ) )
        {
            principal.mostrarDialogoConsultarPorInclinacion( );
        }
    }

    /**
     * Llena la lista de satélites naturales con la nueva información que entra como parámetro
     * @param list Lista con los satélites naturales que se deben desplegar en la interfaz
     */
    public void llenarListaSatelitesNaturales( ArrayList list )
    {
        listaSatelites.removeAll( );
        listaSatelites.setListData( list.toArray( ) );
    }

    /**
     * Cambia la imagen que se ve en pantalla, de acuerdo al planeta seleccionado.
     */
    private void cambiarImagen( )
    {
        String seleccionado = obtenerPlanetaSeleccionado( );
        Icon icono = new ImageIcon( "./data/" + seleccionado + ".jpg" );
        lblImagen.setIcon( icono );
    }

    /**
     * Retorna el nombre del planeta que esta seleccionado en el combo.
     * @return Nombre del planeta. Diferente de null.
     */
    public String obtenerPlanetaSeleccionado( )
    {
        return ( String )comboPlanetas.getSelectedItem( );
    }

    /**
     * Manejo de los eventos de la lista
     * @param e Acción que generó el evento.
     */
    public void valueChanged( ListSelectionEvent e )
    {
        if( e.getValueIsAdjusting( ) )
        {
            principal.mostrarDialogoSateliteEdicion( obtenerPlanetaSeleccionado( ), ( String )listaSatelites.getSelectedValue( ) );
        }

    }

}
