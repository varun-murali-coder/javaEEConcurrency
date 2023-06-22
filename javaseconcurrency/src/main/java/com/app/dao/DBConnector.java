package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//com.mysql.cj.jdbc.Driver
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/javase", "javase", "javase");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}

}
