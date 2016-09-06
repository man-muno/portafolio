package uniandes.cupi2.collections.iterador.test;

import uniandes.cupi2.collections.iterador.IteradorSimple;

/**
 * Clase utilizada para verificar la estructura del iterador simple
 */
public class VerificadorEstructura<T extends Comparable<? super T>>
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Verifica que la estructura sea correcta
     * @param iterador Iterador al que se le va a verificar la estructura
     * @return true si la estructura es correcta o false de lo contrario
     */
    public boolean verificarIterador( IteradorSimple<T> iterador )
    {
        return verificarEstructura( iterador );
    }

    /**
     * Verifica que la estructura del iterador sea correcta
     * @param iterador Iterador al que se le va a verificar la estructura
     * @return True si la estructura es correcta o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private boolean verificarEstructura( IteradorSimple<T> iterador )
    {
        int posActual = iterador.darPosActual( );
        int sigPosLibre = iterador.darSigPosLibre( );
        int tam = iterador.darLongitud( );

        boolean correcta = true;

        if( posActual >= sigPosLibre )
        {
            correcta = false;
        }
        else if( sigPosLibre > tam )
        {
            correcta = false;
        }
        return correcta;
    }

}
