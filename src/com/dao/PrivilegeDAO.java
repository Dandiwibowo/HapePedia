package com.dao;

import java.util.List;

import com.entity.Privilege;

public interface PrivilegeDAO {
    public List<Privilege> getAllPrivileges() throws Exception;
    public Privilege getPrivilege(int id) throws Exception;
    public int addPrivilege(Privilege privilege) throws Exception;
    public int updatePrivilege(Privilege privilege) throws Exception;
    public int deletePrivilege(int id) throws Exception;
}
