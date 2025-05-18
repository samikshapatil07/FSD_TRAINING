package com.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.CourseDao;
import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Course;
import com.lms.model.Track;
import com.lms.utility.DBUtility;

public class CourseDaoImpl implements CourseDao{
	DBUtility db = new DBUtility();
	
	@Override
	public void insert(Course course,int trackId) {
		Connection con = db.connect();
		String sql = "insert into course values (?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, course.getId());
			pstmt.setString(2, course.getTitle());
			pstmt.setDouble(3, course.getFee());
			pstmt.setDouble(4, course.getDiscount());
			pstmt.setString(5, course.getPublishDate().toString());
			pstmt.setInt(6, trackId);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		db.close();
	}

	@Override
	public List<Course> getAll() {
		Connection con = db.connect();
		String sql="select * from course c join track t ON c.track_id = t.id";
		List<Course> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()) {
				Course course = new Course();
				course.setId(rst.getInt("id"));
				course.setTitle(rst.getString("title"));
				course.setFee(rst.getDouble("fee"));
				
				Track track = new Track();
				track.setName(rst.getString("name"));
				
				//attach track to course 
				course.setTrack(track);
				list.add(course);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		db.close();
		return list;
	}

	@Override
	public List<Course> getByTrackId(int trackId) throws InvalidIdException{
		 
		return null;
	}

	@Override
	public Course getById(int courseId) throws InvalidIdException {
		Connection con = db.connect();
		String sql="select * from course where id=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, courseId);
			
			ResultSet rst=  pstmt.executeQuery();
			if(rst.next() == true) {
				Course course = new Course();
				course.setId(rst.getInt("id"));
				course.setTitle(rst.getString("title"));
				course.setFee(rst.getDouble("fee"));
				course.setDiscount(rst.getDouble("discount"));
				
				return course; 
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		db.close();
		throw new InvalidIdException("Course ID given is Invalid");
		 
	}

	@Override
	public List<Course> getCourseByTrackId(int tid) {
		Connection con = db.connect();
	    String sql = "SELECT c.*, t.name FROM course c JOIN track t ON c.track_id = t.id WHERE c.track_id = ?";
	    List<Course> list = new ArrayList<>();
	    
	    try {
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, tid);
	        ResultSet rst = pstmt.executeQuery();
	        
	        while (rst.next()) {
	            Course course = new Course();
	            course.setId(rst.getInt("id"));
	            course.setTitle(rst.getString("title"));
	            course.setFee(rst.getDouble("fee"));
	            course.setDiscount(rst.getDouble("discount"));
	            course.setPublishDate(rst.getDate("publish_date").toLocalDate());

	            Track track = new Track();
	            track.setId(tid);
	            track.setName(rst.getString("name"));

	            course.setTrack(track);
	            list.add(course);
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } finally {
	        db.close();
	    }
	    
	    return list;		
	}
	
	
}