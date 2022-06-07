package com.clinic.service;

import java.util.List;

import com.clinic.entity.VaccineMaster;
import com.clinic.entity.VaccineRecord;

public interface VaccineService {
	
	List < VaccineRecord > getListVaccine (int userId) throws Exception;
	
	VaccineMaster getMstVaccineByCode (String code) throws Exception;
	
	boolean addVaccineRecord (VaccineRecord vaccine) throws Exception;
	
	boolean addVaccineMaster ( VaccineMaster vaccineMaster ) throws Exception;

}
