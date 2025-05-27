package com.hibernate.main.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hibernate.main.model.Course;
import com.hibernate.main.model.Video;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CourseRepository {

	@PersistenceContext
	private EntityManager em;

	public Course getById(int id) {
		 
		return em.find(Course.class, id);
	} 
	
	public List<Video> getAllVideos(){
		return em.createQuery("FROM Video", Video.class).getResultList();
	}
	
}