package model;

public class BankStatement {
	private String customer1AccountNumber;
	private String customer1Name;
	private String customer1AccountType;
	private int availableBalance;
	private String customer2Name;
	private String customer2AccountNumber;
	private int transferedAmount;
	public String getCustomer1AccountNumber() {
		return customer1AccountNumber;
	}
	public void setCustomer1AccountNumber(String customer1AccountNumber) {
		this.customer1AccountNumber = customer1AccountNumber;
	}
	public String getCustomer1Name() {
		return customer1Name;
	}
	public void setCustomer1Name(String customer1Name) {
		this.customer1Name = customer1Name;
	}
	public String getCustomer1AccountType() {
		return customer1AccountType;
	}
	public void setCustomer1AccountType(String customer1AccountType) {
		this.customer1AccountType = customer1AccountType;
	}
	public int getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(int availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getCustomer2Name() {
		return customer2Name;
	}
	public void setCustomer2Name(String customer2Name) {
		this.customer2Name = customer2Name;
	}
	public String getCustomer2AccountNumber() {
		return customer2AccountNumber;
	}
	public void setCustomer2AccountNumber(String customer2AccountNumber) {
		this.customer2AccountNumber = customer2AccountNumber;
	}
	public BankStatement(String customer1AccountNumber, String customer1Name, String customer1AccountType,
			int availableBalance, String customer2Name, String customer2AccountNumber,int transferedAmount) {
		
		this.customer1AccountNumber = customer1AccountNumber;
		this.customer1Name = customer1Name;
		this.customer1AccountType = customer1AccountType;
		this.availableBalance = availableBalance;
		this.customer2Name = customer2Name;
		this.customer2AccountNumber = customer2AccountNumber;
		this.transferedAmount = transferedAmount;
	}
	public int getTransferedAmount() {
		return transferedAmount;
	}
	public void setTransferedAmount(int transferedAmount) {
		this.transferedAmount = transferedAmount;
	}

	
	
}
