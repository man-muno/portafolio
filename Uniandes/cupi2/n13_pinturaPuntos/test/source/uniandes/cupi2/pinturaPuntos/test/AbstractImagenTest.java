/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AbstractImagenTest.java,v 1.2 2007/03/05 19:18:48 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_pinturaPuntos
 * Autor: Manuel Mu�oz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.pinturaPuntos.test;

import junit.framework.TestCase;
import uniandes.cupi2.pinturaPuntos.mundo.IImagen;
import uniandes.cupi2.pinturaPuntos.mundo.IIteradorPintura;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase InterfazImagen est�n correctamente implementados
 */
public abstract class AbstractImagenTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Imagen a probar
     */
    private IImagen imagen;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo de creaci�n de una instancia con las im�genes concretas. Las subclases deben implementar este m�todo, creando una instancia concreta
     */
    abstract public IImagen crearInstancia( int filas, int columnas );

    public void setupEscenario1( )
    {
        imagen = crearInstancia( 150, 80 );
    }

    /**
     * M�todo de configuraci�n de los datos de prueba
     */
    public void setupEscenario2( )
    {
        imagen = crearInstancia( 2, 2 );
        imagen.agregarPunto( 0, 0 );
        imagen.agregarPunto( 1, 1 );
    }

    /**
     * Se encarga de probar que retorne correctamente el n�mero de columnas.
     */
    public void testDarColumnas( )
    {
        setupEscenario1( );
        assertEquals( "La cantidad de columnas deber�a ser 80", 80, imagen.darColumnas( ) );
    }

    /**
     * Se encarga de probar que retorne correctamente el n�mero de filas
     */
    public void testDarFilas( )
    {
        setupEscenario1( );
        assertEquals( "La cantidad de filas deber�a ser 150", 150, imagen.darFilas( ) );
    }

    /**
     * Se encarga de probar que retorne correctamente la cantidad de p�xeles negros que tiene la imagen
     */
    public void testEsNegro( )
    {
        setupEscenario2( );
        assertTrue( "El punto 0,0 es negro", imagen.esNegro( 0, 0 ) );
        assertFalse( "El punto 1,0 es blanco", imagen.esNegro( 1, 0 ) );
        assertFalse( "El punto 0,1 es blanco", imagen.esNegro( 0, 1 ) );
        assertTrue( "El punto 1,1 es negro", imagen.esNegro( 1, 1 ) );
        imagen.agregarPunto( 1, 0 );
        assertTrue( "El punto 1,0 es negro", imagen.esNegro( 1, 0 ) );
    }

    /**
     * Se encarga de retornar la cantidad p�xeles negros en una fila dada
     */
    public void testDarNumeroPixelesNegrosFila( )
    {
        setupEscenario1( );
        for( int i = 0; i < 2; i++ )
        {
            assertEquals( "No deber�a haber puntos negros en la fila " + i, 0, imagen.darNumeroPixelesNegrosFila( i ) );
        }
        setupEscenario2( );
        for( int i = 0; i < 2; i++ )
        {
            assertEquals( "Deber�a haber 1 punto negro en la fila " + i, 1, imagen.darNumeroPixelesNegrosFila( i ) );
        }
        imagen.agregarPunto( 0, 1 );
        assertEquals( "Deber�a haber 2 puntos negros en la fila 0", 2, imagen.darNumeroPixelesNegrosFila( 0 ) );
        assertEquals( "Deber�a haber 1 punto negro en la fila 1", 1, imagen.darNumeroPixelesNegrosFila( 1 ) );
    }

    /**
     * Se encarga de probar el m�todo que retorna la cantidad de p�xeles negros en una imagen
     */
    public void testDarNumeroNegros( )
    {
        setupEscenario1( );
        assertEquals( "La cantidad de p�xeles negros deber�a ser 0", 0, imagen.darNumeroNegros( ) );
        setupEscenario2( );
        assertEquals( "La cantidad de p�xeles negros deber�a ser 2", 2, imagen.darNumeroNegros( ) );
        imagen.agregarPunto( 0, 1 );
        assertEquals( "La cantidad de p�xeles negros deber�a ser 3", 3, imagen.darNumeroNegros( ) );
    }

    /**
     * Se encarga de probar el iterador.
     */
    public void testDarIterador( )
    {
        setupEscenario2( );
        IIteradorPintura iterador = null;
        for( int i = 0; i < imagen.darFilas( ); i++ )
        {
            int contador = 0;
            int contadorNegros = 0;
            iterador = imagen.darIterador( i );
            while( iterador.haySiguiente( ) )
            {
                contador++;
                if( iterador.darSiguiente( ) )
                    contadorNegros++;
            }
            assertEquals( "La cantidad de elementos no es correcta", contador, imagen.darColumnas( ) );
            assertEquals( "La cantidad de pixeles negros no es correcta", contadorNegros, imagen.darNumeroPixelesNegrosFila( i ) );

        }
    }

}
