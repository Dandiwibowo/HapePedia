package com.dao;

import java.util.List;

import com.entity.Daerah;

public interface DaerahDAO {
    public List<Daerah> getAllDaerahs() throws Exception;
    public Daerah getDaerah(int postalCode) throws Exception;
    public int addDaerah(Daerah daerah) throws Exception;
    public int updateDaerah(Daerah daerah) throws Exception;
    public int deleteDaerah(int id) throws Exception;
}
