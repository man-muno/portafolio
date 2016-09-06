/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Heap.java,v 1.8 2006/06/22 22:15:21 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 13, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.heap;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;

/**
 * Implementaci�n de un heap binario ordenado
 * @param <T> Tipo de datos que contiene cada nodo del heap
 */
public class Heap<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Tama�o inicial del arreglo
     */
    private final static int INIT = 20;

    /**
     * N�mero de posiciones a agregar al crecer el arreglo
     */
    private final static int DELTA = 20;
    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Los elementos del heap
     */
    private Elemento<T>[] elementos;  
    
    /**
     * Peso del heap
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del heap vac�o con capacidad para 20 elementos. <br>
     * <b>post: </b> Se construy� un heap vac�o con capacidad para 20 elementos.
     */
    public Heap( )
    {        
        this(INIT); 
    }
    
    /**
     * Constructor del heap con la capacidad inicial. <br>
     * <b>pre: </b> capacidad!=null, capacidad>0. <br>
     * <b>post: </b> Se construy� el heap con la capacidad inicial especificada.
     * @param capacidad Capacidad inicial para el heap
     */
    @SuppressWarnings("unchecked")
    public Heap (int capacidad)
    {
        elementos= new Elemento[capacidad]; 
        peso=0; 
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve la ra�z del heap. La ra�z corresponde al mismo elemento menor. <br>
     * <b>post: </b> Se retorn� la ra�z del heap.
     * @return Ra�z del heap
     */
    public T darRaiz( )
    {
        T raiz= null; 
        if(peso>0)
        {
            raiz= elementos[0].darElemento();    
        }        
        return raiz;                 
    }

    /**
     * Agrega un nuevo elemento al heap. <br>
     * <b>pre:</b> elemento!=null, el elemento no se encuentra en el heap. <br>
     * <b>post:</b> Se agreg� el elemento al heap, conserv�ndose la propiedad de los heaps.
     * @param elemento Elemento que se va a agregar
     */
    @SuppressWarnings("unchecked")
    public void insertar( T elemento )
    {
        //Verifica si hay que aumentar el tama�o de la representaci�n
        if( peso == elementos.length )
        {
            Elemento viejo[] = elementos;
            elementos = new Elemento[elementos.length + DELTA];
            System.arraycopy( viejo, 0, elementos, 0, viejo.length );
        }
        
        Elemento<T> aux= new Elemento(elemento);      
        peso++; 
        int nuevo= peso-1; //Indice inicial del nuevo elemento        
        int padre= ((nuevo+1)/2)-1; //Indice del padre del nuevo elemento
        
        if(peso==1)
        {
            padre=0; 
        }
        
        //Localice el sitio en el que debe ser insertado en elemento
        while(padre>= 0 && nuevo>0 && aux.compareTo(elementos[padre])<0)
        {
            elementos[nuevo]= elementos[padre]; 
            nuevo= padre; 
            padre= ((nuevo+1)/2)-1; 
        }
        
        elementos[nuevo]= aux; 
        
        //peso++; 
    }

    /**
     * Elimina del heap el elemento que llega como par�metro. <br>
     * <b>pre:</b> elemento!=null. <br>
     * <b>post:</b> Se elimin� el elemento si exist�a en la estructura. 
     * @param elemento Elemento que se va a eliminar
     * @return El elemento que se elimin� o null si el elemento no se encontr�. 
     */
    @SuppressWarnings("unchecked")
    public T eliminar( T elemento )
    {
        T eliminado= null; 
        
        if(peso>0)
        {
            Elemento<T> aux= new Elemento<T>(elemento);
            int posEliminado=-1;
            int cont= 0;  
            
            while(posEliminado==-1 && cont<peso)
            {
                if(elementos[cont].compareTo(aux)==0)
                {
                    posEliminado=cont; 
                }                
                cont++; 
            }
            
            if(posEliminado!=-1)
            {
                eliminado= elementos[posEliminado].darElemento(); 
                if(posEliminado<peso-1)
                {
                    elementos[posEliminado]= elementos[peso-1];                     
                }
                elementos[peso-1]= null;
                peso--; 
                
                reheap(posEliminado);                                 
            }            
        }        
        return eliminado; 
    }

    /**
     * Localiza y retorna un elemento en el heap, recibiendo un modelo del mismo como par�metro. <br>
     * <b>pre:</b> modelo!=null. <br>
     * <b>post:</b> Se retorn� elemento del heap que corresponde al modelo o null si no encuentra el elemento.
     * @param modelo Descripci�n del elemento que se va a buscar en el heap. Debe contener por lo menos la informaci�n m�nima necesaria para que el m�todo de comparaci�n del
     *        nodo pueda establecer una relaci�n de orden
     * @return El elemento del heap que corresponde al modelo o null si no encuentra el elemento
     */
    @SuppressWarnings("unchecked")
    public T buscar( T modelo )
    {
        int cont=0;
        T elemento= null;
        Elemento<T> aux= new Elemento(modelo); 
        while(cont<peso && elemento== null)
        {
            if(aux.compareTo(elementos[cont])==0)
            {
                elemento= elementos[cont].darElemento(); 
            }
            
            cont++; 
        }                
        return elemento;         
    }

    /**
     * Devuelve el recorrido por niveles del heap. <br>
     * <b>post:</b> Se retorn� el iterador con los elementos del heap en el orden dado por el recorrido por niveles.
     * @return Iterador con los elementos del en el orden dado por el recorrido por niveles
     */
    public Iterador<T> darRecorridoNiveles( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( peso );        
        try
        {
            for(int cont=0; cont<peso; cont++)
            {            
                resultado.agregar(elementos[cont].darElemento());
            }
            
        }
        catch( IteradorException e )
        {           
            e.printStackTrace();
        } 
        
        return resultado;
    }

    /**
     * Devuelve la altura del heap. <br>
     * <b>post:</b> Se retorn� la altura del heap.
     * @return Altura del heap
     */
    public int darAltura( )
    {
        int altura=0; 
                 
        int cont= 0; 
        
        while(Math.pow(2, cont)<=peso)
        {
            cont++;
        }
        
        altura= cont; 
                
        
        return altura;
    }

    /**
     * Devuelve el peso del heap. <br>
     * <b>post:</b> Se retorn� el peso del heap.
     * @return Peso del heap
     */
    public int darPeso( )
    {
        return peso;
    }

    /**
     * Devuelve el elemento mayor del heap. <br>
     * <b>post:</b> Se retorn� el elemento mayor del heap o null si el heap est� vac�o.
     * @return El elemento mayor del heap o null si el heap est� vac�o
     */
    public T darMayor( )
    {
    	T mayor= null;
    	
    	if(peso>0)
    	{
    		
    		Elemento<T> aux= elementos[0]; 
    		
    		for(int cont=1; cont<peso; cont++)
    		{
    			if(elementos[cont].compareTo(aux)>0)
                {
    			    aux= elementos[cont]; 
                }
    		}
            
            mayor= aux.darElemento(); 
    	}
    	
        return mayor;
    }
    
    /**
     * Devuelve el elemento menor del heap. <br>
     * <b>post:</b> Se retorn� el elemento menor del heap o null si el heap est� vac�o.
     * @return El elemento menor del heap o null si el heap est� vac�o
     */
    public T darMenor( )
    {
    	T menor= null; 
    	
    	if(peso>0)
    	{
    		menor= elementos[0].darElemento(); 
    	}
        return menor;
    }

    /**
     * Elimina el elemento menor del heap. <br>
     * <b>post:</b> Se elimin� el elemento menor del heap.
     * @return El elemento menor del heap o null si el heap est� vac�o
     */
    public T eliminarMenor( )
    {
        T elemento= null;
        if(peso>0)
        {
            elemento= eliminar(elementos[0].darElemento()); 
        }                 
        return elemento; 
    }
    
    /**
     * Vac�a el heap.<br>
     * <b>post:</b> El heap se encuentra vacio.
     */
    public void vaciar( )
    {
        // Se borran las referencias a los objetos presentes en el heap, para permitir el adecuado
        // trabajo del recolector de basura
        for( int i = 0; i < peso; i++ )
            elementos[ i ] = null;
        peso = 0;
    }
    
    /**
     * Retorna los elementos que contiene el heap en un arreglo o null
     * si el heap no tiene elementos. Los elementos se encuentran encapsulados
     * en objetos de tipo Elemento
     * @return Los elementos del heap
     */
    @SuppressWarnings("unchecked")
    public Elemento<T>[] darElementos()
    {
        @SuppressWarnings("unused") Elemento<T>[] elems= null; 
        
        if(peso>0)
        {
            elems= elementos;                         
        }
        
        return elementos; 
    }
            
    // -----------------------------------------------------------------
    // M�todos Auxiliares
    // -----------------------------------------------------------------
    /**
     * Realiza el reheap del heap a partir del nodo correspondiente al �ndice especificado. La operaci�n
     * de reheap se encarga de que el heap siga cumpliendo con la propiedad de que todo elemento 
     * sea mayor al elemento que se encuentra en la ra�z. <br>
     * <b>pre: </b> No existen elementos repetidos en el �rbol. <br>
     * <b>post: </b> Se realiz� el reheap del heap a partir del nodo especificado. 
     * @param indice El indice a partir del cual se va a realizar el reheap.
     */
    private void reheap(int indice)
    {
        boolean terminado= false; 
        
        Elemento<T> elemento= elementos[indice]; 
        int menor= (2*(indice+1))-1; 
        
        while(!terminado && menor<peso)
        {
            int menorDerecho= menor+1;
            
            //Se determina cual de los dos hijos tiene el valor mayor
            if(menorDerecho<peso && elementos[menorDerecho].compareTo(elementos[menor])<0)
            {
                menor= menorDerecho; 
            }
            
            if(elemento.compareTo(elementos[menor])>0)
            {
                elementos[indice]= elementos[menor];
                indice= menor; 
                menor= (2*(indice+1))-1;
            }
            else
            {
                terminado= true; 
            }
        }        
        elementos[indice]= elemento; 
    }
}