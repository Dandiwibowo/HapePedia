package com.dao;

import java.util.List;

import com.entity.Customers;

public interface CustomersDAO {
    public List<Customers> getAllCustomerss() throws Exception;
    public Customers getCustomers(int id) throws Exception;
    public Customers getCustomersByUsername(String username) throws Exception;
    public int addCustomers(Customers customers) throws Exception;
    public int updateCustomers(Customers customers) throws Exception;
    public int deleteCustomers(int id) throws Exception;
}
