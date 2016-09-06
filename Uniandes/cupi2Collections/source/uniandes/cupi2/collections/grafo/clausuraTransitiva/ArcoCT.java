/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArcoCT.java,v 1.1 2008/02/13 23:17:25 jua-gome Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Feb 7, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.grafo.clausuraTransitiva;

import uniandes.cupi2.collections.grafo.IArco;

/**
 * Representa un arco en una clausura transitiva.
 */
public class ArcoCT implements IArco
{

    /**
     * Peso del arco.
     */
    private int peso;

    /**
     * Constructor por parámetros del arco.
     * 
     * @param peso Peso del arco.
     */
    public ArcoCT( int peso )
    {
        this.peso = peso;
    }

    /**
     * Retorna el peso del arco.
     * 
     * @return El peso del arco.
     */
    public int darPeso( )
    {
        return peso;
    }

}
