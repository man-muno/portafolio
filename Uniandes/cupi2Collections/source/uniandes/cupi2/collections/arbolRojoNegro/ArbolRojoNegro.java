/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ArbolRojoNegro.java,v 1.8 2006/06/20 20:34:50 da-romer Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - 25/02/2006
 * Autor: Pablo Barvo - 25/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbolRojoNegro;

import uniandes.cupi2.collections.arbol2_4.Arbol2_4;
import uniandes.cupi2.collections.iterador.*;

/**
 * Implementación de un árbol binario ordenado balanceado por altura que es equivalente a ún árbol 2-4: árbol rojo- negro
 * @param <T> Tipo de datos que contiene cada nodo del árbol
 */
public class ArbolRojoNegro<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Raíz del árbol AVL
     */
    private NodoRojoNegro<T> raiz;

    /**
     * Peso del árbol AVL
     */
    private int peso;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del árbol AVL vacío. <br>
     * <b>post: </b> Se construyó un árbol AVL vacío.
     */
    public ArbolRojoNegro( )
    {
        raiz = null;
        peso = 0;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Devuelve la raíz del árbol para navegarlo. <br>
     * <b>post: </b> Se retornó la raíz del árbol para navegarlo.
     * @return Raíz del árbol para navegarlo
     */
    public NodoRojoNegro<T> darRaiz( )
    {
        return raiz;
    }

    /**
     * Agrega un nuevo elemento al árbol AVL. <br>
     * <b>post:</b> se agregó el elemento al árbol de manera ordenada.
     * @param elemento Elemento que se va a agregar
     * @throws ExisteException El elemento especificado ya existe en el árbol
     */
    public void insertar( T elemento ) throws ExisteException
    {
        if( raiz == null )
        {
            // Caso 1: el árbol es vacío
            raiz = new NodoRojoNegro<T>( elemento, null );
            //La raíz debe quedar negra
             
        }
        else
        {
            // Caso 2: el árbol no es vacío
            raiz = raiz.insertar( elemento );
        }
        raiz.cambiarColor(NodoRojoNegro.NEGRO);
        raiz.cambiarPadreNodo(null); 
        peso++;
    }

    /**
     * Elimina del árbol el elemento que llega como parámetro. <br>
     * <b>post: </b> Se eliminó el elemento especificado si existía en la estructura.
     * @param elemento Elemento que se va a eliminar
     * @throws NoExisteException El elemento especificado no existe en el árbol
     */
    public void eliminar( T elemento ) throws NoExisteException
    {
        if( raiz != null )
        {
            // Caso 1: el árbol no es vacío
            raiz = raiz.eliminar( elemento );
            peso--;
        }
        else
        {
            // Caso 2: el árbol es vacío
            throw new NoExisteException( "El elemento especificado no existe en el árbol" );
        }
    }

    /**
     * Localiza y retorna un elemento en el árbol AVL, recibiendo un modelo del mismo como parámetro. <br>
     * <b>post: </b> Se retornó el elemento del árbol que corresponde al modelo o null si no existe ninguno.
     * @param modelo Descripción del elemento que se va a buscar en el árbol. Debe contener por lo menos la información mínima necesaria para que el método de comparación del
     *        nodo pueda establecer una relación de orden
     * @return E elemento del árbol que corresponde al modelo o null si no existe ninguno
     */
    public T buscar( T modelo )
    {
        return ( raiz != null ) ? raiz.buscar( modelo ) : null;
    }

    /**
     * Devuelve los elementos del árbol en inorden. <br>
     * <b>post: </b> Se retornó el iterador con los elementos del árbol en inorden.
     * @return Iterador con los elementos del árbol en inorden
     */
    public Iterador<T> inorden( )
    {
        IteradorSimple<T> resultado = new IteradorSimple<T>( peso );
        if( raiz != null )
        {
            raiz.inorden( resultado );
        }
        return resultado;
    }

    /**
     * Devuelve la altura del árbol. <br>
     * <b>post: </b> Se retornó la altura del árbol.
     * @return Altura del árbol
     */
    public int darAltura( )
    {
        return ( raiz != null ) ? raiz.darAltura( ) : 0;
    }

    /**
     * Devuelve el peso del árbol AVL. <br>
     * <b>post: </b> Se retornó el peso del árbol.
     * @return Peso del árbol AVL
     */
    public int darPeso( )
    {
        return peso;
    }

    /**
     * Devuelve el elemento mayor del árbol AVL. <br>
     * <b>post: </b> Se retornó el elemento mayor del árbol AVL o null si el árbol está vacio.
     * @return Elemento mayor del árbol AVL o null si el árbol está vacio
     */
    public T darMayor( )
    {
        return ( raiz != null ) ? raiz.darMayor( ) : null;
    }

    /**
     * Devuelve el elemento menor del árbol AVL. <br>
     * <b>post: </b> Se retornó el elemento menor del árbol AVL o null si el árbol está vacio.
     * @return Elemento menor del árbol AVL o null si el árbol está vacio
     */
    public T darMenor( )
    {
        return ( raiz != null ) ? raiz.darMenor( ) : null;
    }
    
    
    public Arbol2_4<T> darArbol2_4()
    {
        return ( raiz != null ) ? raiz.darArbol2_4( ) : null;
    }
    
    public static void main( String[] args )
    {
        ArbolRojoNegro<Integer> arbol= new ArbolRojoNegro<Integer>(); 
        
        try
        {
            arbol.insertar(new Integer(1000));
            arbol.insertar(new Integer(0));
            arbol.insertar(new Integer(2000));
                        
            NodoRojoNegro<Integer> raiz= arbol.darRaiz();
            System.out.println("PRUEBA 1"); 
            arbol.imprimirNodo(raiz); 
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho());
            
            arbol.insertar(new Integer(-1000));
            raiz= arbol.darRaiz();
            System.out.println("\nPRUEBA 2"); 
            arbol.imprimirNodo(raiz); 
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            
            arbol.insertar(new Integer(500));
            raiz= arbol.darRaiz();
            System.out.println("\nPRUEBA 3"); 
            arbol.imprimirNodo(raiz); 
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            
            
            arbol.insertar(new Integer(-2000));
            raiz= arbol.darRaiz();
            System.out.println("\nPRUEBA 4"); 
            arbol.imprimirNodo(raiz); 
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            
            arbol.insertar(new Integer(-800));
            raiz= arbol.darRaiz();
            System.out.println("\nPRUEBA 5"); 
            arbol.imprimirNodo(raiz); 
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            
            arbol.insertar(new Integer(3000));
            raiz= arbol.darRaiz();
            System.out.println("\nPRUEBA 6"); 
            arbol.imprimirNodo(raiz); 
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho().darHijoDerecho());
            
            arbol.insertar(new Integer(1500));
            raiz= arbol.darRaiz();
            System.out.println("\nPRUEBA 7"); 
            arbol.imprimirNodo(raiz); 
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho().darHijoDerecho());
            
            
            arbol.insertar(new Integer(3500));
            raiz= arbol.darRaiz();
            
            System.out.println("\nPRUEBA 8");
            
            arbol.imprimirNodo(raiz); 
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());                        
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho().darHijoDerecho());
            //arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoDerecho());  
            
            arbol= new ArbolRojoNegro<Integer>();
            System.out.println("\nPRUEBA 9");
            arbol.insertar(new Integer(1000));
            arbol.insertar(new Integer(100));
            arbol.insertar(new Integer(2000));
            arbol.insertar(new Integer(3000));
            arbol.insertar(new Integer(50));
            arbol.insertar(new Integer(1500));
            arbol.insertar(new Integer(1400));
            arbol.insertar(new Integer(1600));
            raiz= arbol.darRaiz();
            arbol.imprimirNodo(raiz);
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoIzquierdo());
            
            System.out.println("\nPRUEBA 10");
            arbol.insertar(new Integer(1800));
                       
            raiz= arbol.darRaiz();
            arbol.imprimirNodo(raiz);
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoDerecho());            
            //arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoIzquierdo());
            
            
            System.out.println("\nPRUEBA 11");
            arbol.insertar(new Integer(1900));
                       
            raiz= arbol.darRaiz();
            arbol.imprimirNodo(raiz);
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoDerecho());            
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoIzquierdo());
            
            System.out.println("\nPRUEBA 12");
            //arbol.insertar(new Integer(1450));
            //arbol.insertar(new Integer(1350));
            arbol.insertar(new Integer(0));
                       
            raiz= arbol.darRaiz();
            arbol.imprimirNodo(raiz);
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            //arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoDerecho());
            //arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoDerecho());            
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoIzquierdo());
            
            System.out.println("\nPRUEBA 13");            
            arbol.insertar(new Integer(-10));
                       
            raiz= arbol.darRaiz();
            arbol.imprimirNodo(raiz);
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoIzquierdo());            
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoDerecho());            
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoIzquierdo());
            
            
            System.out.println("\nPRUEBA 14");            
            arbol.insertar(new Integer(-20));
                       
            raiz= arbol.darRaiz();
            arbol.imprimirNodo(raiz);
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoIzquierdo());            
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoDerecho());            
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoIzquierdo());
            
            System.out.println("\nPRUEBA 15");            
            arbol.insertar(new Integer(1450));
            arbol.insertar(new Integer(60));
                       
            raiz= arbol.darRaiz();
            arbol.imprimirNodo(raiz);
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoIzquierdo());            
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoDerecho());            
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoIzquierdo());
            
            System.out.println("\nPRUEBA 15");            
            arbol.insertar(new Integer(1460));            
                       
            raiz= arbol.darRaiz();
            arbol.imprimirNodo(raiz);
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho().darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoIzquierdo());            
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoDerecho());            
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoIzquierdo());
            
            /*System.out.println("\nPRUEBA 11");
            arbol.eliminar(new Integer(2000));
                       
            raiz= arbol.darRaiz();
            arbol.imprimirNodo(raiz);
            arbol.imprimirNodo(raiz.darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoDerecho());
            arbol.imprimirNodo(raiz.darHijoIzquierdo().darHijoIzquierdo().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo());
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoDerecho());
            //arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoDerecho());            
            arbol.imprimirNodo(raiz.darHijoDerecho().darHijoIzquierdo().darHijoIzquierdo());*/
            
        }
        catch( ExisteException e )
        {           
            e.printStackTrace();
        }
        /*catch( NoExisteException e )
        {            
            e.printStackTrace();
        } */
    }
    
    
    private void imprimirNodo(NodoRojoNegro<T> nodo)
    {
        if(nodo.darColor()== NodoRojoNegro.NEGRO)
        {
            System.out.println("Nodo con elemento "+nodo.darRaiz()+" de color NEGRO");
        }
        else
        {
            System.out.println("Nodo con elemento "+nodo.darRaiz()+" de color ROJO");
        }
    }
}
