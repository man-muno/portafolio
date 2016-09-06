/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_adivinaCual
 * Autor: Manuel Mu�oz - 27-Oct-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.adivinaCual.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.adivinaCual.mundo.ArchivoNoCargadoException;
import uniandes.cupi2.adivinaCual.mundo.Juego;
import uniandes.cupi2.adivinaCual.mundo.Pregunta;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazAdivinaCual extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Juego juego;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las opciones sobre el �rbol
     */
    private PanelOpciones panelOpciones;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel donde salen las preguntas
     */
    private PanelJuego panelJuego;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la ventana principal de la aplicaci�n. Construye y coloca los componentes gr�ficos<br>
     * <b>post: </b> Instancia al mundo. Juego es diferente de null.
     */
    public InterfazAdivinaCual( )
    {
        int res = JOptionPane.showConfirmDialog( null, "Quiere empezar con un arbol lleno?", "Escenario inicial", JOptionPane.YES_NO_OPTION );
        boolean inicioLleno = res == JOptionPane.YES_OPTION;
        // Crea la clase principal
        try
        {
            juego = new Juego( Juego.NOMBRE_ARCHIVO, inicioLleno );
        }
        catch( ArchivoNoCargadoException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }

        // Construye la forma
        getContentPane( ).setLayout( new BorderLayout( ) );
        setSize( 879, 509 );
        setResizable( false );
        setLocationRelativeTo( null );
        setTitle( "Adivina cual?" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Creaci�n de los paneles aqu�
        panelOpciones = new PanelOpciones( this );
        add( panelOpciones, BorderLayout.WEST );

        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelJuego = new PanelJuego( this );
        add( panelJuego, BorderLayout.CENTER );

        mostrarDialogo( );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa el juego y hace visible la interfaz principal
     */
    public void iniciarInterfazPrincipal( )
    {
        while( juego.darRaiz( ) == null )
        {
            String primerAnimal = JOptionPane.showInputDialog( this, "Cu�l es el primer animal?", "Iniciando el juego", JOptionPane.QUESTION_MESSAGE );
            if( primerAnimal != null )
            {
                juego.agregarPrimerAnimal( primerAnimal );
            }
        }
        panelJuego.reiniciar( );
        juego.reiniciar( );
        setVisible( true );
        panelJuego.reiniciar( );
    }

    /**
     * Muestra el dialogo de los animales y esconde la interfaz principal
     */
    public void reiniciarInterfazPrincipal( )
    {
        setVisible( false );
        panelJuego.activarBotones( );
        mostrarDialogo( );
    }

    /**
     * Muestra el dialogo de los animales
     */
    private void mostrarDialogo( )
    {
        DialogoAnimales dialogoAnimales = new DialogoAnimales( this, juego.darAnimales( ) );
        dialogoAnimales.setVisible( true );
    }

    /**
     * Retorna la pregunta actual del juego
     * @return Pregunta actual del juego. Diferente de null
     */
    public Pregunta obtenerPreguntaActual( )
    {
        return juego.darPreguntaActual( );
    }

    /**
     * Indica al juego que el usuario contesto positivamente a a la pregunta actual. Actualiza los paneles
     */
    public void seleccionarRespuestaPositiva( )
    {
        Pregunta actual = juego.darPreguntaActual( );
        if( actual.darRespuestaNegativa( ) == null )
        {
            JOptionPane.showMessageDialog( this, "Juego terminado. Su animal pensado fue: " + juego.darPreguntaActual( ).darNombreAnimal( ), "Juego Terminado", JOptionPane.INFORMATION_MESSAGE );
            reiniciarInterfazPrincipal( );
        }
        else
        {
            juego.seleccionarRespuestaPositiva( );
            panelJuego.actualizar( );
        }
    }

    /**
     * Muestra el dialogo para agregar una nueva pregunta
     */
    private void mostrarDialogoAgregarPregunta( )
    {
        DialogoAgregarPregunta dialogo = new DialogoAgregarPregunta( this, juego.darPreguntaActual( ).darNombreAnimal( ) );
    }

    /**
     * Indica al juego que el usuario contesto negativamente a a la pregunta actual. Actualiza los paneles
     */
    public void seleccionarRespuestaNegativa( )
    {
        Pregunta actual = juego.darPreguntaActual( );
        juego.seleccionarRespuestaNegativa( );
        panelJuego.actualizar( );
        if( actual.darRespuestaNegativa( ) == null )
        {
            panelJuego.desactivarBotones( );
            mostrarDialogoAgregarPregunta( );
        }
    }

    /**
     * Muestra el dialogo con la informaci�n del peso del �rbol
     */
    public void mostrarPeso( )
    {
        int peso = juego.darPeso( );
        JOptionPane.showMessageDialog( this, "El peso del �rbol es: " + peso, "Peso del �rbol", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra el dialogo con la informaci�n de la altura del �rbol
     */
    public void mostrarAltura( )
    {
        int altura = juego.darAltura( );
        JOptionPane.showMessageDialog( this, "La altura del �rbol es: " + altura, "Altura del �rbol", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra el dialogo con la informaci�n del recorrido en inorden
     */
    public void mostrarRecorridoInorden( )
    {
        DialogoRecorrido dialogo = new DialogoRecorrido( juego.darInorden( ), "Recorrido Inorden" );
        dialogo.setVisible( true );
    }

    /**
     * Muestra el dialogo con la informaci�n del recorrido en preorden
     */
    public void mostrarRecorridoPreorden( )
    {
        DialogoRecorrido dialogo = new DialogoRecorrido( juego.darPreorden( ), "Recorrido Preorden" );
        dialogo.setVisible( true );
    }

    /**
     * Muestra el dialogo con la informaci�n del recorrido en postorden
     */
    public void mostrarRecorridoPostorden( )
    {
        DialogoRecorrido dialogo = new DialogoRecorrido( juego.darPostorden( ), "Recorrido Postorden" );
        dialogo.setVisible( true );
    }

    /**
     * Llama al mundo para agregar una nueva pregunta.
     * @param pregunta Descripcion de la nueva pregunta. Diferente de null.
     * @param animal Nombre del nuevo animal, que responde la pregunta a agregar. Diferente de null.
     */
    public void agregarPregunta( String pregunta, String animal )
    {
        try
        {
            juego.agregarPregunta( pregunta, animal );
            JOptionPane.showMessageDialog( this, "Nueva pregunta agregada", "Informaci�n ", JOptionPane.INFORMATION_MESSAGE );
            reiniciarInterfazPrincipal( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
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
        String resultado = juego.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = juego.metodo2( );
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
        InterfazAdivinaCual interfaz = new InterfazAdivinaCual( );
    }

}