/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CrucigramaTest.java,v 1.2 2007/04/03 07:40:44 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_crucigrama
 * Autor: Manuel Mu�oz - 05-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.crucigrama.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import junit.framework.TestCase;
import uniandes.cupi2.crucigrama.mundo.Crucigrama;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Crucigrama est�n correctamente implementados
 */
public class CrucigramaTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Crucigrama crucigrama;
    
    private char[][] tablero;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva Crucigrama vac�a
     *  
     */
    private void setupEscenario1( )
    {
        crucigrama = new Crucigrama( );
    }
    
    /**
     * Construye una nueva Crucigrama vac�a
     *  
     */
    private void setupEscenario2( )
    {
        crucigrama = new Crucigrama( );
        Properties propiedades = new Properties();
        try
        {
            propiedades.load( new FileInputStream( new File( "./test/data/crucigrama.properties" ) ) );
            crucigrama.cargarTablero( propiedades );
            
            tablero = new char[][]{{'D','E','M','A','C','R','A','D','A'}, 
                    {'E','T','A','P','A','$','P','O','R'}, 
                    {'S','I','L','E','R','O','$','S','T'},
                    {'C','O','T','O','R','R','A','$','E'},
                    {'O','P','A','$','O','I','D','O','S'},
                    {'N','E','S','O','$','G','A','R','O'},
                    {'F','S','$','C','A','I','M','A','N'}, 
                    {'I','$','P','E','R','N','A','D','A'},
                    {'A','S','O','L','E','A','D','O','S'},  
                    {'R','E','S','O','L','L','A','R','$'}};
        }
        catch( FileNotFoundException e )
        {
            fail(e.getMessage( ));
        }
        catch( IOException e )
        {
            fail(e.getMessage( ));
        }
        catch( Exception e )
        {
            fail(e.getMessage( ));
        }
    }

    /**
     * Prueba 1
     */
    public void testCrucigrama( )
    {
        setupEscenario1( );
        assertNotNull("La lista de preguntas horizontales no deber�a ser null",crucigrama.obtenerListaPreguntasHorizontales( ));
        assertEquals("La lista de preguntas horizontales deber�a ser vac�a",0,crucigrama.obtenerListaPreguntasHorizontales( ).size( ));
        assertNotNull("La lista de preguntas verticales no deber�a ser null",crucigrama.obtenerListaPreguntasVerticales( ));
        assertEquals("La lista de preguntas verticales deber�a ser vac�a",0,crucigrama.obtenerListaPreguntasVerticales( ).size( ));
    }
    
    /**
     * M�todo que prueba que al cargar un tablero no se generen excepciones 
     */
    public void testCargarTablero()
    {
        setupEscenario1( );
        Properties propiedades = new Properties( );

        try
        {
            propiedades.load( new FileInputStream( new File( "./test/data/crucigrama.properties" ) ) );
            crucigrama.cargarTablero( propiedades );
        }
        catch( FileNotFoundException e )
        {
            fail(e.getMessage( ));
        }
        catch( IOException e )
        {
            fail(e.getMessage( ));
        }
        catch( Exception e )
        {
            fail(e.getMessage( ));
        }
    }
    
    /**
     * M�todo que prueba que se retorne correctamente las casillas negras del tablero
     */
    public void testObtenerCasillasNegras()
    {
        setupEscenario2( );
        char[][] casillas = crucigrama.obtenerCasillasNegras( );
        assertNotNull("La matriz de casillas negras no deber�a ser null",crucigrama.obtenerCasillasNegras( ));
        for(int i=0;i<casillas.length;i++)
        {
            char[] fila = casillas[i];
            for(int j=0;j<fila.length;j++)
            {
                if(fila[j]=='$' && tablero[i][j]!='$')
                    fail("La interfaz de casillas negras esta mal armada");
            }
        }
    }
    /**
     * M�todo que prueba que la lista de preguntas horizontales tenga la cantidad de elementos correcta
     */
    public void testObtenerListaPreguntasHorizontales()
    {
        setupEscenario2( );
        assertNotNull("La lista de preguntas no deber�a ser null",crucigrama.obtenerListaPreguntasHorizontales( ));
        assertEquals("La cantidad de preguntas horizontales es incorrecta",13,crucigrama.obtenerListaPreguntasHorizontales( ).size( ));
        
    }

    /**
     * M�todo que prueba que la lista de preguntas horizontales tenga la cantidad de elementos correcta
     */
    public void testObtenerListaPreguntasVerticales()
    {
        setupEscenario2( );
        assertNotNull("La lista de preguntas no deber�a ser null",crucigrama.obtenerListaPreguntasVerticales( ));
        assertEquals("La cantidad de preguntas verticales es incorrecta",13,crucigrama.obtenerListaPreguntasVerticales( ).size( ));
    }
    
    /**
     * M�todo que verifica que valide correctamente un juego
     */
    public void testValidarJuego()
    {
        setupEscenario2( );
        String[][] tableroCorrecto = {{"D","E","M","A","C","R","A","D","A"}, 
                {"E","T","A","P","A","$","P","O","R"}, 
                {"S","I","L","E","R","O","$","S","T"},
                {"C","O","T","O","R","R","A","$","E"},
                {"O","P","A","$","O","I","D","O","S"},
                {"N","E","S","O","$","G","A","R","O"},
                {"F","S","$","C","A","I","M","A","N"}, 
                {"I","$","P","E","R","N","A","D","A"},
                {"A","S","O","L","E","A","D","O","S"},  
                {"R","E","S","O","L","L","A","R","$"}};
        assertTrue("Valido incorrectamente el tablero valido",crucigrama.validarJuego( tableroCorrecto ));
        String[][] tableroIncorrecto = {{"D","E","M","A","C","R","A","D","A"}, 
                {"E","T","A","P","A","$","P","O","R"}, 
                {"S","I","L","E","R","O","$","S","T"},
                {"C","O","T","O","R","R","A","$","E"},
                {"O","P","A","$","O","I","D","O","S"},
                {"N","E","S","S","$","G","A","R","O"},
                {"F","S","$","C","A","I","M","A","N"}, 
                {"I","$","P","E","R","N","A","D","A"},
                {"A","S","O","L","E","A","D","O","S"},  
                {"R","E","S","O","L","L","A","R","$"}};
        
        assertFalse("Valido incorrectamente el tablero inv�lido",crucigrama.validarJuego( tableroIncorrecto ));
        
    }

}