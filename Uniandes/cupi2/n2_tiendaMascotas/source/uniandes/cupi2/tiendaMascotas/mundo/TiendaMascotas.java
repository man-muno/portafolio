/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: TiendaMascotas.java,v 1.6 2007/06/25 19:02:31 camil-ji Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_tiendaMascotas
 * Autor: Manuel Mu�oz - 08-Feb-2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.tiendaMascotas.mundo;

/**
 * Clase que representa la tienda de mascotas
 */
public class TiendaMascotas
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Indica un mensaje que se lanzar� cuando no encuentre ninguna mascota
     */
    public static final String NINGUNA = "Ninguna";

    /**
     * Nombre de la mascota 4
     */
    public static final String NOMBRE_MASCOTA_4 = "Guacamaya";

    /**
     * Nombre de la mascota 3
     */
    // TODO Definir la constante para la mascota 3: Tit�
    public static final String NOMBRE_MASCOTA_3 = "Tit�";
    /**
     * Nombre de la mascota 2
     */
    public static final String NOMBRE_MASCOTA_2 = "Boa";

    /**
     * Nombre de la mascota 1
     */
    public static final String NOMBRE_MASCOTA_1 = "Cacat�a";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Mascota que tiene la tienda. Esta es la Cacat�a
     */
    private Mascota mascota1;

    // TODO
    // Declarar los atributos necesarios para modelar las mascotas 2, 3 y 4
    // Documentar cada uno de los atributos
 
    /**
     * Mascota que tiene la tienda. Esta es la Boa.
     */    
    private Mascota mascota2;
    
    /**
     * Mascota que tiene la tienda. Esta es el Tit�.
     */
    private Mascota mascota3;
    
    /**
     * Mascota que tiene la tienda. Esta es la Guacamaya.
     */
    private Mascota mascota4;
    
    /**
     * Cantidad de veces que se ha vendido la mascota 1. Esta es la Cacat�a
     */
    private int cantidadMascotasVendidas1;

    // TODO
    // Declarar los atributos necesarios para modelar la cantidad vendida de las mascotas 2, 3 y 4
    // Documentar cada uno de los atributos
    /**
     * Cantidad de veces que se ha vendido la mascota 2. Esta es el Tit�.
     */
    private int cantidadMascotasVendidas2;
    
    /**
     * Cantidad de veces que se ha vendido la mascota 3. Esta es la Boa.
     */
    private int cantidadMascotasVendidas3;
    
    /**
     * Cantidad de veces que se ha vendido la mascota 3. Esta es el Guacamaya.
     */
    private int cantidadMascotasVendidas4;
    
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Inicializa las mascotas<br>
     * La mascota1 es Cacat�a con una edad de 2 a�os, precio m�ximo de 1.500.000, cantidad inicial de 20 y macho <br>
     * La mascota2 es Boa con una edad de 1 a�o, precio m�ximo de 2.000.000, cantidad inicial de 54 y hembra <br>
     * La mascota3 es Tit� con una edad de 5 a�os, precio m�ximo de 5.000.000, cantidad inicial de 23 y macho <br>
     * La mascota4 es Guacamaya con una edad de 3 a�os, precio m�ximo de 15.000.000, cantidad inicial de 34 y hembra <br>
     * <b> post:</b> Se cre� la tienda de mascotas con cuatro mascotas ex�ticas.
     */
    
    /**
     * no estoy segura preguntar manuel
     */
    public TiendaMascotas( )
    {
        // TODO
        // Inicializar las cuatro mascotas con los valores indicados en la documentaci�n
    	
        // Inicializa la mascota 1
        mascota1=new Mascota(NOMBRE_MASCOTA_1,2,1500000,20,Mascota.SEXO_HEMBRA);
        // Inicializa la mascota 2	
        mascota2=new Mascota(NOMBRE_MASCOTA_2,1,2000000,54,Mascota.SEXO_MACHO);
        // Inicializa la mascota 3
        mascota3=new Mascota(NOMBRE_MASCOTA_3,5,5000000,23,Mascota.SEXO_MACHO);
        // Inicializa la mascota 4
        mascota4=new Mascota(NOMBRE_MASCOTA_4,3,15000000,34,Mascota.SEXO_HEMBRA);
       
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que retorna una mascota dada su nombre.
     * @param nombreMascota Nombre de la mascota que se quiere obtener. Diferente de null
     * @return Mascota que tiene ese nombre. En caso de no encontrarla o que hayan sido vendidas todas <br>
     *         sus unidades retorna null
     */
    public Mascota obtenerMascota( String nombreMascota )
    {
        if( nombreMascota.equals( NOMBRE_MASCOTA_1 ) )
        {
            return mascota1;
        }
        // TODO
        // Completar el m�todo para las tres mascotas restantes
        if( nombreMascota.equals(NOMBRE_MASCOTA_2))
        {
        	return mascota2;
        }
        if( nombreMascota.equals(NOMBRE_MASCOTA_3))
        {
        	return mascota3;
        }
        if( nombreMascota.equals(NOMBRE_MASCOTA_4))
        {
        	return mascota4;
        }
        return null;
    }

    /**
     * Retorna la cantidad de mascotas que hay en el sistema de acuerdo al nombre de la mascota. <br>
     * Si la mascota es null, la cantidad que devuelve es cero.
     * @param nombreMascota Nombre de la mascota. Diferente de null
     * @return Entero mayor o igual a cero.
     */
    public int obtenerCantidadActual( String nombreMascota )
    {
        Mascota mascota = obtenerMascota( nombreMascota );
        if( mascota != null )
        {
            return mascota.obtenerCantidadActual( );
        }
        else
        {
            return 0;
        }
    }

    /**
     * M�todo que registra la venta de una mascota. Aumenta la cantidad de veces que se ha vendido la mascota. <br>
     * Si la cantidad actual de la mascota es 0, entonces la mascota se vuelve null
     * @param nombreMascota Nombre que tiene la mascota. Diferente de null
     * @param cantidad Cantidad de mascotas que se quieren vender. Entero mayor a cero.
     */
    public void venderMascota( String nombreMascota, int cantidad )
    {
        if( mascota1 != null && mascota1.obtenerNombre( ).equals( nombreMascota ) )
        {
            mascota1.venderMascota( cantidad );
            cantidadMascotasVendidas1 += cantidad;
            if( mascota1.obtenerCantidadActual( ) == 0 )
            {
                mascota1 = null;
            }
        }

        // TODO
        // Completar el m�todo para las tres mascotas restantes
        if( mascota2 !=null && mascota2.obtenerNombre().equals(nombreMascota))
        {
        	mascota2.venderMascota(cantidad);
        	cantidadMascotasVendidas2 += cantidad;
        	if( mascota2.obtenerCantidadActual()==0)
        	{
        		mascota2 = null;
        	}
        }
        
        if( mascota3 !=null && mascota3.obtenerNombre().equals(nombreMascota))
        {
        	mascota3.venderMascota(cantidad);
        	cantidadMascotasVendidas3 += cantidad;
        	if( mascota3.obtenerCantidadActual()==0)
        	{
        		mascota3 = null;
        	}
        }
        
        if( mascota4 !=null && mascota4.obtenerNombre().equals(nombreMascota))
        {
        
        	mascota4.venderMascota(cantidad);
        	cantidadMascotasVendidas4 += cantidad;
        	if( mascota4.obtenerCantidadActual()==0)
        	{
        		mascota4 = null;
            }
        }
    }
    /**
     * M�todo que registra la compra de una mascota. En caso que la mascota sea nula se tiene que crear <br>
     * una nueva utilizando las constantes declaradas y los valores especificados en la documentaci�n del <br>
     * m�todo constructor.
     * @param nombreMascota Nombre de la mascota que se quiere comprar. Diferente de null
     * @param cantidad Cantidad que se quiere comprar
     */
    public void comprarMascota( String nombreMascota, int cantidad )
    {
        if( nombreMascota.equals( NOMBRE_MASCOTA_1 ) )
        {
            if( mascota1 == null )
            {
                mascota1 = new Mascota( NOMBRE_MASCOTA_1, 2, 1500000, cantidad, Mascota.SEXO_MACHO );
            }
            else
            {
                mascota1.comprarMascota( cantidad );
            }
        }

        // TODO
        // Completar el m�todo para las tres mascotas restantes

        if( nombreMascota.equals( NOMBRE_MASCOTA_2 ) )
        {
            if( mascota2 == null )
            {
                mascota2 = new Mascota( NOMBRE_MASCOTA_2, 1, 2000000, cantidad, Mascota.SEXO_HEMBRA );
            }
            else
            {
                mascota2.comprarMascota( cantidad );
            }
        }
        
        if( nombreMascota.equals( NOMBRE_MASCOTA_3 ) )
        {
            if( mascota3 == null )
            {
                mascota3 = new Mascota( NOMBRE_MASCOTA_3, 5, 5000000, cantidad, Mascota.SEXO_MACHO );
            }
            else
            {
                mascota3.comprarMascota( cantidad );
            }
        }
        
        if( nombreMascota.equals( NOMBRE_MASCOTA_4) )
        {
            if( mascota4 == null )
            {
                mascota4 = new Mascota( NOMBRE_MASCOTA_4, 3, 15000000, cantidad, Mascota.SEXO_HEMBRA );
            }
            else
            {
                mascota4.comprarMascota( cantidad );
            }
        }      
             
    }

    /**
     * M�todo que informa el nombre la mascota m�s vendida. <br>
     * En caso que ninguna se haya vendido, se debe retornar el valor de la constante NINGUNA, declarada en el inicio de la clase
     * @return Nombre de la mascota que ha sido mas vendida o "Ninguna" en caso en el que no hayan mascotas suficientes para vender
     */
    
    /**
    * M�todo que informa el nombre la mascota m�s vendida. <br>
    * En caso que ninguna se haya vendido, o que la mayor venta se repita para m�s de una mascota, 
    * se debe retornar el valor de la constante NINGUNA, declarada en el inicio de la clase
    * @return Nombre de la mascota que ha sido mas vendida o "Ninguna" en caso en el que no hayan mascotas vendidas
    *  o que la mayor venta se repita para m�s de una mascota.
    */
 

    public String obtenerMascotaMasVendida( )
    {
        // TODO         
        // Completar el m�todo seg�n la documentaci�n
    	
        if(mascota1.obtenerCantidadActual()>mascota2.obtenerCantidadActual()&& mascota1.obtenerCantidadActual()>mascota3.obtenerCantidadActual()&& mascota1.obtenerCantidadActual()>mascota4.obtenerCantidadActual())
        { 
        	return NOMBRE_MASCOTA_1;
        }
        else if(mascota2.obtenerCantidadActual()>mascota1.obtenerCantidadActual()&& mascota2.obtenerCantidadActual()>mascota3.obtenerCantidadActual()&& mascota2.obtenerCantidadActual()>mascota4.obtenerCantidadActual())
        {
        	return NOMBRE_MASCOTA_2;
        }
        else if(mascota3.obtenerCantidadActual()>mascota1.obtenerCantidadActual()&& mascota3.obtenerCantidadActual()>mascota2.obtenerCantidadActual()&& mascota3.obtenerCantidadActual()>mascota4.obtenerCantidadActual())
        {
        	return NOMBRE_MASCOTA_3;
        }
        else if(mascota4.obtenerCantidadActual()>mascota1.obtenerCantidadActual()&& mascota4.obtenerCantidadActual()>mascota2.obtenerCantidadActual()&& mascota4.obtenerCantidadActual()>mascota3.obtenerCantidadActual())
        {
        	return NOMBRE_MASCOTA_4;
        }
        else
        {	
        	return NINGUNA;
        }
    }

    /**
     * El m�todo debe retornar un mensaje con las mascotas que cumplen con el criterio que el usuario escogi� en los par�metros.<br>
     * Si ninguna de las mascotas cumple con los criterios, se debe devolver el mensaje: <br>
     * <b>"Ninguna de las mascotas cumple los criterios"</b> 
     * @param sexo Sexo del cual el usuario quiere su mascota. Diferente de null y puede ser Mascota.SEXO_HEMBRA o Mascota.SEXO_MACHO
     * @param edad Entero mayor o igual a cero que representa la edad m�xima que el usuario quiere en su mascota
     * @param precio Entero mayor a cero que representa el precio m�ximo que el usuario quiere que cueste la mascota
     * @return Cadena de caracteres diferente de null, que contiene los nombres de las mascotas que <br>
     * cumplen los criterios. En caso de no haber ninguno lo informa tambi�n.
     */
    public String seleccionarMascota( String sexo, int edad, int precio )
    {
        // TODO
        // Completar el m�todo seg�n la documentaci�n
        return "Ninguna de las mascotas cumple con los criterios";
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {

        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}