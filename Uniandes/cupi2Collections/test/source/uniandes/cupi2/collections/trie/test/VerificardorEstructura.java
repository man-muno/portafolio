package uniandes.cupi2.collections.trie.test;

import uniandes.cupi2.collections.trie.NodoTrie;
import uniandes.cupi2.collections.trie.Trie;

/**
 * Clase utilizada para verificar la estructura del trie
 * 
 */
public class VerificardorEstructura <T>
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Verifica que las letras en el trie se encuentren organizadas en orden ascendente.
     * @param nodo Nodo con el que se va a realizar la verificación
     * @return True si el orden es correcto o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private boolean verificarOrdenLetras( NodoTrie<T> nodo )
    {
        boolean correcto = true;

        NodoTrie<T> izqNodo= nodo.darHijoIzquierdo();
        NodoTrie<T> hermanoDerecho; 
        
        if(izqNodo!=null)
        {
            hermanoDerecho= izqNodo.darHermanoDerecho(); 
            
            if(hermanoDerecho!=null)
            {
                //Verificar que el orden sea correcto
                char anterior= izqNodo.darCaracter(); 
                char actual= hermanoDerecho.darCaracter();
                
                //correcto= correcto && verificarOrdenLetras(izqNodo); 
                
                while(hermanoDerecho!=null && correcto)
                {
                    if(anterior>=actual)
                    {
                        correcto= false; 
                    }                    
                    
                    izqNodo= hermanoDerecho; 
                    hermanoDerecho= hermanoDerecho.darHermanoDerecho();
                    anterior= izqNodo.darCaracter();
                    
                    if(hermanoDerecho!=null)
                        actual= hermanoDerecho.darCaracter();
                    //correcto= correcto && verificarOrdenLetras(izqNodo);
                }
                
                if(correcto && nodo.darHermanoDerecho()!=null)
                    correcto= correcto && verificarOrdenLetras(nodo.darHermanoDerecho());
            }
            
            correcto= correcto && verificarOrdenLetras(nodo.darHijoIzquierdo());
        }

        return correcto;
    }
    
    
    /**
     * Verifica que los nodos cuyo caracter es '*' sean hojas y que no sean el hermano
     * izquierdo de ningún nodo
     * @param nodo Nodo con el que se va a realizar la verificación
     * @return True si la estructura es correcta o false de lo contrario
     *//*
    @SuppressWarnings("unchecked")
    private boolean verificarEstructura( NodoTrie<T> nodo )
    {
        boolean correcto = true;

        NodoTrie<T> nodoActual= nodo.darHijoIzquierdo();        
        NodoTrie<T> nodoAnterior= null;
        
        if(nodoActual!=null)
        {                                     
            //Verificar que la estructura sea correcta
            char caracter;                 
            
            //correcto= correcto && verificarOrdenLetras(izqNodo); 
            
            while(nodoActual!=null && correcto)
            {
                caracter= nodoActual.darCaracter();
                if(caracter=='*' && nodoActual.darHijoIzquierdo()!=null) //Verificar que sea hoja
                {
                    correcto= false; 
                }
                else if(caracter=='*' && nodoAnterior!=null) //Verificar que no tenga hermano izquierdo
                {
                    correcto= false;
                }
                
                nodoAnterior= nodoActual; 
                nodoActual= nodoActual.darHermanoDerecho();
                                
            }        
            
            if(nodo.darHermanoDerecho()!=null && correcto)            
                correcto= correcto && verificarEstructura(nodo.darHermanoDerecho());            
            
            if(correcto)
                correcto= correcto && verificarEstructura(nodo.darHijoIzquierdo());                                    
        }

        return correcto;
    }*/

    /**
     * Verifica que la estructura del trie sea correcta
     * @param trie Trie al que se le va a verificar la estructura
     * @return True si la estructura del truie es correcto o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    public boolean verificarTrie( Trie<T> trie )
    {
        return verificarOrdenLetras( trie.darRaiz() );
    }
}
