package com.orderprocesser.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.orderprocesser.controller.App;

public class FTP {

	private static final int BUFFER_SIZE = 4096;
	static Properties prop = new Properties();
	static InputStream input = null;
	static Connection conn = null;

	public static void upload(String filePath) {

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

		String ftpUrl = prop.getProperty("ftpurl");
		String username = prop.getProperty("ftpusername");
		String password = prop.getProperty("ftppassword");
		String host = prop.getProperty("ftphost");
		String uploadPath = "responseFile.csv";
		ftpUrl = String.format(ftpUrl, username, password, host, uploadPath);
		System.out.println("Upload URL: " + ftpUrl);

		try {
			URL url = new URL(ftpUrl);
			URLConnection conn = url.openConnection();
			OutputStream outputStream = conn.getOutputStream();
			FileInputStream inputStream = new FileInputStream(filePath);

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outputStream.close();

			System.out.println("File uploaded");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
