package com.clinic.datamapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.clinic.entity.VaccineMaster;

public class VaccineMasterMapper implements RowMapper<VaccineMaster> {
	@Override
	public VaccineMaster mapRow(ResultSet rs, int row) throws SQLException {
		VaccineMaster result = new VaccineMaster();
		result.setCode(rs.getString("CODE"));
		result.setName(rs.getString("NAME"));
		result.setType(rs.getString("TYPE"));
		result.setExpiredDays(rs.getInt("EXPIRED_DAYS"));
		result.setNextVaccineDays(rs.getInt("NEXT_VACCINE_DAYS"));
		result.setNotes(rs.getString("NOTES"));
		result.setCreatedDtm(rs.getTimestamp("CREATED_DTM"));
		result.setCreatedBy(rs.getString("CREATED_BY"));
		result.setLastUpdDtm(rs.getTimestamp("LASTUPD_DTM"));
		result.setLastUpdBy(rs.getString("LASTUPD_BY"));
		return result;
	}
}