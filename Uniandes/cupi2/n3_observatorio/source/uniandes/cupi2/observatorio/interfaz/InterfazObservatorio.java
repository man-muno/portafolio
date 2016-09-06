/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazObservatorio.java,v 1.5 2007/06/28 22:46:45 camil-ji Exp $
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

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.observatorio.mundo.Observatorio;
import uniandes.cupi2.observatorio.mundo.SateliteNatural;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazObservatorio extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Observatorio observatorio;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel que contiene la imagen del titulo
     */
    private PanelImagen panelImagen;

    /**
     * Panel que contiene la información de los planetas
     */
    private PanelInformacionPlanetas panelPlanetas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Descripción <br>
     * <b>post: </b> Descripción
     */
    public InterfazObservatorio( )
    {
        // Crea la clase principal
        observatorio = new Observatorio( );

        // Construye la forma
        getContentPane( ).setLayout( new BorderLayout( ) );
        setSize( 680, 586 );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( false );

        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelPlanetas = new PanelInformacionPlanetas( this );
        add( panelPlanetas, BorderLayout.CENTER );

        // Creación de los paneles aquí
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        setTitle( "Observatorio Cupi2" );
        pack( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna un arreglo con los nombres de los planetas
     * @return Arreglo que contiene en cada posición el nombre de uno de los planetas
     */
    public String[] obtenerNombresPlanetas( )
    {
        return observatorio.obtenerNombresPlanetas( );
    }

    /**
     * Muestra el dialogo para agregar un satélite natural.
     */
    public void mostrarDialogoSateliteCreacion( )
    {
        DialogoInformacionSatelite dialogo = new DialogoInformacionSatelite( this, null, null );
    }

    /**
     * Llama el observatorio para agregar un nuevo satélite natural al planeta seleccionado.
     * @param nombreSatelite Nombre del nuevo satélite natural. Entero diferente de null.
     * @param excentricidad Excentricidad del nuevo satélite natural. Real superior a 0.
     * @param inclinacion Inclinación del nuevo satélite natural. Real superior a 0.
     */
    public void agregarSateliteNatural( String nombreSatelite, double excentricidad, double inclinacion )
    {
        String nombrePlaneta = panelPlanetas.obtenerPlanetaSeleccionado( );
        if( observatorio.agregarSateliteNatural( nombrePlaneta, nombreSatelite, excentricidad, inclinacion ) )
        {
            JOptionPane.showMessageDialog( this, "Se agregó el satélite natural", "Satélite Natural Agregado", JOptionPane.INFORMATION_MESSAGE );
            panelPlanetas.llenarListaSatelitesNaturales( obtenerNombresSatelitesNaturales( panelPlanetas.obtenerPlanetaSeleccionado( ) ) );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No se pudo agregar el satélite natural, porque ya existe uno con ese nombre", "Satélite Natural No Agregado", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Método que elimina un satélite natural de un planeta.
     * @param nombrePlaneta Nombre del planeta donde se quiere eliminar el satélite. Diferente de null
     * @param nombreSatelite Nombre del satélite natural donde se quiere eliminar el satélite. Diferente de null
     */
    public void eliminarSateliteNatural( String nombrePlaneta, String nombreSatelite )
    {
        observatorio.eliminarSatelite( nombrePlaneta, nombreSatelite );
        panelPlanetas.llenarListaSatelitesNaturales( obtenerNombresSatelitesNaturales( panelPlanetas.obtenerPlanetaSeleccionado( ) ) );
    }

    /**
     * Muestra el satélite natural para ser editado.
     * @param nombrePlaneta Nombre del planeta donde se quiere eliminar el satélite. Diferente de null
     * @param nombreSatelite Nombre del satélite natural donde se quiere eliminar el satélite. Diferente de null
     */
    public void mostrarDialogoSateliteEdicion( String nombrePlaneta, String nombreSatelite )
    {
        DialogoInformacionSatelite dialogo = new DialogoInformacionSatelite( this, observatorio.obtenerSateliteNatural( nombrePlaneta, nombreSatelite ), nombrePlaneta );
    }

    /**
     * Cambia los valores de excentricidad e inclinación de un satélite natural identificado con el nombre.
     * @param nombre Nombre del satélite natural que se quiere editar. Diferente de null.
     * @param excentricidad Nuevo valor de la excentricidad. Real mayor a cero.
     * @param inclinacion Nuevo valor de la inclinación. Real mayor a cero.
     */
    public void editarSateliteNatural( String nombre, double excentricidad, double inclinacion )
    {
        observatorio.editarSateliteNatural( panelPlanetas.obtenerPlanetaSeleccionado( ), nombre, excentricidad, inclinacion );
    }

    /**
     * Muestra el dialogo para consultar los planetas dada una distancia orbital.
     */
    public void mostrarDialogoConsultarPorDistancia( )
    {
        String strPos = JOptionPane.showInputDialog( this, "Ingrese la distancia (UA): ", "Consultar planetas por distancia. ", JOptionPane.QUESTION_MESSAGE );
        if( strPos == null )
        {
            JOptionPane.showMessageDialog( this, "Debe ingresar un valor válido", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try
            {
                double distancia = Double.parseDouble( strPos );
                ArrayList planetas = observatorio.obtenerPlanetasPorDistancia( distancia );
                DialogoListaPlanetas dialogo = new DialogoListaPlanetas( planetas );
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar un valor válido", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * Muestra el dialogo para consultar los planetas dado un planeta.
     */
    public void mostrarDialogoConsultarPorInclinacion( )
    {
        DialogoComboPlanetas dialogo = new DialogoComboPlanetas( this, observatorio.obtenerNombresPlanetas( ) );
    }

    /**
     * Consulta los planetas que tienen menor inclinación que la del planeta dado.
     * @param planeta Nombre del planeta al cual se le quiere compara la inclinación.
     */
    public void consultarPlanetasPorInclinacion( String planeta )
    {
        ArrayList planetas = observatorio.obtenerPlanetasPorInclinacion( planeta );
        DialogoListaPlanetas dialogo = new DialogoListaPlanetas( planetas );
    }

    /**
     * Devuelve un vector con la lista de satélites naturales de un planeta determinado
     * @param nombrePlaneta Nombre del planeta del que se desea conocer sus satélites naturales
     * @return Arraylist con los nombre de los satélites naturales de un planeta dado
     */
    public ArrayList obtenerNombresSatelitesNaturales( String nombrePlaneta )
    {
        ArrayList nombres = new ArrayList( );
        ArrayList satelitesNaturales = observatorio.obtenerSatelitesNaturales( nombrePlaneta );
        for( int i = 0; i < satelitesNaturales.size( ); i++ )
        {
            nombres.add( ( ( SateliteNatural )satelitesNaturales.get( i ) ).obtenerNombre( ) );
        }
        return nombres;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = observatorio.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
        System.out.println( getSize( ) );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = observatorio.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {
        InterfazObservatorio interfaz = new InterfazObservatorio( );
        interfaz.setVisible( true );
    }
}