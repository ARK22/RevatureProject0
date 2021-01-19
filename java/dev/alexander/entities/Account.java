package dev.alexander.entities;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private String account_name;
	
	private int account_id;
	private double account_balance;
	private int user_id;
	

	public Account() {
		super();
	}
	
	public Account(int account_id,int user_id,String account_name,double balance){
		super();
		this.account_id = account_id;
		this.user_id = user_id;
		this.account_name = account_name;
		this.account_balance = balance;
		
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public double getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(double account_balance) {
		this.account_balance = account_balance;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

}
