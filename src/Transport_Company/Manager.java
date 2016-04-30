package Transport_Company;

public class Manager extends Employee{

	public Manager(String employeeName, String employeeId, String employeeAddress, String employeeMobileNo,
			String employeeEmailId, String employeeBranchId, String empPassword, boolean isManager, String branchId) {
		super(employeeName, employeeId, employeeAddress, employeeMobileNo, employeeEmailId, employeeBranchId, empPassword,
				Boolean.TRUE);
	}

}
