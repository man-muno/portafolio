/**
 * 
 */
package uniandes.cupi2.cupEbay.mundo;

import uniandes.cupi2.collections.lista.Lista;

/**
 * @author Mateo
 *
 */
public interface ICupEbay 
{	
	public void agregarProducto(String nombre, String nombreVendedor, int estado, String descripcion, String id, int unidades, int costo, String categorias) throws YaExisteProductoConMismoIdException, VendedorNoExisteException;
	
	public Lista buscarPorPalabras(String palabras);
	
	public Lista buscarPorVendedor(String nombre);
	
	public Lista buscarPorCategorias(String Categorias);
	
	public Producto darProducto(String id) throws ProductoNoExisteException;
	
	public boolean comprarProducto(String nombre) throws ProductoSinUnidadesException;
	
	public void reportarProducto(String id) throws ProductoNoExisteException;
	
	public int darDisponibles();
	
	public int darAgotados();
	
	public int darMaliciosos();
	
	public Lista darLista();
	
	public Vendedor darVendedor(String nombrevendedor) throws VendedorNoExisteException;
	
	public void sumarDisponibles(int n);
	
	public void sumarAgotados();
	
	public void sumarMaliciosos();
	
	public void registrarVendedor(String nom, String pass, String em) throws VendedorYaExisteException;
}
