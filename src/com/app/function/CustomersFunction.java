package com.app.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.dao.CustomersDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.CustomersDAOMySqlImpl;
import com.entity.Customers;
import com.entity.Daerah;

public class CustomersFunction {
    private Customers customers;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public CustomersFunction() {
    }

    public Customers customersLogin(String username, String password) {
        CustomersDAO dao = new CustomersDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            this.customers = dao.getCustomersByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println("Username : "+this.customers.getUsername()+" Password :
        // "+this.customers.getPassword());
        if (password.equals(this.customers.getPassword()))
            return this.customers;
        else
            return null;
    }

    public Customers customersLogout() {
        return null;
    }

    public void showProfileView(Customers customers) {
        System.out.println("Nama\t\t: " + customers.getFullname());
        System.out.println("Username \t: " + customers.getUsername());
        System.out.println("Password \t: " + customers.getPassword());
        System.out.println("Address \t: " + customers.getAddress() + ", " + customers.getDaerah().getDistrict() + ", "
                + customers.getDaerah().getCity() + ", " + customers.getDaerah().getCountry() + ", "
                + customers.getDaerah().getPostalCode());
        System.out.println("Gender \t: " + customers.getGender());
        System.out.println("Birth \t: " + customers.getBirth());

    }

    public void editProfileView(Customers customers) {
        System.out.println("1. Nama\t\t: " + customers.getFullname());
        System.out.println("2. Username \t: " + customers.getUsername());
        System.out.println("3. Password \t: " + customers.getPassword());
        System.out.println("4. Address \t: " + customers.getAddress());
        System.out.println("5. Gender \t: " + customers.getGender());
        System.out.println("6. Birth \t: " + customers.getBirth());
        System.out.println("7. Postal Code \t: " + customers.getDaerah().getPostalCode());
    }

    public Customers editProfileProcess(Customers customers, String nomorEdit, String valueEdit) {
        int status = 0;

        switch (nomorEdit) {
            case "1":
                customers.setFullname(valueEdit);
                break;
            case "2":
                customers.setUsername(valueEdit);
                break;
            case "3":
                customers.setPassword(valueEdit);
                break;
            case "4":
                customers.setAddress(valueEdit);
                break;
            case "5":
                customers.setGender(valueEdit);
                break;
            case "6":
                customers.setBirth(valueEdit);
                break;
            case "7":
                Daerah daerah = new Daerah();
                daerah.setPostalCode(Integer.parseInt(valueEdit));
                customers.setDaerah(daerah);
                break;
        }

        CustomersDAO dao = new CustomersDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            status = dao.updateCustomers(customers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (status == 0) {
            try {
                customers = dao.getCustomers(customers.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    public void showListCustomersView() {
        List<Customers> listCustomers = new ArrayList();
        CustomersDAO dao = new CustomersDAOMySqlImpl(MySqlConnection.getConnection());

        try {
            listCustomers = dao.getAllCustomerss();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Id\t\t Nama\t\t\t Username\t\t Address");
        for(int x=0;x<listCustomers.size();x++){
            System.out.println(((Customers)listCustomers.get(x)).getId()
            +"\t\t"+((Customers)listCustomers.get(x)).getFullname()
            +"\t\t"+((Customers)listCustomers.get(x)).getUsername()
            +"\t\t"+((Customers)listCustomers.get(x)).getAddress());
        }

    }

    public void addNewCustomersView(){
        String menuChoose = "";
        Customers customers = new Customers();
        System.out.println("CREATE NEW Customer");

        System.out.println("Full Name : ");
        try {
            customers.setFullname(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Username : ");
        try {
            customers.setUsername(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Password : ");
        try {
            customers.setPassword(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Address : ");
        try {
            customers.setAddress(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Gender : ");
        try {
            customers.setGender(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Birth : ");
        try {
            customers.setBirth(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Postal Code : ");
        try {
            Daerah daerah = new Daerah();
            daerah.setPostalCode(Integer.parseInt(input.readLine()));
            customers.setDaerah(daerah);
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
                CustomersDAO dao = new CustomersDAOMySqlImpl(MySqlConnection.getConnection());
                try {
                    dao.addCustomers(customers);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                break;
        }
    }
    
    public int deleteCustomersView(int idCustomers){
        int status=0;
        CustomersDAO dao = new CustomersDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            dao.deleteCustomers(idCustomers);
            status=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
