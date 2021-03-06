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
		
		User compare = getUserByName(username);

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
	//Will display all users....
	public void printAllUsers(List<User> table) {
		System.out.println("_____________________________________________________________________");
		if (table == null) {
			System.out.println("No Users");
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
	//Will acquire user based on the username...
	public User getUserByName(String username) {
		return users.getUserByName(username);
	}
	//Will acquire user based on the password...
	public User getUserById(int userId) {
		return getUserById(userId);
	}
	//Will update user account using all fields of the 'updateUser' object...
	public User updateUserById(User updatedUser) 
	{
		return users.updateUserStatus(updatedUser);
	}
	//Returns booleans
	public boolean deleteUser(User user) {
		users.deleteUser(user.getUserId());
		return false;
	}
	
}
