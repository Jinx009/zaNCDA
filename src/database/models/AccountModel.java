package database.models;

public class AccountModel {
	
	private Double allAccount;
	private Double allUnPayAccount;
	private Double allPayAccount;
	private Double partOneUnAccount;
	private Double partOneAccount;
	private Double partTwoUnAccount;
	private Double partTwoAccount;
	
	public AccountModel(){
		this.allAccount = 0.00;
		this.allUnPayAccount = 0.00;
		this.allPayAccount = 0.00;
		this.partOneUnAccount = 0.00;
		this.partOneAccount = 0.00;
		this.partOneUnAccount = 0.00;
		this.partTwoAccount = 0.00;
	}

	public Double getAllAccount() {
		return allAccount;
	}

	public void setAllAccount(Double allAccount) {
		this.allAccount = allAccount;
	}

	public Double getAllUnPayAccount() {
		return allUnPayAccount;
	}

	public void setAllUnPayAccount(Double allUnPayAccount) {
		this.allUnPayAccount = allUnPayAccount;
	}

	public Double getAllPayAccount() {
		return allPayAccount;
	}

	public void setAllPayAccount(Double allPayAccount) {
		this.allPayAccount = allPayAccount;
	}

	public Double getPartOneUnAccount() {
		return partOneUnAccount;
	}

	public void setPartOneUnAccount(Double partOneUnAccount) {
		this.partOneUnAccount = partOneUnAccount;
	}

	public Double getPartOneAccount() {
		return partOneAccount;
	}

	public void setPartOneAccount(Double partOneAccount) {
		this.partOneAccount = partOneAccount;
	}

	public Double getPartTwoUnAccount() {
		return partTwoUnAccount;
	}

	public void setPartTwoUnAccount(Double partTwoUnAccount) {
		this.partTwoUnAccount = partTwoUnAccount;
	}

	public Double getPartTwoAccount() {
		return partTwoAccount;
	}

	public void setPartTwoAccount(Double partTwoAccount) {
		this.partTwoAccount = partTwoAccount;
	}
	
	
}
