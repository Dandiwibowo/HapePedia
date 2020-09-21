package com.dao;

import java.util.List;

import com.entity.Wishlist;

public interface WishlistDAO {
    public List<Wishlist> getAllWishlists() throws Exception;
    public Wishlist getWishlist(int id) throws Exception;
    public List<Wishlist> getWishlistByUser(int id) throws Exception;
    public int addWishlist(Wishlist product) throws Exception;
    public int updateWishlist(Wishlist product) throws Exception;
    public int deleteWishlist(int id) throws Exception;
}
