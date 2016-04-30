package Transport_Company;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
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
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginManager extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;
	private BufferedImage image;
	private CompanyDAO comp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginManager dialog = new LoginManager();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public LoginManager() throws IOException, ClassNotFoundException, SQLException {
		comp = new CompanyDAO();
		image = ImageIO.read(getClass().getResource("/manager.jpg"));
		setBounds(100, 100, 486, 259);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setIconImage(image);
		setTitle("Manager Login");
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblUsername = new JLabel("UserId");
			contentPanel.add(lblUsername, "2, 2, right, default");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 2, fill, default");
			textField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			contentPanel.add(lblPassword, "2, 6, right, default");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "4, 6, fill, default");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegister = new JButton("Register");
				btnRegister.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							CompanyDAO com = new CompanyDAO();
							if(com.getmanagerCount()==0){
							RegisterManager dialog = new RegisterManager();
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							dispose();
							}else{
								JOptionPane.showMessageDialog(null,"At most one manager is possible");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				{
					JButton btnRemove = new JButton("Remove");
					btnRemove.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
								CompanyDAO com = new CompanyDAO();
								if(com.getmanagerCount()!=0){
									com.delete_manager();
									JOptionPane.showMessageDialog(null, "Successfully removed from the database");
								}else{
									JOptionPane.showMessageDialog(null, "No manager in the database");
								}
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					});
					buttonPane.add(btnRemove);
				}
				buttonPane.add(btnRegister);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String username = textField.getText();
						String pass = String.valueOf(passwordField.getPassword());
						try {
							if(comp.check_Password_Manager(username, pass)){
							String bid = comp.get_branch_d(username);
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										Manager_Activity frame = new Manager_Activity(bid);
										frame.setVisible(true);
										dispose();
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							}else{
								JOptionPane.showMessageDialog(null, "Error", "Invalid Login", JOptionPane.ERROR_MESSAGE);
								textField.setText("");
								passwordField.setText("");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
