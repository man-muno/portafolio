/**
 * 
 */
package uniandes.cupi2.cupEbay.interfaz;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import uniandes.cupi2.collections.lista.Lista;

/**
 * @author Owner
 *
 */
public class PanelSignIn extends JPanel 
{

	private JLabel jLabel = null;
	private JTextField jTextField = null;
	private JLabel jLabel1 = null;
	private JPasswordField jPasswordField = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private InterfazCupEbay inter = null;


	/**
	 * @param ebay 
	 * 
	 */
	public PanelSignIn(InterfazCupEbay ebay) {
		super();
		// TODO Auto-generated constructor stub
		inter = ebay;
		initialize();
	}
	
	public PanelSignIn() {
		super();
		// TODO Auto-generated constructor stub
		
		initialize();
	}

	/**
	 * This method initializes this
	 * @param ebay 
	 * 
	 * @return void
	 */
	private void initialize() 
	{
		
		jLabel1 = new JLabel();
		jLabel1.setText("Password");
		jLabel = new JLabel();
		jLabel.setText("Usuario");
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(2);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		gridLayout.setColumns(3);
		this.setLayout(gridLayout);
		this.setSize(400, 100);
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sign in", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
		this.add(jLabel, null);
		this.add(getJTextField(), null);
		this.add(getJButton(), null);
		this.add(jLabel1, null);
		this.add(getJPasswordField(), null);
		this.add(getJButton1(), null);
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
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Sign in");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					String nom = getJTextField().getText();
					String clav = getJPasswordField().getText();
					if(nom == null || nom.equals(""))
					{
						inter.mosAdv("Para ingresar debe poner su nombre de usuario", false);
						
					}					
					else if(clav == null || clav.equals(""))
					{
						inter.mosAdv("Para ingresar debe poner su contraseña", false);
					}
					else
					{
						Lista l = inter.signIn(nom, clav);
						if(l != null)
						{
							DialogoProductosVendedor dpv = new DialogoProductosVendedor(l, inter, nom);
							dpv.setLocationRelativeTo( getParent()  );
							dpv.setVisible( true );
						}
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Log in");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					DialogoRegistroVendedor ventana = new DialogoRegistroVendedor(inter);
					ventana.setLocationRelativeTo( getParent()  );
			        ventana.setVisible( true );
				}
			});
		}
		return jButton1;
	}

}
