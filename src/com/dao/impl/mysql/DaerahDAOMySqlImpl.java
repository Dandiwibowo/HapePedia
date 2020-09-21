package com.dao.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.DaerahDAO;
import com.entity.Daerah;

public class DaerahDAOMySqlImpl implements DaerahDAO {
    private Connection conn;
	
	public DaerahDAOMySqlImpl(Connection conn) {
		this.conn = conn;
	}

    @Override
    public List<Daerah> getAllDaerahs() throws Exception {
        List<Daerah> listOfDaerah = new ArrayList<Daerah>();
		String sql = "SELECT * FROM `tb_daerah`";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			Daerah daerah = new Daerah();
			daerah.setPostalCode(rs.getInt("postal_code"));
			daerah.setDistrict(rs.getString("district"));
			daerah.setCity(rs.getString("city"));
			daerah.setCountry(rs.getString("country"));
			listOfDaerah.add(daerah);
		}
		return listOfDaerah;
    }

    @Override
    public Daerah getDaerah(int postalCode) throws Exception {
        Daerah daerah = new Daerah();
		String sql = "SELECT * FROM `tb_daerah` where id=?";
		PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, postalCode);
        ResultSet rs = pst.executeQuery();
		if(rs.next()) {			
			daerah.setPostalCode(rs.getInt("postal_code"));
			daerah.setDistrict(rs.getString("district"));
			daerah.setCity(rs.getString("city"));
			daerah.setCountry(rs.getString("country"));
		}
		return daerah;
    }

    @Override
    public int addDaerah(Daerah daerah) throws Exception {
        String sql = "insert into `tb_daerah`(`postal_code`,`district`, `city`, `country`) VALUES (?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, daerah.getPostalCode());
		pst.setString(2, daerah.getDistrict());
		pst.setString(3, daerah.getCity());
		pst.setString(4, daerah.getCountry());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int updateDaerah(Daerah daerah) throws Exception {
        String sql = "UPDATE `tb_daerah` SET `postal_code`=?,`district`=?,`city`=?,`country`=? WHERE id= ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, daerah.getPostalCode());
		pst.setString(2, daerah.getDistrict());
		pst.setString(3, daerah.getCity());
        pst.setString(4, daerah.getCountry());
        pst.setInt(5, daerah.getPostalCode());
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }

    @Override
    public int deleteDaerah(int id) throws Exception {
        String sql = "DELETE FROM `tb_daerah` WHERE `postal_code`=?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		int affectedRow = pst.executeUpdate();
		return affectedRow;
    }
    
}
