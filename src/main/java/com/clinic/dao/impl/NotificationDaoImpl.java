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
import com.clinic.dao.NotificationDao;
import com.clinic.datamapper.MstNotificationMapper;
import com.clinic.datamapper.NotificationMapper;
import com.clinic.entity.MstNotification;
import com.clinic.entity.Notification;

@Repository 
public class NotificationDaoImpl implements NotificationDao {
	
	private static final Logger LOG = LogManager.getLogger(NotificationDaoImpl.class);

	public static final String ADD_NOTIF = "INSERT INTO TBL_USER_NOTIFICATION (USER_ID, NOTIF_CODE, MESSAGE_DETAIL, STATUS, CREATED_DTM, CREATED_BY) "
			+ "VALUES (?,?,?,?,?,?) "; 

	public static final String GET_MST_NOTIF = "SELECT ID, NOTIF_CODE, MESSAGE, CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY"
			+ "FROM TBL_MST_NOTIFICATION "
			+ "WHERE NOTIF_CODE = ? "; 

	public static final String GET_LIST_NOTIF = "SELECT ID, USER_ID, NOTIF_CODE, MESSAGE_DETAIL, STATUS, CREATED_DTM, CREATED_BY, LASTUPD_DTM, LASTUPD_BY "
			+ "FROM TBL_USER_NOTIFICATION "
			+ "WHERE USER_ID = ? "; 

	@Autowired
	@Qualifier(ApplicationConstant.BEAN_JDBC_CLINIC)
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean addNotif(Notification notif) {
		LOG.traceEntry();
		LOG.debug("SQL::{}", ADD_NOTIF);
		int result = 0;
		try{ 
			result = jdbcTemplate.update(ADD_NOTIF, 
					new Object[] { notif.getUserId(), notif.getNotifCode(), notif.getMessageDetail(),notif.getStatus(), 
							notif.getCreatedDtm(), notif.getCreatedBy() });
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage());
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result == 0 ? false : true;
	}
	
	@Override
	public List < Notification > getListNotification(int userId) {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_LIST_NOTIF);
		List < Notification > result = new ArrayList < Notification >();
		try{
			result = jdbcTemplate.query(GET_LIST_NOTIF, 
					new Object[] { userId }, new NotificationMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result ;
	}
	
	@Override
	public MstNotification getMstNotification (String notifCode) {
		LOG.traceEntry();
		LOG.debug("SQL::{}", GET_MST_NOTIF);
		List<MstNotification> result = new ArrayList <MstNotification>();
		try{
			result = jdbcTemplate.query(GET_MST_NOTIF, new Object[] { notifCode }, new MstNotificationMapper());
		}catch (Exception e){
			LOG.error("ERR :: {}", e.getMessage()); 
		}
		LOG.debug("RESULT::{}", result);
		LOG.traceExit();
		return result.size() > 0 ? result.get(0) : new MstNotification ();
	}
	
}
