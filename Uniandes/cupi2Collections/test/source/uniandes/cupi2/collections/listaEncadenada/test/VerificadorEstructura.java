package uniandes.cupi2.collections.listaEncadenada.test;

import uniandes.cupi2.collections.listaEncadenada.ListaEncadenada;
import uniandes.cupi2.collections.listaEncadenada.NodoLista;

/**
 * Clase utilizada para verificar la estructura de la lista encadenada
 * 
 */
public class VerificadorEstructura<T extends Comparable<? super T>>
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Pruebas (auxiliar): Informa si la estructura de la cola está correcta, de acuerdo con la definición de un cola encadenada
     * @param lista la lista a la que se le va a verificar la estructura
     * @return True si la estructura de la lista es correcta o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private boolean verificarEstructura( ListaEncadenada<T> lista )
    {

        int elementos = 0;
        int numElems = lista.darLongitud( );
        NodoLista<T> primero = lista.darPrimero( );
        NodoLista<T> ultimo = lista.darUltimo( );
        NodoLista<T> nodo = primero;
        NodoLista<T> anterior = null;
        boolean estructuraCorrecta = false;

        while( nodo != null )
        {
            anterior = nodo;
            nodo = nodo.darSiguiente( );

            if( nodo != null )
            {
                estructuraCorrecta = estructuraCorrecta && nodo.darAnterior( ).equals( anterior );
            }

            elementos++;
        }

        estructuraCorrecta = ( elementos == numElems );

        if( numElems == 0 )
        {
            estructuraCorrecta = estructuraCorrecta && primero == null && ultimo == null;
        }
        else
        {
            estructuraCorrecta = estructuraCorrecta && anterior.equals( ultimo ) && ultimo.darSiguiente( ) == null && primero.darAnterior( ) == null;
        }

        return estructuraCorrecta;
    }

    /**
     * Verifica que la estructura sea correcta
     * @return True si la estructura es correcta o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    public boolean verificarLista( ListaEncadenada lista )
    {
        return verificarEstructura( lista );
    }

}
