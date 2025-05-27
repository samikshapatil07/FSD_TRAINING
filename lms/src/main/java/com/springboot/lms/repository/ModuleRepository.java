package com.springboot.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lms.model.CModule;

public interface ModuleRepository extends JpaRepository<CModule, Integer>{

}
 