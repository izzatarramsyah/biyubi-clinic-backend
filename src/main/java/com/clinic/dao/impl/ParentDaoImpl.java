package com.clinic.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.clinic.dao.ParentDao;
import com.clinic.entity.Parent;
import com.clinic.config.variable.ApplicationConstant;

@Repository
public class ParentDaoImpl implements ParentDao{

	private static final Logger LOG = LogManager.getLogger(ParentDaoImpl.class);

	public static final String ADD_PARENT_USER = "INSERT INTO TBL_PARENT "
			+ " (ID, USERNAME, PASSWORD, FULLNAME, PHONE_NO, ADDRESS, EMAIL, CREATED_DTM, CREATED_BY) "
			+ " VALUES (?,?,?,?,?,?,?,?,?) ";
	
	public static final String GET_PARENT_USER_BY_USERNAME = "SELECT ID, USERNAME, PASSWORD, FULLNAME, PHONE_NO, ADDRESS, EMAIL, LAST_ACTIVITY "
			+ " FROM TBL_PARENT "
			+ " WHERE USERNAME = ? ";
	
	public static final String GET_PARENT_USER_BY_ID = "SELECT ID, USERNAME, PASSWORD, FULLNAME, PHONE_NO, ADDRESS, EMAIL, LAST_ACTIVITY "
			+ " FROM TBL_PARENT "
			+ " WHERE ID = ? ";
	
	public static final String UPDATE_USER_ACTIVITY = "UPDATE TBL_PARENT "
			+ " SET LAST_ACTIVITY = ?, "
			+ " LASTUPD_DTM = ? , "
			+ " LASTUPD_BY = ? "
			+ " WHERE USERNAME = ?";

	public static final String GET_PARENT_USER_BY_FULLNAME = "SELECT ID, USERNAME, PASSWORD, FULLNAME, PHONE_NO, ADDRESS, EMAIL, LAST_ACTIVITY "
			+ " FROM TBL_PARENT "
			+ " WHERE FULLNAME = ? ";
	
	@Autowired
	@Qualifier(ApplicationConstant.BEAN_JDBC_CLINIC)
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean saveParentUser(Parent parentUser) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_PARENT_USER);
		int result = 0;
		try{
			result = jdbcTemplate.update(ADD_PARENT_USER,
					new Object[] {parentUser.getId(), parentUser.getUsername(), parentUser.getPassword(), parentUser.getFullname(),
							parentUser.getPhoneNo(), parentUser.getAddress(), parentUser.getEmail(), "SYSTEM", new Timestamp(new Date().getTime())});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public Parent getParentUser(String username) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_PARENT_USER_BY_USERNAME);
		List<Parent> result = new ArrayList <Parent>();
		try{
			result = jdbcTemplate.query(GET_PARENT_USER_BY_USERNAME, 
					new Object[] {username}, new ParentUserMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() > 0 ? result.get(0) : null;
	}
	
	@Override
	public Parent getParentUser(int id) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_PARENT_USER_BY_ID);
		List<Parent> result = new ArrayList <Parent>();
		try{
			result = jdbcTemplate.query(GET_PARENT_USER_BY_ID, 
					new Object[] {id}, new ParentUserMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() > 0 ? result.get(0) : null;
	}

	private static class ParentUserMapper implements RowMapper<Parent> {
		@Override
		public Parent mapRow(ResultSet rs, int row) throws SQLException {
			Parent result = new Parent();
			result.setId(rs.getInt("ID"));
			result.setUsername(rs.getString("USERNAME"));
			result.setPassword(rs.getString("PASSWORD"));
			result.setFullname(rs.getString("FULLNAME"));
			result.setAddress(rs.getString("ADDRESS"));
			result.setPhoneNo(rs.getString("PHONE_NO"));
			result.setEmail(rs.getString("EMAIL"));
			result.setLastActivity(rs.getTimestamp("LAST_ACTIVITY"));		
			return result;
		}
	}

	@Override
	public boolean updateLastActivity(String username) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", UPDATE_USER_ACTIVITY);
		int result = 0;
		try{ 
			result = jdbcTemplate.update(UPDATE_USER_ACTIVITY, 
					new Object[] {new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), username, username});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public Parent getParentUserByName(String name) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_PARENT_USER_BY_FULLNAME);
		List<Parent> result = new ArrayList <Parent>();
		try{
			result = jdbcTemplate.query(GET_PARENT_USER_BY_FULLNAME, 
					new Object[] {name}, new ParentUserMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() > 0 ? result.get(0) : null;
	}
	
	
}
