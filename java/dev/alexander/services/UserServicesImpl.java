package dev.alexander.services;

import java.util.ArrayList;
import java.util.List;

import dev.alexander.daos.UserDAO;
import dev.alexander.daos.UserDaoImpl;
import dev.alexander.entities.Account;
import dev.alexander.entities.User;

//business logic *****WILL CALL DAO IMPLEMENTATIONS TO ALLOW FOR CREATION OF USERS*******
public class UserServicesImpl implements UserServices {

	private static UserDAO users = new UserDaoImpl();
	
	//Will post the Username and password the user provided to the database
	public Boolean registerUsers(String Username, String Password) {
		User unregisteredNewUser = new User(Username, Password, "N");
		return users.Register(unregisteredNewUser);
	}
	//Will take the returned User object and compare the password the user applied.
	public User login(String username, String password) {
		
		User compare = users.getUserByName(username);

		if (compare == null) {

			return null;
		} else if (compare.getPassword().equals(password)) {
			return compare;
		} else
			return null;
	}
	
	//Returns a list containing all of the users in the table.
	public List<User> getAllUsers() {

		return users.getAllUsers();
	}

	public void printAllUsers(List<User> table) {
		System.out.println("_____________________________________________________________________");
		if (table == null) {
			System.out.println("No Accounts");
			return;
		} else {
			for (User user : table) {
				System.out.println(" |Username:       " +user.getUsername());
				System.out.println(" |Password:       " +user.getPassword());
				System.out.println(" |Id:             " +user.getUserId());
				System.out.println("________________________________________");
			}
		}
	}

	public User getUserByName(String username) {
		return users.getUserByName(username);
	}

	public User getUserById(int userId) {
		return getUserById(userId);
	}
	
	public User updateUserById(User updatedUser) 
	{
		return users.updateUserStatus(updatedUser);
	}

	public boolean deleteUser(User user) {
		users.deleteUser(user.getUserId());
		return false;
	}
	
}
