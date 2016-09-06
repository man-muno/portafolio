/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BitString.java,v 1.17 2006/08/30 17:44:39 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.bitString;

import java.io.*;

/**
 * Un BitString es un arreglo de bits de longitud variable, cada uno de cuyos elementos se referencia por su posición. Las posiciones se numeran de manera consecutiva
 * comenzando en 0.
 */
public class BitString
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Representación como bytes de los elementos
     */
    private byte[] bits;

    /**
     * Número de bits presentes en la estructura
     */
    private int numBits;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Contructor del bitString vacío. <br>
     * <b>post: </b> Se construyó un bitString vacío.
     */
    public BitString( )
    {
        bits = null;
        numBits = 0;
    }

    /**
     * Construye un bitString a partir de otro bitString. <br>
     * <b>post: </b> Se construyó un bitString a partir del bitString especificado.
     * @param bs BitString a partir del cual se va a crear un nuevo bitString
     */
    public BitString( BitString bs )
    {
        if( bs.bits == null )
        {
            bits = null;
            numBits = 0;
        }
        else
        {
            bits = new byte[bs.bits.length];
            for( int i = 0; i < bs.bits.length; i++ )
                bits[ i ] = bs.bits[ i ];
            numBits = bs.numBits;
        }
    }

    /**
     * Construye un bitString a partir del flujo especificado. <br>
     * <b>pre: </b> in!= null. <br>
     * <b>post: </b> Se construyó un bitString a partir del flujo especificado.
     * @param in Flujo del que se va a cargar el bitString
     * @throws IOException Si hay problemas al leer el flujo
     */
    public BitString( DataInputStream in ) throws IOException
    {
        numBits = in.readInt( );
        int nBytes = ( int )Math.ceil( ( double )numBits / 8 );
        bits = new byte[nBytes];
        in.read( bits, 0, nBytes );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Borra todos los elementos presentes en el bitString. <br>
     * <b>post: </b> bits= null, numBits= 0.
     */
    public void vaciar( )
    {
        bits = null;
        numBits = 0;
    }

    /**
     * Retorna la longitud (número de bits) del bitString. <br>
     * <b>post: </b> Se retornó la longitud del BitString.
     * @return Longitud del BitString
     */
    public int darLongitud( )
    {
        return numBits;
    }

    /**
     * Indica si el bit en la posición especificada se encuentra encendido (en 1). <br>
     * <b>post: </b> Se retornó true si el bit en la posición indicada se encuentra encendido o false de lo contrario. Si posBit>=numBits se retorna false.
     * @param posBit Posición dentro del BitString del bit cuyo valor se quiere establecer
     * @return True si el bit en la posición indicada se encuentra encendido o false de lo contrario
     */
    public boolean estaEncendido( int posBit )
    {
        // Verifica si dicha posición está en la secuencia de bits
        if( bits == null || posBit >= numBits )
            return false;
        // Localiza el byte en el que se debería encontrar el bit
        int posByte = posBit / 8;
        // Crea la máscara para extraer el respectivo bit
        int posLocal = posBit % 8;
        byte mascara = 1;
        for( int i = 0; i < posLocal; i++ )
        {
            mascara *= 2;
        }
        // Aplica la máscara sobre el respectivo byte
        int resp = bits[ posByte ] & mascara;
        return resp != 0;
    }

    /**
     * Indica si el bit en la posición especificada se encuentra apagado (en 0). <br>
     * <b>post: </b> Se retornó true si el bit en la posición indicada se encuentra apagado o false de lo contrario. Si posBit>=numBits se retorna false.
     * @param posBit Posición dentro del BitString del bit cuyo valor se quiere establecer
     * @return True si el bit en la posición indicada se encuentra apagado o false de lo contrario
     */
    public boolean estaApagado( int posBit )
    {
        return !estaEncendido( posBit );
    }

    /**
     * Asigna el valor dado en la posición especificada dentro del bitString. <br>
     * <b>pre: </b> posBit!=null, posBit>=0, bit!=null. <br>
     * <b>post: </b> Se asignó el valor dao al bit en la posición especificada. Si posBit sobrepasa el tamaño de la representación, éste es ampliado de tal forma que se le
     * pueda dar el valor especificado al bit en la posición dada y los nuevos bits creados son inicializados en 0.
     * @param posBit Posición del bit cuyo valor va a ser asignado
     * @param bit Valor el bit
     */
    public void asignar( int posBit, boolean bit )
    {
        // Localiza el byte en el que se debería encontrar el bit
        int posByte = posBit / 8;
        // Verifica si se debe cambiar el tamaño de la representación
        if( bits == null || posBit >= bits.length * 8 )
        {
            if( bits == null )
            {
                // Se debe crear el arreglo de bytes e inicializar en cero todas las posiciones
                bits = new byte[posByte + 1];
                for( int i = 0; i < posByte + 1; i++ )
                    bits[ i ] = 0;
            }
            else
            {
                // Debe aumentarse el tamaño de la representación, inicializando en 0 el nuevo espacio
                byte[] old = bits;
                bits = new byte[posByte + 1];
                for( int i = 0; i < posByte + 1; i++ )
                    bits[ i ] = i < old.length ? old[ i ] : 0;
            }
        }
        // Se sabe que el byte que representa el bit que se quiere asignar ya tiene una representación explícita
        // Crea la máscara para asignar el respectivo bit
        int posLocal = posBit % 8;
        byte mascara = 1;
        for( int i = 0; i < posLocal; i++ )
        {
            mascara *= 2;
        }
        if( bit && posBit >= 0 )
        {
            // Aplica la máscara sobre el respectivo byte
            bits[ posByte ] = ( byte ) ( bits[ posByte ] | mascara );
        }
        else if( posBit >= 0 )
        {
            // Crea la máscara para apagar el respectivo bit
            mascara = ( byte )~mascara;
            // Aplica la máscara sobre el respectivo byte
            bits[ posByte ] = ( byte ) ( bits[ posByte ] & mascara );
        }
        // Recalcula la longitud de la secuencia de bits
        if( posBit >= numBits )
            numBits = posBit + 1;
    }

    /**
     * Concatena el bitString especificado con el bitString actual. <br>
     * <b>pre: </b> bs!= null. <br>
     * <b>post: </b> Se concatenó el bitString especificado con el bitString actual. La contatenación implica que todos los bits de bs son adicionados al final del bitString
     * actual y en el orden el que se encuentran.
     * @param bs BitString con el que se va a realizar la concatenación
     */
    public void concatenar( BitString bs )
    {
        if( bs.numBits != 0 )
        {
            if( bits == null )
            {
                bits = new byte[bs.bits.length];
                for( int i = 0; i < bs.bits.length; i++ )
                    bits[ i ] = bs.bits[ i ];
                numBits = bs.numBits;
            }
            else
            {
                for( int i = 0; i < bs.numBits; i++ )
                    concatenar( bs.estaEncendido( i ) );
            }
        }
    }

    /**
     * Concatena el bit especificado al bitString actual. <br>
     * <b>pre: </b> bit!= null. <br>
     * <b>post: </b> Se concatenó el bit especificado al final del bitString actual.
     * @param bit Bit a ser concatenado
     */
    public void concatenar( boolean bit )
    {
        asignar( numBits, bit );
    }

    /**
     * Salva el bitString en el flujo especificado. <br>
     * <b>pre: </b> out!= null. <br>
     * <b>post: </b> Se salvó el bitString en el flujo especificado.
     * @throws IOException Si hay problemas al tratar de escribir en el flujo especificado
     */
    public void salvar( DataOutputStream out ) throws IOException
    {
        out.writeInt( numBits );

        if( bits != null )
            out.write( bits, 0, bits.length );
    }

    /**
     * Indica si dos bitStrings son iguales. <br>
     * <b>pre: </b> bs!= null. <br>
     * <b>post: </b> Se retornó True si los dos bitString son iguales o false de lo contrario. Dos bitStrings son iguales si su número de bits es igual y además cada uno de
     * los bits que se encuentran en la misma posición presenta igual valor. <br>
     * @param bs BitString con el que se va a realizar la comparación
     * @return True si los dos bitString son iguales o false de lo contrario
     */
    public boolean equals( BitString bs )
    {
        if( numBits != bs.numBits )
            return false;
        else
        {
            // Calcula el número de bytes que debe comparar
            int long1 = Math.min( bits == null ? 0 : bits.length, bs.bits == null ? 0 : bs.bits.length );
            int i = 0;
            for( ; i < long1 && bits[ i ] == bs.bits[ i ]; i++ )
                ;
            return i == long1;
        }
    }

    /**
     * Convierte el bitString a un String. <br>
     * <b>post: </b> Se retornó la representación en String del bitString. El String tiene el formato "[numeroElementos]: b1b2b3..bn", donde b1, b2, ..., bn son los bits del
     * bitString y numeroElementos su tamaño.
     * @return La representación en String del bitString
     */
    @Override
    public String toString( )
    {
        StringBuffer bf = new StringBuffer( numBits + 10 );
        bf.append( "[" + numBits + "]:" );
        for( int i = 0; i < numBits; i++ )
        {
            if( estaEncendido( i ) )
                bf.append( "1" );
            else
                bf.append( "0" );
        }
        return bf.toString( );
    }
}
