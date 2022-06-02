package com.clinic.entity;

import java.util.Date;

public class CheckHealth {
	
	private int id;
	private int userId;
	private int weight;
	private int height;
	private int headCircumference;
	private String notes;
	private Date createdDtm;
	private String createdBy;
	private Date updatedDtm;
	private String updatedBy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeadCircumference() {
		return headCircumference;
	}
	public void setHeadCircumference(int headCircumference) {
		this.headCircumference = headCircumference;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getCreatedDtm() {
		return createdDtm;
	}
	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdatedDtm() {
		return updatedDtm;
	}
	public void setUpdatedDtm(Date updatedDtm) {
		this.updatedDtm = updatedDtm;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
