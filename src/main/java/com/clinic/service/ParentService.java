package com.clinic.service;

import java.util.Map;

import com.clinic.entity.Parent;

public interface ParentService {

	Map<String, Object> saveParentUser(Parent parentUser) throws Exception;
	Map<String, Object> login(String username, String password) throws Exception;
	Map<String, Object> getParentUserByName(String name) throws Exception;
	
}
