
package uniandes.cupi2.collections.iterador.test;

import uniandes.cupi2.collections.iterador.IteradorFlexible;
import uniandes.cupi2.collections.listaEncadenada.NodoLista;

/**
 * Clase utilizada para verificar la estructura del iterador flexible
 */
public class VerificadorEstructuraFlexible<T extends Comparable<? super T>>
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Verifica que la estructura sea correcta
     * @param iterador Iterador al que se le va a verificar la estructura
     * @return true si la estructura es correcta o false de lo contrario
     */
    public boolean verificarIterador( IteradorFlexible<T> iterador )
    {
        return verificarEstructura( iterador );
    }

    /**
     * Verifica que la estructura del iterador sea correcta
     * @param iterador Iterador al que se le va a verificar la estructura
     * @return True si la estructura es correcta o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private boolean verificarEstructura( IteradorFlexible<T> iterador )
    {
        //Verificar la estructura del iterador
        int posActual = iterador.darPosActual( );
        int longitud = iterador.darLongitud( );
        int tam = iterador.darLongitud( );

        boolean correcta = true;

        if( posActual >= longitud )
        {
            correcta = false;
        }
        else if( longitud > tam )
        {
            correcta = false;
        }
        
        
        //Verificación de la estructura de la lista
        int elementos = 0;        
        NodoLista<T> primero = iterador.darPrimero( );
        NodoLista<T> ultimo = iterador.darUltimo( );
        NodoLista<T> nodo = primero;
        NodoLista<T> anterior = null;        

        while( nodo != null &&  correcta )
        {
            anterior = nodo;
            nodo = nodo.darSiguiente( );

            if( nodo != null )
            {
                correcta = correcta && nodo.darAnterior( ).equals( anterior );
            }

            elementos++;
        }

        correcta = ( elementos == longitud );

        if( longitud == 0 )
        {
            correcta = correcta && primero == null && ultimo == null;
        }
        else
        {
            correcta = correcta && anterior.equals( ultimo ) && ultimo.darSiguiente( ) == null && primero.darAnterior( ) == null;
        }

        return correcta;
    }

}
