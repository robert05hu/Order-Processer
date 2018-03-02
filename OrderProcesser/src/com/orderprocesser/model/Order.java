package com.orderprocesser.model;

import java.util.Date;

public class Order {

	private int OrderId;
	private String BuyerName;
	private String BuyerEmail;
	private Date OrderDate;
	private double OrderTotalValue;
	private String Address;
	private int Postcode;

	public int getOrderId() {
		return OrderId;
	}

	public String getBuyerName() {
		return BuyerName;
	}

	public String getBuyerEmail() {
		return BuyerEmail;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public double getOrderTotalValue() {
		return OrderTotalValue;
	}

	public String getAddress() {
		return Address;
	}

	public int getPostcode() {
		return Postcode;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public void setBuyerName(String buyerName) {
		BuyerName = buyerName;
	}

	public void setBuyerEmail(String buyerEmail) {
		BuyerEmail = buyerEmail;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public void setOrderTotalValue(double orderTotalValue) {
		OrderTotalValue = orderTotalValue;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public void setPostcode(int postcode) {
		Postcode = postcode;
	}

}
