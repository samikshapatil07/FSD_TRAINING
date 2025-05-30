package com.springboot.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.lms.model.CModule;

public interface ModuleRepository extends JpaRepository<CModule, Integer>{

	@Query("select m from CModule m where m.course.id=?1")
	List<CModule> getModuleByCourseId(int courseId);

}