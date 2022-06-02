package com.clinic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.NotificationDao;
import com.clinic.entity.MstNotification;
import com.clinic.entity.Notification;
import com.clinic.service.NotificationService;
import com.clinic.util.Util;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	NotificationDao notificationDao;

	@Override
	public boolean addNotif(Notification notif) {
		return notificationDao.addNotif(notif);
	}

	@Override
	public List <Notification > getListNotification(int userId) {
		return notificationDao.getListNotification(userId);
	}

	@Override
	public MstNotification getMstNotification(String notifCode) {
		return notificationDao.getMstNotification(notifCode);
	}
	

}
