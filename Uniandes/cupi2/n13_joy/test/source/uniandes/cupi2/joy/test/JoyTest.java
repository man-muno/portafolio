/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: JoyTest.java,v 1.1 2008/01/22 03:41:56 p-marque Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_joy
 * Autor: Manuel Mu�oz - 21-Jan-2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.joy.test;

import junit.framework.TestCase;
import uniandes.cupi2.joy.mundo.Joy;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Joy est�n correctamente implementados
 */
public class JoyTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Joy joy;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva Joy vac�a
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
        //C�digo de la prueba
    }

}