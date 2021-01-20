package dev.alexander.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.alexander.entities.Account;
import dev.alexander.entities.User;
import util.JDBCConnection;

public class AccountDAOImpl implements AccountDAO {
	
	public static Connection conn = JDBCConnection.getConnection();
	
	public boolean createAccount(String givenName, int userId, int balance) {
		try {
			String sql = "CALL add_account(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1,givenName);
			cs.setString(2,Integer.toString(userId));
			cs.setString(3,Integer.toString(balance));
			
			cs.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return false;
	}

	public Account updateAccountAmount(Account account) {
		
		try {
			String sql = "Call update_account_amount(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1,Integer.toString(account.getAccount_id()));
			cs.setString(2,Double.toString(account.getAccount_balance()));
			ResultSet rs= cs.executeQuery();
			
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Account> getAllAccountsById(int usrId)
	{
		List<Account> Accounts = new ArrayList<Account>();
		try {
			String sql = "SELECT * FROM account WHERE owner_id = ? ORDER BY account_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(usrId));

			ResultSet rs = ps.executeQuery();			
			
			while(rs.next()) 
			{
				Account holder = new Account();
				holder.setAccount_id(rs.getInt("ACCOUNT_ID"));
				holder.setUser_id(rs.getInt("OWNER_ID"));
				holder.setAccount_name(rs.getString("NAME"));
				holder.setAccount_balance(rs.getDouble("BALANCE"));
				Accounts.add(holder);	
			}
			return Accounts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return null;
	}

	public Account getAccountById(int acctId, int user_id) {
		
		try {
			String sql = "SELECT * FROM account WHERE account_id = ? AND owner_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,Integer.toString(acctId));
			ps.setString(2,Integer.toString(user_id));
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
			Account holder = new Account();
			holder.setAccount_id(rs.getInt("ACCOUNT_ID"));
			holder.setUser_id(rs.getInt("OWNER_ID"));
			holder.setAccount_name(rs.getString("NAME"));
			holder.setAccount_balance(rs.getInt("BALANCE"));	
			return holder;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean deleteAccount(int acctId) {
		
		try {
			String sql = "Call delete_account(?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1,Integer.toString(acctId));
			cs.executeQuery();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
