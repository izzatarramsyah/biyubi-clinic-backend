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
import com.clinic.dao.MstDao;
import com.clinic.entity.MstMedicine;
import com.clinic.entity.MstTreatment;
import com.clinic.entity.MstType;
import com.clinic.entity.MstVaccine;
import com.clinic.util.Util;

@Repository 
public class MstDaoImpl implements MstDao{
	
	private static final Logger LOG = LogManager.getLogger(AdminDaoImpl.class);
	
	public static final String GET_ALL_LIST_VACCINE = "SELECT ID, VACCINE_NAME, VACCINE_TYPE, VACCINE_DOSE, DESCRIPTION, TTL_UNIT, REMAINING_UNIT, EXPIRED_DATE F"
			+ "ROM TBL_MST_VACCINE";
	
	public static final String GET_ALL_LIST_MEDICINE = "SELECT ID, MEDICINE_NAME, MEDICINE_TYPE, DESCRIPTION, TTL_UNIT, REMAINING_UNIT, EXPIRED_DATE "
			+ "FROM TBL_MST_MEDICINE";

	public static final String GET_ALL_LIST_TREATMENTS = "SELECT ID, TREAT_TYPE, TREAT_NAME, CATEGORY F"
			+ "ROM TBL_MST_TREATMENT";

	public static final String GET_ALL_LIST_VACCINE_BY_ID = "SELECT ID, VACCINE_NAME, VACCINE_TYPE, VACCINE_DOSE, DESCRIPTION, TTL_UNIT, REMAINING_UNIT, EXPIRED_DATE "
			+ "FROM TBL_MST_VACCINE WHERE ID = ? ";
	
	public static final String GET_ALL_LIST_MEDICINE_BY_ID = "SELECT ID, MEDICINE_NAME, MEDICINE_TYPE, DESCRIPTION, TTL_UNIT, REMAINING_UNIT, EXPIRED_DATE "
			+ "FROM TBL_MST_MEDICINE WHERE ID = ? ";

	public static final String GET_ALL_LIST_TREATMENTS_BY_ID = "SELECT ID, TREAT_TYPE, TREAT_NAME, CATEGORY "
			+ "FROM TBL_MST_TREATMENT WHERE ID = ? ";
	
	public static final String UPDATE_VACCINE = "UPDATE TBL_MST_VACCINE SET VACCINE_NAME = ?, VACCINE_TYPE = ?, VACCINE_DOSE = ?, "
												+ "DESCRIPTION = ?, TTL_UNIT = ?, REMAINING_UNIT = ?, EXPIRED_DATE = ?, LASTUPD_DTM = ?, LASTUPD_BY = ? WHERE ID = ?";
	
	public static final String UPDATE_MEDICINE = "UPDATE TBL_MST_MEDICINE SET MEDICINE_NAME = ?, MEDICINE_TYPE = ?, DESCRIPTION = ?, "
												+ "TTL_UNIT = ?, REMAINING_UNIT = ?, EXPIRED_DATE = ?, LASTUPD_DTM = ?, LASTUPD_BY = ?  WHERE ID = ?";

	public static final String UPDATE_TREATMENTS = "UPDATE TBL_MST_TREATMENT SET TREAT_TYPE = ?, TREAT_NAME = ?, CATEGORY = ?, LASTUPD_DTM = ?, LASTUPD_BY = ? WHERE ID = ?";

	public static final String ADD_MST_VACCINE = "INSERT INTO TBL_MST_VACCINE "
			+ " (VACCINE_NAME, VACCINE_TYPE, VACCINE_DOSE, DESCRIPTION, TTL_UNIT, REMAINING_UNIT, EXPIRED_DATE, CREATED_DTM, CREATED_BY) "
			+ " VALUES (?,?,?,?,?,?,?,?,?) ";
	
	public static final String ADD_MST_MEDICINE = "INSERT INTO TBL_MST_MEDICINE "
			+ " (MEDICINE_NAME, MEDICINE_TYPE, DESCRIPTION, TTL_UNIT, REMAINING_UNIT, EXPIRED_DATE, CREATED_DTM, CREATED_BY) "
			+ " VALUES (?,?,?,?,?,?,?,?) ";
	
	public static final String ADD_MST_TREATMENTS = "INSERT INTO TBL_MST_TREATMENT "
			+ " (TREAT_TYPE, TREAT_NAME, CATEGORY, CREATED_DTM, CREATED_BY) "
			+ " VALUES (?,?,?,?,?) ";

	public static final String DELETE_MST_MEDICINE = "DELETE FROM TBL_MST_MEDICINE WHERE ID = ? "; 
	
	public static final String DELETE_MST_VACCINE = "DELETE FROM TBL_MST_VACCINE WHERE ID = ? "; 

	public static final String DELETE_MST_TREATMENTS = "DELETE FROM TBL_MST_TREATMENT WHERE ID = ? "; 

	public static final String GET_MST_TYPE = "SELECT ID, MST_TBL, MST_TYPE_NAME, STATUS FROM TBL_MST_TYPE WHERE MST_TBL = ? "; 
	
	public static final String ADD_MST_TYPE = "INSERT INTO TBL_MST_TYPE "
			+ " (MST_TBL, MST_TYPE_NAME, STATUS, CREATED_DTM, CREATED_BY) "
			+ " VALUES (?,?,?,?,?) ";

	
	@Autowired
	@Qualifier(ApplicationConstant.BEAN_JDBC_CLINIC)
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<MstVaccine> getMstVaccine() throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_ALL_LIST_VACCINE);
		List<MstVaccine> result = new ArrayList <MstVaccine>();
		try{
			result = jdbcTemplate.query(GET_ALL_LIST_VACCINE, 
					new Object[] {}, new MstVaccineMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}
	
	@Override
	public MstVaccine getMstVaccineById(int id) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_ALL_LIST_VACCINE_BY_ID);
		List<MstVaccine> result = new ArrayList <MstVaccine>();
		try{
			result = jdbcTemplate.query(GET_ALL_LIST_VACCINE_BY_ID, 
					new Object[] {id}, new MstVaccineMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() == 0 ? null : result.get(0);
	}
	
	private static class MstVaccineMapper implements RowMapper<MstVaccine> {
		@Override
		public MstVaccine mapRow(ResultSet rs, int row) throws SQLException {
			MstVaccine result = new MstVaccine();
			result.setId(rs.getInt("ID"));
			result.setVaccineName(rs.getString("VACCINE_NAME"));	
			result.setVaccineType(rs.getString("VACCINE_TYPE"));	
			result.setVaccineDose(rs.getString("VACCINE_DOSE"));	
			result.setDescription(rs.getString("DESCRIPTION"));
			result.setTtlUnit(rs.getInt("TTL_UNIT"));
			result.setRemainingUnit(rs.getInt("REMAINING_UNIT"));
			result.setExpiredDate(Util.formatDate(rs.getDate("EXPIRED_DATE")));
			return result;
		}
	}

	@Override
	public List<MstMedicine> getMstMedicines() throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_ALL_LIST_MEDICINE);
		List<MstMedicine> result = new ArrayList <MstMedicine>();
		try{
			result = jdbcTemplate.query(GET_ALL_LIST_MEDICINE, 
					new Object[] {}, new MstMedicinesMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}
	
	@Override
	public MstMedicine getMstMedicinesById(int id) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_ALL_LIST_MEDICINE_BY_ID);
		List<MstMedicine> result = new ArrayList <MstMedicine>();
		try{
			result = jdbcTemplate.query(GET_ALL_LIST_MEDICINE_BY_ID, 
					new Object[] {id}, new MstMedicinesMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() == 0 ? null : result.get(0);
	}
	
	private static class MstMedicinesMapper implements RowMapper<MstMedicine> {
		@Override
		public MstMedicine mapRow(ResultSet rs, int row) throws SQLException {
			MstMedicine result = new MstMedicine();
			result.setId(rs.getInt("ID"));
			result.setMedicineName(rs.getString("MEDICINE_NAME"));	
			result.setMedicineType(rs.getString("MEDICINE_TYPE"));	
			result.setDescription(rs.getString("DESCRIPTION"));
			result.setTtlUnit(rs.getInt("TTL_UNIT"));
			result.setRemainingUnit(rs.getInt("REMAINING_UNIT"));
			result.setExpiredDate(Util.formatDate(rs.getDate("EXPIRED_DATE")));
			return result;
		}
	}

	@Override
	public List<MstTreatment> getMstTreatments() throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_ALL_LIST_MEDICINE);
		List<MstTreatment> result = new ArrayList <MstTreatment>();
		try{
			result = jdbcTemplate.query(GET_ALL_LIST_TREATMENTS, 
					new Object[] {}, new MstTreatmentsMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	} 

	@Override
	public MstTreatment getMstTreatmentsById(int id) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_ALL_LIST_MEDICINE_BY_ID);
		List<MstTreatment> result = new ArrayList <MstTreatment>();
		try{
			result = jdbcTemplate.query(GET_ALL_LIST_TREATMENTS_BY_ID, 
					new Object[] {id}, new MstTreatmentsMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() == 0 ? null : result.get(0);
	} 
	
	private static class MstTreatmentsMapper implements RowMapper<MstTreatment> {
		@Override
		public MstTreatment mapRow(ResultSet rs, int row) throws SQLException {
			MstTreatment result = new MstTreatment();
			result.setId(rs.getInt("ID"));
			result.setTreatType(rs.getString("TREAT_TYPE"));
			result.setTreatName(rs.getString("TREAT_NAME"));
			result.setCategory(rs.getString("CATEGORY"));
			return result;
		}
	}

	@Override
	public boolean editMstVaccine(MstVaccine mstVaccine) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", UPDATE_VACCINE);
		int result = 0;
		try{ 
			result = jdbcTemplate.update(UPDATE_VACCINE, 
					new Object[] {mstVaccine.getVaccineName(), mstVaccine.getVaccineType(), mstVaccine.getVaccineDose(), mstVaccine.getDescription(),
							mstVaccine.getTtlUnit(), mstVaccine.getRemainingUnit(), Util.formatDate(mstVaccine.getExpiredDate()), new Timestamp(new Date().getTime()), "SYSTEM",
							mstVaccine.getId()});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public boolean editMstMedicines(MstMedicine mstMedicines) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", UPDATE_MEDICINE);
		int result = 0;
		try{ 
			result = jdbcTemplate.update(UPDATE_MEDICINE, 
					new Object[] {mstMedicines.getMedicineName(), mstMedicines.getMedicineType(), mstMedicines.getDescription(),
							mstMedicines.getTtlUnit(), mstMedicines.getRemainingUnit(), Util.formatDate(mstMedicines.getExpiredDate()),
							new Timestamp(new Date().getTime()), "SYSTEM",
							mstMedicines.getId()});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public boolean editMstTreatments(MstTreatment mstTreatments) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", UPDATE_TREATMENTS);
		int result = 0;
		try{ 
			result = jdbcTemplate.update(UPDATE_TREATMENTS, 
					new Object[] {mstTreatments.getTreatType(), mstTreatments.getTreatName(), mstTreatments.getCategory(),
							new Timestamp(new Date().getTime()), "SYSTEM",
							mstTreatments.getId()});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}
	
	@Override
	public boolean saveMstVaccine(MstVaccine param) {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_MST_VACCINE);
		int result = 0;
		try{
			result = jdbcTemplate.update(ADD_MST_VACCINE,
					new Object[] {param.getVaccineName(), param.getVaccineType(), param.getVaccineDose(),
							param.getDescription(), param.getTtlUnit(), param.getRemainingUnit(), Util.formatDate(param.getExpiredDate()),
							new Timestamp(new Date().getTime()),  "SYSTEM"});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true; 
	}

	@Override
	public boolean saveMstMedicines(MstMedicine param) {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_MST_MEDICINE);
		int result = 0;
		try{
			result = jdbcTemplate.update(ADD_MST_MEDICINE,
					new Object[] {param.getMedicineName(), param.getMedicineType(), param.getDescription(),
							param.getTtlUnit(), param.getRemainingUnit(), Util.formatDate(param.getExpiredDate()),
							new Timestamp(new Date().getTime()), "SYSTEM"});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true; 
	}

	@Override
	public boolean saveMstTreatments(MstTreatment param) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_MST_TREATMENTS);
		int result = 0;
		try{
			result = jdbcTemplate.update(ADD_MST_TREATMENTS,
					new Object[] {param.getTreatType(), param.getTreatName(), param.getCategory(),
							new Timestamp(new Date().getTime()), "SYSTEM"});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true; 
	}

	@Override
	public boolean deleteMstMedicines(int id) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", DELETE_MST_MEDICINE);
		int result = 0;
		try{
			result = jdbcTemplate.update(DELETE_MST_MEDICINE,
					new Object[] {id});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true; 
	}

	@Override
	public boolean deleteMstVaccine(int id) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}",  DELETE_MST_VACCINE);
		int result = 0;
		try{
			result = jdbcTemplate.update(DELETE_MST_VACCINE,
					new Object[] {id});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public boolean deleteMstTreatments(int id) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", DELETE_MST_TREATMENTS);
		int result = 0;
		try{
			result = jdbcTemplate.update(DELETE_MST_TREATMENTS,
					new Object[] {id});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}

	@Override
	public List<MstType> getMstType(String mstTable) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_MST_TYPE);
		List<MstType> result = new ArrayList <MstType>();
		try{
			result = jdbcTemplate.query(GET_MST_TYPE, 
					new Object[] {mstTable}, new MstTypeMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}
	
	private static class MstTypeMapper implements RowMapper<MstType> {
		@Override
		public MstType mapRow(ResultSet rs, int row) throws SQLException {
			MstType result = new MstType();
			result.setId(rs.getInt("ID"));
			result.setMst_tbl(rs.getString("MST_TBL"));
			result.setMst_type_name(rs.getString("MST_TYPE_NAME"));
			result.setStatus(rs.getString("STATUS"));
			return result;
		}
	}

	@Override
	public boolean addMstType(MstType mstType) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_MST_TYPE);
		int result = 0;
		try{
			result = jdbcTemplate.update(ADD_MST_TYPE,
					new Object[] {mstType.getMst_tbl(), mstType.getMst_type_name(), mstType.getStatus(),
							new Timestamp(new Date().getTime()), "SYSTEM"});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true; 
	}
	
}
