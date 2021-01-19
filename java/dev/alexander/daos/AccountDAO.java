package dev.alexander.daos;

import java.util.List;

import dev.alexander.entities.Account;
import dev.alexander.entities.User;

public interface AccountDAO {
	
	boolean createAccount(String givenName, int userId, int balance);
	Account updateAccountAmount(Account account);
	Account getAccountById(int acctId);
	Boolean deleteAccount(int acctId);
	public List<Account> getAllAccountsById(int usrId);
	

}
