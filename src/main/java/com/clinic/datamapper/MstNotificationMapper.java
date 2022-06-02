package com.clinic.datamapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.clinic.entity.MstNotification;

public class MstNotificationMapper implements RowMapper<MstNotification> {
	@Override
	public MstNotification mapRow(ResultSet rs, int row) throws SQLException {
		MstNotification result = new MstNotification();
		result.setId(rs.getInt("ID"));
		result.setNotifCode(rs.getString("NOTIF_CODE"));
		result.setMessage(rs.getString("MESSAGE"));
		result.setCreatedDtm(rs.getTimestamp("CREATED_DTM"));
		result.setCreatedBy(rs.getString("CREATED_BY"));
		result.setLastUpdDtm(rs.getTimestamp("LASTUPD_DTM"));
		result.setLastUpdBy(rs.getString("LASTUPD_BY"));
		return result;
	}
}
