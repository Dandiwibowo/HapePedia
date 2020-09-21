package com.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {
    public static void main(String[] args) throws NumberFormatException, IOException {
        // AdminDAO dao = new AdminDAOMySqlImpl(MySqlConnection.getConnection());

        // Daerah daerah = new Daerah(60198, "Benowo", "Surabaya", "Indonesia");

        // Admin admin = new Admin();
        // admin.setNama("Dandi Wibowo");
        // admin.setUsername("dandiwibowo9");
        // admin.setPassword("dandiwibowo99");
        // admin.setAddress("Bandarejo");
        // admin.setPosition("Admin");
        // admin.setDaerah(daerah);

        // try {
        //     dao.addAdmin(admin);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // Admin admin2 = new Admin();
        // try {
        //     admin2 = dao.getAdmin(3);
        //     System.out.println(admin2.getNama());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        BufferedReader input = new BufferedReader(new
        
        InputStreamReader(System.in));
        int pilihan = 0;
        do{
            navbar();
            pilihan = Integer.parseInt(input.readLine());
            switch(pilihan){
                case 1:login();break;
                case 9:System.out.println("Bye..bye....");break;
            }
        }
            while(pilihan!=9);
    }

    static void login(){
        System.out.println("LOGIN ADMIN");
        System.out.println("-----------");
    }
    
    static void navbar(){
        System.out.println("===============================================================");
        System.out.println("Press Key");
        System.out.println("Login(L) \t Wishlist(W) \t Cart(C)");
    }
    
}


