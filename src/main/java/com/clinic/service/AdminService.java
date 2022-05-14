package com.clinic.service;

import java.util.Map;

import com.clinic.entity.Admin;
import com.clinic.entity.CheckRecord;
import com.clinic.entity.Child;
import com.clinic.entity.HealthRecord;
import com.clinic.entity.VaccineRecord;

public interface AdminService {

	Map<String, Object> login(String username, String password) throws Exception;
	Map<String, Object> saveChild(Child child) throws Exception;
	Map<String, Object> isValidLoginSession(String username, String password, String sessionId, int lastMinutes) throws Exception;
	Map<String, Object> logout(Admin user) throws Exception;
	Map<String, Object> saveHealthRecord(HealthRecord param) throws Exception;
	Map<String, Object> saveVaccineRecord(VaccineRecord param) throws Exception;
	Map<String, Object> saveCheckRecord(CheckRecord param) throws Exception;
	Map<String, Object> getHealthRecord (HealthRecord param) throws Exception;
	Map<String, Object> getVaccineRecord(VaccineRecord param) throws Exception;
	Map<String, Object> getHealthRecord (HealthRecord param, String startDate, String endDate) throws Exception;
	Map<String, Object> getVaccineRecord(VaccineRecord param, String startDate, String endDate) throws Exception;
	Map<String, Object> getCheckRecord(CheckRecord param) throws Exception;
	Map<String, Object> getCheckRecord(CheckRecord param, String startDate, String endDate) throws Exception;
}
