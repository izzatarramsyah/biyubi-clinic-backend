package com.clinic.entity;

import java.util.Date;

public class Notification {

	private int id;
	private int userId;
	private String notifCode;
	private String messageDetail;
	private String status;
	private String notifDate;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNotifCode() {
		return notifCode;
	}
	public void setNotifCode(String notifCode) {
		this.notifCode = notifCode;
	}
	public String getMessageDetail() {
		return messageDetail;
	}
	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getNotifDate() {
		return notifDate;
	}
	public void setNotifDate(String notifDate) {
		this.notifDate = notifDate;
	}

}
