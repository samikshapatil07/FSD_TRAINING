package com.hibernate.main.dto;

import java.util.List;

import com.hibernate.main.model.Course;
import com.hibernate.main.model.Module;
import com.hibernate.main.model.Video; 

public class ContainerDto {
	private Course course;
	private List<Video> listVideos;
	private List<Module> listModules;

	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Video> getListVideos() {
		return listVideos;
	}

	public void setListVideos(List<Video> listVideos) {
		this.listVideos = listVideos;
	}

	public List<Module> getListModules() {
		return listModules;
	}

	public void setListModules(List<Module> listModules) {
		this.listModules = listModules;
	}

	@Override
	public String toString() {
		return "ContainerDto [course=" + course + ", listVideos=" + listVideos + ", listModules=" + listModules + "]";
	}
 

	
}