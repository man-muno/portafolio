/**
 * 
 */
package uniandes.cupi2.cupEbay.mundo;

import java.io.Serializable;

import uniandes.cupi2.collections.lista.Lista;
import uniandes.cupi2.collections.tablaHashing.TablaHashing;

/**
 * @author Owner
 *
 */
public class Vendedor< T extends Comparable<? super T >> implements Serializable
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------



    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -4070759575843341374L;

	private T nombre;
	
	private String clave;
	
	private String eMail;
	
	private TablaHashing<String, Producto> tablaProductos;
	
	private Lista<Producto> listaProductos;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     *  
     */
    public Vendedor(T nom, String pass, String em )
    {
    	nombre = nom;
    	clave = pass;
    	eMail = em;
    	tablaProductos = new TablaHashing<String, Producto>();
    	listaProductos = new Lista<Producto>();
    	verificarInvariante();
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
    public T darNombre()
    {
    	return nombre;
    }
    
    public String darClave()
    {
    	return clave;
    }
    
    public String darEMail()
    {
    	return eMail;
    }
    
    public TablaHashing darTabla()
    {
    	return tablaProductos;
    }
    
    public Lista darLista()
    {
    	return listaProductos;
    }
    
    public void agregarPro(String id, Producto p)
    {
    	tablaProductos.agregar(id, p);
    	listaProductos.agregar(p);
    }
    //-----------------------------------------------------------------
    // Invariante
    //-----------------------------------------------------------------

    private void verificarInvariante()
    {
    	assert nombre != null: "El nombre debe estar inicializado";
    	assert clave != null: "La clave debe estar inicializada";
    	assert eMail != null: "El e-Mail debe estar inicializado";
    	assert tablaProductos != null: "La tabla de productos debe estar inicializada";
    	assert listaProductos != null: "La lista de productos debe estar inicializada";
    }

}
