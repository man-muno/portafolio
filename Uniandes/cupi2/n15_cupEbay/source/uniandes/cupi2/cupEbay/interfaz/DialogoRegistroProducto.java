/**
 * 
 */
package uniandes.cupi2.cupEbay.interfaz;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JDialog;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import uniandes.cupi2.cupEbay.mundo.Producto;

/**
 * @author Mateo
 *
 */
public class DialogoRegistroProducto extends JDialog {

	private JPanel jContentPane = null;
	private InterfazCupEbay inter;
	private JLabel jLabel = null;
	private JTextField jTextField = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField2 = null;
	private JLabel jLabel3 = null;
	private JTextField jTextField3 = null;
	private JLabel jLabel4 = null;
	private JTextField jTextField4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JTextField jTextField6 = null;
	private JButton jButton = null;
	private JComboBox jComboBox = null;
	private JComboBox jComboBox1 = null;
	private String nomVen;

	/**
	 * @throws HeadlessException
	 */
	public DialogoRegistroProducto() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public DialogoRegistroProducto(InterfazCupEbay in, String nv) throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		inter = in;
		nomVen = nv;
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws HeadlessException
	 */
	public DialogoRegistroProducto(Frame arg0, boolean arg1)
			throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws HeadlessException
	 */
	public DialogoRegistroProducto(Frame arg0, String arg1)
			throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws HeadlessException
	 */
	public DialogoRegistroProducto(Frame arg0, String arg1, boolean arg2)
			throws HeadlessException {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public DialogoRegistroProducto(Frame arg0, String arg1, boolean arg2,
			GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public DialogoRegistroProducto(Dialog arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws HeadlessException
	 */
	public DialogoRegistroProducto(Dialog arg0, boolean arg1)
			throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws HeadlessException
	 */
	public DialogoRegistroProducto(Dialog arg0, String arg1)
			throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws HeadlessException
	 */
	public DialogoRegistroProducto(Dialog arg0, String arg1, boolean arg2)
			throws HeadlessException {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws HeadlessException
	 */
	public DialogoRegistroProducto(Dialog arg0, String arg1, boolean arg2,
			GraphicsConfiguration arg3) throws HeadlessException {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(700, 220);
		this.setTitle("Regisrar Producto");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Categorias (separadas por `,´ )");
			jLabel5 = new JLabel();
			jLabel5.setText("Costo ( $ )");
			jLabel4 = new JLabel();
			jLabel4.setText("Unidades");
			jLabel3 = new JLabel();
			jLabel3.setText("Id (nombre_la opcion seleccionada)");
			jLabel2 = new JLabel();
			jLabel2.setText("Descripcion");
			jLabel1 = new JLabel();
			jLabel1.setText("Estado");
			jLabel = new JLabel();
			jLabel.setText("Nombre");
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(5);
			gridLayout.setHgap(10);
			gridLayout.setVgap(10);
			gridLayout.setColumns(4);
			jContentPane = new JPanel();
			jContentPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jContentPane.setLayout(gridLayout);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJTextField2(), null);
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(getJTextField3(), null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(getJTextField4(), null);
			jContentPane.add(getJComboBox1(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(getJTextField6(), null);
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
		}
		return jTextField2;
	}

	/**
	 * This method initializes jTextField3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
		}
		return jTextField3;
	}

	/**
	 * This method initializes jTextField4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			
		}
		return jTextField4;
	}

	/**
	 * This method initializes jTextField6	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
		}
		return jTextField6;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Registrar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					String resultado = "Debe seleccionar una opcion primero y/o llenar todos los campos" + "\n" + "Algunos de los campos deben tener solo numeros, no pueden tener letras";
					try
					{
						String nom = getJTextField().getText();
						String des = getJTextField2().getText();
						int uni = Integer.parseInt(getJTextField3().getText());
						int cos = Integer.parseInt(getJTextField4().getText());
						String cat = getJTextField6().getText();
						int est =  (getJComboBox().getSelectedItem().equals("NUEVO"))? Producto.NUEVO: ( (getJComboBox().getSelectedItem().equals("USADO"))? Producto.USADO: -1 ) ;
						if(est == -1)
							inter.mosAdv(resultado, false);
						String id = (String) getJComboBox1().getSelectedItem();
						if(id.equals(PanelBuscar.SELE))
							inter.mosAdv(resultado, false);
						
						inter.agregarProducto(nom, nomVen, est, des, nom+id, uni, cos, cat);
						dispose();
					}
					catch (Exception e1)
					{												
						inter.mosAdv(resultado, false);
						//System.out.print(e1.getMessage());
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		if (jComboBox == null) {
			jComboBox = new JComboBox();
			jComboBox.addItem(PanelBuscar.SELE);
			jComboBox.addItem("NUEVO");
			jComboBox.addItem("USADO");
		}
		return jComboBox;
	}

	/**
	 * This method initializes jComboBox1	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.addItem(PanelBuscar.SELE);
			jComboBox1.addItem("_" + (int)(Math.random()*1000));
			jComboBox1.addItem("_" + (int)(Math.random()*1000));
			jComboBox1.addItem("_" + (int)(Math.random()*1000));
			jComboBox1.addItem("_" + (int)(Math.random()*1000));
		}
		return jComboBox1;
	}

}
