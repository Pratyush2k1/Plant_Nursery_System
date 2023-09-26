package com.amdocs.pns.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_conn {
	public Connection getconnection() {
			Connection conn = null;
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("Inside try after class.forname");
				conn=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:orcl","scott","tiger");

				// Checking if the connection is successful
				if (conn != null) {
					System.out.println("Connected to the SQL database!");
				} else {
					System.out.println("Failed to connect to the database.");
				}
			} catch(Exception e) {
				
			}
			return conn;
	}
}
