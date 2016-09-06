/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Sospechoso.java,v 1.1 2007/04/20 12:38:40 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Muñoz - 19-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.interpol.mundo;

/**
 * Clase que representa un sospechoso
 */
public class Sospechoso
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La ruta de la imagen del sospechoso
     */
    private String rutaImagen;

    /**
     * Indica si es el ladrón o no.
     */
    private boolean esSospechoso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros. Los atributos se inicializan con lo parámetros. rutaImagen = ruta, esSospechoso = nEsSospechoso
     */
    public Sospechoso( String ruta, boolean nEsSospechoso )
    {
        rutaImagen = ruta;
        esSospechoso = nEsSospechoso;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna la ruta de la imagen del sospechoso.
     * @return Diferente de null.
     */
    public String darRutaImagen( )
    {
        return rutaImagen;
    }

    /**
     * Retorna si el sospechoso es el ladrón o no
     * @return True en caso de que sea el ladrón, false de lo contrario.
     */
    public boolean esLadron( )
    {
        return esSospechoso;
    }
}
