/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: TextoCompactoHuffman.java,v 1.6 2006/06/02 19:37:16 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Pablo Barvo - Mar 24, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.text.huffman;

import java.io.*;

import uniandes.cupi2.collections.arbolBinario.*;
import uniandes.cupi2.collections.bitString.*;
import uniandes.cupi2.collections.text.*;

/**
 * Contiene el resultado de realizar la compresi�n de un texto con el algoritmo de Huffman
 */
public class TextoCompactoHuffman
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Secuencia de bits que representa el texto compacto
     */
    private BitString bits;

    /**
     * Tabla con la informaci�n de los caracteres presentes y su respectivo c�digo
     */
    private InfoCaracter[] tabla;

    /**
     * El n�mero de caracteres presentes en la compresi�n
     */
    private short numCaracteres;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de texto compacto vacio con la capacidad especificada. <br>
     * <b>post: </b> Se construy� un texto compacto vac�o con la capacidad especificada.
     * @param totalCaracteres La cantidad de caracteres que puede contener el texto compacto
     */
    public TextoCompactoHuffman( int totalCaracteres )
    {
        bits = new BitString( );
        tabla = new InfoCaracter[totalCaracteres];
        numCaracteres = 0;
    }

    /**
     * Constructor de texto compacto cargando la informaci�n de un archivo. <br>
     * <b>post: </b> Se construy� un texto compacto a partir del archivo dado.
     * @param archivo El archivo a partir del cual se va a crear el texto compacto
     * @throws Si hubo problemas al cargar el texto compacto del archivo
     */
    public TextoCompactoHuffman( File archivo ) throws IOException
    {
        DataInputStream in = new DataInputStream( new FileInputStream( archivo ) );
        numCaracteres = in.readShort( );
        tabla = new InfoCaracter[numCaracteres];
        for( int i = 0; i < numCaracteres; i++ )
        {
            tabla[ i ] = new InfoCaracter( in );
        }
        bits = new BitString( in );
        in.close( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega un car�cter a la tabla del texto compacto. <br>
     * <b>post: </b>Se agreg� el car�cter a la tabla.
     * @param info infoCaracter con del car�cter (y su c�digo respectivo) para ser adicionada a la tabla
     */
    public void agregarCaracterTabla( InfoCaracter info )
    {
        tabla[ numCaracteres++ ] = info;
    }

    /**
     * Agrega un car�cter al texto compacto. <br>
     * <b>post: </b>Se agreg� el car�cter al texto compacto.
     * @param info infoCaracter con el c�digo del car�cter a ser adicionado al texto compacto
     */
    public void agregarCaracterTexto( InfoCaracter info )
    {
        bits.concatenar( info.darCodigo( ) );
    }

    /**
     * Descomprime el texto compacto. <br>
     * <b>post: </b> Se descomprimi� el texto compacto y se retorno el resultado.
     * @return Texto descomprimido
     */
    public Texto descomprimir( )
    {
        // Reconstruye el �rbol de Huffman a partir de los c�digos asignados a los caracteres
        ArbolBinario<Character> arbolHuffman = recontruirArbol( );
        // Recorre el c�digo del texto compacto al mismo tiempo que viaja por el �rbol de Huffman.
        // Cada vez que llega a una hoja, agrega dicho caracter al texto de respuesta
        StringBuilder buffer = new StringBuilder( );
        NodoArbolBinario<Character> pNodo = arbolHuffman.darRaiz( );
        for( int pos = 0; pos < bits.darLongitud( ); pos++ )
        {
            pNodo = bits.estaEncendido( pos ) ? pNodo.darDerecho( ) : pNodo.darIzquierdo( );
            if( pNodo.esHoja( ) )
            {
                buffer.append( pNodo.darElemento( ).charValue( ) );
                pNodo = arbolHuffman.darRaiz( );
            }
        }
        return new Texto( buffer );
    }

    /**
     * Salva el texto compacto en el archivo especificado. <br>
     * <b>post: </b> Se salv� el texto en el archivo especificado.
     * @throws IOException Si hubo alg�n problema al salvar el texto
     */
    public void salvar( File archivo ) throws IOException
    {
        DataOutputStream out = new DataOutputStream( new FileOutputStream( archivo ) );
        out.writeShort( numCaracteres );
        for( int i = 0; i < numCaracteres; i++ )
        {
            tabla[ i ].salvar( out );
        }
        bits.salvar( out );
        out.close( );
    }

    /**
     * Retorna el bitString con los c�digos del texto compacto. <br>
     * <b>post: </b>Se retorn� el bisString del texto.
     * @return El bisString del texto
     */
    public BitString darBitString( )
    {
        return bits;
    }

    /**
     * Convierte el texto compacto a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n del texto compacto en String.
     * @return La representaci�n en String del texto
     */
    @Override
    public String toString( )
    {
        String resp = "";
        for( int i = 0; i < numCaracteres; i++ )
        {
            resp += tabla[ i ] + "\n";
        }
        return resp + bits;
    }

    /**
     * A partir de la tabla de c�digos reconstruye el �rbol de Huffman. En cada hoja del �rbol aparece un caracter. El c�digo asociado a dicho caracter aparece expresado como
     * la concatenaci�n de las asociaciones padre-hijo, teniendo en cuenta que cuando la rama va por el hijo izquierdo se agrega un '0' y cuando la rama va por el hijo derecho
     * se agrega un '1'.<br>
     * <b>post: </b> Se retorn� el �rbol de Huffman reconstruido.
     * @return El �rbol de Huffman reconstruido
     */
    private ArbolBinario<Character> recontruirArbol( )
    {
        ArbolBinario<Character> arbol = new ArbolBinario<Character>( );
        NodoArbolBinario<Character> raiz = new NodoArbolBinario<Character>( null );
        arbol.definirRaiz( raiz );
        for( int i = 0; i < numCaracteres; i++ )
        {
            agregarHoja( raiz, tabla[ i ] );
        }
        return arbol;
    }

    /**
     * Agrega una hoja al �rbol de Huffman con el caracter especificado. <br>
     * <b>post: </b> Se agreg� una hoja al �rbol de Huffman. <br>
     * @param raiz Ra�z del �rbol de Huffman
     * @param car Caracter a ser adicionado
     * 
     */
    public void agregarHoja( NodoArbolBinario<Character> raiz, InfoCaracter car )
    {
        // Recorre el c�digo asignado al caracter y va reconstruyendo la rama del �rbol del cual es una hoja
        BitString cod = car.darCodigo( );
        int longitud = cod.darLongitud( );
        for( int pos = 0; pos < longitud; pos++ )
        {
            if( cod.estaEncendido( pos ) )
            {
                // La rama debe seguir por el sub�rbol derecho
                if( raiz.darDerecho( ) != null )
                    raiz = raiz.darDerecho( );
                else
                {
                    // La rama derecha termina en este punto. Debe crearse un nuevo nodo
                    NodoArbolBinario<Character> pNodo = new NodoArbolBinario<Character>( null );
                    raiz.encadenarDerecho( pNodo );
                    raiz = pNodo;
                }
            }
            else
            {
                // La rama debe seguir por el sub�rbol izquierdo
                if( raiz.darIzquierdo( ) != null )
                    raiz = raiz.darIzquierdo( );
                else
                {
                    // La rama izquierda termina en este punto. Debe crearse un nuevo nodo
                    NodoArbolBinario<Character> pNodo = new NodoArbolBinario<Character>( null );
                    raiz.encadenarIzquierdo( pNodo );
                    raiz = pNodo;
                }
            }
        }
        // El par�metro ra�z est� haciendo referencia a la hoja en la cual debe incluirse el nuevo caracter
        raiz.asignarElemento( new Character( car.darCaracter( ) ) );
    }
}
