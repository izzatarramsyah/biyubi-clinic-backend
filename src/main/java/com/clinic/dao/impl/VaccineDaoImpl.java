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
import com.clinic.datamapper.VaccineMapper;
import com.clinic.entity.Vaccine;

@Repository
public class VaccineDaoImpl implements VaccineDao{

	private static final Logger LOG = LogManager.getLogger(VaccineDaoImpl.class);

	public static final String GET_LIST_VACCINE = "SELECT ID, USER_ID, VACCINE_NAME, VACCINE_TYPE, VACCINE_DOSE, DESCRIPTION, "
			+ "VACCINE_DATE, CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ "ROM TBL_VACCINE_RECORD "
			+ "WHERE ";
	
	public static final String ADD_MEDICINE_RECORD = "INSERT INTO TBL_VACCINE_RECORD "
			+ " ( USER_ID, VACCINE_NAME, VACCINE_TYPE, VACCINE_DOSE, DESCRIPTION, VACCINE_DATE, CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY ) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?) ";
	
	
	@Autowired
	@Qualifier(ApplicationConstant.BEAN_JDBC_CLINIC)
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Vaccine> getListVaccine(int userId) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_LIST_VACCINE);
		List < Vaccine > result = new ArrayList <Vaccine>();
		try{
			result = jdbcTemplate.query(GET_LIST_VACCINE, new Object[] { userId }, new VaccineMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result;
	}

	@Override
	public boolean addVaccineRecord(Vaccine vaccine) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_MEDICINE_RECORD);
		int result = 0;
		try{
			result = jdbcTemplate.update(ADD_MEDICINE_RECORD,
					new Object[] { vaccine.getUserId(), vaccine.getVaccineName(), vaccine.getVaccineType(),
							vaccine.getVaccineDose(), vaccine.getDescription(), vaccine.getVaccineDate(),
							vaccine.getCreatedDtm(), vaccine.getCreatedBy(), vaccine.getUpdatedDtm(), vaccine.getUpdatedBy()});
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true; 
	}

	
}
