package Transport_Company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class CompanyDAO {
	
	private Connection con;
	private PreparedStatement stmt,stmt1,stmt2,stmt3;
	private ResultSet rs;
	
	CompanyDAO() throws ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trasnsport","root","Say9753an#");
	}
	
	void insert_manager(String id, String name, String address, String mobile, String emailid, String branchid, String password) throws SQLException{
		stmt = con.prepareStatement("insert into manager(Id,Name,Address,Mobile,EmailId,BranchId,Password) values(?,?,?,?,?,?,?)");
		stmt.setString(1, id);
		stmt.setString(2, name);
		stmt.setString(3, address);
		stmt.setString(4, mobile);
		stmt.setString(5, emailid);
		stmt.setString(6, branchid);
		stmt.setString(7, password);
		stmt.executeUpdate();
		int numemp = get_number_of_employees(branchid);
		stmt1 = con.prepareStatement("update branchoffice set numberofemployees = "+(numemp+1)+ " where branchId = '"+branchid+"'");
		stmt1.executeUpdate();
	}
	
	int get_rate(String branchid) throws SQLException{
		PreparedStatement psmt = con.prepareStatement("select rate from branchoffice where branchId = '"+branchid+"'");
		ResultSet rs = psmt.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
	
	void insert_consignment(String id, int volume, String senderid, String receiverid, String branchid, String destid) throws SQLException{
		PreparedStatement pstatement = con.prepareStatement("insert into consignment(consignmentId,volume,senderId,receiverId,revenue,isTruck,truckno,dispatchstatus,sourceId,destId,orderdate,despatchdate,requirednooftrucks) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		pstatement.setString(1, id);
		pstatement.setInt(2, volume);
		pstatement.setString(3, senderid);
		pstatement.setString(4, receiverid);
		int rate = get_rate(branchid);
		pstatement.setInt(5, rate*volume);
		pstatement.setInt(6, 0);
		pstatement.setString(7, "");
		pstatement.setInt(8, 0);
		pstatement.setString(9, branchid);
		pstatement.setString(10, destid);
		pstatement.setDate(11, new java.sql.Date(System.currentTimeMillis()));
		pstatement.setDate(12, null);
		int vol = ((volume%500==0)? volume/500 : volume/500 +1);
		pstatement.setInt(13, vol);
		pstatement.executeUpdate();
		PreparedStatement psn = con.prepareStatement("update branchoffice set numberofwaitingcon = 1 where branchId = '"+branchid+"'");
		psn.executeUpdate();
	}
	
	int get_number_of_employees(String branchId) throws SQLException{
		stmt = con.prepareStatement("select numberofemployees from branchoffice where branchId = '"+branchId+"'");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
	int get_number_of_trucks(String branchId) throws SQLException{
		stmt = con.prepareStatement("select currentnumoftrucks from branchoffice where branchId = '"+branchId+"'");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
	void insert_employee(String id, String name, String address, String mobile, String emailid, String branchid, String password) throws SQLException{
		stmt = con.prepareStatement("insert into employee(Id,Name,Address,Mobile,Email,BranchId,Password) values(?,?,?,?,?,?,?)");
		stmt.setString(1, id);
		stmt.setString(2, name);
		stmt.setString(3, address);
		stmt.setString(4, mobile);
		stmt.setString(5, emailid);
		stmt.setString(6, branchid);
		stmt.setString(7, password);
		stmt.executeUpdate();
		int numemp = get_number_of_employees(branchid);
		stmt1 = con.prepareStatement("update branchoffice set numberofemployees = "+(numemp+1)+ " where branchId = '"+branchid+"'");
		stmt1.executeUpdate();
	}
	
	void insert_branch(String Id, String address, String phone, int currnooftrucks, int rate) throws SQLException{
		stmt = con.prepareStatement("insert into branchoffice(branchId,branchAddress,branchphone,numberoftrucks,numberofemployees,volumehandled,revenue,idlewaitingtime,rate,currentnumoftrucks,numberofwaitingcon) values(?,?,?,?,?,?,?,?,?,?,?)");
		stmt.setString(1, Id);
		stmt.setString(2, address);
		stmt.setString(3, phone);
		stmt.setInt(4, currnooftrucks);
		stmt.setInt(5, 0);
		stmt.setInt(6, 0);
		stmt.setInt(7, 0);
		stmt.setInt(8, 0);
		stmt.setInt(9, rate);
		stmt.setInt(10, currnooftrucks);
		stmt.setInt(11, 0);
		stmt.executeUpdate();
	}
	
	int get_number_of_cum_trucks(String branchid) throws SQLException{
		stmt = con.prepareStatement("select numberoftrucks from branchoffice where branchId = '"+branchid+"'");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
	void insert_Customer(String id, String name, String address, String mobile, String email, String consignmentid) throws SQLException{
		PreparedStatement ps = con.prepareStatement("insert into customer(Id,Name,Address,Mobile,Email,ConsignmentId) values(?,?,?,?,?,?)");
		ps.setString(1, id);
		ps.setString(2, name);
		ps.setString(3, address);
		ps.setString(4, mobile);
		ps.setString(5, email);
		ps.setString(6, consignmentid);
		ps.executeUpdate();
	}
	
	void temp_inc_truck(String truckno , String branchid) throws SQLException{
		stmt3 = con.prepareStatement("insert into truck(truckNo,currentBranch,noOfCon,status,buyingdate,arrivedate,dispatchdate) values(?,?,?,?,?,?,?)");
		stmt3.setString(1, truckno);
		stmt3.setString(2, branchid);
		stmt3.setInt(3, 0);
		stmt3.setString(4, "waiting in the branchid = "+branchid);
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		stmt3.setDate(5, date);
		int truck = get_number_of_trucks(branchid);
		stmt3.setDate(6, date);
		stmt3.setDate(7, null);
		stmt1 = con.prepareStatement("update branchoffice set currentnumoftrucks = "+(truck+1)+" where branchId = '"+branchid+"'");
		int cumtrucks = get_number_of_cum_trucks(branchid);
		stmt2 = con.prepareStatement("update branchoffice set numberoftrucks = "+(cumtrucks+1)+" where branchId = '"+branchid+"'");
		stmt3.executeUpdate();
		stmt1.executeUpdate();
		stmt2.executeUpdate();
	}
	
	void insert_new_truck(String truckno , String branchid) throws SQLException{
		if(get_waiting_numberofcon(branchid)==0){
			stmt3 = con.prepareStatement("insert into truck(truckNo,currentBranch,noOfCon,status,buyingdate,arrivedate,dispatchdate) values(?,?,?,?,?,?,?)");
			stmt3.setString(1, truckno);
			stmt3.setString(2, branchid);
			stmt3.setInt(3, 0);
			stmt3.setString(4, "waiting in the branchid = "+branchid);
			java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
			stmt3.setDate(5, date);
			int truck = get_number_of_trucks(branchid);
			stmt3.setDate(6, date);
			stmt3.setDate(7, null);
			stmt1 = con.prepareStatement("update branchoffice set currentnumoftrucks = "+(truck+1)+" where branchId = '"+branchid+"'");
			int cumtrucks = get_number_of_cum_trucks(branchid);
			stmt2 = con.prepareStatement("update branchoffice set numberoftrucks = "+(cumtrucks+1)+" where branchId = '"+branchid+"'");
			stmt3.executeUpdate();
			stmt1.executeUpdate();
			stmt2.executeUpdate();
		}else{
			temp_inc_truck(truckno,branchid);
			assign_trucks(truckno,branchid,true);
		}
	}
	
	void increase_truck(int numberoftrucks, String branchid) throws SQLException{
		int truck = get_number_of_trucks(branchid);
		stmt1 = con.prepareStatement("update branchoffice set currentnumoftrucks = "+(truck+numberoftrucks)+" where branchId = '"+branchid+"'");
		int cumtrucks = get_number_of_cum_trucks(branchid);
		stmt2 = con.prepareStatement("update branchoffice set numberoftrucks = "+(cumtrucks+numberoftrucks)+" where branchId = '"+branchid+"'");
		stmt1.executeUpdate();
		stmt2.executeUpdate();
	}
	
	void decrease_truck(int numberoftrucks, String branchid) throws SQLException{
		int truck = get_number_of_trucks(branchid);
		stmt1 = con.prepareStatement("update branchoffice set currentnumoftrucks = "+(truck-numberoftrucks)+" where branchId = '"+branchid+"'");
		stmt1.executeUpdate();
	}
	
	String get_truck_no_con(String consignmentid) throws SQLException{
		PreparedStatement pstm = con.prepareStatement("select truckno from consignment where consignmentId = '"+consignmentid+"'");
		ResultSet rs = pstm.executeQuery();
		if(rs.next())
			return rs.getString(1);
		return "";
	}
	
	void change_status_con(String consignmentid, String truckno) throws SQLException{
		PreparedStatement pstmt_1 = con.prepareStatement("update consignment set isTruck = 1 where consignmentId = '"+consignmentid+"'");
		PreparedStatement pstmt_2 = con.prepareStatement("update consignment set dispatchstatus = 1 where consignmentId = '"+consignmentid+"'");
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		PreparedStatement pstmt_3 = con.prepareStatement("update consignment set despatchdate = ? where consignmentId = '"+consignmentid+"'");
		pstmt_3.setDate(1, date);
		String trucknocon = get_truck_no_con(consignmentid);
		PreparedStatement pstmt_4 = con.prepareStatement("update consignment set truckno = '"+trucknocon+","+truckno+"' where consignmentId = '"+consignmentid+"'");
		pstmt_1.executeUpdate();
		pstmt_2.executeUpdate();
		pstmt_3.executeUpdate();
		pstmt_4.executeUpdate();
	}
	
	int get_no_of_con(String truckno) throws SQLException{
		PreparedStatement pstmt = con.prepareStatement("select noOfCon from truck where truckNo = '"+truckno+"'");
		ResultSet rs = pstmt.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
	
	int get_diff_in_days(java.sql.Date date1, java.sql.Date date2){
		if(date2!=null){
		long mil1 = Math.abs(date1.getTime()-date2.getTime());
		return (int) (TimeUnit.MILLISECONDS.toDays(mil1));
		}else{
			long mil2 = Math.abs(new java.sql.Date(System.currentTimeMillis()).getTime()-date1.getTime());
			return (int) (TimeUnit.MILLISECONDS.toDays(mil2));
		}
	}
	

	int get_idlewaiting(String branchid) throws SQLException{
		PreparedStatement ps = con.prepareStatement("select idlewaitingtime from branchoffice where branchId = '"+branchid+"'");
		ResultSet rs = ps.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
	
	java.sql.Date get_arrive_date(String truckno) throws SQLException{
		PreparedStatement stmk = con.prepareStatement("select arrivedate from truck where truckNo = '"+truckno+"'");
		ResultSet rs = stmk.executeQuery();
		if(rs.next()){
			return rs.getDate(1);
		}
		return new java.sql.Date(System.currentTimeMillis());
	}
	
	java.sql.Date get_dispatch_date(String truckno) throws SQLException{
		PreparedStatement psj = con.prepareStatement("select dispatchdate from truck where truckNo = '"+truckno+"'");
		ResultSet rs = psj.executeQuery();
		if(rs.next()){
			return rs.getDate(1);
		}
		return new java.sql.Date(System.currentTimeMillis());
	}
	
	void change_status_truck(String truckno, String receiverid,String branchid) throws SQLException{
		PreparedStatement pstmt_1 = con.prepareStatement("update truck set currentBranch = '"+receiverid+"' where truckNo = '"+truckno+"'");
		int connum = get_no_of_con(truckno);
		PreparedStatement pstmt_2 = con.prepareStatement("update truck set noOfCon = '"+(connum+1)+"' where truckNo = '"+truckno+"'");
		PreparedStatement pstmt_3 = con.prepareStatement("update truck set status = 'waiting in the branch "+receiverid+"' where truckNo = '"+truckno+"'");
		int idle = get_idlewaiting(branchid);
		//System.out.println(idle);
		//System.out.println(get_diff_in_days(get_arrive_date(truckno),get_dispatch_date(truckno)));
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		int diffs = get_diff_in_days(get_arrive_date(truckno),get_dispatch_date(truckno))+idle;
		PreparedStatement pstmt_4 = con.prepareStatement("update branchoffice set idlewaitingtime = "+ diffs +" where branchId = '"+branchid+"'");
		PreparedStatement pstmt_5 = con.prepareStatement("update truck set arrivedate = ? where truckNo = '"+truckno+"'");
		pstmt_5.setDate(1, date);
		pstmt_1.executeUpdate();
		pstmt_2.executeUpdate();
		pstmt_3.executeUpdate();
		pstmt_4.executeUpdate();
		pstmt_5.executeUpdate();
	}
	
	int get_vol_con(String consignmentid) throws SQLException{
		PreparedStatement pst = con.prepareStatement("select volume from consignment where consignmentId = '"+consignmentid+"'");
		ResultSet rs = pst.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
	
	int get_rev_con(String consignmentid) throws SQLException{
		PreparedStatement pstm = con.prepareStatement("select revenue from consignment where consignmentId = '"+consignmentid+"'");
		ResultSet rs = pstm.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
	
	int get_vol_bra(String branchid) throws SQLException{
		PreparedStatement pst = con.prepareStatement("select volumehandled from branchoffice where branchId = '"+branchid+"'");
		ResultSet rs = pst.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
		
	}
	
	int get_rev_bra(String branchid) throws SQLException{
		PreparedStatement pst = con.prepareStatement("select revenue from branchoffice where branchId = '"+branchid+"'");
		ResultSet rs = pst.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
	
	int get_waiting_con(String branchid) throws SQLException{
		PreparedStatement pst = con.prepareStatement("select numberofwaitingcon from branchoffice where branchId = '"+branchid+"'");
		ResultSet rs = pst.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}
	
	
	int get_waiting_numberofcon(String branchid) throws SQLException{
		stmt = con.prepareStatement("select numberofwaitingcon from branchoffice where branchId = '"+branchid+"'");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	

	java.sql.Date get_order_date(String consignmentid) throws SQLException{
		PreparedStatement p = con.prepareStatement("select orderdate from consignment where consignmentId = '"+consignmentid+"'");
		ResultSet rs = p.executeQuery();
		if(rs.next())
			return rs.getDate(1);
		return new java.sql.Date(System.currentTimeMillis());
	}
	
	java.sql.Date get_despatch_date(String consignmentid) throws SQLException{
		PreparedStatement p = con.prepareStatement("select despatchdate from consignment where consignmentId = '"+consignmentid+"'");
		ResultSet rs = p.executeQuery();
		if(rs.next())
			return rs.getDate(1);
		return new java.sql.Date(System.currentTimeMillis());
	}

	
	void change_status_branch(String branchid, String receiverid, String consignmentid) throws SQLException{
		int vol = get_vol_con(consignmentid);
		int rev = get_rev_con(consignmentid);
		int bra_vol = get_vol_bra(receiverid);
		int rev_bra = get_rev_bra(receiverid);
		int waitingcon = get_waiting_con(branchid);
		/*java.sql.Date date1 = get_order_date(consignmentid);
		java.sql.Date date2 = get_despatch_date(consignmentid);
		//int idle = get_idlewaiting(branchid);*/
		PreparedStatement pstmt = con.prepareStatement("update branchoffice set volumehandled ="+(bra_vol+vol)+" where branchId = '"+receiverid+"'");
		PreparedStatement pstmt_1 = con.prepareStatement("update branchoffice set revenue = "+(rev_bra+rev)+" where branchId = '"+receiverid+"'");
		PreparedStatement pstmt_2 = con.prepareStatement("update branchoffice set numberofwaitingcon = "+(waitingcon-1)+" where branchId = '"+branchid+"'");
		pstmt.executeUpdate();
		pstmt_1.executeUpdate();
		pstmt_2.executeUpdate();
		/*PreparedStatement pstmt_4 =con.prepareStatement("update branchoffice set idlewaitingtime = "+(idle+get_diff_in_days(date1,date2))+" where branchId = '"+branchid+"'");
		pstmt_4.executeUpdate();*/
	}
	
	void allot_truck(int numtrucks, String branchid, String receiverid, String consignmentid) throws SQLException{
		int count = 0;
		PreparedStatement pstmt = con.prepareStatement("select * from truck where currentBranch = '"+branchid+"'");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			change_status_con(consignmentid,rs.getString(1));
			change_status_truck(rs.getString(1),receiverid,branchid);
			if(count==0)
				change_status_branch(branchid,receiverid,consignmentid);
			increase_truck(1, receiverid);
			decrease_truck(1, branchid);
			assign_trucks(rs.getString(1), receiverid,false);
			count++;
			if(count == numtrucks)
				break;
		}
	}
	
	boolean is_truck_available(String consignmentid, String branchid) throws SQLException{
		PreparedStatement psm = con.prepareStatement("select requirednooftrucks from consignment where consignmentId = '"+consignmentid+"'");
		int truck = get_number_of_trucks(branchid);
		ResultSet rs = psm.executeQuery();
		if(rs.next()){
			if(rs.getInt(1) <= truck)
				return true;
		}
		return false;
	}
	
	void assign_trucks(String truckno , String branchid,boolean buy) throws SQLException{
		PreparedStatement ps = con.prepareStatement("select * from consignment where sourceId = '"+branchid+"' and isTruck = 0 order by volume");
		int truck_1 = get_number_of_trucks(branchid);
		ResultSet rs = ps.executeQuery();
		/*if(!buy){
			PreparedStatement pst = con.prepareStatement("update branchoffice set currentnumoftrucks ="+(truck_1+1)+" where branchId = '"+branchid+"'");
			PreparedStatement pst_1 = con.prepareStatement("update branchoffice set numberoftrucks ="+(truck_1+1)+" where branchId = '"+branchid+"'");
			pst.executeUpdate();
			pst_1.executeUpdate();
		}*/
		int truck = get_number_of_trucks(branchid);
		while(rs.next()){
			if(rs.getInt("requirednooftrucks")<=(truck)){
				String receiverid = rs.getString("destId");
				String consignmentid = rs.getString(1);
				allot_truck(rs.getInt("requirednooftrucks"),branchid,receiverid,consignmentid);
				JOptionPane.showMessageDialog(null, "consignment "+rs.getInt(1)+" dispatched");
			}
		}
	}
	
	int getmanagerCount() throws SQLException{
		stmt = con.prepareStatement("select count(*) from manager");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
	boolean if_branch_exists(String branchId) throws SQLException{
		stmt = con.prepareStatement("select * from branchoffice where branchId = '"+branchId.trim()+"'");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	void delete_manager() throws SQLException{
		stmt = con.prepareStatement("delete from manager");
		stmt.executeUpdate();
	}

	boolean check_Password_Manager(String username, String pass) throws SQLException{
		stmt = con.prepareStatement("select Password from manager where Id = '"+username+"'");
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			return encryptor.checkPassword(pass, rs.getString(1));
		}
		return false;
	}
	
	boolean if_consignment_exists(String consignmentid) throws SQLException{
		PreparedStatement psk = con.prepareStatement("select * from consignment where consignmentId = '"+consignmentid+"'");
		ResultSet rs = psk.executeQuery();
		if(rs.next())
			return true;
		return false;
	}
	
	boolean if_truck_exists(String truckno) throws SQLException{
		PreparedStatement psh = con.prepareStatement("select * from truck where truckNo = '"+truckno+"'");
		ResultSet rs = psh.executeQuery();
		if(rs.next())
			return true;
		return false;
	}
	
	String getName(String id) throws SQLException{
		PreparedStatement pf = con.prepareStatement("select Name from customer where Id = '"+id+"'");
		ResultSet rs = pf.executeQuery();
		if(rs.next())
			return rs.getString(1);
		return "";
	}
	
	int get_no_of_consignment(String truckno) throws SQLException{
		PreparedStatement pfg = con.prepareStatement("select noOfCon from truck where truckNo = '"+truckno+"'");
		ResultSet rs = pfg.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
	void change_rate(String branchid, int rate) throws SQLException{
		PreparedStatement psd = con.prepareStatement("update branchoffice set rate = "+rate+" where branchId = '"+branchid+"'");
		psd.executeUpdate();
	}
	
	String get_status(String truckno) throws SQLException{
		PreparedStatement psj = con.prepareStatement("select status from truck where truckNo = '"+truckno+"'");
		ResultSet rs = psj.executeQuery();
		if(rs.next())
			return rs.getString(1);
		return "";
	}
	
	double get_average_wait_con() throws SQLException{
		PreparedStatement psh = con.prepareStatement("select * from consignment");
		ResultSet rs = psh.executeQuery();
		int daysdiff = 0;
		int count = 0;
		while(rs.next()){
			daysdiff += get_diff_in_days(rs.getDate("orderdate"),rs.getDate("despatchdate"));
			count++;
		}
		return ((double)daysdiff/(double)count);
	}
	
	double get_idle_waiting_time(String branchid) throws SQLException{
		int diff = 0;
		PreparedStatement psl = con.prepareStatement("select * from branchoffice where branchId = '"+branchid+"'");
		ResultSet rs = psl.executeQuery();
		PreparedStatement psl1 = con.prepareStatement("select * from truck where currentBranch = '"+branchid+"'");
		ResultSet rs1 = psl1.executeQuery();
		while(rs1.next()){
			diff += get_diff_in_days(rs1.getDate("arrivedate"),new java.sql.Date(System.currentTimeMillis()));
		}
		if(rs.next()){
			return  ((diff+(double)rs.getInt("idlewaitingtime"))/(double)rs.getInt("numberoftrucks"));
		}
		return 0;
	}
	
	boolean check_employee_password(String username , String password) throws SQLException{
		PreparedStatement psl = con.prepareStatement("select * from employee where Id = '"+username+"'");
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		ResultSet rs = psl.executeQuery();
		if(rs.next()){
			return encryptor.checkPassword(password, rs.getString("Password"));
		}
		return false;
	}
	
	String get_branch_id(String userid) throws SQLException{
		PreparedStatement pst = con.prepareStatement("select * from employee where Id = '"+userid+"'");
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			return rs.getString("BranchId");
		}
		return "";
	}
	
	String get_branch_d(String managerid) throws SQLException{
		PreparedStatement psf = con.prepareStatement("select * from manager where Id = '"+managerid+"'");
		ResultSet rs = psf.executeQuery();
		if(rs.next()){
			return rs.getString("BranchId");
		}
		return "";
	}
	
	int get_branchrate(String branchid) throws SQLException{
		PreparedStatement psk = con.prepareStatement("select rate from branchoffice where branchId = '"+branchid+"'");
		ResultSet rs = psk.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
	String get_employee(String id) throws SQLException{
		PreparedStatement psl = con.prepareStatement("select Name from employee where Id = '"+id+"'");
		ResultSet rsy = psl.executeQuery();
		if(rsy.next()){
			return rsy.getString(1);
		}
		return "";
	}
	
}
