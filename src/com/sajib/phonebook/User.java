package com.sajib.phonebook;

import java.util.ArrayList;
import java.sql.*;
import com.sajib.sqlConnection.*;

public class User {

	String username;
	String password;
	int phonenum;
	ArrayList<User> userList = new ArrayList<>();

	User() {
	}

	User(String username, String password, int phonenum) {
		this.username = username;
		this.password = password;
		this.phonenum = phonenum;
		userList.add(this);
		addToDatabase(this);
	}
	public void User(String username, String password, int phonenum) {
		this.username = username;
		this.password = password;
		this.phonenum = phonenum;
		userList.add(this);
		addToDatabase(this);
	}
	public void addToDatabase(User user) {
		String query = "INSERT INTO users VALUES(0,'" + user.username + "', '"+ user.password + "', " + user.phonenum + ");";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		}catch(SQLException e) {
			System.err.println(e);
		}
	}

	public boolean checkUsernameAndPassword(String username, String password) {
		for (int i = 0; i < userList.size(); i++) {
			if (username == usernameGetter(userList.get(i))) {
				if (password == passwordGetter(userList.get(i))) {
					return true;
				}
			}
		}
		return false;
	}

	public String usernameGetter(User user) {
		return user.username;
	}

	public String passwordGetter(User user) {
		return user.password;
	}

	public static final Connection con = connector.getInstance().getConnection();

	public static void loadUsers() {
		String query = "SELECT * FROM users";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			while (rs.next()) {
				User user = new User(rs.getString(2), rs.getString(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
}
