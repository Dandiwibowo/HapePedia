package com.dao;

import java.util.List;

import com.entity.Transaction;

public interface TransactionDAO {
    public List<Transaction> getAllTransactions() throws Exception;
    public Transaction getTransaction(int id) throws Exception;
    public List<Transaction> getTransactionByUser(int id) throws Exception;
    public Transaction getTransactionByOrder(int id) throws Exception;
    public int addTransaction(Transaction transaction) throws Exception;
    public int updateTransaction(Transaction transaction) throws Exception;
    public int deleteTransaction(int id) throws Exception;
}
