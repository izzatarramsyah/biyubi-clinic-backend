package com.clinic.service;

import com.clinic.entity.Child;
import com.clinic.entity.User;

public interface UserService {

	User getUserByID (int id) throws Exception;

	User getUserByUsername (String username) throws Exception;
	
	User checkValidUser (String username, String password) throws Exception;
	Child getChildByParentID (int id) throws Exception;
	
	boolean updateLastActivity(User user) throws Exception;
	
	boolean userRegistration (User user) throws Exception;
	
	boolean childRegistration (Child child) throws Exception;
	
}
