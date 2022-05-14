package com.clinic.dao;

import com.clinic.entity.Child;
import com.clinic.entity.HealthRecord;
import com.clinic.entity.VaccineRecord;

public interface ChildDao {
	
	Child getChildByParentID(int parentId) throws Exception;
	
}
