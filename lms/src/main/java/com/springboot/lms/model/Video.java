package com.springboot.lms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "video")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "video_title")
	private String videoTitle;

	@Column(name = "play_time")
	private float playTime;

	@Column(name = "video_code")
	private String videoCode;

	private int sequence;

	@ManyToOne
	private CModule module;

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

	public CModule getModule() {
		return module;
	}

	public void setModule(CModule module) {
		this.module = module;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}