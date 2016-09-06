/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoTrie.java,v 1.16 2006/07/24 16:49:43 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Daniel Romero - 15/07/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.collections.trie;

import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;
import uniandes.cupi2.collections.lista.Lista;

/**
 * Nodo del trie.
 * @param T El tipo de elemento a ser manejado en el nodo
 */
public class NodoTrie <T>
{    
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Caracter que contiene el nodo
     */
    private char caracter;
    
    /**
     * Elemento del nodo
     */
    private T elemento; 
    
    /**
     * Subárbol izquierdo
     */
    private NodoTrie<T> izqNodo;
        
    /**
     * Subárbol correspondiente al hermano derecho del nodo actual 
     */
    private NodoTrie<T> hermanoDerNodo; 
           
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Constructor del trie. <br>
     * <b> post: </b> Se construyó el nodo con el caracter especificado, elemento= null, izqNodo= null y hermanoDerNodo= null.
     * @param carac Caracter que va a contener el nodo
     */
    public NodoTrie(char carac)
    {
        caracter= carac;        
        izqNodo= null; 
        hermanoDerNodo= null;     
        elemento= null; 
    }
    
    /**
     * Constructor del trie. <br>
     * <b> post: </b> Se construyó el nodo con el caracter y elemento especificados, izqNodo= null y hermanoDerNodo= null.
     * @param carac Caracter que va a contener el nodo
     * @param elem Elemento que contiene el nodo
     */
    public NodoTrie(char carac, T elem)
    {
        caracter= carac;        
        izqNodo= null; 
        hermanoDerNodo= null;     
        elemento= elem; 
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
        
    /**
     * Devuelve los elementos del trie en inorden. <br>
     * <b>pre: </b> resultado!=null. <br>
     * <b>post: </b> Se retorno un vector con el recorrido en inorden del trie.
     * 
     * @param resultado Vector con los elementos del trie en inorden
     */
    public void inorden(IteradorSimple<T> resultado)
    {
        try
        {
            /*if( izqNodo != null )
            {
                izqNodo.inorden( resultado );
                
                if(izqNodo.elemento!=null)
                    resultado.agregar( izqNodo.elemento );
                
                
                NodoTrie<T> nodo= izqNodo.hermanoDerNodo;
                
                while(nodo!=null)
                {
                    nodo.inorden(resultado); 
                    
                    nodo= nodo.hermanoDerNodo; 
                }
            }*/
            if( izqNodo != null )
            {
                izqNodo.inorden( resultado );
                
                if(elemento!=null)
                    resultado.agregar( elemento );
                
                
                NodoTrie<T> nodo= izqNodo.hermanoDerNodo;
                
                while(nodo!=null)
                {
                    nodo.inorden(resultado); 
                    
                    nodo= nodo.hermanoDerNodo; 
                }
            }
            else
            {
                if(elemento!=null)
                    resultado.agregar( elemento );
            }
        }
        catch( IteradorException e )
        {                
            e.printStackTrace();
        }
    }
    
    /**
     * Ingresa el elemento dado asociado con la palabra especificada en el trie del que el nodo actual es la raíz. <br>
     * <b> pre: </b> El elemento no existe en el trie, palabra!=null, elem!=null y la longitud de palabra es mayor o igual a 1.<br>
     * <b> post: </b> Se insertó el elemento asociado con la palabra especificada en el trie.
     * @param palabra Palabra con la que está asociada el elemento
     * @param elem El elemento a ser insertado
     * @return El número de nuevos caracteres adicionados
     */
    public int insertar(String palabra, T elem)
    {
        char c= palabra.charAt(0); //Primer caracter de la palabra
        NodoTrie<T> nodo= null; //Nodo el que se debe realizar la inserción     
        int insertados= 0;          
        
        //Caso 1: La inserción se debe realizar en el hijo izquierdo
        if(izqNodo==null) //Caso 1a: el hijo izquierdo no existe
        {             
            izqNodo= new NodoTrie<T>(c);             
            nodo= izqNodo;  
            insertados++;              
        }
        else if(izqNodo.caracter==c) //Caso 1b: el hermano izquierdo existe y comienza por el caracter que este contiene
        {
            nodo= izqNodo;     
            /*if(izqNodo.izqNodo==null)
            {
                esPalabra= true; 
            }*/
        }
        //Caso 2: La inserción se debe realizar en el hijo derecho (hermano derecho del hijo izquierdo)
        else if(izqNodo.caracter<c)
        {                                   
            //Buscar el nodo en el que se debe realizar la inserción
            NodoTrie<T> hermanoIzquierdo= izqNodo;
            nodo= izqNodo.hermanoDerNodo;                 
            while(nodo!=null && nodo.caracter<c)
            {
                hermanoIzquierdo= nodo; 
                nodo= nodo.hermanoDerNodo; 
            }
            
            if(nodo==null)
            {
                nodo= new NodoTrie<T>(c); 
                hermanoIzquierdo.hermanoDerNodo= nodo; 
                insertados++; 
            }
            else if(nodo.caracter>c)
            {
                NodoTrie<T> aux= new NodoTrie<T>(c); 
                aux.hermanoDerNodo= nodo; 
                hermanoIzquierdo.hermanoDerNodo= aux; 
                nodo= aux; 
                insertados++; 
            }
            /*else if (nodo.izqNodo==null) //Es igual a c. Solo hay que verificar si se trata de un prefijo
            {                    
                esPalabra= true;                     
            }*/
            
            
            
            
            
        }
        //Caso 4: Se debe insertar en el hijo izquierdo pero realizando un corrimiento a la derecha de los demás tries
        else 
        {
            nodo= new NodoTrie<T>(c); 
            nodo.hermanoDerNodo= izqNodo; 
            izqNodo= nodo; 
            insertados++; 
        }
                        
        if(palabra.length()>1) //Si la longitud de la palabra es mayor a 1 se continúa la inserción
        {
            /*if(esPalabra) //Si la palabra es prefijo de la nueva palabra que se está insertando se pone '*' para que no se pierda
            {
                nodo.izqNodo= new NodoTrie(TERMINACION);  
            } */           
            String nPalabra= palabra.substring(1);                
            insertados+= nodo.insertar(nPalabra, elem);                         
        }
        /*else if(izqNodo.caracter==c && !creoIzquierdo) //La palabra a ser insertada es prefijo de una palabra que ya existe
        {
            nodo.elemento= elem; 
            if(izqNodo.izqNodo!=null)
            {
                nodo.hermanoDerNodo= izqNodo.izqNodo; 
            }            
            izqNodo.izqNodo= nodo;             
        }*/
        else if(izqNodo.caracter==c) //El elemento queda en el nodo
        {
            nodo.elemento= elem;
        }
        return insertados; 
    }

    /**
     * Elimina la palabra del trie del que el nodo actual es raíz. <br>
     * <b> pre: </b> palabra!=null, longitud palabra es mayor a cero. <br>
     * <b> post: </b> Se elimino la palabra si esta existía en el trie. Si la palabra
     * no existe se retorna false. 
     * @param palabra La palabra a ser eliminada
     * @return True si la palabra se eliminó o false en caso contrario
     */
    public int eliminar(String palabra)
    {
        int eliminados= 0;
        char c= palabra.charAt(0); //Primer caracter de la palabra
                
        if(izqNodo!=null)
        {
            if(izqNodo.caracter==c) //Sólo se continúa la eliminación si el primer caracter de la palabra es igual al del nodo actual
            {
                if(palabra.length()>1)
                {
                    eliminados+= izqNodo.eliminar(palabra.substring(1)); 
                    
                    if(izqNodo.izqNodo== null && eliminados>0 && izqNodo.elemento==null)
                    {
                        izqNodo= izqNodo.hermanoDerNodo;
                        eliminados++; 
                    }
                }
                else
                {
                    if(izqNodo.elemento!= null)
                    {
                        izqNodo.elemento= null; 
                        
                        if(izqNodo.izqNodo==null)
                        {
                            izqNodo= izqNodo.hermanoDerNodo;
                            eliminados++; 
                        }                                                
                    }
                    /*else if(izqNodo.izqNodo== null)
                    {
                        izqNodo= izqNodo.hermanoDerNodo;
                        eliminados++; 
                    }*/                                        
                }
            }
            else if(izqNodo.caracter<c && izqNodo.hermanoDerNodo!=null) //Buscar si la palabra existe en otro nodo a la derecha y eliminarla
            {
                //Buscar el nodo en el que se debe realizar la eliminación
                NodoTrie<T> hermanoIzquierdo= izqNodo;
                NodoTrie<T> nodo= izqNodo.hermanoDerNodo;                 
                while(nodo!=null && nodo.caracter!=c)
                {
                    hermanoIzquierdo= nodo; 
                    nodo= nodo.hermanoDerNodo; 
                }
                
                if(nodo!=null)
                {
                    if(palabra.length()>1)
                    {
                        eliminados= nodo.eliminar(palabra.substring(1)); 
                        
                        if(nodo.izqNodo== null && eliminados>0 && nodo.elemento==null)
                        {
                            hermanoIzquierdo.hermanoDerNodo= nodo.hermanoDerNodo;
                            eliminados++; 
                        }
                    }
                    else
                    {
                        if(nodo.elemento!= null)
                        {
                            nodo.elemento= null; 
                            
                            if(nodo.izqNodo==null)
                            {
                                hermanoIzquierdo.hermanoDerNodo= nodo.hermanoDerNodo;                                
                                eliminados++; 
                            }                                                
                        /*if(nodo.izqNodo!=null && nodo.izqNodo.caracter==TERMINACION)
                        {
                            nodo.izqNodo= nodo.izqNodo.hermanoDerNodo;
                            //eliminados++; 
                        }
                        else if(nodo.izqNodo== null)
                        {
                            hermanoIzquierdo.hermanoDerNodo= nodo.hermanoDerNodo;
                            eliminados++; 
                        }  */                                      
                        }
                     
                    }
                
                
                }
            }
        }                        
        return eliminados;        
    }  
    
    /**
     * Busca el elemento del trie cuya representación en String corresponte a la palabra dada. <br>
     * <b> pre: </b> palabra!=null. <br>
     * <b> post: </b> Se retornó el elemento del trie cuya representación en String corresponde a la palabra dada. Si la palabra no corresponde
     * a ningún elemento se retorna null.  
     * @param palabra Palabra a ser buscada
     * @return El elemento del trie cuya representación en String corresponde a la palabra dada. Si no existe tal elemento se retorna null.
     */
    public T buscar(String palabra)
    {        
        T elem= null; 
        char c= palabra.charAt(0); //Primer caracter de la palabra
        
        if(izqNodo!=null)
        {
            if(izqNodo.caracter==c) //Sólo se continúa la eliminación si el primer caracter de la palabra es igual al del nodo actual
            {
                if(palabra.length()>1)
                {
                    elem= izqNodo.buscar(palabra.substring(1));                                         
                }
                else
                {
                    if((izqNodo.elemento!=null))
                    {                        
                        elem= izqNodo.elemento; 
                    }               
                    /*if((izqNodo.izqNodo!=null && izqNodo.izqNodo.caracter==TERMINACION) || izqNodo.izqNodo== null)
                    {                        
                        esta= true; 
                    } */                                                           
                }
            }
            else if(izqNodo.caracter<c && izqNodo.hermanoDerNodo!=null) //Buscar si la palabra existe en otro nodo a la derecha y eliminarla
            {
                //Buscar en los hermanos a derechos                
                NodoTrie<T> nodo= izqNodo.hermanoDerNodo;                 
                while(nodo!=null && nodo.caracter!=c)
                {                 
                    nodo= nodo.hermanoDerNodo; 
                }
                
                if(nodo!=null)
                {
                    if(palabra.length()>1)
                    {
                        elem= nodo.buscar(palabra.substring(1)); 
                    }
                    else
                    {
                        if((nodo.elemento!=null))
                        {                        
                            elem= nodo.elemento; 
                        }  
                       /* if((nodo.izqNodo!=null && nodo.izqNodo.caracter==TERMINACION) || nodo.izqNodo== null)
                        {                            
                            esta= true; 
                        }*/                                     
                    }                    
                }                                
            }
        }                        
        return elem;        
    }
    
    /**
     * Retorna la altura del trie del que el nodo actual es raíz. <br> 
     * <b>post: </b> Se retornó la altura del trie. 
     * @return La altura del trie
     */
    public int darAltura()
    {
        int altura= ( izqNodo == null ) ? 1 : izqNodo.darAltura( )+1;
        int aux= ( hermanoDerNodo == null ) ? 0 : hermanoDerNodo.darAltura( ); 
        
        if(aux>altura)
        {
            altura= aux;  
        }                       
        return altura;         
    }
    
    /**
     * Retorna el hijo izquierdo del nodo. <br>
     * <b>post: </b> Se retornó el hijo izquierdo del nodo.
     * @return Hijo izquierdo del nodo 
     */
    public NodoTrie<T> darHijoIzquierdo()
    {
        return izqNodo; 
    }
    
    
    /**
     * Retorna el hermano derecho del nodo. <br>
     * <b>post: </b> Se retornó el hermano derecho del nodo.
     * @return   Hermano derecho del nodo 
     */
    public NodoTrie<T> darHermanoDerecho()
    {
        return hermanoDerNodo; 
    }
    
    /**
     * Retorna el caracter del nodo. <br>
     * <b>post: </b> Se retornó el caracter del nodo.
     * @return Caracter del nodo 
     */
    public char darCaracter()
    {
        return caracter; 
    }
    
    /**
     * Retorna el elemento del nodo. <br>
     * <b>post: </b> Se retornó el elemento del nodo.
     * @return El elemento del nodo
     */
    public T darElemento()
    {
        return elemento; 
    }
    
    /**
     * Busca los elementos que tienen como prefijo la palabra especificada. <br>
     * <b> pre: </b> prefijo!= null, lista!=null. <br>
     * <b> post: </b> La lista contiene los elementos que tienen como prefijo la palabra especificada. 
     * @param prefijo Prefijo de la palabra
     * @param lista La lista en la que se van a adicionar los elementos
     */
    public void buscarPorPrefijo(String prefijo, Lista<T> lista)
    {        
        char c= prefijo.charAt(0); //Primer caracter del prefijo
        
        if(izqNodo!=null)
        {
            if(izqNodo.caracter==c) //Verificar si el prefijo se encuentra por el nodo actual
            {
                if(prefijo.length()>1)
                {
                    izqNodo.buscarPorPrefijo(prefijo.substring(1), lista);                                         
                }
                else
                {
                    izqNodo.darElementosTrie(lista);                    
                }
            }
            else if(izqNodo.caracter<c && izqNodo.hermanoDerNodo!=null) //Buscar si el prefijo existe en otro nodo a la derecha
            {
                //Buscar en los hermanos derechos                
                NodoTrie<T> nodo= izqNodo.hermanoDerNodo;                 
                while(nodo!=null && nodo.caracter!=c)
                {                 
                    nodo= nodo.hermanoDerNodo; 
                }
                
                if(nodo!=null)
                {
                    if(prefijo.length()>1)
                    {
                        nodo.buscarPorPrefijo(prefijo.substring(1), lista); 
                    }
                    else
                    {
                        nodo.darElementosTrie(lista);                                                           
                    }                    
                }                                
            }
        }                                
    }
    
    /**
     * Retorna los elementos del nodo. <br>
     * <b> pre: </b> lista!= null. <br>
     * <b> post: </b> La lista contiene los elementos del nododel trie cuyo raiz es el nodo actual  
     * @param lista La lista en la que se van a adicionar los elementos 
     */
    private void darElementosTrie(Lista<T> lista)
    {
        if(elemento!=null)
        {
            lista.agregar(elemento);
        }
        
        if(izqNodo!=null)
        {               
            izqNodo.darElementosTrie(lista);
            NodoTrie<T> nodo= izqNodo.hermanoDerNodo; 
            
            while(nodo!=null)
            {
                nodo.darElementosTrie(lista);
                nodo= nodo.hermanoDerNodo; 
            }
        }              
    }
}
