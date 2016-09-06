package uniandes.cupi2.collections.bitArray.test;

import junit.framework.TestCase;
import uniandes.cupi2.collections.bitArray.BitArray;
import uniandes.cupi2.collections.bitArray.IndiceFueraDeRango;

/**
 * Esta es la clase usada para verificar los m�todos de la clase BitArray
 */
public class BitArrayTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se har�n las pruebas
     */
    private BitArray bitArray;

    /**
     * El n�mero de elementos a manejar en cada escenario
     */
    private int numeroElementos;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un bitArray vacio con 8 elementos
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
    	numeroElementos = 8;
        bitArray = new BitArray( numeroElementos );
        
    }

    /**
     * Construye un bitArray con 1000 elementos. Posiciones pares con false y posiciones impares con true
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
    	numeroElementos = 1000;
        bitArray = new BitArray( numeroElementos );
        
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            bitArray.asignar(cont, cont % 2 != 0 );
        }

    }
   
    /**
     * Prueba la asignaci�n se haga de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAsignar1( )
    {
        setupEscenario1( );

        for( int cont = 0; cont < numeroElementos; cont += 2 )
        {
            bitArray.asignar( cont, true );
        }

        // Verificar que las asignaciones se hayan realizado de forma correcta
        boolean estado;
        for( int cont = 0; cont < numeroElementos; cont+= 2 )
        {
            estado = bitArray.estaEncendido( cont );

            assertTrue( "El bit deber�a estar prendido", estado );
        }

        // Poner todas las posiciones impares en false
        for( int cont = 1; cont < numeroElementos; cont += 2 )
        {
            estado = bitArray.estaEncendido( cont );

            bitArray.asignar( cont, false );
        }

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            estado = bitArray.estaApagado( cont );

            if( cont % 2 != 0 )
            {
                assertTrue( "El bit deber�a estar apagado", estado );
            }
            else
            {
                assertFalse( "El bit deber�a estar encendido", estado );
            }
        }
    }

    /**
     * Prueba la asignaci�n en una posici�n que no exista
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAsignar2( )
    {
        setupEscenario2( );

        try{
            bitArray.asignar( numeroElementos, true );
            fail("Se debi� arrojar excepci�n");
        }
        catch(IndiceFueraDeRango ifr)
        {
            // Verificar la inicializaci�n del espacio que se ha abierto para adicionar el bit
            boolean estado;
            
            // Verificar el que no se hayan cambiado los valores del bitArray
            for( int cont = 0; cont < numeroElementos; cont++ )
            {
                estado = bitArray.estaEncendido( cont );

                if( cont % 2 != 0 )
                {
                    assertTrue( "El bit deber�a estar encendido", estado );
                }
                else
                {
                    assertFalse( "El bit deber�a estar apagado", estado );
                }

            }
            
            //Verificar el n�mero de elementos
            assertEquals( "El n�mero de bits no es el correcto", numeroElementos, bitArray.darLongitud( ) );
                        
            try
            {
                bitArray.asignar( -1000, false );
                
                fail("Se debi� arrojar excepci�n");
    
            }
            catch (IndiceFueraDeRango ifr2) 
            {
                // Verificar el que no se hayan cambiado los valores del bitArray
                for( int cont = 0; cont < numeroElementos; cont++ )
                {
                    estado = bitArray.estaEncendido( cont );

                    if( cont % 2 != 0 )
                    {
                        assertTrue( "El bit deber�a estar encendido", estado );
                    }
                    else
                    {
                        assertFalse( "El bit deber�a estar apagado", estado );
                    }

                }
                //Verificar el n�mero de elementos
                assertEquals( "El n�mero de bits no es el correcto", numeroElementos, bitArray.darLongitud( ) );       
            }
        }                                
    }

    /**
     * Prueba que la longitud de un bitArray se retorne de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarLongitud( )
    {

        setupEscenario1( );

        // Verificar la longitud
        assertEquals( "El n�mero de bits no es correcto", numeroElementos, bitArray.darLongitud( ) );
        
        setupEscenario2( );
        // Verificar la longitud
        assertEquals( "El n�mero de bits no es correcto", numeroElementos, bitArray.darLongitud( ) );        
    }

    /**
     * Prueba que se indique correctamente cuando un bit esta encendido o no
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEstaEncendido( )
    {

        setupEscenario2( );
        boolean estado;

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            estado = bitArray.estaEncendido( cont );

            if( cont % 2 == 0 )
            {
                assertFalse( "El bit deber�a estar apagado", estado );
            }
            else
            {
                assertTrue( "El bit deber�a estar encendido", estado );
            }
        }        
    }

    /**
     * Prueba que se indique correctamente cuando un bit esta apagado o no
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEstaApagado( )
    {

        setupEscenario2( );
        boolean estado;

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            estado = bitArray.estaApagado( cont );

            if( cont % 2 == 0 )
            {
                assertTrue( "El bit deber�a estar apagado", estado );
            }
            else
            {
                assertFalse( "El bit deber�a estar encendido", estado );
            }
        }
    }

    /**
     * Prueba que se conviertamente un bitstring correctamente a una cadena
     * 
     */
    @SuppressWarnings("unchecked")
    public void testToString( )
    {
        setupEscenario2( );
        String real = bitArray.toString( );
        String esperada;

        StringBuffer bf = new StringBuffer( numeroElementos );
        bf.append( "[" + numeroElementos + "]:" );
        for( int i = 0; i < numeroElementos; i++ )
        {
            if( i % 2 != 0 )
                bf.append( "1" );
            else
                bf.append( "0" );
        }
        esperada = bf.toString( );

        assertEquals( "Las cadenas deber�an ser iguales", esperada, real );

        setupEscenario1( );
        real = bitArray.toString( );
        bf = new StringBuffer( numeroElementos );
        bf.append( "[" + numeroElementos + "]:" );
        
        for( int i = 0; i < numeroElementos; i++ )
        {            
            bf.append( "0" );
        }
        esperada = bf.toString( );
        assertEquals( "Las cadenas deber�an ser iguales", esperada, real );
    }

    /**
     * Prueba que el m�todo equals funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEquals( )
    {

        setupEscenario2( );
        BitArray bs = new BitArray( numeroElementos );
        assertFalse( "Los bitArray no deber�an ser iguales", bs.equals( bitArray ) );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            bs.asignar(cont, cont % 2 != 0 );
        }

        assertTrue( "Los bitArray deber�an ser iguales", bs.equals( bitArray ) );

        bs = new BitArray( numeroElementos*2 );

        assertFalse( "Los bitArray no deber�an ser iguales", bs.equals( bitArray ) );
    }
}
