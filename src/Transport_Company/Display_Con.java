package Transport_Company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Display_Con extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Connection con = null;
	private String consignmentid;
	private String vol;
	private String sendername;
	private String receivername;
	private String rev;
	private String truckno;
	private String dispatchstatus;
	private String sourceid;
	private String destid;
	private String orderdate;
	private String despatchdate;
	private String numtrucks;
	private BufferedImage image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Display_Con dialog = new Display_Con(null,true);
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
	public Display_Con(String consignmentid,boolean bill) throws ClassNotFoundException, SQLException, IOException {
		image = ImageIO.read(getClass().getResource("/TruckSplash.jpg"));
		setIconImage(image);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		if(!bill)
			setTitle("Display consignment status");
		else
			setTitle("Displaying Bill For The Consignment");
		setBounds(100, 100, 453, 336);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		CompanyDAO com =new CompanyDAO();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trasnsport","root","Say9753an#");
		PreparedStatement psf = con.prepareStatement("select * from consignment where consignmentId = '"+consignmentid+"'");
		ResultSet rs = psf.executeQuery();
		if(rs.next()){
			consignmentid = rs.getString(1);
			vol = String.valueOf(rs.getInt(2));
			sendername = com.getName(rs.getString(3));
			receivername = com.getName(rs.getString(4));
			rev = String.valueOf(rs.getInt(5));
			dispatchstatus = (rs.getInt(8)==1)?"Yes":"No";
			truckno = rs.getString(7);
			sourceid = rs.getString(9);
			destid = rs.getString(10);
			orderdate = formatter.format(rs.getDate(11));
			if(rs.getDate(12)!=null)
				despatchdate = formatter.format(rs.getDate(12));
			else
				despatchdate = null;
			String numtrucks = String.valueOf(rs.getInt(13));
		}
		{
			JLabel lblConsignmentId = new JLabel("Consignment Id:");
			contentPanel.add(lblConsignmentId, "2, 2");
		}
		{
			JLabel id = new JLabel("");
			contentPanel.add(id, "4, 2");
			id.setText(consignmentid);
		}
		{
			JLabel lblVolume = new JLabel("Volume :");
			contentPanel.add(lblVolume, "2, 4");
		}
		{
			JLabel volume = new JLabel("");
			contentPanel.add(volume, "4, 4");
			volume.setText(vol);
		}
		{
			JLabel lblSenderId = new JLabel("Sender Name:");
			contentPanel.add(lblSenderId, "2, 6");
		}
		{
			JLabel sname = new JLabel("");
			contentPanel.add(sname, "4, 6");
			sname.setText(sendername);
		}
		{
			JLabel lblReceiverName = new JLabel("Receiver Name:");
			contentPanel.add(lblReceiverName, "2, 8");
		}
		{
			JLabel rname = new JLabel("");
			contentPanel.add(rname, "4, 8");
			rname.setText(receivername);
		}
		{
			JLabel lblRevenue = new JLabel("Revenue");
			contentPanel.add(lblRevenue, "2, 10");
		}
		{
			JLabel revenue = new JLabel("");
			contentPanel.add(revenue, "4, 10");
			revenue.setText(rev);
		}
		{
			JLabel lblDispatchAndReceived = new JLabel("Dispatch and Received:");
			contentPanel.add(lblDispatchAndReceived, "2, 12");
		}
		{
			JLabel disrec = new JLabel("");
			contentPanel.add(disrec, "4, 12");
			disrec.setText(dispatchstatus);
		}
		{
			JLabel lblTruckNo = new JLabel("Truck No. Assigned:");
			contentPanel.add(lblTruckNo, "2, 14");
		}
		{
			JLabel tnumber = new JLabel("");
			contentPanel.add(tnumber, "4, 14");
			tnumber.setText(truckno);
		}
		{
			JLabel lblSourceId = new JLabel("Source Branch Id:");
			contentPanel.add(lblSourceId, "2, 16");
		}
		{
			JLabel sbranch = new JLabel("");
			contentPanel.add(sbranch, "4, 16");
			sbranch.setText(sourceid);
		}
		{
			JLabel lblDestinationBranchId = new JLabel("Destination Branch Id:");
			contentPanel.add(lblDestinationBranchId, "2, 18");
		}
		{
			JLabel dbranch = new JLabel("");
			contentPanel.add(dbranch, "4, 18");
			dbranch.setText(destid);
		}
		{
			JLabel lblOrderDate = new JLabel("Order Date:");
			contentPanel.add(lblOrderDate, "2, 20");
		}
		{
			JLabel order = new JLabel("");
			contentPanel.add(order, "4, 20");
			order.setText(orderdate);
		}
		{
			JLabel lblDespatchDate = new JLabel("Despatch Date:");
			contentPanel.add(lblDespatchDate, "2, 22");
		}
		{
			JLabel despatch = new JLabel("");
			contentPanel.add(despatch, "4, 22");
			if(despatchdate!=null)
				despatch.setText(despatchdate);
			else
				despatch.setText("To be dispatched");
		}
		{
			JLabel lblRequiredNoOf = new JLabel("Required No. of trucks:");
			contentPanel.add(lblRequiredNoOf, "2, 24");
		}
		{
			JLabel truck = new JLabel("");
			contentPanel.add(truck, "4, 24");
			truck.setText(numtrucks);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Close");
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
