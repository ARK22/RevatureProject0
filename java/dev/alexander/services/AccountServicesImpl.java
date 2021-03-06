package dev.alexander.services;

import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;

import dev.alexander.daos.AccountDAO;
import dev.alexander.daos.AccountDAOImpl;
import dev.alexander.entities.Account;

public class AccountServicesImpl implements AccountServices {

	private static AccountDAO account_controls = new AccountDAOImpl();
	//Calls the update account amount DAO passing the new balance after ensuring it has not been overdrafted and will return to menu if it is...
	public Account withdrawAction(int user_id, int account_id) {
		// TODO Auto-generated method stub
		Scanner inp = new Scanner(System.in);
		Account account = getAccountById(account_id, user_id);
		while (true) {
			try {
				System.out.println("Please enter the amount you wish to Withdraw.");
				Double withdraw = inp.nextDouble();
				Double total = account.getAccount_balance() - withdraw;
				if (total < 0.0) {
					System.out.println("The amount you are attempting to withdraw exceeds the balance on the account!");
					return account;
				} else {
					account.setAccount_balance(total);
					return account_controls.updateAccountAmount(account);
				}
			} catch (IllegalFormatException e) {
				System.out.println("That is not a valid number input!!!");
				continue;
			}
		}
	}
	//Calls the update account amount DAO passing the amount to be inserted into the account by the id provided.
	public Account depositAction(int user_id, int account_id) {
		Scanner inp = new Scanner(System.in);
		Account account = getAccountById(account_id, user_id);
		while (true) {
			try {
				System.out.println("Please enter the amount you wish to Deposit.");
				Double deposit = inp.nextDouble();
				account.setAccount_balance(account.getAccount_balance() + deposit);
				return account_controls.updateAccountAmount(account);

			} catch (IllegalFormatException e) {
				System.out.println("That is not a valid number input!!!");
				continue;
			}
		}

	}
	//Calls the delete DAO... 
	public boolean deleteAccountById(int account_id) {
		account_controls.deleteAccount(account_id);
		return false;
	}
	//Calls the get account DAO...
	public Account getAccountById(int account_id, int user_id) {
		return account_controls.getAccountById(account_id, user_id);

	}
	//Calls create account DAO.....
	public Boolean appendAccount(String string, int user_id, int i) {
		// TODO Auto-generated method stub
		return account_controls.createAccount(string, user_id, i);
	}
	//Returns a list of all accounts.......
	public List<Account> getAccountTable(int user_id) {

		return account_controls.getAllAccountsById(user_id);
	}
	//Prints all accounts under a specific owner_id.......
	public void printAllAccountsForUser(List<Account> table) {
		System.out.println("_____________________________________________________________________");
		if (table == null) {
			System.out.println("No Accounts");
			return;
		} else {
			for (Account account : table) {
				System.out.println(" |Account ID:       " + account.getAccount_id());
				System.out.println(" |Type:             " + account.getAccount_name());
				System.out.println(" |Balance:          " + account.getAccount_balance());
				System.out.println("________________________________________");
			}
		}
	}

}
