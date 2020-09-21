package com.entity;

public class Order {
	public Order() {
	}
	public Order(int order_number, Customers customers) {
		this.order_number = order_number;
		this.customers = customers;
	}
	private int order_number;
	private Customers customers;

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}
	
	
	
}
