package com.app.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.dao.ProductDAO;
import com.dao.TransactionDAO;
import com.dao.CartDAO;
import com.dao.OrderDAO;
import com.dao.OrderProductDAO;
import com.dao.PaymentMethodDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.ProductDAOMySqlImpl;
import com.dao.impl.mysql.TransactionDAOMySqlImpl;
import com.dao.impl.mysql.CartDAOMySqlImpl;
import com.dao.impl.mysql.OrderDAOMySqlImpl;
import com.dao.impl.mysql.OrderProductDAOMySqlImpl;
import com.dao.impl.mysql.PaymentMethodDAOMySqlImpl;
import com.entity.Customers;
import com.entity.Order;
import com.entity.OrderProduct;
import com.entity.PaymentMethod;
import com.entity.Product;
import com.entity.Transaction;
import com.entity.Cart;

public class CartFunction {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public CartFunction() {
    }

    public void editCartView(Cart cart) {
        System.out.println("1. Cart\t: " + cart.getProduct());
    }

    public void showListCartView() {
        List<Cart> listCart = new ArrayList();
        CartDAO dao = new CartDAOMySqlImpl(MySqlConnection.getConnection());

        try {
            listCart = dao.getAllCarts();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ID\t\t Product");
        for (int x = 0; x < listCart.size(); x++) {
            System.out.println(
                    ((Cart) listCart.get(x)).getId() + "\t\t" + ((Cart) listCart.get(x)).getProduct().getProductName());
        }

    }

    public void addNewCartView(Customers customers, int idProduct) {
        String menuChoose = "";
        Product product = new Product();

        ProductDAO dao = new ProductDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            product = dao.getProduct(idProduct);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        Cart cart = new Cart();
        cart.setCustomers(customers);
        cart.setProduct(product);

        System.out.println("Create New Cart");
        System.out.println("Product : " + product.getProductName());

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
                CartDAO dao2 = new CartDAOMySqlImpl(MySqlConnection.getConnection());
                try {
                    dao2.addCart(cart);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                break;
        }
    }

    public int deleteCartView(int idCart) {
        int status = 0;
        CartDAO dao = new CartDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            dao.deleteCart(idCart);
            status = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public void checkout(Customers customers) {
        String menuChoose = "";
        List<Cart> listProduct = new ArrayList();
        String noteForm = "", descriptionForm = "";
        Double totalPriceForm = 0.0, finalPriceForm = 0.0;

        CartDAO dao = new CartDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            listProduct = dao.getCartByUser(customers.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("List Product : ");
        System.out.println("No\t Product Name\t Price");
        for (int x = 0; x < listProduct.size(); x++) {

            totalPriceForm += ((Cart) listProduct.get(x)).getProduct().getPrice();
            System.out.println((x + 1) + "\t" + ((Cart) listProduct.get(x)).getProduct().getproductName() + "\t"
                    + ((Cart) listProduct.get(x)).getProduct().getPrice());
        }
        System.out.println("Total Price : " + totalPriceForm);

        System.out.println("Insert Note : ");
        try {
            noteForm = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Insert Description : ");
        try {
            noteForm = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PaymentMethodFunction paymentFc = new PaymentMethodFunction();
        paymentFc.showListPaymentMethodView();
        System.out.println("Type Payment Method Id : ");
        String paymentId = "";
        try {
            paymentId = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finalPriceForm = totalPriceForm;
        System.out.println("Final Price : " + finalPriceForm);

        PaymentMethodDAO dao3 = new PaymentMethodDAOMySqlImpl(MySqlConnection.getConnection());
        PaymentMethod paymentMethod = new PaymentMethod();
        try {
            paymentMethod = dao3.getPaymentMethod(Integer.parseInt(paymentId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Paid");
        System.out.println("2. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                OrderFunction orderFc = new OrderFunction();
                Order order = new Order(orderFc.generateOrderNumber(), customers);
                Transaction transaction = new Transaction();
                transaction.setTotalPrice(totalPriceForm);
                transaction.setFinalPrice(finalPriceForm);
                transaction.setStatus("Barang Diproses");
                transaction.setNote(noteForm);
                transaction.setDescription(descriptionForm);
                transaction.setPaymentMethod(paymentMethod);
                transaction.setOrderNumber(order);

                OrderDAO daoOrder = new OrderDAOMySqlImpl(MySqlConnection.getConnection());
                try {
                    daoOrder.addOrder(order);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                TransactionDAO daoTransaction = new TransactionDAOMySqlImpl(MySqlConnection.getConnection());
                try {
                    daoTransaction.addTransaction(transaction);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                OrderProductDAO daoOrderProduct = new OrderProductDAOMySqlImpl(MySqlConnection.getConnection());
                ProductDAO daoProduct = new ProductDAOMySqlImpl(MySqlConnection.getConnection());
                CartDAO daoCart = new CartDAOMySqlImpl(MySqlConnection.getConnection());
                for (int x = 0; x < listProduct.size(); x++) {
                    OrderProduct orderProduct = new OrderProduct(1, ((Cart) listProduct.get(x)).getProduct().getPrice(),
                            order, ((Cart) listProduct.get(x)).getProduct());
                    try {
                        daoOrderProduct.addOrderProduct(orderProduct);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        daoCart.deleteCart((listProduct.get(x)).getId());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    Product productUpdate = new Product();
                    productUpdate = orderProduct.getProduct();
                    productUpdate.setStock(productUpdate.getStock() - 1);
                    try {
                        daoProduct.updateProduct(productUpdate);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "2":
                break;
        }
    }
}
