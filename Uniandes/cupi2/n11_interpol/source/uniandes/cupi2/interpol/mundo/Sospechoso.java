/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Sospechoso.java,v 1.1 2007/04/20 12:38:40 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_interpol
 * Autor: Manuel Mu�oz - 19-Mar-2007
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
     * Indica si es el ladr�n o no.
     */
    private boolean esSospechoso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por par�metros. Los atributos se inicializan con lo par�metros. rutaImagen = ruta, esSospechoso = nEsSospechoso
     */
    public Sospechoso( String ruta, boolean nEsSospechoso )
    {
        rutaImagen = ruta;
        esSospechoso = nEsSospechoso;
    }

    // -----------------------------------------------------------------
    // M�todos
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
     * Retorna si el sospechoso es el ladr�n o no
     * @return True en caso de que sea el ladr�n, false de lo contrario.
     */
    public boolean esLadron( )
    {
        return esSospechoso;
    }
}
