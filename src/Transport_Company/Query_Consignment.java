package Transport_Company;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Query_Consignment extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private BufferedImage image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Query_Consignment dialog = new Query_Consignment(true);
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
	 * @throws IOException 
	 */
	public Query_Consignment(boolean bill) throws ClassNotFoundException, SQLException, IOException {
		image = ImageIO.read(getClass().getResource("/splash.jpg"));
		setIconImage(image);
		CompanyDAO com = new CompanyDAO();
		if(!bill)
			setTitle("Query Consignment");
		else
			setTitle("Show Bill");
		setBounds(100, 100, 437, 177);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblEnterConsignmentId = new JLabel("Enter Consignment Id");
			lblEnterConsignmentId.setBounds(10, 31, 127, 14);
			contentPanel.add(lblEnterConsignmentId);
		}
		
		textField = new JTextField();
		textField.setBounds(122, 28, 197, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String consignmentid = textField.getText();
						try {
							if(com.if_consignment_exists(consignmentid)){
							Display_Con dialog = new Display_Con(consignmentid,bill);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
							dispose();
							}else{
								JOptionPane.showMessageDialog(null, " The Consignment doesn't exist");
							}
						} catch (Exception e) {
							e.printStackTrace();
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
