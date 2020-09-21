package com.entity;

public class Duitku {
	private String account_number;
	private String pin;
	private Double debit;
	private Customers customers;
	
	public Duitku() {
	}
	
	public Duitku(String account_number, String pin, Double debit, Customers customers) {
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Double getDebit() {
		return debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}
	
	
	
}
