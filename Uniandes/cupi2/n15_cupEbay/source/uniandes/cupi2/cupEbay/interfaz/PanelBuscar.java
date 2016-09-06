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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import uniandes.cupi2.cupEbay.mundo.CupEbay;


/**
 * @author Owner
 *
 */
public class PanelBuscar extends JPanel 
{

	public static final String SELE = "Seleccione una opcion";
	private JTextField jTextField = null;
	private JButton jButton = null;
	private JLabel jLabel = null;
	private JComboBox jComboBox = null;


	/**
	 * 
	 */
	public PanelBuscar() {
		super();
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
		jLabel = new JLabel();
		jLabel.setText("Buscar por:");
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(2);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		gridLayout.setColumns(3);
		this.setLayout(gridLayout);
		this.setSize(400, 100);
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
		this.add(jLabel, null);
		this.add(getJComboBox(), null);
		this.add(getJTextField(), null);
		this.add(getJButton(), null);
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
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Buscar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
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
			jComboBox.addItem(SELE);
			jComboBox.addItem(CupEbay.PAL);
			jComboBox.addItem(CupEbay.CAT);
			jComboBox.addItem(CupEbay.VEN);
		}
		return jComboBox;
	}

}
