package dev.alexander.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dev.alexander.entities.Account;
import dev.alexander.entities.User;
import util.JDBCConnection;

//light implementation to be modeled to SQL DB later.....
public class UserDaoImpl implements UserDAO {
	public static Connection conn = JDBCConnection.getConnection();

	public Boolean Register(User newUser) {
		
		try {
			String sql = "Call register_users(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, newUser.getUsername());
			cs.setString(2, newUser.getPassword());	
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User getUserById(int Id) {
		
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(Id));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				User a = new User();
				a.setUserId(rs.getInt("ID"));
				a.setUsername(rs.getString("USERNAME"));
				a.setPassword(rs.getString("PASSWORD"));
				a.setSuperUser(rs.getString("ADMIN"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserByName(String username) {
		//User finder = user_table.get(username);
		try {
			String sql = "SELECT * FROM users WHERE username = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {

				User a = new User();
				a.setUserId(rs.getInt("USER_ID"));
				a.setUsername(rs.getString("USERNAME"));
				a.setPassword(rs.getString("PASSWORD"));
				a.setSuperUser(rs.getString("ADMIN"));

				return a;
			}
			else 
			{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getAllUsers() {
		
		List<User> aggregate_table = new ArrayList<User>();

		try 
		{
			String sql = "SELECT * FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) 
			{
				User holder = new User();
				holder.setUsername(rs.getString("USERNAME"));
				holder.setPassword(rs.getString("PASSWORD"));
				holder.setSuperUser(rs.getString("ADMIN"));
				holder.setUserId(rs.getInt("USER_ID"));
				aggregate_table.add(holder);	
			}
			return aggregate_table;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return null;
	}

	public User updateUserStatus(User user) {
		
		try {
			String sql = "CALL update_user_info(?,?,?)";

			CallableStatement ps = conn.prepareCall(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2,user.getPassword());
			ps.setString(3, Integer.toString(user.getUserId()));
			ResultSet rs = ps.executeQuery();
			
			
				return user;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public boolean deleteUser(int id) {
		
		try {

			String sql = "DELETE users WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ps.executeQuery();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
