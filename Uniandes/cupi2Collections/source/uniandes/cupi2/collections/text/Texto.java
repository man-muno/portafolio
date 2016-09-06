/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: J. Villalobos - 07-mar-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.text;

import java.io.*;

import uniandes.cupi2.collections.text.huffman.*;

/**
 * Representa un texto
 */
public class Texto
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Numero m�ximo de caracteres del c�digo usado en el texto (256 supone ASCII)
     */
    public final static int MAX_CHAR = 256;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Texto contenido en el objeto
     */
    private StringBuilder texto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo texto vac�o. <br>
     * <b>post: </b> Se construy� un texto vac�o.
     */
    public Texto( )
    {
        texto = null;
    }

    /**
     * Crea un nuevo texto con un valor inicial establecido. <br>
     * <b>post: </b> Se construy� un texto con el valor inicial especificado. <br>
     * @param textoInicial Valor inicial para el texto
     */
    public Texto( String textoInicial )
    {
        texto = new StringBuilder( textoInicial );
    }

    /**
     * Crea un nuevo texto con un valor inicial establecido. <br>
     * <b>post: </b> Se construy� un texto con el valor inicial especificado.
     * @param textoInicial Valor inicial para el texto
     */
    public Texto( StringBuilder textoInicial )
    {
        texto = textoInicial;
    }

    /**
     * Crea un nuevo texto a partir del archivo especificado. <br>
     * <b>post: </b> Se construy� un texto con el valor inicial le�do del archivo especificado.
     * @param archivo Archivo del que se va a cargar el texto
     */
    public Texto( File archivo ) throws IOException
    {
        texto = new StringBuilder( );
        BufferedReader in = new BufferedReader( new FileReader( archivo ) );
        String linea = in.readLine( );
        while( linea != null )
        {
            texto.append( linea );

            linea = in.readLine( );

            if( linea != null )
            {
                texto.append( "\r\n" );
            }
        }
        in.close( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Define el texto que se va a procesar. <br>
     * <b>post: </b> Se cambi� el texto a ser procesado.
     * @param textoProcesar Texto que se va a procesar
     */
    public void cambiarTexto( String textoProcesar )
    {
        texto = new StringBuilder( textoProcesar );
    }

    /**
     * Retorna la longitud del texto. <br>
     * <b>post: </b> Se retorn� la longitud del texto.
     * @return La longitud del texto
     */
    public int darLongitud( )
    {
        return texto == null ? 0 : texto.length( );
    }

    /**
     * Devuelve el StringBuilder con el texto. <br>
     * <b>post: </b> Se retorn� el StringBuilder con el texto.
     * @return El StringBuilder con el texto
     */
    public StringBuilder darStringBuilder( )
    {
        return texto;
    }

    /**
     * Indica si dos textos son guales. <br>
     * <b>post: </b> Se retorn� tru si el texto actual es igual al especificado o false en caso contrario.
     * @param txt Texto con el que se va a realizar la comparaci�n.
     * @return True si el texto actual es igual al especificado o false en caso contrario
     */
    public boolean equals( Texto txt )
    {
        return texto.toString( ).equals( txt.texto.toString( ) );
    }

    /**
     * Comprime el texto utilizando el algoritmo de Huffman. <br>
     * <b>post: </b> Se comprimi� el texto utilizando el algortimo de Huffman.
     * @return Resultado de la compresi�n
     */
    public TextoCompactoHuffman huffman( )
    {
        if( texto == null )
            return null;
        // Inicializa el arreglo de nodos de la estructura
        NodoHuffman[] nodos = new NodoHuffman[MAX_CHAR];
        for( int i = 0; i < MAX_CHAR; i++ )
        {
            nodos[ i ] = new NodoHuffman( ( char )i );
        }
        // Calcula la frecuencia de cada caracter del texto, suponiendo que
        // todos los valores est�n entre 0 y MAX_CHAR-1.
        for( int i = 0; i < texto.length( ); i++ )
        {
            nodos[ texto.charAt( i ) ].registrarNuevo( );
        }
        // Crea una lista ordenada por frecuencia con los nodos de los
        // caracteres que tengan al menos una ocurrencia y cuenta el n�mero
        // de caracteres distintos presentes
        ListaHuffman lista = new ListaHuffman( );
        int numCaracteresDistintos = 0;
        for( int i = 0; i < MAX_CHAR; i++ )
        {
            if( nodos[ i ].darFrecuencia( ) > 0 )
            {
                lista.insertarOrdenado( nodos[ i ] );
                numCaracteresDistintos++;
            }
        }
        // Considerando solo los nodos que se encuentran en la lista, genera el
        // �rbol siguiendo el algoritmo de Huffman y le genera el c�digo respectivo
        // a cada caracter
        lista.generarArbolHuffman( );

        // Crea el objeto que representa el texto compacto y coloca all� los caracteres
        // presentes con su respectivo c�digo
        TextoCompactoHuffman tc = new TextoCompactoHuffman( numCaracteresDistintos );
        for( int i = 0; i < MAX_CHAR; i++ )
        {
            if( nodos[ i ].darFrecuencia( ) > 0 )
                tc.agregarCaracterTabla( nodos[ i ].darInfoCaracter( ) );
        }
        // Escribe cada caracter del texto original usando el c�digo calculado
        for( int i = 0; i < texto.length( ); i++ )
        {
            tc.agregarCaracterTexto( nodos[ texto.charAt( i ) ].darInfoCaracter( ) );
        }
        // Devuelve el texto compacto
        return tc;
    }

    /**
     * Salva el texto en el archivo especificado. <br>
     * <b>pre: </b> archivo!=null. <br>
     * <b>post: </b> Se salv� el texto en el archivo especificado.
     * @param archivo Archivo en el que se va a guardar el texto
     * @throws IOException Si hubo problemas al guardar el texto en el archivo dado
     */
    public void salvar( File archivo ) throws IOException
    {
        PrintWriter out = new PrintWriter( archivo );
        out.print( texto.toString( ) );
        out.close( );
    }

    /**
     * Convierte el texto a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n del texto en String.
     * @return La representaci�n en String del texto
     */
    @Override
    public String toString( )
    {
        return texto.toString( );
    }
}