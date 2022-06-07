package com.clinic.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.CheckHealthDao;
import com.clinic.entity.CheckHealth;
import com.clinic.service.CheckHealthService;

@Service
public class CheckHealthServiceImpl implements CheckHealthService{

	@Autowired
	CheckHealthDao checkHealthDao;
	
	@Override
	public List<CheckHealth> getListVaccine(int userId) throws Exception {
		return checkHealthDao.getListCheckHealth(userId);
	}

	@Override
	public boolean addCheckHealthRecord(CheckHealth checkHealth) throws Exception {
		checkHealth.setCreatedDtm(new Date());
		checkHealth.setCreatedBy("SYSTEM");
		return checkHealthDao.addCheckHealthRecord(checkHealth);
	}

}
