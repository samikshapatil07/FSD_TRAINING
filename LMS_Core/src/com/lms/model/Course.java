package com.lms.model;

import java.time.LocalDate;

public class Course {
	private int id;
	private String title; 
	private double fee;
	private double discount;
	private LocalDate publishDate; 
	
	//inject track here as course has-a track by creating a reference of Track
	private Track track;

	public Course() { }

	public Course(int id, String title, double fee, double discount, LocalDate publishDate, Track track) {
		super();
		this.id = id;
		this.title = title;
		this.fee = fee;
		this.discount = discount;
		this.publishDate = publishDate;
		this.track = track;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", fee=" + fee + ", discount=" + discount + ", publishDate="
				+ publishDate + ", track=" + track + "]";
	} 
	
	
	
}