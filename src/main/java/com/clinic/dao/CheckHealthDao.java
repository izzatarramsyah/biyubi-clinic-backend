package com.clinic.dao;

import java.util.List;
import com.clinic.entity.CheckHealth;

public interface CheckHealthDao {
	List < CheckHealth > getListCheckHealth(int userId) throws Exception;
	boolean addCheckHealthRecord(CheckHealth checkHealth) throws Exception;
}
