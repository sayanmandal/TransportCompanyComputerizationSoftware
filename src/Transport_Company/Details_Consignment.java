package Transport_Company;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Details_Consignment extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	public int dispatched;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Details_Consignment dialog = new Details_Consignment(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Details_Consignment(String branch) throws ClassNotFoundException, SQLException {
		CompanyDAO com= new CompanyDAO();
		setTitle("Enter Details Of Consignment");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblId = new JLabel("Id");
			contentPanel.add(lblId, "2, 2, right, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 2, fill, default");
			textField.setColumns(10);
		}
		{
			JLabel lblVolume = new JLabel("Volume");
			contentPanel.add(lblVolume, "2, 4, right, default");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "4, 4, fill, default");
			textField_1.setColumns(10);
		}
		{
			JLabel lblSenderid = new JLabel("SenderId");
			contentPanel.add(lblSenderid, "2, 6, right, default");
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2, "4, 6, fill, default");
			textField_2.setColumns(10);
		}
		{
			JLabel lblReceiverid = new JLabel("ReceiverId");
			contentPanel.add(lblReceiverid, "2, 8, right, default");
		}
		{
			textField_3 = new JTextField();
			contentPanel.add(textField_3, "4, 8, fill, default");
			textField_3.setColumns(10);
		}
		{
			JLabel lblSourceid = new JLabel("SourceId");
			contentPanel.add(lblSourceid, "2, 10, right, default");
		}
		{
			textField_4 = new JTextField();
			contentPanel.add(textField_4, "4, 10, fill, default");
			textField_4.setColumns(10);
		}
		{
			JLabel lblDestid = new JLabel("Destination Id");
			contentPanel.add(lblDestid, "2, 12, right, default");
		}
		{
			textField_5 = new JTextField();
			contentPanel.add(textField_5, "4, 12, fill, default");
			textField_5.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Next");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String id = textField.getText();
						try{
						int volume = Integer.valueOf(textField_1.getText());
						String senderid = textField_2.getText();
						String receiverid = textField_3.getText();
						String branchid = textField_4.getText();
						String destid = textField_5.getText();
						try {
							if(branch.equals(branchid)){
							if(com.if_branch_exists(branchid) && com.if_branch_exists(destid) && !com.if_consignment_exists(id)){
							com.insert_consignment(id, volume, senderid, receiverid, branchid, destid);
							if(com.is_truck_available(id, branchid)){
								int vol = (volume%500==0)?volume/500:volume/500 +1;
								com.allot_truck(vol, branchid, destid, id);
								dispatched = 1;
							}else{
								dispatched = 0;
							}
							sender_Details dialog = new sender_Details(dispatched,id,senderid,receiverid);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							dispose();
							}else{
								JOptionPane.showMessageDialog(null,"Error","The Branch Doesn't Exist or The consignment exists beforehand",JOptionPane.ERROR_MESSAGE);
							}}else{
								JOptionPane.showMessageDialog(null, "Error", "Sorry! This is not your branch", JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						}catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Error", "Error E001:Please Enter a valid Number in The Volume Field", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				{
					JButton btnCancel = new JButton("Cancel");
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							dispose();
						}
					});
					buttonPane.add(btnCancel);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
