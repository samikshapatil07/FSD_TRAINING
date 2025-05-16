package com.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lms.dao.TrackDao;
import com.lms.model.Track;
import com.lms.utility.DBUtility;

public class TrackDaoImpl implements TrackDao{

	DBUtility db = new DBUtility();
	
	@Override
	public void insert(Track track) {
		Connection con = db.connect();
		String sql = "insert into track(id,name) values (?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, track.getId());
			pstmt.setString(2, track.getName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		db.close();
	}

	
}