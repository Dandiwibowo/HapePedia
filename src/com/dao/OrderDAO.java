package com.dao;

import java.util.List;

import com.entity.Order;

public interface OrderDAO {
    public List<Order> getAllOrders() throws Exception;
    public Order getOrder(int id) throws Exception;
    public List<Order> getOrderByUser(int id) throws Exception;
    public int addOrder(Order order) throws Exception;
    public int updateOrder(Order order) throws Exception;
    public int deleteOrder(int id) throws Exception;
}
