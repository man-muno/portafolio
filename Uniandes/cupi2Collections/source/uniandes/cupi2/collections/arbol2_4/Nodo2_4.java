/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Nodo2_4.java,v 1.7 2006/09/14 18:52:57 da-romer Exp $
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

import uniandes.cupi2.collections.iterador.*;

/**
 * Nodo del árbol 2-3
 * 
 * @param <T> Tipo de datos que contiene cada nodo del árbol
 */
public class Nodo2_4<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Primera raíz del nodo
     */
    private T r1;

    /**
     * Segunda raíz del nodo
     */
    private T r2;

    /**
     * Tercera raíz del nodo
     */
    private T r3;

    /**
     * Subárbol 1
     */
    private Nodo2_4<T> h1;

    /**
     * Subárbol 2
     */
    private Nodo2_4<T> h2;

    /**
     * Subárbol 3
     */
    private Nodo2_4<T> h3;

    /**
     * Subárbol 4
     */
    private Nodo2_4<T> h4;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo con el primer elemento. <br>
     * <b> post: </b> Se construyó el nodo con el elemento especificado.
     * @param obj Elemento a agregar al nodo
     */
    public Nodo2_4( T obj )
    {
        r1 = obj;
        r2 = null;
        h1 = null;
        h2 = null;
        h3 = null;
        h4 = null;
    }

    /**
     * Constructor del nodo con el primer y segundo elementos. <br>
     * <b> pre: </b> ob1<obj2 <b> post: </b> Se construyó el nodo con los elementos especificado.
     * @param obj1 Elemento 1 a agregar al nodo
     * @param obj2 Elemento 2 a agregar al nodo
     */
    public Nodo2_4( T obj1, T obj2 )
    {
        r1 = obj1;
        r2 = obj2;
        h1 = null;
        h2 = null;
        h3 = null;
        h4 = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la primera raíz en el nodo. <br>
     * <b>post: </b> Se retornó la raíz uno en el nodo.
     * @return Raíz uno en el nodo
     */
    public T darRaiz1( )
    {
        return r1;
    }

    /**
     * Devuelve la segunda raíz en el nodo. <br>
     * <b>post: </b> Se retornó la raíz dos en el nodo.
     * @return Raíz dos en el nodo
     */
    public T darRaiz2( )
    {
        return r2;
    }
    
    /**
     * Devuelve la tercera raíz en el nodo. <br>
     * <b>post: </b> Se retornó la raíz tres en el nodo.
     * @return Raíz tres en el nodo
     */
    public T darRaiz3( )
    {
        return r3;
    }

    /**
     * Devuelve el Hijo 1 del nodo (izquierda).<br>
     * <b>post: </b> Se retornó el hijo 1 del nodo.
     * @return Hijo 1 del nodo (izquierda)
     */
    public Nodo2_4<T> darHijo1( )
    {
        return h1;
    }

    /**
     * Devuelve el Hijo 2 del nodo (centro). <br>
     * <b>post: </b> Se retornó el hijo 2 del nodo. <br>
     * @return Hijo 2 del nodo (centro)
     */
    public Nodo2_4<T> darHijo2( )
    {
        return h2;
    }

    /**
     * Devuelve el Hijo 3 del nodo (derecha). <br>
     * <b>post: </b> Se retornó el hijo 3 del nodo. <br>
     * @return Hijo 3 del nodo (derecha)
     */
    public Nodo2_4<T> darHijo3( )
    {
        return h3;
    }

    /**
     * Devuelve el Hijo 4 del nodo (centro). <br>
     * <b>post: </b> Se retornó el hijo 4 del nodo. <br>
     * @return Hijo 4 del nodo (centro)
     */
    public Nodo2_4<T> darHijo4( )
    {
        return h4;
    }

    /**
     * Indica si el nodo es una hoja. <br>
     * <b>post: </b> Se retornó true si el nodo es una hoja o false de lo contrario. Un nodo es una hoja si sus cuatro hijos se encuentran en null.
     * @return True si es hoja, False si no
     */
    public boolean esHoja( )
    {
        return h1 == null && h2 == null && h3 == null && h4 == null;
    }

    /**
     * Informa si un elemento se encuentra presente en el árbol 2-4 cuya raíz es este nodo. <br>
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
            else if( r3 == null )
            {
                return ( h3 == null ) ? null : h3.buscar( modelo );
            }
            else
            {
                // Compara con el tercer elemento
                int resultado3 = modelo.compareTo( r3 );
                if( resultado3 == 0 )
                {
                    return r3;
                }
                else if( resultado3 < 0 )
                {
                    return ( h3 == null ) ? null : h3.buscar( modelo );
                }
                else
                {
                    return ( h4 == null ) ? null : h4.buscar( modelo );
                }
            }
        }
    }

    /**
     * Calcula la altura del árbol 2-4 cuya raíz es este nodo. <br>
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
            return h1.darAltura( ) + 1;
        }
    }

    /**
     * Agrega un nuevo elemento al árbol 2-4 cuya raíz es este nodo y retorna el nodo que corresponde a la nueva raíz del árbol <b>pre: </b> obj es diferente de null. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se insertó un elemento en el árbol si este no existía previamente en la estructura.
     * 
     * @param obj != null
     * @return Nodo nuevo
     * @throws ExisteException El elemento ya existe en el árbol
     */
    public Nodo2_4<T> insertar( T obj ) throws ExisteException
    {
        Retorno ret = new Retorno( );
        if( auxInsertar( obj, ret ) )
        {
            Nodo2_4<T> nodo = new Nodo2_4<T>( ret.val );
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
     * Elimina un valor dado del árbol 2-4 cuya raíz es este nodo, y retorna una referencia al nodo raíz de la estructura resultante. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se eliminó un elemento del árbol si este existía en la estructura.
     * 
     * @param obj El objeto a ser eliminado
     * @return Nodo resultado de la operación
     * @throws NoExisteException Excepción generada si el elemento especificado no existe
     */
    public Nodo2_4<T> eliminar( T obj ) throws NoExisteException
    {
        return auxEliminar( obj ) ? h1 : this;
    }

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>pre: </b> rsultado!=null. <br>
     * <b>post: </b> Se retorno un vector con el recorrido en inorden del árbol.
     * 
     * @param resultado Vector con los elementos del árbol en inorden
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
            
            if( r3 != null )
            {
                resultado.agregar( r3 );
                if( h4 != null )
                {
                    h4.inorden( resultado );
                }
            }
        }
    }

    // -----------------------------------------------------------------
    // Operaciones Auxiliares
    // -----------------------------------------------------------------

    /**
     * Inserta el objeto en el árbol, sin aumentar de altura. Si debe aumentar de altura retorna true, y en la estructura de retorno, envía los dos árboles 2-4 que deben
     * subir, y la raíz del nuevo nivel. <br>
     * <b>post: </b> Se insertó un elemento en el árbol si este no existía previamente en la estructura. Se retornó true si se tuvo que aumentar la altura o false de lo
     * contrario.
     * 
     * @param elemento Elemento a insertar
     * @param ret Retorno de la operación
     * @return True si debe aumentar altura, False si no
     * @throws ExisteException Excepción generada si el elemento especificado ya existe en el árbol
     */
    private boolean auxInsertar( T elemento, Retorno ret ) throws ExisteException
    {
        // Compara el elemento con los 3 elementos
        int resultado1 = elemento.compareTo( r1 );
        int resultado2 = ( r2 == null ) ? 0 : elemento.compareTo( r2 );
        int resultado3 = ( r3 == null ) ? 0 : elemento.compareTo( r3 );

        // Verifica que el elemento que llega no se encuentre en el nodo
        if( resultado1 == 0 || ( r2 != null && resultado2 == 0 ) || ( r3 != null && resultado3 == 0 ) )
        {
            throw new ExisteException( "El elemento ya existe en el árbol" );
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
        else if( r3 == null || resultado3 < 0 )
        {
            return h3.auxInsertar( elemento, ret ) ? subirInfo3( ret ) : false;
        }
        else
        {
            return h4.auxInsertar( elemento, ret ) ? subirInfo4( ret ) : false;
        }
    }

    /**
     * Inserta un elemento en una hoja del árbol. Si debe aumentar de altura retorna true, y en la estructura de retorno envía los dos nodos en los que se dividió la hoja y la
     * raíz del nuevo nivel. <br>
     * <b>post: </b> Se insertó un elemento en el árbol si este no existía previamente en la estructura. Si retornó true si se debe aumentar la altura o false de lo contrario.
     * 
     * @param elemento Elemento a insertar
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False sino
     */
    private boolean insHoja( T elemento, Retorno ret )
    {
        if( r2 == null )
        {
            // Caso 1a: hay espacio en el nodo: no hay que aumentar un nivel. Sólo hay una raíz.
            // Basta con ordenar las raíces
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
        else if( r3 == null )
        {
            // Caso 1b: hay espacio en el nodo: no hay que aumentar un nivel. Hay dos raíces.
            // Basta con ordenar las raíces
            if( elemento.compareTo( r1 ) < 0 )
            {
                r3 = r2;
                r2 = r1;
                r1 = elemento;
            }
            else if( elemento.compareTo( r2 ) < 0 )
            {
                r3 = r2;
                r2 = elemento;
            }
            else
            {
                r3 = elemento;
            }
            return false;
        }
        else
        {
            // Caso 2: no hay espacio en el nodo y se debe partir

            // Siempre sube la raíz 2
            ret.val = r2;

            ret.izq = new Nodo2_4<T>( r1 );
            ret.der = new Nodo2_4<T>( r3 );

            if( elemento.compareTo( r2 ) < 0 )
            {
                if(elemento.compareTo(r1)<0)
                {
                    ret.izq.r1= elemento;
                    ret.izq.r2= r1;
                }
                else
                {
                    ret.izq.r2= elemento;
                }                
            }
            else
            {
                if(elemento.compareTo(r3)<0)
                {
                    ret.der.r1= elemento;
                    ret.der.r2= r3;
                }
                else
                {
                    ret.der.r2= elemento;
                }
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
    private boolean subirInfo1( Retorno ret )
    {
        if( r2 == null || r3 == null )
        {
            // Hay campo en este nodo: solo hay que reorganizar
            r3 = r2;
            r2 = r1;            
            r1 = ret.val;
            h4 = h3;
            h3 = h2;
            h2 = ret.der;
            h1 = ret.izq;
            return false;
        }
        else
        {
            // No hay campo en el nodo: hay que partir y volver a subir
            Nodo2_4<T> nodo = new Nodo2_4<T>( r3 );
            nodo.h1 = h3;
            nodo.h2 = h4;
            T temp = r2;
            r3 = null;
            r2 = r1;
            r1 = ret.val;
            h4 = null;
            h3 = h2;
            h2 = ret.der;
            h1 = ret.izq;
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
    private boolean subirInfo2( Retorno ret )
    {
        if( r2 == null || r3 == null )
        {
            // Hay campo en este nodo: solo hay que reorganizar
            r3 = r2;
            r2 = ret.val;
            h4 = h3;
            h3 = ret.der;
            h2 = ret.izq;
            return false;
        }
        else
        {
            // No hay campo en el nodo: hay que partir y volver a subir
            Nodo2_4<T> nodo = new Nodo2_4<T>( r3 );
            nodo.h1 = h3;
            nodo.h2 = h4;
            T temp = r2;
            r3 = null;
            r2 = ret.val;
            h4 = null;
            h3 = ret.der;
            h2 = ret.izq;
            ret.val = temp;
            ret.izq = this;
            ret.der = nodo;
            return true;
        }
    }

    /**
     * La inserción se hizo sobre el tercer subárbol, vienen subiendo en la estructura de retorno un elemento y dos subárboles. <br>
     *  <b>post: </b> Se retornó true si se debe aumentar la altura del árbol o false de lo contrario. <br>
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfo3( Retorno ret )
    {
        if( r3 == null )
        {
            // Hay campo en este nodo: solo hay que reorganizar
            r3 = ret.val;            
            h4 = ret.der;
            h3 = ret.izq;            
            return false;
        }
        else
        {
            //No hay campo en el nodo: hay que partir y volver a subir
            Nodo2_4<T> nodo = new Nodo2_4<T>( ret.val );
            nodo.r2= r3; 
            nodo.h1 = ret.izq;
            nodo.h2 = ret.der;
            nodo.h3 = h4;
            T temp = r2;
            r3 = null;
            r2 = null;
            h4 = null;
            h3 = null;            
            ret.val = temp;
            ret.izq = this;
            ret.der = nodo;
            return true;
        }
        
    }

    /**
     * La inserción se hizo sobre el cuarto subárbol, vienen subiendo en la estructura de retorno un elemento y dos subárboles. <br>
     * <b>post: </b> Se retornó true si se debe aumentar la altura del árbol o false de lo contrario. <br>
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfo4( Retorno ret )
    {
        // No hay campo en el nodo: hay que partir y volver a subir
        Nodo2_4<T> nodo = new Nodo2_4<T>( r3 );
        nodo.r2= ret.val; 
        nodo.h1 = h3;
        nodo.h2 = ret.izq;
        nodo.h3 = ret.der;
        T temp = r2;
        r3 = null;
        r2 = null;
        h4 = null;
        h3 = null;            
        ret.val = temp;
        ret.izq = this;
        ret.der = nodo;
        return true;      
    }

    /**
     * Elimina un elemento del árbol 2-4. Retorna true si el árbol resultado ha perdido un nivel. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se retornó si la altura del árbol disminuye o false de lo contrario.
     * 
     * @param obj Elemento a eliminar
     * @return True si la altura cambió, False si no
     * @throws NoExisteException El elemento especificado no existe en el nodo
     */
    private boolean auxEliminar( T obj ) throws NoExisteException
    {
        int resultado1 = obj.compareTo( r1 );
        int resultado2 = ( r2 == null ) ? 0 : obj.compareTo( r2 );
        int resultado3 = ( r3 == null ) ? 0 : obj.compareTo( r3 );
        
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
                    r2 = r3;
                    r3 = null; 
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
                r2= r3; 
                r3= null;                
                return false;
            }
            else
            {
                T menor = h3.calcularMenorElem( );
                r2 = menor;
                return h3.auxEliminar( menor ) ? restaurar3( ) : false;
            }
        }
        else if( r3 != null && resultado3 == 0 )
        {
            if( esHoja( ) )
            {                
                r3= null;                
                return false;
            }
            else
            {
                T menor = h4.calcularMenorElem( );
                r3 = menor;
                return h4.auxEliminar( menor ) ? restaurar4( ) : false;
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
        else if( ( r3 == null || resultado3 < 0 ) && h3 != null )
        {
            return h3.auxEliminar( obj ) ? restaurar3( ) : false;
        }
        else if( h4 != null )
        {
            return h4.auxEliminar( obj ) ? restaurar4( ) : false;
        }
        else
            throw new NoExisteException( "El elemento especificado no existe en el árbol" );

    }

    /**
     * Retorna el menor elemento del árbol 2-3 cuya raíz es este nodo. <br>
     * <b>post: </b> Se retornó el menor elemento del árbol cuya raíz es este nodo.
     * 
     * @return Menor elemento del árbol
     */
    private T calcularMenorElem( )
    {
        Nodo2_4<T> aux = this;
        while( aux.h1 != null )
            aux = aux.h1;
        return aux.r1;
    }

    /**
     * Se ha eliminado un elemento del hijo 1, y por esta razón se ha perdido altura. Debe rebalancear la información del nodo actual. <br>
     * <b>post: </b> Se retornó true si es necesario rebalancear el árbol o false de lo contrario
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
        if( h2.r2 != null && r2!=null)
        {
            h2.r1 = h2.r2;
            h2.r2 = h2.r3;
            h2.r3= null; 
            h2.h2 = h2.h3;
            h2.h3 = h2.h4;            
            h2.h4 = null;
            return false;
        }
        /*else if(r2!=null)
        {
            h2.r1 = r2;
            r2 = h3.r1;
            h2.h2 = h3.h1;
            h3.h1 = h3.h2;
            h3.h2 = null;
            if(h3.r2!=null)
            {
                
                h3.r1 = h3.r2;
                h3.r2 = h3.r3;
                h3.h2 = h3.h3;
                h3.h3 = h3.h4;            
                h3.h4 = null;
                return false;
            }
            else if(r3!=null)
            {
                h3.r1 = r3;
                r3 = h4.r1;
                h3.h2 = h4.h1;
                h4.h1 = h4.h2;
                h4.h2 = null;
                if(h4.r2!=null)
                {
                    
                    h4.r1 = h4.r2;
                    h4.r2 = h4.r3;
                    h4.h2 = h4.h3;
                    h4.h3 = h4.h4;            
                    h4.h4 = null;
                    return false;
                }
                else return restaurar4(); 
            }
            else 
                return restaurar3(); 
        }*/
        else
            return restaurar2( );
    }

    /**
     * Se ha eliminado un elemento del hijo 2, y por esa razón se ha perdido altura. Debe rebalancear la información del nodo actual. <br>
     * <b>post: </b> Se retornó true si es necesario rebalancear el árbol o false de lo contrario.
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
                h3.r2 = h3.r3;    
                h3.r3= null; 
                h3.h2 = h3.h3;
                h3.h3 = h3.h4;
                h3.h4 = null;
                return false;
            }
            else
            {
                return restaurar3( );
            }
        }
        else if( h1.r3 != null )
        {
            h2.r1 = r1;
            r1 = h1.r3;
            h1.r3 = null;
            h2.h2 = h2.h1;
            h2.h1 = h1.h4;
            h1.h4 = null;
            return false;
        }
        else if( h1.r2 != null )
        {
        	h1.r3 = r1;
            h1.h4 = h2.h1;            
            return true;
        }
        else
        {
            /*h1.r3 = r1;
            h1.h4 = h2.h1;*/ //ASI ESTABA ORIGINALMENTE        	        	              
        	h1.r2= r1; 
            h1.r3= h2.r2;
            h1.h3 = h2.h1;
        	
            return true;
        }
    }
    
    /**
     * Se ha eliminado un elemento del hijo 3, y por esa razón se ha perdido altura. Debe rebalancear la información del nodo actual. <br>
     * <b>post: </b> Se retornó true si es necesario rebalancear el árbol o false de lo contrario.
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurar3( )
    {
    	
        if( h2.r3 != null )
        {
            h3.r1 = r2;
            r2 = h2.r3;
            h2.r3 = null;
            h3.h2 = h3.h1;
            h3.h1 = h2.h4;
            h2.h4 = null;
            return false;
        }
        else if( h2.r2 != null )
        {
            h3.r1 = r2;
            r2 = h2.r2;
            h2.r2 = null;
            h3.h2 = h3.h1;
            h3.h1 = h2.h3;
            h2.h3 = null;
            return false;
        }
        else if( r3 != null )
        {
            h3.r1 = r3;
            h3.h2 = h4.h1;
            r3 = h4.r1;
            h4.h1 = h4.h2;
            if( h4.r2 != null )
            {
                h4.r1 = h4.r2;
                h4.r2 = h4.r3;
                h4.r3= null; 
                h3.h2 = h3.h3;
                h3.h3 = h3.h4;
                h3.h4 = null;
                return false;
            }
            else
            {
                return restaurar4( );
            }

        }
        else
        {
            h2.r2 = r2;
            h2.h3 = h3.h1;
            r2= null; //ESTO LO AGREGUE
            h3= null; 
            return false;
        }
    }

    /**
     * Se ha eliminado un elemento del hijo 4, y por esta razón se ha perdido altura. Debe rebalancear la información del nodo actual. <br>
     * <b> pre: </b> r3!=null. <br>
     * <b>post: </b> Se retornó true si es necesario rebalancear el árbol o false de lo contrario.
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurar4( )
    {
        
        if( h3.r3 != null )
        {
            h4.r1 = r3;
            r3 = h3.r3;
            h3.r3 = null;
            h4.h2 = h4.h1;
            h4.h1 = h3.h4;
            h3.h4 = null;            
        }
        else if( h3.r2 != null )
        {
            h4.r1 = r3;
            r3 = h3.r2;
            h3.r2 = null;
            h4.h2 = h4.h1;
            h4.h1 = h3.h3;
            h3.h3 = null;
        }
        else
        {
            h3.r2 = r3;
            h3.h3 = h3.h1;
            r3 = null;
            h4 = null;
            //return true; 
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    /**
     * Contiene dos nodos de árbol 2-4, que vienen desplazándose como parte del proceso de modificación
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
        private Nodo2_4<T> izq;

        /**
         * Nodo derecho desplazándose
         */
        private Nodo2_4<T> der;

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
        private Retorno( T pVal, Nodo2_4<T> pIzq, Nodo2_4<T> pDer )
        {
            val = pVal;
            izq = pIzq;
            der = pDer;
        }
    }
}
