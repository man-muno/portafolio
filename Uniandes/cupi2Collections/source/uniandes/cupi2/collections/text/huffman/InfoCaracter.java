/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InfoCaracter.java,v 1.6 2006/06/02 19:37:16 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
     * C�digo binario asignado
     */
    private BitString codigo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de un infoCaracter con el caracter dado. <br>
     * <b>post: </b> Se construy� el infoCaracter con el caracter dado con codigo= null.
     */
    public InfoCaracter( char pCaracter )
    {
        caracter = pCaracter;
        codigo = null;
    }

    /**
     * Constructor de un infoCaracter a partir del flujo especificado. <br>
     * <b>post: </b> Se construy� un infoCaracter a partir del flujo especificado. <br>
     * @param in Flujo a partir del cual se va a construir el infoCaracter
     */
    public InfoCaracter( DataInputStream in ) throws IOException
    {
        caracter = in.readChar( );
        codigo = new BitString( in );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorta el caracter. <br>
     * <b>post: </b> Se retorn� el caracter.
     * @return El caracter
     */
    public char darCaracter( )
    {
        return caracter;
    }

    /**
     * Retorta el c�digo. <br>
     * <b>post: </b> Se retorn� el c�digo.
     * @return El c�digo
     */
    public BitString darCodigo( )
    {
        return codigo;
    }

    /**
     * Asigna un nuevo c�digo al infoCaracter. <br>
     * <b>post: </b> Se asigno el nuevo c�digo al info caracter.
     * @param info InfoCaracter que contiene el nuevo c�digo
     * @param bit Bit para la creaci�n del nuevo c�digo en caso de que el codigo de info sea null
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
     * <b>post: </b> Se guard� el infoCaracter en el flujo especificado.
     * @param in Flujo a partir del cual se va a construir el infoCaracter
     */
    public void salvar( DataOutputStream out ) throws IOException
    {
        out.writeChar( caracter );
        codigo.salvar( out );
    }

    /**
     * Convierte el infoCaracter a un String. <br>
     * <b>post: </b> Se retorn� la representaci�n en String del infoCaracter. Dicha representaci�n tiene el formato: "caracter: codigo".
     * @return La representaci�n en String del infoCaracter
     */
    @Override
    public String toString( )
    {
        return caracter + ":" + codigo;
    }
}
