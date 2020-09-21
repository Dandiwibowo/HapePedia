package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.OrderDAO;
import com.entity.Customers;
import com.entity.Order;

public class OrderDAOMySqlImpl implements OrderDAO {

    private Connection conn;
	
	public OrderDAOMySqlImpl(Connection conn) {
		this.conn = conn;
    }
    
    @Override
    public List<Order> getAllOrders() throws Exception {
        List<Order> listOfOrder = new ArrayList<Order>();
		String sql = "SELECT * FROM `tb_order` JOIN tb_customers WHERE tb_order.tb_customers_id = tb_customers.id";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Customers customers = new Customers();
            customers.setId(rs.getInt("id"));
			customers.setFullname(rs.getString("nama"));
			customers.setUsername(rs.getString("username"));
			customers.setPassword(rs.getString("password"));
			customers.setAddress(rs.getString("address"));
			customers.setGender(rs.getString("gender"));
			customers.setBirth(rs.getString("birth"));
            customers.setCreatedDate(rs.getTimestamp("created_date"));
            
			Order order = new Order();
			order.setOrder_number(rs.getInt("order_number"));
			order.setCustomers(customers);
			listOfOrder.add(order);
		}
		return listOfOrder;
    }

    @Override
    public Order getOrder(int id) throws Exception {
        Order order = null;
        String sql = "SELECT * FROM `tb_order` JOIN tb_customers WHERE tb_order.tb_customers_id = tb_customers.id and tb_order.order_number =?";
		PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Customers customers = new Customers();
            customers.setId(rs.getInt("id"));
			customers.setFullname(rs.getString("nama"));
			customers.setUsername(rs.getString("username"));
			customers.setPassword(rs.getString("password"));
			customers.setAddress(rs.getString("address"));
			customers.setGender(rs.getString("gender"));
			customers.setBirth(rs.getString("birth"));
            customers.setCreatedDate(rs.getTimestamp("created_date"));
            
			order = new Order();
			order.setOrder_number(rs.getInt("order_number"));
			order.setCustomers(customers);
			
		}
		return order;
    }

    @Override
    public List<Order> getOrderByUser(int id) throws Exception {
        List<Order> listOfOrder = new ArrayList<Order>();
		String sql = "SELECT * FROM `tb_order` JOIN tb_customers WHERE tb_order.tb_customers_id = tb_customers.id and tb_order.tb_customers_id =?";
		PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Customers customers = new Customers();
            customers.setId(rs.getInt("id"));
			customers.setFullname(rs.getString("nama"));
			customers.setUsername(rs.getString("username"));
			customers.setPassword(rs.getString("password"));
			customers.setAddress(rs.getString("address"));
			customers.setGender(rs.getString("gender"));
			customers.setBirth(rs.getString("birth"));
            customers.setCreatedDate(rs.getTimestamp("created_date"));
            
			Order order = new Order();
			order.setOrder_number(rs.getInt("order_number"));
			order.setCustomers(customers);
			listOfOrder.add(order);
		}
		return listOfOrder;
    }

    @Override
    public int addOrder(Order order) throws Exception {
        String sql = "INSERT INTO `tb_order`(`order_number`, `tb_customers_id`) VALUES (?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, order.getOrder_number());
		pst.setInt(2, order.getCustomers().getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateOrder(Order order) throws Exception {
        String sql = "UPDATE `tb_order` SET `order_number`=?,`tb_customers_id`=? WHERE order_number=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, order.getOrder_number());
		pst.setInt(2, order.getCustomers().getId());
		pst.setInt(3, order.getOrder_number());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteOrder(int id) throws Exception {
        String sql = "DELETE FROM `tb_order` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
