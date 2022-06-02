package com.clinic.entity;

import java.util.Date;

public class MstNotification {
	
	private int id;
	private String notifCode;
	private String message;
	private Date createdDtm;
	private String createdBy;
	private Date lastUpdDtm;
	private String lastUpdBy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNotifCode() {
		return notifCode;
	}
	public void setNotifCode(String notifCode) {
		this.notifCode = notifCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
