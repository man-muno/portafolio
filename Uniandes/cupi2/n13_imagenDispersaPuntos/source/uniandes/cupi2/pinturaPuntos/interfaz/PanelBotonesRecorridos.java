/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBotonesRecorridos.java,v 1.1 2007/04/23 21:03:00 man-muno Exp $
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
     * Constante que identifica que el usuario quiere saber la relaci�n entre negros y blancos
     */
    public static final String RELACION_NEGRO_BLANCO = "RELACION_NEGRO_BLANCO";

    /**
     * Constante que identifica que el usuario quiere ver el color que se encuentra en una posici�n dada
     */
    public static final String VER_COLOR_X_Y = "VER_COLOR_X_Y";

    /**
     * Constante que identifica que el usuario quiere ver cuantos p�xeles negros hay en la imagen
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
     * Constante que identifica que el usuario quere ver la cantidad de p�xeles dada una columna
     */
    public static final String MOSTRAR_PIXELES_COLUMNA = "MOSTRAR_PIXELES_COLUMNA";

    /**
     * Constante que identifica que el usuario quere ver la cantidad de p�xeles dada una fila
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
     * Bot�n Ver XY
     */
    private JButton btnVerXY = null;

    /**
     * Bot�n Negros Imagen
     */
    private JButton btnNegrosImagen = null;

    /**
     * Bot�n Ver n�mero columnas
     */
    private JButton btnNumColumnas = null;

    /**
     * Bot�n Ver n�mero filas
     */
    private JButton btnNumeroFilas = null;

    /**
     * Bot�n Ver n�mero p�xeles negros en una fila
     */
    private JButton btnNumeroPixelesNegrosFilas = null;

    /**
     * Bot�n Ver relaci�n entre negros y blancos
     */
    private JButton btnRelacionNegros = null;

    /**
     * Bot�n Ver n�mero p�xeles negros en una columna
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo de inicializaci�n del panel
     */
    private void initialize( )
    {
        GridLayout gridLayout = new GridLayout( );
        gridLayout.setColumns( 2 );
        gridLayout.setHgap( 4 );
        gridLayout.setVgap( 4 );
        gridLayout.setRows( 3 );
        this.setPreferredSize( new Dimension( 75, 100 ) );
        this.setBorder( javax.swing.BorderFactory.createTitledBorder( null, "Pintura - Edici�n Gr�fica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null ) );
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
     * Devuelve el bot�n que va dar la relaci�n entre negros y blancos.
     * 
     * @return Bot�n Relaci�n de Negros y blancos
     */
    private JButton getBtnRelacionNegros( )
    {
        if( btnRelacionNegros == null )
        {
            btnRelacionNegros = new JButton( );
            btnRelacionNegros.setText( "Relaci�n Negros/Blancos" );
            btnRelacionNegros.setVisible( false );
            btnRelacionNegros.setActionCommand( RELACION_NEGRO_BLANCO );
            btnRelacionNegros.addActionListener( this );
        }
        return btnRelacionNegros;
    }

    /**
     * Devuelve el bot�n que va dar el color de un p�xel en especial.
     * 
     * @return Bot�n Ver Color.
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
     * Devuelve el bot�n que va dar el n�mero de p�xeles negros en la imagen
     * 
     * @return Bot�n P�xeles negros
     */
    private JButton getBtnNumPixelesNegros( )
    {
        if( btnNegrosImagen == null )
        {
            btnNegrosImagen = new JButton( );
            btnNegrosImagen.setVisible( false );
            btnNegrosImagen.setText( "N�mero P�xeles Negros" );
            btnNegrosImagen.setActionCommand( MOSTRAR_PIXELES_NEGROS );
            btnNegrosImagen.addActionListener( this );
        }
        return btnNegrosImagen;
    }

    /**
     * Devuelve el bot�n que va dar el n�mero de Columnas.
     * 
     * @return Bot�n N�mero de columnas.
     */
    private JButton getBtnNumColumnas( )
    {
        if( btnNumColumnas == null )
        {
            btnNumColumnas = new JButton( );
            btnNumColumnas.setVisible( false );
            btnNumColumnas.setText( "N�mero de Columnas" );
            btnNumColumnas.addActionListener( this );
            btnNumColumnas.setActionCommand( MOSTRAR_COLUMNAS );
        }
        return btnNumColumnas;
    }

    /**
     * Devuelve el bot�n que va dar el n�mero de Filas.
     * 
     * @return Bot�n N�mero de filas.
     */
    private JButton getBtnNumFilas( )
    {
        if( btnNumeroFilas == null )
        {
            btnNumeroFilas = new JButton( );
            btnNumeroFilas.setVisible( false );
            btnNumeroFilas.setText( "N�mero de Filas" );
            btnNumeroFilas.setActionCommand( MOSTRAR_FILAS );
            btnNumeroFilas.addActionListener( this );
        }
        return btnNumeroFilas;
    }

    /**
     * Devuelve el bot�n que va dar el n�mero de p�xeles negros en una fila.
     * 
     * @return Bot�n N�mero de filas.
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
     * Devuelve el bot�n que va dar el n�mero de p�xeles negros en una columna.
     * 
     * @return Bot�n P�xeles negros Columna.
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
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Muestra el bot�n Ver XY
     * 
     */
    public void mostrarBtnVerXY( )
    {
        btnVerXY.setVisible( true );
        add( btnVerXY );
    }

    /**
     * Oculta el bot�n Ver XY
     * 
     */
    public void ocultarBtnVerXY( )
    {
        btnVerXY.setVisible( false );
        remove( btnVerXY );
    }

    /**
     * Muestra el bot�n Negros Imagen
     * 
     */
    public void mostrarBtnNegrosImagen( )
    {
        btnNegrosImagen.setVisible( true );
        add( btnNegrosImagen );
    }

    /**
     * Oculta el bot�n Negros Imagen
     * 
     */
    public void ocultarBtnNegrosImagen( )
    {
        btnNegrosImagen.setVisible( false );
        remove( btnNegrosImagen );
    }

    /**
     * Muestra el bot�n N�mero de columnas
     * 
     */
    public void mostrarBtnNumColumnas( )
    {
        btnNumColumnas.setVisible( true );
        add( btnNumColumnas );

    }

    /**
     * Oculta el bot�n N�mero de columnas
     * 
     */
    public void ocultarBtnNumColumnas( )
    {
        btnNumColumnas.setVisible( false );
        remove( btnNumColumnas );

    }

    /**
     * Muestra el bot�n N�mero de filas
     * 
     */
    public void mostrarBtnNumeroFilas( )
    {
        btnNumeroFilas.setVisible( true );
        add( btnNumeroFilas );
    }

    /**
     * Oculta el bot�n N�mero de filas
     * 
     */
    public void ocultarBtnNumeroFilas( )
    {
        btnNumeroFilas.setVisible( false );
        remove( btnNumeroFilas );
    }

    /**
     * Muestra el bot�n N�mero de p�xeles en una fila.
     * 
     */
    public void mostrarBtnNumeroPixelesNegrosFilas( )
    {
        btnNumeroPixelesNegrosFilas.setVisible( true );
        add( btnNumeroPixelesNegrosFilas );
    }

    /**
     * Oculta el bot�n N�mero de p�xeles en una fila.
     * 
     */
    public void ocultarBtnNumeroPixelesNegrosFilas( )
    {
        btnNumeroPixelesNegrosFilas.setVisible( false );
        remove( btnNumeroPixelesNegrosFilas );
    }

    /**
     * Muestra el bot�n Relaci�n Blancos / Negros
     * 
     */
    public void mostrarBtnRelacion( )
    {
        btnRelacionNegros.setVisible( true );
        add( btnRelacionNegros );
    }

    /**
     * Oculta el bot�n Relaci�n Blancos / Negros
     * 
     */
    public void ocultarBtnRelacion( )
    {
        btnRelacionNegros.setVisible( false );
        remove( btnRelacionNegros );
    }

    /**
     * Muestra el bot�n N�mero de p�xeles negros en una imagen
     * 
     */
    public void mostrarNegrosColumnas( )
    {
        btnNegrosColumna.setVisible( true );
        add( btnNegrosColumna );
    }

    /**
     * Oculta el bot�n N�mero de p�xeles negros en una imagen
     * 
     */
    public void ocultarNegrosColumnas( )
    {
        btnNegrosColumna.setVisible( false );
        remove( btnNegrosColumna );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
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