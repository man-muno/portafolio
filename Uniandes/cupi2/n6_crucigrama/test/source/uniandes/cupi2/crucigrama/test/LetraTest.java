/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: LetraTest.java,v 1.1 2007/03/23 21:55:04 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_crucigrama
 * Autor: Manuel Muñoz - 05-Mar-2007
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
import uniandes.cupi2.crucigrama.mundo.Letra;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Letra estén correctamente implementados
 */
public class LetraTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Letra letra;
    

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva Letra f
     */
    private void setupEscenario1( )
    {
        letra = new Letra("f");
    }
    
    /**
     * Construye una nueva Letra $
     */
    private void setupEscenario2( )
    {
        letra = new Letra("$");
    }
    
   
    /**
     * Prueba que al ingresar una letra verifique que es la que corresponde
     */
    public void testEsLetraCorrecta()
    {
        setupEscenario1( );
        letra.ingresarLetraJuego( "g" );
        assertFalse("Letra ingresada es incorrecta",letra.esLetraCorrecta( ));
        letra.ingresarLetraJuego( "f" );
        assertTrue("Letra ingresada es correcta",letra.esLetraCorrecta( ));
        letra.ingresarLetraJuego( "F" );
        assertTrue("Letra ingresada es correcta",letra.esLetraCorrecta( ));
    }
    
    /**
     * Prueba si retorna correctamente que usa letra sea vacía o negra
     */
    public void testEsNegra()
    {
        setupEscenario1( );
        assertFalse("Letra ingresada no es negra",letra.esNegra( ));
        setupEscenario2( );
        assertTrue("Letra ingresada es negra",letra.esNegra( ));
    }
    
    /**
     * Prueba el metodo de ingresar una letra jugada por el usuario
     */
    public void testIngresarLetraJuego()
    {
        setupEscenario1( );
        letra.ingresarLetraJuego( "g" );
        assertFalse("Letra ingresada es incorrecta",letra.esLetraCorrecta( ));
        letra.ingresarLetraJuego( "f" );
        assertTrue("Letra ingresada es correcta",letra.esLetraCorrecta( ));
        letra.ingresarLetraJuego( "F" );
        assertTrue("Letra ingresada es correcta",letra.esLetraCorrecta( ));
        
    }
    

}