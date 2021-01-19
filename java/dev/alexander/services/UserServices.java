package dev.alexander.services;

import java.util.ArrayList;
import java.util.List;

import dev.alexander.entities.Account;
import dev.alexander.entities.User;

public interface UserServices {
	
	Boolean registerUsers(String username, String Password);
	User login(String username, String password);
	List<User> getAllUsers();
	void printAllUsers(List<User> table);
	User getUserByName(String username);
	User getUserById(int userId);
	User updateUserById(User updatedUser);
	boolean deleteUser(User user);
}
