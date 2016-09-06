/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PreguntaTest.java,v 1.1 2007/03/22 14:05:58 man-muno Exp $
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

import junit.framework.TestCase;
import uniandes.cupi2.crucigrama.mundo.Pregunta;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Pregunta estén correctamente implementados
 */
public class PreguntaTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Pregunta pregunta;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva Crucigrama vacía
     *  
     */
    private void setupEscenario1( )
    {
        pregunta = new Pregunta(1,2,"Pregunta de prueba" );
    }

    /**
     * Método que verifica que retorne la coordenada de la pregunta correctamente
     */
    public void testObtenerCoordenada()
    {
        setupEscenario1( );
        assertNotNull( "La coordenada de la pregunta no debería ser null", pregunta.obtenerCoordenada( ) );
        assertEquals( "La coordenada de la pregunta es incorrecta","1:2", pregunta.obtenerCoordenada( ) );
    }
    
    /**
     * Método que verifica que retorne la descripción de la pregunta correctamente
     */
    public void testObtenerDescripcion()
    {
        setupEscenario1( );
        assertNotNull( "La descripcion de la pregunta no debería ser null", pregunta.obtenerDescripcion( ) );
        assertEquals( "La descripcion de la pregunta es incorrecta","Pregunta de prueba", pregunta.obtenerDescripcion( ) );
    }
    
    
}