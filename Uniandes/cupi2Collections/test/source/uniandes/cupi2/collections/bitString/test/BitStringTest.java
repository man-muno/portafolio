package uniandes.cupi2.collections.bitString.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import uniandes.cupi2.collections.bitString.BitString;
import junit.framework.TestCase;

/**
 * Esta es la clase usada para verificar los métodos de la clase BitString
 */
public class BitStringTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se harán las pruebas
     */
    private BitString bitString;

    /**
     * El número de elementos a manejar en cada escenario
     */
    private int numeroElementos;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Construye un bitstring vacio
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
        bitString = new BitString( );
        numeroElementos = 0;
    }

    /**
     * Construye un bitstring con 1000 elementos Posiciones pares con false y posiciones impares con true
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario2( )
    {
        bitString = new BitString( );
        numeroElementos = 1000;

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            bitString.concatenar( cont % 2 != 0 );
        }

    }

    /**
     * Prueba que la concatenación de elementos se esté realizando de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testConcatenar1( )
    {
        setupEscenario1( );
        boolean estado;
        for( int cont = 0; cont < 20; cont++ )
        {
            estado = cont % 2 == 0;
            bitString.concatenar( estado );

        }

        // Verificar el número de elementos
        assertEquals( "El número de bits no es el correcto", 20, bitString.darLongitud( ) );

        // Verificar que los elementos se hayan concatenado correctamente
        for( int cont = 0; cont < 20; cont++ )
        {
            estado = bitString.estaEncendido( cont );
            if( cont % 2 == 0 )
            {
                assertTrue( "El bit debería estar encendido", estado );
            }
            else
            {
                assertFalse( "El bit debería estar apagado", estado );
            }

            estado = bitString.estaApagado( cont );

            if( cont % 2 == 0 )
            {
                assertFalse( "El bit debería estar encendido", estado );
            }
            else
            {
                assertTrue( "El bit debería estar apagado", estado );
            }

        }
    }

    /**
     * Prueba la concatenación de dos bitstring
     * 
     */
    @SuppressWarnings("unchecked")
    public void testConcatenar2( )
    {
        setupEscenario2( );
        boolean estado;
        BitString bitString2 = new BitString( );
        for( int cont = 0; cont < 1000; cont++ )
        {
            estado = cont % 2 == 0;
            bitString2.concatenar( estado );

        }

        bitString.concatenar( bitString2 );

        // Verificar el número de elementos
        assertEquals( "El número de bits no es el correcto", numeroElementos + 1000, bitString.darLongitud( ) );

        // Verificar que los elementos se hayan concatenado correctamente
        for( int cont = 0; cont < numeroElementos + 1000; cont++ )
        {
            estado = bitString.estaEncendido( cont );

            if( cont >= numeroElementos )
            {

                if( cont % 2 == 0 )
                {
                    assertTrue( "El bit debería estar encendido", estado );
                }
                else
                {
                    assertFalse( "El bit debería estar apagado", estado );
                }
            }
            else
            {
                if( cont % 2 == 0 )
                {
                    assertFalse( "El bit debería estar encendido", estado );
                }
                else
                {
                    assertTrue( "El bit debería estar apagado", estado );
                }
            }
        }

        // Concatenar un bitString vacio a un bitString con elementos
        BitString bitString3 = new BitString( );
        bitString.concatenar( bitString3 );

        // Verificar el número de elementos
        assertEquals( "El número de bits no es el correcto", numeroElementos + 1000, bitString.darLongitud( ) );

        // Concatener un bitString con elementos a un bitstring vacio
        setupEscenario1( );
        bitString.concatenar( bitString2 );

        assertEquals( "El número de bits no es el correcto", 1000, bitString.darLongitud( ) );

    }

    /**
     * Prueba la asignación se haga de forma correcta
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAsignar1( )
    {
        setupEscenario2( );

        for( int cont = 0; cont < numeroElementos; cont += 2 )
        {
            bitString.asignar( cont, true );
        }

        // Verificar que las asignaciones se hayan realizado de forma correcta
        boolean estado;
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            estado = bitString.estaEncendido( cont );

            assertTrue( "El bit debería estar prendido", estado );
        }

        // Poner todas las posiciones impares en false
        for( int cont = 1; cont < numeroElementos; cont += 2 )
        {
            estado = bitString.estaEncendido( cont );

            bitString.asignar( cont, false );
        }

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            estado = bitString.estaApagado( cont );

            if( cont % 2 != 0 )
            {
                assertTrue( "El bit debería estar apagado", estado );
            }
            else
            {
                assertFalse( "El bit debería estar encendido", estado );
            }
        }
    }

    /**
     * Prueba la asignación en una posición que no exista
     * 
     */
    @SuppressWarnings("unchecked")
    public void testAsignar2( )
    {
        setupEscenario2( );

        bitString.asignar( numeroElementos + 999, true );

        // Verificar el número de elementos
        assertEquals( "El número de bits no es el correcto", numeroElementos + 1000, bitString.darLongitud( ) );

        // Verificar la inicialización del espacio que se ha abierto para adicionar el bit
        boolean estado;
        for( int cont = numeroElementos; cont < numeroElementos + 1000; cont++ )
        {
            estado = bitString.estaApagado( cont );

            if( cont != numeroElementos + 999 )
            {
                assertTrue( "El bit debería estar apagado", estado );
            }
            else
            {
                assertFalse( "El bit debería estar encendido", estado );
            }

        }

        // Verificar el resto del bitString
        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            estado = bitString.estaEncendido( cont );

            if( cont % 2 != 0 )
            {
                assertTrue( "El bit debería estar encendido", estado );
            }
            else
            {
                assertFalse( "El bit debería estar apagado", estado );
            }

        }

        setupEscenario1( );
        bitString.asignar( numeroElementos + 499, false );
        for( int cont = 0; cont < 500; cont++ )
        {

            estado = bitString.estaApagado( cont );
            assertTrue( "El bit debería estar apagado", estado );

        }

        // Verificar lo que ocurre con un índice negativo
        bitString.asignar( -1000, false );

        // Verificar el número de elementos
        assertEquals( "El número de bits no es el correcto", 500, bitString.darLongitud( ) );

    }

    /**
     * Prueba que la longitud de un bitString se retorne de forme correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarLongitud( )
    {

        setupEscenario1( );

        // Verificar la longitud
        assertEquals( "El número de bits no es correcto", numeroElementos, bitString.darLongitud( ) );

        bitString.asignar( 1000, false );
        assertEquals( "El número de bits no es correcto", numeroElementos + 1001, bitString.darLongitud( ) );

        setupEscenario2( );
        // Verificar la longitud
        assertEquals( "El número de bits no es correcto", numeroElementos, bitString.darLongitud( ) );

        bitString.concatenar( false );
        assertEquals( "El número de bits no es correcto", numeroElementos + 1, bitString.darLongitud( ) );

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
            estado = bitString.estaEncendido( cont );

            if( cont % 2 == 0 )
            {
                assertFalse( "El bit debería estar apagado", estado );
            }
            else
            {
                assertTrue( "El bit debería estar encendido", estado );
            }
        }

        bitString.concatenar( true );
        bitString.concatenar( true );
        bitString.concatenar( false );

        estado = bitString.estaEncendido( numeroElementos );
        assertTrue( "El bit debería estar encendido", estado );
        estado = bitString.estaEncendido( numeroElementos + 1 );
        assertTrue( "El bit debería estar encendido", estado );
        estado = bitString.estaEncendido( numeroElementos + 2 );
        assertFalse( "El bit debería estar apagado", estado );
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
            estado = bitString.estaApagado( cont );

            if( cont % 2 == 0 )
            {
                assertTrue( "El bit debería estar apagado", estado );
            }
            else
            {
                assertFalse( "El bit debería estar encendido", estado );
            }
        }

        bitString.concatenar( true );
        bitString.concatenar( true );
        bitString.concatenar( false );

        estado = bitString.estaApagado( numeroElementos );
        assertFalse( "El bit debería estar encendido", estado );
        estado = bitString.estaApagado( numeroElementos + 1 );
        assertFalse( "El bit debería estar encendido", estado );
        estado = bitString.estaApagado( numeroElementos + 2 );
        assertTrue( "El bit debería estar apagado", estado );
    }

    /**
     * Prueba que se salve correctamente un bitstring
     * 
     */
    @SuppressWarnings("unchecked")
    public void testSalvarCargar( )
    {
        setupEscenario2( );

        FileOutputStream fos;
        FileInputStream fis;
        try
        {
            fos = new FileOutputStream( "./test/data/pruebaSalvarBitString.dat" );
            DataOutputStream dos = new DataOutputStream( fos );
            bitString.salvar( dos );
            dos.close( );
            fos.close( );

            fis = new FileInputStream( "./test/data/pruebaSalvarBitString.dat" );

            DataInputStream dis = new DataInputStream( fis );
            BitString bs = new BitString( dis );

            assertTrue( "Las bitstring deberían ser iguales", bitString.equals( bs ) );

            dis.close( );
            fis.close( );

            // Salvar un bitString vacio y volverlo a cargar
            setupEscenario1( );
            fos = new FileOutputStream( "./test/data/pruebaSalvarBitString2.dat" );
            dos = new DataOutputStream( fos );
            bitString.salvar( dos );
            dos.close( );
            fos.close( );

            fis = new FileInputStream( "./test/data/pruebaSalvarBitString2.dat" );

            dis = new DataInputStream( fis );
            bs = new BitString( dis );

            assertTrue( "Las bitstring deberían ser iguales", bitString.equals( bs ) );

            dis.close( );
            fis.close( );

        }
        catch( FileNotFoundException e )
        {
            e.printStackTrace( );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
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
        String real = bitString.toString( );
        String esperada;

        StringBuffer bf = new StringBuffer( numeroElementos + 10 );
        bf.append( "[" + numeroElementos + "]:" );
        for( int i = 0; i < numeroElementos; i++ )
        {
            if( i % 2 != 0 )
                bf.append( "1" );
            else
                bf.append( "0" );
        }
        esperada = bf.toString( );

        assertEquals( "Las cadenas deberían ser iguales", esperada, real );

        setupEscenario1( );
        real = bitString.toString( );
        bf = new StringBuffer( numeroElementos + 10 );
        bf.append( "[" + numeroElementos + "]:" );
        esperada = bf.toString( );
        assertEquals( "Las cadenas deberían ser iguales", esperada, real );
    }

    /**
     * Prueba que el método equals funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testEquals( )
    {

        setupEscenario2( );
        BitString bs = new BitString( );
        assertFalse( "Los bitStrings no deberían ser iguales", bs.equals( bitString ) );

        for( int cont = 0; cont < numeroElementos; cont++ )
        {
            bs.concatenar( cont % 2 != 0 );
        }

        assertTrue( "Los bitStrings deberían ser iguales", bs.equals( bitString ) );

        bs.concatenar( false );

        assertFalse( "Los bitStrings no deberían ser iguales", bs.equals( bitString ) );
    }

    /**
     * Prueba que el método vaciar funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testVaciar( )
    {
        setupEscenario2( );
        bitString.vaciar( );
        assertEquals( "El tamaño debería ser cero", 0, bitString.darLongitud( ) );

        setupEscenario1( );
        bitString.vaciar( );
        assertEquals( "El tamaño debería ser cero", 0, bitString.darLongitud( ) );

        bitString.asignar( 2500, true );
        bitString.vaciar( );
        assertEquals( "El tamaño debería ser cero", 0, bitString.darLongitud( ) );

    }

}
