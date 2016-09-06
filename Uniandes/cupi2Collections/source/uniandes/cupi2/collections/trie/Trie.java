/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Trie.java,v 1.11 2006/07/24 16:49:43 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 11, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.trie;

import uniandes.cupi2.collections.iterador.IteradorSimple;
import uniandes.cupi2.collections.lista.Lista;

/**
 * Representa un trie
 * @param <T> Tipo de datos a almacenar en el trie. El método toString de la clase T debe 
 * estar bien definido 
 */
public class Trie<T>
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Raíz del trie
     */
    private NodoTrie<T> raiz;
    
    /**
     * Peso del trie
     */
    private int peso;
    
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Construye un tree vacío.<br>
     * <b> post: </b> Se construyó un trie vacío. 
     */
    public Trie()
    {
        raiz= new NodoTrie<T>(' '); 
    }
    
    /**
     * Construye un tree con la raíz especificada. <br>
     * <b> pre: </b> El caracter de la raiz debe ser ' ' y su elemento==null. <br>
     * <b> post: </b> Se construyó un trie con la raíz especificada. 
     * @param r La raíz del trie
     * @param p El peso del trie (número de nodos que tiene el trie)
     */
    public Trie(NodoTrie<T> r, int p)
    {
        raiz= r; 
        peso= p; 
    }
    
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    
    /**
     * Inserta un elemento dado en el trie. <br>
     * <b> pre: </b> elemento!=null. <br>
     * <b> post: </b> Se inserto el elemento dado en el trie.
     * @param elemento El elemento a ser insertado en el trie
     * @throws PalabraInvalidaException Si el elemento a ser insertado no es válido (su representación en String es vacía)
     * @throws PalabraInvalidaException Si el  elemento a ser insertado ya existe en el trie
     */
    public void insertar(T elemento) throws PalabraInvalidaException
    {
        String palabra= elemento.toString(); 
        if(palabra.length()==0)
        {
            throw new PalabraInvalidaException("El elemento que se quiere ingresar no es válido");
 
        }
        else if(buscar(palabra)!=null)
        {
            throw new PalabraInvalidaException("El elemento que se quiere ingresar ya existe en el trie");
        }
        else
        {
            peso+=raiz.insertar(palabra, elemento);
        }
    }

    /**
     * Elimina el elemento del trie cuya representación en String corresponde a la palabra dada. <br>
     * <b> pre: </b> palabra!=null. <br> 
     * <b> post: </b> Se eliminó el elemento del trie cuya representación en String corresponde a la palabra dada. Si no existe tal elemento se retorna null.
     * @param palabra La palabra a ser eliminada
     * @return El elemento del trie cuya representación en String corresponde a la palabra dada. Si no existe tal elemento se retorna null. 
     */
    public T eliminar(String palabra)
    {
    	T elemento= buscar(palabra); 
    	int eliminados= elemento!=null?raiz.eliminar(palabra): 0;     	    	
        peso-= eliminados;         
        return elemento;  
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
        return raiz.buscar(palabra);
    }
    
    /**
     * Busca todos los elementos asociados con el prefijo dado.<br>
     * <b> pre: </b> prefijo!=null. <br>
     * <b> post: </b> Se retornó la lista de elementos correspondiente al prefijo dado.
     * @param prefijo El prefijo del que se desean los elementos asociados
     * @return La lista de elementos correspondiente al prefijo dado
     */
    public Lista<T> buscarPorPrefijo(String prefijo)
    {
        Lista<T> lista= new Lista<T>();
        
        raiz.buscarPorPrefijo(prefijo, lista); 
        
        return lista; 
    }
    
    /**
     * Devuelve los elementos del trie en inorden. <br>
     * <b>pre: </b> resultado!=null. <br>
     * <b>post: </b> Se retorno un vector con el recorrido en inorden del trie.
     * 
     * @param resultado Vector con los elementos del trie en inorden
     */
    public IteradorSimple<T> inorden()
    {
        IteradorSimple<T> resultado= new IteradorSimple<T>(peso); 
        raiz.inorden(resultado);
        
        return resultado; 
    }
    
    /**
     * Retorna el peso (número de nodos) del trie
     * <b>post: </b> Se retornó el peso del trie
     * @return El peso del trie
     */
    public int darPeso()
    {
        return peso; 
    }
    

    /**
     * Retorna la alturta del trie. <br>
     * <b>post: </b> Se retornó la altura del trie.
     * @return La altura del trie
     */
    public int darAltura()
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }
    
    /**
     * Retorna la raíz del trie. <br>
     * <b>post: </b> Se retornó la raíz trie.
     * @return La raíz del trie
     */
    public NodoTrie<T> darRaiz()
    {
        return raiz; 
    }
    
    
    public static void main( String[] args )
    {
        int c= '*'; 
        int c1= 'a';
        int c2= 'z';
        int c3= 'A';
        int c4= 'Z';
        int c5= '1';
        int c6= '9';
        int c7= '<';
        char c8= ( char ) ( c-1 ); 
        
        System.out.println(c);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        
    }
}
