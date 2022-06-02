package com.clinic.service;

import java.util.List;

import com.clinic.entity.MstNotification;
import com.clinic.entity.Notification;

public interface NotificationService {
	boolean addNotif (Notification notif);
	List < Notification > getListNotification (int userId);
	MstNotification getMstNotification (String notifCode);
}
