/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CabezaCoordenada.java,v 1.1 2008/04/07 01:37:57 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan Erasmo Gómez - Abr 1, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.matriz.MatrizDispersa;

/**
 * Clase que encapsula un NodoMatrizDispersa para poder ser usado como cabeza de un encadenamiento de fila o de columna en una matriz dispersa.
 * 
 * @param <T> Tipo de datos a guardar en la matriz.
 */
public class CabezaCoordenada<T> implements Comparable<CabezaCoordenada<T>>
{

    /**
     * Fila o columna de la cabeza.
     */
    private int indice;

    /**
     * Nodo de la matriz que va a ser usado como cabeza de fila o columna.
     */
    private NodoMatrizDispersa<T> cabeza;

    /**
     * Constructor de la clase.
     * @param cabeza Nodo a ser usado como cabeza.
     * @param indice Fila o columna de la que el objeto es cabeza.
     */
    public CabezaCoordenada( NodoMatrizDispersa<T> cabeza, int indice )
    {
        this.indice = indice;
        this.cabeza = cabeza;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo( CabezaCoordenada<T> o )
    {
        return indice - o.indice;
    }

    /**
     * Retorna el nodo a ser usado como cabeza.
     * @return El nodo a ser usado como cabeza.
     */
    public NodoMatrizDispersa<T> darCabeza( )
    {
        return cabeza;
    }

    /**
     * Moficia el nodo a ser usado como cabeza.
     * @param cabeza Nuevo nodo a ser usado como cabeza.
     */
    public void cambiarCabeza( NodoMatrizDispersa<T> cabeza )
    {
        this.cabeza = cabeza;
    }

    /**
     * Retorna la fila o columna de la que el objeto es cabeza.
     * @return La fila o columna de la que el objeto es cabeza.
     */
    public int darIndice( )
    {
        return indice;
    }

}
