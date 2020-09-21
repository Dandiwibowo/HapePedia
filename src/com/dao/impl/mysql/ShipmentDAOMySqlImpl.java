package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.ShipmentDAO;
import com.entity.Order;
import com.entity.Shipment;

public class ShipmentDAOMySqlImpl implements ShipmentDAO {

    private Connection conn;
	
	public ShipmentDAOMySqlImpl(Connection conn) {
		this.conn = conn;
	}

    @Override
    public List<Shipment> getAllShipments() throws Exception {
        List<Shipment> listOfShipment = new ArrayList<Shipment>();
		String sql = "SELECT * FROM `tb_shipment` JOIN tb_order WHERE tb_shipment.tb_order_order_number=tb_order.order_number";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Order order = new Order();
            order.setOrder_number(rs.getInt("order_number"));
			Shipment shipment = new Shipment();
			shipment.setReceiptNumber(rs.getString("id"));
			shipment.setPrice(rs.getDouble("nama"));
			shipment.setCourierName(rs.getString("username"));
			shipment.setDeliveryTime(rs.getString("password"));
			shipment.setPackageName(rs.getString("address"));
			shipment.setAddressDestination(rs.getString("position"));
			shipment.setOrderNumber(order);
			listOfShipment.add(shipment);
		}
		return listOfShipment;
    }

    @Override
    public Shipment getShipment(int id) throws Exception {
        Shipment shipment = null;
		String sql = "SELECT * FROM `tb_shipment` JOIN tb_order WHERE tb_shipment.tb_order_order_number=tb_order.order_number and `tb_shipment`.id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			Order order = new Order();
            order.setOrder_number(rs.getInt("order_number"));
			shipment = new Shipment();
			shipment.setReceiptNumber(rs.getString("id"));
			shipment.setPrice(rs.getDouble("nama"));
			shipment.setCourierName(rs.getString("username"));
			shipment.setDeliveryTime(rs.getString("password"));
			shipment.setPackageName(rs.getString("address"));
			shipment.setAddressDestination(rs.getString("position"));
			shipment.setOrderNumber(order);
		}
		return shipment;
    }

    @Override
    public List<Shipment> getShipmentByUser(int id) throws Exception {
        List<Shipment> listOfShipment = new ArrayList<Shipment>();
		String sql = "SELECT * FROM `tb_shipment` JOIN tb_order WHERE tb_shipment.tb_order_order_number=tb_order.order_number where tb_order.tb_customers_id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
		while(rs.next()) {
            Order order = new Order();
            order.setOrder_number(rs.getInt("order_number"));
			Shipment shipment = new Shipment();
			shipment.setReceiptNumber(rs.getString("id"));
			shipment.setPrice(rs.getDouble("nama"));
			shipment.setCourierName(rs.getString("username"));
			shipment.setDeliveryTime(rs.getString("password"));
			shipment.setPackageName(rs.getString("address"));
			shipment.setAddressDestination(rs.getString("position"));
			shipment.setOrderNumber(order);
			listOfShipment.add(shipment);
		}
		return listOfShipment;
    }

    @Override
    public Shipment getShipmentByOrder(int id) throws Exception {
        Shipment shipment = null;
		String sql = "SELECT * FROM `tb_shipment` JOIN tb_order WHERE tb_shipment.tb_order_order_number=tb_order.order_number and `tb_shipment`.tb_order_order_number=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			Order order = new Order();
            order.setOrder_number(rs.getInt("order_number"));
			shipment = new Shipment();
			shipment.setReceiptNumber(rs.getString("id"));
			shipment.setPrice(rs.getDouble("nama"));
			shipment.setCourierName(rs.getString("username"));
			shipment.setDeliveryTime(rs.getString("password"));
			shipment.setPackageName(rs.getString("address"));
			shipment.setAddressDestination(rs.getString("position"));
			shipment.setOrderNumber(order);
		}
		return shipment;
    }

    @Override
    public int addShipment(Shipment shipment) throws Exception {
        String sql = "INSERT INTO `tb_shipment`(`receipt_number`, `price`, `courier_name`, `delivery_time`, `package_name`, `address_destination`, `tb_order_order_number`) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, shipment.getReceiptNumber());
		pst.setDouble(2, shipment.getPrice());
		pst.setString(3, shipment.getCourierName());
		pst.setString(4, shipment.getDeliveryTime());
		pst.setString(5, shipment.getPackageName());
		pst.setString(5, shipment.getAddressDestination());
		pst.setInt(7, shipment.getOrderNumber().getOrder_number());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateShipment(Shipment shipment) throws Exception {
        String sql = "UPDATE `tb_shipment` SET `receipt_number`=?,`price`=?,`courier_name`=?,`delivery_time`=?,`package_name`=?,`address_destination`=?,`tb_order_order_number`=? WHERE receipt_number=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, shipment.getReceiptNumber());
		pst.setDouble(2, shipment.getPrice());
		pst.setString(3, shipment.getCourierName());
		pst.setString(4, shipment.getDeliveryTime());
		pst.setString(5, shipment.getPackageName());
		pst.setString(5, shipment.getAddressDestination());
		pst.setInt(7, shipment.getOrderNumber().getOrder_number());
		pst.setString(8, shipment.getReceiptNumber());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteShipment(int id) throws Exception {
        String sql = "DELETE FROM `tb_shipment` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
