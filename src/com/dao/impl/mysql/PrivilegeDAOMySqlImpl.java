package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.PrivilegeDAO;
import com.entity.Privilege;

public class PrivilegeDAOMySqlImpl implements PrivilegeDAO {

    private Connection conn;
	
	public PrivilegeDAOMySqlImpl(Connection conn) {
		this.conn = conn;
    }
    
    @Override
    public List<Privilege> getAllPrivileges() throws Exception {
        List<Privilege> listOfPrivilege = new ArrayList<Privilege>();
		String sql = "SELECT * FROM `tb_privilege` JOIN `tb_daerah` WHERE `tb_privilege`.tb_daerah_postal_code=`tb_daerah`.`postal_code`";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			Privilege privilege = new Privilege();
			privilege.setId(rs.getInt("id"));
			privilege.setPrivilege(rs.getString("privilege"));
			listOfPrivilege.add(privilege);
		}
		return listOfPrivilege;
    }

    @Override
    public Privilege getPrivilege(int id) throws Exception {
        Privilege privilege = null;
		String sql = "SELECT * FROM `tb_privilege` JOIN `tb_daerah` WHERE `tb_privilege`.tb_daerah_postal_code=`tb_daerah`.`postal_code` and `tb_privilege`.id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			privilege = new Privilege();
			privilege.setId(rs.getInt("id"));
			privilege.setPrivilege(rs.getString("privilege"));
		}
		return privilege;
    }

    @Override
    public int addPrivilege(Privilege privilege) throws Exception {
        String sql = "INSERT INTO `tb_privilege`(`privilege`) VALUES (?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, privilege.getPrivilege());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updatePrivilege(Privilege privilege) throws Exception {
        String sql = "UPDATE `tb_privilege` SET `privilege`=? WHERE id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, privilege.getPrivilege());
		pst.setLong(2, privilege.getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deletePrivilege(int id) throws Exception {
        String sql = "DELETE FROM `tb_privilege` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
