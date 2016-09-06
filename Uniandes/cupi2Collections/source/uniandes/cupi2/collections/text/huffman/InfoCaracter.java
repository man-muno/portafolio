/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InfoCaracter.java,v 1.6 2006/06/02 19:37:16 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: J. Villalobos - 07-mar-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.text.huffman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import uniandes.cupi2.collections.bitString.BitString;

public class InfoCaracter
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Caracter en el nodo
     */
    private char caracter;

    /**
     * Código binario asignado
     */
    private BitString codigo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de un infoCaracter con el caracter dado. <br>
     * <b>post: </b> Se construyó el infoCaracter con el caracter dado con codigo= null.
     */
    public InfoCaracter( char pCaracter )
    {
        caracter = pCaracter;
        codigo = null;
    }

    /**
     * Constructor de un infoCaracter a partir del flujo especificado. <br>
     * <b>post: </b> Se construyó un infoCaracter a partir del flujo especificado. <br>
     * @param in Flujo a partir del cual se va a construir el infoCaracter
     */
    public InfoCaracter( DataInputStream in ) throws IOException
    {
        caracter = in.readChar( );
        codigo = new BitString( in );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorta el caracter. <br>
     * <b>post: </b> Se retornó el caracter.
     * @return El caracter
     */
    public char darCaracter( )
    {
        return caracter;
    }

    /**
     * Retorta el código. <br>
     * <b>post: </b> Se retornó el código.
     * @return El código
     */
    public BitString darCodigo( )
    {
        return codigo;
    }

    /**
     * Asigna un nuevo código al infoCaracter. <br>
     * <b>post: </b> Se asigno el nuevo código al info caracter.
     * @param info InfoCaracter que contiene el nuevo código
     * @param bit Bit para la creación del nuevo código en caso de que el codigo de info sea null
     */
    public void asignarCodigo( InfoCaracter info, boolean bit )
    {
        if( info.codigo != null )
            codigo = new BitString( info.codigo );
        else if( codigo == null )
            codigo = new BitString( );
        codigo.concatenar( bit );
    }

    /**
     * Salva el infoCaracter en el flujo especificado. <br>
     * <b>post: </b> Se guardó el infoCaracter en el flujo especificado.
     * @param in Flujo a partir del cual se va a construir el infoCaracter
     */
    public void salvar( DataOutputStream out ) throws IOException
    {
        out.writeChar( caracter );
        codigo.salvar( out );
    }

    /**
     * Convierte el infoCaracter a un String. <br>
     * <b>post: </b> Se retornó la representación en String del infoCaracter. Dicha representación tiene el formato: "caracter: codigo".
     * @return La representación en String del infoCaracter
     */
    @Override
    public String toString( )
    {
        return caracter + ":" + codigo;
    }
}
