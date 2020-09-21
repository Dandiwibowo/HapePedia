package com.dao;

import java.util.List;

import com.entity.OrderProduct;

public interface OrderProductDAO {
    public List<OrderProduct> getAllOrderProducts() throws Exception;
    public OrderProduct getOrderProduct(int id) throws Exception;
    public List<OrderProduct> getOrderProductByOrder(int id) throws Exception;
    public List<OrderProduct> getOrderProductByUser(int id) throws Exception;
    public int addOrderProduct(OrderProduct order) throws Exception;
    public int updateOrderProduct(OrderProduct order) throws Exception;
    public int deleteOrderProduct(int id) throws Exception;
}
