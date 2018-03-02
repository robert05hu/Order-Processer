package com.orderprocesser.model;

public class OrderItem {

	private int OrderItemId;
	private int OrderId;
	private double SalePrice;
	private double ShippingPrice;
	private double TotalItemPrice;
	private String SKU;
	private String Status;

	public int getOrderItemId() {
		return OrderItemId;
	}

	public int getOrderId() {
		return OrderId;
	}

	public double getSalePrice() {
		return SalePrice;
	}

	public double getShippingPrice() {
		return ShippingPrice;
	}

	public double getTotalItemPrice() {
		return TotalItemPrice;
	}

	public String getSKU() {
		return SKU;
	}

	public void setOrderItemId(int orderItemId) {
		OrderItemId = orderItemId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public void setSalePrice(double salePrice) {
		SalePrice = salePrice;
	}

	public void setShippingPrice(double shippingPrice) {
		ShippingPrice = shippingPrice;
	}

	public void setTotalItemPrice(double totalItemPrice) {
		TotalItemPrice = totalItemPrice;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	};

}
