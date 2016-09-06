package uniandes.cupi2.cupEbay.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import uniandes.cupi2.collections.iterador.Iterador;
import uniandes.cupi2.collections.lista.Lista;
import uniandes.cupi2.cupEbay.mundo.Producto;


public class DialogoProductosVendedor extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando que se le asigna al botón cerrar
     */
    private static final String CERRAR = "CERRAR";
	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Interfaz principal.
     */
    private InterfazCupEbay padre;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------
    /**
     * Cuadro de texto de las preguntas
     */
    private JTextArea txtReco;
    /**
     * Scroll Pane de las preguntas
     */
    private JScrollPane scroll;
    /**
     * boton que cierra el dialogo
     */
    private JButton btnCerrar;

	private String nomVen;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    /**
     * Constructor del panel
     * @param recorrido Interfaz principal CoviCupi2
     */
    public DialogoProductosVendedor( Lista r, InterfazCupEbay inter, String ven )
    {
    	// Construir la forma del diálogo
    	nomVen = ven;
    	this.setTitle("Productos del vendedor: " + ven);
        padre = inter;
        setLayout( new BorderLayout( ) );
        setPreferredSize( new Dimension( 300, 330 ) );
        this.setSize(new java.awt.Dimension(350,300));
        
        txtReco = new JTextArea(  );
        String t = "Productos puestos en venta por " + ven + " :" + "\n" + "\n";
        for(int i = 0; i < r.darLongitud(); i++)
        {
        	t += "\n";
            Producto p = (Producto) r.darElemento( i );
            t += "Nombre: " + p.darNombre() + "\n";
            String e = (p.darEstado()==0)? "Nuevo": "Usado";
            t += "Estado: " + e + "\n";
            t += "Descripcion: " + p.darDescripcion() + "\n";
            t += "Identificador: " + p.darId() + "\n";
            t += "Unidades: " + p.darUnidades() + "\n";
            t += "Costo: " + p.darCosto() + "\n";
            String cate = "";
            Iterador iter = p.darCategorias().darIterador();
            while(iter.haySiguiente())
            {
            	cate += iter.darSiguiente() + ","; 
            }
            t += "Categorias: " + cate + "\n";
        }
		txtReco.setText( t );
		txtReco.setEditable(false);
        txtReco.setLineWrap( true );
        txtReco.setWrapStyleWord( true );
        
        scroll = new JScrollPane(txtReco);
        
        add(scroll, BorderLayout.CENTER);
        
        btnCerrar = new JButton( "Agregar Producto" );
        btnCerrar.setActionCommand( CERRAR );
        btnCerrar.addActionListener( this );
        add( btnCerrar, BorderLayout.SOUTH );
    }
				
    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------


	/**
     * Se ejecuta una acción cuando se hace click en el boton de cerrar
     * @param e El evento del click en un botón- evento != null
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( CERRAR ) )
        {
            padre.crearDialogoAgregarProducto(nomVen);            
            this.dispose();
        }
    }
    
}
