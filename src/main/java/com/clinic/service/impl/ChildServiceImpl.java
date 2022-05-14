package com.clinic.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.ChildDao;
import com.clinic.entity.Child;
import com.clinic.entity.HealthRecord;
import com.clinic.entity.VaccineRecord;
import com.clinic.service.ChildService;
import com.clinic.util.Util;

@Service
public class ChildServiceImpl implements ChildService{

	private static final Logger LOG = LogManager.getLogger(ChildServiceImpl.class);

	@Autowired
	ChildDao childDao;

	@Override
	public Map<String, Object> getChildByParentID(int parentId) throws Exception {
		LOG.info("param getChildByParentID::{}" +parentId);
		Child child = childDao.getChildByParentID(parentId);
		return Util.setMapResponse("success", true, child);
	}

}
