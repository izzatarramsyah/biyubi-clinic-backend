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

import com.clinic.config.variable.ApplicationConstant;
import com.clinic.dao.AdminDao;
import com.clinic.entity.Admin;
import com.clinic.entity.CheckRecord;
import com.clinic.entity.Child;
import com.clinic.entity.HealthRecord;
import com.clinic.entity.VaccineRecord;
import com.clinic.entity.MstMedicine;
import com.clinic.entity.MstTreatment;
import com.clinic.entity.MstVaccine;

@Repository
public class AdminDaoImpl implements AdminDao{

	private static final Logger LOG = LogManager.getLogger(AdminDaoImpl.class);
	
	public static final String GET_ADMIN_USER = "SELECT ID, USERNAME, PASSWORD, STATUS, SESSIONID, LAST_ACTIVITY "
			+ " FROM TBL_ADMIN "
			+ " WHERE USERNAME = ? AND PASSWORD = ? ";
	
	public static final String ADD_CHILD = "INSERT INTO TBL_CHILD "
			+ " (PARENT_ID, FULLNAME, BIRTHDATE, GENDER, NOTES, CREATED_DTM, CREATED_BY) "
			+ " VALUES (?,?,?,?,?,?,?,?) ";
		
	public static final String UPDATE_USER_ACTIVITY = "UPDATE TBL_ADMIN "
			+ " SET LAST_ACTIVITY = ?, "
			+ " LASTUPD_DTM = ? , "
			+ " LASTUPD_BY = ? "
			+ " WHERE USERNAME = ?";

	public static final String UPDATE_USER_SESSIONID = "UPDATE TBL_ADMIN "
			+ " SET SESSIONID = ? "
			+ " WHERE USERNAME = ?";

	public static final String ADD_HEALTH_CHECK_RECORD = "INSERT INTO TBL_HEALTH_RECORD "
			+ " (PARENT_ID, CHILD_ID, COMPLAINS, FINDINGS, TREAT_ID, MEDICINE_ID, CREATED_DTM, CREATED_BY) "
			+ " VALUES (?,?,?,?,?,?,?,?) ";
	
	public static final String ADD_VACCINE_RECORD = "INSERT INTO TBL_VACCINE_RECORD "
			+ " (PARENT_ID, CHILD_ID, VACCINE_ID, NOTES, CREATED_DTM, CREATED_BY) "
			+ " VALUES (?,?,?,?,?,?) ";
	
	public static final String GET_HEALTH_CHECK_RECORD = "SELECT ID, PARENT_ID, CHILD_ID, COMPLAINS, FINDINGS, TREAT_ID, MEDICINE_ID, CREATED_DTM, CREATED_BY "
			+ " FROM TBL_HEALTH_RECORD "
			+ " WHERE PARENT_ID = ? ";
	
	public static final String GET_HEALTH_CHECK_RECORD_BY_DATE = "SELECT ID, PARENT_ID, CHILD_ID, COMPLAINS, FINDINGS, TREAT_ID, MEDICINE_ID, CREATED_DTM, CREATED_BY "
			+ " FROM TBL_HEALTH_RECORD "
			+ " WHERE PARENT_ID = ? "
			+ " AND CREATED_DTM >= to_date(?,'yyyy-MM-dd')"
			+ " AND CREATED_DTM <= to_date(?,'yyyy-MM-dd')";
	
	public static final String GET_VACCINE_RECORD = "SELECT ID, PARENT_ID, CHILD_ID, VACCINE_ID, NOTES, CREATED_DTM, CREATED_BY "
			+ "FROM TBL_VACCINE_RECORD "
			+ "WHERE PARENT_ID = ? ";
	
	public static final String GET_VACCINE_RECORD_BY_DATE = "SELECT ID, PARENT_ID, CHILD_ID, VACCINE_ID, NOTES, CREATED_DTM, CREATED_BY "
			+ "FROM TBL_VACCINE_RECORD "
			+ "WHERE PARENT_ID = ? "
			+ " AND CREATED_DTM >= to_date(?,'yyyy-MM-dd')"
			+ " AND CREATED_DTM <= to_date(?,'yyyy-MM-dd')";
	
	public static final String ADD_CHECK_RECORD = "INSERT INTO TBL_CHECK_RECORD "
			+ " (PARENT_ID, CHILD_ID, WEIGHT, HEIGHT, HEAD_CIRCUMFERENCE, NOTES, CREATED_DTM, CREATED_BY) "
			+ " VALUES (?,?,?,?,?,?,?,?) ";
	
	public static final String GET_CHECK_RECORD = "SELECT ID, PARENT_ID, CHILD_ID, WEIGHT, HEIGHT, HEAD_CIRCUMFERENCE, NOTES, CREATED_DTM, CREATED_BY "
			+ "FROM TBL_CHECK_RECORD "
			+ "WHERE PARENT_ID = ? "
			+ "AND CHILD_ID = ? ";
	
	public static final String GET_CHECK_RECORD_BY_DATE = "SELECT ID, PARENT_ID, CHILD_ID, WEIGHT, HEIGHT, HEAD_CIRCUMFERENCE, NOTES, CREATED_DTM, CREATED_BY "
			+ " FROM TBL_CHECK_RECORD "
			+ " WHERE PARENT_ID = ? "
			+ " AND CREATED_DTM >= to_date(?,'yyyy-MM-dd')"
			+ " AND CREATED_DTM <= to_date(?,'yyyy-MM-dd')";
	
	@Autowired
	@Qualifier(ApplicationConstant.BEAN_JDBC_CLINIC)
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Admin getAdminUser(String username, String password) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_ADMIN_USER);
		List<Admin> result = new ArrayList <Admin>();
		try{
			result = jdbcTemplate.query(GET_ADMIN_USER, 
					new Object[] {username, password}, new AdminUserMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() > 0 ? result.get(0) : null;
	}
	
	private static class AdminUserMapper implements RowMapper<Admin> {
		@Override
		public Admin mapRow(ResultSet rs, int row) throws SQLException {
			Admin result = new Admin();
			result.setId(rs.getInt("ID"));
			result.setUsername(rs.getString("USERNAME"));
			result.setPassword(rs.getString("PASSWORD"));
			result.setStatus(rs.getString("STATUS"));
			result.setLastActivity(rs.getTimestamp("LAST_ACTIVITY"));		
			result.setSessionId(rs.getString("SESSIONID"));
			return result;
		}
	}

	@Override
	public boolean saveChild(Child child) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_CHILD);
		int result = 0;
		try{
			result = jdbcTemplate.update(ADD_CHILD,
					new Object[] {child.getParentId(), child.getFullname(), child.getBirthDate(), child.getGender(),
							child.getNotes(), "SYSTEM", new Timestamp(new Date().getTime())});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true; 
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
	public boolean updateSessionId(String sessionId, String username) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", UPDATE_USER_SESSIONID, sessionId, username);
		int result = 0;
		try{ 
			result = jdbcTemplate.update(UPDATE_USER_SESSIONID, 
					new Object[] {sessionId, username});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public boolean saveHealthRecord(HealthRecord param) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_HEALTH_CHECK_RECORD);
		int result = 0;
		try{ 
			result = jdbcTemplate.update(ADD_HEALTH_CHECK_RECORD, 
					new Object[] {param.getParentId(), param.getChildId(), param.getComplains(), param.getFindings(),
							param.getTreatId(), param.getMedicineId(), new Timestamp(new Date().getTime()), "SYSTEM"});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public boolean saveVaccineRecord(VaccineRecord param) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_VACCINE_RECORD);
		int result = 0;
		try{ 
			result = jdbcTemplate.update(ADD_VACCINE_RECORD, 
					new Object[] {param.getParentId(), param.getChildId(), param.getVaccineId(), param.getNotes(),
							new Timestamp(new Date().getTime()), "SYSTEM"});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public List<HealthRecord> getHealthRecord(HealthRecord param) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_HEALTH_CHECK_RECORD);
		List<HealthRecord> result = new ArrayList <HealthRecord>();
		try{
			result = jdbcTemplate.query(GET_HEALTH_CHECK_RECORD, 
					new Object[] {param.getParentId()}, new ChildCheckHealthMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}
	
	private static class ChildCheckHealthMapper implements RowMapper<HealthRecord> {
		@Override
		public HealthRecord mapRow(ResultSet rs, int row) throws SQLException {
			HealthRecord result = new HealthRecord();
			result.setId(rs.getInt("ID"));
			result.setParentId(rs.getInt("PARENT_ID"));
			result.setChildId(rs.getInt("CHILD_ID"));
			result.setComplains(rs.getString("COMPLAINS"));
			result.setFindings(rs.getString("FINDINGS"));
			result.setTreatId(rs.getInt("TREAT_ID"));
			result.setMedicineId(rs.getInt("MEDICINE_ID"));
			result.setCreatedDtm(rs.getDate("CREATED_DTM"));
			result.setCreatedBy(rs.getString("CREATED_BY"));
			return result;
		}
	}
	
	@Override
	public List<VaccineRecord> getVaccineRecord(VaccineRecord param) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_VACCINE_RECORD);
		List<VaccineRecord> result = new ArrayList <VaccineRecord>();
		try{
			result = jdbcTemplate.query(GET_VACCINE_RECORD, 
					new Object[] {param.getParentId()}, new ChildVaccineMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}
	
	private static class ChildVaccineMapper implements RowMapper<VaccineRecord> {
		@Override
		public VaccineRecord mapRow(ResultSet rs, int row) throws SQLException {
			VaccineRecord result = new VaccineRecord();
			result.setId(rs.getInt("ID"));
			result.setParentId(rs.getInt("PARENT_ID"));
			result.setChildId(rs.getInt("CHILD_ID"));
			result.setVaccineId(rs.getInt("VACCINE_ID"));
			result.setNotes(rs.getString("NOTES"));
			result.setCreatedDtm(rs.getDate("CREATED_DTM"));
			result.setCreatedBy(rs.getString("CREATED_BY"));
			return result;
		}
	}

	@Override
	public List<HealthRecord> getHealthRecord(HealthRecord param, String startDate, String endDate)
			throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_HEALTH_CHECK_RECORD_BY_DATE);
		List<HealthRecord> result = new ArrayList <HealthRecord>();
		try{
			result = jdbcTemplate.query(GET_HEALTH_CHECK_RECORD_BY_DATE, 
					new Object[] {param.getParentId(), startDate, endDate}, new ChildCheckHealthMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}

	@Override
	public List<VaccineRecord> getVaccineRecord(VaccineRecord param, String startDate, String endDate)
			throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_VACCINE_RECORD_BY_DATE);
		List<VaccineRecord> result = new ArrayList <VaccineRecord>();
		try{
			result = jdbcTemplate.query(GET_VACCINE_RECORD_BY_DATE, 
					new Object[] {param.getParentId(), startDate, endDate}, new ChildVaccineMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}

	@Override
	public boolean saveCheckRecord(CheckRecord param) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_CHECK_RECORD);
		int result = 0;
		try{ 
			result = jdbcTemplate.update(ADD_CHECK_RECORD, 
					new Object[] {param.getParentId(), param.getChildId(), param.getWeight(), 
							param.getHeight(), param.getHeadCircumference(), param.getNotes(),
							new Timestamp(new Date().getTime()), "SYSTEM"});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public List<CheckRecord> getCheckRecord(CheckRecord param) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_CHECK_RECORD);
		List<CheckRecord> result = new ArrayList <CheckRecord>();
		try{
			result = jdbcTemplate.query(GET_CHECK_RECORD, 
					new Object[] {param.getParentId(), param.getChildId()}, new CheckRecordMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}
	
	@Override
	public List<CheckRecord> getCheckRecord(CheckRecord param, String startDate, String endDate) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_CHECK_RECORD_BY_DATE);
		List<CheckRecord> result = new ArrayList <CheckRecord>();
		try{
			result = jdbcTemplate.query(GET_CHECK_RECORD_BY_DATE, 
					new Object[] {param.getParentId(), param.getChildId(), startDate, endDate}, new CheckRecordMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}
	
	private static class CheckRecordMapper implements RowMapper<CheckRecord> {
		@Override
		public CheckRecord mapRow(ResultSet rs, int row) throws SQLException {
			CheckRecord result = new CheckRecord();
			result.setId(rs.getInt("ID"));
			result.setParentId(rs.getInt("PARENT_ID"));
			result.setChildId(rs.getInt("CHILD_ID"));
			result.setWeight(rs.getInt("WEIGHT"));
			result.setHeight(rs.getInt("HEIGHT"));
			result.setHeadCircumference(rs.getInt("HEAD_CIRCUMFERENCE"));
			result.setNotes(rs.getString("NOTES"));
			result.setCreatedDtm(rs.getDate("CREATED_DTM"));
			result.setCreatedBy(rs.getString("CREATED_BY"));
			return result;
		}
	}
	
}
