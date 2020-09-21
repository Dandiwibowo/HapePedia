package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.ProductDAO;
import com.entity.Category;
import com.entity.Product;

public class ProductDAOMySqlImpl implements ProductDAO {

    private Connection conn;
	
	public ProductDAOMySqlImpl(Connection conn) {
		this.conn = conn;
    }
    
    @Override
    public List<Product> getAllProducts() throws Exception {
        List<Product> listOfProduct = new ArrayList<Product>();
		String sql = "SELECT * FROM `tb_product` INNER JOIN tb_category ON tb_product.tb_category_id = tb_category.id";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Category category = new Category();
			category.setId(rs.getInt("id"));
			category.setCategory(rs.getString("category"));
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setStock(rs.getInt("stock"));
			product.setPrice(rs.getDouble("price"));
			product.setColor(rs.getString("color"));
			product.setGuarantee(rs.getString("guarantee"));
			product.setDescription(rs.getString("description"));
			product.setCreatedDate(rs.getTimestamp("created_date"));
			product.setCategoryId(category);
			listOfProduct.add(product);
		}
		return listOfProduct;
    }

    @Override
    public Product getProduct(int id) throws Exception {
        Product product = null;
		String sql = "SELECT * FROM `tb_product` INNER JOIN tb_category ON tb_product.tb_category_id = tb_category.id WHERE tb_product.id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			Category category = new Category();
			category.setId(rs.getInt("id"));
			category.setCategory(rs.getString("category"));
			product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setStock(rs.getInt("stock"));
			product.setPrice(rs.getDouble("price"));
			product.setColor(rs.getString("color"));
			product.setGuarantee(rs.getString("guarantee"));
			product.setDescription(rs.getString("description"));
			product.setCreatedDate(rs.getTimestamp("created_date"));
			product.setCategoryId(category);
		}
		return product;
    }

    @Override
    public List<Product> getProductByKeyword(String keyword) throws Exception {
        List<Product> listOfProduct = new ArrayList<Product>();
		String sql = "SELECT * FROM `tb_product` INNER JOIN tb_category ON tb_product.tb_category_id = tb_category.id WHERE tb_product.product_name=? OR tb_product.color=? OR tb_category.category=?";
		PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, keyword);
        pst.setString(2, keyword);
        pst.setString(3, keyword);
        ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Category category = new Category();
			category.setId(rs.getInt("id"));
			category.setCategory(rs.getString("category"));
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setProductName(rs.getString("product_name"));
			product.setStock(rs.getInt("stock"));
			product.setPrice(rs.getDouble("price"));
			product.setColor(rs.getString("color"));
			product.setGuarantee(rs.getString("guarantee"));
			product.setDescription(rs.getString("description"));
			product.setCreatedDate(rs.getTimestamp("created_date"));
			product.setCategoryId(category);
			listOfProduct.add(product);
		}
		return listOfProduct;
    }

    @Override
    public int addProduct(Product product) throws Exception {
        String sql = "INSERT INTO `tb_product`(`product_name`, `stock`, `price`, `color`, `guarantee`, `description`,  `tb_category_id`) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, product.getProductName());
		pst.setInt(2, product.getStock());
		pst.setDouble(3, product.getPrice());
		pst.setString(4, product.getColor());
		pst.setString(5, product.getGuarantee());
		pst.setString(6, product.getDescription());
		pst.setLong(7, product.getCategoryId().getId());
		// System.out.println(pst);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateProduct(Product product) throws Exception {
        String sql = "UPDATE `tb_product` SET `product_name`=?,`stock`=?,`price`=?,`color`=?,`guarantee`=?,`description`=? WHERE id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, product.getProductName());
		pst.setInt(2, product.getStock());
		pst.setDouble(3, product.getPrice());
		pst.setString(4, product.getColor());
		pst.setString(5, product.getGuarantee());
		pst.setString(6, product.getDescription());
		pst.setLong(7, product.getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteProduct(int id) throws Exception {
        String sql = "DELETE FROM `tb_product` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
