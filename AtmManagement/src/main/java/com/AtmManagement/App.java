package com.AtmManagement;

import java.util.*;

import com.serviceimplements.MethodImplementations;

import exceptions.IncorrectPassword;
import exceptions.InsufficientBalance;
import exceptions.InvalidAccountNumber;
import exceptions.InvalidAccountType;
import exceptions.InvalidAmount;
import exceptions.InvalidInput;
import model.BankStatement;
import model.Customers;

import java.io.*;

public class App {

	public static void main(String[] args) throws IOException, InvalidAccountNumber, IncorrectPassword,
			InsufficientBalance, InvalidInput, InvalidAccountType, InvalidAmount {
		Scanner sc=new Scanner(System.in);

		MethodImplementations methods = new MethodImplementations();
		List<Customers> customerList = new ArrayList<>();
		List<BankStatement> statementList = new ArrayList<>();
		methods.readFile(customerList);
		boolean flag=true;
		while (flag) {
			
			System.out.println("==============ATM MANAGEMENT===============");
			System.out.println();
			System.out.println("Enter Your Account Number");
			String accountNumber = sc.next();
			System.out.println(accountNumber);
			methods.userInput(customerList, statementList , accountNumber);
			flag=methods.choice();
		}
	}
}
