package com.clinic.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.AdminDao;
import com.clinic.dao.ParentDao;
import com.clinic.entity.Admin;
import com.clinic.entity.CheckRecord;
import com.clinic.entity.Child;
import com.clinic.entity.HealthRecord;
import com.clinic.entity.VaccineRecord;
import com.clinic.entity.MstMedicine;
import com.clinic.entity.MstTreatment;
import com.clinic.entity.MstVaccine;
import com.clinic.entity.Parent;
import com.clinic.service.AdminService;
import com.clinic.util.Util;

@Service 
public class AdminServiceImpl implements AdminService{

	private static final Logger LOG = LogManager.getLogger(AdminServiceImpl.class);

	@Autowired
	AdminDao adminDao;	
	
	@Autowired
	ParentDao parentDao;

	@Override
	public Map<String, Object> login(String username, String password) throws Exception {
		LOG.info("param login::{}:{}" +username, password);
		Admin adminUser = new Admin();
		try {
			adminUser = adminDao.getAdminUser(username, password);
			if (adminUser == null) {
				return Util.setMapResponse("User Not Found", false);
			}
			String sessionId = adminUser.getSessionId() != null && !"".equalsIgnoreCase(adminUser.getSessionId())
					? adminUser.getSessionId()
					: UUID.randomUUID().toString().replace("-", "").toUpperCase();
			adminUser.setSessionId(sessionId);
			adminDao.updateSessionId(sessionId, adminUser.getUsername());
			adminDao.updateLastActivity(username);
		} catch (Exception e) {
			LOG.error("ERR::[{}]:{}", e.getMessage());
			return Util.setMapResponse("System Error", false);
		}
		return Util.setMapResponse("success", true, adminUser);
	}	
	
	@Override
	public Map<String, Object> saveChild(Child child) throws Exception {
		
		LOG.info("param saveChild::{}:{}:{}:{}:{}:{}:{}" +child.getParentId(), child.getFullname(), child.getBirthDate(), 
				child.getGender(), child.getNotes(), "SYSTEM", new Timestamp(new Date().getTime()) );
		if (adminDao.saveChild(child)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false); 
	
	}
	
	@Override 
	public Map<String, Object> isValidLoginSession(String username, String password, String sessionId, int lastMinutes) throws Exception {
		LOG.info("param isValidLoginSession::{}:{}" +sessionId, lastMinutes);
		Admin admin = new Admin();
		admin = adminDao.getAdminUser(username, password);
		if (admin == null) {
			return Util.setMapResponse("User Not Found", false);
		}else{
			Date lastActivity = admin.getLastActivity();
			boolean isIdle = false;
			LOG.info(sessionId, lastMinutes);
			if(lastActivity == null){
				isIdle = true; 
			}else{
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MINUTE, (lastMinutes * -1));
				if(cal.getTime().after(lastActivity)){
					isIdle = true;
				}
			}
			if(sessionId == null){ //login request
				if(admin.getSessionId() == null || "".equalsIgnoreCase(admin.getSessionId())){
					return Util.setMapResponse("success", true); //User already logout
				}else{
					return Util.setMapResponse("failed", isIdle);
				}
			}else{ //Transactional request
				if(admin.getSessionId() == null || "".equalsIgnoreCase(admin.getSessionId())){
					return Util.setMapResponse("failed", false); //User already logout
				}else{
					if(sessionId.equalsIgnoreCase(admin.getSessionId())){ //user still active in last 15 minutes
						return Util.setMapResponse("success", true); //valid sessionId
					}else{
						return Util.setMapResponse("failed", false); //invalid sessionId
					}
				}
			}
		}
	}
	
	@Override
	public Map<String, Object> logout(Admin user) throws Exception{
		LOG.info("param logout::{}:{}" +user.getUsername(), user.getPassword());
		Admin admin = new Admin();
		admin = adminDao.getAdminUser(user.getUsername(), user.getPassword());
		if (admin == null) {
			return Util.setMapResponse("User Not Found", false);
		}else{
			adminDao.updateSessionId(null, user.getUsername());
		}
		return Util.setMapResponse("success", true); 
	}


	@Override
	public Map<String, Object> saveHealthRecord(HealthRecord param) throws Exception {
		LOG.info("param saveHealthRecord::{}:{}:{}:{}:{}:{}:{}:{}" +param.getParentId(), param.getChildId(), param.getComplains(), param.getFindings(),
				param.getTreatId(), param.getMedicineId(), new Timestamp(new Date().getTime()), "SYSTEM");
		if (adminDao.saveHealthRecord(param)) {
			return Util.setMapResponse("success", true); 
		}
		return Util.setMapResponse("failed", false); 
	
	}

	@Override
	public Map<String, Object> saveVaccineRecord(VaccineRecord param) throws Exception {
		LOG.info("param saveVaccineRecord::{}:{}:{}:{}:{}" +param.getChildId(), param.getVaccineId(), param.getNotes(),
				new Timestamp(new Date().getTime()), "SYSTEM");
		if (adminDao.saveVaccineRecord(param)) {
			return Util.setMapResponse("success", true);
		}
		return Util.setMapResponse("failed", false); 
	}

	@Override
	public Map<String, Object> getHealthRecord(HealthRecord param) throws Exception {
		LOG.info("param getHealthRecord::{}" +param.getParentId());
		List<HealthRecord> list = adminDao.getHealthRecord(param);
		return Util.setMapResponse("success", true, list);
	}

	@Override
	public Map<String, Object> getVaccineRecord(VaccineRecord param) throws Exception {
		LOG.info("param getVaccineRecord::{}" +param.getParentId());
		List<VaccineRecord> list = adminDao.getVaccineRecord(param);		
		return Util.setMapResponse("success", true, list);
	}

	@Override
	public Map<String, Object> getHealthRecord(HealthRecord param, String startDate, String endDate)
			throws Exception {
		LOG.info("param getHealthRecord::{}:{}:{}" +param.getParentId(), startDate, endDate);
		List<HealthRecord> list = adminDao.getHealthRecord(param, startDate, endDate);
		return Util.setMapResponse("success", true, list);
	}

	@Override
	public Map<String, Object> getVaccineRecord(VaccineRecord param, String startDate, String endDate)
			throws Exception {
		LOG.info("param getVaccineRecord::{}:{}:{}" +param.getParentId(), startDate, endDate);
		List<VaccineRecord> list = adminDao.getVaccineRecord(param, startDate, endDate);		
		return Util.setMapResponse("success", true, list);
	}

	@Override
	public Map<String, Object> getCheckRecord(CheckRecord param) throws Exception {
		LOG.info("param getCheckRecord::{}:{}:{}" +param.getParentId(), param.getChildId());
		List<CheckRecord> list = adminDao.getCheckRecord(param);		
		return Util.setMapResponse("success", true, list);
	}

	@Override
	public Map<String, Object> saveCheckRecord(CheckRecord param) throws Exception {
		LOG.info("param saveCheckRecord::{}:{}:{}:{}:{}:{}:{}:{}" +
				param.getParentId(), param.getChildId(), param.getWeight(), 
				param.getHeight(), param.getHeadCircumference(), param.getNotes(),
				new Timestamp(new Date().getTime()), "SYSTEM");
		if (adminDao.saveCheckRecord(param)) {
			return Util.setMapResponse("success", true);
		}
		return Util.setMapResponse("failed", false); 
	}

	@Override
	public Map<String, Object> getCheckRecord(CheckRecord param, String startDate, String endDate) throws Exception {
		LOG.info("param getCheckRecord::{}:{}:{}" +param.getParentId(), param.getChildId(), startDate, endDate);
		List<CheckRecord> list = adminDao.getCheckRecord(param, startDate, endDate);		
		return Util.setMapResponse("success", true, list);
	}

	
}
