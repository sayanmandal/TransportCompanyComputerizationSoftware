package Transport_Company;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class StartFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BufferedImage image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public StartFrame() throws IOException {
		image = ImageIO.read(getClass().getResource("/microsoft.jpg"));
		setIconImage(image);
		setTitle("Transport Company Computerization Software");
		setResizable(false);
		URL url = getClass().getResource("/Truck.gif");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "LogIn Screen", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		setContentPane(contentPane);
		contentPane.setBackground(Color.green);
		contentPane.setLayout(null);
		
		Icon icon = new ImageIcon(url);
		JLabel lblNewLabel = new JLabel(icon);
		lblNewLabel.setBounds(0, 0, getWidth(), getHeight());
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("<html>Log In as <br>Manager</br></html>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LoginManager dialog = new LoginManager();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 70, 110, 55);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<html>Log In as <br>Employee</br></html>");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoginEmployee dialog = new LoginEmployee();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(490, 70, 103, 55);
		contentPane.add(btnNewButton_1);
		
		URL url1 = getClass().getResource("/emp.gif");
		Icon icon1 = new ImageIcon(url1);
		
		JLabel label = new JLabel("");
		label.setBounds(464, 148, icon1.getIconWidth(), icon1.getIconHeight());
		label.setIcon(icon1); 
		contentPane.add(label);
	}
}
