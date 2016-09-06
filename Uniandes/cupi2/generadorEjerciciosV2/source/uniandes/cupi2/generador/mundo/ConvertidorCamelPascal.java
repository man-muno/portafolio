/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 
 * Ejercicio: generadorEjerciciosV2
 * Autor: Pablo Barvo - Sep 2, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.generador.mundo;

/**
 * Convierte Strings de Camel Case a Pascal case y viceversa.
 * @author Pablo Barvo
 */
public class ConvertidorCamelPascal
{

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor sin parametros
     */
    public ConvertidorCamelPascal( )
    {
    }

    //-----------------------------------------------------------------
    // M�todos p�blicos
    //-----------------------------------------------------------------

    /**
     * Convierte un String en Pascal case a Camel case. <br>
     * <br>
     * Ejemplo: <br>
     * Texto de Entrada: BotonAzul <br>
     * Texto de Salida: botonAzul <br>
     * @param texto Texto en Pascal case.
     * @return Texto en Camel case.
     */
    public String convertirCamel( String texto )
    {
        String result;
        result = texto.substring( 0, 1 ).toLowerCase( );
        result = result + texto.substring( 1, texto.length( ) );
        return result;
    }

    /**
     * Convierte un String en Camel case a Pascal case. <br>
     * <br>
     * Ejemplo: <br>
     * Texto de Entrada: botonAzul <br>
     * Texto de Salida: BotonAzul <br>
     * @param texto Texto en Camel case.
     * @return Texto en Pascal case.
     */
    public String convertirPascal( String texto )
    {
        String result;
        result = texto.substring( 0, 1 ).toUpperCase( );
        result = result + texto.substring( 1, texto.length( ) );
        return result;
    }

}
