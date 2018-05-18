package com.sajib.sqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connector {
	
	private static connector instance = null;
	
	private static final String username = "root";
	private static final String password = "root1234";
	private static final String URL = "jdbc:mysql://localhost:3306/phonebook?useSSL=false&serverTimezone=UTC";
	
	private Connection connection = null;

	private connector() {
		
	}
	
	public static connector getInstance() {
		if(instance == null) {
			instance = new connector();
		}
		return instance;
	}
	
	private boolean openConnection() {
		try {
			connection = DriverManager.getConnection(URL, username, password);
			return true;
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
			}
		}
	public Connection getConnection() {
		if(connection == null) {
			if(openConnection()) {
				return connection;
			}
			
			else {
				return null;
			}
		}
		return connection;
	}
	public void close() {
		System.out.println("The connection is closed");
		try {
			connection.close();
			connection = null;
		}
		catch(Exception e) {
			System.err.println(e);
		}
	}
}
