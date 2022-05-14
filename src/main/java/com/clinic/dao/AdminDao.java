package com.clinic.dao;

import java.util.List;

import com.clinic.entity.Admin;
import com.clinic.entity.CheckRecord;
import com.clinic.entity.Child;
import com.clinic.entity.HealthRecord;
import com.clinic.entity.VaccineRecord;

public interface AdminDao {
	
	Admin getAdminUser(String username, String password) throws Exception;
	boolean saveChild(Child child) throws Exception;
	boolean updateLastActivity(String username) throws Exception;
	boolean updateSessionId(String sessionId, String username) throws Exception;
	boolean saveHealthRecord(HealthRecord param) throws Exception;
	boolean saveVaccineRecord(VaccineRecord param) throws Exception;
	boolean saveCheckRecord(CheckRecord param) throws Exception;
	List<HealthRecord> getHealthRecord(HealthRecord param) throws Exception;
	List<VaccineRecord> getVaccineRecord(VaccineRecord param) throws Exception;
	List<HealthRecord> getHealthRecord(HealthRecord param, String startDate, String endDate) throws Exception;
	List<VaccineRecord> getVaccineRecord(VaccineRecord param, String startDate, String endDate) throws Exception;
	List<CheckRecord> getCheckRecord(CheckRecord param) throws Exception;
	List<CheckRecord> getCheckRecord(CheckRecord param, String startDate, String endDate) throws Exception;


}
