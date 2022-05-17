package model;

public class Customers {
	private String customerAcNumber;
	private String customerName;
	private int customerPassword;
	private String customerAccountType;
	private int availableBalance;
	
	
	public String getCustomerAcNumber() {
		return customerAcNumber;
	}
	public void setCustomerAcNumber(String customerAcNumber) {
		this.customerAcNumber = customerAcNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(int customerPassword) {
		this.customerPassword = customerPassword;
	}
	public String getCustomerAccountType() {
		return customerAccountType;
	}
	public void setCustomerAccountType(String customerAccountType) {
		this.customerAccountType = customerAccountType;
	}
	public int getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(int availableBalance) {
		this.availableBalance = availableBalance;
	}
	
	public	Customers(String customerName,String customerAcNumber,int customerPassword,String customerAccountType,int availableBalance){
		this.customerName = customerName;
		this.customerAcNumber = customerAcNumber;
		this.customerPassword = customerPassword;
		this.customerAccountType = customerAccountType;
		this.availableBalance = availableBalance;
	}
}
