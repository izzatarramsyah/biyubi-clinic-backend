package com.clinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.entity.Vaccine;
import com.clinic.service.VaccineService;

@Service
public class VaccineServiceImpl implements VaccineService{

	@Autowired
	VaccineService vaccineService;
	
	@Override
	public List<Vaccine> getListVaccine(int userId) throws Exception {
		return vaccineService.getListVaccine(userId);
	}

	@Override
	public boolean addVaccineRecord(Vaccine vaccine) throws Exception {
		return vaccineService.addVaccineRecord(vaccine);
	}

}
