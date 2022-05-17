package com.serviceimplements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

import java.util.List;
import java.util.Scanner;

import exceptions.IncorrectPassword;
import exceptions.InsufficientBalance;
import exceptions.InvalidAccountNumber;
import exceptions.InvalidAccountType;
import exceptions.InvalidAmount;
import exceptions.InvalidInput;
import model.BankStatement;
import model.Customers;
import service.MethodInferface;

public class MethodImplementations implements MethodInferface {

	private static final String INVALID_INPUT = "Invalid Input!!";
	private static final String PATH = "src/main/resources/CustomerDetails.csv";
	int count = 0;
	Scanner sc = new Scanner(System.in);

	@Override
//	Method to read the file and store the data in customerList
	public void readFile(List<Customers> customerList) {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
			while ((line = br.readLine()) != null) {
				String[] splitArray = line.split(",");
				String password = splitArray[2];
				int convertedPassword = Integer.parseInt(password);
				String amount = splitArray[4];
				int convertedAmount = Integer.parseInt(amount);
				Customers c = new Customers(splitArray[0], splitArray[1], convertedPassword, splitArray[3],
						convertedAmount);
				customerList.add(c);
			}
			int size = customerList.size();
			System.out.println("Data Successfully Added in the list");
			System.out.println("Number of records read : " + size);
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
	}

	@Override
	// Method to accept the input from user
	public void userInput(List<Customers> customerList, List<BankStatement> statementList, String accountNumber2)
			throws IOException, InvalidAccountNumber, IncorrectPassword, InsufficientBalance, InvalidInput,
			InvalidAccountType, InvalidAmount {

		try {
			Scanner scan = new Scanner(System.in);
			count = 0;
			String accountNumber = accountNumber2;
			for (Customers customers : customerList) {
				if (accountNumber.equals(customers.getCustomerAcNumber())) {
					count++;
					System.out.println("Enter Your Account Type \n" + "1.Saving Account\n" + "2.Current Account");
					int accountType = scan.nextInt();
					System.out.println(accountType);
					if (accountType == 1) {
						saving(customerList, statementList, scan, accountNumber, customers);
					} else if (accountType == 2) {
						current(customerList, statementList, scan, accountNumber, customers);
					} else {
						throw new InvalidInput(INVALID_INPUT);
					}
				}

			}
			if (count == 0) {
				throw new InvalidAccountNumber("Invalid Account Number!!");
			}
		} catch (InputMismatchException e) {
			System.out.println("Input Mismatch");
		} catch (InvalidAccountNumber | InvalidAccountType | InvalidInput e) {
			System.out.println(e.getMessage());
		}
	}

	private void current(List<Customers> customerList, List<BankStatement> statementList, Scanner scan,
			String accountNumber, Customers customers) throws IOException, InvalidAccountNumber, InsufficientBalance,
			IncorrectPassword, InvalidInput, InvalidAccountType {
		if (customers.getCustomerAccountType().equals("current")) {
			System.out.println("Select Option Below \n" + "1.Transfer\n" + "2.Bank Statement\n");
			int choice = scan.nextInt();

			if (choice == 1) {
				transfer(accountNumber, customers, customerList, statementList);
			} else if (choice == 2) {
				if (statementList.isEmpty()) {
					System.out.println("No Statements Found!!");
				} else {
					viewBankStatement(accountNumber, customers, customerList, statementList);
				}
			} else {
				throw new InvalidInput(INVALID_INPUT);
			}

		} else {
			throw new InvalidAccountType("Invalid Account Type!!");
		}
	}

	private void saving(List<Customers> customerList, List<BankStatement> statementList, Scanner scan,
			String accountNumber, Customers customers) throws IOException, IncorrectPassword, InsufficientBalance,
			InvalidAmount, InvalidAccountNumber, InvalidInput, InvalidAccountType {
		if (customers.getCustomerAccountType().equals("saving")) {
			System.out.println(
					"Select Option Below \n" + "1.Withdraw\n" + "2.Transfer\n" + "3.Bank Statement\n");
			int choice = scan.nextInt();

			if (choice == 1) {
				withdraw(accountNumber, customers, customerList, statementList);
			} else if (choice == 2) {
				transfer(accountNumber, customers, customerList, statementList);
			} else if (choice == 3) {
				if (statementList.isEmpty()) {
					System.out.println("No Statements Found!!");
				} else {
					viewBankStatement(accountNumber, customers, customerList, statementList);
				}
			} else {
				throw new InvalidInput(INVALID_INPUT);
			}
		} else {
			throw new InvalidAccountType("Invalid Account Type!!");
		}
	}

	// Method to view the Bank Statement of the customer
	@Override
	public void viewBankStatement(String accountNumber, Customers customers, List<Customers> customerList,
			List<BankStatement> statementList) {
		count = 0;
		statementList.forEach(bankStatement -> {
			if ((bankStatement.getCustomer1AccountNumber().equals(accountNumber)) && count == 0) {
				System.out.println(
						"Transfered To" + "   " + "Account No" + "   " + "Transfered Amount" + "   " + "Balance");
			}
			if (bankStatement.getCustomer1AccountNumber().equals(accountNumber)) {
				System.out.println(bankStatement.getCustomer2Name() + "\t " + "\t "
						+ bankStatement.getCustomer2AccountNumber() + "\t " + "\t "
						+ bankStatement.getTransferedAmount() + "\t " + "\t " + bankStatement.getAvailableBalance());
				count++;
			}
		});

	}

	// Method to transfer the amount and store the data in statementList
	@Override
	public void transfer(String accountNumber, Customers customers, List<Customers> customerList,
			List<BankStatement> statementList)
			throws IOException, InvalidAccountNumber, InsufficientBalance, IncorrectPassword {
		try {

			System.out.println("Enter Account number where you want to transfer  :");
			String transferAccountNumber = sc.next();
			count = 0;

			for (Customers customer2 : customerList) {
				if (customer2.getCustomerAcNumber().equals(transferAccountNumber)) {
					count++;
					System.out.println("Enter Amount you want to transfer :");
					int amount = sc.nextInt();
					if (amount > 0) {
						System.out.println("Enter Your Password");
						int password = sc.nextInt();
						if (customers.getCustomerPassword() == password) {
							if (amount > customers.getAvailableBalance()) {
								throw new InsufficientBalance("Insufficient Balance!!");
							} else {
								customers.setAvailableBalance(customers.getAvailableBalance() - amount);
								customer2.setAvailableBalance(customer2.getAvailableBalance() + amount);
								System.out.println("Amount transferd to " + transferAccountNumber + " Successfully");
								System.out.println("Available Balance is : " + customers.getAvailableBalance());
								BankStatement statement = new BankStatement(customers.getCustomerAcNumber(),
										customers.getCustomerName(), customers.getCustomerAccountType(),
										customers.getAvailableBalance(), customer2.getCustomerName(),
										customer2.getCustomerAcNumber(), amount);
								statementList.add(statement);
								updateCsv(customerList);
							}
						} else {
							throw new IncorrectPassword("Invalid Password!!");
						}
					} else {
						throw new InvalidAmount("Amount Should be greater than zero!!");
					}

				}

			}
			if (count == 0) {
				throw new InvalidAccountNumber("Invalid Account Number!!");
			}
		} catch (InsufficientBalance | InvalidAmount | IncorrectPassword e) {
			System.out.println(e.getMessage());
		}

	}

	// Method to Write the csv file
	@Override
	public void updateCsv(List<Customers> customerList) throws IOException {

		FileWriter writer = new FileWriter(PATH);
		customerList.forEach(customers -> {
			try {
				writer.append(String.valueOf(customers.getCustomerName()) + "," + customers.getCustomerAcNumber() + ","
						+ customers.getCustomerPassword() + "," + customers.getCustomerAccountType() + ","
						+ customers.getAvailableBalance() + "\n");
			} catch (IOException e) {
				System.out.println(e);
			}
		});
		writer.close();
		System.out.println("Data saved in csv file");
	}

	public boolean choice() {
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Do you want to continue ? \n1.yes \n2.NO");
			int choice = scan.nextInt();
			if (choice == 1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Input Mismatch!!");
			choice();
		}

		return false;
	}

	// Method to withdraw the amount
	@Override
	public void withdraw(String accountNumber, Customers customers, List<Customers> customerList,
			List<BankStatement> statementList) throws IOException, IncorrectPassword, InsufficientBalance,
			InvalidAmount, InvalidAccountNumber, InvalidInput, InvalidAccountType {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter Amount you want to withdraw :");
			int amount = scan.nextInt();
			if (amount > 0) {
				int password;
				System.out.println("Enter Your Password");
				password = scan.nextInt();

				if (customers.getCustomerPassword() == password) {
					if (amount > customers.getAvailableBalance()) {
						throw new InsufficientBalance("Insufficient Balance!!");
					} else {
						customers.setAvailableBalance(customers.getAvailableBalance() - amount);
						System.out.println("your balance is :" + customers.getAvailableBalance());
						BankStatement statement = new BankStatement(customers.getCustomerAcNumber(),
								customers.getCustomerName(), customers.getCustomerAccountType(),
								customers.getAvailableBalance(), "self", "Atm", amount);
						statementList.add(statement);
						updateCsv(customerList);
//						System.out.println("Do you want to continue ? \n1.yes \n2.NO");

					}
				} else {
					throw new IncorrectPassword("Invalid Password!!");

				}
			} else {
				throw new InvalidAmount("Amount Should be greater than zero!!");
			}
		} catch (InputMismatchException e1) {

			System.out.println("Input Mismatch!!");
		} catch (InvalidAmount | IncorrectPassword | InsufficientBalance e) {
			System.out.println(e.getMessage());
		}
	}

}
