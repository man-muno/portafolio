package uniandes.cupi2.collections.avl.test;

import uniandes.cupi2.collections.avl.ArbolAVL;
import uniandes.cupi2.collections.avl.NodoAVL;

/**
 * Clase utilizada para verificar la estructura del árbol AVL
 * 
 */
public class VerificadorEstructura<T extends Comparable<? super T>>
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Pruebas (auxiliar) Informa si los elementos de la estructura están ordenados, y no hay elementos repetidos, teniendo en cuenta que en el recorrido en inorden, ret.ant
     * es el último elemento visitado. ret.ant es null, cuando no ha visitado ningún objeto
     * @param ret El último elemento visitado de la estructura
     * @param nodo El nodo desde el que se va a verificar el orden
     * @return True si el orden es correcto en el árbol cuya raíz es el nodo especificado, o false de lo contrario
     */
    private Retorno2 verificarOrden( Retorno2 ret, NodoAVL<T> nodo )
    {
        if( nodo != null )
        {
            NodoAVL<T> izqNodo = nodo.darHijoIzquierdo( );
            NodoAVL<T> derNodo = nodo.darHijoDerecho( );
            T elem = nodo.darRaiz( );
            // Verifica que el primer hijo esté ordenado
            ret = ( izqNodo == null ) ? ret : verificarOrden( ret, izqNodo );
            if( ret.todoOK )
            {
                // Compara la raíz del árbol con el último elemento recorrido
                // del hijo izquierdo
                if( ret.ant != null && elem.compareTo( ret.ant ) <= 0 )
                    ret.todoOK = false;
                else
                {
                    // Actualiza el último elemento recorrido
                    ret.ant = elem;
                    // Verifica que el Nodo derecho este ordenado
                    ret = ( derNodo == null ) ? ret : verificarOrden( ret, derNodo );
                    if( ret.todoOK && derNodo != null )
                    {
                        // Compara el elemento con el último elemento
                        // recorrido de la raíz derecha
                        if( elem.compareTo( ret.ant ) >= 0 )
                            ret.todoOK = false;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Pruebas (auxiliar): Informa si la estructura del árbol es correcta, de acuerdo con la definición de un árbol avl
     * @param nodo Nodo del árbol desde el que se va a verificar la estructura
     * @return True si la estructura es correcta en el árbol cuya raíz es el nodo especificado, o false de lo contrario
     */
    private boolean verificarEstructura( NodoAVL<T> nodo )
    {
        if( nodo != null )
        {
            NodoAVL<T> izqNodo = nodo.darHijoIzquierdo( );
            NodoAVL<T> derNodo = nodo.darHijoDerecho( );
            int altIzq = ( izqNodo == null ) ? 0 : izqNodo.darAltura( );
            int altDer = ( derNodo == null ) ? 0 : derNodo.darAltura( );
            if( Math.abs( altIzq - altDer ) > 1 )
                return false;
            else if( izqNodo == null || derNodo == null )
                return true;
            else
                return verificarEstructura( izqNodo ) && verificarEstructura( derNodo );
        }

        return true;
    }

    /**
     * Verifica que tanto la estructura como el orden en el árbol sean correctos
     * @return True si la estructura y el orden del árbol es correcta o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    public boolean verificarArbol( ArbolAVL arbol )
    {
        Retorno2 ret = verificarOrden( new Retorno2( ), arbol.darRaiz( ) );
        return ret.todoOK && verificarEstructura( arbol.darRaiz( ) );
    }

    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    /**
     * Clase interna: Estructura para retornar la información del recorrido del árbol, mientras establece si está ordenado
     */
    private class Retorno2
    {
        boolean todoOK; // Hasta el momento el árbol es ordenado
        T ant; // Ultimo elemento recorrido

        // Constructora
        Retorno2( )
        {
            todoOK = true;
            ant = null;
        }
    }

}
