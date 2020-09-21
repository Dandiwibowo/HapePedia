package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.CategoryDAO;
import com.entity.Category;

public class CategoryDAOMySqlImpl implements CategoryDAO {

    private Connection conn;
	
	public CategoryDAOMySqlImpl(Connection conn) {
		this.conn = conn;
	}


    @Override
    public Category getCategory(int id) throws Exception {
        Category category = null;
		String sql = "select * from tb_category where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			category = new Category();
			category.setId(rs.getInt("id"));
			category.setCategory(rs.getString("category"));
		}
		return category;
	}
	
	@Override
    public Category getCategoryByString(String categoryString) throws Exception {
        Category category = null;
		String sql = "select * from tb_category where category=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, categoryString);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			category = new Category();
			category.setId(rs.getInt("id"));
			category.setCategory(rs.getString("category"));
		}
		return category;
    }

    @Override
    public int addCategory(Category category) throws Exception {
        String sql = "insert into `tb_category`(`category`) VALUES (?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, category.getCategory());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateCategory(Category category) throws Exception {
        String sql = "UPDATE `tb_category` SET `category`=? WHERE id= ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, category.getCategory());
		pst.setInt(2, category.getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteCategory(int id) throws Exception {
        String sql = "DELETE FROM `tb_category` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public List<Category> getAllCategories() throws Exception {
        List<Category> listOfCategory = new ArrayList<Category>();
		String sql = "select * from tb_category";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			Category category = new Category();
			category.setId(rs.getInt("id"));
			category.setCategory(rs.getString("category"));
			listOfCategory.add(category);
		}
		return listOfCategory;
    }
    
}
