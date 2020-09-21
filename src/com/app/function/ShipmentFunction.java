package com.app.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.dao.ShipmentDAO;
import com.dao.connection.MySqlConnection;
import com.dao.impl.mysql.ShipmentDAOMySqlImpl;
import com.entity.Shipment;

public class ShipmentFunction {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public ShipmentFunction() {
    }


    public void showDetailShipmentView(Shipment shipment) {
        System.out.println("Receipt Number\t\t: " + shipment.getReceiptNumber());
        System.out.println("Order Number\t\t: " + shipment.getOrderNumber().getOrder_number());
        System.out.println("Courier Name \t: " + shipment.getCourierName());
        System.out.println("Package Name \t: " + shipment.getPackageName());
        System.out.println("Destination \t: " + shipment.getAddressDestination());
        System.out.println("Price \t: " + shipment.getPrice());
        System.out.println("Delivery Time \t: " + shipment.getDeliveryTime());
        
    }


    public void showListShipmentView() {
        List<Shipment> listShipment = new ArrayList();
        ShipmentDAO dao = new ShipmentDAOMySqlImpl(MySqlConnection.getConnection());

        try {
            listShipment = dao.getAllShipments();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Receipt Number\t\t Order Number\t\t Courier Name\t\t Package Name\t\t Delivery Time\t\t Destination");
        for(int x=0;x<listShipment.size();x++){
            System.out.println(((Shipment)listShipment.get(x)).getReceiptNumber()
            +"\t\t"+((Shipment)listShipment.get(x)).getOrderNumber().getOrder_number()
            +"\t\t"+((Shipment)listShipment.get(x)).getCourierName()
            +"\t\t"+((Shipment)listShipment.get(x)).getPackageName()
            +"\t\t"+((Shipment)listShipment.get(x)).getDeliveryTime()
            +"\t\t"+((Shipment)listShipment.get(x)).getAddressDestination());
        }

    }
    
    public int deleteShipmentView(int idShipment){
        int status=0;
        ShipmentDAO dao = new ShipmentDAOMySqlImpl(MySqlConnection.getConnection());
        try {
            dao.deleteShipment(idShipment);
            status=1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
