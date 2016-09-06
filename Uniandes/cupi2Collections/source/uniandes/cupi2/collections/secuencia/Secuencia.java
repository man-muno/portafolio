/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Secuencia.java,v 1.5 2006/06/15 16:31:37 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Daniel Romero- Mayo 6 de 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.secuencia;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;

/**
 * Representa una secuencia modelada con un arreglo. Sirve principalmente para mostrar la utilización de diferentes
 * algoritmos de ordenamiento y búsqueda.
 * @param <T> Tipo de datos a almacenar en la secuencia. Los objetos de tipo T deben implementar la interface Comparable
 */
public class Secuencia<T extends Comparable<? super T>> //extends Elementos
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Tamaño inicial del arreglo
     */
    private final static int INIT = 20;
   
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Los elementos de la secuencia
     */
    private Elemento<T>[] elems;
    
     
     
    //Elementos elementos; 
    /**
     * Número de elementos actualmente en la lista
     */
    private int numElems;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la secuencia. <br>
     * <b>post: </b> Se construyó una secuencia con capacidad para 20 elementos.
     */
    public Secuencia( )
    {
        this( INIT );
    }

    /**
     * Constructor de la secuencia con la capacidad inicial. <br>
     * <b>pre: </b> capacidad!=null, capacidad>0. <br>
     * <b>post: </b> Se construyó la secuencia con la capacidad inicial especificada.
     * @param capacidad Capacidad para la secuencia
     */
    @SuppressWarnings("unchecked")
    public Secuencia( int capacidad )
    {                
        elems= new Elemento[capacidad];                  
        numElems = 0;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve el elemento en la posición especificada. <br>
     * <b>post</b>: Se retornó el elemento en la posición especificada.
     * @param pos Posición del elemento
     * @return Elemento en la posición especificada
     */
    @SuppressWarnings("unchecked")
    public T darElemento( int pos )
    {
        if( pos < 0 || pos > numElems )
            throw new IndiceFueraDeRango( pos );
        return elems[ pos ].darElemento();
    }

    /**
     * Agrega el elemento al final de la secuencia. <br>
     * <b>pre: </b> elem!= null. <br>
     * <b>post: </b> Se agregó el elemento especificado al final de la secuencia.
     * @param elem Elemento a agregar
     */
    @SuppressWarnings("unchecked")
    public void agregar( T elem )
    {
        // Verifica si hay que aumentar el tamaño de la representación
        if( numElems == elems.length )
        {
           throw new IndiceFueraDeRango(numElems); 
        }
        elems[ numElems++ ] = new Elemento(elem);
    }

    /**
     * Devuelve la longitud de la secuencia. <br>
     * <b>post:</b> Se retornó la longitud de la secuencia (número de elementos).
     * @return Longitud de la secuencia
     */
    public int darLongitud( )
    {
        return numElems;
    }


    /**
     * Devuelve un iterador con los elementos de la secuencia. <br>
     * <b>post:</b> Se retornó iterador con los elementos de la secuencia.
     * @return Iterador con los elementos de la secuencia
     */
    @SuppressWarnings("unchecked")
    public Iterador<T> darIterador( )
    {
        IteradorSimple<T> respuesta = new IteradorSimple<T>( numElems );
        for( int i = 0; i < numElems; i++ )
        {
            try
            {
                respuesta.agregar( (elems[ i ].darElemento()) );
            }
            catch( IteradorException e )
            {
                // Nunca debería ocurrir esta excepción
                e.printStackTrace( );
            }
        }
        return respuesta;
    }

    /**
     * Vacía la seucencia.<br>
     * <b>post:</b> La lista se encuentra vacia.
     */
    public void vaciar( )
    {
        // Se borran las referencias a los objetos presentes en la secuencia, para permitir el adecuado
        // trabajo del recolector de basura
        for( int i = 0; i < numElems; i++ )
            elems[ i ] = null;
        numElems = 0;
    }

    /**
     * Convierte la secuencia a un String. <br>
     * <b>post: </b> Se retornó la representación en String de la secuencia. El String tiene el formato "[numeroElementos]: e1-e2-e3..-en", donde e1, e2, ..., en son los elementos
     * que contiene la secuencia y numeroElementos su longitud.
     * @return La representación en String de la secuencia
     */
    @Override
    public String toString( )
    {
        String resp = "[" + numElems + "]:";
        for( int i = 0; i < numElems; i++ )
        {
            resp += elems[ i ].darElemento() + "-";
        }
        return resp;
    }
    
    // -----------------------------------------------------------------
    // Ordenamientos
    // -----------------------------------------------------------------
    /**
     * Ordena la secuencia usando el algoritmo bubble sort. <br>
     * <b> post: </b> Se ordenó la secuencia (en orden ascendente) usando el algoritmo bubble sort.
     */
    @SuppressWarnings("unchecked")
    public void bubbleSort( )
    {
        for( int i = 0; i < numElems; i++ )
        {             
            for( int j = 0; j < numElems - i - 1; j++ )
            {
               
                //Si elemento en la posición j es mayor al elemento en la posición j+1
                //se intercambian                
                if( elems[j].compareTo(elems[j+1]) >0)
                {                    
                    Elemento<T> temp=  elems[j]; 
                    elems[j]= elems[j+1]; 
                    elems[j+1]= temp;                                         
                }
            }

        }
    }
    
    /**
     * Ordena la secuencia usando el algoritmo insertion sort. <br>
     * <b> post: </b> Se ordenó la secuencia (en orden ascendente) usando el algoritmo insertion sort.
     */
    @SuppressWarnings("unchecked")
    public void insertionSort( )
    {
        // Organizar la secuencia. La parte desordenada del arreglo inicia en la posición 1
        // de la secuencia
        Elemento<T> elemento= null; 
        for( int desordenado = 1; desordenado < numElems; desordenado++ )
        {                            
            //Se busca donde insertar el elemento desde el final de la parte ordenada
            // de la secuencia hacia atrás
            elemento= elems[desordenado]; 
            int ordenado = desordenado-1;
            while( ordenado >= 0 && elemento.compareTo( elems[ordenado] ) < 0 )
            {                
                
                elems[ordenado+1]= elems[ordenado];                 
                ordenado--;                                
            }

            // Asignar el elemento en la posición que le corresponde
            elems[ordenado+1]= elemento;
        }
    }
    
    /**
     * Ordena la secuencia usando el algoritmo merge sort. <br>
     * <b> post: </b> Se ordenó la secuencia (en orden ascendente) usando el algoritmo merge sort.
     */
    public void mergeSort()
    {
        auxMergeSort(0, numElems); 
    }
    
    /**
     * Ordena los elementos partiendo la secuencia en dos y mezcla las secuencias ordenadas
     * para obtener la secuencia total ordenada. 
     * <b> pre: </b> primero>=0, primero<n, ultimo>primero, ultimo<n. <br>
     * <b> post: </b> Se ordenó la secuencia (en orden ascendente) usando el algoritmo merge sort.
     * @param primero Posición del elemento a partir del cual se va a realizar el ordenamiento
     * @param ultimo Posición del último elemento a ser considerado en el ordenamiento
     */
    @SuppressWarnings("unchecked")
    private void auxMergeSort(int primero, int ultimo)
    {
        //Solo se consideran las subsecuencias con longitud mayor a 1
        if(ultimo-primero>=2)
        {
            int mitad= (primero+ultimo)/2;
            
            auxMergeSort(primero, mitad);
            auxMergeSort(mitad, ultimo);
            
            T elemento1= ( T )elems[mitad-1]; 
            T elemento2= ( T )elems[mitad];
            //Si la parte a mezclar de la secuencia no se encuentra ordenada se realiza la mezcla 
            if(elemento1.compareTo(elemento2)>0)
            {
                int i= primero; 
                int j= mitad;
                int k= 0; 
                Elemento<T>[] auxiliar= new Elemento[numElems]; //En este arreglo quedan los elementos ordenados temporalmente
                
                while(i<mitad && j < ultimo)
                {
                    if(elems[i].compareTo(elems[j])<0)
                    {
                        auxiliar[k]= elems[i];
                        i++; 
                    }
                    else
                    {
                        auxiliar[k]= elems[j];
                        j++;
                    }                    
                    k++; 
                }
                
                if(i<mitad) //Desplazar los elementos de i a mitad-1
                {
                    int l=0; 
                    for(int cont= i; cont<mitad; cont++)
                    {
                        Elemento<T> elemento= elems[cont]; 
                        elems[primero+k+l]= elemento;
                        l++; 
                    }
                }
                
                //Realizar la copia de la secuencia auxiliar a la definitiva de los elementos ordenados
                for(int cont= 0; cont<k; cont++)
                {
                    Elemento<T> elemento= auxiliar[cont]; 
                    elems[primero+cont]= elemento;  
                }
                
            }
        }
    }
    
    /**
     * Ordena la secuencia usando el algoritmo quick sort. <br>
     * <b> post: </b> Se ordenó la secuencia (en orden ascendente) usando el algoritmo quick sort.
     */
    public void quickSort( )
    {                       
        //Ordena la secuencia usando como pivote el elemento que se encuentra en la posición 0
        auxQuickSort(0, numElems-1);    
    }

    /**
     * Ordena los elementos de la secuencia desde la posición del pivote hasta la posición especificada. <br>
     * <b> pre: </b> pivote>=0, pivote<n, fin>=pivote y fin<n. "n" corresponde a la longitud de la secuencia. <br>
     * <b> post: </b> Se ordenaron los elementos (en orden ascendente) usando como pivote el elemento en posición especificada.
     * @param secuencia Secuencia a ser ordenada
     * @param pivote Posición del pivote
     * @param fin Indice en el que terminan los elementos a ser considerados     
     */
    private void auxQuickSort(int pivote, int fin)
    {
        if(fin>pivote)
        {
            int posPivote= particionQuickSort(pivote, fin); //Nueva posicipon del pivote después de la partición
            auxQuickSort(pivote, posPivote-1); //Se ordenas los elementos que se encuentran antes del ivote
            auxQuickSort(posPivote+1, fin); //Se ordenan los elementos que están despues del pivote    
        }
        
    }
    
    /**
     * Realiza la partición de la secuencia de tal forma que todos los elementos mayores (o iguales) al pivote queden
     * después de éste y todos los menores antes. <br>
     * <b> pre: </b> pivote>=0, pivote<n, fin>=pivote y fin<n. "n" corresponde a la longitud de la secuencia. <br>
     * <b> post: </b> Se realizó la partición de la secuencia de tal forma que todos los elementos mayores (o iguales) al pivote quedaron
     * después de éste y todos los menores antes. 
     * @param secuencia La secuencia en la que se va a realizar la partición
     * @param pivote La posición del pivote antes de la partición
     * @param fin La finalización de los elementos 
     * @return Indice del pivote después de la partición
     */
    @SuppressWarnings("unchecked")
    private int particionQuickSort(int pivote, int fin)
    {
        int posPivote= pivote;
        Elemento<T> elementoPivote= elems[pivote]; 
        int i= pivote;
        int j= fin; 
        
        while(i<j)
        {
            
             
            while(i<j && elems[i].compareTo(elementoPivote)<=0)
            {
                i++;                 
            }
                        
            while(elems[j].compareTo(elementoPivote)>0)
            {
                j--;                 
            }
            
            if(j>i)
            {                 
                Elemento<T> temp= elems[i];  
                elems[i]= elems[j];
                elems[j]= temp;
            }                                              
        }        
        
        elems[pivote]= elems[j];
        elems[j]= elementoPivote;         
        posPivote= j; 
        return posPivote; 
    }
    
    /**
     * Ordena la secuencia usando el algoritmo selection sort. <br>
     * <b> post: </b> Se ordenó la secuencia usando el algoritmo selection sort.
     */
    @SuppressWarnings("unchecked")
    public void selectionSort( )
    {
        for( int menor = 0; menor < numElems; menor++ )
        {
             
            for( int j = menor + 1; j < numElems; j++ )
            {                
                if( elems[menor].compareTo(elems[j])>0)
                {
                    Elemento<T> temp= elems[j]; 
                    elems[j]= elems[menor];
                    elems[menor]= temp;                       
                }
            }
        }
    }
    
    /**
     * Ordena la secuencia usando el algoritmo shake sort. <br>
     * <b> post: </b> Se ordenó la secuencia (en orden ascendente) usando el algoritmo shake sort.
     */
    @SuppressWarnings("unchecked")
    public void shakeSort( )
    {
        int izquierda = 0;
        int derecha = numElems;
        boolean termino = false;
         
        while( !termino )
        {
            termino = true;
            derecha--;
            for( int i = izquierda; i < derecha; i++ )
            {                
                if( elems[i].compareTo(elems[i+1]) >0 )
                {   
                    Elemento<T> temp= elems[i]; 
                    elems[i]= elems[i+1];
                    elems[i+1]= temp;                     
                    termino = false;
                }

            }
            if( termino )
                break;
            termino = true;

            for( int i = derecha; i > izquierda; i-- )
            {                              
                if( elems[i].compareTo(elems[i-1]) <0 )
                {     
                    Elemento<T> temp= elems[i]; 
                    elems[i]= elems[i-1]; 
                    elems[i-1]= temp;                     
                    termino = false;
                }

            }
            izquierda++;
        }
    }
    
    /**
     * Ordena la secuencia usando el algoritmo shell sort. <br>
     * <b> post: </b> Se ordenó la secuencia (en orden ascendente) usando el algoritmo shell sort.
     */
    @SuppressWarnings("unchecked")
    public void shellSort( )
    {
        // Organizar la secuencia. El espaciamiento entre las subsecuencias es n/2, 
        // donde n es el tamaño de la secuencia
        for( int espacio = numElems/2; espacio>0 ; espacio/= 2)
        {            
            for( int cont = 0; cont<espacio ; cont++)
            {
                auxShellSort(cont, numElems, espacio);
            }         
        }
    }

    /**
     * Ordena los elementos separados por el espacio dado de la secuencia dentro de los limites establecidos. <br>
     * <b> pre: </b> inicio>=0, inicio<n, fin>inicio y fin<n-1. "n" corresponde a la longitud de la secuencia. <br>
     * <b> post: </b> Se ordenaron los elementos determinados por el espacio dado.
     * @param inicio Indice en la que inician los elementos a ser considerados
     * @param fin Indice en el que terminan los elementos a ser considerados
     * @param espacio El espacio que hay entre los elementos a ser ordenados
     */
    @SuppressWarnings("unchecked")
    private void auxShellSort( int inicio, int fin, int espacio )
    {
        // Ordena los elementos por inserción de acuerdo al espacio dado
        for( int desordenado= inicio+espacio; desordenado<fin; desordenado+=espacio)
        {                        
            //Se busca donde insertar el elemento desde el final de la parte ordenada
            // de la secuencia hacia atrás
            int ordenado;            
            ordenado= desordenado-espacio;      
            Elemento<T> elemento= elems[desordenado];
            while(ordenado>=inicio && elemento.compareTo(elems[ordenado])<0)
            {   
                elems[ordenado+espacio]= elems[ordenado];                
                ordenado-=espacio;                                                  
            }
            
            //Asignar el elemento en la posición que le corresponde
            elems[ordenado+espacio]= elemento;                                
        }       
    }  
    
    
    // -----------------------------------------------------------------
    // Búsquedas
    // -----------------------------------------------------------------
    /**
     * Busca el elemento especificado de forma secuencial. <br>
     * <b>post:</b> Se retornó la posición del elemento buscado dentro de la seucencia o -1 si no existe. <br>
     * @param elem Elemento a buscar
     * @return Posición del elemento buscado dentro de la secuencia o -1 si no existe
     */
    @SuppressWarnings("unchecked")
    public int busquedaSecuencial( T elem )
    {
        int pos = 0;
        Elemento<T> aux= new Elemento<T>(elem); 
        for( ; pos < numElems && aux.compareTo( elems[ pos ] )!=0; pos++ )
            ;
        return pos == numElems ? -1 : pos;
    }
    
    /**
     * Busca el elemento especificado usando la búsqueda binaria. <br>
     * <b> pre: </b> La secuencia se encuentra ordenada. <br>
     * <b>post:</b> Se retornó la posición del elemento buscado dentro de la seucencia o -1 si no existe. <br>
     * @param elem Elemento a buscar
     * @return Posición del elemento buscado dentro de la secuencia o -1 si no existe
     */
    @SuppressWarnings("unchecked")
    public int busquedaBinaria( T elem )
    {
        int pos = -1;
        Elemento<T> aux= new Elemento<T>(elem); 
        pos= auxBusquedaBinaria(aux, 0, numElems-1);         
        return pos;
    }
    
    /**
     * 
     * @param elem
     * @param inicio
     * @param fin
     * @return
     */
    private int auxBusquedaBinaria(Elemento<T> elem, int inicio, int fin)
    {
        int pos=-1; 
        
        //La búsqueda sólo se realiza si no se a revisado al menos un elemento
        int mitad= (inicio+fin)/2;
        if(mitad<=fin && mitad>=inicio)
        {                                     
            if(elems[mitad].compareTo(elem)==0) //Se encontró el elemento
            {
                pos= mitad; 
            }
            else if(elems[mitad].compareTo(elem)>0) //El elemento puede estar en la parte izquierda de la secuencia
            {
                pos= auxBusquedaBinaria(elem, inicio, mitad-1);
                 
            }
            else //El elemento puede estar en la parte derecha de la secuencia
            {
                pos= auxBusquedaBinaria(elem, mitad+1, fin);
            }
                
        }
                
        return pos; 
    }
}
