package com.clinic.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.api.object.HeaderResponse;
import com.clinic.api.request.APIRequest;
import com.clinic.api.response.APIResponse;
import com.clinic.constant.StatusCode;
import com.clinic.entity.MstVaccine;
import com.clinic.entity.MstMedicine;
import com.clinic.entity.MstTreatment;
import com.clinic.entity.MstType;
import com.clinic.service.MstService;

@CrossOrigin
@RestController
@RequestMapping("/mst")
public class MstController extends BaseController {
	
	private static final Logger LOG = LogManager.getLogger(MstController.class);

	@Autowired
	MstService mstService;
	
	@RequestMapping(value = "/getMstVaccine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> getMstVaccine(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		Map<String, Object> result = null;
		try{
			APIRequest<MstVaccine> req = getRequestMstVaccine(input);
			LOG.info("REQ::{}", req.toString());
			if (req.getHeader().getCommand().equals("get-all")){
				result = mstService.getMstVaccine();
			} else if (req.getHeader().getCommand().equals("get-param")) {
				result = mstService.getMstVaccineById(req.getPayload().getId());
			}
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}

	@RequestMapping(value = "/getMstMedicines", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> getMstMedicines(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		Map<String, Object> result = null;
		try{
			APIRequest<MstMedicine> req = getRequestMstMedicines(input); 
			LOG.info("REQ::{}", req.toString());
			if (req.getHeader().getCommand().equals("get-all")){
				result = mstService.getMstMedicines();
			} else if (req.getHeader().getCommand().equals("get-param")) {
				result = mstService.getMstMedicinesById(req.getPayload().getId());
			}
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/getMstTreatments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> getMstTreatments(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		Map<String, Object> result = null;
		try{
			APIRequest<MstTreatment> req = getRequestMstTreatments(input); 
			LOG.info("REQ::{}", req.toString());
			if (req.getHeader().getCommand().equals("get-all")){
				result = mstService.getMstTreatments();
			} else if (req.getHeader().getCommand().equals("get-param")) {
				result = mstService.getMstTreatmentsById(req.getPayload().getId());
			}
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/editMstVaccine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> editMstVaccine(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<MstVaccine> req = getRequestMstVaccine(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = mstService.editMstVaccine(req.getPayload());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/editMstMedicines", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> editMstMedicines(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<MstMedicine> req = getRequestMstMedicines(input); 
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = mstService.editMstMedicines(req.getPayload());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/editMstTreatments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> editMstTreatments(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<MstTreatment> req = getRequestMstTreatments(input); 
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = mstService.editMstTreatments(req.getPayload());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/addVaccine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> addVaccine(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<MstVaccine> req = getRequestMstVaccine(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = mstService.saveMstVaccine(req.getPayload());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/addMedicine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> addMedicine(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{ 
			APIRequest<MstMedicine> req = getRequestMstMedicines(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = mstService.saveMstMedicines(req.getPayload());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/addTreatments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> addTreatments(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{ 
			APIRequest<MstTreatment> req = getRequestMstTreatments(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = mstService.saveMstTreatments(req.getPayload());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/deleteMstMedicine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> deleteMstMedicine(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<MstMedicine> req = getRequestMstMedicines(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = mstService.deleteMstMedicines(req.getPayload().getId());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/deleteMstVaccine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> deleteMstVaccine(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<MstVaccine> req = getRequestMstVaccine(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = mstService.deleteMstVaccine(req.getPayload().getId());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/deleteMstTreatments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> deleteMstTreatments(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<MstTreatment> req = getRequestMstTreatments(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = mstService.deleteMstTreatments(req.getPayload().getId());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/getMstType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> getMstType(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		Map<String, Object> result = null;
		try{
			APIRequest<MstType> req = getRequestMstType(input); 
			LOG.info("REQ::{}", req.toString());
			result = mstService.getMstType(req.getPayload().getMst_tbl());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
	@RequestMapping(value = "/addMstType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> addMstType(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		Map<String, Object> result = null;
		try{
			APIRequest<MstType> req = getRequestMstType(input); 
			LOG.info("REQ::{}", req.toString());
			result = mstService.addMstType(req.getPayload());
			response.setPayload(result);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("ERR::[{}]:{}", e.getMessage());
			statusTrx = StatusCode.GENERIC_ERROR;
			responseMsg = StatusCode.GENERIC_ERROR.toString();
		}
		response.setHeader(new HeaderResponse (statusTrx.getCode(), responseMsg));
		LOG.debug("RES::[{}]:{}", response);
		LOG.traceExit();
		return response;
	}
	
}
