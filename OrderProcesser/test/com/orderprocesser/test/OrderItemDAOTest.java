package com.orderprocesser.test;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.orderprocesser.model.OrderItem;
import com.orderprocesser.model.OrderItemDAO;

import junit.framework.Assert;

public class OrderItemDAOTest {
	
	@Test
	public void testGetOrderItemById() throws SQLException {
		OrderItemDAO orderItemDao = new OrderItemDAO();
		OrderItem orderItem = orderItemDao.getOrderItemById(123, 348);
		
		

		Assert.assertEquals(orderItem.getSKU(), "SKU");

	}
	
	@Test
	public void testGetOrderItemByOrderId() throws SQLException {
		OrderItemDAO orderItemDao = new OrderItemDAO();
		OrderItem orderItem = (OrderItem) orderItemDao.getOrderItemByOrderId(348);
		
		

		Assert.assertEquals(orderItem.getSKU(), "SKU");

	}


}
