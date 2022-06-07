package com.clinic.entity;

import java.util.Date;

public class VaccineMaster {
	
	private String code;
	private String name;
	private String type;
	private int expiredDays;
	private int nextVaccineDays;
	private String notes;
	private Date createdDtm;
	private String createdBy;
	private Date lastUpdDtm;
	private String lastUpdBy;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public int getExpiredDays() {
		return expiredDays;
	}
	public void setExpiredDays(int expiredDays) {
		this.expiredDays = expiredDays;
	}
	public int getNextVaccineDays() {
		return nextVaccineDays;
	}
	public void setNextVaccineDays(int nextVaccineDays) {
		this.nextVaccineDays = nextVaccineDays;
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
	public Date getLastUpdDtm() {
		return lastUpdDtm;
	}
	public void setLastUpdDtm(Date lastUpdDtm) {
		this.lastUpdDtm = lastUpdDtm;
	}
	public String getLastUpdBy() {
		return lastUpdBy;
	}
	public void setLastUpdBy(String lastUpdBy) {
		this.lastUpdBy = lastUpdBy;
	}

}
