/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoB.java,v 1.1 2008/03/30 01:53:03 jua-gome Exp $
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

package uniandes.cupi2.collections.arbol.arbolB;

import java.io.Serializable;

import uniandes.cupi2.collections.arbol.ElementoExisteException;
import uniandes.cupi2.collections.arbol.ElementoNoExisteException;
import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;
import uniandes.cupi2.collections.lista.Lista;

/**
 * Nodo del árbol B
 * 
 * @param <T> Tipo de datos que contiene cada nodo del árbol. Debe implementar la interface Comparable.
 */
public class NodoB<T extends Comparable> implements Serializable
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Orden del árbol B
     */
    private int orden;

    /**
     * Lista de las raíces del nodo actual
     */
    private Lista<T> raices;

    /**
     * Lista con los hijos del nodo actual
     */
    private Lista<NodoB<T>> hijos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo con el primer elemento. <br>
     * <b> post: </b> Se construyó el nodo con el elemento especificado.
     * @param obj Elemento a agregar al nodo
     * @param orden Orden del árbol B
     */
    public NodoB( T obj, int orden )
    {
        this.orden = orden;
        raices = new Lista<T>( );
        raices.agregar( obj );
        hijos = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Indica si el nodo es una hoja.<br>
     * <b>post: </b> Se retornó true si el nodo es una hoja o false de lo contrario. Un nodo es una hoja si no tiene hijos.
     * @return True si es hoja, False si no
     */
    public boolean esHoja( )
    {
        return hijos == null;
    }

    /**
     * Informa si un elemento se encuentra presente en el árbol B cuya raíz es el nodo actual. <br>
     * <b>pre: </b> modelo!=null. <br>
     * <b>post: </b> Se retorno un elemento que corresponde al modelo dado. Si ningún elemento corresponde al elemento se retorna null.
     * @param modelo Modelo del elemento que se desea buscar
     * @return Elemento encontrado o null si no lo encuentra
     */
    public T buscar( T modelo )
    {
        for( int i = 0; i <= raices.darLongitud( ); i++ )
        {
            if( i != raices.darLongitud( ) )
            {
                // Compara con el primer elemento
                int resultado1 = modelo.compareTo( raices.darElemento( i ) );
                if( resultado1 == 0 )
                {
                    return raices.darElemento( i );
                }
                else if( resultado1 < 0 )
                {
                    return ( hijos.darElemento( i ) == null ) ? null : hijos.darElemento( i ).buscar( modelo );
                }
            }
            else
            {
                int resultado1 = modelo.compareTo( raices.darElemento( i - 1 ) );
                if( resultado1 > 0 )
                    return esHoja( ) ? null : hijos.darElemento( i ).buscar( modelo );
            }
        }
        return null;
    }

    /**
     * Calcula la altura del árbol B cuya raíz es este nodo. <br>
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
            return hijos.darElemento( 0 ).darAltura( ) + 1;
        }
    }

    /**
     * Agrega un nuevo elemento al árbol B cuya raíz el nodo actual y retorna el nodo que corresponde a la nueva raíz del árbol<br>
     * <b>pre: </b> obj es diferente de null. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se insertó un elemento en el árbol si este no existía previamente en la estructura.
     * 
     * @param obj != null
     * @return Nodo nuevo
     * @throws ElementoExisteException El elemento ya existe en el árbol
     */
    public NodoB<T> insertar( T obj ) throws ElementoExisteException
    {
        Retorno ret = new Retorno( );
        if( auxInsertar( obj, ret ) )
        {
            NodoB<T> nodo = new NodoB<T>( ret.val, orden );
            nodo.hijos = new Lista<NodoB<T>>( );
            nodo.hijos.agregar( ret.izq );
            nodo.hijos.agregar( ret.der );
            return nodo;
        }
        else
        {
            return this;
        }
    }

    /**
     * Elimina un valor dado del árbol B cuya raíz es este nodo, y retorna una referencia al nodo raíz de la estructura resultante. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se eliminó un elemento del árbol si este existía en la estructura.
     * 
     * @param obj El objeto a ser eliminado
     * @return Nodo resultado de la operación
     * @throws ElementoNoExisteException Excepción generada si el elemento especificado no existe
     */
    public NodoB<T> eliminar( T obj ) throws ElementoNoExisteException
    {
        if( auxEliminar( obj ) )
        {
            return hijos != null ? hijos.darElemento( 0 ) : null;
        }
        return this;
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
        for( int i = 0; i <= raices.darLongitud( ); i++ )
        {
            if( !esHoja( ) )
                hijos.darElemento( i ).inorden( resultado );
            if( i < raices.darLongitud( ) )
                resultado.agregar( raices.darElemento( i ) );
        }
    }

    /**
     * Retorna la raíz dada la posición especificada
     * @param pos Posición donde se quiere buscar la raíz
     * @return Elemento que se encuentra en la posición dada, si la posición esta fuera del rango retorna null
     */
    public T darRaiz( int pos )
    {
        return raices.darLongitud( ) < pos ? null : raices.darElemento( pos );
    }

    /**
     * Retorna el hijo dada la posición especificada
     * @param pos Posición donde se quiere buscar la raíz
     * @return Nodo que se encuentra en la posición dada, si la posición esta fuera del rango retorna null
     */
    public NodoB<T> darHijo( int pos )
    {
        return hijos == null || hijos.darLongitud( ) < pos ? null : hijos.darElemento( pos );
    }

    /**
     * Retorna la cantidad de hijos que tiene el nodo
     * @return Entero mayor o igual a cero
     */
    public int darCantidadHijos( )
    {
        return hijos != null ? hijos.darLongitud( ) : 0;
    }

    /**
     * Retorna la cantidad de raíces que tiene el nodo
     * @return Entero mayor a cero
     */
    public int darCantidadRaices( )
    {
        return raices.darLongitud( );
    }

    // -----------------------------------------------------------------
    // Operaciones Auxiliares
    // -----------------------------------------------------------------

    /**
     * Inserta el objeto en el árbol, sin aumentar de altura. Si debe aumentar de altura retorna true, y en la estructura de retorno, envía los dos árboles B que deben subir,
     * y la raíz del nuevo nivel. <br>
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
        // Verifica que el elemento que llega no se encuentre en el nodo
        if( raices.contiene( elemento ) )
        {
            throw new ElementoExisteException( "El elemento ya existe en el árbol" );
        }
        else if( esHoja( ) )
        {
            return insHoja( elemento, ret );
        }
        else
        {
            // Se debe agregar en alguno de los hijos.
            for( int i = 0; i < hijos.darLongitud( ); i++ )
            {
                int resultado = i < raices.darLongitud( ) ? elemento.compareTo( raices.darElemento( i ) ) : 1;
                if( resultado < 0 )
                {
                    return hijos.darElemento( i ).auxInsertar( elemento, ret ) ? subirInfo( hijos.darElemento( i ), ret ) : false;
                }
                else if( i == raices.darLongitud( ) )
                {
                    return hijos.darElemento( i ).auxInsertar( elemento, ret ) ? subirInfo( hijos.darElemento( i ), ret ) : false;
                }
            }
        }
        return false;
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
        raices.agregar( elemento );
        raices = organizar( raices );
        if( raices.darLongitud( ) > orden - 1 )
        {
            // Caso 2: El nodo es mas grande de lo que permite el orden
            for( int i = 0; i < raices.darLongitud( ); i++ )
            {
                T temp = raices.darElemento( i );
                if( i < ( orden - 1 ) / 2 )
                {
                    if( ret.izq == null )
                    {
                        ret.izq = new NodoB<T>( temp, orden );
                    }
                    else
                    {
                        ret.izq.raices.agregar( temp );
                    }
                }
                else
                {
                    if( ret.val == null )
                    {
                        ret.val = temp;
                    }
                    else if( ret.der == null )
                    {
                        ret.der = new NodoB<T>( temp, orden );
                    }
                    else
                    {
                        ret.der.raices.agregar( temp );
                    }
                }
            }
            raices.eliminar( elemento );
            return true;
        }
        return false;
    }

    /**
     * Elimina un elemento del árbol B. Retorna true si el árbol resultado ha perdido un nivel. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se retornó si la altura del árbol disminuye o false de lo contrario.
     * 
     * @param obj Elemento a eliminar
     * @return True si la altura cambió, False si no
     * @throws ElementoNoExisteException El elemento especificado no existe en el nodo
     */
    private boolean auxEliminar( T obj ) throws ElementoNoExisteException
    {
        if( raices.contiene( obj ) )
        {
            // El elemento a eliminar se encuentra en las raíces del nodo actual
            if( esHoja( ) )
            {
                raices.eliminar( obj );
                if( raices.darLongitud( ) < ( orden - 1 ) / 2 )
                    return true;
                else
                    return false;
            }
            else
            {
                int posEliminar = raices.buscar( obj );
                T menor = hijos.darElemento( posEliminar + 1 ).calcularMenorElem( );
                raices.agregar( menor );
                organizar( raices );
                raices.eliminar( posEliminar );
                return hijos.darElemento( posEliminar + 1 ).auxEliminar( menor ) ? restaurarHijos( ) : false;
            }
        }
        else if( hijos != null )
        {
            // Se debe eliminar en alguno de los hijos.
            for( int i = 0; i < hijos.darLongitud( ); i++ )
            {
                int resultado = i < raices.darLongitud( ) ? obj.compareTo( raices.darElemento( i ) ) : 1;
                if( resultado < 0 )
                {
                    return hijos.darElemento( i ).auxEliminar( obj ) ? restaurarHijos( ) : false;
                }
                else if( i == raices.darLongitud( ) )
                {
                    return hijos.darElemento( i ).auxEliminar( obj ) ? restaurarHijos( ) : false;
                }
            }
        }
        else
            throw new ElementoNoExisteException( "El elemento especificado no existe en el árbol" );
        return false;

    }

    /**
     * Se ha eliminado un elemento de alguno de los hijos, y por esta razón se ha perdido altura. Debe rebalancear la información del nodo actual. <br>
     * <b>post: </b> Se retornó true si es necesario rebalancear el árbol o false de lo contrario
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurarHijos( )
    {
        for( int i = 0; i < hijos.darLongitud( ); i++ )
        {
            NodoB<T> hijo = hijos.darElemento( i );
            if( hijo.raices.darLongitud( ) < ( orden - 1 ) / 2 )
            {
                if( raices.darLongitud( ) > i )
                {
                    // El hijo a restaurar debe hacerlo con el hijo posterior
                    NodoB<T> hijoPost = hijos.darElemento( i + 1 );
                    if( hijoPost != null && hijoPost.raices.darLongitud( ) >= ( orden - 1 ) / 2 )
                    {
                        // El hijo posterior tiene los elementos necesarios para cumplir la regla de cantidad de raíces
                        hijo.raices.agregar( raices.eliminar( i ) );
                        raices.agregar( hijoPost.raices.eliminar( 0 ) );
                        organizar( raices );
                        // Se deben tener en cuenta los hijos del elemento que fue movido
                        if( hijoPost.hijos != null )
                        {
                            hijo.hijos.agregar( hijoPost.hijos.eliminar( 0 ) );
                        }
                    }
                    else if( hijoPost != null )
                    {
                        // El hijo posterior no puede perder un elemento y continuar cumpliendo con la reglas de raíces
                        // Es necesario unir los dos nodos
                        hijoPost.raices.agregar( raices.eliminar( i ) );
                        organizar( hijoPost.raices );
                        for( int j = hijo.raices.darLongitud( ) - 1; j >= 0; j-- )
                        {
                            // Se agregan los elementos que están en el nodo a eliminar
                            hijoPost.raices.insertar( hijo.raices.eliminar( j ), 0 );
                        }
                        if( !hijo.esHoja( ) )
                        {
                            for( int j = hijo.hijos.darLongitud( ) - 1; j >= 0; j-- )
                            {
                                // Se agregan los elementos que están en el nodo a eliminar
                                hijoPost.hijos.insertar( hijo.hijos.eliminar( j ), 0 );
                            }
                        }
                        hijos.eliminar( hijo );
                        if( raices.darLongitud( ) == 0 )
                            return true;
                    }
                }
                else
                {
                    // El hijo a restaurar debe hacerlo con el hijo anterior
                    NodoB<T> hijoAnt = hijos.darElemento( i - 1 );
                    if( hijoAnt != null && hijoAnt.raices.darLongitud( ) > ( orden - 1 ) / 2 )
                    {
                        // El hijo anterior tiene los elementos necesarios para cumplir la regla de cantidad de raíces
                        T nRaiz = hijoAnt.raices.eliminar( hijoAnt.raices.darLongitud( ) - 1 );
                        hijo.raices.insertar( raices.eliminar( raices.darLongitud( ) - 1 ), 0 );
                        raices.agregar( nRaiz );
                    }
                    else
                    {
                        // El hijo anterior no puede perder un elemento y continuar cumpliendo con la reglas de raíces
                        // Es necesario unir los dos nodos
                        hijoAnt.raices.agregar( raices.eliminar( raices.darLongitud( ) - 1 ) );
                        for( int j = hijo.raices.darLongitud( ) - 1; j >= 0; j-- )
                        {
                            hijoAnt.raices.agregar( hijo.raices.eliminar( j ) );
                        }
                        if( !hijo.esHoja( ) )
                        {
                            for( int j = hijo.hijos.darLongitud( ) - 1; j >= 0; j-- )
                            {
                                hijoAnt.hijos.agregar( hijo.hijos.eliminar( j ) );
                            }
                        }
                        hijos.eliminar( hijo );
                        if( raices.darLongitud( ) == 0 )
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * La inserción se hizo sobre el hijo especificado, vienen subiendo en la estructura de retorno un elemento y dos subárboles. <br>
     * <b>post: </b> Se retornó true si se debe aumentar la altura del árbol o false de lo contrario.
     * @param nodoHijo Nodo donde fue realizada la inserci[on del nuevo elemento
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfo( NodoB<T> nodoHijo, Retorno ret )
    {
        if( raices.darLongitud( ) == ( orden - 1 ) )
        {
            // El nodo esta lleno y debe separase
            int posEliminado = hijos.buscar( nodoHijo );
            hijos.eliminar( posEliminado );
            hijos.insertar( ret.izq, posEliminado );
            hijos.insertar( ret.der, posEliminado + 1 );
            raices.agregar( ret.val );
            raices = organizar( raices );
            ret.val = null;
            ret.der = null;
            ret.izq = null;

            for( int i = 0; i < raices.darLongitud( ); i++ )
            {
                T temp = raices.darElemento( i );
                if( i < ( orden - 1 ) / 2 )
                {
                    if( ret.izq == null )
                    {
                        ret.izq = new NodoB<T>( temp, orden );
                        ret.izq.hijos = new Lista<NodoB<T>>( );
                    }
                    else
                    {
                        ret.izq.raices.agregar( temp );
                    }
                    ret.izq.hijos.agregar( hijos.darElemento( i ) );
                }
                else
                {
                    if( ret.val == null )
                    {
                        ret.val = temp;
                        ret.izq.hijos.agregar( hijos.darElemento( i ) );
                    }
                    else if( ret.der == null )
                    {
                        ret.der = new NodoB<T>( temp, orden );
                        ret.der.hijos = new Lista<NodoB<T>>( );
                        ret.der.hijos.agregar( hijos.darElemento( i ) );
                    }
                    else
                    {
                        ret.der.raices.agregar( temp );
                        ret.der.hijos.agregar( hijos.darElemento( i ) );
                    }
                    if( i + 1 == raices.darLongitud( ) )
                    {
                        ret.der.hijos.agregar( hijos.darElemento( i + 1 ) );
                    }
                }
            }

            raices.eliminar( ret.val );
            return true;
        }
        else
        {
            // El nodo tiene espacio y hay que reacomodar los hijos
            Lista<NodoB<T>> nHijos = new Lista<NodoB<T>>( );
            Lista<T> nRaices = new Lista<T>( );
            boolean establecido = false;
            for( int i = 0; i < raices.darLongitud( ) || !establecido; )
            {
                if( i == raices.darLongitud( ) || ( !establecido && ret.val.compareTo( raices.darElemento( i ) ) < 0 ) )
                {
                    nRaices.agregar( ret.val );
                    nHijos.agregar( ret.izq );
                    nHijos.agregar( ret.der );
                    establecido = true;
                }
                else
                {
                    T elemento = raices.darElemento( i );
                    nRaices.agregar( elemento );
                    nHijos.agregar( hijos.darElemento( establecido ? i + 1 : i ) );
                    i++;
                }
            }
            raices = nRaices;
            hijos = nHijos;
            return false;
        }
    }

    /**
     * Organiza de menor a mayor la lista especificada.
     * @param lista Lista a ordenar
     * @return Lista ordenada
     */
    private Lista<T> organizar( Lista<T> lista )
    {
        for( int i = 0; i < lista.darLongitud( ); i++ )
        {
            for( int j = 0; j < lista.darLongitud( ) - i - 1; j++ )
            {

                // Si elemento en la posición j es mayor al elemento en la posición j+1
                // se intercambian
                if( lista.darElemento( j ).compareTo( lista.darElemento( j + 1 ) ) > 0 )
                {
                    T temp = lista.darElemento( j );
                    lista.asignar( lista.darElemento( j + 1 ), j );
                    lista.asignar( temp, j + 1 );
                }
            }
        }
        return lista;
    }

    /**
     * Retorna el menor elemento del árbol B cuya raíz es este nodo. <br>
     * <b>post: </b> Se retornó el menor elemento del árbol cuya raíz es este nodo.
     * 
     * @return Menor elemento del árbol
     */
    private T calcularMenorElem( )
    {
        NodoB<T> aux = this;
        while( aux.hijos != null )
            aux = aux.hijos.darElemento( 0 );
        return aux.raices.darElemento( 0 );
    }

    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    /**
     * Contiene dos nodos de árbol B, que vienen desplazándose como parte del proceso de modificación
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
        private NodoB<T> izq;

        /**
         * Nodo derecho desplazándose
         */
        private NodoB<T> der;

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
    }
}
