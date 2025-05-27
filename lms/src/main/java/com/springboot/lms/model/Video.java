package com.springboot.lms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vidoe")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  int id;
	
	@Column(name = "video_title")
	private String viodeoTitle;
	
	@Column(name = "play_time")
	private  float playTime;
	
	@Column(name = "video_code")
	private String videoCode;

	@ManyToOne
	private CModule module;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getViodeoTitle() {
		return viodeoTitle;
	}

	public void setViodeoTitle(String viodeoTitle) {
		this.viodeoTitle = viodeoTitle;
	}

	public float getPlayTime() {
		return playTime;
	}

	public void setPlayTime(float playTime) {
		this.playTime = playTime;
	}

	public String getVideoCode() {
		return videoCode;
	}

	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}
	
	

}
