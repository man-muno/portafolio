/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolB.java,v 1.3 2006/08/31 22:12:03 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - 25/02/2006
 * Autor: Pablo Barvo - 25/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.arbolB;

/**
 * Implementación de un árbol B
 * @param <T> Tipo de datos que contiene cada nodo del árbol
 */
public class ArbolB<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Raíz del árbol B
     */
    private NodoArbolB<T> raiz;

    /**
     * Peso del árbol B
     */
    private int peso;
    
    /**
     * Orden del árbol B 
     */
    private int orden; 

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo árbol vacío del orden especificado
     * @param elOrden Orden del árbol B
     */
    public ArbolB(int elOrden)
    {
        raiz = null;
        peso = 0;
        orden= elOrden; 
        
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
}
