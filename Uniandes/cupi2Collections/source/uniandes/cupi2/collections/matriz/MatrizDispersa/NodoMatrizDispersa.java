/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoMatrizDispersa.java,v 1.1 2008/04/07 01:37:57 jua-gome Exp $
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
 * Nodo de la matriz dispersa
 */
public class NodoMatrizDispersa<T>
{

    /**
     * Elemento contenido en el nodo.
     */
    private T elemento;

    /**
     * Referencia al siguiente nodo avanzando por la columna.
     * </p>
     * Que este valor sea <code>null</code> indica que este nodo es el último de su columna.
     */
    private NodoMatrizDispersa<T> sigAbajo;

    /**
     * Referencia al siguiente nodo avanzando por la fila.
     * </p>
     * Que este valor sea <code>null</code> indica que este nodo es el último de su fila.
     */
    private NodoMatrizDispersa<T> sigDerecha;

    /**
     * Fila que le corresponde al nodo dentro de la matriz.
     */
    private int fila;

    /**
     * Columna que le corresponde al nodo dentro de la matriz.
     */
    private int columna;

    /**
     * Construye un nodo a partir de un elemento.
     * @param elemento Elemento contenido en el nodo.
     */
    protected NodoMatrizDispersa( T elemento, int fila, int columna )
    {
        this.elemento = elemento;
        sigAbajo = null;
        sigDerecha = null;
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Retorna el elemento contenido en el nodo.
     * @return El elemento contenido en el nodo.
     */
    public T darElemento(){
        return elemento;
    }
    
    /**
     * Retorn la fila que le corresponde al nodo dentro de la matriz.
     * @return La fila que le corresponde al nodo dentro de la matriz.
     */
    public int darFila( )
    {
        return fila;
    }

    /**
     * Retorn la columna que le corresponde al nodo dentro de la matriz.
     * @return La columna que le corresponde al nodo dentro de la matriz.
     */
    public int darColumna( )
    {
        return columna;
    }

    /**
     * Retorna la referencia al siguiente nodo avanzando por la columna.
     * @return La referencia al siguiente nodo avanzando por la columna.
     */
    public NodoMatrizDispersa<T> darSigAbajo( )
    {
        return sigAbajo;
    }

    /**
     * Cambia la referencia al siguiente nodo avanzando por la columna.
     * @param sigAbajo La nueva referencia al siguiente nodo avanzando por la columna.
     */
    public void cambiarSigAbajo( NodoMatrizDispersa<T> sigAbajo )
    {
        this.sigAbajo = sigAbajo;
    }

    /**
     * Retorna la referencia al siguiente nodo avanzando por la fila.
     * @return La referencia al siguiente nodo avanzando por la fila.
     */
    public NodoMatrizDispersa<T> darSigDerecha( )
    {
        return sigDerecha;
    }

    /**
     * Cambia la referencia al siguiente nodo avanzando por la fila.
     * @param sigAbajo La nueva referencia al siguiente nodo avanzando por la fila.
     */
    public void cambiarSigDerecha( NodoMatrizDispersa<T> sigDerecha )
    {
        this.sigDerecha = sigDerecha;
    }

}
