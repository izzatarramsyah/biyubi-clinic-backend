package com.clinic.dao;

import java.util.List;

import com.clinic.entity.VaccineMaster;
import com.clinic.entity.VaccineRecord;

public interface VaccineDao {
	
	List < VaccineRecord > getListVaccine ( int userId ) throws Exception;
	
	boolean addVaccineRecord ( VaccineRecord vaccineRecord ) throws Exception;
	
	boolean addVaccineMaster ( VaccineMaster vaccineMaster ) throws Exception;

	VaccineMaster getMstVaccineByCode ( String code ) throws Exception;
	
	int countVaccineMaster () throws Exception;

}
