package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.CustomersDAO;
import com.entity.Customers;
import com.entity.Daerah;

public class CustomersDAOMySqlImpl implements CustomersDAO {
    private Connection conn;
	
	public CustomersDAOMySqlImpl(Connection conn) {
		this.conn = conn;
    }
    
    @Override
    public List<Customers> getAllCustomerss() throws Exception {
        List<Customers> listOfCustomers = new ArrayList<Customers>();
		String sql = "SELECT * FROM `tb_customers` JOIN `tb_daerah` WHERE `tb_customers`.tb_daerah_postal_code=`tb_daerah`.`postal_code`";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Customers customers = new Customers();
            Daerah daerah = new Daerah(rs.getInt("postal_code"),rs.getString("district"),rs.getString("city"),rs.getString("country"));

			customers.setId(rs.getInt("id"));
			customers.setFullname(rs.getString("fullname"));
			customers.setUsername(rs.getString("username"));
			customers.setPassword(rs.getString("password"));
			customers.setAddress(rs.getString("address"));
			customers.setGender(rs.getString("gender"));
			customers.setBirth(rs.getString("birth"));
			customers.setCreatedDate(rs.getTimestamp("created_date"));
			customers.setPostalCode(daerah);
			listOfCustomers.add(customers);
		}
		return listOfCustomers;
    }

    @Override
    public Customers getCustomers(int id) throws Exception {
        Customers customers = new Customers();
		String sql = "SELECT * FROM `tb_customers` JOIN `tb_daerah` WHERE `tb_customers`.tb_daerah_postal_code=`tb_daerah`.`postal_code` and `tb_customers`.id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			Daerah daerah = new Daerah(rs.getInt("postal_code"),rs.getString("district"),rs.getString("city"),rs.getString("country"));
			customers.setId(rs.getInt("id"));
			customers.setFullname(rs.getString("fullname"));
			customers.setUsername(rs.getString("username"));
			customers.setPassword(rs.getString("password"));
			customers.setAddress(rs.getString("address"));
			customers.setGender(rs.getString("gender"));
			customers.setBirth(rs.getString("birth"));
			customers.setCreatedDate(rs.getTimestamp("created_date"));
			customers.setPostalCode(daerah);
		}
		return customers;
    }

    @Override
    public Customers getCustomersByUsername(String username) throws Exception {
        Customers customers = new Customers();
		String sql = "SELECT * FROM `tb_customers` JOIN `tb_daerah` WHERE `tb_customers`.tb_daerah_postal_code=`tb_daerah`.`postal_code` and `tb_customers`.username=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, username);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			Daerah daerah = new Daerah(rs.getInt("postal_code"),rs.getString("district"),rs.getString("city"),rs.getString("country"));
			customers.setId(rs.getInt("id"));
			customers.setFullname(rs.getString("fullname"));
			customers.setUsername(rs.getString("username"));
			customers.setPassword(rs.getString("password"));
			customers.setAddress(rs.getString("address"));
			customers.setGender(rs.getString("gender"));
			customers.setBirth(rs.getString("birth"));
			customers.setCreatedDate(rs.getTimestamp("created_date"));
			customers.setPostalCode(daerah);
		}
		return customers;
    }

    @Override
    public int addCustomers(Customers customers) throws Exception {
        String sql = "insert into `tb_customers`(`fullname`,`username`, `password`, `address`, `gender`,`birth`, `tb_daerah_postal_code`) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, customers.getFullname());
		pst.setString(2, customers.getUsername());
		pst.setString(3, customers.getPassword());
		pst.setString(4, customers.getAddress());
		pst.setString(5, customers.getGender());
		pst.setString(6, customers.getBirth());
		pst.setInt(7, customers.getPostalCode().getPostalCode());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateCustomers(Customers customers) throws Exception {
        String sql = "UPDATE `tb_customers` SET `fullname`=?,`username`=?,`password`=?,`address`=?,`gender`=?,`birth`=?,`tb_daerah_postal_code`=? WHERE id= ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, customers.getFullname());
		pst.setString(2, customers.getUsername());
		pst.setString(3, customers.getPassword());
		pst.setString(4, customers.getAddress());
		pst.setString(5, customers.getGender());
		pst.setString(6, customers.getBirth());
		pst.setInt(7, customers.getPostalCode().getPostalCode());
		pst.setInt(8, customers.getId());
		int affectedRow = pst.executeUpdate();
		// System.out.println(sql);
		return affectedRow;
    }

    @Override
    public int deleteCustomers(int id) throws Exception {
        String sql = "DELETE FROM `tb_customers` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
