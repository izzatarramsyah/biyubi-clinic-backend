package com.clinic.datamapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.clinic.entity.CheckHealth;

public class CheckHealthMapper implements RowMapper<CheckHealth> {
	@Override
	public CheckHealth mapRow(ResultSet rs, int row) throws SQLException {
		CheckHealth result = new CheckHealth();
		result.setId(rs.getInt("ID"));
		result.setUserId(rs.getInt("USER_ID"));
		result.setWeight(rs.getInt("WEIGHT"));
		result.setHeight(rs.getInt("HEIGHT"));
		result.setHeadCircumference(rs.getInt("HEAD_CIRCUMFERENCE"));
		result.setNotes(rs.getString("NOTES"));
		result.setCreatedDtm(rs.getTimestamp("CREATED_DTM"));
		result.setCreatedBy(rs.getString("CREATED_BY"));
		result.setUpdatedDtm(rs.getTimestamp("LASTUPD_DTM"));
		result.setUpdatedBy(rs.getString("LASTUPD_BY"));
		return result;
	}
}
