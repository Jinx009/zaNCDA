package database.models;

public class AccountModel {
	
	private double allAccount;
	private double allUnPayAccount;
	private double allPayAccount;
	private double partOneUnAccount;
	private double partOneAccount;
	private double partTwoUnAccount;
	private double partTwoAccount;
	
	public AccountModel(){
		this.allAccount = 0;
		this.allUnPayAccount = 0;
		this.allPayAccount = 0;
		this.partOneUnAccount = 0;
		this.partOneAccount = 0;
		this.partOneUnAccount = 0;
		this.partTwoAccount = 0;
	}
	
	public double getAllAccount() {
		return allAccount;
	}
	public void setAllAccount(double allAccount) {
		this.allAccount = allAccount;
	}
	public double getAllUnPayAccount() {
		return allUnPayAccount;
	}
	public void setAllUnPayAccount(double allUnPayAccount) {
		this.allUnPayAccount = allUnPayAccount;
	}
	public double getAllPayAccount() {
		return allPayAccount;
	}
	public void setAllPayAccount(double allPayAccount) {
		this.allPayAccount = allPayAccount;
	}
	public double getPartOneUnAccount() {
		return partOneUnAccount;
	}
	public void setPartOneUnAccount(double partOneUnAccount) {
		this.partOneUnAccount = partOneUnAccount;
	}
	public double getPartOneAccount() {
		return partOneAccount;
	}
	public void setPartOneAccount(double partOneAccount) {
		this.partOneAccount = partOneAccount;
	}
	public double getPartTwoUnAccount() {
		return partTwoUnAccount;
	}
	public void setPartTwoUnAccount(double partTwoUnAccount) {
		this.partTwoUnAccount = partTwoUnAccount;
	}
	public double getPartTwoAccount() {
		return partTwoAccount;
	}
	public void setPartTwoAccount(double partTwoAccount) {
		this.partTwoAccount = partTwoAccount;
	}
}
