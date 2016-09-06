/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: SateliteNaturalTest.java,v 1.5 2007/06/28 22:59:38 camil-ji Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_observatorio
 * Autor: Manuel Mu�oz - 13-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.observatorio.test;

import junit.framework.TestCase;
import uniandes.cupi2.observatorio.mundo.SateliteNatural;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase SateliteNatural est�n correctamente implementados
 */
public class SateliteNaturalTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private SateliteNatural satelite;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo SateliteNatural vac�o
     * 
     */
    private void setupEscenario1( )
    {
        satelite = new SateliteNatural( "nombre", 0.1, 0.2 );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo editar y la inicializaci�n de los atributos en el m�todo constructor<br>
     * <b> M�todos a probar: </b> <br>
     * editar<br>
     * <b> Objetivo: </b> Probar la modificaci�n de un sat�lite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Creaci�n de un nuevo sat�lite natural con el nombre nombre con excentricidad 0.1 e inclinaci�n 0.2<br>
     * 2. Modificar los datos del sat�lite natural con el nombre nombre con excentricidad 0.3 e inclinaci�n 0.4<br>
     * 3. Verificar que los nuevos datos de el sat�lite natural sean correctos.
     */
    public void testEditar( )
    {
        setupEscenario1( );
        assertEquals( "La excentricidad es incorrecta", 0.1, satelite.obtenerExcentricidad( ) );
        assertEquals( "La inclinaci�n es incorrecta", 0.2, satelite.obtenerInclinacion( ) );
        satelite.cambiarExcentricidad( 0.3 );
        satelite.cambiarInclinacionOrbital( 0.4 );
        assertEquals( "La excentricidad es incorrecta", 0.3, satelite.obtenerExcentricidad( ) );
        assertEquals( "La inclinaci�n es incorrecta", 0.4, satelite.obtenerInclinacion( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerExcentricidad<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerExcentricidad<br>
     * <b> Objetivo: </b> Probar que se creo correctamente el sat�lite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el valor correcto de la excentricidad.
     */
    public void testObtenerExcentricidad( )
    {
        setupEscenario1( );
        assertEquals( "La excentricidad es incorrecta", 0.1, satelite.obtenerExcentricidad( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerInclinacion<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerInclinacion<br>
     * <b> Objetivo: </b> Probar que se creo correctamente el sat�lite natural<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el valor correcto de la inclinaci�n.
     */
    public void testObtenerInclinacion( )
    {
        setupEscenario1( );
        assertEquals( "La inclinaci�n es incorrecta", 0.2, satelite.obtenerInclinacion( ) );
    }

    /**
     * Este m�todo se encarga de verificar el m�todo obtenerNombre<br>
     * <b> M�todos a probar: </b> <br>
     * obtenerNombre<br>
     * <b> Objetivo: </b> Probar que se creo correctamente el planeta<br>
     * <b> Resultados esperados: </b> <br>
     * 1. Obtener el valor correcto del nombre del planeta.
     */
    public void testObtenerNombre( )
    {
        setupEscenario1( );
        assertEquals( "El nombre es incorrecto", "nombre", satelite.obtenerNombre( ) );
    }

}