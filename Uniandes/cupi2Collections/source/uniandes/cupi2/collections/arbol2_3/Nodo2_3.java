/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Nodo2_3.java,v 1.24 2006/06/23 18:26:01 da-romer Exp $
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

package uniandes.cupi2.collections.arbol2_3;

import uniandes.cupi2.collections.iterador.*;

/**
 * Nodo del �rbol 2-3
 * 
 * @param <T> Tipo de datos que contiene cada nodo del �rbol
 */
public class Nodo2_3<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Primera ra�z del nodo
     */
    private T r1;

    /**
     * Segunda ra�z del nodo
     */
    private T r2;

    /**
     * Sub�rbol 1
     */
    private Nodo2_3<T> h1;

    /**
     * Sub�rbol 2
     */
    private Nodo2_3<T> h2;

    /**
     * Sub�rbol 3
     */
    private Nodo2_3<T> h3;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo con el primer elemento. <br>
     * <b> post: </b> Se construy� el nodo con el elemento especificado.
     * @param obj Elemento a agregar al nodo
     */
    public Nodo2_3( T obj )
    {
        r1 = obj;
        r2 = null;
        h1 = null;
        h2 = null;
        h3 = null;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la primera ra�z en el nodo. <br>
     * <b>post: </b> Se retorn� la ra�z uno en el nodo.
     * @return Ra�z uno en el nodo
     */
    public T darRaiz1( )
    {
        return r1;
    }

    /**
     * Devuelve la segunda ra�z en el nodo. <br>
     * <b>post: </b> Se retorn� la ra�z dos en el nodo.
     * @return Ra�z dos en el nodo
     */
    public T darRaiz2( )
    {
        return r2;
    }

    /**
     * Devuelve el Hijo 1 del nodo (izquierda).<br>
     * <b>post: </b> Se retorn� el hijo 1 del nodo.
     * @return Hijo 1 del nodo (izquierda)
     */
    public Nodo2_3<T> darHijo1( )
    {
        return h1;
    }

    /**
     * Devuelve el Hijo 2 del nodo (centro). <br>
     * <b>post: </b> Se retorn� el hijo 2 del nodo. <br>
     * @return Hijo 2 del nodo (centro)
     */
    public Nodo2_3<T> darHijo2( )
    {
        return h2;
    }

    /**
     * Devuelve el Hijo 3 del nodo (derecha). <br>
     * <b>post: </b> Se retorn� el hijo 3 del nodo. <br>
     * @return Hijo 3 del nodo (derecha)
     */
    public Nodo2_3<T> darHijo3( )
    {
        return h3;
    }

    /**
     * Indica si el nodo es una hoja. <br>
     * <b>post: </b> Se retorn� true si el nodo es una hoja o false de lo contrario. Un nodo es una hoja si sus tres hijos se encuentran en null.
     * @return True si es hoja, False si no
     */
    public boolean esHoja( )
    {
        return h1 == null && h2 == null && h3 == null;
    }

    /**
     * Informa si un elemento se encuentra presente en el �rbol 2-3 cuya ra�z es este nodo. <br>
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
        int resultado1 = modelo.compareTo( r1 );
        if( resultado1 == 0 )
        {
            return r1;
        }
        else if( resultado1 < 0 )
        {
            return ( h1 == null ) ? null : h1.buscar( modelo );
        }
        else if( r2 == null )
        {
            return ( h2 == null ) ? null : h2.buscar( modelo );
        }
        else
        {
            // Compara con el segundo elemento
            int resultado2 = modelo.compareTo( r2 );
            if( resultado2 == 0 )
            {
                return r2;
            }
            else if( resultado2 < 0 )
            {
                return ( h2 == null ) ? null : h2.buscar( modelo );
            }
            else
            {
                return ( h3 == null ) ? null : h3.buscar( modelo );
            }
        }
    }

    /**
     * Calcula la altura del �rbol 2-3 cuya ra�z es este nodo. <br>
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
            return h1.darAltura( ) + 1;
        }
    }

    /**
     * Agrega un nuevo elemento al �rbol 2-3 cuya ra�z es este nodo y retorna el nodo que corresponde a la nueva ra�z del �rbol <b>pre: </b> obj es diferente de null. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se insert� un elemento en el �rbol si este no exist�a previamente en la estructura.
     * 
     * @param obj != null
     * @return Nodo nuevo
     * @throws ExisteException El elemento ya existe en el �rbol
     */
    public Nodo2_3<T> insertar( T obj ) throws ExisteException
    {
        Retorno ret = new Retorno( );
        if( auxInsertar( obj, ret ) )
        {
            Nodo2_3<T> nodo = new Nodo2_3<T>( ret.val );
            nodo.h1 = ret.izq;
            nodo.h2 = ret.der;
            return nodo;
        }
        else
        {
            return this;
        }
    }

    /**
     * Elimina un valor dado del �rbol 2-3 cuya ra�z es este nodo, y retorna una referencia al nodo ra�z de la estructura resultante. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se elimin� un elemento del �rbol si este exist�a en la estructura.
     * 
     * @param obj El objeto a ser eliminado
     * @return Nodo resultado de la operaci�n
     * @throws NoExisteException Excepci�n generada si el elemento especificado no existe
     */
    public Nodo2_3<T> eliminar( T obj ) throws NoExisteException
    {
        return auxEliminar( obj ) ? h1 : this;
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
        if( h1 != null )
        {
            h1.inorden( resultado );
        }
        resultado.agregar( r1 );
        if( h2 != null )
        {
            h2.inorden( resultado );
        }
        if( r2 != null )
        {
            resultado.agregar( r2 );
            if( h3 != null )
            {
                h3.inorden( resultado );
            }
        }
    }

    // -----------------------------------------------------------------
    // Operaciones Auxiliares
    // -----------------------------------------------------------------

    /**
     * Inserta el objeto en el �rbol, sin aumentar de altura. Si debe aumentar de altura retorna true, y en la estructura de retorno, env�a los dos �rboles 2-3 que deben
     * subir, y la ra�z del nuevo nivel. <br>
     * <b>post: </b> Se insert� un elemento en el �rbol si este no exist�a previamente en la estructura. Se retorn� true si se tuvo que aumentar la altura o false de lo
     * contrario.
     * 
     * @param elemento Elemento a insertar
     * @param ret Retorno de la operaci�n
     * @return True si debe aumentar altura, False si no
     * @throws ExisteException Excepci�n generada si el elemento especificado ya existe en el �rbol
     */
    private boolean auxInsertar( T elemento, Retorno ret ) throws ExisteException
    {
        // Compara el elemento con los 2 elementos
        int resultado1 = elemento.compareTo( r1 );
        int resultado2 = ( r2 == null ) ? 0 : elemento.compareTo( r2 );

        // Verifica que el elemento que llega no se encuentre en el nodo
        if( resultado1 == 0 || ( r2 != null && resultado2 == 0 ) )
        {
            throw new ExisteException( "El elemento ya existe en el �rbol" );
        }
        else if( esHoja( ) )
        {
            return insHoja( elemento, ret );
        }
        else if( resultado1 < 0 )
        {
            return h1.auxInsertar( elemento, ret ) ? subirInfo1( ret ) : false;
        }
        else if( r2 == null || resultado2 < 0 )
        {
            return h2.auxInsertar( elemento, ret ) ? subirInfo2( ret ) : false;
        }
        else
        {
            return h3.auxInsertar( elemento, ret ) ? subirInfo3( ret ) : false;
        }
    }

    /**
     * Inserta un elemento en una hoja del �rbol. Si debe aumentar de altura retorna true, y en la estructura de retorno env�a los dos nodos en los que se dividi� la hoja y la
     * ra�z del nuevo nivel. <br>
     * <b>post: </b> Se insert� un elemento en el �rbol si este no exist�a previamente en la estructura. Si retorn� true si se debe aumentar la altura o false de lo contrario.
     * 
     * @param elemento Elemento a insertar
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False sino
     */
    private boolean insHoja( T elemento, Retorno ret )
    {
        if( r2 == null )
        {
            // Caso 1: hay espacio en el nodo: no hay que aumentar un nivel.
            // Basta con ordenar las ra�ces
            if( elemento.compareTo( r1 ) < 0 )
            {
                r2 = r1;
                r1 = elemento;
            }
            else
            {
                r2 = elemento;
            }
            return false;
        }
        else
        {
            // Caso 2: no hay espacio en el nodo y se debe partir
            if( elemento.compareTo( r1 ) < 0 )
            {
                // Sube la ra�z 1
                ret.val = r1;
                ret.izq = new Nodo2_3<T>( elemento );
                r1 = r2;
                r2 = null;
                ret.der = this;
            }
            else if( elemento.compareTo( r2 ) < 0 )
            {
                // Sube el elemento nuevo
                ret.val = elemento;
                ret.izq = new Nodo2_3<T>( r1 );
                r1 = r2;
                r2 = null;
                ret.der = this;
            }
            else
            {
                // Sube la ra�z 2
                ret.val = r2;
                ret.der = new Nodo2_3<T>( elemento );
                r2 = null;
                ret.izq = this;
            }
            return true;
        }
    }

    /**
     * La inserci�n se hizo sobre el primer sub�rbol, vienen subiendo en la estructura de retorno un elemento y dos sub�rboles. <br>
     * <b>post: </b> Se retorn� true si se debe aumentar la altura del �rbol o false de lo contrario.
     * 
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfo1( Retorno ret )
    {
        if( r2 == null )
        {
            // Hay campo en este nodo: solo hay que reorganizar
            r2 = r1;
            h3 = h2;
            r1 = ret.val;
            h1 = ret.izq;
            h2 = ret.der;
            return false;
        }
        else
        {
            // No hay campo en el nodo: hay que partir y volver a subir
            Nodo2_3<T> nodo = new Nodo2_3<T>( r2 );
            nodo.h1 = h2;
            nodo.h2 = h3;
            T temp = r1;
            r1 = ret.val;
            r2 = null;
            h1 = ret.izq;
            h2 = ret.der;
            h3 = null;
            ret.val = temp;
            ret.izq = this;
            ret.der = nodo;
            return true;
        }
    }

    /**
     * La inserci�n se hizo sobre el segundo sub�rbol, vienen subiendo en la estructura de retorno un elemento y dos sub�rboles. <br>
     * <b>post: </b> True si se debe aumentar la altura del �rbol o false de lo contrario.
     * 
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfo2( Retorno ret )
    {
        if( r2 == null )
        {
            // Hay campo en este nodo: solo hay que reorganizar
            r2 = ret.val;
            h2 = ret.izq;
            h3 = ret.der;
            return false;
        }
        else
        {
            // No hay campo en el nodo: hay que partir y volver a subir
            Nodo2_3<T> nodo = new Nodo2_3<T>( r2 );
            nodo.h1 = ret.der;
            nodo.h2 = h3;
            h2 = ret.izq;
            h3 = null;
            r2 = null;
            ret.izq = this;
            ret.der = nodo;
            return true;
        }
    }

    /**
     * La inserci�n se hizo sobre el tercer sub�rbol, vienen subiendo en la estructura de retorno un elemento y dos sub�rboles. <br> 
     * <b>post: </b> Se retorn� true si se debe aumentar la altura del �rbol o false de lo contrario.
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfo3( Retorno ret )
    {
        // No hay campo en el nodo: hay que partir y volver a subir
        Nodo2_3<T> nodo = new Nodo2_3<T>( ret.val );
        nodo.h1 = ret.izq;
        nodo.h2 = ret.der;
        ret.val = r2;
        r2 = null;
        h3 = null;
        ret.izq = this;
        ret.der = nodo;
        return true;
    }

    /**
     * Elimina un elemento del �rbol 2-3. Retorna true si el �rbol resultado ha perdido un nivel. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se retorn� si la altura del �rbol disminuye o false de lo contrario.
     * 
     * @param obj Elemento a eliminar
     * @return True si la altura cambi�, False si no
     * @throws NoExisteException El elemento especificado no existe en el nodo
     */
    private boolean auxEliminar( T obj ) throws NoExisteException
    {
        int resultado1 = obj.compareTo( r1 );
        int resultado2 = ( r2 == null ) ? 0 : obj.compareTo( r2 );
        if( resultado1 == 0 )
        {
            if( esHoja( ) )
            {
                if( r2 == null )
                {
                    return true;
                }
                else
                {
                    r1 = r2;
                    r2 = null;
                    return false;
                }
            }
            else
            {
                T menor = h2.calcularMenorElem( );
                r1 = menor;
                return h2.auxEliminar( menor ) ? restaurar2( ) : false;
            }
        }
        else if( r2 != null && resultado2 == 0 )
        {
            if( esHoja( ) )
            {
                r2 = null;
                return false;
            }
            else
            {
                T menor = h3.calcularMenorElem( );
                r2 = menor;
                return h3.auxEliminar( menor ) ? restaurar3( ) : false;
            }
        }
        else if( resultado1 < 0 && h1 != null )
        {
            return h1.auxEliminar( obj ) ? restaurar1( ) : false;
        }
        else if( ( r2 == null || resultado2 < 0 ) && h2 != null )
        {
            return h2.auxEliminar( obj ) ? restaurar2( ) : false;
        }
        else if( h3 != null )
        {
            return h3.auxEliminar( obj ) ? restaurar3( ) : false;
        }
        else
            throw new NoExisteException( "El elemento especificado no existe en el �rbol" );

    }

    /**
     * Retorna el menor elemento del �rbol 2-3 cuya ra�z es este nodo. <br>
     * <b>post: </b> Se retorn� el menor elemento del �rbol cuya ra�z es este nodo.
     * 
     * @return Menor elemento del �rbol
     */
    private T calcularMenorElem( )
    {
        Nodo2_3<T> aux = this;
        while( aux.h1 != null )
            aux = aux.h1;
        return aux.r1;
    }

    /**
     * Se ha eliminado un elemento del hijo 1, y por esta raz�n se ha perdido altura. Debe rebalancear la informaci�n del nodo actual. <br>
     * <b>post: </b> Se retorn� true si es necesario rebalancear el �rbol o false de lo contrario
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurar1( )
    {
        h1.r1 = r1;
        r1 = h2.r1;
        h1.h2 = h2.h1;
        h2.h1 = h2.h2;
        h2.h2 = null;
        if( h2.r2 != null )
        {
            h2.r1 = h2.r2;
            h2.r2 = null;
            h2.h2 = h2.h3;
            h2.h3 = null;
            return false;
        }
        else
            return restaurar2( );
    }

    /**
     * Se ha eliminado un elemento del hijo 2, y por esa raz�n se ha perdido altura. Debe rebalancear la informaci�n del nodo actual. <br>
     * <b>post: </b> Se retorn� true si es necesario rebalancear el �rbol o false de lo contrario.
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurar2( )
    {
        if( r2 != null )
        {
            h2.r1 = r2;
            h2.h2 = h3.h1;
            r2 = h3.r1;
            h3.h1 = h3.h2;
            if( h3.r2 != null )
            {
                h3.r1 = h3.r2;
                h3.r2 = null;
                h3.h2 = h3.h3;
                h3.h3 = null;
                return false;
            }
            else
            {
                return restaurar3( );
            }
        }
        else if( h1.r2 != null )
        {
            h2.r1 = r1;
            r1 = h1.r2;
            h1.r2 = null;
            h2.h2 = h2.h1;
            h2.h1 = h1.h3;
            h1.h3 = null;
            return false;
        }
        else
        {
            h1.r2 = r1;
            h1.h3 = h2.h1;
            return true;
        }
    }

    /**
     * Se ha eliminado un elemento del hijo 3, y por esta raz�n se ha perdido altura. Debe rebalancear la informaci�n del nodo actual. <br>
     * <b>post: </b> Se retorn� true si es necesario rebalancear el �rbol o false de lo contrario.
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurar3( )
    {
        if( h2.r2 != null )
        {
            h3.r1 = r2;
            r2 = h2.r2;
            h2.r2 = null;
            h3.h2 = h3.h1;
            h3.h1 = h2.h3;
            h2.h3 = null;
        }
        else
        {
            h2.r2 = r2;
            h2.h3 = h3.h1;
            r2 = null;
            h3 = null;
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    /**
     * Contiene dos nodos de �rbol 2-3, que vienen desplaz�ndose como parte del proceso de modificaci�n
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
         * Nodo izquierdo desplaz�ndose
         */
        private Nodo2_3<T> izq;

        /**
         * Nodo derecho desplaz�ndose
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
         * Constructor del retorno con par�metros
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
