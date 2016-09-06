/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BitArray.java,v 1.5 2007/02/20 13:44:41 jvillalo2 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * Autor: Daniel Romero - Septiembre 10, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.bitArray;


/**
 * Un BitArray es un arreglo de bits de longitud fija, cada uno de cuyos elementos se referencia por su posición. Las posiciones se numeran de manera consecutiva,
 * comenzando en 0.
 */
public class BitArray
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
     * Constructor del bitArray vacío. <br>
     * <b>post: </b> Se construyó un bitArray vacío con la longitud especificada.
     * @param nBits Número de bits que contendrá el arreglo. NBits>=0
     */
    public BitArray( int nBits )
    {
        bits = new byte[(nBits/8)+(nBits%8 != 0 ? 1 : 0)];
        numBits = nBits;
    }

    /**
     * Construye un bitArray a partir de otro bitArray. <br>
     * <b>post: </b> Se construyó un bitArray a partir del bitArray especificado.
     * @param ba BitArray a partir del cual se va a crear un nuevo bitArray
     */
    public BitArray( BitArray ba )
    {       
        bits = new byte[ba.bits.length];
        for( int i = 0; i < ba.bits.length; i++ )
        	bits[ i ] = ba.bits[ i ];
        numBits = ba.numBits;        
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Retorna el tamaño (número máximo de bits) del bitArray. <br>
     * <b>post: </b> Se retornó la longitud del BitArray.
     * @return Longitud del BitArray
     */
    public int darLongitud( )
    {
        return numBits;
    }

    /**
     * Indica si el bit en la posición especificada se encuentra encendido (en 1). <br>
     * <b>post: </b> Se retornó true si el bit en la posición indicada se encuentra encendido o false de lo contrario. Si posBit>=numBits se retorna false.
     * @param posBit Posición dentro del BitArray del bit cuyo valor se quiere establecer
     * @return True si el bit en la posición indicada se encuentra encendido o false de lo contrario
     */
    public boolean estaEncendido( int posBit )
    {
        // Verifica si dicha posición está en la secuencia de bits
        if( posBit >= numBits || posBit<0 )
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
     * <b>post: </b> Se retornó true si el bit en la posición indicada se encuentra apagado o false de lo contrario. Si posBit>=numBits <br>
     * o posBit<0 se retorna false.
     * @param posBit Posición dentro del BitArray del bit cuyo valor se quiere establecer
     * @return True si el bit en la posición indicada se encuentra apagado o false de lo contrario
     */
    public boolean estaApagado( int posBit )
    {
        return !estaEncendido( posBit );
    }

    /**
     * Asigna el valor dado en la posición especificada dentro del bitArray. <br>    
     * <b>post: </b> Se asignó el valor dado al bit en la posición especificada. 
     * @param posBit Posición del bit cuyo valor va a ser asignado
     * @param bit Valor el bit
     * @throws IndiceFueraDeRango Si la posición del bit no es un índice válido (posBit>=numBits || posBit<0)
     * 
     */
    public void asignar( int posBit, boolean bit ) 
    {
    	
    	//Verica que la posición del bit no sobrepase la longitud del arreglo
    	if(posBit>=numBits || posBit<0)
    	{
    		throw new IndiceFueraDeRango(posBit);
    	}
    	
        // Localiza el byte en el que se debería encontrar el bit
        int posByte = posBit / 8;
      
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
    }


    /**
     * Indica si dos bitArray son iguales. <br>
     * <b>pre: </b> ba!= null. <br>
     * <b>post: </b> Se retornó true si los dos bitArray son iguales o false de lo contrario. <br> 
     * Dos bitArray son iguales si su número de bits es igual y además cada uno de <br>
     * los bits que se encuentran en la misma posición presenta igual valor. <br>
     * @param ba BitArray con el que se va a realizar la comparación
     * @return True si los dos bitArray son iguales o false de lo contrario
     */
    public boolean equals( BitArray ba )
    {
        if( numBits != ba.numBits )
            return false;
        else
        {
            // Calcula el número de bytes que debe comparar
            int long1 = Math.min( bits == null ? 0 : bits.length, ba.bits == null ? 0 : ba.bits.length );
            int i = 0;
            for( ; i < long1 && bits[ i ] == ba.bits[ i ]; i++ )
                ;
            return i == long1;
        }
    }

    /**
     * Convierte el bitArray a un String. <br>
     * <b>post: </b> Se retornó la representación en String del bitArray. El String tiene el formato "[numeroElementos]: b1b2b3..bn",<br> 
     * donde b1, b2, ..., bn son los bits del bitArray y numeroElementos su tamaño.
     * @return La representación en String del bitArray
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
