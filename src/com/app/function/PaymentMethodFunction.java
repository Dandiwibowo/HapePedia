package com.app.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.dao.PaymentMethodDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.PaymentMethodDAOMySqlImpl;
import com.entity.PaymentMethod;

public class PaymentMethodFunction {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public PaymentMethodFunction() {
    }

    public void editPaymentMethodView(PaymentMethod paymentMethod) {
        System.out.println("1. PaymentMethod\t: " + paymentMethod.getPaymentMethod());
    }

    public PaymentMethod editPaymentMethodProcess(PaymentMethod paymentMethod, String nomorEdit, String valueEdit) {
        
        switch (nomorEdit) {
            case "1":
                paymentMethod.setPaymentMethod(valueEdit);
                break;
        }

        PaymentMethodDAO dao = new PaymentMethodDAOMySqlImpl(MySqlConnection.getConnection());
        try {
           dao.updatePaymentMethod(paymentMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentMethod;
    }

    public void showListPaymentMethodView() {
        List<PaymentMethod> listPaymentMethod = new ArrayList();
        PaymentMethodDAO dao = new PaymentMethodDAOMySqlImpl(MySqlConnection.getConnection());

        try {
            listPaymentMethod = dao.getAllPaymentMethods();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("ID\t\t PaymentMethod");
        for(int x=0;x<listPaymentMethod.size();x++){
            System.out.println(
                ((PaymentMethod)listPaymentMethod.get(x)).getId()
                +"\t\t"+((PaymentMethod)listPaymentMethod.get(x)).getPaymentMethod());
        }

    }

    public void addNewPaymentMethodView(){
        String menuChoose = "";
        PaymentMethod paymentMethod = new PaymentMethod();
        System.out.println("Create New PaymentMethod");

        System.out.println("PaymentMethod : ");
        try {
            paymentMethod.setPaymentMethod(input.readLine());
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
                PaymentMethodDAO dao = new PaymentMethodDAOMySqlImpl(MySqlConnection.getConnection());
                try {
                    dao.addPaymentMethod(paymentMethod);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                break;
        }
    }
    
    public int deletePaymentMethodView(int idPaymentMethod){
        int status=0;
        PaymentMethodDAO dao = new PaymentMethodDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            dao.deletePaymentMethod(idPaymentMethod);
            status=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
