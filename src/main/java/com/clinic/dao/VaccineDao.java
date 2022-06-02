package com.clinic.dao;

import java.util.List;
import com.clinic.entity.Vaccine;

public interface VaccineDao {
	List< Vaccine > getListVaccine(int userId) throws Exception;
	boolean addVaccineRecord(Vaccine mstVaccine) throws Exception;
}
