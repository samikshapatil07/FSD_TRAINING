package com.springboot.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lms.model.Video;

public interface VideoRepository extends JpaRepository<Video, Integer>{

}
