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

package uniandes.cupi2.cupEbay.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupEbay.mundo.CupEbay;
import uniandes.cupi2.cupEbay.mundo.ProductoNoExisteException;
import uniandes.cupi2.cupEbay.mundo.VendedorNoExisteException;
import uniandes.cupi2.cupEbay.mundo.VendedorYaExisteException;
import uniandes.cupi2.cupEbay.mundo.YaExisteProductoConMismoIdException;

/**
 * Esta es la clase usada para verificar que los métodos de la clase CupEbay estén correctamente implementados
 */
public class CupEbayTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se harán las pruebas
     */
    private CupEbay cupEbay;

    //-----------------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva CupEbay vacía
     *  
     */
    private void setupEscenario1( )
    {
        cupEbay = new CupEbay( );
        try 
        {
			cupEbay.registrarVendedor("Diego_aven", "dieguito", "avediego@hotmail.com");
			cupEbay.registrarVendedor("Kathy", "hiro", "cataws2000@hotmail.com");
		}
        catch (VendedorYaExisteException e) 
		{
			fail();
		}
        String des = "Ipod nano negro de 4 GB con cargador y cable de transeferencia";
        String cat = "reproductores, mp3, ipod";
        String des2 = "Guitarra espanola con autografo de Joan Manuel Serrat";
        String cat2 = "Guitarras, instrumentos, Joan Manuel Serrat, autografos";
        String des3 = "muñeca inflable";
        String cat3 = "juguetes, porno, inflable";
        try 
        {
			cupEbay.agregarProducto("Ipod nano", "Diego_aven", 0, des, "ipod_123", 10, 400000, cat);
			cupEbay.agregarProducto("Guitarra acustica", "Kathy", 1, des2, "guitarra_123", 1, 2000000, cat2);
			cupEbay.agregarProducto("muñeca", "Diego_aven", 0, des3, "muñeca_123", 5, 200000, cat3);
		} 
        catch (YaExisteProductoConMismoIdException e) 
        {
			fail();
		}
        catch (VendedorNoExisteException e) 
		{
			fail();
		}        
    }

    /**
     * Prueba 1
     */
    public void testCupEbay( )
    {
        setupEscenario1( );
        
        try 
        {
			assertEquals(cupEbay.darProducto("guitarra_123").darNombre(), "Guitarra acustica");
			assertEquals(cupEbay.darProducto("ipod_123").darNombre(), "Ipod nano");
		} 
        catch (ProductoNoExisteException e) 
        {
        	fail();
		}        
        assertEquals(cupEbay.darDisponibles(), 16);
    }
    
    public void testRegistrarVendedor()
    {
    	setupEscenario1( ); 
    	try 
    	{
			cupEbay.registrarVendedor("Mat", "catica", "arakiz@hotmail.com");
			assertEquals(cupEbay.darVendedor("Mat").darEMail(),"arakiz@hotmail.com");
			assertEquals(cupEbay.darVendedor("Mat").darClave(), "catica");
		}
    	catch (VendedorYaExisteException e) 
    	{
    		fail();
		}
    	catch (VendedorNoExisteException e) 
		{
    		fail();
		}        
    }
    
    public void testAgregarProducto()
    {
    	setupEscenario1();
    	String des = "procesador intel core dual 1.7 GHz, memoria DDR 1 GB, disco duro 120 GB, unidad de Dvd/RW";
        String cat = "computadores, laptop, PC, portatiles, dell";
    	try 
    	{
			cupEbay.agregarProducto("Dell dimension 5200", "Kathy", 0, des, "lapTop_123", 10, 3500000, cat );
			assertNotNull(cupEbay.darProducto("lapTop_123"));
		}
    	catch (YaExisteProductoConMismoIdException e1) 
    	{
    		fail();
		}	
    	catch (ProductoNoExisteException e) 
    	{
    		fail();
		} 
    	catch (VendedorNoExisteException e) 
		{
			fail();
		}
    }
    
    public void testBuscarPorCategoria()
    {
    	
    }
    
    public void testBuscarPorPalabra()
    {
    	
    }
    
    public void testBuscarPorVendedor()
    {
    	
    }
    
    public void testReportarProducto()
    {
    	setupEscenario1();
    	
    	try 
    	{
			cupEbay.reportarProducto("muñeca_123");
			assertEquals(cupEbay.darMaliciosos(), 1);
	    	assertEquals(cupEbay.darProducto("muñeca_123").darReportado(), true);
		} 
    	catch (ProductoNoExisteException e) 
    	{
			fail();
		}    	
    }
    
    public void testComprarProducto()
    {
    	
    } 

}