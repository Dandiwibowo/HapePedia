package com.app.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.dao.DaerahDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.DaerahDAOMySqlImpl;
import com.entity.Daerah;

public class DaerahFunction {
    
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public DaerahFunction() {
    }

    public void editDaerahView(Daerah daerah) {
        System.out.println("1. Postal Codes\t\t: " + daerah.getPostalCode());
        System.out.println("2. District \t: " + daerah.getDistrict());
        System.out.println("3. City \t: " + daerah.getCity());
        System.out.println("4. Country \t: " + daerah.getCountry());
    }

    public Daerah editDaerahProcess(Daerah daerah, String nomorEdit, String valueEdit) {
        
        switch (nomorEdit) {
            case "1":
                daerah.setPostalCode(Integer.parseInt(valueEdit));
                break;
            case "2":
                daerah.setDistrict(valueEdit);
                break;
            case "3":
                daerah.setCity(valueEdit);
                break;
            case "4":
                daerah.setCountry(valueEdit);
                break;
        }

        DaerahDAO dao = new DaerahDAOMySqlImpl(MySqlConnection.getConnection());
        try {
           dao.updateDaerah(daerah);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return daerah;
    }

    public void showListPostalCodesView() {
        List<Daerah> listDaerah = new ArrayList();
        DaerahDAO dao = new DaerahDAOMySqlImpl(MySqlConnection.getConnection());

        try {
            listDaerah = dao.getAllDaerahs();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Postal Codes\t\t District\t\t\t City\t\t Country");
        for(int x=0;x<listDaerah.size();x++){
            System.out.println(((Daerah)listDaerah.get(x)).getPostalCode()
            +"\t\t"+((Daerah)listDaerah.get(x)).getDistrict()
            +"\t\t"+((Daerah)listDaerah.get(x)).getCity()
            +"\t\t"+((Daerah)listDaerah.get(x)).getCountry());
        }

    }

    public void addNewPostalCodesView(){
        String menuChoose = "";
        Daerah daerah = new Daerah();
        System.out.println("Create New Postal Codes");

        System.out.println("Postal Code : ");
        try {
            daerah.setPostalCode(Integer.parseInt(input.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("District : ");
        try {
            daerah.setDistrict(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("City : ");
        try {
            daerah.setCity(input.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Country : ");
        try {
            daerah.setCountry(input.readLine());
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
                DaerahDAO dao = new DaerahDAOMySqlImpl(MySqlConnection.getConnection());
                try {
                    dao.addDaerah(daerah);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                break;
        }
    }
    
    public int deletePostalCodesView(int idDaerah){
        int status=0;
        DaerahDAO dao = new DaerahDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            dao.deleteDaerah(idDaerah);
            status=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
