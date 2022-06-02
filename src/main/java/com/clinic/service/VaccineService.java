package com.clinic.service;

import java.util.List;

import com.clinic.entity.Vaccine;

public interface VaccineService {
	List < Vaccine > getListVaccine (int userId) throws Exception;
	boolean addVaccineRecord (Vaccine vaccine) throws Exception;
}
