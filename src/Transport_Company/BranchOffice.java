package Transport_Company;

public class BranchOffice extends Office{
	
	private boolean isBranch;

	public BranchOffice(String branchId, String branchAddress, String branchPhone, int numberOfTrucks,
			int numberOfEmployees, long volumeHandled, long revenueGenerated, long idleWaitingTime, long rate) {
		super(branchId, branchAddress, branchPhone, numberOfTrucks, numberOfEmployees, volumeHandled, revenueGenerated,
				idleWaitingTime, rate);
	}

	public boolean isBranch() {
		return isBranch;
	}

	public void setBranch(boolean isBranch) {
		this.isBranch = isBranch;
	}

}
