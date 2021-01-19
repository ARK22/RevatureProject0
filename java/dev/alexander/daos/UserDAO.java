package dev.alexander.daos;

import java.util.List;
import java.util.Set;

import dev.alexander.entities.User;

public interface UserDAO 
{
	
	Boolean Register(User newUser);
	
	User getUserById(int Id);
	
	User getUserByName(String name);
	
	List<User> getAllUsers();
	
	User updateUserStatus(User user);
	
	boolean deleteUser(int id);
}
