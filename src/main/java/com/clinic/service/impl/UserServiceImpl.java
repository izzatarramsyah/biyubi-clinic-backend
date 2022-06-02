package com.clinic.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.UserDao;
import com.clinic.entity.Child;
import com.clinic.entity.User;
import com.clinic.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public User getUserByID (int id) throws Exception {
		User user = userDao.getUserByID(id);
		if (user != null) {
			Child child = userDao.getChildByParentID(user.getId());
			user.setChild(child);
			return user;
		}
		return null;
	}
	
	@Override
	public User getUserByUsername (String username) throws Exception {
		User user = userDao.getUserByUsername(username);
		if (user != null) {
			Child child = userDao.getChildByParentID(user.getId());
			user.setChild(child);
			return user;
		}
		return null;
	}

	@Override
	public User checkValidUser (String username, String password) throws Exception {
		User user = userDao.getUserByUsername(username);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				Child child = userDao.getChildByParentID(user.getId());
				user.setChild(child);
				return user;
			} else {
				return null;
			}
		}
		return null;
	}
	
	@Override
	public Child getChildByParentID(int id) throws Exception {
		return userDao.getChildByParentID(id);
	}
	
	@Override
	public boolean updateLastActivity (User user) throws Exception {
		return userDao.updateLastActivity(user);
	}

	@Override
	public boolean userRegistration (User user) throws Exception {
		user.setStatus("INISIATE");
		user.setCreatedDtm(new Date());
		user.setCreatedBy("SYSTEM");
		return userDao.insertUser(user);
	}

	@Override
	public boolean childRegistration (Child child) throws Exception {
		child.setCreatedDtm(new Date());
		child.setCreatedBy("SYSTEM");
		return userDao.insertChild(child);
	}

}
