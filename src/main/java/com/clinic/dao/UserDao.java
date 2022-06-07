package com.clinic.dao;

import com.clinic.entity.Child;
import com.clinic.entity.User;

public interface UserDao {
	
	boolean insertUser (User user) throws Exception;
	
	boolean insertChild (Child child) throws Exception;
	
	boolean updateLastActivity(User user) throws Exception;
	
	User getUserByUsername (String username) throws Exception;
	
	User getUserByEmail (String email) throws Exception;
	
	User getUserByPhoneNo (String phoneno) throws Exception;
	
	User getUserByID (int id) throws Exception;
	
	Child getChildByUserID (int id) throws Exception;

}
