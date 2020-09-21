package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.ImageProductDAO;
import com.entity.ImageProduct;

public class ImageProductDAOMySqlImpl implements ImageProductDAO {

    private Connection conn;
	
	public ImageProductDAOMySqlImpl(Connection conn) {
		this.conn = conn;
    }
    
    @Override
    public List<ImageProduct> getAllImageProducts() throws Exception {
        List<ImageProduct> listOfImageProduct = new ArrayList<ImageProduct>();
		String sql = "SELECT * FROM `tb_image_product` JOIN `tb_product` WHERE `tb_image_product`.tb_product_id=`tb_product`.`id`";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			ImageProduct image = new ImageProduct();
			image.setId(rs.getInt("id"));
			image.setImages(rs.getString("images"));
			listOfImageProduct.add(image);
		}
		return listOfImageProduct;
    }

    @Override
    public ImageProduct getImageProduct(int id) throws Exception {
        ImageProduct image = null;
		String sql = "SELECT * FROM `tb_image_product` JOIN `tb_product` WHERE `tb_image_product`.tb_product_id=`tb_product`.`id` and `tb_image_product`.id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			image = new ImageProduct();
			image.setId(rs.getInt("id"));
			image.setImages(rs.getString("images"));
		}
		return image;
    }

    @Override
    public List<ImageProduct> getImageProductByProduct(int id) throws Exception {
        List<ImageProduct> listOfImageProduct = new ArrayList<ImageProduct>();
		String sql = "SELECT * FROM `tb_image_product` JOIN `tb_product` WHERE `tb_image_product`.tb_product_id=`tb_product`.`id` and `tb_image_product`.tb_product_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			ImageProduct image = new ImageProduct();
			image.setId(rs.getInt("id"));
			image.setImages(rs.getString("images"));
			listOfImageProduct.add(image);
		}
		return listOfImageProduct;
    }

    @Override
    public int addImageProduct(ImageProduct image) throws Exception {
        String sql = "INSERT INTO `tb_image_product`(`images`, `tb_product_id`) VALUES (?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, image.getImages());
		pst.setInt(2, image.getProduct().getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateImageProduct(ImageProduct image) throws Exception {
        String sql = "UPDATE `tb_image` SET `images`=?,`tb_product_id`=? WHERE id= ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, image.getImages());
		pst.setInt(2, image.getProduct().getId());
		pst.setInt(3, image.getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteImageProduct(int id) throws Exception {
        String sql = "DELETE FROM `tb_image` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
