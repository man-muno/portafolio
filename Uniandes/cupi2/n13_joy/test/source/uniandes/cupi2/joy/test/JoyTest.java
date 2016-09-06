/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: JoyTest.java,v 1.1 2008/01/22 03:41:56 p-marque Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_joy
 * Autor: Manuel Muñoz - 21-Jan-2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.joy.test;

import junit.framework.TestCase;
import uniandes.cupi2.joy.mundo.Joy;

/**
 * Esta es la clase usada para verificar que los métodos de la clase Joy estén correctamente implementados
 */
public class JoyTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private Joy joy;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva Joy vacía
     *  
     */
    private void setupEscenario1( )
    {
        joy = new Joy( );
    }

    /**
     * Prueba 1
     */
    public void testJoy( )
    {
        setupEscenario1( );
        
        //
        //Código de la prueba
    }

}