package com.hibernate.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.main.dto.ContainerDto;
import com.hibernate.main.model.Course;
import com.hibernate.main.model.Module;
import com.hibernate.main.model.Video;
import com.hibernate.main.repository.CourseRepository;

@Service
public class CourseService {

	private CourseRepository courseRepository;
	
	@Autowired
	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}


	 
	
	public ContainerDto getById(int id) {
		 
		List<Video> listVideo =  courseRepository.getAllVideos();
		listVideo = listVideo.stream()
						.filter(v-> v.getModule().getCourse().getId() == id)
						.toList();
		 //fetch all modules list from the above video list 
		
		List<Module> listModule = listVideo.stream()	
										.map(v->v.getModule())
										.distinct()
										.toList();
		
		ContainerDto dto = new ContainerDto();
		dto.setCourse(listModule.get(0).getCourse());
		dto.setListModules(listModule);
		dto.setListVideos(listVideo);
		return dto;
	}

}