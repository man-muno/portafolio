/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Marzo 14, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.arbol;

/**
 * Representa un arbol que ordene sus elementos de alguna forma.
 * @param <T> Tipo de elemento a guardar en el arbol.
 */
public interface IArbolOrdenado<T extends Comparable<? super T>> extends IArbol<T>
{

    /**
     * Inserta un nuevo elemento en el árbol.
     * 
     * @param elem Elemento a insertar.
     * @throws ElementoExisteException Si el elemento a insertar ya se encuentra en el árbol
     */
    public void insertar( T elem ) throws ElementoExisteException;

    /**
     * Eliminar un elemento del árbol.
     * 
     * @param elem Elemento a eliminar del árbol.
     * @throws ElementoNoExisteException Si el elemento a eliminar no es encontrado en el árbol.
     */
    public void eliminar( T elem ) throws ElementoNoExisteException;

}
