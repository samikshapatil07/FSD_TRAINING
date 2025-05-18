package com.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lms.dao.EnrollDao;
import com.lms.model.Enroll;
import com.lms.utility.DBUtility;

public class EnrollDaoImpl implements EnrollDao {

	DBUtility db = new DBUtility();
	
	@Override
	public void insert(Enroll enroll) {
		Connection con = db.connect();
		String sql = "insert into enroll values (?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, enroll.getLearner().getId());
			pstmt.setInt(2, enroll.getCourse().getId());
			pstmt.setString(3, enroll.getDateOfEnroll().toString());
			pstmt.setString(4, String.valueOf(enroll.getCoupon()));
			pstmt.setString(5, enroll.getFeePaid());
			
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		db.close();

	}

}