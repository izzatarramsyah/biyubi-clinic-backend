package com.clinic.dao;

import java.util.List;
import com.clinic.entity.Notification;
import com.clinic.entity.MstNotification;

public interface NotificationDao {
	boolean addNotif (Notification notif);
	List < Notification > getListNotification (int userId);
	MstNotification getMstNotification (String notifCode);
}
