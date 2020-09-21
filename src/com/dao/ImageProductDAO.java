package com.dao;

import java.util.List;

import com.entity.ImageProduct;

public interface ImageProductDAO {
    public List<ImageProduct> getAllImageProducts() throws Exception;
    public ImageProduct getImageProduct(int id) throws Exception;
    public List<ImageProduct> getImageProductByProduct(int id) throws Exception;
    public int addImageProduct(ImageProduct image) throws Exception;
    public int updateImageProduct(ImageProduct image) throws Exception;
    public int deleteImageProduct(int id) throws Exception;
}
