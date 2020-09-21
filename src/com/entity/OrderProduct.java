package com.entity;

public class OrderProduct {
    private int id;	
	private int qty;	
	private double total_price;	
	private Order order;	
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderProduct(){};
    public OrderProduct(int qty, double total_price, Order order, Product product) {
        this.qty = qty;
        this.total_price = total_price;
        this.order = order;
        this.product = product;
    }
    
    
}
