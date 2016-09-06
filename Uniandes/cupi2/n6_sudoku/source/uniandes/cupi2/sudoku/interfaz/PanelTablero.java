/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelTablero.java,v 1.13 2007/01/22 21:07:48 f-vela Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_sudoku
 * Autor: Manuel Muñoz - Aug 7, 2006
 * Autor: Daniel Romero - 17-nov-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sudoku.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import uniandes.cupi2.sudoku.mundo.Casilla;
import uniandes.cupi2.sudoku.mundo.Sudoku;

/**
 * Panel que contiene las casillas del tablero de Sudoku
 */
public class PanelTablero extends JPanel
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que modela la cantidad de casillas que tiene la zona
     */
    public static final int NUM_CASILLAS_ZONA = 9;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación
     */
    private InterfazSudoku principal;

    /**
     * Representa el color que toman las casillas cuando el Sudoku es resuelto
     */
    private Color colorExito;

    /**
     * Representa el color que toman las casillas cuando el Sudoku tiene errores
     */
    private Color colorError;

    /**
     * Representa el color que toman las casillas cuando están vacías
     */
    private Color colorVacio;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Las casillas del tablero
     */
    private JTextField[][] casillas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Constructor por parámetros de la clase.
     * @param ventanaPrincipal Ventana principal de la aplicación
     */
    public PanelTablero( InterfazSudoku ventanaPrincipal )
    {
        // Inicialización de los atributos y propiedades del panel
        principal = ventanaPrincipal;
        setLayout( new GridLayout( Sudoku.NUMERO_FILAS / 3, Sudoku.NUMERO_COLUMNAS / 3 ) );
        setBackground( Color.BLACK );

        // Colores
        colorExito = new Color( 87, 150, 38 );
        colorError = new Color( 200, 1, 1 );
        colorVacio = new Color( 229, 132, 15 );

        casillas = new JTextField[Sudoku.NUMERO_FILAS][Sudoku.NUMERO_COLUMNAS];

        // Creación de los paneles con las zonas
        JPanel[][] panelesZonas = new JPanel[Sudoku.CANTIDAD_ZONAS / 3][Sudoku.CANTIDAD_ZONAS / 3];
        Border borde = new BevelBorder( BevelBorder.RAISED );

        for( int i = 0; i < Sudoku.CANTIDAD_ZONAS / 3; i++ )
        {
            for( int j = 0; j < Sudoku.CANTIDAD_ZONAS / 3; j++ )
            {
                panelesZonas[ i ][ j ] = new JPanel( );
                panelesZonas[ i ][ j ].setLayout( new GridLayout( NUM_CASILLAS_ZONA / 3, NUM_CASILLAS_ZONA / 3 ) );
                panelesZonas[ i ][ j ].setBorder( borde );
                add( panelesZonas[ i ][ j ] );
            }
        }

        // Adición de los campos de texto en los paneles de zonas
        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                casillas[ i ][ j ] = new JTextField( "" );
                casillas[ i ][ j ].setHorizontalAlignment( JTextField.CENTER );                
                Font tipoLetra = casillas[ i ][ j ].getFont( );
                Font nuevoTipoLetra = new Font( tipoLetra.getName( ), Font.PLAIN, tipoLetra.getSize( ) + 3 );
                casillas[ i ][ j ].setFont( nuevoTipoLetra );
                casillas[ i ][ j ].setPreferredSize( new Dimension( 50, 50 ) );
                casillas[ i ][ j ].setEditable( false );
                panelesZonas[ i / 3 ][ j / 3 ].add( casillas[ i ][ j ] );
            }
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Vuelve a pintar los cuadros de texto
     */
    public void actualizarTablero( )
    {
        Casilla[][] casillasTablero = principal.darCasillasTablero( );

        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                // colocar el campo de texto con su configuración por defecto
                casillas[ i ][ j ].setText( "" );
                casillas[ i ][ j ].setEditable( true );
                casillas[ i ][ j ].setBackground( Color.WHITE );
                Font tipoLetra = casillas[ i ][ j ].getFont( );
                Font nuevoTipoLetra = new Font( tipoLetra.getName( ), Font.PLAIN, tipoLetra.getSize( ) );
                casillas[ i ][ j ].setFont( nuevoTipoLetra );

                // Se cambia el look and feel de acuerdo a la información de la casilla
                if( casillasTablero[ i ][ j ].esInicial( ) && casillasTablero[ i ][ j ].estaMarcada( ) )
                {
                    casillas[ i ][ j ].setBackground( colorError );
                    casillas[ i ][ j ].setEditable( false );
                    casillas[ i ][ j ].setText( "" + casillasTablero[ i ][ j ].darValorReal( ) );
                    Font letra = casillas[ i ][ j ].getFont( );
                    Font nuevaLetra = new Font( letra.getName( ), Font.BOLD, letra.getSize( ) );
                    casillas[ i ][ j ].setFont( nuevaLetra );

                }
                else if( casillasTablero[ i ][ j ].esInicial( ) )
                {
                    casillas[ i ][ j ].setText( "" + casillasTablero[ i ][ j ].darValorReal( ) );
                    Font letra = casillas[ i ][ j ].getFont( );
                    Font nuevaLetra = new Font( letra.getName( ), Font.BOLD, letra.getSize( ) );
                    casillas[ i ][ j ].setFont( nuevaLetra );
                    casillas[ i ][ j ].setEditable( false );
                    casillas[ i ][ j ].setBackground( Color.WHITE );
                }
                else if( casillasTablero[ i ][ j ].estaMarcada( ) )
                {
                    casillas[ i ][ j ].setBackground( colorError );
                    if( casillasTablero[ i ][ j ].darValorIngresado( ) != 0 )
                    {
                        casillas[ i ][ j ].setText( "" + casillasTablero[ i ][ j ].darValorIngresado( ) );
                    }
                    else
                    {
                        casillas[ i ][ j ].setText( "" );
                    }
                }
                else if( casillasTablero[ i ][ j ].darValorIngresado( ) == 0 )
                {
                    casillas[ i ][ j ].setBackground( colorVacio );
                }
                else if( casillasTablero[ i ][ j ].darValorIngresado( ) != 0 )
                {
                    casillas[ i ][ j ].setBackground( Color.WHITE );
                    casillas[ i ][ j ].setText( "" + casillasTablero[ i ][ j ].darValorIngresado( ) );
                }
            }

        }
    }

    /**
     * Devuelve la matriz con los datos ingresados por el usuario en la interfaz
     * @return Se retornó la matriz de enteros ingresados por el usuario
     * @throws Exception Si el contenido de alguna casilla no es un número entre 1 y 9
     */
    public int[][] darMatriz( ) throws Exception
    {
        // Inicialización de la matriz
        int[][] tablero = new int[Sudoku.NUMERO_FILAS][Sudoku.NUMERO_COLUMNAS];

        for( int i = 0; i < Sudoku.NUMERO_FILAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                int valor = 0;
                try
                {
                    String contenido= ( String )casillas[ i ][ j ].getText( ); 
                    
                    if(contenido!=null && !contenido.equals(""))
                    {
                        valor = Integer.parseInt( ( String )casillas[ i ][ j ].getText( ) );
                        if(valor<1 || valor>9)
                        {                        
                            throw new Exception("El contenido que ingrese en cada casilla debe ser un valor numérico entre 1 y 9");
                        }
                    }
                    
                    tablero[ i ][ j ] = valor;                                                            
                }
                catch( NumberFormatException e )
                {
                    throw new Exception("El contenido que ingrese en cada casilla debe ser un valor numérico entre 1 y 9");
                }
                
            }
        }

        return tablero;
    }

    /**
     * Pone los campos de texto en el color que indica que el juego se ha terminado con éxito
     */
    public void pintarTableroTerminado( )
    {
        for( int i = 0; i < Sudoku.CANTIDAD_ZONAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                casillas[ i ][ j ].setBackground( colorExito );
                ;
            }

        }
    }

    /**
     * Se muestra en los campos de texto la solución del juego
     */
    public void mostrarSolucion( )
    {
        Casilla[][] casillasTablero = principal.darCasillasTablero( );
        for( int i = 0; i < Sudoku.CANTIDAD_ZONAS; i++ )
        {
            for( int j = 0; j < Sudoku.NUMERO_COLUMNAS; j++ )
            {
                casillas[ i ][ j ].setBackground( colorExito );
                casillas[ i ][ j ].setText( "" + casillasTablero[ i ][ j ].darValorReal( ) );
                casillas[ i ][ j ].setEditable( false );
            }

        }
    }
}
