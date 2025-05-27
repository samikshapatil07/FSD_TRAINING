package com.hibernate.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_document")
public class CourseDocument {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	@Column(name = "document_heading")
	private String documentHeading; 
	private String documentLink; 
	@ManyToOne
	private Module module; 
}