/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazPinturaPuntos.java,v 1.2 2007/03/05 19:18:48 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_pinturaPuntos
 * Autor: Pablo Andr�s M�rquez - 03-sep-2006
 * Autor: Manuel Mu�oz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.pinturaPuntos.interfaz;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.pinturaPuntos.mundo.IFabrica;
import uniandes.cupi2.pinturaPuntos.mundo.IImagen;
import uniandes.cupi2.pinturaPuntos.mundo.ImagenPunto;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazPinturaPuntos extends JFrame
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String NOMBRE_ARCHIVO = "data/fabricas.properties";

    /**
     * Constante que identifica la fabrica a utilizar
     */
    private static final String NOMBRE_FABRICA = "fabrica1";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Imagen que se est� editando.
     */
    private IImagen imagen;

    /**
     * Archivo de propiedades de la fabrica de imagenes
     */
    private Properties propiedades;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que se hacen las preguntas sobre la imagen.
     */
    private PanelBotonesRecorridos panelBotonesRecorridos;

    /**
     * Panel que est� ubicado en la parte inferior de la interfaz.
     */
    private JPanel panelSur;

    /**
     * Panel donde est�n los botones para la edici�n de im�genes.
     */
    private PanelBotones panelBotones;

    /**
     * Panel donde est� la imagen que se est� editando.
     */
    private PanelCanvas panelCanvas;

    /**
     * Panel donde est� la imagen encabezado de Pintura.
     */
    private PanelImagen panelImagen;

    /**
     * Panel donde est�n los dos botones de opci�n 1 y opci�n 2.
     */
    private PanelExtension panelExtension;
    /**
     * El men� que va permitir guardar y cargar la imagen.
     */
    private JMenuBar menu;
    /**
     * Men� �tem Salvar
     */
    private JMenuItem menuSalvar = null;
    /**
     * Men� �tem Cargar
     */
    private JMenuItem menuCargar = null;
    /**
     * Men� �tem Archivo
     */
    private JMenu menuArchivo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Interfaz princiapla de la aplicaci�n Pinturañ<br>
     * <b>post: </b> Una ventana principal de la aplicaci�n Pintura.
     */
    public InterfazPinturaPuntos( )
    {

        // Construye la forma
        getContentPane( ).setLayout( new BorderLayout( ) );
        setTitle( "Pintura - Edici�n Gr�fica" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        JPanel panelNorte = new JPanel( );
        panelNorte.setLayout( new BorderLayout( ) );
        getContentPane( ).add( panelNorte, BorderLayout.NORTH );

        panelImagen = new PanelImagen( );
        panelNorte.add( panelImagen, BorderLayout.CENTER );

        panelSur = new JPanel( );
        panelSur.setLayout( new BorderLayout( ) );
        getContentPane( ).add( panelSur, BorderLayout.SOUTH );

        panelExtension = new PanelExtension( this );
        panelSur.add( panelExtension, BorderLayout.SOUTH );

        panelBotonesRecorridos = new PanelBotonesRecorridos( this );
        panelSur.add( panelBotonesRecorridos, BorderLayout.CENTER );

        panelCanvas = new PanelCanvas( this );
        getContentPane( ).add( panelCanvas, BorderLayout.CENTER );

        panelBotones = new PanelBotones( this );
        getContentPane( ).add( panelBotones, BorderLayout.WEST );

        menu = new JMenuBar( );
        menuArchivo = new JMenu( "Archivo" );
        panelNorte.add( menu, BorderLayout.SOUTH );

        menu.add( menuArchivo );
        menuArchivo.add( getMenuCargar( ) );
        menuArchivo.add( getMenuSalvar( ) );

        pack( );
        setLocationRelativeTo( null );

        propiedades = new Properties( );
        try
        {
            propiedades.load( new FileInputStream( new File( NOMBRE_ARCHIVO ) ) );
        }
        catch( FileNotFoundException e )
        {
            JOptionPane.showMessageDialog( this, "Error al cargar el archivo de propiedades", "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, "Error al cargar el archivo de propiedades", "Error", JOptionPane.ERROR_MESSAGE );
        }

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Pone la imagen como la imagen que se est� editando
     * 
     * @param imagen La imagen que se va editar.
     */
    public void setImagen( IImagen imagen )
    {
        this.imagen = imagen;
    }

    /**
     * Devuelve el panel donde est�n los botones donde se hacen consultas sobre la imagen.
     * 
     * @return
     */
    public PanelBotonesRecorridos getPanelBotonesRecorridos( )
    {
        return panelBotonesRecorridos;
    }

    /**
     * Devuelve el men� para cargar una imagen.
     * @return El men� para cargar una imagen.
     */
    private JMenuItem getMenuCargar( )
    {
        if( menuCargar == null )
        {
            menuCargar = new JMenuItem( "Cargar" );
            menuCargar.addActionListener( new java.awt.event.ActionListener( )
            {
                public void actionPerformed( java.awt.event.ActionEvent e )
                {
                    cargar( );
                }

            } );
        }
        return menuCargar;
    }
    /**
     * Carga una imagen.
     */
    private void cargar( )
    {
        JFileChooser chooser = new JFileChooser( "./data" );
        int returnVal = chooser.showOpenDialog( this );
        if( returnVal == JFileChooser.APPROVE_OPTION )
        {
            String pathName = chooser.getSelectedFile( ).getPath( );
            FileInputStream f_in;
            try
            {
                f_in = new FileInputStream( pathName );
                ObjectInputStream obj_in = new ObjectInputStream( f_in );
                IImagen imagenN = ( IImagen )obj_in.readObject( );
                this.imagen = imagenN;
                panelCanvas.repaint( );
            }
            catch( FileNotFoundException e )
            {
                JOptionPane.showMessageDialog( this, "No se encuentra el archivo", "Error", JOptionPane.ERROR_MESSAGE );
            }
            catch( IOException e )
            {
                JOptionPane.showMessageDialog( this, "Error al cargar el archivo", "Error", JOptionPane.ERROR_MESSAGE );
            }
            catch( ClassNotFoundException e )
            {
                JOptionPane.showMessageDialog( this, "Error al cargar el archivo", "Error", JOptionPane.ERROR_MESSAGE );
            }

        }
        else
        {
            chooser.setVisible( false );
        }
    }
    /**
     * Devuelve el men� para salvar una imagen.
     * @return El men� para salvar una imagen.
     */
    private JMenuItem getMenuSalvar( )
    {
        if( menuSalvar == null )
        {
            menuSalvar = new JMenuItem( "Guardar" );
            menuSalvar.addActionListener( new java.awt.event.ActionListener( )
            {
                public void actionPerformed( java.awt.event.ActionEvent e )
                {
                    salvar( );
                }

            } );
        }
        return menuSalvar;
    }

    /**
     * Salva una imagen.
     */
    private void salvar( )
    {
        JFileChooser chooser = new JFileChooser( "./data" );
        int returnVal = chooser.showSaveDialog( this );
        if( returnVal == JFileChooser.APPROVE_OPTION )
        {
            FileOutputStream f_out;
            try
            {
                f_out = new FileOutputStream( chooser.getSelectedFile( ).getPath( ) );
                ObjectOutputStream obj_out = new ObjectOutputStream( f_out );
                obj_out.writeObject( imagen );
            }
            catch( FileNotFoundException e )
            {
                JOptionPane.showMessageDialog( this, "Error al salvar el archivo", "Error", JOptionPane.ERROR_MESSAGE );
            }
            catch( IOException e )
            {
                JOptionPane.showMessageDialog( this, "No se puede guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE );
            }

        }
        else
        {
            chooser.setVisible( false );
        }
    }

    public void mostrarDialogoNuevaImagen( )
    {
        new DialogoNuevaImagen( this, panelCanvas.getAlto( ), panelCanvas.getAncho( ) );
    }

    public void mostrarDialogoNuevoPunto( )
    {
        new DialogoNuevoPunto( this, getFilas( ), getColumnas( ) );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = imagen.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = imagen.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * 
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazPinturaPuntos interfaz = new InterfazPinturaPuntos( );
        interfaz.setVisible( true );
    }

    public void crearImagen( int x, int y )
    {
        IFabrica fabrica = null;
        try
        {
            fabrica = ( IFabrica )Class.forName( propiedades.getProperty( NOMBRE_FABRICA ) ).newInstance( );
            imagen = fabrica.crearImagen( x, y );
            panelCanvas.nueva( x, y );
            mostrarBotonesNuevaImagen( );
            repaint( );
        }
        catch( InstantiationException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( IllegalAccessException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( ClassNotFoundException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        catch( NullPointerException e )
        {
            JOptionPane.showMessageDialog( this, "Error al obtener la propiedad del archivo", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    public void mostrarBotonesNuevaImagen( )
    {
        getPanelBotonesRecorridos( ).mostrarBtnVerXY( );
        getPanelBotonesRecorridos( ).mostrarBtnNegrosImagen( );
        getPanelBotonesRecorridos( ).mostrarBtnNumeroFilas( );
        getPanelBotonesRecorridos( ).mostrarBtnNumColumnas( );
        getPanelBotonesRecorridos( ).mostrarBtnNumeroPixelesNegrosFilas( );
        getPanelBotonesRecorridos( ).mostrarNegrosColumnas( );
        getPanelBotonesRecorridos( ).mostrarBtnRelacion( );
        getPanelBotonesRecorridos( ).mostrarNegrosColumnas( );
    }

    public void agregarNuevoPunto( int x, int y )
    {
        ( ( ImagenPunto )imagen ).agregarPunto( y, x );
        panelCanvas.nuevoPunto( x, y );
    }

    public int getColumnas( )
    {
        return imagen == null ? 0 : imagen.darColumnas( );
    }

    public int getFilas( )
    {
        return imagen == null ? 0 : imagen.darFilas( );
    }

    public boolean isColor( int fila, int columna )
    {
        return imagen.esNegro( fila, columna );
    }

    public int darNumeroPixelesNegrosFila( int fila )
    {
        return imagen.darNumeroPixelesNegrosFila( fila );
    }

    /**
     * Muestra un dialogo que va mostrar la relaci�n entre negros y blancos
     */
    public void verRelacionNegrosBlancos( )
    {
        int x = imagen.darNumeroNegros( );
        int y = ( ( getColumnas( ) * getFilas( ) ) - imagen.darNumeroNegros( ) );

        JOptionPane.showMessageDialog( this, "La relaci�n Negros/Blancos = " + x + "/" + y, "Relaci�n", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra un dialogo que va mostrar el color de un p�xel.
     */
    public void verColorXY( )
    {
        DialogoColorXY dialogoColor = new DialogoColorXY( this );
    }

    /**
     * Muestra un dialogo que va mostrar los negros de una imagen.
     */
    public void mostrarPixelesNegrosImagen( )
    {
        JOptionPane.showMessageDialog( this, "N�mero de p�xeles negros: " + imagen.darNumeroNegros( ), "P�xeles Negros", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra un dialogo que va mostrar el n�mero de columnas.
     */
    public void mostrarNumeroColumnas( )
    {
        JOptionPane.showMessageDialog( this, "N�mero de Columnas: " + imagen.darColumnas( ), "Columnas", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra un dialogo que va mostrar el n�mero de filas.
     */
    public void mostrarNumeroFilas( )
    {
        JOptionPane.showMessageDialog( this, "N�mero de Filas: " + imagen.darFilas( ), "Filas", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra un dialogo que va mostrar el n�mero de p�xeles negros en una fila.
     */
    public void mostrarPixelesNegrosFila( )
    {
        DialogoPixelesNegrosFila dialogo = new DialogoPixelesNegrosFila( this );
    }

    /**
     * Muestra un dialogo que va mostrar el n�mero de p�xeles negros en una columna.
     */
    public void mostrarPixelesNegrosColumna( )
    {
        DialogoPixelesNegrosColumna dialogo = new DialogoPixelesNegrosColumna( this );
    }

    public int getAncho( )
    {
        return panelCanvas.getAncho( );
    }

    public int getAlto( )
    {
        return panelCanvas.getAlto( );
    }
}