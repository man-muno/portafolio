package uniandes.cupi2.collections.avl.test;

import uniandes.cupi2.collections.avl.ArbolAVL;
import uniandes.cupi2.collections.avl.NodoAVL;

/**
 * Clase utilizada para verificar la estructura del �rbol AVL
 * 
 */
public class VerificadorEstructura<T extends Comparable<? super T>>
{

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Pruebas (auxiliar) Informa si los elementos de la estructura est�n ordenados, y no hay elementos repetidos, teniendo en cuenta que en el recorrido en inorden, ret.ant
     * es el �ltimo elemento visitado. ret.ant es null, cuando no ha visitado ning�n objeto
     * @param ret El �ltimo elemento visitado de la estructura
     * @param nodo El nodo desde el que se va a verificar el orden
     * @return True si el orden es correcto en el �rbol cuya ra�z es el nodo especificado, o false de lo contrario
     */
    private Retorno2 verificarOrden( Retorno2 ret, NodoAVL<T> nodo )
    {
        if( nodo != null )
        {
            NodoAVL<T> izqNodo = nodo.darHijoIzquierdo( );
            NodoAVL<T> derNodo = nodo.darHijoDerecho( );
            T elem = nodo.darRaiz( );
            // Verifica que el primer hijo est� ordenado
            ret = ( izqNodo == null ) ? ret : verificarOrden( ret, izqNodo );
            if( ret.todoOK )
            {
                // Compara la ra�z del �rbol con el �ltimo elemento recorrido
                // del hijo izquierdo
                if( ret.ant != null && elem.compareTo( ret.ant ) <= 0 )
                    ret.todoOK = false;
                else
                {
                    // Actualiza el �ltimo elemento recorrido
                    ret.ant = elem;
                    // Verifica que el Nodo derecho este ordenado
                    ret = ( derNodo == null ) ? ret : verificarOrden( ret, derNodo );
                    if( ret.todoOK && derNodo != null )
                    {
                        // Compara el elemento con el �ltimo elemento
                        // recorrido de la ra�z derecha
                        if( elem.compareTo( ret.ant ) >= 0 )
                            ret.todoOK = false;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Pruebas (auxiliar): Informa si la estructura del �rbol es correcta, de acuerdo con la definici�n de un �rbol avl
     * @param nodo Nodo del �rbol desde el que se va a verificar la estructura
     * @return True si la estructura es correcta en el �rbol cuya ra�z es el nodo especificado, o false de lo contrario
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
     * Verifica que tanto la estructura como el orden en el �rbol sean correctos
     * @return True si la estructura y el orden del �rbol es correcta o false de lo contrario
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
     * Clase interna: Estructura para retornar la informaci�n del recorrido del �rbol, mientras establece si est� ordenado
     */
    private class Retorno2
    {
        boolean todoOK; // Hasta el momento el �rbol es ordenado
        T ant; // Ultimo elemento recorrido

        // Constructora
        Retorno2( )
        {
            todoOK = true;
            ant = null;
        }
    }

}
