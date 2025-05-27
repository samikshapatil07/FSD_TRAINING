package com.hibernate.main.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hibernate.main.model.Learner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class LearnerRepository {
	@PersistenceContext
	 private EntityManager em;
	 public void insert(Learner learner) {
		 em.persist(learner);
	 }
	public List<Learner> getAll() {
		return em.createQuery("FROM Learner", Learner.class).getResultList();
	}
	
	public Learner getById(int id) {
		return em.find(Learner.class, id);
	}
	
	public void delete(int id) {
		em.remove(getById(id));
	}
	public void update(Learner learner) {
		em.merge(learner);		
	}
}



/*
 *    em: null 
 *    
 *    getAll() --- em   em=null 
 *    getById() -- em: 100X  em=null 
 *    delete(learner) -- em:200X :---   u fetched using 100X, 
 *    then i detached this em with 100X and u r deleting using 200X 
 *    which was fetched using 100X and its already detached. 
 *    
 *    HQL: FROM Learner 
 *    
 *    DaoImpl <class>
 * 
 * 	  	
 * 	  Repository <class>  
 * 
 * Learner 
 *     User 	
 * 
 */