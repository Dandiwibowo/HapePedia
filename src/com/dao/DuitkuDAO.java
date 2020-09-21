package com.dao;

import java.util.List;

import com.entity.Duitku;

public interface DuitkuDAO {
    public List<Duitku> getAllDuitkus() throws Exception;
    public Duitku getDuitku(int id) throws Exception;
    public int addDuitku(Duitku duitku) throws Exception;
    public int updateDuitku(Duitku duitku) throws Exception;
    public int deleteDuitku(int id) throws Exception;
}
