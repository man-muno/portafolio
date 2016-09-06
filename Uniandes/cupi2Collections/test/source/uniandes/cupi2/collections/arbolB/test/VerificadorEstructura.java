package uniandes.cupi2.collections.arbolB.test;

import uniandes.cupi2.collections.arbol2_4.Arbol2_4;
import uniandes.cupi2.collections.arbol2_4.Nodo2_4;
import uniandes.cupi2.collections.arbolB.NodoArbolB;


/**
 * Clase utilizada para verificar la estructura del �rbol 2_4
 * 
 */

public class VerificadorEstructura<T extends Comparable<? super T>>
{

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Informa si los elementos de la estructura est�n ordenados, y no hay elementos repetidos, teniendo en cuenta que en el recorrido en inorden, ret.ant es el �ltimo
     * elemento visitado. ret.ant es null, cuando no ha visitado ning�n objeto
     * 
     * @param ret El �ltimo elemento visitado en la estructura
     * @param nodo El nodo a partir del cual se va a verificar la estructura
     * @return El objeto con la informaci�n de si el orden es correcto y el �ltimo elemento visitado
     */
    /*@SuppressWarnings("unchecked")
    private Retorno2 verificarOrden( Retorno2 ret, NodoArbolB<T> nodo )
    {

        // Verifica que el primer hijo est� ordenado
        if( nodo != null )
        {
            Nodo2_4<T> h1 = nodo.darHijo1( );
            Nodo2_4<T> h2 = nodo.darHijo2( );
            Nodo2_4<T> h3 = nodo.darHijo3( );
            Nodo2_4<T> h4 = nodo.darHijo4( );
            T r1 = ( T )nodo.darRaiz1( );
            T r2 = ( T )nodo.darRaiz2( );
            T r3 = ( T )nodo.darRaiz3( );
            ret = ( h1 == null ) ? ret : verificarOrden( ret, h1 );
            if( ret.todoOK )
            {
                // Compara la primera ra�z del �rbol con el �ltimo elemento
                // recorrido
                // del primer hijo
                if( ret.ant != null && r1.compareTo( ret.ant ) <= 0 )
                    ret.todoOK = false;
                else
                {
                    // Actualiza el �ltimo elemento recorrido
                    ret.ant = r1;
                    // Verifica que el segundo hijo est� ordenado
                    ret = ( h2 == null ) ? ret : verificarOrden( ret, h2 );
                    if( ret.todoOK && r2 != null )
                    {
                        // Compara la segunda ra�z del �rbol con el �ltimo
                        // elemento
                        // recorrido del segundo hijo
                        if( r2.compareTo( ret.ant ) <= 0 )
                            ret.todoOK = false;
                        else
                        {
                            // Actualiza el �ltimo elemento recorrido
                            ret.ant = r2;
                            // Verifica que el tercer hijo est� ordenado
                            ret = ( h3 == null ) ? ret : verificarOrden( ret, h3 );                            
                            if( ret.todoOK && r3 != null )
                            {
                                // Compara la tercera ra�z del �rbol con el �ltimo
                                // elemento
                                // recorrido del tercer hijo
                                if( r3.compareTo( ret.ant ) <= 0 )
                                    ret.todoOK = false;
                                else
                                {
                                    // Actualiza el �ltimo elemento recorrido
                                    ret.ant = r3;
                                    // Verifica que el tercer hijo est� ordenado
                                    ret = ( h4 == null ) ? ret : verificarOrden( ret, h4 );
                                }
                            }
                        }                                                
                    }
                }
            }
        }
        return ret;
    }

    *//**
     * Si la estructura del �rbol es correcta, de acuerdo con la definici�n de un �rbol 2-4
     * se retorna true o false de lo contrario. 
     * @param nodo Nodo a partir del cual se va a realizar la verificaci�n
     * @return True si la estructura es correcta o false de lo contrario
     *//*
    @SuppressWarnings("unchecked")
    private boolean verificarEstructura( NodoArbolB<T> nodo )
    {

        if( nodo != null )
        {
            Nodo2_4<T> h1 = nodo.darHijo1( );
            Nodo2_4<T> h2 = nodo.darHijo2( );
            Nodo2_4<T> h3 = nodo.darHijo3( );
            Nodo2_4<T> h4 = nodo.darHijo4( );
            T r1 = ( T )nodo.darRaiz1( );
            T r2 = ( T )nodo.darRaiz2( );
            T r3 = ( T )nodo.darRaiz3( );

            int alt1 = ( h1 == null ) ? 0 : h1.darAltura( );
            int alt2 = ( h2 == null ) ? 0 : h2.darAltura( );
            int alt3 = ( h3 == null ) ? 0 : h3.darAltura( );
            int alt4 = ( h4 == null ) ? 0 : h4.darAltura( );
            if( alt1 != alt2 || ( r2 != null && r1.compareTo( r2 ) >= 0 ) || ( r2 != null && alt2 != alt3 ) || (r3!=null && (r1.compareTo(r3)>=0 || r2.compareTo(r3)>=0)) || (r3!=null && alt3!=alt4) )
                return false;
            else if( h1 == null )
                return true;
            else if( r2 == null )
                return verificarEstructura( h1 ) && verificarEstructura( h2 );
            else if(r3== null)
                return verificarEstructura( h1 ) && verificarEstructura( h2 ) && verificarEstructura( h3 );
            else
            	return verificarEstructura( h1 ) && verificarEstructura( h2 ) && verificarEstructura( h3 ) && verificarEstructura(h4);
        }

        return true;
    }

    *//**
     * Verifica que tanto la estructura como el orden en el �rbol sean correctos
     * 
     * @param arbol Arbol a ser verificado
     * @return True si la estructura y orden del �rbol es correcta o false de lo contrario
     *//*
    @SuppressWarnings("unchecked")
    public boolean verificarArbol( Arbol2_4 arbol )
    {
        Retorno2 ret = verificarOrden( new Retorno2( ), arbol.darRaiz( ) );
        return ret.todoOK && verificarEstructura( arbol.darRaiz( ) );
    }

    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    *//**
     * Clase interna: Estructura para retornar la informaci�n del recorrido del �rbol, mientras establece si est� ordenado
     *//*
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
    }*/
}
