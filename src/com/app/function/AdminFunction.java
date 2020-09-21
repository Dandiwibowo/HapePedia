package com.app.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.dao.AdminDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.AdminDAOMySqlImpl;
import com.entity.Admin;
import com.entity.Daerah;

public class AdminFunction {
    private Admin admin;
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public AdminFunction() {
    }

    public Admin adminLogin(String username, String password) {
        AdminDAO dao = new AdminDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            this.admin = dao.getAdminByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println("Username : "+this.admin.getUsername()+" Password :
        // "+this.admin.getPassword());
        if (password.equals(this.admin.getPassword()))
            return this.admin;
        else
            return null;
    }

    public Admin adminLogout() {
        return null;
    }

    public void showProfileView(Admin admin) {
        System.out.println("Nama\t\t: " + admin.getNama());
        System.out.println("Username \t: " + admin.getUsername());
        System.out.println("Password \t: " + admin.getPassword());
        System.out.println("Address \t: " + admin.getAddress() + ", " + admin.getDaerah().getDistrict() + ", "
                + admin.getDaerah().getCity() + ", " + admin.getDaerah().getCountry() + ", "
                + admin.getDaerah().getPostalCode());
        System.out.println("Position \t: " + admin.getPosition());

    }

    public void editProfileView(Admin admin) {
        System.out.println("1. Nama\t\t: " + admin.getNama());
        System.out.println("2. Username \t: " + admin.getUsername());
        System.out.println("3. Password \t: " + admin.getPassword());
        System.out.println("4. Address \t: " + admin.getAddress());
        System.out.println("5. Position \t: " + admin.getPosition());
        System.out.println("6. Postal Code \t: " + admin.getDaerah().getPostalCode());
    }

    public Admin editProfileProcess(Admin admin, String nomorEdit, String valueEdit) {
        int status = 0;

        switch (nomorEdit) {
            case "1":
                admin.setNama(valueEdit);
                break;
            case "2":
                admin.setUsername(valueEdit);
                break;
            case "3":
                admin.setPassword(valueEdit);
                break;
            case "4":
                admin.setAddress(valueEdit);
                break;
            case "5":
                admin.setPosition(valueEdit);
                break;
            case "6":
                Daerah daerah = new Daerah();
                daerah.setPostalCode(Integer.parseInt(valueEdit));
                admin.setDaerah(daerah);
                break;
        }

        AdminDAO dao = new AdminDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            status = dao.updateAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (status == 0) {
            try {
                admin = dao.getAdmin(admin.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return admin;
    }

    public void showListAdminView() {
        List<Admin> listAdmin = new ArrayList();
        AdminDAO dao = new AdminDAOMySqlImpl(MySqlConnection.getConnection());

        try {
            listAdmin = dao.getAllAdmins();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Id\t\t Nama\t\t\t Username\t\t Position");
        for(int x=0;x<listAdmin.size();x++){
            System.out.println(((Admin)listAdmin.get(x)).getId()
            +"\t\t"+((Admin)listAdmin.get(x)).getNama()
            +"\t\t"+((Admin)listAdmin.get(x)).getUsername()
            +"\t\t"+((Admin)listAdmin.get(x)).getPosition());
        }

    }

    public void addNewAdminView(){
        String menuChoose = "";
        Admin admin = new Admin();
        System.out.println("CREATE NEW ADMIN");

        System.out.println("Full Name : ");
        try {
            admin.setNama(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Username : ");
        try {
            admin.setUsername(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Password : ");
        try {
            admin.setPassword(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Address : ");
        try {
            admin.setAddress(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Position : ");
        try {
            admin.setPosition(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Postal Code : ");
        try {
            Daerah daerah = new Daerah();
            daerah.setPostalCode(Integer.parseInt(input.readLine()));
            admin.setDaerah(daerah);
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
                AdminDAO dao = new AdminDAOMySqlImpl(MySqlConnection.getConnection());
                try {
                    dao.addAdmin(admin);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                break;
        }
    }
    
    public int deleteAdminView(int idAdmin){
        int status=0;
        AdminDAO dao = new AdminDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            dao.deleteAdmin(idAdmin);
            status=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
