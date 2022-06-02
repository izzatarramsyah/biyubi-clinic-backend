package com.clinic.datamapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.clinic.entity.Vaccine;

public class VaccineMapper implements RowMapper<Vaccine> {
	@Override
	public Vaccine mapRow(ResultSet rs, int row) throws SQLException {
		Vaccine result = new Vaccine();
		result.setId(rs.getInt("ID"));
		result.setUserId(rs.getInt("USER_ID"));
		result.setVaccineName(rs.getString("VACCINE_NAME"));	
		result.setVaccineType(rs.getString("VACCINE_TYPE"));	
		result.setVaccineDose(rs.getString("VACCINE_DOSE"));	
		result.setDescription(rs.getString("DESCRIPTION"));
		result.setVaccineDate(rs.getTimestamp("VACCINE_DATE"));
		result.setCreatedDtm(rs.getTimestamp("CREATED_DTM"));
		result.setCreatedBy(rs.getString("CREATED_BY"));
		result.setUpdatedDtm(rs.getTimestamp("LASTUPD_DTM"));
		result.setUpdatedBy(rs.getString("LASTUPD_BY"));
		return result;
	}
}
