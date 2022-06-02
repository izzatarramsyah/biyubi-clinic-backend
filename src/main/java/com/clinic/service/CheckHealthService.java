package com.clinic.service;

import java.util.List;
import com.clinic.entity.CheckHealth;

public interface CheckHealthService {
	List < CheckHealth > getListVaccine (int userId) throws Exception;
	boolean addCheckHealthRecord (CheckHealth checkHealth) throws Exception;
}
