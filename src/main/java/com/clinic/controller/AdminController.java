package com.clinic.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.api.object.HeaderResponse;
import com.clinic.api.request.APIRequest;
import com.clinic.api.response.APIResponse;
import com.clinic.config.variable.ApplicationConstant;
import com.clinic.constant.StatusCode;
import com.clinic.entity.Admin;
import com.clinic.entity.CheckRecord;
import com.clinic.entity.HealthRecord;
import com.clinic.entity.VaccineRecord;
import com.clinic.service.AdminService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
	
	private static final Logger LOG = LogManager.getLogger(ChildController.class);
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> login(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<Admin> req = getRequestAdminUser(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = adminService.login(req.getPayload().getUsername(), req.getPayload().getPassword());
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
	
	@RequestMapping(value = "/checkSession", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> checkSession(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<Admin> req = getRequestAdminUser(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = adminService.isValidLoginSession
					(req.getPayload().getUsername(), req.getPayload().getPassword(), 
							req.getHeader().getSession(), ApplicationConstant.USER_IDLE_TIMEOUT);
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
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> logout(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<Admin> req = getRequestAdminUser(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = adminService.logout(req.getPayload());
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
	 
	@RequestMapping(value = "/saveHealthRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> saveHealthRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<HealthRecord> req = getRequestChildCheckHealth(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = adminService.saveHealthRecord(req.getPayload());
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
		
	@RequestMapping(value = "/saveVaccineRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> saveVaccineRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<VaccineRecord> req = getRequestChildVaccine(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = adminService.saveVaccineRecord(req.getPayload());
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
	
	@RequestMapping(value = "/getHealthRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> getHealthRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		Map<String, Object> result ;
		try{
			APIRequest<HealthRecord> req = getRequestChildCheckHealth(input);
			if (req.getHeader().getCommand().equals("get-param")) {
				result = adminService.getHealthRecord(req.getPayload(), req.getHeader().getStartDate(), req.getHeader().getEndDate());
			} else {
				result = adminService.getHealthRecord(req.getPayload());
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

	@RequestMapping(value = "/getVaccineRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> getVaccineRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		Map<String, Object> result ;
		try{
			APIRequest<VaccineRecord> req = getRequestChildVaccine(input);
			if (req.getHeader().getCommand().equals("get-param")) {
				result = adminService.getVaccineRecord(req.getPayload(), req.getHeader().getStartDate(), req.getHeader().getEndDate());
			} else {
				result = adminService.getVaccineRecord(req.getPayload());
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
	
	@RequestMapping(value = "/saveCheckRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> saveCheckRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<CheckRecord> req = getRequestCheckRecord(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = adminService.saveCheckRecord(req.getPayload());
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
	
	@RequestMapping(value = "/getCheckRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> getCheckRecord(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		Map<String, Object> result ;
		try{
			APIRequest<CheckRecord> req = getRequestCheckRecord(input);
			if (req.getHeader().getCommand().equals("get-param")) {
				result = adminService.getCheckRecord(req.getPayload(), req.getHeader().getStartDate(), req.getHeader().getEndDate());
			} else {
				result = adminService.getCheckRecord(req.getPayload());
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
	
}
