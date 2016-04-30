package Transport_Company;

public class Employee {
	
	private String EmployeeName;
	private String EmployeeId;
	private String EmployeeAddress;
	private String EmployeeMobileNo;
	private String EmployeeEmailId;
	private String EmployeeBranchId;
	private String EmpPassword;
	private boolean isManager;
	
	public Employee(String employeeName, String employeeId, String employeeAddress, String employeeMobileNo,
			String employeeEmailId, String employeeBranchId, String empPassword,boolean isManager) {
		super();
		EmployeeName = employeeName;
		EmployeeId = employeeId;
		EmployeeAddress = employeeAddress;
		EmployeeMobileNo = employeeMobileNo;
		EmployeeEmailId = employeeEmailId;
		EmployeeBranchId = employeeBranchId;
		EmpPassword = empPassword;
		this.isManager = isManager;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public String getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(String employeeId) {
		EmployeeId = employeeId;
	}

	public String getEmployeeAddress() {
		return EmployeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		EmployeeAddress = employeeAddress;
	}

	public String getEmployeeMobileNo() {
		return EmployeeMobileNo;
	}

	public void setEmployeeMobileNo(String employeeMobileNo) {
		EmployeeMobileNo = employeeMobileNo;
	}

	public String getEmployeeEmailId() {
		return EmployeeEmailId;
	}

	public void setEmployeeEmailId(String employeeEmailId) {
		EmployeeEmailId = employeeEmailId;
	}

	public String getEmployeeBranchId() {
		return EmployeeBranchId;
	}

	public void setEmployeeBranchId(String employeeBranchId) {
		EmployeeBranchId = employeeBranchId;
	}

	public String getEmpPassword() {
		return EmpPassword;
	}

	public void setEmpPassword(String empPassword) {
		EmpPassword = empPassword;
	}

	@Override
	public String toString() {
		return "Employee [EmployeeName=" + EmployeeName + ", EmployeeId=" + EmployeeId + ", EmployeeAddress="
				+ EmployeeAddress + ", EmployeeMobileNo=" + EmployeeMobileNo + ", EmployeeEmailId=" + EmployeeEmailId
				+ ", EmployeeBranchId=" + EmployeeBranchId + ", EmpPassword=" + EmpPassword + "]";
	}
	
}
