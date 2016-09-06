/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: IteradorFlexible.java,v 1.1 2006/09/12 16:10:41 da-romer Exp $
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
package uniandes.cupi2.collections.iterador;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.listaEncadenada.ListaEncadenada;

/**
 * Implementación de un iterador compuesto. A través de este tipo de iterador se pueden recorrer los elementos de la estructura de datos sobre la que se encuentra asociada
 * pero ésta no puede ser modificada. También se pueden agregar e insertar elementos.  
 * @param <T> Tipo de datos sobre los que se itera
 */
@SuppressWarnings("unchecked")
public class IteradorFlexible<T> extends ListaEncadenada<T> implements Iterador<T>
{    
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private final static int NADA = -1;
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Posición que del próximo elemento a ser visitado
     */
    private int posActual;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------    
    /**
     * Constructor del iterador. <br>
     * <b> post: </b> Se creó un iterador vació.
     */
    @SuppressWarnings("unchecked")
    public IteradorFlexible( )
    {   
        super(); 
        posActual = NADA;    
    }
    
    // -----------------------------------------------------------------
    // Métodos: interface Iterador
    // -----------------------------------------------------------------

    /**
     * Indica si aún hay elementos sobre los cuales iterar. <br>
     * <b>post: </b> Se retornó true si aún no se han recorrido todos los elementos o false en caso contrario.
     * @return True si aún no se han recorrido todos los elementos o false en caso contrario
     */
    public boolean haySiguiente( )
    {        
        return ( posActual + 1 ) < darLongitud();
    }

    /**
     * Retorna el elemento a ser visitado. <br>
     * <b>pre: </b> Aún existe al menos un elemento sobre el cual iterar. <br>
     * <b>post: </b> Se retornó el elemento a ser visitado.
     * @return True si aún no se han recorrido todos los elementos o false en caso contrario
     */
    public T darSiguiente( )
    {        
        return ( ( posActual + 1 ) < darLongitud() ) ? dar(++posActual) : null;
    }

    /**
     * Sitúa el iterador de nuevo al inicio de la colección de datos con la que se encuentra asociado. <br>
     * <b>post: </b> El iterador se encuentra al inicio de la colección de datos con la que se encuentra asociada.
     */
    public void reiniciar( )
    {        
        posActual = NADA;
    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Retorna la posición del próximo elemento a ser visitado. <br>
     * <b>post: </b> Se retornó la posición del próximo elemento a ser visitado.
     * @return La posición del próximo elemento a ser visitado
     */
    public int darPosActual( )
    {
        return posActual;
    }
    
    /**
     * Agrega un nuevo elemento al final del iterador. Este método hace lo mismo que insertarCola <br>
     * <b>post: </b> Se adicionó el elemento especificado en la última posición del iterador.
     */
    public void agregar( T elem )
    {
        insertarCola(elem);        
    }
    
    /**
     * Inserta un nuevo elemento en la primera posición del iterador. Este método hace lo mismo que insertarCabeza <br>
     * <b>post: </b> Se adicionó el elemento especificado en la primera posición del iterador. <br>
     */
    public void insertar( T elem )
    {
        insertarCabeza(elem);         
    }
    
    /**
     * Convierte el iterador a un String. <br>
     * <b>post: </b> Se retornó la representación en String del iterador. El String tiene el formato "[numeroElementos]: e1-e2-e3..-en", donde e1, e2, ..., en son los
     * elementos del iterador y numeroElementos su tamaño.
     * @return La representación en String del iterador
     */
    @Override
    public String toString( )
    {
        String resp = "[" + darLongitud() + "]:";
        for( int i = 0; i < darLongitud(); i++ )
        {
            resp += dar( i ) + "-";
        }
        return resp;
    }

}
