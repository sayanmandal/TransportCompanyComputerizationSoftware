package Transport_Company;

public class Office {
	
	private String BranchId;
	private String BranchAddress;
	private String BranchPhone;
	private int NumberOfTrucks;
	private int NumberOfEmployees;
	private long VolumeHandled;
	private long RevenueGenerated;
	private long IdleWaitingTime;
	private static long rate;
	
	public Office(String branchId, String branchAddress, String branchPhone, int numberOfTrucks, int numberOfEmployees,
			long volumeHandled, long revenueGenerated, long idleWaitingTime, long rate) {
		super();
		BranchId = branchId;
		BranchAddress = branchAddress;
		BranchPhone = branchPhone;
		NumberOfTrucks = numberOfTrucks;
		NumberOfEmployees = numberOfEmployees;
		VolumeHandled = volumeHandled;
		RevenueGenerated = revenueGenerated;
		IdleWaitingTime = idleWaitingTime;
		this.rate = rate;
	}

	public String getBranchId() {
		return BranchId;
	}

	public void setBranchId(String branchId) {
		BranchId = branchId;
	}

	public String getBranchAddress() {
		return BranchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		BranchAddress = branchAddress;
	}

	public String getBranchPhone() {
		return BranchPhone;
	}

	public void setBranchPhone(String branchPhone) {
		BranchPhone = branchPhone;
	}

	public int getNumberOfTrucks() {
		return NumberOfTrucks;
	}

	public void setNumberOfTrucks(int numberOfTrucks) {
		NumberOfTrucks = numberOfTrucks;
	}

	public int getNumberOfEmployees() {
		return NumberOfEmployees;
	}

	public void setNumberOfEmployees(int numberOfEmployees) {
		NumberOfEmployees = numberOfEmployees;
	}

	public long getVolumeHandled() {
		return VolumeHandled;
	}

	public void setVolumeHandled(long volumeHandled) {
		VolumeHandled = volumeHandled;
	}

	public long getRevenueGenerated() {
		return RevenueGenerated;
	}

	public void setRevenueGenerated(long revenueGenerated) {
		RevenueGenerated = revenueGenerated;
	}

	public long getIdleWaitingTime() {
		return IdleWaitingTime;
	}

	public void setIdleWaitingTime(long idleWaitingTime) {
		IdleWaitingTime = idleWaitingTime;
	}

	public long getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Office [BranchId=" + BranchId + ", BranchAddress=" + BranchAddress + ", BranchPhone=" + BranchPhone
				+ ", NumberOfTrucks=" + NumberOfTrucks + ", NumberOfEmployees=" + NumberOfEmployees + ", VolumeHandled="
				+ VolumeHandled + ", RevenueGenerated=" + RevenueGenerated + ", IdleWaitingTime=" + IdleWaitingTime
				+ ", rate=" + rate + "]";
	}
	
}
