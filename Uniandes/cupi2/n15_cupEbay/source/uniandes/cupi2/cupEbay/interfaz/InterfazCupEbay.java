/**
 * 
 */
package uniandes.cupi2.cupEbay.interfaz;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFrame;

import uniandes.cupi2.collections.lista.Lista;
import uniandes.cupi2.cupEbay.mundo.CupEbay;
import uniandes.cupi2.cupEbay.mundo.IFabrica;
import uniandes.cupi2.cupEbay.mundo.PasswordInvalidoException;
import uniandes.cupi2.cupEbay.mundo.VendedorNoExisteException;
import uniandes.cupi2.cupEbay.mundo.VendedorYaExisteException;
import uniandes.cupi2.cupEbay.mundo.YaExisteProductoConMismoIdException;

import java.awt.GridLayout;

/**
 * @author Owner
 *
 */
public class InterfazCupEbay extends JFrame 
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta del archivo de donde se carga automáticamente el estado modelo del mundo
     */
    private final static String RUTA_ARCHIVO = "./data/cupEbay.data";
    private final static String FAB = "./data/fabricas.properties";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private CupEbay cupEbay;
    private IFabrica fab;
	private JPanel jContentPane = null;
	private PanelExtension panelExtension = null;

	private PanelSignIn panelSignIn = null;

	private PanelBuscar panelBuscar = null;

	/**
	 * @throws HeadlessException
	 */
	public InterfazCupEbay() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param gc
	 */
	public InterfazCupEbay(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public InterfazCupEbay(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param title
	 * @param gc
	 */
	public InterfazCupEbay(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() 
	{
		this.setSize(500, 265);
		this.setContentPane(getJContentPane());
		this.setTitle("CupEBay");
		this.setLocationRelativeTo(null);
		
		Properties propiedades = new Properties();
		
		try
		{
			FileInputStream is = new FileInputStream(new File(FAB));
			propiedades.load(is);
			is.close();
		}
		catch (Exception e)
		{
			String s = "Formato invalido";
        	JOptionPane.showMessageDialog( this, s, "Error", JOptionPane.ERROR_MESSAGE );
		}
		try 
		{
			fab = ( IFabrica)Class.forName( propiedades.getProperty( "fabrica1") ).newInstance( );
			cupEbay = (CupEbay) fab.crearCupiPal();
		} 
		catch (InstantiationException e1) 
		{
			String s = "Error al cargar la fabrica" + e1.getMessage();
        	JOptionPane.showMessageDialog( this, s, "Error", JOptionPane.ERROR_MESSAGE );
		} 
		catch (IllegalAccessException e1) 
		{
			String s = "Error al cargar la fabrica" + e1.getMessage();
        	JOptionPane.showMessageDialog( this, s, "Error", JOptionPane.ERROR_MESSAGE );
		} 
		catch (ClassNotFoundException e1) 
		{
			String s = "Error al cargar la fabrica" + e1.getMessage();
        	JOptionPane.showMessageDialog( this, s, "Error", JOptionPane.ERROR_MESSAGE );
		}
		
        // Carga la clase principal del mundo
		cargarCup();		
	}
	
	private void cargarCup()
	{
		try
        {
        	File archivo = new File( RUTA_ARCHIVO );
            
        	if (archivo.exists())
        	{
        		ObjectInputStream os = new ObjectInputStream(new FileInputStream(archivo));  		 
        		cupEbay =  (CupEbay) os.readObject( );
        		os.close( );
        	}
        	else
        	{
        		FileOutputStream fs = new FileOutputStream(archivo);
       		 	ObjectOutputStream oos = new ObjectOutputStream(fs );
                oos.writeObject( cupEbay );
                oos.close( );
        	}
        	
        }
        catch (FileNotFoundException e) 
        {
        	String s = "Error, el archvo no se encontro";
        	JOptionPane.showMessageDialog( this, s, "Error", JOptionPane.ERROR_MESSAGE );
		} 
        catch (IOException e) 
        {
        	String s = "Error cuando se trata de serializar el archivo"; 
        	JOptionPane.showMessageDialog( this, s, "Error", JOptionPane.ERROR_MESSAGE );
		
		} 
        catch (ClassNotFoundException e)
		{
			String s = "Error, no hay acceso al archivo";
			JOptionPane.showMessageDialog( this, s, "Error", JOptionPane.ERROR_MESSAGE );
		}
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelExtension(), java.awt.BorderLayout.SOUTH);
			jContentPane.add(getPanelSignIn(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getPanelBuscar(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
    /**
     * Método que se llama cuando se cierra la ventana principal.
     */
    public void dispose( )
    {
        try
        {
        	try
            {
                
       		 	File archivo = new File(RUTA_ARCHIVO);
       		 	FileOutputStream fs = new FileOutputStream(archivo);
       		 	ObjectOutputStream oos = new ObjectOutputStream(fs );
                oos.writeObject( cupEbay );
                oos.close( );
            }
            catch( IOException e )
            {                
                throw new IOException( "Error al salvar: " + e.getMessage( ) );
            }            
            super.dispose( );
        }
        catch( Exception e )
        {
            setVisible( true );
            int respuesta = JOptionPane.showConfirmDialog( this, "Problemas salvando la información:\n" + e.getMessage( ) + "\n¿Quiere cerrar el programa sin salvar?", "Error", JOptionPane.YES_NO_OPTION );
            if( respuesta == JOptionPane.YES_OPTION )
            {
                super.dispose( );
            }
        }
    }

    public void mosAdv(String resultado, boolean b) 
	{
		if(b)
		{
			JOptionPane.showMessageDialog( this, resultado, "Info", JOptionPane.INFORMATION_MESSAGE );
		}
		//String resultado = "Debe seleccionar una opcion primero y/o llenar todos los campos" + "\n" + "Los campos que deben tener solo numeros no pueden tener letras";
		else
			JOptionPane.showMessageDialog( this, resultado, "Advertencia", JOptionPane.WARNING_MESSAGE );
	}
    
    public Lista signIn(String nom, String passwordCliente)
    {
    	try 
    	{
			return cupEbay.darProductosVendedor(nom, passwordCliente);
		} 
    	catch (VendedorNoExisteException e) 
    	{
			mosAdv(e.getMessage(), false);
		} 
    	catch (PasswordInvalidoException e) 
    	{
    		mosAdv(e.getMessage(), false);
		}
		return null;
    }
    public void logIn(String nom, String pass, String em)
    {
    	try 
    	{
			cupEbay.registrarVendedor(nom, pass, em);
			mosAdv("Usuario registrado exitosamente", true);
		} 
    	catch (VendedorYaExisteException e) 
    	{
    		mosAdv(e.getMessage() + ". Eliga otro nombre!!", false);
		}
    }
    
    public void crearDialogoAgregarProducto(String nv)
    {
    	DialogoRegistroProducto ventana = new DialogoRegistroProducto(this, nv);
		ventana.setLocationRelativeTo( getParent()  );
        ventana.setVisible( true );    	
    }
    
    public void agregarProducto(String nombre, String nombreVendedor, int estado, String descripcion, String id, int unidades, int costo, String categorias)
    {
    	try 
    	{
			cupEbay.agregarProducto(nombre, nombreVendedor, estado, descripcion, id, unidades, costo, categorias);
			mosAdv("Producto agregado exitosamente", true);
		} 
    	catch (YaExisteProductoConMismoIdException e) 
    	{
    		mosAdv(e.getMessage(), false);
		}
    	catch (VendedorNoExisteException e)
    	{
    		mosAdv(e.getMessage(), false);
		}
    }
	
    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------

	/**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = cupEbay.metodo1();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = cupEbay.metodo2();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }


    //-----------------------------------------------------------------
    // Main
    //-----------------------------------------------------------------

	/**
	 * This method initializes panelExtension	
	 * 	
	 * @return uniandes.cupi2.cupEbay.interfaz.PanelExtension	
	 */
	private PanelExtension getPanelExtension() {
		if (panelExtension == null) {
			panelExtension = new PanelExtension(this);
		}
		return panelExtension;
	}

	/**
	 * This method initializes panelSignIn	
	 * 	
	 * @return uniandes.cupi2.cupEbay.interfaz.PanelSignIn	
	 */
	private PanelSignIn getPanelSignIn() {
		if (panelSignIn == null) {
			panelSignIn = new PanelSignIn(this);
		}
		return panelSignIn;
	}

	/**
	 * This method initializes panelBuscar	
	 * 	
	 * @return uniandes.cupi2.cupEbay.interfaz.PanelBuscar	
	 */
	private PanelBuscar getPanelBuscar() {
		if (panelBuscar == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			gridLayout.setHgap(10);
			gridLayout.setVgap(10);
			gridLayout.setColumns(2);
			panelBuscar = new PanelBuscar();
			panelBuscar.setLayout(gridLayout);
		}
		return panelBuscar;
	}

	/**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazCupEbay interfaz = new InterfazCupEbay();
        interfaz.setVisible( true );
    }



}
