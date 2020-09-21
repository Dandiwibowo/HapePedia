package com.dao;

import java.util.List;

import com.entity.Cart;

public interface CartDAO {
    public List<Cart> getAllCarts() throws Exception;
    public Cart getCart(int id) throws Exception;
    public List<Cart> getCartByUser(int id) throws Exception;
    public int addCart(Cart product) throws Exception;
    public int updateCart(Cart product) throws Exception;
    public int deleteCart(int id) throws Exception;
}
