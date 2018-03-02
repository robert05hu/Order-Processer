package com.orderprocesser.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.log.Log;
import com.orderprocesser.controller.App;

public class Database {

	private static Properties prop = new Properties();
	private static InputStream input = null;
	private static Connection conn = null;

	public static Connection getConn() {
		return conn;
	}

	public static void connect() {
		try {

			String filename = "database.properties";
			input = App.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
			}

			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		System.out.println("Connecting database...");

		try {
			conn = (Connection) DriverManager.getConnection(url, username, password);
			System.out.println("Database connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void disconnect(){
		try {
			conn.close();
			System.out.println("Database closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
