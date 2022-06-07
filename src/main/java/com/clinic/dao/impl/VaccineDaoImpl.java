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
import com.clinic.dao.VaccineDao;
import com.clinic.datamapper.VaccineMasterMapper;
import com.clinic.datamapper.VaccineRecordMapper;
import com.clinic.entity.VaccineMaster;
import com.clinic.entity.VaccineRecord;

@Repository
public class VaccineDaoImpl implements VaccineDao{ 

	private static final Logger LOG = LogManager.getLogger(VaccineDaoImpl.class);

	public static final String GET_LIST_VACCINE = "SELECT ID, USER_ID, CHILD_ID, VACCINE_CODE, BATCH, VACCINE_DATE, EXPIRED_DATE, "
			+ "NEXT_VACCINE_DATE, NOTES, CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ "FROM TBL_VACCINE_RECORD "
			+ "WHERE USER_ID = ? ";
	
	public static final String ADD_MEDICINE_RECORD = "INSERT INTO TBL_VACCINE_RECORD "
			+ " ( USER_ID, CHILD_ID, VACCINE_CODE, BATCH, VACCINE_DATE, EXPIRED_DATE,"
			+ " NEXT_VACCINE_DATE, NOTES, CREATED_DTM, CREATED_BY ) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?) ";
	
	public static final String GET_MST_VACCINE = "SELECT CODE, NAME, TYPE, EXPIRED_DAYS, NEXT_VACCINE_DAYS, NOTES, CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ "FROM TBL_VACCINE_MASTER "
			+ "WHERE CODE = ? ";
	
	public static final String ADD_MST_VACCINE = "INSERT INTO TBL_VACCINE_MASTER "
			+ "( CODE, NAME, TYPE, EXPIRED_DAYS, NEXT_VACCINE_DAYS, NOTES, CREATED_DTM, CREATED_BY ) "
			+ "VALUES (?,?,?,?,?,?,?,?) ";
	
	public static final String COUNT_MST_VACCINE = "SELECT COUNT(*) FROM TBL_VACCINE_MASTER ";
	
	@Autowired
	@Qualifier(ApplicationConstant.BEAN_JDBC_CLINIC)
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List < VaccineRecord > getListVaccine ( int userId ) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_LIST_VACCINE);
		List < VaccineRecord > result = new ArrayList < VaccineRecord >();
		try{
			result = jdbcTemplate.query(GET_LIST_VACCINE, new Object[] { userId }, new VaccineRecordMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}

	@Override
	public boolean addVaccineRecord ( VaccineRecord vaccine ) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_MEDICINE_RECORD);
		int result = 0;
		try{			
			result = jdbcTemplate.update(ADD_MEDICINE_RECORD,
					new Object[] { vaccine.getUserId(), vaccine.getChildId(), vaccine.getVaccineCode(),
							vaccine.getBatch(), vaccine.getVaccineDate(), vaccine.getExpiredDate(),
							vaccine.getNextVaccineDate(), vaccine.getNotes(), 
							vaccine.getCreatedDtm(), vaccine.getCreatedBy() });
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true; 
	}

	@Override
	public VaccineMaster getMstVaccineByCode (String code) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_MST_VACCINE);
		List < VaccineMaster > result = new ArrayList <VaccineMaster>();
		try{
			result = jdbcTemplate.query(GET_MST_VACCINE, new Object[] { code }, new VaccineMasterMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() == 0 ? null : result.get(0);
	}

	@Override
	public boolean addVaccineMaster(VaccineMaster vaccineMaster) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_MST_VACCINE);
		int result = 0;
		try{			
			result = jdbcTemplate.update(ADD_MST_VACCINE,
					new Object[] { vaccineMaster.getCode(), vaccineMaster.getName(), vaccineMaster.getType(),
							vaccineMaster.getExpiredDays(), vaccineMaster.getNextVaccineDays(), vaccineMaster.getNotes(),
							vaccineMaster.getCreatedDtm(), vaccineMaster.getCreatedBy() });
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true; 
	}

	@Override
	public int countVaccineMaster() throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", COUNT_MST_VACCINE);
		Integer result = 0;
		try{
			result = jdbcTemplate.queryForObject(COUNT_MST_VACCINE, new Object[] {}, Integer.class);
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}

	
}
