package uniandes.cupi2.collections.arbolBinarioOrdenado.test;

import uniandes.cupi2.collections.arbolBinarioOrdenado.ArbolBinarioOrdenado;
import uniandes.cupi2.collections.arbolBinarioOrdenado.NodoArbolBinarioOrdenado;

/**
 * Clase utilizada para verificar la estructura del árbol binario ordenado
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
     * @param ret El último elemento visitado del árbol
     * @param nodo Nodo desde el que se va a verificar el orden
     * @return True si el orden es correcto en el árbol del que el nodo especificado es raíz o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private Retorno2 verificarOrden( Retorno2 ret, NodoArbolBinarioOrdenado<T> nodo )
    {
        // Verifica que el primer hijo esté ordenado
        if( nodo != null )
        {
            NodoArbolBinarioOrdenado izqNodo = nodo.darHijoIzquierdo( );
            NodoArbolBinarioOrdenado derNodo = nodo.darHijoDerecho( );
            ret = ( izqNodo == null ) ? ret : verificarOrden( ret, izqNodo );
            T elem = nodo.darRaiz( );
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
     * Verifica que tanto la estructura como el orden en el árbol sean correctos
     * @return True si el orden del árbol es correcto o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    public boolean verificarArbol( ArbolBinarioOrdenado arbol )
    {
        Retorno2 ret = verificarOrden( new Retorno2( ), arbol.darRaiz( ) );
        return ret.todoOK;
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
