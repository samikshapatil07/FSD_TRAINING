package com.springboot.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.lms.model.Course;
import com.springboot.lms.model.Learner;
import com.springboot.lms.model.LearnerCourse;

public interface LearnerCourseRepository extends JpaRepository<LearnerCourse, Integer>{

	@Query(value = "select * from learner_course where learner_id=?1 AND course_id=?2", nativeQuery = true)
	Optional<LearnerCourse> getUsingNativeSql(int learnerId, int courseId);
	
	@Query("select lc from LearnerCourse lc where lc.learner.id= ?1 AND lc.course.id=?2")
	Optional<LearnerCourse> getUsingJPQL(int learnerId, int courseId);

	@Query("select lc.learner from LearnerCourse lc where lc.course.id=?1")
	List<Learner> getLearnerByCourseId(int courseId);
	
	@Query("select lc.course from LearnerCourse lc where lc.learner.id=?1")
	List<Course> getCourseByLearnerId();
}

/*
 * Native Query locks this application on MySql DB(or on a particular DB)
 * INSTEAD 
 * Use JPQL as it queries the model class and not the DB tables so it does not care which DB we use. 
 * its DB Independent 
 * */