/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazCupi2CruiseLines.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupi2CruiseLines
 * Autor: Manuel Mu�oz - 13-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupi2CruiseLines.interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.cupi2CruiseLines.mundo.Agencia;
import uniandes.cupi2.cupi2CruiseLines.mundo.Ciudad;
import uniandes.cupi2.cupi2CruiseLines.mundo.CiudadExisteException;
import uniandes.cupi2.cupi2CruiseLines.mundo.CiudadNoExisteException;
import uniandes.cupi2.cupi2CruiseLines.mundo.Crucero;
import uniandes.cupi2.cupi2CruiseLines.mundo.CruceroExisteException;
import uniandes.cupi2.cupi2CruiseLines.mundo.CruceroNoExisteException;
import uniandes.cupi2.cupi2CruiseLines.mundo.PersistenciaException;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazCupi2CruiseLines extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Agencia agencia;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la informaci�n de los cruceros
     */
    private PanelCruceros panelCruceros;

    /**
     * Panel con la imagen de encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel con las operaciones sobre las ciudades
     */
    private PanelBotonesCiudad panelBotonesCiudad;

    /**
     * Panel donde se muestra una ciudad
     */
    private PanelSeleccionCiudad panelSeleccionCiudad;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por defecto. Inicializa y coloca los componentes gr�ficos<br>
     */
    public InterfazCupi2CruiseLines( )
    {

        // Construye la forma
        getContentPane( ).setLayout( new GridBagLayout( ) );
        setTitle( "Cupi2 CruiseLines" );
        setSize( 729, 536 );
        setResizable( false );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        // Creaci�n de los paneles aqu�
        panelImagen = new PanelImagen( );
        GridBagConstraints constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.gridwidth = 2;
        add( panelImagen, constraint );

        panelCruceros = new PanelCruceros( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        constraint.gridheight = 2;
        add( panelCruceros, constraint );

        panelSeleccionCiudad = new PanelSeleccionCiudad( );
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        add( panelSeleccionCiudad, constraint );

        panelBotonesCiudad = new PanelBotonesCiudad( this );
        constraint = new GridBagConstraints( );
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets( 5, 5, 5, 5 );
        add( panelBotonesCiudad, constraint );

        centrar( );
        // Crea la clase principal
        try
        {
            agencia = new Agencia( Agencia.RUTA_ARCHIVO_PERSISTENCIA );
            actualizarCruceros( );
            panelBotonesCiudad.mostrarBotonesCreacionCiudad( agencia.darPuertoSalida( panelCruceros.obtenerSeleccionado( ) ) );
            panelSeleccionCiudad.actualizarImagen( agencia.darPuertoSalida( panelCruceros.obtenerSeleccionado( ) ) );
        }
        catch( PersistenciaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( CruceroNoExisteException e )
        {
        }

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Centra el di�logo en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

    /**
     * Muestra el di�logo para agregar un nuevo crucero
     */
    public void mostraDialogoAgregarCrucero( )
    {
        DialogoAgregarCrucero dialogo = new DialogoAgregarCrucero( this );
    }

    /**
     * M�todo que llama al mundo para crear un nuevo crucero
     * @param nombre Nombre del crucero. Diferente de null.
     * @param numDias N�mero de d�as de duraci�n del crucero. Entero mayor a cero.
     * @param precio Precio del crucero. Entero mayor a cero.
     * @param fecha Fecha de salida del crucero. Diferente de null.
     */
    public void agregarCrucero( String nombre, int numDias, int precio, Date fecha )
    {
        try
        {
            agencia.agregarCrucero( nombre, numDias, precio, fecha );
            actualizarCruceros( );
            panelBotonesCiudad.mostrarBotonesCreacionCrucero( );
            panelSeleccionCiudad.actualizarImagen( null );
        }
        catch( CruceroExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Actualiza la lista de los cruceros.
     */
    private void actualizarCruceros( )
    {
        panelCruceros.actualizarCruceros( agencia.darCruceros( ) );
    }

    /**
     * Muestra los detalles del crucero seleccionado
     */
    public void mostrarDialogoDetallesCrucero( )
    {
        Crucero crucero;
        try
        {
            crucero = agencia.darCrucero( panelCruceros.obtenerSeleccionado( ) );
            DialogoDetallesCrucero dialogo = new DialogoDetallesCrucero( crucero );
        }
        catch( CruceroNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * Muestra el di�logo de agregar una nueva ciudad
     */
    public void mostrarDialogoAgregarCiudad( )
    {
        DialogoAgregarCiudad dialogo = new DialogoAgregarCiudad( this );
    }

    /**
     * M�todo que llama al mundo para agregar una nueva ciudad
     * @param nombre Nombre de la nueva ciudad. Diferente de null.
     * @param pais Nombre del pa�s donde queda la ciudad. Diferente de null.
     * @param coordX Coordenada X de la ciudad en el mundo. Real mayor a cero.
     * @param coordY Coordenada Y de la ciudad en el mundo. Real mayor a cero.
     */
    public void agregarCiudad( String nombre, String pais, double coordX, double coordY )
    {
        try
        {
            agencia.agregarCiudad( panelCruceros.obtenerSeleccionado( ), nombre, pais, coordX, coordY );
            panelSeleccionCiudad.actualizarImagen( agencia.darCiudad( panelCruceros.obtenerSeleccionado( ), nombre, pais ) );
            panelBotonesCiudad.mostrarBotonesCreacionCiudad( agencia.darCiudad( panelCruceros.obtenerSeleccionado( ), nombre, pais ) );
        }
        catch( CruceroNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( CiudadNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( CiudadExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra el di�logo con los detalles de una ciudad
     */
    public void mostrarDialogoDetallesCiudad( )
    {
        DialogoDetallesCiudad dialogo = new DialogoDetallesCiudad( panelSeleccionCiudad.obtenerCiudad( ) );
    }

    /**
     * Muestra la ciudad inicial de crucero
     */
    public void mostrarCiudadInicial( )
    {
        try
        {
            panelSeleccionCiudad.actualizarImagen( agencia.darPuertoSalida( panelCruceros.obtenerSeleccionado( ) ) );
            panelBotonesCiudad.mostrarBotonesCreacionCiudad( agencia.darPuertoSalida( panelCruceros.obtenerSeleccionado( ) ) );
        }
        catch( CruceroNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra la ciudad final del crucero
     */
    public void mostrarCiudadFinal( )
    {
        try
        {
            panelSeleccionCiudad.actualizarImagen( agencia.darPuertoLlegada( panelCruceros.obtenerSeleccionado( ) ) );
            panelBotonesCiudad.mostrarBotonesCreacionCiudad( agencia.darPuertoLlegada( panelCruceros.obtenerSeleccionado( ) ) );
        }
        catch( CruceroNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra el di�logo para poder eliminar una ciudad del crucero
     */
    public void mostrarDialogoEliminarCiudad( )
    {
        try
        {
            DialogoEliminarCiudad dialogo = new DialogoEliminarCiudad( this, agencia.darCiudades( panelCruceros.obtenerSeleccionado( ) ) );
        }
        catch( CruceroNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra la siguiente ciudad a la seleccionada
     */
    public void mostrarSiguienteCiudad( )
    {
        try
        {
            Ciudad ciudad = agencia.darSiguienteCiudad( panelCruceros.obtenerSeleccionado( ), panelSeleccionCiudad.obtenerCiudad( ).darNombre( ), panelSeleccionCiudad.obtenerCiudad( ).darPais( ) );
            panelSeleccionCiudad.actualizarImagen( ciudad );
            panelBotonesCiudad.mostrarBotonesCreacionCiudad( ciudad );
        }
        catch( CruceroNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( CiudadNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra la anterior ciudad a la seleccionada
     */
    public void mostrarAnteriorCiudad( )
    {
        try
        {
            Ciudad ciudad = agencia.darAnteriorCiudad( panelCruceros.obtenerSeleccionado( ), panelSeleccionCiudad.obtenerCiudad( ).darNombre( ), panelSeleccionCiudad.obtenerCiudad( ).darPais( ) );
            panelSeleccionCiudad.actualizarImagen( ciudad );
            panelBotonesCiudad.mostrarBotonesCreacionCiudad( ciudad );
        }
        catch( CruceroNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( CiudadNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * M�todo que llama al mundo para eliminar una ciudad
     * @param nombreCiudad Nombre de la ciudad a eliminar. Diferente de null.
     * @param pais Nombre del pa�s donde se encuentra la ciudad. Diferente de null.
     */
    public void eliminarCiudad( String nombreCiudad, String pais )
    {
        try
        {
            agencia.eliminarCiudad( panelCruceros.obtenerSeleccionado( ), nombreCiudad, pais );
            panelSeleccionCiudad.actualizarImagen( agencia.darPuertoSalida( panelCruceros.obtenerSeleccionado( ) ) );
            panelBotonesCiudad.mostrarBotonesCreacionCiudad( agencia.darPuertoSalida( panelCruceros.obtenerSeleccionado( ) ) );
        }
        catch( CiudadNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( CruceroNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Genera un reporte con el estado de los cruceros
     */
    public void generarReporte( )
    {
        JFileChooser chooser = new JFileChooser( "./data" );
        int returnVal = chooser.showSaveDialog( this );
        if( returnVal == JFileChooser.APPROVE_OPTION )
        {
            String pathArchivo = chooser.getSelectedFile( ).getParent( );
            String nombreArchivo = chooser.getSelectedFile( ).getName( );
            if( !nombreArchivo.endsWith( ".txt" ) )
            {
                nombreArchivo = nombreArchivo + ".txt";
            }
            try
            {
                agencia.generarItinerario( pathArchivo, nombreArchivo );
            }
            catch( FileNotFoundException e )
            {
                JOptionPane.showMessageDialog( this, "Problemas al crear el archivo del reporte", "Error", JOptionPane.ERROR_MESSAGE );
            }
            catch( CruceroNoExisteException e )
            {
                JOptionPane.showMessageDialog( this, "Problemas al crear el archivo del reporte", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Actualiza la interfaz cuando cambia el crucero seleccionado
     */
    public void cambioCrucero( )
    {
        try
        {
            panelSeleccionCiudad.actualizarImagen( agencia.darPuertoSalida( panelCruceros.obtenerSeleccionado( ) ) );
            panelBotonesCiudad.mostrarBotonesCreacionCiudad( agencia.darPuertoSalida( panelCruceros.obtenerSeleccionado( ) ) );
        }
        catch( CruceroNoExisteException e )
        {
        }

    }

    /**
     * Este m�todo se encarga de salvar la informaci�n de la agencia, justo antes de cerrar la aplicaci�n
     */
    public void dispose( )
    {
        try
        {
            agencia.guardarCruiseLine( Agencia.RUTA_ARCHIVO_PERSISTENCIA );
            super.dispose( );
        }
        catch( Exception e )
        {
            setVisible( true );
            int respuesta = JOptionPane.showConfirmDialog( this, "Problemas guardando la informaci�n de Cup-E-Mart:\n" + e.getMessage( ) + "\n�Quiere cerrar el programa sin salvar?", "Error", JOptionPane.YES_NO_OPTION );
            if( respuesta == JOptionPane.YES_OPTION )
            {
                super.dispose( );
            }
        }
    }

    /**
     * Muestra el dialogo para buscar una ciudad
     */
    public void mostrarDialogoBuscarCiudad( )
    {
        DialogoBuscarCiudad dialogo = new DialogoBuscarCiudad( this );
    }

    /**
     * M�todo que dado el nombre de una ciudad y un pa�s informa si el crucero pasa por ella o no
     * @param nombreCiudad Nombre de la ciudad. Diferente de null.
     * @param pais Nombre del pais donde se encuentra la ciudad. Diferente de null.
     */
    public void buscarCiudad( String nombreCiudad, String pais )
    {
        try
        {
            agencia.darCiudad( panelCruceros.obtenerSeleccionado( ), nombreCiudad, pais );
            JOptionPane.showMessageDialog( this, "La ciudad si existe en el crucero", "Ciudad encontrada", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( CruceroNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( CiudadNoExisteException e )
        {
            JOptionPane.showMessageDialog( this, "La ciudad que quiere buscar no existe", "Error", JOptionPane.INFORMATION_MESSAGE );
        }

    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = agencia.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = agencia.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {
        InterfazCupi2CruiseLines interfaz = new InterfazCupi2CruiseLines( );
        interfaz.setVisible( true );
    }

}