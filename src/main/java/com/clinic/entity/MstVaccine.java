package com.clinic.entity;

import java.util.Date;

public class MstVaccine {

	private int id;
	private String vaccineName;
	private String vaccineType;
	private String vaccineDose;
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
	public String getVaccineName() {
		return vaccineName;
	}
	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}
	public String getVaccineType() {
		return vaccineType;
	}
	public void setVaccineType(String vaccineType) {
		this.vaccineType = vaccineType;
	}
	public String getVaccineDose() {
		return vaccineDose;
	}
	public void setVaccineDose(String vaccineDose) {
		this.vaccineDose = vaccineDose;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
