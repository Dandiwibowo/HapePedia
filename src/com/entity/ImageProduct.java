package com.entity;

public class ImageProduct {
    private int id;	
    private String images;
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public ImageProduct(String images,Product product) {
        this.images = images;
        this.product = product;
    }
    public ImageProduct() {
        
    }

    
}
