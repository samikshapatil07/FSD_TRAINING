
package com.lms.service;

import java.time.LocalDate;
import java.util.List;

import com.lms.dao.CourseDao;
import com.lms.dao.TrackDao;
import com.lms.dao.impl.CourseDaoImpl;
import com.lms.dao.impl.TrackDaoImpl;
import com.lms.model.Course;
import com.lms.model.Track;

public class CourseService {

	private CourseDao courseDao = new CourseDaoImpl();
	private TrackDao trackDao = new TrackDaoImpl();
	
	public void insertTrack(Track track) {
		 //generate track id
		int id = (int) (Math.random() * 10000000);
		track.setId(id);
		trackDao.insert(track);
	}

	public void insertCourse(Course course, int trackId) {
		//generate track id
		int id = (int) (Math.random() * 10000000);
		course.setId(id);
		//set publish date to today's date
		course.setPublishDate(LocalDate.now());
		courseDao.insert(course, trackId);
	}

	public List<Course> getAll() {
		
		return courseDao.getAll();
	}

	public List<Course> getCourseByTrackId(int tid) {
	    return courseDao.getCourseByTrackId(tid);
	}

}
