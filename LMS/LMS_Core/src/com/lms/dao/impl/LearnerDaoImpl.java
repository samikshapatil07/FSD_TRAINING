
package com.lms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.dao.LearnerDao;
import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Learner;
import com.lms.utility.DBUtility;
import com.lms.utility.LearnerUtility;

public class LearnerDaoImpl implements LearnerDao{

 	private LearnerUtility learnerUtility = new LearnerUtility(); //100X: []
	@Override
	public List<Learner> getAll() {
		 DBUtility db = new DBUtility(); 
		 Connection con = db.connect();
		 String sql="select * from learner";
		 List<Learner> list = new ArrayList<>() ;
		 //prepare the statement 
		 try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			//execute the statement 
			ResultSet rst =  pstmt.executeQuery(sql);
			while(rst.next() == true) { //while the records exist in the DB 
				//read the columns id,name,email 
				Learner learner = new Learner(     //10X 
									rst.getInt("id"),
									rst.getString("name"),
									rst.getString("email")); 
				list.add(learner);
			}		
		} catch (SQLException e) {
			 System.out.println(e.getMessage());
		} 
		 db.close();	
		return list; 
	}

	@Override
	public Learner getById(int id) throws InvalidIdException {
		
		DBUtility db = new DBUtility();
	    Connection con = db.connect();
	    String sql = "SELECT * FROM learner WHERE id = ?";
	    Learner learner = null;
	    
	    try {
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            learner = new Learner();
	            learner.setId(rs.getInt("id"));
	            learner.setName(rs.getString("name"));
	            learner.setEmail(rs.getString("email"));
	        } else {
	            throw new InvalidIdException("Id given is Invalid");
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } finally {
	        db.close();
	    }
 
	    return learner;
		/*
		for(Learner learner : learnerUtility.getSampleData()) { //100X: [l1,l2,l3,l4]
			if(learner.getId() == id) 
					return learner; 
		}
		
		throw new InvalidIdException("Id given is Invalid");
		*/
		/*
		List<Learner> list =  learnerUtility.getSampleData();  //3
		list = list.stream()
				.filter(l-> l.getId() == id)
				.toList();
		if(list.isEmpty())
			throw new InvalidIdException("Id given is Invalid");
		
		return list.get(0);
		*/ 
	}

	@Override
	public void deleteById(int id) throws InvalidIdException {
		
		List<Learner> list = learnerUtility.getSampleData(); //100X=[1,2,3,4]
		int size  = list.size(); //this is the initial size 
		//System.out.println("In delete method... " + list);
		//filter the record as per given id 
		list =  list.stream().filter(l->l.getId() != id).toList();
		int resetSize = list.size();
		if(size == resetSize)
			throw new InvalidIdException("Could not find givn ID"); 
		
		//System.out.println("In delete, after filter,,, "+ list);
		LearnerUtility.setList(list);
	}
	//100X:  [2,3,4,1]
	//200X: [2,3,4,1]
	@Override
	public Learner update(int id, Learner learner) throws InvalidIdException, InvalidInputException { 
		deleteById(id); 
		List<Learner> list = getAll();
		List<Learner> newList = new ArrayList<>(list); //200X
		newList.add(learner);
		LearnerUtility.setList(newList);
		return learner;
	}

	@Override
	public void insert(Learner learner) throws InvalidInputException {
		DBUtility db = new DBUtility();
		// Establish the Connection
		Connection con = db.connect();
		//Generate random ID for Learner 
		int id = (int) (Math.random() * 1000000000);
		//SQL to insert record 
		String sql="insert into learner(id,name,email) values (?,?,?)";
		try {
			//Prepare the sql statement 
			PreparedStatement pstmt = con.prepareStatement(sql);
			//Give values of ? query parameter
			pstmt.setInt(1, id);
			pstmt.setString(2, learner.getName());
			pstmt.setString(3, learner.getEmail());
			//run the statement 
			pstmt.executeUpdate();
		} catch (SQLException e) {
			 System.out.println(e.getMessage());
		}
		
		db.close();
		
	}

	 public static void main(String[] args) {
		 
		LearnerDaoImpl dao = new LearnerDaoImpl();
		System.out.println("------------------------------------");
		dao.getAll().stream()
					.forEach(l-> System.out.println(l));
		System.out.println("------------------------------------");
		 
		try {
			dao.deleteById(4);
			
		} catch (InvalidIdException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("------------------------------------");
		dao.getAll().stream()
					.forEach(l-> System.out.println(l));
		System.out.println("------------------------------------");
		
	}

}
