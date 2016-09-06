package uniandes.cupi2.collections.text.test;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;
import uniandes.cupi2.collections.text.Texto;
import uniandes.cupi2.collections.text.huffman.TextoCompactoHuffman;

/**
 * Esta es la clase usada para verificar los m�todos de la clase Texto
 */
public class TextoTest extends TestCase
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    public static final String TEXTO1 = "Esto es una prueba del algoritmo de huffman para la compresi�n de texto";
    public static final String TEXTO2 = "Ejemplo ";
    public static final String TEXTO3 = "��";
    public static final String TEXTO4 = "Un texto lo suficientemente largo para probar que las comprensi�n y descompresi�n de todo se este realizando"
            + "apropiadamente sin importar las letras que se est�n usando (como la �) o alg�n otro car�cter en particular";
    
    public static final String TEXTO5 = "��������������������������";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es la clase donde se har�n las pruebas
     */
    private Texto texto;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un texto vacio
     * 
     */
    @SuppressWarnings("unchecked")
    public void setupEscenario1( )
    {
        texto = new Texto( );

    }

    /**
     * Prueba que la comprensi�n y descompresi�n de un texto se realice correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testComprimirDescomprir1( )
    {
        setupEscenario1( );
        texto.cambiarTexto( TEXTO1 );

        // Comprimir el texto
        TextoCompactoHuffman comprimido = texto.huffman( );
        Texto descomprimido = comprimido.descomprimir( );

        // Verificar que los textos sean iguales
        assertEquals( "El texto descomprimido no es correcto", TEXTO1, descomprimido.toString( ) );

        texto.cambiarTexto( TEXTO2 );

        comprimido = texto.huffman( );
        descomprimido = comprimido.descomprimir( );

        // Verificar que los textos sean iguales
        assertEquals( "El texto descomprimido no es correcto", TEXTO2, descomprimido.toString( ) );

        texto.cambiarTexto( TEXTO3 );

        comprimido = texto.huffman( );
        descomprimido = comprimido.descomprimir( );

        // Verificar que los textos sean iguales
        assertEquals( "El texto descomprimido no es correcto", TEXTO3, descomprimido.toString( ) );
    }

    /**
     * Prueba que la comprensi�n y descompresi�n de un texto se realice correctamente usando un archivo para salvar y recuperar el texto comprimido
     * 
     */
    @SuppressWarnings("unchecked")
    public void testComprimirDescomprir2( )
    {
        setupEscenario1( );
        texto.cambiarTexto( TEXTO4 );

        TextoCompactoHuffman comprimido = texto.huffman( );
        File f = new File( "./test/data/pruebaHuffman.dat" );
        try
        {
            comprimido.salvar( f );

            TextoCompactoHuffman comprimido2 = new TextoCompactoHuffman( f );

            Texto descomprimido = comprimido2.descomprimir( );

            // Verificar que los textos sean iguales
            assertEquals( "El texto descomprimido no es el correcto", TEXTO4, descomprimido.toString( ) );

            // Verificar que los dos textos comprimidos sean iguales
            assertEquals( "El texto comprimido no es el correcto", comprimido.toString( ), comprimido2.toString( ) );

        }
        catch( IOException e )
        {

            e.printStackTrace( );
        }
    }
    
    /**
     * Prueba que la comprensi�n y descompresi�n de un texto se realice correctamente usando un archivo para salvar y recuperar el texto comprimido
     * 
     */
    @SuppressWarnings("unchecked")
    public void testComprimirDescomprir3( )
    {
        setupEscenario1( );
        texto.cambiarTexto( TEXTO5 );

        TextoCompactoHuffman comprimido = texto.huffman( );
        File f = new File( "./test/data/pruebaHuffman2.dat" );
        try
        {
            comprimido.salvar( f );

            TextoCompactoHuffman comprimido2 = new TextoCompactoHuffman( f );

            Texto descomprimido = comprimido2.descomprimir( );

            // Verificar que los textos sean iguales
            assertEquals( "El texto descomprimido no es el correcto", TEXTO5, descomprimido.toString( ) );

            // Verificar que los dos textos comprimidos sean iguales
            assertEquals( "El texto comprimido no es el correcto", comprimido.toString( ), comprimido2.toString( ) );

        }
        catch( IOException e )
        {

            e.printStackTrace( );
        }
    }

    /**
     * Prueba que la longitud del texto se este retornando correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testDarLongitud( )
    {
        setupEscenario1( );

        // Verificar el tama�o del texto
        texto.cambiarTexto( TEXTO1 );
        assertEquals( "El tama�o del texto no es correcto", TEXTO1.length( ), texto.darLongitud( ) );

        // Verificar el tama�o del texto
        texto.cambiarTexto( TEXTO2 );
        assertEquals( "El tama�o del texto no es correcto", TEXTO2.length( ), texto.darLongitud( ) );

        // Verificar el tama�o del texto
        texto.cambiarTexto( TEXTO3 );
        assertEquals( "El tama�o del texto no es correcto", TEXTO3.length( ), texto.darLongitud( ) );

        // Verificar el tama�o del texto
        texto.cambiarTexto( TEXTO4 );
        assertEquals( "El tama�o del texto no es correcto", TEXTO4.length( ), texto.darLongitud( ) );

    }

    /**
     * Prueba que el m�todo salvar de Texto funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testSalvarCargar( )
    {
        setupEscenario1( );
        texto.cambiarTexto( TEXTO4 );
        File f = new File( "./test/data/pruebaHuffman2.dat" );

        try
        {
            texto.salvar( f );
            Texto t = new Texto( f );

            // Verificar que los textos sean iguales
            assertEquals( "Los textos no son iguales", TEXTO4, t.toString( ) );

        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Prueba que el m�todo toString de Texto funcione correctamente
     * 
     */
    @SuppressWarnings("unchecked")
    public void testToString( )
    {

    }
}
