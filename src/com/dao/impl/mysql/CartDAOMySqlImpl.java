package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.CartDAO;
import com.entity.Customers;
import com.entity.Product;
import com.entity.Cart;

public class CartDAOMySqlImpl implements CartDAO {
    private Connection conn;

    public CartDAOMySqlImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Cart> getAllCarts() throws Exception {
        List<Cart> listOfCart = new ArrayList<Cart>();
		String sql = "SELECT * FROM `tb_wishlist` INNER JOIN tb_product ON tb_wishlist.tb_product_id=tb_product.id INNER JOIN tb_customers ON tb_customers.id=tb_wishlist.tb_customers_id";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setStock(rs.getInt("stock"));
			product.setPrice(rs.getDouble("price"));
			product.setColor(rs.getString("color"));
			product.setGuarantee(rs.getString("guarantee"));
			product.setDescription(rs.getString("description"));
			product.setCreatedDate(rs.getTimestamp("created_date"));
            Customers customers = new Customers();
            customers.setId(rs.getInt("id"));
			customers.setFullname(rs.getString("fullname"));
			customers.setUsername(rs.getString("username"));
			customers.setPassword(rs.getString("password"));
			customers.setAddress(rs.getString("address"));
			customers.setGender(rs.getString("gender"));
			customers.setBirth(rs.getString("birth"));
			customers.setCreatedDate(rs.getTimestamp("created_date"));
            Cart wishlist = new Cart();
			wishlist.setId(rs.getInt("id"));
			wishlist.setProduct(product);
			wishlist.setCustomers(customers);
			listOfCart.add(wishlist);
		}
		return listOfCart;
    }

    @Override
    public Cart getCart(int id) throws Exception {
        Cart wishlist = null;
		String sql = "SELECT * FROM `tb_wishlist` INNER JOIN tb_product ON tb_wishlist.tb_product_id=tb_product.id INNER JOIN tb_customers ON tb_customers.id=tb_wishlist.tb_customers_id WHERE tb_wishlist.id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setStock(rs.getInt("stock"));
			product.setPrice(rs.getDouble("price"));
			product.setColor(rs.getString("color"));
			product.setGuarantee(rs.getString("guarantee"));
			product.setDescription(rs.getString("description"));
			product.setCreatedDate(rs.getTimestamp("created_date"));
            Customers customers = new Customers();
            customers.setId(rs.getInt("id"));
			customers.setFullname(rs.getString("fullname"));
			customers.setUsername(rs.getString("username"));
			customers.setPassword(rs.getString("password"));
			customers.setAddress(rs.getString("address"));
			customers.setGender(rs.getString("gender"));
			customers.setBirth(rs.getString("birth"));
			customers.setCreatedDate(rs.getTimestamp("created_date"));
            wishlist = new Cart();
			wishlist.setId(rs.getInt("id"));
			wishlist.setProduct(product);
			wishlist.setCustomers(customers);
		}
		return wishlist;
    }

    @Override
    public List<Cart> getCartByUser(int id) throws Exception {
        List<Cart> listOfCart = new ArrayList<Cart>();
		String sql = "SELECT * FROM `tb_wishlist` INNER JOIN tb_product ON tb_wishlist.tb_product_id=tb_product.id INNER JOIN tb_customers ON tb_customers.id=tb_wishlist.tb_customers_id WHERE tb_customers.id=1";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setStock(rs.getInt("stock"));
			product.setPrice(rs.getDouble("price"));
			product.setColor(rs.getString("color"));
			product.setGuarantee(rs.getString("guarantee"));
			product.setDescription(rs.getString("description"));
			product.setCreatedDate(rs.getTimestamp("created_date"));
            Customers customers = new Customers();
            customers.setId(rs.getInt("id"));
			customers.setFullname(rs.getString("fullname"));
			customers.setUsername(rs.getString("username"));
			customers.setPassword(rs.getString("password"));
			customers.setAddress(rs.getString("address"));
			customers.setGender(rs.getString("gender"));
			customers.setBirth(rs.getString("birth"));
			customers.setCreatedDate(rs.getTimestamp("created_date"));
            Cart wishlist = new Cart();
			wishlist.setId(rs.getInt("id"));
			wishlist.setProduct(product);
			wishlist.setCustomers(customers);
			listOfCart.add(wishlist);
		}
		return listOfCart;
    }

    @Override
    public int addCart(Cart product) throws Exception {
        String sql = "INSERT INTO `tb_wishlist`(`tb_product_id`, `tb_customers_id`) VALUES (?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, product.getProduct().getId());
		pst.setInt(2, product.getCustomers().getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateCart(Cart product) throws Exception {
        String sql = "UPDATE `tb_wishlist` SET `tb_product_id`=?, `tb_customers_id`=? WHERE tb_wishlist.id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, product.getProduct().getId());
		pst.setInt(2, product.getCustomers().getId());
		pst.setInt(3, product.getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteCart(int id) throws Exception {
        String sql = "DELETE FROM `tb_wishlist` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
}
