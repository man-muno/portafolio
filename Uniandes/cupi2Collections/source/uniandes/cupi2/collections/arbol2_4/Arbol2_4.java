/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Arbol2_4.java,v 1.3 2006/06/20 00:49:27 da-romer Exp $
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
package uniandes.cupi2.collections.arbol2_4;

import uniandes.cupi2.collections.arbolRojoNegro.ArbolRojoNegro;
import uniandes.cupi2.collections.colaEncadenada.ColaEncadenada;
import uniandes.cupi2.collections.colaEncadenada.ColaVaciaException;
import uniandes.cupi2.collections.iterador.*;

/**
 * Implementaci�n de un �rbol 2-4
 * 
 * @param <T> Tipo de datos que contiene cada nodo del �rbol
 */
public class Arbol2_4<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Ra�z del �rbol 2-4
     */
    private Nodo2_4<T> raiz;

    /**
     * Peso del �rbol 2-4
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo �rbol vac�o. <br>
     * <b>post: </b> Se construy� un �rbol vac�o.
     */
    public Arbol2_4( )
    {
        raiz = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ra�z del �rbol para ser navegada. <br>
     * <b>post: </b> Se retorn� la ra�z del �rbol.
     * @return Ra�z del �rbol
     */
    public Nodo2_4<T> darRaiz( )
    {
        return raiz;
    }

    /**
     * Busca un elemento en el �rbol dado su modelo. <br>
     * <b>pre: </b> modelo!=null <br>
     * <b>post: </b> Se retorn� el elemento del �rbol que corresponde al modelo. Si ning�n elemento corresponde al modelo se retorna null.
     * 
     * @param modelo Descripci�n del elemento que se va a buscar en el �rbol. Debe contener por lo menos la informaci�n m�nima necesaria para que el m�todo de comparaci�n del
     *        nodo pueda establecer una relaci�n de orden.
     * @return T elemento del �rbol que corresponde al modelo o null si no lo encuentra.
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * Calcula la altura del �rbol. <br>
     * <b>post: </b> Se retorn� la ultura del �rbol.
     * @return Altura del �rbol
     */
    public int darAltura( )
    {
        return raiz == null ? 0 : raiz.darAltura( );
    }

    /**
     * Retorna el peso total del �rbol. <br>
     * <b>post: </b> Se retorn� el peso total del �rbol.
     * @return Peso total del �rbol
     */
    public int darPeso( )
    {
        return peso;
    }

    /**
     * Inserta el elemento al �rbol. <br>
     * <b>pre:</b> elemento!=null. <br>
     * <b>post: </b> Se insert� un elemento en el �rbol si este no exist�a previamente en la estructura. <br>
     * 
     * @param elemento Elemento a insertar
     * @throws ExisteException Excepci�n generada si el elemento insertado ya existe en el �rbol
     */
    public void insertar( T elemento ) throws ExisteException
    {
        if( raiz == null )
        {
            // Caso 1: el �rbol es vac�o
            raiz = new Nodo2_4<T>( elemento );
        }
        else
        {
            // Caso 2: el �rbol no es vac�o
            raiz = raiz.insertar( elemento );
        }
        peso++;
    }

    /**
     * Elimina el elemento especificado del �rbol. <br>
     * <b>pre: </b> elemento!=null. <br>
     * <b>post: </b> Se elimin� el elemento del �rbol si este exist�a en la estructura. <br>
     * @param elemento Elemento a eliminar
     * @throws NoExisteException Excepci�n lanzada si el elemento especificado no existe en el �rbol
     */
    public void eliminar( T elemento ) throws NoExisteException
    {
        if( raiz != null )
        {
            // Caso 1: el �rbol no es vac�o
            raiz = raiz.eliminar( elemento );
            peso--;
        }
        else
        {
            // Caso 2: el �rbol es vac�o
            throw new NoExisteException( "El elemento especificado no existe en el �rbol" );
        }
    }

    /**
     * Devuelve los elementos del �rbol en inorden. <br>
     * <b>post: </b> Se retorno el iterador para recorrer los elementos del �rbol en inorden.
     * @return Iterador para recorrer los elementos del �rbol en inorden
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
                // Nunca deber�a lanzar esta excepci�n porque el tama�o del
                // iterador es el peso del �rbol
            }
        }
        return resultado;
    }
    
    /**
     * Retorna el �rbol rojo negro equivalente al �rbol 2_4 actual. <br>
     * <b>post: </b> Se retorn� el �rbol rojo negro equivalente al �rbol 2_4 actual.
     * @return El �rbol rojo negro equivalente al �rbol 2_4 actual
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
                    // Toma el primer �rbol de la cola
                    nodo = cola.tomarElemento( );
                }
                catch( ColaVaciaException e )
                {
                    // Nunca deber�a aparecer esta excepci�n
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

                // Agrega los dos sub�rboles (si no son vac�os) a la cola
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
