package com.sajib.sqlConnection;

import java.sql.*;
import java.util.ArrayList;

public class statements {

	public static final Connection con = connector.getInstance().getConnection();
	public static ArrayList<String> tableList() throws SQLException {
		DatabaseMetaData meta = con.getMetaData();
		ResultSet res = meta.getTables(null, null, null, new String[] { "TABLE" });
		ArrayList<String> tableList = new ArrayList<>();
		while (res.next()) {
			tableList.add(res.getString("TABLE_NAME"));
		}
		return tableList;
	}
	public static void checkTables(String username) throws SQLException{
		ArrayList<String> tableList = tableList();
		boolean tableExists = false;
		if (tableList.size()>0) {
			for(int i=0; i<tableList.size(); i++) {
				if(tableList.get(i) == username) {
					tableExists = true;
				}
			}
		}
		if (!tableExists) {
			String statement = "create table " + username + " (contactname TEXT, contactnumber INT);";
			Statement stmt = null;
			try {
				stmt = con.createStatement();
				stmt.executeUpdate(statement);
			}
			catch(SQLException e) {
				System.err.println(e);
			}
		}
	}
	public static void addContact(String username, String contactName, int contactNumber) {
		try {
			checkTables(username);
		}catch(SQLException e) {
			System.err.println(e);
		}
		String query = "INSERT INTO " + username + " VALUES ('" + contactName + "', " + contactNumber + ");";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		}
		catch(SQLException e) {
			System.err.println(e);
		}
	}
	public static void editContactName(String username, String contactName, String newContactName) {
		try {
			checkTables(username);
		}catch(SQLException e) {
			System.err.println(e);
		}
		String query = "UPDATE " + username + " SET contactname='" + newContactName + "' WHERE contactname='" + contactName + "';";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		}
		catch(SQLException e) {
			System.err.println(e);
		}
	}
	public static void editContactNumber(String username, String contactName, int newContactNumber) {
		try {
			checkTables(username);
		}catch(SQLException e) {
			System.err.println(e);
		}
		String query = "UPDATE " + username + " SET contactnumber=" + newContactNumber + " WHILE contactname='" + contactName + "';";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		}catch(SQLException e) {
			System.err.println(e);
		}
	}
	public static void deleteContact(String username, String contactName) {
		try {
			checkTables(username);
		}catch(SQLException e) {
			System.err.println(e);
		}
		String query = "DELETE FROM " + username + "WHERE contactname='" + contactName + "';";
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		}catch(SQLException e) {
			System.err.println(e);
		}
	}
	public static void listContacts(String username) {
		String query = "SELECT * FROM " + username;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				System.out.println("Name: " + rs.getString(1) + "Number: " + rs.getInt(2));
			}
		}catch(SQLException e) {
			System.err.println(e);
		}
	}
}
