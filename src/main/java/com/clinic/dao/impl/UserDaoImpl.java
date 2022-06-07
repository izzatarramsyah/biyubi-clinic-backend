package com.clinic.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clinic.config.variable.ApplicationConstant;
import com.clinic.dao.UserDao;
import com.clinic.datamapper.ChildMapper;
import com.clinic.datamapper.UserMapper;
import com.clinic.entity.Child;
import com.clinic.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);

	public static final String INSERT_USER = "INSERT INTO TBL_USER "
			+ " ( USERNAME, PASSWORD, FULLNAME, ADDRESS, EMAIL, PHONE_NO, STATUS, CREATED_DTM, CREATED_BY ) "
			+ " VALUES (?,?,?,?,?,?,?,?,?) ";
	 
	public static final String INSERT_CHILD = "INSERT INTO TBL_CHILD "
			+ " ( USER_ID, FULLNAME, BIRTH_DATE, GENDER, NOTES, CREATED_DTM, CREATED_BY )"
			+ " VALUES (?,?,?,?,?,?,?) ";
	
	public static final String GET_USER_BY_USERNAME = "SELECT ID, USERNAME, FULLNAME, PASSWORD, ADDRESS, EMAIL, PHONE_NO, STATUS, LAST_ACTIVITY, "
			+ " CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ " FROM TBL_USER "
			+ " WHERE USERNAME = ? ";
	
	public static final String GET_USER_BY_EMAIL = "SELECT ID, USERNAME, FULLNAME, PASSWORD, ADDRESS, EMAIL, PHONE_NO, STATUS, LAST_ACTIVITY, "
			+ " CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ " FROM TBL_USER "
			+ " WHERE EMAIL = ? ";
	
	public static final String GET_USER_BY_PHONENO = "SELECT ID, USERNAME, FULLNAME, PASSWORD, ADDRESS, EMAIL, PHONE_NO, STATUS, LAST_ACTIVITY, "
			+ " CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ " FROM TBL_USER "
			+ " WHERE PHONE_NO = ? ";
	
	public static final String GET_USER_BY_ID = "SELECT ID, USERNAME, PASSWORD, FULLNAME, ADDRESS, EMAIL, PHONE_NO, STATUS, LAST_ACTIVITY, "
			+ " CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ " FROM TBL_USER "
			+ " WHERE ID = ? ";
	
	public static final String GET_CHILD_BY_USER_ID = "SELECT ID, USER_ID, FULLNAME, BIRTH_DATE, "
			+ " GENDER, NOTES, CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ " FROM TBL_CHILD "
			+ " WHERE USER_ID = ? ";

	public static final String UPDATE_USER_ACTIVITY = "UPDATE TBL_USER "
			+ " SET LAST_ACTIVITY = ?, "
			+ " LASTUPD_DTM = ? , "
			+ " LASTUPD_BY = ? "
			+ " WHERE USERNAME = ?";
	
	@Autowired
	@Qualifier(ApplicationConstant.BEAN_JDBC_CLINIC)
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean insertUser(User user) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", INSERT_USER);
		int result = 0;
		try{
			result = jdbcTemplate.update(INSERT_USER,
					new Object[] {user.getUsername(), user.getPassword(), user.getFullname(), 
							user.getAddress(), user.getEmail(), user.getPhone_no(), user.getStatus(), 
							user.getCreatedDtm(), user.getCreatedBy()});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}
	
	@Override
	public boolean insertChild(Child child) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", INSERT_CHILD);
		int result = 0;
		try{
			result = jdbcTemplate.update(INSERT_CHILD,
					new Object[] { child.getUserId(), child.getFullname(), child.getBirthDate(), child.getGender(),
							child.getNotes(), child.getCreatedDtm(), child.getCreatedBy() });
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public User getUserByUsername(String username) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_USER_BY_USERNAME);
		List < User > result = new ArrayList < User >();
		try{
			result = jdbcTemplate.query(GET_USER_BY_USERNAME, new Object[] { username }, new UserMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() > 0 ? result.get(0) : null;
	}
	
	@Override
	public User getUserByEmail(String email) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_USER_BY_EMAIL);
		List < User > result = new ArrayList < User >();
		try{
			result = jdbcTemplate.query(GET_USER_BY_EMAIL, new Object[] { email }, new UserMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() > 0 ? result.get(0) : null;
	}
	
	@Override
	public User getUserByPhoneNo(String phoneno) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_USER_BY_PHONENO);
		List < User > result = new ArrayList < User >();
		try{
			result = jdbcTemplate.query(GET_USER_BY_PHONENO, new Object[] { phoneno }, new UserMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() > 0 ? result.get(0) : null;
	}

	@Override
	public User getUserByID(int id) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_USER_BY_ID);
		List < User > result = new ArrayList < User >();
		try{
			result = jdbcTemplate.query(GET_USER_BY_ID, new Object[] { id }, new UserMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() > 0 ? result.get(0) : null;
	}

	@Override
	public Child getChildByUserID(int id) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_CHILD_BY_USER_ID);
		List < Child > result = new ArrayList < Child >();
		try{
			result = jdbcTemplate.query(GET_CHILD_BY_USER_ID, new Object[] { id }, new ChildMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() > 0 ? result.get(0) : null; 
	}
	
	@Override
	public boolean updateLastActivity(User user) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", UPDATE_USER_ACTIVITY);
		int result = 0;
		try{ 
			result = jdbcTemplate.update(UPDATE_USER_ACTIVITY, 
					new Object[] { user.getLastActivity(), user.getUpdatedDtm(), user.getUpdatedBy(), user.getUsername()});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}
	
}
