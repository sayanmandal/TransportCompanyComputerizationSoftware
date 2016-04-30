package Transport_Company;

public class Truck {
	
	private String truckNo;
	private String currentBranch;
	private long numberOfConsignmentsHandled;
	private String status;
	private String usage;
	
	public Truck(String truckNo, String currentBranch, long numberOfConsignmentsHandled, String status, String usage) {
		super();
		this.truckNo = truckNo;
		this.currentBranch = currentBranch;
		this.numberOfConsignmentsHandled = numberOfConsignmentsHandled;
		this.status = status;
		this.usage = usage;
	}

	public String getTruckNo() {
		return truckNo;
	}

	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}

	public String getCurrentBranch() {
		return currentBranch;
	}

	public void setCurrentBranch(String currentBranch) {
		this.currentBranch = currentBranch;
	}

	public long getNumberOfConsignmentsHandled() {
		return numberOfConsignmentsHandled;
	}

	public void setNumberOfConsignmentsHandled(long numberOfConsignmentsHandled) {
		this.numberOfConsignmentsHandled = numberOfConsignmentsHandled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	@Override
	public String toString() {
		return "Truck [truckNo=" + truckNo + ", currentBranch=" + currentBranch + ", numberOfConsignmentsHandled="
				+ numberOfConsignmentsHandled + ", status=" + status + ", usage=" + usage + "]";
	}
	
}
