package com.clinic.controller;

import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.entity.Parent;
import com.clinic.service.ParentService;
import com.clinic.api.object.HeaderResponse;
import com.clinic.api.request.APIRequest;
import com.clinic.api.response.APIResponse;
import com.clinic.constant.StatusCode;

@CrossOrigin
@RestController
@RequestMapping("/parentUser")
public class ParentController extends BaseController {
	
	private static final Logger LOG = LogManager.getLogger(ParentController.class);
	
	@Autowired
	ParentService parentUserService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> login(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<Parent> req = getRequestParentUser(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = parentUserService.login(req.getPayload().getUsername(), req.getPayload().getPassword());
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
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> registration(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<Parent> req = getRequestParentUser(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = parentUserService.saveParentUser(req.getPayload());
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
	
	@RequestMapping(value = "/getParentUserByName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public APIResponse<Map<String, Object>> getParentUserByName(@RequestBody String input) {
		LOG.traceEntry();
		APIResponse<Map<String, Object>> response = new APIResponse<Map<String, Object>> ();
		StatusCode statusTrx = StatusCode.SUCCESS;
		String responseMsg = StatusCode.SUCCESS.toString();
		try{
			APIRequest<Parent> req = getRequestParentUser(input);
			LOG.info("REQ::{}", req.toString());
			Map<String, Object> result = parentUserService.getParentUserByName(req.getPayload().getFullname());
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
