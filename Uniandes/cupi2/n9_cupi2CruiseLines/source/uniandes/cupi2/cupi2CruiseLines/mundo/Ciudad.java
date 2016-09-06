/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Ciudad.java,v 1.3 2007/04/07 23:39:17 man-muno Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_cupi2CruiseLines
 * Autor: Manuel Mu�oz - 13-Mar-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupi2CruiseLines.mundo;

import java.io.Serializable;

/**
 * Clase que representa una ciudad <b>inv: </b> <br>
 * nombre != null pais != null
 */
public class Ciudad implements Serializable
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Versi�n de la clase
     */
    private static final long serialVersionUID = -3654380110006144986L;

    /**
     * Nombre de la ciudad
     */
    private String nombre;

    /**
     * Pa�s donde queda la ciudad
     */
    private String pais;

    /**
     * Ciudad siguiente en el crucero
     */
    private Ciudad anterior;

    /**
     * Ciudad anterior en el crucero
     */
    private Ciudad siguiente;

    /**
     * La coordenada x de la ciudad en el plano mundial
     */
    private double coordenadaX;

    /**
     * La coordenada y de la ciudad en el plano mundial
     */
    private double coordenadaY;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por par�metros. Inicializa los atributos con los par�metros
     * @param nombreCiudad Nombre de la ciudad. Diferente de null.
     * @param pais2 Nombre del pa�s donde esta la ciudad. Diferente de null.
     * @param coordX Coordenada x en el mapa. Entero mayor a cero.
     * @param coordY Coordenada Y en el mapa. Entero mayor a cero.
     */
    public Ciudad( String nombreCiudad, String pais2, double coordX, double coordY )
    {
        nombre = nombreCiudad;
        pais = pais2;
        coordenadaX = coordX;
        coordenadaY = coordY;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna la coordenada Y de la ciudad.
     * @return Entero mayor a cero.
     */
    public double darCoordenadaY( )
    {
        return coordenadaY;
    }

    /**
     * Retorna la coordenada X de la ciudad.
     * @return Entero mayor a cero.
     */
    public double darCoordenadaX( )
    {
        return coordenadaX;
    }

    /**
     * Cambia la siguiente ciudad.
     * @param ciudad La nueva ciudad siguiente. Puede ser null.
     */
    public void cambiarSiguiente( Ciudad ciudad )
    {
        siguiente = ciudad;
    }

    /**
     * Cambia la anterior ciudad.
     * @param ciudad La nueva ciudad anterior. Puede ser null.
     */
    public void cambiarAnterior( Ciudad anterior2 )
    {
        anterior = anterior2;
    }

    /**
     * Retorna el nombre de la ciudad.
     * @return Diferente de null.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna el nombre el pa�s donde se encuentra la ciudad.
     * @return Diferente de null.
     */
    public String darPais( )
    {
        return pais;
    }

    /**
     * Retorna la ciudad que es siguiente en la lista.
     * @return La ciudad que es siguiente en la lista. Puede ser null.
     */
    public Ciudad darSiguiente( )
    {
        return siguiente;
    }

    /**
     * Retorna la ciudad que es anterior en la lista.
     * @return La ciudad que es anterior en la lista. Puede ser null.
     */
    public Ciudad darAnterior( )
    {
        return anterior;
    }

    /**
     * M�todo que verifica la invariante de la clase <b>inv: </b> <br>
     * nombre != null pais != null
     */
    private void verificarInvariante( )
    {
        assert nombre != null : "El nombre no puede ser null";
        assert pais != null : "El pais no puede ser null";
    }

}
