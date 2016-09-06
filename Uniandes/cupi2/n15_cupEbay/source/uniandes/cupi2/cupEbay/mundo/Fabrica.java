/**
 * 
 */
package uniandes.cupi2.cupEbay.mundo;

import java.io.File;
import java.io.Serializable;

/**
 * clase que representa fabrica de cupitaxi
 *
 */
public class Fabrica implements IFabrica, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7791520723559344110L;

	/**crea una nueva cupitaxi
	 * @throws Exception 
	 * @throws Exception 
	 * 
	 */
	public ICupEbay crearCupiPal() 
	{
		// TODO Auto-generated method stub
		return new CupEbay();
	}

}
