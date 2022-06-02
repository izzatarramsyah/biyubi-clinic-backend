package com.clinic.dao;

import com.clinic.entity.Child;
import com.clinic.entity.User;

public interface UserDao {
	
	boolean insertUser (User user) throws Exception;
	
	boolean insertChild (Child child) throws Exception;
	
	boolean updateLastActivity(User user) throws Exception;
	
	User getUserByUsername (String username) throws Exception;
	
	User getUserByID (int id) throws Exception;
	
	Child getChildByParentID (int id) throws Exception;

}
