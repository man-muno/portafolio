/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IIteradorBolsa.java,v 1.1 2007/01/31 14:33:44 man-muno Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_bolsa
 * Autor: Jorge Villalobos - 23-ene-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.bolsa.mundo;

/**
 * Interfaz que define las operaciones de un iterador simple sobre una bolsa
 */

public interface IIteradorBolsa
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa un valor inexistente en una bolsa
     */
    public final static int INVALIDO = Integer.MAX_VALUE;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Indica si quedan valores por recorrer en el iterador
     * @return verdadero si quedan valores por recorrer en el iterador, y falso en caso contrario
     */
    public boolean haySiguiente( );

    /**
     * Retorna el siguiente valor en el iterador
     * @return el siguiente valor en el iterador si este existe o INVALIDO si no hay más valores
     */
    public int darSiguiente( );
}
