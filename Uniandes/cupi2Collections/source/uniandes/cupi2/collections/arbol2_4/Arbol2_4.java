/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Arbol2_4.java,v 1.3 2006/06/20 00:49:27 da-romer Exp $
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
package uniandes.cupi2.collections.arbol2_4;

import uniandes.cupi2.collections.arbolRojoNegro.ArbolRojoNegro;
import uniandes.cupi2.collections.colaEncadenada.ColaEncadenada;
import uniandes.cupi2.collections.colaEncadenada.ColaVaciaException;
import uniandes.cupi2.collections.iterador.*;

/**
 * Implementación de un árbol 2-4
 * 
 * @param <T> Tipo de datos que contiene cada nodo del árbol
 */
public class Arbol2_4<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Raíz del árbol 2-4
     */
    private Nodo2_4<T> raiz;

    /**
     * Peso del árbol 2-4
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo árbol vacío. <br>
     * <b>post: </b> Se construyó un árbol vacío.
     */
    public Arbol2_4( )
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
    public Nodo2_4<T> darRaiz( )
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
     * <b>post: </b> Se retornó la ultura del árbol.
     * @return Altura del árbol
     */
    public int darAltura( )
    {
        return raiz == null ? 0 : raiz.darAltura( );
    }

    /**
     * Retorna el peso total del árbol. <br>
     * <b>post: </b> Se retornó el peso total del árbol.
     * @return Peso total del árbol
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
     * @throws ExisteException Excepción generada si el elemento insertado ya existe en el árbol
     */
    public void insertar( T elemento ) throws ExisteException
    {
        if( raiz == null )
        {
            // Caso 1: el árbol es vacío
            raiz = new Nodo2_4<T>( elemento );
        }
        else
        {
            // Caso 2: el árbol no es vacío
            raiz = raiz.insertar( elemento );
        }
        peso++;
    }

    /**
     * Elimina el elemento especificado del árbol. <br>
     * <b>pre: </b> elemento!=null. <br>
     * <b>post: </b> Se eliminó el elemento del árbol si este existía en la estructura. <br>
     * @param elemento Elemento a eliminar
     * @throws NoExisteException Excepción lanzada si el elemento especificado no existe en el árbol
     */
    public void eliminar( T elemento ) throws NoExisteException
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
            throw new NoExisteException( "El elemento especificado no existe en el árbol" );
        }
    }

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del árbol en inorden.
     * @return Iterador para recorrer los elementos del árbol en inorden
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
    
    /**
     * Retorna el árbol rojo negro equivalente al árbol 2_4 actual. <br>
     * <b>post: </b> Se retornó el árbol rojo negro equivalente al árbol 2_4 actual.
     * @return El árbol rojo negro equivalente al árbol 2_4 actual
     */
    public ArbolRojoNegro<T> darArbolRojoNegro()
    {
    	ArbolRojoNegro<T> rn= new ArbolRojoNegro<T>();
    	
    	if(raiz!=null)
    	{
    		ColaEncadenada<Nodo2_4<T>> cola = new ColaEncadenada<Nodo2_4<T>>( );
            cola.insertar( darRaiz() );
            while( cola.darLongitud( ) != 0 )
            {
                Nodo2_4<T> nodo = null;
                try
                {
                    // Toma el primer árbol de la cola
                    nodo = cola.tomarElemento( );
                }
                catch( ColaVaciaException e )
                {
                    // Nunca debería aparecer esta excepción
                }
                try
                {
                    rn.insertar( nodo.darRaiz1() );
                    
                    if(nodo.darRaiz2()!=null)
                    {
                    	rn.insertar( nodo.darRaiz2() );
                    
                    	if(nodo.darRaiz3()!=null)
                        {
                        	rn.insertar( nodo.darRaiz3() );                        	
                        }                    	
                    }
                }
                catch (uniandes.cupi2.collections.arbolRojoNegro.ExisteException e) {					
					e.printStackTrace();
				}

                // Agrega los dos subárboles (si no son vacíos) a la cola
                if( nodo.darHijo1() != null )
                {
                    cola.insertar( nodo.darHijo1() );
                }
                if( nodo.darHijo2() != null )
                {
                    cola.insertar( nodo.darHijo2() );
                }
                if( nodo.darHijo3() != null )
                {
                    cola.insertar( nodo.darHijo3() );
                }
                if( nodo.darHijo4() != null )
                {
                    cola.insertar( nodo.darHijo4() );
                }
            }
    	}
    	
    	
    	return rn; 
    }
    
}
