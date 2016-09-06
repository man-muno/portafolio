package uniandes.cupi2.collections.pilaEncadenada.test;

import uniandes.cupi2.collections.pilaEncadenada.NodoPila;
import uniandes.cupi2.collections.pilaEncadenada.PilaEncadenada;

/**
 * Clase utilizada para verificar la estructura de la pila encadenada
 * 
 */
public class VerificadorEstructura<T extends Comparable<? super T>>
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Verifica que la estructura de la pila sea correcta
     * @param pila Pila a la que se va a verificar la estructura
     * @return True si la estructura es correcta o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private boolean verificarEstructura( PilaEncadenada<T> pila )
    {
        int elementos = 0;
        NodoPila<T> primero = pila.darPrimero( );
        NodoPila<T> anterior = null;
        NodoPila<T> nodo = primero;
        int numElems = pila.darLongitud( );

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
     * Verifica que la estructura de la pila encadenada sea correcta
     * @param pila Pila a la que se va a verificar la estructura
     * @return True si la estructura de la pila es correcta o false de lo contrario
     */
    public boolean verificarPila( PilaEncadenada<T> pila )
    {
        return verificarEstructura( pila );
    }

}
