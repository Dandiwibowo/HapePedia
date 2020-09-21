package com.dao;

import java.util.List;
import com.entity.Admin;

public interface AdminDAO {
    public List<Admin> getAllAdmins() throws Exception;
    public Admin getAdmin(int id) throws Exception;
    public Admin getAdminByUsername(String username) throws Exception;
    public int addAdmin(Admin admin) throws Exception;
    public int updateAdmin(Admin admin) throws Exception;
    public int deleteAdmin(int id) throws Exception;
}
