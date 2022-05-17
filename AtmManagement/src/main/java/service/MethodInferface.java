package service;

import java.io.IOException;
import java.util.List;

import exceptions.IncorrectPassword;
import exceptions.InsufficientBalance;
import exceptions.InvalidAccountNumber;
import exceptions.InvalidAccountType;
import exceptions.InvalidAmount;
import exceptions.InvalidInput;
import model.BankStatement;
import model.Customers;

public interface MethodInferface {

	//	Method to read the file and store the data in customerList
	void readFile(List<Customers> customerList);

	//Method to accept the input from user
	void userInput(List<Customers> customerList, List<BankStatement> statementList , String accountNumber) throws IOException,
			InvalidAccountNumber, IncorrectPassword, InsufficientBalance, InvalidInput, InvalidAccountType, InvalidAmount;

	//Method to view the Bank Statement of the customer
	void viewBankStatement(String accountNumber, Customers customers, List<Customers> customerList,
			List<BankStatement> statementList);

	//Method to transfer the amount and store the data in statementList
	void transfer(String accountNumber, Customers customers, List<Customers> customerList,
			List<BankStatement> statementList) throws IOException, InvalidAccountNumber, InsufficientBalance, IncorrectPassword;

	//Method to Write the csv file
	void updateCsv(List<Customers> customerList) throws IOException;

	//Method to withdraw the amount
	void withdraw(String accountNumber, Customers customers, List<Customers> customerList,
			List<BankStatement> statementList) throws IOException, IncorrectPassword, InsufficientBalance, InvalidAmount, InvalidAccountNumber, InvalidInput, InvalidAccountType;

	
	

}