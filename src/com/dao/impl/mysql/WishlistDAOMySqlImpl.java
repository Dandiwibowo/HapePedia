package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.WishlistDAO;
import com.entity.Customers;
import com.entity.Product;
import com.entity.Wishlist;

public class WishlistDAOMySqlImpl implements WishlistDAO {
    private Connection conn;

    public WishlistDAOMySqlImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Wishlist> getAllWishlists() throws Exception {
        List<Wishlist> listOfWishlist = new ArrayList<Wishlist>();
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
            Wishlist wishlist = new Wishlist();
			wishlist.setId(rs.getInt("id"));
			wishlist.setProduct(product);
			wishlist.setCustomers(customers);
			listOfWishlist.add(wishlist);
		}
		return listOfWishlist;
    }

    @Override
    public Wishlist getWishlist(int id) throws Exception {
        Wishlist wishlist = null;
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
            wishlist = new Wishlist();
			wishlist.setId(rs.getInt("id"));
			wishlist.setProduct(product);
			wishlist.setCustomers(customers);
		}
		return wishlist;
    }

    @Override
    public List<Wishlist> getWishlistByUser(int id) throws Exception {
        List<Wishlist> listOfWishlist = new ArrayList<Wishlist>();
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
            Wishlist wishlist = new Wishlist();
			wishlist.setId(rs.getInt("id"));
			wishlist.setProduct(product);
			wishlist.setCustomers(customers);
			listOfWishlist.add(wishlist);
		}
		return listOfWishlist;
    }

    @Override
    public int addWishlist(Wishlist product) throws Exception {
        String sql = "INSERT INTO `tb_wishlist`(`tb_product_id`, `tb_customers_id`) VALUES (?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, product.getProduct().getId());
		pst.setInt(2, product.getCustomers().getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateWishlist(Wishlist product) throws Exception {
        String sql = "UPDATE `tb_wishlist` SET `tb_product_id`=?, `tb_customers_id`=? WHERE tb_wishlist.id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, product.getProduct().getId());
		pst.setInt(2, product.getCustomers().getId());
		pst.setInt(3, product.getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteWishlist(int id) throws Exception {
        String sql = "DELETE FROM `tb_wishlist` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
}
