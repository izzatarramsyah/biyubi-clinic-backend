package com.clinic.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.MstDao;
import com.clinic.dao.impl.AdminDaoImpl;
import com.clinic.entity.MstVaccine;
import com.clinic.service.MstService;
import com.clinic.util.Util;
import com.clinic.entity.MstMedicine;
import com.clinic.entity.MstTreatment;
import com.clinic.entity.MstType;

@Service
public class MstServiceImpl implements MstService{

	private static final Logger LOG = LogManager.getLogger(MstServiceImpl.class);
	
	@Autowired
	MstDao mstDao;
	
	@Override
	public Map<String, Object> getMstVaccine() throws Exception {
		List<MstVaccine> list = mstDao.getMstVaccine();
		return Util.setMapResponse("success", true, list);
	}
 
	@Override
	public Map<String, Object> getMstMedicines() throws Exception {
		List<MstMedicine> list = mstDao.getMstMedicines();
		return Util.setMapResponse("success", true, list);
	}

	@Override
	public Map<String, Object> getMstTreatments() throws Exception {
		List<MstTreatment> list = mstDao.getMstTreatments();
		return Util.setMapResponse("success", true, list);
	}

	@Override
	public Map<String, Object> editMstVaccine(MstVaccine mstVaccine) throws Exception {
		LOG.info("param editMstVaccine ::{},{},{},{},{},{},{},{},{}", 
				mstVaccine.getVaccineName(), mstVaccine.getVaccineType(), mstVaccine.getVaccineDose(), mstVaccine.getDescription(),
				mstVaccine.getTtlUnit(), mstVaccine.getRemainingUnit(), new Timestamp(new Date().getTime()), "SYSTEM",
				mstVaccine.getId());
		if (mstDao.editMstVaccine(mstVaccine)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false);
	}

	@Override
	public Map<String, Object> editMstMedicines(MstMedicine mstMedicines) throws Exception {
		LOG.info("param editMstMedicines ::{},{},{},{},{},{},{},{},{}", 
				mstMedicines.getMedicineName(), mstMedicines.getMedicineType(), mstMedicines.getDescription(),
				mstMedicines.getTtlUnit(), mstMedicines.getRemainingUnit(), mstMedicines.getExpiredDate(),
				new Timestamp(new Date().getTime()), "SYSTEM", mstMedicines.getId());
		if (mstDao.editMstMedicines(mstMedicines)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false);
	}

	@Override
	public Map<String, Object> editMstTreatments(MstTreatment mstTreatments) throws Exception {
		LOG.info("param editMstTreatments ::{},{},{},{},{},{}", 
				 mstTreatments.getTreatType(), mstTreatments.getTreatName(), mstTreatments.getCategory(),
				new Timestamp(new Date().getTime()), "SYSTEM",mstTreatments.getId());
		if (mstDao.editMstTreatments(mstTreatments)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false);
	}
	
	@Override
	public Map<String, Object> saveMstVaccine(MstVaccine param) throws Exception {
		LOG.debug("param saveMstVaccine::{}:{}:{}:{}:{}:{}:{}", 
				param.getVaccineName(), param.getVaccineType(), param.getVaccineDose(), 
				param.getDescription(), param.getTtlUnit(), param.getRemainingUnit(), param.getExpiredDate());
		if (mstDao.saveMstVaccine(param)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false);
	}
 
	@Override
	public Map<String, Object> saveMstMedicines(MstMedicine param) throws Exception {
		LOG.info("param saveMstMedicines::{}:{}:{}:{}:{}:{}", 
				param.getMedicineName(), param.getMedicineType(), param.getDescription(), 
				param.getTtlUnit(), param.getRemainingUnit(), param.getExpiredDate());
		if (mstDao.saveMstMedicines(param)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false);
	}
	
	@Override
	public Map<String, Object> saveMstTreatments(MstTreatment param) throws Exception {
		LOG.info("info saveMstTreatments::{}:{}:{}", 
				param.getTreatType(), param.getTreatName(), param.getCategory(),
				"SYSTEM", new Timestamp(new Date().getTime()));
		if (mstDao.saveMstTreatments(param)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false);
	}

	@Override
	public Map<String, Object> deleteMstMedicines(int id) throws Exception {
		LOG.info("param deleteMstMedicines ::{}",  id);
		if (mstDao.deleteMstMedicines(id)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false);
	}

	@Override
	public Map<String, Object> deleteMstVaccine(int id) throws Exception {
		LOG.info("param deleteMstVaccine::{}", id);
		if (mstDao.deleteMstVaccine(id)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false);
	}

	@Override
	public Map<String, Object> deleteMstTreatments(int id) throws Exception {
		LOG.info("param deleteMstTreatments::{}", id);
		if (mstDao.deleteMstTreatments(id)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false);
	}

	@Override
	public Map<String, Object> getMstVaccineById(int vaccineId) throws Exception {
		LOG.debug("param getMstVaccineById::{}", vaccineId);
		MstVaccine mstVaccine = mstDao.getMstVaccineById(vaccineId);
		return Util.setMapResponse("success", true, mstVaccine);
	}

	@Override
	public Map<String, Object> getMstMedicinesById(int medicinesId) throws Exception {
		LOG.debug("param getMstMedicinesById::{}", medicinesId);
		MstMedicine mstMedicine = mstDao.getMstMedicinesById(medicinesId);
		return Util.setMapResponse("success", true, mstMedicine);
	}

	@Override
	public Map<String, Object> getMstTreatmentsById(int treatId) throws Exception {
		LOG.debug("param getMstTreatmentsById::{}", treatId);
		MstTreatment mstTreatment = mstDao.getMstTreatmentsById(treatId);
		return Util.setMapResponse("success", true, mstTreatment);
	}
	

	@Override
	public Map<String, Object> getMstType(String mstTable) throws Exception {
		LOG.debug("param getMstTypegetMstType::{}", mstTable);
		List<MstType> mstTreatment = mstDao.getMstType(mstTable);
		return Util.setMapResponse("success", true, mstTreatment);
	}

	@Override
	public Map<String, Object> addMstType(MstType mstType) throws Exception {
		LOG.info("param addMstType::{}", mstType);
		if (mstDao.addMstType(mstType)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false);
	}

}
