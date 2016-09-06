/**
 * 
 */
package uniandes.cupi2.cupEbay.mundo;

import java.io.Serializable;

import uniandes.cupi2.collections.lista.Lista;

/**
 * @author Mateo
 *
 */
public class Categoria implements Comparable<Categoria>, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3301374181010065171L;

	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
	private String nomb;
	
	private Lista<Producto> produc;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------


	/**
	 * 
	 */
	public Categoria(String n ) 
	{
		nomb = n;
		produc = new Lista<Producto>();
	}	

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

	public String darNombre()
	{
		return nomb;
	}
	
	public Lista darListaProdu()
	{
		return produc;
	}
	
	public void agregarProdu(Producto p)
	{
		produc.agregar(p);
	}

/*	public int compareTo(Object arg0 ) 
	{
		if(nomb.compareTo(((Categoria) arg0).darNombre())>0)
    	{
    		return 1;
    	}
    	else if(nomb.compareTo(((Categoria) arg0).darNombre())<0)
    	{
    		return -1;
    	}
    	else
    	{
    		return 0;
    	}
	}
*/
    public int compareTo( Categoria arg0 )
    {
        if(nomb.compareTo(((Categoria) arg0).darNombre())>0)
        {
            return 1;
        }
        else if(nomb.compareTo(((Categoria) arg0).darNombre())<0)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
	
}
