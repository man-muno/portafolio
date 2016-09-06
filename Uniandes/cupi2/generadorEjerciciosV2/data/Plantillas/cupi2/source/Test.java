/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: [[NOMBRE_COMPLETO]]
 * Autor: [[AUTOR]] - [[FECHA]]
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package [[PAQUETE]].[[DIRECTORIO_PRUEBAS]];

import junit.framework.TestCase;
import [[PAQUETE]].[[DIRECTORIO_MUNDO]].[[CLASE_MUNDO]];

/**
 * Esta es la clase usada para verificar que los m�todos de la clase [[CLASE_MUNDO]] est�n correctamente implementados
 */
public class [[CLASE_PRUEBAS]] extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private [[CLASE_MUNDO]] [[NOMBRE_ATRIBUTO_MUNDO]];

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva [[CLASE_MUNDO]] vac�a
     *  
     */
    private void setupEscenario1( )
    {
        [[NOMBRE_ATRIBUTO_MUNDO]] = new [[CLASE_MUNDO]]( );
    }

    /**
     * Prueba 1
     */
    public void test[[CLASE_MUNDO]]( )
    {
        setupEscenario1( );
        
        //
        //C�digo de la prueba
    }

}