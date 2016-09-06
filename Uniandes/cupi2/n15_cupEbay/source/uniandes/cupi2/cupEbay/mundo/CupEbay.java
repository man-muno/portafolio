/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_cupEbay
 * Autor: Mateo y Diego - 16-sep-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupEbay.mundo;
import java.io.Serializable;

import uniandes.cupi2.collections.avl.ArbolAVL;
import uniandes.cupi2.collections.lista.Lista;
import uniandes.cupi2.collections.tablaHashing.TablaHashing;


/**
 *  
 */
public class CupEbay implements ICupEbay, Serializable
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3988351056308739494L;
	public final static String PAL = "Palabras";
	public final static String CAT = "Categorias (maximo 3 separadas por `,´";
	public final static String VEN = "Vendedor";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	private TablaHashing<String, Producto> tablaProductos;
	
	private TablaHashing<String, Vendedor> tablaVendedores;
	
	private TablaHashing<String, Categoria> tablaCategorias;
	
	private Lista<Producto> listaProductos;
	
	private ArbolAVL<String> arbolPalabras;
	
	private ArbolAVL<String> arbolVendedor;
	
	private ArbolAVL<Categoria> arbolCategoria;
	
	private int unidadesDisponibles;
	
	private int reportados;
	
	private int agotados;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * @throws PersistenciaException 
     *  
     */
    public CupEbay( ) 
    {
    	reportados = 0;
    	agotados = 0;
    	unidadesDisponibles = 0;
    	tablaProductos = new TablaHashing<String, Producto>();
    	tablaVendedores = new TablaHashing<String, Vendedor>();
    	listaProductos = new Lista<Producto>();
    	arbolCategoria = new ArbolAVL<Categoria>();
    	arbolPalabras = new ArbolAVL<String>();
    	arbolVendedor = new ArbolAVL<String>();
    	tablaCategorias = new TablaHashing<String, Categoria>();
    	verificarInvariante();
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    
	public void agregarProducto(String nombre, String nombreVendedor, int estado, String descripcion, String id, int unidades, int costo, String categorias)throws YaExisteProductoConMismoIdException, VendedorNoExisteException 
	{
		
		if(tablaProductos.dar(id) != null)
		{
			throw new YaExisteProductoConMismoIdException("ya hay un producto con el mismo id");
		}
		Lista<Categoria> c = crearCategoria(categorias);
		Producto p = new Producto(nombre, nombreVendedor, estado, descripcion, id, unidades, costo, c );
		agregarProdACategoria(c, p);
		Vendedor v = tablaVendedores.dar(nombreVendedor);
		if(v == null)
		{ 
			throw new VendedorNoExisteException("el vendedor al que se le quiere agregar el producto no se encontro");
		}
		v.agregarPro(id, p);
		tablaProductos.agregar(id, p);
		listaProductos.agregar(p);
		unidadesDisponibles += unidades;
		
		// TODO agregar el producto a los arboles
	}
	
	public void registrarVendedor(String nom, String pass, String em)throws VendedorYaExisteException 
	{
		if(tablaVendedores.dar(nom) != null)
		{
			throw new VendedorYaExisteException("El vendedor con ese nombre ya existe");
		}
		Vendedor v = new Vendedor(nom,pass, em);
		tablaVendedores.agregar(nom, v);
		// TODO toca agregarlo al arbol de vendedores
	}

	public Lista buscarPorPalabras(String palabras) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Lista buscarPorVendedor(String nombre) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Lista buscarPorCategorias(String Categorias) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public Lista darLista() 
	{
		return listaProductos;
	}

	public Producto darProducto(String id) throws ProductoNoExisteException 
	{
		Producto p = tablaProductos.dar(id);
		if(p == null)
		{
			throw new ProductoNoExisteException("El producto buscado no existe");
		}
		return p;
	}

	public boolean comprarProducto(String id) throws ProductoSinUnidadesException 
	{
		boolean b = false;
		Producto p = tablaProductos.dar(id);
		if(p.darUnidades() <=0)
		{
			throw new ProductoSinUnidadesException("El producto no tiene unidades disponibles");
		}
		else
		{
			p.descontarUnidad();
			b = true;
		}
		return b;		
	}

	public void reportarProducto(String id) throws ProductoNoExisteException 
	{
		if(tablaProductos.dar(id)!= null)
		{
			tablaProductos.dar(id).cambiarReportado();
		}
		else
		{
			throw new ProductoNoExisteException("El producto que se quiere reportar no existe");
		}
		sumarMaliciosos();
	}

	public int darDisponibles() 
	{
		return unidadesDisponibles;
	}

	public int darAgotados() 
	{
		return agotados;
	}

	public int darMaliciosos() 
	{
		return reportados;
	}

	public Vendedor darVendedor(String nombreVendedor) throws VendedorNoExisteException 
	{
		Vendedor v = tablaVendedores.dar(nombreVendedor);
		if(v==null)
		{
			throw new VendedorNoExisteException("El vendedor que se busca no existe");
		}
		return v;		
	}

	public void sumarDisponibles(int n) 
	{
		unidadesDisponibles += n;
	}

	public void sumarAgotados() 
	{
		agotados++;
	}

	public void sumarMaliciosos() 
	{
		reportados++;
	}
	
	public Lista darProductosVendedor(String nom, String passwordCliente) throws VendedorNoExisteException, PasswordInvalidoException
	{
		validarInfoVendedor(nom, passwordCliente);
		Vendedor v = tablaVendedores.dar(nom);
		return v.darLista();
	}
	
	public Lista crearCategoria(String s)
	{
		Lista<Categoria> categorias = new Lista<Categoria>();
		String[] t = s.split(",");
		for(int i=0;i<t.length; i++)
    	{
			if( tablaCategorias.dar(t[i]) == null )
			{
				Categoria c = new Categoria(t[i]);
				tablaCategorias.agregar(t[i], c);
				categorias.agregar(c);				
			}					
    	}
		return categorias;
	}
	
	public void agregarProdACategoria(Lista<Categoria> c, Producto p)
	{
		for(int i=0;i<c.darLongitud(); i++)
    	{
			Categoria cat = c.darElemento(i);
			cat.agregarProdu(p);
    	}
	}
	
	private void validarInfoVendedor(String nom, String passwordCliente) throws VendedorNoExisteException, PasswordInvalidoException
	{
		Vendedor v = tablaVendedores.dar(nom);
		if(v == null)
			throw new VendedorNoExisteException("El vendedor con nombre: " + nom + " no existe en el sistema");
		if( !v.darClave().equals(passwordCliente) )
			throw new PasswordInvalidoException("El password ingresado no es valido");
	}
    
    public ArbolAVL<Categoria> darArbol()
    {
        return arbolCategoria;
    }
    
    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

    /**
     * Método para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }    

    //-----------------------------------------------------------------
    // Invariante
    //-----------------------------------------------------------------

    private void verificarInvariante()
    {
    	assert unidadesDisponibles >= 0: "Las unidades disponibles deben ser mayor o igual a cero";
    	assert reportados >= 0: "Los productos reportados deben ser mayor o igual a cero";
    	assert agotados >= 0: "Los productos agotados deben ser mayor o igual a cero";
    	assert tablaProductos != null: "La tabla de productos debe estar inicializada";
    	assert listaProductos != null: "La lista de productos debe estar inicializada";
    	assert tablaVendedores != null: "La tabla de vendedores debe estar inicializada";
    	assert arbolPalabras != null: "El arbol de productos por palabras debe estar inicializada";
    	assert arbolVendedor != null: "El arbol de productos por vendedor debe estar inicializada";
    	assert arbolCategoria != null: "El arbol de productos por categoria debe estar inicializada";
    }    
}