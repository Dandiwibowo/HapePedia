package com.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.app.function.CustomersFunction;
import com.app.function.CartFunction;
import com.app.function.CategoryFunction;
import com.app.function.DaerahFunction;
import com.app.function.PaymentMethodFunction;
import com.app.function.ProductFunction;
import com.app.function.ShipmentFunction;
import com.app.function.TransactionFunction;
import com.app.function.WishlistFunction;
import com.dao.CategoryDAO;
import com.dao.DaerahDAO;
import com.dao.PaymentMethodDAO;
import com.dao.ProductDAO;
import com.dao.ShipmentDAO;
import com.dao.TransactionDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.CategoryDAOMySqlImpl;
import com.dao.impl.mysql.DaerahDAOMySqlImpl;
import com.dao.impl.mysql.PaymentMethodDAOMySqlImpl;
import com.dao.impl.mysql.ProductDAOMySqlImpl;
import com.dao.impl.mysql.ShipmentDAOMySqlImpl;
import com.dao.impl.mysql.TransactionDAOMySqlImpl;
import com.entity.Customers;
import com.entity.Cart;
import com.entity.Category;
import com.entity.Daerah;
import com.entity.PaymentMethod;
import com.entity.Product;
import com.entity.Shipment;
import com.entity.Transaction;
import com.entity.Wishlist;

public class MainCustomer {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    static Customers customersUser = new Customers();
    static CustomersFunction customersFc = new CustomersFunction();;
    
    static Daerah daerahUser = new Daerah();
    static DaerahFunction daerahFc = new DaerahFunction();
    
    static Category categoryUser = new Category();
    static CategoryFunction categoryFc = new CategoryFunction();
    
    static PaymentMethod paymentMethodUser = new PaymentMethod();
    static PaymentMethodFunction paymentMethodFc = new PaymentMethodFunction();
    
    static Product productUser = new Product();
    static ProductFunction productFc = new ProductFunction();
    
    static Transaction transactionUser = new Transaction();
    static TransactionFunction transactionFc = new TransactionFunction();
    
    static Shipment shipmentUser = new Shipment();
    static ShipmentFunction shipmentFc = new ShipmentFunction();
    
    static Wishlist wishlistUser = new Wishlist();
    static WishlistFunction wishlistFc = new WishlistFunction();
    
    static Cart cartUser = new Cart();
    static CartFunction cartFc = new CartFunction();
    
    static String menu;
    static int productChoosed;

    public static void main(final String[] args) throws NumberFormatException, IOException {
        menu = "Home";
        while (customersUser != null) {
            myMenu();
            // clearScreen();
        }
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void login() {
        String username = "", password = "";

        System.out.println("  ****** LOGIN User ******");
        System.out.println("-----------------------------");
        System.out.println("Masukkan Username : ");
        try {
            username = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Masukkan Password : ");
        try {
            password = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        customersUser = customersFc.customersLogin(username, password);

        if (customersUser.getFullname() != null) {
            System.out.println("Welcome " + customersUser.getFullname());
            menu = "Home";
        } else
            System.out.println("Sorry username or password doesn't exist");

    }

    static void myMenu() {
        switch (menu) {

            case "Home":
                menuHome();
                break;
            case "Logout":
                menuLogout();
                break;
            case "Login":
                login();
                break;
            case "Registration":
                customersFc.addNewCustomersView();
                menu = "Home";
                break;
            case "Close":
                menuLogout();
                break;


            case "Show Profile":
                if(customersUser.getUsername()==null)
                    login();
                else    
                    menuShowProfile();
                break;
            case "Edit Profile":
                menuEditProfile();
                break;

            
            case "Show Detail Product":
                menuShowProduct();
                break;

            case "Show Wish List":
                if(customersUser.getUsername()==null)
                    login();
                else    
                    menuShowWishlist();
                break;

            case "Show Cart":
                if(customersUser.getUsername()==null)
                    login();
                else    
                    menuShowCart();
                break;

            
            case "Show List Transaction":
                if(customersUser.getUsername()==null)
                    login();
                else    
                    menuShowTransaction();
                break;

            case "Show Shipping List":
                if(customersUser.getUsername()==null)
                    login();
                else    
                    menuShowShipping();
                break;
            

        }
    }


    private static void menuLogout() {
        System.out.println("Have a nice day, Bye ...");
        customersUser = customersFc.customersLogout();
    }

    static void menuHome() {
        productFc.showListProductView();
        String menuChoose = "";
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Show Profile");
        System.out.println("2. Show Wish List");
        System.out.println("3. Show Cart");
        System.out.println("4. Show Transaction");
        System.out.println("5. Show Shipping List");
        System.out.println("6. Show Detail Product");
        if(customersUser.getUsername()==null){
            System.out.println("11. Login");
            System.out.println("12. Registration");
        }
        else
            System.out.println("13. Logout");
        System.out.println("14. Close");

        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                menu = "Show Profile";
                break;
            case "2":
                menu = "Show Wish List";
                break;
            case "3":
                menu = "Show Cart";
                break;
            case "4":
                menu = "Show List Transaction";
                break;
            case "5":
                menu = "Show Shipping List";
                break;
            case "6":
                menu = "Show Detail Product";
                break;
            case "11":
                menu = "Login";
                break;
            case "12":
                menu = "Registration";
                break;
            case "13":
                menu = "Logout";
                break;
            case "14":
                menu = "Close";
                break;
        }
    }


    // ================ Customers Function =================
    static void menuShowProfile() {
        String menuChoose = "";

        customersFc.showProfileView(customersUser);
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Edit profile");
        System.out.println("2. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                menu = "Edit Profile";
                break;
            case "2":
                menu = "Home";
                break;
        }

    }

    private static void menuEditProfile() {
        String menuChoose = "", nomorEdit = "", valueEdit = "";

        customersFc.editProfileView(customersUser);

        System.out.println("Choose number of form : ");
        try {
            nomorEdit = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Type new value : ");
        try {
            valueEdit = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                customersUser = customersFc.editProfileProcess(customersUser, nomorEdit, valueEdit);
                menu = "Show Profile";
                break;
            case "2":
                menu = "Home";
                break;
        }
    }


    // ================ Product Function =================
    static void menuShowProduct(){
        String menuChoose = "";

        productFc.showListProductView();
        System.out.println("===============================================================");
        System.out.println("Type Product Id");
        System.out.println("0. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (menuChoose.equals("0"))
            menu = "Home";
        else
        menuShowDetailProduct(Integer.parseInt(menuChoose));
    }
    
    static void menuShowDetailProduct(int idProduct) {
        String menuChoose = "";
        
        ProductDAO dao = new ProductDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            productUser = dao.getProduct(idProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }      
        productFc.showDetailProductView(productUser);
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Add To Wishlist");
        System.out.println("2. Add to Cart");
        System.out.println("3. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                wishlistFc.addNewWishlistView(customersUser, idProduct);
                menu = "Show Wish List";
                break;
            case "2":
                cartFc.addNewCartView(customersUser, idProduct);
                menu = "Show Cart";
                break;
            case "3":
                menu = "Show List Product";
                break;
        }

    }

    // ================ End of Product Function =================

    // ================ Wishlist Function =================
    static void menuShowWishlist(){
        String menuChoose = "";

        wishlistFc.showListWishlistView();
        System.out.println("===============================================================");
        System.out.println("Type Product Id");
        System.out.println("0. Back");
        System.out.println("Please type number of menu for delete from wishlist: ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (menuChoose.equals("0"))
            menu = "Home";
        else{
            wishlistFc.deleteWishlistView(Integer.parseInt(menuChoose));
            menu = "Show Wish List";
        }
    }
    // ================ End of Wishlist Function =================

    // ================ Cart Function =================
    static void menuShowCart(){
        String menuChoose = "";

        cartFc.showListCartView();
        System.out.println("===============================================================");
        System.out.println("Type Product Id");
        System.out.println("0. Back");
        System.out.println("Type 'order' for checkout");
        System.out.println("Type number of menu for delete from wishlist: ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (menuChoose.equals("0"))
            menu = "Home";
        else if (menuChoose.equals("order")){
            cartFc.checkout(customersUser);
            menu = "Show List Transaction";
        }
        else{
            cartFc.deleteCartView(Integer.parseInt(menuChoose));
            menu = "Show Wish List";
        }
    }
    // ================ End of Cart Function =================

    // ================ Transaction Function =================
    static void menuShowTransaction(){
        String menuChoose = "";

        transactionFc.showListTransactionView();
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("0. Back");
        System.out.println("Type Order Number for Detail");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (menuChoose.equals("0"))
            menu = "Home";
        else
            menuShowDetailTransaction(Integer.parseInt(menuChoose));
        
    }

    static void menuShowDetailTransaction(int orderNumber) {
        String menuChoose = "";
        
        TransactionDAO dao = new TransactionDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            transactionUser = dao.getTransaction(orderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        transactionFc.showDetailTransactionView(transactionUser);

        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                menu = "Home";
                break;
        }

    }
    // ================ End of Transaction Function =================
    
    // ================ Shipping Function =================
    static void menuShowShipping(){
        String menuChoose = "";

        shipmentFc.showListShipmentView();
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("0. Back");
        System.out.println("Please Type Receipt Number for Detail");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (menuChoose.equals("0"))
            menu = "Home";
        else
            menuShowDetailShipment(Integer.parseInt(menuChoose));
    }

    static void menuShowDetailShipment(int receiptNumber) {
        String menuChoose = "";
        
        ShipmentDAO dao = new ShipmentDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            shipmentUser = dao.getShipment(receiptNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        shipmentFc.showDetailShipmentView(shipmentUser);

        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                menu = "Home";
                break;
        }

    }
    // ================ End of Shipping Function =================
}


