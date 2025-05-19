package com.ecom.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
	
	private String url="jdbc:mysql://localhost:3306/ecommerce";
	private String userDB = "root";
	private String passDB = "root"; 
	private String driver = "com.mysql.cj.jdbc.Driver";
	private Connection conn; 
	private static DBUtility db = new DBUtility();
	
	private DBUtility() { } 
    
	public static DBUtility getInstance() { 
		return db;
	}  
	
	public Connection connect() {
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			conn =  DriverManager.getConnection(url, userDB, passDB);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return conn; 
	}
	
	public void close() {
		try {
			if(!conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
