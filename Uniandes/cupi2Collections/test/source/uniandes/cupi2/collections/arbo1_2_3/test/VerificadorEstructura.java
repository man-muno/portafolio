package uniandes.cupi2.collections.arbo1_2_3.test;

import uniandes.cupi2.collections.arbol.arbol1_2_3.Arbol1_2_3;
import uniandes.cupi2.collections.arbol.arbol1_2_3.Nodo1_2_3;

/**
 * Clase utilizada para verificar la estructura del árbol 1_2_3
 * 
 */

public class VerificadorEstructura<T extends Comparable>
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Informa si los elementos de la estructura están ordenados, y no hay elementos repetidos, teniendo en cuenta que en el recorrido en inorden, ret.ant es el último
     * elemento visitado. ret.ant es null, cuando no ha visitado ningún objeto
     * 
     * @param ret El último elemento visitado en la estructura
     * @param nodo El nodo a partir del cual se va a verificar la estructura
     * @return El objeto con la información de si el orden es correcto y el último elemento visitado
     */
    @SuppressWarnings("unchecked")
    private Retorno verificarOrden( Retorno ret, Nodo1_2_3<T> nodo )
    {

        // Verifica que el primer hijo esté ordenado
        if( nodo != null )
        {
            Nodo1_2_3<T> h1 = nodo.darHijoIzq( );
            Nodo1_2_3<T> h2 = nodo.darHijoCent( );
            Nodo1_2_3<T> h3 = nodo.darHijoDer( );
            T r1 = ( T )nodo.darRaizIzq( );
            T r2 = ( T )nodo.darRaizDer( );
            ret = ( h1 == null ) ? ret : verificarOrden( ret, h1 );
            if( ret.todoOK )
            {
                // Compara la primera raíz del árbol con el último elemento
                // recorrido
                // del primer hijo
                if( ret.ant != null && r1.compareTo( ret.ant ) <= 0 )
                    ret.todoOK = false;
                else
                {
                    // Actualiza el último elemento recorrido
                    ret.ant = r1;
                    // Verifica que el segundo hijo esté ordenado
                    ret = ( h2 == null ) ? ret : verificarOrden( ret, h2 );
                    if( ret.todoOK && r2 != null )
                    {
                        // Compara la segunda raíz del árbol con el último
                        // elemento
                        // recorrido del segundo hijo
                        if( r2.compareTo( ret.ant ) <= 0 )
                            ret.todoOK = false;
                        else
                        {
                            // Actualiza el último elemento recorrido
                            ret.ant = r2;
                            // Verifica que el tercer hijo esté ordenado
                            ret = ( h3 == null ) ? ret : verificarOrden( ret, h3 );
                        }
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Si la estructura del árbol es correcta, de acuerdo con la definición de un árbol 1-2-3 se retorna true o false de lo contrario.
     * @param nodo Nodo a partir del cual se va a realizar la verificación
     * @return True si la estructura es correcta o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private boolean verificarEstructura( Nodo1_2_3<T> nodo )
    {

        if( nodo != null )
        {
            Nodo1_2_3<T> h1 = nodo.darHijoIzq( );
            Nodo1_2_3<T> h2 = nodo.darHijoCent( );
            Nodo1_2_3<T> h3 = nodo.darHijoDer( );
            T r1 = ( T )nodo.darRaizIzq( );
            T r2 = ( T )nodo.darRaizDer( );

            if( r2 != null && r1.compareTo( r2 ) >= 0 || ( r2 == null && ( h2 != null || h3 != null ) ) )
                return false;
            else if( h1 == null )
                return true;
            else if( r2 == null )
                return verificarEstructura( h1 );
            else if( r2 != null )
                return verificarEstructura( h1 ) && verificarEstructura( h2 ) && verificarEstructura( h3 );
        }

        return true;
    }

    /**
     * Verifica que tanto la estructura como el orden en el árbol sean correctos
     * 
     * @param arbol Arbol a ser verificado
     * @return True si la estructura y orden del árbol es correcta o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    public boolean verificarArbol( Arbol1_2_3 arbol )
    {
        Retorno ret = verificarOrden( new Retorno( ), arbol.darRaiz( ) );
        return ret.todoOK && verificarEstructura( arbol.darRaiz( ) );
    }

    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    /**
     * Clase interna: Estructura para retornar la información del recorrido del árbol, mientras establece si está ordenado
     */
    private class Retorno
    {
        boolean todoOK; // Hasta el momento el árbol es ordenado

        T ant; // Ultimo elemento recorrido

        // Constructora
        Retorno( )
        {
            todoOK = true;
            ant = null;
        }
    }
}
