package com.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.app.function.AdminFunction;
import com.app.function.CategoryFunction;
import com.app.function.CustomersFunction;
import com.app.function.DaerahFunction;
import com.app.function.PaymentMethodFunction;
import com.app.function.ProductFunction;
import com.app.function.ShipmentFunction;
import com.app.function.TransactionFunction;
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
import com.entity.Admin;
import com.entity.Category;
import com.entity.Daerah;
import com.entity.PaymentMethod;
import com.entity.Product;
import com.entity.Shipment;
import com.entity.Transaction;

public class MainAdmin {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    static Admin adminUser = new Admin();
    static AdminFunction adminFc = new AdminFunction();
    
    static Daerah daerahUser = new Daerah();
    static DaerahFunction daerahFc = new DaerahFunction();
    
    static Category categoryUser = new Category();
    static CategoryFunction categoryFc = new CategoryFunction();
    
    static PaymentMethod paymentMethodUser = new PaymentMethod();
    static PaymentMethodFunction paymentMethodFc = new PaymentMethodFunction();
    
    static Product productUser = new Product();
    static ProductFunction productFc = new ProductFunction();
    
    static CustomersFunction customersFc = new CustomersFunction();
    
    static Transaction transactionUser = new Transaction();
    static TransactionFunction transactionFc = new TransactionFunction();
    
    static Shipment shipmentUser = new Shipment();
    static ShipmentFunction shipmentFc = new ShipmentFunction();
    
    static String menu;

    public static void main(final String[] args) throws NumberFormatException, IOException {
        login();
        while (adminUser != null) {
            myMenu();
            clearScreen();
        }
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void login() {
        String username = "", password = "";

        System.out.println("  ****** LOGIN ADMIN ******");
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

        adminUser = adminFc.adminLogin(username, password);

        if (adminUser != null) {
            System.out.println("Welcome " + adminUser.getNama());
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


            case "Show Profile":
                menuShowProfile();
                break;
            case "Edit Profile":
                menuEditProfile();
                break;


            case "Show List Admin":
                menuShowListAdmin();
                break;
            case "Create New Admin":
                menuAddNewAdmin();
                break;
            case "Delete Admin":
                menuDeleteAdmin();
                break;


            case "Show List Postal Codes":
                menuShowPostalCodes();
                break;
            case "Create New Postal Codes":
                menuAddNewPostalCodes();
                break;
            case "Edit Postal Codes":
                menuEditPostalCodes();
                break;
            case "Delete Postal Codes":
                menuDeletePostalCodes();
                break;

            
            case "Show List Category":
                menuShowCategory();
                break;
            case "Create New Category":
                menuAddNewCategory();
                break;
            case "Edit Category":
                menuEditCategory();
                break;
            case "Delete Category":
                menuDeleteCategory();
                break;

            
            case "Show List Payment Method":
                menuShowPaymentMethod();
                break;
            case "Create New Payment Method":
                menuAddNewPaymentMethod();
                break;
            case "Edit Payment Method":
                menuEditPaymentMethod();
                break;
            case "Delete Payment Method":
                menuDeletePaymentMethod();
                break;


            case "Show List Product":
                menuShowProduct();
                break;
            case "Create New Product":
                menuAddNewProduct();
                break;
            case "Edit Product":
                menuEditProduct();
                break;
            case "Delete Product":
                menuDeleteProduct();
                break;

            
            case "Show List Customers":
                menuShowCustomers();
                break;
            case "Show List Transaction":
                menuShowTransaction();
                break;
            case "Show Shipping List":
                menuShowShipping();
                break;
            

        }
    }


    private static void menuLogout() {
        System.out.println("Have a nice day, Bye ...");
        adminUser = adminFc.adminLogout();
    }

    static void menuHome() {
        String menuChoose = "";
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Show profile");
        System.out.println("2. Show List Admin");
        System.out.println("3. Show List Postal Codes");
        System.out.println("4. Show List Category");
        System.out.println("5. Show List Payment Method");
        System.out.println("6. Show Product");
        System.out.println("7. Show Customers");
        System.out.println("8. Show Transaction");
        System.out.println("9. Show Shipping List");
        System.out.println("12. Logout");
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
                menu = "Show List Admin";
                break;
            case "3":
                menu = "Show List Postal Codes";
                break;
            case "4":
                menu = "Show List Category";
                break;
            case "5":
                menu = "Show List Payment Method";
                break;
            case "6":
                menu = "Show List Product";
                break;
            case "7":
                menu = "Show List Customers";
                break;
            case "8":
                menu = "Show List Transaction";
                break;
            case "9":
                menu = "Show Shipping List";
                break;
            case "12":
                menu = "Logout";
                break;
        }
    }


    // ================ Admin Function =================
    static void menuShowProfile() {
        String menuChoose = "";

        adminFc.showProfileView(adminUser);
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

        adminFc.editProfileView(adminUser);

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
                adminUser = adminFc.editProfileProcess(adminUser, nomorEdit, valueEdit);
                menu = "Show Profile";
                break;
            case "2":
                menu = "Home";
                break;
        }
    }

    private static void menuShowListAdmin() {
        String menuChoose = "";

        adminFc.showListAdminView();
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Create New Admin");
        System.out.println("2. Delete Admin");
        System.out.println("3. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                menu = "Create New Admin";
                break;
            case "2":
                menu = "Delete Admin";
                break;
            case "3":
                menu = "Home";
                break;
        }
    }

    private static void menuAddNewAdmin() {
       adminFc.addNewAdminView();
       menu = "Show List Admin";
    }

    private static void menuDeleteAdmin() {
        String idAdmin = "";

        adminFc.showListAdminView();
        System.out.println("===============================================================");
        System.out.println("Type Username who will deleted :");
        try {
            idAdmin = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        adminFc.deleteAdminView(Integer.parseInt(idAdmin));
        menu = "Show List Admin";
    }

    // ============= End of Admin Function =============


    // ================ Postal Codes Function =================
    static void menuShowPostalCodes(){
        String menuChoose = "";

        daerahFc.showListPostalCodesView();
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Edit Postal Codes");
        System.out.println("2. Add New Postal Codes");
        System.out.println("3. Delete Postal Codes");
        System.out.println("4. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                menu = "Edit Postal Codes";
                break;
            case "2":
                menu = "Create New Postal Codes";
                break;
            case "3":
                menu = "Delete Postal Codes";
                break;
            case "4":
                menu = "Home";
                break;
        }
    }
    
    static void menuShowDetailPostalCodes(int idPostal) {
        String menuChoose = "", nomorEdit = "", valueEdit = "";
        
        DaerahDAO dao = new DaerahDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            daerahUser = dao.getDaerah(idPostal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        daerahFc.editDaerahView(daerahUser);
        
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
                daerahFc.editDaerahProcess(daerahUser, nomorEdit, valueEdit);
                menu = "Show List Postal Codes";
                break;
            case "2":
                menu = "Home";
                break;
        }

    }

    private static void menuEditPostalCodes() {
        String menuChoose = "";

        daerahFc.showListPostalCodesView();
        
        
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("Please type Postal Code that will edited : ");
        System.out.println("0. Back");
        
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(menuChoose=="0") {
            menu = "Home";
        }
        else{
            menuShowDetailPostalCodes(Integer.parseInt(menuChoose));
        }
        
    }

    private static void menuDeletePostalCodes() {
        String idPostal = "";

        daerahFc.showListPostalCodesView();
        System.out.println("===============================================================");
        System.out.println("Type Postal Codes who will deleted :");
        try {
            idPostal = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        daerahFc.deletePostalCodesView(Integer.parseInt(idPostal));
        menu = "Show List Postal Codes";
    }

    private static void menuAddNewPostalCodes() {
        daerahFc.addNewPostalCodesView();
        menu = "Show List Postal Codes";
    }
    // ================ End of Postal Codes Function =================


    // ================ Category Function =================
    static void menuShowCategory(){
        String menuChoose = "";

        categoryFc.showListCategoryView();
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Edit Category");
        System.out.println("2. Add New Category");
        System.out.println("3. Delete Category");
        System.out.println("4. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                menu = "Edit Category";
                break;
            case "2":
                menu = "Create New Category";
                break;
            case "3":
                menu = "Delete Category";
                break;
            case "4":
                menu = "Home";
                break;
        }
    }
    
    static void menuShowDetailCategory(int idPostal) {
        String menuChoose = "", nomorEdit = "", valueEdit = "";
        
        CategoryDAO dao = new CategoryDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            categoryUser = dao.getCategory(idPostal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        categoryFc.editCategoryView(categoryUser);
        
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
                categoryFc.editCategoryProcess(categoryUser, nomorEdit, valueEdit);
                menu = "Show List Category";
                break;
            case "2":
                menu = "Home";
                break;
        }

    }

    private static void menuEditCategory() {
        String menuChoose = "";

        categoryFc.showListCategoryView();
        
        
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("Please type Id that will edited : ");
        System.out.println("0. Back");
        
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(menuChoose=="0") {
            menu = "Home";
        }
        else{
            menuShowDetailCategory(Integer.parseInt(menuChoose));
        }
        
    }

    private static void menuDeleteCategory() {
        String idPostal = "";

        categoryFc.showListCategoryView();
        System.out.println("===============================================================");
        System.out.println("Type Category who will deleted :");
        try {
            idPostal = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        categoryFc.deleteCategoryView(Integer.parseInt(idPostal));
        menu = "Show List Category";
    }

    private static void menuAddNewCategory() {
        categoryFc.addNewCategoryView();
        menu = "Show List Category";
    }
    // ================ End of Category Function =================
   
    // ================ PaymentMethod Function =================
    static void menuShowPaymentMethod(){
        String menuChoose = "";

        paymentMethodFc.showListPaymentMethodView();
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Edit Payment Method");
        System.out.println("2. Add New Payment Method");
        System.out.println("3. Delete Payment Method");
        System.out.println("4. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                menu = "Edit Payment Method";
                break;
            case "2":
                menu = "Create New Payment Method";
                break;
            case "3":
                menu = "Delete Payment Method";
                break;
            case "4":
                menu = "Home";
                break;
        }
    }
    
    static void menuShowDetailPaymentMethod(int idPostal) {
        String menuChoose = "", nomorEdit = "", valueEdit = "";
        
        PaymentMethodDAO dao = new PaymentMethodDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            paymentMethodUser = dao.getPaymentMethod(idPostal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        paymentMethodFc.editPaymentMethodView(paymentMethodUser);
        
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
                paymentMethodFc.editPaymentMethodProcess(paymentMethodUser, nomorEdit, valueEdit);
                menu = "Show List Payment Method";
                break;
            case "2":
                menu = "Home";
                break;
        }

    }

    private static void menuEditPaymentMethod() {
        String menuChoose = "";

        paymentMethodFc.showListPaymentMethodView();
        
        
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("Please type ID that will edited : ");
        System.out.println("0. Back");
        
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(menuChoose=="0") {
            menu = "Home";
        }
        else{
            menuShowDetailPaymentMethod(Integer.parseInt(menuChoose));
        }
        
    }

    private static void menuDeletePaymentMethod() {
        String idPostal = "";

        paymentMethodFc.showListPaymentMethodView();
        System.out.println("===============================================================");
        System.out.println("Type PaymentMethod who will deleted :");
        try {
            idPostal = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        paymentMethodFc.deletePaymentMethodView(Integer.parseInt(idPostal));
        menu = "Show List Payment Method";
    }

    private static void menuAddNewPaymentMethod() {
        paymentMethodFc.addNewPaymentMethodView();
        menu = "Show List Payment Method";
    }
    // ================ End of PaymentMethod Function =================

    // ================ Product Function =================
    static void menuShowProduct(){
        String menuChoose = "";

        productFc.showListProductView();
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("1. Edit Product");
        System.out.println("2. Add New Product");
        System.out.println("3. Delete Product");
        System.out.println("4. Back");
        System.out.println("Please type number of menu : ");
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (menuChoose) {
            case "1":
                menu = "Edit Product";
                break;
            case "2":
                menu = "Create New Product";
                break;
            case "3":
                menu = "Delete Product";
                break;
            case "4":
                menu = "Home";
                break;
        }
    }
    
    static void menuShowDetailProduct(int idPostal) {
        String menuChoose = "", nomorEdit = "", valueEdit = "";
        
        ProductDAO dao = new ProductDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            productUser = dao.getProduct(idPostal);
        } catch (Exception e) {
            e.printStackTrace();
        }

        productFc.editProductView(productUser);
        
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
                productFc.editProductProcess(productUser, nomorEdit, valueEdit);
                menu = "Show List Product";
                break;
            case "2":
                menu = "Home";
                break;
        }

    }

    private static void menuEditProduct() {
        String menuChoose = "";

        productFc.showListProductView();
        
        
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("Please type ID that will edited : ");
        System.out.println("0. Back");
        
        try {
            menuChoose = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(menuChoose=="0") {
            menu = "Home";
        }
        else{
            menuShowDetailProduct(Integer.parseInt(menuChoose));
        }
        
    }

    private static void menuDeleteProduct() {
        String idPostal = "";

        productFc.showListProductView();
        System.out.println("===============================================================");
        System.out.println("Type Product who will deleted :");
        try {
            idPostal = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productFc.deleteProductView(Integer.parseInt(idPostal));
        menu = "Show List Product";
    }

    private static void menuAddNewProduct() {
        productFc.addNewProductView();
        menu = "Show List Product";
    }
    // ================ End of Product Function =================

    // ================ Customer Function =================
    static void menuShowCustomers(){
        String menuChoose = "";

        customersFc.showListCustomersView();
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
    // ================ End of Customer Function =================
 
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


