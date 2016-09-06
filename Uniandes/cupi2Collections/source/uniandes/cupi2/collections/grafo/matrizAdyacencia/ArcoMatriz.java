/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Juan E. Gomez - Ene 28, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.grafo.matrizAdyacencia;

import uniandes.cupi2.collections.grafo.Arco;

/**
 * Representa una posición en una matriz de adyacencia
 */
public class ArcoMatriz
{

    /**
     * Marca del arco
     */
    private boolean marcado;

    /**
     * Arco
     */
    private Arco arco;

	/**
	 * Construye una posición de una matriz de ayacencia a partir de un
	 * <code>Arco</code>
	 * 
	 * @param arco
	 *            Información del arco
	 */
	public ArcoMatriz(Arco arco) {
		marcado = false;
		this.arco = arco;
	}

	/**
	 * Retorna la marca del arco
	 * 
	 * @return <code>true</code> si el arco está marcado o <code>false</code>
	 *         en caso contrario
	 */
	public boolean marcado() {
		return marcado;
	}

	/**
	 * Marca el arco
	 */
	public void marcar() {
		marcado = true;
	}

	/**
	 * Elimina la marca del vertice
	 */
	public void desmarcar() {
		marcado = false;
	}

	/**
	 * Retorna el arco contenido
	 * @return La información de arco contenido
	 */
	public Arco darArco() {
		return arco;
	}

}
