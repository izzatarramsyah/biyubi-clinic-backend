package com.clinic.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.lang.reflect.Type;

import com.clinic.api.request.APIRequest;
import com.clinic.entity.Admin;
import com.clinic.entity.Article;
import com.clinic.entity.CheckRecord;
import com.clinic.entity.Child;
import com.clinic.entity.HealthRecord;
import com.clinic.entity.VaccineRecord;
import com.clinic.entity.MstMedicine;
import com.clinic.entity.MstTreatment;
import com.clinic.entity.MstType;
import com.clinic.entity.MstVaccine;
import com.clinic.entity.Parent;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BaseController {

	 private static final Logger LOG = LogManager.getLogger(BaseController.class);
	 
	 protected <T> T ObjectMapper(String content, Class<T> valueType)
	            throws JsonParseException, JsonMappingException, IOException {
	     LOG.traceEntry();
	     ObjectMapper mapper = new ObjectMapper();
	     T t = (T) mapper.readValue(content, valueType);
	     LOG.traceExit();
	      //return (T) mapper.readValue(content, valueType);
	     return t;  
	 }
	
	 public APIRequest<Parent> getRequestParentUser(String content) throws Exception {
		 Gson gson = new Gson();
		 Type fooType = new TypeToken<APIRequest<Parent>>(){}.getType();
		 return gson.fromJson(content, fooType);
	 }
	 
	 public APIRequest<Child> getRequestChild(String content) throws Exception {
		 Gson gson = new Gson();
		 Type fooType = new TypeToken<APIRequest<Child>>(){}.getType();
		 return gson.fromJson(content, fooType);
	 }
	 
	 public APIRequest<VaccineRecord> getRequestChildVaccine(String content) throws Exception {
		 Gson gson = new Gson();
		 Type fooType = new TypeToken<APIRequest<VaccineRecord>>(){}.getType();
		 return gson.fromJson(content, fooType);
	 }
	 
	 public APIRequest<HealthRecord> getRequestChildCheckHealth(String content) throws Exception {
		 Gson gson = new Gson();
		 Type fooType = new TypeToken<APIRequest<HealthRecord>>(){}.getType();
		 return gson.fromJson(content, fooType);
	 }

	 public APIRequest<MstVaccine> getRequestMstVaccine(String content) throws Exception {
		 Gson gson = new Gson();
		 Type fooType = new TypeToken<APIRequest<MstVaccine>>(){}.getType();
		 return gson.fromJson(content, fooType);
	 }
	 
	 public APIRequest<MstMedicine> getRequestMstMedicines(String content) throws Exception {
		 Gson gson = new Gson();
		 Type fooType = new TypeToken<APIRequest<MstMedicine>>(){}.getType();
		 return gson.fromJson(content, fooType);
	 }
	 
	 public APIRequest<Admin> getRequestAdminUser(String content) throws Exception {
		 Gson gson = new Gson();
		 Type fooType = new TypeToken<APIRequest<Admin>>(){}.getType();
		 return gson.fromJson(content, fooType);
	 }
	 
	 public APIRequest<MstTreatment> getRequestMstTreatments(String content) throws Exception {
		 Gson gson = new Gson();
		 Type fooType = new TypeToken<APIRequest<MstTreatment>>(){}.getType();
		 return gson.fromJson(content, fooType);
	 }
	 
	 public APIRequest<Article> getRequestArticle(String content) throws Exception {
		 Gson gson = new Gson();
		 Type fooType = new TypeToken<APIRequest<Article>>(){}.getType();
		 return gson.fromJson(content, fooType);
	 }
	 
	 public APIRequest<MstType> getRequestMstType(String content) throws Exception {
		 Gson gson = new Gson();
		 Type fooType = new TypeToken<APIRequest<MstType>>(){}.getType();
		 return gson.fromJson(content, fooType);
	 }

	 public APIRequest<CheckRecord> getRequestCheckRecord(String content) throws Exception {
		 Gson gson = new Gson();
		 Type fooType = new TypeToken<APIRequest<CheckRecord>>(){}.getType();
		 return gson.fromJson(content, fooType);
	 }
	 
	 
}
