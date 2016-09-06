/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IIteradorPintura.java,v 1.1 2007/03/05 02:09:02 man-muno Exp $
 * Universidad de los Andes (Bogotá· - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n13_pinturaPuntos
 * Autor: Manuel Muñoz - 24 - feb - 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.pinturaPuntos.mundo;

/**
 * Interfaz que define las operaciones de un iterador simple sobre una bolsa
 */

public interface IIteradorPintura
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa un valor inexistente en una bolsa
     */
    public final static boolean INVALIDO = false;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Indica si quedan valores por recorrer en el iterador
     * @return verdadero si quedan valores por recorrer con el iterador, y falso en caso contrario
     */
    public boolean haySiguiente( );

    /**
     * Retorna el siguiente valor del iterador
     * @return el siguiente valor en el iterador si este existe o INVALIDO si no hay más valores
     */
    public boolean darSiguiente( );

    /**
     * Agrega un elemento recorriendo el iterador de izquierda a derecha y de arriba a abajo <br>
     * <b>pre: </b> Hay espacio en el iterador para el nuevo valor <br>
     * <b>post: </b> Se agregó el elemento especificado en la última posición libre del iterador, teniendo en cuenta que se llena de izquierda a derecha y de arriba a abajo.
     */
    public void agregar( boolean elem );
}
