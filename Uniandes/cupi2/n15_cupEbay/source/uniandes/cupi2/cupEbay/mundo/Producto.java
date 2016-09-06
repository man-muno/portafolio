/**
 * 
 */
package uniandes.cupi2.cupEbay.mundo;

import java.io.Serializable;

import uniandes.cupi2.collections.lista.Lista;

/**
 * @author Owner
 *
 */
public class Producto implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3328486667208502103L;

	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	public final static int NUEVO = 0;

	public final static int USADO = 1;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	private String nombre;
	
	private String nombreVendedor;
	
	private int estado;
	
	private String descripcion;
	
	private String id;
	
	private int unidades;
	
	private int costo;
	
	private Lista<Categoria> categorias;
	
	private boolean reportado;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     *  
     */
    public Producto( String nom, String nomVen, int est, String des, String id1, int uni, int cos, Lista lista )
    {
    	nombre = nom;
    	nombreVendedor = nomVen;
    	estado = est;
    	descripcion = des;
    	id = id1;
    	unidades = uni;
    	costo = cos;
    	categorias = lista;
    	reportado = false;    	
    	
    	verificarInvariante();
    }

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------
    public String darNombre()
    {
    	return nombre;
    }
    
    public String darNombreVendedor()
    {
    	return nombreVendedor;
    }
    
    public int darEstado()
    {
    	return estado;
    }
    
    public String darDescripcion()
    {
    	return descripcion;
    }
    
    public String darId()
    {
    	return id;
    }

    public int darUnidades()
    {
    	return unidades;
    }

    public Lista darCategorias()
    {
    	return categorias;
    }
    
    public boolean darReportado()
    {
    	return reportado;
    }
    
    public int darCosto()
    {
    	return costo;
    }

    public void cambiarReportado()
    {
    	reportado = true;
    }
    
    public void descontarUnidad()
    {
    	unidades--;
    }

    //-----------------------------------------------------------------
    // Invariante
    //-----------------------------------------------------------------

    private void verificarInvariante()
    {
    	assert nombre != null: "El nombre debe estar inicializado";
    	assert nombreVendedor != null: "El nombre del vendedor debe estar inicializado";
    	assert estado == NUEVO || estado == USADO: "El estado debe estar inicializado";
    	assert descripcion != null: "La descripcion debe estar inicializada";
    	assert id != null: "El id debe estar inicializado";
    	assert unidades >= 0: "Las unidades debe ser positivas";
    	assert categorias != null: "Las categorias deben estar inicializadas";
    }
}
