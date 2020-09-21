package com.entity;

import java.sql.Timestamp;

public class DuitkuTransaction {
    private int id;
    private double debit;
    private String description;
    private String eWallet;
    private int sender;
    private int receiver;
    private java.sql.Timestamp transaction_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public java.sql.Timestamp getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(java.sql.Timestamp transaction_date) {
        this.transaction_date = transaction_date;
    }

    public DuitkuTransaction(int id, double debit, String description, int sender, int receiver,
            Timestamp transaction_date) {
        this.id = id;
        this.debit = debit;
        this.description = description;
        this.sender = sender;
        this.receiver = receiver;
        this.transaction_date = transaction_date;
    }

    public DuitkuTransaction(){

    }

    public String geteWallet() {
        return eWallet;
    }

    public void seteWallet(String eWallet) {
        this.eWallet = eWallet;
    }

    public DuitkuTransaction(double debit, String description, String eWallet, int sender, int receiver,
            Timestamp transaction_date) {
        this.debit = debit;
        this.description = description;
        this.eWallet = eWallet;
        this.sender = sender;
        this.receiver = receiver;
        this.transaction_date = transaction_date;
    }
   
}
