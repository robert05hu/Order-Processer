package com.orderprocesser.validator;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import com.orderprocesser.model.Order;
import com.orderprocesser.model.OrderDAO;
import com.orderprocesser.model.OrderItem;
import com.orderprocesser.model.OrderItemDAO;

public class OrderValidator {

	public static int validate(String orderItemId, String orderId, String buyerName, String buyerEmail, String address,
			String postcode, String salePrice, String shippingPrice, String SKU, String status, String orderDate) {

		double newShippingPrice = Double.parseDouble(shippingPrice);
		double newSalePrice = Double.parseDouble(salePrice);

		// e-mail checking
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(buyerEmail);
		if (m.matches() == false)
			return 1;

		// empty string checking
		if (orderId.isEmpty() || buyerName.isEmpty() || buyerEmail.isEmpty() || address.isEmpty() || postcode.isEmpty())
			return 2;

		// empty date checking
		if (orderDate.isEmpty()) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			orderDate = dtf.format(localDate);
		}

		// date parsing
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date newOrderDate = null;
		try {
			newOrderDate = df.parse(orderDate);
		} catch (ParseException e) {
			return 3;
		}

		// postcode checking
		int newPostCode = 0;
		try {
			newPostCode = Integer.parseInt(postcode);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return 4;
		}

		if (orderItemId.isEmpty() || salePrice.isEmpty() || shippingPrice.isEmpty() || SKU.isEmpty() || status.isEmpty()
				|| orderId.isEmpty())
			return 2;
		// checking shipping price
		if (newShippingPrice < 0)
			return 8;

		// checking sale price
		if (newSalePrice < 1)
			return 9;

		// checking status
		int statusCount = 0;

		if (status.equals("IN_STOCK") || status.equals("OUT_OF_STOCK")) {
			statusCount = 1;
		}

		if (statusCount == 0)
			return 10;

		// checking for duplicates
		int newOrderItemId = Integer.parseInt(orderItemId);
		int newOrderId = Integer.parseInt(orderId);

		OrderItemDAO orderItemDao = new OrderItemDAO();

		OrderItem testOrderItem = new OrderItem();

		try {
			testOrderItem = orderItemDao.getOrderItemById(newOrderItemId, newOrderId);
		} catch (SQLException e1) {
			return 11;
		}

		if (testOrderItem.getOrderItemId() == newOrderItemId && testOrderItem.getOrderId() == newOrderId)
			return 12;

		// add item
		OrderItem orderItem = new OrderItem();
		double totalItemPrice = Double.parseDouble(shippingPrice) + Double.parseDouble(salePrice);

		orderItem.setOrderItemId(Integer.parseInt(orderItemId));
		orderItem.setOrderId(Integer.parseInt(orderId));
		orderItem.setSalePrice(Double.parseDouble(salePrice));
		orderItem.setShippingPrice(Double.parseDouble(shippingPrice));
		orderItem.setTotalItemPrice(totalItemPrice);
		orderItem.setSKU(SKU);
		orderItem.setStatus(status);

		try {
			orderItemDao.addOrderItem(orderItem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 13;
		}

		OrderDAO orderDao = new OrderDAO();
		Order order = new Order();

		// checking for duplicate
		try {
			order = orderDao.getOrderById(newOrderId);
			if (order.getOrderId() == newOrderId) {
				orderDao.updateOrderTotalValue(newOrderId, totalItemPrice);
				return 0;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			return 11;
		}

		// add item
		order.setOrderId(newOrderId);
		order.setBuyerName(buyerName);
		order.setBuyerEmail(buyerEmail);
		order.setOrderDate(newOrderDate);
		order.setOrderTotalValue(totalItemPrice);
		order.setAddress(address);
		order.setPostcode(newPostCode);

		try {
			orderDao.addOrder(order);
		} catch (SQLException e) {
			return 7;
		}

		return 0;

	}

}
