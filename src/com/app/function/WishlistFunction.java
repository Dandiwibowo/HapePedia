package com.app.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.dao.ProductDAO;
import com.dao.WishlistDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.ProductDAOMySqlImpl;
import com.dao.impl.mysql.WishlistDAOMySqlImpl;
import com.entity.Customers;
import com.entity.Product;
import com.entity.Wishlist;

public class WishlistFunction {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public WishlistFunction() {
    }

    public void editWishlistView(Wishlist wishlist) {
        System.out.println("1. Wishlist\t: " + wishlist.getProduct());
    }

    public void showListWishlistView() {
        List<Wishlist> listWishlist = new ArrayList();
        WishlistDAO dao = new WishlistDAOMySqlImpl(MySqlConnection.getConnection());

        try {
            listWishlist = dao.getAllWishlists();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ID\t\t Product");
        for (int x = 0; x < listWishlist.size(); x++) {
            System.out.println(
                    ((Wishlist) listWishlist.get(x)).getId() + "\t\t" + ((Wishlist) listWishlist.get(x)).getProduct().getProductName());
        }

    }

    public void addNewWishlistView(Customers customers, int idProduct) {
        String menuChoose = "";
        Product product = new Product();

        ProductDAO dao = new ProductDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            product = dao.getProduct(idProduct);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setCustomers(customers);
        wishlist.setProduct(product);

        System.out.println("Create New Wishlist");
        System.out.println("Product : "+product.getProductName());
        
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Save");
        System.out.println("2. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                WishlistDAO dao2 = new WishlistDAOMySqlImpl(MySqlConnection.getConnection());
                try {
                    dao2.addWishlist(wishlist);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                break;
        }
    }
    
    public int deleteWishlistView(int idWishlist){
        int status=0;
        WishlistDAO dao = new WishlistDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            dao.deleteWishlist(idWishlist);
            status=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
