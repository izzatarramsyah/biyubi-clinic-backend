package com.clinic.datamapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.clinic.entity.Notification;
import com.clinic.util.Util;

public class NotificationMapper implements RowMapper<Notification> {
	@Override
	public Notification mapRow(ResultSet rs, int row) throws SQLException {
		Notification result = new Notification();
		result.setId(rs.getInt("ID"));
		result.setUserId(rs.getInt("USER_ID"));
		result.setNotifCode(rs.getString("NOTIF_CODE"));
		result.setMessageDetail(rs.getString("MESSAGE_DETAIL"));
		result.setStatus(rs.getString("STATUS"));
		result.setNotifDate(Util.formatDate(rs.getDate("CREATED_DTM")));
		result.setCreatedDtm(rs.getTimestamp("CREATED_DTM"));
		result.setCreatedBy(rs.getString("CREATED_BY"));
		result.setLastUpdDtm(rs.getTimestamp("LASTUPD_DTM"));
		result.setLastUpdBy(rs.getString("LASTUPD_BY"));
		return result;
	}
}