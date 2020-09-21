package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.DuitkuTransactionDAO;
import com.entity.DuitkuTransaction;

public class DuitkuTransactionDAOMySqlImpl implements DuitkuTransactionDAO {

    private Connection conn;
	
	public DuitkuTransactionDAOMySqlImpl(Connection conn) {
		this.conn = conn;
    }
    
    @Override
    public List<DuitkuTransaction> getAllDuitkuTransactions() throws Exception {
        List<DuitkuTransaction> listOfDuitkuTransaction = new ArrayList<DuitkuTransaction>();
		String sql = "SELECT * FROM `tb_duitkuTransaction`";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			DuitkuTransaction duitkuTransaction = new DuitkuTransaction();
			duitkuTransaction.setId(rs.getInt("id"));
			duitkuTransaction.setDebit(rs.getDouble("debit"));
			duitkuTransaction.setDescription(rs.getString("description"));
			duitkuTransaction.setSender(rs.getInt("sender"));
			duitkuTransaction.setReceiver(rs.getInt("receiver"));
			duitkuTransaction.setTransaction_date(rs.getTimestamp("transaction_date"));
			listOfDuitkuTransaction.add(duitkuTransaction);
		}
		return listOfDuitkuTransaction;
    }

    @Override
    public DuitkuTransaction getDuitkuTransaction(int id) throws Exception {
        DuitkuTransaction duitkuTransaction = null;
		String sql = "SELECT * FROM `tb_duitkuTransaction` WHERE id =?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			duitkuTransaction = new DuitkuTransaction();
			duitkuTransaction.setId(rs.getInt("id"));
			duitkuTransaction.setDebit(rs.getDouble("debit"));
			duitkuTransaction.setDescription(rs.getString("description"));
			duitkuTransaction.setSender(rs.getInt("sender"));
			duitkuTransaction.setReceiver(rs.getInt("receiver"));
			duitkuTransaction.setTransaction_date(rs.getTimestamp("transaction_date"));
		}
		return duitkuTransaction;
    }

    @Override
    public List<DuitkuTransaction> getDuitkuTransactionByUser(int id) throws Exception {
        List<DuitkuTransaction> listOfDuitkuTransaction = new ArrayList<DuitkuTransaction>();
		String sql = "SELECT * FROM `tb_duitkuTransaction` WHERE receiver =? OR sender=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        pst.setLong(2, id);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			DuitkuTransaction duitkuTransaction = new DuitkuTransaction();
			duitkuTransaction.setId(rs.getInt("id"));
			duitkuTransaction.setDebit(rs.getDouble("debit"));
			duitkuTransaction.setDescription(rs.getString("description"));
			duitkuTransaction.setSender(rs.getInt("sender"));
			duitkuTransaction.setReceiver(rs.getInt("receiver"));
			duitkuTransaction.setTransaction_date(rs.getTimestamp("transaction_date"));
			listOfDuitkuTransaction.add(duitkuTransaction);
		}
		return listOfDuitkuTransaction;
    }

    @Override
    public int addDuitkuTransaction(DuitkuTransaction transaction) throws Exception {
        String sql = "INSERT INTO `tb_duitku_transaction`(`debit`, `description`, `sender`, `receiver`,  `e-wallet_account_number`) VALUES (?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setDouble(1, transaction.getDebit());
		pst.setString(2, transaction.getDescription());
		pst.setInt(3, transaction.getSender());
		pst.setInt(4, transaction.getReceiver());
		pst.setString(5, transaction .geteWallet());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateDuitkuTransaction(DuitkuTransaction transaction) throws Exception {
        String sql = "UPDATE `tb_duitku_transaction` SET `debit`=?,`description`=?,`sender`=?,`receiver`=?,`transaction_date`=?,`e-wallet_account_number`=? WHERE id= ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setDouble(1, transaction.getDebit());
		pst.setString(2, transaction.getDescription());
		pst.setInt(3, transaction.getSender());
		pst.setInt(4, transaction.getReceiver());
		pst.setTimestamp(5, transaction.getTransaction_date());
		pst.setString(6, transaction .geteWallet());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteDuitkuTransaction(int id) throws Exception {
        String sql = "DELETE FROM `tb_duitku_transaction` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
