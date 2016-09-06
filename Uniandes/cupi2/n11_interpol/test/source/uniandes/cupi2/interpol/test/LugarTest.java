/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: LugarTest.java,v 1.2 2007/04/20 12:38:41 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Mu�oz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.interpol.test;

import junit.framework.TestCase;
import uniandes.cupi2.interpol.mundo.Lugar;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Lugar est�n correctamente implementados
 */
public class LugarTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Lugar lugar;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Lugar vac�a
     * 
     */
    private void setupEscenario1( )
    {
        lugar = new Lugar( "Banco", "Banco para cambiar dinero a d�lares", 4 );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo constructor y los analizadores<br>
     * <b> M�todos a probar: </b> <br>
     * Lugar<br>
     * darNombre<br>
     * esLadron<br>
     * darTiempoGastado<br>
     * <b> Objetivo: </b> Probar que se construye correctamente el objeto<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se construye un lugar con el nombre "Banco", la pista "Banco para cambiar dinero a d�lares" y con tiempo de 4<br>
     * 2. Se obtienen los datos del sospechoso creado y concuerdan con los ingresados<br>
     */
    public void testLugar( )
    {
        setupEscenario1( );
        assertNotNull( "El nombre del lugar no puede ser null", lugar.darNombre( ) );
        assertEquals( "El nombre del lugar es incorrecto", "Banco", lugar.darNombre( ) );
        assertNotNull( "La pista del lugar no puede ser null", lugar.darPista( ) );
        assertEquals( "La pista del lugar es incorrecta", "Banco para cambiar dinero a d�lares", lugar.darPista( ) );
        assertEquals( "El tiempo gastado mirando la pista es incorrecto", 4, lugar.darTiempoGastado( ) );
    }

}