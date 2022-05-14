package com.clinic.service;

import java.util.Map;

import com.clinic.entity.Child;
import com.clinic.entity.HealthRecord;
import com.clinic.entity.VaccineRecord;

public interface ChildService {

	Map<String, Object> getChildByParentID(int parentId) throws Exception;

}
