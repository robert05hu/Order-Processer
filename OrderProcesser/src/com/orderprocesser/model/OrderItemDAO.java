package com.orderprocesser.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class OrderItemDAO {

	public OrderItem getOrderItemById(int orderItemId, int orderId) throws SQLException {

		String query = "SELECT * FROM order_item" + " WHERE order_item.OrderItemId = " + orderItemId
				+ " AND order_item.OrderId = " + orderId;

		OrderItem orderItem = new OrderItem();

		ResultSet rs = Database.getConn().createStatement().executeQuery(query);

		while (rs.next()) {

			orderItem.setOrderItemId(rs.getInt("OrderItemId"));
			orderItem.setOrderId(rs.getInt("OrderId"));
			orderItem.setSalePrice(rs.getDouble("SalePrice"));
			orderItem.setShippingPrice(rs.getDouble("ShippingPrice"));
			orderItem.setTotalItemPrice(rs.getDouble("TotalItemPrice"));
			orderItem.setSKU(rs.getString("SKU"));
			orderItem.setStatus(rs.getString("Status"));

		}
		return orderItem;
	}

	public List<OrderItem> getOrderItemByOrderId(int orderId) throws SQLException {

		String query = "SELECT * FROM order_item" + " WHERE OrderId = " + orderId;

		List<OrderItem> list = new ArrayList<>();

		ResultSet rs = Database.getConn().createStatement().executeQuery(query);

		while (rs.next()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderItemId(rs.getInt("OrderItemId"));
			orderItem.setOrderId(rs.getInt("OrderId"));
			orderItem.setSalePrice(rs.getDouble("SalePrice"));
			orderItem.setShippingPrice(rs.getDouble("ShippingPrice"));
			orderItem.setTotalItemPrice(rs.getDouble("TotalItemPrice"));
			orderItem.setSKU(rs.getString("SKU"));
			orderItem.setStatus(rs.getString("Status"));

		}

		return list;
	}

	public void addOrderItem(OrderItem orderItem) throws SQLException {

		Connection conn = Database.getConn();
		PreparedStatement preparedStmt;

		preparedStmt = (PreparedStatement) conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
		preparedStmt.execute();

		String query = " INSERT INTO order_item (OrderItemId, OrderId, SalePrice, ShippingPrice, TotalItemPrice, SKU, Status)"
				+ " values (?, ?, ?, ?, ?, ?, ?);" + "";

		preparedStmt = (PreparedStatement) conn.prepareStatement(query);

		preparedStmt.setInt(1, orderItem.getOrderItemId());
		preparedStmt.setInt(2, orderItem.getOrderId());
		preparedStmt.setDouble(3, orderItem.getSalePrice());
		preparedStmt.setDouble(4, orderItem.getShippingPrice());
		preparedStmt.setDouble(5, orderItem.getTotalItemPrice());
		preparedStmt.setString(6, orderItem.getSKU());
		preparedStmt.setString(7, orderItem.getStatus());

		preparedStmt.execute();

		preparedStmt = (PreparedStatement) conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
		preparedStmt.execute();

	}

}
