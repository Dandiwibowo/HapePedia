package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.AdminDAO;
import com.entity.Admin;
import com.entity.Daerah;

public class AdminDAOMySqlImpl implements AdminDAO {

    private Connection conn;
	
	public AdminDAOMySqlImpl(Connection conn) {
		this.conn = conn;
	}

    @Override
    public List<Admin> getAllAdmins() throws Exception{
		List<Admin> listOfAdmin = new ArrayList<Admin>();
		String sql = "SELECT * FROM `tb_admin` INNER JOIN `tb_daerah` ON `tb_admin`.tb_daerah_postal_code=`tb_daerah`.`postal_code`";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			Daerah daerah = new Daerah();
			daerah.setPostalCode(rs.getInt("postal_code"));
			daerah.setDistrict(rs.getString("district"));
			daerah.setCity(rs.getString("city"));
			daerah.setCountry(rs.getString("country"));
			Admin admin = new Admin();
			admin.setId(rs.getInt("id"));
			admin.setNama(rs.getString("nama"));
			admin.setUsername(rs.getString("username"));
			admin.setPassword(rs.getString("password"));
			admin.setAddress(rs.getString("address"));
			admin.setPosition(rs.getString("position"));
			admin.setDaerah(daerah);
			listOfAdmin.add(admin);
		}
		return listOfAdmin;
    }

    @Override
    public Admin getAdmin(int id) throws Exception{
        Admin admin = null;
		String sql = "SELECT * FROM `tb_admin` INNER JOIN `tb_daerah` ON `tb_admin`.tb_daerah_postal_code=`tb_daerah`.`postal_code` WHERE `tb_admin`.id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setLong(1, id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			Daerah daerah = new Daerah();
			daerah.setPostalCode(rs.getInt("postal_code"));
			daerah.setDistrict(rs.getString("district"));
			daerah.setCity(rs.getString("city"));
			daerah.setCountry(rs.getString("country"));
			admin = new Admin();
			admin.setId(rs.getInt("id"));
			admin.setNama(rs.getString("nama"));
			admin.setUsername(rs.getString("username"));
			admin.setPassword(rs.getString("password"));
			admin.setAddress(rs.getString("address"));
			admin.setPosition(rs.getString("position"));
			admin.setDaerah(daerah);
		}
		return admin;
    }

    @Override
    public int addAdmin(Admin admin) throws Exception{
        String sql = "insert into `tb_admin`(`nama`,`username`, `password`, `address`, `position`, `tb_daerah_postal_code`) VALUES (?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, admin.getNama());
		pst.setString(2, admin.getUsername());
		pst.setString(3, admin.getPassword());
		pst.setString(4, admin.getAddress());
		pst.setString(5, admin.getPosition());
		pst.setLong(6, admin.getDaerah().getPostalCode());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateAdmin(Admin admin) throws Exception{
        String sql = "UPDATE `tb_admin` SET `nama`=?,`username`=?,`password`=?,`address`=?,`position`=?,`tb_daerah_postal_code`=? WHERE id= ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, admin.getNama());
		pst.setString(2, admin.getUsername());
		pst.setString(3, admin.getPassword());
		pst.setString(4, admin.getAddress());
		pst.setString(5, admin.getPosition());
		pst.setLong(6, admin.getDaerah().getPostalCode());
		pst.setInt(7, admin.getId());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteAdmin(int id) throws Exception{
        String sql = "DELETE FROM `tb_admin` WHERE `id`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

	@Override
	public Admin getAdminByUsername(String username) throws Exception {
		Admin admin = null;
		String sql = "SELECT * FROM `tb_admin` INNER JOIN `tb_daerah` ON `tb_admin`.tb_daerah_postal_code=`tb_daerah`.`postal_code` where username=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, username);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			Daerah daerah = new Daerah();
			daerah.setPostalCode(rs.getInt("postal_code"));
			daerah.setDistrict(rs.getString("district"));
			daerah.setCity(rs.getString("city"));
			daerah.setCountry(rs.getString("country"));
			admin = new Admin();
			admin.setId(rs.getInt("id"));
			admin.setNama(rs.getString("nama"));
			admin.setUsername(rs.getString("username"));
			admin.setPassword(rs.getString("password"));
			admin.setAddress(rs.getString("address"));
			admin.setPosition(rs.getString("position"));
			admin.setDaerah(daerah);
		}
		return admin;
	}

}
