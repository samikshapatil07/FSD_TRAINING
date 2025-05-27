package com.hibernate.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	@Column(name = "video_title")
	private String videoTitle; 
	@ManyToOne //<-- module_id will be created in video tbl
	private Module module;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	@Override
	public String toString() {
		return "Video [id=" + id + ", videoTitle=" + videoTitle + ", module=" + module + "]";
	} 
	
	
}