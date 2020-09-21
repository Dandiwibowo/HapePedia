package com.dao;

import java.util.List;

import com.entity.Shipment;

public interface ShipmentDAO {
    public List<Shipment> getAllShipments() throws Exception;
    public Shipment getShipment(int id) throws Exception;
    public List<Shipment> getShipmentByUser(int id) throws Exception;
    public Shipment getShipmentByOrder(int id) throws Exception;
    public int addShipment(Shipment shipment) throws Exception;
    public int updateShipment(Shipment shipment) throws Exception;
    public int deleteShipment(int id) throws Exception;
}
