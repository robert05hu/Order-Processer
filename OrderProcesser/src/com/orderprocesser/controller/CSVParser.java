package com.orderprocesser.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.orderprocesser.model.Database;
import com.orderprocesser.model.Order;
import com.orderprocesser.model.OrderDAO;
import com.orderprocesser.model.OrderItemDAO;
import com.orderprocesser.validator.OrderValidator;
import com.orderprocesser.validator.OrderValidator;

public class CSVParser {

	public static final String delimiter = ";";

	public static void parse(String inputFile, String responseFile) {

		try {
			Database.connect();
			File iFile = new File(inputFile);
			File rFile = new File(responseFile);
			FileWriter fw = new FileWriter(rFile);
			FileReader fr = new FileReader(iFile);
			BufferedReader br = new BufferedReader(fr);

			String line = "";
			String[] records;

			while ((line = br.readLine()) != null) {

				records = line.split(delimiter);

				if (records[0].isEmpty())
					continue;
				else
					fw.write(records[0] + ";");

				// line length checking
				/*
				 * if(records.length < 11 || records.length > 12) { fw.write("ERROR");
				 * fw.append(";"); fw.write("Line length does not fetch!");
				 * fw.write(System.lineSeparator()); continue; }
				 */

				int orderItemValidate = OrderValidator.validate(records[1], // OrderItemId
						records[2], // OrderId
						records[3], // BuyerName
						records[4], // BuyerEmail
						records[5], // Address
						records[6], // Postcode
						records[7], // SalePrice
						records[8], // ShippingPrice
						records[9], // SKU
						records[10], // Status
						records.length == 12 ? records[11] : "" // OrderDate with array.length checking
				);
				// order validating
				if (orderItemValidate != 0) {
					fw.write("ERROR");
					fw.append(";");
				} 

				// output errors:
				if (orderItemValidate == 1) {
					fw.write("Incorrect e-mail!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 2) {
					fw.write("Empty field found!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 3) {
					fw.write("Date format error!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 4) {
					fw.write("Postcode error!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 5) {
					fw.write("Database fetching error!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 6) {
					fw.write("Duplicate found!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 7) {
					fw.write("Database insert error!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 8) {
					fw.write("Shipping price error!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 9) {
					fw.write("Sale price error!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 10) {
					fw.write("Status error!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 11) {
					fw.write("Database fetching error!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 12) {
					fw.write("Duplicate found!");
					fw.write(System.lineSeparator());
					continue;
				} else if (orderItemValidate == 13) {
					fw.write("Database insert error!");
					fw.write(System.lineSeparator());
					continue;
				}

				fw.write("OK");
				fw.write(System.lineSeparator());

			}

			br.close();
			fw.close();
			Database.disconnect();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
