/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Nodo1_2_3.java,v 1.3 2007/10/16 15:41:45 p-marque Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Manuel Mu�oz - May 16, 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbol1_2_3;

import java.io.Serializable;

import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;

/**
 * Nodo del �rbol 1-2-3
 * 
 * @param <T> Tipo de datos que contiene cada nodo del �rbol. Debe comprar la interface Comparable.
 */
public class Nodo1_2_3<T extends Comparable> implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Ra�z izquierda del nodo
     */
    private T raizIzq;

    /**
     * Ra�z derecha del nodo
     */
    private T raizDer;

    /**
     * Sub�rbol izquierdo
     */
    private Nodo1_2_3<T> hijoIzq;

    /**
     * Sub�rbol central
     */
    private Nodo1_2_3<T> hijoCent;

    /**
     * Sub�rbol derecho
     */
    private Nodo1_2_3<T> hijoDer;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo con el primer elemento. <br>
     * <b> post: </b> Se construy� el nodo con el elemento especificado.
     * @param obj Elemento a agregar al nodo
     */
    public Nodo1_2_3( T obj )
    {
        raizIzq = obj;
        raizDer = null;
        hijoIzq = null;
        hijoCent = null;
        hijoDer = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ra�z izquierda del nodo. <br>
     * <b>post: </b> Se retorn� la ra�z izquierda del nodo.
     * @return Ra�z izquierda del nodo.
     */
    public T darRaizIzq( )
    {
        return raizIzq;
    }

    /**
     * Devuelve la ra�z derecha del nodo. <br>
     * <b>post: </b> Se retorn� la ra�z derecha del nodo.
     * @return Ra�z derecha del nodo
     */
    public T darRaizDer( )
    {
        return raizDer;
    }

    /**
     * Devuelve el hijo izquierdo del nodo.<br>
     * <b>post: </b> Se retorn� el hijo izquierdo del nodo.
     * @return Hijo izquierdo del nodo
     */
    public Nodo1_2_3<T> darHijoIzq( )
    {
        return hijoIzq;
    }

    /**
     * Devuelve el Hijo central del nodo. <br>
     * <b>post: </b> Se retorn� el hijo central del nodo. <br>
     * @return Hijo central del nodo
     */
    public Nodo1_2_3<T> darHijoCent( )
    {
        return hijoCent;
    }

    /**
     * Devuelve el Hijo derecho del nodo. <br>
     * <b>post: </b> Se retorn� el hijo 3 del nodo. <br>
     * @return Hijo 3 del nodo
     */
    public Nodo1_2_3<T> darHijoDer( )
    {
        return hijoDer;
    }

    /**
     * Indica si el nodo es una hoja.<br>
     * <b>post: </b> Se retorn� true si el nodo es una hoja o false de lo contrario. Un nodo es una hoja si sus tres hijos se encuentran en null.
     * @return True si es hoja, False si no
     */
    public boolean esHoja( )
    {
        return hijoIzq == null && hijoCent == null && hijoDer == null;
    }

    /**
     * Informa si un elemento se encuentra presente en el �rbol 1-2-3 cuya ra�z es el nodo actual. <br>
     * <b>pre: </b> modelo!=null. <br>
     * <b>post: </b> Se retorno un elemento que corresponde al modelo dado. Si ning�n elemento corresponde al elemento se retorna null.
     * 
     * @param modelo Modelo del elemento que se desea buscar
     * @return Elemento encontrado o null si no lo encuentra
     */
    public T buscar( T modelo )
    {
        //
        // Compara con el primer elemento
        int resultado1 = modelo.compareTo( raizIzq );
        if( resultado1 == 0 )
        {
            return raizIzq;
        }
        else if( resultado1 < 0 )
        {
            return ( hijoIzq == null ) ? null : hijoIzq.buscar( modelo );
        }
        else if( raizDer == null )
        {
            return ( hijoCent == null ) ? null : hijoCent.buscar( modelo );
        }
        else
        {
            // Compara con el segundo elemento
            int resultado2 = modelo.compareTo( raizDer );
            if( resultado2 == 0 )
            {
                return raizDer;
            }
            else if( resultado2 < 0 )
            {
                return ( hijoCent == null ) ? null : hijoCent.buscar( modelo );
            }
            else
            {
                return ( hijoDer == null ) ? null : hijoDer.buscar( modelo );
            }
        }
    }

    /**
     * Calcula la altura del �rbol 1-2-3 cuya ra�z es este nodo. <br>
     * <b>post: </b> Se retorn� la altura del �rbol.
     * @return Devuelve la altura del �rbol
     */
    public int darAltura( )
    {
        if( esHoja( ) )
        {
            return 1;
        }
        else
        {
            return hijoIzq != null ? hijoIzq.darAltura( ) + 1 : hijoCent != null ? hijoCent.darAltura( ) + 1 : hijoDer != null ? hijoDer.darAltura( ) + 1 : 1;
        }
    }

    /**
     * Agrega un nuevo elemento al �rbol 1-2-3 cuya ra�z el nodo actual y retorna el nodo que corresponde a la nueva ra�z del �rbol<br>
     * <b>pre: </b> obj es diferente de null. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se insert� un elemento en el �rbol si este no exist�a previamente en la estructura.
     * 
     * @param obj != null
     * @throws ElementoExisteException El elemento ya existe en el �rbol
     */
    public void insertar( T elemento ) throws ElementoExisteException
    {
        // Compara el elemento a insertar con las raices del nodo
        int resultado = elemento.compareTo( raizIzq );
        int resultado2 = raizDer != null ? elemento.compareTo( raizDer ) : 0;
        // Se busca si el elemento se encuentra en el nodo
        if( resultado == 0 || ( raizDer != null && resultado2 == 0 ) )
            throw new ElementoExisteException( "El elemento ya existe en el �rbol" );

        // Si el nodo no tiene ra�z derecha y el elemento es mayor que la raiz izquierda
        if( raizDer == null && resultado > 0 )
        {
            raizDer = elemento;
        }
        else if( raizDer == null && resultado < 0 )
        {
            // El nodo no tiene raiz derecha y el elemento a agregar es manor que el que se encuentra en la raiz izquierda
            raizDer = raizIzq;
            raizIzq = elemento;
        }

        if( resultado < 0 && resultado2 < 0 )
        {
            // El elemento es menor que la raiz izquierda y que la raiz derecha
            if( hijoIzq == null )
            {
                hijoIzq = new Nodo1_2_3<T>( elemento );
            }
            else
            {
                hijoIzq.insertar( elemento );
            }
        }
        else if( resultado > 0 && resultado2 < 0 )
        {
            // El elemento es mayor que la raiz izquierda y menor que la raiz derecha
            if( hijoCent == null )
            {
                hijoCent = new Nodo1_2_3<T>( elemento );
            }
            else
            {
                hijoCent.insertar( elemento );
            }
        }
        else if( resultado > 0 && resultado2 > 0 )
        {
            // El elemento es mayor que la raiz izquierda y que la raiz derecha
            if( hijoDer == null )
            {
                hijoDer = new Nodo1_2_3<T>( elemento );
            }
            else
            {
                hijoDer.insertar( elemento );
            }
        }
    }

    /**
     * Elimina un valor dado del �rbol 1-2-3 cuya ra�z es este nodo, y retorna una referencia al nodo ra�z de la estructura resultante. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se elimin� un elemento del �rbol si este exist�a en la estructura.
     * 
     * @param obj El objeto a ser eliminado
     * @throws ElementoNoExisteException Excepci�n generada si el elemento especificado no existe
     */
    public Nodo1_2_3<T> eliminar( T elemento ) throws ElementoNoExisteException
    {
        int resultado = elemento.compareTo( raizIzq );
        int resultado2 = raizDer != null ? elemento.compareTo( raizDer ) : 0;

        if( resultado == 0 )
        {
            // El elemento a eliminar se encuentra en la ra�z derecha del nodo
            if( hijoIzq != null )
            {
                // El nodo tiene hijo izquierdo y es necesario buscar un nuevo valor para la ra�z izquierda
                raizIzq = hijoIzq.calcularMayorElem( );
                hijoIzq = hijoIzq.eliminar( raizIzq );
                return this;
            }
            else if( raizDer != null )
            {
                // El nodo no tiene hijo izquierdo entonces toca colocar la ra�z derecha en la ra�z izquierda
                raizIzq = raizDer;
                raizDer = hijoDer != null ? hijoDer.calcularMayorElem( ) : null;
                if( raizDer != null )
                    hijoDer.eliminar( raizDer );
                // Es necesario reacomodar los hijos para que cumpla las reglas del �rbol 1-2-3
                hijoIzq = hijoCent;
                hijoCent = hijoDer;
                hijoDer = null;
                return this;
            }
            else
                return null;
        }
        else if( resultado2 == 0 && raizDer != null )
        {
            // El valor a eliminar se encuentra en la ra�z derecha del nodo
            if( hijoDer != null )
            {
                // Si el nodo tiene hijo derecho toca colocar una nueva raiz derecha
                raizDer = hijoDer.calcularMenorElem( );
                hijoDer.eliminar( raizDer );
                return this;
            }
            else
            {
                // Si no tiene hijo derecho solo se elimina la ra�z
                raizDer = null;
                return this;
            }
        }

        if( resultado < 0 && hijoIzq != null )
        {
            // El elemento es menor que la ra�z izquierda y que la ra�z derecha
            hijoIzq = hijoIzq.eliminar( elemento );
        }
        else if( ( raizDer == null || resultado2 < 0 ) && hijoCent != null )
        {
            // El elemento es mayor que la ra�z izquierda y menor que la ra�z derecha
            hijoCent = hijoCent.eliminar( elemento );
        }
        else if( hijoDer != null )
        {
            // El elemento es mayor que la ra�z izquierda y que la ra�z derecha
            hijoDer = hijoDer.eliminar( elemento );
        }
        else
        {
            throw new ElementoNoExisteException( "No se encontr� el elemento a eliminar" );
        }
        return this;
    }

    /**
     * Devuelve los elementos del �rbol en inorden. <br>
     * <b>pre: </b> resultado!=null. <br>
     * <b>post: </b> Se retorno un vector con el recorrido en inorden del �rbol.
     * 
     * @param resultado Vector con los elementos del �rbol en inorden
     */
    public void inorden( IteradorSimple<T> resultado ) throws IteradorException
    {
        if( hijoIzq != null )
        {
            hijoIzq.inorden( resultado );
        }
        resultado.agregar( raizIzq );
        if( hijoCent != null )
        {
            hijoCent.inorden( resultado );
        }
        if( raizDer != null )
        {
            resultado.agregar( raizDer );
            if( hijoDer != null )
            {
                hijoDer.inorden( resultado );
            }
        }
    }

    // -----------------------------------------------------------------
    // Operaciones Auxiliares
    // -----------------------------------------------------------------

    /**
     * Retorna el menor elemento que tiene el �rbol cuya ra�z es el nodo actual. <b>post: </b> Se retorno el menor elemento cuya ra�z es el nodo actual.
     * @return Valor que representa el menor de los elementos
     */
    /**
     * Retorna el menor elemento del �rbol 1-2-3 cuya ra�z es este nodo. <br>
     * <b>post: </b> Se retorn� el menor elemento del �rbol cuya ra�z es este nodo.
     * 
     * @return Menor elemento del �rbol
     */
    private T calcularMenorElem( )
    {
        Nodo1_2_3<T> aux = this;
        while( aux.hijoIzq != null )
            aux = aux.hijoIzq;
        return aux.raizIzq;
    }

    /**
     * Retorna el mayor elemento que tiene el �rbol cuya ra�z es el nodo actual. <b>post: </b> Se retorno el mayor elemento cuya ra�z es el nodo actual.
     * @return Valor que representa el mayor de los elementos
     */
    private T calcularMayorElem( )
    {
        Nodo1_2_3<T> aux = this;
        while( aux.hijoDer != null || aux.hijoCent != null )
        {
            aux = aux.hijoDer != null ? aux.hijoDer : aux.hijoCent != null ? aux.hijoCent : aux;
        }
        return aux.raizDer != null ? aux.raizDer : aux.raizIzq;

    }
}
