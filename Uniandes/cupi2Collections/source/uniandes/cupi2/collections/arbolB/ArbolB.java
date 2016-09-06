/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolB.java,v 1.3 2006/08/31 22:12:03 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
 * Implementaci�n de un �rbol B
 * @param <T> Tipo de datos que contiene cada nodo del �rbol
 */
public class ArbolB<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Ra�z del �rbol B
     */
    private NodoArbolB<T> raiz;

    /**
     * Peso del �rbol B
     */
    private int peso;
    
    /**
     * Orden del �rbol B 
     */
    private int orden; 

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo �rbol vac�o del orden especificado
     * @param elOrden Orden del �rbol B
     */
    public ArbolB(int elOrden)
    {
        raiz = null;
        peso = 0;
        orden= elOrden; 
        
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
}
