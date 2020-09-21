package com.dao;

import java.util.List;

import com.entity.DuitkuTransaction;

public interface DuitkuTransactionDAO {
    public List<DuitkuTransaction> getAllDuitkuTransactions() throws Exception;
    public DuitkuTransaction getDuitkuTransaction(int id) throws Exception;
    public List<DuitkuTransaction> getDuitkuTransactionByUser(int id) throws Exception;
    public int addDuitkuTransaction(DuitkuTransaction transaction) throws Exception;
    public int updateDuitkuTransaction(DuitkuTransaction transaction) throws Exception;
    public int deleteDuitkuTransaction(int id) throws Exception;
}
