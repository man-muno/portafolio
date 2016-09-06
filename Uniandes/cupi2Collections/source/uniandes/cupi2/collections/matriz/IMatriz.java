/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IMatriz.java,v 1.1 2008/04/07 01:37:46 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Abr 1, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.matriz;

/**
 * Representa una matriz generica.
 * </p>
 * Una matriz es una contenedora bidimensional en la que se usa un sistema de coordenadas para ubicar los elementos alojados en la estructura.
 * </p>
 * Una casilla de la matriz se identifica por una dupla <code>[fila, columna]</code>.
 * @param <T> Tipo de datos a guardar en la matriz.
 */
public interface IMatriz<T>
{

    /**
     * Retorna el número de columnas de la matriz.
     * @return El número de columnas de la matriz.
     */
    public int darNColumnas( );

    /**
     * Retorna el número de filas de la matriz.
     * @return El número de filas de la matriz.
     */
    public int darNFilas( );

    /**
     * Retorna un elemento de la matriz.
     * @param fila Fila del elemento buscado.
     * @param columna Columna del elemento buscado.
     * @return El elemento que se encuentra en la coordenada <code>[fila, columna]</code> o <code>null</code> si esta casilla se encuentra vacia.
     * @throws CoordenadaFueraDeRangoException Si la fila o la columna ingresada por parámetro se salen de los límites de la matriz.
     */
    public T darElemento( int fila, int columna );

    /**
     * Cambia un elemento de la matriz.
     * @param fila Fila donde se quiere ubicar el elemento.
     * @param columna Columna donde se quiere ubicar el elemento.
     * @param elemento Elemento a ubicar en la coordenada <code>[fila, columna]</code>. Puede ser <code>null</code>.
     * @throws CoordenadaFueraDeRangoException Si la fila o la columna ingresada por parámetro se salen de los límites de la matriz
     */
    public void cambiarElemento( int fila, int columna, T elemento );

}
