package com.orderprocesser.test;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.orderprocesser.model.Order;
import com.orderprocesser.model.OrderDAO;

import junit.framework.Assert;

public class OrderDAOTest {
	
	@Test
	public void testGetOrderById() throws SQLException {
		OrderDAO orderDao = new OrderDAO();
		Order order = orderDao.getOrderById(123);

		Assert.assertEquals(order.getBuyerName(), "Bob");

	}

}
