/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBotonesRecorridos.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
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

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel con los botones para los recorridos.
 */
public class PanelBotonesRecorridos extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que identifica que el usuario quiere saber la relación entre negros y blancos
     */
    public static final String RELACION_NEGRO_BLANCO = "RELACION_NEGRO_BLANCO";

    /**
     * Constante que identifica que el usuario quiere ver el color que se encuentra en una posición dada
     */
    public static final String VER_COLOR_X_Y = "VER_COLOR_X_Y";

    /**
     * Constante que identifica que el usuario quiere ver cuantos píxeles negros hay en la imagen
     */
    public static final String MOSTRAR_PIXELES_NEGROS = "MOSTRAR_PIXELES_NEGROS";

    /**
     * Constante que identifica que el usuario quiere saber la cantidad de columnas que tiene la imagen
     */
    public static final String MOSTRAR_COLUMNAS = "MOSTRAR_COLUMNAS";

    /**
     * Constante que identifica que el usuario quiere saber la cantidad de filas que tiene la imagen
     */
    public static final String MOSTRAR_FILAS = "MOSTRAR_FILAS";

    /**
     * Constante que identifica que el usuario quere ver la cantidad de píxeles dada una columna
     */
    public static final String MOSTRAR_PIXELES_COLUMNA = "MOSTRAR_PIXELES_COLUMNA";

    /**
     * Constante que identifica que el usuario quere ver la cantidad de píxeles dada una fila
     */
    public static final String MOSTRAR_PIXELES_FILA = "MOSTRAR_PIXELES_FILA";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Interfaz principal Pintura
     */
    private InterfazImagenDispersaPuntos principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------
    /**
     * Botón Ver XY
     */
    private JButton btnVerXY = null;

    /**
     * Botón Negros Imagen
     */
    private JButton btnNegrosImagen = null;

    /**
     * Botón Ver número columnas
     */
    private JButton btnNumColumnas = null;

    /**
     * Botón Ver número filas
     */
    private JButton btnNumeroFilas = null;

    /**
     * Botón Ver número píxeles negros en una fila
     */
    private JButton btnNumeroPixelesNegrosFilas = null;

    /**
     * Botón Ver relación entre negros y blancos
     */
    private JButton btnRelacionNegros = null;

    /**
     * Botón Ver número píxeles negros en una columna
     */
    private JButton btnNegrosColumna = null;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     * 
     * @param Panel de botones que se hacen preguntas sobre la imagen.
     */
    public PanelBotonesRecorridos( InterfazImagenDispersaPuntos principal )
    {
        this.principal = principal;
        initialize( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método de inicialización del panel
     */
    private void initialize( )
    {
        GridLayout gridLayout = new GridLayout( );
        gridLayout.setColumns( 2 );
        gridLayout.setHgap( 4 );
        gridLayout.setVgap( 4 );
        gridLayout.setRows( 3 );
        this.setPreferredSize( new Dimension( 75, 100 ) );
        this.setBorder( javax.swing.BorderFactory.createTitledBorder( null, "Pintura - Edición Gráfica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null ) );
        this.setLayout( gridLayout );
        this.add( getBtnVerColor( ), null );
        this.add( getBtnNumPixelesNegros( ), null );
        this.add( getBtnNumColumnas( ), null );
        this.add( getBtnNumFilas( ), null );
        this.add( getBtnNumPixelesNegroFila( ), null );
        this.add( getBtnRelacionNegros( ), null );
        this.add( getBtnNegrosColumna( ), null );
    }

    /**
     * Devuelve el botón que va dar la relación entre negros y blancos.
     * 
     * @return Botón Relación de Negros y blancos
     */
    private JButton getBtnRelacionNegros( )
    {
        if( btnRelacionNegros == null )
        {
            btnRelacionNegros = new JButton( );
            btnRelacionNegros.setText( "Relación Negros/Blancos" );
            btnRelacionNegros.setVisible( false );
            btnRelacionNegros.setActionCommand( RELACION_NEGRO_BLANCO );
            btnRelacionNegros.addActionListener( this );
        }
        return btnRelacionNegros;
    }

    /**
     * Devuelve el botón que va dar el color de un píxel en especial.
     * 
     * @return Botón Ver Color.
     */
    private JButton getBtnVerColor( )
    {
        if( btnVerXY == null )
        {
            btnVerXY = new JButton( );
            btnVerXY.setVisible( false );
            btnVerXY.setText( "Ver Color" );
            btnVerXY.addActionListener( this );
            btnVerXY.setActionCommand( VER_COLOR_X_Y );
        }
        return btnVerXY;
    }

    /**
     * Devuelve el botón que va dar el número de píxeles negros en la imagen
     * 
     * @return Botón Píxeles negros
     */
    private JButton getBtnNumPixelesNegros( )
    {
        if( btnNegrosImagen == null )
        {
            btnNegrosImagen = new JButton( );
            btnNegrosImagen.setVisible( false );
            btnNegrosImagen.setText( "Número Píxeles Negros" );
            btnNegrosImagen.setActionCommand( MOSTRAR_PIXELES_NEGROS );
            btnNegrosImagen.addActionListener( this );
        }
        return btnNegrosImagen;
    }

    /**
     * Devuelve el botón que va dar el número de Columnas.
     * 
     * @return Botón Número de columnas.
     */
    private JButton getBtnNumColumnas( )
    {
        if( btnNumColumnas == null )
        {
            btnNumColumnas = new JButton( );
            btnNumColumnas.setVisible( false );
            btnNumColumnas.setText( "Número de Columnas" );
            btnNumColumnas.addActionListener( this );
            btnNumColumnas.setActionCommand( MOSTRAR_COLUMNAS );
        }
        return btnNumColumnas;
    }

    /**
     * Devuelve el botón que va dar el número de Filas.
     * 
     * @return Botón Número de filas.
     */
    private JButton getBtnNumFilas( )
    {
        if( btnNumeroFilas == null )
        {
            btnNumeroFilas = new JButton( );
            btnNumeroFilas.setVisible( false );
            btnNumeroFilas.setText( "Número de Filas" );
            btnNumeroFilas.setActionCommand( MOSTRAR_FILAS );
            btnNumeroFilas.addActionListener( this );
        }
        return btnNumeroFilas;
    }

    /**
     * Devuelve el botón que va dar el número de píxeles negros en una fila.
     * 
     * @return Botón Número de filas.
     */
    private JButton getBtnNumPixelesNegroFila( )
    {
        if( btnNumeroPixelesNegrosFilas == null )
        {
            btnNumeroPixelesNegrosFilas = new JButton( );
            btnNumeroPixelesNegrosFilas.setVisible( false );
            btnNumeroPixelesNegrosFilas.setText( "Puntos Fila" );
            btnNumeroPixelesNegrosFilas.addActionListener( this );
            btnNumeroPixelesNegrosFilas.setActionCommand( MOSTRAR_PIXELES_FILA );
        }
        return btnNumeroPixelesNegrosFilas;
    }

    /**
     * Devuelve el botón que va dar el número de píxeles negros en una columna.
     * 
     * @return Botón Píxeles negros Columna.
     */
    private JButton getBtnNegrosColumna( )
    {
        if( btnNegrosColumna == null )
        {
            btnNegrosColumna = new JButton( );
            btnNegrosColumna.setVisible( false );
            btnNegrosColumna.setText( "Puntos Columna" );
            btnNegrosColumna.addActionListener( this );
            btnNegrosColumna.setActionCommand( MOSTRAR_PIXELES_COLUMNA );
        }
        return btnNegrosColumna;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Muestra el botón Ver XY
     * 
     */
    public void mostrarBtnVerXY( )
    {
        btnVerXY.setVisible( true );
        add( btnVerXY );
    }

    /**
     * Oculta el botón Ver XY
     * 
     */
    public void ocultarBtnVerXY( )
    {
        btnVerXY.setVisible( false );
        remove( btnVerXY );
    }

    /**
     * Muestra el botón Negros Imagen
     * 
     */
    public void mostrarBtnNegrosImagen( )
    {
        btnNegrosImagen.setVisible( true );
        add( btnNegrosImagen );
    }

    /**
     * Oculta el botón Negros Imagen
     * 
     */
    public void ocultarBtnNegrosImagen( )
    {
        btnNegrosImagen.setVisible( false );
        remove( btnNegrosImagen );
    }

    /**
     * Muestra el botón Número de columnas
     * 
     */
    public void mostrarBtnNumColumnas( )
    {
        btnNumColumnas.setVisible( true );
        add( btnNumColumnas );

    }

    /**
     * Oculta el botón Número de columnas
     * 
     */
    public void ocultarBtnNumColumnas( )
    {
        btnNumColumnas.setVisible( false );
        remove( btnNumColumnas );

    }

    /**
     * Muestra el botón Número de filas
     * 
     */
    public void mostrarBtnNumeroFilas( )
    {
        btnNumeroFilas.setVisible( true );
        add( btnNumeroFilas );
    }

    /**
     * Oculta el botón Número de filas
     * 
     */
    public void ocultarBtnNumeroFilas( )
    {
        btnNumeroFilas.setVisible( false );
        remove( btnNumeroFilas );
    }

    /**
     * Muestra el botón Número de píxeles en una fila.
     * 
     */
    public void mostrarBtnNumeroPixelesNegrosFilas( )
    {
        btnNumeroPixelesNegrosFilas.setVisible( true );
        add( btnNumeroPixelesNegrosFilas );
    }

    /**
     * Oculta el botón Número de píxeles en una fila.
     * 
     */
    public void ocultarBtnNumeroPixelesNegrosFilas( )
    {
        btnNumeroPixelesNegrosFilas.setVisible( false );
        remove( btnNumeroPixelesNegrosFilas );
    }

    /**
     * Muestra el botón Relación Blancos / Negros
     * 
     */
    public void mostrarBtnRelacion( )
    {
        btnRelacionNegros.setVisible( true );
        add( btnRelacionNegros );
    }

    /**
     * Oculta el botón Relación Blancos / Negros
     * 
     */
    public void ocultarBtnRelacion( )
    {
        btnRelacionNegros.setVisible( false );
        remove( btnRelacionNegros );
    }

    /**
     * Muestra el botón Número de píxeles negros en una imagen
     * 
     */
    public void mostrarNegrosColumnas( )
    {
        btnNegrosColumna.setVisible( true );
        add( btnNegrosColumna );
    }

    /**
     * Oculta el botón Número de píxeles negros en una imagen
     * 
     */
    public void ocultarNegrosColumnas( )
    {
        btnNegrosColumna.setVisible( false );
        remove( btnNegrosColumna );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( RELACION_NEGRO_BLANCO ) )
        {
            principal.verRelacionNegrosBlancos( );
        }
        else if( comando.equals( VER_COLOR_X_Y ) )
        {
            principal.verColorXY( );
        }
        else if( comando.equals( MOSTRAR_PIXELES_NEGROS ) )
        {
            principal.mostrarPixelesNegrosImagen( );
        }
        else if( comando.equals( MOSTRAR_FILAS ) )
        {
            principal.mostrarNumeroFilas( );
        }
        else if( comando.equals( MOSTRAR_COLUMNAS ) )
        {
            principal.mostrarNumeroColumnas( );
        }
        else if( comando.equals( MOSTRAR_PIXELES_COLUMNA ) )
        {
            principal.mostrarPixelesNegrosColumna( );
        }
        else if( comando.equals( MOSTRAR_PIXELES_FILA ) )
        {
            principal.mostrarPixelesNegrosFila( );
        }
    }

}