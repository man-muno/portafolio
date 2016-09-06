/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AbstractBolsaTest.java,v 1.4 2007/02/12 15:19:41 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Manuel Munoz - Sep 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.test;

import junit.framework.*;
import uniandes.cupi2.bolsa.mundo.*;

/**
 * Clase donde se definen los métodos de prueba para las bolsas
 */
public abstract class AbstractBolsaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Instancia de la bolsa a probar
     */
    protected IBolsa bolsa;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Método de creación de una instancia con las estructuras concretas de datos. Las subclases deben implementar este método, creando una instancia
     * concreta
     */
    abstract public IBolsa crearInstancia( int inferior, int superior );

    /**
     * Método de configuración de los datos de prueba
     */
    public void setupEscenario1( )
    {
        bolsa = crearInstancia( 0, 4 );
    }

    /**
     * Método de configuración de los datos de prueba
     */
    public void setupEscenario2( )
    {
        bolsa = crearInstancia( 0, 4 );
        try
        {
            bolsa.agregar( 1 );
            bolsa.agregar( 3 );
            bolsa.agregar( 2 );
            bolsa.agregar( 2 );
            bolsa.agregar( 3 );
        }
        catch( FueraLimiteException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Prueba que se cree la bolsa con los limites y la longitud correcta
     */
    public void testConstructor( )
    {
        setupEscenario1( );
        assertEquals( "El límite superior debe ser 4", 4, bolsa.darSuperior( ) );
        assertEquals( "El límite inferior debe ser 0", 0, bolsa.darInferior( ) );
        assertEquals( "La longitud debe ser 0", 0, bolsa.darLongitud( ) );
    }

    /**
     * Prueba de inserción de elementos en la bolsa
     */
    public void testInsertar( )
    {
        setupEscenario1( );
        try
        {
            bolsa.agregar( 1 );
            bolsa.agregar( 3 );
            assertEquals( "La cantidad de elementos debería ser 2", 2, bolsa.darLongitud( ) );
        }
        catch( FueraLimiteException e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Prueba de eliminación de elementos de la bolsa
     */
    public void testEliminar( )
    {
        setupEscenario2( );
        try
        {
            bolsa.eliminar( 1 );
        }
        catch( NoExisteException e )
        {
            // No deberia ocurrir, porque en setupEscenario2 se esta agregando
            fail( e.getMessage( ) );
        }
        assertEquals( "La cantidad de elementos debería ser 4", 4, bolsa.darLongitud( ) );
        assertEquals( "El elemento 1 no debería estar en la bolsa", false, bolsa.buscar( 1 ) );
    }

    /**
     * Prueba de búsqueda de elementos en la bolsa
     */
    public void testBuscar( )
    {
        setupEscenario2( );
        boolean respuesta = bolsa.buscar( 5 );
        assertEquals( "La cantidad de elementos debería ser 5", 5, bolsa.darLongitud( ) );
        assertEquals( "El elemento 5 no debería estar en la bolsa", false, respuesta );
        try
        {
            bolsa.eliminar( 1 );
        }
        catch( NoExisteException e )
        {
            // No deberia ocurrir, porque en setupEscenario2 se esta agregando
            fail( e.getMessage( ) );
        }
        respuesta = bolsa.buscar( 1 );
        assertEquals( "La cantidad de elementos debería ser 4", 4, bolsa.darLongitud( ) );
        assertEquals( "El elemento 1 no debería estar en la bolsa", false, respuesta );
    }

    /**
     * Prueba de retorno de elementos de la bolsa
     */
    public void testRetornar( )
    {
        setupEscenario2( );
        int respuesta = 0;
        try
        {
            respuesta = bolsa.retornar( 2 );
        }
        catch( NoExisteException e )
        {
            // No debería ocurrir ya que en setupEscenario2 se coloca un elemento en la posición 2
            fail( e.getMessage( ) );
        }
        assertEquals( "La cantidad de elementos debería ser 5", 5, bolsa.darLongitud( ) );
        assertEquals( "El elemento de la bolsa debería ser 2", 2, respuesta );
    }

    /**
     * Prueba la longitud de la bolsa
     */
    public void testDarCantidadElementos( )
    {
        setupEscenario2( );
        assertEquals( "La cantidad de elementos debería ser 5", 5, bolsa.darLongitud( ) );
    }

    /**
     * Prueba el limite inferior de la bolsa
     */
    public void testDarInferior( )
    {
        setupEscenario1( );
        assertEquals( "El límite inferior debe ser 0", 0, bolsa.darInferior( ) );
    }

    /**
     * Prueba el limite superior de la bolsa
     */
    public void testDarSuperior( )
    {
        setupEscenario1( );
        assertEquals( "El límite superior debe ser 4", 4, bolsa.darSuperior( ) );
    }

    /**
     * Prueba el método de obtener el iterador
     */
    public void testDarIterador( )
    {
        setupEscenario2( );
        IIteradorBolsa iterador = bolsa.darIterador( );
        assertNotNull( "El iterador no puede ser null", iterador );
        try
        {
            assertEquals( "El elemento retornado no corresponde al que sigue en la bolsa", bolsa.retornar( 1 ), iterador.darSiguiente( ) );
            assertEquals( "El elemento retornado no corresponde al que sigue en la bolsa", bolsa.retornar( 2 ), iterador.darSiguiente( ) );
            assertEquals( "El elemento retornado no corresponde al que sigue en la bolsa", bolsa.retornar( 3 ), iterador.darSiguiente( ) );
            assertEquals( "El elemento retornado no corresponde al que sigue en la bolsa", bolsa.retornar( 4 ), iterador.darSiguiente( ) );
            assertEquals( "El elemento retornado no corresponde al que sigue en la bolsa", bolsa.retornar( 5 ), iterador.darSiguiente( ) );
        }
        catch( NoExisteException e )
        {
            fail(e.getMessage( ));
        }
    }
    
    
}
