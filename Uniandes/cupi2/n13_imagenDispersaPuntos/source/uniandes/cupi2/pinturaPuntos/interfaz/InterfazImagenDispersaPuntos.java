/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazImagenDispersaPuntos.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
 * Universidad de los Andes (Bogotá· - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_imagenDispersaPuntos
 * Autor: Pablo Andrés Márquez - 03-sep-2006
 * Autor: Manuel Muñoz - 24 - feb - 2007
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
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazImagenDispersaPuntos extends JFrame
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta del archivo donde esta definida la fábrica de la imagen
     */
    private static final String NOMBRE_ARCHIVO = "data/fabricas.properties";

    /**
     * Constante que identifica la fabrica a utilizar
     */
    private static final String NOMBRE_FABRICA = "fabrica1";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Imagen que se está editando.
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
     * Panel que está ubicado en la parte inferior de la interfaz.
     */
    private JPanel panelSur;

    /**
     * Panel donde están los botones para la edición de imágenes.
     */
    private PanelBotones panelBotones;

    /**
     * Panel donde está la imagen que se está editando.
     */
    private PanelCanvas panelCanvas;

    /**
     * Panel donde está la imagen encabezado de Pintura.
     */
    private PanelImagen panelImagen;

    /**
     * Panel donde están los dos botones de opción 1 y opción 2.
     */
    private PanelExtension panelExtension;
    /**
     * El menú que va permitir guardar y cargar la imagen.
     */
    private JMenuBar menu;
    /**
     * Menú Ítem Salvar
     */
    private JMenuItem menuSalvar = null;
    /**
     * Menú Ítem Cargar
     */
    private JMenuItem menuCargar = null;
    /**
     * Menú Ítem Archivo
     */
    private JMenu menuArchivo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Interfaz principal de la aplicación<br>
     * <b>post: </b> Una ventana principal de la aplicación Pintura.
     */
    public InterfazImagenDispersaPuntos( )
    {

        // Construye la forma
        getContentPane( ).setLayout( new BorderLayout( ) );
        setTitle( "Pintura - Edición Gráfica" );
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
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Pone la imagen como la imagen que se está editando
     * 
     * @param imagen La imagen que se va editar.
     */
    public void setImagen( IImagen imagen )
    {
        this.imagen = imagen;
    }

    /**
     * Devuelve el panel donde están los botones donde se hacen consultas sobre la imagen.
     * 
     * @return
     */
    public PanelBotonesRecorridos getPanelBotonesRecorridos( )
    {
        return panelBotonesRecorridos;
    }

    /**
     * Devuelve el menú para cargar una imagen.
     * @return El menú para cargar una imagen.
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
                panelCanvas.validate( );
                mostrarBotonesNuevaImagen( );
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
     * Devuelve el menú para salvar una imagen.
     * @return El menú para salvar una imagen.
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

    /**
     * Muestra el diálogo para crear una nueva imagen
     */
    public void mostrarDialogoNuevaImagen( )
    {
        new DialogoNuevaImagen( this, panelCanvas.getAlto( ), panelCanvas.getAncho( ) );
    }

    /**
     * Muestra el diálogo para agregar un nuevo punto
     */
    public void mostrarDialogoNuevoPunto( )
    {
        new DialogoNuevoPunto( this, getFilas( ), getColumnas( ) );
    }

    /**
     * Crea una nueva imagen sobre la cual se quiere pintar
     * @param x Cantidad de filas. Entero mayor a cero.
     * @param y Cantidad de columnas. Entero mayor a cero.
     */
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

    /**
     * Muestra los botones de las operaciones cuando hay una imagen creada.
     */
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

    /**
     * Agrega un nuevo punto en una posición dada
     * @param x Fila donde se quiere agregar el punto. Entero mayor a cero y menor que la cantidad de filas.
     * @param y Columna donde se quiere agregar el punto. Entero mayor a cero y menor que la cantidad de columnas.
     */
    public void agregarNuevoPunto( int x, int y )
    {
        ( ( ImagenPunto )imagen ).agregarPunto( y, x );
        panelCanvas.nuevoPunto( x, y );
    }

    /**
     * Devuelve la cantidad de columnas que tiene la imagen actual
     * @return Entero mayor a cero.
     */
    public int getColumnas( )
    {
        return imagen == null ? 0 : imagen.darColumnas( );
    }

    /**
     * Devuelve la cantidad de filas que tiene la imagen actual
     * @return Entero mayor a cero.
     */
    public int getFilas( )
    {
        return imagen == null ? 0 : imagen.darFilas( );
    }

    /**
     * Retorna si el color en una posición dada es negro o no.
     * @param fila Fila donde se quiere preguntar si el píxel es negro. Entero mayor a cero.
     * @param columna Columna donde se quiere preguntar si el píxel es negro. Entero mayor a cero.
     * @return True en caso que el píxel sea negro, false de lo contrario.
     */
    public boolean isColor( int fila, int columna )
    {
        return imagen.esNegro( fila, columna );
    }

    /**
     * Retorna la cantidad de píxeles negros de la fila.
     * @param fila Entero mayor a cero.
     * @return Entero mayor o igual a cero.
     */
    public int darNumeroPixelesNegrosFila( int fila )
    {
        return imagen.darNumeroPixelesNegrosFila( fila );
    }

    /**
     * Retorna la cantidad de píxeles negros de la columna.
     * @param columna Entero mayor a cero.
     * @return Entero mayor o igual a cero.
     */
    public int darNumeroPixelesNegrosColumna( int columna )
    {
        return imagen.darNumeroPixelesNegrosColumna( columna );
    }
    
    /**
     * Muestra un dialogo que va mostrar la relación entre negros y blancos
     */
    public void verRelacionNegrosBlancos( )
    {
        int x = imagen.darNumeroNegros( );
        int y = ( imagen.darNumeroBlancos( )  - imagen.darNumeroNegros( ) );

        JOptionPane.showMessageDialog( this, "La relación Negros/Blancos = " + x + "/" + y, "Relación", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra un dialogo que va mostrar el color de un píxel.
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
        JOptionPane.showMessageDialog( this, "Número de píxeles negros: " + imagen.darNumeroNegros( ), "Píxeles Negros", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra un dialogo que va mostrar el número de columnas.
     */
    public void mostrarNumeroColumnas( )
    {
        JOptionPane.showMessageDialog( this, "Número de Columnas: " + imagen.darColumnas( ), "Columnas", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra un dialogo que va mostrar el número de filas.
     */
    public void mostrarNumeroFilas( )
    {
        JOptionPane.showMessageDialog( this, "Número de Filas: " + imagen.darFilas( ), "Filas", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra un dialogo que va mostrar el número de píxeles negros en una fila.
     */
    public void mostrarPixelesNegrosFila( )
    {
        DialogoPixelesNegrosFila dialogo = new DialogoPixelesNegrosFila( this );
    }

    /**
     * Muestra un dialogo que va mostrar el número de píxeles negros en una columna.
     */
    public void mostrarPixelesNegrosColumna( )
    {
        DialogoPixelesNegrosColumna dialogo = new DialogoPixelesNegrosColumna( this );
    }

    /**
     * Retorna el ancho de la imagen actual
     * @return Entero mayor a cero.
     */
    public int getAncho( )
    {
        return panelCanvas.getAncho( );
    }

    /**
     * Retorna el alto de la imagen actual
     * @return Entero mayor a cero.
     */
    public int getAlto( )
    {
        return panelCanvas.getAlto( );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = imagen.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
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
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * 
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazImagenDispersaPuntos interfaz = new InterfazImagenDispersaPuntos( );
        interfaz.setVisible( true );
    }

}