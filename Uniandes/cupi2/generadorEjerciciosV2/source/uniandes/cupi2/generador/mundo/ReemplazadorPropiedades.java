/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto Cupi2 
 * Ejercicio: generadorEjerciciosV2
 * Autor: Pablo Barvo - Sep 2, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.generador.mundo;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Reemplaza valores en textos o archivos con base a algunas propiedades. <br>
 * <br>
 * Realiza busquedas en los textos/archivos por entradas identificadas por [["NOMBRE_PROPIEDAD"]] y lo reemplaza por el valor de la propiedad. <br>
 * <br>
 * Ejemplo: <br>
 * Texto de Entrada: Creo que [[APELLIDO]] se fué. <br>
 * Propiedades de entrada: APELLIDO - Villalobos <br>
 * Texto de Salida: Creo que Villalobos se fué. <br>
 * @author Pablo Barvo
 */
public class ReemplazadorPropiedades
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Caracteres encontrados antes del nombre de la propiedad
     */
    private static final String INICIO_PROPIEDAD = "\\[\\[";

    /**
     * Caracteres encontrados despues del nombre de la propiedad
     */
    private static final String FIN_PROPIEDAD = "\\]\\]";

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor sin parametros
     */
    public ReemplazadorPropiedades( )
    {
    }

    //-----------------------------------------------------------------
    // Métodos públicos
    //-----------------------------------------------------------------

    /**
     * Reemplaza todas las propiedades especificadas en un archivo de texto. <br>
     * NOTA: No funciona correctamente con archivos binarios.
     * @param archivo Archivo a reemplazar
     * @param valores Propiedades a reemplazar
     * @throws IOException Error al leer el archivo
     */
    public void reemplazarArchivo( String archivo, Properties valores ) throws IOException
    {
        //
        //Lee todo el archivo a memoria
        byte[] bytes = ManejadorArchivos.leerArchivo( archivo );
        //
        //Reemplaza como texto
        String texto = reemplazarTexto( new String( bytes ), valores );
        //
        //Escribe el archivo de nuevo
        ManejadorArchivos.escribirArchivo( archivo, texto.getBytes( ) );
    }

    /**
     * Reemplaza todas las propiedades especificadas en un String.
     * @param texto Texto a reemplazar
     * @param valores Propiedades a reemplazar
     * @return Texto reemplazado
     */
    public String reemplazarTexto( String texto, Properties valores )
    {
        Enumeration enumerator = valores.keys( );
        String resultado = texto;
        while( enumerator.hasMoreElements( ) )
        {
            String nombrePropiedad = ( String )enumerator.nextElement( );
            resultado = reemplazarValor( resultado, nombrePropiedad, valores.getProperty( nombrePropiedad ) );
        }
        return resultado;
    }

    //-----------------------------------------------------------------
    // Métodos privados
    //-----------------------------------------------------------------

    /**
     * Reemplaza una propiedad especifica en el texto.
     * @param texto Texto a remplazar
     * @param nombrePropiedad Nombre de la propiedad
     * @param valorPropiedad Valor a poner
     * @return Texto con la propiedad remplazada
     */
    private String reemplazarValor( String texto, String nombrePropiedad, String valorPropiedad )
    {
        String aBuscar = ReemplazadorPropiedades.INICIO_PROPIEDAD + nombrePropiedad + ReemplazadorPropiedades.FIN_PROPIEDAD;
        return texto.replaceAll( aBuscar, valorPropiedad );
    }

}
