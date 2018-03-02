package com.orderprocesser.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class OrderDAO {

	/*
	 * public List<Order> getOrders() {
	 * 
	 * String query = "SELECT * FROM `order`;"; List<Order> list = new
	 * ArrayList<>(); try { ResultSet rs =
	 * Database.getConn().createStatement().executeQuery(query);
	 * 
	 * while (rs.next()) {
	 * 
	 * Order order = new Order();
	 * 
	 * order.setOrderId(rs.getInt("OrderId"));
	 * order.setBuyerName(rs.getString("BuyerName"));
	 * order.setBuyerEmail(rs.getString("BuyerEmail"));
	 * order.setOrderDate(rs.getDate("OrderDate"));
	 * order.setOrderTotalValue(rs.getDouble("OrderTotalValue"));
	 * order.setAddress(rs.getString("Address"));
	 * order.setPostcode(rs.getInt("Postcode"));
	 * 
	 * list.add(order); }
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block return null; }
	 * 
	 * return list; }
	 */
	public Order getOrderById(int orderId) throws SQLException {
		String query = "SELECT * FROM `order`" + "WHERE OrderId = " + orderId;

		Order order = new Order();

		ResultSet rs = Database.getConn().createStatement().executeQuery(query);

		while (rs.next()) {

			order.setOrderId(rs.getInt("OrderId"));
			order.setBuyerName(rs.getString("BuyerName"));
			order.setBuyerEmail(rs.getString("BuyerEmail"));
			order.setOrderDate(rs.getDate("OrderDate"));
			order.setOrderTotalValue(rs.getDouble("OrderTotalValue"));
			order.setAddress(rs.getString("Address"));
			order.setPostcode(rs.getInt("Postcode"));

		}

		return order;

	}

	public void addOrder(Order order) throws SQLException {

		Connection conn = Database.getConn();

		String query = " INSERT INTO `order` (OrderId, BuyerName, BuyerEmail, OrderDate, OrderTotalValue, Address, Postcode)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";

		OrderItemDAO orderItemDao = new OrderItemDAO();

		PreparedStatement preparedStmt;

		preparedStmt = (PreparedStatement) conn.prepareStatement(query);

		preparedStmt.setInt(1, order.getOrderId());
		preparedStmt.setString(2, order.getBuyerName());
		preparedStmt.setString(3, order.getBuyerEmail());
		preparedStmt.setDate(4, new java.sql.Date(order.getOrderDate().getTime()));
		preparedStmt.setDouble(5, order.getOrderTotalValue());
		preparedStmt.setString(6, order.getAddress());
		preparedStmt.setInt(7, order.getPostcode());

		preparedStmt.execute();

	}

	public void updateOrderTotalValue(int orderId, double totalItemPrice) throws SQLException {

		String query = "SELECT * FROM `order`" + " WHERE OrderId = " + orderId;
		Connection conn = Database.getConn();

		ResultSet rs = conn.createStatement().executeQuery(query);

		while (rs.next()) {
			totalItemPrice += rs.getDouble("OrderTotalValue");
		}

		query = "UPDATE `order` SET OrderTotalValue = ? WHERE OrderId = ?";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);

		ps.setDouble(1, totalItemPrice);
		ps.setInt(2, orderId);
		ps.executeUpdate();
		ps.close();

	}

}
