package Transport_Company;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Add_Employee extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Add_Employee dialog = new Add_Employee();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Add_Employee() {
		setBounds(100, 100, 485, 299);
		setTitle("Adding New Employee");
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblEnterId = new JLabel("Enter ID");
			contentPanel.add(lblEnterId, "2, 2, right, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 2, fill, default");
			textField.setColumns(10);
		}
		{
			JLabel lblEnterName = new JLabel("Enter Name");
			contentPanel.add(lblEnterName, "2, 4, right, default");
		}
		{
			textField_1 = new JTextField();
			contentPanel.add(textField_1, "4, 4, fill, default");
			textField_1.setColumns(10);
		}
		{
			JLabel lblEnterAddress = new JLabel("Enter Address");
			contentPanel.add(lblEnterAddress, "2, 6, right, default");
		}
		{
			textField_2 = new JTextField();
			contentPanel.add(textField_2, "4, 6, fill, default");
			textField_2.setColumns(10);
		}
		{
			JLabel lblEnterMobileNo = new JLabel("Enter Mobile No.");
			contentPanel.add(lblEnterMobileNo, "2, 8, right, default");
		}
		{
			textField_3 = new JTextField();
			contentPanel.add(textField_3, "4, 8, fill, default");
			textField_3.setColumns(10);
		}
		{
			JLabel lblEnterEmailid = new JLabel("Enter EmailID");
			contentPanel.add(lblEnterEmailid, "2, 10, right, default");
		}
		{
			textField_5 = new JTextField();
			contentPanel.add(textField_5, "4, 10, fill, default");
			textField_5.setColumns(10);
		}
		{
			JLabel lblEnterBranchid = new JLabel("Enter BranchId");
			contentPanel.add(lblEnterBranchid, "2, 12, right, default");
		}
		{
			textField_4 = new JTextField();
			contentPanel.add(textField_4, "4, 12, fill, default");
			textField_4.setColumns(10);
		}
		{
			JLabel lblEnterPassword = new JLabel("Enter Password");
			contentPanel.add(lblEnterPassword, "2, 14, right, default");
		}
		{
			passwordField_1 = new JPasswordField();
			contentPanel.add(passwordField_1, "4, 14, fill, default");
		}
		{
			JLabel lblReenterPassword = new JLabel("Re-enter Password ");
			contentPanel.add(lblReenterPassword, "2, 16, right, default");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "4, 16, fill, default");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id = textField.getText();
						String name = textField_1.getText();
						String address = textField_2.getText();
						String mob  = textField_3.getText();
						String email = textField_5.getText();
						String branchId = textField_4.getText();
						String password = String.valueOf(passwordField.getPassword());
						String password_1 = String.valueOf(passwordField_1.getPassword());
						if(password.equals(password_1)){
						StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
						CompanyDAO com;
						try{
						com = new CompanyDAO();
						if(com.if_branch_exists(branchId)){
						com.insert_employee(id, name, address, mob, email, branchId, encryptor.encryptPassword(password));
						JOptionPane.showMessageDialog(null, "Successfully Added "+name +" as an Employee");
						dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Error", "Branch Doesn't Exist", JOptionPane.ERROR_MESSAGE);
						}
						}catch(Exception ex){
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error", "Connection not possible", JOptionPane.ERROR_MESSAGE);
						}
						}else{
							JOptionPane.showMessageDialog(null, "Error", "Passowrds didn't match", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
