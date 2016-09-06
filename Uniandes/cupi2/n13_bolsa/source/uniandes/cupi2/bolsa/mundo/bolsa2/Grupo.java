/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Grupo.java,v 1.2 2007/02/01 15:18:01 jvillalo2 Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Manuel Muñoz - Aug 16, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bolsa.mundo.bolsa2;

import uniandes.cupi2.bolsa.mundo.*;

/**
 * Clase que representa un elemento en la bolsa. Se utiliza un elemento, ya que se quiere contar la cantidad de veces que se repite el elemento en la
 * bolsa
 */
public class Grupo extends Elemento
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Cantidad de veces que se repite el valor
     */
    private int veces;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros de la clase Elemento
     * @param valor Valor que tendrá el elemento
     */
    public Grupo(Integer valor)
    {
        super( valor );
        veces = 1;
    }

    /**
     * Retorna la cantidad de veces que se repite el elemento
     * @return Entero mayor o igual a cero
     */
    public int obtenerVeces( )
    {
        return veces;
    }

    /**
     * Aumenta en un la cantidad de veces del valor
     */
    public void aumentarVeces( )
    {
        veces++;
    }

    /**
     * Disminuye en uno la cantidad de veces del valor
     */
    public void disminuirVeces( )
    {
        veces--;
    }
}
