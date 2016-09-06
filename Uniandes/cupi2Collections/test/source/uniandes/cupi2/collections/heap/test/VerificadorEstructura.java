package uniandes.cupi2.collections.heap.test;

import uniandes.cupi2.collections.heap.Elemento;
import uniandes.cupi2.collections.heap.Heap;

/**
 * Clase utilizada para verificar la estructura del árbol binario ordenado
 * 
 */
public class VerificadorEstructura<T extends Comparable<? super T>>
{

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    /**
     * Pruebas (auxiliar) Informa si los elementos de la estructura están ordenados, y no hay elementos repetidos, teniendo en cuenta que en el recorrido en inorden, ret.ant
     * es el último elemento visitado. ret.ant es null, cuando no ha visitado ningún objeto   
     * @param heap El heap al que se le va a verificar el orden
     * @return True si el orden es correcto en el heap o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    private boolean verificarOrden( Heap<T> heap)
    {
        int peso= heap.darPeso(); 
        Elemento[] elementos= heap.darElementos();
        boolean ordenado= true; 
        //Verificar que todos los hijos sean mayores o iguales al padre
        int padre= (peso/2)-1; 
        while(peso>0 && ordenado && padre>=0)
        {
            //Verificar si el hijo es mayor que el padre
            if(elementos[peso-1].compareTo(elementos[padre])<=0)
            {
                ordenado= false;
            }
            
            peso--; 
            padre= (peso/2)-1;   
        }         
        return ordenado;
    }

    /**
     * Verifica que tanto la estructura como el orden en el árbol sean correctos
     * @return True si el orden del árbol es correcto o false de lo contrario
     */
    @SuppressWarnings("unchecked")
    public boolean verificarHeap( Heap<T> heap )
    {
        return verificarOrden( heap );                 
    }
}
