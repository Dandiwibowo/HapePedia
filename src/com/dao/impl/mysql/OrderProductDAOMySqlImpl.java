package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.OrderProductDAO;
import com.entity.Order;
import com.entity.OrderProduct;
import com.entity.Product;

public class OrderProductDAOMySqlImpl implements OrderProductDAO {

    private Connection conn;
	
	public OrderProductDAOMySqlImpl(Connection conn) {
		this.conn = conn;
    }
    
    @Override
    public List<OrderProduct> getAllOrderProducts() throws Exception {
        List<OrderProduct> listOfOrderProduct = new ArrayList<OrderProduct>();
		String sql = "SELECT * FROM `tb_order_product` INNER JOIN tb_order ON tb_order_product.tb_order_order_number = tb_order.order_number INNER JOIN tb_product ON tb_order_product.tb_product_id = tb_product.id";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Order order = new Order();
			order.setOrder_number(rs.getInt("order_number"));
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setStock(rs.getInt("stock"));
			product.setPrice(rs.getDouble("price"));
			product.setColor(rs.getString("color"));
			product.setGuarantee(rs.getString("guarantee"));
			product.setDescription(rs.getString("description"));
			product.setCreatedDate(rs.getTimestamp("created_date"));
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setId(rs.getInt("id"));
			orderProduct.setQty(rs.getInt("qty"));
			orderProduct.setTotal_price(rs.getDouble("total_price"));
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
			listOfOrderProduct.add(orderProduct);
		}
		return listOfOrderProduct;
    }

    @Override
    public OrderProduct getOrderProduct(int id) throws Exception {
        OrderProduct orderProduct = null;
		String sql = "SELECT * FROM `tb_order_product` INNER JOIN tb_order ON tb_order_product.tb_order_order_number = tb_order.order_number INNER JOIN tb_product ON tb_order_product.tb_product_id = tb_order_product.id WHERE `tb_order`.order_number=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			Order order = new Order();
			order.setOrder_number(rs.getInt("order_number"));
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setStock(rs.getInt("stock"));
			product.setPrice(rs.getDouble("price"));
			product.setColor(rs.getString("color"));
			product.setGuarantee(rs.getString("guarantee"));
			product.setDescription(rs.getString("description"));
			product.setCreatedDate(rs.getTimestamp("created_date"));
			orderProduct = new OrderProduct();
			orderProduct.setId(rs.getInt("id"));
			orderProduct.setQty(rs.getInt("qty"));
			orderProduct.setTotal_price(rs.getDouble("total_price"));
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
		}
		return orderProduct;
    }

    @Override
    public List<OrderProduct> getOrderProductByOrder(int id) throws Exception {
        List<OrderProduct> listOfOrderProduct = new ArrayList<OrderProduct>();
		String sql = "SELECT * FROM `tb_order_product` INNER JOIN tb_order ON tb_order_product.tb_order_order_number = tb_order.order_number INNER JOIN tb_product ON tb_order_product.tb_product_id = tb_product.id WHERE tb_order_product.tb_order_order_number=?";
		PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Order order = new Order();
			order.setOrder_number(rs.getInt("order_number"));
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setStock(rs.getInt("stock"));
			product.setPrice(rs.getDouble("price"));
			product.setColor(rs.getString("color"));
			product.setGuarantee(rs.getString("guarantee"));
			product.setDescription(rs.getString("description"));
			product.setCreatedDate(rs.getTimestamp("created_date"));
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setId(rs.getInt("id"));
			orderProduct.setQty(rs.getInt("qty"));
			orderProduct.setTotal_price(rs.getDouble("total_price"));
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
			listOfOrderProduct.add(orderProduct);
		}
		return listOfOrderProduct;
    }

    @Override
    public List<OrderProduct> getOrderProductByUser(int id) throws Exception {
        List<OrderProduct> listOfOrderProduct = new ArrayList<OrderProduct>();
		String sql = "SELECT * FROM `tb_order_product` INNER JOIN tb_order ON tb_order_product.tb_order_order_number = tb_order.order_number INNER JOIN tb_product ON tb_order_product.tb_product_id = tb_product.id WHERE tb_order.tb_customers_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Order order = new Order();
			order.setOrder_number(rs.getInt("order_number"));
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setStock(rs.getInt("stock"));
			product.setPrice(rs.getDouble("price"));
			product.setColor(rs.getString("color"));
			product.setGuarantee(rs.getString("guarantee"));
			product.setDescription(rs.getString("description"));
			product.setCreatedDate(rs.getTimestamp("created_date"));
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setId(rs.getInt("id"));
			orderProduct.setQty(rs.getInt("qty"));
			orderProduct.setTotal_price(rs.getDouble("total_price"));
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
			listOfOrderProduct.add(orderProduct);
		}
		return listOfOrderProduct;
    }

    @Override
    public int addOrderProduct(OrderProduct order) throws Exception {
        String sql = "INSERT INTO `tb_order_product`(`qty`, `total_price`, `tb_order_order_number`, `tb_product_id`) VALUES (?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, order.getQty());
		pst.setDouble(2, order.getTotal_price());
		pst.setInt(3, order.getOrder().getOrder_number());
		pst.setInt(4, order.getProduct().getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateOrderProduct(OrderProduct order) throws Exception {
        String sql = "UPDATE `tb_order_product` SET `qty`=?,`total_price`=?,`tb_order_order_number`=?,`tb_product_id`=? WHERE id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, order.getQty());
		pst.setDouble(2, order.getTotal_price());
		pst.setInt(3, order.getOrder().getOrder_number());
		pst.setInt(4, order.getProduct().getId());
		pst.setInt(5, order.getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteOrderProduct(int id) throws Exception {
        String sql = "DELETE FROM `tb_order_product` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
