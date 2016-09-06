/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoRojoNegro.java,v 1.9 2007/06/05 01:19:16 man-muno Exp $
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

package uniandes.cupi2.collections.arbolRojoNegro.bak;

import java.io.Serializable;

import uniandes.cupi2.collections.arbol2_4.Arbol2_4;
import uniandes.cupi2.collections.colaEncadenada.ColaEncadenada;
import uniandes.cupi2.collections.colaEncadenada.ColaVaciaException;
import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;

/**
 * Nodo de un �rbol rojo negro
 * @param <T> Tipo de elemento que va a contener cada nodo del �rbol
 */
public class NodoRojoNegro<T extends Comparable> implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa el hecho de que el nodo est� balanceado hacia la izquierda.
     */
    private static final int BIZQ = 1;

    /**
     * Constante que representa el hecho de que el nodo est� balanceado.
     */
    private static final int BAL = 0;

    /**
     * Constante que representa el hecho de que el nodo est� balanceado hacia la derecha.
     */
    private static final int BDER = -1;

    /**
     * Constante que representa el color rojo
     */
    public static final int ROJO = 0;

    /**
     * Constante que representa el color negro
     */
    public static final int NEGRO = 1;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Elemento almacenado en el nodo
     */
    private T elem;

    /**
     * Nodo a la derecha
     */
    private NodoRojoNegro<T> derNodo;

    /**
     * Nodo a la izquierda
     */
    private NodoRojoNegro<T> izqNodo;

    /**
     * Nodo padre del nodo
     */
    private NodoRojoNegro<T> padreNodo;

    /**
     * Indica el estado de balanceo del nodo
     */
    private int balance;

    /**
     * Indica el color del nodo
     */
    private int color;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo. <br>
     * <b>post: </b> Se construy� el nodo con el elemento especificado, derNodo= null, izqNodo= null, color= ROJO.
     * @param pElemento Elemento que va a ser almacenado en el nodo
     */
    public NodoRojoNegro( T pElemento, NodoRojoNegro<T> padre )
    {
        elem = pElemento;
        derNodo = null;
        izqNodo = null;
        padreNodo = padre;
        color = ROJO;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ra�z del �rbol (elemento del nodo). <br>
     * <b>post: </b> Se retorn� la ra�z del �rbol (elemento del nodo).
     * @return Ra�z del �rbol (elemento del nodo)
     */
    public T darRaiz( )
    {
        return elem;
    }

    /**
     * Devuelve el hijo derecho del nodo. <br>
     * <b>post: </b> Se retorn� el hijo derecho del nodo.
     * @return Hijo derecho del nodo
     */
    public NodoRojoNegro<T> darHijoDerecho( )
    {
        return derNodo;
    }

    /**
     * Devuelve el hijo izquierdo del nodo. <br>
     * <b>post: </b> Se retorn� el hijo izquierdo del nodo.
     * @return Hijo izquierdo del nodo
     */
    public NodoRojoNegro<T> darHijoIzquierdo( )
    {
        return izqNodo;
    }

    /**
     * Agrega un nuevo elemento en el �rbol cuya ra�z es el nodo actual. <br>
     * <b>pre: </b> pElemento!=null. <br>
     * <b>post: </b> Se insert� el elemento especificado en el �rbol.
     * @param pElemento elemento que se va a agregar
     * @return Ra�z del �rbol producto de insertar en el �rbol que comienza en el nodo actual el elemento que llega como par�metro
     * @throws ExisteException El elemento ya existe en el �rbol
     */
    public NodoRojoNegro<T> insertar( T pElemento ) throws ExisteException
    {
        // Agrega el elemento al �rbol utilizando una operaci�n auxiliar
        Retorno retorno = new Retorno( null, false );
        auxInsertar( pElemento, retorno );
        return retorno.respuesta;
    }

    /**
     * Elimina el elemento dado como par�metro, del �rbol cuya ra�z es el nodo actual. <br>
     * <b>pre: </b> pElemento!=null. <br>
     * <b>post: </b> Se elimin� el elemento especificado si exist�a en el �rbol.
     * @param pElemento Elemento que se va a eliminar
     * @return Ra�z del �rbol producto de eliminar del �rbol que comienza en el nodo actual el elemento que llega como par�metro
     * @throws NoExisteException El elemento no se encontr� en el �rbol
     */
    public NodoRojoNegro<T> eliminar( T pElemento ) throws NoExisteException
    {
        // Elimina el elemento utilizando una operaci�n auxiliar
        Retorno retorno = new Retorno( null, false );
        auxEliminar( pElemento, retorno );
        return retorno.respuesta;
    }

    /**
     * Busca el elemento cuyo modelo viene dado como par�metro, en el �rbol cuya ra�z es el nodo actual. <br>
     * <b>pre: </b> modelo!=null. <br>
     * <b>post: </b> Se retorn� el elemento que cumple con el modelo o null si no encuentra ninguno.
     * @param modelo Modelo del elemento que se va a buscar
     * @return Elemento que cumple con el modelo o null si no encuentra ninguno
     */
    public T buscar( T modelo )
    {
        // Compara el valor con el valor del nodo
        int resultado = elem.compareTo( modelo );
        if( resultado == 0 )
        {
            // Caso 1: El elemento est� en el nodo ra�z
            return elem;
        }
        else if( resultado > 0 )
        {
            // Caso 2: El elemento puede estar en el sub�rbol izquierdo
            return ( izqNodo != null ) ? izqNodo.buscar( modelo ) : null;
        }
        else
        {
            // Caso 3: El elemento puede estar en el sub�rbol derecho
            return ( derNodo != null ) ? derNodo.buscar( modelo ) : null;
        }
    }

    /**
     * Agrega los elementos al iterador que llega como par�metro, utilizando para esto un recorrido en inorden. <br>
     * <b>pre: </b> resultado!=null. <br>
     * <b>post: </b> Se retorn� el resultado del recorrido.
     * @param resultado Resultado del recorrido
     */
    public void inorden( IteradorSimple<T> resultado )
    {
        // Agrega los elementos del sub�rbol izquierdo
        if( izqNodo != null )
        {
            izqNodo.inorden( resultado );
        }

        try
        {
            // Agrega el elemento que se encuentra en el nodo
            resultado.agregar( elem );
        }
        catch( IteradorException e )
        {
            // Nunca deber�a aparecer esta excepci�n
        }

        // Agrega los elementos del sub�rbol derecho
        if( derNodo != null )
        {
            derNodo.inorden( resultado );
        }
    }

    /**
     * Devuelve la altura del �rbol cuya ra�z es el nodo actual. <br>
     * <b>post: </b> Se retorn� la altura del �rbol cuya ra�z es el nodo actual.
     * @return Altura del �rbol cuya ra�z es el nodo actual
     */
    public int darAltura( )
    {
        int a1 = ( izqNodo == null ) ? 0 : izqNodo.darAltura( );
        int a2 = ( derNodo == null ) ? 0 : derNodo.darAltura( );
        return ( a1 >= a2 ) ? a1 + 1 : a2 + 1;
    }

    /**
     * Devuelve el elemento mayor del �rbol cuya ra�z es el nodo actual. <br>
     * <b>post: </b> Se retorn� el elemento mayor del �rbol cuya ra�z es el nodo actual.
     * @return Elemento mayor del �rbol cuya ra�z es el nodo actual
     */
    public T darMayor( )
    {
        NodoRojoNegro<T> nodo = mayorElemento( );
        return ( nodo == null ) ? null : nodo.darRaiz( );
    }

    /**
     * Devuelve el elemento menor del �rbol cuya ra�z es el nodo actual. <br>
     * <b>post: </b> Se retorn� el elemento menor del �rbol cuya ra�z es el nodo actual.
     * @return Elemento menor del �rbol cuya ra�z es el nodo actual
     */
    public T darMenor( )
    {
        NodoRojoNegro<T> nodo = menorElemento( );
        return ( nodo == null ) ? null : nodo.darRaiz( );
    }

    // -----------------------------------------------------------------
    // Operaciones Auxiliares
    // -----------------------------------------------------------------

    /**
     * Agrega el elemento al �rbol cuya ra�z es el nodo actual. Primero se realiza la inserci�n, luego el cambio de color y finalmente el balanceo. <br>
     * <b>pre: </b> pElemento!=null, retorno!=null. <br>
     * <b>post: </b> Se insert� el elemento en el �rbol.
     * @param pElemento que se va a insertar
     * @param retorno Objeto con la informaci�n de la nueva ra�z del �rbol y un indicador de cambio de altura
     * @throws ExisteException El elemento ya existe en el �rbol
     */
    private void auxInsertar( T pElemento, Retorno retorno ) throws ExisteException
    {
        // Compara el elemento con el valor almacenado en el nodo
        int resultado = elem.compareTo( pElemento );
        if( resultado == 0 )
        {
            // Caso 1: El elemento est� en el nodo actual
            throw new ExisteException( "El elemento ya existe en el �rbol" );
        }
        else if( resultado > 0 )
        {
            // Caso 2: Debe agregar por la izquierda
            if( izqNodo == null )
            {
                // Se debe crear el nodo nuevo
                izqNodo = new NodoRojoNegro<T>( pElemento, this );
                retorno.respuesta = this;
                if( derNodo == null )
                {
                    balance = BIZQ;
                    retorno.diferenciaAltura = true;
                }
                else
                {
                    balance = BAL;
                    retorno.diferenciaAltura = false;

                }
                // cambiarColorNodosInsercion();
            }
            else
            {
                // Caso general de inserci�n por la izquierda
                izqNodo.auxInsertar( pElemento, retorno );
                izqNodo = retorno.respuesta;
                cambiarColorNodosInsercionIzq( retorno );

                // cambiarColorNodosInsercionIzquierda(retorno);

                // Si el nodo actual y el hijo izquierdo son rojos el balanceo debe realizarse un nivel arriba
                if( color == ROJO && ( ( izqNodo != null && izqNodo.darColor( ) == ROJO ) || ( derNodo != null && derNodo.darColor( ) == ROJO ) ) && padreNodo != null )
                {
                    retorno.rotar = false;
                }
                else
                // La rotaci�n se debe realizar en este nivel
                {
                    // retorno.rotar= true;
                }

                // Balancea si es necesario y si se debe rotar en el nivel
                if( retorno.diferenciaAltura && retorno.rotar )
                {
                    // El sub�rbol izquierdo aument� de altura, debe corregirse
                    // el balance y el color de los nodos

                    switch( balance )
                    {
                        case BIZQ:
                            retorno.diferenciaAltura = false;
                            retorno.respuesta = balanceaIzq( );
                            // Se pone la raiz en negro y los dos hijos en rojo
                            // retorno.respuesta.cambiarColor(NEGRO);

                            // if(retorno.respuesta.izqNodo!=null)
                            // retorno.respuesta.izqNodo.cambiarColor(ROJO);

                            // if(retorno.respuesta.derNodo!=null)
                            // retorno.respuesta.derNodo.cambiarColor(ROJO);
                            break;
                        case BAL:
                            balance = BIZQ;
                            retorno.respuesta = this;
                            // izqNodo.cambiarColor(NEGRO);

                            // if(derNodo!=null)
                            // derNodo.cambiarColor(NEGRO);

                            break;
                        case BDER:
                            balance = BAL;
                            retorno.diferenciaAltura = false;
                            retorno.respuesta = this;
                            // izqNodo.cambiarColor(NEGRO);
                            // if(derNodo!=null)
                            // derNodo.cambiarColor(NEGRO);
                            break;
                    }
                }
                else
                {
                    retorno.respuesta = this;
                }
            }
        }
        else
        {
            // Caso 3: Debe agregar por la derecha
            if( derNodo == null )
            {
                // Se debe crear el nodo nuevo
                derNodo = new NodoRojoNegro<T>( pElemento, this );
                retorno.respuesta = this;
                if( izqNodo == null )
                {
                    balance = BDER;
                    retorno.diferenciaAltura = true;
                }
                else
                {
                    balance = BAL;
                    retorno.diferenciaAltura = false;
                }
                // cambiarColorNodosInsercion();
            }
            else
            {
                // Caso general de inserci�n por la derecha

                derNodo.auxInsertar( pElemento, retorno );
                derNodo = retorno.respuesta;
                cambiarColorNodosInsercionDer( retorno );
                if( color == ROJO && ( ( izqNodo != null && izqNodo.darColor( ) == ROJO ) || ( derNodo != null && derNodo.darColor( ) == ROJO ) ) && padreNodo != null )
                {
                    retorno.rotar = false;
                }
                else
                // La rotaci�n se debe realizar en este nivel
                {
                    // retorno.rotar= true;
                }

                // Balancea si es necesario
                if( retorno.diferenciaAltura && retorno.rotar )
                {

                    // El sub�rbol derecho aument� de altura, debe corregirse el
                    // balance y el color

                    switch( balance )
                    {
                        case BIZQ:
                            balance = BAL;
                            retorno.diferenciaAltura = false;
                            retorno.respuesta = this;
                            // derNodo.cambiarColor(NEGRO);
                            // if(izqNodo!=null)
                            // izqNodo.cambiarColor(NEGRO);
                            break;
                        case BAL:
                            balance = BDER;
                            retorno.respuesta = this;
                            // derNodo.cambiarColor(NEGRO);
                            // if(izqNodo!=null)
                            // izqNodo.cambiarColor(NEGRO);
                            break;
                        case BDER:
                            retorno.diferenciaAltura = false;
                            retorno.respuesta = balanceaDer( );
                            // Se pone la raiz en negro y los dos hijos en rojo
                            // retorno.respuesta.cambiarColor(NEGRO);
                            // if(retorno.respuesta.izqNodo!=null)
                            // retorno.respuesta.izqNodo.cambiarColor(ROJO);

                            // if(retorno.respuesta.derNodo!=null)
                            // retorno.respuesta.derNodo.cambiarColor(ROJO);
                            break;
                    }
                }
                else
                {
                    retorno.respuesta = this;
                }
            }
        }
    }

    /**
     * Elimina el elemento del �rbol cuya ra�z es el nodo actual. <br>
     * <b>pre: </b> pElemento!=null, retorno!=null. <br>
     * <b>post: </b> Se elimin� el elemento del �rbol si exist�a.
     * @param pElemento Elemento que se va a eliminar
     * @param retorno Objeto con la informaci�n de la nueva ra�z del �rbol y un indicador de cambio de altura
     * @throws NoExisteException Elemento no encontrado en el �rbol
     */
    private void auxEliminar( T pElemento, Retorno retorno ) throws NoExisteException
    {
        // Compara el elemento con el valor almacenado en el nodo
        int resultado = elem.compareTo( pElemento );
        if( resultado == 0 )
        {
            // Caso 1: El elemento est� en el nodo actual
            if( izqNodo == null & derNodo == null )
            {
                // No tiene hijos, simplemente debe eliminarlo
                retorno.diferenciaAltura = true;
                retorno.respuesta = null;
            }
            else if( izqNodo == null )
            {
                retorno.respuesta = derNodo;
                retorno.diferenciaAltura = true;
            }
            else
            {
                // Reemplaza el nodo con la mayor elemento del nodo izquierdo
                NodoRojoNegro<T> reemplazo = izqNodo.mayorElemento( );
                elem = reemplazo.elem;
                izqNodo.auxEliminar( reemplazo.elem, retorno );
                izqNodo = retorno.respuesta;

                // Balancea si es necesario
                cambiarColorNodosInsercionDerecha( retorno );
                if( retorno.diferenciaAltura )
                {
                    balanElimDer( retorno );
                }
                else
                {
                    retorno.respuesta = this;
                }
            }
        }
        else if( resultado > 0 )
        {
            // Caso 2: El elemento debe estar por la izquierda
            if( izqNodo == null )
            {
                throw new NoExisteException( "El elemento no se encuentra en el �rbol" );
            }
            izqNodo.auxEliminar( pElemento, retorno );
            izqNodo = retorno.respuesta;

            // Balancea si es necesario
            if( retorno.diferenciaAltura )
            {
                balanElimDer( retorno );
            }
            else
            {
                retorno.respuesta = this;
            }
        }
        else
        {
            // Caso 3: El elemento debe estar por la derecha
            if( derNodo == null )
            {
                throw new NoExisteException( "El elemento no se encuentra en el �rbol" );
            }
            derNodo.auxEliminar( pElemento, retorno );
            derNodo = retorno.respuesta;

            // Balancea si es necesario
            cambiarColorNodosInsercionIzquierda( retorno );
            if( retorno.diferenciaAltura )
            {
                balanElimIzq( retorno );
            }
            else
            {
                retorno.respuesta = this;
            }
        }
    }

    /**
     * Balancea el sub�rbol izquierdo de un �rbol AVL que se ha desbalanceado por una inserci�n. Actualiza los factores de balanceo. <br>
     * <b>post: </b> Se balance� el sub�rbol izquierdo y se actualizar�n los factores de balanceo. <br>
     * @return Nodo balanceado
     */
    private NodoRojoNegro<T> balanceaIzq( )
    {
        if( izqNodo.balance == BIZQ )
        {
            // Caso 3
            balance = BAL;
            izqNodo.balance = BAL;
            return roteDer( );
        }
        else
        {
            // Caso 5
            switch( izqNodo.derNodo.balance )
            {
                case BIZQ:
                    balance = BDER;
                    izqNodo.balance = BAL;
                    break;
                case BAL:
                    balance = BAL;
                    izqNodo.balance = BAL;
                    break;
                case BDER:
                    balance = BAL;
                    izqNodo.balance = BIZQ;
                    break;
            }
            izqNodo.derNodo.balance = BAL;
            return roteIzqDer( );
        }
    }

    /**
     * Balancea el sub�rbol derecho de un �rbol AVL que se ha desbalanceado por una inserci�n. Actualiza los factores de balanceo. <br>
     * <b>post: </b> Se balance� el sub�rbol derecho y se actualizar�n los factores de balanceo.
     * @return Nodo balanceado
     */
    private NodoRojoNegro<T> balanceaDer( )
    {
        if( derNodo.balance == BDER )
        {
            // Caso 2
            balance = BAL;
            derNodo.balance = BAL;
            return roteIzq( );
        }
        else
        {
            // Caso 4
            switch( derNodo.izqNodo.balance )
            {
                case BIZQ:
                    balance = BAL;
                    derNodo.balance = BDER;
                    break;
                case BAL:
                    balance = BAL;
                    derNodo.balance = BAL;
                    break;
                case BDER:
                    balance = BIZQ;
                    derNodo.balance = BAL;
                    break;
            }
            derNodo.izqNodo.balance = BAL;
            return roteDerIzq( );
        }
    }

    /**
     * Rota a la izquierda un nodo y sus hijos. <br>
     * <b>post: </b> Se rot� a la izquierda un nodo y sus hijos.
     * @return El nodo rotado a la izquierda
     */
    private NodoRojoNegro<T> roteIzq( )
    {
        NodoRojoNegro<T> temp = derNodo;
        temp.cambiarPadreNodo( darPadreNodo( ) );
        derNodo = temp.izqNodo;
        if( derNodo != null )
            derNodo.cambiarPadreNodo( this );
        temp.izqNodo = this;
        cambiarPadreNodo( temp );
        return temp;
    }

    /**
     * Rota a la derecha un nodo y sus hijos. <br>
     * <b>post: </b> Se rot� a la derecha un nodo y sus hijos.
     * @return Nodo El nodo rotado a la derecha
     */
    private NodoRojoNegro<T> roteDer( )
    {
        NodoRojoNegro<T> temp = izqNodo;
        temp.cambiarPadreNodo( darPadreNodo( ) );
        izqNodo = temp.derNodo;
        if( izqNodo != null )
            izqNodo.cambiarPadreNodo( this );
        temp.derNodo = this;
        cambiarPadreNodo( temp );
        return temp;
    }

    /**
     * Rota a la derecha y despu�s a la izquierda un nodo y sus hijos. <br>
     * <b>post: </b> Se rot� a la derecha y despues a la izquierda un nodo y sus hijos.
     * @return Nodo El nodo rotado a la derecha y luego a la izquierda
     */
    private NodoRojoNegro<T> roteDerIzq( )
    {
        derNodo = derNodo.roteDer( );
        return roteIzq( );
    }

    /**
     * Rota a la izquierda y despu�s a la derecha un nodo y sus hijos. <br>
     * <b>post: </b> Se rot� a la izquierda y despues a la derecha un nodo y sus hijos.
     * @return Nodo El nodo rotado a la izquierda y luego a la derecha
     */
    private NodoRojoNegro<T> roteIzqDer( )
    {
        izqNodo = izqNodo.roteIzq( );
        return roteDer( );
    }

    /**
     * Se ha eliminado un elemento del sub�rbol izquierdo y por esta raz�n el sub�rbol derecho se encuentra desbalanceado. Esta rutina reestablece el balance perdido. Al
     * entrar, retorno.diferenciaAltura == TRUE. <br>
     * <b>pre: </b> retorno!=null. <br>
     * <b>post: </b> Se restableci� el balance� perdido a causa de una eliminac�n en el sub�rbol izquierdo.
     * @param retorno Estructura que contiene el �rbol resultado y una variable que me permite determinar si el nodo est� desbalanceado
     */
    private void balanElimDer( Retorno retorno )
    {
        switch( balance )
        {
            case BIZQ:
                balance = BAL;
                retorno.respuesta = this;
                break;
            case BAL:
                balance = BDER;
                retorno.diferenciaAltura = false;
                retorno.respuesta = this;
                break;
            case BDER:
                if( derNodo.balance != BIZQ )
                {
                    retorno.respuesta = roteIzq( );
                    if( retorno.respuesta.balance == BAL )
                    {
                        retorno.respuesta.balance = BIZQ;
                        retorno.respuesta.izqNodo.balance = BDER;
                        retorno.diferenciaAltura = false;
                    }
                    else
                    {
                        retorno.respuesta.balance = BAL;
                        retorno.respuesta.izqNodo.balance = BAL;
                    }
                }
                else
                {
                    retorno.respuesta = roteDerIzq( );
                    if( retorno.respuesta.balance == BDER )
                    {
                        retorno.respuesta.izqNodo.balance = BIZQ;
                    }
                    else
                    {
                        retorno.respuesta.izqNodo.balance = BAL;
                    }
                    if( retorno.respuesta.balance == BIZQ )
                    {
                        retorno.respuesta.derNodo.balance = BDER;
                    }
                    else
                    {
                        retorno.respuesta.derNodo.balance = BAL;
                    }
                    retorno.respuesta.balance = BAL;
                }
                break;
        }
    }

    /**
     * Se ha eliminado un elemento del sub�rbol derecho y por esta raz�n el sub�rbol izquierdo se encuentra desbalanceado. Esta rutina reestablece el balance perdido. Al
     * entrar, ret.difAltura == TRUE. <br>
     * <b>pre: </b> retorno!=null. <br>
     * <b>post: </b> Se restableci� el balance� perdido a causa de una eliminac�n en el sub�rbol derecho.
     * @param retorno Estructura que contiene el �rbol resultado y una variable que me permite determinar si el nodo est� desbalanceado
     */
    private void balanElimIzq( Retorno retorno )
    {
        switch( balance )
        {
            case BIZQ:
                if( izqNodo.balance != BDER )
                {
                    retorno.respuesta = roteDer( );
                    if( retorno.respuesta.balance == BAL )
                    {
                        retorno.respuesta.balance = BDER;
                        retorno.respuesta.derNodo.balance = BIZQ;
                        retorno.diferenciaAltura = false;
                    }
                    else
                    {
                        retorno.respuesta.balance = BAL;
                        retorno.respuesta.derNodo.balance = BAL;
                    }
                }
                else
                {
                    retorno.respuesta = roteIzqDer( );
                    if( retorno.respuesta.balance == BIZQ )
                    {
                        retorno.respuesta.derNodo.balance = BDER;
                    }
                    else
                    {
                        retorno.respuesta.derNodo.balance = BAL;
                    }
                    if( retorno.respuesta.balance == BDER )
                    {
                        retorno.respuesta.izqNodo.balance = BIZQ;
                    }
                    else
                    {
                        retorno.respuesta.izqNodo.balance = BAL;
                    }
                    retorno.respuesta.balance = BAL;
                }
                break;
            case BAL:
                balance = BIZQ;
                retorno.diferenciaAltura = false;
                retorno.respuesta = this;
                break;
            case BDER:
                balance = BAL;
                retorno.respuesta = this;
                break;
        }
    }

    /**
     * Retorna el nodo con el mayor elemento de un �rbol AVL. <br>
     * <b>post: </b> Se retorn� el nodo con el mayor elemento de un �rbol AVL.
     * @return Nodo Nodo con el mayor elemento de un �rbol AVL
     */
    private NodoRojoNegro<T> mayorElemento( )
    {
        return ( derNodo == null ) ? this : derNodo.mayorElemento( );
    }

    /**
     * Retorna el nodo con el menor elemento de un �rbol AVL.<br>
     * <b>post: </b> Se retorn� el nodo con el menor elemento de un �rbol AVL.
     * @return Nodo Nodo con el menor elemento de un �rbol AVL
     */
    private NodoRojoNegro<T> menorElemento( )
    {
        return ( izqNodo == null ) ? this : izqNodo.menorElemento( );
    }

    /**
     * Cambia el color del nodo. <br>
     * <b>pre: </b> c= ROJO o c= NEGRO. <br>
     * <b>post: </b> color= c.
     * @param c Nuevo color del nodo
     */
    public void cambiarColor( int c )
    {
        color = c;
    }

    /**
     * Retorna el color del nodo. <br>
     * <b>post: </b> Se retorn� el color del nodo.
     * @return El color del nodo
     */
    public int darColor( )
    {
        return color;
    }

    /**
     * Cambia el padre del nodo
     * @param padre Nuevo padre del nodo
     */
    public void cambiarPadreNodo( NodoRojoNegro<T> padre )
    {
        padreNodo = padre;
    }

    public NodoRojoNegro<T> darPadreNodo( )
    {
        return padreNodo;
    }

    /**
     * Realiza el cambio de color en los nodos cuando la inserci�n se realiz� por la izquierda. <br>
     * <b> pre: </b> La inserci�n se realiz� por la izquierda, a�n no se ha realizado el balanceo del �rbol. <br>
     * <b> post: </b> Se realiz� el cambio de color de los nodos de acuerdo al caso de inserci�n.
     */
    private void cambiarColorNodosInsercionIzquierda( Retorno retorno )
    {
        retorno.rotar = true;
        if( izqNodo.darHijoIzquierdo( ) != null && derNodo == null ) // Caso 1: Inserci�n en un 2-nodo quedando un nodo rojo con un hijo izquierdo rojo
        {
            cambiarColor( ROJO );
            izqNodo.cambiarColor( NEGRO );
            retorno.cambioColor = false;
        }
        else if( izqNodo.darHijoDerecho( ) != null && derNodo == null ) // Caso 2: Inserci�n en un 2 nodo quedando un nodo rojo con un hijo derecho rojo
        {
            cambiarColor( ROJO );
            izqNodo.darHijoDerecho( ).cambiarColor( NEGRO );
            retorno.cambioColor = false;
        }
        else if( ( izqNodo.darHijoIzquierdo( ) != null || izqNodo.darHijoDerecho( ) != null ) && derNodo != null && izqNodo.darColor( ) == ROJO && derNodo.darColor( ) == ROJO && balance == BAL && retorno.diferenciaAltura ) // Caso
        // 3:
        // Inserci�n
        // en
        // un 4
        // nodo
        // que
        // implica
        // su
        // divisi�n
        {
            izqNodo.cambiarColor( NEGRO );
            derNodo.cambiarColor( NEGRO );
            retorno.cambioColor = false;
        }
        else if( color == NEGRO && izqNodo.darColor( ) == NEGRO && izqNodo.darHijoIzquierdo( ) != null && izqNodo.darHijoDerecho( ) != null && retorno.diferenciaAltura ) // Caso
        // 4:
        // Inserci�n
        // en
        // un 4
        // nodo
        // que
        // tiene
        // padre
        // negro
        // y
        // hubo
        // aumento
        // de
        // altura
        {
            cambiarColor( ROJO );
            izqNodo.cambiarColor( ROJO );
            izqNodo.darHijoIzquierdo( ).cambiarColor( NEGRO );
            izqNodo.darHijoDerecho( ).cambiarColor( NEGRO );
            retorno.cambioColor = false;
        }
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && izqNodo != null && izqNodo.darColor( ) == NEGRO && izqNodo.darHijoDerecho( ) != null && izqNodo.darHijoIzquierdo( ) != null
                && ( izqNodo.darHijoIzquierdo( ).darHijoDerecho( ) != null || izqNodo.darHijoIzquierdo( ).darHijoIzquierdo( ) != null || izqNodo.darHijoDerecho( ).darHijoDerecho( ) != null || izqNodo.darHijoDerecho( ).darHijoIzquierdo( ) != null )
                && padreNodo.darHijoIzquierdo( ) == this ) // Caso 5: Inserci�n en un 4 nodo que tiene padre rojo. La inserci�n se realiz� sobre una rama externa
        {

            padreNodo.cambiarColor( ROJO );
            cambiarColor( NEGRO );
            izqNodo.cambiarColor( ROJO );
            izqNodo.darHijoDerecho( ).cambiarColor( NEGRO );
            izqNodo.darHijoIzquierdo( ).cambiarColor( NEGRO );
            retorno.rotar = false; // El balanceo debe realizarse un nivel m�s arriba
            retorno.cambioColor = false;
        }
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && derNodo != null && derNodo.darColor( ) == NEGRO && derNodo.darHijoDerecho( ) != null && derNodo.darHijoIzquierdo( ) != null
                && padreNodo.darHijoIzquierdo( ) == this ) // Caso 6: Inserci�n en un 4 nodo que tiene padre rojo. La inserci�n se realiz� sobre una rama interna. El nodo
        // debe ser un hijo izquierdo
        {
            padreNodo.cambiarColor( ROJO );
            derNodo.darHijoDerecho( ).cambiarColor( NEGRO );
            derNodo.darHijoIzquierdo( ).cambiarColor( NEGRO );
            retorno.rotar = false; // El balanceo debe realizarse un nivel m�s arriba
            retorno.cambioColor = false;
        }
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && izqNodo != null && izqNodo.darColor( ) == NEGRO && izqNodo.darHijoDerecho( ) != null && izqNodo.darHijoIzquierdo( ) != null
                && padreNodo.darHijoDerecho( ) == this ) // Caso 7: Inserci�n en un 4 nodo que tiene padre rojo. La inserci�n se realiz� en una rama interna. El nodo debe
        // ser un hijo derecho
        {
            padreNodo.cambiarColor( ROJO );
            izqNodo.darHijoDerecho( ).cambiarColor( NEGRO );
            izqNodo.darHijoIzquierdo( ).cambiarColor( NEGRO );
            retorno.rotar = false; // El balanceo debe realizarse un nivel m�s arriba
            retorno.cambioColor = false;
        }
    }

    /**
     * Realiza el cambio de color en los nodos cuando la inserci�n se realiz� por la derecha. <br>
     * <b> pre: </b> La inserci�n se realiz� por la derecha, a�n no se ha realizado el balanceo del �rbol. <br>
     * <b> post: </b> Se realiz� el cambio de color de los nodos de acuerdo al caso de inserci�n.
     */
    private void cambiarColorNodosInsercionDerecha( Retorno retorno )
    {
        retorno.rotar = true;

        if( derNodo.darHijoDerecho( ) != null && izqNodo == null ) // Caso 1: Inserci�n en un 2 nodo quedando un nodo rojo con un hijo derecho rojo
        {
            cambiarColor( ROJO );
            derNodo.cambiarColor( NEGRO ); // se puede quitar
            retorno.cambioColor = false;
        }
        else if( derNodo.darHijoIzquierdo( ) != null && izqNodo == null ) // Caso 2: Inserci�n en un 2 nodo quedando un nodo rojo con un hijo izquierdo rojo
        {
            cambiarColor( ROJO );
            derNodo.darHijoIzquierdo( ).cambiarColor( NEGRO ); // se puede quitar
            retorno.cambioColor = false;
        }
        else if( ( derNodo.darHijoDerecho( ) != null || derNodo.darHijoIzquierdo( ) != null ) && izqNodo != null && izqNodo.darColor( ) == ROJO && derNodo.darColor( ) == ROJO && balance == BAL && retorno.diferenciaAltura ) // Caso
        // 3:
        // Inserci�n
        // en
        // un 4
        // nodo
        // que
        // no
        // implica
        // balanceo
        {
            izqNodo.cambiarColor( NEGRO );
            derNodo.cambiarColor( NEGRO );
            retorno.cambioColor = false;
        }
        else if( color == NEGRO && derNodo.darColor( ) == NEGRO && derNodo.darHijoDerecho( ) != null && izqNodo.darHijoIzquierdo( ) != null && retorno.diferenciaAltura ) // Caso
        // 4:
        // Inserci�n
        // en
        // un 4
        // nodo
        // que
        // tiene
        // padre
        // negro
        // y
        // hubo
        // aumento
        // de
        // altura
        {
            cambiarColor( ROJO );
            derNodo.cambiarColor( ROJO );
            derNodo.darHijoIzquierdo( ).cambiarColor( NEGRO );
            derNodo.darHijoDerecho( ).cambiarColor( NEGRO );
            retorno.cambioColor = false;
        }
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && derNodo != null && derNodo.darColor( ) == NEGRO && derNodo.darHijoDerecho( ) != null && derNodo.darHijoIzquierdo( ) != null
                && ( derNodo.darHijoIzquierdo( ).darHijoDerecho( ) != null || derNodo.darHijoIzquierdo( ).darHijoIzquierdo( ) != null || derNodo.darHijoDerecho( ).darHijoDerecho( ) != null || derNodo.darHijoDerecho( ).darHijoIzquierdo( ) != null )
                && padreNodo.darHijoDerecho( ) == this ) // Caso 5: Inserci�n en un 4 nodo que tiene padre rojo rama externa
        {

            padreNodo.cambiarColor( ROJO );
            cambiarColor( NEGRO );
            derNodo.cambiarColor( ROJO );
            derNodo.darHijoDerecho( ).cambiarColor( NEGRO );
            derNodo.darHijoIzquierdo( ).cambiarColor( NEGRO );
            retorno.rotar = false;
            retorno.cambioColor = false;
        }
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && izqNodo != null && izqNodo.darColor( ) == NEGRO && izqNodo.darHijoDerecho( ) != null && izqNodo.darHijoIzquierdo( ) != null
                && padreNodo.darHijoDerecho( ) == this ) // Caso 6: Inserci�n en un 4 nodo que tiene padre rojo rama interna
        {
            padreNodo.cambiarColor( ROJO );
            izqNodo.darHijoDerecho( ).cambiarColor( NEGRO );
            izqNodo.darHijoIzquierdo( ).cambiarColor( NEGRO );
            retorno.rotar = false;
            retorno.cambioColor = false;
        }
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && derNodo != null && derNodo.darColor( ) == NEGRO && derNodo.darHijoDerecho( ) != null && derNodo.darHijoIzquierdo( ) != null
                && padreNodo.darHijoIzquierdo( ) == this ) // Caso 7: Inserci�n en un 4 nodo que tiene padre rojo rama interna
        {
            padreNodo.cambiarColor( ROJO );
            derNodo.darHijoDerecho( ).cambiarColor( NEGRO );
            derNodo.darHijoIzquierdo( ).cambiarColor( NEGRO );
            retorno.rotar = false;
            retorno.cambioColor = false;
        }
    }

    /**
     * Realiza el cambio de color en los nodos cuando la inserci�n se realiz� por la derecha. <br>
     * <b> pre: </b> La inserci�n se realiz� por la derecha, a�n no se ha realizado el balanceo del �rbol. <br>
     * <b> post: </b> Se realiz� el cambio de color de los nodos de acuerdo al caso de inserci�n.
     * @param Retorno Retorno utlizado para indicar si se debe o no realizar rotaci�n
     */
    private void cambiarColorNodosInsercionDer( Retorno retorno )
    {
        retorno.rotar = true;

        // Si el nodo izquierdo y derecho son rojos se colorean negros.
        if( derNodo != null && izqNodo != null && izqNodo.darColor( ) == ROJO && derNodo.darColor( ) == ROJO && balance == BAL && retorno.diferenciaAltura ) // Si el nodo
        // izquierdo y
        // derecho son
        // rojos se
        // colorean
        // negros.
        {
            izqNodo.cambiarColor( NEGRO );
            derNodo.cambiarColor( NEGRO );

            // Si el nodo no es la ra�z, pone su padre rojo
            if( padreNodo != null )
            {
                padreNodo.cambiarColor( ROJO );
            }
        }
        // La inserci�n se realiz� en un 4 nodo que tiene padre Negro. La inserci�n se realiz� sobre una rama externa y el t�o del nodo actual debe ser negro
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && derNodo != null && derNodo.darColor( ) == NEGRO && derNodo.darHijoDerecho( ) != null && derNodo.darHijoIzquierdo( ) != null
                && ( derNodo.darHijoIzquierdo( ).darHijoDerecho( ) != null || derNodo.darHijoIzquierdo( ).darHijoIzquierdo( ) != null || derNodo.darHijoDerecho( ).darHijoDerecho( ) != null || derNodo.darHijoDerecho( ).darHijoIzquierdo( ) != null )
                && padreNodo.darHijoDerecho( ) == this && padreNodo.darHijoIzquierdo( ) != null && padreNodo.darHijoIzquierdo( ).darColor( ) == NEGRO ) // Caso 5: Inserci�n en
        // un 4 nodo que tiene
        // padre rojo rama
        // externa
        {

            padreNodo.cambiarColor( ROJO );
            derNodo.cambiarColor( ROJO );
            retorno.rotar = false;

        }
        // La inserci�n se realiz� en un 4 nodo que tiene padre Negro. La inserci�n se realiz� sobre una rama externa y el t�o del nodo actual debe ser rojo
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && derNodo != null && derNodo.darColor( ) == NEGRO && derNodo.darHijoDerecho( ) != null && derNodo.darHijoIzquierdo( ) != null
                && ( derNodo.darHijoIzquierdo( ).darHijoDerecho( ) != null || derNodo.darHijoIzquierdo( ).darHijoIzquierdo( ) != null || derNodo.darHijoDerecho( ).darHijoDerecho( ) != null || derNodo.darHijoDerecho( ).darHijoIzquierdo( ) != null )
                && padreNodo.darHijoDerecho( ) == this && padreNodo.darHijoIzquierdo( ) != null && padreNodo.darHijoIzquierdo( ).darColor( ) == ROJO ) // Caso 5: Inserci�n en
        // un 4 nodo que tiene
        // padre rojo rama
        // externa
        {

            padreNodo.darHijoIzquierdo( ).cambiarColor( NEGRO );
        }
        // La inserci�n se realiz� en un 4 nodo que tiene padre Negro. La inserci�n se realiz� sobre una rama interna. El nodo debe ser un hijo derecho y el tio debe ser negro
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && izqNodo != null && izqNodo.darColor( ) == NEGRO && izqNodo.darHijoDerecho( ) != null && izqNodo.darHijoIzquierdo( ) != null
                && padreNodo.darHijoDerecho( ) == this && padreNodo.darHijoIzquierdo( ) != null && padreNodo.darHijoIzquierdo( ).darColor( ) == NEGRO ) // Caso 6: Inserci�n en
        // un 4 nodo que tiene
        // padre rojo rama
        // interna
        {
            padreNodo.cambiarColor( ROJO );
            retorno.rotar = false;
        }
        // La inserci�n se realiz� en un 4 nodo que tiene padre Negro. La inserci�n se realiz� sobre una rama interna. El nodo debe ser un hijo izquierdo y el tio debe ser
        // negro
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && derNodo != null && derNodo.darColor( ) == NEGRO && derNodo.darHijoDerecho( ) != null && derNodo.darHijoIzquierdo( ) != null
                && padreNodo.darHijoIzquierdo( ) == this && padreNodo.darHijoDerecho( ) != null && padreNodo.darHijoDerecho( ).darColor( ) == NEGRO ) // Caso 7: Inserci�n en
        // un 4 nodo que tiene
        // padre rojo rama
        // interna
        {
            padreNodo.cambiarColor( ROJO );
            retorno.rotar = false;
        }
        else if( ( ( color == NEGRO && padreNodo != null ) || ( padreNodo == null && color == ROJO ) ) && derNodo.darColor( ) == NEGRO && derNodo.darHijoDerecho( ) != null && izqNodo.darHijoIzquierdo( ) != null && retorno.diferenciaAltura ) // Caso
        // 4:
        // Inserci�n
        // en
        // un 4
        // nodo
        // que
        // tiene
        // padre
        // negro
        // y
        // hubo
        // aumento
        // de
        // altura
        {
            derNodo.cambiarColor( ROJO );

            if( padreNodo != null )
                retorno.rotar = false;

        }
        // La inserci�n se realiz� en un 2 nodo quedando un nodo rojo con un hijo derecho rojo
        else if( derNodo.darHijoDerecho( ) != null && izqNodo == null ) // Caso 1: Inserci�n en un 2 nodo quedando un nodo rojo con un hijo derecho rojo
        {
            cambiarColor( ROJO );
            derNodo.cambiarColor( NEGRO ); // se puede quitar
        }
    }

    /**
     * Realiza el cambio de color en los nodos cuando la inserci�n se realiz� por la izquierda. <br>
     * <b> pre: </b> La inserci�n se realiz� por la izquierda, a�n no se ha realizado el balanceo del �rbol. <br>
     * <b> post: </b> Se realiz� el cambio de color de los nodos de acuerdo al caso de inserci�n.
     * @param Retorno Retorno utlizado para indicar si se debe o no realizar rotaci�n
     */
    private void cambiarColorNodosInsercionIzq( Retorno retorno )
    {
        retorno.rotar = true;

        // Si el nodo izquierdo y derecho son rojos se colorean negros.
        if( derNodo != null && izqNodo != null && izqNodo.darColor( ) == ROJO && derNodo.darColor( ) == ROJO && balance == BAL && retorno.diferenciaAltura )
        {
            izqNodo.cambiarColor( NEGRO );
            derNodo.cambiarColor( NEGRO );

            // Si el nodo no es la ra�z, pone su padre rojo
            if( padreNodo != null )
            {
                padreNodo.cambiarColor( ROJO );
            }
        }
        // La inserci�n se realiz� en un 4 nodo que tiene padre Negro. La inserci�n se realiz� sobre una rama externa y el t�o del nodo actual debe ser negro
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && izqNodo != null && izqNodo.darColor( ) == NEGRO && izqNodo.darHijoDerecho( ) != null && izqNodo.darHijoIzquierdo( ) != null
                && ( izqNodo.darHijoIzquierdo( ).darHijoDerecho( ) != null || izqNodo.darHijoIzquierdo( ).darHijoIzquierdo( ) != null || izqNodo.darHijoDerecho( ).darHijoDerecho( ) != null || izqNodo.darHijoDerecho( ).darHijoIzquierdo( ) != null )
                && padreNodo.darHijoIzquierdo( ) == this && padreNodo.darHijoDerecho( ) != null && padreNodo.darHijoDerecho( ).darColor( ) == NEGRO )
        {

            padreNodo.cambiarColor( ROJO );
            izqNodo.cambiarColor( ROJO );
            retorno.rotar = false;
        }
        // La inserci�n se realiz� en un 4 nodo que tiene padre Negro. La inserci�n se realiz� sobre una rama externa y el t�o del nodo actual debe ser rojo
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && izqNodo != null && izqNodo.darColor( ) == NEGRO && izqNodo.darHijoDerecho( ) != null && izqNodo.darHijoIzquierdo( ) != null
                && ( izqNodo.darHijoIzquierdo( ).darHijoDerecho( ) != null || izqNodo.darHijoIzquierdo( ).darHijoIzquierdo( ) != null || izqNodo.darHijoDerecho( ).darHijoDerecho( ) != null || izqNodo.darHijoDerecho( ).darHijoIzquierdo( ) != null )
                && padreNodo.darHijoIzquierdo( ) == this && padreNodo.darHijoDerecho( ) != null && padreNodo.darHijoDerecho( ).darColor( ) == ROJO )
        {

            padreNodo.darHijoDerecho( ).cambiarColor( NEGRO );

        }
        // La inserci�n se realiz� en un 4 nodo que tiene padre Negro. La inserci�n se realiz� sobre una rama interna. El nodo debe ser un hijo izquierdo y el tio debe ser
        // negro
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && derNodo != null && derNodo.darColor( ) == NEGRO && derNodo.darHijoDerecho( ) != null && derNodo.darHijoIzquierdo( ) != null
                && padreNodo.darHijoIzquierdo( ) == this && padreNodo.darHijoIzquierdo( ) == this && padreNodo.darHijoDerecho( ) != null && padreNodo.darHijoDerecho( ).darColor( ) == NEGRO )
        {
            padreNodo.cambiarColor( ROJO );
            retorno.rotar = false;
        }
        // La inserci�n se realiz� en un 4 nodo que tiene padre Negro. La inserci�n se realiz� sobre una rama interna. El nodo debe ser un hijo derecho y el tio debe ser negro
        else if( padreNodo != null && padreNodo.darColor( ) == NEGRO && color == ROJO && izqNodo != null && izqNodo.darColor( ) == NEGRO && izqNodo.darHijoDerecho( ) != null && izqNodo.darHijoIzquierdo( ) != null
                && padreNodo.darHijoDerecho( ) == this && padreNodo.darHijoDerecho( ) == this && padreNodo.darHijoIzquierdo( ) != null && padreNodo.darHijoIzquierdo( ).darColor( ) == NEGRO )
        {
            padreNodo.cambiarColor( ROJO );
            retorno.rotar = false;
        }
        //
        else if( ( ( color == NEGRO && padreNodo != null ) || ( padreNodo == null && color == ROJO ) ) && izqNodo.darColor( ) == NEGRO && izqNodo.darHijoDerecho( ) != null && derNodo != null && derNodo.darHijoIzquierdo( ) != null
                && retorno.diferenciaAltura )
        {
            izqNodo.cambiarColor( ROJO );

            if( padreNodo != null )
                retorno.rotar = false;

        }
        // La inserci�n se realiz� en un 2 nodo quedando un nodo rojo con un hijo izquierdo rojo
        else if( izqNodo.darHijoIzquierdo( ) != null && derNodo == null )
        {
            cambiarColor( ROJO );
            izqNodo.cambiarColor( NEGRO ); // se puede quitar
        }
    }

    /**
     * Retorna el �rbol 2-4 equivalante al �rbol actual. <br>
     * <b>post: </b> Se retorn� el �rbol rojo negro equivalante al �rbol actual.
     * @return El �rbol rojo negro equivalante al �rbol actual
     */
    public Arbol2_4<T> darArbol2_4( )
    {
        Arbol2_4<T> arbol = new Arbol2_4<T>( );

        ColaEncadenada<NodoRojoNegro<T>> cola = new ColaEncadenada<NodoRojoNegro<T>>( );
        cola.insertar( this );
        while( cola.darLongitud( ) != 0 )
        {
            NodoRojoNegro<T> nodo = null;
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
                arbol.insertar( nodo.elem );
            }
            catch( uniandes.cupi2.collections.arbol2_4.ElementoExisteException e )
            {
                // Nunca deber�a aparecer esta excepci�n
            }

            // Agrega los dos sub�rboles (si no son vac�os) a la cola
            if( nodo.izqNodo != null )
            {
                cola.insertar( nodo.izqNodo );
            }
            if( nodo.derNodo != null )
            {
                cola.insertar( nodo.derNodo );
            }
        }

        return arbol;
    }
    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    /**
     * Estructura para retornar la ra�z del �rbol AVL resultado de un proceso de modificaci�n, con un indicador de si su altura ha sido modificada.
     */
    private class Retorno
    {
        // -----------------------------------------------------------------
        // Atributos
        // -----------------------------------------------------------------

        /**
         * Ra�z del �rbol de respuesta
         */
        private NodoRojoNegro<T> respuesta;

        /**
         * Indicador de cambio de altura del �rbol.
         */
        private boolean diferenciaAltura;

        /**
         * Indicador de si se debe realizar la rotaci�n o no
         */
        private boolean rotar;

        /**
         * Indicador de si se debe realizar el cambio de color o no
         */
        private boolean cambioColor;

        // -----------------------------------------------------------------
        // Constructores
        // -----------------------------------------------------------------

        /**
         * M�todo constructor de la clase Retorno<br>
         * <b>post: </b> Se construy� en objeto retorno con los valores especificados<br>
         * @param pRespuesta Ra�z del �rbol de respuesta
         * @param pDiferenciaAltura Indicador de cambio de altura del �rbol
         */
        private Retorno( NodoRojoNegro<T> pRespuesta, boolean pDiferenciaAltura )
        {
            respuesta = pRespuesta;
            diferenciaAltura = pDiferenciaAltura;
            rotar = true;
            cambioColor = true;
        }
    }
}
