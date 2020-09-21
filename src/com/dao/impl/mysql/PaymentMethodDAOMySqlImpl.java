package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.PaymentMethodDAO;
import com.entity.PaymentMethod;

public class PaymentMethodDAOMySqlImpl implements PaymentMethodDAO {

    
    private Connection conn;
	
	public PaymentMethodDAOMySqlImpl(Connection conn) {
		this.conn = conn;
	}


    @Override
    public List<PaymentMethod> getAllPaymentMethods() throws Exception {
        List<PaymentMethod> listOfPaymentMethod = new ArrayList<PaymentMethod>();
		String sql = "SELECT * FROM `tb_payment_method`";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			PaymentMethod payment = new PaymentMethod();
			payment.setId(rs.getInt("id"));
			payment.setPaymentMethod(rs.getString("payment_method"));
			listOfPaymentMethod.add(payment);
		}
		return listOfPaymentMethod;
    }

    @Override
    public PaymentMethod getPaymentMethod(int id) throws Exception {
        PaymentMethod payment = null;
		String sql = "SELECT * FROM `tb_payment_method` WHERE id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			payment = new PaymentMethod();
			payment.setId(rs.getInt("id"));
			payment.setPaymentMethod(rs.getString("payment_method"));
			
		}
		return payment;
    }

    @Override
    public int addPaymentMethod(PaymentMethod payment) throws Exception {
        String sql = "INSERT INTO `tb_payment_method`(`payment_method`) VALUES (?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, payment.getPaymentMethod());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updatePaymentMethod(PaymentMethod payment) throws Exception {
        String sql = "UPDATE `tb_payment_method` SET `payment_method`=? WHERE id= ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, payment.getPaymentMethod());
		pst.setInt(2, payment.getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deletePaymentMethod(int id) throws Exception {
        String sql = "DELETE FROM `tb_payment_method` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
