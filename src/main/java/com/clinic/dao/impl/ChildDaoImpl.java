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
import com.clinic.dao.ChildDao;
import com.clinic.entity.Child;
import com.clinic.entity.HealthRecord;
import com.clinic.entity.VaccineRecord;

@Repository
public class ChildDaoImpl implements ChildDao{
	
	private static final Logger LOG = LogManager.getLogger(ChildDaoImpl.class);

	public static final String GET_CHILD_BY_PARENT_ID = "SELECT ID, PARENT_ID, FULLNAME, BIRTH_DATE, GENDER, NOTES "
			+ " FROM TBL_CHILD "
			+ " WHERE PARENT_ID = ? ";
	
	@Autowired
	@Qualifier(ApplicationConstant.BEAN_JDBC_CLINIC)
	private JdbcTemplate jdbcTemplate; 

	@Override
	public Child getChildByParentID(int parentId) throws Exception {
		LOG.traceEntry();
		LOG.debug("SQL::{}||{}", GET_CHILD_BY_PARENT_ID, parentId);
		List<Child> result = new ArrayList <Child>();
		try{
			result = jdbcTemplate.query(GET_CHILD_BY_PARENT_ID, 
					new Object[] {parentId}, new ChildMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() > 0 ? result.get(0) : null;
	}
	
	private static class ChildMapper implements RowMapper<Child> {
		@Override
		public Child mapRow(ResultSet rs, int row) throws SQLException {
			Child result = new Child();
			result.setId(rs.getInt("ID"));
			result.setParentId(rs.getInt("PARENT_ID"));
			result.setFullname(rs.getString("FULLNAME"));
			result.setBirthDate(rs.getDate("BIRTH_DATE"));
			result.setGender(rs.getString("GENDER"));
			result.setNotes(rs.getString("NOTES"));
			return result;
		}
	}

}
