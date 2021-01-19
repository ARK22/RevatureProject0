package dev.alexander.entities;

import java.util.ArrayList;

public class User {
	
	private String username;
	private String password;
	private String superUser;
	private int userId;
	private ArrayList<Account> activeAccounts;
	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User() {
		super();
	}
	
	public User(String username, String password, String superStatus) 
	{
		this.username = username;
		this.password = password;
		this.superUser = superStatus;
		this.userId = 0;
		this.activeAccounts = null;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getSuperUser() {
		return superUser;
	}


	public void setSuperUser(String superUser) {
		this.superUser = superUser;
	}
	
	public ArrayList<Account> getActiveAccounts() {
		return activeAccounts;
	}

	public void setActiveAccounts(ArrayList<Account> activeAccounts) {
		this.activeAccounts = activeAccounts;
	}



}
