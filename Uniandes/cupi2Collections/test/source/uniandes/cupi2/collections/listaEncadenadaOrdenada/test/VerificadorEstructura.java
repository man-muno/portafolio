package uniandes.cupi2.collections.listaEncadenadaOrdenada.test;

import uniandes.cupi2.collections.listaEncadenadaOrdenada.ListaEncadenadaOrdenada;
import uniandes.cupi2.collections.listaEncadenadaOrdenada.NodoLista;

/**
 * Clase utilizada para verificar la estructura y orden de la lista encadenada ordenada
 * 
 */
public class VerificadorEstructura<T extends Comparable<? super T>>
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Verifica que la estructura de la lista sea correcta
     * @param La lista a la que se le va a verificar el orden
     * @return True si la lista es correcta o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private boolean verificarEstructura( ListaEncadenadaOrdenada lista )
    {
        int elementos = 0;
        NodoLista<T> primero = lista.darPrimero( );
        NodoLista<T> nodo = primero;
        NodoLista<T> anterior = null;
        int numElems = lista.darLongitud( );
        boolean estructuraCorrecta = false;

        while( nodo != null )
        {
            anterior = nodo;
            nodo = nodo.darSiguiente( );
            elementos++;
        }

        estructuraCorrecta = ( elementos == numElems );

        if( numElems == 0 )
        {
            estructuraCorrecta = estructuraCorrecta && primero == null;
        }
        else
        {
            estructuraCorrecta = estructuraCorrecta && anterior.darSiguiente( ) == null;
        }

        return estructuraCorrecta;
    }

    /**
     * Verifica que los elementos de la lista estén en el orden correcto
     * @param lista La lista a la que se le va a verificar el orden
     * @return True si el orden es correcto o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private boolean verificarOrden( ListaEncadenadaOrdenada<T> lista )
    {
        boolean ordenada = true;
        NodoLista<T> primero = lista.darPrimero( );
        NodoLista<T> nodo = primero;
        NodoLista<T> anterior = null;

        while( nodo != null && ordenada )
        {
            anterior = nodo;
            nodo = nodo.darSiguiente( );

            if( nodo != null )
            {
                if( anterior.darElemento( ).compareTo( nodo.darElemento( ) ) > 0 )
                {
                    ordenada = false;
                }

            }

        }

        return ordenada;
    }

    /**
     * Verifica que la estructura y orden de la lista ordenda sean correctos
     * @param lista La lista a la que se le va a verificar el orden y la estructura
     * @return True si la estructura y orden son correctos o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    public boolean verificarLista( ListaEncadenadaOrdenada lista )
    {
        return verificarEstructura( lista ) && verificarOrden( lista );
    }

}
