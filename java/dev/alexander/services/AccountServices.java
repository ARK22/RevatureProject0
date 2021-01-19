package dev.alexander.services;

import java.util.List;

import dev.alexander.entities.Account;

public interface AccountServices {
	//based on a selected account
	Account withdrawAction(int user_id, int account_id);
	Account depositAction(int user_id, int account_id);
	boolean deleteAccountById(int account_id);
	Account getAccountById(int account_id);
	Boolean appendAccount(String name,int user_id,int amount);
	public List<Account> getAccountTable(int user_id);
	public void printAllAccountsForUser(List<Account> table);

	

}
