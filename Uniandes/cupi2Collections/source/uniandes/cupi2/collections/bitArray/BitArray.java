/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BitArray.java,v 1.5 2007/02/20 13:44:41 jvillalo2 Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Un BitArray es un arreglo de bits de longitud fija, cada uno de cuyos elementos se referencia por su posici�n. Las posiciones se numeran de manera consecutiva,
 * comenzando en 0.
 */
public class BitArray
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Representaci�n como bytes de los elementos
     */
    private byte[] bits;

    /**
     * N�mero de bits presentes en la estructura
     */
    private int numBits;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del bitArray vac�o. <br>
     * <b>post: </b> Se construy� un bitArray vac�o con la longitud especificada.
     * @param nBits N�mero de bits que contendr� el arreglo. NBits>=0
     */
    public BitArray( int nBits )
    {
        bits = new byte[(nBits/8)+(nBits%8 != 0 ? 1 : 0)];
        numBits = nBits;
    }

    /**
     * Construye un bitArray a partir de otro bitArray. <br>
     * <b>post: </b> Se construy� un bitArray a partir del bitArray especificado.
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
    // M�todos
    // -----------------------------------------------------------------
    
    /**
     * Retorna el tama�o (n�mero m�ximo de bits) del bitArray. <br>
     * <b>post: </b> Se retorn� la longitud del BitArray.
     * @return Longitud del BitArray
     */
    public int darLongitud( )
    {
        return numBits;
    }

    /**
     * Indica si el bit en la posici�n especificada se encuentra encendido (en 1). <br>
     * <b>post: </b> Se retorn� true si el bit en la posici�n indicada se encuentra encendido o false de lo contrario. Si posBit>=numBits se retorna false.
     * @param posBit Posici�n dentro del BitArray del bit cuyo valor se quiere establecer
     * @return True si el bit en la posici�n indicada se encuentra encendido o false de lo contrario
     */
    public boolean estaEncendido( int posBit )
    {
        // Verifica si dicha posici�n est� en la secuencia de bits
        if( posBit >= numBits || posBit<0 )
            return false;
        // Localiza el byte en el que se deber�a encontrar el bit
        int posByte = posBit / 8;
        // Crea la m�scara para extraer el respectivo bit
        int posLocal = posBit % 8;
        byte mascara = 1;
        for( int i = 0; i < posLocal; i++ )
        {
            mascara *= 2;
        }
        // Aplica la m�scara sobre el respectivo byte
        int resp = bits[ posByte ] & mascara;
        return resp != 0;
    }

    /**
     * Indica si el bit en la posici�n especificada se encuentra apagado (en 0). <br>
     * <b>post: </b> Se retorn� true si el bit en la posici�n indicada se encuentra apagado o false de lo contrario. Si posBit>=numBits <br>
     * o posBit<0 se retorna false.
     * @param posBit Posici�n dentro del BitArray del bit cuyo valor se quiere establecer
     * @return True si el bit en la posici�n indicada se encuentra apagado o false de lo contrario
     */
    public boolean estaApagado( int posBit )
    {
        return !estaEncendido( posBit );
    }

    /**
     * Asigna el valor dado en la posici�n especificada dentro del bitArray. <br>    
     * <b>post: </b> Se asign� el valor dado al bit en la posici�n especificada. 
     * @param posBit Posici�n del bit cuyo valor va a ser asignado
     * @param bit Valor el bit
     * @throws IndiceFueraDeRango Si la posici�n del bit no es un �ndice v�lido (posBit>=numBits || posBit<0)
     * 
     */
    public void asignar( int posBit, boolean bit ) 
    {
    	
    	//Verica que la posici�n del bit no sobrepase la longitud del arreglo
    	if(posBit>=numBits || posBit<0)
    	{
    		throw new IndiceFueraDeRango(posBit);
    	}
    	
        // Localiza el byte en el que se deber�a encontrar el bit
        int posByte = posBit / 8;
      
        // Se sabe que el byte que representa el bit que se quiere asignar ya tiene una representaci�n expl�cita
        // Crea la m�scara para asignar el respectivo bit
        int posLocal = posBit % 8;
        byte mascara = 1;
        for( int i = 0; i < posLocal; i++ )
        {
            mascara *= 2;
        }
        if( bit && posBit >= 0 )
        {
            // Aplica la m�scara sobre el respectivo byte
            bits[ posByte ] = ( byte ) ( bits[ posByte ] | mascara );
        }
        else if( posBit >= 0 )
        {
            // Crea la m�scara para apagar el respectivo bit
            mascara = ( byte )~mascara;
            // Aplica la m�scara sobre el respectivo byte
            bits[ posByte ] = ( byte ) ( bits[ posByte ] & mascara );
        }
    }


    /**
     * Indica si dos bitArray son iguales. <br>
     * <b>pre: </b> ba!= null. <br>
     * <b>post: </b> Se retorn� true si los dos bitArray son iguales o false de lo contrario. <br> 
     * Dos bitArray son iguales si su n�mero de bits es igual y adem�s cada uno de <br>
     * los bits que se encuentran en la misma posici�n presenta igual valor. <br>
     * @param ba BitArray con el que se va a realizar la comparaci�n
     * @return True si los dos bitArray son iguales o false de lo contrario
     */
    public boolean equals( BitArray ba )
    {
        if( numBits != ba.numBits )
            return false;
        else
        {
            // Calcula el n�mero de bytes que debe comparar
            int long1 = Math.min( bits == null ? 0 : bits.length, ba.bits == null ? 0 : ba.bits.length );
            int i = 0;
            for( ; i < long1 && bits[ i ] == ba.bits[ i ]; i++ )
                ;
            return i == long1;
        }
    }

    /**
     * Convierte el bitArray a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n en String del bitArray. El String tiene el formato "[numeroElementos]: b1b2b3..bn",<br> 
     * donde b1, b2, ..., bn son los bits del bitArray y numeroElementos su tama�o.
     * @return La representaci�n en String del bitArray
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
