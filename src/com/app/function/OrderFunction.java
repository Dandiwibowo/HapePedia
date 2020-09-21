package com.app.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.dao.OrderDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.OrderDAOMySqlImpl;
import com.entity.Order;

public class OrderFunction {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public OrderFunction() {
    }

    public int generateOrderNumber(){
        // int status=0, randomNumber=0;
        // OrderDAO dao = new OrderDAOMySqlImpl(MySqlConnection.getConnection());
        // while (status==0){
            
        //     try {
        //         Order order = dao.getOrder(randomNumber);
        //         if(order!=null)
        //             status=1;
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        //     System.out.println(randomNumber);
        // }

        // return randomNumber;

        int randomNumber = (int) ((Math.random() * ((999 - 100) + 1)) + 100);

        return randomNumber;
    }
}
