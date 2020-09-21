package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.TransactionDAO;
import com.entity.Order;
import com.entity.PaymentMethod;
import com.entity.Transaction;

public class TransactionDAOMySqlImpl implements TransactionDAO {

    private Connection conn;
	
	public TransactionDAOMySqlImpl(Connection conn) {
		this.conn = conn;
	}

    @Override
    public List<Transaction> getAllTransactions() throws Exception {
        List<Transaction> listOfTransaction = new ArrayList<Transaction>();
		String sql = "SELECT * FROM `tb_transaction` INNER JOIN tb_payment_method ON tb_transaction.tb_payment_method_id=tb_payment_method.id INNER JOIN tb_order ON tb_transaction.tb_order_order_number=tb_order.order_number";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            PaymentMethod payment = new PaymentMethod();
			payment.setId(rs.getInt("id"));
            payment.setPaymentMethod(rs.getString("payment_method"));
            Order order = new Order();
			order.setOrder_number(rs.getInt("order_number"));
			Transaction transaction = new Transaction();
			transaction.setId(rs.getInt("id"));
			transaction.setTotalPrice(rs.getDouble("total_price"));
			transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
			transaction.setStatus(rs.getString("status"));
			transaction.setNote(rs.getString("note"));
			transaction.setDescription(rs.getString("description"));
			transaction.setFinalPrice(rs.getDouble("final_price"));
			transaction.setPaymentMethod(payment);
			transaction.setOrderNumber(order);
			listOfTransaction.add(transaction);
		}
		return listOfTransaction;
    }

    @Override
    public Transaction getTransaction(int id) throws Exception {
        Transaction transaction = null;
		String sql = "SELECT * FROM `tb_transaction` INNER JOIN tb_payment_method ON tb_transaction.tb_payment_method_id=tb_payment_method.id INNER JOIN tb_order ON tb_transaction.tb_order_order_number=tb_order.order_number WHERE tb_transaction.idtb_transaction=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			PaymentMethod payment = new PaymentMethod();
			payment.setId(rs.getInt("id"));
            payment.setPaymentMethod(rs.getString("payment_method"));
            Order order = new Order();
			order.setOrder_number(rs.getInt("order_number"));
			transaction = new Transaction();
			transaction.setId(rs.getInt("id"));
			transaction.setTotalPrice(rs.getDouble("total_price"));
			transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
			transaction.setStatus(rs.getString("status"));
			transaction.setNote(rs.getString("note"));
			transaction.setDescription(rs.getString("description"));
			transaction.setFinalPrice(rs.getDouble("final_price"));
			transaction.setPaymentMethod(payment);
			transaction.setOrderNumber(order);
		}
		return transaction;
    }

    @Override
    public List<Transaction> getTransactionByUser(int id) throws Exception {
        List<Transaction> listOfTransaction = new ArrayList<Transaction>();
		String sql = "SELECT * FROM `tb_transaction` INNER JOIN tb_payment_method ON tb_transaction.tb_payment_method_id=tb_payment_method.id INNER JOIN tb_order ON tb_transaction.tb_order_order_number=tb_order.order_number WHERE tb_order.tb_customers_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            PaymentMethod payment = new PaymentMethod();
			payment.setId(rs.getInt("id"));
            payment.setPaymentMethod(rs.getString("payment_method"));
            Order order = new Order();
			order.setOrder_number(rs.getInt("order_number"));
			Transaction transaction = new Transaction();
			transaction.setId(rs.getInt("id"));
			transaction.setTotalPrice(rs.getDouble("total_price"));
			transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
			transaction.setStatus(rs.getString("status"));
			transaction.setNote(rs.getString("note"));
			transaction.setDescription(rs.getString("description"));
			transaction.setFinalPrice(rs.getDouble("final_price"));
			transaction.setPaymentMethod(payment);
			transaction.setOrderNumber(order);
			listOfTransaction.add(transaction);
		}
		return listOfTransaction;
    }

    @Override
    public Transaction getTransactionByOrder(int id) throws Exception {
		Transaction transaction = null;
		String sql = "SELECT * FROM `tb_transaction` INNER JOIN tb_payment_method ON tb_transaction.tb_payment_method_id=tb_payment_method.id INNER JOIN tb_order ON tb_transaction.tb_order_order_number=tb_order.order_number WHERE tb_order.order_number=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			PaymentMethod payment = new PaymentMethod();
			payment.setId(rs.getInt("id"));
            payment.setPaymentMethod(rs.getString("payment_method"));
            Order order = new Order();
			order.setOrder_number(rs.getInt("order_number"));
			transaction = new Transaction();
			transaction.setId(rs.getInt("id"));
			transaction.setTotalPrice(rs.getDouble("total_price"));
			transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
			transaction.setStatus(rs.getString("status"));
			transaction.setNote(rs.getString("note"));
			transaction.setDescription(rs.getString("description"));
			transaction.setFinalPrice(rs.getDouble("final_price"));
			transaction.setPaymentMethod(payment);
			transaction.setOrderNumber(order);
		}
		return transaction;
    }

    @Override
    public int addTransaction(Transaction transaction) throws Exception {
        String sql = "INSERT INTO `tb_transaction`(`total_price`, `status`, `note`, `description`, `final_price`, `tb_payment_method_id`, `tb_order_order_number`) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setDouble(1, transaction.getTotalPrice());
		pst.setString(2, transaction.getStatus());
		pst.setString(3, transaction.getNote());
		pst.setString(4, transaction.getDescription());
		pst.setDouble(5, transaction.getFinalPrice());
		pst.setLong(6, transaction.getPaymentMethod().getId());
		pst.setLong(7, transaction.getOrderNumber().getOrder_number());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateTransaction(Transaction transaction) throws Exception {
        String sql = "UPDATE `tb_transaction` SET `total_price`=?,`status`=?,`note`=?,`description`=?,`final_price`=?,`tb_payment_method_id`=?,`tb_order_order_number`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setDouble(1, transaction.getTotalPrice());
		pst.setString(2, transaction.getStatus());
		pst.setString(3, transaction.getNote());
		pst.setString(4, transaction.getDescription());
		pst.setDouble(5, transaction.getFinalPrice());
		pst.setLong(6, transaction.getPaymentMethod().getId());
		pst.setLong(7, transaction.getOrderNumber().getOrder_number());
		pst.setLong(8, transaction.getId());
		int affectedRow = pst.executeUpdate();
        return affectedRow;
    }

    @Override
    public int deleteTransaction(int id) throws Exception {
        String sql = "DELETE FROM `tb_transaction` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
