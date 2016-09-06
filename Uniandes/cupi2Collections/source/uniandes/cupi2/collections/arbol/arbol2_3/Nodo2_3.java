/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Nodo2_3.java,v 1.1 2008/03/30 01:53:03 jua-gome Exp $
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

package uniandes.cupi2.collections.arbol.arbol2_3;

import java.io.Serializable;

import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;
import uniandes.cupi2.collections.arbol.ElementoExisteException;
import uniandes.cupi2.collections.arbol.ElementoNoExisteException;

/**
 * Nodo del árbol 2-3
 * 
 * @param <T> Tipo de datos que contiene cada nodo del árbol. Debe comprar la interface Comparable.
 */
public class Nodo2_3<T extends Comparable> implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Raíz izquierda del nodo
     */
    private T raizIzq;

    /**
     * Raíz derecha del nodo
     */
    private T raizDer;

    /**
     * Subárbol izquierdo
     */
    private Nodo2_3<T> hijoIzq;

    /**
     * Subárbol central
     */
    private Nodo2_3<T> hijoCent;

    /**
     * Subárbol derecho
     */
    private Nodo2_3<T> hijoDer;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo con el primer elemento. <br>
     * <b> post: </b> Se construyó el nodo con el elemento especificado.
     * @param obj Elemento a agregar al nodo
     */
    public Nodo2_3( T obj )
    {
        raizIzq = obj;
        raizDer = null;
        hijoIzq = null;
        hijoCent = null;
        hijoDer = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz izquierda del nodo. <br>
     * <b>post: </b> Se retornó la raíz izquierda del nodo.
     * @return Raíz izquierda del nodo.
     */
    public T darRaizIzq( )
    {
        return raizIzq;
    }

    /**
     * Devuelve la raíz derecha del nodo. <br>
     * <b>post: </b> Se retornó la raíz derecha del nodo.
     * @return Raíz derecha del nodo
     */
    public T darRaizDer( )
    {
        return raizDer;
    }

    /**
     * Devuelve el hijo izquierdo del nodo.<br>
     * <b>post: </b> Se retornó el hijo izquierdo del nodo.
     * @return Hijo izquierdo del nodo
     */
    public Nodo2_3<T> darHijoIzq( )
    {
        return hijoIzq;
    }

    /**
     * Devuelve el Hijo central del nodo. <br>
     * <b>post: </b> Se retornó el hijo central del nodo. <br>
     * @return Hijo central del nodo
     */
    public Nodo2_3<T> darHijoCent( )
    {
        return hijoCent;
    }

    /**
     * Devuelve el Hijo derecho del nodo. <br>
     * <b>post: </b> Se retornó el hijo 3 del nodo. <br>
     * @return Hijo 3 del nodo
     */
    public Nodo2_3<T> darHijoDer( )
    {
        return hijoDer;
    }

    /**
     * Indica si el nodo es una hoja.<br>
     * <b>post: </b> Se retornó true si el nodo es una hoja o false de lo contrario. Un nodo es una hoja si sus tres hijos se encuentran en null.
     * @return True si es hoja, False si no
     */
    public boolean esHoja( )
    {
        return hijoIzq == null && hijoCent == null && hijoDer == null;
    }

    /**
     * Informa si un elemento se encuentra presente en el árbol 2-3 cuya raíz es el nodo actual. <br>
     * <b>pre: </b> modelo!=null. <br>
     * <b>post: </b> Se retorno un elemento que corresponde al modelo dado. Si ningún elemento corresponde al elemento se retorna null.
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
     * Calcula la altura del árbol 2-3 cuya raíz es este nodo. <br>
     * <b>post: </b> Se retornó la altura del árbol.
     * @return Devuelve la altura del árbol
     */
    public int darAltura( )
    {
        if( esHoja( ) )
        {
            return 1;
        }
        else
        {
            return hijoIzq.darAltura( ) + 1;
        }
    }

    /**
     * Agrega un nuevo elemento al árbol 2-3 cuya raíz el nodo actual y retorna el nodo que corresponde a la nueva raíz del árbol<br>
     * <b>pre: </b> obj es diferente de null. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se insertó un elemento en el árbol si este no existía previamente en la estructura.
     * 
     * @param obj != null
     * @return Nodo nuevo
     * @throws ElementoExisteException El elemento ya existe en el árbol
     */
    public Nodo2_3<T> insertar( T obj ) throws ElementoExisteException
    {
        Retorno ret = new Retorno( );
        if( auxInsertar( obj, ret ) )
        {
            Nodo2_3<T> nodo = new Nodo2_3<T>( ret.val );
            nodo.hijoIzq = ret.izq;
            nodo.hijoCent = ret.der;
            return nodo;
        }
        else
        {
            return this;
        }
    }

    /**
     * Elimina un valor dado del árbol 2-3 cuya raíz es este nodo, y retorna una referencia al nodo raíz de la estructura resultante. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se eliminó un elemento del árbol si este existía en la estructura.
     * 
     * @param obj El objeto a ser eliminado
     * @return Nodo resultado de la operación
     * @throws ElementoNoExisteException Excepción generada si el elemento especificado no existe
     */
    public Nodo2_3<T> eliminar( T obj ) throws ElementoNoExisteException
    {
        return auxEliminar( obj ) ? hijoIzq : this;
    }

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>pre: </b> resultado!=null. <br>
     * <b>post: </b> Se retorno un vector con el recorrido en inorden del árbol.
     * 
     * @param resultado Vector con los elementos del árbol en inorden
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
     * Inserta el objeto en el árbol, sin aumentar de altura. Si debe aumentar de altura retorna true, y en la estructura de retorno, envía los dos árboles 2-3 que deben
     * subir, y la raíz del nuevo nivel. <br>
     * <b>post: </b> Se insertó un elemento en el árbol si este no existía previamente en la estructura. Se retornó true si se tuvo que aumentar la altura o false de lo
     * contrario.
     * 
     * @param elemento Elemento a insertar
     * @param ret Retorno de la operación
     * @return True si debe aumentar altura, False si no
     * @throws ElementoExisteException Excepción generada si el elemento especificado ya existe en el árbol.
     */
    private boolean auxInsertar( T elemento, Retorno ret ) throws ElementoExisteException
    {
        // Compara el elemento con los 2 elementos
        int resultado1 = elemento.compareTo( raizIzq );
        int resultado2 = ( raizDer == null ) ? 0 : elemento.compareTo( raizDer );

        // Verifica que el elemento que llega no se encuentre en el nodo
        if( resultado1 == 0 || ( raizDer != null && resultado2 == 0 ) )
        {
            throw new ElementoExisteException( "El elemento ya existe en el árbol" );
        }
        else if( esHoja( ) )
        {
            return insHoja( elemento, ret );
        }
        else if( resultado1 < 0 )
        {
            return hijoIzq.auxInsertar( elemento, ret ) ? subirInfoIzq( ret ) : false;
        }
        else if( raizDer == null || resultado2 < 0 )
        {
            return hijoCent.auxInsertar( elemento, ret ) ? subirInfoCent( ret ) : false;
        }
        else
        {
            return hijoDer.auxInsertar( elemento, ret ) ? subirInfoDer( ret ) : false;
        }
    }

    /**
     * Inserta un elemento en una hoja del árbol. Si debe aumentar de altura retorna true, y en la estructura de retorno envía los dos nodos en los que se dividió la hoja y la
     * raíz del nuevo nivel. <br>
     * <b>post: </b> Se insertó un elemento en el árbol si este no existía previamente en la estructura. Se retornó true si se debe aumentar la altura o false de lo contrario.
     * 
     * @param elemento Elemento a insertar
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False sino
     */
    private boolean insHoja( T elemento, Retorno ret )
    {
        if( raizDer == null )
        {
            // Caso 1: hay espacio en el nodo: no hay que aumentar un nivel.
            // Basta con ordenar las raíces
            if( elemento.compareTo( raizIzq ) < 0 )
            {
                raizDer = raizIzq;
                raizIzq = elemento;
            }
            else
            {
                raizDer = elemento;
            }
            return false;
        }
        else
        {
            // Caso 2: no hay espacio en el nodo y se debe partir
            if( elemento.compareTo( raizIzq ) < 0 )
            {
                // Sube la raíz 1
                ret.val = raizIzq;
                ret.izq = new Nodo2_3<T>( elemento );
                raizIzq = raizDer;
                raizDer = null;
                ret.der = this;
            }
            else if( elemento.compareTo( raizDer ) < 0 )
            {
                // Sube el elemento nuevo
                ret.val = elemento;
                ret.izq = new Nodo2_3<T>( raizIzq );
                raizIzq = raizDer;
                raizDer = null;
                ret.der = this;
            }
            else
            {
                // Sube la raíz 2
                ret.val = raizDer;
                ret.der = new Nodo2_3<T>( elemento );
                raizDer = null;
                ret.izq = this;
            }
            return true;
        }
    }

    /**
     * La inserción se hizo sobre el primer subárbol, vienen subiendo en la estructura de retorno un elemento y dos subárboles. <br>
     * <b>post: </b> Se retornó true si se debe aumentar la altura del árbol o false de lo contrario.
     * 
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfoIzq( Retorno ret )
    {
        if( raizDer == null )
        {
            // Hay campo en este nodo: solo hay que reorganizar
            raizDer = raizIzq;
            hijoDer = hijoCent;
            raizIzq = ret.val;
            hijoIzq = ret.izq;
            hijoCent = ret.der;
            return false;
        }
        else
        {
            // No hay campo en el nodo: hay que partir y volver a subir
            Nodo2_3<T> nodo = new Nodo2_3<T>( raizDer );
            nodo.hijoIzq = hijoCent;
            nodo.hijoCent = hijoDer;
            T temp = raizIzq;
            raizIzq = ret.val;
            raizDer = null;
            hijoIzq = ret.izq;
            hijoCent = ret.der;
            hijoDer = null;
            ret.val = temp;
            ret.izq = this;
            ret.der = nodo;
            return true;
        }
    }

    /**
     * La inserción se hizo sobre el segundo subárbol, vienen subiendo en la estructura de retorno un elemento y dos subárboles. <br>
     * <b>post: </b> True si se debe aumentar la altura del árbol o false de lo contrario.
     * 
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfoCent( Retorno ret )
    {
        if( raizDer == null )
        {
            // Hay campo en este nodo: solo hay que reorganizar
            raizDer = ret.val;
            hijoCent = ret.izq;
            hijoDer = ret.der;
            return false;
        }
        else
        {
            // No hay campo en el nodo: hay que partir y volver a subir
            Nodo2_3<T> nodo = new Nodo2_3<T>( raizDer );
            nodo.hijoIzq = ret.der;
            nodo.hijoCent = hijoDer;
            hijoCent = ret.izq;
            hijoDer = null;
            raizDer = null;
            ret.izq = this;
            ret.der = nodo;
            return true;
        }
    }

    /**
     * La inserción se hizo sobre el tercer subárbol, vienen subiendo en la estructura de retorno un elemento y dos subárboles. <br>
     * <b>post: </b> Se retornó true si se debe aumentar la altura del árbol o false de lo contrario.
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfoDer( Retorno ret )
    {
        // No hay campo en el nodo: hay que partir y volver a subir
        Nodo2_3<T> nodo = new Nodo2_3<T>( ret.val );
        nodo.hijoIzq = ret.izq;
        nodo.hijoCent = ret.der;
        ret.val = raizDer;
        raizDer = null;
        hijoDer = null;
        ret.izq = this;
        ret.der = nodo;
        return true;
    }

    /**
     * Elimina un elemento del árbol 2-3. Retorna true si el árbol resultado ha perdido un nivel. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se retornó si la altura del árbol disminuye o false de lo contrario.
     * 
     * @param obj Elemento a eliminar
     * @return True si la altura cambió, False si no
     * @throws ElementoNoExisteException El elemento especificado no existe en el nodo
     */
    private boolean auxEliminar( T obj ) throws ElementoNoExisteException
    {
        int resultado1 = obj.compareTo( raizIzq );
        int resultado2 = ( raizDer == null ) ? 0 : obj.compareTo( raizDer );
        if( resultado1 == 0 )
        {
            if( esHoja( ) )
            {
                if( raizDer == null )
                {
                    return true;
                }
                else
                {
                    raizIzq = raizDer;
                    raizDer = null;
                    return false;
                }
            }
            else
            {
                T menor = hijoCent.calcularMenorElem( );
                raizIzq = menor;
                return hijoCent.auxEliminar( menor ) ? restaurarHijoCent( ) : false;
            }
        }
        else if( raizDer != null && resultado2 == 0 )
        {
            if( esHoja( ) )
            {
                raizDer = null;
                return false;
            }
            else
            {
                T menor = hijoDer.calcularMenorElem( );
                raizDer = menor;
                return hijoDer.auxEliminar( menor ) ? restaurarHijoDer( ) : false;
            }
        }
        else if( resultado1 < 0 && hijoIzq != null )
        {
            return hijoIzq.auxEliminar( obj ) ? restaurarHijoIzq( ) : false;
        }
        else if( ( raizDer == null || resultado2 < 0 ) && hijoCent != null )
        {
            return hijoCent.auxEliminar( obj ) ? restaurarHijoCent( ) : false;
        }
        else if( hijoDer != null )
        {
            return hijoDer.auxEliminar( obj ) ? restaurarHijoDer( ) : false;
        }
        else
            throw new ElementoNoExisteException( "El elemento especificado no existe en el árbol" );

    }

    /**
     * Retorna el menor elemento del árbol 2-3 cuya raíz es este nodo. <br>
     * <b>post: </b> Se retornó el menor elemento del árbol cuya raíz es este nodo.
     * 
     * @return Menor elemento del árbol
     */
    private T calcularMenorElem( )
    {
        Nodo2_3<T> aux = this;
        while( aux.hijoIzq != null )
            aux = aux.hijoIzq;
        return aux.raizIzq;
    }

    /**
     * Se ha eliminado un elemento del hijo 1, y por esta razón se ha perdido altura. Debe rebalancear la información del nodo actual. <br>
     * <b>post: </b> Se retornó true si es necesario rebalancear el árbol o false de lo contrario
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurarHijoIzq( )
    {
        hijoIzq.raizIzq = raizIzq;
        raizIzq = hijoCent.raizIzq;
        hijoIzq.hijoCent = hijoCent.hijoIzq;
        hijoCent.hijoIzq = hijoCent.hijoCent;
        hijoCent.hijoCent = null;
        if( hijoCent.raizDer != null )
        {
            hijoCent.raizIzq = hijoCent.raizDer;
            hijoCent.raizDer = null;
            hijoCent.hijoCent = hijoCent.hijoDer;
            hijoCent.hijoDer = null;
            return false;
        }
        else
            return restaurarHijoCent( );
    }

    /**
     * Se ha eliminado un elemento del hijo 2, y por esa razón se ha perdido altura. Debe rebalancear la información del nodo actual. <br>
     * <b>post: </b> Se retornó true si es necesario rebalancear el árbol o false de lo contrario.
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurarHijoCent( )
    {
        if( raizDer != null )
        {
            hijoCent.raizIzq = raizDer;
            hijoCent.hijoCent = hijoDer.hijoIzq;
            raizDer = hijoDer.raizIzq;
            hijoDer.hijoIzq = hijoDer.hijoCent;
            if( hijoDer.raizDer != null )
            {
                hijoDer.raizIzq = hijoDer.raizDer;
                hijoDer.raizDer = null;
                hijoDer.hijoCent = hijoDer.hijoDer;
                hijoDer.hijoDer = null;
                return false;
            }
            else
            {
                return restaurarHijoDer( );
            }
        }
        else if( hijoIzq.raizDer != null )
        {
            hijoCent.raizIzq = raizIzq;
            raizIzq = hijoIzq.raizDer;
            hijoIzq.raizDer = null;
            hijoCent.hijoCent = hijoCent.hijoIzq;
            hijoCent.hijoIzq = hijoIzq.hijoDer;
            hijoIzq.hijoDer = null;
            return false;
        }
        else
        {
            hijoIzq.raizDer = raizIzq;
            hijoIzq.hijoDer = hijoCent.hijoIzq;
            return true;
        }
    }

    /**
     * Se ha eliminado un elemento del hijo 3, y por esta razón se ha perdido altura. Debe rebalancear la información del nodo actual. <br>
     * <b>post: </b> Se retornó true si es necesario rebalancear el árbol o false de lo contrario.
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurarHijoDer( )
    {
        if( hijoCent.raizDer != null )
        {
            hijoDer.raizIzq = raizDer;
            raizDer = hijoCent.raizDer;
            hijoCent.raizDer = null;
            hijoDer.hijoCent = hijoDer.hijoIzq;
            hijoDer.hijoIzq = hijoCent.hijoDer;
            hijoCent.hijoDer = null;
        }
        else
        {
            hijoCent.raizDer = raizDer;
            hijoCent.hijoDer = hijoDer.hijoIzq;
            raizDer = null;
            hijoDer = null;
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    /**
     * Contiene dos nodos de árbol 2-3, que vienen desplazándose como parte del proceso de modificación
     */
    private class Retorno
    {
        // -----------------------------------------------------------------
        // Atributos
        // -----------------------------------------------------------------
        /**
         * Valor que va subiendo
         */
        private T val;

        /**
         * Nodo izquierdo desplazándose
         */
        private Nodo2_3<T> izq;

        /**
         * Nodo derecho desplazándose
         */
        private Nodo2_3<T> der;

        // -----------------------------------------------------------------
        // Constructores
        // -----------------------------------------------------------------

        /**
         * Constructor del retorno
         */
        private Retorno( )
        {
            val = null;
            izq = null;
            der = null;

        }

        /**
         * Constructor del retorno con parámetros
         * 
         * @param pVal Elemento
         * @param pIzq Nodo izquierdo
         * @param pDer Nodo derecho
         */
        private Retorno( T pVal, Nodo2_3<T> pIzq, Nodo2_3<T> pDer )
        {
            val = pVal;
            izq = pIzq;
            der = pDer;
        }
    }
}
