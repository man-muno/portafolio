/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Elemento.java,v 1.2 2006/06/21 18:55:27 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Daniel Romero- Mayo 6 de 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.secuencia;

/**
 * Clase auxiliar de la secuencia para poder instanciar un arreglo de objetos comparables.
 * Representa un elemento de la secuencia. 
 * @param <T> El tipo de elementos manejados en la secuencia
 */
public class Elemento  <T extends Comparable<? super T>> extends Object implements Comparable
{
    /**
     * El elemento
     */
    private T elemento; 
    
    /**
     * Constructor del elemento. <br> 
     * <b> post: </b>Se construyó un objeto de tipo Elemento con la información especificada
     * @param elem El elemento que contendrá el objeto
     */
    public Elemento(T elem)
    {
        elemento= elem; 
    }
    
    /**
     * Retorna el elemento que contiene el objeto. <br>
     * <b> post: </b> Se retornó el elemento que contiene el objeto. 
     * @return Se retornó el elemento que contiene el objeto
     */
    public T darElemento()
    {
        return elemento; 
    }
    
    /**
     * Compara el elemento con el objeto especificado. <br>
     * <b> post: </b> Se retornó 0 si los objetos son iguales, 1 si el objeto actual es mayor al especificado o -1 si es menor. 
     * @param elem Elemento con el que se va a realizar la comparación
     * @return 0 si los objetos son iguales, 1 si el objeto actual es mayor al especificado o -1 si es menor  
     */
    @SuppressWarnings("unchecked")
    public int compareTo(Object elem)
    {
        Elemento aux= ( Elemento )elem; 
        return elemento.compareTo(( T )aux.elemento); 
    } 
}
