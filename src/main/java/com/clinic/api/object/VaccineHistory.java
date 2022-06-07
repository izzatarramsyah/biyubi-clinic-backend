package com.clinic.api.object;

import java.util.Date;

public class VaccineHistory {
	
	private Date date;
	private String name;
	private String type;
	private int batch;
	private Date expDate;
	private Date nextVaccineDate;
	private String notes;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public Date getNextVaccineDate() {
		return nextVaccineDate;
	}
	public void setNextVaccineDate(Date nextVaccineDate) {
		this.nextVaccineDate = nextVaccineDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
