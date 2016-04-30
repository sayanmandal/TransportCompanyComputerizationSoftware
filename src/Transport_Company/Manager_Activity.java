package Transport_Company;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Manager_Activity extends JFrame {

	private JPanel contentPane;
	private BufferedImage image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_Activity frame = new Manager_Activity(null);
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
	public Manager_Activity(String bid) throws IOException {
		setTitle("Manager Activities");
		image = ImageIO.read(getClass().getResource("/manager.jpg"));
		setIconImage(image);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 531, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Query Consignment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Query_Consignment dialog = new Query_Consignment(false);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(114, 42, 304, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblWhatDoYou = new JLabel("What Do You Want To Do?");
		lblWhatDoYou.setFont(new Font("Verdana", Font.BOLD, 16));
		lblWhatDoYou.setBounds(114, 11, 234, 20);
		contentPane.add(lblWhatDoYou);
		
		JButton btnNewButton_1 = new JButton("View Average Waiting Period OF Consignment");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Avg_Wait_Con dialog = new Avg_Wait_Con();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(114, 82, 304, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnViewIdleWaiting = new JButton("View Idle Waiting Time Of Truck");
		btnViewIdleWaiting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					idle_wait_branch dialog = new idle_wait_branch();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnViewIdleWaiting.setBounds(114, 116, 304, 23);
		contentPane.add(btnViewIdleWaiting);
		
		JButton btnViewTruckStatus = new JButton("View Truck Status");
		btnViewTruckStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					View_Truck_Status dialog = new View_Truck_Status();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnViewTruckStatus.setBounds(114, 150, 304, 23);
		contentPane.add(btnViewTruckStatus);
		
		JButton btnChangeRate = new JButton("Change Rate");
		btnChangeRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Change_Rate dialog = new Change_Rate();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnChangeRate.setBounds(114, 184, 304, 23);
		contentPane.add(btnChangeRate);
		
		JButton btnBuyTruck = new JButton("Buy Truck");
		btnBuyTruck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {try {
				Buy_Truck dialog = new Buy_Truck();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}}
		});
		btnBuyTruck.setBounds(114, 218, 304, 23);
		contentPane.add(btnBuyTruck);
		
		JButton btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Add_Employee dialog = new Add_Employee();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddEmployee.setBounds(114, 252, 304, 23);
		contentPane.add(btnAddEmployee);
		
		JButton btnViewTruckUsage = new JButton("View Truck Usage");
		btnViewTruckUsage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					View_Truck_Usage dialog = new View_Truck_Usage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnViewTruckUsage.setBounds(114, 286, 304, 23);
		contentPane.add(btnViewTruckUsage);
		
		JButton btnNewButton_2 = new JButton("Enter Details of Consignment");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Details_Consignment dialog = new Details_Consignment(bid);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(114, 320, 304, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Add Branch");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Add_Branch dialog = new Add_Branch();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(114, 354, 304, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Show Bill");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Query_Consignment dialog = new Query_Consignment(true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(114, 388, 304, 23);
		contentPane.add(btnNewButton_4);
	}

}
