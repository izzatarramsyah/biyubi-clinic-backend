package com.clinic.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.ParentDao;
import com.clinic.dao.impl.ParentDaoImpl;
import com.clinic.entity.Parent;
import com.clinic.service.ParentService;
import com.clinic.util.Util;

@Service
public class ParentServiceImpl implements ParentService{

	private static final Logger LOG = LogManager.getLogger(ParentServiceImpl.class);
	
	@Autowired
	ParentDao parentDao;
	
	@Override
	public Map<String, Object> saveParentUser(Parent parentUser) throws Exception {
		LOG.info("param saveParentUser::{}:{}:{}:{}:{}:{}:{}:{}:{}", 
				parentUser.getId(), parentUser.getUsername(), parentUser.getPassword(), parentUser.getFullname(),
				parentUser.getPhoneNo(), parentUser.getAddress(), parentUser.getEmail(), "SYSTEM", new Timestamp(new Date().getTime()));
		parentUser.setCreatedBy(parentUser.getUsername());
		parentUser.setCreatedDtm(new Timestamp(new Date().getTime()));
		if (!parentDao.saveParentUser(parentUser)) {
			return Util.setMapResponse("failed", false);
		}
		return Util.setMapResponse("success", true);
	}

	@Override
	public Map<String, Object> login(String username, String password) throws Exception{
		LOG.info("param login::{} ", username, password);
		Parent parent = parentDao.getParentUser(username);
		if (parent == null) {
			return Util.setMapResponse("User Not Found", false);
		}
		parentDao.updateLastActivity(username);
		return Util.setMapResponse("success", true);
	}

	@Override
	public Map<String, Object> getParentUserByName(String username) throws Exception {
		LOG.info("param getParentUserByName::{} ", username);
		Parent parent = parentDao.getParentUserByName(username);
		if (parent == null) {
			return Util.setMapResponse("User Not Found", false);
		}
		return Util.setMapResponse("success", true, parent);
	}
	
	
	
	
}
