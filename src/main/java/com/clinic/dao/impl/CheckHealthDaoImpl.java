package com.clinic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clinic.config.variable.ApplicationConstant;
import com.clinic.dao.CheckHealthDao;
import com.clinic.datamapper.CheckHealthMapper;
import com.clinic.entity.CheckHealth;

@Repository
public class CheckHealthDaoImpl implements CheckHealthDao{
	
	private static final Logger LOG = LogManager.getLogger(CheckHealthDaoImpl.class);

	public static final String GET_LIST_CHECK_HEALTH = "SELECT ID, USER_ID, CHILD_ID, WEIGHT, LENGTH, HEAD_CIRCUMFERENCE, NOTES, "
			+ "CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ "FROM BIYUBIDB.TBL_CHECK_HEALTH "
			+ "WHERE USER_ID = ? ";
	
	public static final String ADD_CHECK_HEALTH_RECORD = "INSERT INTO BIYUBIDB.TBL_CHECK_HEALTH "
			+ " ( USER_ID, CHILD_ID, WEIGHT, LENGTH, HEAD_CIRCUMFERENCE, NOTES, CREATED_DTM, CREATED_BY) "
			+ " VALUES (?,?,?,?,?,?,?,?) ";
	
	
	@Autowired
	@Qualifier(ApplicationConstant.BEAN_JDBC_CLINIC)
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List < CheckHealth > getListCheckHealth ( int userId ) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_LIST_CHECK_HEALTH);
		List < CheckHealth > result = new ArrayList < CheckHealth >();
		try{
			result = jdbcTemplate.query(GET_LIST_CHECK_HEALTH, new Object[] { userId }, new CheckHealthMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}

	@Override
	public boolean addCheckHealthRecord(CheckHealth checkHealth) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_CHECK_HEALTH_RECORD);
		int result = 0;
		try{
			result = jdbcTemplate.update(ADD_CHECK_HEALTH_RECORD,
					new Object[] { checkHealth.getUserId(), checkHealth.getChildId(), checkHealth.getWeight(), 
							checkHealth.getLength(), checkHealth.getHeadCircumference(), checkHealth.getNotes(),
							checkHealth.getCreatedDtm(), checkHealth.getCreatedBy() });
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true; 
	}

}
