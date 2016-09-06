/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoArbolB.java,v 1.11 2007/02/11 16:59:43 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - 25/02/2006
 * Autor: Pablo Barvo - 25/02/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.collections.arbolB;

import uniandes.cupi2.collections.iterador.IteradorException;
import uniandes.cupi2.collections.iterador.IteradorSimple;
import uniandes.cupi2.collections.lista.Lista;

/**
 * Nodo del �rbol B
 * @param <T> Tipo de datos que contiene cada nodo del �rbol
 */
public class NodoArbolB<T extends Comparable<? super T>>
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    
    /**
     * Orden del �rbol B (n�mero de nodos hijos asociados) 
     */
    private int orden; 
    
    /**
     * Ra�ces del nodo. raices<=orden-1
     */
    private Lista<T> raices;
    
    /**
     * Hijos del nodo. hijos.darLongitud()<=orden
     */
    private Lista<NodoArbolB<T>> hijos;
    
    

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del nodo con el primer elemento
     * @param elOrden Orden del �rbol al que pertenece el nodo. elOrden>0
     * @param hijo1 Elemento a agregar al nodo
     */
    public NodoArbolB( int elOrden, T hijo1 )
    {
        orden= elOrden;
        raices= new Lista(elOrden-1);
        raices.agregar(hijo1); 
        hijos= null; 
    }
    
    /**
     * Constructor del nodo con el primer elemento
     * @param elOrden Orden del �rbol al que pertenece el nodo. elOrden>0
     * @param hijo1 Elemento a agregar al nodo
     */
    public NodoArbolB( int elOrden, Lista<T> lasRaices )
    {
        orden= elOrden;
        raices= lasRaices;        
        hijos= null; 
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Devuelve la ra�z indicada. <br>
     * @param raiz La posici�n de la ra�z que se desea consultar
     * <b>post: </b> Se retorn� la ra�z indicada del nodo.
     * @return Ra�z indicada
     */
    public T darRaiz(int raiz )
    {
        return raices.darElemento(raiz);
    }

    /**
     * Devuelve el Hijo indicado del nodo.<br>
     * <b>post: </b> Se retorn� el hijo 1 del nodo.
     * @param hijo El n�mero del hijo que se desea consultar 
     * @return Hijo indicado del nodo
     */
    public NodoArbolB<T> darHijo(int hijo )
    {
        return hijos.darElemento(hijo);
    }

    
    /**
     * Indica si el nodo es una hoja. <br>
     * <b>post: </b> Se retorn� true si el nodo es una hoja o false de lo contrario. Un nodo es una hoja si no tiene hijos.
     * @return True si es hoja, False si no
     */
    public boolean esHoja( )
    {
        return hijos==null;
    }

    /**
     * Informa si un elemento se encuentra presente en el �rbol B cuya ra�z es este nodo. <br>
     * <b>pre: </b> modelo!=null. <br>
     * <b>post: </b> Se retorno un elemento que corresponde al modelo dado. Si ning�n elemento corresponde al elemento se retorna null.
     * 
     * @param modelo Modelo del elemento que se desea buscar
     * @return Elemento encontrado o null si no lo encuentra
     */
    public T buscar( T modelo )
    {               
        // Compara con el primer elemento
        int resultado;
        T raizActual;
        
        T raizSiguiente;
     
        for(int cont=0; cont<raices.darLongitud(); cont++)
        {
            raizActual= raices.darElemento(cont); 
            resultado= modelo.compareTo( raizActual );
            
            raizSiguiente= (cont+1<raices.darLongitud()) ? raices.darElemento(cont+1) : null; 
                        
            if(resultado== 0)
            {
                return raizActual; 
            }
            else if(resultado<0)
            {
                NodoArbolB<T> hijo= (hijos==null) ? null : hijos.darElemento(cont); 
                return (hijo==null) ? null : hijo.buscar(modelo); 
            }
            else if(raizSiguiente==null)
            {
                NodoArbolB<T> hijo= (hijos==null || cont+1>=hijos.darLongitud()) ? null : hijos.darElemento(cont+1);
                return (hijo==null) ? null : hijo.buscar(modelo); 
            }
        }
        return null; 
    }

    /**
     * Calcula la altura del �rbol 2-3 cuya ra�z es este nodo. <br>
     * <b>post: </b> Se retorn� la altura del �rbol.
     * @return Devuelve la altura del �rbol
     */
    public int darAltura( )
    {        
        if( esHoja( ) )
        {
            return 1;
        }
        else
        {
            NodoArbolB<T> hijo= hijos.darElemento(0);
            return hijo.darAltura( ) + 1;
        }
    }

    /**
     * Agrega un nuevo elemento al �rbol B cuya ra�z es este nodo y retorna el nodo que corresponde a la nueva ra�z del �rbol. <br>      
     * <b>post: </b> Se insert� un elemento en el �rbol si este no exist�a previamente en la estructura.     
     * @param obj != null
     * @return Nodo nuevo
     * @throws ExisteException El elemento ya existe en el �rbol
     */
    public NodoArbolB<T> insertar( T obj ) throws ExisteException
    {
        Retorno ret = new Retorno( );
        if( auxInsertar( obj, ret ) )
        {
            NodoArbolB<T> nodo = new NodoArbolB<T>( orden, ret.val );
            
            nodo.hijos= new Lista<NodoArbolB<T>>(orden); 
            nodo.hijos.agregar(ret.hijo1);
            nodo.hijos.agregar(ret.hijo2);
            return nodo;
        }
        else
        {
            return this;
        }
    }

    /**
     * Elimina un valor dado del �rbol B cuya ra�z es este nodo, y retorna una referencia al nodo ra�z de la estructura resultante. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se elimin� un elemento del �rbol si este exist�a en la estructura.
     * 
     * @param obj El objeto a ser eliminado
     * @return Nodo resultado de la operaci�n
     * @throws NoExisteException Excepci�n generada si el elemento especificado no existe
     */
    public NodoArbolB<T> eliminar( T obj ) throws NoExisteException
    {
        return auxEliminar( obj ) ? hijos.darElemento(0) : this;
    }

    /**
     * Devuelve los elementos del �rbol en inorden. <br>
     * <b>pre: </b> resultado!=null. <br>
     * <b>post: </b> Se retorno un vector con el recorrido en inorden del �rbol.
     * 
     * @param resultado Vector con los elementos del �rbol en inorden
     */
    public void inorden( IteradorSimple<T> resultado ) throws IteradorException
    {
        if(hijos!=null)
        {
            for(int cont=0; cont<hijos.darLongitud(); cont++)
            {
                NodoArbolB<T> hijo= hijos.darElemento(cont); 
                hijo.inorden(resultado); 
                
                if(cont!=hijos.darLongitud()-1)
                {
                    resultado.agregar(raices.darElemento(cont));
                }
            }
        }
        else
        {
            for(int cont=0; cont<raices.darLongitud(); cont++)
            {
                resultado.agregar(raices.darElemento(cont));     
            }
            
        }               
    }

    // -----------------------------------------------------------------
    // Operaciones Auxiliares
    // -----------------------------------------------------------------

    /**
     * Inserta el objeto en el �rbol, sin aumentar de altura. Si debe aumentar de altura retorna true, y en la estructura de retorno, env�a los dos �rboles 2-3 que deben
     * subir, y la ra�z del nuevo nivel. <br>
     * <b>post: </b> Se insert� un elemento en el �rbol si este no exist�a previamente en la estructura. Se retorn� true si se tuvo que aumentar la altura o false de lo
     * contrario.
     * 
     * @param elemento Elemento a insertar
     * @param ret Retorno de la operaci�n
     * @return True si debe aumentar altura, False si no
     * @throws ExisteException Excepci�n generada si el elemento especificado ya existe en el �rbol
     */
    private boolean auxInsertar( T elemento, Retorno ret ) throws ExisteException
    {
        // Busca si el elemento ya existe en el nodo actual
        int resultado;
        T raiz; 
        boolean mayor= true; 
        int cont=0; 
        while(cont<raices.darLongitud() && mayor)
        {
           raiz= raices.darElemento(cont); 
           
           resultado= elemento.compareTo(raiz); 
        
           //Verifica que el elemento que llega no se encuentre en el nodo
           if(resultado==0)
           {
               throw new ExisteException( "El elemento ya existe en el �rbol" );
           }
           else if(resultado==-1)
           {
               mayor= true; 
           }
           
           cont++;
        }
                
        // Verifica que el elemento que llega no se encuentre en el nodo
        if( esHoja( ) )
        {
            return insHoja( elemento, ret );
        }
        else        
        {
            NodoArbolB<T> hijo= hijos.darElemento(cont-1); 
            return hijo.auxInsertar( elemento, ret ) ? subirInfoN( ret, cont-1 ) : false;
        }
    }

    /**
     * Inserta un elemento en una hoja del �rbol. Si debe aumentar de altura retorna true, y en la estructura de retorno env�a los dos nodos en los que se dividi� la hoja y la
     * ra�z del nuevo nivel. <br>
     * <b>post: </b> Se insert� un elemento en el �rbol si este no exist�a previamente en la estructura. Si retorn� true si se debe aumentar la altura o false de lo contrario.
     * 
     * @param elemento Elemento a insertar
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False sino
     */
    private boolean insHoja( T elemento, Retorno ret )
    {
        T raiz; 
        
        if( raices.darLongitud()<orden )
        {
            // Caso 1: hay espacio en el nodo: no hay que aumentar un nivel.
            // Basta con ordenar las ra�ces                        
            insertarRaizOrdenada(elemento);             
            return false;
        }
        else
        {
            // Caso 2: no hay espacio en el nodo y se debe partir
            
            // Se busca la ra�z que debe subir
            
            // Verifica el n�mero de ra�ces es impar
            if((orden-1)%2!=0)
            {
                insHojaNumeroRaicesImpar(elemento, ret); 
            }
            else
            {
                insHojaNumeroRaicesPar(elemento, ret); 
            }                        
            return true;
        }
    }
    
    
    private void insHojaNumeroRaicesImpar(T elemento, Retorno ret)
    {
        //Siempre sube el elemento de la mitad
        int mitad= (orden-1)/2; 
        T raiz= raices.darElemento(mitad);
        ret.val = raiz;
        raices.eliminar(mitad);
        
        //Se crean dos nodos cada uno con la mitad de las ra�ces que quedan
        Lista<T> raices1= sublistaRaices(0,mitad+1);
        Lista<T> raices2= sublistaRaices(mitad+1, orden-1);
                                
        NodoArbolB<T> hijo1= new NodoArbolB(orden, raices1);
        NodoArbolB<T> hijo2= new NodoArbolB(orden, raices2);
                
        ret.hijo1= hijo1;
        ret.hijo2= hijo2;
        
        //Se busca el lugar en donde debe insertarse el elemento
        if(elemento.compareTo(raiz)<0)
        {
            hijo1.insertarRaizOrdenada(elemento);
        }
        else
        {
            hijo2.insertarRaizOrdenada(elemento);
        }
                                
    }
    
    private void insHojaNumeroRaicesPar(T elemento, Retorno ret)
    {   
        int mitad= ((orden-1)/2)-1;
        
        //Se busca la posici�n en la que se debe insertar el elemento
        int pos=0; 
        
        boolean encontroPos= false; 
        while(pos<raices.darLongitud() && !encontroPos)
        {
            T raiz= raices.darElemento(pos); 
            
            if(elemento.compareTo(raiz)<0)
            {
                encontroPos= false; 
            }
            else
            {
                pos++; 
            }
        }
        
        //De acuerdo a la posici�n en la que se debe insertar el elemento, se determina la ra�z que debe subir
        T raiz;
        if(pos<=mitad)
        {
            //Sube la ra�z que est� en la mitad
            raiz= raices.darElemento(mitad);
            raices.eliminar(mitad); 
            ret.val = raiz;
            
            Lista<T> raices1= sublistaRaices(0,mitad+1);                                                
            NodoArbolB<T> hijo1= new NodoArbolB(orden, raices1);
            hijo1.insertarRaizOrdenada(elemento);            
            eliminarRaices(0,mitad+1); 
                                    
            NodoArbolB<T> hijo2= this;
                                                
            ret.hijo1= hijo1;
            ret.hijo2= hijo2; 
        }
        else if(pos==mitad+1)
        {
            //Sube el elemento nuevo
            ret.val = elemento; 
            
            Lista<T> raices1= sublistaRaices(0,mitad+1);                                                
            NodoArbolB<T> hijo1= new NodoArbolB(orden, raices1);
            hijo1.insertarRaizOrdenada(elemento);            
            eliminarRaices(0,mitad+1); 
                                    
            NodoArbolB<T> hijo2= this;
             
            ret.hijo1= hijo1;
            ret.hijo2= hijo2;                                        
        }
        else
        {
            //Sube la ra�z que se encuentra en mitad+1
            raiz= raices.darElemento(mitad+1);
            raices.eliminar(mitad+1);
            ret.val = raiz;
            
            Lista<T> raices2= sublistaRaices(mitad+1, orden);                                                
            NodoArbolB<T> hijo2= new NodoArbolB(orden, raices2);
            hijo2.insertarRaizOrdenada(elemento);            
            eliminarRaices(mitad+1, orden); 
                                    
            NodoArbolB<T> hijo1= this;
                                    
            ret.hijo1= hijo1;
            ret.hijo2= hijo2;                            
        }                
    }

    /**
     * <b>pre:</b> raices.darLongitud()<orden-1
     * @param elemento
     * @return La posici�n en la que fue insertada el elemento
     */
    private int insertarRaizOrdenada(T elemento)
    {
        int pos=0; 
        
        boolean encontroPos= false; 
        while(pos<raices.darLongitud() && !encontroPos)
        {
            T raiz= raices.darElemento(pos); 
            
            if(elemento.compareTo(raiz)<0)
            {
                encontroPos= false; 
            }
            else
            {
                pos++; 
            }
        }
        
        raices.insertar(elemento, pos);
        
        return pos; 
    }
    
    private Lista<T> sublistaRaices(int posInicio, int posFin)
    {
        Lista<T> raices1= new Lista(orden-1);        
                
        for(int cont=posInicio; cont<posFin; cont++)
        {
            T elem= raices.darElemento(cont);             
            raices1.agregar(elem); 
                        
        }
        
        return raices1; 
    }
    
    private Lista<NodoArbolB<T>> sublistaHijos(int posInicio, int posFin)
    {
        Lista<NodoArbolB<T>> hijos1= new Lista(orden);        
                
        for(int cont=posInicio; cont<posFin; cont++)
        {
            NodoArbolB<T> nodo= hijos.darElemento(cont);             
            hijos1.agregar(nodo); 
                        
        }        
        return hijos1; 
    }
    
    private void eliminarRaices(int posInicio, int posFin)
    {                        
        for(int cont=posInicio; cont<posFin; cont++)
        {
            raices.eliminar(cont);                                                
        }        
    }
    
    private void eliminarHijos(int posInicio, int posFin)
    {                        
        for(int cont=posInicio; cont<posFin; cont++)
        {
            hijos.eliminar(cont);                                                
        }        
    }
    
    /**
     * La inserci�n se hizo sobre el primer sub�rbol, vienen subiendo en la estructura de retorno un elemento y dos sub�rboles. <br>
     * <b>post: </b> Se retorn� true si se debe aumentar la altura del �rbol o false de lo contrario.
     * 
     * @param ret Variable de retorno
     * @return True si debe aumentar la altura, False si no
     */
    private boolean subirInfoN( Retorno ret, int numeroHijo )
    {
        if(raices.darLongitud()<orden-1)
        {
            //Hay campo en este nodo: solo hay que reorganizar
            int posicion= insertarRaizOrdenada(ret.val);
            
            hijos.insertar(ret.hijo1, posicion);
            hijos.insertar(ret.hijo2, posicion+1);
            return false; 
        }
        else
        {
            int mitad= (orden-1)/2;
            if((orden-1)%2!=0)
            {
                
                T temp= raices.darElemento(mitad); //Sube
                
                Lista<NodoArbolB<T>> hijosNodo= sublistaHijos(mitad+1, hijos.darLongitud());                                         
                Lista<T> raicesNodo= sublistaRaices(mitad+1, raices.darLongitud());
                NodoArbolB<T> nodo = new NodoArbolB<T>( orden, raicesNodo ); 

                //La inserci�n se hizo antes de la mitad. El elemento de ret debe quedar en el nodo actual
                if(numeroHijo<=mitad)
                {                                                                                                
                    nodo.hijos= hijosNodo;                     
                    eliminarHijos(mitad, hijos.darLongitud());
                    eliminarRaices(mitad, raices.darLongitud()); 
                                                            
                    hijos.insertar(ret.hijo1, numeroHijo);
                    hijos.insertar(ret.hijo2, numeroHijo+1);
                    
                    insertarRaizOrdenada(ret.val);
                    
                    ret.val= temp; 
                    ret.hijo1= this;
                    ret.hijo2= nodo;
                }                                
                else
                {                                                            
                    int posInsercion= nodo.insertarRaizOrdenada(ret.val); 
                    hijosNodo.insertar(ret.hijo1, posInsercion);
                    hijosNodo.insertar(ret.hijo1, posInsercion+1); 
                    nodo.hijos= hijosNodo; 
                    
                    eliminarHijos(mitad, hijos.darLongitud());
                    eliminarRaices(mitad, raices.darLongitud());                                                                                     
                }
                
                    
                ret.val= temp; 
                ret.hijo1= this;
                ret.hijo2= nodo;
               
                
            }
            else
            {
                Lista<NodoArbolB<T>> hijosNodo;                                         
                Lista<T> raicesNodo= sublistaRaices(mitad, raices.darLongitud());
                NodoArbolB<T> nodo = new NodoArbolB<T>( orden, raicesNodo );
                
                T temp= null; 
                
                if(numeroHijo<mitad) //Sube el elemento que se encuentra en la posici�n mitad
                {
                    temp= raices.darElemento(mitad-1); //Sube
                    
                    hijosNodo= sublistaHijos(mitad, hijos.darLongitud());    
                                        
                    nodo.hijos= hijosNodo;
                                                             
                    eliminarHijos(mitad, hijos.darLongitud());
                    eliminarRaices(mitad, raices.darLongitud());
                    
                    hijos.insertar(ret.hijo1, numeroHijo);
                    hijos.insertar(ret.hijo2, numeroHijo+1);
                    
                    insertarRaizOrdenada(ret.val);
                    
                    ret.val= temp;
                }
                else if(numeroHijo==mitad) //Sube el mismo elemento que est� en ret
                {
                    hijosNodo= sublistaHijos(mitad+1, hijos.darLongitud());
                    hijosNodo.insertar(ret.hijo2, 0); 
                    nodo.hijos= hijosNodo;                                                                                                    
                    
                    eliminarHijos(mitad+1, hijos.darLongitud());
                    eliminarRaices(mitad, raices.darLongitud());
                    
                    hijos.insertar(ret.hijo1, mitad);
                }
                
                 
                ret.hijo1= this;
                ret.hijo2= nodo;               
            }
            
            return true;
        }

    }
    
   
    /**
     * Elimina un elemento del �rbol B. Retorna true si el �rbol resultado ha perdido un nivel. <br>
     * <b>pre: </b> obj!=null. <br>
     * <b>post: </b> Se retorn� si la altura del �rbol disminuye o false de lo contrario.
     * 
     * @param obj Elemento a eliminar
     * @return True si la altura cambi�, False si no
     * @throws NoExisteException El elemento especificado no existe en el nodo
     */
    private boolean auxEliminar( T obj ) throws NoExisteException
    {
        int resultado=1;
        int pos=0; 
        T raiz; 
        
        while(pos<raices.darLongitud() && resultado!=-1 && resultado!=0)
        {
            raiz= raices.darElemento(pos); 
            resultado=obj.compareTo(raiz);            
            pos++; 
        }
                        
        if( resultado == 0 )
        {
            if( esHoja( ) )
            {
                if( raices.darLongitud()==1 )
                {
                    return true;
                }
                else
                {
                    raices.eliminar(pos-1);                     
                    return false;
                }
            }
            else
            {
                NodoArbolB<T> hijo= hijos.darElemento(pos);                
                T menor = hijo.calcularMenorElem( );
                raices.eliminar(pos-1);
                insertarRaizOrdenada(menor); //TODO se podr�a insertar en la primera posici�n                
                return hijo.auxEliminar( menor ) ? restaurarN(pos) : false;
            }
        }        
        else if( resultado < 0 && hijos != null )
        {
            NodoArbolB<T> hijo= hijos.darElemento(pos-1);
            return hijo.auxEliminar( obj ) ? restaurarN(pos-1) : false;
        }       
        else
            throw new NoExisteException( "El elemento especificado no existe en el �rbol" );

    }

    /**
     * Retorna el menor elemento del �rbol 2-3 cuya ra�z es este nodo. <br>
     * <b>post: </b> Se retorn� el menor elemento del �rbol cuya ra�z es este nodo.
     * 
     * @return Menor elemento del �rbol
     */
    private T calcularMenorElem( )
    {
        NodoArbolB<T> aux = this;
        while( aux.hijos != null )
            aux = aux.darHijo(0);
        return aux.raices.darElemento(0);
    }
    
    /**
     * Se ha eliminado un elemento del hijo 1, y por esta raz�n se ha perdido altura. Debe rebalancear la informaci�n del nodo actual. <br>
     * <b>post: </b> Se retorn� true si es necesario rebalancear el �rbol o false de lo contrario
     * 
     * @return True si debe continuar rebalanceando, False si no
     */
    private boolean restaurarN(int numeroHijo)
    {
        NodoArbolB<T> hijo0= numeroHijo-1>=0? hijos.darElemento(numeroHijo-1): null;
        NodoArbolB<T> hijo1= hijos.darElemento(numeroHijo); 
        NodoArbolB<T> hijo2= hijos.darLongitud()>numeroHijo+1? hijos.darElemento(numeroHijo+1): null;        
        
        if(numeroHijo==0)
        {
            
           
            hijo1.insertarRaizOrdenada(raices.darElemento(numeroHijo));
            
            raices.eliminar(numeroHijo); 
            raices.insertar(hijo2.darRaiz(numeroHijo), numeroHijo);
            
            hijo1.hijos.eliminar(numeroHijo+1);
            
            
            hijo1.hijos.insertar(hijo2.darHijo(numeroHijo), numeroHijo+1); 
            hijo2.hijos.eliminar(numeroHijo);    
            
                                                                                    
        }
                
        //Raiz numeroHijo es diferente de null
        if(numeroHijo<=raices.darLongitud()-1)
        {
            
            hijo1.raices.eliminar(0); // h2.r1 = r2;                            
            hijo1.raices.insertar(raices.darElemento(numeroHijo), 0);                
            
            
            hijo1.hijos.eliminar(numeroHijo); //h2.h2 = h3.h1;    
            hijo1.hijos.insertar(hijo2.darHijo(0), numeroHijo);
            
            
            raices.eliminar(numeroHijo);  //r2 = h3.r1;                              
            raices.insertar(hijo2.darRaiz(0),numeroHijo);
            hijo2.raices.eliminar(0); 
            
            hijo2.hijos.eliminar(0); //h3.h1 = h3.h2; 
            
           if(hijo2.raices.darLongitud()>0) //La ra�z 2 exist�a- El hijo2 tenia m�s de una ra�z
           {
               return false; 
           }
           else
           {
               restaurarN(numeroHijo+1); 
           }                                                                                    
        }
        else if(hijo0!=null) //La inserci�n no se realiz� por el primer hijo
        {
            int numeroRaices0= hijo0.raices.darLongitud(); 
            
            if(numeroRaices0>1) //Hay m�s de una ra�z
            {
                
                hijo1.raices.eliminar(0); //h3.r1 = r2; 
                hijo1.raices.insertar(raices.darElemento(numeroHijo-1),0);
                
                raices.eliminar(numeroHijo-1); //r2 = h2.r3;                                                    
                raices.insertar(hijo0.darRaiz(numeroRaices0-1),numeroHijo-1);
                
                hijo0.raices.eliminar(numeroRaices0-1); //h2.r3 = null; 
                
                hijo1.hijos.eliminar(1); //h3.h2 = h3.h1;
                hijo1.hijos.insertar(hijos.darElemento(0),1);
                
                hijo1.hijos.eliminar(0); //h3.h1 = h2.h4;
                hijo1.hijos.insertar(hijo0.hijos.darElemento(numeroRaices0),0);                    
                hijo0.hijos.eliminar(numeroRaices0); //h2.h4 = null;                                                                                 
                return false; 
            }
            else
            {
                hijo0.raices.eliminar(1); //h2.r2 = r2; 
                hijo0.raices.insertar(raices.darElemento(numeroHijo-1), 1);
                
                hijo0.hijos.eliminar(2); //h2.h3 = h3.h1; 
                hijo0.hijos.insertar(hijo1.darHijo(0), 2);
                
                raices.eliminar(numeroHijo-1); //r2 = null;
                
                hijos.eliminar(numeroHijo);//h3 = null;     
                
                if(numeroHijo==1)
                {
                    return true; 
                }
               
                 
                   
            }
        }
        
        return false;
            
    }

    // -----------------------------------------------------------------
    // Clases Privadas
    // -----------------------------------------------------------------

    /**
     * Contiene dos nodos de �rbol 2-3, que vienen desplaz�ndose como parte del proceso de modificaci�n
     */
    private class Retorno
    {
        // -----------------------------------------------------------------
        // Atributos
        // -----------------------------------------------------------------
        /**
         * Valor que va subiendo
         */
        private T val;

        /**
         * Nodo izquierdo desplaz�ndose
         */
        private NodoArbolB<T> hijo1;
        
        /**
         * Nodo izquierdo desplaz�ndose
         */
        private NodoArbolB<T> hijo2;

        
        // -----------------------------------------------------------------
        // Constructores
        // -----------------------------------------------------------------

        /**
         * Constructor del retorno
         */
        private Retorno( )
        {
            val = null;            
            hijos= null; 

        }

        /**
         * Constructor del retorno con par�metros        
         * @param pVal Elemento
         * @param hIzq Lista de nodos izquierdo
         * @param hDer Lista de nodos derechos
         */
        private Retorno( T pVal, NodoArbolB<T> h1, NodoArbolB<T> h2)
        {
            val = pVal;
            hijo1 = h1;
            hijo2 = h2;
            
        }
    }

}
