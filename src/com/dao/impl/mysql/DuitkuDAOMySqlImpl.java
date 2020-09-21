package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.DuitkuDAO;
import com.entity.Customers;
import com.entity.Duitku;

public class DuitkuDAOMySqlImpl implements DuitkuDAO {

    private Connection conn;
	
	public DuitkuDAOMySqlImpl(Connection conn) {
		this.conn = conn;
    }
    
    @Override
    public List<Duitku> getAllDuitkus() throws Exception {
        List<Duitku> listOfDuitku = new ArrayList<Duitku>();
		String sql = "SELECT * FROM `tb_duitku` JOIN `tb_customers` WHERE `tb_duitku`.tb_customers_id=`tb_customers`.`id`";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Customers customers = new Customers();
            customers.setId(rs.getInt("tb_customers_id"));
			customers.setFullname(rs.getString("nama"));
			customers.setUsername(rs.getString("username"));
			customers.setPassword(rs.getString("password"));
			customers.setAddress(rs.getString("address"));
			customers.setGender(rs.getString("gender"));
			customers.setBirth(rs.getString("birth"));
			customers.setCreatedDate(rs.getTimestamp("created_date"));
            
            Duitku duitku = new Duitku();
            duitku.setAccount_number(rs.getString("account_number"));
			duitku.setPin(rs.getString("pin"));
			duitku.setDebit(rs.getDouble("debit"));
			duitku.setCustomers(customers);
			listOfDuitku.add(duitku);
		}
		return listOfDuitku;
    }

    @Override
    public Duitku getDuitku(int id) throws Exception {
        Duitku duitku = null;
		String sql = "SELECT * FROM `tb_duitku` JOIN `tb_customers` WHERE `tb_duitku`.tb_customers_id=`tb_customers`.`id` and `tb_duitku`.id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
            Customers customers = new Customers();
            customers.setId(rs.getInt("tb_customers_id"));
			customers.setFullname(rs.getString("nama"));
			customers.setUsername(rs.getString("username"));
			customers.setPassword(rs.getString("password"));
			customers.setAddress(rs.getString("address"));
			customers.setGender(rs.getString("gender"));
			customers.setBirth(rs.getString("birth"));
			customers.setCreatedDate(rs.getTimestamp("created_date"));
            
            duitku = new Duitku();
            duitku.setAccount_number(rs.getString("account_number"));
			duitku.setPin(rs.getString("pin"));
			duitku.setDebit(rs.getDouble("debit"));
			duitku.setCustomers(customers);
		}
		return duitku;
    }

    @Override
    public int addDuitku(Duitku duitku) throws Exception {
        String sql = "INSERT INTO `tb_duitku`(`account_number`, `pin`, `debit`, `tb_customers_id`) VALUES (?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, duitku.getAccount_number());
		pst.setString(2, duitku.getPin());
		pst.setDouble(3, duitku.getDebit());
		pst.setInt(4, duitku.getCustomers().getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateDuitku(Duitku duitku) throws Exception {
        String sql = "UPDATE `tb_duitku` SET `account_number`=?,`pin`=?,`debit`=?,`tb_customers_id`=? WHERE id= ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, duitku.getAccount_number());
		pst.setString(2, duitku.getPin());
		pst.setDouble(3, duitku.getDebit());
		pst.setInt(4, duitku.getCustomers().getId());
		pst.setString(5, duitku.getAccount_number());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteDuitku(int id) throws Exception {
        String sql = "DELETE FROM `tb_duitku` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
