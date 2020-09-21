package com.entity;
public class Cart {
	public Cart() {
	}
	
	private int id;
	private Product Product;
	private Customers Customers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return Product;
	}

	public void setProduct(Product Product) {
		this.Product = Product;
	}

	public Customers getCustomers() {
		return Customers;
	}

	public void setCustomers(Customers Customers) {
		this.Customers = Customers;
	}
	
}
