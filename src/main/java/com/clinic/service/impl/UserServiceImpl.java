package com.clinic.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.UserDao;
import com.clinic.entity.Child;
import com.clinic.entity.User;
import com.clinic.service.UserService;
import com.clinic.util.Security;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public User getUserByID (int id) throws Exception {
		User user = userDao.getUserByID(id);
		if (user != null) {
			Child child = userDao.getChildByUserID(user.getId());
			user.setChild(child);
			return user;
		}
		return null;
	}
	
	@Override
	public User getUserByUsername (String username) throws Exception {
		User user = userDao.getUserByUsername(username);
		if (user != null) {
			Child child = userDao.getChildByUserID(user.getId());
			user.setChild(child);
			return user;
		}
		return null;
	}

	@Override
	public boolean checkValidUser (String username, String password) throws Exception {
		User user = userDao.getUserByUsername(username);
		if (user != null) {
			String decryptedPassword = Security.decrypt(user.getPassword());
			if (decryptedPassword.equals(password)) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Child getChildByParentID(int id) throws Exception {
		return userDao.getChildByUserID(id);
	}
	
	@Override
	public boolean updateLastActivity (User user) throws Exception {
		return userDao.updateLastActivity(user);
	}

	@Override
	public boolean userRegistration (User user) throws Exception {
		user.setPassword(Security.encrypt(user.getPassword()));
		user.setStatus("ACTIVE");
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

	@Override
	public User getUserByEmail(String email) throws Exception {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User getUserByPhoneNo(String phoneno) throws Exception {
		return userDao.getUserByPhoneNo(phoneno);
	}

}
