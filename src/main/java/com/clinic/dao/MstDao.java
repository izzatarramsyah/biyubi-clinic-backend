package com.clinic.dao;

import java.util.List;

import com.clinic.entity.MstMedicine;
import com.clinic.entity.MstTreatment;
import com.clinic.entity.MstType;
import com.clinic.entity.MstVaccine;

public interface MstDao {

	List<MstVaccine> getMstVaccine() throws Exception;
	List<MstMedicine> getMstMedicines() throws Exception;
	List<MstTreatment> getMstTreatments() throws Exception;
	MstVaccine getMstVaccineById(int id) throws Exception;
	MstMedicine getMstMedicinesById(int id) throws Exception;
	MstTreatment getMstTreatmentsById(int id) throws Exception;
	boolean editMstVaccine(MstVaccine mstVaccine) throws Exception;
	boolean editMstMedicines(MstMedicine mstMedicines) throws Exception;
	boolean editMstTreatments(MstTreatment mstTreatments) throws Exception;
	boolean saveMstVaccine(MstVaccine param) throws Exception;
	boolean saveMstMedicines(MstMedicine param) throws Exception;
	boolean saveMstTreatments(MstTreatment param) throws Exception;
	boolean deleteMstVaccine(int id) throws Exception;
	boolean deleteMstMedicines(int id) throws Exception;
	boolean deleteMstTreatments(int id) throws Exception;
	List<MstType> getMstType(String mstTable) throws Exception;
	boolean addMstType(MstType mstType) throws Exception;

}
