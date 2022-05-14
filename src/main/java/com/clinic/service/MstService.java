package com.clinic.service;

import java.util.List;
import java.util.Map;

import com.clinic.entity.MstMedicine;
import com.clinic.entity.MstTreatment;
import com.clinic.entity.MstType;
import com.clinic.entity.MstVaccine;

public interface MstService {

	Map<String, Object> getMstVaccine() throws Exception;
	Map<String, Object> getMstMedicines() throws Exception;
	Map<String, Object> getMstTreatments() throws Exception;
	Map<String, Object> getMstVaccineById(int vaccineId) throws Exception;
	Map<String, Object> getMstMedicinesById(int medicinesId) throws Exception;
	Map<String, Object> getMstTreatmentsById(int treatId) throws Exception;
	Map<String, Object> editMstVaccine(MstVaccine mstVaccine) throws Exception;
	Map<String, Object> editMstMedicines(MstMedicine mstMedicines) throws Exception;
	Map<String, Object> editMstTreatments(MstTreatment mstTreatments) throws Exception;
	Map<String, Object> saveMstVaccine(MstVaccine param) throws Exception;
	Map<String, Object> saveMstMedicines(MstMedicine param) throws Exception;
	Map<String, Object> saveMstTreatments(MstTreatment param) throws Exception;
	Map<String, Object> deleteMstMedicines(int id) throws Exception;
	Map<String, Object> deleteMstVaccine(int id) throws Exception;
	Map<String, Object> deleteMstTreatments(int id) throws Exception;
	Map<String, Object> getMstType(String mstTable) throws Exception;
	Map<String, Object> addMstType(MstType mstType) throws Exception;

}
