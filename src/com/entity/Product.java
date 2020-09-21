package com.entity;

public class Product {
    private int id;
	private String productName;	
	private int stock = 0;
	private double price = 0;
	private String color;
	private String guarantee;
	private String description;
	private java.sql.Timestamp createdDate;
	private Category categoryId;
   
    public Product (
            int id,  
            String productName, 
            int stock, 
            double price,  
            String color,  
            String guarantee,  
            String description,  
            java.sql.Timestamp createdDate,  
            Category categoryId
        ){

        this.id = id;
        this.productName = productName;
        this.stock = stock;
        this.price = price;
        this.color = color;
        this.guarantee = guarantee;
        this.description = description;
        this.createdDate = createdDate;
        this.categoryId = categoryId;

    }
    public Product (){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getproductName() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(java.sql.Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    
}
