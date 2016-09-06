package uniandes.cupi2.collections.colaEncadenada.test;

import uniandes.cupi2.collections.colaEncadenada.ColaEncadenada;
import uniandes.cupi2.collections.colaEncadenada.NodoCola;

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
     * Informa si la estructura de la cola está correcta, de acuerdo con la definición de un cola encadenada
     * @param cola Cola a la que se le va a verificar la estructura
     * @return True si la estructura de la cola es correcta o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private boolean verificarEstructura( ColaEncadenada cola )
    {
        NodoCola<T> primero = cola.darPrimero( );
        NodoCola<T> ultimo = cola.darUltimo( );
        int numElems = cola.darLongitud( );
        int elementos = 0;
        NodoCola<T> nodo = primero;
        NodoCola<T> anterior = null;
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
            estructuraCorrecta = estructuraCorrecta && primero == null && ultimo == null;
        }
        else
        {
            estructuraCorrecta = estructuraCorrecta && anterior.equals( ultimo ) && ultimo.darSiguiente( ) == null;
        }

        return estructuraCorrecta;
    }

    /**
     * Verifica que la estructura sea correcta
     * @return True si la estructura de la cola es correcta o false de lo contrario
     */
    public boolean verificarCola( ColaEncadenada cola )
    {
        return verificarEstructura( cola );
    }
}
