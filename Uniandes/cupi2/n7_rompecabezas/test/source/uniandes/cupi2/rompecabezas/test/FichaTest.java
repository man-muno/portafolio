/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_rompecabezas
 * Autor: Manuel Mu�oz - 02-Oct-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.rompecabezas.test;

import junit.framework.TestCase;
import uniandes.cupi2.rompecabezas.mundo.Ficha;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Ficha est�n correctamente implementados
 */
public class FichaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ficha para realizar las pruebas
     */
    private Ficha ficha1;

    /**
     * Ficha para realizar las pruebas
     */
    private Ficha ficha2;

    /**
     * Ficha para realizar las pruebas
     */
    private Ficha ficha3;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye tres fichas con valores diferentes
     */
    private void setupEscenario1( )
    {
        ficha1 = new Ficha( "ruta1", 1, 0, 0, Ficha.ESQUINA );
        ficha2 = new Ficha( "ruta2", 2, 0, 1, Ficha.BORDE );
        ficha3 = new Ficha( "ruta3", 3, 1, 0, Ficha.INTERNA );
    }

    /**
     * Verifica el constructor. <br>
     * <b> M�todos a probar: </b> <br>
     * Ficha (constructor), obtenerRuta, obtenerColumna, obtenerFila, obtenerPosicion, obtenerRegion<br>
     * <b> Objetivo: </b> Probar que el constructor crea una ficha de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear una ficha los atributos del objeto deben quedar inicializados con el valor correcto.
     */
    public void testFicha( )
    {
        setupEscenario1( );

        assertEquals( "La ruta de la ficha est� equivocada", "ruta1", ficha1.obtenerRuta( ) );
        assertEquals( "La columna de la ficha est� equivocada", 0, ficha1.obtenerColumna( ) );
        assertEquals( "La fila de la ficha est� equivocada", 0, ficha1.obtenerFila( ) );
        assertEquals( "La posici�n de la ficha en la figura est� equivocada", 1, ficha1.obtenerPosicion( ) );
        assertEquals( "La regi�n de la ficha est� equivocada", Ficha.ESQUINA, ficha1.obtenerRegion( ) );

        assertEquals( "La ruta de la ficha est� equivocada", "ruta2", ficha2.obtenerRuta( ) );
        assertEquals( "La columna de la ficha est� equivocada", 1, ficha2.obtenerColumna( ) );
        assertEquals( "La fila de la ficha est� equivocada", 0, ficha2.obtenerFila( ) );
        assertEquals( "La posici�n de la ficha en la figura est� equivocada", 2, ficha2.obtenerPosicion( ) );
        assertEquals( "La regi�n de la ficha est� equivocada", Ficha.BORDE, ficha2.obtenerRegion( ) );

        assertEquals( "La ruta de la ficha est� equivocada", "ruta3", ficha3.obtenerRuta( ) );
        assertEquals( "La columna de la ficha est� equivocada", 0, ficha3.obtenerColumna( ) );
        assertEquals( "La fila de la ficha est� equivocada", 1, ficha3.obtenerFila( ) );
        assertEquals( "La posici�n de la ficha en la figura est� equivocada", 3, ficha3.obtenerPosicion( ) );
        assertEquals( "La regi�n de la ficha est� equivocada", Ficha.INTERNA, ficha3.obtenerRegion( ) );

    }

    /**
     * Verifica el m�todo compararPorColumna. <br>
     * <b> M�todos a probar: </b> <br>
     * compararPorColumna. <br>
     * <b> Objetivo: </b> Probar que el m�todo compararPorColumna realiza la comparaci�n de dos fichas de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar una ficha cuya columna es menor a la de otra el resultado debe ser -1. <br>
     * 2. Al comparar una ficha cuya columna es igual a la de otra el resultado debe ser 0. <br>
     * 3. Al comparar una ficha cuya columna es mayor a la de otra el resultado debe ser 1.
     */
    public void testCompararPorColumna( )
    {
        setupEscenario1( );

        assertEquals( "La ficha 1 deber�a ser menor", -1, ficha1.compararPorColumna( ficha2 ) );
        assertEquals( "Las columnas de las fichas 1 y 3 deber�an ser iguales", 0, ficha1.compararPorColumna( ficha3 ) );
        assertEquals( "La ficha 2 deber�a ser mayor", 1, ficha2.compararPorColumna( ficha3 ) );
    }

    /**
     * Verifica el m�todo compararPorFila. <br>
     * <b> M�todos a probar: </b> <br>
     * compararPorFila. <br>
     * <b> Objetivo: </b> Probar que el m�todo compararPorFila realiza la comparaci�n de dos fichas de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar una ficha cuya fila es menor a la de otra el resultado debe ser -1. <br>
     * 2. Al comparar una ficha cuya fila es igual a la de otra el resultado debe ser 0. <br>
     * 3. Al comparar una ficha cuya fila es mayor a la de otra el resultado debe ser 1.
     */
    public void testCompararPorFila( )
    {
        setupEscenario1( );

        assertEquals( "La ficha 2 deber�a ser menor", -1, ficha2.compararPorFila( ficha3 ) );
        assertEquals( "Las filas de las fichas 1 y 2 deber�an ser iguales", 0, ficha1.compararPorFila( ficha2 ) );
        assertEquals( "La ficha 3 deber�a ser mayor", 1, ficha3.compararPorFila( ficha2 ) );
    }

    /**
     * Verifica el m�todo compararPorRegion. <br>
     * <b> M�todos a probar: </b> <br>
     * compararPorRegion. <br>
     * <b> Objetivo: </b> Probar que el m�todo compararPorRegion realiza la comparaci�n de dos fichas de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar una ficha cuya regi�n es menor a la de otra el resultado debe ser -1. <br>
     * 2. Al comparar una ficha cuya regi�n es igual a la de otra el resultado debe ser 0. <br>
     * 3. Al comparar una ficha cuya regi�n es mayor a la de otra el resultado debe ser 1.
     */
    public void testCompararPorRegion( )
    {
        setupEscenario1( );

        assertEquals( "La ficha 1 deber�a ser menor", -1, ficha1.compararPorRegion( ficha2 ) );
        assertEquals( "La ficha 2 deber�a ser mayor", 1, ficha2.compararPorRegion( ficha1 ) );
        assertEquals( "La ficha 2 deber�a ser menor", -1, ficha2.compararPorRegion( ficha3 ) );
        assertEquals( "La ficha 3 deber�a ser mayor", 1, ficha3.compararPorRegion( ficha2 ) );
        assertEquals( "Las regiones de las fichas 1 y 1 deber�an ser iguales", 0, ficha1.compararPorFila( ficha1 ) );

    }

    /**
     * Verifica el m�todo compararPorPosicion. <br>
     * <b> M�todos a probar: </b> <br>
     * compararPorPosicion. <br>
     * <b> Objetivo: </b> Probar que el m�todo compararPorPosicion realiza la comparaci�n de dos fichas de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar una ficha cuya posici�n es menor a la de otra el resultado debe ser -1. <br>
     * 2. Al comparar una ficha cuya posici�n es igual a la de otra el resultado debe ser 0. <br>
     * 3. Al comparar una ficha cuya posici�n es mayor a la de otra el resultado debe ser 1.
     */
    public void testCompararPorPosicion( )
    {
        setupEscenario1( );

        assertEquals( "La ficha 1 deber�a ser menor", -1, ficha1.compararPorPosicion( ficha2 ) );
        assertEquals( "Las posiciones de las fichas deber�an ser iguales", 0, ficha1.compararPorPosicion( ficha1 ) );
        assertEquals( "La ficha 3 deber�a ser mayor", 1, ficha3.compararPorPosicion( ficha2 ) );
    }

}