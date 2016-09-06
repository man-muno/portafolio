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
import javax.swing.JPasswordField;
import javax.swing.JButton;

/**
 * @author Mateo
 *
 */
public class DialogoRegistroVendedor extends JDialog {

	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JTextField jTextField = null;
	private JLabel jLabel1 = null;
	private JPasswordField jPasswordField = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField1 = null;
	private JButton jButton = null;
	private InterfazCupEbay inter;

	/**
	 * @throws HeadlessException
	 */
	public DialogoRegistroVendedor() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param inter
	 * @throws HeadlessException
	 */
	public DialogoRegistroVendedor(InterfazCupEbay in) throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		inter = in;
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws HeadlessException
	 */
	public DialogoRegistroVendedor(Frame arg0, boolean arg1)
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
	public DialogoRegistroVendedor(Frame arg0, String arg1)
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
	public DialogoRegistroVendedor(Frame arg0, String arg1, boolean arg2)
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
	public DialogoRegistroVendedor(Frame arg0, String arg1, boolean arg2,
			GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws HeadlessException
	 */
	public DialogoRegistroVendedor(Dialog arg0, boolean arg1)
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
	public DialogoRegistroVendedor(Dialog arg0, String arg1)
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
	public DialogoRegistroVendedor(Dialog arg0, String arg1, boolean arg2)
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
	public DialogoRegistroVendedor(Dialog arg0, String arg1, boolean arg2,
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
		this.setSize(600, 125);
		this.setTitle("Registrar usuario");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("E-Mail");
			jLabel1 = new JLabel();
			jLabel1.setText("Password");
			jLabel = new JLabel();
			jLabel.setText("Nombre usuario");
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			gridLayout.setHgap(10);
			gridLayout.setVgap(10);
			gridLayout.setColumns(4);
			jContentPane = new JPanel();
			jContentPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jContentPane.setLayout(gridLayout);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJPasswordField(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJTextField1(), null);
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
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
		}
		return jPasswordField;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
		}
		return jTextField1;
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
					String nom = getJTextField().getText();
					String clav = getJPasswordField().getText();
					String mail = getJTextField1().getText();
					if(nom == null || nom.equals("") )
					{
						inter.mosAdv("Para ingresar debe poner un nombre de usuario", false);
					}					
					else if(clav == null || clav.equals(""))
					{
						inter.mosAdv("Para ingresar debe poner una contraseña", false);
					}					
					else if(mail == null || mail.equals(""))
					{
						inter.mosAdv("Para ingresar debe poner un e-mail valido", false);
					}
					else
					{
						inter.logIn(nom, clav, mail);
						dispose();
					}
				}
			});
		}
		return jButton;
	}

}
