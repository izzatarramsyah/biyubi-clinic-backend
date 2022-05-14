package com.clinic.dao;

import com.clinic.entity.Parent;

public interface ParentDao {
	
	boolean saveParentUser(Parent parentUser) throws Exception;
	Parent getParentUser(String username) throws Exception;
	Parent getParentUser(int id) throws Exception;
	boolean updateLastActivity(String username) throws Exception;
	Parent getParentUserByName(String name) throws Exception;

}
