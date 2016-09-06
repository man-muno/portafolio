/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazPrision.java,v 1.4 2006/11/26 22:13:00 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_prision
 * Autor: Manuel Mu�oz - 04-Sep-2006
 * Autor: Daniel Romero- Nov 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.prision.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.prision.mundo.Prision;
import uniandes.cupi2.prision.mundo.Prisionero;
import uniandes.cupi2.prision.mundo.Sector;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazPrision extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Prision prision;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel que muestra los sectores
     */
    private PanelSector panelSector;

    /**
     * panel donde se encuentra el encabezado de la aplicaci�n
     */
    private PanelImagen panelImagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por defecto de la interfaz. Se crean e inicializan todos los componentes gr�ficos y la clase principal.
     */
    public InterfazPrision( )
    {
        // Crea la clase principal
        prision = new Prision( );

        // Construye la forma
        setTitle( "Prisi�n de Cupi2" );
        setResizable( false );
        setLayout( new BorderLayout( ) );
        setSize( 710, 445 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );        

        // Creaci�n de los paneles aqu�
        panelImagen = new PanelImagen( );
        panelSector = new PanelSector( this );
        panelExtension = new PanelExtension( this );

        add( panelImagen, BorderLayout.NORTH );
        add( panelExtension, BorderLayout.SOUTH );
        add( panelSector, BorderLayout.CENTER );
        
        centrar();

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    
    /**
     * Centra la ventana en la pantalla
     */
    private void centrar( )
    {
        Dimension screen = Toolkit.getDefaultToolkit( ).getScreenSize( );
        int xEsquina = ( screen.width - getWidth( ) ) / 2;
        int yEsquina = ( screen.height - getHeight( ) ) / 2;
        setLocation( xEsquina, yEsquina );
    }

    /**
     * Retorna todos los sectores que tiene la prisi�n
     * @return Arreglo de Sectores
     */
    public Sector[] obtenerSectores( )
    {
        return prision.darSectores( );
    }

    /**
     * Retorna el sector con el nombre dado
     * @param nombre El sector con el nombre dado
     * @return Se retorn� el sector con el nombre dado o null si �ste no existe
     */
    public Sector buscarSector( String nombre )
    {
        return prision.buscarSector( nombre );
    }

    /**
     * Llama el m�todo que actualiza la informaci�n del panel de los sectores
     */
    public void mostrarSector( )
    {
        panelSector.mostrarInfoSector( );
    }

    /**
     * Mostrar el dialogo que permite ingresar un nuevo prisionero
     */
    public void mostrarDialogoIngresarPrisionero( )
    {
        DialogoIngresarPrisionero dialogo = new DialogoIngresarPrisionero( this );
        dialogo.setVisible( true );
    }

    /**
     * M�todo para agregar un prisionero a la prisi�n
     * @param nombre Nombre del prisionero. Diferente de null.
     * @param apellido Apellido del prisionero. Diferente de null.
     * @param numero N�mero que va a tener el prisionero
     * @param crimen Crimen que cometi� el prisionero. Diferente de null.
     * @param sentencia La duraci�n de la sentencia en meses.
     * @param grupoCriminal Grupo criminal al que pertenece el nuevo prisionero
     * @param nomSector Nombre del sector para el cual va el prisionero
     */
    public void ingresarPrisionero( String nombre, String apellido, int numero, String crimen, int sentencia, String grupoCriminal, String nomSector )
    {
        prision.ingresarNuevoPrisionero( nombre, apellido, numero, crimen, sentencia, grupoCriminal, nomSector );
    }

    /**
     * Revisa que en la prisi�n no exista el prisionero
     * @param numero N�mero del prisionero
     * @return True si existe un prisionero con ese n�mero, false de lo contrario.
     */
    public boolean existePrisionero( int numero )
    {
        return prision.existePrisionero( numero );
    }

    /**
     * M�todo que se encarga de darle salida a un prisionero, pregunt�ndole al usuario el n�mero del prisionero. <br>
     * <b> pre: </b> prision!=null <br>
     */
    public void darSalida( )
    {
        String strNumero = JOptionPane.showInputDialog( this, "N�mero del prisionero:", "Dar Salida Prisionero", JOptionPane.QUESTION_MESSAGE );
        if( strNumero != null && strNumero.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "El n�mero no es v�lido.", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else if (strNumero!=null)
        {
            try
            {
                int numero = Integer.parseInt( strNumero );

                if( !prision.darSalidaPrisionero( numero ) )
                {
                    JOptionPane.showMessageDialog( this, "No se encontr� el prisionero requerido", "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "El n�mero del prisionero es inv�lido.", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    /**
     * Permite que el usuario realice la b�squeda de un prisionero pidi�ndolo su n�mero de identificaci�n <br>
     * y mostr�ndole un dialogo con la informaci�n.<br>
     * <b> pre: </b> prision!=null <br>
     */
    public void buscar( )
    {
        String numeroS = JOptionPane.showInputDialog( this, "N�mero del Prisionero:", "B�squeda Prisionero", JOptionPane.QUESTION_MESSAGE );
        
        if( numeroS != null && numeroS.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Ingrese un n�mero v�lido.", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else if(numeroS!=null)
        {
            try
            {
                int numero = Integer.parseInt( numeroS );
                Prisionero prisionero = prision.buscarPrisionero( numero );
                if( prisionero == null )
                {
                    JOptionPane.showMessageDialog( this, "Ning�n prisionero tiene el n�mero especificado.", "B�squeda Prisionero", JOptionPane.INFORMATION_MESSAGE );
                }
                else
                {
                    Sector sector = prision.buscarSectorPrisionero( numero );
                    DialogoInformacionPrisionero dialogo = new DialogoInformacionPrisionero( this, prisionero, sector.darNombre( ) );
                    dialogo.setVisible( true );
                }
            }
            catch( NumberFormatException e )
            {
                JOptionPane.showMessageDialog( this, "Ingrese un n�mero v�lido.", "Error", JOptionPane.ERROR_MESSAGE );
            }

        }
    }

    /**
     * Abre la tabla con la lista de prisioneros de acuerdo al sector seleccionado en el combo. <br>
     * <b> pre: </b> prision!=null <br>
     */
    public void mostrarDialogoPrisionerosSector( )
    {
        String nombreSector = panelSector.obtenerSectorSeleccionado( );
        ArrayList prisioneros = prision.darPrisionerosSector( nombreSector );

        DialogoPrisionerosSector dialogo = new DialogoPrisionerosSector( this, prisioneros );
        dialogo.setVisible( true );

    }

    /**
     * Cambia de un sector a otro un prisionero
     * @param numero El n�mero del prisionero a reubicar
     * @param nuevoSector Nombre del sector al cual se quiere cambiar el prisionero
     */
    public void reubicar( int numero, String nuevoSector )
    {
        boolean existePrisionero = prision.existePrisionero( numero );
        boolean existeSector = prision.existeSector( nuevoSector );
        if( existePrisionero && existeSector )
        {
            boolean reubicado = prision.reubicarPrisionero( numero, nuevoSector );

            if( reubicado )
            {
                JOptionPane.showMessageDialog( this, "El prisionero fue reubicado.", "Prisi�n Cupi2", JOptionPane.INFORMATION_MESSAGE );
            }
            else
            {
                JOptionPane.showMessageDialog( this, "El prisionero no fue reubicado debido a que ya se encontraba en el sector.", "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if( !existePrisionero )
        {
            JOptionPane.showMessageDialog( this, "No existe un prisionero con n�mero " + numero + ".", "Error", JOptionPane.ERROR_MESSAGE );
        }
        else if( !existeSector )
        {
            JOptionPane.showMessageDialog( this, "No existe el sector " + nuevoSector + ".", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Muestra el dialogo que permite reubicar un prisionero
     * 
     */
    public void mostrarDialogoReubicarPrisionero( )
    {
        DialogoReubicarPrisionero dialogo = new DialogoReubicarPrisionero( this );
        dialogo.setVisible( true );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = prision.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = prision.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args Argumentos para la ejecuci�n de la aplicaci�n. En este caso no se requieren.
     */
    public static void main( String[] args )
    {

        InterfazPrision interfaz = new InterfazPrision( );
        interfaz.setVisible( true );
    }
}