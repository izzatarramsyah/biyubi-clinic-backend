package com.clinic.entity;

import java.util.Date;

public class MstMedicine {

	private int id;
	private String medicineName;
	private String medicineType;
	private String description;
	private int ttlUnit;
	private int remainingUnit;
	private String expiredDate;
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
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTtlUnit() {
		return ttlUnit;
	}
	public void setTtlUnit(int ttlUnit) {
		this.ttlUnit = ttlUnit;
	}
	public int getRemainingUnit() {
		return remainingUnit;
	}
	public void setRemainingUnit(int remainingUnit) {
		this.remainingUnit = remainingUnit;
	}
	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
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
