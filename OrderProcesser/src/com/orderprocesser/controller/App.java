package com.orderprocesser.controller;

import com.orderprocesser.model.Database;
import com.orderprocesser.model.FTP;
import com.orderprocesser.model.OrderDAO;

public class App {

	public static void main(String[] args) {

		String inputFile = "C:/Users/Robi/Downloads/inputFile.csv";
		String responseFile = "C:/Users/Robi/Downloads/responseFile.csv";
		CSVParser.parse(inputFile, responseFile);
		
		FTP.upload("C:/Users/Robi/Downloads/responseFile.csv");

	}

}
