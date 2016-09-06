/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Iterador.java,v 1.8 2006/07/19 16:58:52 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 5, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.iterador;

/**
 * M�todos b�sicos que debe ofrecer todo Iterador de cupi2 Collections
 * @param <T> Tipo de datos sobre los que se iteran
 */
public interface Iterador<T>
{
    /**
     * Indica si a�n hay elementos sobre los cuales iterar. <br>
     * <b>post: </b> Se retorn� true si a�n no se han recorrido todos los elementos o false en caso contrario.
     * @return True si a�n no se han recorrido todos los elementos o false en caso contrario
     */
    public boolean haySiguiente( );

    /**
     * Retorna el elemento a ser visitado. <br>
     * <b>pre: </b> A�n existe al menos un elemento sobre el cual iterar. <br>
     * <b>post: </b> Se retorn� el elemento a ser visitado.
     * @return El elemento a ser visitado o null si no hay elemento para visitar
     */
    public T darSiguiente( );

    /**
     * Sit�a el iterador de nuevo al inicio de la colecci�n de datos con la que se encuentra asociado. <br>
     * <b>post: </b> El iterador se encuentra al inicio de la colecci�n de datos con la que se encuentra asociada.
     */
    public void reiniciar( );
}
