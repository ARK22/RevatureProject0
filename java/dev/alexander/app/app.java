package dev.alexander.app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dev.alexander.entities.Account;
import dev.alexander.entities.User;
import dev.alexander.services.AccountServicesImpl;
import dev.alexander.services.UserServicesImpl;

public class app {

	private static Scanner input = new Scanner(System.in);
	private static User current_logged_user = null;
	private static UserServicesImpl controls = new UserServicesImpl();
	private static AccountServicesImpl act_controls = new AccountServicesImpl();
	private static List<Account> current_user_account_table = null;

	public static void main(String[] args) {
		frontPage();
		input.close();

	}

	// ----------------------------- Menus for the application
	// ---------------------------
	public static void frontPage() {
		System.out.println("-----------------Welcome to the Revature Project 0 Banking Application-----------------");

		while (true) {
			System.out.println("	Please enter a number to select from our list of options and press 'ENTER' ");
			System.out.println("1. Log in");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			try {
				int optionConvert = input.nextInt();

				switch (optionConvert) {

				case 1: {

					handleLogin();
					input.nextLine();
					continue;

				}
				case 2: {
					register();
					input.nextLine();
					continue;
				}
				case 3: {

					System.out.println("Thank you for using our service!");
					return;
				}
				default: {
					System.out.println("Please enter a valid response between the numbers listed.");
				}

				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Character!!! Please input a valid option");
				input.nextLine();
			}

		}
	}

	// Will Find all accounts associated with a logged in user.....CAN ONLY BE
	// ACCESSED IF THE PERSON IS LOGGED IN**
	private static void firstScreen() {
		while (true) {
			System.out.println(
					" Welcome " + current_logged_user.getUsername() + " please choose from the options below!");
			System.out.println("1. View existing accounts");
			System.out.println("2. Create an account");
			System.out.println("3. Select an account");
			System.out.println("4.Log out");

			try {
				int optionConvert = input.nextInt();

				switch (optionConvert) {

				case 1: {
					// viewAllAccountsByID
					current_user_account_table = act_controls.getAccountTable(current_logged_user.getUserId());
					act_controls.printAllAccountsForUser(current_user_account_table);
					continue;
				}
				case 2: {
					// actControls.appendAccount(current_logged_user.getUserId());
					handleNewAccount();
					continue;
				}
				case 3: {
					// actControls.SelectAccount();
					System.out.println("Please the enter the account number for the account you wish to view");

					try {
						int choice = input.nextInt();
						Account selected_account = act_controls.getAccountById(choice);

						if (selected_account == null) {
							System.out.println("Not a valid account! Please choose from one of the accounts listed.");
							// actControls.printAccountsByUserId();
							continue;
						} else {
							accountScreen(selected_account);
							selected_account = null;
						}
					} catch (InputMismatchException e) {
						System.out.println("Invalid Character!!! Please input a valid option");
						input.nextLine();
					}
					continue;
				}
				case 4: {

					System.out.println("logging out...");
					current_logged_user = null;
					return;
				}
				default: {
					System.out.println("Please enter a valid response between the numbers listed.");
				}

				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Character!!! Please input a valid option");
				input.nextLine();
			}

		}
	}

	// Hybrid of first screen featuring the additional commands for the super
	// user....
	private static void superScreen() {
		while (true) {
			System.out.println("Welcome administrator! Please enter a number to select from our list of options and press 'ENTER' ");
			System.out.println("1. See all existing Users.");
			System.out.println("2. Select a User.");
			System.out.println("3. Create a User.");
			System.out.println("4. Logout.");

			try {
				int optionConvert = input.nextInt();

				switch (optionConvert) {

				case 1: {
					List<User> table = controls.getAllUsers();
					controls.printAllUsers((ArrayList<User>) table);
					continue;
				}
				case 2: {
					System.out.println("Enter The name of the User you wish to edit.");
		
					String username = input.next();
					if(controls.getUserByName(username) == null){
						System.out.println("USER NOT FOUND");	
					}
					else{
						userScreen(controls.getUserByName(username));}
					continue;
				}
				case 3: {
					register();
					System.out.println("Account has been created!!");
					continue;
				}
				case 4: {

					System.out.println("Thank you for using our service!");
					return;
				}
				default: {
					System.out.println("Please enter a valid response between the numbers listed.");
				}

				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Character!!! Please input a valid option");
				input.nextLine();
			}

		}

	}

	// Screen for manipulating an account once selected...
	private static void accountScreen(Account selected_account) {
		while (true) {
			System.out.println("_____________________________________________________________________________________");
			System.out.println("|Id: " + selected_account.getAccount_id() + "  |Type: "
					+ selected_account.getAccount_name() + "  | Balance: " + selected_account.getAccount_balance());
			System.out.println("_____________________________________________________________________________________");
			System.out.println("");
			System.out.println("");
			System.out.println("	Please enter a number to select from our list of options and press 'ENTER' ");
			System.out.println("1. Deposit ");
			System.out.println("2. Withdraw");
			System.out.println("3. Delete an account.");
			System.out.println("4. Back to main menu");

			try {
				int optionConvert = input.nextInt();

				switch (optionConvert) {

				case 1: {
					// deposit
					selected_account = act_controls.depositAction(current_logged_user.getUserId(),
							selected_account.getAccount_id());
					continue;
				}
				case 2: {
					// withdraw
					selected_account = act_controls.withdrawAction(current_logged_user.getUserId(),
							selected_account.getAccount_id());
					continue;
				}
				case 3: {
					// delete
					if (selected_account.getAccount_balance() == 0.0) {
						act_controls.deleteAccountById(selected_account.getAccount_id());
						System.out.println("Account Deleted!");
						return;
					} else {
						System.out.println("ACTION CANNOT BE COMPLETED: Account Balance is not zero!");
						continue;
					}

				}

				case 4: {
					// exit
					System.out.println("Returning!");
					return;
				}
				default: {
					System.out.println("Please enter a valid response between the numbers listed.");
				}

				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Character!!! Please input a valid option");
				input.nextLine();
			}

		}

	}

	// 
	private static void handleLogin() {

		System.out.println("Please enter a Username");
		String username = input.next();
		System.out.println("Please enter your Password");
		String password = input.next();
		current_logged_user = controls.login(username, password);

		if (current_logged_user == null) {

			System.out.println("Username or password was incorrect...");
			return;
		} else if (current_logged_user.getSuperUser().equals("Y")) {
			superScreen();
		} else {
			System.out.println(current_logged_user.getSuperUser());
			firstScreen();
		}
	}

	// Calls the service for register passing the appropriate fields
	private static void register() {
		while (true) {

			System.out.println("Please enter a Username");
			String username = input.next();
			System.out.println("Please enter your Password");
			String password = input.next();
			if (password.length() > 8) {
				// register will add the item to the table of users
				controls.registerUsers(username, password);
				return;
			} else {
				System.out.println("INVALID PASSWORD MUST BE AT LEAST SEVEN CHARACTERS IN LENGTH");
				input.nextLine();
				continue;
			}
		}
	}
	//Contains the form for creating a new account.
	private static void handleNewAccount() {

		System.out.println("Please pick the type of account you'd like to create.");
		while (true) {
			System.out.println("1. Checking");
			System.out.println("2. Savings");
			try {
				int choice = input.nextInt();

				switch (choice) {

				case 1: {
					act_controls.appendAccount("Checking", current_logged_user.getUserId(), 0);
					return;
				}

				case 2: {
					act_controls.appendAccount("Savings", current_logged_user.getUserId(), 0);
					return;
				}
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid Character!!! Please input a valid option");
				input.nextLine();
			}
		}

	}
	//Menu for selected users.
	private static void userScreen(User selectedUser) {
		while (true) {
			System.out.println("_____________________________________________________________________________________");
			System.out.println("|Id: " + selectedUser.getUserId() + "  |Username: "
					+ selectedUser.getUsername() + "  | Password: " + selectedUser.getPassword());
			System.out.println("_____________________________________________________________________________________");
			System.out.println("");
			System.out.println("");
			System.out.println("	Please enter a number to select from our list of options and press 'ENTER' ");
			System.out.println("1. Change Username");
			System.out.println("2. Change Password ");
			System.out.println("3. Delete Account");
			System.out.println("4. Back to main menu");

			try {
				int optionConvert = input.nextInt();

				switch (optionConvert) {

				case 1: {
					System.out.println("Enter the new Name you wish to be associate with this User");
					String username = input.next();
					selectedUser.setUsername(username); 
					controls.updateUserById(selectedUser);
					continue;
					
				}
				case 2: {
					System.out.println("Enter the new Password you wish to be associate with this User");
					String password = input.next();
					selectedUser.setPassword(password); 
					controls.updateUserById(selectedUser);
					continue;
				}
				case 3: {
					controls.deleteUser(selectedUser);
					return;
					
				}

				case 4: {
					// exit
					System.out.println("Returning!");
					return;
				}
				default: {
					System.out.println("Please enter a valid response between the numbers listed.");
				}

				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Character!!! Please input a valid option");
				input.nextLine();
			}

		}

	}
	
	
}
