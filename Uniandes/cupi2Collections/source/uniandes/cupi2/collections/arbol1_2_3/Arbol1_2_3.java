/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Arbol1_2_3.java,v 1.3 2007/10/16 15:41:45 p-marque Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Manuel Muñoz - May 16, 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.arbol1_2_3;

import java.io.Serializable;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;

/**
 * Implementación de un árbol 1-2-3
 * 
 * @param <T> Tipo de datos que contiene cada nodo del árbol. Los nodos deben implementar la interface comparable
 */
public class Arbol1_2_3<T extends Comparable> implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Raíz del árbol 1-2-3
     */
    private Nodo1_2_3<T> raiz;

    /**
     * Peso del árbol 1-2-3
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo árbol vacío. <br>
     * <b>post: </b> Se construyó un árbol vacío, con raíz null.
     */
    public Arbol1_2_3( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz del árbol para ser navegada. <br>
     * <b>post: </b> Se retornó la raíz del árbol.
     * @return Raíz del árbol
     */
    public Nodo1_2_3<T> darRaiz( )
    {
        return raiz;
    }

    /**
     * Busca un elemento en el árbol dado su modelo. <br>
     * <b>pre: </b> modelo!=null <br>
     * <b>post: </b> Se retornó el elemento del árbol que corresponde al modelo. Si ningún elemento corresponde al modelo se retorna null.
     * 
     * @param modelo Descripción del elemento que se va a buscar en el árbol. Debe contener por lo menos la información mínima necesaria para que el método de comparación del
     *        nodo pueda establecer una relación de orden.
     * @return T elemento del árbol que corresponde al modelo o null si no lo encuentra.
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * Calcula la altura del árbol. <br>
     * <b>post: </b> Se retornó la altura del árbol. Entero mayor o igual a cero.
     * @return Altura del árbol. Entero mayor o igual a cero.
     */
    public int darAltura( )
    {
        return raiz == null ? 0 : raiz.darAltura( );
    }

    /**
     * Retorna el peso total del árbol. <br>
     * <b>post: </b> Se retornó el peso total del árbol. Entero mayor o igual a cero.
     * @return Peso total del árbol. Entero mayor o igual a cero.
     */
    public int darPeso( )
    {
        return peso;
    }

    /**
     * Inserta el elemento al árbol. <br>
     * <b>pre:</b> elemento!=null. <br>
     * <b>post: </b> Se insertó un elemento en el árbol si este no existía previamente en la estructura. <br>
     * 
     * @param elemento Elemento a insertar
     * @throws ElementoExisteException Excepción lanzada si el elemento insertado ya existe en el árbol
     */
    public void insertar( T elemento ) throws ElementoExisteException
    {
        if( raiz == null )
        {
            // Caso 1: el árbol es vacío
            raiz = new Nodo1_2_3<T>( elemento );
        }
        else
        {
            // Caso 2: el árbol no es vacío
            raiz.insertar( elemento );
        }
        peso++;
    }

    /**
     * Elimina el elemento especificado del árbol. <br>
     * <b>pre: </b> elemento!=null. <br>
     * <b>post: </b> Se eliminó el elemento del árbol si este existía en la estructura. <br>
     * @param elemento Elemento a eliminar
     * @throws ElementoNoExisteException Excepción lanzada si el elemento especificado no existe en el árbol
     */
    public void eliminar( T elemento ) throws ElementoNoExisteException
    {
        if( raiz != null )
        {
            // Caso 1: el árbol no es vacío
            raiz = raiz.eliminar( elemento );
            peso--;
        }
        else
        {
            // Caso 2: el árbol es vacío
            throw new ElementoNoExisteException( "El elemento especificado no existe en el árbol" );
        }
    }

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del árbol en inorden.
     * @return Iterador para recorrer los elementos del árbol en inorden. Diferente de null.
     */
    public Iterador<T> inorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( peso );
        if( raiz != null )
        {
            try
            {
                raiz.inorden( resultado );
            }
            catch( IteradorException e )
            {
                // Nunca debería lanzar esta excepción porque el tamaño del
                // iterador es el peso del árbol
            }
        }
        return resultado;
    }
}
