package com.app.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.dao.TransactionDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.TransactionDAOMySqlImpl;
import com.entity.Transaction;

public class TransactionFunction {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public TransactionFunction() {
    }


    public void showDetailTransactionView(Transaction transaction) {
        System.out.println("Order Number\t\t: " + transaction.getOrderNumber().getOrder_number());
        System.out.println("Transaction Date \t: " + transaction.getTransactionDate());
        System.out.println("Final Price \t: " + transaction.getFinalPrice());
        System.out.println("Status \t: " + transaction.getStatus());
        System.out.println("Payment Method \t: " + transaction.getPaymentMethod().getPaymentMethod());
        System.out.println("Description \t: " + transaction.getDescription());
        
    }


    public void showListTransactionView() {
        List<Transaction> listTransaction = new ArrayList();
        TransactionDAO dao = new TransactionDAOMySqlImpl(MySqlConnection.getConnection());

        try {
            listTransaction = dao.getAllTransactions();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Id\t\t Order Number\t\t Transaction Date\t\t Final Price\t\t Status");
        for(int x=0;x<listTransaction.size();x++){
            System.out.println(((Transaction)listTransaction.get(x)).getId()
            +"\t\t"+((Transaction)listTransaction.get(x)).getOrderNumber().getOrder_number()
            +"\t\t"+((Transaction)listTransaction.get(x)).getTransactionDate()
            +"\t\t"+((Transaction)listTransaction.get(x)).getFinalPrice()
            +"\t\t"+((Transaction)listTransaction.get(x)).getStatus());
        }

    }
    
    public int deleteTransactionView(int idTransaction){
        int status=0;
        TransactionDAO dao = new TransactionDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            dao.deleteTransaction(idTransaction);
            status=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
