package com.clinic.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.entity.VaccineMaster;
import com.clinic.entity.VaccineRecord;
import com.clinic.service.VaccineService;
import com.clinic.dao.VaccineDao;

@Service
public class VaccineServiceImpl implements VaccineService{

	@Autowired
	VaccineDao vaccineDao;
	
	@Override
	public List < VaccineRecord > getListVaccine ( int userId ) throws Exception {
		return vaccineDao.getListVaccine(userId);
	}

	@Override
	public boolean addVaccineRecord ( VaccineRecord vaccine ) throws Exception {
		VaccineMaster mstVaccine = vaccineDao.getMstVaccineByCode( vaccine.getVaccineCode() );

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar expDate = Calendar.getInstance();
		Calendar vaccineDate = Calendar.getInstance();
		Date today = new Date();
		expDate.setTime(today);
		expDate.add(Calendar.DATE, mstVaccine.getExpiredDays());
		vaccineDate.setTime(today);
		vaccineDate.add(Calendar.DATE, mstVaccine.getNextVaccineDays());
         
        Date newExpDate = sdf.parse(sdf.format(expDate.getTime()));
        Date newVaccineDate = sdf.parse(sdf.format(vaccineDate.getTime()));
        
		vaccine.setNextVaccineDate(newExpDate);
		vaccine.setExpiredDate(newVaccineDate);
		vaccine.setVaccineDate(today);
		vaccine.setCreatedDtm(today);
		vaccine.setCreatedBy("SYSTEM");
		return vaccineDao.addVaccineRecord(vaccine);
	}

	@Override
	public VaccineMaster getMstVaccineByCode(String code) throws Exception {
		return vaccineDao.getMstVaccineByCode(code);
	}

	@Override
	public boolean addVaccineMaster(VaccineMaster vaccineMaster) throws Exception {
		int count = vaccineDao.countVaccineMaster();
		String code = "VACCINE_" + String.format("%03d", ++count);
		vaccineMaster.setCode(code);
		vaccineMaster.setCreatedDtm(new Date());
		vaccineMaster.setCreatedBy("SYSTEM");
		return vaccineDao.addVaccineMaster(vaccineMaster);
	}

}
